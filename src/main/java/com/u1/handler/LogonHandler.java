package com.u1.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.u1.model.Condition;
import com.u1.model.UserForAuthOnly;
import com.u1.service.CommonService;
import com.u1.service.UserAuthService;


public class LogonHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		//get Session
		HttpSession session = request.getSession();
		
		UserForAuthOnly user = (UserForAuthOnly)authentication.getPrincipal();
//		System.out.println("in handler : "+user.getUserId());
//		System.out.println("in handler : "+user.getUsername());
//		System.out.println("in handler : "+user.getPassword());
		
		//TODO log logon record

		//access right control and session store
		boolean[] accessRights = new boolean[10];
		if(user.getAccessRights()!=null && user.getAccessRights().size()>0){
			for(Integer i:user.getAccessRights()){
				if(i!=null){
					accessRights[i]=true;
				}
			}
		}
		session.setAttribute("accessRights", accessRights);
		session.setAttribute("self", new String[]{user.getUserId().toString(), user.getGivenName(), user.getCustomer().getCustomerId().toString(), user.getCustomer().getCustomerName()});
		
		//call super to handle default URL
		myOnAuthenticationSuccess(request, response, authentication);
	}
	
	public void myOnAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		 SavedRequest savedRequest = requestCache.getRequest(request, response);

		 if (savedRequest == null || savedRequest.getMethod().equalsIgnoreCase("POST")) {
//			 super.onAuthenticationSuccess(request, response, authentication);
			 clearAuthenticationAttributes(request);
			 response.sendRedirect("");
            return;
        }
		 
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);
            return;
        }

        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
	
	@Resource
	CommonService commonService;
	@Resource
	UserAuthService userAuthService;
}
