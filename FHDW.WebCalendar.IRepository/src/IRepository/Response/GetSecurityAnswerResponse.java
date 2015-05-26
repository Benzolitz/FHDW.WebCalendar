package IRepository.Response;

public class GetSecurityAnswerResponse extends IResponse
{
	private String answer;

	public void SetAnswer(String p_answer)
	{
		answer = p_answer;
	}
	
	public String GetAnswer()
	{
		return answer;
	}
}
