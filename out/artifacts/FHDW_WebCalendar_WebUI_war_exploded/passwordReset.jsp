
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
	<div id="resetBox">
		<form name="FrmReset" method="post" action="#">
			<div id="divHeadline"></div>
			<div>
				<input id="txtUsername" name="txtUsername" type="text" />
			</div>
			
			<div style="clear:both; margin-bottom: 7px;">- oder -</div>
			
			<div>
				<input id="txtUsermail" name="txtUsermail" type="password" />
			</div>
			<input type="Submit" value="Passwort zur&uuml;cksetzen" name="BtnReset" style="clear: both; float: right; width: 210px;" />
		</form>
	</div>
</body>
</html>