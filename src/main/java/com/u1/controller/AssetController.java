package com.u1.controller;

import com.u1.exception.U1Exception;

import com.u1.model.Asset;
import com.u1.model.AssetAttribute;
import com.u1.model.AssetAttributesTemplateWithOption;
import com.u1.model.AssetLog;
import com.u1.model.AssetType;
import com.u1.model.AssetTypeWithTemplate;
import com.u1.model.Attachment;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.DataConvertion;
import com.u1.model.IdForm;
import com.u1.model.RefUser;
import com.u1.model.Resources;
import com.u1.model.SearchResult;
import com.u1.model.SimpleAsset;
import com.u1.model.SimpleAssetWithGroup;
import com.u1.model.SimpleAssetWithTask;
import com.u1.model.SimpleUsers;
import com.u1.model.TaskLog;
import com.u1.model.TaskWithAsset;
import com.u1.model.UserForAuthOnly;

import com.u1.service.AssetService;
import com.u1.service.CommonService;

import com.u1.util.Constants;
import com.u1.util.DataConvertionUtil;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import javax.persistence.criteria.From;

import javax.servlet.http.HttpServletRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.groups.Default;


@Controller
@SessionAttributes({"searchAsset",
    "currentAssetType"
})
public class AssetController extends BaseController {
    @Resource
    protected AssetService assetService;

    @RequestMapping("/asset")
    public String intiList(ModelMap modelmap) {
        //refresh the seesion key, to empty the search formbean cache
        //		List<Condition> conditions=super.initEnabledCondition();
        //		modelmap.put("searchAsset", conditions);
        //		Class[] classList = new Class[]{AssetType.class};
        //		Object[] dbreturn = commonService.getAlistAndOtherFullList(SimpleAsset.class, conditions, null, classList);
        //		Util.buildSearchResultList(modelmap, (SearchResult)dbreturn[0]);
        //		modelmap.put("assetTypeList", dbreturn[1]);
        modelmap.put("simpleAsset", new SimpleAsset());

        return "asset";
    }

    @RequestMapping("/assetlist/{page}")
    public String listpage(@PathVariable
    int page, ModelMap modelmap,
        @ModelAttribute("searchAsset")
    List<Condition> conditions) {
        return super.listpage(modelmap, page, SimpleAsset.class, conditions,
            "assetlist");
    }

    @RequestMapping(value = "/assetsearch", method = RequestMethod.POST)
    public String search(SimpleAsset simpleAsset, ModelMap modelmap,
        HttpServletRequest request, Authentication authentication) {
        List<Condition> conditions = super.initEnabledCondition();
        Condition c;
        UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
//        Customer targetCustomer = user.getCustomer();
//        if (!targetCustomer.equals(simpleAsset.getCustomer())) {
//            if (user.getAccessRights().contains(new Integer(8))) {
//                targetCustomer = simpleAsset.getCustomer();
//            }
//        }
//        if (targetCustomer!=null) {
//            c = new Condition("customer", targetCustomer);
//            conditions.add(c);
//        }
        Util.handleCustomerInfo(simpleAsset, user, true);
        if(simpleAsset.getCustomer()!=null){
        	c = new Condition("customer", simpleAsset.getCustomer());
          conditions.add(c);
        }
        if (simpleAsset.getAssetType() != null) {
            c = new Condition("assetType", simpleAsset.getAssetType());
            conditions.add(c);
        }

        if (!simpleAsset.getAssetName().isEmpty()) {
            c = new Condition("assetName", simpleAsset.getAssetName(), false);
            conditions.add(c);
        }

        if (!simpleAsset.getLocation().isEmpty()) {
            c = new Condition("location", simpleAsset.getLocation(), false);
            conditions.add(c);
        }

        if (!simpleAsset.getAssetNum().isEmpty()) {
            c = new Condition("assetNum", simpleAsset.getAssetNum(), false);
            conditions.add(c);
        }

        if (!simpleAsset.getStatus().isEmpty()) {
            c = new Condition("status", simpleAsset.getStatus());
            conditions.add(c);
        }

        String expiryDateFrom = request.getParameter("expiryDateFrom");
        String expiryDateTo = request.getParameter("expiryDateTo");

        if (!expiryDateFrom.isEmpty() || !expiryDateTo.isEmpty()) {
            DataConvertion dc = DataConvertionUtil.convertDateFromTo(expiryDateFrom,
                    expiryDateTo);

            if (dc.hasError()) {
                throw new U1Exception(dc.getErrorMessage());
            } else {
                c = new Condition("expiredDate", (Date) dc.getResult().get(0),
                        (Date) dc.getResult().get(1));
                conditions.add(c);
            }
        }

        modelmap.put("searchAsset", conditions);

        return super.listpage(modelmap, 0, SimpleAsset.class, conditions,
            "assetlist");
    }

