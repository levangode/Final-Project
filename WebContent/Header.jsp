<%@page import="DBQuizControllers.*"%>
<%@page import="database.*"%>
<%@page import="backend.User"%>

<style>
div.notif {
	width: 18px;
	height: 18px;
	font-size: 15px;
	text-align: center;
	font-color: red;
	position: absolute; 
	top: 8 px; 
	left: 10 px;
	right:70px;
	border: 1px solid;
}
</style>

<div id="top" style="text-align: center; height: 154; min-width: 1024;">
	<div
		style="float: left; text-align: right; width: 200px; height: 154px;">
	</div>
	<a href="HomePage.jsp"> <img src="Logo.png" width=600px;
		height="154" alt="Welcome" id="homelogo" style="margin: auto;">
	</a>
	<form action="Login" method="post"
		style="float: right; text-align: right; width: 200px; height: 154px; margin: 0px;">
		<%
			if ((boolean) request.getSession().getAttribute("logged_in")) {
				User curUser = new UserController()
						.getUserByLogin((String) request.getSession().getAttribute("user_name"));
				out.write("<span style='text-align:center; font-size:30px'><img src='" + curUser.getImageURL()
						+ "' alt='User Image' style='width:40px;height:40px; border: 2px ; border-radius: 10px'>");
				out.write((String) request.getSession().getAttribute("user_name") + "   </span>");
				String login = (String) request.getSession().getAttribute("user_name");
				DBQuizController quizCont = new DBQuizController();
				int yourID = quizCont.getAuthorId(login);
				int requestNum = new DBFriendController().getFriendRequests(yourID).size();
				if (requestNum > 0) {
					out.write("<br><div class='notif'><strong style='color:red;font-weight:bold'>" + requestNum
							+ "</strong></div>");
				}else{
					out.write("<br>");
				}
				out.write("<a href='UserPage.jsp?id=" + yourID + "' >Your Page</a>");
				out.write("<br><a  href=\"Logout.jsp\">Logout</a>");
			} else {
				out.write("User Name <input type=\"text\" name=\"user_login\">");
				out.write("Password <input type=\"password\" name=\"user_password\">");
				out.write("<input type=\"submit\" value=\"Login\">");
				out.write("<button type=\"button\" onclick=\"location.href = 'Register.html'\">Register</button>");
			}
		%>
		<br>
	</form>
</div>