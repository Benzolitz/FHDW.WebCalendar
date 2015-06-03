$(document).ready(function()
{
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
var buildUserCalendar = function(p_week, p_year)
{
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
var checkWeek = function(p_week)
{
	return p_week === -1 ? new Date().getWeekNumber() : p_week;
};

/**
 * Hole die aktuelle Kalenderwoche.
 * 
 * @source http://stackoverflow.com/a/6117889
 * @return Gibt die aktuelle Kalenderwoche zurück.
 */
Date.prototype.getWeekNumber = function()
{
	var date = new Date(+this);
	date.setHours(0, 0, 0);
	date.setDate(date.getDate() + 4 - (date.getDay() || 7));
	return Math.ceil((((date - new Date(date.getFullYear(), 0, 1)) / 8.64e7) + 1) / 7);
};

/**
 * Baue die Wochentabelle auf.
 * 
 * @param p_week
 * @param p_year
 * @return Gibt eine aufgebaute Tabelle zurück
 */
var buildCalendar = function(p_week, p_year)
{
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
var getTableHead = function(p_week, p_year)
{
	var firstDayOfWeek = getDateOfISOWeek(p_week, p_year);
	var tableHead = "<thead><tr>";

	for (var i = 0; i <= 7; i++)
	{
		if (i == 0)
		{
			tableHead += "<th id='tdTime'></th>";
		}
		else
		{
			tableHead += "<th class='claHead'>" + getDate(firstDayOfWeek, i - 1) + "</th>";
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
function getDateOfISOWeek(p_week, p_year)
{
	var simple = new Date(p_year, 0, 1 + (p_week - 1) * 7);
	var dow = simple.getDay();
	var ISOweekStart = simple;
	if (dow <= 4)
	{
		ISOweekStart.setDate(simple.getDate() - simple.getDay() + 1);
	}
	else
	{
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
var getDate = function(p_firstDayOfWeek, p_additionalDays)
{
	var currentDate = new Date(p_firstDayOfWeek.getTime() + (p_additionalDays * 24 * 60 * 60 * 1000));

	var weekDay = currentDate.getDay();
	var weekDays = new Array("So.", "Mo.", "Di.", "Mi.", "Do.", "Fr.", "Sa.")

	var day = currentDate.getDate();
	if (day < 10)
	{
		day = "0" + day
	}

	var month = currentDate.getMonth() + 1;
	if (month < 10)
	{
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
var getTableBody = function()
{
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
var getAllDayRow = function()
{
	var allDay = "<tr id='trAllDay'>";

	for (var i = 0; i <= 7; i++)
	{
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
var getTimeRows = function()
{
	var timeRows = "";
	for (var i = 0; i < 24; i = i + 0.5)
	{
		timeRows += "<tr class='claTimeFrame'>";

		for (var j = 0; j <= 7; j++)
		{
			var columnClass = "";
			var columnValue = "";
			var columnId = "";
			if (Number.isInteger(i))
			{
				if (j === 0)
				{
					columnClass = "claFullHour claTimeColumn";
					columnValue = i;
					if (i === 0)
					{
						columnId = "timeWidthIndicator";
					}
				}
				else
				{
					columnClass = "claFullHour";
					if (i === 0 && j === 1)
					{

						columnId = "dayWidthIndicator";
					}
				}
			}
			else
			{
				columnClass = "claHalfHour";
			}
			timeRows += "<td id='" + columnId + "' class='" + columnClass + "'>" + columnValue + "</td>";
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
var showUserEvents = function(p_week)
{
	$.ajax(
	{
	type : "POST",
	url : "CalendarController",
	dataType : "json",
	data :
	{
	action : "getEvents",
	week : p_week,
	userid : $("#hidUserId").val()
	},
	success : function(response)
	{
		var eventList = response;
		for (var i = 0; i < eventList.length; i++)
		{
			var event = eventList[i];
			var start = event.startTime;
			var end = event.endTime;
			var eventStart = new Date(start.year, start.month - 1, start.dayOfMonth, start.hourOfDay, start.minute, 0);
			var eventEnd = new Date(end.year, end.month - 1, end.dayOfMonth, end.hourOfDay, end.minute, 0);

			var eventMarginLeft = getLeftMargin(eventStart.getDay());
			var eventMarginTop = getTopMargin(eventStart.getHours(), eventStart.getMinutes());
			var eventHeight = getEventHeight(eventStart.getHours(), eventStart.getMinutes(), eventEnd.getHours(), eventEnd.getMinutes());

			$("#calendar").append("<div class='claEvent' style='height: " + eventHeight + "px;left: " + eventMarginLeft + "px; top: " + eventMarginTop + "px;'><a onClick='openEventWindow(" + event.id + ")'>" + event.title + "</a></div>");

		}
	},
	error : function(jqXHR, textStatus, errorThrown)
	{
		alert(jqXHR.responseText);
	}
	});
};

var getLeftMargin = function(p_eventDayNumber)
{
	var dayWidth = $(".claFullHour:nth-child(2)").width();
	var left = $(".claTimeColumn").width() + 15;

	left += p_eventDayNumber === 0 ? dayWidth * 7 : dayWidth * (p_eventDayNumber - 1);

	return left;
};

var getTopMargin = function(p_eventStartHour, p_eventStartMinute)
{
	var top = 76; // 76px is the height of the headers.

	var startMinutes = (p_eventStartHour * 60) + p_eventStartMinute;
	var modTop = startMinutes % 30;

	for (var i = 0; i < startMinutes - modTop; i += 30)
	{
		top += 14;
	}

	top += 14 * (modTop / 30);

	return top;
};

var getEventHeight = function(p_eventStartHour, p_eventStartMinute, p_eventEndHour, p_eventEndMinute)
{
	var height = 0;
	var totalMinutes = 0;

	if (p_eventEndMinute >= p_eventStartMinute)
	{
		totalMinutes = ((p_eventEndHour - p_eventStartHour) * 60) + (p_eventEndMinute - p_eventStartMinute);
	}
	else
	{
		totalMinutes = ((p_eventEndHour - p_eventStartHour) * 60) - (p_eventStartMinute - p_eventEndMinute);
	}

	var modHeight = totalMinutes % 30;
	for (var i = 0; i < totalMinutes - modHeight; i += 30)
	{
		height += 14;
	}

	height += 14 * (modHeight / 30);

	return height;
};

/**
 * Gehe eine Woche zurück.
 */
var getLastWeek = function()
{
	var currentWeek = parseInt($("#hidCurrentWeek").val());
	var currentYear = parseInt($("#hidCurrentYear").val());

	if (currentWeek <= 1)
	{
		currentYear--;
		currentWeek = 53;
	}
	else
	{
		currentWeek--;
	}
	buildUserCalendar(currentWeek, currentYear);
};

/**
 * Gehe zur nächsten Woche.
 */
var getNextWeek = function()
{
	var currentWeek = parseInt($("#hidCurrentWeek").val());
	var currentYear = parseInt($("#hidCurrentYear").val());

	if (currentWeek >= 53)
	{
		currentYear++;
		currentWeek = 1;
	}
	else
	{
		currentWeek++;
	}

	buildUserCalendar(currentWeek, currentYear);
};

/**
 * Melde den Benutzer ab.
 */
var Logout = function()
{
	$.ajax(
	{
	type : "POST",
	url : "CalendarController",
	data :
	{
		action : "logout"
	}
	});
};

/**
 * PopUp Fenster für eine Termineingabe
 * 
 * @param p_eventId
 */
var openEventWindow = function(p_eventId)
{
	settings = "width=750,height=500,top=20,left=20,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no,dependent=no";
	win = window.open("http://localhost:8080/FHDW.WebCalendar.WebUI/Event.jsp?id=" + p_eventId, "", settings);
	win.focus();
};