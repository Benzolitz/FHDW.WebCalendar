package test.services;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import Exceptions.AlreadyExist;
import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Model.SecurityQuestion.SecurityQuestion;
import Model.User.User;
import Model.User.UserSecurity;
import Services.RegistrationService;

public class RegistrationServiceTest
{
	private RegistrationService registrationService;
	
	/**
	 * @return the registrationService
	 */
	public RegistrationService GetRegistrationService()
	{
		if(this.registrationService == null) {
			this.registrationService = new RegistrationService();
		}
		return registrationService;
	}

	private User GetTestUserTrue() {
		User user = new User();
		UserSecurity userSecurity = new UserSecurity();
		userSecurity.SetPassword("abcdef");
		userSecurity.SetSecurityAnswer("test");
		userSecurity.SetSecurityQuestionId(1);
		userSecurity.SetSecurityQuestion("Wie lautet der Name meines Hundes?");
		user.SetUserSecurity(userSecurity);
		user.SetUsername("TestBenutzer");
		user.SetEMail("TestBenutzer@Email.de");
		user.SetFirstname("TestUser");
		user.SetLastname("TestUser");
		user.SetPhonenumber("11234125412");
		return user;
	}
	
	private User GetTestWrongUserModel() {
		User user = new User();
		UserSecurity userSecurity = new UserSecurity();
		userSecurity.SetPassword("abcdef");
		user.SetUserSecurity(userSecurity);
		user.SetUsername("TestBenutzer");
		user.SetEMail("TestBenutzer@Email.de");
		user.SetFirstname("TestUser");
		user.SetLastname("TestUser");
		user.SetPhonenumber("11234125412");
		return user;
	}
	
	private User GetTestUserAlreadyExist() {
		User user = new User();
		UserSecurity userSecurity = new UserSecurity();
		userSecurity.SetPassword("abcdef");
		user.SetUserSecurity(userSecurity);
		user.SetUsername("User1");
		user.SetEMail("Email1@Mail.de");
		return user;
	}
			
	
	@Test
	public void RegistrateNewUser()
	{
		try
		{
			assertEquals(1, GetRegistrationService().RegisterNewUser(GetTestUserTrue()));
		}
		catch (IOException e)
		{		
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());	
		}
		catch (AlreadyExist e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void RegistrateNewUserAlreadyExist()
	{
		try
		{
			GetRegistrationService().RegisterNewUser(GetTestUserAlreadyExist());
		}
		catch (IOException e)
		{		
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());	
		}
		catch (AlreadyExist e)
		{
			// Erwartet
		}
	}
	
	@Test
	public void RegistrateWrongUserModel()
	{
		try
		{
			GetRegistrationService().RegisterNewUser(GetTestWrongUserModel());
		}
		catch (IOException e)
		{		
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());	
		}
		catch (AlreadyExist e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void GetAllSecurityQuestionsTest() {
		
		try
		{
			Collection<SecurityQuestion> dbCollection = GetRegistrationService().GetAlLSecurityQuestions();
		    assertNotNull(dbCollection);
		    assertEquals(6, dbCollection.size());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
}
