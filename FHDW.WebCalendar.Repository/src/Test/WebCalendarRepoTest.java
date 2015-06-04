package Test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import IRepository.IWebCalendarRepo;
import Model.SecurityQuestion.SecurityQuestion;
import Repository.JDBC.WebCalendarRepo;

public class WebCalendarRepoTest
{
	@Test
	public void testGetAllSecurityQuestions()
	{
		IWebCalendarRepo repo = new WebCalendarRepo();
		Collection<SecurityQuestion> repoQuestions;
		Collection<String> expectedQuestions;
		expectedQuestions = new ArrayList<String>();
		 
		
		expectedQuestions.add("Wie lautet der Name meines Hundes?");
		expectedQuestions.add("Wie lautet der zweite Vorname des Vaters?");
		expectedQuestions.add("Welches ist Ihr Lieblingsfilm?");
		expectedQuestions.add("Wie heiﬂt die Person, der Sie Ihren ersten Kuss geschenkt haben?");
		expectedQuestions.add("Wie heiﬂt der Lehrer, der Sie zum ersten Mal durchfallen lies?");
		expectedQuestions.add("Zu welcher Uhrzeit wurden Sie geboren?(hh:mm)");
		
		try
		{
			repoQuestions = repo.GetAllSecurityQuestions();
			
			assertTrue(repoQuestions.size() == 6);
			for (SecurityQuestion securityQuestion : repoQuestions)
			{
				assertTrue(expectedQuestions.contains(securityQuestion.GetName()));
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
