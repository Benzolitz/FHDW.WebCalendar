package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.User.User;
import Model.User.UserSecurity;

@WebServlet ("/RegisterController")
public class RegisterController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException
	{
		User user = new User();
		user.SetUsername(p_request.getParameter(""));
		user.SetEMail(p_request.getParameter(""));
		user.SetFirstname(p_request.getParameter(""));
		user.SetLastname(p_request.getParameter(""));
		user.SetPhonenumber(p_request.getParameter(""));
		
		UserSecurity userSecurity = new UserSecurity();
		userSecurity.SetPassword(p_request.getParameter(""));
		userSecurity.SetSecurityQuestionId(1);
		userSecurity.SetSecurityQuestion(p_request.getParameter(""));
		userSecurity.SetSecurityAnswer(p_request.getParameter(""));
		user.SetUserSecurity(userSecurity);
		
		p_response.sendRedirect("Login.jsp?message=Benutzer erfolgreich erstellt!");
	}
}
