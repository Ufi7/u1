package com.u1.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Length;

public class SimpleAsset0 implements java.io.Serializable, CustomerInterface {
	private Integer assetPid;
	//@Pattern(regexp="[\\s\u4e00-\u9fa5_a-zA-Z0-9_#.,!?。()-+/\\:。？！，（）]{0,20}", message="{Pattern.asset.assetName}")
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.asset.assetName}")
	//@Length(min=2, max=20, groups=Entity.class, message="{Length.asset.assetName}")
	//@NotNull(groups=Entity.class, message="{NotNull.asset.assetName}")
	private String assetName;
	private String location;
	private String assetNum;
	private Boolean enabled;
	private Date effectiveDate;
	private Date expiredDate;
	private String status;
	private Customer customer;
	public SimpleAsset0(){
		
	}
	
	public SimpleAsset0(String id){
		this.assetPid=Integer.valueOf(id);
	}

	/**
	 * @return the assetPid
	 */
	public Integer getAssetPid() {
		return assetPid;
	}

	/**
	 * @param assetPid the assetPid to set
	 */
	public void setAssetPid(Integer assetPid) {
		this.assetPid = assetPid;
	}

	/**
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/**
	 * @return the assetNum
	 */
	public String getAssetNum() {
		return assetNum;
	}

	/**
	 * @param assetNum the assetNum to set
	 */
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
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
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return the expiredDate
	 */
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * @param expiredDate the expiredDate to set
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
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

	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SimpleAsset0 [assetPid=" + assetPid + ", assetName="
				+ assetName + ", assetNum=" + assetNum + ", enabled=" + enabled
				+ ", effectiveDate=" + effectiveDate + ", expiredDate="
				+ expiredDate + ", status=" + status + "]";
	}

	public interface Entity extends Default{}
	
}
