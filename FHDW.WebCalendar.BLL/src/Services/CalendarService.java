package Services;

import java.util.ArrayList;
import java.util.Collection;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Helper.CalendarHelper;
import Model.Calendar.Calendar;
import Model.Calendar.Event.EventCalendarView;

/**
 * @author Frederik Heinrichs
 * Buiniss Logik f�r die Anzeige eines Kalenders
 */
public class CalendarService extends BaseService 
{
	static final String DEFAULT_CALENDARNAME = "DEFAULT";
	
	public CalendarService () {
		// nothing to init
	}
	
	
	/**
	 * Erstellt einen Kalender f�r einen Benutzer
	 * 
	 * @param p_userId
	 * @param p_calenderName
	 * 
	 * @return die generierte id des Erstellten Kalenders
	 * 
	 * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 * @throws IOException, wenn der eingebene Kalendername leer war
	 * 
	 * @see CalendarHelper#checkCalendarName(String)
	 */
	public int CreateCalendar(int p_userId, String p_calenderName) throws DatabaseException, IOException {	
		CalendarHelper.checkCalendarName(p_calenderName); // throws IOException
		try
		{
			Integer result_calenderId = GetRepo().CreateNewCalendar(p_userId, p_calenderName);
			
			if (result_calenderId == null || result_calenderId <= 0) {
				throw new DatabaseException();
			}
			
			return result_calenderId;
		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * L�scht einen Kalender aus der Datenbank
	 * 
	 * @param p_calendarId
	 * 
	 * @return
	 * 
	 * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 */
	public boolean deleteCalendar(int p_calendarId) throws DatabaseException {
		try
		{
			GetRepo().DeleteCalendar(p_calendarId);
			return true;
		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}
	}
	
	
	/**
	 * L�d alle Events eines Benutzers die in dem angegeben Zeitraum liegen
	 * 
	 * @param p_userId
	 * @param p_DateFrom
	 * @param p_DateTo
	 * 
	 * @return Liste aller Events zu einem Benutzer, asu dem angegeben Zeitraum<br>
	 * !!WICHTIG:  Liste kann leer, aber nicht null sein!!
	 * 
	 * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 * @throws NotFound wirft einen Fehler wenn der Benutzer nicht exisitiert
	 * 
	 * @see CalendarService#GetAllEvents(int)
	 */
	public Collection<EventCalendarView> GetEventsFromTo(int p_userId, int p_calendarId, java.util.Calendar p_DateFrom, java.util.Calendar p_DateTo) throws DatabaseException, NotFound {
		Collection<EventCalendarView> result_events = new ArrayList <EventCalendarView>();	
		try{
			result_events = GetRepo().GetEventsForUser(p_calendarId, p_userId, p_DateFrom, p_DateTo);
			
			return result_events;
		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}
	}	
	
	/**
	 * L�d eine Liste aller Kalender, eines Benutzers, aus der Datenbank
	 * 
	 * @param p_userId
	 * 
	 * @return Eine liste aller kalender eines Benutzer<br>
	 * !!WICHTIG: Liste kann leer, aber nicht null sein!!
	 * 
	 * @throws DatabaseException, wenn ein unbekannter Fehler in der Datenbank entstanden ist
	 * @throws NotFound wirft einen Fehler wenn der Benutzer nicht exisitiert
	 */
	public Collection<Calendar> GetAllUserCalendar(int p_userId) throws DatabaseException {		
		Collection<Calendar> result_userCalendar = new ArrayList <Calendar>();
		try
		{
			result_userCalendar = GetRepo().GetAllUserCalendar(p_userId);
			return result_userCalendar;	
		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}
	}


	public int GetDefaultCalendarId(int p_userId) throws DatabaseException
	{
		Collection<Calendar> userCalendar = GetAllUserCalendar(p_userId);
		int defaultCalendarID = -1;
		for (Calendar calendar : userCalendar) {
			if (calendar.GetName().equals(CalendarService.DEFAULT_CALENDARNAME)) {
				defaultCalendarID = calendar.GetId();
			}
		}

		return defaultCalendarID;
	}
}
