package com.u1.model;

// Generated 2013-11-20 23:37:44 by Hibernate Tools 3.4.0.CR1
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import org.hibernate.validator.constraints.Length;

import com.u1.model.SimpleAsset0.Entity;
/**
 * AssetGroup generated by hbm2java
 */
public class AssetGroup0 implements java.io.Serializable, CustomerInterface {

	private Integer assetGroupPid;
	@Pattern(regexp=ModelConstants.REG_CHN_20, message="{Pattern.assetgroup.groupName}")
	private String groupName;
	@Pattern(regexp=ModelConstants.REG_CHN_ALLOW_EMPTY_20, message="{Pattern.assetgroup.description}")
	private String description;
	private Boolean enabled;
	@NotNull(groups=Entity.class, message="{NotNull.customer}")
	private Customer customer;

	public AssetGroup0() {
	}

	public AssetGroup0(String groupName, String description, boolean enabled) {
		this.groupName = groupName;
		this.description = description;
		this.enabled = enabled;
	}

	public Integer getAssetGroupPid() {
		return this.assetGroupPid;
	}

	public void setAssetGroupPid(Integer assetGroupPid) {
		this.assetGroupPid = assetGroupPid;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
		return "AssetGroup0 [assetGroupPid=" + assetGroupPid + ", groupName="
				+ groupName + ", description=" + description + ", enabled="
				+ enabled + "]";
	}

	
	public interface Entity extends Default{}
}
