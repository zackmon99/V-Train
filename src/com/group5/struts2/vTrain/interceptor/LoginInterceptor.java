package com.group5.struts2.vTrain.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public  class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 109350702414327990L;

	public String intercept(ActionInvocation invocation) throws Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		String loggedIn = (String) session.get("logged-in");
		
		if (loggedIn == null) {
			try {
				return "notloggedin";
				
			} catch (Exception e) {
				System.out.println("Error getting session information!");
			}
		}
		
		
		return invocation.invoke();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
}
