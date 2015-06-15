package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import Services.*;

@WebServlet ("/LoginController")
public class LoginController extends javax.servlet.http.HttpServlet
{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private LoginService loginService;
	
	public LoginController()
	{
		userService = new UserService();
		loginService = new LoginService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Login(response, username, password);
	}
	
	private void Login(HttpServletResponse p_response, String p_username, String p_password) throws IOException
	{
		String redirect = "";
		try
		{
			if (loginService.CheckLoginData(p_username, p_password) > 0)
			{
				Cookie cookie = new Cookie("FHDW.WebCalendar", String.format("login=true&userId=%d&username=%s", userService.GetUserId(p_username), p_username));
				cookie.setMaxAge(3600);
				p_response.addCookie(cookie);
				redirect = "Calendar.jsp";
			}
			else
			{
				redirect = "Login.jsp?username=" + p_username + "&message=Der Benutzername, oder das Passwort sind falsch!";
			}
		}
		catch (Exception exception)
		{
			redirect = "Login.jsp?username=" + p_username + "&message=Der Benutzername, oder das Passwort sind falsch!";
		}
		
		p_response.sendRedirect(redirect);
	}
}
