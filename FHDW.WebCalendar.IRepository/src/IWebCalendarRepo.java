/**
 * @author Eduard
 *
 */
public interface IWebCalendarRepo
{
	/**
	 * Speichert einen neuen oder ge�nderten Termin.
	 * @param event	Terminobjekt, welches gespeichert werden soll
	 */
	public SaveEventResponse SaveEvent(Event event);

	/**
	 * L�scht einen vorhandenen Termin.
	 * @param event	Terminobjekt, welches gel�scht werden soll
	 */
	public DeleteEventResponse DeleteEvent(Event event);
	
	/**
	 * Speichert einen neuen Benutzer.
	 * @param user	Benutzerobjekt, welches gespeichert werden soll
	 */
	public RegistrateUserResponse RegistrateUser(User user);
	
	/**
	 * Pr�ft, ob der Benutzer existiert.
	 * @param user
	 */
	public CheckUserResponse CheckUser(User user);
}
