package com.u1.model;

import java.util.List;

public class AssetTypeWithTemplate extends AssetType0 {
	public AssetTypeWithTemplate(){
		super();
	}
	
	public AssetTypeWithTemplate(String id){
//		super(id);
//		this.setAssetTypePid(Integer.valueOf(id));
		this.assetTypePid=Integer.valueOf(id);
	}
	
	private List<AssetAttributesTemplateWithOption> templateList;
	
	//not for DB mapping
	private Object[][] existingAttributeIdValues;
	

	/**
	 * @return the existingAttributeIdValues
	 */
	public Object[][] getExistingAttributeIdValues() {
		return existingAttributeIdValues;
	}


	/**
	 * @param existingAttributeIdValues the existingAttributeIdValues to set
	 */
	public void setExistingAttributeIdValues(Object[][] existingAttributeIdValues) {
		this.existingAttributeIdValues = existingAttributeIdValues;
	}


	/**
	 * @return the templateList
	 */
	public List<AssetAttributesTemplateWithOption> getTemplateList() {
		return templateList;
	}


	/**
	 * @param templateList the templateList to set
	 */
	public void setTemplateList(List<AssetAttributesTemplateWithOption> templateList) {
		this.templateList = templateList;
	}

	
}
