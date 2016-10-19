package com.u1.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;

import com.u1.model.SimpleAsset0.Entity;

public class TaskForUpdate implements Serializable {
	private Integer taskPid;
	private String status;
	@NotNull(groups=AssignedTo.class, message="{NotNull.task.assignedTo}")
	private RefUser assignedTo;
	private Date endDatetime;
	private Date startDatetime;
	private Date dueDatetime;
	private Date lastUpdatedTimestamp;
	@Min(groups=ReValue.class,value = 0, message = "{Minmax.task.score}")
    @Max(groups=ReValue.class,value = 100, message = "{Minmax.task.score}")
	@NotNull(groups=ReValue.class, message="{Minmax.task.score}")
	private Integer score;
	private Boolean enabled;
	@Pattern(groups=Done.class, regexp=ModelConstants.REG_CHN_100, message="{Pattern.task.rootcause}")
	private String rootcause;
	@Length(groups=Done.class, max=65536, message="{Length.task.solution}")
	@NotEmpty(groups=Done.class, message="{Length.task.solution}")
	private String solution;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskForUpdate [taskPid=" + taskPid + ", status=" + status
				+ ", assignedTo=" + assignedTo + ", endDatetime=" + endDatetime
				+ ", startDatetime=" + startDatetime + ", dueDatetime="
				+ dueDatetime + ", lastUpdatedTimestamp="
				+ lastUpdatedTimestamp + ", score=" + score + ", enabled="
				+ enabled  + "]";
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
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}
	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}

	public interface ReValue extends Default{};
	public interface AssignedTo extends Default{};
	public interface Done extends Default{};
}
