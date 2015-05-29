$(document).ready(function() {
	setCharatersLeft(parseInt($("#txaEventComment").attr("maxlength")));
	
	
	$("#txaEventComment").keyup(function() {
		var maxLength = $("#txaEventComment").attr("maxlength");
		var charsLeft = parseInt(maxLength) - $("#txaEventComment").val().length;
		
		setCharatersLeft(charsLeft);
	});
});

var setCharatersLeft = function(p_charCount) {
	$("#spaChars").text(p_charCount);
}