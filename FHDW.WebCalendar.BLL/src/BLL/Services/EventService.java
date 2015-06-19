package BLL.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import BLL.Exceptions.DatabaseException;
import BLL.Exceptions.IOException;
import BLL.Exceptions.NotFound;
import BLL.Helper.EventHelper;
import DomainModel.Calendar.Calendar;
import DomainModel.Calendar.Event;

/**
 * Business Logik für das Verwalten von Terminen.
 * 
 * @author Frederik Heinrichs
 */
public class EventService extends BaseService
{

    /**
     * Erstellt ein Event in einem Kalender eines Benutzers
     * 
     * @param p_event
     * 
     * @return true wenn das Event erstellt werden konnte
     *  
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws IOException, Wenn die pflichtfelder im Event leer waren
     * @throws NotFound, wenn der Benutzer nicht in der Datenbank gefunden wurde
     * 
     * @see Event
     */
    public boolean CreateEvent(Event p_event) throws DatabaseException,
            IOException, NotFound
    {
        EventHelper.checkEventData(p_event); // throws IOException
        try
        {
            HashMap<Integer, Integer> p_requiredUser = CheckEventUserList(p_event
                    .GetRequiredUser());
            p_requiredUser.put(p_event.GetCreatorId(), p_event.GetCalendarId());
            GetRepo()
                    .SaveEvent(p_event.GetTitle(), p_event.GetLocation(),
                            p_event.GetStartTime(), p_event.GetEndTime(),
                            p_event.GetMessage(), p_event.GetCategory(),
                            p_event.GetCreatorId(), p_event.GetCalendarId(),
                            p_requiredUser,
                            CheckEventUserList(p_event.GetOptionalUser()));
            return true;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Ändert die Werte eines Events für einen Benutzer
     * 
     * @param p_event
     * 
     * @return
     * 
     * @throws IOException
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws NotFound, wenn der Benutzer nicht in der Datenbank gefunden wurde
     */
    public boolean ChangeEvent(Event p_event) throws IOException,
            DatabaseException, NotFound
    {
        EventHelper.checkEventData(p_event);
        try
        {
            HashMap<Integer, Integer> p_requiredUser = CheckEventUserList(p_event
                    .GetRequiredUser());
            p_requiredUser.put(p_event.GetCreatorId(), p_event.GetCalendarId());
            GetRepo().UpdateEvent(p_event.GetId(), p_event.GetTitle(),
                    p_event.GetLocation(), p_event.GetStartTime(),
                    p_event.GetEndTime(), p_event.GetMessage(),
                    p_event.GetCategory(), p_requiredUser,
                    CheckEventUserList(p_event.GetOptionalUser()));
            return true;
        }
        catch (SQLException e)
        {
            throw new DatabaseException(e);
        }
    }

    /**
     * Löscht ein Event für einen Benutzer
     * 
     * @param p_eventId
     * @param p_userId
     * 
     * @return true, wenn das löschen erfolgreich war oder der Termin nicht mehr existiert
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     */
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

    /**
     * Gibt ein passendes Event aus der Datenbank zurück
     * 
     * @param p_eventId
     * 
     * @return
     * 
     * @throws NotFound, wenn das Event in der Datenbank nicht gefunden werden konnte
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     */
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

    /**
     * Ermittelt alle Eingetragenen Benutzer aus einer Liste und<br>
     * erstellt eine Hashmap mit userId und der ID des DefaultKalenders für den entsprechenden Benutzer aus der Liste.
     * 
     * @param p_userNameList
     * 
     * @return HashMap <userId, defaultCalendarId>
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws NotFound, wenn Ein Benutzer der Liste nicht in der Datenbank existiert
     */
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
     * Suche nach Events<br>
     * Es werden alle Event.Title und Event.Category durchsucht
     * 
     * @param p_calendar
     * @param searchString
     * 
     * @return volle oder leere Collection mit events (Nie NUll)
     * 
     * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank
     *             entstanden ist
     * @throws NotFound, wenn der Benutzer nicht gefunden werden konnte
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
