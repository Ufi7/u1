package com.u1.model;

import javax.validation.constraints.Pattern;

public class Roles0 implements java.io.Serializable {

	private Integer roleId;
	
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.roles.roleName}")
	private String roleName;
	
	public Roles0() {
	}

	public Roles0(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
