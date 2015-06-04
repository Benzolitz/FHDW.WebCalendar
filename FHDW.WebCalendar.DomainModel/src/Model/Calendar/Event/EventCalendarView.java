package Model.Calendar.Event;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import Model.BaseObject;

public class EventCalendarView extends BaseObject
{
	private String title;
	private Calendar startTime;
	private Calendar endTime;
	private String location;
	private Collection<String> category;
	private boolean required;
	private int calendarId;
	
	
	public void SetTitle(String p_title)
	{
		title = p_title;
	}
	
	public String GetTitle()
	{
		return title;
	}
	
	public void SetStartTime(Calendar p_starTime)
	{
		startTime = p_starTime;
	}
	
	public Calendar GetStartTime()
	{
		return startTime;
	}
	
	public void SetEndTime(Calendar p_endTime)
	{
		endTime = p_endTime;
	}
	
	public Calendar GetEndTime()
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
	
	public void SetCategory(Collection<String> p_category)
	{
		category = p_category;
	}
	
	public Collection<String> GetCategory()
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
