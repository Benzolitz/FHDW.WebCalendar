$(document).ready(function() {
	DisableSubmitButton();

	$(".claRequiredField").keyup(test);
	$(".claRequiredField").focusout(SetHintForEmptyField);

	$("#btnReset").click(resetTextBoxBorders);
});

var DisableSubmitButton = function() {
	$("#btnSubmit").attr("disabled");
	$("#btnSubmit").removeClass("claEnabledButton");
	$("#btnSubmit").addClass("claDisabledButton");
};
var EnableSubmitButton = function() {
	$("#btnSubmit").removeAttr("disabled");
	$("#btnSubmit").removeClass("claDisabledButton");
	$("#btnSubmit").addClass("claEnabledButton");
};
var resetTextBoxBorders = function() {
	$(".claRequiredField").each(function() {
		$(this).css("border", "none");
	});

	DisableSubmitButton();
};
var SetHintForEmptyField = function() {
	if ($(this).val() === "") {
		$(this).addClass("claRequiredFieldEmpty");
	} else {
		$(this).removeClass("claRequiredFieldEmpty");
	}
};
var test = function() {
	if (RequiredInformationGiven()) {
		EnableSubmitButton();
	} else {
		DisableSubmitButton();
	}
};
var RequiredInformationGiven = function() {
	var RequiredFieldsSet = true;

	$(".claRequiredField").each(function() {
		if ($(this).val() === "") {
			RequiredFieldsSet = false;
		}
	});

	return RequiredFieldsSet;
};