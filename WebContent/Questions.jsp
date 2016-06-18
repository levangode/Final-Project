<%@page import="database.DBQuizController"%>
<%@page import="database.DBconnector"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
body {
	background: #16A085;
	background: -webkit-linear-gradient(to left, #16A085, #F4D03F);
	background: linear-gradient(to left, #16A085, #F4D03F);
	text-align: center;
}

h3 {
	margin: 0px;
}

input {
	margin: 5px;
}

button {
	margin: 5px;
}

textarea {
	resize: none;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script>
	var count = 1;
	function addImage() {
		var fieldName = "image" + count;
		var answerName = "answer"+count;
		var descriptionName = "description"+count;
		var tickName = "tick" + count;
		$('<input type="text" name="'+answerName+'" id="'+fieldName+'" placeholder="'+fieldName+'" style="display:none;"><br />')
				.appendTo('#answers').slideDown('fast');
		$('<textarea name="'+descriptionName+'" rows="3" cols="30" placeholder="'+descriptionName+'" style="display:none;"></textarea><br>')
				.appendTo('#answers').slideDown('fast');
		$('<input type="checkbox" name="'+tickName+'" style="display:none;">Correct<br />')
				.appendTo('#answers').slideDown('fast');
		$('#'+fieldName).focus();
		count += 1;
	}
	function addAnswer(){
		var fieldName = "answer" + count;
		var answerName = "answer"+count;
		var descriptionName = "description"+count;
		var tickName = "tick" + count;
		$('<textarea name="'+answerName+'" id="'+fieldName+'" rows="3" cols="30" placeholder="'+fieldName+'" style="display:none;"></textarea><br />')
				.appendTo('#answers').slideDown('fast');
		$('<textarea name="'+descriptionName+'" rows="3" cols="30" placeholder="'+descriptionName+'" style="display:none;"></textarea><br>')
				.appendTo('#answers').slideDown('fast');
		$('<input type="checkbox" name="'+tickName+'" style="display:none;">Correct<br />')
				.appendTo('#answers').slideDown('fast');
		$('#'+fieldName).focus();
		count += 1;
	}
	
	
	function clear(){
		$("#question").html("");
		count=1;
	}
	function addBlank(){
		var fieldName = "blank" + count;
		var answerName = "answer"+count;
		$('#questionText').val($('#questionText').val()+ ' '+count+'. ____ '); 
		$('<input type="text" name="'+answerName+'" id="'+fieldName+'" placeholder="'+fieldName+'" style="display:none;"><br />')
		.appendTo('#answers').slideDown('fast');
		$('#questionText').focus();
		count += 1;
	}
	
	function addQuestion() {
		clear();
		var choice = $("#type").val();
		switch (choice) {
		case 'Question-Response':
		case 'Multiple Choice':
			$('<br><textarea name="question" id="questionText" placeholder="Type your question here..." rows="3" cols="35" style="display:none;" autofocus required></textarea>').appendTo('#question').slideDown('slow');
			$('<br><textarea name="description" style="display:none;" placeholder="Enter additional description" rows="3" cols="35"></textarea>').appendTo('#question').slideDown('slow');
			$('<br><input type="text" required style="width:35px; display:none;" maxlength=3 name="timeLimit">Time Limit(minutes)<br>').appendTo('#question').slideDown('slow');
			$('<div id="answers"></div>').appendTo('#question');
			$('<button type="button" onclick="addAnswer()" style="display:none;">Create Answer</button><br> ').appendTo('#question').slideDown('slow');
			break;
		case 'Fill in the Blank':
			$('<br><textarea name="question" id="questionText" placeholder="Type your question here..." rows="3" cols="35" style="display:none;" autofocus required></textarea>').appendTo('#question').slideDown('slow');
			$('<br><textarea name="description" style="display:none;" placeholder="Enter additional description" rows="3" cols="35"></textarea>').appendTo('#question').slideDown('slow');
			$('<br><input type="text" required style="width:35px; display:none;" maxlength=3 name="timeLimit">Time Limit(minutes)<br>').appendTo('#question').slideDown('slow');
			$('<div id="answers"></div>').appendTo('#question');
			$('<button type="button" onclick="addBlank()" style="display:none;">Blank</button><br> ').appendTo('#question').slideDown('slow');
			break;
		case 'Picture-Response Questions':
			$('<br><textarea name="question" required id="questionText" placeholder="Type your question here..." rows="3" cols="35" style="display:none;" autofocus></textarea>').appendTo('#question').slideDown('slow');
			$('<br><textarea name="description" style="display:none;" placeholder="Enter additional description" rows="3" cols="35"></textarea>').appendTo('#question').slideDown('slow');
			$('<br><input type="text" required style="width:35px; display:none;" maxlength=3 name="timeLimit">Time Limit(minutes)<br>').appendTo('#question').slideDown('slow');
			$('<div id="answers"></div>').appendTo('#question');
			$('<button type="button" onclick="addImage()" style="display:none;">Create Answer</button><br> ').appendTo('#question').slideDown('slow');
			break;
		}
		$('<input type="submit" value="Next Question" style="display:none;">').appendTo('#question').slideDown('slow');
	}
	
	
</script>
<body>
	<form id="main" action="NextQuestion" method="post" onsubmit="">
		<input type="hidden" name="questionNum" value=<% out.write(request.getParameter("questionNum")); %> >
		<h2>
			Question <%	out.write(request.getParameter("questionNum"));%>:
		</h2>
		<%
			DBQuizController b = new DBQuizController();
			ArrayList<String> types = b.getQuestionTypes();
		%>
		<select id="type" name="type" onchange="addQuestion()" >
			<option disabled selected> --- select an option --- </option>
			<%
				for (int i = 0; i < types.size(); i++) {
					out.write("<option value=\"" + types.get(i) + "\">" + types.get(i) + "</option>");
				}
			%>
		</select> <br />
		<div id="question"></div>
		<input type="submit" name="finalise" value="Finish Creating">
	</form>
</body>
</html>