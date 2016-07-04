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
		    {message_id : id, paramOne : 1, paramX : 'abc'},
		    function(data) {
		       alert('page content: ' + data);
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


</style>

<title>messages</title>
</head>
<body>

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