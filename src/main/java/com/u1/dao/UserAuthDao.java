package com.u1.dao;

import java.util.List;

import org.hibernate.Query;

import com.u1.model.UserForAuthOnly;

public interface UserAuthDao {
	public List<Integer> getUserAccessableResource(String username);
	public List<Integer> getGroupAccessableResource(Integer userid);
	public List<Integer> getAccessableResource(String username);
	public boolean lockCheckPasswordByID(Integer userid, String password);
	public void updatePassword(Integer userid, String newpassword);
}
