package Services;

import java.sql.SQLException;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Helper.UserHelper;

public class PasswordResetService extends BaseService
{	
	public PasswordResetService() {
		// nothing to init
	}
	

	/**
	 * Ändert das Password zu einem Benutzer
	 * 
	 * @param p_userId
	 * @param p_newPassword
	 * 
	 * @return true wenn das Password geändert wurde
	 * 
	 * @throws IOException wenn das eingegeben Password falsch ist <br>
	 * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 * 
	 * @see UserHelper#checkUserPassword(String)
	 */
	public boolean changeUserPasword(int p_userId, String p_newPassword) throws DatabaseException, IOException {	
		UserHelper.checkUserPassword(p_newPassword);
		
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
	}
	
	/**
	 * Gibt die Security Frage für einen Benutzer
	 * 
	 * @param p_userId
	 * 
	 * @return Die Frage als String
	 * 
	 * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 */
	public String GetUserSecurityQuestion(int p_userId) throws DatabaseException {	
		try
		{
			return GetRepo().GetSecurityQuestion(p_userId);
		}
		catch (SQLException e)
		{
			// TODO: SQLException Loggen
			// TODO: Fehlermeldung Benutzerfreundlich durchreichen
			throw new DatabaseException("Ein unbekannter Fehler ist aufgetreten", e);
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
	public boolean checkSecurityAnswer(int p_userId, String p_securityAnswer) throws NotFound, DatabaseException {	
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
