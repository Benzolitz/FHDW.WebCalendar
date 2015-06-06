package Services;

import java.sql.SQLException;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHelper;

public class PasswordResetService extends BaseService
{	
	public PasswordResetService() {
		// nothing to init
	}
	
	/**
	 * 1. Überprüft ob das neue password korrekt eingebenen wurde <br>
	 * 2. Überprüft ob das Alte password korrekt war<br>
	 * 3. Überprüft ob die Eingegebene SecurityAnswer richtig war<br>
	 * 
	 * @param p_userId
	 * @param p_oldPassword
	 * @param p_newPassword
	 * @param p_securityAnswer
	 * 
	 * @return true, wenn alles korrekt war und das password geändert wurde <br>
	 * false, wenn die SecurityAnswer falsch war
	 * 
	 * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 * @throws IOException wenn die eingegeben Passwörter falsch waren <br> @link {@link UserHelper#checkUserPassword(String)}
	 * @throws NotFound <br>wenn keine Benutzerantwort in der Datenbank gefunden werden konnte<br>
	 * wenn das Password zu einem Benutzer nicht gefunden werden konnte
	 * 
	 * @see LoginService#CheckUserPassword(int, String)
	 * @see RegistrationService#checkSecurityAnswer(int, String)
	 */
	public boolean changeUserPasword(int p_userId, String p_oldPassword, String p_newPassword, String p_securityAnswer) throws NotFound, IOException, DatabaseException {	
		UserHelper.checkUserPassword(p_newPassword);
		
		// Check old userpassword (SecurityCheck)
		if (!new LoginService().CheckUserPassword(p_userId, p_oldPassword)){// throws NotFound, IOException, DatabaseException
			throw new IOException("Das Eingebene Password war nicht korrekt");
		} 
		
		// Check securityAnswer (SecurityCheck)
		if(checkSecurityAnswer(p_userId, p_securityAnswer)) { // throws NotFound, DatabaseException
			try
			{
				GetRepo().ResetPassword(p_userId, p_newPassword);
				return true;
			}
			catch (SQLException e)
			{
				// TODO: SQLException Loggen
				// TODO: Fehlermeldung Benutzerfreundlich durchreichen
				throw new DatabaseException("Ein unbekannter Fehler ist aufgetreten", e);
			}	
		} else {
			return false;
		}	
	}
	
	/**
	 * Überprüft eine Eingebene SecurityAnswer eines Benutzers mit der aus der Datenbank<br>
	 * Groß und Kleinschreibung wird nicht beachtet!
	 * 
	 * @param p_userId
	 * @param p_securityAnswer
	 * 
	 * @return true wenn die eingebene antwort korrekt war<br>
	 *         false wenn die eingebene antwort nicht korrekt war
	 * 
	 * @throws NotFound wenn keine Benutzerantwort in der Datenbank gefunden werden konnte
	 * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 */
	private boolean checkSecurityAnswer(int p_userId, String p_securityAnswer) throws NotFound, DatabaseException {	
		try
		{
			String user_answer = GetRepo().GetSecurityAnswer(p_userId);
			
			if (user_answer == null || user_answer.isEmpty()) {
				// TODO: Fehlermeldung Benutzerfreundlich durchreichen
				throw new NotFound("Es wurde keine Benutzerantwort in der Datenbank gefunden");
			} else {		
				if (user_answer.equalsIgnoreCase(p_securityAnswer)) {
					return true;
				} else {
					return false;
				}
			}
		}
		catch (SQLException e)
		{
			// TODO: SQLException Loggen
			// TODO: Fehlermeldung Benutzerfreundlich durchreichen
			throw new DatabaseException("Ein unbekannter Fehler ist aufgetreten", e);
		}
	}

}
