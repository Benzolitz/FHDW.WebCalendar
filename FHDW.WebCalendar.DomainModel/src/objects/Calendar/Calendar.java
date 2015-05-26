package objects.Calendar;

import objects.BaseObject;


public class Calendar extends BaseObject
{

	private int ownerId;
	private String name;

	
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
