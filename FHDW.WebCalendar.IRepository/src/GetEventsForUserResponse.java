import java.util.Collection;


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
