<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Account</title>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Buttons.css">
</head>
<body>
	<div id="main" class="main">
	<jsp:include page="Header.jsp"></jsp:include>
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
	</div>
</body>
</html>