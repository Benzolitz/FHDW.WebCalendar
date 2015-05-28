package Services;

import Repository.JDBC.WebCalendarRepo;


/**
 * @author Frederik Heinrichs
 * TODO: Kommentar schreiben
 * 
 */
public abstract class BaseService {

	public static WebCalendarRepo repo;

	/**
	 * gelt Singelton for WebCalendarRepo repo
	 * @see WebCalendarRepo
	 * 
	 * @return WebCalendarRepo
	 */
	public static WebCalendarRepo GetRepo() {
		if (repo == null) {
			repo = new WebCalendarRepo();
		}
		return repo;
	}
	
}
