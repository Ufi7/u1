package com.u1.model;

public class SimpleAsset extends SimpleAsset0 {
	public SimpleAsset(){
		super();
	}
	public SimpleAsset(String str){
		super.setAssetPid(Integer.valueOf(str));
	}
	
	private AssetType assetType;

	/**
	 * @return the assetType
	 */
	public AssetType getAssetType() {
		return assetType;
	}

	/**
	 * @param assetType the assetType to set
	 */
	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}
	
}
