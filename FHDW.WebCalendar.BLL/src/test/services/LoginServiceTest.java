package test.services;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Services.LoginService;
import Services.UserService;

public class LoginServiceTest extends LoginService
{
	
	private static LoginService loginService;
	private final int USERIDTRUE = 1;
	private final String USERNAMETRUE = "USER1";
	private final String USERPASSWORDTRUE = "pass1";
	private final String USERPASSWORDFALSE = "wrong";
	private final String USERPASSWORDIO = "";
	
	/**
	 * @return the loginService
	 */
	public static LoginService GetLoginService()
	{
		return loginService == null ? new LoginService() : loginService;
	}

	@Test
	public void testCheckLoginTrue()
	{		
		try
		{
			assertEquals(this.USERIDTRUE, GetLoginService().CheckLoginData(this.USERNAMETRUE, this.USERPASSWORDTRUE)); 
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	@Test
	public void testCheckLoginFalse()
	{
		try
		{
			assertEquals(-1, GetLoginService().CheckLoginData(this.USERNAMETRUE, this.USERPASSWORDFALSE)); 
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}

	@Test
	public void testCheckLoginFailIO()
	{
		try
		{
			GetLoginService().CheckLoginData(this.USERNAMETRUE, this.USERPASSWORDIO);
			fail("Das Password : " + this.USERPASSWORDIO + " müsste falsch sein!");
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			// Erwartet
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());	
		}
	}		
}
