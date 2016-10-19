package com.u1.model;

import java.util.List;

public class SimpleUserWithGroupAndPassword extends SimpleUsers0 {
	private String password;
	private List<Groups> groupList;

	public SimpleUserWithGroupAndPassword() {
		super();
	}

	/**
	 * @return the groupList
	 */
	public List<Groups> getGroupList() {
		return groupList;
	}

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(List<Groups> groupList) {
		this.groupList = groupList;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
