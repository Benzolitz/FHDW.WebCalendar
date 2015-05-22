import java.util.ArrayList;
import java.util.Date;

import jdk.nashorn.internal.runtime.RewriteException;

import com.mysql.fabric.xmlrpc.base.Array;


public class TestConnection
{

	public static void main(String[] args) {
		
		WebCalendarRepo testRepo = new WebCalendarRepo();
		
		//TestCheckUsernameOrEmailWithUsername(testRepo);				//Working
		//TestCheckUsernameOrEmailWithEmail(testRepo);					//Working
		//TestCheckUsernameOrEmailWithWrongUsername(testRepo);			//Working
		
		//TestValidateLoginWithUsername(testRepo);						//Working
		//TestValidateLoginWithEmail(testRepo);							//Working
		//TestValidateLoginWithWrongUsername(testRepo);					//Working
		//TestValidateLoginWithWrongPassword(testRepo);					//Working
		
		TestRegistrateNewUser(testRepo);								//Working
		
		//TestGetSecurityQuestionWithUsername(testRepo);				//Working
		//TestGetSecurityQuestionWithEmail(testRepo);					//Working
		//TestGetSecurityQuestionWithWrongUsername(testRepo);			//Working
		
		//TestValidateSecurityAnswerCorrectAnswer(testRepo);			//Working
		//TestValidateSecurityAnswerWrongAnswer(testRepo);				//Working
		
		//TestResetPassword(testRepo);									//Working
		
		//TestGetEventsForUser(testRepo);								//Working
		
		//TestGetEventDetailed(testRepo);								//Working
		
		//TestGetAllUserCalendar(testRepo);								//Working
		
		//TestSaveEvent(testRepo);
		
	}
	
