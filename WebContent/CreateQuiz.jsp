<%@page import="DBQuizControllers.DBQuizController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<%
	if (!(boolean) request.getSession().getAttribute("logged_in")) {
		response.sendRedirect("NotLoggedIn.jsp");
		return;
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Your Quiz</title>
<link rel="stylesheet" type="text/css" href="Button.css">
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
</head>
<style>
h2 {
	margin: 10px;
}

input[type=checkbox] {
	margin: 10px;
}

input[type=radio] {
	margin: 10px;
}

input[type=number] {
	margin: 10px;
}

textarea {
	resize: none;
}
</style>
<body>
	<div id="main" class="main">
		<jsp:include page="Header.jsp" />
		<h1>Create Quiz</h1>
		<h2>Choose Quiz category</h2>
		<%
			DBQuizController c = new DBQuizController();
			ArrayList<String> difficulties = c.getQuizDifficulties();
			ArrayList<String> categories = c.getQuizCategories();
		%>

		<form action="CreateQuiz" method="post">
			<select name="categories">
				<%
					for (int i = 0; i < categories.size(); i++) {
						out.write("<option value=\"" + categories.get(i) + "\">" + categories.get(i) + "</option>");
					}
				%>
			</select> <select name="difficulty">
				<%
					for (String a : difficulties) {
						out.write("<option value=\"" + a + "\">" + a + "</option>");
					}
				%>
			</select>
			<h2>Enter quiz name</h2>
			<br /> <input type="text" class="inputs" name="quiz_name" maxlength="25"
				placeholder="Enter quiz name here..." required> <br />
			<h2>Enter quiz description</h2>
			<br />
			<textarea name="quiz_description" class="inputs" rows="3" cols="45" maxlength="150"
				placeholder="Enter quiz description here..." required></textarea>
			<p>
				<input name="Random Questions" type="checkbox">Random
				Questions
			</p>
			<p>
				<input name="Immediate Correction" type="checkbox">Immediate
				Correction
			</p>
			<h4>Show the quiz on:</h4>
			<p>
				<input type="radio" name="Show on" value="One Page" checked>One
				Page
			</p>
			<p>
				<input type="radio" name="Show on" value="Multiple Pages">Multiple
				Pages
			</p>
			<p>
				<input type="number" min="1" max="1000" name="time_limit"
					placeholder="NO" style="width: 40px;"> Time Limit (minutes)
			</p>
			<input type="submit" value="Create" class="btn">

		</form>
	</div>
</body>
</html>