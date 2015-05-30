package Services;

import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHTMLHelper;
import Repository.JDBC.WebCalendarRepo;

/**
 * @author Frederik Heinrichs
 * 
 * Buisness Logik für den Loginbereich in der Applikation
 * 
 * @see WebCalendarRepo
 * @see UserHTMLHelper
 * @see UserService
 */
public class LoginService extends BaseService
{
	public LoginService() {
		//nothing to init
	}
	
	
	/**
	 * Vergleiche das eingebene Password eines Benutzers mit dem aus der Datenbank
	 * 
	 * @param p_username
	 * @param p_password
	 * 
	 * @return userId wenn das Password korrekt ist<br>
	 *         -1 , wenn das Password nicht korrekt ist<br>
	 *         
	 * @throws NotFound wenn der Benutzer nicht vorhanden ist
	 * @throws IOException wenn das Eingebene Password oder der Benutzername nicht den RegelEntsprechen
	 * 
	 *  @see UserHTMLHelper#checkUserPassword(String)
	 *  @see UserHTMLHelper#checkUserName(String)
	 */
	public int CheckLoginData(String p_username, String p_password) throws IOException, NotFound {		
		//Check userName
		UserService userService = new UserService();
		int userId = userService.GetUserId(p_username); // throws IOException, Notfound
		
		//Check UserPassword
		return checkUserPassword(userId, p_password) ? userId : -1; // throws IOExceptions
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
	 * 
	 * @see UserHTMLHelper#checkUserPassword(String)
	 */
	protected boolean checkUserPassword(int p_userId, String p_password) throws NotFound, IOException {
		UserHTMLHelper.checkUserPassword(p_password); 
		UserService userService = new UserService();
		String userPasswordToCompare = userService.GetUserPassword(p_userId);
		if (!userPasswordToCompare.equals(p_password)) {
			return true;
		} else {
			return false;
		}
	}
	

	

}