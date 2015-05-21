/**
 * @author Eduard
 *
 */
public interface IWebCalendarRepo
{
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
	
	/**
	 * Prüft, ob der Benutzer existiert.
	 * @param user
	 */
	CheckUserResponse CheckUser(User user);
}
