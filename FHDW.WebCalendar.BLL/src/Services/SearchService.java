package Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Exceptions.DatabaseException;
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
	 */
	public List<EventCalendarView> searchEvents(int p_userId, String p_searchString) throws DatabaseException {
		CalenderService calendarService = new CalenderService();
		List<EventCalendarView> result = new ArrayList<EventCalendarView>();
		Collection<EventCalendarView> events = calendarService.GetAllEvents(p_userId);
		
		boolean found = false;
		for(EventCalendarView event : events) {
			
			if (event.GetTitle().contains(p_searchString)) {
				found = true;
			} else if(event.GetLocation().contains(p_searchString)) {
				found = true;
			}
			
			for (String category : event.GetCategory()) {
				if (category.contains(p_searchString)) {
					found = true;
				}
			}
			if (found) {
				result.add(event);
			}
		}
		return result;
	}
}
