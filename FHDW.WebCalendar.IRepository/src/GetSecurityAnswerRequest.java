
public class GetSecurityAnswerRequest extends IRequest
{
	private int userId;
	
	public GetSecurityAnswerRequest(int p_userId)
	{
		userId = p_userId;
	}

	public void SetUserId(int p_userId)
	{
		userId = p_userId;
	}
		
	public int GetUserId()
	{
		return userId;
	}
}
