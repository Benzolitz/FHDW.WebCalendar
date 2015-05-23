import java.util.Collection;

public class GetAllUserCalendarResponse extends IResponse
{
	private Collection<Calendar> calendars;
	
	public void SetCalendars(Collection<Calendar> p_calendars)
	{
		calendars = p_calendars;
	}
	
	public Collection<Calendar> getCalendars()
	{
		return calendars;
	}
}
