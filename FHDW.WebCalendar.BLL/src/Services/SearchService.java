package Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import Exceptions.DatabaseException;
import Exceptions.NotFound;
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
	public List<EventCalendarView> searchEvents(int p_userId, String p_searchString) throws DatabaseException, NotFound {
		CalenderService calendarService = new CalenderService();
		List<EventCalendarView> result = new ArrayList<EventCalendarView>();
		
		// Setze den SuchZeitram von 1.1.2000 - 31.12.2050
		Calendar DateFrom = Calendar.getInstance();
		DateFrom.set(1990, 1, 1, 0, 0);
		
		Calendar DateTo = Calendar.getInstance();
		DateFrom.set(2050, 1, 1, 0, 0);;
		
		Collection<EventCalendarView> events = calendarService.GetEventsFromTo(0 , p_userId, DateFrom, DateTo);
		
		boolean found = false;
		for(EventCalendarView event : events) {
			
			if (event.GetTitle().contains(p_searchString)) {
				found = true;
			}
			
//			for (String category : event.GetCategory()) {
//				if (category.contains(p_searchString)) {
//					found = true;
//				}
//			}
			if (found) {
				result.add(event);
			}
		}
		return result;
	}
}
