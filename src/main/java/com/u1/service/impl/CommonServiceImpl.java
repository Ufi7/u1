package com.u1.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.u1.dao.CommonDao;
import com.u1.model.Condition;
import com.u1.model.Order;
import com.u1.model.SearchResult;
import com.u1.service.CommonService;

public class CommonServiceImpl<M extends java.io.Serializable, ID extends java.io.Serializable> implements CommonService<M, ID>{
	
	public ID save(M model){
		return (ID)commonDao.save(model);
	}
	public void saveOrUpdate(M model){
		commonDao.saveOrUpdate(model);
	}
	public void update(M model){
		commonDao.update(model);
	}
	public M merge(M model){
		return (M)commonDao.merge(model);
	}
	public void myMerge(M model, ID id){
		commonDao.myMerge(model, id);
	}
	public void myMerge(M model, ID id, int updateChildrenEntityDepth){
		commonDao.myMerge(model, id, updateChildrenEntityDepth);
	}
	public void delete(M model){
		commonDao.delete(model);
	}
	public M get(Class<M> entityClass, ID id){
		return (M)commonDao.get(entityClass, id);
	}
	public M getByEmail(Class<M> entityClass, String email){
		return (M)commonDao.getByEmail(entityClass, email);
	}
	public M getByNickname(Class<M> entityClass, String nickname){
		return (M)commonDao.getByNickname(entityClass, nickname);
	}
	public M getByUsername(Class<M> entityClass, String username){
		return (M)commonDao.getByUsername(entityClass, username);
	}
	public M getByUrlname(Class<M> entityClass, String urlname){
		return (M)commonDao.getByUrlname(entityClass, urlname);
	}
	public List<M> searchByNickname(Class<M> entityClass, String nickname){
		return commonDao.searchByNickname(entityClass, nickname);
	}
	public boolean checkStatus(Class<M> entityClass, ID id){
		return commonDao.checkStatus(entityClass, id);
	}
	public int disable(Class<M> entityClass, ID id){
		return commonDao.disable(entityClass, id);
	}
	public int enable(Class<M> entityClass, ID id){
		return commonDao.enable(entityClass, id);
	}
	public int delete(Class<M> entityClass, ID id){
		return commonDao.delete(entityClass, id);
	}
	public Integer countAll(Class<M> entityClass){
		return commonDao.countAll(entityClass);
	}
	public Integer countValid(Class<M> entityClass){
		return commonDao.countValid(entityClass);
	}
	public Integer countInvalid(Class<M> entityClass){
		return commonDao.countInvalid(entityClass);
	}
	public List<M> listAll(Class<M> entityClass){
		return commonDao.listAll(entityClass);
	}
	public List<M> listValid(Class<M> entityClass){
		return commonDao.listValid(entityClass);
	}
	public List<M> listInvalid(Class<M> entityClass){
		return commonDao.listInvalid(entityClass);
	}
	public int updateFieldWithValueById(Class<M> entityClass, String fieldname, Object value, ID id){
		return commonDao.updateFieldWithValueById(entityClass, fieldname, value, id);
	}
	public boolean checkFieldValueExisted(Class<M> entityClass, String fieldname, Object value){
		return commonDao.checkFieldValueExisted(entityClass, fieldname, value);
	}
	public int countByMultiCondition(Class<M> entityClass, List<Condition> conditions){
		return commonDao.countByMultiCondition(entityClass, conditions);
	}
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders){
		return commonDao.searchByMultiCondition(entityClass, conditions, orders);
	}
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int index){
		return commonDao.searchByMultiCondition(entityClass, conditions, orders, index);
	}
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int sizePerPage, int index){
		return commonDao.searchByMultiCondition(entityClass, conditions, orders, sizePerPage, index);
	}
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders){
		return commonDao.search(entityClass, conditions, orders);
	}
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int index){
		return commonDao.search(entityClass, conditions, orders, index);
	}
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int sizePerPage, int index){
		return commonDao.search(entityClass, conditions, orders, sizePerPage, index);
	}
	public SearchResult addNewAndReturnUpdatedList(M model,List<Condition> conditions, List<Order> orders){
		commonDao.save(model);
		return commonDao.search(model.getClass(), conditions, orders);
		
	}
	public Object[] getAbAndAllB(Object akey, Class a, Class b){
		Object[] returnO = new Object[2];
		returnO[0] = commonDao.get(a, akey);
		if(returnO[0]==null){
			return returnO;
		}
		returnO[1] = commonDao.listAll(b);
		return returnO;
	}
	public Object[] getAlistAndOtherFullList(Class<M> entityClass, List<Condition> conditions, List<Order> orders, Class[] classList){
		Object[] returnO = new Object[1+classList.length];
		returnO[0] = commonDao.search(entityClass, conditions, orders);
		for(int i=0;i<classList.length;i++){
			returnO[i+1] = commonDao.listAll(classList[i]);
		}
		return returnO;
	}
	public void saveObjects(Object[] objects){
		for(Object o:objects){
			commonDao.save(o);
		}
	}
	
	protected CommonDao commonDao;
	public void setCommonDao(CommonDao commonDao){
		this.commonDao=commonDao;
	}

	//testing
	public void test(){
		commonDao.test();
	}
}
