<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="backend.*"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">
<link rel="stylesheet" type="text/css" href="StyleForUserQuizes.css">
<style>
div.center {
	text-align: center;
	padding: 20px;
}

div.left {
	text-align: left;
	padding: 20px;
}
</style>


<%
	int mid = Integer.parseInt(request.getParameter("id"));
	UserController db = new UserController();
	User user = db.getUserByID(mid);
	DBFriendController friendDB = new DBFriendController();
	DBQuizController quizDB = new DBQuizController();
	int id = quizDB.getAuthorId(user.getLogin());
	UserController userController = new UserController();
	List<Integer> friendIds = friendDB.getFriendsIDList(id);
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Friends</title>
</head>
<body>

	<%
		for (int i = 0; i < friendIds.size(); i++) {
			User friend = userController.getUserByID(friendIds.get(i));
			int friendsId = friendIds.get(i);
			String url = friend.getImageURL();
			String name = friend.getName();
			out.print("<li> <img border='0' alt='FriendImage' src='" + url + "' width='100' height='100'>"
					+ "<h3><a href='UserPage.jsp?id=" + friendsId + "'>" + name + "</a></h3>	</li>");
		}
	%>

	<div class="center">
		<a class="btn" href="HomePage.jsp"> Return To Homepage </a>
	</div>
	<div class="center">
		<a class="btn" href="UserPage.jsp?id=<%out.print(id);%>"> Return
			To User Page </a>
	</div>

</body>
</html>