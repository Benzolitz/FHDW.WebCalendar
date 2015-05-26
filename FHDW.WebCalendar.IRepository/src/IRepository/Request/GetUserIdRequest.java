package IRepository.Request;

import IRepository.Response.IResponse;

public class GetUserIdRequest extends IResponse
{
	private String usernameOrEmail;
	
	public GetUserIdRequest()
	{
		
	}
	
	public GetUserIdRequest(String p_usernameOrEmail)
	{
		usernameOrEmail = p_usernameOrEmail;
	}
	
	public void SetUsernameOrEmail(String p_usernameOrEmail)
	{
		usernameOrEmail = p_usernameOrEmail;
	}
	
	public String GetUsernameOrEmail()
	{
		return usernameOrEmail;
	}
}
