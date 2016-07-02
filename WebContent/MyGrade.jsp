<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="BasicStyles.css">
<link rel="stylesheet" type="text/css" href="Button.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz Score</title>
</head>
<body>

	<%
		double score = (double) request.getSession().getAttribute("userScore");
		double maxScore = (double) request.getSession().getAttribute("maxScore");
		out.print(score + "/" + maxScore);
	%>


</body>
</html>