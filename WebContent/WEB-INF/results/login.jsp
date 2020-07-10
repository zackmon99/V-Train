<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	
		<head>
			<title>V-Train Login</title>
			<h1>Test</h1>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css" media="Center" />	
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mobile.css" type="text/css" media="handheld" />
	
						
		</head>

	<body>
	
	 <img src="logo.png"  alt="V-Train" />
       </body> 
	 <body>
	<br />
	<div class="center">
	<div class="login">
	
	<s:form action="login" namespace="/" method="post" name="loginForm" >
		<s:textfield name="username" size="15" maxlength="50" required="true" key="username"></s:textfield>
		<br />
		<s:password name="password" size="15" maxlength="50" required="true" key="password"></s:password>
		
		<s:submit key="login" align="left" />
		
	</s:form>
	
	<s:form action="registration" namespace="/" method="post" name="registrationForm" >
	
		<s:submit key="registration" align="center" />
	</s:form>
	
	</div>
	</div>
</body>
</html>