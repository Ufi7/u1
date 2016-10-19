package com.u1.service;

import javax.servlet.http.HttpServletRequest;
import com.u1.model.Asset;
import com.u1.model.UserForAuthOnly;

public interface AssetService extends CommonService {
	
	public String updateAsset(Integer id, Asset newone, HttpServletRequest request, UserForAuthOnly user);
	
	public String addAsset(Integer AssetTypeid, Asset newone, HttpServletRequest request);
}