	private static void TestCheckUsernameOrEmailWithUsername(WebCalendarRepo testRepo)
	{
		CheckUsernameOrEmailResponse response;
		CheckUsernameOrEmailRequest request = new CheckUsernameOrEmailRequest();
		request.SetUsernameOrEmail("User1");
		
		response = testRepo.CheckUsernameOrEmail(request);
		System.out.println("TestCheckUsernameOrEmailWithUsername was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestCheckUsernameOrEmailWithEmail(WebCalendarRepo testRepo)
	{
		CheckUsernameOrEmailResponse response;
		CheckUsernameOrEmailRequest request = new CheckUsernameOrEmailRequest();
		request.SetUsernameOrEmail("Email1@Mail.de");
		
		response = testRepo.CheckUsernameOrEmail(request);
		System.out.println("TestCheckUsernameOrEmailWithEmail was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestCheckUsernameOrEmailWithWrongUsername(WebCalendarRepo testRepo)
	{
		CheckUsernameOrEmailResponse response;
		CheckUsernameOrEmailRequest request = new CheckUsernameOrEmailRequest();
		request.SetUsernameOrEmail("UserWhichDoesntExist");
		
		response = testRepo.CheckUsernameOrEmail(request);
		System.out.println("TestCheckUsernameOrEmailWithUsername was successfully: " + String.valueOf(!response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestValidateLoginWithUsername(WebCalendarRepo testRepo)
	{
		ValidateLoginResponse response;
		ValidateLoginRequest request = new ValidateLoginRequest();
		request.SetUsernameOrEmail("User1");
		request.SetPassword("pass1");
		
		response = testRepo.ValidateLogin(request);
		System.out.println("TestValidateLoginWithUsername was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestValidateLoginWithEmail(WebCalendarRepo testRepo)
	{
		ValidateLoginResponse response;
		ValidateLoginRequest request = new ValidateLoginRequest();
		request.SetUsernameOrEmail("Email1@Mail.de");
		request.SetPassword("pass1");
		
		response = testRepo.ValidateLogin(request);
		System.out.println("TestValidateLoginWithEmail was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestValidateLoginWithWrongUsername(WebCalendarRepo testRepo)
	{
		ValidateLoginResponse response;
		ValidateLoginRequest request = new ValidateLoginRequest();
		request.SetUsernameOrEmail("UserWhichDoesntExist");
		request.SetPassword("pass1");
		
		response = testRepo.ValidateLogin(request);
		System.out.println("TestValidateLoginWithWrongUsername was successfully: " + String.valueOf(!response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestValidateLoginWithWrongPassword(WebCalendarRepo testRepo)
	{
		ValidateLoginResponse response;
		ValidateLoginRequest request = new ValidateLoginRequest();
		request.SetUsernameOrEmail("User1");
		request.SetPassword("WrongPass");
		
		response = testRepo.ValidateLogin(request);
		System.out.println("TestValidateLoginWithWrongPassword was successfully: " + String.valueOf(!response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestRegistrateNewUser(WebCalendarRepo testRepo)
	{
		RegistrateNewUserResponse response;
		RegistrateNewUserRequest request = new RegistrateNewUserRequest();
		request.SetUsername("NewUser");
		request.SetEMail("NewUser@Mail.de");
		request.SetFirstName("Max");
		request.SetLastName("Mustermann");
		request.SetPassword("maxpass");
		request.SetPhoneNumber("+49 1234/56789");
		request.SetSecurityQuestion(1);
		request.SetSecurityAnswer("Geheime Antwort");
		
		response = testRepo.RegistrateNewUser(request);
		System.out.println("TestRegistrateNewUser was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestGetSecurityQuestionWithUsername(WebCalendarRepo testRepo)
	{
		GetSecurityQuestionResponse response;
		GetSecurityQuestionRequest request = new GetSecurityQuestionRequest();
		request.SetUsernameOrEmail("User1");
		
		response = testRepo.GetSecurityQuestion(request);
		System.out.println("TestGetSecurityQuestionWithUsername was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage() + " " + response.GetSecurityQuestion());
	}
	
	private static void TestGetSecurityQuestionWithEmail(WebCalendarRepo testRepo)
	{
		GetSecurityQuestionResponse response;
		GetSecurityQuestionRequest request = new GetSecurityQuestionRequest();
		request.SetUsernameOrEmail("Email1@Mail.de");
		
		response = testRepo.GetSecurityQuestion(request);
		System.out.println("TestGetSecurityQuestionWithEmail was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage() + " " + response.GetSecurityQuestion());
	}
	
	private static void TestGetSecurityQuestionWithWrongUsername(WebCalendarRepo testRepo)
	{
		GetSecurityQuestionResponse response;
		GetSecurityQuestionRequest request = new GetSecurityQuestionRequest();
		request.SetUsernameOrEmail("UserWhichNotExists");
		
		response = testRepo.GetSecurityQuestion(request);
		System.out.println("TestGetSecurityQuestionWithWrongUsername was successfully: " + String.valueOf(!response.IsSuccess()));
		System.out.println(response.GetMessage() + " " + response.GetSecurityQuestion());
	}
	
	private static void TestValidateSecurityAnswerCorrectAnswer(WebCalendarRepo testRepo)
	{
		ValidateSecurityAnswerResponse response;
		ValidateSecurityAnswerRequest request = new ValidateSecurityAnswerRequest();
		request.SetUsernameOrEmail("User1");
		request.SetAnswer("Hund");
		
		response = testRepo.ValidateSecurityAnswer(request);
		System.out.println("TestValidateSecurityAnswerCorrectAnswer was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestValidateSecurityAnswerWrongAnswer(WebCalendarRepo testRepo)
	{
		ValidateSecurityAnswerResponse response;
		ValidateSecurityAnswerRequest request = new ValidateSecurityAnswerRequest();
		request.SetUsernameOrEmail("User1");
		request.SetAnswer("Katze");
		
		response = testRepo.ValidateSecurityAnswer(request);
		System.out.println("TestValidateSecurityAnswerWrongAnswer was successfully: " + String.valueOf(!response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestResetPassword(WebCalendarRepo testRepo)
	{
		ResetPasswordResponse response;
		ResetPasswordRequest request = new ResetPasswordRequest();
		request.SetUsernameOrEmail("User1");
		request.SetPassword("newPass1");
		
		response = testRepo.ResetPassword(request);
		System.out.println("TestResetPassword was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}
	
	private static void TestGetEventsForUser(WebCalendarRepo testRepo)
	{
		GetEventsForUserResponse response;
		GetEventsForUserRequest request = new GetEventsForUserRequest();
		request.SetUsernameOrEmail("User1");
		
		response = testRepo.GetEventsForUser(request);
		System.out.println("TestGetEventsForUser was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
		if(response.IsSuccess())
			for (EventCalendarView event : response.GetEvents())
				System.out.println(event.GetTitle());
	}
	
	private static void TestGetEventDetailed(WebCalendarRepo testRepo)
	{
		GetEventDetailedResponse response;
		GetEventDetailedRequest request = new GetEventDetailedRequest();
		request.SetEventId(1);
		
		response = testRepo.GetEventDetailed(request);
		System.out.println("TestGetEventDetailed was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
		if(response.IsSuccess())
		{
			System.out.println("Title:" + response.GetEvent().GetTitle());
			for (String s : response.GetEvent().GetRequiredUser())
				System.out.println("Required User:" + s);
		}
	}
	
	private static void TestGetAllUserCalendar(WebCalendarRepo testRepo)
	{
		GetAllUserCalendarResponse response;
		GetAllUserCalendarRequest request = new GetAllUserCalendarRequest();
		request.SetUserId(1);
		
		response = testRepo.GetAllUserCalendar(request);
		System.out.println("GetAllUserCalendar was successfully: " + String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
		if(response.IsSuccess())
		{
			for (Calendar calendar : response.getCalendars())
			{
				System.out.println(calendar.GetName());
			}
		}
	}
	
	private static void TestSaveEvent(WebCalendarRepo testRepo)
	{
		User testUser = new User();
		testUser.SetId(1);
		
		Event testEvent = new Event();
		testEvent.SetCategory(new ArrayList<String>(){{add("5");}});
		testEvent.SetCreationTime(new Date());
		testEvent.SetCreator(testUser);
		testEvent.SetEndTime(new Date());
		testEvent.SetLocation("Location Troll");
		testEvent.SetMessage("Message Troll");
		testEvent.SetStartTime(new Date());
		
		testRepo.SaveEvent(testEvent);
	}


	
}
