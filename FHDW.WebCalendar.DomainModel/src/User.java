
public class User
{
	private String Username;
	private String EMail;
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	private String SecurityQuestion;
	private String SecurityAnswer;
	
	public void SetUsername(String username)
	{
		Username = username;
	}
	
	public String GetUsername()
	{
		return Username;
	}
	
	public void SetEMail(String email)
	{
		EMail = email;
	}
	
	public String GetEMail()
	{
		return EMail;
	}
	
	public void SetFirstName(String firstName)
	{
		FirstName = firstName;
	}
	
	public String GetFirstName()
	{
		return FirstName;
	}
	
	public void SetLastName(String lastName)
	{
		LastName = lastName;
	}
	
	public String GetLastName()
	{
		return LastName;
	}
	
	public void SetPhoneNumber(String phoneNumber)
	{
		PhoneNumber = phoneNumber;
	}
	
	public String GetPhoneNumber()
	{
		return PhoneNumber;
	}
	
	public void SetSecurityQuestion(String securityQuestion)
	{
		SecurityQuestion = securityQuestion;
	}
	
	public String GetSecurityQuestion()
	{
		return SecurityQuestion;
	}

	public void SetSecurityAnswer(String securityAnswer)
	{
		SecurityAnswer = securityAnswer;
	}
	
	public String GetSecurityAnswer()
	{
		return SecurityAnswer;
	}
	
}