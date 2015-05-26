package IRepository.Request;

public class GetSecurityQuestionRequest extends IRequest
{
	private String usernameOrEmail;
	
	public GetSecurityQuestionRequest()
	{
		
	}
	
	public GetSecurityQuestionRequest(String p_usernameOrEmail)
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
