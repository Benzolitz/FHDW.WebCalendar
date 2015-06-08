package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Exceptions.DatabaseException;
import Exceptions.NotFound;
import Model.Calendar.Calendar;
import Model.Calendar.Event.EventCalendarView;

/**
 * @author Frederik Heinrichs
 * TODO: Kommentar schreiben
 */
public class SearchService extends BaseService
{	
	public SearchService() {
		//nothing to init
	}
	
	
	/**
	 * @param p_calendar
	 * @param searchString
	 * 
	 * @return
	 * @throws DatabaseException 
	 * @throws NotFound 
	 */
	public Collection<EventCalendarView> searchEvents(int p_userId, String p_searchString) throws DatabaseException, NotFound {
		CalendarService calendarService = new CalendarService();
		
		// Setze den SuchZeitram von 1.1.2000 - 31.12.2050
		java.util.Calendar DateFrom = java.util.Calendar.getInstance();
		DateFrom.set(1990, 1, 1, 0, 0);		
		java.util.Calendar DateTo = java.util.Calendar.getInstance();
		DateFrom.set(2050, 1, 1, 0, 0);;
		
		Collection<EventCalendarView> result_events = new ArrayList <EventCalendarView>();	
		Collection<Calendar> userCalendar = calendarService.GetAllUserCalendar(p_userId); // throws DatabaseException
		
		for (Calendar c : userCalendar) {
			Collection <EventCalendarView> calendarEvents;
			try
			{
				calendarEvents = GetRepo().GetEventsForUser(c.GetId(), p_userId, DateFrom, DateTo);
				
				if (calendarEvents != null && !calendarEvents.isEmpty()) {
					result_events.addAll(calendarEvents);
				}
			}
			catch (SQLException e)
			{
				throw new DatabaseException(e);
			}
		}

		return result_events;
	}
	
}
