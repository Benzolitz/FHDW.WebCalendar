
public abstract class IResponse
{
	private String Message;
	private boolean Success;
	
	public void MessageFailure(String message)
	{
		Success = false;
		Message = message;
	}
	
	public void MessageSuccess(String message)
	{
		Success = true;
		Message = message;
	} 
	
	public String GetMessage()
	{
		return Message;
	}
	
	public boolean IsSuccess()
	{
		return Success;
	}
}
