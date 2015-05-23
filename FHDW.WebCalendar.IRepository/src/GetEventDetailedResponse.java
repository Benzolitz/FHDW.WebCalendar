
public class GetEventDetailedResponse extends IResponse
{
	private Event event;
	
	public void SetEvent(Event p_event)
	{
		event = p_event;
	}
	
	public Event GetEvent()
	{
		return event;
	}
}
