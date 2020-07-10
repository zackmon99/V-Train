<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.group5.struts2.vTrain.classes.User" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>V-Train</title>
<style type="text/css">
    <%@include file="../css/main.css" %>
   
    </style>
</head>
<body>
<%
	User user = (User)session.getAttribute("user");
%>
<%= user.getFirstName() %> <%= user.getLastName() %> (<%= user.getRole() %>)
<br />
<input type=button onClick="location.href='logout'" value='Logout'>
<br />
<s:form action="search" namespace="/" method="post" name="searchForm" theme="xhtml">
		<s:textfield name="searchQuery" size="15" maxlength="200"></s:textfield>
		<s:submit key="search"></s:submit>
</s:form>
<br />
<br />
<SPAN id="UserButtons">
<input type=button onClick="location.href='profile'" value='Profile'>
<input type=button onClick="location.href='required'" value='View Required Videos/Quizzes'>
<input type=button onClick="location.href='advSearch'" value='Advanced Search'>
<%
	//Setting page variables
	String role = user.getRole();
	System.out.println(role + "a");
	
	//Displaying buttons by role
	if (role.equals("Manager") || role.equals("Administrator")) {
%>
<input type=button onClick="location.href='upload'" value='Upload'>
<input type=button onClick="location.href='viewUploads'" value="My Uploads">
<input type=button onClick="location.href='createReport'" value="Create Report">
<%
	}
	
	if (role.equals("Administrator")) {
%>
<input type=button onClick="location.href='administrate'" value="Administrative Functions">
<%
	}
%>
</SPAN>
<SPAN id="MainContent">

</SPAN>
</body>
</html>