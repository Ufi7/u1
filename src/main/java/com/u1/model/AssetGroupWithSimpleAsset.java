package com.u1.model;

import java.util.List;

public class AssetGroupWithSimpleAsset extends AssetGroup0 {
	
	private Customer customer;
	private List<SimpleAsset> assetList;

	
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
	
	
}
