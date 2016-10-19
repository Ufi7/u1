package com.u1.model;

import javax.validation.constraints.Pattern;

public class SimpleTask {
	private Integer taskPid;
	private String taskName;
	private String taskNum;
	private String status;
	private TaskType taskType;
	private String piority;
	private Integer assignedToId;
	private Integer customerId;
	private Boolean enabled;
	
	public SimpleTask(){
	}
	/**
	 * @return the taskNum
	 */
	public String getTaskNum() {
		return taskNum;
	}

	/**
	 * @param taskNum the taskNum to set
	 */
	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}
	
	/**
	 * @return the piority
	 */
	public String getPiority() {
		return piority;
	}

	/**
	 * @param piority the piority to set
	 */
	public void setPiority(String piority) {
		this.piority = piority;
	}

	/**
	 * @return the taskPid
	 */
	public Integer getTaskPid() {
		return taskPid;
	}
	/**
	 * @param taskPid the taskPid to set
	 */
	public void setTaskPid(Integer taskPid) {
		this.taskPid = taskPid;
	}
	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the taskType
	 */
	public TaskType getTaskType() {
		return taskType;
	}
	/**
	 * @param taskType the taskType to set
	 */
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	/**
	 * @return the assignedToId
	 */
	public Integer getAssignedToId() {
		return assignedToId;
	}
	/**
	 * @param assignedToId the assignedToId to set
	 */
	public void setAssignedToId(Integer assignedToId) {
		this.assignedToId = assignedToId;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
