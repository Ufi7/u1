package com.u1.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

public class AttrSelectOption implements Serializable {

	private Integer attrSelectOptionPid;
//	private String value;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.attrselectoption.description}")
	private String description;
	private AssetAttributesTemplateWithOption template;

	public AttrSelectOption(){
	}
	public AttrSelectOption(String description){
		this.description = description;
	}
	/**
	 * @return the attrSelectOptionPid
	 */
	public Integer getAttrSelectOptionPid() {
		return attrSelectOptionPid;
	}
	/**
	 * @param attrSelectOptionPid the attrSelectOptionPid to set
	 */
	public void setAttrSelectOptionPid(Integer attrSelectOptionPid) {
		this.attrSelectOptionPid = attrSelectOptionPid;
	}
//	/**
//	 * @return the value
//	 */
//	public String getValue() {
//		return value;
//	}
//	/**
//	 * @param value the value to set
//	 */
//	public void setValue(String value) {
//		this.value = value;
//	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the template
	 */
	public AssetAttributesTemplateWithOption getTemplate() {
		return template;
	}
	/**
	 * @param template the template to set
	 */
	public void setTemplate(AssetAttributesTemplateWithOption template) {
		this.template = template;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AttrSelectOption [attrSelectOptionPid=" + attrSelectOptionPid
				+ ", description=" + description + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((attrSelectOptionPid == null) ? 0 : attrSelectOptionPid
						.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttrSelectOption other = (AttrSelectOption) obj;
		if (attrSelectOptionPid == null) {
			if (other.attrSelectOptionPid != null)
				return false;
		} else if (!attrSelectOptionPid.equals(other.attrSelectOptionPid))
			return false;
		return true;
	}
}
