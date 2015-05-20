import java.time.LocalTime;

public class Event
{
	private LocalTime EventStart;
	private LocalTime EventEnd;
	private String Location;
	private User Creator;
	private LocalTime CreationTime;
	private String Category;
	private String Message;
	
	public void SetEventStart(LocalTime eventStart)
	{
		EventStart = eventStart;
	}
	
	public LocalTime GetEventStart()
	{
		return EventStart;
	}
	
	public void SetEventEnd(LocalTime eventEnd)
	{
		EventEnd = eventEnd;
	}
	
	public LocalTime GetEventEnd()
	{
		return EventEnd;
	}
	
	public void SetLocation(String location)
	{
		Location = location;
	}
	
	public String GetLocation()
	{
		return Location;
	}
	
	public void SetCreator(User creator)
	{
		Creator = creator;
	}
	
	public User GetCreator()
	{
		return Creator;
	}
	
	public void SetCreationTime(LocalTime creationTime)
	{
		CreationTime = creationTime;
	}
	
	public LocalTime GetCreationTime()
	{
		return CreationTime;
	}
	
	public void SetCategory(String category)
	{
		Category = category;
	}
	
	public String GetCategory()
	{
		return Category;
	}
	
	public void SetMessage(String message)
	{
		Message = message;
	}
	
	public String GetMessage()
	{
		return Message;
	}
	
}
