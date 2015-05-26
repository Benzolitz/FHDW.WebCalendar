
public class GetEventsForUserRequest extends IRequest
{
	private int userId;
	private int calendarId;
	
	public GetEventsForUserRequest()
	{
		
	}
	
	public GetEventsForUserRequest(int p_calendarId, int p_userId)
	{
		calendarId = p_calendarId;
		userId = p_userId;
	}
	
	public void SetUserId(int p_userId)
	{
		userId = p_userId;
	}
	
	public int GetUserId()
	{
		return userId;
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
