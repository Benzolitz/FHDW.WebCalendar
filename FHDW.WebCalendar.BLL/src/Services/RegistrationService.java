package Services;

import java.util.Collection;

import Exceptions.AlreadyExist;
import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.UserHTMLHelper;
import Model.User.User;

/**
 * @author Frederik Heinrichs
 * TODO: Kommentar schreiben
 *
 *
 */
public class RegistrationService extends BaseService
{	
	public RegistrationService() {
		// nothing to init
	}
	
	
	/**
	 * TODO: Kommentar schreiben
	 * 
	 * @param p_user
	 * 
	 * @return
	 * 
	 * @throws DatabaseException
	 * @throws AlreadyExist
	 */
	public int RegsiterNewUser(User p_user) throws DatabaseException, AlreadyExist {
	
		//TODO: WebRepo-> RegistrateNewUser auf DomainModell abstimmn! Wäre besser!
	
//		RegistrateNewUserResponse response = GetRepo().RegistrateNewUser(new RegistrateNewUserRequest());
//		if (!response.IsSuccess()) {
//			// TODO: Exception Handling optimieren!
//			// besser wäre wenn das Repository schon entsprechende Excpetions werfen würde!
//			if (response.GetMessage().contains("error")) {
//				throw new DatabaseException(response.GetMessage());
//			} else {
//				throw new AlreadyExist(response.GetMessage());
//			}
//		}
//		
//		return response.GetUserId();
		return 0;
	}
	
	/**
	 * TODO: Kommentar schreiben
	 * 
	 * @param p_userId
	 * @param p_oldPassword
	 * @param p_newPassword
	 * @param p_securityAnswer
	 * 
	 * @return
	 * 
	 * @throws DatabaseException
	 * @throws NotFound
	 * @throws IOException
	 */
	public boolean changeUserPasword(int p_userId, String p_oldPassword, String p_newPassword, String p_securityAnswer) throws DatabaseException, NotFound, IOException {	
		
		if (!p_oldPassword.equals(new UserService().GetUserPassword(p_userId))) {
			throw new IOException("Das Alte Password ist falsch");
		}
		
		if (!UserHTMLHelper.checkUserPassword(p_newPassword)) {
			throw new IOException("Das neue Password ist falsch");
		}
		
		if(checkSecurityAnswer(p_userId, p_securityAnswer)) {
//			ResetPasswordResponse response = GetRepo().ResetPassword(new ResetPasswordRequest());
//	
//			if (!response.IsSuccess()) {
//				throw new DatabaseException(response.GetMessage());
//			} else {
//				return true;
//			}
			return true;
			
		} else {
			return false;
		}	
	}
	
	
	/**
	 * Lade alle SecurityQuestions aus der Datenbank
	 * TODO: Kommentar schreiben
	 * @return
	 * 
	 * @throws DatabaseException
	 * @throws NotFound
	 */
	public Collection<String> getAlLSecurityQuestions() throws DatabaseException, NotFound {
//		GetAllSecurityQuestionsResponse response = GetRepo().GetAllSecurityQuestions(new GetAllSecurityQuestionsRequest());
//		
//		if(response.GetCategories().isEmpty() && response.IsSuccess()) {
//			throw new NotFound("Es wurde keine SecurityQuestons gefunden");
//		}
//		
//		if (!response.IsSuccess()) {
//			throw new DatabaseException(response.GetMessage());
//		}
//		
//		return response.GetCategories();
		return null;
	}
	
	
	
	/**
	 * TODO: Kommentar schreiben
	 * 
	 * @param p_userId
	 * @param p_securityAnswer
	 * 
	 * @return
	 * 
	 * @throws DatabaseException
	 * @throws NotFound
	 * @throws IOException
	 */
	private boolean checkSecurityAnswer(int p_userId, String p_securityAnswer) throws DatabaseException, NotFound, IOException {
//		GetSecurityAnswerResponse answerResponse = GetRepo().GetSecurityAnswer(new GetSecurityAnswerRequest(p_userId));
//		if (!answerResponse.IsSuccess()) {
//			throw new DatabaseException(answerResponse.GetMessage());
//		} else if (answerResponse.GetAnswer().isEmpty()) {
//			throw new NotFound("Es konnte keine SecurityAntwort für diesen Benutzer gefunden werden");
//		} else {	
//			if (!answerResponse.GetAnswer().equals(p_securityAnswer)) {
//				return false;
//			} else {
//				return true;
//			}
//		}	
		
		return true;
	}	
}
