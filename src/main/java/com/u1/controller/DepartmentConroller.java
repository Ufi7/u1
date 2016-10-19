package com.u1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u1.model.AssetType;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.Department;
import com.u1.util.Util;
@Controller
public class DepartmentConroller extends BaseController {
	@RequestMapping("/department")
	public String listAndInit(ModelMap model){
		//return super.listAndInitAndUpdateSessionSearchCS(model, Department.class, "department", true, true, new Department(), "department", null,true);
		model.put("department", new Department());
		return "department";
	}
	
	@RequestMapping(value="/departmentsubmit", method = RequestMethod.POST)
	public @ResponseBody String addNew(ModelMap model, @Valid Department department, BindingResult result){
		Department d = new Department();
		d.setDepartmentName(department.getDepartmentName());
		d.setDepartmentDesc(department.getDepartmentDesc());
		d.setCustomer(department.getCustomer());
		d.setEnabled(true);
		return super.addNewAndReturnNewId(d, result);
	}
	
	@RequestMapping("/departmentlist/{customerid}")
	public String listpage(@PathVariable int customerid, ModelMap modelmap){
		return listpage(customerid, 1, modelmap);
	}
	
	@RequestMapping("/departmentlist/{customerid}/{page}")
	public String listpage(@PathVariable int customerid, @PathVariable int page, ModelMap modelmap){
		//return super.listpage(model, page, Department.class, "departmentlist", true);
		List<Condition> cs = initEnabledCondition();
		Condition c1 = new Condition("customer", new Customer(customerid));
		cs.add(c1);
		return listpage(modelmap, page, Department.class, cs, "departmentlist");
	}
	
	@RequestMapping("/departmentdetail/{id}") 
	public String detailEnquiry(@PathVariable Integer id, ModelMap model){
		return super.detailEnquiry(model, Department.class, id, "departmentDetail", "departmentdetail");
	}
	
	@RequestMapping(value="/departmentdetailupdate/{id}",method = RequestMethod.POST) 
	public String update(@PathVariable Integer id, @Valid Department department, BindingResult result, ModelMap model){
		Department d = new Department();
		d.setDepartmentName(department.getDepartmentName());
		d.setDepartmentDesc(department.getDepartmentDesc());
		return super.update(model, d, id, result, "success_update_department", d.getDepartmentName());
	}
	
	@RequestMapping(value="/departmentdelete/{id}",method = RequestMethod.POST) 
	public String delete(@PathVariable Integer id, ModelMap modelmap){
		return super.updateStatus(modelmap, Department.class, id, true, "success_delete_department");
	}	
}
