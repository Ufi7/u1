package com.u1.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.u1.model.SimpleAsset0.Entity;

public class Task0 implements Serializable, CustomerInterface {
	private Integer taskPid;
	@Pattern(regexp=ModelConstants.REG_CHN_100, message="{Pattern.task.taskName}")
	private String taskName;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_100, message="{Pattern.task.rootcause}")
	private String rootcause;
	private String status;
//	private String taskTypeCode;
	 @NotNull(message="{NotNull.task.taskType}")
	private TaskType taskType;
//	private String createdByName;
//	private String assignedToName;
	private RefUser createdBy;
	private RefUser assignedTo;
	private Date startDatetime;
	private Date endDatetime;
//	private String detail;
	private String taskNum;
	private Date createdDatetime;
	private Date lastUpdatedTimestamp;
	private Date dueDatetime;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.task.contact}")
	private String contact;
	@Pattern(regexp = "^1$|^2$|^0$", message="{Pattern.task.piority}")
	@NotNull(message="{Pattern.task.piority}")
	private String piority;
	private Integer score;
//	private String departmentstr;
	@NotNull(message="{NotNull.customer}")
	protected Customer customer;
	private Department department;
	private Boolean enabled;
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
	 * @return the rootcause
	 */
	public String getRootcause() {
		return rootcause;
	}
	/**
	 * @param rootcause the rootcause to set
	 */
	public void setRootcause(String rootcause) {
		this.rootcause = rootcause;
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
//	/**
//	 * @return the taskTypeCode
//	 */
//	public String getTaskTypeCode() {
//		return taskTypeCode;
//	}
//	/**
//	 * @param taskTypeCode the taskTypeCode to set
//	 */
//	public void setTaskTypeCode(String taskTypeCode) {
//		this.taskTypeCode = taskTypeCode;
//	}
	
	/**
	 * @return the startDatetime
	 */
	public Date getStartDatetime() {
		return startDatetime;
	}
	/**
	 * @param startDatetime the startDatetime to set
	 */
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	/**
	 * @return the endDatetime
	 */
	public Date getEndDatetime() {
		return endDatetime;
	}
	/**
	 * @param endDatetime the endDatetime to set
	 */
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
//	/**
//	 * @return the detail
//	 */
//	public String getDetail() {
//		return detail;
//	}
//	/**
//	 * @param detail the detail to set
//	 */
//	public void setDetail(String detail) {
//		this.detail = detail;
//	}
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
	 * @return the createdDatetime
	 */
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	/**
	 * @param createdDatetime the createdDatetime to set
	 */
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * @return the lastUpdatedTimestamp
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp the lastUpdatedTimestamp to set
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return the dueDatetime
	 */
	public Date getDueDatetime() {
		return dueDatetime;
	}
	/**
	 * @param dueDatetime the dueDatetime to set
	 */
	public void setDueDatetime(Date dueDatetime) {
		this.dueDatetime = dueDatetime;
	}
	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
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
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

//	/**
//	 * @return the departmentstr
//	 */
//	public String getDepartmentstr() {
//		return departmentstr;
//	}
//	/**
//	 * @param departmentstr the departmentstr to set
//	 */
//	public void setDepartmentstr(String departmentstr) {
//		this.departmentstr = departmentstr;
//	}
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
	 * @return the createBy
	 */
	public RefUser getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createBy the createBy to set
	 */
	public void setCreatedBy(RefUser createBy) {
		this.createdBy = createBy;
	}
	/**
	 * @return the assignedTo
	 */
	public RefUser getAssignedTo() {
		return assignedTo;
	}
	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(RefUser assignedTo) {
		this.assignedTo = assignedTo;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task0 [taskPid=" + taskPid + ", taskName=" + taskName
				+ ", status=" + status + ", taskType=" + taskType
				+ ", createdBy=" + createdBy + ", assignedTo=" + assignedTo
				+ ", startDatetime=" + startDatetime + ", endDatetime="
				+ endDatetime + ", taskNum=" + taskNum + ", createdDatetime="
				+ createdDatetime + ", lastUpdatedTimestamp="
				+ lastUpdatedTimestamp + ", dueDatetime=" + dueDatetime
				+ ", contact=" + contact + ", piority=" + piority + ", score="
				+ score + ", enabled=" + enabled + "]";
	}


	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
