
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loginbereich</title>
<link rel="stylesheet" type="text/css" href="stylesheets/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/passwordReset.css" />
</head>
<body>
	<div id="divPasswordReset">
		<div id="divHeadline">Passwort zurücksetzen</div>
		<hr />
		<div id="divContent">
			<form name="frmLogin" method="post" action="PasswordReset">
				<div class="divCenter">
					<input id="txtUsername" name="username" type="text" placeholder="Benutzername" />
				</div>
				<div id="divOr">
				- oder - 
				</div>
				<div class="divCenter">
					<input id="txtUsermail" name="usermail" type="text" placeholder="E-Mailadresse" />
				</div>
				<div class="divCenter">
					<input id="btnSubmit" type="Submit" value="Neues Passwort" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>