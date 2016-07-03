<%@page import="DBQuizControllers.DBQuizController"%>
<%@page import="database.DBconnector"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
		<%
			if (!(boolean) request.getSession().getAttribute("logged_in")) {
				response.sendRedirect("NotLoggedIn.jsp");
				return;
			}
		%>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
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
<script src="ScriptsForQuestionCreating.js" type="text/javascript"></script>
<body>
	<div id="main" class="main">
		<jsp:include page="Header.jsp" />
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