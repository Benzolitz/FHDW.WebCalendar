package Test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import IRepository.IWebCalendarRepo;
import Model.Calendar.Event;
import Model.User.SecurityQuestion;
import Repository.JDBC.WebCalendarRepo;

public class WebCalendarRepoTest
{

    @Test
    public void GetUserIdWithWrongUsernameOrEmail()
    {
        IWebCalendarRepo repo = new WebCalendarRepo();
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
        IWebCalendarRepo repo = new WebCalendarRepo();
        Integer result;
        try
        {
            result = repo.GetUserId("User1");
            assertTrue(result == 1);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void GetUserPasswordWithCorrectUserId()
    {
        IWebCalendarRepo repo = new WebCalendarRepo();
        String result;
        try
        {
            result = repo.GetUserPassword(1);
            assertTrue(result.equals("pass1"));
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void GetAllSecurityQuestions()
    {
        IWebCalendarRepo repo = new WebCalendarRepo();
        Collection<SecurityQuestion> repoQuestions;
        Collection<String> expectedQuestions;
        expectedQuestions = new ArrayList<String>();

        expectedQuestions.add("Wie lautet der Name meines Hundes?");
        expectedQuestions.add("Wie lautet der zweite Vorname des Vaters?");
        expectedQuestions.add("Welches ist Ihr Lieblingsfilm?");
        expectedQuestions
                .add("Wie heiﬂt die Person, der Sie Ihren ersten Kuss geschenkt haben?");
        expectedQuestions
                .add("Wie heiﬂt der Lehrer, der Sie zum ersten Mal durchfallen lies?");
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
        IWebCalendarRepo repo = new WebCalendarRepo();
        Integer result;
        try
        {
            result = repo.RegistrateNewUser("UserTest", "EmailTest",
                    "PassTest", "FirstTest", "LastTest", "0123456789", 1,
                    "TestAnswer");
            assertTrue(result != null);
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void GetSecurityQuestionWithCorrectId()
    {
        IWebCalendarRepo repo = new WebCalendarRepo();
        String result;
        try
        {
            result = repo.GetSecurityQuestion(1);
            assertTrue(result.equals("Wie lautet der Name meines Hundes?"));
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void GetSecurityAnswerWithCorrectId()
    {
        IWebCalendarRepo repo = new WebCalendarRepo();
        String result;
        try
        {
            result = repo.GetSecurityAnswer(1);
            assertTrue(result.equals("Hund"));
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void ResetPasswordWithCorrectId()
    {
        IWebCalendarRepo repo = new WebCalendarRepo();
        String result;
        try
        {
            repo.ResetPassword(2, "NeuesPW");
            result = repo.GetUserPassword(2);
            assertTrue(result.equals("NeuesPW"));
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    @Test
    public void GetEventsForUserWithCorrectData()
    {
        Collection<Event> result;
        IWebCalendarRepo repo = new WebCalendarRepo();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
