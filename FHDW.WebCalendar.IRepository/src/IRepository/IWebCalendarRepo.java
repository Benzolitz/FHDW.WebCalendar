package IRepository;

import java.sql.SQLException;
import java.util.Collection;

import Model.Calendar.Calendar;
import Model.Calendar.Event.Event;
import Model.Calendar.Event.EventCalendarView;
import Model.SecurityQuestion.SecurityQuestion;

/**
 * @author Eduard
 *
 */
public interface IWebCalendarRepo
{
	/**
	 * Pr�ft, ob es einen Benutzer mit dem �bergebenen Benutzernamen bzw. Email gibt.
	 * @param p_usernameOrEmail
	 * @return Gibt die BenutzerID des Benutzers zur�ck.
	 */
	Integer GetUserId(String p_usernameOrEmail) throws SQLException; 
		
	/**
	 * Liefert das Passwort zu einem Benutzer.
	 * @param userId
	 * @return
	 */
	String GetUserPassword(int p_userId) throws SQLException;
	
	/**
	 * Liefert alle m�glichen Sicherheitsfragen.
	 * @return
	 */
	Collection<SecurityQuestion> GetAllSecurityQuestions() throws SQLException;
	
	/**
	 * Registriert einen neuen Benutzer. Erstellt zugleich einen leeren Kalender f�r diesen Benutzer.
	 * @param p_username
	 * @param p_email
	 * @param p_password
	 * @param p_firstName
	 * @param p_lastName
	 * @param p_phoneNumber
	 * @param p_securityQuestion
	 * @param p_securityAnswer
	 * @return Gibt die BenutzerID vom erstellten Benutzer zur�ck.
	 */
	int RegistrateNewUser(String p_username, String p_email, String p_password, String p_firstName, String p_lastName, String p_phoneNumber, int p_securityQuestion, String p_securityAnswer) throws SQLException;
	
	/**
	 * Liefert die Sicherheitsfrage zu einem Benutzerkonto.
	 * @param p_userId
	 * @return
	 */
	String GetSecurityQuestion(int p_userId) throws SQLException;
		
	/**
	 * Liefert von einem Benutzer die Sicherheitsantwort.
	 * @param p_userId
	 * @return
	 */
	String GetSecurityAnswer(int p_userId) throws SQLException;
	
	/**
	 * Setzt das Password eines Benutzers zur�ck.
	 * @param p_userId
	 * @param p_password
	 */
	void ResetPassword(int p_userId, String p_password) throws SQLException;
	
	/**
	 * Erstellt f�r einen bestimmten Benutzer einen neuen Kalendar.
	 * @param p_userId
	 * @param p_calendarName
	 * @return Gibt die KalenderID des erstellten Kalendars zur�ck.
	 */
	int CreateNewCalendar(int p_userId, String p_calendarName) throws SQLException;
	
	/**
	 * Gibt alle Kalender von einem Benutzer zur�ck.
	 * @param p_userId
	 * @return
	 */
	Collection<Calendar> GetAllUserCalendar(int p_userId) throws SQLException;
	
	/**
	 * Gibt alle Termine inklusive Kategorien f�r die Kalendaransicht zu einem bestimmten Benutzer zur�ck.
	 * @param p_calendarId
	 * @param p_userId
	 * @return
	 */
	Collection<EventCalendarView> GetEventsForUser(int p_calendarId, int p_userId, java.util.Calendar p_from, java.util.Calendar p_to) throws SQLException; 
	
	/**
	 * Gibt zu einem bestimmten Termin alle Informationen wieder.
	 * @param p_eventId
	 * @return
	 */
	Event GetEventDetailed(int p_eventId) throws SQLException;
	
	/**
	 * Speichert einen neuen Termin.
	 * @param p_title
	 * @param p_location
	 * @param p_starttime
	 * @param p_endtime
	 * @param p_message
	 * @param p_categories
	 * @param p_creatorId
	 * @param p_calendarId
	 */
	void SaveEvent(String p_title, String p_location, java.util.Calendar p_starttime, java.util.Calendar p_endtime, String p_message, Collection<String> p_categories, int p_creatorId, int p_calendarId, Collection<Integer> requiredUserId, Collection<Integer> optionalUserId) throws SQLException;

	/**
	 * L�scht einen vorhandenen Termin.
	 * @param p_eventId
	 */
	void DeleteEvent(int p_eventId, int p_userId) throws SQLException;
	
	/**
	 * L�scht einen vorhandenen Kalendar und die dazugeh�rigen Termine.
	 * @param p_calendarId
	 */
	void DeleteCalendar(int p_calendarId) throws SQLException;
	
	/**
	 * L�scht einen Benutzer und die dazugeh�rigen Kalender und Termine.
	 * @param p_userId
	 */
	void DeleteUser(int p_userId) throws SQLException;
	
	/**
	 * Aktualisiert die Daten zu einem Termin.
	 * @param p_eventId
	 * @param p_title
	 * @param p_location
	 * @param p_starttime
	 * @param p_endtime
	 * @param p_message
	 * @param p_categories
	 */
	void UpdateEvent(int p_eventId, String p_title, String p_location, java.util.Calendar p_starttime, java.util.Calendar p_endtime, String p_message, Collection<String> p_categories, Collection<Integer> requiredUserId, Collection<Integer> optionalUserId) throws SQLException;
	
}
