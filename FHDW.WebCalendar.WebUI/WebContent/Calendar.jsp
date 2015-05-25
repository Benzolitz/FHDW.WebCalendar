<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null)
	{
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("username")) userName = cookie.getValue();
		}
	}
	if (userName == null) response.sendRedirect("Login.jsp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kalender</title>


<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css"
	href="stylesheets/custom/calendar.css" />


<script src="scripts/framework/jquery-1.11.2.min.js"></script>
<script src="scripts/custom/calendar.js"></script>

</head>
<body>
	<div id="header">
		<input id="btnLogout" type="button" value="Logout" onClick="Logout()" />
	</div>

	<div id="info"></div>

	<div id="calendar">
		<div id="calendarHead">
			<input id="txtSearchBox" type="text" placeholder="Suche"/>
			<input id="btnLastWeek" type="button" value="d" onClick="getLastWeek()" />
			<input id="btnNextWeek" type="button" value="d" onClick="getNextWeek()" />
			<input id="hidCurrentWeek" type="hidden" />
		</div>
		<div id="calendarBody"></div>
	</div>

</body>
</html>