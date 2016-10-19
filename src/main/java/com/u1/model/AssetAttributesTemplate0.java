package com.u1.model;

import javax.validation.constraints.Pattern;

public class AssetAttributesTemplate0 implements java.io.Serializable{
	private Integer assetAttrTemplatePid;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.assetattributes.attributeName}")
	private String attributeName;
	@Pattern(regexp="^ENTRY$|^CALENDAR$|^BOOLEAN$|^SELECT$|^MULTI_SELECT$", message="{Pattern.assetattributes.attributeType}")
	private String attributeType;
//	private String assetType;
	private Integer assetTypePid;
	private String primaryType;
	private Integer length;
	private Boolean required;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.assetattributes.section}")
	private String section;
	private Boolean enabled;
	
	public AssetAttributesTemplate0(){
		
	}
	
	/**
	 * @return the assetAttrTemplatePid
	 */
	public Integer getAssetAttrTemplatePid() {
		return assetAttrTemplatePid;
	}
	/**
	 * @param assetAttrTemplatePid the assetAttrTemplatePid to set
	 */
	public void setAssetAttrTemplatePid(Integer assetAttrTemplatePid) {
		this.assetAttrTemplatePid = assetAttrTemplatePid;
	}
	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}
	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	/**
	 * @return the attributeType
	 */
	public String getAttributeType() {
		return attributeType;
	}
	/**
	 * @param attributeType the attributeType to set
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	/**
	 * @return the assetTypePid
	 */
	public Integer getAssetTypePid() {
		return assetTypePid;
	}
	/**
	 * @param assetTypePid the assetTypePid to set
	 */
	public void setAssetTypePid(Integer assetTypePid) {
		this.assetTypePid = assetTypePid;
	}
	/**
	 * @return the primaryType
	 */
	public String getPrimaryType() {
		return primaryType;
	}
	/**
	 * @param primaryType the primaryType to set
	 */
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}
	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * @return the required
	 */
	public Boolean getRequired() {
		return required;
	}
	/**
	 * @param required the required to set
	 */
	public void setRequired(Boolean required) {
		this.required = required;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
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
		return "AssetAttributesTemplate0 [assetAttrTemplatePid="
				+ assetAttrTemplatePid + ", attributeName=" + attributeName
				+ ", attributeType=" + attributeType + ", assetTypePid="
				+ assetTypePid + ", primaryType=" + primaryType + ", length="
				+ length + ", required=" + required + ", section=" + section
				+ ", enabled=" + enabled + "]";
	}
	
	
}
