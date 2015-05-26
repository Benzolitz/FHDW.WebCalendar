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
	if (userName != null) response.sendRedirect("Calendar.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loginbereich</title>

<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css"
	href="stylesheets/custom/register.css" />


<script src="scripts/framework/jquery-1.11.2.min.js"></script>
<script src="scripts/custom/all.js"></script>
<script src="scripts/custom/register.js"></script>

</head>
<body>

	<div id="divRegistration" class="claMainBox">
		<div id="divHeadline" class="claHeadLine">Registrierung</div>
		<hr />
		<div id="divContent">
			<form name="frmLogin" method="post" action="RegisterController">
				<div class="claCenterBoxDefault">
					<input id="txtUsername" name="txtUsername" class="claTextDefault claUserData claRequiredField" type="text" value="<% String username = request.getParameter("username"); if(username != null) out.print(username);%>" placeholder="Benutzername*" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtPassword" name="txtPassword" class="claTextDefault claUserData claRequiredField" type="password" placeholder="Passwort*" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtPasswordCheck" name="txtPasswordCheck" class="claTextDefault claUserData claRequiredField" type="password" placeholder="Passwort wiederholen*" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtUsermail" name="txtUsermail" class="claTextDefault claUserData claRequiredField" type="text" placeholder="E-Mailadresse*" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtUserFirstname" name="txtUserFirstname" class="claTextDefault claUserData" type="text" placeholder="Vorname" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtUserLastname" name="txtUserLastname" class="claTextDefault claUserData" type="text" placeholder="Nachname" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtUserphone" name="txtUserphone" class="claTextDefault claUserData" type="text" placeholder="Telefon" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtUserSecurityQuestion" name="txtUserSecurityQuestion" class="claTextDefault claUserData claRequiredField" type="text" placeholder="Sicherheitsfrage*" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtUserSecurityAnswer" name="txtUserSecurityAnswer" class="claTextDefault claUserData claRequiredField" type="text" placeholder="Sicherheitsantwort*" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="btnSubmit" name="btnSubmit" class="claButtonDefault claButtons" type="Submit" value="Registrieren" /> 
					<input id="btnReset" name="btnReset" class="claButtonDefault claButtons" type="Reset" value="Zur&uuml;cksetzen" />
				</div>
				<div id="divHint">Mit * gekennzeichnete Felder, m&uuml;ssen ausgef&uuml;llt werden!</div>
			</form>
		</div>
	</div>
</body>
</html>