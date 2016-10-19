package com.u1.model;

import javax.validation.constraints.Pattern;

// Generated 2014-2-26 14:23:35 by Hibernate Tools 3.4.0.CR1

/**
 * Component generated by hbm2java
 */
public class Component implements java.io.Serializable {

	private Integer componentId;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.component.field1}")
	private String field1;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.component.field2}")
	private String field2;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.component.field3}")
	private String field3;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.component.field4}")
	private String field4;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.component.field5}")
	private String field5;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.component.field6}")
	private String field6;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.component.field7}")
	private String field7;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.component.field8}")
	private String field8;
	private String field9;
	private String field10;

	public Component() {
	}
	
	public Component(Integer componentId){
		this.componentId=componentId;
	}

	public Component(String field1, String field2, String field3,
			String field4, String field5, String field6, String field7,
			String field8, String field9, String field10) {
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		this.field4 = field4;
		this.field5 = field5;
		this.field6 = field6;
		this.field7 = field7;
		this.field8 = field8;
		this.field9 = field9;
		this.field10 = field10;
	}

	public Integer getComponentId() {
		return this.componentId;
	}

	public void setComponentId(Integer componentId) {
		this.componentId = componentId;
	}

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return this.field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return this.field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return this.field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getField6() {
		return this.field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}

	public String getField7() {
		return this.field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}

	public String getField8() {
		return this.field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}

	public String getField9() {
		return this.field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}

	public String getField10() {
		return this.field10;
	}

	public void setField10(String field10) {
		this.field10 = field10;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Component [componentId=" + componentId + ", field1=" + field1
				+ ", field2=" + field2 + ", field3=" + field3 + ", field4="
				+ field4 + ", field5=" + field5 + ", field6=" + field6
				+ ", field7=" + field7 + ", field8=" + field8 + ", field9="
				+ field9 + ", field10=" + field10 + "]";
	}

}