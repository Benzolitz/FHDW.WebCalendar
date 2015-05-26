package Helper.User;

import Model.User.User;

public class UserHelper
{	
	// TODO: Was sind Pflichtfelder?
	
	public static boolean checkUserData(User p_user) {
		if (!UserHelper.checkUserPassword(p_user.getUserSecurity().GetPassword())) {
			return false;
		}
		
		if (!UserHelper.checkUserName(p_user.GetUsername())) {
			return false;
		}
		
		if (!UserHelper.checkUserName(p_user.getFirstname())) {
			return false;
		}
		
		if (!UserHelper.checkUserName(p_user.getLastname())) {
			return false;
		}
		
		if (!UserHelper.checkUserMail(p_user.GetEMail())) {
			return false;
		}
		
		if (!UserHelper.checkPhonenNumber(p_user.getPhonenumber())) {
			return false;
		}
				
		if (!UserHelper.checkSecurityQuestion(p_user.getUserSecurity().GetSecurityQuestion())) {
			return false;
		}
		
		if (!UserHelper.checkSecurityAnswer(p_user.getUserSecurity().GetSecurityAnswer())) {
			return false;
		}
		
		return true;
	}
	
	public static boolean checkUserPassword(String p_password) {
		if (p_password.isEmpty()) {
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}
	}
	
	public static boolean checkUserName(String p_username) {
		if (p_username.isEmpty()) {
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}
	
	public static boolean checkUserMail(String p_mailadress) {
		if (p_mailadress.isEmpty()) {
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}
	
	public static boolean checkPhonenNumber(String p_mailadress) {
		if (p_mailadress.isEmpty()) {
			
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}

	public static boolean checkSecurityQuestion(String p_question) {
		if (p_question.isEmpty()) {
			
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}
	
	public static boolean checkSecurityAnswer(String p_answer) {
		if (p_answer.isEmpty()) {
			
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}

	
	
	
}
