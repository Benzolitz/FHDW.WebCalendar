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
	 * Registriert einen neuen Benutzer. 
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
	 * Setzt das Password eines Benutzers zur�ck.
	 * @param p_request
	 * @return
	 */
	ResetPasswordResponse ResetPassword(ResetPasswordRequest p_request); 
	
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
	
}
