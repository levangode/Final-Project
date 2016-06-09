<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Buttons.css">
<title>Incorrect Information</title>
</head>
<body>
	<h1>Please Try Again</h1>
	<body>Either your user name or password is incorrect. 
	Please try again.</body>
	<form action="Login" method="post">
		User Name: <input type="text" name="user_login">
		<br />
		Password: <input type="text" name="user_password">
		<br />
		<input type = "submit" value="Login">
		<br />
		<a href = "Register.html">Create New Account</a>
		
	</form>
</body>
</html>