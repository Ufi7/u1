package com.u1.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.NotEmpty;

public class Log implements Serializable {

	protected Integer taskLogId;
	protected RefUser owner;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_100, message="{Pattern.tasklog.remark}")
	@NotEmpty(groups=AddLog.class, message="{Pattern.tasklog.remark}")
	protected String remark;
	protected String autoremark;
	protected Date createdtimestamp;

	public Log(){
		
	}
	
	public Log(RefUser owner, String remark, String autoremark) {
		super();
		this.owner = owner;
		this.remark = remark;
		this.autoremark = autoremark;
	}



	/**
	 * @return the taskLogId
	 */
	public Integer getTaskLogId() {
		return taskLogId;
	}



	/**
	 * @param taskLogId the taskLogId to set
	 */
	public void setTaskLogId(Integer taskLogId) {
		this.taskLogId = taskLogId;
	}



	/**
	 * @return the owner
	 */
	public RefUser getOwner() {
		return owner;
	}



	/**
	 * @param owner the owner to set
	 */
	public void setOwner(RefUser owner) {
		this.owner = owner;
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



	/**
	 * @return the autoremark
	 */
	public String getAutoremark() {
		return autoremark;
	}



	/**
	 * @param autoremark the autoremark to set
	 */
	public void setAutoremark(String autoremark) {
		this.autoremark = autoremark;
	}



	/**
	 * @return the createdtimestamp
	 */
	public Date getCreatedtimestamp() {
		return createdtimestamp;
	}



	/**
	 * @param createdtimestamp the createdtimestamp to set
	 */
	public void setCreatedtimestamp(Date createdtimestamp) {
		this.createdtimestamp = createdtimestamp;
	}



	public interface AddLog extends Default{};
}
