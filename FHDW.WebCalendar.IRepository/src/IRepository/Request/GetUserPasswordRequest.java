package IRepository.Request;

public class GetUserPasswordRequest extends IRequest
{
	private int userId;
	
	public GetUserPasswordRequest()
	{
		
	}
	
	public GetUserPasswordRequest(int p_userId)
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
