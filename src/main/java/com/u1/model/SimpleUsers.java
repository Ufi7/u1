package com.u1.model;

// Generated 2013-8-14 16:29:13 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

/**
 * Users generated by hbm2java
 */
public class SimpleUsers extends SimpleUsers0 implements CustomerInterface{
	//overwrite below properties to renew the validation pattern, to allow emtpy input in search
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.users.username}")
	private String username;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.users.givenName}")
	private String givenName;
	@Pattern(regexp=ModelConstants.TEL_ALLOW_EMPTY, message="{Pattern.users.telephone}")
	private String telephone;
	@Pattern(regexp=ModelConstants.EMAIL_ALLOW_EMPTY,message="{Pattern.users.email}")
	private String email;
	
	public SimpleUsers(){
		super();
	}
	public SimpleUsers(Integer id){
		super(id);
	}
	public SimpleUsers(String idstr){
		super(idstr);
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
}
