<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	Register:<br /><br />
	<s:form action="register" namespace="/" method="post" name="registerForm" theme="xhtml">
		<s:textfield name="fname" size="15" maxlength="50" required="true" key="fname"></s:textfield>
		<s:textfield name="lname" size="15" maxlength="50" required="true" key="lname"></s:textfield>
		<s:select key="department" list="deptNames" headerKey="-1"
		headerValue="Select Department" name="department" required="true"></s:select>
		<s:select key="role" list="roleNames" headerKey="-1"
		headerValue="Select Role" name="role" required="true"></s:select>
		<s:textfield name="username" size="15" maxlength="50" required="true" key="username"></s:textfield>
		<s:password name="password" size="15" maxlength="50" required="true" key="password"></s:password>
		<s:submit key="register" />
	</s:form>
</body>
</html>