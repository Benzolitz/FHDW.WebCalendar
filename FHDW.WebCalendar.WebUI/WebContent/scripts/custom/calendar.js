$(document).ready(function() {
	{
		buildUserCalendar(-1, new Date().getFullYear());
	}
});

/**
 * Baut die Tabelle und setzt die Termine eines Nutzers.
 * 
 * @param p_week
 * @param p_year
 */
var buildUserCalendar = function(p_week, p_year) {
	var week = checkWeek(p_week);
	$("#hidCurrentWeek").val(week);
	$("#hidCurrentYear").val(p_year);

	buildCalendar(week, p_year);
	showUserEvents(week, p_year);
};

/**
 * Überprüfe die angegebene Kalenderwoche.
 * 
 * @param p_week
 * @return Gibt eine Kalenderwoche zurück.
 */
var checkWeek = function(p_week) {
	return p_week === -1 ? new Date().getWeekNumber() : p_week;
};

/**
 * Hole die aktuelle Kalenderwoche.
 * 
 * @source http://stackoverflow.com/a/6117889
 * @return Gibt die aktuelle Kalenderwoche zurück.
 */
Date.prototype.getWeekNumber = function() {
	var date = new Date(+this);
	date.setHours(0, 0, 0);
	date.setDate(date.getDate() + 4 - (date.getDay() || 7));
	return Math
			.ceil((((date - new Date(date.getFullYear(), 0, 1)) / 8.64e7) + 1) / 7);
};

/**
 * Baue die Wochentabelle auf.
 * 
 * @param p_week
 * @param p_year
 * @return Gibt eine aufgebaute Tabelle zurück
 */
var buildCalendar = function(p_week, p_year) {
	var table = "<table id='tabCalendar'>";

	table += getTableHead(p_week, p_year);
	table += getTableBody();

	table += "</table>";

	$("#calendar").html(table);
};

/**
 * Baut den Kalenderkopf auf.
 * 
 * @param p_week
 * @param p_year
 * @return Gibt einen aufgebauten Kalenderkopf zurück.
 */
var getTableHead = function(p_week, p_year) {
	var firstDayOfWeek = getDateOfISOWeek(p_week, p_year);
	var tableHead = "<thead><tr>";

	for (var i = 0; i <= 7; i++) {
		if (i == 0) {
			tableHead += "<th id='tdTime'></th>";
		} else {
			tableHead += "<th class='claHead'>"
					+ getDate(firstDayOfWeek, i - 1) + "</th>";
		}
	}

	tableHead += "</thead></tr>";
	return tableHead;

};

/**
 * Hole den ersten Tag einer Kalenderwoche.
 * 
 * @source http://stackoverflow.com/a/16591175
 * @param p_week
 * @param p_year
 * @return Erster Tag der angegebenen Kalenderwoche.
 */
function getDateOfISOWeek(p_week, p_year) {
	var simple = new Date(p_year, 0, 1 + (p_week - 1) * 7);
	var dow = simple.getDay();
	var ISOweekStart = simple;
	if (dow <= 4) {
		ISOweekStart.setDate(simple.getDate() - simple.getDay() + 1);
	} else {
		ISOweekStart.setDate(simple.getDate() + 8 - simple.getDay());
	}
	return ISOweekStart;
}

/**
 * Hole ein formatiertes Datum.
 * 
 * @param p_firstDayOfWeek
 * @param p_additionalDays
 * @return Formatiertes Datum - "ddd. dd.MM.yyyy"
 */
var getDate = function(p_firstDayOfWeek, p_additionalDays) {
	var currentDate = new Date(p_firstDayOfWeek.getTime()
			+ (p_additionalDays * 24 * 60 * 60 * 1000));

	var weekDay = currentDate.getDay();
	var weekDays = new Array("So.", "Mo.", "Di.", "Mi.", "Do.", "Fr.", "Sa.")

	var day = currentDate.getDate();
	if (day < 10) {
		day = "0" + day
	}

	var month = currentDate.getMonth() + 1;
	if (month < 10) {
		month = "0" + month
	}

	var year = currentDate.getFullYear();

	return weekDays[weekDay] + " " + day + "." + month + "." + year;
};

/**
 * Baue den Body der Tabelle.
 * 
 * @return Aufgabauter Body der Tabelle.
 */
var getTableBody = function() {
	var tableBody = "<tbody>";

	tableBody += getAllDayRow();
	tableBody += getTimeRows();

	tableBody += "</tbody>";
	return tableBody;
};

/**
 * Baue eine Tabellenreihe für ganztägige Termine.
 * 
 * @return Tabellenreihe für ganztägige Termine.
 */
var getAllDayRow = function() {
	var allDay = "<tr id='trAllDay'>";

	for (var i = 0; i <= 7; i++) {
		allDay += "<td class='claAllDay'></td>";
	}

	allDay += "</tr>";
	return allDay;
};

