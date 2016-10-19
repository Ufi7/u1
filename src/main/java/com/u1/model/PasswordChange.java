package com.u1.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class PasswordChange implements Serializable {
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{passwordChange.oldpsw}")
	private String oldpsw;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{passwordChange.newpsw}")
	private String newpsw;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{passwordChange.newpsw}")
	private String newpsw2;

	/**
	 * @return the oldpsw
	 */
	public String getOldpsw() {
		return oldpsw;
	}

	/**
	 * @param oldpsw the oldpsw to set
	 */
	public void setOldpsw(String oldpsw) {
		this.oldpsw = oldpsw;
	}

	/**
	 * @return the newpsw
	 */
	public String getNewpsw() {
		return newpsw;
	}

	/**
	 * @param newpsw the newpsw to set
	 */
	public void setNewpsw(String newpsw) {
		this.newpsw = newpsw;
	}

	/**
	 * @return the newpsw2
	 */
	public String getNewpsw2() {
		return newpsw2;
	}

	/**
	 * @param newpsw2 the newpsw2 to set
	 */
	public void setNewpsw2(String newpsw2) {
		this.newpsw2 = newpsw2;
	}
	
	
}
