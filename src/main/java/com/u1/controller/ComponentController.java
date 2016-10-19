package com.u1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import com.u1.exception.U1Exception;
import com.u1.model.Component;
import com.u1.model.Componentrecord;
import com.u1.model.Componentrecord.NeedPrice;
import com.u1.model.Componentstore;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.Order;
import com.u1.model.RefUser;
import com.u1.model.SearchResult;
import com.u1.model.Task;
import com.u1.model.TaskWithAsset;
import com.u1.model.UserForAuthOnly;
import com.u1.service.ComponentService;
import com.u1.util.Constants;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;

@Controller
@SessionAttributes({"searchConpomentstoreCS","searchConpomenttypeCS", "searchConpomentSelectstoreCS"})
public class ComponentController extends BaseController {

	@RequestMapping("/component")
	public String init(ModelMap modelmap){
		modelmap.put("searchConpomentstoreCS", Util.initEnabledCondition());
		modelmap.put("searchConpomenttypeCS", new ArrayList<Condition>());
		modelmap.put("componentSearch", new Component());
		return "component";
	}
	
	@RequestMapping(value="/componentsearch", method=RequestMethod.POST)
	public @ResponseBody SearchResult search(ModelMap modelmap, Component component, Authentication authentication, HttpServletRequest request){
		//TODO
		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
		List<Condition> cs = initEnabledCondition();
		Condition c;
		Integer customerid = Util.handleCustomerInfo(user, request, false);
		c = new Condition("customerId", customerid);
		cs.add(c);
		if(!component.getField1().isEmpty()){
			c = new Condition("component/field1", component.getField1(), false);
			cs.add(c);
		}
		if(!component.getField2().isEmpty()){
			c = new Condition("component/field2", component.getField2(), false);
			cs.add(c);
		}
		if(!component.getField3().isEmpty()){
			c = new Condition("component/field3", component.getField3(), false);
			cs.add(c);
		}
		if(!component.getField4().isEmpty()){
			c = new Condition("component/field4", component.getField4(), false);
			cs.add(c);
		}
		if(!component.getField5().isEmpty()){
			c = new Condition("component/field5", component.getField5(), false);
			cs.add(c);
		}
		if(!component.getField6().isEmpty()){
			c = new Condition("component/field6", component.getField6(), false);
			cs.add(c);
		}
		if(!component.getField7().isEmpty()){
			c = new Condition("component/field7", component.getField7(), false);
			cs.add(c);
		}
		if(!component.getField8().isEmpty()){
			c = new Condition("component/field8", component.getField8(), false);
			cs.add(c);
		}
//		if(!component.getField9().isEmpty()){
//			c = new Condition("component/field9", component.getField9(), false);
//			cs.add(c);
//		}
//		if(!component.getField10().isEmpty()){
//			c = new Condition("component/field10", component.getField10(), false);
//			cs.add(c);
//		}
		modelmap.put("searchConpomentstoreCS", cs);
		return commonService.search(Componentstore.class, cs, null);
	}
	
	@RequestMapping(value="/componenttypesearch", method=RequestMethod.POST)
	public @ResponseBody SearchResult searchType(ModelMap modelmap, Component component){
		//TODO 
		//List<Condition> cs = initEnabledCondition();
		List<Condition> cs = new ArrayList();
		Condition c;
		if(!component.getField1().isEmpty()){
			c = new Condition("field1", component.getField1(), false);
			cs.add(c);
		}
		if(!component.getField2().isEmpty()){
			c = new Condition("field2", component.getField2(), false);
			cs.add(c);
		}
		if(!component.getField3().isEmpty()){
			c = new Condition("field3", component.getField3(), false);
			cs.add(c);
		}
		if(!component.getField4().isEmpty()){
			c = new Condition("field4", component.getField4(), false);
			cs.add(c);
		}
		if(!component.getField5().isEmpty()){
			c = new Condition("field5", component.getField5(), false);
			cs.add(c);
		}
		if(!component.getField6().isEmpty()){
			c = new Condition("field6", component.getField6(), false);
			cs.add(c);
		}
		if(!component.getField7().isEmpty()){
			c = new Condition("field7", component.getField7(), false);
			cs.add(c);
		}
		if(!component.getField8().isEmpty()){
			c = new Condition("field8", component.getField8(), false);
			cs.add(c);
		}
//		if(!component.getField9().isEmpty()){
//			c = new Condition("field9", component.getField9(), false);
//			cs.add(c);
//		}
//		if(!component.getField10().isEmpty()){
//			c = new Condition("field10", component.getField10(), false);
//			cs.add(c);
//		}
		modelmap.put("searchConpomenttypeCS", cs);
		return commonService.search(Component.class, cs, null);
	}
	
	@RequestMapping(value="/componentlist/{page}")
	public @ResponseBody SearchResult search( @PathVariable int page, @ModelAttribute("searchConpomentstoreCS") List<Condition> cs){
		return commonService.search(Componentstore.class, cs, null, page<1?0:page-1);
	}
	
	@RequestMapping(value="/componenttypelist/{page}")
	public @ResponseBody SearchResult searchType( @PathVariable int page, @ModelAttribute("searchConpomenttypeCS") List<Condition> cs){
		return commonService.search(Component.class, cs, null, page<1?0:page-1);
	}
	
	@RequestMapping(value="/addnewcomponenttype", method=RequestMethod.POST)
	public @ResponseBody Component addNewType(@Valid Component component, BindingResult result, ModelMap modelmap){
		if(result.hasErrors()){
			throw new U1Exception(buildErrorMessage(result));
		}
		commonService.save(component);
		return component;
	}
	
