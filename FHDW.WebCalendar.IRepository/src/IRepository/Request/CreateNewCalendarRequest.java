package IRepository.Request;

public class CreateNewCalendarRequest extends IRequest
{
	private int userId;
	private String calendarName;
	
	public CreateNewCalendarRequest()
	{
		
	}
	
	public CreateNewCalendarRequest(int p_userId, String p_calendarName)
	{
		userId = p_userId;
		calendarName = p_calendarName;
	}
	
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
