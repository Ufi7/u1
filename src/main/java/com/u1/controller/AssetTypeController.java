package com.u1.controller;

import com.u1.model.AssetType;
import com.u1.model.Condition;
import com.u1.model.Department;
import com.u1.model.Resources;
import com.u1.model.RoleWithResource;
import com.u1.model.Roles;
import com.u1.model.SearchResult;

import com.u1.service.CommonService;

import com.u1.util.Util;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

import javax.validation.Valid;


@Controller
public class AssetTypeController extends BaseController {
    @RequestMapping("/assettype")
    public String initPage(ModelMap model) {
        SearchResult sr = this.commonService.search(AssetType.class,
                super.initEnabledCondition(), null);
        Util.buildSearchResultList(model, sr);

        model.put("assetType", new AssetType());

        return "assettype";
    }

    @RequestMapping(value = "/assettypesubmit", method = RequestMethod.POST)
    public @ResponseBody
    String addNew(@Valid
    AssetType assetType, BindingResult result) {
        AssetType at = new AssetType();
        at.setCode(assetType.getCode());
        at.setDescription(assetType.getDescription());
        at.setDefinedCode(assetType.getDefinedCode());
        at.setEnabled(true);
//        at.setDefinedNum(0);

        return super.addNewAndReturnNewId(at, result);
    }

    @RequestMapping("/assettypelist/{page}")
    public String searchByPage(@PathVariable
    int page, ModelMap model) {
        //check parameter
        if (page < 1) {
            page = 1; //set to default if invalid
        }

        //process search with session conditions
        SearchResult sr = this.commonService.search(AssetType.class,
                super.initEnabledCondition(), null, page - 1);
        Util.buildSearchResultList(model, sr);

        return "assettypelist";
    }

    @RequestMapping("/assettypedetail/{id}")
    public String detailEnquiry(@PathVariable
    Integer id, ModelMap model) {
        AssetType at = (AssetType) this.commonService.get(AssetType.class, id);

        //handle if selected role missing
        if (at == null) {
            //TODO handle error while role missing
        }

        //set result to request scope
        model.put("assetTypeDetail", at);

        return "assettypedetail";
    }

    @RequestMapping(value = "/assettypedetailupdate/{id}", method = RequestMethod.POST)
    public String update(@PathVariable
    Integer id, @Valid
    AssetType assetType, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "validationerror";
        }

        AssetType at = new AssetType();
        at.setCode(assetType.getCode());
        at.setDescription(assetType.getDescription());
        this.commonService.myMerge(assetType, id);

        //update success message
        model.put("message_key", "success_update_assettype");
        model.put("key_word", assetType.getCode());

        return "success";
    }

    @RequestMapping(value = "/assettypedelete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable
    Integer id, ModelMap model) {
        this.commonService.disable(AssetType.class, id);
        model.put("message_key", "success_delete_assettype");

        return "success";
    }
}
