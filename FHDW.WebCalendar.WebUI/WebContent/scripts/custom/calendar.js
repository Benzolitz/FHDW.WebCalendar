$(document).ready(function() {
	{
		getTable(-1);
	}
});

var getTable = function(weeknumber) {
	$.get('CalendarController', {
		weeknumber : weeknumber
	}, function(responseText) {
		$('#calender').text(responseText)
	});
}