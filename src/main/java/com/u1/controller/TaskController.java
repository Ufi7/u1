package com.u1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

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
import javax.validation.groups.Default;
import com.u1.exception.U1Exception;
import com.u1.model.AssetGroup;
import com.u1.model.AssetGroupWithSimpleAsset;
import com.u1.model.Attachment;
import com.u1.model.Component;
import com.u1.model.Componentrecord;
import com.u1.model.Componentstore;
import com.u1.model.Condition;
import com.u1.model.Customer;
import com.u1.model.DataConvertion;
import com.u1.model.IdForm;
import com.u1.model.RefTask;
import com.u1.model.RefUser;
import com.u1.model.SearchResult;
import com.u1.model.SimpleAsset;
import com.u1.model.SimpleUsers;
import com.u1.model.Task;
import com.u1.model.TaskForUpdate;
import com.u1.model.TaskForUpdate.AssignedTo;
import com.u1.model.TaskForUpdate.Done;
import com.u1.model.TaskForUpdate.ReValue;
import com.u1.model.TaskLog;
import com.u1.model.Log.AddLog;
import com.u1.model.TaskType;
import com.u1.model.TaskWithAsset;
import com.u1.model.UserForAuthOnly;
import com.u1.service.ComponentService;
import com.u1.service.TaskService;
import com.u1.util.Constants;
import com.u1.util.DataConvertionUtil;
import com.u1.util.MessageSourceHelper;
import com.u1.util.Util;

@Controller
@SessionAttributes("searchTask")
public class TaskController extends BaseController {
	@RequestMapping(value="/task")
	public String getLatestTask(ModelMap modelmap){
//		List<Condition> cs = initEnabledCondition();
//		DataConvertion<Date> initDate = DataConvertionUtil.getDateRange("today", 3);
//		Condition c = new Condition("createdDatetime",initDate.getResult().get(0), initDate.getResult().get(1));
//		cs.add(c);
//		modelmap.put("searchTask", cs);
		modelmap.put("tasksearch", new Task());
		return "task";
	}
	
	@RequestMapping(value="/tasklist/{page}")
	public String listTask(ModelMap modelmap, @PathVariable int page, @ModelAttribute("searchTask") List<Condition> conditions){
		return super.listpage(modelmap, page, Task.class, conditions, "tasklist");
	}
	
	@RequestMapping(value="/tasksearch", method=RequestMethod.POST)
	public String searchTask(ModelMap modelmap, Task task, HttpServletRequest request, Authentication authentication){
		UserForAuthOnly user = (UserForAuthOnly) authentication.getPrincipal();
		List<Condition> cs = initEnabledCondition();
		Condition c;		
//		Customer targetCustomer = user.getCustomer();
//		if (!targetCustomer.equals(task.getCustomer())) {
//            if (user.getAccessRights().contains(new Integer(8))) {
//                targetCustomer = task.getCustomer();
//            }
//        }
//		if (targetCustomer!=null) {
//            c = new Condition("customer", targetCustomer);
//            cs.add(c);
//        }
		Util.handleCustomerInfo(task, user, true);
        if (task.getCustomer()!=null) {
            c = new Condition("customer", task.getCustomer());
            cs.add(c);
        }
		if(!task.getTaskNum().isEmpty()){
			c = new Condition("taskNum", task.getTaskNum(), false);
			cs.add(c);
		}
		if(!task.getTaskName().isEmpty()){
			c = new Condition("taskName", task.getTaskName(), false);
			cs.add(c);
		}
		if(!task.getStatus().isEmpty()){
			c = new Condition("status", task.getStatus(), false);
			cs.add(c);
		}
		String assignedtoStr = request.getParameter("assignedto");
		if(!assignedtoStr.isEmpty()){
			if(assignedtoStr.equalsIgnoreCase("assigned")){
				c = new Condition("assignedTo", false, null);
				cs.add(c);
			}else if(assignedtoStr.equalsIgnoreCase("unassigned")){
				c = new Condition("assignedTo", true, null);
				cs.add(c);
			}else{
				c = new Condition("assignedTo", new RefUser(assignedtoStr));
				cs.add(c);
			}
		}
		if(task.getTaskType()!=null){
			c = new Condition("taskType", task.getTaskType());
			cs.add(c);
		}
		String createdDatePerid = request.getParameter("createdDatePerid");
		if(!createdDatePerid.isEmpty()){
			int period = 0;
			switch(createdDatePerid){
				case "3":
				{
					period = 3;
					break;
				}
				case "7":
				{
					period = 7;
					break;
				}
				case "30":
				{
					period = 30;
					break;
				}
				case "365":
				{
					period = 365;
					break;
				}
				default:
				{
					period = 0;
					break;
				}
			}
			if(period!=0){
				DataConvertion<Date> initDate = DataConvertionUtil.getDateRange("today", period);
				c = new Condition("createdDatetime",initDate.getResult().get(0), initDate.getResult().get(1));
				cs.add(c);
			}
		}
		modelmap.put("searchTask", cs);
		SearchResult sr = commonService.search(Task.class, cs, null);
		Util.buildSearchResultList(modelmap, sr);
		return "tasklist";
	}
	
