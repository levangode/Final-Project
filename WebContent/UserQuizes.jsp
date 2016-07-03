<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@page import="backend.*"%>

<%@page import="DBQuizControllers.*"%>
<%@page import="quizInfoes.*"%>
<%@page import="database.*"%>
<%@page import="java.util.ArrayList"%>
<html>
	<%
		if (!(boolean) request.getSession().getAttribute("logged_in")) {
			response.sendRedirect("NotLoggedIn.jsp");
			return;
		}
	%>
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
	int id = Integer.parseInt(request.getParameter("id"));
	UserController udb = new UserController();
	User user = udb.getUserByID(id);
%>





<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="main">
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
			<a class="btn" href="UserPage.jsp?id=<%out.print(id);%>"> Return
				To User Page </a>
		</div>

	</div>
</body>
</html>