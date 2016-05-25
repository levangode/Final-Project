<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
</head>
<body>
	<h1>
		The Name <%=request.getAttribute("user_login")%> is Already In Use
	</h1>
	Please enter another name and password.
	<br />
	<br />
	<form action="Registration" method="post">
		User Name: <input type="text" name="user_login"> <br />
		Password: <input type="text" name="user_password"> <input
			type="submit" value="Sign up">
	</form>
</body>
</html>