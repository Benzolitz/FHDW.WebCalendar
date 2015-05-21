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
	
	/**
	 * Pr�ft, ob der Benutzer existiert.
	 * @param user
	 */
	CheckUserResponse CheckUser(User user);
}
