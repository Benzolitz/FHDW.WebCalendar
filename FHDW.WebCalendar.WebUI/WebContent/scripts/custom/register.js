var samePasswords = false;
$(document).ready(function() {
	DisableSubmitButton();

	$(".claRequiredField").keyup(test);
	$(".claRequiredField").focusout(checkForEmptyField);

	$("#txtPassword").keyup(checkPasswords);
	$("#txtPasswordCheck").keyup(checkPasswords);

	$("#btnReset").click(resetTextBoxBorders);
});

var checkPasswords = function() {
	if ($("#txtPassword").val() === $("#txtPasswordCheck").val()) {
		samePasswords = true;
		$("#txtPassword").removeClass("claRequiredFieldEmpty");
		$("#txtPasswordCheck").removeClass("claRequiredFieldEmpty");
	} else {
		samePasswords = false;
		$("#txtPassword").addClass("claRequiredFieldEmpty");
		$("#txtPasswordCheck").addClass("claRequiredFieldEmpty");
	}
};

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
var checkForEmptyField = function() {
	if ($(this).val() === "") {
		$(this).addClass("claRequiredFieldEmpty");
	} else {
		$(this).removeClass("claRequiredFieldEmpty");
	}
};
var test = function() {
	if (RequiredInformationGiven() && samePasswords) {
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
