package com.u1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.u1.model.Condition;
import com.u1.model.Groups;
import com.u1.model.PasswordChange;
import com.u1.model.Resources;
import com.u1.model.RoleWithResource;
import com.u1.model.Roles;
import com.u1.model.SearchResult;
import com.u1.model.SimpleUserWithGroup;
import com.u1.model.SimpleUserWithGroupAndPassword;
import com.u1.model.SimpleUsers;
import com.u1.model.SimpleUsers0;
import com.u1.model.UserForAuthOnly;
import com.u1.service.CommonService;
import com.u1.service.UserAuthService;
import com.u1.util.Constants;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;

@Controller
@SessionAttributes("searchUser")
public class UsersController extends BaseController {
	@RequestMapping("/users")
	public String listpage(ModelMap model, SessionStatus status){
		
		//refresh the seesion key, to empty the search formbean cache
		List<Condition> conditions=new ArrayList();
		model.put("searchUser", conditions);
		
		//default process to search all result
		SearchResult sr = this.commonService.search(SimpleUsers.class, null, null);
		Util.buildSearchResultList(model, sr);
		
		//init formbean
		model.put("simpleUsers", new SimpleUsers());
		
		return "users";
	}
	
	@RequestMapping(value="/userssearch", method = RequestMethod.POST)
	public String search(@Valid SimpleUsers searchSimpleUsers, BindingResult result, ModelMap model){
		//vdalidate search input
//		if(result.hasErrors()){
//			return "validationerror";
//		}
		
		//init search condition
		List<Condition> conditions=new ArrayList();
		if(!"".equals(searchSimpleUsers.getUsername())){
			Condition c = new Condition("username", searchSimpleUsers.getUsername() , false);
			conditions.add(c);
		}
		if(!"".equals(searchSimpleUsers.getGivenName())){
			Condition c = new Condition("givenName", searchSimpleUsers.getGivenName() , false);
			conditions.add(c);
		}
		if(!"".equals(searchSimpleUsers.getTelephone())){
			Condition c = new Condition("telephone", searchSimpleUsers.getTelephone() , false);
			conditions.add(c);
		}
		if(!"".equals(searchSimpleUsers.getEmail())){
			Condition c = new Condition("email", searchSimpleUsers.getEmail() , false);
			conditions.add(c);
		}
		model.put("searchUser", conditions);
		
		//process search with conditions
		SearchResult sr = this.commonService.search(SimpleUsers.class, conditions, null);
		Util.buildSearchResultList(model, sr);
		
		return "userslist";
	}
	
	@RequestMapping("/userslist/{page}")
	public String searchByPage(@PathVariable int page, ModelMap model, @ModelAttribute("searchUser") List<Condition> conditions){
		//check parameter
		if(page<1){
			page=1;//set to default if invalid
		}
		
		//process search with session conditions
		SearchResult sr = this.commonService.search(SimpleUsers.class, conditions, null, page-1);
		Util.buildSearchResultList(model, sr);
		
		return "userslist";
	}
	
	@RequestMapping("/usersdetail/{id}") 
	public String detailEnquiry(@PathVariable Integer id, ModelMap model){
		List<Groups> allGroupList;
		SimpleUserWithGroup simpleUserWithGroup;
		Object[] dbReturn=this.commonService.getAbAndAllB(id, SimpleUserWithGroup.class, Groups.class);
		simpleUserWithGroup=(SimpleUserWithGroup)dbReturn[0];
		allGroupList=(List<Groups>)dbReturn[1];
		
		if(simpleUserWithGroup==null){
			//TODO hanling if user missing
		}
		
		//set result to request scope
		model.put("simpleUserWithGroup", simpleUserWithGroup);
		Util.build3SelTeamplateForGroups(model, allGroupList, simpleUserWithGroup.getGroupList(),"groupList");
		
		return "usersdetail";
	}
	
