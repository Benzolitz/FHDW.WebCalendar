import java.util.Date;
import java.util.List;

public class Event
{
	private Date startTime;
	private Date endTime;
	private String location;
	private User creator;
	private Date creationTime;
	private List<String> category;
	private String message;
	private List<String> requiredUser;
	private List<String> optionalUser;

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

	public void SetCategory(List<String> p_category)
	{
		category = p_category;
	}

	public List<String> GetCategory()
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

	public List<String> GetRequiredUser() {
		return requiredUser;
	}

	public void SetRequiredUser(List<String> p_requiredUser) {
		requiredUser = p_requiredUser;
	}

	public List<String> GetOptionalUser() {
		return optionalUser;
	}

	public void SetOptionalUser(List<String> p_optionalUser) {
		optionalUser = p_optionalUser;
	}

}
