package Model.User;

/**
 * @author Eduard Kress, Lucas Engel, Frederik Heinrichs
 */
public class UserSecurity
{
    private String password;
    private int securityQuestionId;
    private String securityQuestion;
    private String securityAnswer;

    public String GetPassword()
    {
        return password;
    }

    public void SetPassword(String p_password)
    {
        password = p_password;
    }

    public int GetSecurityQuestionId()
    {
        return securityQuestionId;
    }

    public void SetSecurityQuestionId(int p_securityQuestionId)
    {
        securityQuestionId = p_securityQuestionId;
    }

    public String GetSecurityQuestion()
    {
        return securityQuestion;
    }

    public void SetSecurityQuestion(String p_securityQuestion)
    {
        securityQuestion = p_securityQuestion;
    }

    public String GetSecurityAnswer()
    {
        return securityAnswer;
    }

    public void SetSecurityAnswer(String p_securityAnswer)
    {
        securityAnswer = p_securityAnswer;
    }
}
