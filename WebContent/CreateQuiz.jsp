<%@page import="database.DBQuizController"%>
<%@page import="database.DBconnector"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Your Quiz</title>
</head>
<style>
body {
	text-align: center;
}
h2{
	margin:10px;
}



</style>
<body>
	<h1>Create Quiz</h1>
	<h2>Choose Quiz category</h2>
	<%
		DBQuizController c = new DBQuizController();
		ArrayList<String> categories = c.getQuizCategories();
	%>
	<select name="Categories">
		<%
			for (int i = 0; i < categories.size(); i++) {
				out.write("<option value=\"" + categories.get(i) + "\">" + categories.get(i) + "</option>");
			}
		%>
	</select>

	<form action="Create" method="post">
		<h2>Enter quiz name</h2>
		<br /> <input type="text" name="Name" > <br />
		<h2>Enter quiz
		description</h2> <br />
		<textarea name="message" rows="3" cols="45" maxlength="1000">Enter quiz description here...</textarea>
	</form>
</body>
</html>