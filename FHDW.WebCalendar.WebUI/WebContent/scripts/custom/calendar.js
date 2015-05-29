$(document).ready(function() {
	{
		buildUserCalendar(-1, new Date().getFullYear());
	}
});

var buildUserCalendar = function(p_week, p_year) {
	var week = checkWeek(p_week);

	var calendarTable = getUserTable(week, p_year);
	var calendarEvents = getUserEvents(week, p_year);

	$("#calendar").html(calendarTable);
};

var checkWeek = function(p_week) {
	var date = new Date();
	return p_week === -1 ? 22 : p_week;
};

var getUserTable = function() {
	var table = "<table id='tabCalendar'>";

	table += getTableHead();
	table += getTableBody();

	table += "</table>";

	return table;
};

var getTableHead = function() {
	var tableHead = "<thead><tr>";

	for (var i = 0; i <= 7; i++) {
		if (i == 0) {
			tableHead += "<th id='tdTime'></th>";
		} else {
			tableHead += "<th class='claHead'>" + getDate(i - 1) + "</th>";
		}
	}

	tableHead += "</thead></tr>";
	return tableHead;

};

var getDate = function(p_additionalDays) {
	var currentDate = new Date(new Date().getTime()
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

var getTableBody = function() {
	var tableBody = "<tbody>";

	tableBody += getAllDayRow();
	tableBody += getTimeRows();

	tableBody += "</tbody>";
	return tableBody;
};

var getAllDayRow = function() {
	var allDay = "<tr id='trAllDay'>";

	for (var i = 0; i <= 7; i++) {
		allDay += "<td class='claAllDay'></td>";
	}

	allDay += "</tr>";
	return allDay;
};

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

var getUserEvents = function() {
	var events = "";

	return events;
};

var getLastWeek = function() {
	var currentWeek = parseInt($("#hidCurrentWeek").val());
	var currentYear = parseInt($("#hidCurrentYear").val());

	if (currentWeek <= 1) {
		currentYear--;
	}

};

var getNextWeek = function() {
	var currentWeek = parseInt($("#hidCurrentWeek").val());
	var currentYear = parseInt($("#hidCurrentYear").val());

	if (currentWeek >= 53) {
		currentYear++;
	}
};

var Logout = function() {
	$.ajax({
		type : "POST",
		url : "CalendarController",
		data : {
			action : "logout"
		}
	});
};

var newEvent = function() {
	openEventWindow(-1);
};

var openEventWindow = function(p_eventId) {
	// TODO: WENN p_eventId != -1 DANN Event Informationen abfragen.
	settings = "width=750,height=500,top=20,left=20,scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no,dependent=no";
	win = window.open("http://localhost:8080/FHDW.WebCalendar.WebUI/Event.jsp",
			"", settings);
	win.focus();
};