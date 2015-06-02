package Services;

import java.sql.SQLException;

import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHTMLHelper;
import Model.User.User;
import Repository.JDBC.WebCalendarRepo;

/**
 * @author Frederik Heinrichs
 * 
 * Buisness Logik für die Verwaltung von Benutzerdaten
 * 
 * @see WebCalendarRepo
 * @see UserHTMLHelper
 */
public class UserService extends BaseService
{	
	public UserService() {
		// nothing to init
	}
		
	/**
	 * Überprüfe den Eingebenen Benutzernamen und lade die BenutzerId zu einem Benutzernamen
	 * 
	 * @param p_username
	 * 
	 * @return userId
	 * 
	 * @throws IOException wenn der eingebene Benutzername nicht den Regeln entspricht
	 * @throws NotFound wenn keine BenutzerId gefunden wurde
	 * 
	 * @see UserHTMLHelper#checkUserName(String)
	 */
	public int GetUserId(String p_username) throws IOException, NotFound {
		UserHTMLHelper.checkUserName(p_username);// throws IOExceptions		
		
		int reuslt_userID = -1;
		try
		{
			reuslt_userID = GetRepo().GetUserId(p_username);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}//TODO: Was ist wenn der User nicht vorhanden ist? Kann die DB da nicht schon vorgefertigte Exceptions werfen? NotFound!		
		if (reuslt_userID <= 0) {
			throw new NotFound("Benutzer wurde nicht gefunden");
		} else {
			return reuslt_userID;
		}
	}
	
	/**
	 * Lade den Benutzer passend zur Id
	 * 
	 * @param p_userId
	 * 
	 * @return User
	 */
	public User GetUser(int p_userId) {
	
		//TODO: GetuserInfo gibts im Repo noch nicht!
		
		return new User();
	}
	
	/**
	 * Lade das Password zu einem Benutzer
	 * 
	 * @param p_userId
	 * @param p_password
	 * 
	 * @return Password
	 * 
	 * @throws NotFound wenn das password für den Benutzer leer ist
	 */
	public String GetUserPassword(int p_userId) throws NotFound {		
		String result_userPw = "";
		try
		{
			result_userPw = GetRepo().GetUserPassword(p_userId);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if (result_userPw.isEmpty()) {
			throw new NotFound("Es wurde kein Password zu dem Benutzer gefunden!");
		} else {
			return result_userPw;
		}
	}
	
}
