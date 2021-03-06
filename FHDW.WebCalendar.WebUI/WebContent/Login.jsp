<!--
 *
 * @author Lucas Engel
 * 
 * 			Loginbereich
 * 			Diese Webseite dient f�r den Login. Der Benutzer kann au�erdem zu der Webseite Passwort zur�cksetzen (Reset.jsp) oder zur Registrierung (Register.jsp) gehen.
 *
 *
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	Cookie calendarCookie = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null)
	{
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("FHDW.WebCalendar")) calendarCookie = cookie;
		}
	}
	if (calendarCookie != null) response.sendRedirect("Calendar.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/custom/login.css" />

<script src="scripts/framework/jquery-1.11.2.min.js"></script>
<script src="scripts/custom/login.js"></script>

</head>
<body>
	<div id="divLogin" class="claMainBox">
		<div id="divHeadline" class="claHeadLine">Anmeldung</div>
		<hr />
		<div id="divContent">
			<form name="frmLogin" method="post" action="LoginController">
				<div class="claCenterBoxDefault">
					<input id="txtUsername" class="claTextDefault" name="username" type="text" value="<%String username = request.getParameter("username");
			if (username != null) out.print(username);%>" placeholder="Benutzername" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtPassword" class="claTextDefault" name="password" type="password" placeholder="Passwort" />
				</div>
				<div id="divLinks" class="claCenterBoxDefault">
					<a href="Reset.jsp">Passwort vergessen?</a>
				</div>
				<div class="claCenterBoxDefault">
					<input id="btnSubmit" class="claButtonDefault" type="Submit" value="Login" />
					<input id="btnRegister" class="claButtonDefault" type="button" value="Registrieren" />
				</div>
			</form>
			<%
				String message = request.getParameter("message");
				if (message != null)
				{
					out.println("<div id='divHint'>" + message + "</div>");
				}
			%>
		</div>
	</div>
</body>
</html>
