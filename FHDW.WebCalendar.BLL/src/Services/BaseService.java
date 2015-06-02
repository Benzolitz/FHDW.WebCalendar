package Services;

import Repository.JDBC.WebCalendarRepo;


/**
 * @author Frederik Heinrichs
 * 
 * Abstrakter Basis Service für die Verwaltung gemeinsamer Attribute
 * 
 */
public abstract class BaseService {

	public static WebCalendarRepo repo;

	/**
	 * Singelton wür das Repository
	 * 
	 * @return WebCalendarRepo
	 * 
	 * @see WebCalendarRepo
	 */
	public static WebCalendarRepo GetRepo() {
		return repo == null ? new WebCalendarRepo() : repo;
	}
	
}
