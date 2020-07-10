<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>V-Train Login</title>
<s:head />
</head>
<body>
	<span>
	<!-- LOGO -->
	</span>
	<b>Login failed, try again.</b>
	<br />
	<br />
	<s:form action="login" namespace="/" method="post" name="myForm" theme="xhtml">
		<s:textfield name="username" size="15" maxlength="50" required="true" key="username"></s:textfield>
		<br />
		<s:password name="password" size="15" maxlength="50" required="true" key="password"></s:password>
		<s:submit key="login" />
	</s:form>
</body>
</html>