package com.u1.service.impl;

import com.u1.dao.SqlDao;

import com.u1.model.Customer;
import com.u1.model.RefTask;
import com.u1.model.RefUser;
import com.u1.model.SimpleUsers;
import com.u1.model.Slaconfig;
import com.u1.model.TaskForUpdate;
import com.u1.model.TaskLog;
import com.u1.model.TaskType;
import com.u1.model.TaskWithAsset;
import com.u1.model.UserForAuthOnly;

import com.u1.service.AssetService;
import com.u1.service.TaskService;

import com.u1.util.Constants;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class TaskServiceImpl extends CommonServiceImpl implements TaskService {
    
    public void addNewTask(TaskWithAsset t, UserForAuthOnly user) {
        Calendar c = Calendar.getInstance();
        RefUser self = new RefUser(user.getUserId());

        //set status and createdBy
        String status = null;

        if (t.getAssignedTo() != null) {
            if (t.getAssignedTo().getUserId().equals(user.getUserId())) {
                status = Constants.TASK_STATUS_IN_PROGRESS;
            } else {
                status = Constants.TASK_STATUS_ASSIGNED;
            }
        } else {
            status = Constants.TASK_STATUS_CREATED;
        }

        t.setStatus(status);
        t.setEnabled(true);
        t.setCreatedBy(self);

        //construc task num, and update for next
        TaskType tt = (TaskType) commonDao.get(TaskType.class,
                t.getTaskType().getTaskTypePid());
        String definedCode = tt.getDefinedCode();
        int num = sqlDao.lockGetTaskTypeDefinedNum(t.getTaskType().getTaskTypePid());
        String numstr = String.valueOf(num);

        while (numstr.length() < Constants.AUTOGEN_NUM_LENGTH) {
            numstr = "0" + numstr;
        }

        t.setTaskNum(Constants.TASK_NUM_PREFIX.concat(String.valueOf(c.get(Calendar.YEAR)))
                                              .concat(tt.getDefinedCode())
                                              .concat(numstr));

        int spendhr = 0;

        if (t.getPiority().equals(0)) {
            spendhr = tt.getLowDefaultTime();
        } else if (t.getPiority().equals(2)) {
            spendhr = tt.getHighDefaultTime();
        } else {
            spendhr = tt.getMediumDefaultTime();
        }

        t.setStartDatetime(c.getTime());

        Calendar e = (Calendar) c.clone();
        e.add(Calendar.HOUR, spendhr);
        t.setDueDatetime(e.getTime());
        commonDao.save(t);
        createTaskLog(new RefTask(t.getTaskPid()), self, (t.getAssignedTo() != null) ? t.getAssignedTo() : null, status.substring(0,1), null, null);
    }
    
    public String updateTask(TaskForUpdate tfd, TaskLog tl,UserForAuthOnly user){
    	if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_ADD_LOG)){	//add log no need any checking
    		createTaskLog(new RefTask(tfd.getTaskPid()),new RefUser(user.getUserId()), null, tl.getActionCode(), tl.getRemark(), null);
    		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
    	}
    	List allowlt = checkMatrix(tl.getActionCode());
    	Object[] statusAndAsiToId = sqlDao.lockGetTaskStatusAndAssignedToId(tfd.getTaskPid());
    	String currentStatus = (String)statusAndAsiToId[0];
    	Integer asiToId = (Integer)statusAndAsiToId[1];
    	Integer customerId = (Integer)statusAndAsiToId[2];
    	Util.checkCrossCustomerProcess(user, new Customer(customerId));//add checking for cross company
    	boolean actionAllow = false;
    	Object[] matchRow = null;
    	for(int i=0;i<allowlt.size();i++){
    		matchRow = (Object[])allowlt.get(i);
    		if(currentStatus.equals(matchRow[0])){//match current status
    			if((boolean)matchRow[3]){// need self action
    				if(asiToId.equals(user.getUserId())){
    					actionAllow = true;
        				break;
    				}else{
    					break;
    				}
    			}else{
    				actionAllow = true;
    				break;
    			}
    		}
    	}
    	if(!actionAllow){//not allow
    		return Constants.HTML_PREFIX_CONTENT_ERROR+MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR");
    	}
    	String autoremak = null;
    	if(matchRow[2]!=null || (int)matchRow[4]!=0 || tl.getActionCode().equals(Constants.TASK_ACTION_CODE_REVALUE)){//need to update task record
    		TaskForUpdate realOne = (TaskForUpdate)get(TaskForUpdate.class, tfd.getTaskPid());
    		if(matchRow[2]!=null){
    			realOne.setStatus((String)matchRow[2]);
    		}
    		if((int)matchRow[4]==1){
    			realOne.setAssignedTo(new RefUser(user.getUserId()));
    		}else if((int)matchRow[4]==2){
    			realOne.setAssignedTo(tl.getAssignedTo());
    		}else if((int)matchRow[4]==3){
    			realOne.setAssignedTo(null);
    		}
    		if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_DONE)){
    			realOne.setRootcause(tfd.getRootcause());
    			realOne.setSolution(tfd.getSolution());
    			realOne.setEndDatetime(Calendar.getInstance().getTime());
    			//TODO task score handling
    			if(!realOne.getEndDatetime().after(realOne.getDueDatetime())){
    				realOne.setScore(100);
    			}else{
    				calcScore(realOne, (Slaconfig)commonDao.get(Slaconfig.class, 1));
    			}
    		}
    		if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_REVALUE)){
    			if(realOne.getEndDatetime()!=null && Calendar.getInstance().getTimeInMillis()-realOne.getEndDatetime().getTime()>Constants.MAX_REVALUALBE_TASK_PERIOD){
    				return Constants.HTML_PREFIX_CONTENT_ERROR+MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR");
    			}
    			if(realOne.getScore()!=null){
    				autoremak = realOne.getScore().toString();
    			}
    			realOne.setScore(tfd.getScore());
    		}
    		update(realOne);
    	}
    	createTaskLog(new RefTask(tfd.getTaskPid()), new RefUser(user.getUserId()), tl.getActionCode().equals(Constants.TASK_ACTION_CODE_ASSIGN)?tfd.getAssignedTo():null, tl.getActionCode(), tl.getRemark(), autoremak);
    	return Constants.HTML_PREFIX_CONTENT_SUCCESS;
    }
    
    public List checkMatrix(String actionCode){
    	List allowList = new ArrayList();
    	for(int i=0;i<Constants.STATUS_ACTIONCODE_MATRIX.length;i++){
    		if(actionCode.equals(Constants.STATUS_ACTIONCODE_MATRIX[i][1])){
    			allowList.add(Constants.STATUS_ACTIONCODE_MATRIX[i]);
    		}
    	}
    	return allowList;
    }
    
    public void calcScore(TaskForUpdate t, Slaconfig sla){
    	long timeout = t.getEndDatetime().getTime() - t.getDueDatetime().getTime();;
    	int score;
    	if(sla.getSeloption()==1){// calculate based on timeout count
    		score = 100 - sla.getOption1deduction()*((new Long(timeout/(1000*60*60)).intValue()+(timeout%(1000*60*60)==0?0:1)));
    	}else{// calculate based on timeout percentage
    		long targetspan = t.getDueDatetime().getTime() - t.getStartDatetime().getTime();
    		long calunit = targetspan*sla.getOption2inteval()/100;
    		score = 100 - sla.getOption2deduction()*new Long(timeout/calunit).intValue() - (timeout%calunit==0?0:1)*sla.getOption2deduction();
    	}
    	if(score<0 || score>100){
    		score=0;
    	}
    	t.setScore(score);
    }

    public void createTaskLog(RefTask task, RefUser owner,RefUser assignedTo, String actionCode, String remark, String autoremark) {
    	TaskLog tl = new TaskLog(task, actionCode, assignedTo, owner, remark, autoremark);
        commonDao.save(tl);
    }

    protected SqlDao sqlDao;
    public void setSqlDao(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }
}
