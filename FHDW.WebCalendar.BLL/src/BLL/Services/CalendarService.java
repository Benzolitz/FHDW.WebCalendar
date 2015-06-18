package BLL.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import BLL.Exceptions.DatabaseException;
import BLL.Exceptions.IOException;
import BLL.Exceptions.NotFound;
import BLL.Helper.CalendarHelper;
import DomainModel.Calendar.Calendar;

/**
 * @author Frederik Heinrichs Buiniss Logik für die Anzeige eines Kalenders
 */
public class CalendarService extends BaseService
{
    static final String defaultCalendarName = "DEFAULT";

    /**
     * Erstellt einen Kalender für einen Benutzer
     * 
     * @param p_userId
     * @param p_calenderName
     * 
     * @return die generierte id des Erstellten Kalenders
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws IOException, wenn der eingebene Kalendername leer war
     * 
     * @see CalendarHelper#checkCalendarName(String)
     */
    public int CreateCalendar(int p_userId, String p_calenderName)
            throws DatabaseException, IOException
    {
        CalendarHelper.checkCalendarName(p_calenderName); // throws IOException
        try
        {
            Integer result_calenderId = GetRepo().CreateNewCalendar(p_userId,
                    p_calenderName);

            if (result_calenderId == null || result_calenderId <= 0)
            {
                throw new DatabaseException();
            }

            return result_calenderId;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Löscht einen Kalender aus der Datenbank
     * 
     * @param p_calendarId
     * 
     * @return
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     */
    public boolean DeleteCalendar(int p_calendarId) throws DatabaseException
    {
        try
        {
            GetRepo().DeleteCalendar(p_calendarId);
            return true;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Läd eine Liste aller Kalender, eines Benutzers, aus der Datenbank
     * 
     * @param p_userId
     * 
     * @return Eine liste aller kalender eines Benutzer<br>
     *         !!WICHTIG: Liste kann leer, aber nicht null sein!!
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws NotFound wirft einen Fehler wenn der Benutzer nicht exisitiert
     */
    public Collection<Calendar> GetAllUserCalendar(int p_userId)
            throws DatabaseException
    {
        Collection<Calendar> result_userCalendar = new ArrayList<Calendar>();
        try
        {
            result_userCalendar = GetRepo().GetAllUserCalendar(p_userId);
            return result_userCalendar;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    public int GetDefaultCalendarId(int p_userId) throws DatabaseException
    {
        Collection<Calendar> userCalendar = GetAllUserCalendar(p_userId);
        int defaultCalendarID = -1;
        for (Calendar calendar : userCalendar)
        {
            if (calendar.GetName().equals(defaultCalendarName))
            {
                defaultCalendarID = calendar.GetId();
            }
        }

        return defaultCalendarID;
    }
}
