package com.u1.service;

import java.util.List;

import com.u1.model.UserForAuthOnly;
import org.springframework.security.core.userdetails.UserDetailsService;
//import com.u1.model.UserAuth;

public interface UserAuthService extends CommonService<UserForAuthOnly, Integer> {
//	public List<User> getAllUsers();
//	public Object getUserById(Integer userid);
	
	public List<Integer> getAccessableResource(Integer userid);
	
	public String updatePassword(Integer userid, String oldpassword, String newpassword);
}
