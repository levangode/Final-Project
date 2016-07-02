<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="backend.*"%>
<%@page import="questions.*"%>
<%@page import="questions.PrintableQuestion"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	if (!(boolean) request.getSession().getAttribute("logged_in")) {
		response.sendRedirect("NotLoggedIn.jsp");
		return;
	}
%>
<%
	System.out.print("gio");
	Timestamp startTime = new Timestamp(new Date().getTime());
	DBQuizController cont = new DBQuizController();
	int id = Integer.parseInt(request.getParameter("id"));
	Quiz quiz = cont.getQuiz(id);
	request.getSession().setAttribute("Quiz", quiz);
	request.getSession().setAttribute("Starttime", startTime);
%>

<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<style>
body {
	text-align: left
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%
		quiz.getQuiz_name();
	%>
</title>
</head>
<body>
	<div id="main">
		<form action="GradeQuiz" method="post" id="quiz">
			<%
				ArrayList<Question> questions = quiz.getQuestions();
				for (int i = 0; i < questions.size(); i++) {
					Question q = questions.get(i);
					out.print((i + 1) + ")");
					out.print(q.getQuestionHtml(i));
				}
			%>
			<input type="hidden" name="id"
				value=<%out.write(request.getParameter("id"));%>> <input
				type="submit" value="Submit">
		</form>
	</div>
</body>
</html>
