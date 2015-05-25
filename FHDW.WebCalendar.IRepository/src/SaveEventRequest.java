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

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String p_location)
	{
		location = p_location;
	}

	public Date getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Date p_starttime)
	{
		starttime = p_starttime;
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date p_endtime)
	{
		endtime = p_endtime;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String p_message)
	{
		message = p_message;
	}

	public Collection<String> getCategories()
	{
		return categories;
	}

	public void setCategories(Collection<String> p_categories)
	{
		categories = p_categories;
	}

	public int getCreatorId()
	{
		return creatorId;
	}

	public void setCreatorId(int p_creatorId)
	{
		creatorId = p_creatorId;
	}

	public int getCalendarId()
	{
		return calendarId;
	}

	public void setCalendarId(int p_calendarId)
	{
		calendarId = p_calendarId;
	}

}
