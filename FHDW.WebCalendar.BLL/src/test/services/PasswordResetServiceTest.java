package test.services;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Services.PasswordResetService;

public class PasswordResetServiceTest
{
	
	public PasswordResetService resetService;
	public final int USERID = 1;
	public final String USERPASSWORDOLD = "pass1";
	public final String USERPASSWORDNEW = "NEWPW";
	public final String USERANSWERTRUE = "HUND";
	public final String USERANSWERFALSE = "AKK";
	
	
	/**
	 * @return the restService
	 */
	public PasswordResetService GetResetService()
	{
		if (this.resetService == null) {
			this.resetService = new PasswordResetService();
		}
		return this.resetService;
	}
	
	@Test
	public void resetPasswordWithNonCorrectAnswer()
	{
		try
		{
			if (GetResetService().changeUserPasword(this.USERID, this.USERPASSWORDOLD, this.USERPASSWORDNEW, this.USERANSWERFALSE)) {
				fail("Faschle Benutzer antwort sollte eigentlich Falsch sein");
			} else {
				return;
			}
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
	public void resetPasswordWithCorrectAnswer()
	{
		try
		{
			// Passowrd änder
			if (GetResetService().changeUserPasword(this.USERID, this.USERPASSWORDOLD, this.USERPASSWORDNEW, this.USERANSWERTRUE)) {
				// Password wuieder zurück ändern
				if (GetResetService().changeUserPasword(this.USERID, this.USERPASSWORDNEW, this.USERPASSWORDOLD, this.USERANSWERTRUE)) {
					return;
				} 	
			} 
			
			fail("Wrong User Answer");	
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
	
}
