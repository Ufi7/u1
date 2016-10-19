package com.u1.service;

import java.util.List;

import com.u1.model.Component;
import com.u1.model.Componentrecord;
import com.u1.model.Componentstore;
import com.u1.model.TaskLog;

public interface ComponentService extends CommonService {
	public void addNewComponentStore(Componentstore store, Componentrecord record);
	
	public void updateComponentRecord(Integer componentstoreId, Integer verifyCustomerId, Componentrecord record);
	
	public void consumeComponent(TaskLog tasklog, List<Componentrecord> recordlist);
	
	public List<Object> getComponentstoreStatistics(Integer storeid);
	
	public Double getComponentstoreLatestPrice(Integer storeid);
	
	public List<Object> getConsumedComponentOfTask(Integer taskid);
}
