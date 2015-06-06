package Services;

import java.sql.SQLException;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHelper;
import Model.User.User;
import Repository.JDBC.WebCalendarRepo;

/**
 * @author Frederik Heinrichs
 * 
 * Buisness Logik für die Verwaltung von Benutzerdaten
 * 
 * @see WebCalendarRepo
 * @see UserHelper
 */
public class UserService extends BaseService
{	
	public UserService() {
		// nothing to init
	}
		
	/**
	 * Überprüfe den Eingebenen Benutzernamen oder die E-mailadresse <br> 
	 * und lade die passwende BenutzerId aus der Datenbank
	 * 
	 * @param p_usernameOrEmail
	 * 
	 * @return userId
	 * 
	 * @throws IOException wenn der Benutzername falsch eingegeben wurde
	 * @throws NotFound wenn keine BenutzerId gefunden wurde
	 * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank aufgetaucht ist
	 * 
	 * @see UserHelper#checkUserName(String)
	 */
	public int GetUserId(String p_usernameOrEmail) throws IOException, NotFound, DatabaseException {
		
		if (!p_usernameOrEmail.contains("@")) {
			UserHelper.checkUserName(p_usernameOrEmail);// throws IOExceptions
		} else {
			UserHelper.checkUserMail(p_usernameOrEmail);
		}
		
		try {
			Integer reuslt_userID = GetRepo().GetUserId(p_usernameOrEmail); // throws SQLException
			
			if (reuslt_userID == null || reuslt_userID <= 0) {
				throw new NotFound("Benutzer wurde nicht gefunden");
			}
			
			return reuslt_userID;	
		}
		catch (SQLException e) {
			throw new DatabaseException("Ein unbekannter Fehler ist aufgetreten", e);
		}		
	}

	/**
	 * Lade das Password zu einem Benutzer
	 * 
	 * @param p_userId
	 * @param p_password
	 * 
	 * @return Password - kann nicht null und nicht leer sein
	 * 
	 * @throws NotFound wenn das password für den Benutzer leer ist oder wenn es keinen Benutzer mit der ID gibt
	 * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank aufgetaucht ist
	 */
	public String GetUserPassword(int p_userId) throws NotFound, DatabaseException {		
		try
		{
			String result_userPw = GetRepo().GetUserPassword(p_userId);
			
			if (result_userPw == null) {
				throw new NotFound("Es wurde kein Benutzer mit der ID: " + p_userId + " gefunden!");
			} else if (result_userPw.isEmpty()) {
				throw new NotFound("Es wurde kein Password zu dem Benutzer gefunden!");	
			}
			
			return result_userPw;
		}
		catch (SQLException esql)
		{
			throw new DatabaseException("Ein unbekannter Fehler ist aufgetreten", esql);
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
	
}
