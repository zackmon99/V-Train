package com.group5.struts2.vTrain.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Logout {

	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.clear();
		return "success";
	}
}
