<%@page import="java.sql.SQLException"%>
<%@page import="DBConnector.Connector"%>
<%@page import="java.sql.Connection"%>
<%@page import="DBMessageControllers.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="backend.Message"%>
<%@page import="java.util.List"%>
<%@page import="database.UserController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript">
	///modal box test

	var userName = "";

	function setUserName(name) {
		userName = name;
		//alert("user name set to " + name);
	}

	///modal box test

	function change(id) {
		$("p").html("" + id);
	}

	function messageSeen(id) {
		//alert("in function sendReq " + param);
		$.get("MessageSeen", {
			message_id : id
		}, function(data) {
			alert('' + data);
		});
	}

	function clearFields() {
		$('#input_message_to').val("");
		$('#input_message_subject').val("");
		$('#input_message_text').val("");
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
		$("#button_change").click(function() {
			var param = $(this).attr("id");
			//alert(param);
			sendRequest(param);
		});
	});

	$(document).ready(function() {
		$("#button_seen").click(function() {
			//var param = $(this).attr("id");
			//alert(param);
			messageSeen(9);
			//sendMessage();
		});
	});

	$(document).ready(function() {
		$(".message_recieved").click(function() {
			//var param = $(this).attr("id");
			//alert(param);
			//        alert($(this).data('value'));
			$(this).addClass('message_display_seen');

			messageSeen($(this).data('value'));
			//sendMessage();
		});
	});

	function clearAllViews() {
		$("#messages_recieved").hide();
		$("#messages_sent").hide();
	}

	function switchView(view) {
		clearAllViews();
		switch (view) {
		case "inbox":
			$("#messages_recieved").show();
			break;
		case "sent":
			$("#messages_sent").show();
		}
	}

	function updateView(view_id) {

		if (view_id == "view_inbox") {
			switchView("inbox");
		}
		if (view_id == "view_sent") {
			switchView("sent");
		}
	}

	$(document).ready(function() {
		$(".views").click(function() {
			//var param = $(this).attr("id");
			//alert(param);
			//alert($(this).attr('id'));

			updateView($(this).attr('id'));

			//$(this).addClass('message_display_seen');

			//messageSeen($(this).data('value'));
			//sendMessage();
		});
	});

	$(document).ready(function() {
		$("#button_send").click(function() {
			sendMessage();
			$('#myModal').hide();
			clearFields();
		});
	});

	$(document).ready(function() {
		$("#button_reset").click(function() {
			clearFields();
		});
	});

	function showUserNotFound() {
		$("#span_not_found").show();
		$("#span_found").hide();
	}

	function showUserFound() {
		$("#span_not_found").hide();
		$("#span_found").show();
	}

	function displayUserFound() {
		var user = $("#input_message_to").val();

		$.get("UserValid", {
			user_login : user
		}, function(data) {
			if (data == "OK") {
				showUserFound();
			} else {
				showUserNotFound();
			}
		});

		//alert("asdasda");

	}

	$(document).ready(function() {
		$("#input_message_to").change(function() {
			displayUserFound();
		});
	});
</script>

<style type="text/css">
p.views {
	background-color: red;
	cursor: pointer;
	margin-left: 5px;
	margin-right: 5px;
}

#messages_sent {
	display: none;
}

#messages_recieved {
	
}

.message_display_seen {
	background: cyan;
}

.message_display {
	border: groove;
	padding: 5px;
	cursor: pointer;
}

.user_input_check {
	display: none;
}

#input_message_text {
	height: 100px;
	width: 400px;
}

body {
	background-color: #94be9c;
}

div.messageDisplay {
	
}

#message_list_frame {
	float: left;
	margin-left: 15px;
	padding: 5px;
	padding-right: 15px;
	background: pink;
	border-style: dotted;
	border-width: thin;
}

#views_list_frame {
	float: left;
	width: 100px;
	background: silver;
}

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

<title>messages</title>
</head>
<body>

	<jsp:include page="Header.jsp"></jsp:include>
	<div id="main" class="main">
		<!-- Trigger/Open The Modal -->
		<button id="myBtn">Compose Message</button>

		<!-- The Modal -->
		<div id="myModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content rounded-bottom">
				<div class="modal-header">
					<span class="close">&times</span>
					<h2>Compose Message</h2>
				</div>
				<div class="modal-body">
					To: <br> <input id="input_message_to" type="text"> <span
						id="span_not_found" class="user_input_check">User with such
						login not found</span> <span id="span_found" class="user_input_check">User
						name is Valid</span> <br> Subject: <br> <input
						id="input_message_subject" type="text"> <br> Text: <br>
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

		<p>This is a decent mailing system!</p>


		<br>
		<div style="width:280px; margin:auto;">
			<div id="views_list_frame">
				Views
				<p id="view_inbox" class="views">Inbox</p>

				<p id="view_sent" class="views">Sent</p>

			</div>

			<div id="message_list_frame">
				<div id="messages_recieved">
					Messages Received

					<%
					String userName = (String) session.getAttribute("user_name");

					out.print("<script>" + "setUserName('" + userName + "');" + "</script>");

					Connection con = null;
					
					try {
						con = Connector.getConnection();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					UserController dbu = new UserController(con);

					int userID = dbu.getUserIDByLogin(userName);

					DBMessage dbm = new DBMessage();

					List<MessageRecievedInfo> recievedMessages = dbm.getRecievedMessagesInfo(userID);

					for (int i = 0; i < recievedMessages.size(); i++) {
						out.print(recievedMessages.get(i).getPreviewHTML(i + 1));
					}
					
					Connector.returnConnection(con);
					
				%>

					<!-- <button id = "button_seen" type = "button" >
				Test Seen Servlet
			</button>
	
			<button id = "button_send1" type = "button" >
				Test Send Message Servlet
			</button> -->

				</div>
			</div>

			<div id="messages_sent">
				Messages Sent

				<%
				//String userName = (String)session.getAttribute("user_name");

				//out.print("<script>" + "setUserName('" + userName + "');"+ "</script>");

				Connection con1 = null;
		
				try {
					con = Connector.getConnection();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				UserController dbu1 = new UserController(con1);

				int userID1 = dbu1.getUserIDByLogin(userName);

				DBMessage dbm1 = new DBMessage();

				List<MessageSentInfo> sentMessages = dbm1.getSentMessagesInfo(userID);

				for (int i = 0; i < sentMessages.size(); i++) {
					out.print(sentMessages.get(i).getPreviewHTML(i + 1));
				}
				
				Connector.returnConnection(con1);
			%>

			</div>

		</div>
	</div>

</body>
</html>