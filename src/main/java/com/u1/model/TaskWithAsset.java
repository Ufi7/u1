package com.u1.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.u1.model.AssetGroup0.Entity;

public class TaskWithAsset extends Task0 {
	@Length(max=65536, message="{Length.task.detail}")
	private String detail;
	private String solution;
//	private Department department;
	private List<SimpleAsset> assetList;
//	private List<TaskLog> tasklogList;

//	/**
//	 * @return the tasklogList
//	 */
//	public List<TaskLog> getTasklogList() {
//		return tasklogList;
//	}
//
//	/**
//	 * @param tasklogList the tasklogList to set
//	 */
//	public void setTasklogList(List<TaskLog> tasklogList) {
//		this.tasklogList = tasklogList;
//	}

	/**
	 * @return the assetList
	 */
	public List<SimpleAsset> getAssetList() {
		return assetList;
	}

	/**
	 * @param assetList the assetList to set
	 */
	public void setAssetList(List<SimpleAsset> assetList) {
		this.assetList = assetList;
	}

	/**
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

//	/**
//	 * @return the department
//	 */
//	public Department getDepartment() {
//		return department;
//	}
//
//	/**
//	 * @param department the department to set
//	 */
//	public void setDepartment(Department department) {
//		this.department = department;
//	}

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
	
}
