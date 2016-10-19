package com.u1.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.u1.dao.hibernateimpl.CommonDaoImpl;
import com.u1.exception.U1Exception;
import com.u1.model.AssetType;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.DataConvertion;
import com.u1.model.Department;
import com.u1.model.Resources;
import com.u1.model.SearchResult;
import com.u1.model.SelectOptionModel;
import com.u1.model.UserForAuthOnly;
import com.u1.service.CommonService;
import com.u1.service.ComponentService;
import com.u1.service.StatisticsService;
import com.u1.util.Constants;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;

public class BaseController {
	private final static Logger logger = Logger.getLogger(BaseController.class);
	
	//init list(optional) and new formbean(optional)
	public<M extends java.io.Serializable> String listAndInit(ModelMap modelmap, Class<M> entityClass, String intipage, boolean needList, boolean needFormbean, Object newFormbean, String formbeanKey){
		if(needList){
			SearchResult sr = commonService.search(entityClass, null, null);
			Util.buildSearchResultList(modelmap, sr);
		}
		if(needFormbean){
			modelmap.put(formbeanKey, newFormbean);
		}
		return intipage;
	}
	
	//init list(optional) with enabled condition and new formbean(optional)
	public<M extends java.io.Serializable> String listAndInitAndUpdateSessionSearchCS(ModelMap modelmap, Class<M> entityClass, String intipage, boolean needList, boolean needFormbean, Object newFormbean, String formbeanKey, String sessionkey, boolean listenabled){
		if(needList){
			List conditions = null;
			if(listenabled){
				conditions = initEnabledCondition();
			}else{
				conditions = new ArrayList();
			}
			if(sessionkey!=null){
				modelmap.put(sessionkey, conditions);
			}
			SearchResult sr = commonService.search(entityClass, conditions, null);
			Util.buildSearchResultList(modelmap, sr);
		}
		if(needFormbean){
			modelmap.put(formbeanKey, newFormbean);
		}
		return intipage;
	}
	
	//add new enity, return HTML page with message inside
	public<M extends java.io.Serializable> String addNew(ModelMap modelmap, M model,BindingResult result, String messageKey, String messageParameter){
		if(result.hasErrors()){
			return "validationerror";
		}
		commonService.save(model);
		modelmap.put("message_key", messageKey);
		modelmap.put("key_word", messageParameter);
		return "success";
	}
	
	//add new entity and return a updated list
	public<M extends java.io.Serializable> String addNewAndReturnUpdatedListPage(ModelMap modelmap, M model,BindingResult result, String returnpage){
		if(result.hasErrors()){
			return "validationerror";
		}
		SearchResult sr = commonService.addNewAndReturnUpdatedList(model, null, null);
		Util.buildSearchResultList(modelmap, sr);
		return returnpage;
	}
	
	//add new entity and resutn JSON with new id
	public<M extends java.io.Serializable> String addNewAndReturnNewId(M model, BindingResult result){
		if(result.hasErrors()){
			return buildErrorMessage(result);
		}
		return commonService.save(model).toString();
		
	}
	
	//list page
	public<M extends java.io.Serializable> String listpage(ModelMap modelmap, int page, Class<M> entityClass, String returnpage){
		return listpage(modelmap, page, entityClass, null, returnpage);
	}
	
	public<M extends java.io.Serializable> String listpage(ModelMap modelmap, int page, Class<M> entityClass, String returnpage, boolean listenabled){
		List<Condition> cs=null;
		if(listenabled){
			cs=initEnabledCondition();
		}
		return listpage(modelmap, page, entityClass, cs, returnpage);
	}
	
	//list page
	public<M extends java.io.Serializable> String listpage(ModelMap modelmap, int page, Class<M> entityClass, List<Condition> cs, String returnpage){
		//check parameter
		if(page<1){
			page=1;//set to default if invalid
		}
				
		//process search with session conditions
		SearchResult sr = commonService.search(entityClass, cs, null, page-1);
		Util.buildSearchResultList(modelmap, sr);
		return returnpage;
	}
	
	//detail entity
	public<M extends java.io.Serializable, ID extends java.io.Serializable> String detailEnquiry(ModelMap modelmap, Class<M> entityClass, ID id, String entityKey, String returnpage){
		M entiry=(M)commonService.get(entityClass, id);
		
		//handle if selected role missing
		if(entiry==null){
			//TODO handle error while role missing
		}
		
		//set result to request scope
		modelmap.put(entityKey, entiry);
		
		return returnpage;
	}
	
	//update entity, return HTML page with message inside
	public<M extends java.io.Serializable, ID extends java.io.Serializable> String update(ModelMap modelmap, M model, ID id, BindingResult result, String messageKey, String messageParameter){
		if(result.hasErrors()){
			return "validationerror";
		}
						
		commonService.myMerge(model, id);
		
		//update success message
		modelmap.put("message_key", messageKey);
		modelmap.put("key_word", messageParameter);
		return "success";
	}
	
