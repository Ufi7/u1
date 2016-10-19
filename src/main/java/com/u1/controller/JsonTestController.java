package com.u1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.u1.model.Condition;
import com.u1.model.Groups;
import com.u1.model.Groups0;
import com.u1.model.Resources;
import com.u1.model.UserForAuthOnly;
import com.u1.service.CommonService;
import com.u1.service.UserAuthService;
@Controller
public class JsonTestController {
	
	@RequestMapping("/jsontest")
	protected @ResponseBody String[] test(){
//		Condition c = new Condition("username", "admin");
//		List<Condition> cs=new ArrayList();cs.add(c);
//		List a = commonService.listAll(UserForAuthOnly.class);
//		for(UserForAuthOnly u:(List<UserForAuthOnly>)a){
//			System.out.println(u);
//		}
//		return a;
		String[] test = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh", "ii", "jj"};
		
		return test;
	}
	
	@Resource
	private CommonService commonService;
}
