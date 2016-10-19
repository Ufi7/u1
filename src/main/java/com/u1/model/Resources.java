package com.u1.model;

import java.util.List;

// Generated 2013-9-2 21:52:17 by Hibernate Tools 3.4.0.CR1

/**
 * Resources generated by hbm2java
 */
public class Resources implements java.io.Serializable {

	private Integer resourceId;
	private String resourceName;
	private String type;
	private String description;
	private List<Roles> roleList;

	public Resources() {
	}
	
	public Resources(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public Resources(String resourceIdStr) {
		this.resourceId = Integer.valueOf(resourceIdStr);
	}

	public Resources(String resourceName, String type, String description) {
		this.resourceName = resourceName;
		this.type = type;
		this.description = description;
	}

	public Integer getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return this.resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the roleList
	 */
	public List<Roles> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Roles> roleList) {
		this.roleList = roleList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resourceId == null) ? 0 : resourceId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resources other = (Resources) obj;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		return true;
	}
	
	

}