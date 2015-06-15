package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Helper.EventHelper;
import Model.Calendar.Calendar;
import Model.Calendar.Event;

public class EventService extends BaseService
{

    public boolean CreateEvent(Event event) throws DatabaseException,
            IOException, NotFound
    {
        EventHelper.checkEventData(event); // throws IOException
        try
        {
            HashMap<Integer, Integer> p_requiredUser = CheckEventUserList(event
                    .GetRequiredUser());
            p_requiredUser.put(event.GetCreatorId(), event.GetCalendarId());
            GetRepo()
                    .SaveEvent(event.GetTitle(), event.GetLocation(),
                            event.GetStartTime(), event.GetEndTime(),
                            event.GetMessage(), event.GetCategory(),
                            event.GetCreatorId(), event.GetCalendarId(),
                            p_requiredUser,
                            CheckEventUserList(event.GetOptionalUser()));
            return true;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    public boolean ChangeEvent(Event event) throws IOException,
            DatabaseException, NotFound
    {
        EventHelper.checkEventData(event);
        try
        {
            HashMap<Integer, Integer> p_requiredUser = CheckEventUserList(event
                    .GetRequiredUser());
            p_requiredUser.put(event.GetCreatorId(), event.GetCalendarId());
            GetRepo().UpdateEvent(event.GetId(), event.GetTitle(),
                    event.GetLocation(), event.GetStartTime(),
                    event.GetEndTime(), event.GetMessage(),
                    event.GetCategory(), p_requiredUser,
                    CheckEventUserList(event.GetOptionalUser()));
            return true;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    public boolean RemoveEvent(int p_eventId, int p_userId)
            throws DatabaseException
    {
        try
        {
            GetEvent(p_eventId); // Throws NotFound
            // TODO: Was is wenn das Update nicht funktioniert hat, ein boolean
            // als Rückgabe wert wäre von vorteil
            GetRepo().DeleteEvent(p_eventId, p_userId);
            return true;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
        catch (NotFound e)
        {
            // TODO: NotFound sollte trotzdem geloggt werden
            // Event existiert nicht mehr
            return true;
        }
    }

    public Event GetEvent(int p_eventId) throws NotFound, DatabaseException
    {
        try
        {
            Event result_Event = GetRepo().GetEventDetailed(p_eventId);
            if (result_Event == null)
            {
                throw new NotFound("Das Event mit der ID: " + p_eventId
                        + "Existiert nicht");
            }
            return result_Event;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    private HashMap<Integer, Integer> CheckEventUserList(
            Collection<String> p_userNameList) throws DatabaseException,
            NotFound
    {
        HashMap<Integer, Integer> result_userIds = new HashMap<Integer, Integer>();
        for (String userName : p_userNameList)
        {
            try
            {
                int userId = GetUserService().GetUserId(userName);
                int defaultCalendarID = GetCalendarService()
                        .GetDefaultCalendarId(userId);
                if (defaultCalendarID < 0)
                {
                    throw new NotFound("Termin konnte für den Teilnehmer: "
                            + userName + " nicht erstellt werden.");
                }

                result_userIds.put(userId, defaultCalendarID);
            }
            catch (IOException e)
            {
                // Benutzer mit leeren namen ignorieren
            }
        }

        return result_userIds;
    }

    /**
     * @param p_calendar
     * @param searchString
     * 
     * @return
     * @throws DatabaseException
     * @throws NotFound
     */
    public Collection<Event> SearchEvents(int p_userId, String p_searchString)
            throws DatabaseException, NotFound
    {
        // Setze den SuchZeitram von 1.1.2000 - 31.12.2050
        java.util.Calendar DateFrom = java.util.Calendar.getInstance();
        DateFrom.set(1990, 1, 1, 0, 0);
        java.util.Calendar DateTo = java.util.Calendar.getInstance();
        DateTo.set(2050, 1, 1, 0, 0);

        Collection<Event> result_events = new ArrayList<Event>();
        Collection<Calendar> userCalendar = GetCalendarService()
                .GetAllUserCalendar(p_userId); // throws DatabaseException

        for (Calendar c : userCalendar)
        {
            Collection<Event> calendarEvents;
            try
            {
                calendarEvents = GetRepo().GetEventsForUser(c.GetId(),
                        p_userId, DateFrom, DateTo);

                if (calendarEvents != null && !calendarEvents.isEmpty())
                {
                    for (Event event : calendarEvents)
                    {
                        boolean found = false;
                        if (event.GetTitle() != null
                                && event.GetTitle().contains(p_searchString))
                        {
                            found = true;
                        }

                        if (event.GetCategory() != null)
                        {
                            for (String category : event.GetCategory())
                            {
                                if (category != null
                                        && category.contains(p_searchString))
                                {
                                    found = true;
                                }
                            }
                        }

                        if (found)
                        {
                            result_events.add(event);
                        }
                    }
                }
            }
            catch (SQLException e)
            {
                throw new DatabaseException(e);
            }
        }

        return result_events;
    }

    /**
     * Läd alle Events eines Benutzers die in dem angegeben Zeitraum liegen
     * 
     * @param p_userId
     * @param p_DateFrom
     * @param p_DateTo
     * 
     * @return Liste aller Events zu einem Benutzer, asu dem angegeben Zeitraum<br>
     *         !!WICHTIG: Liste kann leer, aber nicht null sein!!
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws NotFound wirft einen Fehler wenn der Benutzer nicht exisitiert
     * 
     * @see CalendarService#GetAllEvents(int)
     */
    public Collection<Event> GetEventsFromTo(int p_userId, int p_calendarId,
            java.util.Calendar p_from, java.util.Calendar p_to)
            throws DatabaseException, NotFound
    {
        Collection<Event> result_events = new ArrayList<Event>();
        try
        {
            result_events = GetRepo().GetEventsForUser(p_calendarId, p_userId,
                    p_from, p_to);

            return result_events;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }
}
