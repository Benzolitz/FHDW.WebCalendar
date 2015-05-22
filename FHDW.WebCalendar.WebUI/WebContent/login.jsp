<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loginbereich</title>
<link rel="stylesheet" type="text/css" href="stylesheets/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/login.css" />
</head>
<body>
	<div id="divLogin">
		<div id="divHeadline">Anmeldung</div>
		<hr />
		<div id="divContent">
			<form name="frmLogin" method="post" action="Login">
				<div class="divCenter">
					<input id="txtUsername" name="username" type="text"
						placeholder="Benutzername" />
				</div>
				<div class="divCenter">
					<input id="txtPassword" name="password" type="password"
						placeholder="Passwort" />
				</div>
				<div class="divCenter">
					<input id="btnSubmit" type="Submit" value="Login" />
				</div>
				<div id="divHint"><%
					if(request.getParameter("failure") != null && request.getParameter("failure").equals("true"))
					{
						out.println(request.getParameter("error"));
					}
				%></div>
				<div id="divLinks">
					<a href="registration.jsp">Registrierung</a> <br /> <a
						href="passwordReset.jsp">Passwort vergessen?</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>