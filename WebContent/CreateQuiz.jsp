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
.slideDown{
	animation-name: slideDown;
	-webkit-animation-name: slideDown;	

	animation-duration: 1s;	
	-webkit-animation-duration: 1s;

	animation-timing-function: ease;	
	-webkit-animation-timing-function: ease;	

	visibility: visible !important;						
}
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
	text-align: center;
}

h2 {
	margin: 10px;
}
input[name=add]{
	margin: 10px;
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
	<input name="add" type="button" onclick="addInput()" value="Add Question" class="slideDown"/>
	<div id="Questions"></div>
	<script>
		var count = 1;
		var fieldName = 0;
		function addInput() {
			var fieldName = "Question " + count;
			var selectName = "category" + count;
			document.getElementById("Questions").innerHTML += '<br/><input type="text" id="'+fieldName+'" value="'+fieldName+'"class="slideDown" /><br/>';
			count += 1;
		}
	</script>
</body>
</html>