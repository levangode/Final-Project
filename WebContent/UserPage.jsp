<%@page import="database.UserController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="backend.*"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">

<style>
div.left {
	float: left;
	padding: 20px;
}

div.right {
	text-align: right;
	padding: 20px;
}
</style>


<%
	int id = Integer.parseInt(request.getParameter("id"));
	UserController db = new UserController();
	User user = db.getUserByID(id);
%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%
		out.print(user.getName());
	%>
</title>
</head>
<body>
	<div class='left'>
		<h1>
			<%
				out.print(user.getName());
			%>
		</h1>


		<%
			String url = user.getImageURL();
			if (url == null) {
				url = "defaultPicture.png";
			}
			out.print("<img src='" + url + "' alt='Profile Picture' style='width:300px;height:220px;'>");
			String login = (String) request.getSession().getAttribute("user_name");
			int myId = new DBQuizController().getAuthorId(login);
			if (myId == id) {
				out.print(
						"<form action='ChangeImgUrl' method='post'>Chane Your Image: <input type='text' name='imgUrl'>"
								+ "<input type='submit' value='Submit'></form>");
			}
		%>
		<p>
			<a class="btn" href="HomePage.jsp"> Return To Homepage </a>
		</p>
	</div>
	<div class='right'>
		<a class="btn" href="UserFriendlistPage.jsp"> My Friends </a>
	</div>
	<div class='right'>
		<a class="btn" href="UserQuizes.jsp"> My Quizes </a>
	</div>
	<div class='right'>
		<a class="btn" href="UserFriendlistPage.jsp"> Recent Quizes </a>
	</div>


</body>
</html>