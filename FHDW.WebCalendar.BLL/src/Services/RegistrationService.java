package Services;

import java.sql.SQLException;
import java.util.Collection;

import Exceptions.AlreadyExist;
import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Helper.UserHelper;
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

	private static final String DEFAULT_CALENDARNAME = "DEFAULT";
	
	public RegistrationService() {
		// nothing to init
	}
	
	
	/**
	 * 1. Überprüft die eingebenen Benutzerdaten <br>
	 * 2. Überprüft ob der Benutzer mit dem Namen oder der E-Mailadresse bereits existiert<br>
	 * 3. Erstellt den benutzer in der Datenbank<br>
	 * 4. Desweiteren wird ein Default Kalendar für jeden Benutzer erstellt
	 * 
	 * @param p_user
	 * 
	 * @return die generierte user_id
	 * 
	 * @throws IOException wenn die eingebenen Benutzerdaten nicht korrekt waren
	 * @throws DatabaseException Wenn ein ubekannter Fehler in der Datenbank entstanden ist 
	 * @throws AlreadyExist Wenn der Benutzer mit dem Namen oder E-Mailadresse bereits existiert
	 * 
	 * @see UserHelper#checkUserData(User)
	 * @see RegistrationService#DoesUserAlreadyExist(User)
	 */
	public int RegisterNewUser(User p_user) throws IOException, DatabaseException, AlreadyExist {	
		UserHelper.checkUserData(p_user); // throws IOException		
		try
		{
			DoesUserAlreadyExist(p_user); // throws AlreadyExist
			
			Integer reuslt_userID = GetRepo().RegistrateNewUser(p_user.GetUsername(), p_user.GetEMail(), p_user.GetUserSecurity().GetPassword(), 
					p_user.GetFirstname(), p_user.GetLastname(), p_user.GetPhonenumber(), 
					p_user.GetUserSecurity().GetSecurityQuestionId(), p_user.GetUserSecurity().GetSecurityAnswer());
			
			if (reuslt_userID == null || reuslt_userID <= 0) {
				throw new DatabaseException("Benutzer konnte nicht erstellt werden");
			} else {	
				new CalendarService().CreateCalendar(reuslt_userID, RegistrationService.DEFAULT_CALENDARNAME); // throws IO Exception, DatabseException
			}
			
			return reuslt_userID;
		}
		catch (SQLException e)
		{
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * Überprüft ob der Benutzer mit der E-Mailadresse oder dem Benutzernamen schon exisitiert
	 * 
	 * @param p_user
	 * 
	 * @return false wenn der Benutzer nicht existiert
	 * 
	 * @throws IOException wenn der Benutzername falsch geschrieben ist
	 * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank unterlaufen ist
	 * @throws AlreadyExist wenn der Benutzer bereits existiert
	 */
	private boolean DoesUserAlreadyExist(User p_user) throws IOException, DatabaseException, AlreadyExist {
		//Überprüfe ob es schon einen Benutzer mit dem Namen gibt
		try
		{
			GetUserService().GetUserId(p_user.GetUsername());
			throw new AlreadyExist("Der Benutzername: " + p_user.GetUsername() + " existiert bereits");
		}
		catch (NotFound nf)
		{
			// User does not exist
		}
		
		//Überprüfe ob es schon einen Benutzer mit der E-Mailadresse gibt
		try
		{
			GetUserService().GetUserId(p_user.GetEMail());
			throw new AlreadyExist("Die E-Mailadresse : " + p_user.GetEMail() + " existiert bereits");
		}
		catch (NotFound nf)
		{
			// User does not exist
		}
		
		return false;
	}
	
	/**
	 * Lade alle SecurityQuestions aus der Datenbank
	 * 
	 * @return Collection mit allen SecurityQuestions aus der Datenbank (Kann nicht leer oder null sein!)
	 * 
	 * @throws NotFound wenn keine SecurityQeustions gefunden werden konnten (Die Liste leer der null ist)
	 */
	public Collection<SecurityQuestion> GetAlLSecurityQuestions() throws DatabaseException, NotFound {
		Collection <SecurityQuestion> result_securityQuestions = null;
		try
		{
			result_securityQuestions = GetRepo().GetAllSecurityQuestions();
			
			if (result_securityQuestions == null || result_securityQuestions.isEmpty()) {
				throw new NotFound("Es wurden keine SecurityQeustions gefunden");
			} else {
				return result_securityQuestions;
			}
		}
		catch (SQLException e)
		{
			throw new DatabaseException(e);
		}
	}

}
