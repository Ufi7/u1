package com.u1.model;

import java.util.List;

public class SimpleUserWithGroup extends SimpleUsers0 {

	private List<Groups> groupList;

	public SimpleUserWithGroup() {
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
}
