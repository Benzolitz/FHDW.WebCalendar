
public class DeleteCalendarRequest extends IRequest
{
	private int calendarId;
	
	public DeleteCalendarRequest()
	{
		
	}
	
	public DeleteCalendarRequest(int p_calendarId)
	{
		calendarId = p_calendarId;
	}

	public void SetCalendarId(int p_calendarId)
	{
		calendarId = p_calendarId;
	}
	
	public int GetCalendarId()
	{
		return calendarId;
	}
}
