package com.u1.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.u1.util.FileOperateUtil;

@Controller
@RequestMapping(value = "background/fileOperate")
public class FileOperateController {
	/**
	 * 到上传文件的位置
	 * 
	 * @author geloin
	 * @date 2012-3-29 下午4:01:31
	 * @return
	 */
	@RequestMapping(value = "/to_upload")
	public String toUpload() {
		return "testtoupload";
	}

	@RequestMapping(value = "/upload")
	public ModelAndView upload(HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		// 别名
		String[] alaises = ServletRequestUtils.getStringParameters(request,
				"alais");

		String[] params = new String[] { "alais" };
		Map<String, Object[]> values = new HashMap<String, Object[]>();
		values.put("alais", alaises);

		List<Map<String, Object>> result = FileOperateUtil.upload(request,
				params, values);

		map.put("result", result);

		return new ModelAndView("home", map);
	}

	@RequestMapping(value = "download/{filename}")
	public ModelAndView download(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String filename) throws Exception {

		String storeName = filename+".zip";
		String realName = "abc.zip";
		String contentType = "application/octet-stream";

		FileOperateUtil.download(request, response, storeName, contentType,
				realName);

		return null;
	}
}

