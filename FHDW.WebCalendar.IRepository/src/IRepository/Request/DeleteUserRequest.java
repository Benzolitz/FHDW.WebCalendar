package IRepository.Request;

public class DeleteUserRequest extends IRequest
{
	private int userId;
	
	public DeleteUserRequest()
	{
		
	}
	
	public DeleteUserRequest(int p_userId)
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
