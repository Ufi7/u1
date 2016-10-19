package com.u1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.SearchResult;
import com.u1.model.TaskType;
import com.u1.util.Constants;
import com.u1.util.Util;

@Controller
public class CustomerController extends BaseController {
	@RequestMapping("/customer")
	public String init(ModelMap modelmap){
		List<Condition> cs = Util.initEnabledCondition();
		SearchResult result = commonService.search(Customer.class, cs, null, 0);
		Util.buildSearchResultList(modelmap, result);
		return "customer";
	}
	
	@RequestMapping("/customerlist/{page}")
	public String list(@PathVariable int page, ModelMap modelmap){
		if(page<1){
			page=1;//set to default if invalid
		}
		List<Condition> cs = Util.initEnabledCondition();
		SearchResult result = commonService.search(Customer.class, cs, null, page-1);
		Util.buildSearchResultList(modelmap, result);
		return "customerlist";
	}
	
	@RequestMapping("/customerdetail/{id}")
	public String detail(@PathVariable Integer id, ModelMap modelmap){
		Customer c = (Customer)commonService.get(Customer.class, id);
		modelmap.put("customerDetail", c);
		return "customerdetail";
	}
	
	@RequestMapping(value="/customerdetailupdate/{id}", method=RequestMethod.POST)
	public @ResponseBody String update(@PathVariable Integer id, @Valid Customer customer, BindingResult result, ModelMap modelmap){
		return super.updateWithoutMessage(customer, id, result);
	}
	
	@RequestMapping(value="customerdelete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Integer id){
		return super.updateStatusWithoutMessage(Customer.class, id, true);
	}
	
	@RequestMapping(value="customersubmit", method=RequestMethod.POST)
	public @ResponseBody String addCustomer(@Valid Customer customer, BindingResult result){
		customer.setEnabled(true);
		return super.addNewAndReturnNewId(customer, result);
	}
}
