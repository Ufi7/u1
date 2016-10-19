package com.u1.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.u1.model.CacheItem;
import com.u1.model.Condition;
import com.u1.model.Order;
import com.u1.model.Resources;
import com.u1.model.Roles;
import com.u1.model.RoleWithResource;
import com.u1.model.SearchResult;
import com.u1.model.SelectOptionModel;
import com.u1.service.CommonService;
import com.u1.test.U1Test;
import com.u1.util.CacheUtil;
import com.u1.util.Constants;
import com.u1.util.Util;

@Controller
@SessionAttributes("searchRole")
public class RolesController extends BaseController {
	
	@RequestMapping("/roles")
	public String listpage(ModelMap model, SessionStatus status){
		//refresh the seesion key, to empty the search formbean cache
		List<Condition> conditions=new ArrayList();
		model.put("searchRole", conditions);
		
		//default process to search all result 
		
		SearchResult sr = this.commonService.search(Roles.class, null, null);
		Util.buildSearchResultList(model, sr);
		
		//init formbean
		model.put("roles", new Roles());
		
		return "roles";
	}
	
	@RequestMapping(value="/rolessearch", method = RequestMethod.POST)
	public String search(@Valid Roles searchRole, BindingResult result, ModelMap model){
		//TODO handle validation
//		if(result.hasErrors()){
//			return "validationerror";
//		}
		
		//init search condition and put to session
		List<Condition> conditions=new ArrayList();
		if(!"".equals(searchRole.getRoleName())){
			Condition c = new Condition("roleName", searchRole.getRoleName() , false);
			conditions.add(c);
		}
		model.put("searchRole", conditions);
		
		//process search with conditions
		SearchResult sr = this.commonService.search(Roles.class, conditions, null);
		Util.buildSearchResultList(model, sr);

		return "roleslist";
	}
	
	@RequestMapping("/roleslist/{page}")
	public String searchByPage(@PathVariable int page, ModelMap model, @ModelAttribute("searchRole") List<Condition> conditions){
		//check parameter
		if(page<1){
			page=1;//set to default if invalid
		}
		
		//process search with session conditions
		SearchResult sr = this.commonService.search(Roles.class, conditions, null, page-1);
		Util.buildSearchResultList(model, sr);

		return "roleslist";
	}
	
	@RequestMapping("/rolesdetail/{id}") 
	public String detailEnquiry(@PathVariable Integer id, ModelMap model){
		
		List<Resources> allResourceList;
		RoleWithResource rr;
		Object[] dbReturn=this.commonService.getAbAndAllB(id, RoleWithResource.class, Resources.class);
		rr=(RoleWithResource)dbReturn[0];
		allResourceList=(List<Resources>)dbReturn[1];
				
		//handle if selected role missing
		if(rr==null){
			//TODO handle error while role missing
		}
		
		//set result to request scope
		model.put("roleWithResource", rr);
		Util.build3SelTeamplate(model, allResourceList, rr.getResourceList(),"resourceList");
		
		return "rolesdetail";
	}
	
	@RequestMapping(value="/rolesdetailupdate/{id}", method = RequestMethod.POST) 
	public String update(@PathVariable Integer id, @Valid RoleWithResource roleWithResource,
			BindingResult result, ModelMap model){
		//TODO handle validation
		if(result.hasErrors()){
			return "validationerror";
		}
		
//		//the Id was automatically set in name, need to copy
//		if(roleWithResource.getResourceList()!=null){
//			for(Resources r:roleWithResource.getResourceList()){
//				r.setResourceId(Integer.valueOf(r.getResourceName()));
//			}
//		}
//				
		//update roles
		roleWithResource.setRoleId(id);
		this.commonService.update(roleWithResource);
		
		//update success message
		model.put("message_key", "success_update_roles");
		model.put("key_word", roleWithResource.getRoleName());
		
		return "success";
	}
	
	@RequestMapping("/addroles")
	public String addNewPage(ModelMap model){
		
		//init selectable resource list
		Util.build3SelTeamplate(model, this.commonService.listAll(Resources.class), null, "resourceList");
		
		//init empty formbean
		model.put("roleWithResource", new RoleWithResource());
		
		return "addroles";
	}
	
	@RequestMapping(value="/addrolessubmit", method = RequestMethod.POST)
	public String addNew(@Valid RoleWithResource roleWithResource, BindingResult result, ModelMap model){
		//TODO handle validation
		if(result.hasErrors()){
			return "validationerror";
		}
		
		//update roles
		roleWithResource.setRoleId(null);
		this.commonService.save(roleWithResource);
		
		//update success message
		model.put("message_key", "success_add_roles");
		model.put("key_word", roleWithResource.getRoleName());
		
		return "success";
	}
}
