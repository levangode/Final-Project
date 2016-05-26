<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<title>Incorrect Information</title>
</head>
<body>
	<h1>Please Try Again</h1>
	<body>Either your user name or password is incorrect. 
	Please try again.</body>
	<br />
	<br />
	<form action="Login" method="post">
		User Name: <input type="text" name="user_login">
		<br />
		Password: <input type="text" name="user_password">
		<input type = "submit" value="Login">
		<br />
		<a href = "Register.html">Create New Account</a>
		
	</form>
</body>
</html>