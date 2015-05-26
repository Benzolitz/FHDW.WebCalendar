package Services.user;

import objects.user.User;
import Exceptions.IOException;
import Exceptions.NotFound;
import Exceptions.ServiceException;
import Helper.UserHelper;
import IRepository.Request.GetUserIdRequest;
import IRepository.Request.GetUserPasswordRequest;
import IRepository.Request.RegistrateNewUserRequest;
import IRepository.Response.GetUserIdResponse;
import IRepository.Response.GetUserPasswordResponse;
import IRepository.Response.RegistrateNewUserResponse;
import Services.BaseService;


public class UserService extends BaseService 
{
	
	/**
	 * @param p_user
	 * @return userid if success
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	public int createNewUser(User p_user) throws ServiceException, IOException {
		
		if(!UserHelper.checkUserData(p_user)) {
			throw new IOException("");
		}
		
		RegistrateNewUserResponse response = GetRepo().RegistrateNewUser(new RegistrateNewUserRequest());
		if (!response.IsSuccess()) {
			throw new ServiceException();
		}
		p_user.SetId(response.GetUserId());
		
		return response.GetUserId();
	}
	
	/**
	 * 1. Übergabe Paramter werden auf korrekte Eingabe geprüft
	 * 2. Usernamen prüfen ob vorhanden
	 * 3. Password prüfen ob korrekt
	 * 4. true ausgeben
	 * 
	 * @param p_username
	 * @param p_password
	 * @return
	 * @throws Exception 
	 */
	public boolean CheckLoginData(String p_username, String p_password) throws NotFound, IOException {
		// check parameters
		if (!UserHelper.checkUserName(p_username)) {
			throw new IOException("Benutzername falsch eingegeben");
		}
		
		if (!UserHelper.checkUserPassword(p_password)) {
			throw new IOException("Password falsch eingegeben");
		}
	
		// check userID
		GetUserIdResponse userIdResponse = GetRepo().GetUserId(new GetUserIdRequest(p_username));
		if (!userIdResponse.IsSuccess()) {
			throw new NotFound("test");
		}
		
		// check userPW
		GetUserPasswordResponse userPwRequest = GetRepo().GetUserPassword(new GetUserPasswordRequest(userIdResponse.GetUserId()));
		if (!userPwRequest.IsSuccess()) {
			throw new IOException("test");
		}
		
		return true;
	}
	
	
}
