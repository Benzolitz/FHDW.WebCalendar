import com.sun.org.apache.regexp.internal.recompile;


public class Calendar
{
	private int id;
	private int ownerId;
	private String name;
	
	public void SetId(int p_id)
	{
		id = p_id;
	}
	
	public int GetId()
	{
		return id;
	}
	
	public void SetOwnerId(int p_ownerId)
	{
		ownerId = p_ownerId;
	}
	
	public int GetOwnerId()
	{
		return ownerId;
	}
	
	public void SetName(String p_name)
	{
		name = p_name;
	}
	
	public String GetName()
	{
		return name;
	}
}
