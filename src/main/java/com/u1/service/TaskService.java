package com.u1.service;

import javax.annotation.Resource;

import com.u1.model.TaskForUpdate;
import com.u1.model.TaskLog;
import com.u1.model.TaskWithAsset;
import com.u1.model.UserForAuthOnly;

public interface TaskService extends CommonService {
	public void addNewTask(TaskWithAsset t, UserForAuthOnly user);
	public String updateTask(TaskForUpdate tfd, TaskLog tl, UserForAuthOnly user);
}
