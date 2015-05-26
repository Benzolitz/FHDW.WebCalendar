package IRepository.Response;
import java.util.Collection;

import Model.Calendar.Event.EventCalendarView;


public class GetEventsForUserResponse extends IResponse
{
	Collection<EventCalendarView> events;
	
	public void SetEvents(Collection<EventCalendarView> p_events)
	{
		events = p_events;
	}
	
	public Collection<EventCalendarView> GetEvents()
	{
		return events;
	}
}
