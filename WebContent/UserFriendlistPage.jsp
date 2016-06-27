<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="backend.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">
<style>
div.center {
	text-align: left;
	padding: 20px;
}
</style>


<%
	User user = (User) request.getSession().getAttribute("User");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Friends</title>
</head>
<body>

	<%
		
	%>

	<div class="center">
		<a class="btn" href="HomePage.jsp"> Return To Homepage </a>
	</div>
	<div class="center">
		<a class="btn" href="UserPage.jsp"> Return To User Page </a>
	</div>

</body>
</html>