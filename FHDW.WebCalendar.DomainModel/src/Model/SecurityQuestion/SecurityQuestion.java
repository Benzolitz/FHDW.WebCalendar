package Model.SecurityQuestion;

import Model.BaseObject;

public class SecurityQuestion extends BaseObject
{
	private String name;
	
	public void SetName(String p_name)
	{
		name = p_name;
	}
	
	public String GetName()
	{
		return name;
	}
}
