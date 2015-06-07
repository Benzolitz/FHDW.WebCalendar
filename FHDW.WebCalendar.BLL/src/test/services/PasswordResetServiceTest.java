package test.services;

import static org.junit.Assert.*;

import org.junit.Test;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Services.PasswordResetService;

/**
 * @author Frederik Heinrichs
 * Testklasse für den PasswordResetService
 */
public class PasswordResetServiceTest
{
	
	public PasswordResetService resetService;
	public final int USERID = 1;
	public final String USERPASSWORDNEWFALSEIO = "";
	public final String USERPASSWORDNEW = "NEWPW";
	public final String USERPASSWORDOLD = "pass1";
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
	public void checkAnswerTrue()
	{
		try
		{
			if (GetResetService().checkSecurityAnswer(this.USERID, this.USERANSWERTRUE)) {
				return;
			} else {
				fail("Die Benutzer Antwort sollte korrekt sein! Datenbank überprüfen!");
			}
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
	public void checkAnswerFalse()
	{
		try
		{
			if (GetResetService().checkSecurityAnswer(this.USERID, this.USERANSWERFALSE)) {
				fail("Die Benutzer Antwort sollte falsch sein! Datenbank überprüfen!");
			} else {
				return;
			}
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
	public void GetSecurityQuestion()
	{		
		try
		{
			String question = GetResetService().GetUserSecurityQuestion(this.USERID);
			if (question != null && !question.isEmpty()) {
				return;
			} else {
				fail("Es sollte eigentliche eine SecurityQuestion vorhanden sein! Datenbank überprüfen!");
			}	
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void changePasswordTrue()
	{
		try
		{
			// Passowrd änder
			if (GetResetService().changeUserPasword(this.USERID, this.USERPASSWORDNEW)) {
				// Password wuieder zurück ändern
				
				if (GetResetService().changeUserPasword(this.USERID, this.USERPASSWORDOLD)) {
					return;
				}
				
			} 
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		} 
		fail("Something went wrong");
	}
	
	@Test
	public void changePasswordFalseIO()
	{
		try
		{
			// Passowrd änder
			if (GetResetService().changeUserPasword(this.USERID, this.USERPASSWORDNEWFALSEIO)) {
				// Password wuieder zurück ändern
				fail("Das Password hätte falsch sein müssen");		
			} 
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
	
}
