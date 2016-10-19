package com.u1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u1.model.AssetType;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.Department;
import com.u1.model.RefCustomer;
import com.u1.model.RefUser;
import com.u1.model.SimpleUsers;
import com.u1.model.TaskType;
@Controller
public class OptionController extends BaseController {
	@RequestMapping(value="/option/assettypelist")
	public @ResponseBody String[][] getAssetTypeOption(){
//		try{
//			Thread.sleep(5000);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		List<AssetType> atl = commonService.listValid(AssetType.class);
		String[][] returnvalue = new String[atl.size()][2];
		for(int i=0;i<atl.size();i++){
			returnvalue[i][0]=atl.get(i).getAssetTypePid().toString();
			returnvalue[i][1]=atl.get(i).getCode();
		}
		return returnvalue;
	}
	
	@RequestMapping(value="/option/tasktypelist")
	public @ResponseBody String[][] getTaskTypeOption(){
		List<TaskType> ttl = commonService.listValid(TaskType.class);
		String[][] returnvalue = new String[ttl.size()][2];
		for(int i=0;i<ttl.size();i++){
			returnvalue[i][0]=ttl.get(i).getTaskTypePid().toString();
			returnvalue[i][1]=ttl.get(i).getCode();
		}
		return returnvalue;
	}
	
	@RequestMapping(value="/option/userlist")
	public @ResponseBody String[][] getSimpleUsersOption(){
		List<RefUser> sul = commonService.listValid(RefUser.class);
		String[][] returnvalue = new String[sul.size()][2];
		for(int i=0;i<sul.size();i++){
			returnvalue[i][0]=sul.get(i).getUserId().toString();
			returnvalue[i][1]=sul.get(i).getGivenName();
		}
		return returnvalue;
	}
	
	@RequestMapping(value="/option/assigntolist/{customerid}")
	public @ResponseBody String[][] getAssignUsersOption(@PathVariable int customerid){
		List<Condition> cs = initEnabledCondition();
		Condition c = new Condition("customer", new Customer(customerid));
		cs.add(c);
		List<SimpleUsers> sul = commonService.searchByMultiCondition(SimpleUsers.class, cs, null, 0, 0);
		String[][] returnvalue = new String[sul.size()][2];
		for(int i=0;i<sul.size();i++){
			returnvalue[i][0]=sul.get(i).getUserId().toString();
			returnvalue[i][1]=sul.get(i).getGivenName();
		}
		return returnvalue;
	}
	
	@RequestMapping(value="/option/customerlist")
	public @ResponseBody String[][] getValidCustomer(){
		List<RefCustomer> dl = commonService.listValid(RefCustomer.class);
		String[][] returnvalue = new String[dl.size()][2];
		for(int i=0;i<dl.size();i++){
			returnvalue[i][0]=dl.get(i).getCustomerId().toString();
			returnvalue[i][1]=dl.get(i).getCustomerName();
		}
		return returnvalue;
	}
	
	@RequestMapping(value="/option/departmentlist/{customerid}")
	public @ResponseBody String[][] getValidDeartment(@PathVariable int customerid){
		//List<Department> dl = commonService.listValid(Department.class);
		List<Condition> cs = initEnabledCondition();
		Condition c = new Condition("customer", new Customer(customerid));
		cs.add(c);
		List<Department> dl = commonService.searchByMultiCondition(Department.class, cs, null, 0, 0);
		String[][] returnvalue = new String[dl.size()][2];
		for(int i=0;i<dl.size();i++){
			returnvalue[i][0]=dl.get(i).getDepartmentPid().toString();
			returnvalue[i][1]=dl.get(i).getDepartmentName();
		}
		return returnvalue;
	}
}
