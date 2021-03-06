package BLL.Services;

import Repository.MySQL.CalendarRepo;

/**
 * Abstrakter Basis Service f�r die Verwaltung gemeinsamer Attribute. 
 * 
 * @author Frederik Heinrichs
 */
public abstract class BaseService
{
    public static CalendarRepo repo;
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
     * @see CalendarRepo
     */
    public static CalendarRepo GetRepo()
    {
        return repo == null ? new CalendarRepo() : repo;
    }
}
