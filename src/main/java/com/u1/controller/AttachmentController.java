package com.u1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.u1.model.AssetLog;
import com.u1.model.Attachment;
import com.u1.model.IdForm;
import com.u1.model.RefTask;
import com.u1.model.RefUser;
import com.u1.model.SimpleUsers;
import com.u1.model.TaskLog;
import com.u1.model.UserForAuthOnly;
import com.u1.util.Constants;
import com.u1.util.FileOperateUtil;

@Controller
public class AttachmentController extends BaseController {

	@RequestMapping(value="/upload/task/{taskid}", method=RequestMethod.POST)
	public @ResponseBody String uploadTaskAtm(Authentication authentication, @PathVariable Integer taskid,HttpServletRequest request, @Valid IdForm form, BindingResult result)throws Exception{
		if(result.hasErrors()){
			return super.buildErrorMessage(result);
		}
		
		List<Map<String, Object>> ulresult = FileOperateUtil.upload(request, null, null);
		UserForAuthOnly user = (UserForAuthOnly)authentication.getPrincipal();
		String realname = (String)ulresult.get(0).get(FileOperateUtil.REALNAME);
		String storename = (String)ulresult.get(0).get(FileOperateUtil.STORENAME);
		Long size = (Long)ulresult.get(0).get(FileOperateUtil.SIZE);
		String suffix = (String)ulresult.get(0).get(FileOperateUtil.SUFFIX);
		Attachment atm = new Attachment(taskid, null, realname, storename, form.getRemark(), suffix, size,new SimpleUsers(user.getUserId()));
		TaskLog tl = new TaskLog(new RefTask(taskid), Constants.TASK_ACTION_CODE_UPLOADFILE, null, new RefUser(user.getUserId()),  null, realname);
		Object[] updateObjects = new Object[]{atm,tl};
		commonService.saveObjects(updateObjects);
		
		return atm.getAtmId().toString();
	}
	
	@RequestMapping(value="/upload/asset/{assetid}", method=RequestMethod.POST)
	public @ResponseBody String uploadAssetAtm(Authentication authentication, @PathVariable Integer assetid,HttpServletRequest request, @Valid IdForm form, BindingResult result)throws Exception{
		if(result.hasErrors()){
			return super.buildErrorMessage(result);
		}
		
		List<Map<String, Object>> ulresult = FileOperateUtil.upload(request, null, null);
		UserForAuthOnly user = (UserForAuthOnly)authentication.getPrincipal();
		String realname = (String)ulresult.get(0).get(FileOperateUtil.REALNAME);
		String storename = (String)ulresult.get(0).get(FileOperateUtil.STORENAME);
		Long size = (Long)ulresult.get(0).get(FileOperateUtil.SIZE);
		String suffix = (String)ulresult.get(0).get(FileOperateUtil.SUFFIX);
		Attachment atm = new Attachment(null, assetid, realname, storename, form.getRemark(), suffix, size,new SimpleUsers(user.getUserId()));
		AssetLog al = new AssetLog(assetid, Constants.ASSET_ACTION_CODE_UPLOADFILE, new RefUser(user.getUserId()),  null, realname);
		Object[] updateObjects = new Object[]{atm,al};
		commonService.saveObjects(updateObjects);
		
		return atm.getAtmId().toString();
	}
	
	@RequestMapping(value = "download/{atmid}")
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Integer atmid) throws Exception {
		Attachment atm = (Attachment)commonService.get(Attachment.class, atmid);
		
		String storeName = atm.getStorefilename();
		String realName = atm.getFilename();
		String contentType = "application/octet-stream";

		FileOperateUtil.download(request, response, storeName, contentType,
				realName+".zip");

		return null;
	}
}
