public class ValidateLoginRequest
{
	private String usernameOrEmail;
	private String password;
	
	public void SetUsernameOrEmail(String p_usernameOrEmail)
	{
		usernameOrEmail = p_usernameOrEmail;
	}
	
	public String GetUsernameOrEmail()
	{
		return usernameOrEmail;
	}
	
	public void SetPassword(String p_password)
	{
		password = p_password;
	}
	
	public String GetPassword()
	{
		return password;
	}
}
