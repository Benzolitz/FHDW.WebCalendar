package IRepository.Request;
import java.util.Collection;
import java.util.Date;


public class UpdateEventRequest extends IRequest
{
	private int eventId;
	private String title;
	private String location;
	private Date starttime;
	private Date endtime;
	private String message;
	private Collection<String> categories;
	
	public UpdateEventRequest()
	{
		
	}
	
	public UpdateEventRequest(int p_eventId, String p_title, String p_location, Date p_starttime, Date p_endtime, String p_message, Collection<String> p_categories)
	{
		eventId = p_eventId;
		title = p_title;
		location = p_location;
		starttime = p_starttime;
		endtime = p_endtime;
		message = p_message;
		categories = p_categories;
	}

	public int GetEventId()
	{
		return eventId;
	}

	public void SetEventId(int p_eventId)
	{
		eventId = p_eventId;
	}

	public String GetTitle()
	{
		return title;
	}

	public void SetTitle(String p_title)
	{
		title = p_title;
	}

	public String GetLocation()
	{
		return location;
	}

	public void SetLocation(String p_location)
	{
		location = p_location;
	}

	public Date GetStarttime()
	{
		return starttime;
	}

	public void SetStarttime(Date p_starttime)
	{
		starttime = p_starttime;
	}

	public Date GetEndtime()
	{
		return endtime;
	}

	public void SetEndtime(Date p_endtime)
	{
		endtime = p_endtime;
	}

	public String GetMessage()
	{
		return message;
	}

	public void SetMessage(String p_message)
	{
		message = p_message;
	}

	public Collection<String> GetCategories()
	{
		return categories;
	}

	public void SetCategories(Collection<String> p_categories)
	{
		categories = p_categories;
	}
	
	
}
