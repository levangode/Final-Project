<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="backend.*"%>
<%@page import="questions.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	//DBQuizController cont = new DBQuizController();
	//int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
	Quiz quiz = (Quiz) session.getAttribute("Quiz");
	//ArrayList<Question> questions = quiz.getQuestions();
	//String quizName = quiz.getQuiz_name();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%
		quiz.getQuiz_name();
	%>
</title>
</head>
<body>

	<%
		out.print("<form action='' method='' id='quiz'>");
		ArrayList<Question> questions = quiz.getQuestions();
		for (int i = 0; i < questions.size(); i++) {
			Question q = questions.get(i);
			out.print((i + 1) + ")");
			out.print(q.getQuestionHtml(i));
		}
		out.print("</form>");
		out.print("<button type='submit' form='quiz' value='Submit'>Submit</button>");
	%>



</body>
</html>
