<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
body{
	text-align: center;
	line-height: 2px;
}
button{
	margin:5px;
}
input{
	margin:5px;
}
textarea{
	margin:5px;
	resize:none;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js">
</script>
<script>
	var count = 1;
	var fieldName = 0;
	function myFunction() {
		var fieldName = "answer" + count;
		var tickName = "tick" + count;
		$('<textarea rows="3" cols="35" id="'+fieldName+'" value="'+fieldName+'" style="display:none;"></textarea><br />').appendTo('#answers').slideDown('fast');
		$('<input type="checkbox" id="'+tickName+'" style="display:none;">Correct<br />').appendTo("#answers").slideDown('fast');
		count += 1;
	}
</script>

<body>
	<h3>Question '+count+':</h3>; 
	<br /><textarea id="question" rows="3" cols="35" autofocus>Type your question here...</textarea>;
	<div id="answers"></div>
	<button type="button" onclick="myFunction()">Create Answer</button>
</body>
</html>