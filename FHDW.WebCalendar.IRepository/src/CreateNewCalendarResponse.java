
public class CreateNewCalendarResponse extends IResponse
{
	private int calendarId;

	public void SetCalendarId(int p_calendarId)
	{
		calendarId = p_calendarId;
	}
	
	public int GetCalendarId()
	{
		return calendarId;
	}
}
