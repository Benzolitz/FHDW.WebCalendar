package Model.User;

public class UserSecurity
{	
	
	/**
	 * @return the password
	 */
	public String GetPassword()
	{
		return password;
	}

	/**
	 * @param p_password the password to Set
	 */
	public void SetPassword(String p_password)
	{
		password = p_password;
	}

	/**
	 * @return the securityQuestionId
	 */
	public int GetSecurityQuestionId()
	{
		return securityQuestionId;
	}

	/**
	 * @param p_securityQuestionId the securityQuestionId to Set
	 */
	public void SetSecurityQuestionId(int p_securityQuestionId)
	{
		securityQuestionId = p_securityQuestionId;
	}

	/**
	 * @return the securityQuestion
	 */
	public String GetSecurityQuestion()
	{
		return securityQuestion;
	}

	/**
	 * @param p_securityQuestion the securityQuestion to Set
	 */
	public void SetSecurityQuestion(String p_securityQuestion)
	{
		securityQuestion = p_securityQuestion;
	}

	/**
	 * @return the securityAnswer
	 */
	public String GetSecurityAnswer()
	{
		return securityAnswer;
	}

	/**
	 * @param p_securityAnswer the securityAnswer to Set
	 */
	public void SetSecurityAnswer(String p_securityAnswer)
	{
		securityAnswer = p_securityAnswer;
	}

	private String password;
	private int securityQuestionId;
	private String securityQuestion;
	private String securityAnswer;
	
	public UserSecurity(String password, int questionId, String question, String answer) {
		this.password = password;
		this.securityQuestion = question;
		this.securityAnswer = answer;
		this.securityQuestionId = questionId;
		
	}
	
}
