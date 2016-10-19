package com.u1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.u1.dao.CommonDao;
import com.u1.dao.UserAuthDao;
import com.u1.model.Condition;
import com.u1.model.UserForAuthOnly;
//import com.u1.dao.UserDao;
import com.u1.service.UserAuthService;
import com.u1.util.Constants;
import com.u1.util.MessageSourceHelper;
//import com.u7.model.UserAuth;
import com.u1.util.Util;

public class UserAuthServiceImpl extends CommonServiceImpl<UserForAuthOnly, Integer> implements UserAuthService,UserDetailsService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		Condition c1 = new Condition("username", username);
//		List<Condition> cs = new ArrayList();cs.add(c1);
//		List<UserForAuthOnly> users = (List<UserForAuthOnly>)commonDao.searchByMultiCondition(UserForAuthOnly.class, cs, null);
		UserForAuthOnly users=(UserForAuthOnly)commonDao.getByUsername(UserForAuthOnly.class, username);
		if(users==null)
			throw new UsernameNotFoundException("User not found");
		users.setAccessRights(userAuthDao.getGroupAccessableResource(users.getUserId()));
		return users;
	}

	public List<Integer> getAccessableResource(Integer userid){
		List b = userAuthDao.getGroupAccessableResource(userid);
//		for(Object o:a){
//			if(!b.contains(o)){
//				b.add(o);
//			}
//		}
		return b;
//		return userAuthDao.getAccessableResource(username);
	}
	
	public String updatePassword(Integer userid, String oldpassword, String newpassword){
		if(userAuthDao.lockCheckPasswordByID(userid, oldpassword)){
			userAuthDao.updatePassword(userid, newpassword);
		}else{
			return Constants.HTML_PREFIX_CONTENT_ERROR+MessageSourceHelper.getMessage("OLD_PASSWORD_INCORRECT");
		}
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	
	private UserAuthDao userAuthDao;
	public void setUserAuthDao(UserAuthDao userAuthDao) {
		this.userAuthDao = userAuthDao;
	}
//	private CommonDao commonDao;
//	public void setCommonDao(CommonDao commonDao){
//		this.commonDao=commonDao;
//	}
	
}
