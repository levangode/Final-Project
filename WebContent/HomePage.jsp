<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="database.*"%>
<%@page import="backend.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="DBQuizControllers.QuizInfoController"%>
<%@page import="quizInfoes.*"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="Button.css">
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<style>


</style>
</head>
<body>
	<div id="main" class="main">
		<!-- start top panel -->
		<jsp:include page="Header.jsp" />
		<div style="text-align: center; height: 50px;">
			<a class="btn" href="CreateQuiz.jsp"> Create New Quiz </a>
		</div>
		<!-- End Top Panel -->
		<!-- Bottom Panel -->

		<div>
			<!-- Left Panel -->
			<div class="box bloki" style="text-align: center;">
				<h2>Categories</h2>
				<ul>
					<%
						DBQuizController q = new DBQuizController();
						ArrayList<String> quizCategories = q.getQuizCategories();
						out.print("<li><a href=\"HomePage.jsp\">" + "All" + "</a></li>");
						for (String a : quizCategories) {
							out.print("<li><a href=\"HomePage.jsp?category=" + a + "\">" + a + "</a></li>");
						}
					%>
				</ul>
			</div>

			<!-- End Left Panel -->
			<!-- QuizList -->
			<div class="box"
				style="text-align: center; width: calc(100% - 432px); position: relative;">
				<%
					QuizInfoController all = new QuizInfoController();
					String param = request.getParameter("category");
					List<QuizDetailedInfo> quizzes = new ArrayList<>();
					if (param == null) {
						param = request.getParameter("quiz_name");
						if (param != null) {
							quizzes = all.getQuizListByName(param);
						} else {
							quizzes = all.getQuizzes(param);
						}
					} else {
						quizzes = all.getQuizzes(param);
					}

					for (QuizDetailedInfo b : quizzes) {
						b.showOnCard(out);
					}
				%>
			</div>
			<!-- End QuizList -->
			<!-- Right Panel -->

			<div class="box" style="float: right">
				<div class="box bloki" style="text-align: center;">
					<form action='SearchQuiz' method='post'>
						Search Quiz<input type="text" maxlength=20 class="searchInput" name='quiz_name'> <input
							type='submit' value='Search'>
					</form>
				</div>
				<div class="box bloki" style="position: relative;">
					<h2 style="text-align: center; margin: 1px;">Popular Quizzes</h2>
					<ul>
						<%
							QuizInfoController getter = new QuizInfoController();
							ArrayList<QuizInfo> popular = getter.getPopularQuizzes("times_taken");
							for (QuizInfo a : popular) {
								out.print("<li><a href=\"QuizSummaryPage.jsp?id=" + a.getQuiz_id() + "\">" + a.getQuiz_name()
										+ "</a></li>");
							}
						%>
					</ul>
				</div>
				<div class="box bloki" style="position: relative;">
					<h2 style="text-align: center; margin: 1px;">Most Liked</h2>
					<ul>
						<%
							QuizInfoController liked = new QuizInfoController();
							ArrayList<QuizInfo> likes = liked.getPopularQuizzes("quiz_likes");
							for (QuizInfo a : likes) {
								out.print("<li><a href=\"QuizSummaryPage.jsp?id=" + a.getQuiz_id() + "\">" + a.getQuiz_name()
										+ "</a></li>");
							}
						%>
					</ul>
				</div>

				<div class="box bloki" style="position: relative;">
					<h2 style="text-align: center; margin: 1px;">My Quizzes</h2>
					<ul>
						<%
							QuizInfoController getter2 = new QuizInfoController();
							ArrayList<QuizInfo> mine = getter2.getMyQuizzes((String) request.getSession().getAttribute("user_name"));
							for (QuizInfo a : mine) {
								out.print("<li><a href=\"QuizSummaryPage.jsp?id=" + a.getQuiz_id() + "\">" + a.getQuiz_name()
										+ "</a></li>");
								//TODO shignidan ro dabechdos tavisi tavi
							}
						%>
					</ul>
				</div>
			</div>
			<!-- End Right Panel -->
		</div>
	</div>
</body>
</html>
