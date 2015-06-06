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
	private UserService userService;
		
	/**
	 * Singelton für den privaten Userservice
	 * 
	 * @return the userService
	 */
	public UserService GetUserService()
	{
		if (this.userService == null) {
			this.userService = new UserService();
		}
		return this.userService;
	}
	
	
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
