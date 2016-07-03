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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript">
	var numQuestion = -1;

	var curQuestion = 1;
	
	function hideQuestions(){
		var x;
		for(x=2; x<=numQuestion; x++){
			$("#div_question_"+x).hide();
		}
	}
	
	function setNumQuestions(num){
		numQuestion = num;
		//alert("question number set to " + num);
		hideQuestions();
	}

	function nextQuestion(){
		//alert("display next question");
		if(curQuestion == numQuestion){
			alert("This is the last Question!");
		}
		else {
			$("#" + "div_question_" + curQuestion).hide();
			curQuestion++;
			$("#" + "div_question_" + curQuestion).show();
		}
	}

	function prevQuestion(){
		//alert("display previous question");
		
		if(curQuestion == 1){
			alert("This is The firts Question!");
		}
		else {
			$("#" + "div_question_" + curQuestion).hide();
			curQuestion--;
			$("#" + "div_question_" + curQuestion).show();
		}
		
	}

	function switchQuestion(id){
		if(id == "but_next"){
			nextQuestion();
		} 
		else if (id == "but_prev"){
			prevQuestion();
		}
		else {
			alert("shit happens");
		}
	}

	$(document).ready(function(){
	    $(".next_prev_buttons").click(function(){
	    	switchQuestion($(this).attr("id"));
	    });
	});

</script>

<style>
body {
	text-align: left
}

.next_prev_buttons{
	float:right;
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
					out.print("<div id = 'div_question_" + (i+1) + "'>");
					out.print((i + 1) + ")");
					out.print(q.getQuestionHtml(i));
					out.print("</div>");
				}
				
				out.print("<script> setNumQuestions("+ questions.size() +"); </script>");
			%>

			

			<div id="div_buttons">
				<button type = "button" id="but_next" class = "next_prev_buttons">next</button>
				<button type = "button" id="but_prev" class = "next_prev_buttons">previous</button>
			</div>

			<input type="hidden" name="id"
				value=<%out.write(request.getParameter("id"));%>> <input
				type="submit" value="Submit">
		</form>
	</div>
</body>
</html>
