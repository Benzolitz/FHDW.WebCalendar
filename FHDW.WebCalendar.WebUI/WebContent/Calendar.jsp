<!--
 *
 * @author Lucas Engel
 * 
 * 			Kalendaransicht.
 * 			Auf dieser Webseite bekommt der Benutzer mehrere Informationen angezeigt.
 * 			Im #header-Bereich befindet sich aktuell nur die Möglichkeit zum Ausloggen. Hier könnten weitere Informationen über den Benutzer stehen, oder weitere allgemeine Funktionalitäten eingebaut werden.
 * 			Im #userControls-Bereich stehen dem Benutzer alle wichtigen Funktionalitäten zu Verfügung. Hier kann der Benutzer Termine suchen, einen neuen Termin anlegen, die derzeitige Woche ändern und seine Kalender verwalten.
 * 			Im #calendar-Bereich ist der eigentliche Kalendar mit den Terminen des Benutzers zu sehen.
 *
 *
-->
 
<%@page import="Controller.CalendarController, java.util.*"%>
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
            if (cookie.getName().equals("FHDW.WebCalendar"))
                calendarCookie = cookie;
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
<script src="scripts/framework/jquery-collision.min.js"></script>
<script src="scripts/custom/calendar.js"></script>

</head>
<body>
	<div id="header">
		<input id="btnLogout" type="button" value="Logout" onClick="Logout()" />
	</div>
	<div id="calendarBox">
		<div id="userControls">
			<div id="divSearchBox">
				<input id="txtSearchBox" type="text" placeholder="Suche" />
			</div>
			<div id="divSearchResults"></div>
			<div id="divNewEvent">
				<input id="btnNewEvent" type="button" value="Neuer Termin" onClick="openEventWindow(-1)" />
			</div>
			<div id="divArrows">
				<input id="btnLastWeek" type="button" value="&uarr;" onClick="getLastWeek()" />
				<br />
				<input id="btnNextWeek" type="button" value="&darr;" onClick="getNextWeek()" />
			</div>
			<form>
				<div id="divCalendarSelection">
					<input type="button" id="btnCreateNewCalendar" class="claButtonDefault" value="Neuer Kalender" onClick="showCalendarCreation()" />

					<%
					    if (userId != null)
					    {
					        Services.CalendarService calendarService = new Services.CalendarService();

					        Collection<Model.Calendar.Calendar> userCalendar = calendarService
					                .GetAllUserCalendar(Integer.parseInt(userId));

					        boolean firstCal = true;
					        for (Model.Calendar.Calendar cal : userCalendar)
					        {
					            String checked = firstCal ? "checked='checked'" : "";

					            out.write("<div class='claCalendarSelection'><input "
					                    + checked + " type='radio' id='" + cal.GetId()
					                    + "' name='Calendar' value='" + cal.GetName()
					                    + "'><label for='" + cal.GetId() + "'> "
					                    + cal.GetName() + "</label></div>");
					            firstCal = false;

					        }
					    }
					%>
				</div>
			</form>
			<input id="ckiUserId" type="hidden" value="<%=userId%>" />
			<input id="hidCurrentWeek" type="hidden" />
			<input id="hidCurrentYear" type="hidden" />
		</div>
		<div id="calendar"></div>
	</div>
	<div id="divNewCalendar">
		Neuen Kalender erstellen
		<br />
		Name:
		<input type="text" id="txtNewCalendarName" />
		<br />
		<input type="button" id="btnSaveNewCalendar" class="claButtonDefault" value="Speichern" onClick="saveNewCalendar()" />
		<input type="button" id="btnAbortNewCalendarCreation" class="claButtonDefault" value="Abbrechen" onClick="abortCalendarCreation()" />
	</div>
</body>
</html>