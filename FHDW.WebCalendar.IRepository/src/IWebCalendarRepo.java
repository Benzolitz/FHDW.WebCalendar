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
	public SaveEventResponse SaveEvent(Event event);

	/**
	 * Löscht einen vorhandenen Termin.
	 * @param event	Terminobjekt, welches gelöscht werden soll
	 */
	public DeleteEventResponse DeleteEvent(Event event);
	
	/**
	 * Speichert einen neuen Benutzer.
	 * @param user	Benutzerobjekt, welches gespeichert werden soll
	 */
	public RegistrateUserResponse RegistrateUser(User user);
	
	/**
	 * Prüft, ob der Benutzer existiert.
	 * @param user
	 */
	public CheckUserResponse CheckUser(User user);
}
