<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.DBQuizController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>


body{
	

background: #16A085; /* fallback for old browsers */
background: -webkit-linear-gradient(to left, #16A085 , #F4D03F); /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to left, #16A085 , #F4D03F); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
        
	background-color: #ffcc66;
}
.headers ul {
	text-align: center;
	padding: auto;
	margin: auto;
	font-family: helvetica;
}

.headers a {
	text-decoration: none;
	color: white;
	font-size: 20px;
	background: grey;
	margin-top:30px;
	border-right: 1px solid;
}

.headers li {
	display: inline;
}

.headers a:hover {
	background: #364444;
}

div.box {
	float: left;
	width: 180px;
	background: #D5FFE2;
}

.box ul {
	float: left
}

.box a {
	text-decoration: none;
	color: black;
	font-size: 20px;
}

.box a:hover {
	background: #9EC4AA;
}
.headers{
	padding: 10px;
}



</style>
</head>
<body>

	<span id="homelogo" style="align:left">
		WELCOMEEE
	</span>
	<div id="top" align="right" style="float:right">
		<form action="Login" method="post">
			User Name: <input type="text" name="user_login"> Password: <input
				type="text" name="user_password"> <input type="submit"
				value="Login">
			<button type="button" onclick="location.href = 'Register.html'">Register</button>
		</form>
	</div>

	<div class="headers">
		<ul>
			<li><a href="https://www.google.com"> Create New Quiz </a></li>
			<li><a href="https://www.google.com"> Top Quizzes </a></li>
			<li><a href="https://www.google.com"> My Quizzes </a></li>
			<li><a href="https://www.google.com"> New Quizzes </a></li>
		</ul>
	</div>

	<br>

	<div class="box">
		<span style="margin: 30px; font-family: helvetica; font-size: 24px">
			Categories</span>
		<ul>
			<li><a href="https://www.google.com"> Funny </a></li>
			<li><a href="https://www.google.com"> Science </a></li>
			<li><a href="https://www.google.com"> Personality </a></li>
			<li><a href="https://www.google.com"> Shit </a></li>
		</ul>
	</div>

	<div class="box" style="float: right">
		<span style="margin: 30px; font-family: helvetica; font-size: 24px">
			Hot Quizes</span>
		<ul>
			<li><a href="https://www.google.com"> Quiz1</a></li>
			<li><a href="https://www.google.com"> Quiz2</a></li>
			<li><a href="https://www.google.com"> Quiz3</a></li>
			<li><a href="https://www.google.com"> Quiz4</a></li>
		</ul>
	</div>


</body>
</html>