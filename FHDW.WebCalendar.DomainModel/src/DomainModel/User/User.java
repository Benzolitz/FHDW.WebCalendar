package DomainModel.User;

import DomainModel.Base.BaseObject;

/**
 * @author Eduard Kress, Lucas Engel, Frederik Heinrichs
 */
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
    public UserSecurity GetUserSecurity()
    {
        return userSecurity;
    }

    /**
     * @param p_userSecurity the userSecurity to Set
     */
    public void SetUserSecurity(UserSecurity p_userSecurity)
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
    public String GetFirstname()
    {
        return firstname;
    }

    /**
     * @param p_firstname
     */
    public void SetFirstname(String p_firstname)
    {
        firstname = p_firstname;
    }

    /**
     * @return
     */
    public String GetLastname()
    {
        return lastname;
    }

    /**
     * @param p_lastname
     */
    public void SetLastname(String p_lastname)
    {
        lastname = p_lastname;
    }

    /**
     * @return
     */
    public String GetPhonenumber()
    {
        return phonenumber;
    }

    /**
     * @param p_phonenumber
     */
    public void SetPhonenumber(String p_phonenumber)
    {
        phonenumber = p_phonenumber;
    }

}