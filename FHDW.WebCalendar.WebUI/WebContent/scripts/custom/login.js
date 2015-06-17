/**
 * @author Lucas Engel
 * 
 *         JavaScript-Logik für den Login. 
 * 
 * @see Login.jsp
 */

$(document).ready(function() {
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