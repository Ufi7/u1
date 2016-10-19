package com.u1.dao.hibernateimpl;

import com.u1.dao.SqlDao;
import com.u1.exception.U1Exception;
import com.u1.model.Componentstore;
import com.u1.model.Customer;
import com.u1.model.RefTask;
import com.u1.model.RefUser;
import com.u1.model.SimpleUsers;
import com.u1.util.Constants;
import com.u1.util.DateUtil;
import com.u1.util.MessageSourceHelper;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class SqlDaoImpl implements SqlDao {
	protected SessionFactory sessionFactory;

	private final String SQL_GET_EXSITING_SECTION = "select distinct section from asset_attributes_template where enabled=1 and section != '' order by section";
    //	private final String SQL_GET_TOP100_TASKNAME="SELECT task_name FROM task_items where task_name!='' group by task_name order by count(*) desc, task_pid desc limit 0, 100;";
    private final String SQL_GET_TOP100_TASKNAME = "SELECT task_name FROM task_items where enabled=1 and task_name!='' group by task_name order by count(*) desc,task_name asc";
    private final String SQL_GET_TOP100Contact = "SELECT contact FROM task_items where enabled=1 and contact!='' group by contact order by count(*) desc,contact asc";
    private final String SQL_GET_TOP100TaskRootCause = "SELECT ROOT_CAUSE FROM TASK_ITEMS WHERE ROOT_CAUSE!='' GROUP BY ROOT_CAUSE ORDER BY count(*) asc";
    
    
    @Override
    public List<String> getExistingAssetAttributesTeamplateSection() {
        return getSQLQueryStringList(SQL_GET_EXSITING_SECTION);
    }

    public List<String> getTop100TaskName() {
        return getSQLQueryStringList(SQL_GET_TOP100_TASKNAME);
    }

    public List<String> getTop100Contact() {
        return getSQLQueryStringList(SQL_GET_TOP100Contact);
    }

    public List<String> getTop100TaskRootCause() {
        return getSQLQueryStringList(SQL_GET_TOP100TaskRootCause);
    }

    public List<String> getSQLQueryStringList(String sql) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    public int lockGetAssetTypeDefinedNum(Integer assettypeid) {
        String enqsql = "select DEFINED_NUM from asset_type where ASSET_TYPE_PID=:assetid for update";
        String updsql = "update asset_type set DEFINED_NUM=:num where ASSET_TYPE_PID=:assetid";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(enqsql);
        query.setParameter("assetid", assettypeid);
        int value=(int) query.list().get(0) + 1;
        query = sessionFactory.getCurrentSession().createSQLQuery(updsql);
        query.setParameter("num", value);
        query.setParameter("assetid", assettypeid);
        query.executeUpdate();
        return value;
    }

    public int lockGetTaskTypeDefinedNum(Integer tasktypeid) {
    	String enqsql = "select DEFINED_NUM from task_type where TASK_TYPE_PID=:taskid for update";
        String updsql = "update task_type set DEFINED_NUM=:num where TASK_TYPE_PID=:taskid";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(enqsql);
        query.setParameter("taskid", tasktypeid);
        int value=(int) query.list().get(0) + 1;
        query = sessionFactory.getCurrentSession().createSQLQuery(updsql);
        query.setParameter("num", value);
        query.setParameter("taskid", tasktypeid);
        query.executeUpdate();
        return value;
    }
    
    public Object[] lockGetTaskStatusAndAssignedToId(Integer taskid){
    	String enqsql = "select STATUS, ASSIGNED_TO, CUSTOMER_ID from task_items where TASK_PID=:taskid for update";
        Query query = sessionFactory.getCurrentSession().createSQLQuery(enqsql);
        query.setParameter("taskid", taskid);
        return (Object[])query.list().get(0);
    }
    
    public List<Object[]> getLast12MonthTaskCountByMonth(Integer customerId){
    	Calendar c = Calendar.getInstance();
    	String hql = "select count(*), avg(t.score), year(t.endDatetime),month(t.endDatetime) from Task as t where t.enabled=1 and t.status ='done' and t.endDatetime between '"
				.concat(DateUtil.get1stDayOfCurrentMonthInLastYear(c)).concat("' and '").concat(DateUtil.getLastDayOfLastMonth(c)).concat(" 23:59:59.999'");
    	if(!customerId.equals(0)){
    		hql += " and t.customer = :customer";
    	}
    	hql += " group by year(t.endDatetime), month(t.endDatetime) order by t.endDatetime asc";
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	if(!customerId.equals(0)){
    		query.setParameter("customer", new Customer(customerId));
    	}
		return query.list();
    }
    
    public List<Object[]> getTaskStasByUserAndDateRange(Integer customerid, Integer userid, String dateFrom, String dateTo){
    	String hql = "select count(*), avg(t.score), year(t.endDatetime),month(t.endDatetime) from Task as t where t.enabled=1 and t.status ='done' ";
    	if(!dateFrom.isEmpty() && !dateTo.isEmpty()){
    		hql = hql.concat("and t.endDatetime between '").concat(dateFrom).concat("' and '").concat(dateTo).concat(" 23:59:59.999' ");
    	}else{
    		Calendar c = Calendar.getInstance();
    		hql = hql.concat("and t.endDatetime between '").concat(DateUtil.get1stDayOfCurrentMonthInLastYear(c)).concat("' and '").concat(DateUtil.getLastDayOfLastMonth(c)).concat(" 23:59:59.999' ");
    	}
    	if(customerid!=null){
    		hql = hql.concat(" and t.customer = :customer ");
    	}
    	if(userid!=null){
    		hql = hql.concat(" and t.assignedTo = :user ");
    	}
    	hql = hql.concat(" group by year(t.endDatetime), month(t.endDatetime) order by t.endDatetime asc");
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	if(customerid!=null){
    		query.setParameter("customer", new Customer(customerid));
    	}
    	if(userid!=null){
    		query.setParameter("user", new RefUser(userid));
    	}
		return query.list();
    }
    
    public List<Object[]> getTaskCountOfThisMonth(Integer customerId){
    	String hql = "Select count(*), t.status from Task as t where t.enabled=1 and t.createdDatetime > :createdDateTime";
    	if(!customerId.equals(0)){
    		hql += " and t.customer = :customer";
    	}
    	hql += " group by t.status order by t.status asc";
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	query.setParameter("createdDateTime", DateUtil.getStartOfThisMonth(Calendar.getInstance()).getTime());
    	if(!customerId.equals(0)){
    		query.setParameter("customer", new Customer(customerId));
    	}
    	return query.list();
    }
    
    public List<Object[]> getRootcauseOverview(Integer customerId){
    	String hql = "select count(*), t.rootcause from Task as t where t.enabled=1 and t.rootcause !=''";
    	if(!customerId.equals(0)){
    		hql += " and t.customer = :customer";
    	}
    	hql += " group by t.rootcause order by count(*) desc";
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	if(!customerId.equals(0)){
    		query.setParameter("customer", new Customer(customerId));
    	}
		return query.list();
    }
    
//    public List<Object[]> getPendingTask(){
//    	String hql = "Select t.taskPid, t.taskNum, t.taskType.code, t.piority, t.taskName, t.createdDatetime from Task t where t.enabled=1 and t.status = ? or t.status = ? order by t.createdDatetime desc";
//    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
//    	query.setParameter(0, Constants.TASK_STATUS_CREATED);
//    	query.setParameter(1, Constants.TASK_STATUS_PENDING_REASSIGNED);
//		return query.list();
//    }
//    
//    public List<Object[]> getMyAssignedTask(Integer userid){
//    	String hql = "Select t.taskPid, t.taskNum, t.taskType.code, t.piority, t.taskName, t.createdDatetime from Task t where t.enabled=1 and t.status = ? and t.assignedTo = ? order by t.createdDatetime desc";
//    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
//    	query.setParameter(0, Constants.TASK_STATUS_ASSIGNED);
//    	query.setParameter(1, new SimpleUsers(userid));
//		return query.list();
//    }
//    
//    public List<Object[]> getMyInProgressTask(Integer userid){
//    	String hql = "Select t.taskPid, t.taskNum, t.taskType.code, t.piority, t.taskName, t.createdDatetime from Task t where t.enabled=1 and t.status = ? and t.assignedTo = ? order by t.createdDatetime desc";
//    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
//    	query.setParameter(0, Constants.TASK_STATUS_IN_PROGRESS);
//    	query.setParameter(1, new SimpleUsers(userid));
//		return query.list();
//    }
    
    public Long getMyDoneTaskCountInThisMonth(Integer userid){
    	String hql = "Select count(*) from Task t where t.enabled=1 and t.status = :status and t.assignedTo = :userid and t.endDatetime >= :endDatetime order by t.createdDatetime desc";
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	query.setParameter("status", Constants.TASK_STATUS_DONE);
    	query.setParameter("userid", new RefUser(userid));
    	query.setParameter("endDatetime", DateUtil.getStartOfThisMonth(Calendar.getInstance()).getTime());
		return (Long)query.list().get(0);
    }
    
    public List<String> getComponentFieldListByIndex(Integer index){
    	if(index > 0 && index <= 10){
			String indexstr = index.toString();
			String sql = "select field"+indexstr+" from COMPONENT where field"+indexstr+" != '' group by field"+indexstr+ " order by count(*) desc, field"+indexstr+" asc";
			return  getSQLQueryStringList(sql);
		}else{
			return null;
		}
    }
    
    public List<Object[]> getLast12MonthsComponentstoreAveragePriceByMonth(Integer storeid){
    	Calendar c = Calendar.getInstance();
    	String hql = "select round(sum(record.price*record.quantity)/sum(record.quantity)), year(record.createdDatetime),month(record.createdDatetime) from Componentrecord as record where record.componentstore=:store and record.reason=1 and record.createdDatetime between '"
				.concat(DateUtil.get1stDayOf11MonthAgo(c)).concat("' and '").concat(Constants.SIMPLE_DATE_FORMAT.format(c.getTime())).concat(" 23:59:59.999'").concat(" group by year(record.createdDatetime), month(record.createdDatetime) order by record.createdDatetime asc");
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	query.setParameter("store", new Componentstore(storeid));
    	return query.list();
    }
    
    public List<Object[]> getLast12MonthsComponentstoreUsageByMonth(Integer storeid){
    	Calendar c = Calendar.getInstance();
    	String hql = "select sum(record.quantity), year(record.createdDatetime),month(record.createdDatetime) from Componentrecord as record where record.componentstore=:store and record.reason=3 and record.createdDatetime between '"
				.concat(DateUtil.get1stDayOf11MonthAgo(c)).concat("' and '").concat(Constants.SIMPLE_DATE_FORMAT.format(c.getTime())).concat(" 23:59:59.999'").concat(" group by year(record.createdDatetime), month(record.createdDatetime) order by record.createdDatetime asc");
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	query.setParameter("store", new Componentstore(storeid));
    	return query.list();
    }
    
    public Double getComponentstoreLatestPrice(Integer storeid){
    	String hql = "SELECT record1.price FROM Componentrecord as record1 where record1.createdDatetime = (select max(record0.createdDatetime) FROM Componentrecord as record0 where record0.componentstore = :store and record0.reason = 1) order by record1.price desc";
    	Query query=sessionFactory.getCurrentSession().createQuery(hql);
    	query.setParameter("store", new Componentstore(storeid));
    	List result =  query.list();
    	if(result.size()>0){
    		return (Double)result.get(0);
    	}else{
    		return null;
    	}
    }
    
    public Object[] lockgetComponentStoreCustomerIdAndCount(Integer storeid){
    	String sql = "select c.CUSTOMER_ID, c.COUNT from componentstore as c where c.COMPONENT_STORE_ID = :storeid for update";
    	Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
    	query.setParameter("storeid", storeid);
    	return (Object[])query.list().get(0);
    }
    
    public Integer lockgetComponentStoreAndCount(Integer storeid){
    	String sql = "select c.COUNT from componentstore as c where c.COMPONENT_STORE_ID = :storeid for update";
    	Query query=sessionFactory.getCurrentSession().createSQLQuery(sql);
    	query.setParameter("storeid", storeid);
    	return (Integer)query.list().get(0);
    }
    
    public void updateComponentStoreCount(Integer storeId, Integer count){
    	 String updsql = "update componentstore set COUNT=:count where COMPONENT_STORE_ID=:storeid";
         Query query = sessionFactory.getCurrentSession().createSQLQuery(updsql);
         query.setParameter("storeid", storeId);
         query.setParameter("count", count);
         query.executeUpdate();
    }
    	
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
