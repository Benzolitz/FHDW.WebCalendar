package objects.user;

import objects.BaseObject;

public class User extends BaseObject
{

	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private UserSecurity userSecurity;

	/**
	 * @param p_username
	 */
	public void SetUsername(String p_username)
	{
		username = p_username;
	}
	
	/**
	 * @return
	 */
	public String GetUsername()
	{
		return username;
	}
	
	/**
	 * @return the userSecurity
	 */
	public UserSecurity getUserSecurity()
	{
		return userSecurity;
	}

	/**
	 * @param p_userSecurity the userSecurity to set
	 */
	public void setUserSecurity(UserSecurity p_userSecurity)
	{
		userSecurity = p_userSecurity;
	}

	/**
	 * @param p_email
	 */
	public void SetEMail(String p_email)
	{
		email = p_email;
	}
	
	/**
	 * @return
	 */
	public String GetEMail()
	{
		return email;
	}

	/**
	 * @return
	 */
	public String getFirstname()
	{
		return firstname;
	}

	/**
	 * @param p_firstname
	 */
	public void setFirstname(String p_firstname)
	{
		firstname = p_firstname;
	}

	/**
	 * @return
	 */
	public String getLastname()
	{
		return lastname;
	}

	/**
	 * @param p_lastname
	 */
	public void setLastname(String p_lastname)
	{
		lastname = p_lastname;
	}

	/**
	 * @return
	 */
	public String getPhonenumber()
	{
		return phonenumber;
	}

	/**
	 * @param p_phonenumber
	 */
	public void setPhonenumber(String p_phonenumber)
	{
		phonenumber = p_phonenumber;
	}

		
}