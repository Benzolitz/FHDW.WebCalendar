
public class GetUserPasswordResponse extends IResponse
{
	private String password;

	public void SetPassword(String p_password)
	{
		password = p_password;
	}
	
	public String GetPassword()
	{
		return password;
	}
}
