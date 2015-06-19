package BLL.Helper;

import BLL.Exceptions.IOException;
import DomainModel.User.User;
import DomainModel.User.UserSecurity;

/**
 * Helper für die Überprüfung eingebener Daten eines User-Models.
 * 
 * @author Frederik Heinrichs
 *
 * @see User
 * @see UserSecurity
 */
public class UserHelper
{	
	/**
	 * 
	 * @param p_user
	 * 
	 * @return
	 * 
	 * @throws IOException 
	 */
	public static boolean checkUserData(User p_user) throws IOException {
		UserHelper.checkUserPassword(p_user.GetUserSecurity().GetPassword());
		UserHelper.checkUserName(p_user.GetUsername());
		UserHelper.checkUserMail(p_user.GetEMail());
		
		return true;
	}
	
	/**
	 * Überprüft die Syntax eines Passwords<br>
	 * 1. Das password darf nicht leer oder null sein
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
	 * Überprüft ob Vorname und Nachname nicht leer sind
	 * 
	 * @param p_firstOrLastname Vorname oder Nachname
	 * 
	 * @return true, wenn Name nicht leer ist
	 * 		   false, wenn Name leer ist
	 */
	public static void checkUserName(String p_username) throws IOException {
		if (p_username == null || p_username.isEmpty()) {
			throw new IOException("Der Eingebenen Benutzername ist leer");
		} 	
	}
	
	
	/**
	 * Überprüft die Syntax der E-Mailadresse<br>
	 * 1. Die E-Mailadresse darf nicht leer oder null sein<br>
	 * 2. Die E-mailadresse muss ein @ und einen . enthalten<br>
	 * 3. Es müssen zeichen vor dem @ Zeichen stehen
	 * 4. Es müssen zeichen vor dem Punkt stehen
	 * @param p_mailadress
	 * 
	 * @throws IOException <br>
	 * 1. Die Eingegebene E-Mailadresse ist leer<br>
	 * 2. Die Eingegebene E-Mailadresse war nicht korrekt
	 */
	public static void checkUserMail(String p_mailadress) throws IOException {		
		p_mailadress.trim();
		int at, dot, len;
		len = p_mailadress.length();
		at = p_mailadress.indexOf('@');
		dot = p_mailadress.lastIndexOf('.');
		
		// p_mailadress nicht angegeben oder nur Leerzeichen, oder kein @ bzw .
		if ( (len == 0) || (at == -1) || (dot == -1) ) {
			throw new IOException("Die Eingegebene E-Mailadresse ist leer");
		}
		
		// keine EMailadresse vor @ Zeichen oder . vor &		
		if ( (at == 0) || (dot < at) )
			throw new IOException("Die Eingegebene E-Mailadresse nicht korrekt");
		
		 // Mindestens zwei Zeichen für die Endung
		if (! (dot+2 < len)) {
			throw new IOException("Die Eingegebene E-Mailadresse war nicht korrekt");
		} 
	}
	
}
