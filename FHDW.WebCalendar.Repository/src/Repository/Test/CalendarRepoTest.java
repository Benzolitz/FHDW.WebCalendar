package Repository.Test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import DomainModel.Calendar.Calendar;
import DomainModel.Calendar.Event;
import DomainModel.User.SecurityQuestion;
import DomainModel.User.User;
import DomainModel.User.UserSecurity;
import IRepository.ICalendarRepo;
import Repository.MySQL.CalendarRepo;

/**
 * JUnit Tests für die Datenbank. Vorraussetzung für die Tests ist die Importierung der Testdaten.
 * @author Eduard Kress
 */
public class CalendarRepoTest
{

    @Test
    public void GetUserIdWithWrongUsernameOrEmail()
    {
        ICalendarRepo repo = new CalendarRepo();
        Integer result;
        try
        {
            result = repo.GetUserId("Some Wrong User");
            assertTrue(result == null);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void GetUserIdWithCorrectUsernameOrEmail()
    {
        ICalendarRepo repo = new CalendarRepo();
        Integer result;
        try
        {
            result = repo.GetUserId("User1");
            assertTrue(result == 1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void GetUserPasswordWithCorrectUserId()
    {
        ICalendarRepo repo = new CalendarRepo();
        String result;
        try
        {
            result = repo.GetUserPassword(1);
            assertTrue(result.equals("pass1"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void GetAllSecurityQuestions()
    {
        ICalendarRepo repo = new CalendarRepo();
        Collection<SecurityQuestion> repoQuestions;
        Collection<String> expectedQuestions;
        expectedQuestions = new ArrayList<String>();

        expectedQuestions.add("Wie lautet der Name meines Hundes?");
        expectedQuestions.add("Wie lautet der zweite Vorname des Vaters?");
        expectedQuestions.add("Welches ist Ihr Lieblingsfilm?");
        expectedQuestions
                .add("Wie heißt die Person, der Sie Ihren ersten Kuss geschenkt haben?");
        expectedQuestions
                .add("Wie heißt der Lehrer, der Sie zum ersten Mal durchfallen lies?");
        expectedQuestions.add("Zu welcher Uhrzeit wurden Sie geboren?(hh:mm)");

        try
        {
            repoQuestions = repo.GetAllSecurityQuestions();

            assertTrue(repoQuestions.size() == 6);
            for (SecurityQuestion securityQuestion : repoQuestions)
            {
                assertTrue(expectedQuestions.contains(securityQuestion
                        .GetName()));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void RegistrateNewUserWithCorrectData()
    {
        ICalendarRepo repo = new CalendarRepo();
        Integer result;
        try
        {
            User user = new User();
            user.SetUsername("UserTest");
            user.SetEMail("Email@Test.de");
            user.SetFirstname("FirstTest");
            user.SetLastname("LastTest");
            user.SetPhonenumber("0123456789");
            
            UserSecurity userSecurity = new UserSecurity();
            userSecurity.SetPassword("PassTest");
            userSecurity.SetSecurityQuestionId(1);
            userSecurity.SetSecurityAnswer("TestAnswer");
            user.SetUserSecurity(userSecurity);
            
            result = repo.RegistrateNewUser(user);
            assertTrue(result != null);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void GetSecurityQuestionWithCorrectId()
    {
        ICalendarRepo repo = new CalendarRepo();
        String result;
        try
        {
            result = repo.GetSecurityQuestion(1);
            assertTrue(result.equals("Wie lautet der Name meines Hundes?"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void GetSecurityAnswerWithCorrectId()
    {
        ICalendarRepo repo = new CalendarRepo();
        String result;
        try
        {
            result = repo.GetSecurityAnswer(1);
            assertTrue(result.equals("Hund"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void ResetPasswordWithCorrectId()
    {
        ICalendarRepo repo = new CalendarRepo();
        String result;
        try
        {
            repo.ResetPassword(2, "NeuesPW");
            result = repo.GetUserPassword(2);
            assertTrue(result.equals("NeuesPW"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void GetEventsForUserWithCorrectData()
    {
        Collection<Event> result;
        ICalendarRepo repo = new CalendarRepo();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        java.util.Calendar from = java.util.Calendar.getInstance();
        java.util.Calendar to = java.util.Calendar.getInstance();
        try
        {
            from.setTime(sdf.parse("2015-05-21 17:00:00"));
            to.setTime(sdf.parse("2015-05-21 18:00:00"));
        }
        catch (ParseException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try
        {
            result = repo.GetEventsForUser(1, 1, from, to);
            for (Event eventCalendarView : result)
            {
                System.out.println(eventCalendarView.GetStartTime().getTime());
            }
            assertTrue(result.size() == 1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void CreateNewCalendarForExistingUser()
    {
    	Integer result;
    	ICalendarRepo repo = new CalendarRepo();
    	
    	try
        {
            result = repo.CreateNewCalendar(1, "NewCalendar");
            assertTrue(result != null);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void GetAllUserCalendarFromExistingUser()
    {
    	Collection<Calendar> result;
    	ICalendarRepo repo = new CalendarRepo();
    	try
        {
            result = repo.GetAllUserCalendar(1);
            assertTrue(result.size() > 0);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void GetEventDetailsOfAnExistingEvent()
    {
    	Event result;
    	ICalendarRepo repo = new CalendarRepo();
    	try
        {
            result = repo.GetEventDetailed(2);
            assertTrue(!result.GetTitle().isEmpty());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
