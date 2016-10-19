package com.u1.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StringListController extends BaseController {
	
	@RequestMapping("/strlist/section")
	public @ResponseBody List<String> getExistingSectionList(){
		return statisticsService.getExistingAssetAttributesTeamplateSection();
	}
	
	@RequestMapping("/strlist/taskname")
	public @ResponseBody List<String> getTop100TaskNameList(){
		return statisticsService.getTop100TaskName();
	}
	
	@RequestMapping("/strlist/contact")
	public @ResponseBody List<String> getTop100Contact(){
		return statisticsService.getTop100Contact();
	}
	
	@RequestMapping("/strlist/taskrootcause")
	public @ResponseBody List<String> getTop100TaskRootCause(){
		return statisticsService.getTop100TaskRootCause();
	}
	
	@RequestMapping("/strlist/componentfield/{index}")
	public @ResponseBody List<String> getComponentFieldListByIndex(@PathVariable Integer index){
		return statisticsService.getComponentFieldListByIndex(index);
	}
}
