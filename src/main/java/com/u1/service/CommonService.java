package com.u1.service;

import java.util.List;

import com.u1.model.Condition;
import com.u1.model.Order;
import com.u1.model.SearchResult;

public interface CommonService<M, ID>{
	public ID save(M model);
	public void saveOrUpdate(M model);
	public void update(M model);
	public M merge(M model);
	public void myMerge(M model, ID id);
	public void myMerge(M model, ID id, int updateChildrenEntityDepth);
	public void delete(M model);
	public M get(Class<M> entityClass, ID id);
	public M getByEmail(Class<M> entityClass, String email);
	public M getByNickname(Class<M> entityClass, String nickname);
	public M getByUsername(Class<M> entityClass, String username);
	public M getByUrlname(Class<M> entityClass, String urlname);
	public List<M> searchByNickname(Class<M> entityClass, String nickname);
	public boolean checkStatus(Class<M> entityClass, ID id);
	public int disable(Class<M> entityClass, ID id);
	public int enable(Class<M> entityClass, ID id);
	public int delete(Class<M> entityClass, ID id);
	public Integer countAll(Class<M> entityClass);
	public Integer countValid(Class<M> entityClass);
	public Integer countInvalid(Class<M> entityClass);
	public List<M> listAll(Class<M> entityClass);
	public List<M> listValid(Class<M> entityClass);
	public List<M> listInvalid(Class<M> entityClass);
	public int updateFieldWithValueById(Class<M> entityClass, String fieldname, Object value, ID id);
	public boolean checkFieldValueExisted(Class<M> entityClass, String fieldname, Object value);
	public int countByMultiCondition(Class<M> entityClass, List<Condition> conditions);
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders);
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int index);
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int sizePerPage, int index);
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders);
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int index);
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int sizePerPage, int index);
	public SearchResult addNewAndReturnUpdatedList(M model,List<Condition> conditions, List<Order> orders);
	public Object[] getAbAndAllB(Object akey, Class a, Class b);
	public Object[] getAlistAndOtherFullList(Class<M> entityClass, List<Condition> conditions, List<Order> orders, Class[] classList);
	public void saveObjects(Object[] objects);
	//testing
	public void test();
}
