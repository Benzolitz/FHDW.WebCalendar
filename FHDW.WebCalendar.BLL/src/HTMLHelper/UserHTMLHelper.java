package HTMLHelper;

import Exceptions.IOException;
import Model.User.User;
import Model.User.UserSecurity;

/**
 * @author Frederik Heinrichs
 *
 * HTMLHelper für die überprüfung eingebener Daten für User objekte
 * 
 * @see User
 * @see UserSecurity
 */
public class UserHTMLHelper
{	
	/**
	 * TODO: Kommentar schreiben
	 * @param p_user
	 * @return
	 * @throws IOException 
	 */
	public static boolean checkUserData(User p_user) throws IOException {
		UserHTMLHelper.checkUserPassword(p_user.GetUserSecurity().GetPassword());
		UserHTMLHelper.checkUserName(p_user.GetUsername());

		
		if (!UserHTMLHelper.checkUserMail(p_user.GetEMail())) {
			return false;
		}
		
		if (!UserHTMLHelper.checkPhonenNumber(p_user.GetPhonenumber())) {
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
	 * @throws IOException wenn das eingebene password leer war oder kleiner als 6 Zeichen
	 */
	public static void checkUserPassword(String p_password) throws IOException {
		if (p_password.isEmpty()) {
			throw new IOException("Das eingebenen Password war leer");
		} else {
			if (p_password.length() < 6) {
				throw new IOException("Das eingebenen Password war zu lang");
			}	
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
	 * @throws IOException 
	 */
	public static void checkUserName(String p_username) throws IOException {
		if (p_username.isEmpty()) {
			throw new IOException("Der Eingebenen Benutzername ist leer");
		} else {
			if (p_username.length() > 12) {
				throw new IOException("Der Eingebenen Benutzername hat mehr als 12 Zeichen");
			}
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
