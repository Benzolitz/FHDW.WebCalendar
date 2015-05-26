package Services;

import Repository.JDBC.WebCalendarRepo;


/**
 * @author Frederik Heinrichs
 * 
 */
public abstract class BaseService {

	public static WebCalendarRepo repo;

	public static WebCalendarRepo GetRepo() {
		if (repo == null) {
			repo = new WebCalendarRepo();
		}
		return repo;
	}
	
}
