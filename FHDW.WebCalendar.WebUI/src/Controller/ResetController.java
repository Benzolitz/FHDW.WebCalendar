package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException
	{
		CheckEnterdData(p_request, p_response);
	}
	
	private void CheckEnterdData(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
	{
		Integer userId = null;
		
		try
		{
			String name = p_request.getParameter("username");
			userId = userService.GetUserId(name);
		}
		catch (Exception e)
		{}
		
		try
		{
			String mail = p_request.getParameter("usermail");
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
		p_response.sendRedirect(redirect);
		
	}
}
