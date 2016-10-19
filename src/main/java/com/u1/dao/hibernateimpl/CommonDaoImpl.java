package com.u1.dao.hibernateimpl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.StringBuffer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.u1.dao.CommonDao;
import com.u1.model.Condition;
import com.u1.model.Order;
import com.u1.model.SearchResult;
import com.u1.model.SimpleUserWithGroup;
import com.u1.model.TaskType;
import com.u1.util.Constants;

public class CommonDaoImpl<M extends java.io.Serializable,ID extends java.io.Serializable> implements CommonDao<M, ID> {
	private final static String DB_FIELD_ENABLE = "enabled";
	private final static String DB_FIELD_ID = "id";
	private final static String DB_FIELD_EMAIL="email";
	private final static String DB_FIELD_NICKNAME="nickname";
	private final static String DB_FIELD_USERNAME="username";
	private final static String DB_FIELD_URLNAME="urlname";
	private final static String CHAR_ANYMATCH="%";
	private final static Logger logger = Logger.getLogger(CommonDaoImpl.class);
	private final static Order DEFAULT_ORDER_ENTRY = new Order(DB_FIELD_ID, false);
	private final static List<Order> DEFAULT_ORDER_List=new ArrayList();
	
	static{
		DEFAULT_ORDER_List.add(DEFAULT_ORDER_ENTRY);
	}
	
	
	public ID save(M model){
		return (ID)sessionFactory.getCurrentSession().save(model);
	}
	public void saveOrUpdate(M model){
		sessionFactory.getCurrentSession().saveOrUpdate(model);
	}
	public void update(M model){
		sessionFactory.getCurrentSession().update(model);
	}
	public M merge(M model){
		return (M)sessionFactory.getCurrentSession().merge(model);
	}
	public void myMerge(M model, ID id){
		myMerge(model, id, 0);
	}
	public void myMerge(M model, ID id, int updateChildrenEntityDepth){
		M dbone = (M)sessionFactory.getCurrentSession().load(model.getClass(), id);
		sessionFactory.getCurrentSession().merge(myCompare(dbone,model, updateChildrenEntityDepth));
	}
	public void delete(M model){
		sessionFactory.getCurrentSession().delete(model);
	}
	public M get(Class<M> entityClass, ID id){
		return (M)sessionFactory.getCurrentSession().get(entityClass, id);
	}
	public M getByEmail(Class<M> entityClass, String email){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(DB_FIELD_EMAIL, email));
		List result=criteria.list();
		return result.size()==0?null:(M)result.get(0);
	}
	public M getByNickname(Class<M> entityClass, String nickname){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(DB_FIELD_NICKNAME, nickname));
		List result=criteria.list();
		return result.size()==0?null:(M)result.get(0);
	}
	public M getByUsername(Class<M> entityClass, String username){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(DB_FIELD_USERNAME, username));
		List result=criteria.list();
		return result.size()==0?null:(M)result.get(0);
	}
	public M getByUrlname(Class<M> entityClass, String urlname){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(DB_FIELD_URLNAME, urlname));
		List result=criteria.list();
		return result.size()==0?null:(M)result.get(0);
	}
	public List<M> searchByNickname(Class<M> entityClass, String nickname){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.like(DB_FIELD_NICKNAME, CHAR_ANYMATCH+nickname+CHAR_ANYMATCH));
		return criteria.list();		
	}
	public boolean checkStatus(Class<M> entityClass, ID id){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.setProjection(Projections.property(DB_FIELD_ENABLE));
		criteria.add(Restrictions.idEq(id));
		List result = criteria.list();
		return result.size()==0?false:(boolean)criteria.list().get(0);
	}
	public int disable(Class<M> entityClass, ID id){
		String hql = "Update "+entityClass.getName()+" set "+DB_FIELD_ENABLE+"=0 where id = :id";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(DB_FIELD_ID, id.toString());
		return query.executeUpdate();
	}
	public int enable(Class<M> entityClass, ID id){
		String hql = "Update "+entityClass.getName()+" set "+DB_FIELD_ENABLE+"=1 where id = :id";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(DB_FIELD_ID, id.toString());
		return query.executeUpdate();
	}
	public int delete(Class<M> entityClass, ID id){
		String hql = "Delete "+entityClass.getName()+" where id = :id";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(DB_FIELD_ID, id.toString());
		return query.executeUpdate();
	}
	public Integer countAll(Class<M> entityClass){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		return (Integer)criteria.list().get(0);
	}
	public Integer countValid(Class<M> entityClass){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq(DB_FIELD_ENABLE, true));
		return (Integer)criteria.list().get(0);
	}
	public Integer countInvalid(Class<M> entityClass){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq(DB_FIELD_ENABLE, false));
		return (Integer)criteria.list().get(0);
	}
	public List<M> listAll(Class<M> entityClass){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		return criteria.list();
	}
	public List<M> listValid(Class<M> entityClass){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(DB_FIELD_ENABLE, true));
		return criteria.list();
	}
	public List<M> listInvalid(Class<M> entityClass){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(DB_FIELD_ENABLE, false));
        return criteria.list();
	}
	public int updateFieldWithValueById(Class<M> entityClass, String fieldname, Object value, ID id){
		StringBuffer sb = new StringBuffer().append("Update ").append(entityClass.getName()).append(" set ").append(fieldname).append(" =? where id=?");
		Query query=sessionFactory.getCurrentSession().createQuery(sb.toString());
		query.setParameter(0, value);
		query.setParameter(1, id);
		return query.executeUpdate();
	}
	
	public boolean checkFieldValueExisted(Class<M> entityClass, String fieldname, Object value){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(fieldname, value));
        return criteria.list().size()==0?false:true;
	}
	
	public int countByMultiCondition(Class<M> entityClass, List<Condition> conditions){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		Map aliass=new Hashtable();//for alias name store
		if(conditions!=null){
			for(Condition c:conditions){
				String fieldname;
				String[] strs = c.getFieldname().split("/");
				if(strs.length>1){
					String alias=strs[0];
					for(int i=0;i<strs.length-1;i++){
						if(!aliass.containsKey(alias)){
							criteria.createAlias(alias, strs[i]);
							aliass.put(alias, alias);
						}
						alias=strs[i]+"."+strs[i+1];
					}
					fieldname = strs[strs.length-2]+"."+strs[strs.length-1];
				}else{
					fieldname=c.getFieldname();
				}
				if(c.getFindnull()!=null){
					if(c.getFindnull()){
						criteria.add(Restrictions.isNull(fieldname));
					}else{
						criteria.add(Restrictions.isNotNull(fieldname));
					}
				}else if(c.getFindempty()!=null){
					if(c.getFindempty()){
						criteria.add(Restrictions.isEmpty(fieldname));
					}else{
						criteria.add(Restrictions.isNotEmpty(fieldname));
					}
				}else if(c.getValue()!=null){
					if(c.getValue().getClass().isAssignableFrom(String.class) && !c.isPrecious()){
						if(c.getValue() instanceof List){
							List list = (List)c.getValue();
							Disjunction dis=Restrictions.disjunction();
							for(Object o:list){
								dis.add(Restrictions.like(fieldname, CHAR_ANYMATCH+o+CHAR_ANYMATCH));
							}
							criteria.add(dis);
						}else{
							criteria.add(Restrictions.like(fieldname, CHAR_ANYMATCH+c.getValue()+CHAR_ANYMATCH));
						}
					}else{
						if(c.getValue() instanceof Object[]){
							Object[] os = (Object[])c.getValue();
							Disjunction dis=Restrictions.disjunction();
							for(Object o:os){
								dis.add(Restrictions.eq(fieldname, o));
							}
							criteria.add(dis);
						}else{
							criteria.add(Restrictions.eq(fieldname, c.getValue()));
						}
					}
				}else if(c.getMaxvalue()!=null && c.getMinvalue()!=null){
					criteria.add(Restrictions.between(fieldname, c.getMinvalue(), c.getMaxvalue()));
				}
			}
		}
		return ((Long)criteria.list().get(0)).intValue();
	}
	
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders){
		return searchByMultiCondition(entityClass, conditions, orders, Constants.SIZE_PER_PAGE, 0);
	}
	
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int index){
		return searchByMultiCondition(entityClass, conditions, orders, Constants.SIZE_PER_PAGE, index);
	}
	
	public List<M> searchByMultiCondition(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int sizePerPage, int index){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClass);
		Map aliass=new Hashtable();//for alias name store
		if(conditions != null){
			for(Condition c:conditions){
				String fieldname;
				String[] strs = c.getFieldname().split("/");
				if(strs.length>1){
					String alias=strs[0];
					for(int i=0;i<strs.length-1;i++){
						if(!aliass.containsKey(alias)){
							criteria.createAlias(alias, strs[i]);
							aliass.put(alias, alias);
						}
						alias=strs[i]+"."+strs[i+1];
					}
					fieldname = strs[strs.length-2]+"."+strs[strs.length-1];
				}else{
					fieldname=c.getFieldname();
				}
				if(c.getFindnull()!=null){
					if(c.getFindnull()){
						criteria.add(Restrictions.isNull(fieldname));
					}else{
						criteria.add(Restrictions.isNotNull(fieldname));
					}
				}else if(c.getFindempty()!=null){
					if(c.getFindempty()){
						criteria.add(Restrictions.isEmpty(fieldname));
					}else{
						criteria.add(Restrictions.isNotEmpty(fieldname));
					}
				}else if(c.getValue()!=null){
					if(c.getValue().getClass().isAssignableFrom(String.class) && !c.isPrecious()){
						if(c.getValue() instanceof List){
							List list = (List)c.getValue();
							Disjunction dis=Restrictions.disjunction();
							for(Object o:list){
								dis.add(Restrictions.like(fieldname, CHAR_ANYMATCH+o+CHAR_ANYMATCH));
							}
							criteria.add(dis);
						}else{
							criteria.add(Restrictions.like(fieldname, CHAR_ANYMATCH+c.getValue()+CHAR_ANYMATCH));
						}
					}else{
						if(c.getValue() instanceof Object[]){
							Object[] os = (Object[])c.getValue();
							Disjunction dis=Restrictions.disjunction();
							for(Object o:os){
								dis.add(Restrictions.eq(fieldname, o));
							}
							criteria.add(dis);
						}else{
							criteria.add(Restrictions.eq(fieldname, c.getValue()));
						}
					}
				}else if(c.getMaxvalue()!=null && c.getMinvalue()!=null){
					criteria.add(Restrictions.between(fieldname, c.getMinvalue(), c.getMaxvalue()));
				}
			}
		}
		if(orders==null){
			orders=DEFAULT_ORDER_List;
		}
		for(Order o:orders){
			if(o.isAscFlag()){
				criteria.addOrder(org.hibernate.criterion.Order.asc(o.getFieldname()));
			}else{
				criteria.addOrder(org.hibernate.criterion.Order.desc(o.getFieldname()));
			}
		}
		if(sizePerPage!=0){
			criteria.setFirstResult(sizePerPage*index);
			criteria.setMaxResults(sizePerPage);
		}
		return (List<M>)criteria.list();
	}
	
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders){
		return search(entityClass, conditions, orders, Constants.SIZE_PER_PAGE, 0);
	}
	
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int index){
		return search(entityClass, conditions, orders, Constants.SIZE_PER_PAGE, index);
	}
	
	public SearchResult search(Class<M> entityClass, List<Condition> conditions, List<Order> orders, int sizePerPage, int index){
		SearchResult sr = new SearchResult();
		int count = countByMultiCondition(entityClass, conditions);
		sr.setTotalCount(count);
		if(count==0){
			sr.setCurrentPage(1);
			sr.setTotalPage(1);
			return sr;
		}
		int temp = count/sizePerPage;
		sr.setTotalPage(temp + (count%sizePerPage==0?0:1));
		if(index!=0){
			Integer _count = index * sizePerPage;
			if(_count>=count){
				index = sr.getTotalPage()-1;
			}
		}
		sr.setCurrentPage(index+1);
		sr.setResult(searchByMultiCondition(entityClass,conditions,orders,sizePerPage,index));
		return sr;
	}
	
	public <T> T myCompare(T dbone, T updateone, int updateChildrenEntityDepth){
		Class c = updateone.getClass();
		try{
			while(c.getSuperclass()!=null){
				for(Method m :c.getDeclaredMethods()){
					boolean get_method=false;
					boolean is_method=false;
					String setMethodName=null;
					if(m.getName().startsWith(METHOD_GET_PREFIX)){
						get_method=true;
						setMethodName = METHOD_SET_PREFIX + m.getName().substring(3);
					}else if(m.getName().startsWith(METHOD_IS_PREFIX)){
						is_method=true;
						setMethodName = METHOD_SET_PREFIX + m.getName().substring(2);
					}
					if(get_method||is_method){
						boolean skip = false;
						for(String s:METHOD_EXCLUDE){
							if(m.getName().toLowerCase().indexOf(s)>-1){
								skip=true;
								break;
							}
						}
						if(skip){
							continue;
						}
						Object dbfield = m.invoke(dbone, null);
						Object updatefield = m.invoke(updateone, null);
						if(updatefield==null & dbfield!=null && !(dbfield instanceof List) && !(dbfield instanceof Set)){
							for(Method n : c.getDeclaredMethods()){
								if(n.getName().equals(setMethodName)){
									n.invoke(updateone, m.invoke(dbone, null));
									break;
								}
							}
						}else if(dbfield instanceof List && updateChildrenEntityDepth>0){
							List dblist = (List)dbfield;
							List updatelist = (List)updatefield;
							List tobeupdate=new ArrayList();
							List tobeupdateindex = new ArrayList();
							if(updatelist != null){
								for(int i=0;i<updatelist.size();i++){
									int index = dblist.indexOf(updatelist.get(i));
									if(index>-1){
										tobeupdate.add(myCompare(dblist.get(index), updatelist.get(i), --updateChildrenEntityDepth));
										tobeupdateindex.add(i);
									}
									
								}
								for(int a=0;a<tobeupdate.size();a++){
									updatelist.set((int)tobeupdateindex.get(a), tobeupdate.get(a));
								}
							}
							List toberemove = new ArrayList();
							for(Object o:dblist){
								if(updatelist==null || !updatelist.contains(o)){
									toberemove.add(o);
								}
							}
							for(Object o:toberemove){
								dblist.remove(o);
								sessionFactory.getCurrentSession().delete(o);
							}
						}
						else if(dbfield instanceof Set && updateChildrenEntityDepth>0){
							Object[] dbset = null;
							((Set)dbfield).toArray(dbset);
							Object[] updateset = null;
							if(updatefield != null){
								((Set)updatefield).toArray(updateset);
							}
							if(updateset != null){
								for(int i=0; i<updateset.length;i++){
									for(int n=0; n<dbset.length;n++){
										if(updateset[i].equals(dbset[n])){
											((Set)dbfield).remove(updateset[i]);
											((Set)dbfield).add(myCompare(dbset[n], updateset[i], --updateChildrenEntityDepth));
											break;
										}
									}
								}
							}
							for(Object o:dbset){
								if(updatefield==null || !((Set)updatefield).contains(o)){
									((Set)dbfield).remove(o);
									sessionFactory.getCurrentSession().delete(o);
								}
							}
						}
					}
				}
				c=c.getSuperclass();
			}
		}catch(Exception e){
			logger.error(e);
			e.printStackTrace();
			return null;
		}
		return updateone;
	}
	public static final String METHOD_GET_PREFIX="get";
	public static final String METHOD_IS_PREFIX="is";
	public static final String METHOD_SET_PREFIX="set";
	public static final String[] METHOD_EXCLUDE= new String[]{"lastupdatedtimestamp" };
	
	protected SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//testing
	public void test(){
//		String enqsql = "select DEFINED_NUM, enabled, task_type_code from task_type where TASK_TYPE_PID=? for update";
//		String updsql = "update task_type_lock set DEFINED_NUM=? where TASK_TYPE_PID=?";
//		Query query=sessionFactory.getCurrentSession().createSQLQuery(enqsql);
//		query.setInteger(0, 5);
//		Object[] os = (Object[])query.list().get(0);
//		for(Object o:os){
//			System.out.println(o.getClass().getName());
//			System.out.println(o);
//		}
//		
//		System.out.println("start to sleep............");
//		try{
//			Thread.sleep(30000);
//		}catch(Exception e){
//			//
//		}
//		System.out.println("end sleep............");
		
		String hql = "select count(*), avg(t.score), year(t.endDatetime),month(t.endDatetime)  from Task as t group by year(t.endDatetime), month(t.endDatetime) order by t.endDatetime desc";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Object> ol = query.list();
		System.out.println(ol.size());
		for(Object o : ol){
			System.out.println(o.getClass().getName());
			if(o instanceof Object[]){
				Object[] l = (Object[])o;
				for(Object le : l){
					System.out.print(le);
					System.out.print("\t");
					System.out.print(le.getClass());
					System.out.print("\t");
				}
				System.out.println();
			}
		}
	}
}
