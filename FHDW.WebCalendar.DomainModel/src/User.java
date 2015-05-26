public class User
{
	private int id;
	private String username;
	private String email;
	private String password;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private int SecurityQuestionId;
	private String SecurityAnswer;
	
	public void SetId(int p_id)
	{
		id = p_id;
	}
	
	public int GetId()
	{
		return id;
	}
	
	public void SetUsername(String p_username)
	{
		username = p_username;
	}
	
	public String GetUsername()
	{
		return username;
	}
	
	public void SetEMail(String p_email)
	{
		email = p_email;
	}
	
	public String GetEMail()
	{
		return email;
	}
	
	public String GetPassword()
	{
		return password;
	}
	
	public void SetPassword(String p_password)
	{
		password = p_password;
	}
	
	public void SetFirstName(String p_firstName)
	{
		FirstName = p_firstName;
	}
	
	public String GetFirstName()
	{
		return FirstName;
	}
	
	public void SetLastName(String p_lastName)
	{
		LastName = p_lastName;
	}
	
	public String GetLastName()
	{
		return LastName;
	}
	
	public void SetPhoneNumber(String p_phoneNumber)
	{
		PhoneNumber = p_phoneNumber;
	}
	
	public String GetPhoneNumber()
	{
		return PhoneNumber;
	}
	
	public void SetSecurityQuestion(int p_securityQuestionId)
	{
		SecurityQuestionId = p_securityQuestionId;
	}
	
	public int GetSecurityQuestion()
	{
		return SecurityQuestionId;
	}
	
	public void SetSecurityAnswer(String p_securityAnswer)
	{
		SecurityAnswer = p_securityAnswer;
	}
	
	public String GetSecurityAnswer()
	{
		return SecurityAnswer;
	}
	
}