$(document).ready(function() {
	{
		$("#calender").css({
			width : $(document).width() - 200 + "px",
			height : $(document).height() - 50 + "px"
		});

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