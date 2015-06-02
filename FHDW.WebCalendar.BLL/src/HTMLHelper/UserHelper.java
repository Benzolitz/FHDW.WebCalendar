package HTMLHelper;

import Exceptions.IOException;
import Model.User.User;
import Model.User.UserSecurity;

/**
 * @author Frederik Heinrichs
 *
 * Helper für die überprüfung eingebener Daten für User objekte
 * 
 * @see User
 * @see UserSecurity
 */
public class UserHelper
{	
	/**
	 * TODO: Kommentar schreiben
	 * @param p_user
	 * @return
	 * @throws IOException 
	 */
	public static boolean checkUserData(User p_user) throws IOException {
		UserHelper.checkUserPassword(p_user.GetUserSecurity().GetPassword());
		UserHelper.checkUserName(p_user.GetUsername());

		
		if (!UserHelper.checkUserMail(p_user.GetEMail())) {
			return false;
		}
		
		if (!UserHelper.checkPhonenNumber(p_user.GetPhonenumber())) {
			return false;
		}
				
		return true;
	}
	
	/**
	 * Überprüft die Syntax eines Passwords<br>
	 * 
	 * @param p_password
	 * 
	 * @throws IOException wenn das eingebene password leer war
	 */
	public static void checkUserPassword(String p_password) throws IOException {
		if (p_password == null  || p_password.isEmpty()) {
			throw new IOException("Das eingebenen Password war leer");
		} 
	}
	
	/**
	 * Überprüft die Syntax eines Benutzernamen<br>
	 * 
	 * @param p_username
	 * 
	 * @throws IOException IOException wenn der eingebene user_name leer war
	 */
	public static void checkUserName(String p_username) throws IOException {
		if (p_username == null || p_username.isEmpty()) {
			throw new IOException("Der Eingebenen Benutzername ist leer");
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
	
}
