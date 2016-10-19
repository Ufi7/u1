package com.u1.dao;

import java.util.List;

import com.u1.model.Componentstore;


public interface SqlDao {
    public List<String> getExistingAssetAttributesTeamplateSection();

    public List<String> getTop100TaskName();

    public List<String> getTop100Contact();

    public List<String> getTop100TaskRootCause();

    public int lockGetAssetTypeDefinedNum(Integer assettypeid);

    public int lockGetTaskTypeDefinedNum(Integer tasktypeid);
    
    public Object[] lockGetTaskStatusAndAssignedToId(Integer taskid);
    
    public List<Object[]> getLast12MonthTaskCountByMonth(Integer customerId);
    
    public List<Object[]> getTaskStasByUserAndDateRange(Integer customerid, Integer userid, String dateFrom, String dateTo);
    
    public List<Object[]> getRootcauseOverview(Integer customerId);
    
    public List<Object[]> getTaskCountOfThisMonth(Integer customerId);
    
//    public List<Object[]> getPendingTask();
//    
//    public List<Object[]> getMyAssignedTask(Integer userid);
//    
//    public List<Object[]> getMyInProgressTask(Integer userid);
    
    public Long getMyDoneTaskCountInThisMonth(Integer userid);
    
    public List<String> getComponentFieldListByIndex(Integer index);
    
    public List<Object[]> getLast12MonthsComponentstoreAveragePriceByMonth(Integer storeid);
    
    public List<Object[]> getLast12MonthsComponentstoreUsageByMonth(Integer storeid);
    
    public Double getComponentstoreLatestPrice(Integer storeid);
    
    public Object[] lockgetComponentStoreCustomerIdAndCount(Integer storeid);
    
    public Integer lockgetComponentStoreAndCount(Integer storeid);
    
    public void updateComponentStoreCount(Integer storeId, Integer count);
}
