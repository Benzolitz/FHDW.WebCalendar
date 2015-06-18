package BLL.Services;

import Repository.MySQL.WebCalendarRepo;

/**
 * @author Frederik Heinrichs
 * Abstrakter Basis Service f�r die Verwaltung gemeinsamer Attribute 
 */
public abstract class BaseService
{
    public static WebCalendarRepo repo;
    private UserService userService;
    private CalendarService calendarService;

    /**
     * Singelton f�r den privaten Kalender Service
     * 
     * @return the calendarService
     */
    public CalendarService GetCalendarService()
    {
        return calendarService == null ? new CalendarService()
                : calendarService;
    }

    /**
     * Singelton f�r den privaten Userservice
     * 
     * @return the userService
     */
    public UserService GetUserService()
    {
        return userService == null ? new UserService() : userService;
    }

    /**
     * Singelton w�r das Repository
     * <br>
     * !Hinweis! Hier k�nnten unterschiedliche Implementierungen hinterlegt werden
     * 
     * @return WebCalendarRepo
     * 
     * @see WebCalendarRepo
     */
    public static WebCalendarRepo GetRepo()
    {
        return repo == null ? new WebCalendarRepo() : repo;
    }
}
