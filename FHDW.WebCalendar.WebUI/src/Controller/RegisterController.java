package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User.User;
import Model.User.UserSecurity;
import Services.*;

@WebServlet ("/RegisterController")
public class RegisterController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private RegistrationService registrationService;
	
	public RegisterController()
	{
		registrationService = new RegistrationService();
	}
	
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException
	{
		String message = "";
		try
		{
			User user = new User();
			user.SetUsername(p_request.getParameter("txtUsername"));
			user.SetEMail(p_request.getParameter("txtUsermail"));
			user.SetFirstname(p_request.getParameter("txtUserFirstname"));
			user.SetLastname(p_request.getParameter("txtUserLastname"));
			user.SetPhonenumber(p_request.getParameter("txtUserphone"));
			
			UserSecurity userSecurity = new UserSecurity();
			userSecurity.SetPassword(p_request.getParameter("txtPassword"));
			userSecurity.SetSecurityQuestionId(Integer.parseInt(p_request.getParameter("selSecurityQuestion")));
			userSecurity.SetSecurityQuestion("");
			userSecurity.SetSecurityAnswer(p_request.getParameter("txtUserSecurityAnswer"));
			user.SetUserSecurity(userSecurity);
			registrationService.RegisterNewUser(user);
			
			message = "Der Benutzer '" + user.GetUsername() + "' wurde erfolgreich erstellt!";
		}
		catch (Exception e)
		{
			message = e.getMessage();
		}
		
		p_response.sendRedirect("Login.jsp?message=" + message);
	}
}
