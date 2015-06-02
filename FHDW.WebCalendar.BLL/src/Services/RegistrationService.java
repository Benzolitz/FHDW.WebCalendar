package Services;

import java.sql.SQLException;
import java.util.Collection;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHelper;
import Model.SecurityQuestion.SecurityQuestion;
import Model.User.User;
import Repository.JDBC.WebCalendarRepo;

/**
 * @author Frederik Heinrichs
 * 
 * Buisness Logik für die Registrierung eines Benutzers
 * 
 * @see WebCalendarRepo
 * @see UserHelper
 * @see LoginService
 */
public class RegistrationService extends BaseService
{	
	public RegistrationService() {
		// nothing to init
	}
	
	
	/**
	 * Überprüfe die eingebenen Benutzerdaten und erstelle diesen dann in der Datenbank
	 * Desweiteren wird ein Default Kalendar für jeden Benutzer erstellt
	 * 
	 * @param p_user
	 * 
	 * @return die generierte user_id
	 * 
	 * @throws Exception Wenn ein ubekannter Fehler in der Datenbank entstanden ist 
	 * @throws IOException wenn die eingebenen Benutzerdaten nicht korrekt waren
	 * 
	 * @see UserHelper#checkUserData(User)
	 */
	public int RegsiterNewUser(User p_user) throws Exception, IOException {	
		UserHelper.checkUserData(p_user); // throws IOException
		
		//TODO: Warum nicht den User als übergabe parameter im WebRepo?	
		int result_userid = GetRepo().RegistrateNewUser(p_user.GetUsername(), p_user.GetEMail(), p_user.GetUserSecurity().GetPassword(), 
				p_user.GetFirstname(), p_user.GetLastname(), p_user.GetPhonenumber(), 
				p_user.GetUserSecurity().GetSecurityQuestionId(), p_user.GetUserSecurity().GetSecurityAnswer());
		 //TODO: Was ist wenn der Benutzer bereits existiert?? ne passende Exception wie AlreadyExist wäre Sinnvoll!
		if (result_userid <= 0) {
			throw new Exception("Es ist ein Fehler aufgetreten beim speichern des benutzers aufgetreten");
		} else {
			// Calendar
			GetRepo().CreateNewCalendar(result_userid, CalenderService.DEFAULT_CALENDARNAME);
			
			return result_userid;
		}
	}
	
	/**
	 * Überprüft ob das neue password korrekt eingebenen wurde (SecurityCheck) <br>
	 * und ob das alte Password mit dem aus der Datenbank übereinstimmt
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
	 * @see LoginService#checkUserPassword(int, String)
	 * @see RegistrationService#checkSecurityAnswer(int, String)
	 */
	public boolean changeUserPasword(int p_userId, String p_oldPassword, String p_newPassword, String p_securityAnswer) throws NotFound, IOException {	
		// Check old userpassword (SecurityCheck)
		if (new LoginService().checkUserPassword(p_userId, p_oldPassword)){// throws NotFound, IOException
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
			// TODO: ein return wert wäre gut boolean damit ich prüfen kann ob es geklappt hat oder nicht
			return true;		
		} else {
			return false;
		}	
	}
	
	/**
	 * Lade alle SecurityQuestions aus der Datenbank
	 * 
	 * @return Collection mit allen SecurityQuestions aus der Datenbank
	 * 
	 * @throws NotFound wenn keine SecurityQeustions gefunden werden konnten
	 */
	public Collection<SecurityQuestion> getAlLSecurityQuestions() throws DatabaseException, NotFound {
		Collection <SecurityQuestion> result_securityQuestions = null;
		try
		{
			result_securityQuestions = GetRepo().GetAllSecurityQuestions();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if (result_securityQuestions == null || result_securityQuestions.isEmpty()) {
			throw new NotFound("Es wurden keine SecurityQeustions gefunden");
		} else {
			return result_securityQuestions;
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
