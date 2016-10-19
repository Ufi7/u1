package com.u1.model;

import java.util.List;

public class RoleWithResource extends Roles0 {
	public RoleWithResource(){
		super();
	}

	private List<Resources> resourceList;

	/**
	 * @return the resourceList
	 */
	public List<Resources> getResourceList() {
		return resourceList;
	}

	/**
	 * @param resourceList the resourceList to set
	 */
	public void setResourceList(List<Resources> resourceList) {
		this.resourceList = resourceList;
	}
	
}
