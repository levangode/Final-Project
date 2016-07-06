<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="backend.*"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE>
<html>
<%
	if (!(boolean) request.getSession().getAttribute("logged_in")) {
		response.sendRedirect("NotLoggedIn.jsp");
		return;
	}
%>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
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
	List<Integer> friendIds = friendDB.getFriendsIDList(id);
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Friends</title>
</head>
<body>
	<div id="main" class="main">
		<jsp:include page="Header.jsp"></jsp:include>
		<ul>
			<%
				for (int i = 0; i < friendIds.size(); i++) {
					UserController a = new UserController();
					User friend = a.getUserByID(friendIds.get(i));
					int friendsId = friendIds.get(i);
					String url = friend.getImageURL();
					String login = friend.getLogin();
					String name = friend.getName();
					String link = "<h3><a href='UserPage.jsp?id=" + friendsId + "'>" + login + "</a></h3>";
					if (request.getParameter("challenge") != null) {
						link = "<form id='myform"+i+"' action='SendChallenge' method='post'>"
								+ "<input type='hidden' name='sender' value='" + id + "'>"
								+ "<input type='hidden' name='receiver' value='" + friendsId + "'>"
								+ "<input type='hidden' name='quiz' value='" + request.getParameter("challenge") + "'>"
								+ "<h3><a href=\"#\" onclick=\"document.getElementById('myform"+i+"').submit()\">"
								+ login + "</a><h3>";
					} 
					out.print("<li> <img border='0' alt='FriendImage' src='" + url + "' width='100' height='100'>" + link );
					
					if (name != null) {
						out.print("<p>" + name + "</p>");
					}
					out.print("</form></li>");
				}
			%>
			
		</ul>
		<div class="center">
				<a class="btn" href="UserPage.jsp?id=<%out.print(id);%>"> Return
					To User Page </a>
			</div>
	</div>
</body>
</html>