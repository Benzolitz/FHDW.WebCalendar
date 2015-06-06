package Services;

import java.sql.SQLException;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;

public class PasswordResetService extends BaseService
{	
	public PasswordResetService() {
		// nothing to init
	}
	
	/**
	 * �berpr�ft ob das neue password korrekt eingebenen wurde (SecurityCheck) <br>
	 * und ob das alte Password mit dem aus der Datenbank �bereinstimmt
	 * 
	 * @param p_userId
	 * @param p_oldPassword
	 * @param p_newPassword
	 * @param p_securityAnswer
	 * 
	 * @return true <br>
	 * 		   false
	 * 
	 * @throws DatabaseException
	 * @throws NotFound
	 * 
	 * @see LoginService#CheckUserPassword(int, String)
	 * @see RegistrationService#checkSecurityAnswer(int, String)
	 */
	public boolean changeUserPasword(int p_userId, String p_oldPassword, String p_newPassword, String p_securityAnswer) throws NotFound, IOException, DatabaseException {	
		// Check old userpassword (SecurityCheck)
		if (new LoginService().CheckUserPassword(p_userId, p_oldPassword)){// throws NotFound, IOException
			throw new IOException("Das Eingebene Password war nicht korrekt");
		} 
		
		// Check securityAnswer (SecurityCheck)
		if(checkSecurityAnswer(p_userId, p_securityAnswer)) { // throws NotFound
			try
			{
				GetRepo().ResetPassword(p_userId, p_newPassword);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			// TODO: ein return wert w�re gut boolean damit ich pr�fen kann ob es geklappt hat oder nicht
			return true;		
		} else {
			return false;
		}	
	}
	
	/**
	 * �berpr�ft eine Eingebene SecurityAnswer eines Benutzers mit der aus der Datenbank<br>
	 * Gro� und Kleinschreibung wird nicht beachtet!
	 * 
	 * @param p_userId
	 * @param p_securityAnswer
	 * 
	 * @return true wenn die eingebene antwort korrekt war<br>
	 *         false wenn die eingebene antwort nicht korrekt war
	 * 
	 * @throws NotFound wenn keine Benutzerantwort in der Datenbank gefunden werden konnte
	 */
	private boolean checkSecurityAnswer(int p_userId, String p_securityAnswer) throws NotFound {
		String user_answer = "";
		try
		{
			user_answer = GetRepo().GetSecurityAnswer(p_userId);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if (user_answer.isEmpty()) {
			throw new NotFound("Es wurde keine Benutzerantwort in der Datenbank gefunden");
		} else {		
			if (user_answer.equalsIgnoreCase(p_securityAnswer)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	
}
