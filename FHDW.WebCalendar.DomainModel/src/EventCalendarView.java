import java.util.Date;
import java.util.List;


public class EventCalendarView
{
	private int id;
	private String title;
	private Date startTime;
	private Date endTime;
	private String location;
	private List<String> category;
	private boolean required;
	private int calendarId;
	
	public void SetId(int p_id)
	{
		id = p_id;
	}
	
	public int GetId()
	{
		return id;
	}
	
	public void SetTitle(String p_title)
	{
		title = p_title;
	}
	
	public String GetTitle()
	{
		return title;
	}
	
	public void SetStartTime(Date p_starTime)
	{
		startTime = p_starTime;
	}
	
	public Date GetStartTime()
	{
		return startTime;
	}
	
	public void SetEndTime(Date p_endTime)
	{
		endTime = p_endTime;
	}
	
	public Date GetEndTime()
	{
		return endTime;
	}
	
	public void SetLocation(String p_location)
	{
		location = p_location;
	}
	
	public String GetLocation()
	{
		return location;
	}
	
	public void SetCategory(List<String> p_category)
	{
		category = p_category;
	}
	
	public List<String> GetCategory()
	{
		return category;
	}
				
	public void SetCalendarId(int p_calendarId)
	{
		calendarId = p_calendarId;
	}
	
	public int GetCalendarId()
	{
		return calendarId;
	}

	public void SetRequired(boolean p_required)
	{
		required = p_required;
	}
	
	public boolean GetRequired()
	{
		return required;
	}
}
