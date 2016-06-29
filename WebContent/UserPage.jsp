<%@page import="database.UserController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="backend.*"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">
<link rel="stylesheet" type="text/css" href="MyButtonStyles.css">



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



</script>
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
			DBFriendController dbf = new DBFriendController();
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
				out.print(
						"<form action='ChangeUserName' method='post'>Chane Your Name: <input type='text' name='userName'>"
								+ "<input type='submit' value='Submit'></form>");
			} else if (!dbf.isFriend(myId, id) && !dbf.isRequestSent(myId, id)) {
				request.getSession().setAttribute("friendId", id);
				out.print("<form action='SendFriendshipRequest' method='post'>"
						+ "<input type='submit' value='Send Friend Request' class='btn'></form>");
			}
		%>
		<p>
			<a class="btn" href="HomePage.jsp"> Return To Homepage </a>
		</p>
	</div>

	<%
		if (myId == id) {
			ArrayList<Integer> requests = dbf.getFriendRequests(myId);
			out.print("<div class='left'><ul> ");
			UserController userController = new UserController();
			for (int i = 0; i < requests.size(); i++) {
				int friendsId = requests.get(i);
				User friend = userController.getUserByID(friendsId);
				String furl = friend.getImageURL();
				String flogin = friend.getLogin();
				String fname = friend.getName();
				out.print("<li> <img border='0' alt='FriendImage' src='" + furl + "' width='100' height='100'>"
						+ "<h3><a href='UserPage.jsp?id=" + friendsId + "'>" + flogin + "</a></h3>"
						+ "<form action='AcceptFriendRequest' method='post'>"
						+ "<input type='submit' value='Accept' class='button tick'><input type='hidden' name='fid' value='"+friendsId+"'></form> ");
						out.print("<form action='RejectFriendshipRequest' method='post'>"
								+ "<input type='submit' value='Reject' class='button tick' style='float:right' ><input type='hidden' name='fid' value='"+friendsId+"'></form>");
				if (fname != null) {
					out.print("<p>" + fname + "</p>");
				}
				out.print("</li>");
			}

			out.print("</ul> </div>");

		}
	%>


	<div class='right'>

		<a class="btn" href="UserFriendlistPage.jsp?id=<%out.print(id);%>">
			Friends </a>
	</div>
	<div class='right'>
		<a class="btn" href="UserQuizes.jsp?id=<%out.print(id);%>">
			Quizes </a>
	</div>



</body>
</html>