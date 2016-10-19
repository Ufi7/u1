package com.u1.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecCodeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;    
        HttpServletResponse response = (HttpServletResponse) arg1;  
        
        //clear last previous error
        request.getSession(true).setAttribute("SECCODE_ERROR", false);
        
        String inputseccode = request.getParameter("seccode");  
        String seccode = (String)request.getSession(true).getAttribute("seccode");  
        if(inputseccode.equals(seccode)){  
        	//continue handle request
            arg2.doFilter(request, response);   
        }else{
        	//set error and redirect
        	request.getSession(true).setAttribute("SECCODE_ERROR", true);
            response.sendRedirect("login");  
        }  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
