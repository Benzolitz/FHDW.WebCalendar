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
	href="stylesheets/custom/login.css" />
</head>
<body>
	<div id="divLogin">
		<div id="divHeadline">Anmeldung</div>
		<hr />
		<div id="divContent">
			<form name="frmLogin" method="post" action="LoginController">
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
					<input id="btnRegister" type="Submit" value="Registrieren" />
				</div>
				<div id="divHint">
					<%
						String message = request.getParameter("message");
						if (message != null)
						{
							out.println(message);
						}
					%>
				</div>
				<div id="divLinks">
					<a href="Register.jsp">Registrierung</a> <br /> <a
						href="Reset.jsp">Passwort vergessen?</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
