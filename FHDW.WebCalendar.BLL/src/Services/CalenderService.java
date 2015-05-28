package Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Calendar.Calendar;
import Model.Calendar.Event.Event;


public class CalenderService extends BaseService 
{
	public CalenderService () {
		// nothing to init
	}
	
		
	public Calendar getCalendar(int p_userId) {
		//TODO:coming soon
		return new Calendar();
	}
	
	public int createEvent(Calendar calendar, Event event) {
		//TODO:coming soon
		
		return 0;
	}
	
	public boolean changeEvent(Event event) {
		//TODO:coming soon
		
		return true;
	}
	
	public boolean removeEvent(Event event) {
		//TODO:coming soon
		
		return true;
	}
	
	public Event getEvent(int EventId, Calendar calendar) {
		//TODO:coming soon
		
		return new Event();
	}
	
	public List<Event> getAllEvents(Calendar p_calelndar) {
		//TODO:coming soon
		return new ArrayList<Event>();
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