	@RequestMapping(value="/newcomponentstore", method=RequestMethod.POST)
	public @ResponseBody String addNewStore(Authentication authentication, HttpServletRequest request, Componentstore store, @Valid Componentrecord record, BindingResult result){
		if(result.hasErrors()){
			return buildErrorMessage(result);
		}
		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
		if((store.getCustomerId().equals(user.getCustomer().getCustomerId()) && !user.getAccessRights().contains(new Integer(8)))
				|| (record.getReason()!=1 && record.getReason()!=2 && record.getReason()!=5)
				){
			throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));	//handle error case
		}else{
			if(record.getReason()==1){
				Set<ConstraintViolation<Componentrecord>> c=validator.validate(record, NeedPrice.class);
				if(c.size()!=0){
					return super.buildErrorMessage(c);
				}
			}
		}
		store.setCount(record.getQuantity());
		store.setEnabled(true);
		
		//record.setComponentstoreId();//
		record.setUser(new RefUser(user.getUserId()));
		record.setTaskLog(null);
		record.setLogQuantity(record.getQuantity());
		componentService.addNewComponentStore(store, record);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	@RequestMapping("/componentstoredetail/{id}")
	public String componentDetail(@PathVariable int id, ModelMap modelmap){
		modelmap.put("componentstoreDetail", commonService.get(Componentstore.class, id));
		return "componentstoredetail";
	}
	
	@RequestMapping("/componentstorestatistic/{id}")
	public @ResponseBody Object getComponentstoreStatistic(@PathVariable int id){
		return componentService.getComponentstoreStatistics(id);
	}
	
	@RequestMapping("/componentstorelatestprice/{id}")
	public @ResponseBody Double getComponentstoreLatestPrice(@PathVariable int id){
		return componentService.getComponentstoreLatestPrice(id);
	}
	
	@RequestMapping("/componentstorelog/{id}")
	public String getComponentstoreLog(@PathVariable int id, ModelMap modelmap){
		List<Condition> cs=new ArrayList();
		Condition c = new Condition("componentstore", new Componentstore(id));
		cs.add(c);
		List<Order> os = new ArrayList();
		Order o = new Order("createdDatetime", false);
		os.add(o);
		modelmap.put("componentrecordlist", componentService.searchByMultiCondition(Componentrecord.class, cs, os, 0, 0));
		return "componentstorelog";
	}
	
	@RequestMapping(value="/updatecomponentstore/{id}", method=RequestMethod.POST)
	public @ResponseBody String updateStore(@PathVariable int id, @Valid Componentrecord record, BindingResult result, Authentication authentication, HttpServletRequest request){
		if(result.hasErrors()){
			return buildErrorMessage(result);
		}
		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
		Integer verifyCustomerId = user.getAccessRights().contains(new Integer(8))?0:user.getCustomer().getCustomerId();//if allow cross company set 0
		if(record.getReason()==3){
			throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));	//handle error case
		}else if(record.getReason()==1){
			Set<ConstraintViolation<Componentrecord>> c=validator.validate(record, NeedPrice.class);
			if(c.size()!=0){
				return super.buildErrorMessage(c);
			}
		}
		record.setComponentstore(new Componentstore(id));
		record.setUser(new RefUser(user.getUserId()));
		record.setTaskLog(null);
		componentService.updateComponentRecord(id, verifyCustomerId, record);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	@RequestMapping(value="/componentselectsearch", method=RequestMethod.POST)
	public @ResponseBody SearchResult searchForComponentSelect(ModelMap modelmap, Component component, Authentication authentication, HttpServletRequest request){
		//TODO
		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
		List<Condition> cs = initEnabledCondition();
		Condition c;
		Integer customerid = Util.handleCustomerInfo(user, request, false);
		c = new Condition("customerId", customerid);
		cs.add(c);
		if(!component.getField1().isEmpty()){
			c = new Condition("component/field1", component.getField1(), false);
			cs.add(c);
		}
		if(!component.getField2().isEmpty()){
			c = new Condition("component/field2", component.getField2(), false);
			cs.add(c);
		}
		if(!component.getField3().isEmpty()){
			c = new Condition("component/field3", component.getField3(), false);
			cs.add(c);
		}
		if(!component.getField4().isEmpty()){
			c = new Condition("component/field4", component.getField4(), false);
			cs.add(c);
		}
		if(!component.getField5().isEmpty()){
			c = new Condition("component/field5", component.getField5(), false);
			cs.add(c);
		}
		if(!component.getField6().isEmpty()){
			c = new Condition("component/field6", component.getField6(), false);
			cs.add(c);
		}
		if(!component.getField7().isEmpty()){
			c = new Condition("component/field7", component.getField7(), false);
			cs.add(c);
		}
		if(!component.getField8().isEmpty()){
			c = new Condition("component/field8", component.getField8(), false);
			cs.add(c);
		}
//		if(!component.getField9().isEmpty()){
//			c = new Condition("component/field9", component.getField9(), false);
//			cs.add(c);
//		}
//		if(!component.getField10().isEmpty()){
//			c = new Condition("component/field10", component.getField10(), false);
//			cs.add(c);
//		}
		modelmap.put("searchConpomentSelectstoreCS", cs);
		return commonService.search(Componentstore.class, cs, null);
	}
	
	@RequestMapping(value="/componentselectlist/{page}")
	public @ResponseBody SearchResult searchForComponentSelectPage( @PathVariable int page, @ModelAttribute("searchConpomentSelectstoreCS") List<Condition> cs){
		return commonService.search(Componentstore.class, cs, null, page<1?0:page-1);
	}
	
	@Resource
	protected ComponentService componentService;
}
