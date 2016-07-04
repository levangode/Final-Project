<%@page import="DBMessageControllers.MessageRecievedInfo"%>
<%@page import="DBMessageControllers.DBMessage"%>
<%@page import="DBMessageControllers.MessageInfo"%>
<%@page import="backend.Message"%>
<%@page import="java.util.List"%>
<%@page import="database.UserController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script type="text/javascript">
	///modal box test
	
	
	
	///modal box test
	
	function change(id){
		$("p").html("" + id);
	}
	
	function sendRequest(param){
		//alert("in function sendReq " + param);
		$.get(
		    "MessageTest",
		    {paramOne : 1, paramX : 'abc'},
		    function(data) {
		       alert('page content: ' + data);
	    	}
		);
	}
	
	function messageSeen(id){
		//alert("in function sendReq " + param);
		$.get(
		    "MessageSeen",
		    {message_id : id},
		    function(data) {
		       alert('' + data);
	    	}
		);
	}
	
	function sendMessage(){
		var senderLogin = "z";
		var recipientLogin = "mike";
		var messageText = "ra xdeba shechema";
		var messageSubject = "trakoo!";
		
		$.get(
		    "MessageSend",
		    {
		    	sender_login : senderLogin,
		    	recipient_login : recipientLogin,
		    	message_text : messageText,
		    	message_subject : messageSubject
		    },
		    function(data) {
		       alert('' + data);
		    }
		);
		
	}

	$(document).ready(function(){
	    $("#button_change").click(function(){
	    	var param = $(this).attr("id");
	    	//alert(param);
	        sendRequest(param);
	    });
	});
	
	$(document).ready(function(){
	    $("#button_seen").click(function(){
	    	//var param = $(this).attr("id");
	    	//alert(param);
	        messageSeen(1);
	        //sendMessage();
	    });
	});
	
	$(document).ready(function(){
	    $("#button_send").click(function(){
	    	//var param = $(this).attr("id");
	    	//alert(param);
	        //messageSeen(1);
	        sendMessage();
	    });
	});

</script>

<style type="text/css">
	div.messageDisplay{
	    
	}
	
	#message_list_frame{
		float: left;

		margin-left: 15px;
		padding: 5px;
		padding-right: 15px;
		
		background: pink;
    	border-style: dotted;
    	border-width: thin;
    	
	}
	
	#views_list_frame{
		float: left;
		width: 80px;
		
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
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    -webkit-animation-name: fadeIn; /* Fade in the background */
    -webkit-animation-duration: 0.4s;
    animation-name: fadeIn;
    animation-duration: 0.4s
}

/* Modal Content */
.modal-content {
    /*position: fixed;*/
    bottom: 0;
    background-color: #fefefe;
    width: 70%;
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

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

.modal-header {
    padding: 2px 16px;
    background-color: #5cb85c;
    color: white;
}

.modal-body {padding: 2px 16px;}

.modal-footer {
    padding: 2px 16px;
    background-color: #5cb85c;
    color: white;
}

/* Add Animation */
/*@-webkit-keyframes slideIn {
    from {bottom: -300px; opacity: 0}
    to {bottom: 0; opacity: 1}
}

@keyframes slideIn {
    from {bottom: -300px; opacity: 0}
    to {bottom: 0; opacity: 1}
}*/

@-webkit-keyframes fadeIn {
    from {opacity: 0}
    to {opacity: 1}
}

@keyframes fadeIn {
    from {opacity: 0}
    to {opacity: 1}
}


</style>

<title>messages</title>
</head>
<body>

	<h2>Bottom Modal</h2>

	<!-- Trigger/Open The Modal -->
	<button id="myBtn">Open Modal</button>
	
	<!-- The Modal -->
	<div id="myModal" class="modal">
	
	  <!-- Modal content -->
	  <div class="modal-content">
	    <div class="modal-header">
	      <span class="close">&times</span>
	      <h2>Modal Header</h2>
	    </div>
	    <div class="modal-body">
	      <p>Some text in the Modal Body</p>
	      <p>Some other text...</p>
	      <p>Some text in the Modal Body</p>
	      <p>Some other text...</p>
	      <p>Some text in the Modal Body</p>
	      <p>Some other text...</p>
	      <p>Some text in the Modal Body</p>
	      <p>Some other text...</p>
	    </div>
	    <div class="modal-footer">
	      <h3>Modal Footer</h3>
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

	<p>Trawi is real!</p>

	<button id="button_change" type="button">Click tom do things!!</button>

	<p>z</p>

	<form action="MessageTest" method="get">
		<input type="submit" value = "TestMessage">
			 
		
	</form>

	</br>

	<div id="views_list_frame">
		Views
		
	</div>

	<div id="message_list_frame">
		Messages

		<%
			String userName = (String)session.getAttribute("user_name");
			
			out.print("<h1>" + "Traki " + userName + "</h1>");
			
			UserController dbu = new UserController();
			
			int userID = dbu.getUserIDByLogin(userName);
			
			DBMessage dbm = new DBMessage();
			
			List<MessageRecievedInfo> recievedMessages = dbm.getRecievedMessagesInfo(userID);
			
			for(int i=0; i<recievedMessages.size(); i++){
				out.print(recievedMessages.get(i).getPreviewHTML(i+1));
			}
		%>
		
		<button id = "button_seen" type = "button" >
			Test Seen Servlet
		</button>

		<button id = "button_send" type = "button" >
			Test Send Message Servlet
		</button>
		
		<div>
			<p>MessageOne</p>
			
		</div>

		<div>
			<p>MessageTwo</p>
		</div>

		<div>
			<p>MessageThree</p>
		</div>

	</div>


</body>
</html>