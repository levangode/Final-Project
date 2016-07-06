<%@page import="java.sql.SQLException"%>
<%@page import="DBConnector.Connector"%>
<%@page import="java.sql.Connection"%>
<%@page import="backend.*"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">
<link rel="stylesheet" type="text/css" href="MyButtonStyles.css">
<%
	if (!(boolean) request.getSession().getAttribute("logged_in")) {
		response.sendRedirect("NotLoggedIn.jsp");
		return;
	}
%>
<%
	String user_name = request.getParameter("user_name");
%>

<title>Search Results for</title>
</head>
<body>
	<div id="main" class="main">
	<jsp:include page="Header.jsp"></jsp:include>
		<%
			Connection con = null;
			
			try {
				con = Connector.getConnection();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			List<User> users = new UserController(con).getUserList(user_name);
			out.print("<div class='left'><ul> ");
			UserController userController = new UserController(con);
			for (int i = 0; i < users.size(); i++) {
				User res = users.get(i);
				int userid = new DBQuizController().getAuthorId(res.getLogin());
				String uurl = res.getImageURL();
				String ulogin = res.getLogin();
				String uname = res.getName();
				out.print("<li> <img border='0' alt='FriendImage' src='" + uurl + "' width='100' height='100'>"
						+ "<h3><a href='UserPage.jsp?id=" + userid + "'>" + ulogin + "</a></h3>");

				if (uname != null) {
					out.print("<p>" + uname + "</p>");
				}
				out.print("</li>");
			}

			out.print("</ul> </div>");
			
			Connector.returnConnection(con);
		%>
	</div>
</body>
</html>