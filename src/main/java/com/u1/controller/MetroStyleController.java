package com.u1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.u1.exception.U1Exception;
import com.u1.model.AssetGroup;
import com.u1.model.AssetGroupWithSimpleAsset;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.SearchResult;
import com.u1.model.SimpleAsset;

@Controller
@SessionAttributes({"searchMetroAsset","searchMetroAssetGroup"})
public class MetroStyleController extends BaseController {
	
	public static int METRO_BLOCKS_SIZE_PER_PAGE = 20;
	
	@RequestMapping("/metro/assetlist/{customerid}")
	public @ResponseBody SearchResult initMetroAssetList(ModelMap modelmap, @PathVariable int customerid){
		List<Condition> cs = super.initEnabledCondition();
		Condition condition = new Condition("customer", new Customer(customerid));
		cs.add(condition);
		modelmap.put("searchMetroAsset", cs);
		return commonService.search(SimpleAsset.class, cs, null, METRO_BLOCKS_SIZE_PER_PAGE, 0);
	}
	
	
	@RequestMapping(value="/metro/assetlist/{customerid}", method = RequestMethod.POST)
	public @ResponseBody SearchResult searchMetroAssetList(SimpleAsset sa, ModelMap modelmap,@PathVariable int customerid)throws U1Exception{
//		if(result.hasErrors()){
//			throw new U1Exception(buildErrorMessage(result));
//		}
		List<Condition> cs = super.initEnabledCondition();
		Condition condition = new Condition("customer", new Customer(customerid));
		cs.add(condition);
		modelmap.put("searchMetroAsset", cs);
		if(sa.getAssetType()!=null){
			Condition ctype = new Condition("assetType", sa.getAssetType());
			cs.add(ctype);
		}
		if(!"".equals(sa.getAssetName())){
			Condition cname = new Condition("assetName", sa.getAssetName(), false);
			cs.add(cname);
		}
		if(!"".equals(sa.getAssetNum())){
			Condition cnum = new Condition("assetNum", sa.getAssetNum(), false);
			cs.add(cnum);
		}
		if(!"".equals(sa.getStatus())){
			Condition cstatus = new Condition("status", sa.getStatus());
			cs.add(cstatus);
		}
		modelmap.put("searchMetroAsset", cs);
		return commonService.search(SimpleAsset.class, cs, null, METRO_BLOCKS_SIZE_PER_PAGE, 0);
	}
	
	@RequestMapping("/metro/assetlist/page/{page}")
	public @ResponseBody SearchResult metroAssetListPage(@PathVariable int page, ModelMap modelmap, @ModelAttribute("searchMetroAsset") List<Condition> cs){
		//check parameter
		if(page<1){
			page=1;//set to default if invalid
		}
		//process search with session conditions
		return commonService.search(SimpleAsset.class, cs, null, METRO_BLOCKS_SIZE_PER_PAGE, page-1);
	}
	
	@RequestMapping("/metro/assetgrouplist/{customerid}")
	public @ResponseBody SearchResult initMetroAssetGroupList(ModelMap modelmap, @PathVariable int customerid){
		List<Condition> cs = super.initEnabledCondition();
		Condition condition = new Condition("customer", new Customer(customerid));
		cs.add(condition);
		modelmap.put("searchMetroAssetGroup", cs);
		return commonService.search(AssetGroup.class, cs, null, METRO_BLOCKS_SIZE_PER_PAGE, 0);
	}
	
	@RequestMapping(value="/metro/assetgrouplist/{customerid}", method = RequestMethod.POST)
	public @ResponseBody SearchResult searchMetroGroupAsstList(AssetGroup ag, ModelMap modelmap, @PathVariable int customerid)throws U1Exception{
//		if(result.hasErrors()){
//			throw new U1Exception(buildErrorMessage(result));
//		}
		List<Condition> cs = super.initEnabledCondition();
		Condition condition = new Condition("customer", new Customer(customerid));
		cs.add(condition);
		if(!"".equals(ag.getGroupName())){
			Condition cname = new Condition("description", ag.getGroupName(), false);
			cs.add(cname);
		}
		modelmap.put("searchMetroAssetGroup", cs);
		return commonService.search(AssetGroup.class, cs, null, METRO_BLOCKS_SIZE_PER_PAGE, 0);
	}
	
	@RequestMapping("/metro/assetgrouplist/page/{page}")
	public @ResponseBody SearchResult metroAssetGroupListPage(@PathVariable int page, ModelMap modelmap, @ModelAttribute("searchMetroAssetGroup") List<Condition> cs){
		//check parameter
		if(page<1){
			page=1;//set to default if invalid
		}
		//process search with session conditions
		return commonService.search(AssetGroup.class, cs, null, METRO_BLOCKS_SIZE_PER_PAGE, page-1);
	}
	
	@RequestMapping("/metro/assetlistingroup/{groupid}")
	public @ResponseBody String[][] getAssetStringUnderGroup(@PathVariable Integer groupid){
		AssetGroupWithSimpleAsset agwa = (AssetGroupWithSimpleAsset)commonService.get(AssetGroupWithSimpleAsset.class, groupid);
		if(agwa==null){
			//todo no record hanlding
		}
		String[][] returnValue = new String[agwa.getAssetList().size()][5];
		for(int i=0;i<agwa.getAssetList().size();i++){
			SimpleAsset sa = agwa.getAssetList().get(i);
			returnValue[i][0] = sa.getAssetPid().toString();
			returnValue[i][1] = sa.getAssetName();
			returnValue[i][2] = sa.getAssetNum();
			returnValue[i][3] = sa.getAssetType().getCode();
			returnValue[i][4] = sa.getStatus();
		}
		return returnValue;
	}
}
