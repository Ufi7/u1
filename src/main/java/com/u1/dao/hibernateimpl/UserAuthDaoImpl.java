package com.u1.dao.hibernateimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.u1.dao.UserAuthDao;
import com.u1.model.UserForAuthOnly;

public class UserAuthDaoImpl implements UserAuthDao {
	private final static String SQL_GET_ACCESSABLE_RESOURCE_FOR_USER_ONLY = "select distinct role_resources.resource_id from users, user_groups, group_roles,role_Resources where users.user_id=user_groups.user_id and user_groups.group_id = group_roles.group_id and group_roles.role_id = role_Resources.role_id and users.username=? order by role_resources.resource_id asc";
	private final static String SQL_GET_ACCESSABLE_RESOURCE_FOR_USERsGRUOP_ONLY = "select distinct role_resources.resource_id from user_groups left join group_roles on user_groups.group_id = group_roles.group_id  left join role_resources on group_roles.role_id = role_resources.role_id where user_groups.user_id=? order by role_resources.resource_id";
	private final static String SQL_GET_ACCESSABLE_RESOURCE = "select distinct role_resources.resource_id from users, user_groups, group_roles,role_Resources,user_authorities where (users.user_id=user_groups.user_id and user_groups.group_id = group_roles.group_id and group_roles.role_id = role_Resources.role_id and users.username=?) or (users.user_id = user_authorities.user_id and user_authorities.role_id = role_resources.role_id and users.username=?) order by role_resources.resource_id asc;";
	

	
	public List<Integer> getUserAccessableResource(String username) {
		// TODO Auto-generated method stub
		Query query=sessionFactory.getCurrentSession().createSQLQuery(SQL_GET_ACCESSABLE_RESOURCE_FOR_USER_ONLY);
		query.setString(0, username);
		return query.list();
	}
	
	public List<Integer> getGroupAccessableResource(Integer userid) {
		// TODO Auto-generated method stub
		Query query=sessionFactory.getCurrentSession().createSQLQuery(SQL_GET_ACCESSABLE_RESOURCE_FOR_USERsGRUOP_ONLY);
		query.setInteger(0, userid);
		return query.list();
	}
	
	@Override
	public List<Integer> getAccessableResource(String username) {
		// TODO Auto-generated method stub
		Query query=sessionFactory.getCurrentSession().createSQLQuery(SQL_GET_ACCESSABLE_RESOURCE);
		query.setString(0, username);
		query.setString(1, username);
		return query.list();
	}
	
	public boolean lockCheckPasswordByID(Integer userid, String password){
		String enqsql = "select password from users where user_id=? and password=? for update";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(enqsql);
		query.setParameter(0, userid);
		query.setParameter(1, password);
		if(query.list().size()==1){
			return true;
		}
		return false;
	}
	
	public void updatePassword(Integer userid, String newpassword){
		String updsql = "update users set password=? where user_id = ? ";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(updsql);
		query.setParameter(0,newpassword);
		query.setParameter(1,userid);
		query.executeUpdate();
	}
	
	

	protected SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