    @RequestMapping("/assetdetail/{id}")
    public String detail(@PathVariable
    Integer id, ModelMap modelmap) {
        Asset a = (Asset) commonService.get(Asset.class, id);
        a.getAssetType()
         .setExistingAttributeIdValues(Util.returnAttributesStringArray(a));
        modelmap.put("assetDetail", a);

        return "assetdetail";
    }

    @RequestMapping(value = "/updateasset/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String update(@PathVariable
    Integer id, ModelMap modelmap, @Valid
    Asset a, BindingResult result, HttpServletRequest request,
        Authentication authentication, AssetLog al) {
    	a.setCustomer(null);//force clean customer from update...avoid to update customer
        //common fields validation
        Set<ConstraintViolation<AssetLog>> constraintViolations = validator.validate(al,
                javax.validation.groups.Default.class);

        if (constraintViolations.size() != 0) {
            return super.buildErrorMessage(constraintViolations);
        }

        if (result.hasErrors()) {
            return super.buildErrorMessage(result);
        }

        //real time validating the self-defined fields
        return assetService.updateAsset(id, a, request,
            (UserForAuthOnly) authentication.getPrincipal());
    }

    @RequestMapping("/newasset")
    public String initnewasset(ModelMap modelmap) {
        //		List<AssetType> atlist = this.commonService.listValid(AssetType.class);
        //		modelmap.put("assetTypeList", atlist);
        modelmap.put("newasset", new Asset());

        return "newasset";
    }

    @RequestMapping("/addasset/{assettypeid}")
    public String getAssetTypeDetail(@PathVariable
    Integer assettypeid, ModelMap modelmap) {
        AssetTypeWithTemplate at = (AssetTypeWithTemplate) commonService.get(AssetTypeWithTemplate.class,
                assettypeid);
        Asset a = new Asset();
        a.setAssetType(at);
        modelmap.put("newasset", a);

        return "addassetdetail";
    }

    @RequestMapping(value = "/newassetsubmit", method = RequestMethod.POST)
    public @ResponseBody
    String newAsset(ModelMap modelmap, Asset a, HttpServletRequest request,
        Authentication authentication, @Valid AssetLog al, BindingResult result) {
    	
    	//validation
        if (result.hasErrors()) {
            return super.buildErrorMessage(result);
        }
        
        UserForAuthOnly user = (UserForAuthOnly)authentication.getPrincipal();
		Util.handleCustomerInfo(a, user, false);
        Set<ConstraintViolation<Asset>> constraintViolations = validator.validate(a,
                com.u1.model.Asset.Entity.class);

        if (constraintViolations.size() != 0) {
            return super.buildErrorMessage(constraintViolations);
        }
        //Util.checkCrossCustomerProcess(authentication, a.getCustomer());
        
        RefUser su = new RefUser();
        su.setUserId(user.getUserId());
        a.setCreatedBy(su);
        a.setEnabled(true);

        return assetService.addAsset(a.getAssetType().getAssetTypePid(), a,
            request);
    }

    @RequestMapping("/assetattachment/{id}")
    public String getAssetAttchmentList(@PathVariable
    Integer id, ModelMap modelmap) {
        List<Condition> cs = super.initEnabledCondition();
        Condition c = new Condition("assetId", id);
        cs.add(c);

        List<Attachment> atml = commonService.searchByMultiCondition(Attachment.class,
                cs, null, 0, 0);
        modelmap.put("atmlist", atml);
        modelmap.put("assetid", id);

        return "assetattachmentlist";
    }

    @RequestMapping("/assettask/{id}")
    public String getRelatedTask(@PathVariable
    Integer id, ModelMap modelmap) {
        SimpleAssetWithTask sawt = (SimpleAssetWithTask) commonService.get(SimpleAssetWithTask.class,
                id);
        modelmap.put("result", sawt.getTaskList());

        return "assettasklist";
    }

    @RequestMapping("/assetingroup/{id}")
    public String getRelatedGroup(@PathVariable
    Integer id, ModelMap modelmap) {
        SimpleAssetWithGroup sawg = (SimpleAssetWithGroup) commonService.get(SimpleAssetWithGroup.class,
                id);
        modelmap.put("result", sawg.getGroupList());

        return "assetingrouplist";
    }

    @RequestMapping("/assetlog/{id}")
    public String getRelatedLog(@PathVariable
    Integer id, ModelMap modelmap) {
        List<Condition> cs = new ArrayList();
        Condition c = new Condition("assetPid", id);
        cs.add(c);

        List<AssetLog> tllist = commonService.searchByMultiCondition(AssetLog.class,
                cs, null, 0, 0);
        modelmap.put("assetlogList", tllist);

        return "assetlog";
    }

    @RequestMapping("/addasset")
    public String copyasset(ModelMap modelmap, IdForm form) {
        Asset a;

        if (form.getId() == null) {
            a = new Asset();
        } else {
            a = (Asset) commonService.get(Asset.class, form.getId());
            a.getAssetType()
             .setExistingAttributeIdValues(Util.returnAttributesStringArray(a));
            a.setAssetName(null);
        }

        modelmap.put("newasset", a);

        return "addasset";
    }
}
