package Controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.ExceptionController;
import Exceptions.DatabaseException;
import Exceptions.NotFound;
import Services.UserService;

/**
 * Servlet implementation class PasswordReset
 */
@WebServlet ("/ResetController")
public class ResetController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	UserService userService;
	
	public ResetController()
	{
		userService = new UserService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Integer userId = null;
		
		Enumeration<String> test = request.getParameterNames();
		
		try
		{
			String name = request.getParameter("username");
			userId = userService.GetUserId(name);
		}
		catch (Exception e)
		{}
		
		try
		{
			String mail = request.getParameter("usermail");
			userId = userService.GetUserId(mail);
		}
		catch (Exception e)
		{}
		
		String redirect = "";
		if (userId != null)
		{
			redirect = "ResetSecurity.jsp?user=" + userId;
		}
		else
		{	
			redirect = "Reset.jsp?message=Der Benutzername, oder die EMailadresse, wurden nicht gefunden!";
		}
		response.sendRedirect(redirect);
	}
}
