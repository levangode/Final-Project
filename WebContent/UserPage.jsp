<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="backend.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">
<%
	User user = (User) request.getSession().getAttribute("User");
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%
		out.print(user.getName());
	%>
</title>
</head>
<body>
	<h1>
		<%
			out.print(user.getName());
		%>
	</h1>
	<%
		String url = user.getImageURL();
		if (url == null) {
			url = "defaultPicture.png";
		}
		out.print("<img src='" + url + "' alt='Profile Picture' style='width:300px;height:220px;'>");
	%>
	<a class="btn" href="HomePage.jsp"> Return To Homepage </a>

</body>
</html>