	@RequestMapping(value="/deletetask", method=RequestMethod.POST)
	public @ResponseBody String deleteTask(IdForm form){
		commonService.disable(Task.class, form.getId());
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	@RequestMapping("/taskdetail/{id}")
	public String detail(ModelMap modelmap,  @PathVariable Integer id){
		//TODO add revaluable flag
		TaskWithAsset task = (TaskWithAsset)commonService.get(TaskWithAsset.class, id);
		if(task==null){
			//TODO record not found handling...
		}
		Calendar c = Calendar.getInstance();
		if(task.getStatus().equals(Constants.TASK_STATUS_DONE)&& task.getEndDatetime()!=null){
			if(c.getTimeInMillis()-task.getEndDatetime().getTime()<Constants.MAX_REVALUALBE_TASK_PERIOD){
				modelmap.put("revaluable", true);
			}
		}
		modelmap.put("taskDetail", task);
		return "taskdetail";
	}
	
	
	
	@RequestMapping(value="/taskupdate/{id}", method=RequestMethod.POST)
	public @ResponseBody String update(HttpServletRequest request, @PathVariable Integer id, Authentication authentication, TaskForUpdate t, TaskLog tl){
		//common fields validation
		if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_COMPONENTSELECT)){
			throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));
		}//s only for component select, will from another url but not this update
		
		Class c1class=Default.class;
		if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_ADD_LOG)){
			c1class=AddLog.class;
		}
		Set<ConstraintViolation<TaskLog>> c1=validator.validate(tl, c1class);
		if(c1.size()!=0){
			return super.buildErrorMessage(c1);
		}
		Class c2class = Default.class;
		if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_REVALUE)){
			c2class = ReValue.class;
		}else if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_DONE)){
			c2class = Done.class;
		}else if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_ASSIGN)){
			c2class = AssignedTo.class;
		}
		Set<ConstraintViolation<TaskForUpdate>> c2=validator.validate(t, c2class);
		if(c2.size()!=0){
			return super.buildErrorMessage(c2);
		}
		
		if(tl.getActionCode().equals(Constants.TASK_ACTION_CODE_REJECT) || tl.getActionCode().equals(Constants.TASK_ACTION_CODE_ASSIGN) || tl.getActionCode().equals(Constants.TASK_ACTION_CODE_REVALUE)){
			//TODO check assign right
			HttpSession session = request.getSession();
    		boolean[] accessRights = (boolean[])session.getAttribute("accessRights");
			if(!accessRights[6]){
				return buildErrorMessage(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR")); 
			}
		}
		t.setTaskPid(id);
		return taskService.updateTask(t, tl, (UserForAuthOnly)authentication.getPrincipal());
	}
	
	@RequestMapping("/newtask")
	public String initNewTaskPage(ModelMap modelmap){
		modelmap.put("newTask", new TaskWithAsset());
		return "newtask";
	}
	
	@RequestMapping("/addtask/gettaskspend/{tasktypeid}/{level}")
	public @ResponseBody String getTaskTypeLevelSpend(@PathVariable Integer tasktypeid, @PathVariable int level){
		TaskType tt = (TaskType)commonService.get(TaskType.class, tasktypeid);
		if(tt==null){
			//TODO handle no recrod case
		}
		if(level==0){
			return Constants.HTML_PREFIX_CONTENT_SUCCESS + tt.getLowDefaultTime().toString();
		}else if(level == 2){
			return Constants.HTML_PREFIX_CONTENT_SUCCESS+tt.getHighDefaultTime().toString();
		}else{
			return Constants.HTML_PREFIX_CONTENT_SUCCESS+tt.getMediumDefaultTime().toString();
		}
	}
	
	@RequestMapping(value="/newtasksubmit", method=RequestMethod.POST)
	public @ResponseBody String submit(HttpServletRequest request, TaskWithAsset task, Authentication authentication, ModelMap modelmap) throws U1Exception{
		UserForAuthOnly user = (UserForAuthOnly)authentication.getPrincipal();
		Util.handleCustomerInfo(task, user, false);
		Set<ConstraintViolation<TaskWithAsset>> c=validator.validate(task, Default.class);
		if(c.size()!=0){
			return super.buildErrorMessage(c);
		}
//		if(result.hasErrors()){
//			return super.buildErrorMessage(result);
//		}
//		Util.checkCrossCustomerProcess(authentication, task.getCustomer());
		if(task.getAssignedTo()!=null){
			if(!task.getAssignedTo().getUserId().equals(user.getUserId())){
				//TODO check assign right
				HttpSession session = request.getSession();
	    		boolean[] accessRights = (boolean[])session.getAttribute("accessRights");
				if(!accessRights[6]){
					return Constants.HTML_PREFIX_CONTENT_ERROR+buildErrorMessage(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR")); 
				}
			}
		}
		taskService.addNewTask(task, user);
		return task.getTaskPid().toString();
	}
	
	@RequestMapping(value="/tasklog/{id}")
	public String listLog(@PathVariable Integer id, ModelMap modelmap){
		List<Condition> cs = new ArrayList();
		Condition c = new Condition("task", new RefTask(id));
		cs.add(c);
		List<TaskLog> tllist = commonService.searchByMultiCondition(TaskLog.class, cs, null, 0, 0);
		modelmap.put("tasklogList", tllist);
		return "tasklog";
	}
	
	@RequestMapping(value="/addtask")
	public String copytask(IdForm form, ModelMap modelmap){
		TaskWithAsset task;
		if(form.getId()==null){
			task = new TaskWithAsset();
		}else{
			task = (TaskWithAsset)commonService.get(TaskWithAsset.class, form.getId());
			task.setAssignedTo(null);//force to clear assignedTo
			task.setTaskName(null);//force to clear task name;
		}
		modelmap.put("newTask", task);
		return "addtask";
	}
	
	@RequestMapping("/taskattachment/{id}")
	public String getAssetAttchmentList(@PathVariable Integer id, ModelMap modelmap){
		List<Condition> cs = super.initEnabledCondition();
		Condition c = new Condition("taskId", id);
		cs.add(c);
		List<Attachment> atml = commonService.searchByMultiCondition(Attachment.class, cs, null, 0, 0);
		modelmap.put("atmlist", atml);
		modelmap.put("taskid", id);
		return "taskattachmentlist";
	}
	
	@RequestMapping("/myfinishtask")
	public String listMyFinishTask(ModelMap modelmap,Authentication authentication){
		UserForAuthOnly user = (UserForAuthOnly)authentication.getPrincipal();
		Task task = new Task();
		task.setAssignedTo(new RefUser(user.getUserId()));
		task.setStatus(Constants.TASK_STATUS_DONE);
		modelmap.put("tasksearch", task);
		return "task";
	}
	
	@RequestMapping("/raisetaskforasset")
	public String raiseTaskForAsset(IdForm form, ModelMap modelmap){
		TaskWithAsset task = new TaskWithAsset();
		if(form.getId()!=null){
			List<SimpleAsset> asl = new ArrayList();
			SimpleAsset sa = (SimpleAsset)commonService.get(SimpleAsset.class, form.getId());
			asl.add(sa);
			task.setAssetList(asl);
		}
		modelmap.put("newTask", task);
		return "addtask";
	}
	
	@RequestMapping("/taskselectcomponent/{taskid}")
	public String initComponentSelect(@PathVariable Integer taskid, ModelMap modelmap, HttpServletRequest request){
		RefTask task = (RefTask)commonService.get(RefTask.class, taskid);
		if(!task.getStatus().equals(Constants.TASK_STATUS_IN_PROGRESS)){
			return buildErrorMessageAndRetuanSystemErrorPage(Constants.ERROR_TASK_STATUS, new Object[0], request);
		}
		modelmap.put("task", task);
		modelmap.put("componentSelect", new Component());
		return "componentselect";
	}
	
	@RequestMapping(value="/taskcomponentupdate/{taskid}", method=RequestMethod.POST)
	public @ResponseBody String logSelectedComponentForTask(@PathVariable Integer taskid, HttpServletRequest request, TaskLog tasklog, Authentication authentication){
		UserForAuthOnly user = (UserForAuthOnly)authentication.getPrincipal();
		tasklog.setActionCode(Constants.TASK_ACTION_CODE_COMPONENTSELECT);
		tasklog.setTask(new RefTask(taskid));
		tasklog.setOwner(new RefUser(user.getUserId()));
		Set<ConstraintViolation<TaskLog>> c1=validator.validate(tasklog, Default.class);
		if(c1.size()!=0){
			return super.buildErrorMessage(c1);
		}
		String[] componentStoreIds = request.getParameterValues("componentStoreId");
		String[] quantitys = request.getParameterValues("quantity");
		if(componentStoreIds.length!=quantitys.length){
			throw new U1Exception(MessageSourceHelper.getMessage("ACCESS_RIGHT_ERROR"));
		}
		List<Componentrecord> recordList = new ArrayList();
		Componentrecord record=null;
		for(int i=0;i<componentStoreIds.length;i++){
			if(quantitys[i].trim()!=""){
				try{
					Integer id = Integer.valueOf(componentStoreIds[i]);
					Integer quantity = Integer.valueOf(quantitys[i]);
					if(quantity>0){//only add to list while quantity is valid
						record = new Componentrecord();
						record.setComponentstore(new Componentstore(id));
						record.setQuantity(quantity);
						record.setReason(3);
						record.setRemark(tasklog.getRemark());
						record.setUser(new RefUser(user.getUserId()));
						recordList.add(record);
					}else if(quantity<0){
						throw new U1Exception(MessageSourceHelper.getMessage(Constants.ERROR_COMPONENT_SELECT));
					}else if(quantity==0){
						//nothing to do, 0 will be ignore as empty.
					}
				}catch(NumberFormatException e){//input a non-digit
					throw new U1Exception(MessageSourceHelper.getMessage(Constants.ERROR_COMPONENT_SELECT));
				}
			}
		}
		if(recordList.size()<1){
			throw new U1Exception(MessageSourceHelper.getMessage(Constants.ERROR_COMPONENT_SELECT));
		}
		componentService.consumeComponent(tasklog, recordList);
		return Constants.HTML_PREFIX_CONTENT_SUCCESS;
	}
	
	@RequestMapping("/taskconsumedcomponent/{taskid}")
	public @ResponseBody List<Object> getConsumedComponentOfTask(@PathVariable Integer taskid){
		return componentService.getConsumedComponentOfTask(taskid);
	}
	
	@Resource
	protected TaskService taskService;
	@Resource
	protected ComponentService componentService;
}
