package com.u1.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.u1.dao.SqlDao;
import com.u1.exception.U1Exception;
import com.u1.model.Component;
import com.u1.model.Componentrecord;
import com.u1.model.Componentstore;
import com.u1.model.Condition;
import com.u1.model.Order;
import com.u1.model.RefTask;
import com.u1.model.SearchResult;
import com.u1.model.TaskLog;
import com.u1.service.ComponentService;
import com.u1.util.Constants;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;

public class ComponentServiceImpl extends CommonServiceImpl implements
		ComponentService {
	@Override
	public void addNewComponentStore(Componentstore store, Componentrecord record) {
		// TODO Auto-generated method stub
		commonDao.save(store);
		record.setComponentstore(store);
		commonDao.save(record);
	}

	@Override
	public void updateComponentRecord(Integer storeId, Integer verifyCustomerId, Componentrecord record) {
		// TODO Auto-generated method stub
		Object[] storeIdAndCount = sqlDao.lockgetComponentStoreCustomerIdAndCount(storeId);
		Integer customerId = (Integer)storeIdAndCount[0];
		Integer count = (Integer)storeIdAndCount[1];
		if(verifyCustomerId != 0){
			if(!customerId.equals(verifyCustomerId)){
				//handle cross company exception.
				throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));
			}
		}
		if(record.getReason()==3 || record.getReason()==4 || record.getReason()==7){
			if(record.getQuantity()>count){
				Componentstore store = (Componentstore)get(Componentstore.class, storeId);
				String name = store.getComponent().getField1() + "/" + store.getComponent().getField2()+ "/" + store.getComponent().getField3()+ "/" + store.getComponent().getField4();
				throw new U1Exception(MessageSourceHelper.getMessage(Constants.ERROR_UPDATE_COMPONENT_STORE, new Object[]{name, count}));
			}
			count = count - record.getQuantity();
		}else{
			count = count + record.getQuantity();
		}
		sqlDao.updateComponentStoreCount(storeId, count);
		record.setLogQuantity(count);
		commonDao.save(record);
	}

	@Override
	public void consumeComponent(TaskLog tasklog, List<Componentrecord> recordlist) {
		// TODO Auto-generated method stub
		Object[] statusAndAsiToId = sqlDao.lockGetTaskStatusAndAssignedToId(tasklog.getTask().getTaskPid());//lock task, to check the status & assigned to
    	String currentStatus = (String)statusAndAsiToId[0];
    	Integer asiToId = (Integer)statusAndAsiToId[1];
    	if(!Constants.TASK_STATUS_IN_PROGRESS.equals(currentStatus) || !tasklog.getOwner().getUserId().equals(asiToId)){
    		throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));
    	}
		save(tasklog);
		for(Componentrecord record:recordlist){
			record.setTaskLog(tasklog);
			updateComponentRecord(record.getComponentstore().getComponentStoreId(), 0, record);
		}
	}
	
	public List<Object> getComponentstoreStatistics(Integer storeid){
		List<Object> returnlist = new ArrayList();
		returnlist.add(Util.build12MonthData(sqlDao.getLast12MonthsComponentstoreAveragePriceByMonth(storeid), true, true));
		returnlist.add(Util.build12MonthData(sqlDao.getLast12MonthsComponentstoreUsageByMonth(storeid), true, true));
		return returnlist;
	}
	
	public Double getComponentstoreLatestPrice(Integer storeid){
		return sqlDao.getComponentstoreLatestPrice(storeid);
	}
	
	public List<Object> getConsumedComponentOfTask(Integer taskid){
		//return sqlDao.getConsumedComponentOfTask(taskid);
		List<Condition> cs = new ArrayList();
		Condition c = new Condition("taskLog/task", new RefTask(taskid));
		cs.add(c);
		List<Order> os = new ArrayList();
		Order o = new Order("componentstore");
		os.add(o);
		List<Componentrecord> recordlist = commonDao.searchByMultiCondition(Componentrecord.class, cs, os, 0, 0);
		List returnList = new ArrayList();
		Componentstore previous=null;
		Integer count = 0;
		for(Componentrecord r:recordlist){
			if(!r.getComponentstore().equals(previous)){
				if(previous!=null)
					returnList.add(new Object[]{previous,count});
				previous = r.getComponentstore();
				count = r.getQuantity();
			}else{
				count+=r.getQuantity();
			}
		}
		if(previous!=null){
			returnList.add(new Object[]{previous,count});
		}
		return returnList;
	}
	
	protected SqlDao sqlDao;
    public void setSqlDao(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }
}
