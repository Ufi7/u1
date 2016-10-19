package com.u1.model;

import javax.validation.constraints.Pattern;

public class RefUser implements java.io.Serializable {
	private Integer userId;
	private String username;
	private Boolean enabled;
	private String givenName;
	private RefCustomer customer;

	public RefUser(){
	}
	
	public RefUser(Integer userId){
		this.userId = userId;
	}
	
	public RefUser(String userId){
		this.userId = Integer.valueOf(userId);
	}
	
	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
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
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the customer
	 */
	public RefCustomer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(RefCustomer customer) {
		this.customer = customer;
	}

	
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	
}
