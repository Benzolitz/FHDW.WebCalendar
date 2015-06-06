package test.services;

import org.junit.Test;

import static org.junit.Assert.*;
import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Services.UserService;

public class UserServiceTest
{	
	private static UserService userService;
		
	/**
	 * @return the loginService
	 */
	public static UserService GetUserService()
	{
		return userService == null ? new UserService() : userService;
	}
	
	
	@Test
	public void testGetUserIdByName()
	{	
		try
		{
			assertEquals(1, GetUserService().GetUserId("User1"));
			assertEquals(2, GetUserService().GetUserId("User2"));
			assertEquals(3, GetUserService().GetUserId("User3"));
			assertEquals(4, GetUserService().GetUserId("User4"));
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}	
	}
	
	@Test
	public void testGetUserIdByMail()
	{	
		try
		{
			assertEquals(1, GetUserService().GetUserId("Email1@Mail.de"));
			assertEquals(2, GetUserService().GetUserId("Email2@Mail.de"));
			assertEquals(3, GetUserService().GetUserId("Email3@Mail.de"));
			assertEquals(4, GetUserService().GetUserId("Email4@Mail.de"));
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}	
	}
	
	@Test
	public void testGetUserIdByNameNotFound()
	{	
		try
		{
			String wrongUsername = "User1337";
			GetUserService().GetUserId(wrongUsername);
			fail("Der Benutzer mit dem Benutzername: " + wrongUsername + "sollte nicht in der Datenbank existieren");
		}
		catch (NotFound e)
		{
			// Erwartet
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}	
		

	}
	
	@Test
	public void testGetUserIdByMailNotFound()
	{
		try
		{
			String wrongUsername = "User1337@Mail.de";
			assertEquals(1, GetUserService().GetUserId(wrongUsername));
			fail("Der Benutzer mit der Emailadresse: " + wrongUsername + "sollte nicht in der Datenbank existieren");
		}
		catch (NotFound e)
		{
			// Erwartet
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void testGetUserIdByNameIOException()
	{	
		
		String wrongUserName = "";
		try // Test Benutzer nicht ID korrekt
		{
			GetUserService().GetUserId(wrongUserName);
			fail("Die Eingabe eines leeren Benutzers sollte einen Fehler auslösen");
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			// Erwartet
		}	
	}
	
	@Test
	public void testGetUserIdByMailIOException()
	{
		String wrongUserMail = "@Email1.de";
		try // Test Benutzer nicht ID korrekt
		{
			GetUserService().GetUserId(wrongUserMail);
			fail("Die Eingabe einer E-Mailadresse ohne @ Zeichen sollte einen Fehler auslösen");
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			// Erwartet
		}
		
		wrongUserMail = "abcdef@Email1.";
		try // Test Benutzer nicht ID korrekt
		{
			GetUserService().GetUserId(wrongUserMail);
			fail("Die Eingabe einer E-Mailadresse ohne @ Zeichen sollte einen Fehler auslösen");
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			// Erwartet
		}
	}
	
	@Test
	public void testPassword()
	{	
		try // Test Benutzer ID korrekt
		{
			//assertEquals("pass1", GetUserService().GetUserPassword(1));
			assertEquals("pass2", GetUserService().GetUserPassword(2));
			assertEquals("pass3", GetUserService().GetUserPassword(3));
			assertEquals("pass4", GetUserService().GetUserPassword(4));	
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void testPasswordUserNotFound()
	{
		int wrongUserID = 1337;
		try // Test Benutzer nicht ID korrekt
		{
			GetUserService().GetUserPassword(wrongUserID);
			fail("Der Benutzer mit der ID:" + wrongUserID + " , sollte nicht in der Datenbank existieren und der UserService sollte eine NotfoundExcepion werfen");
		}
		catch (NotFound e)
		{
			// Erwartet
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}	
	}
	
}
