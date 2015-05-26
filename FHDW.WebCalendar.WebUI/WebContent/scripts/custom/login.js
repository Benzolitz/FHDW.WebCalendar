$(document).ready(function() {
	$("#btnSubmit").click(function() {
		$.ajax({
			type : "POST",
			url : "LoginController",
			data : {
				username : $("txtUsername").val(),
				password : $("txtPassword").val()
			}
		});
	});

	$("#btnRegister").click(function() {
		var parameter = "";
		var username = $("#txtUsername").val();

		if (username != "") {
			parameter = "?username=" + username;
		}

		window.location.replace("Register.jsp" + parameter);
	});
	
	if(document.getElementById("divHint") !== null)
	{
		$("#divLogin").animate({height : $("#divLogin").height() + 50});
	}
	
});