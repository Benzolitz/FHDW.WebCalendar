package Services;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHTMLHelper;

/**
 * @author Frederik Heinrichs
 *TODO: Kommentar schreiben
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
	 * @throws NotFound 
	 * @throws IOException 
	 * @throws DatabaseException       
	 */
	public int CheckLoginData(String p_username, String p_password) throws DatabaseException, IOException, NotFound {		
		if (!UserHTMLHelper.checkUserPassword(p_password)) {
			return -1;
		} else {	
			UserService userService = new UserService();
			int userId = userService.GetUserId(p_username);
			String userPasswordToCompare = userService.GetUserPassword(userId);
			if (!userPasswordToCompare.equals(p_password)) {
				return userId;
			}
		}
		return -1;
	}
	

	

}