$(document).ready(function() {
	{
		getTable(-1);
	}
});

var getTable = function(weeknumber) {
	
}

var Logout = function() {
	$.ajax({
		type : "POST",
		url : "CalendarController",
		data : {
			action : "logout"
		}
	});
};