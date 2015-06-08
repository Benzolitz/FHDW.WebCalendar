package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.PasswordResetService;

@WebServlet ("/ResetSecurityController")
public class ResetSecurityController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PasswordResetService passwordResetService;
	
	public ResetSecurityController()
	{
		passwordResetService = new PasswordResetService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String userId = request.getParameter("userId");
			String password = request.getParameter("newPassword");
			String securityAnswer = request.getParameter("securityAnswer");
			
			if (passwordResetService.checkSecurityAnswer(Integer.parseInt(userId), securityAnswer))
			{
				passwordResetService.changeUserPasword(Integer.parseInt(userId), password);
			}
			
			response.sendRedirect("Login.jsp?message=Das Passwort wurde erfolgreich geaendert!");
		}
		catch (Exception e)
		{
		}
	}
	
}
