<%@page import="ChallengeControllers.ChallengeController"%>
<%@page import="database.UserController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="backend.*"%>
<%@page import="database.*"%>
<%@page import="DBQuizControllers.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE>
<html>
<%
	if (!(boolean) request.getSession().getAttribute("logged_in")) {
		response.sendRedirect("NotLoggedIn.jsp");
		return;
	}
%>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">
<link rel="stylesheet" type="text/css" href="MyButtonStyles.css">



<style>
div.left {
	float: left;
	padding: 20px;
}

div.right {
	text-align: center;
	margin-bottom: 10px;
}
</style>

<style type="text/css">

/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
	-webkit-animation-name: fadeIn; /* Fade in the background */
	-webkit-animation-duration: 0.4s;
	animation-name: fadeIn;
	animation-duration: 0.4s
}

/* rounded corners*/
.rounded-bottom {
	border-bottom-right-radius: 7px;
	border-bottom-left-radius: 7px;
}

/* Modal Content */
.modal-content {
	/*position: fixed;*/
	bottom: 0;
	background-color: #fefefe;
	width: 70%;
	max-width: 700px;
	min-width: 420px;
	margin-left: auto;
	margin-right: auto;
	-webkit-animation-name: fadeIn;
	-webkit-animation-duration: 0.4s;
	animation-name: fadeIn;
	animation-duration: 0.4s
}

