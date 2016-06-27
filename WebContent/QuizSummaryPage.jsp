<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Summary</title>
<style>
.column {
	float: left;
	font-family: sans-serif;
	margin: 0px 15px;
	overflow:hidden;
}
</style>
</head>
<body>
	<div style="position: relative; margin:auto; min-width:1000; text-align: center;">
		<div id="top" style="position:relative; overflow: hidden;">
			<div style="width: 22%;" class="column">
				<h4>Quiz Info:</h4>
				<p>Name: </p>
				<p>Description:</p>
				<p>Quiz Category:</p>
				<p>
					Author: <a href="UserPage.jsp">saxeli</a>
				</p>
				<p>Quiz Difficulty</p>
				<p>Create Date:</p>
				<p>Immediate Correction:</p>
				<p>Quiz Likes:</p>
				<p>Times Taken:</p>
			</div>
			<div style="width: 22%;" class="column">
				<h4>Users' past performance of this quiz</h4>

			</div>
			<div style="width: 22%;" class="column">
				<h4>Highest Performers of all time</h4>
			</div>
			<div style="width: 22%;" class="column">
				<h4>Top Performers of the day</h4>
			</div>
		</div>
		<div id="mid" style="position:relative; overflow: hidden;">
			<h4>Statistics</h4>
		</div>
		<div id="bottom" style="overflow: hidden; margin: 20px;">
			<form action="QuizPage.jsp">
				<input type="hidden" name="id"
					value=<%out.print(request.getParameter("id"));%>> <input
					type="submit" value="Take Quiz">
			</form>
		</div>
	</div>

</body>
</html>