	//update entity and return JSON <!--success-->
	public<M extends java.io.Serializable, ID extends java.io.Serializable> String updateWithoutMessage(M model, ID id, BindingResult result){
		if(result.hasErrors()){
			return buildErrorMessage(result);
		}
		commonService.myMerge(model, id);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	//update entity and return JSON <!--success-->
	public<M extends java.io.Serializable, ID extends java.io.Serializable> String updateWithoutMessage(M model, ID id, int updateChildrenEntityDepth, BindingResult result){
		if(result.hasErrors()){
			return buildErrorMessage(result);
		}
		commonService.myMerge(model, id, updateChildrenEntityDepth);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	//delete entity, return HTML page with message inside
	public<M extends java.io.Serializable> String delete(ModelMap modelmap, M model, String messageKey){
		commonService.delete(model);
		modelmap.put("message_key", messageKey);
		return "success";
	}
	
	//delete entity and resutn json <!--success-->
	public<M extends java.io.Serializable> String deleteWithoutMessage(M model){
		commonService.delete(model);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	//update entity enabled status, return HTML page with message inside
	public<M extends java.io.Serializable, ID extends java.io.Serializable> String updateStatus(ModelMap modelmap, Class<M> entityClass, ID id, boolean currentStatus, String messageKey){
		if(currentStatus){
			commonService.disable(entityClass, id);
		}else{
			commonService.enable(entityClass, id);
		}
		
		//update success message
		modelmap.put("message_key", messageKey);
		return "success";
	}
	
	//update entity enabled status and return  json <!--success-->
	public<M extends java.io.Serializable, ID extends java.io.Serializable> String updateStatusWithoutMessage(Class<M> entityClass, ID id, boolean currentStatus){
		int result;
		if(currentStatus){
			result = commonService.disable(entityClass, id);
		}else{
			result = commonService.enable(entityClass, id);
		}
		return result==1?Constants.HTML_PREFIX_CONTENT_SUCCESS:Constants.HTML_PREFIX_CONTENT_ERROR;
	}
	
	//build validation error message for JSON use
	public static String buildErrorMessage(BindingResult result){
		StringBuffer error = new StringBuffer();
		error.append(Constants.HTML_PREFIX_CONTENT_ERROR);
		for(ObjectError oe:result.getAllErrors()){
			error.append(oe.getDefaultMessage());
		}
		return error.toString();
	}
	
	//build validation error message for JSON use
	public static <T> String buildErrorMessage(Set<ConstraintViolation<T>> constraintViolations){
		StringBuffer error = new StringBuffer();
		error.append(Constants.HTML_PREFIX_CONTENT_ERROR);
		for(ConstraintViolation cv:constraintViolations){
			error.append(cv.getMessage());
		}
		return error.toString();
	}
	
	public static String buildErrorMessage(String message){
		return Constants.HTML_PREFIX_CONTENT_ERROR + message;
	}
	
	public static List<Condition> initEnabledCondition(){
		return Util.initEnabledCondition();
	}
	
	//exception handler
	@ExceptionHandler(U1Exception.class)
	public @ResponseBody String handleException(U1Exception ex, HttpServletRequest request){
		ex.printStackTrace();
		Throwable t = ex;
		while(t.getCause()!=null){
			t=t.getCause();
		}
		//logger.error(t);
		Object[] error = checkError(t);
		if(error!=null){
			return Constants.HTML_PREFIX_CONTENT_ERROR + MessageSourceHelper.getMessage((String)error[0], (String[])error[1]);
		}
		return Constants.HTML_PREFIX_CONTENT_ERROR + ex.getLocalizedMessage();
	}
	
	//exception handler
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, HttpServletRequest request){
		ex.printStackTrace();
		Throwable t = ex;
		while(t.getCause()!=null){
			t=t.getCause();
		}
		//logger.error(t);
		Object[] error = checkError(t);
		if(error!=null){
			request.setAttribute("errormessagecode", error[0]);
			request.setAttribute("errormessageparamlist", error[1]);
		}
		return "systemerror";
	}
	
	public String buildErrorMessageAndRetuanSystemErrorPage(String key, Object[] params, HttpServletRequest request){
		request.setAttribute("errormessagecode", key);
		request.setAttribute("errormessageparamlist", params);
		return "systemerror";
	}
	
	private static Object[] checkError(Throwable t){
		if(t.getMessage().startsWith(Constants.ERROR_MESSAGE_DUPLICATE_ENTRY_PREFIX)){
			if(t.getMessage().indexOf(Constants.ERROR_AVOID_COMPONENTSOTRE_DUPLICATE)>-1){//special hanling AVOID_COMPONENTSOTRE_DUPLICATE
				return new Object[]{Constants.ERROR_AVOID_COMPONENTSOTRE_DUPLICATE, null};
			}
			//common handling
			String fieldvalue = t.getMessage().split("'")[1];
			return new Object[]{"SQL_ERROR_DUPLICATE_ENTRY", new String[]{fieldvalue}};
		}
		return null;
	}
	
//	public static void checkCrossCustomerProcess(Authentication authentication, Customer inputCustomer){
//		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
//		if(!user.getCustomer().equals(inputCustomer) && !user.getAccessRights().contains(new Integer(8))){
//			throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));
//		}
//	}

	@Resource
	protected CommonService commonService;
	@Resource
	protected StatisticsService statisticsService;
	@Resource
	protected ReloadableResourceBundleMessageSource messageSource;
	@Resource ComponentService componentService;
	@Resource
	protected LocalValidatorFactoryBean validator;

}
