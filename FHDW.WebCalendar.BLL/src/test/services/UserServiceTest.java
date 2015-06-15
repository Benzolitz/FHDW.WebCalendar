package test.services;

import java.util.Collection;

import org.junit.Test;

import static org.junit.Assert.*;
import Exceptions.AlreadyExist;
import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Model.User.SecurityQuestion;
import Model.User.User;
import Model.User.UserSecurity;
import Services.UserService;

public class UserServiceTest
{
    private static UserService userService;
    private final int userIdTrue = 1;
    private final String usernameTrue = "USER1";
    private final String userpasswordTrue = "pass1";
    private final String userpasswordFalse = "wrong";
    private final String userpasswordIO = "";
    public final String userpasswordNew = "NEWPW";
    public final String userPasswordOld = "pass1";
    public final String userAnswerTrue = "HUND";
    public final String userAnswerFalse = "AKK";

    /**
     * @return the loginService
     */
    public static UserService GetUserService()
    {
        return userService == null ? new UserService() : userService;
    }

    @Test
    public void TestGetUserIdByName()
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
    public void TestGetUserIdByMail()
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
    public void TestGetUserIdByNameNotFound()
    {
        try
        {
            String wrongUsername = "User1337";
            GetUserService().GetUserId(wrongUsername);
            fail("Der Benutzer mit dem Benutzername: " + wrongUsername
                    + "sollte nicht in der Datenbank existieren");
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
    public void TestGetUserIdByMailNotFound()
    {
        try
        {
            String wrongUsername = "User1337@Mail.de";
            assertEquals(1, GetUserService().GetUserId(wrongUsername));
            fail("Der Benutzer mit der Emailadresse: " + wrongUsername
                    + "sollte nicht in der Datenbank existieren");
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
    public void TestGetUserIdByNameIOException()
    {

        String wrongUserName = "";
        try
        // Test Benutzer nicht ID korrekt
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
    public void TestGetUserIdByMailIOException()
    {
        String wrongUserMail = "@Email1.de";
        try
        // Test Benutzer nicht ID korrekt
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
        try
        // Test Benutzer nicht ID korrekt
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
    public void TestPassword()
    {
        try
        // Test Benutzer ID korrekt
        {
            // assertEquals("pass1", GetUserService().GetUserPassword(1));
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
    public void TestPasswordUserNotFound()
    {
        int wrongUserID = 1337;
        try
        // Test Benutzer nicht ID korrekt
        {
            GetUserService().GetUserPassword(wrongUserID);
            fail("Der Benutzer mit der ID:"
                    + wrongUserID
                    + " , sollte nicht in der Datenbank existieren und der UserService sollte eine NotfoundExcepion werfen");
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

    private User GetTestUserTrue()
    {
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

    private User GetTestWrongUserModel()
    {
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

    private User GetTestUserAlreadyExist()
    {
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
            assertEquals(1, GetUserService().RegisterNewUser(GetTestUserTrue()));
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
            GetUserService().RegisterNewUser(GetTestUserAlreadyExist());
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
            GetUserService().RegisterNewUser(GetTestWrongUserModel());
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
    public void GetAllSecurityQuestionsTest()
    {

        try
        {
            Collection<SecurityQuestion> dbCollection = GetUserService()
                    .GetAllSecurityQuestions();
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

    @Test
    public void CheckAnswerTrue()
    {
        try
        {
            if (GetUserService().CheckSecurityAnswer(userIdTrue,
                    userpasswordTrue))
            {
                return;
            }
            else
            {
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
    public void CheckAnswerFalse()
    {
        try
        {
            if (GetUserService().CheckSecurityAnswer(userIdTrue, userAnswerTrue))
            {
                fail("Die Benutzer Antwort sollte falsch sein! Datenbank überprüfen!");
            }
            else
            {
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
            String question = GetUserService().GetUserSecurityQuestion(
                    userIdTrue);
            if (question != null && !question.isEmpty())
            {
                return;
            }
            else
            {
                fail("Es sollte eigentliche eine SecurityQuestion vorhanden sein! Datenbank überprüfen!");
            }
        }
        catch (DatabaseException e)
        {
            fail(e.getMessage() + "\n" + e.getStackTrace());
        }
    }

    @Test
    public void ChangePasswordTrue()
    {
        try
        {
            // Passowrd änder
            if (GetUserService().ChangeUserPasword(userIdTrue, "test"))
            {
                // Password wieder zurück ändern

                if (GetUserService().ChangeUserPasword(userIdTrue, "hallo"))
                {
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
    public void ChangePasswordFalseIO()
    {
        try
        {
            // Passowrd änder
            if (GetUserService().ChangeUserPasword(userIdTrue, "sdf"))
            {
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

    @Test
    public void TestCheckLoginTrue()
    {
        try
        {
            assertEquals(userIdTrue,
                    GetUserService().CheckLoginData("asd", "sdfsdf"));
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
    public void TestCheckLoginFalse()
    {
        try
        {
            assertEquals(
                    -1,
                    GetUserService().CheckLoginData(usernameTrue,
                            userpasswordFalse));
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
    public void TestCheckLoginFailIO()
    {
        try
        {
            GetUserService().CheckLoginData(usernameTrue, userpasswordIO);
            fail("Das Password : " + userpasswordIO + " müsste falsch sein!");
        }
        catch (NotFound e)
        {
            fail(e.getMessage() + "\n" + e.getStackTrace());
        }
        catch (IOException e)
        {

        }
        catch (DatabaseException e)
        {
            fail(e.getMessage() + "\n" + e.getStackTrace());
        }
    }
}
