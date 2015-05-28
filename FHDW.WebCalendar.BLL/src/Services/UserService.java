package Services;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHTMLHelper;
import Model.User.User;

/**
 * @author Frederik Heinrichs
 *	TODO: Kommentar schreiben!
 */
public class UserService extends BaseService
{	
	public UserService() {
		// nothing to init
	}
	
	
	/**
	 * Lade die BenutzerId zu einem Benutzernamen
	 * 
	 * @param p_username
	 * 
	 * @return userId
	 * 
	 * @throws IOException
	 * @throws NotFound
	 */
	public int GetUserId(String p_username) throws DatabaseException, IOException, NotFound {
		// Überprüfe die Eingabe auf Regelverstöße oder Eingabefehler
		if (!UserHTMLHelper.checkUserName(p_username)) {
			throw new IOException("Benutzername falsch eingegeben");
		}
		
//		// check userID
//		GetUserIdResponse userIdResponse = 
//				GetRepo().GetUserId(new GetUserIdRequest(p_username));
//		
//		if(userIdResponse.GetUserId() == 0 && userIdResponse.IsSuccess()) {
//			throw new NotFound("Es wurde kein Password für den Benutzer gefunden");
//		}
//		
//		if (!userIdResponse.IsSuccess()) {
//			throw new DatabaseException(userIdResponse.GetMessage());
//		}
//		
//		return userIdResponse.GetUserId();		
		return 0;
	}
	
	/**
	 * TODO:
	 * Benutzername aus DB laden? 
	 * Wozu geben wir die Daten ein, wenn wir die nirgendwo verwenden!!!
	 * 
	 * @param p_userId
	 * 
	 * @return
	 */
	public User GetUser(int p_userId) {
		
		return new User();
	}
	
	/**
	 * Lade das passende p_password zu einem Benutzer
	 * 
	 * @param p_userId
	 * @param p_password
	 * 
	 * @return Benutzer Password
	 * 
	 * @throws Exception
	 */
	public String GetUserPassword(int p_userId) throws DatabaseException, NotFound {		
//		GetUserPasswordResponse userPwRequest = 
//				GetRepo().GetUserPassword(new GetUserPasswordRequest(p_userId));
//		
//		if(userPwRequest.GetPassword().isEmpty() && userPwRequest.IsSuccess()) {
//			throw new NotFound("Es wurde kein Password für den Benutzer gefunden");
//		}
//		
//		if (!userPwRequest.IsSuccess()) {
//			throw new DatabaseException(userPwRequest.GetMessage());
//		}
//		
//		return userPwRequest.GetPassword();		
		
		return "";
	}
	
}
