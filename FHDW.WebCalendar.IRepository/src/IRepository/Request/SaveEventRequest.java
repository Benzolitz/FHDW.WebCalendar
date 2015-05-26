package IRepository.Request;
import java.util.Collection;
import java.util.Date;

public class SaveEventRequest extends IRequest
{
	private String title;
	private String location;
	private Date starttime;
	private Date endtime;
	private String message;
	private Collection<String> categories;
	private int creatorId;
	private int calendarId;
	
	public SaveEventRequest()
	{
		
	}
	
	public SaveEventRequest(String p_title, String p_location, Date p_starttime, Date p_endtime, String p_message, Collection<String> p_categories, int p_creatorId, int p_calendarId)
	{
		title = p_title;
		location = p_location;
		starttime = p_starttime;
		endtime = p_endtime;
		message = p_message;
		categories = p_categories;
		creatorId = p_creatorId;
		calendarId = p_calendarId;
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

	public int GetCreatorId()
	{
		return creatorId;
	}

	public void SetCreatorId(int p_creatorId)
	{
		creatorId = p_creatorId;
	}

	public int GetCalendarId()
	{
		return calendarId;
	}

	public void SetCalendarId(int p_calendarId)
	{
		calendarId = p_calendarId;
	}

}
