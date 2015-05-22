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
	if(userName != null) response.sendRedirect("Calendar.jsp");
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
<script src="scripts/custom/register.js"></script>

</head>
<body>

	<div id="divRegistration">
		<div id="divHeadline">Registrierung</div>
		<hr />
		<div id="divContent">
			<form name="frmLogin" method="post" action="RegisterController">
				<div class="divCenter">
					<input id="txtUsername" name="txtUsername"
						class="claUserdata claRequiredField" type="text"
						placeholder="Benutzername*" />
				</div>
				<div class="divCenter">
					<input id="txtPassword" name="txtPassword"
						class="claUserdata claRequiredField" type="password"
						placeholder="Passwort*" />
				</div>
				<div class="divCenter">
					<input id="txtPasswordCheck" name="txtPasswordCheck"
						class="claUserdata claRequiredField" type="password"
						placeholder="Passwort wiederholen*" />
				</div>
				<div class="divCenter">
					<input id="txtUsermail" name="txtUsermail"
						class="claUserdata claRequiredField" type="text"
						placeholder="E-Mailadresse*" />
				</div>
				<div class="divCenter">
					<input id="txtUserFirstname" name="txtUserFirstname"
						class="claUserdata" type="text" placeholder="Vorname" />
				</div>
				<div class="divCenter">
					<input id="txtUserLastname" name="txtUserLastname"
						class="claUserdata" type="text" placeholder="Nachname" />
				</div>
				<div class="divCenter">
					<input id="txtUserphone" name="txtUserphone" class="claUserdata"
						type="text" placeholder="Telefon" />
				</div>
				<div class="divCenter">
					<input id="txtUserSecurityQuestion" name="txtUserSecurityQuestion"
						class="claUserdata claRequiredField" type="text"
						placeholder="Sicherheitsfrage*" />
				</div>
				<div class="divCenter">
					<input id="txtUserSecurityAnswer" name="txtUserSecurityAnswer"
						class="claUserdata claRequiredField" type="text"
						placeholder="Sicherheitsantwort*" />
				</div>
				<div class="divCenter">
					<input id="btnSubmit" name="btnSubmit"
						class="claButtons claEnabledButton" type="Submit"
						value="Registrieren" /> <input id="btnReset" name="btnReset"
						class="claButtons claEnabledButton" type="Reset"
						value="Zurücksetzen" />
				</div>
				<div id="divHint">Mit * gekennzeichnete Felder, müssen
					ausgefüllt werden!</div>
			</form>
		</div>
	</div>
</body>
</html>