
public class GetEventsForUserRequest extends IRequest
{
	private String usernameOrEmail;
	
	public void SetUsernameOrEmail(String p_usernameOrEmail)
	{
		usernameOrEmail = p_usernameOrEmail;
	}
	
	public String GetUsernameOrEmail()
	{
		return usernameOrEmail;
	}
}
