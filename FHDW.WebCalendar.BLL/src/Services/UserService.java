package Services;

import java.sql.SQLException;
import java.util.Collection;

import Exceptions.AlreadyExist;
import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Helper.UserHelper;
import Model.User.SecurityQuestion;
import Model.User.User;
import Repository.JDBC.WebCalendarRepo;

/**
 * @author Frederik Heinrichs
 * 
 *         Buisness Logik für die Verwaltung von Benutzerdaten
 * 
 * @see WebCalendarRepo
 * @see UserHelper
 */
public class UserService extends BaseService
{
    /**
     * Überprüfe den Eingebenen Benutzernamen oder die E-mailadresse <br>
     * und lade die passwende BenutzerId aus der Datenbank
     * 
     * @param p_usernameOrEmail
     * 
     * @return userId
     * 
     * @throws IOException wenn der Benutzername falsch eingegeben wurde
     * @throws NotFound wenn keine BenutzerId gefunden wurde
     * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank
     *             aufgetaucht ist
     * 
     * @see UserHelper#checkUserName(String)
     */
    public int GetUserId(String p_usernameOrEmail) throws IOException,
            NotFound, DatabaseException
    {

        if (!p_usernameOrEmail.contains("@"))
        {
            UserHelper.checkUserName(p_usernameOrEmail);// throws IOExceptions
        }
        else
        {
            UserHelper.checkUserMail(p_usernameOrEmail);
        }

        try
        {
            Integer reuslt_userID = GetRepo().GetUserId(p_usernameOrEmail); // throws
                                                                            // SQLException

            if (reuslt_userID == null || reuslt_userID <= 0)
            {
                throw new NotFound("Benutzer: " + p_usernameOrEmail
                        + " wurde nicht gefunden");
            }

            return reuslt_userID;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Lade das Password zu einem Benutzer
     * 
     * @param p_userId
     * @param p_password
     * 
     * @return Password - kann nicht null und nicht leer sein
     * 
     * @throws NotFound wenn das password für den Benutzer leer ist oder wenn es
     *             keinen Benutzer mit der ID gibt
     * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank
     *             aufgetaucht ist
     */
    public String GetUserPassword(int p_userId) throws NotFound,
            DatabaseException
    {
        try
        {
            String result_userPw = GetRepo().GetUserPassword(p_userId);

            if (result_userPw == null)
            {
                throw new NotFound("Es wurde kein Benutzer mit der ID: "
                        + p_userId + " gefunden!");
            }
            else if (result_userPw.isEmpty())
            {
                throw new NotFound(
                        "Es wurde kein Password zu dem Benutzer gefunden!");
            }

            return result_userPw;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * 1. Überprüft die eingebenen Benutzerdaten <br>
     * 2. Überprüft ob der Benutzer mit dem Namen oder der E-Mailadresse bereits
     * existiert<br>
     * 3. Erstellt den benutzer in der Datenbank<br>
     * 4. Desweiteren wird ein Default Kalendar für jeden Benutzer erstellt
     * 
     * @param p_user
     * 
     * @return die generierte user_id
     * 
     * @throws IOException wenn die eingebenen Benutzerdaten nicht korrekt waren
     * @throws DatabaseException Wenn ein ubekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws AlreadyExist Wenn der Benutzer mit dem Namen oder E-Mailadresse
     *             bereits existiert
     * 
     * @see UserHelper#checkUserData(User)
     * @see RegistrationService#DoesUserAlreadyExist(User)
     */
    public int RegisterNewUser(User p_user) throws IOException,
            DatabaseException, AlreadyExist
    {
        UserHelper.checkUserData(p_user); // throws IOException
        try
        {
            DoesUserAlreadyExist(p_user); // throws AlreadyExist

            Integer reuslt_userID = GetRepo().RegistrateNewUser(
                    p_user.GetUsername(), p_user.GetEMail(),
                    p_user.GetUserSecurity().GetPassword(),
                    p_user.GetFirstname(), p_user.GetLastname(),
                    p_user.GetPhonenumber(),
                    p_user.GetUserSecurity().GetSecurityQuestionId(),
                    p_user.GetUserSecurity().GetSecurityAnswer());

            if (reuslt_userID == null || reuslt_userID <= 0)
            {
                throw new DatabaseException();
            }
            else
            {
                new CalendarService().CreateCalendar(reuslt_userID,
                        CalendarService.defaultCalendarName); // throws IO
                                                              // Exception,
                                                              // DatabseException
            }

            return reuslt_userID;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Überprüft ob der Benutzer mit der E-Mailadresse oder dem Benutzernamen
     * schon exisitiert
     * 
     * @param p_user
     * 
     * @return false wenn der Benutzer nicht existiert
     * 
     * @throws IOException wenn der Benutzername falsch geschrieben ist
     * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank
     *             unterlaufen ist
     * @throws AlreadyExist wenn der Benutzer bereits existiert
     */
    private boolean DoesUserAlreadyExist(User p_user) throws IOException,
            DatabaseException, AlreadyExist
    {
        // Überprüfe ob es schon einen Benutzer mit dem Namen gibt
        try
        {
            GetUserService().GetUserId(p_user.GetUsername());
            throw new AlreadyExist("Der Benutzername: " + p_user.GetUsername()
                    + " existiert bereits");
        }
        catch (NotFound nf)
        {
            // User does not exist
        }

        // Überprüfe ob es schon einen Benutzer mit der E-Mailadresse gibt
        try
        {
            GetUserService().GetUserId(p_user.GetEMail());
            throw new AlreadyExist("Die E-Mailadresse : " + p_user.GetEMail()
                    + " existiert bereits");
        }
        catch (NotFound nf)
        {
            // User does not exist
        }

        return false;
    }

    /**
     * Lade alle SecurityQuestions aus der Datenbank
     * 
     * @return Collection mit allen SecurityQuestions aus der Datenbank (Kann
     *         nicht leer oder null sein!)
     * 
     * @throws NotFound wenn keine SecurityQeustions gefunden werden konnten
     *             (Die Liste leer der null ist)
     */
    public Collection<SecurityQuestion> GetAllSecurityQuestions()
            throws DatabaseException, NotFound
    {
        Collection<SecurityQuestion> result_securityQuestions = null;
        try
        {
            result_securityQuestions = GetRepo().GetAllSecurityQuestions();

            if (result_securityQuestions == null
                    || result_securityQuestions.isEmpty())
            {
                throw new NotFound("Es wurden keine SecurityQeustions gefunden");
            }
            else
            {
                return result_securityQuestions;
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Überprüfe das eingebene Password eines Benutzers
     * 
     * @param p_userId
     * @param p_password
     * 
     * @return true wenn das eingebene Password korrekt war<br>
     *         false wenn das eingebenen Password nicht mit dem aus der
     *         Datenbank übereinstimmt
     * 
     * @throws NotFound wenn das password zu einem benutzer nicht gefunden
     *             werden konnte
     * @throws IOException wenn das eingebenen Password nicht korrekt war
     * @throws DatabaseException
     * 
     * @see UserHelper#checkUserPassword(String)
     */
    protected boolean CheckUserPassword(int p_userId, String p_password)
            throws NotFound, IOException, DatabaseException
    {
        UserHelper.checkUserPassword(p_password); // throws IOException
        String userPasswordToCompare = GetUserService().GetUserPassword(
                p_userId); // throws NotFound, DatabaseException
        if (userPasswordToCompare.equals(p_password))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Ändert das Password zu einem Benutzer
     * 
     * @param p_userId
     * @param p_newPassword
     * 
     * @return true wenn das Password geändert wurde
     * 
     * @throws IOException wenn das eingegeben Password falsch ist <br>
     * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * 
     * @see UserHelper#checkUserPassword(String)
     */
    public boolean ChangeUserPasword(int p_userId, String p_newPassword)
            throws DatabaseException, IOException
    {
        UserHelper.checkUserPassword(p_newPassword);

        try
        {
            GetRepo().ResetPassword(p_userId, p_newPassword);
            return true;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Gibt die Security Frage für einen Benutzer
     * 
     * @param p_userId
     * 
     * @return Die Frage als String
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     */
    public String GetUserSecurityQuestion(int p_userId)
            throws DatabaseException
    {
        try
        {
            return GetRepo().GetSecurityQuestion(p_userId);
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Überprüft eine Eingebene SecurityAnswer eines Benutzers mit der aus der
     * Datenbank<br>
     * Groß und Kleinschreibung wird nicht beachtet!
     * 
     * @param p_userId
     * @param p_securityAnswer
     * 
     * @return true wenn die eingebene antwort korrekt war<br>
     *         false wenn die eingebene antwort nicht korrekt war
     * 
     * @throws NotFound wenn keine Benutzerantwort in der Datenbank gefunden
     *             werden konnte
     * @throws DatabaseException wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     */
    public boolean CheckSecurityAnswer(int p_userId, String p_securityAnswer)
            throws NotFound, DatabaseException
    {
        try
        {
            String user_answer = GetRepo().GetSecurityAnswer(p_userId);

            if (user_answer == null || user_answer.isEmpty())
            {
                // TODO: Fehlermeldung Benutzerfreundlich durchreichen
                throw new NotFound(
                        "Es wurde keine Benutzerantwort in der Datenbank gefunden");
            }
            else
            {
                if (user_answer.equalsIgnoreCase(p_securityAnswer))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }
    
    /**
     * Lade den passenden Benutzer zu einem Benutzernamen oder einer E-Mailadresse <br>
     * und vergleiche das eingebene Password mit dem aus der Datenbank
     * 
     * @param p_usernameOrEMail
     * @param p_password
     * 
     * @return userId wenn das Password korrekt ist<br>
     *         -1 , wenn das Password nicht korrekt ist<br>
     *         
     * @throws NotFound wenn der Benutzer nicht vorhanden ist
     * @throws IOException wenn das Eingebene Password oder der Benutzername nicht korrekt eingegeben wurden
     * @throws DatabaseException  wenn ein unbekannter Fehler in der Datenbank aufgetaucht ist
     * 
     *  @see LoginService#CheckUserPassword(int, String)
     *  @see UserHelper#checkUserName(String)
     */
    public int CheckLoginData(String p_usernameOrEMail, String p_password) throws NotFound, DatabaseException, IOException {                
            //Check userName
            int userId;
            try
            {
                    userId = GetUserService().GetUserId(p_usernameOrEMail);// throws IOException, Notfound
                    
                    //Check UserPassword
                    return CheckUserPassword(userId, p_password) ? userId : -1; // throws IOExceptions
            }
            catch (IOException e)
            {
                    throw new IOException("Benutzername oder Password falsch!");
            } 

    }
}
