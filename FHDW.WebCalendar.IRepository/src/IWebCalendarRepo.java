/**
 * @author Eduard
 *
 */
public interface IWebCalendarRepo
{
	/**
	 * Prüft, ob es einen Benutzer mit dem übergebenen Benutzernamen bzw. Email gibt.
	 * @param p_request
	 * @return Response-Objekt, in dem Informationen über die Abfrage stehen
	 */
	CheckUsernameOrEmailResponse CheckUsernameOrEmail(CheckUsernameOrEmailRequest p_request); 
	
	/**
	 * Prüft, ob der Login für den Benutzer richtig ist.
	 * @param p_request
	 * @return
	 */
	ValidateLoginResponse ValidateLogin(ValidateLoginRequest p_request);
	
	/**
	 * Registriert einen neuen Benutzer. Erstellt zugleich einen leeren Kalender für diesen Benutzer.
	 * @param p_request
	 * @return
	 */
	RegistrateNewUserResponse RegistrateNewUser(RegistrateNewUserRequest p_request);
	
	/**
	 * Liefert die Sicherheitsfrage zu einem Benutzerkonto.
	 * @param p_request
	 * @return
	 */
	GetSecurityQuestionResponse GetSecurityQuestion (GetSecurityQuestionRequest p_request);
	
	/**
	 * Überprüft, ob die angegebene Antwort mit der Antwort bei der Registrierung übereinstimmt.
	 * @param p_request
	 * @return
	 */
	ValidateSecurityAnswerResponse ValidateSecurityAnswer(ValidateSecurityAnswerRequest p_request);
	
	/**
	 * Setzt das Password eines Benutzers zurück.
	 * @param p_request
	 * @return
	 */
	ResetPasswordResponse ResetPassword(ResetPasswordRequest p_request);
	
	/**
	 * Erstellt für einen bestimmten Benutzer einen neuen Kalendar.
	 * @param p_request
	 * @return
	 */
	CreateNewCalendarResponse CreateNewCalendar(CreateNewCalendarRequest p_request);
	
	/**
	 * Gibt alle Kalender von einem Benutzer zurück.
	 * @param p_request
	 * @return
	 */
	GetAllUserCalendarResponse GetAllUserCalendar(GetAllUserCalendarRequest p_request);
	
	/**
	 * Gibt alle Termine inklusive Kategorien für die Kalendaransicht zu einem bestimmten Benutzer zurück.
	 * @param p_request
	 * @return
	 */
	GetEventsForUserResponse GetEventsForUser(GetEventsForUserRequest p_request); 
	
	/**
	 * Gibt zu einem bestimmten Termin alle Informationen wieder.
	 * @param p_request
	 * @return
	 */
	GetEventDetailedResponse GetEventDetailed(GetEventDetailedRequest p_request);
	
	/**
	 * Speichert einen neuen oder geänderten Termin.
	 * @param event	Terminobjekt, welches gespeichert werden soll
	 */
	SaveEventResponse SaveEvent(Event event);

	/**
	 * Löscht einen vorhandenen Termin.
	 * @param event	Terminobjekt, welches gelöscht werden soll
	 */
	DeleteEventResponse DeleteEvent(Event event);
	
}
