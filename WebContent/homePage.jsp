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
	padding: 10px;
	background: grey;
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
</style>



</head>
<body>




	<div class="headers">
		<ul>
			<li><a href="https://www.google.com"> Create New Quiz </a></li>
			<li><a href="https://www.google.com"> Top Quizes </a></li>
			<li><a href="https://www.google.com"> My Quizes </a></li>
			<li><a href="https://www.google.com"> New Quizes </a></li>
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
			<%
				//DBQuizController control = (DBQuizController) application.getAttribute("quizDBConnection");
				//ArrayList<String> topQuizIDs = control.getTopQuizesID(10, true);
				//ArrayList<String> topQuizNames = control.getTopQuizesNames(topQuizIDs);

				//for (int i = 0; i < topQuizNames.size(); i++) {
					//out.print(
						//	"<li><a href= " + "quizPages/" + topQuizIDs.get(i) + "> " + topQuizNames.get(i) + "</a></li>");
				//}
			%>
			<li><a href="https://www.google.com"> Quiz1</a></li>
			<li><a href="https://www.google.com"> Quiz2</a></li>
			<li><a href="https://www.google.com"> Quiz3</a></li>
			<li><a href="https://www.google.com"> Quiz4</a></li>
		</ul>
	</div>


</body>
</html>