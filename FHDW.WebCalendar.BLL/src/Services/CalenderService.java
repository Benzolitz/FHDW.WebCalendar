package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import Exceptions.DatabaseException;
import Model.Calendar.Calendar;
import Model.Calendar.Event.Event;
import Model.Calendar.Event.EventCalendarView;


public class CalenderService extends BaseService 
{
	static final String DEFAULT_CALENDARNAME = "DEFAULT";
	
	public CalenderService () {
		// nothing to init
	}
	
	
	public int createCalendar(int p_userId, String p_calenderName) throws DatabaseException {	
		try
		{
			Integer result_calenderId = GetRepo().CreateNewCalendar(p_userId, p_calenderName);
			
			if (result_calenderId == null || result_calenderId <= 0) {
				//TODO: Benutzer wieder löschen, da dieser hier ja bereits angelegt wurde?
				throw new DatabaseException("Kalender für den Benutzer mit der Id: " + p_userId + "konnte nicht erstellt werden");
			}
			
			return result_calenderId;
		}
		catch (SQLException e)
		{
			throw new DatabaseException(e.getMessage(), e);
		}
	}
	
	
		
	public Calendar getCalendar(int p_userId) {
		//TODO: comming soon
		return new Calendar();
	}
	
	public Collection<EventCalendarView> getAllEvents(Calendar p_calelndar) {
		Collection <EventCalendarView> result_events = new ArrayList<EventCalendarView>();
		try
		{
			result_events = GetRepo().GetEventsForUser(p_calelndar.GetId(), p_calelndar.GetOwnerId());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//TODO: Exception handling was ist wenn der Benutzer nicht exitiert
		return result_events;
	}
	
	public List<Event> getEventsFromTo(Calendar p_calelndar, Date p_DateFrom, Date p_DateTo){
		//TODO:coming soon
		return new ArrayList<Event>();
	}
	
	public List<Event> getCurrentWeek(Calendar p_calelndar) {
		//TODO:coming soon
		return new ArrayList<Event>();	
	}		
}
