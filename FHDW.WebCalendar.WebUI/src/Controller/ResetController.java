package Controller;

import java.io.IOException;

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
		try
		{
			userId = userService.GetUserId(request.getParameter("username"));
		}
		catch (Exception e)
		{}
		
		try
		{
			userId = userService.GetUserId(request.getParameter("usermail"));
		}
		catch (Exception e)
		{}
		
		if (userId != null)
		{
			response.sendRedirect("ResetSecurity.jsp?user=" + userId);
		}
	}
}
