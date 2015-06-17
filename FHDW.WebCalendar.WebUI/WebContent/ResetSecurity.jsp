<!--
 *
 * @author Lucas Engel
 * 
 * 			Passwort zur�cksetzen
 * 			Dies ist der zweite Teil des Passwort zur�cksetzen Verfahrens, bei welchem der Benutzer seine Sicherheitsfrage beantworten muss und ein neues Passwort angeben muss.
 *
 *
-->

<%@page import="Services.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    Cookie calendarCookie = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null)
    {
        for (Cookie cookie : cookies)
        {
            if (cookie.getName().equals("FHDW.WebCalendar"))
                calendarCookie = cookie;
        }
    }
    if (calendarCookie != null)
        response.sendRedirect("Calendar.jsp");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loginbereich</title>
<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/custom/reset.css" />
</head>
<body>
	<div id="divPasswordResetSecurity" class="claMainBox">
		<div id="divHeadline" class="claHeadLine">Sicherheitsabfrage</div>
		<hr />
		<div id="divContent">
			<form name="frmResetSecurity" method="post" action="ResetSecurityController">
				<div class="claCenterBoxDefault">
					<%
					    String userId = request.getParameter("user");
					    UserService userService = new UserService();
					    String question = userService
					            .GetUserSecurityQuestion(Integer.parseInt(userId));

					    out.write(question);
					%>
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtSecAnswer" class="claTextDefault" name="securityAnswer" type="text" placeholder="Sicherheitsantwort" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="txtNewPassword" class="claTextDefault" name="newPassword" type="password" placeholder="Neues Passwort" />
				</div>
				<div class="claCenterBoxDefault">
					<input id="btnSubmit" class="claButtonDefault" type="Submit" value="Absenden" />
				</div>
				<input type="hidden" name="userId" value="<%=userId%>" />
			</form>
		</div>
	</div>
</body>
</html>