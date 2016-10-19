package com.u1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.u1.model.Condition;
import com.u1.model.GroupWithRole;
import com.u1.model.Groups;
import com.u1.model.Resources;
import com.u1.model.Roles;
import com.u1.model.RoleWithResource;
import com.u1.model.SearchResult;
import com.u1.service.CommonService;
import com.u1.util.Util;

@Controller
@SessionAttributes("searchGroup")
public class GroupsController extends BaseController {
	@RequestMapping("/groups")
	public String listpage(ModelMap model, SessionStatus status){
		//refresh the seesion key, to empty the search formbean cache
		List<Condition> conditions=new ArrayList();
		model.put("searchGroup", conditions);
		
		//default process to search all result 
		
		SearchResult sr = this.commonService.search(Groups.class, null, null);
		Util.buildSearchResultList(model, sr);
		
		//init formbean
		model.put("groups", new Groups());
		
		return "groups";
	}
	
	@RequestMapping(value="/groupssearch", method = RequestMethod.POST)
	public String search(@Valid Groups searchGroup, BindingResult result, ModelMap model){
		//TODO handle validation
//		if(result.hasErrors()){
//			return "validationerror";
//		}
		
		//init search condition and put to session
		List<Condition> conditions=new ArrayList();
		if(!"".equals(searchGroup.getGroupName())){
			Condition c = new Condition("groupName", searchGroup.getGroupName() , false);
			conditions.add(c);
		}
		model.put("searchGroup", conditions);
		
		//process search with conditions
		SearchResult sr = this.commonService.search(Groups.class, conditions, null);
		Util.buildSearchResultList(model, sr);

		return "groupslist";
	}
	
	@RequestMapping("/groupslist/{page}")
	public String searchByPage(@PathVariable int page, ModelMap model, @ModelAttribute("searchGroup") List<Condition> conditions){
		//check parameter
		if(page<1){
			page=1;//set to default if invalid
		}
		
		//process search with session conditions
		SearchResult sr = this.commonService.search(Groups.class, conditions, null, page-1);
		Util.buildSearchResultList(model, sr);

		return "groupslist";
	}
	
	@RequestMapping("/groupsdetail/{id}") 
	public String detailEnquiry(@PathVariable Integer id, ModelMap model){
		
		//get Group by id and roles list
		Object[] GroupAndAllRolesList = this.commonService.getAbAndAllB(id, GroupWithRole.class, Roles.class);
		
		//init RoleWithResource(also for resource list optional)
		GroupWithRole gr = (GroupWithRole)GroupAndAllRolesList[0];
		
		//handle if selected role missing
		if(gr==null){
			//TODO handle error while group missing
		}
		
		//set result to request scope
		model.put("groupWithRole", gr);
		Util.build3SelTeamplateForRoles(model, (List<Roles>)GroupAndAllRolesList[1], gr.getRoleList(),"roleList");
		
		return "groupsdetail";
	}
	
	@RequestMapping(value="/groupsdetailupdate/{id}", method = RequestMethod.POST) 
	public String update(@PathVariable Integer id, @Valid GroupWithRole groupWithRole, BindingResult result, ModelMap model){
		
		//TODO handle validation
		if(result.hasErrors()){
			return "validationerror";
		}
		
		//update roles
		groupWithRole.setGroupId(id);
		this.commonService.update(groupWithRole);
		
		//update success message
		model.put("message_key", "success_update_groups");
		model.put("key_word", groupWithRole.getGroupName());
		
		return "success";
	}
	
	@RequestMapping("/addgroups")
	public String addNewPage(ModelMap model){
		
		//init selectable resource list
		Util.build3SelTeamplateForRoles(model, this.commonService.listAll(Roles.class), null, "roleList");
		
		//init empty formbean
		model.put("groupWithRole", new GroupWithRole());
		
		return "addgroups";
	}
	
	@RequestMapping(value="/addgroupssubmit", method = RequestMethod.POST)
	public String addNew(@Valid GroupWithRole groupWithRole, BindingResult result, ModelMap model){
		//TODO handle validation
		if(result.hasErrors()){
			return "validationerror";
		}
		
		//update roles
		groupWithRole.setGroupId(null);
		this.commonService.save(groupWithRole);
		
		//update success message
		model.put("message_key", "success_add_groups");
		model.put("key_word", groupWithRole.getGroupName());
		
		return "success";
	}
}
