package com.u1.model;

public class Order {
	private String fieldname;
	private boolean ascFlag=true;
	public Order(String fieldname){
		this.fieldname=fieldname;
	}
	public Order(String fieldname, boolean ascFlag){
		this.fieldname=fieldname;
		this.ascFlag=ascFlag;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public boolean isAscFlag() {
		return ascFlag;
	}
	public void setAscFlag(boolean ascFlag) {
		this.ascFlag = ascFlag;
	}
	
}
