package Repository.JDBC;
import IRepository.Request.GetAllUserCalendarRequest;
import IRepository.Request.GetEventDetailedRequest;
import IRepository.Request.GetEventsForUserRequest;
import IRepository.Request.GetSecurityQuestionRequest;
import IRepository.Request.GetUserIdRequest;
import IRepository.Request.RegistrateNewUserRequest;
import IRepository.Request.ResetPasswordRequest;
import IRepository.Response.GetAllUserCalendarResponse;
import IRepository.Response.GetEventDetailedResponse;
import IRepository.Response.GetEventsForUserResponse;
import IRepository.Response.GetSecurityQuestionResponse;
import IRepository.Response.GetUserIdResponse;
import IRepository.Response.RegistrateNewUserResponse;
import IRepository.Response.ResetPasswordResponse;
import Model.Calendar.Calendar;
import Model.Calendar.Event.EventCalendarView;

public class TestConnection
{

	public static void main(String[] args)
	{

		WebCalendarRepo testRepo = new WebCalendarRepo();

		// TestCheckUsernameOrEmailWithUsername(testRepo); //Working
		// TestCheckUsernameOrEmailWithEmail(testRepo); //Working
		// TestCheckUsernameOrEmailWithWrongUsername(testRepo); //Working

		// TestValidateLoginWithUsername(testRepo); //Working
		// TestValidateLoginWithEmail(testRepo); //Working
		// TestValidateLoginWithWrongUsername(testRepo); //Working
		// TestValidateLoginWithWrongPassword(testRepo); //Working

		// TestRegistrateNewUser(testRepo); //Working

		// TestGetSecurityQuestionWithUsername(testRepo); //Working
		// TestGetSecurityQuestionWithEmail(testRepo); //Working
		// TestGetSecurityQuestionWithWrongUsername(testRepo); //Working

		// TestValidateSecurityAnswerCorrectAnswer(testRepo); //Working
		// TestValidateSecurityAnswerWrongAnswer(testRepo); //Working

		// TestResetPassword(testRepo); //Working

		// TestGetEventsForUser(testRepo); //Working

		// TestGetEventDetailed(testRepo); //Working

		// TestGetAllUserCalendar(testRepo); //Working

		// TestSaveEvent(testRepo);

	}

	private static void TestCheckUsernameOrEmailWithUsername(
			WebCalendarRepo testRepo)
	{
		GetUserIdResponse response;
		GetUserIdRequest request = new GetUserIdRequest();
		request.SetUsernameOrEmail("User1");

		response = testRepo.GetUserId(request);
		System.out
				.println("TestCheckUsernameOrEmailWithUsername was successfully: "
						+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}

	private static void TestCheckUsernameOrEmailWithEmail(
			WebCalendarRepo testRepo)
	{
		GetUserIdResponse response;
		GetUserIdRequest request = new GetUserIdRequest();
		request.SetUsernameOrEmail("Email1@Mail.de");

		response = testRepo.GetUserId(request);
		System.out
				.println("TestCheckUsernameOrEmailWithEmail was successfully: "
						+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}

	private static void TestCheckUsernameOrEmailWithWrongUsername(
			WebCalendarRepo testRepo)
	{
		GetUserIdResponse response;
		GetUserIdRequest request = new GetUserIdRequest();
		request.SetUsernameOrEmail("UserWhichDoesntExist");

		response = testRepo.GetUserId(request);
		System.out
				.println("TestCheckUsernameOrEmailWithUsername was successfully: "
						+ String.valueOf(!response.IsSuccess()));
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
		System.out.println("TestRegistrateNewUser was successfully: "
				+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}

	private static void TestGetSecurityQuestionWithUsername(
			WebCalendarRepo testRepo)
	{
		GetSecurityQuestionResponse response;
		GetSecurityQuestionRequest request = new GetSecurityQuestionRequest();
		request.SetUsernameOrEmail("User1");

		response = testRepo.GetSecurityQuestion(request);
		System.out
				.println("TestGetSecurityQuestionWithUsername was successfully: "
						+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage() + " "
				+ response.GetSecurityQuestion());
	}

	private static void TestGetSecurityQuestionWithEmail(
			WebCalendarRepo testRepo)
	{
		GetSecurityQuestionResponse response;
		GetSecurityQuestionRequest request = new GetSecurityQuestionRequest();
		request.SetUsernameOrEmail("Email1@Mail.de");

		response = testRepo.GetSecurityQuestion(request);
		System.out
				.println("TestGetSecurityQuestionWithEmail was successfully: "
						+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage() + " "
				+ response.GetSecurityQuestion());
	}

	private static void TestGetSecurityQuestionWithWrongUsername(
			WebCalendarRepo testRepo)
	{
		GetSecurityQuestionResponse response;
		GetSecurityQuestionRequest request = new GetSecurityQuestionRequest();
		request.SetUsernameOrEmail("UserWhichNotExists");

		response = testRepo.GetSecurityQuestion(request);
		System.out
				.println("TestGetSecurityQuestionWithWrongUsername was successfully: "
						+ String.valueOf(!response.IsSuccess()));
		System.out.println(response.GetMessage() + " "
				+ response.GetSecurityQuestion());
	}

	private static void TestResetPassword(WebCalendarRepo testRepo)
	{
		ResetPasswordResponse response;
		ResetPasswordRequest request = new ResetPasswordRequest();
		request.SetUsernameOrEmail("User1");
		request.SetPassword("newPass1");

		response = testRepo.ResetPassword(request);
		System.out.println("TestResetPassword was successfully: "
				+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
	}

	private static void TestGetEventsForUser(WebCalendarRepo testRepo)
	{
		GetEventsForUserResponse response;
		GetEventsForUserRequest request = new GetEventsForUserRequest();
		request.SetCalendarId(1);
		request.SetUserId(1);

		response = testRepo.GetEventsForUser(request);
		System.out.println("TestGetEventsForUser was successfully: "
				+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
		if (response.IsSuccess())
			for (EventCalendarView event : response.GetEvents())
				System.out.println(event.GetTitle());
	}

	private static void TestGetEventDetailed(WebCalendarRepo testRepo)
	{
		GetEventDetailedResponse response;
		GetEventDetailedRequest request = new GetEventDetailedRequest();
		request.SetEventId(1);

		response = testRepo.GetEventDetailed(request);
		System.out.println("TestGetEventDetailed was successfully: "
				+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
		if (response.IsSuccess())
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
		System.out.println("GetAllUserCalendar was successfully: "
				+ String.valueOf(response.IsSuccess()));
		System.out.println(response.GetMessage());
		if (response.IsSuccess())
		{
			for (Calendar calendar : response.getCalendars())
			{
				System.out.println(calendar.GetName());
			}
		}
	}

}
