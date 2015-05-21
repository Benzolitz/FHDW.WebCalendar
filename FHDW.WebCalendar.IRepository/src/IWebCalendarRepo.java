/**
 * @author Eduard
 *
 */
public interface IWebCalendarRepo
{
	/**
	 * Pr�ft, ob es einen Benutzer mit dem �bergebenen Benutzernamen bzw. Email gibt.
	 * @param p_request
	 * @return Response-Objekt, in dem Informationen �ber die Abfrage stehen
	 */
	CheckUsernameOrEmailResponse CheckUsernameOrEmail(CheckUsernameOrEmailRequest p_request); 
	
	/**
	 * Pr�ft, ob der Login f�r den Benutzer richtig ist.
	 * @param p_request
	 * @return
	 */
	ValidateLoginResponse ValidateLogin(ValidateLoginRequest p_request);
	
	/**
	 * Speichert einen neuen oder ge�nderten Termin.
	 * @param event	Terminobjekt, welches gespeichert werden soll
	 */
	SaveEventResponse SaveEvent(Event event);

	/**
	 * L�scht einen vorhandenen Termin.
	 * @param event	Terminobjekt, welches gel�scht werden soll
	 */
	DeleteEventResponse DeleteEvent(Event event);
	
	/**
	 * Speichert einen neuen Benutzer.
	 * @param user	Benutzerobjekt, welches gespeichert werden soll
	 */
	RegistrateUserResponse RegistrateUser(User user);
}
