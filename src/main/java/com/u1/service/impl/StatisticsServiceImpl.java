package com.u1.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.u1.dao.CommonDao;
import com.u1.dao.SqlDao;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.SimpleTask;
import com.u1.model.SimpleUsers;
import com.u1.model.Task;
import com.u1.service.StatisticsService;
import com.u1.util.Constants;
import com.u1.util.Util;

public class StatisticsServiceImpl extends CommonServiceImpl implements StatisticsService {

	@Override
	public List<String> getExistingAssetAttributesTeamplateSection() {
		return sqlDao.getExistingAssetAttributesTeamplateSection();
	}
	
	public List<String> getTop100TaskName(){
		return sqlDao.getTop100TaskName();
	}
	public List<String> getTop100Contact(){
		return sqlDao.getTop100Contact();
	}
	public List<String> getTop100TaskRootCause(){
		return sqlDao.getTop100TaskRootCause();
	}
	
	public List<Object[]> getLast12MonthTaskCountByMonth(Integer customerId){
		List<Object[]> result = sqlDao.getLast12MonthTaskCountByMonth(customerId);
		return buildJsonArrayForGraphUse(result);
	}
	
	public List<Object[]> getTaskStasByUserAndDateRange(Integer customerid, Integer userid, String dateFrom, String dateTo){
		List<Object[]> result = sqlDao.getTaskStasByUserAndDateRange(customerid, userid, dateFrom, dateTo);;
		return buildJsonArrayForGraphUse(result);
	}
	
	public List<Object[]> buildJsonArrayForGraphUse(List<Object[]> result){
		if(result.size()==0)
			return result;
		else{
			Object[] pre, cur;
			List<Object[]> returnlist = new ArrayList();
			pre = result.get(0);
			returnlist.add(new Object[]{pre[0], pre[1],pre[2].toString()+"."+pre[3].toString()});
			for(int i=1;i<result.size();i++){
				pre = result.get(i-1);
				cur = result.get(i);
				int prey = ((Integer)pre[2]).intValue();
				int prem = ((Integer)pre[3]).intValue();
				int cury = ((Integer)cur[2]).intValue();
				int curm = ((Integer)cur[3]).intValue();
				int offset = 1;
				while(!((prey+((prem+offset)>12?1:0)==cury)&&(((prem+offset==12)?12:(prem+offset)%12)==curm))){
					
					returnlist.add(new Object[]{null, null, ""+(prey+((prem+offset)>12?1:0))+"."+((prem+offset==12)?12:(prem+offset)%12)});
					offset++;
				}
				returnlist.add(new Object[]{cur[0], cur[1],cur[2].toString()+"."+cur[3].toString()});
			}
			return returnlist;
		}
	}
	
	public List<Object[]> getRootcauseOverview(Integer customerId){
		return sqlDao.getRootcauseOverview(customerId);
	}
	
	
	public int[] getTaskCountOfThisMonth(Integer customerId){
//		return sqlDao.getTaskCountOfThisMonth();
		List<Object[]> ol = sqlDao.getTaskCountOfThisMonth(customerId);
		int in_progress=0, assigned=0, pendingassigned=0,done=0;
		for(int i=0;i<ol.size();i++){
			Object[] o = ol.get(i);
			String status = (String)o[1];
			if(status.equals(Constants.TASK_STATUS_ASSIGNED)){
				assigned+=((Long)o[0]).intValue();
			}else if(status.equals(Constants.TASK_STATUS_CREATED)){
				pendingassigned+=((Long)o[0]).intValue();
			}else if(status.equals(Constants.TASK_STATUS_PENDING_REASSIGNED)){
				pendingassigned+=((Long)o[0]).intValue();
			}else if(status.equals(Constants.TASK_STATUS_DONE)){
				done+=((Long)o[0]).intValue();
			}else if(status.equals(Constants.TASK_STATUS_IN_PROGRESS)){
				in_progress+=((Long)o[0]).intValue();
			}
		}
		return new int[]{pendingassigned,assigned,in_progress,done};
	}
	
//	public List<SimpleTask> getPendingTask(){
//		List<Condition> cs = Util.initEnabledCondition();
//		Condition c;
//		String[] vs = new String[]{Constants.TASK_STATUS_ASSIGNED, Constants.TASK_STATUS_PENDING_REASSIGNED};
//		c = new Condition("status", vs);
//		cs.add(c);
//		c = new Condition("customerId", new Integer(1));
//		cs.add(c);
//		return commonDao.searchByMultiCondition(SimpleTask.class, cs, null, 0, 0);
//	}
	
	
	
//	public List<Object[]> getMyAssignedTask(Integer userid){
//		return sqlDao.getMyAssignedTask(userid);
//	}
//    public List<Object[]> getMyInProgressTask(Integer userid){
//    	return sqlDao.getMyInProgressTask(userid);
//    }
    
    
    public Object[] getMyTaskSummary(Integer userid){
    	Object[] os = new Object[3];
    	//SimpleUsers su = new SimpleUsers(userid);
    	Condition c;List<Condition> cs;
    	cs = Util.initEnabledCondition();
    	c = new Condition("assignedToId", userid);
    	cs.add(c);
    	c = new Condition("status", Constants.TASK_STATUS_ASSIGNED);
    	cs.add(c);
    	os[0] = commonDao.searchByMultiCondition(SimpleTask.class, cs, null, 0, 0);
//    	os[0] = sqlDao.getMyAssignedTask(userid);
    	cs = Util.initEnabledCondition();
    	c = new Condition("assignedToId", userid);
    	cs.add(c);
    	c = new Condition("status", Constants.TASK_STATUS_IN_PROGRESS);
    	cs.add(c);
    	os[1] = commonDao.searchByMultiCondition(SimpleTask.class, cs, null, 0, 0);
//    	os[1] = sqlDao.getMyInProgressTask(userid);
    	os[2] = sqlDao.getMyDoneTaskCountInThisMonth(userid);
    	return os;
    }
	
	public Object[] getHomePageData(Integer customerId){
		Object[] os = new Object[3];
		os[0]=getLast12MonthTaskCountByMonth(customerId);
		os[1]=getRootcauseOverview(customerId);
		os[2]=getTaskCountOfThisMonth(customerId);
		return os;
	}
	
	public List<Object[]> getSlaStasData(Integer customerid, Integer userid, String dateFrom, String dateTo){
		return getTaskStasByUserAndDateRange(customerid, userid, dateFrom, dateTo);
	}
	
	public List<String> getComponentFieldListByIndex(Integer index){
		return sqlDao.getComponentFieldListByIndex(index);
	}

	protected SqlDao sqlDao;
	public void setSqlDao(SqlDao sqlDao){
		this.sqlDao=sqlDao;
	}
}
