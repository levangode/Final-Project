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

ul {
	width: 100%;
	padding-left: 0px;
	text-align: center;
}

li {
	list-style-type: none;
}

#card {
	border-radius: 25px;
	border: 2px solid #000000;
	padding: 10px;
	width: calc(100% - 30px);
	height: 150px;
	margin: 5px 3px;
}

h2 {
	margin: 1px;
}

p {
	font-size: 18;
	margin: 0;
}

.descr {
	font-size: 16;
	line-height: 1em;
	height: 2em;
	text-overflow: ellipsis;
	width: 100%;
	overflow: hidden;
}

h3 {
	margin-bottom: 1px;
}

.para {
	font-size: 14;
	margin: 0;
	padding: 7px;
	position: absolute;
}

li {
	margin: 2px 0px;
}

.headers {
	padding: 10px;
}
</style>
</head>
<body>
	<div style="min-width: 1024px; position: relative;">
		<!-- start top panel -->
		<jsp:include page="Header.jsp" />
		<div style="text-align: center; height: 50px;">
			<a class="btn" href="CreateQuiz.jsp"> Create New Quiz </a>
		</div>
		<!-- End Top Panel -->
		<!-- Bottom Panel -->
		<div style="float: left">
			<form action='SearchUser' method='post'>
				Search Quiz<input type='text' name='user_name'> <input
					type='submit' value='Search'>
			</form>
		</div>
		<div>
			<!-- Left Panel -->
			<div class="box" style="text-align: center;">
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
				style="text-align: center; width: calc(100% - 360px);">
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
				<div class="box" style="position: relative;">
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
				<div class="box" style="position: relative;">
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

				<div class=box style="position: relative;">
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
