package com.u1.service.impl;

import com.u1.dao.SqlDao;

import com.u1.model.Asset;
import com.u1.model.AssetAttributesTemplateWithOption;
import com.u1.model.AssetLog;
import com.u1.model.AssetTypeWithTemplate;
import com.u1.model.RefUser;
import com.u1.model.SearchResult;
import com.u1.model.SimpleUsers;
import com.u1.model.TaskLog;

import com.u1.service.AssetService;
import com.u1.model.UserForAuthOnly;

import com.u1.util.Constants;
import com.u1.util.Util;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class AssetServiceImpl extends CommonServiceImpl implements AssetService {
    protected SqlDao sqlDao;

    public String updateAsset(Integer id, Asset newone, HttpServletRequest request,com.u1.model.UserForAuthOnly user) {
        Asset dbone = (Asset) commonDao.get(Asset.class, id);
        Util.checkCrossCustomerProcess(user, dbone.getCustomer());
        String error = Util.buildAssetAttributes(dbone.getAssetType(), newone, request, dbone.getAttributeList());

        if (!error.equals("")) { //has error

            return Constants.HTML_PREFIX_CONTENT_ERROR + error;
        }
        newone.setAssetNum(null); //keep asset Num
                                  //handle object update
        commonDao.myMerge(newone, id, 1);
        String remark=(String)request.getParameterValues("remark")[0];
        createAssetLog(id, new RefUser(user.getUserId()), Constants.ASSET_ACTION_CODE_UPDATE, remark, newone.getStatus());
        return Constants.HTML_PREFIX_CONTENT_SUCCESS;
    }

    public String addAsset(Integer assetTypeid, Asset newone,
        HttpServletRequest request) {
        AssetTypeWithTemplate at = (AssetTypeWithTemplate) commonDao.get(AssetTypeWithTemplate.class,
                assetTypeid);
        String error = Util.buildAssetAttributes(at, newone, request, null);

        if (!error.equals("")) { //has error

            return Constants.HTML_PREFIX_CONTENT_ERROR + error;
        }

        int definedNum = sqlDao.lockGetAssetTypeDefinedNum(assetTypeid);
        String numstr = String.valueOf(definedNum);
        while (numstr.length() < Constants.AUTOGEN_NUM_LENGTH) {
            numstr = "0" + numstr;
        }
        newone.setAssetNum(Constants.ASSET_NUM_PREFIX.concat(String.valueOf(
                    Calendar.getInstance().get(Calendar.YEAR)))
                                                     .concat(at.getDefinedCode())
                                                     .concat(numstr));
        
        commonDao.save(newone).toString();
        String remark=(String)request.getParameterValues("remark")[0];
        createAssetLog(newone.getAssetPid(), newone.getCreatedBy(), Constants.ASSET_ACTION_CODE_NEW, remark, newone.getStatus());
        return newone.getAssetPid().toString();
    }

    public void setSqlDao(SqlDao sqlDao) {
        this.sqlDao = sqlDao;
    }
    
    public void createAssetLog(Integer assetPid, RefUser owner, String actionCode, String remark, String autoremark) {
    	AssetLog tl = new AssetLog(assetPid, actionCode, owner, remark, autoremark);
        commonDao.save(tl);
    }
}
