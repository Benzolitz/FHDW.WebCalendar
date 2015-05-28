<%@page import="Controller.CalendarController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	Cookie calendarCookie = null;
	String userId = null;
	String redirect = null;
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null)
	{
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("FHDW.WebCalendar")) calendarCookie = cookie;
		}
	}
	
	if (calendarCookie == null)
	{
		redirect = "Login.jsp?message=Sie muessen sich zuerst einloggen!";
	}
	else
	{
		String cookieValue = calendarCookie.getValue();
		String[] cookieValues = cookieValue.split("&");
		
		for (String value : cookieValues)
		{
			String[] val = value.split("=");
			
			if (val[0].toLowerCase().equals("userid"))
			{
				userId = val[1];
			}
		}
		
		if (userId == null)
		{
			redirect = "Login.jsp?message=UserId konnte nicht gefunden werden!";
			CalendarController.Logout(response, cookies);
		}
	}
	
	if (redirect != null)
	{
		response.sendRedirect(redirect);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kalender</title>


<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/custom/calendar.css" />


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
			<div>
				<input id="btnNewEvent" type="button" value="Neuer Termin" />
			</div>
			<div>
				<input id="btnLastWeek" type="button" value="&larr;" onClick="getLastWeek()" />
				<input id="btnNextWeek" type="button" value="&rarr;" onClick="getNextWeek()" />
			</div>
			<div>
				<input id="txtSearchBox" type="text" placeholder="Suche" />
			</div>
			<input id="ckiUserId" type="hidden" value="<%=userId%>" />
			<input id="hidCurrentWeek" type="hidden" />
			<input id="hidCurrentYear" type="hidden" />
		</div>
		<div id="calendarBody"></div>
	</div>

</body>
</html>