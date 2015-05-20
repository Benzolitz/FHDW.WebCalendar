$(document).ready(function() {

	DisableSubmitButton();

	$(".claRequiredField").keyup(test);
	$(".claRequiredField").focusout(SetHintForEmptyField);

	$("#btnReset").click(ResetTextBoxBorders);
});

var DisableSubmitButton = function() {
	$("#btnSubmit").removeClass("claEnabledButton");
	$("#btnSubmit").addClass("claDisabledButton");
}

var EnableSubmitButton = function() {
	$("#btnSubmit").removeClass("claDisabledButton");
	$("#btnSubmit").addClass("claEnabledButton");
}

var ResetTextBoxBorders = function() {
	$(".claRequiredField").each(function() {
		$(this).css("border", "1px solid #ABADB3");
	});
}

var SetHintForEmptyField = function() {
	if ($(this).val() === "") {
		$(this).css("border", "1px solid #F00")
	} else {
		$(this).css("border", "1px solid #ABADB3")
	}
}

var test = function() {
	if (RequiredInformationGiven()) {
		$("#btnSubmit").removeAttr("disabled");
		EnableSubmitButton();
	} else {
		$("#btnSubmit").attr("disabled");
		DisableSubmitButton();
	}
}

var RequiredInformationGiven = function() {
	var RequiredFieldsSet = true;

	$(".claRequiredField").each(function() {
		if ($(this).val() === "") {
			RequiredFieldsSet = false;
		}
	});

	return RequiredFieldsSet;
}
