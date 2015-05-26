
public class GetEventDetailedRequest extends IRequest
{
	private int eventId;
	
	public GetEventDetailedRequest()
	{
		
	}
	
	public GetEventDetailedRequest(int p_eventId)
	{
		eventId = p_eventId;
	}
	
	public void SetEventId(int p_eventId)
	{
		eventId = p_eventId;
	}
	
	public int GetEventId()
	{
		return eventId;
	}
}
