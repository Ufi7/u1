package com.u1.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.u1.model.SimpleAsset0.Entity;


public class Asset extends SimpleAsset0 {
    private Boolean configable;
    private Integer approvedBy;
    private Integer configVersion;
    @Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_20, message = "{Pattern.asset.host}")
    private String host;
    @Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_20, message = "{Pattern.asset.owner}")
    private String owner;
    @Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_20, message = "{Pattern.asset.location}")
    private String location;
    @Digits(integer = 10, fraction = 2, message = "{Digits.asset.price}")
    private Double price;
    @Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_20, message = "{Pattern.asset.customNum}")
    private String customNum;
    @Min(value = 0, message = "{asset.weight}")
    @Max(value = 9999, message = "{asset.weight}")
    private Integer weight;
    @Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_20, message = "{Pattern.asset.operatingSystem}")
    private String operatingSystem;
    @Pattern(regexp = ModelConstants.REG_CHN_ALLOW_EMPTY_20, message = "{Pattern.asset.assetUsage}")
    private String assetUsage;
    @Pattern(regexp = "^breakdown$|^expired$|^fixing$|^idle$|^in_use$|^orderring$|^suspended$|^wait_for_fix$", message = "{Pattern.asset.status}")
    private String status;
    private RefUser createdBy;
    private List<AssetAttribute> attributeList;
    @NotNull(groups=Entity.class, message="{NotNull.asset.assetType}")
    private AssetTypeWithTemplate assetType;
    @NotNull(groups=Entity.class, message="{NotNull.customer}")
	private Customer customer;
    public Asset() {
        super();
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
     * @return the configable
     */
    public Boolean getConfigable() {
        return configable;
    }

    /**
     * @param configable the configable to set
     */
    public void setConfigable(Boolean configable) {
        this.configable = configable;
    }

    /**
     * @return the createdBy
     */
    public RefUser getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(RefUser createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the approvedBy
     */
    public Integer getApprovedBy() {
        return approvedBy;
    }

    /**
     * @param approvedBy the approvedBy to set
     */
    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     * @return the configVersion
     */
    public Integer getConfigVersion() {
        return configVersion;
    }

    /**
     * @param configVersion the configVersion to set
     */
    public void setConfigVersion(Integer configVersion) {
        this.configVersion = configVersion;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the customNum
     */
    public String getCustomNum() {
        return customNum;
    }

    /**
     * @param customNum the customNum to set
     */
    public void setCustomNum(String customNum) {
        this.customNum = customNum;
    }

    /**
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return the operatingSystem
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * @param operatingSystem the operatingSystem to set
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * @return the assetUsage
     */
    public String getAssetUsage() {
        return assetUsage;
    }

    /**
     * @param assetUsage the assetUsage to set
     */
    public void setAssetUsage(String assetUsage) {
        this.assetUsage = assetUsage;
    }

    /**
     * @return the assetType
     */
    public AssetTypeWithTemplate getAssetType() {
        return assetType;
    }

    /**
     * @param assetType the assetType to set
     */
    public void setAssetType(AssetTypeWithTemplate assetType) {
        this.assetType = assetType;
    }

    /**
     * @return the attributeList
     */
    public List<AssetAttribute> getAttributeList() {
        return attributeList;
    }

    /**
     * @param attributeList the attributeList to set
     */
    public void setAttributeList(List<AssetAttribute> attributeList) {
        this.attributeList = attributeList;
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
        return "Asset [configable=" + configable + ", approvedBy=" +
        approvedBy + ", configVersion=" + configVersion + ", host=" + host +
        ", owner=" + owner + ", location=" + location + ", price=" + price +
        ", customNum=" + customNum + ", weight=" + weight +
        ", operatingSystem=" + operatingSystem + ", assetUsage=" + assetUsage +
        ", createdBy=" + createdBy + ", assetType=" + assetType +
        ", attributeList=" + attributeList + ", toString()=" +
        super.toString() + "]";
    }
}