	@RequestMapping(value="/usersdetailupdate/{id}", method = RequestMethod.POST) 
	public String detailEnquiry(@PathVariable Integer id, @Valid SimpleUserWithGroup simpleUserWithGroup,
			BindingResult result, ModelMap model){
		if(result.hasErrors()){
			return "validationerror";
		}
		
		//setup a new entity to update which matches files listed in page, avoid to update some unnecessary fields from page
		SimpleUserWithGroup updateSimpleUserWithGroup = new SimpleUserWithGroup();
		updateSimpleUserWithGroup.setUsername(simpleUserWithGroup.getUsername());
		updateSimpleUserWithGroup.setGivenName(simpleUserWithGroup.getGivenName());
		updateSimpleUserWithGroup.setTelephone(simpleUserWithGroup.getTelephone());
		updateSimpleUserWithGroup.setEmail(simpleUserWithGroup.getEmail());
		updateSimpleUserWithGroup.setGroupList(simpleUserWithGroup.getGroupList());
		updateSimpleUserWithGroup.setCustomer(simpleUserWithGroup.getCustomer());
		this.commonService.myMerge(updateSimpleUserWithGroup, id);
		
		//update success message
		model.put("message_key", "success_update_users");
		model.put("key_word", updateSimpleUserWithGroup.getUsername());
		
		return "success";
	}
	
	@RequestMapping("/addusers")
	public String addNewPage(ModelMap model){
		//init the groups list
		Util.build3SelTeamplateForGroups(model, this.commonService.listAll(Groups.class), null,"groupList");
		
		model.put("simpleUserWithGroup", new SimpleUserWithGroup());
		return "addusers";
	}
	
	@RequestMapping(value="/adduserssubmit", method = RequestMethod.POST)
	public String addNewUsers(@Valid SimpleUserWithGroupAndPassword simpleUserWithGroupAndPassword, BindingResult result, ModelMap model){
		if(result.hasErrors()){
			return "validationerror";
		}
		
		//setup a new entity to update which matches files listed in page, avoid to update some unnecessary fields from page
		SimpleUserWithGroupAndPassword newSimpleUserWithGroupAndPassword = new SimpleUserWithGroupAndPassword();
		newSimpleUserWithGroupAndPassword.setUsername(simpleUserWithGroupAndPassword.getUsername());
		newSimpleUserWithGroupAndPassword.setGivenName(simpleUserWithGroupAndPassword.getGivenName());
		newSimpleUserWithGroupAndPassword.setTelephone(simpleUserWithGroupAndPassword.getTelephone());
		newSimpleUserWithGroupAndPassword.setGroupList(simpleUserWithGroupAndPassword.getGroupList());
		newSimpleUserWithGroupAndPassword.setEmail(simpleUserWithGroupAndPassword.getEmail());
		newSimpleUserWithGroupAndPassword.setCustomer(simpleUserWithGroupAndPassword.getCustomer());
		
		//setup init information
		newSimpleUserWithGroupAndPassword.setPassword(Util.MD5(simpleUserWithGroupAndPassword.getTelephone()));
		newSimpleUserWithGroupAndPassword.setEnabled(true);
		this.commonService.save(newSimpleUserWithGroupAndPassword);
		
		//update success message
		model.put("message_key", "success_add_users");
		model.put("key_word", newSimpleUserWithGroupAndPassword.getUsername());
		
		return "success";
	}
	
	@RequestMapping(value="/pswchange", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(@Valid PasswordChange pc, BindingResult result, Authentication authentication){
		if(result.hasErrors()){
			return super.buildErrorMessage(result);
		}
		if(!pc.getNewpsw().equals(pc.getNewpsw2())){
			return super.buildErrorMessage(Constants.HTML_PREFIX_CONTENT_ERROR+MessageSourceHelper.getMessage("NEW_PASSWORD_2_INPUT_NOT_MATCH")); 
		}else if(pc.getOldpsw().equals(pc.getNewpsw())){
			return super.buildErrorMessage(Constants.HTML_PREFIX_CONTENT_ERROR+MessageSourceHelper.getMessage("NEW_PASSWORD_SAME_AS_OLD_ONE"));
		}
		return userAuthService.updatePassword(((UserForAuthOnly)authentication.getPrincipal()).getUserId(), Util.MD5(pc.getOldpsw()), Util.MD5(pc.getNewpsw()));
	}
	
	@Resource
	UserAuthService userAuthService;
}
