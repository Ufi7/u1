package com.u1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.u1.model.Asset;
import com.u1.model.AssetGroup;
import com.u1.model.AssetGroupWithSimpleAsset;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.TaskType;
import com.u1.model.UserForAuthOnly;
import com.u1.util.Constants;
import com.u1.util.Util;
@Controller
@SessionAttributes("searchAssetGroup")
public class AssetGroupController extends BaseController {

	@RequestMapping("/assetgroup")
	public String assetgroupinit(ModelMap modelmap){
		//return super.listAndInitAndUpdateSessionSearchCS(modelmap, AssetGroup.class, "assetgroup", true, true, new AssetGroup(), "assetGroup", "searchAssetGroup", true);
		modelmap.put("assetGroup", new AssetGroup());
		return "assetgroup";
	}
	
	@RequestMapping("/assetgrouplist/{page}")
	public String list(ModelMap modelmap, @PathVariable int page, @ModelAttribute("searchAssetGroup") List<Condition> conditions){
		return super.listpage(modelmap, page, AssetGroup.class, conditions, "assetgrouplist");
	}
	
	@RequestMapping(value="/assetgroupsearch", method = RequestMethod.POST)
	public String list(ModelMap modelmap, AssetGroup ag, Authentication authentication){
		List<Condition> cs = initEnabledCondition();
		//TODO add condition
		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
//        Customer targetCustomer = user.getCustomer();
//        if (ag.getCustomer()!= null && !targetCustomer.equals(ag.getCustomer())) {
//            if (user.getAccessRights().contains(new Integer(8))) {
//                targetCustomer = ag.getCustomer();
//            }
//        }
//        Condition c1 = new Condition("customer", targetCustomer);
//        cs.add(c1);
		
		Util.handleCustomerInfo(ag, user, true);
	    if(ag.getCustomer()!=null){
	       	Condition c = new Condition("customer", ag.getCustomer());
	       	cs.add(c);
	    }
		if(!ag.getGroupName().isEmpty()){
			Condition c = new Condition("groupName", ag.getGroupName(), false);
			cs.add(c);
		}
		modelmap.put("searchAssetGroup", cs);
		return super.listpage(modelmap, 0, AssetGroup.class, cs, "assetgrouplist");
	}
	
	@RequestMapping("/assetgroupdetail/{id}")
	public String detail(ModelMap modelmap,  @PathVariable Integer id){
		return super.detailEnquiry(modelmap, AssetGroupWithSimpleAsset.class, id, "assetGroupDetail", "assetgroupdetail");
	}
	
	@RequestMapping(value="/assetgroupupdate/{id}", method = RequestMethod.POST)
	public @ResponseBody String update(@PathVariable Integer id, @Valid AssetGroupWithSimpleAsset ag, BindingResult result, ModelMap modelmap){
//		Set<ConstraintViolation<AssetGroupWithSimpleAsset>> constraintViolations=validator.validate(ag, Default.class);
//		if(constraintViolations.size()!=0){
//			return super.buildErrorMessage(constraintViolations);
//		}
		ag.setCustomer(null);//foce clean the customer, avoid update this field
		if(result.hasErrors()){
			super.buildErrorMessage(result);
		}
		ag.setAssetGroupPid(id);
		commonService.myMerge(ag, id);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	@RequestMapping(value="/assetgroupdelete/{id}", method = RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Integer id){
		return super.updateStatusWithoutMessage(AssetGroup.class, id, true);
	}
	
	@RequestMapping(value="/addnewassetgroup", method = RequestMethod.POST)
	public @ResponseBody String addAssetGroup(ModelMap model, AssetGroup assetGroup, Authentication authentication){
		assetGroup.setAssetGroupPid(null);
		assetGroup.setEnabled(true);
		Util.handleCustomerInfo(assetGroup, (UserForAuthOnly)authentication.getPrincipal(), false);
		Set<ConstraintViolation<AssetGroup>> constraintViolations=validator.validate(assetGroup, com.u1.model.AssetGroup.Entity.class);
		if(constraintViolations.size()!=0){
			return super.buildErrorMessage(constraintViolations);
		}
		return commonService.save(assetGroup).toString();
	}
}
