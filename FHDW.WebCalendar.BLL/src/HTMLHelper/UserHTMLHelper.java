package HTMLHelper;

import Model.User.User;

public class UserHTMLHelper
{	
	/**
	 * TODO: Kommentar schreiben
	 * @param p_user
	 * @return
	 */
	public static boolean checkUserData(User p_user) {
		if (!UserHTMLHelper.checkUserPassword(p_user.GetUserSecurity().GetPassword())) {
			return false;
		}
		
		if (!UserHTMLHelper.checkUserName(p_user.GetUsername())) {
			return false;
		}
		
		if (!UserHTMLHelper.checkUserName(p_user.GetFirstname())) {
			return false;
		}
		
		if (!UserHTMLHelper.checkUserName(p_user.GetLastname())) {
			return false;
		}
		
		if (!UserHTMLHelper.checkUserMail(p_user.GetEMail())) {
			return false;
		}
		
		if (!UserHTMLHelper.checkPhonenNumber(p_user.GetPhonenumber())) {
			return false;
		}
		
		if (!UserHTMLHelper.checkSecurityAnswer(p_user.GetUserSecurity().GetSecurityAnswer())) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Überprüft die Syntax eines Passwords<br>
	 * Passwörter dürfen nicht leer sein und müssen aus mindestens sechs Zeichen bestehen.
	 * 
	 * @param p_password
	 * 
	 * @return true, wenn p_password eine korrekte Syntax bestizt<br>
	 * 		   false, wenn p_password eine falsche Syntax bestizt
	 */
	public static boolean checkUserPassword(String p_password) {
		if (p_password.isEmpty()) {
			return false;
		} else {
			if (p_password.length() < 6) {
				return false;
			}	
			return true;
		}
	}
	
	/**
	 * Überprüft die Syntax eines Benutzernamen<br>
	 * Benutzernamen dürfen nur aus Zeichen und Zahlen bestehen <br>
	 * und maximal aus 12 Zeichen
	 * 
	 * @param p_username
	 * 
	 * @return true, wenn p_username eine korrekte Syntax bestizt<br>
	 * 		   false, wenn p_username eine falsche Syntax bestizt
	 */
	public static boolean checkUserName(String p_username) {
		if (p_username.isEmpty()) {
			return false;
		} else {
			if (p_username.length() > 12) {
				return false;
			}
			
//			Pattern pattern = Pattern.compile(p_username);
//		    Matcher matcher = pattern.matcher("[0-9_a-zA-F]");
//			TODO:
//			if (!matcher.matches()) {
//				return false;
//			}
			
			return true;
		}	
	}
	
	/**
	 * Überprüft die Syntax der Vor- und Nachnamen<br>
	 * Benutzernamen dürfen nur aus Zeichen und Zahlen bestehen <br>
	 * und maximal aus 12 Zeichen
	 * 
	 * @param p_firstOrLastname Vorname oder Nachname
	 * 
	 * @return true, wenn p_username eine korrekte Syntax bestizt<br>
	 * 		   false, wenn p_username eine falsche Syntax bestizt
	 */
	public static boolean checkName(String p_firstOrLastname) {
		if (p_firstOrLastname.isEmpty()) {
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}
	
	/**
	 * TODO: Regeln aufstellen
	 * @param p_mailadress
	 * @return
	 */
	public static boolean checkUserMail(String p_mailadress) {
		if (p_mailadress.isEmpty()) {
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}
	
	/**
	 * TODO: Regeln aufstellen
	 * @param p_mailadress
	 * @return
	 */
	public static boolean checkPhonenNumber(String p_mailadress) {
		if (p_mailadress.isEmpty()) {
			
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}
	
	/**
	 * TODO: Regeln aufstellen
	 * @param p_answer
	 * @return
	 */
	public static boolean checkSecurityAnswer(String p_answer) {
		if (p_answer.isEmpty()) {
			
			return false;
		} else {
			// TODO: Gibt es Regeln die beim Password eingehalten werden müssen?
			
			return true;
		}	
	}
}
