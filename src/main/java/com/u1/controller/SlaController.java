package com.u1.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.u1.exception.U1Exception;
import com.u1.model.Condition;
import com.u1.model.DataConvertion;
import com.u1.model.SimpleUsers;
import com.u1.model.Slaconfig;
import com.u1.model.TaskLog;
import com.u1.model.UserForAuthOnly;
import com.u1.service.AssetService;
import com.u1.service.SlaService;
import com.u1.util.Constants;
import com.u1.util.DataConvertionUtil;
import com.u1.util.Util;

@Controller
public class SlaController extends BaseController {
	@RequestMapping("/slasetup")
	public String setupinit(ModelMap modelmap){
		Slaconfig config = slaService.getSla();
		modelmap.put("sla", config);
		return "slasetup";
	}
	
	@RequestMapping(value="/slasetupsubmit", method=RequestMethod.POST)
	public @ResponseBody String update(ModelMap modelmap, Slaconfig config){
		Class validateClass = null;
		if(config.getSeloption()==2){
			validateClass = Slaconfig.Option2.class;
		}else{
			validateClass = Slaconfig.Option1.class;
		}
		Set<ConstraintViolation<Slaconfig>> s=validator.validate(config, validateClass);
		if(s.size()!=0){
			return super.buildErrorMessage(s);
		}
		slaService.updateSla(config);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	@RequestMapping("/sla")
	public String slaStasInit(){
		return "sla";
	}
	
	@RequestMapping(value="/slacalc", method=RequestMethod.POST)
	public @ResponseBody List<Object[]> slaStasCalc(SimpleUsers inputuser, HttpServletRequest request, Authentication authentication){
		UserForAuthOnly userlogin = (UserForAuthOnly) authentication.getPrincipal();
		//validate date range
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		if(!dateFrom.isEmpty() || !dateTo.isEmpty()){
			DataConvertion dc = DataConvertionUtil.convertDateFromTo(dateFrom, dateTo);
			if(dc.hasError()){
				throw new U1Exception(dc.getErrorMessage());
			}
		}
		Util.handleCustomerInfo(inputuser, userlogin, true);
		//check user
		return statisticsService.getSlaStasData(inputuser.getCustomer()==null?null:inputuser.getCustomer().getCustomerId(), inputuser==null?null:inputuser.getUserId(), dateFrom, dateTo);
	}
	
	@Resource
	protected SlaService slaService;
}
