<%@page import="DBQuizControllers.DBQuizController"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="ScriptsForQuestionCreating.js" type="text/javascript"></script>
<body>
	<div id="main">
	<jsp:include page="Header.jsp" />
	<%
		if (!(boolean) request.getSession().getAttribute("logged_in")) {
			response.sendRedirect("NotLoggedIn.jsp");
		}
	%>
	<form id="main" action="NextQuestion" method="post" onsubmit="">
		<input type="hidden" name="questionNum"
			value=<%out.write(request.getParameter("questionNum"));%>>
		<h2>
			Question
			<%
			out.write(request.getParameter("questionNum"));
		%>:
		</h2>
		<%
			DBQuizController b = new DBQuizController();
			ArrayList<String> types = b.getQuestionTypes();
		%>
		<select id="type" name="type" onchange="addQuestion()">
			<option disabled selected>--- select an option ---</option>
			<%
				for (int i = 0; i < types.size(); i++) {
					out.write("<option value=\"" + types.get(i) + "\">" + types.get(i) + "</option>");
				}
			%>
		</select> <br />
		<div id="question"></div>
		<input type="submit" name="finalise" value="Finish Creating">
	</form>
	</div>
</body>
</html>