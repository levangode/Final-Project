<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="backend.*"%>
<%@page import="database.*"%>
getUserByID
<%@page import="DBQuizControllers.*"%>
<%@page import="quizInfoes.*"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="StyleForUserQuizes.css">
<link rel="stylesheet" type="text/css" href="Button.css">

<style>
div.center {
	text-align: center;
	padding: 20px;
}

div.left {
	text-align: left;
	padding: 20px;
}
</style>
<%
	User user = (User) request.getSession().getAttribute("User");
%>





<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="center">
		You have Created:
		<%
		out.print(user.getCreatedQuizCount());
	%>
		Quizes
	</div>

	<div class="center">
		<ul>
			<%
				QuizInfoController db = new QuizInfoController();
				ArrayList<ShortInfo> quizes = db.getMyQuizesShortInfo(user.getLogin());
				for (int i = 0; i < quizes.size(); i++) {
					ShortInfo curQuiz = quizes.get(i);
					int quiz_id = curQuiz.getQuiz_id();
					String quiz_name = curQuiz.getQuiz_name();
					String quiz_description = curQuiz.getQuiz_description();
					int quiz_likes = curQuiz.getQuiz_likes();
					Timestamp date = curQuiz.getDate();
					out.print("<li><h1><a href='QuizSummaryPage.jsp?id=" + quiz_id + "'>" + quiz_name + "</a></h1> " + "<p>"
							+ quiz_description + "</p>" + "<p style='float:left'> Total Likes:" + quiz_likes + "</p>"
							+ "<p style='float:right'>Date Created:" + date + "</p></li>");
				}
			%>
		</ul>
	</div>




	<div class="center">
		<a class="btn" href="HomePage.jsp"> Return To Homepage </a>
	</div>
	<div class="center">
		<a class="btn" href="UserPage.jsp"> Return To User Page </a>
	</div>


</body>
</html>