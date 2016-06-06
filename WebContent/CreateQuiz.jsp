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
<link rel="stylesheet" type="text/css" href="Buttons.css">
</head>
<style>

@keyframes slideDown {
	0% {
		transform: translateY(-100%);
	}
	50%{
		transform: translateY(8%);
	}
	65%{
		transform: translateY(-4%);
	}
	80%{
		transform: translateY(4%);
	}
	95%{
		transform: translateY(-2%);
	}			
	100% {
		transform: translateY(0%);
	}		
}

@-webkit-keyframes slideDown {
	0% {
		-webkit-transform: translateY(-100%);
	}
	50%{
		-webkit-transform: translateY(8%);
	}
	65%{
		-webkit-transform: translateY(-4%);
	}
	80%{
		-webkit-transform: translateY(4%);
	}
	95%{
		-webkit-transform: translateY(-2%);
	}			
	100% {
		-webkit-transform: translateY(0%);
	}	
}
body {
	background: #16A085;
	background: -webkit-linear-gradient(to left, #16A085 , #F4D03F); 
	background: linear-gradient(to left, #16A085 , #F4D03F); 
	text-align: center;
}

h2 {
	margin: 10px;
}
input[name=add]{
	margin: 10px;
}
textarea{
	resize:disabled;
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
		<br /> <input type="text" name="Name"> <br />
		<h2>Enter quiz description</h2>
		<br />
		<textarea name="message" rows="3" cols="45" maxlength="1000">Enter quiz description here...</textarea>
	</form>
	<div id="questions"></div>
	
	<input name="add" type="button" onclick="addInput()" value="Add Question" class="slideDown" />
	<%
		DBQuizController b = new DBQuizController();
		ArrayList<String> types = b.getQuestionTypes();
	%>
	<select name="type">
		<%
			for (int i = 0; i < types.size(); i++) {
				out.write("<option value=\"" + types.get(i) + "\">" + types.get(i) + "</option>");
			}
		%>
	</select>
	<br />
	<button type="button" onclick="location.href = 'HomePage.jsp'">Save</button>
</body>
</html>