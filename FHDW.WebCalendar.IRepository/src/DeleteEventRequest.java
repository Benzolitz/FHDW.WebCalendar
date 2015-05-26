
public class DeleteEventRequest extends IRequest
{
	private int eventId;
	
	public DeleteEventRequest()
	{
		
	}
	
	public DeleteEventRequest(int p_eventId)
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
