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
	 * Speichert einen neuen oder geänderten Termin.
	 * @param event	Terminobjekt, welches gespeichert werden soll
	 */
	SaveEventResponse SaveEvent(Event event);

	/**
	 * Löscht einen vorhandenen Termin.
	 * @param event	Terminobjekt, welches gelöscht werden soll
	 */
	DeleteEventResponse DeleteEvent(Event event);
	
	/**
	 * Speichert einen neuen Benutzer.
	 * @param user	Benutzerobjekt, welches gespeichert werden soll
	 */
	RegistrateUserResponse RegistrateUser(User user);
}
