<%@page import="DBQuizControllers.*"%>
<div id="top" style="text-align: center; height: 154; min-width:1024;">
	<div style="float: left; text-align: right; width: 200px; height: 154;">
	</div>
	<a href="HomePage.jsp"> <img src="Logo.png" width=600px;
		height="154" alt="Welcome" id="homelogo" style="margin: auto;">
	</a>
	<form action="Login" method="post"
		style="float: right; text-align: right; width: 200px; height: 154px; margin: 0px;">
		<%
			if ((boolean) request.getSession().getAttribute("logged_in")) {
				out.write("You are logged in as: ");
				out.write((String) request.getSession().getAttribute("user_name") + "   ");
				String login = (String) request.getSession().getAttribute("user_name");
				DBQuizController quizCont = new DBQuizController();
				int yourID = quizCont.getAuthorId(login);
				out.write("<br><a href='UserPage.jsp?id=" + yourID + "'>Your Page</a>");
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