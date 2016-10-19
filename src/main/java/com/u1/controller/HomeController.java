package com.u1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.u1.model.CacheItem;
import com.u1.model.Condition;
import com.u1.model.IdForm;
import com.u1.model.SimpleTask;
import com.u1.model.UserForAuthOnly;
import com.u1.service.CommonService;
import com.u1.service.UserAuthService;
import com.u1.util.Constants;
import com.u1.util.Util;


@Controller
//@SessionAttributes("user")
//@SessionAttributes("accessRights")
@SessionAttributes("currentCustomerID")
public class HomeController extends BaseController {
	
//	private CacheItem<Object[]> homePageTaskStatistic = new CacheItem<Object[]>(2*60*60*1000l); //2 hrs cache
	private Map overviewMap = new HashMap();
	
	@RequestMapping("/")
	protected String defaultPage(Authentication authentication, ModelMap model){
		return homepage(authentication, model);
	}
	
	@RequestMapping("/home")
	protected String homepage(Authentication authentication, ModelMap modelmap){
		modelmap.put("currentCustomerID", ((UserForAuthOnly)authentication.getPrincipal()).getCustomer().getCustomerId());
		return "home";
	}
	
	@RequestMapping(value="/homeswitch", method=RequestMethod.POST)
	protected @ResponseBody String homeSwitchpage(Authentication authentication, ModelMap modelmap, IdForm form){
		Integer id = 0;
		if (form.getId() != null) {
			id=form.getId();
		}
		modelmap.put("currentCustomerID", id);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
//	@RequestMapping("/home/taskoverview")
//	public @ResponseBody Object[] getTaskOverview(){
//		if(homePageTaskStatistic.isExpiry()){
//			homePageTaskStatistic.updateCache(statisticsService.getHomePageData());
//		}
//		return homePageTaskStatistic.getO();
//	}
	@RequestMapping("/home/taskoverview")
	public @ResponseBody Object[] getTaskOverview(@ModelAttribute("currentCustomerID") Integer customerId){
		CacheItem<Object[]> cache = (CacheItem<Object[]>)overviewMap.get(customerId.toString());
		if(cache==null){
			cache = new CacheItem<Object[]>(2*60*60*1000l); //2 hrs cache
			overviewMap.put(customerId.toString(), cache);
		}
		if(cache.isExpiry()){
			cache.updateCache(statisticsService.getHomePageData(customerId));
		}
		return cache.getO();
	}
	
	@RequestMapping("/home/listpendingtask")
	public @ResponseBody List<SimpleTask> getPendingTask( @ModelAttribute("currentCustomerID") Integer customerId){
		List<Condition> cs = Util.initEnabledCondition();
		Condition c;
		c = new Condition("status", new String[]{Constants.TASK_STATUS_ASSIGNED, Constants.TASK_STATUS_PENDING_REASSIGNED});
		cs.add(c);
		if(!customerId.equals(0)){
			c = new Condition("customerId", customerId);
			cs.add(c);
		}
		return commonService.searchByMultiCondition(SimpleTask.class, cs, null, 0, 0);
	}
	
	@RequestMapping("/home/mytask")
	public @ResponseBody Object[] getMyTaskSummary(Authentication authentication){
		return statisticsService.getMyTaskSummary(((UserForAuthOnly)authentication.getPrincipal()).getUserId());
	}	
	
	@RequestMapping("/seccode")
	public String seccode(){
		return "seccode";
	}
	
	
}
