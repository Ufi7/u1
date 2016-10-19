package com.u1.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;


public class AssetType0 implements Serializable {
    protected Integer assetTypePid;
    @Pattern(regexp = ModelConstants.REG_CHN_20, message = "{Pattern.assetType.code}")
    private String code;
    @Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_20, message = "{Pattern.assetType.description}")
    private String description;
    private Boolean enabled;
    @Pattern(regexp = ModelConstants.DEFINED_CODE, message = "{DEFINED_CODE}")
    private String definedCode;
//    private Integer definedNum;

    public AssetType0() {
    }

    public AssetType0(String id) {
        this.assetTypePid = Integer.valueOf(id);
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

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

    /**
     * @return the definedCode
     */
    public String getDefinedCode() {
        return definedCode;
    }

    /**
     * @param definedCode the definedCode to set
     */
    public void setDefinedCode(String definedCode) {
        this.definedCode = definedCode;
    }

//    /**
//     * @return the definedNum
//     */
//    public Integer getDefinedNum() {
//        return definedNum;
//    }
//
//    /**
//     * @param definedNum the definedNum to set
//     */
//    public void setDefinedNum(Integer definedNum) {
//        this.definedNum = definedNum;
//    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AssetType [assetTypePid=" + assetTypePid + ", code=" + code +
        ", description=" + description + ", enabled=" + enabled + "]";
    }
}
