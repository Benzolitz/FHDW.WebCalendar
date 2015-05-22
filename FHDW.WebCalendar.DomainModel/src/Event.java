import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Event
{
	private int id;
	private String title;
	private Date startTime;
	private Date endTime;
	private String location;
	private User creator;
	private Date creationTime;
	private Collection<String> category;
	private String message;
	private Collection<String> requiredUser;
	private Collection<String> optionalUser;
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
	
	public void SetCreator(User p_creator)
	{
		creator = p_creator;
	}
	
	public User GetCreator()
	{
		return creator;
	}
	
	public void SetCreationTime(Date p_creationTime)
	{
		creationTime = p_creationTime;
	}
	
	public Date GetCreationTime()
	{
		return creationTime;
	}
	
	public void SetCategory(Collection<String> p_category)
	{
		category = p_category;
	}
	
	public Collection<String> GetCategory()
	{
		return category;
	}
	
	public void SetMessage(String p_message)
	{
		message = p_message;
	}
	
	public String GetMessage()
	{
		return message;
	}
	
	public Collection<String> GetRequiredUser()
	{
		return requiredUser;
	}
	
	public void SetRequiredUser(Collection<String> p_requiredUser)
	{
		requiredUser = p_requiredUser;
	}
	
	public Collection<String> GetOptionalUser()
	{
		return optionalUser;
	}
	
	public void SetOptionalUser(Collection<String> p_optionalUser)
	{
		optionalUser = p_optionalUser;
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
