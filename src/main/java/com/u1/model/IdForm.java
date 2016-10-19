package com.u1.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class IdForm implements Serializable {
	private Integer id;
	@Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_100, message = "{Pattern.tasklog.remark}")
	private String remark;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
