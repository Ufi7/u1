package com.u1.util;

import java.util.List;

import javax.annotation.Resource;
import com.u1.model.CacheItem;
import com.u1.model.Resources;
import com.u1.model.RoleWithResource;
import com.u1.service.CommonService;

public class CacheUtil {
	private static CacheItem<List<Resources>> resourceList = new CacheItem<List<Resources>>(1000*60*20l);
	/**
	 * @return the resourceList
	 */
	public static CacheItem<List<Resources>> getResourceList() {
		return resourceList;
	}
	
//	/**
//	 * @return the resourceList
//	 */
//	public CacheItem<List<Resources>> getResourceList() {
//		if(resourceList.isExpiry()){
//			resourceList.forceUpdateCache(commonService.listAll(Resources.class));
//		}
//		return resourceList;
//	}
	
	@Resource 
	private CommonService commonService;
}
