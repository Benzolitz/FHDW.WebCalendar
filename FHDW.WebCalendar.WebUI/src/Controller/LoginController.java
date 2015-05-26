package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.user.UserService;

import java.io.IOException;

@WebServlet ("/LoginController")
public class LoginController extends javax.servlet.http.HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Login(response, username, password);
	}
	
	private void Login(HttpServletResponse p_response, String p_username, String p_password) throws IOException
	{
		try
		{
			UserService userService = new UserService();
			
			if (userService.CheckLoginData(p_username, p_password))
			//if (p_username.equals(p_password))
			{
				Cookie cookie = new Cookie("username", p_username);
				cookie.setMaxAge(3600);
				p_response.addCookie(cookie);
				p_response.sendRedirect("Calendar.jsp");
			}
			else
			{
				p_response.sendRedirect("Login.jsp?username=" + p_username + "&message=Der Benutzername, oder das Passwort sind falsch!");
			}
		}
		catch (Exception exception)
		{
			p_response.sendRedirect("Login.jsp?username=" + p_username + "&message=" + exception.getMessage());
		}
	}
}
