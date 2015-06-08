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
    if (calendarCookie == null)
        response.sendRedirect("Login.jsp");
    
    String eventId = request.getParameter("id");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Termin</title>

<link rel="stylesheet" href="stylesheets/framework/font-awesome.min.css">

<link rel="stylesheet" type="text/css" href="stylesheets/custom/all.css" />
<link rel="stylesheet" type="text/css" href="stylesheets/custom/event.css" />

<script src="scripts/framework/jquery-1.11.2.min.js"></script>
<script src="scripts/framework/datetimepicker.js"></script>
<script src="scripts/custom/event.js"></script>

</head>
<body>
	<div id="event" class="claMainBox">
		<div class="claHeadLine">Terminverwaltung</div>
		<hr />
		<div id="divEventData">
			<div class="claDataRow">
				<div id="divEventTitle" class="claTwoRowData">
					<div class="claIcon">
						<span class="fa fa-bookmark faData"></span>
					</div>
					<input type="text" id="txtEventTitle" class="claTextDefault claTxtTwoRowData" name="eventTitle" placeholder="Terminname" />
				</div>
				<div id="divEventCategory" class="claTwoRowData">
					<div class="claIcon">
						<span class="fa fa-folder-open faData"></span>
					</div>
					<input type="text" id="txtEventCategory" class="claTextDefault claTxtTwoRowData" name="eventCategory" placeholder="Kategorie (optional)" />
				</div>
			</div>
			<div class="claDataRow">
				<div id="divEventStart" class="claTwoRowData">
					<div class="claIcon">
						<span id="startClock" class="fa fa-clock-o faData"></span>
					</div>
					<input type="text" id="txtEventStart" class="claTextDefault claTxtTwoRowData" name="eventStart" placeholder="Terminstart">

				</div>
				<div id="divEventEnd" class="claTwoRowData">
					<div class="claIcon">
						<span id="endClock" class="fa fa-clock-o faData"></span>
					</div>
					<input type="text" id="txtEventEnd" class="claTextDefault claTxtTwoRowData" name="eventEnd" placeholder="Terminende" />
				</div>
			</div>
			<div class="claDataRow">
				<div id="divEventLocation" class="claOneRowData">
					<div class="claIcon">
						<span class="fa fa-building faData"></span>
					</div>
					<input type="text" id="txtEventLocation" class="claTextDefault claTxtOneRowData" name="eventLocation" placeholder="Ort" />
				</div>
			</div>
			<div class="claDataRow">
				<div id="divEventRequiredGuests" class="claOneRowData">
					<div class="claIcon">
						<span class="fa fa-users faData"></span>
					</div>
					<input type="text" id="txtEventRequiredGuests" class="claTextDefault claTxtOneRowData" name="eventRequiredGuests" placeholder="Erforderliche Personen" />
				</div>
			</div>
			<div class="claDataRow">
				<div id="divEventOptionalGuests" class="claOneRowData">
					<div class="claIcon">
						<span class="fa fa-user-plus faData"></span>
					</div>
					<input type="text" id="txtEventOptionalGuests" class="claTextDefault claTxtOneRowData" name="eventOptionalGuests" placeholder="Optionale Personen" />
				</div>
			</div>

		</div>
		<div id="divButtons">
			<div class="claCenterDiv">
				<input id="btnFile" class="claButtonDefault claEventButtons" type="Button" value="Anh&auml;nge" onClick="addFileToEvent()" />
			</div>
			<div class="claCenterDiv">
				<input id="btnSubmit" class="claButtonDefault claEventButtons" type="Button" value="Speichern" onClick="saveEvent()" />
			</div>
			<div class="claCenterDiv">
				<input id="btnDelete" class="claButtonDefault claEventButtons <%= eventId == "-1" ? ".claDisabledButton" : "" %>" type="Button" value="L&ouml;schen" <%= eventId == "-1" ? "" : "onClick='deleteEvent()'" %> />
			</div>
		</div>
		<div id="divEventComment">
			<span id="faComment" class="fa fa-comments"></span>
			<span id="spaCharactersLeft">
				�brige Zeichen:
				<span id="spaChars"></span>
			</span>
			<textarea id="txaEventComment" maxlength="4000" placeholder="Bemerkung (optional)"></textarea>
		</div>
	</div>
</body>
</html>