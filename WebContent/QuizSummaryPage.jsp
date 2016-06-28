<%@page import="DBQuizControllers.QuizInfoController"%>
<%@page import="quizInfoes.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Summary</title>
<style>
.column {
	float: left;
	font-family: sans-serif;
	margin: 0px 15px;
	overflow: hidden;
}
</style>
</head>
<body>
	<div
		style="position: relative; margin: auto; min-width: 1000; text-align: center;">
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
						out.print("You Have Never Taken This Quiz Before");
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
					ArrayList<HighScore> scor = scores.getHighScores(quiz_id);
					if (scor.isEmpty()) {
						out.print("Quiz hasn't been taken yet, be the first!");
					}
					for (HighScore next : scor) {
						next.showOnCard(out);
					}
				%>
			</div>
			<div style="width: 22%;" class="column">
				<h4>Top Performers of the day</h4>
			</div>
		</div>
		<div id="mid" style="position: relative; overflow: hidden;">
			<h4>Statistics</h4>
		</div>
		<div id="bottom" style="overflow: hidden; margin: 20px;">
			<form action="QuizPage.jsp">
				<input type="hidden" name="id"
					value=<%out.print(request.getParameter("id"));%>> <input
					type="submit" value="Take Quiz">
			</form>
		</div>
	</div>

</body>
</html>