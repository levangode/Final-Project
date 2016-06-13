<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="database.*"%>
<%@page import="backend.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	DBQuizController cont = new DBQuizController();
	int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
	Quiz quiz = cont.getQuiz(quiz_id);
	ArrayList<Question> questions = quiz.getQuestions();
	String quizName = quiz.getQuizName();
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%
		quiz.getQuizName();
	%>
</title>
</head>
<body>

	<%
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			String questionTxt = question.getQuestiontext();
			ArrayList<Answer> answers = question.getAnswers();
			out.print("<h2>" + questionTxt + "</h2>");
			for (int j = 0; j < answers.size(); j++) {
				Answer answer = answers.get(j);
				String command = "<div>" + "<input type='radio' " + "id = 'question-" + i + "-answer-" + j + "' "
						+ "value = '" + j + "' />" + "<label for='question-" + i + "-answer-" + j + "' >"
						+ answer.answerText() + "</div>";

				out.print(command);
			}
		}
	%>



</body>
</html>