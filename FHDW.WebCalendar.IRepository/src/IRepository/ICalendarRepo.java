package IRepository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import com.sun.media.sound.ModelAbstractChannelMixer;

import DomainModel.Calendar.Calendar;
import DomainModel.Calendar.Event;
import DomainModel.User.SecurityQuestion;

/**
 * Das Interface IWebCalendarRepo.
 * @author Eduard Kress
 */
public interface ICalendarRepo
{
    
    /**
     * Pr�ft, ob es einen Benutzer mit dem �bergebenen Benutzernamen bzw. Email
     * gibt.
     *
     * @param p_usernameOrEmail Einen Benutzernamen oder eine E-Mail-Adresse
     * @return Gibt die BenutzerID des Benutzers zur�ck. Falls es f�r die �bergebenen Benutzernamen oder E-Mail keinen passenden Benutzer gibt, wird null geliefert.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    Integer GetUserId(String p_usernameOrEmail) throws SQLException;

    /**
     * Liefert das Passwort zu einem Benutzer.
     *
     * @param p_userId Eine BenutzerID.
     * @return Gibt das zu dem Benutzer geh�rende Passwort zur�ck. Falls die �bergebene BenutzerID eine nicht existierende BenutzerID ist, wird null geliefert.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    String GetUserPassword(int p_userId) throws SQLException;

    /**
     * Liefert alle m�glichen Sicherheitsfragen.
     *
     * @return Gibt eine Liste mit Domain-Objekten des Types {@link DomainModel.User.SecurityQuestion} zur�ck. Je Objekt ist die Frage als Zeichenkette und deren ID vorhanden.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    Collection<SecurityQuestion> GetAllSecurityQuestions() throws SQLException;

    /**
     * Registriert ein neues Benutzerkonto.
     *
     * @param p_username Der Benutzername des Kontos.
     * @param p_email Die E-Mail-Adresse des Kontoinhabers.
     * @param p_password Das Passwort des Kontos.
     * @param p_firstName Der Vorname des Kontoinhabers.
     * @param p_lastName Der Nachname des Kontoinhabers.
     * @param p_phoneNumber Die Telefonnummer des Kontoinhabers.
     * @param p_securityQuestion Die SicherheitsfrageID, die f�r dieses Konto.
     * @param p_securityAnswer Die Sicherheitsantwort des Kontos.
     * @return Gibt die BenutzerID des registrierten Benutzers zur�ck.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    int RegistrateNewUser(String p_username, String p_email, String p_password,
            String p_firstName, String p_lastName, String p_phoneNumber,
            int p_securityQuestion, String p_securityAnswer)
            throws SQLException;

    /**
     * Liefert die Sicherheitsfrage zu einem Benutzerkonto.
     *
     * @param p_userId Eine BenutzerID.
     * @return Gibt die Sicherheitsfrage als Zeichenkette zur�ck. Falls die �bergebene BenutzerID eine nicht existierende BenutzerID ist, wird null geliefert.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    String GetSecurityQuestion(int p_userId) throws SQLException;

    /**
     * Liefert von einem Benutzer die Sicherheitsantwort.
     *
     * @param p_userId Eine BenutzerID.
     * @return Gibt die Sicherheitsantwort als Zeichenkette zur�ck. Falls die �bergebene BenutzerID eine nicht existierende BenutzerID ist, wird null geliefert.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    String GetSecurityAnswer(int p_userId) throws SQLException;

    /**
     * Setzt das Password eines Benutzers zur�ck.
     *
     * @param p_userId Eine BenutzerID.
     * @param p_password Das neue Passwort f�r das dazugeh�rige Konto.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    void ResetPassword(int p_userId, String p_password) throws SQLException;

    /**
     * Erstellt f�r einen bestimmten Benutzer einen neuen Kalendar.
     *
     * @param p_userId Eine BenutzerID.
     * @param p_calendarName Der Name des zu erstellenden Kalendars.
     * @return Gibt die KalenderID des erstellten Kalendars zur�ck.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    int CreateNewCalendar(int p_userId, String p_calendarName)
            throws SQLException;

    /**
     * Gibt alle Kalender von einem Benutzer zur�ck.
     *
     * @param p_userId Eine BenutzerID.
     * @return Eine Liste mit Domain-Objekten des Typs {@link DomainModel.Calendar.Calendar} zur�ck.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    Collection<Calendar> GetAllUserCalendar(int p_userId) throws SQLException;

    /**
     * Gibt alle Termine inklusive Kategorien f�r die Kalendaransicht zu einem
     * bestimmten Benutzer zur�ck.
     *
     * @param p_calendarId Eine KalendarID.
     * @param p_userId Eine BenutzerID.
     * @param p_from Von wann die Termine geholt werden sollen.
     * @param p_to Bis wann die Termine geholt werden sollen.
     * @return Gibt eine Liste mit Domain.Objekten des Typs {@link DomainModel.Calendar.Event} zur�ck.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    Collection<Event> GetEventsForUser(int p_calendarId, int p_userId,
            java.util.Calendar p_from, java.util.Calendar p_to)
            throws SQLException;

    /**
     * Gibt zu einem bestimmten Termin alle Informationen wieder.
     *
     * @param p_eventId Eine TerminID.
     * @return Gibt ein Objekt des Typs {@link DomainModel.Calendar.Event} zur�ck.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    Event GetEventDetailed(int p_eventId) throws SQLException;

    /**
     * Speichert einen neuen Termin.
     *
     * @param p_title Der Titel des Termins.
     * @param p_location Der Ort des Termins.
     * @param p_starttime Die Startzeit des Termins.
     * @param p_endtime Die Endzeit des Termins.
     * @param p_message Die Nachricht des Termins.
     * @param p_categories Die Kategorien eines Termins.
     * @param p_creatorId Die BenutzerID des Erstellers.
     * @param requiredUserId Die erforderlichen Teilnehmer.
     * @param optionalUserId Die optionalen Teilnehmer.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    void SaveEvent(String p_title, String p_location,
            java.util.Calendar p_starttime, java.util.Calendar p_endtime,
            String p_message, Collection<String> p_categories, int p_creatorId,
            int p_calendarId, HashMap<Integer, Integer> requiredUserId,
            HashMap<Integer, Integer> optionalUserId) throws SQLException;

    /**
     * L�scht einen vorhandenen Termin in einem Kalendar. Falls der L�scher auch der Ersteller ist, so wird der Termin in allen Kalendern gel�scht.
     *
     * @param p_eventId Eine TerminID.
     * @param p_userId Eine BenutzerID.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    void DeleteEvent(int p_eventId, int p_userId) throws SQLException;

    /**
     * L�scht einen vorhandenen Kalendar und die dazugeh�rigen Termine.
     *
     * @param p_calendarId Eine KalendarID.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    void DeleteCalendar(int p_calendarId) throws SQLException;

    /**
     * L�scht einen Benutzer und die dazugeh�rigen Kalender und Termine.
     *
     * @param p_userId Eine BenutzerID.
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    void DeleteUser(int p_userId) throws SQLException;

    /**
     * Aktualisiert die Daten zu einem Termin.
     *
     * @param p_eventId Eine TerminID.
     * @param p_title Der neue Titel des Termins.
     * @param p_location Der neue Ort des Termins.
     * @param p_starttime Die neue Startzeit des Termins.
     * @param p_endtime Die neue Endzeit des Termins.
     * @param p_message Die neue Nachricht des Termins.
     * @param p_categories Die neuen Kategorien des Termins.
     * @param requiredUserId Die neuen erforderlichen Benutzer.
     * @param optionalUserId DIe neuen optionalen Benutzer
     * @throws SQLException Wenn ein Problem mit der Datenbank aufgetreten ist.
     */
    void UpdateEvent(int p_eventId, String p_title, String p_location,
            java.util.Calendar p_starttime, java.util.Calendar p_endtime,
            String p_message, Collection<String> p_categories,
            HashMap<Integer, Integer> requiredUserId,
            HashMap<Integer, Integer> optionalUserId) throws SQLException;

}
