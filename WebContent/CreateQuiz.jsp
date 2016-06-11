<%@page import="database.DBQuizController"%>
<%@page import="database.DBconnector"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Your Quiz</title>
<link rel="stylesheet" type="text/css" href="Button.css">
</head>
<style>
body {
	background: #16A085;
	background: -webkit-linear-gradient(to left, #16A085, #F4D03F);
	background: linear-gradient(to left, #16A085, #F4D03F);
	text-align: center;
}

h2 {
	margin: 10px;
}

input[name=add] {
	margin: 10px;
}

textarea {
	resize: none;
}
</style>
<body>
	<% 
	if(!(boolean)request.getSession().getAttribute("logged_in")){
		response.sendRedirect("NotLoggedIn.jsp");			
	}
	%>
	<h1>Create Quiz</h1>
	<h2>Choose Quiz category</h2>
	<%
		DBQuizController c = new DBQuizController();
		ArrayList<String> categories = c.getQuizCategories();
	%>

	<form action="CreateQuiz" method="post">
		<select name="categories">
			<%
				for (int i = 0; i < categories.size(); i++) {
					out.write("<option value=\"" + categories.get(i) + "\">" + categories.get(i) + "</option>");
				}
			%>
		</select>
		<select name="difficulty"> <!-- TODO from base?------------------------------------------- -->
			<option value="Easy">Easy</option>
			<option value="Medium">Medium</option>
			<option value="Hard">Hard</option>
		</select>
		<h2>Enter quiz name</h2>
		<br /> <input type="text" name="quiz_name"
			placeholder="Enter quiz name here..."> <br />
		<h2>Enter quiz description</h2>
		<br />
		<textarea name="quiz_description" rows="3" cols="45" maxlength="1000" placeholder="Enter quiz description here..."></textarea>
		<br /><input type="submit" value="Create" class="btn">
	</form>

</body>
</html>