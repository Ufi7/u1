package com.u1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.u1.util.Constants;

public class SecurityCheckInterceptor implements HandlerInterceptor{  
	
	private final static Object[][] GET_REQUEST_MATRIX = new Object[][]{
			{"/roles", 1}, {"/addroles", 1}, {"/groups", 1}, {"/addgroups", 1},{"/users", 1},{"/addusers", 1},
			{"/assettype", 2}, {"/tasktype", 2},{"/department", 2},{"/customer", 2},
			{"/assetattributes", 3},{"/addassetattributes", 3},
			{"/newasset", 4},{"/asset", 4},{"/assetgroup", 4},{"/component",4},
			{"/newtask", 5},{"/task", 5},
			{"/download/", new int[]{4,5}},
			{"/sla",7}, {"/slasetup", 7}
//			{"/", },{"/", }
	};
	
	private final static Object[][] POST_REQUEST_MATRIX = new Object[][]{
		{"/rolessearch", 1},{"/rolesdetailupdate/",1}, {"/addrolessubmit",1}, {"/groupssearch",1}, {"/groupsdetailupdate/",1}, {"/addgroupssubmit",1}, {"/userssearch",1}, {"/usersdetailupdate/",1}, {"/adduserssubmit",1},
		{"/assettypesubmit",2},{"/assettypedetailupdate/",2},{"/assettypedelete/",2},{"/departmentsubmit",2},{"/departmentdetailupdate/",2},{"/departmentdelete/",2},
		{"/tasktypesubmit",2},{"/tasktypedetailupdate/",2},{"/tasktypedelete/",2},{"/customersubmit", 2}, {"/customerdetailupdate/", 2}, {"/customerdelete/", 2},
		{"/assetattributesdetail/",3},{"/assetattributesstatusupdate/",3},{"/assetattributesdelete/",3},{"/addassetattributes",3},
		{"/assetsearch", 4},{"/newassetsubmit", 4},{"/updateasset/", 4},{"/assetgroupsearch", 4},{"/assetgroupupdate/", 4},{"/assetgroupdelete/", 4},{"/addnewassetgroup",4},{"/newassetsubmit", 4},{"/upload/asset/",4},
		{"/addnewcomponenttype", 4},{"/newcomponentstore", 4},{"/updatecomponentstore/",4},
		{"/tasksearch", 5},{"/newtasksubmit", 5}, {"/taskupdate/", 5}, {"/upload/task/",5},{"/taskcomponentupdate/", 5},
		{"/deletetask", 6},
		{"/slacalc", 7}, {"/slasetupsubmit", 7},
		{"/homeswitch", 8}
//		{""}
//		{"/", },{"/", }
};
	
	public boolean checkAccessRight(String uri, boolean[] accessRights, Object[][] matrix){
		for(int i=0;i<matrix.length;i++){
			if(((String)matrix[i][0]).endsWith("/")){
				if(uri.indexOf((String)matrix[i][0])>-1){
//					System.out.println("match url "+matrix[i][0]+" , check access right "+matrix[i][1]);
					if(matrix[i][1] instanceof Integer){
						return accessRights[(int)matrix[i][1]];
					}else if(matrix[i][1] instanceof int[]){
						return checkRightArray(accessRights,(int[])matrix[i][1]);
					}
				}
			}else{
				if(uri.endsWith((String)matrix[i][0])){
//					System.out.println("match url "+matrix[i][0]+" , check access right "+matrix[i][1]);
					if(matrix[i][1] instanceof Integer){
						return accessRights[(int)matrix[i][1]];
					}else if(matrix[i][1] instanceof int[]){
						return checkRightArray(accessRights,(int[])matrix[i][1]);
					}
				}
			}
		}
		return true;
	}
	
	public static boolean checkRightArray(boolean[] vs, int[] ars){
		boolean value = false;
		for(int a=0;a<ars.length;a++){
			value=vs[ars[a]];
			if(value==true)
				return value;
		}
		return value;
	}
		
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response,Object handler)throws Exception{
//    		System.out.println("handler checking................................................................................");
//    		System.out.println(request.getRequestURI());
//    		System.out.println(request.getMethod());
    		//get Session
    		HttpSession session = request.getSession();
    		boolean[] accessRights = (boolean[])session.getAttribute("accessRights");
    		Object[][] validMatrix = null;
    		if(request.getMethod().equals("POST")){
    			validMatrix = POST_REQUEST_MATRIX;
    		}else{
    			validMatrix = GET_REQUEST_MATRIX;
    		}
    		boolean result = checkAccessRight(request.getRequestURI(), accessRights, validMatrix);
    		if(!result){
    			response.getOutputStream().print(Constants.HTML_PREFIX_CONTENT_ERROR+"Access Denied");
    		}
    		return result;
        }  
        public void postHandle(HttpServletRequest request,HttpServletResponse response,  
        	Object handler,ModelAndView modelAndView)throws Exception{
//        	System.out.println("triggered postHandle().....");
        }  
        public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response,Object handler,Exception ex)throws Exception{

        }  
}
