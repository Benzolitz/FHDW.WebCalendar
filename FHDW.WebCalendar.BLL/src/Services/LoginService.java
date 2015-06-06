package Services;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHelper;
import Repository.JDBC.WebCalendarRepo;

/**
 * @author Frederik Heinrichs
 * 
 * Buisness Logik für den Loginbereich in der Applikation
 * 
 * @see WebCalendarRepo
 * @see UserHelper
 * @see UserService
 */
public class LoginService extends BaseService
{
	private UserService userService;
	
	public LoginService() {
		//nothing to init
	}
	
	
	/**
	 * @return the userService
	 */
	public UserService GetUserService()
	{
		if (this.userService == null) {
			this.userService = new UserService();
		}
		return this.userService;
	}
	
	
	/**
	 * Lade den passenden Benutzer zu einem Benutzernamen oder einer E-Mailadresse <br>
	 * und vergleiche das eingebene Password mit dem aus der Datenbank
	 * 
	 * @param p_usernameOrEMail
	 * @param p_password
	 * 
	 * @return userId wenn das Password korrekt ist<br>
	 *         -1 , wenn das Password nicht korrekt ist<br>
	 *         
	 * @throws NotFound wenn der Benutzer nicht vorhanden ist
	 * @throws IOException wenn das Eingebene Password oder der Benutzername nicht korrekt eingegeben wurden
	 * @throws DatabaseException  wenn ein unbekannter Fehler in der Datenbank aufgetaucht ist
	 * 
	 *  @see LoginService#CheckUserPassword(int, String)
	 *  @see UserHelper#checkUserName(String)
	 */
	public int CheckLoginData(String p_usernameOrEMail, String p_password) throws IOException, NotFound, DatabaseException {		
		//Check userName
		int userId = GetUserService().GetUserId(p_usernameOrEMail); // throws IOException, Notfound
		
		//Check UserPassword
		return CheckUserPassword(userId, p_password) ? userId : -1; // throws IOExceptions
	}
	
	/**
	 * Überprüfe das eingebene Password eines Benutzers
	 * 
	 * @param p_userId
	 * @param p_password
	 * 
	 * @return true wenn das eingebene Password korrekt war<br>
	 *         false wenn das eingebenen Password nicht mit dem aus der Datenbank übereinstimmt
	 * 
	 * @throws NotFound wenn das password zu einem benutzer nicht gefunden werden konnte
	 * @throws IOException wenn das eingebenen Password nicht korrekt war
	 * @throws DatabaseException 
	 * 
	 * @see UserHelper#checkUserPassword(String)
	 */
	protected boolean CheckUserPassword(int p_userId, String p_password) throws NotFound, IOException, DatabaseException {
		UserHelper.checkUserPassword(p_password); // throws IOException
		String userPasswordToCompare = GetUserService().GetUserPassword(p_userId); // throws NotFound, DatabaseException
		if (userPasswordToCompare.equals(p_password)) {
			return true;
		} else {
			return false;
		}
	}
	
}