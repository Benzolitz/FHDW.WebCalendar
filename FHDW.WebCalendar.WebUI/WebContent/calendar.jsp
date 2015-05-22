<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null)
	{
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("username"))
				userName = cookie.getValue();
		}
	}
	if(userName == null) response.sendRedirect("login.jsp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kalender</title>


<link rel="stylesheet" type="text/css" href="stylesheets/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/calender.css" />


<script src="scripts/jquery-1.11.2.min.js"></script>
<script src="scripts/calender.js"></script>

</head>
<body>
	<div id="header">
		<form action="CalendarController" method="post">
			<input type="Submit" value="Logout" />
		</form>
	</div>

	<div id="info"></div>

	<div id="calender"></div>

</body>
</html>