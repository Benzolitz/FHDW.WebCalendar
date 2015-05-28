$(document).ready(function() {
	{
		checkCookie();
		buildUserCalendar(-1, new Date().getFullYear());
	}
});

var checkCookie = function() {
	var cookieValue = getCalendarCookieValue();
	var cookieValues = cookieValue.split("&");

	for (var i = 0; i < cookieValues.length; i++) {
		var value = cookieValues[i].split("=");

		if (value[0].toLowerCase() === "userid") {
			$("#ckiUserId").val(value[1]);
		}
	}
};

var getCalendarCookieValue = function() {
	var calendarCookie = null;
	var cookieName = "\"FHDW.WebCalendar=";
	var cookies = document.cookie.split(';');
	var cookieValue = "";

	for (var i = 0; i < cookies.length; i++) {
		if (cookies[i].indexOf(cookieName)) {
			calendarCookie = cookies[i];
		}
	}

	cookieValue = calendarCookie.substring(cookieName.length);
	cookieValue = cookieValue.substring(0, cookieValue.length - 1);

	return cookieValue;
};

var buildUserCalendar = function(p_week, p_year) {
	var week = checkWeek(p_week);

	var calendarTable = getUserTable(week, p_year);
	var calendarEvents = getUserEvents(week, p_year);

	$("#calendarBody").html(calendarTable);
};

var checkWeek = function(p_week) {
	var date = new Date();
	return p_week === -1 ? 22 : p_week;
};

var getUserTable = function() {
	var table = "<table>";

	table += getTableHead();
	table += getTableBody();

	table = "</table>";

	return table;
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