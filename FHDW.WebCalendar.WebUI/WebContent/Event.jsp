<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String userName = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null)
	{
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("username")) userName = cookie.getValue();
		}
	}
	if(userName == null) response.sendRedirect("Login.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css"
	href="stylesheets/custom/appointment.css" />

<link rel="stylesheet" href="stylesheets/framework/font-awesome.min.css">

</head>
<body>
	<div id="appointment">
		<h2>Terminverwaltung</h2>
		<hr />
		<form name="appointmentForm" action="EventController" method="post">
			<div id="fields"
				style="width: 80%; height: 50%; float: left; margin-top: 5px;">
				<span class="fa fa-bookmark"
					style="margin-right: 5px; margin-left: 7px;"></span> <input
					type="text" name="appointmentTitel" placeholder="Terminname" /> <span
					class="fa fa-folder-open"></span> <input type="text"
					name="appointmentCategorie" placeholder="Kategorie" /><br /> <span
					class="fa fa-clock-o" style="margin-right: 2px; margin-left: 7px;"></span>
				<input type="text" name="appointmentStart" placeholder="Start" /> <span
					class="fa fa-clock-o" style="margin-right: 4px; margin-left: 6px;"></span>
				<input type="text" name="appointmentEnd" placeholder="Ende" /><br />
				<span class="fa fa-building"
					style="margin-right: 2px; margin-left: 7px;"></span> <input
					type="text" name="appointmentLocation" placeholder="Ort" /><br />
				<span class="fa fa-users" style="margin-right: 1px;"></span> <input
					type="text" name="appointmentRequired" style="width: 545px;"
					placeholder="Erforderlich" /><br /> <span class="fa fa-user-plus"></span>
				<input type="text" name="appointmentOptional" style="width: 545px;"
					placeholder="Optional" /><br />
			</div>
			<div id="buttons" style="float: right; height: 50%; width: 19%;">
				<br /> <input type="Submit" value="Speichern" /> <br /> <br /> <input
					type="Submit" value="Anhang" />
			</div>
			<div id="addition"
				style="clear: both; width: 95%; height: 260px; margin: 5px;">
				<span class="fa fa-comments"></span> <br />
				<textarea name="taeAppointmentComment"
					style="width: 95%; height: 85%; margin-left: 5px;"
					placeholder="Anmerkung"></textarea>
				<br />
			</div>

		</form>
	</div>
</body>
</html>