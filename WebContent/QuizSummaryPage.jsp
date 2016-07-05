<%@page import="DBQuizControllers.DBQuizController"%>
<%@page import="DBQuizControllers.QuizInfoController"%>
<%@page import="quizInfoes.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Summary</title>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<style>
p {
	font-size: 14px;
	line-height: 1.5;
	border-style: solid;
	border-width: 1px;
	border-color: #005A31;
}
</style>
</head>
<body>
	<div style="min-width: 1024px; position: relative;">
		<jsp:include page="Header.jsp" />
		<div
			style="position: relative; margin: auto; min-width: 1024; text-align: center;">
			<div id="top" style="position: relative; overflow: hidden;">
				<div style="width: 22%;" class="column">
					<h4>Quiz Info</h4>
					<%
						QuizInfoController qq = new QuizInfoController();
						int quiz_id = Integer.parseInt(request.getParameter("id"));
						QuizFullSummary summary = qq.getQuizSummary(quiz_id);
						summary.showOnCard(out);
					%>

				</div>
				<div style="width: 22%;" class="column">
					<h4>User's past performance of this quiz</h4>
					<%
						QuizInfoController activity = new QuizInfoController();
						String user_login = (String) request.getSession().getAttribute("user_name");
						ArrayList<UserActivity> act = activity.getUserActivity(user_login, quiz_id);
						if (act.isEmpty()) {
							out.print("<p>You Have Never Taken This Quiz Before</p>");
						}
						for (UserActivity next : act) {
							next.showOnCard(out);
						}
					%>
				</div>
				<div style="width: 22%;" class="column">
					<h4>Highest Performers of all time</h4>
					<%
						QuizInfoController scores = new QuizInfoController();
						ArrayList<HighScore> scor = scores.getHighScores(quiz_id, Constants.NO_HOURS_LIMIT);
						if (scor.isEmpty()) {
							out.print("<p>Quiz hasn't been taken yet, be the first!</p>");
						}
						for (HighScore next : scor) {
							next.showOnCard(out);
						}
					%>
				</div>
				<div style="width: 22%;" class="column">
					<h4>Top Performers of the day</h4>
					<%
						QuizInfoController todayScores = new QuizInfoController();
						ArrayList<HighScore> todayScor = todayScores.getHighScores(quiz_id, Constants.PAST_HOURS);
						if (todayScor.isEmpty()) {
							out.print("<p>Quiz hasn't been taken yet, be the first!</p>");
						}
						for (HighScore next : todayScor) {
							next.showOnCard(out);
						}
					%>
				</div>
			</div>
			<div id="mid" style="position: relative; overflow: hidden;">
				<h4>Statistics</h4>
				<%
					QuizInfoController stat = new QuizInfoController();
					Statistics statist = stat.getStatistics(quiz_id);
					statist.showOnCard(out);
				%>
			</div>
			<div id="bottom" style="overflow: hidden; margin: 20px;">
				<form action="QuizPage.jsp">
					<input type="hidden" name="id"
						value=<%out.print(request.getParameter("id"));%>> <input
						class="btn" type="submit" value="Take Quiz">
				</form>
				<%
					DBQuizController another = new DBQuizController();
					String button = "";
					if (another.canLike(user_login, quiz_id)) {
						button = "<input class=\"btn\" type=\"submit\" value=\"Like Quiz\">";
					} else {
						button = "<input class=\"btn\" type=\"submit\" value=\"Liked\" disabled>";
					}
					out.write("<form action=\"SubmitLike\" method=\"post\">");
					out.write("<input type=\"hidden\" name=\"like_id\" value=" + request.getParameter("id") + ">");
					out.write(button);
					out.write("</form>");
				%>
				<%
					DBQuizController g = new DBQuizController();
					int user_id = g.getAuthorId(user_login);
				%>
				<form action="UserFriendlistPage.jsp">
					<input type="hidden" name="id" value=<%out.print(user_id);%>>
					<input type="hidden" name="challenge"
						value='<%out.print(quiz_id);%>'> <input type="submit"
						class='btn' value="Challenge Friend">
				</form>
			</div>
		</div>
	</div>
</body>
</html>