package com.u1.controller;

import com.u1.model.AssetType;
import com.u1.model.TaskType;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


@Controller
public class TaskTypeController extends BaseController {
    @RequestMapping("/tasktype")
    public String listAndInit(ModelMap modelmap) {
        return super.listAndInitAndUpdateSessionSearchCS(modelmap,
            TaskType.class, "tasktype", true, true, new TaskType(), "taskType",
            null, true);
    }

    @RequestMapping(value = "/tasktypesubmit", method = RequestMethod.POST)
    public @ResponseBody
    String addNew(ModelMap model, @Valid
    TaskType taskType, BindingResult result) {
        taskType.setTaskTypePid(null);
        taskType.setEnabled(true);
        taskType.setIconName(null);
//        taskType.setDefinedNum(0);

        return super.addNewAndReturnNewId(handleDefaulvalue(taskType), result);
    }

    @RequestMapping("/tasktypelist/{page}")
    public String listpage(@PathVariable
    int page, ModelMap model) {
        return super.listpage(model, page, TaskType.class, "tasktypelist", true);
    }

    @RequestMapping("/tasktypedetail/{id}")
    public String detailEnquiry(@PathVariable
    Integer id, ModelMap model) {
        return super.detailEnquiry(model, TaskType.class, id, "tasktypeDetail",
            "tasktypedetail");
    }

    @RequestMapping(value = "/tasktypedetailupdate/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String update(@PathVariable
    Integer id, @Valid
    TaskType taskType, BindingResult result) {
        taskType.setEnabled(true);
        taskType.setIconName(null);
        taskType.setTaskTypePid(id);
//        taskType.setDefinedNum(null);

        if (taskType.getWeight() == null) {
            taskType.setWeight(0);
        }

        if (taskType.getHighDefaultTime() == null) {
            taskType.setHighDefaultTime(0);
        }

        if (taskType.getMediumDefaultTime() == null) {
            taskType.setMediumDefaultTime(0);
        }

        if (taskType.getLowDefaultTime() == null) {
            taskType.setLowDefaultTime(0);
        }

        return super.updateWithoutMessage(taskType, id, result);
    }

    @RequestMapping(value = "/tasktypedelete/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable
    Integer id) {
        return super.updateStatusWithoutMessage(TaskType.class, id, true);
    }

    public static TaskType handleDefaulvalue(TaskType taskType) {
        if (taskType.getWeight() == null) {
            taskType.setWeight(0);
        }

        if (taskType.getHighDefaultTime() == null) {
            taskType.setHighDefaultTime(0);
        }

        if (taskType.getMediumDefaultTime() == null) {
            taskType.setMediumDefaultTime(0);
        }

        if (taskType.getLowDefaultTime() == null) {
            taskType.setLowDefaultTime(0);
        }

        return taskType;
    }
}
