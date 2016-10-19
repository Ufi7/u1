package com.u1.service;

import java.util.List;

import com.u1.model.SimpleTask;
import com.u1.model.Task;

public interface StatisticsService {
	public List<String> getExistingAssetAttributesTeamplateSection();
	public List<String> getTop100TaskName();
	public List<String> getTop100Contact();
	public List<String> getTop100TaskRootCause();
//	public List<String> getSQLQueryStringList(String sql);
//	public List<Object> getLockedAssetTypeDefinedNum(Integer assettypeid);
//	public List<Object> getLockedTaskTypeDefinedNum(Integer tasktypeid);
	
	public List<Object[]> getLast12MonthTaskCountByMonth(Integer customerId);
	public List<Object[]> getRootcauseOverview(Integer customerId);
	public int[] getTaskCountOfThisMonth(Integer customerId);
	public Object[] getHomePageData(Integer customerId);
	public List<Object[]> getSlaStasData(Integer customerid, Integer userid, String datefrom, String dateto);
	
//	public List<SimpleTask> getPendingTask();
//	public List<Object[]> getMyAssignedTask(Integer userid);
//    public List<Object[]> getMyInProgressTask(Integer userid);
    public Object[] getMyTaskSummary(Integer userid);
    
    //Component
    public List<String> getComponentFieldListByIndex(Integer index);
	
}