/**
 * Baue eine Tabellenreihe im halben stunden Zyklus.
 * 
 * @return Tabellenreihe im halben stunden Zyklus.
 */
var getTimeRows = function() {
	var timeRows = "";
	for (var i = 0; i < 24; i = i + 0.5) {
		timeRows += "<tr class='claTimeFrame'>";

		for (var j = 0; j <= 7; j++) {
			var columnClass = "";
			var columnValue = "";
			if (Number.isInteger(i)) {
				if (j === 0) {
					columnClass = "claFullHour claTimeColumn";
					columnValue = i;
				} else {
					columnClass = "claFullHour";
				}
			} else {
				columnClass = "claHalfHour";
			}
			timeRows += "<td class='" + columnClass + "'>" + columnValue
					+ "</td>";
		}

		timeRows += "</tr>";
	}

	return timeRows;
};

/**
 * Hole alle Termine des Benutzers aus der Datenbank.
 * 
 * @return Termine des Benutzers.
 */
var showUserEvents = function(p_week) {

	$.ajax({
		type : "POST",
		url : "CalendarController",
		dataType : "json",
		data : {
			action : "getEvents",
			week : p_week,
			userid : $("#hidUserId").val()
		},
		success : function(response) {
			alert(response);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Something really bad happened " + textStatus);
			alert(jqXHR.responseText);
		}
	});

	var event = {
		title : "TEST",
		start : "2015-06-06T00:00:00Z",
		end : "2015-06-01T14:00:00Z"
	};

	var eventStart = new Date(event.start);
	var eventEnd = new Date(event.end);

	// Calculate left
	var eventDayNumber = eventStart.getDay();
	var dayWidth = $(".claFullHour:nth-child(2)").width() / 10;
	var left = $(".claTimeFrame > td").width();

	if (eventDayNumber === 0) {
		left += dayWidth * 7;
	} else {
		left += dayWidth * (eventDayNumber - 1);
	}

	// Calculate height
	var height = 60;
	var totalMinutes = 0;

	var eventStartHour = eventStart.getHours();
	var eventEndHour = eventEnd.getHours();

	var eventStartMinute = eventStart.getMinutes();
	var eventEndMinute = eventEnd.getMinutes();

	if (eventEndMinute > eventStartMinute) {
		totalMinutes = ((eventEndHour - eventStartHour) * 60)
				+ (eventEndMinute - eventStartMinute);
	}

	var modHeight = totalMinutes % 30;
	for (var i = 0; i <= totalMinutes - modHeight; i += 30) {
		height += 13;
	}

	height += ((modHeight / 30) * 100);

	// Calculate top
	var top = 76;

	var startHour = eventStart.getHours();
	var startMinute = eventStart.getMinutes();
	// alert("startHour: " + startHour + "\nstartMinute: " + startMinute);

	var startMinutes = (startHour * 60) + startMinute;
	var modTop = startMinutes % 30;

	for (var i = 0; i < startMinutes - modTop; i += 30) {
		top += 13;
	}

	top += ((modTop / 30) * 100);

	$("#calendar").append(
			"<div class='claEvent' style='height: " + height + "px;left: "
					+ left + "px; top: " + top + "px;'></div>");
};

/**
 * Gehe eine Woche zurück.
 */
var getLastWeek = function() {
	var currentWeek = parseInt($("#hidCurrentWeek").val());
	var currentYear = parseInt($("#hidCurrentYear").val());

	if (currentWeek <= 1) {
		currentYear--;
		currentWeek = 53;
	} else {
		currentWeek--;
	}
	buildUserCalendar(currentWeek, currentYear);
};

/**
 * Gehe zur nächsten Woche.
 */
var getNextWeek = function() {
	var currentWeek = parseInt($("#hidCurrentWeek").val());
	var currentYear = parseInt($("#hidCurrentYear").val());

	if (currentWeek >= 53) {
		currentYear++;
		currentWeek = 1;
	} else {
		currentWeek++;
	}

	buildUserCalendar(currentWeek, currentYear);
};

/**
 * Melde den Benutzer ab.
 */
var Logout = function() {
	$.ajax({
		type : "POST",
		url : "CalendarController",
		data : {
			action : "logout"
		}
	});
};

/**
 * Zeige eine leere Termineingabe.
 */
var newEvent = function() {
	openEventWindow(-1);
};

/**
 * PopUp Fenster für eine Termineingabe
 * 
 * @param p_eventId
 */
var openEventWindow = function(p_eventId) {
	// TODO: WENN p_eventId != -1 DANN Event Informationen abfragen.
	settings = "width=750,height=500,top=20,left=20,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no,dependent=no";
	win = window.open("http://localhost:8080/FHDW.WebCalendar.WebUI/Event.jsp",
			"", settings);
	win.focus();
};