
public class ValidateSecurityAnswerRequest extends IRequest
{
	private String usernameOrEmail;
	private String answer;
	
	public ValidateSecurityAnswerRequest(String p_usernameOrEmail, String p_answer)
	{
		usernameOrEmail = p_usernameOrEmail;
		answer = p_answer;
	}
	
	public void SetUsernameOrEmail(String p_usernameOrEmail)
	{
		usernameOrEmail = p_usernameOrEmail;
	}
	
	public String GetUsernameOrEmail()
	{
		return usernameOrEmail;
	}
	
	public void SetAnswer(String p_answer)
	{
		answer = p_answer;
	}
	
	public String GetAnswer()
	{
		return answer;
	}
}
