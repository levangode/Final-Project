<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="database.*"%>
<%@page import="backend.*"%>
<%@page import="DBQuizControllers.*"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Buttons.css">
<style>
body {
	background: #16A085; /* fallback for old browsers */
	background: -webkit-linear-gradient(to left, #16A085, #F4D03F);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to left, #16A085, #F4D03F);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	background-color: #ffcc66;
}

.headers ul {
	text-align: center;
	padding: auto;
	margin: 20px;
	font-family: helvetica;
}

.headers a {
	text-decoration: none;
	color: white;
	font-size: 20px;
	margin-top: 30px;
}

.headers li {
	display: inline;
}

.headers a:hover {
	color: #364444;
}

div.box {
	float: left;
	width: 180px;
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

.headers {
	padding: 10px;
}
</style>
</head>
<body>

	<span id="homelogo" style="align: left"> WELCOMEEE </span>
	<div id="top" align="right" style="float: right">

		<form action="Login" method="post">
			<%
				if ((boolean) request.getSession().getAttribute("logged_in")) {
					out.write("You are logged in as: ");
					out.write((String) request.getSession().getAttribute("user_name") + "   ");
					out.write("<a href=\"Logout.jsp\">Logout</a>");
				} else {
					out.write("User Name: <input type=\"text\" name=\"user_login\">");
					out.write("Password: <input type=\"text\" name=\"user_password\">");
					out.write("<input type=\"submit\" value=\"Login\">");
					out.write("<button type=\"button\" onclick=\"location.href = 'Register.html'\">Register</button>");
				}
			%>
		</form>
	</div>

	<div class="headers">
		<ul>
			<li><a href="CreateQuiz.jsp"> Create New Quiz </a></li>
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
			<%
				DBQuizController q = new DBQuizController();
				ArrayList<String> quizCategories = q.getQuizCategories();
				for (String a : quizCategories) {
					out.print("<li><a href=\"QuizPage.jsp\">" + a + "</a></li>");
				}
			%>
		</ul>
	</div>

	<div class="box" style="float: right">
		<div class="box" style="position: relative;">
			<h4 style="text-align: center; margin: 1px;">Hot Quizzes</h4>
			<ul>
				<%
					QuizInfoController getter = new QuizInfoController();
					ArrayList<QuizInfo> popular = getter.getPopularQuizzes();
					for (QuizInfo a : popular) {
						out.print("<li><a href=\"QuizPage.jsp\">" + a.getQuiz_name() + "</a></li>");
					}
				%>
			</ul>
		</div>
		<div style="">
			<h4 style="text-align: center; margin: 1px;">My Quizzes</h4>
			<ul>
				<%
					QuizInfoController getter2 = new QuizInfoController();
					ArrayList<QuizInfo> mine = getter2.getMyQuizzes((String) request.getSession().getAttribute("user_name"));
					System.out.println((String) request.getSession().getAttribute("user_name"));
					for (QuizInfo a : mine) {
						out.print("<li><a href=\"QuizPage.jsp\">" + a.getQuiz_name() + "</a></li>");
						//TODO shignidan ro dabechdos tavisi tavi
					}
				%>
			</ul>
		</div>
	</div>


</body>
</html>
