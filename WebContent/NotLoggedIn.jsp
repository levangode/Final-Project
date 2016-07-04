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
	<div id="main" class="main">
	<jsp:include page="Header.jsp"></jsp:include>
	<h3>You need to be logged in to view this page</h3>
	<form action="Login" method="post">
		User Name: <input type="text" class="inputs" name="user_login">
		<br />
		Password: <input type="password" class="inputs" name="user_password">
		<br />
		<input type = "submit" value="Login">
		<br />
		<a href = "Register.html">Create New Account</a>
		
	</form>
	</div>
</body>
</html>