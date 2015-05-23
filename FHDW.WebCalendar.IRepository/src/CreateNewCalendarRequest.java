
public class CreateNewCalendarRequest extends IRequest
{
	private int userId;
	private String calendarName;
	
	public void SetUserId(int p_userId)
	{
		userId = p_userId;
	}
	
	public int GetUserId()
	{
		return userId;
	}

	public void SetCalendarName(String p_calendarName)
	{
		calendarName = p_calendarName;
	}
	
	public String GetCalendarName()
	{
		return calendarName;
	}	
	
}
