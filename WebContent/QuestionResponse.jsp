<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js">
</script>
<script>
	var count = 1;
	var fieldName = 0;
	function myFunction() {
		var fieldName = "answer" + count;
		var tickName = "tick" + count;
		
		$('<input type="text" id="'+fieldName+'" value="'+fieldName+'" style="display:none;">').appendTo('#answers').slideDown('fast');
		$('<input type="checkbox" id="'+tickName+'" style="display:none;">Correct<br />').appendTo("#answers").slideDown('fast');
		count += 1;
	}
</script>
</head>
<body>
	Question: <input type="text" id="question" value="Type your question here...">
	<div id="answers"></div>
	<button type="button" onclick="myFunction()">Create</button>
	
</body>
</html>