/* The Close Button */
.close {
	color: white;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

.modal-header {
	padding: 2px 16px;
	background-color: #5cb85c;
	color: white;
}

.modal-body {
	padding: 2px 16px;
}

.modal-footer {
	padding: 10px 26px;
	background-color: #5cb85c;
	color: white;
}

@
-webkit-keyframes fadeIn {
	from {opacity: 0
}

to {
	opacity: 1
}

}
@
keyframes fadeIn {
	from {opacity: 0
}

to {
	opacity: 1
}
}
</style>

<script type="text/javascript">
	var userName = "";

	function setUserName(name) {
		userName = name;
		//alert("user name set to " + name);
	}

	function sendMessage() {
		var senderLogin = userName;
		var recipientLogin = $('#input_message_to').val();
		var messageText = $('#input_message_subject').val();
		var messageSubject = $('#input_message_text').val();

		$.get("MessageSend", {
			sender_login : senderLogin,
			recipient_login : recipientLogin,
			message_text : messageText,
			message_subject : messageSubject
		}, function(data) {
			alert('' + data);
		});

	}

	$(document).ready(function() {
		$("#button_send").click(function() {
			sendMessage();
			$('#myModal').hide();
			//clearFields();
		});
	});

	$(document)
			.ready(
					function() {
<%String userName = (String) session.getAttribute("user_name");

			out.print("setUserName('" + userName + "');");%>
	});
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
	<%
		int id = Integer.parseInt(request.getParameter("id"));
		UserController db = new UserController();
		User user = db.getUserByID(id);
		out.print(user.getName());
	%>
</title>
</head>
<body>
	<div id="main" class="main">
		<jsp:include page="Header.jsp"></jsp:include>


<div class='right'>
				<!-- Trigger/Open The Modal -->
				<button id="myBtn">Quick Message</button>
			</div>
		<!-- The Modal -->
		<div id="myModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content rounded-bottom">
				<div class="modal-header">
					<span class="close">&times</span>
					<h2>Compose Message</h2>
				</div>
				<div class="modal-body">
					To: <br> <input id="input_message_to" type="text">
					<script type="text/javascript">
						var to =
					<%String user_name = user.getLogin();

			out.print("'" + user_name + "'");%>
						;

						$("#input_message_to").val(to);
						$("#input_message_to").prop('disabled', true);
					</script>
					<br> Subject: <br> <input id="input_message_subject"
						type="text"> <br> Text: <br>
					<textarea id="input_message_text">
	      		
	      	</textarea>
				</div>
				<div class="modal-footer rounded-bottom">
					<button id="button_send">Send</button>
					<button id="button_reset">Reset</button>
				</div>
			</div>

		</div>

		<script>
			// Get the modal
			var modal = document.getElementById('myModal');

			// Get the button that opens the modal
			var btn = document.getElementById("myBtn");

			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];

			// When the user clicks the button, open the modal
			btn.onclick = function() {

				if (userName == $("#input_message_to").val()) {
					alert("Don't send messages to yourself");
					return;
				}

				modal.style.display = "block";

			}

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
				modal.style.display = "none";
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		</script>

		<div class='left' style='width: 250px;'>
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
							"<form action='ChangeImgUrl' method='post'>Chane Your Image: <input type='text' class='inputs' name='imgUrl'>"
									+ "<input type='submit' value='Submit'></form>");
					out.print(
							"<form action='ChangeUserName' method='post'>Chane Your Name: <input class='inputs' type='text' name='userName'>"
									+ "<input type='submit' value='Submit'></form>");
				} else if (!(new DBFriendController().isFriend(myId, id)) && !(new DBFriendController().isRequestSent(myId, id))) {
					request.getSession().setAttribute("friendId", id);
					out.print("<form action='SendFriendshipRequest' method='post'>"
							+ "<input type='submit' value='Send Friend Request' class='btn'></form>");
				} else if ((new DBFriendController().isFriend(myId, id))) {
					request.getSession().setAttribute("friendId", id);
					out.print("<form action='RemoveFriend' method='post'>"
							+ "<input type='submit' value='Remove Friend' class='btn'></form>");
				}
			%>
		</div>

		<%
		DBFriendController dbf3 = new DBFriendController();
			if (myId == id) {
				ArrayList<Integer> requests = dbf3.getFriendRequests(myId);
				out.print("<div class='left'><h3>Friend Requests</h3><ul> ");
				UserController userController = new UserController();
				if (requests.size() == 0) {
					out.print("<p>No New Requests</p>");
				}
				for (int i = 0; i < requests.size(); i++) {
					int friendsId = requests.get(i);
					User friend = userController.getUserByID(friendsId);
					String furl = friend.getImageURL();
					String flogin = friend.getLogin();
					String fname = friend.getName();
					out.print("<li> <img border='0' alt='FriendImage' src='" + furl + "' width='100' height='100'>"
							+ "<h3><a href='UserPage.jsp?id=" + friendsId + "'>" + flogin + "</a></h3>"
							+ "<form action='AcceptFriendRequest' method='post'>"
							+ "<input type='submit' value='Accept' class='button tick'><input type='hidden' name='fid' value='"
							+ friendsId + "'></form> ");
					out.print("<form action='RejectFriendshipRequest' method='post'>"
							+ "<input type='submit' value='Reject' class='button tick' style='float:right' ><input type='hidden' name='fid' value='"
							+ friendsId + "'></form>");
					if (fname != null) {
						out.print("<p>" + fname + "</p>");
					}
					out.print("</li>");
				}
				out.print("</ul> </div>");

			}
		%>

		<%
			if (myId == id) {
				out.print("<div class='left'><h3>Challenges</h3><ul>");
				ChallengeController chc = new ChallengeController();
				ArrayList<Challenge> challenges = chc.getChallenges(id);
				for (Challenge a : challenges) {
					a.showOnCard(out);
				}
				if (challenges.size() == 0) {
					out.print("<p>No New Challenges</p>");
				}
				out.print("</ul></div>");
			}
		%>
		<div style='height: 300px; width: 150px; margin: 2px; float: right;'>
			<div class='right'>
				<form action='SearchUser' method='post' style='text-align: center;'>
					Search User<br>
					<input type='text' class='searchInput' name='user_name'> <input
						type='submit' value='Search'>
				</form>
			</div>


			<div class='right'>

				<a class="btn" href="UserFriendlistPage.jsp?id=<%out.print(id);%>">
					Friends </a>
			</div>
			<div class='right'>
				<a class="btn" href="UserQuizes.jsp?id=<%out.print(id);%>">
					Quizzes </a>
			</div>
		</div>

	</div>

</body>
</html>