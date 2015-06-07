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
}
