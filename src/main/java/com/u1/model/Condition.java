package com.u1.model;

public class Condition<V extends java.io.Serializable,MAX, MIN extends java.io.Serializable> implements java.io.Serializable {
	private String fieldname;
	private V value;
	private Boolean findnull=null;
	private Boolean findempty=null;
	private boolean precious=true;
	private MIN minvalue;
	private MAX maxvalue;
	
	public Condition(String fieldname, V value){
		this.fieldname=fieldname;
		this.value=value;
	}
	
	public Condition(String fieldname, Boolean nulloremptyvalue, String nullorempty){
		this.fieldname=fieldname;
		if(nullorempty==null){
			this.findnull=nulloremptyvalue;
		}else{
			this.findempty=nulloremptyvalue;
		}
	}
	
	public Condition(String fieldname, V value, boolean precious){
		this.fieldname=fieldname;
		this.value=value;
		this.precious=precious;
	}
	
	public Condition(String fieldname, MIN minvalue, MAX maxvalue){
		this.fieldname=fieldname;
		this.minvalue=minvalue;
		this.maxvalue=maxvalue;
	}
	
	public Condition(String fieldname, V value, int subProperty, boolean precious){
		this.fieldname=fieldname;
		this.value=value;
		this.precious=precious;
	}
	
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public V getValue() {
		return value;
	}
	public void setValue(V value) {
		this.value = value;
	}
	public MAX getMaxvalue() {
		return maxvalue;
	}
	public void setMaxvalue(MAX maxvalue) {
		this.maxvalue = maxvalue;
	}
	public MIN getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(MIN minvalue) {
		this.minvalue = minvalue;
	}

	public boolean isPrecious() {
		return precious;
	}

	public void setPrecious(boolean precious) {
		this.precious = precious;
	}

	/**
	 * @return the findnull
	 */
	public Boolean getFindnull() {
		return findnull;
	}

	/**
	 * @param findnull the findnull to set
	 */
	public void setFindnull(Boolean findnull) {
		this.findnull = findnull;
	}

	/**
	 * @return the findempty
	 */
	public Boolean getFindempty() {
		return findempty;
	}

	/**
	 * @param findempty the findempty to set
	 */
	public void setFindempty(Boolean findempty) {
		this.findempty = findempty;
	}
	
}
