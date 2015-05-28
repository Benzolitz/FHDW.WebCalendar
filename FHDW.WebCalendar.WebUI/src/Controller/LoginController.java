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
	private UserService userService;
	
	public LoginController()
	{
		//userService = new UserService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Login(response, username, password);
	}
	
	private void Login(HttpServletResponse p_response , String p_username, String p_password) throws IOException
	{
		String redirect = "";
		try
		{			
			//if (userService.CheckLoginData(p_username, p_password))
			if (p_username.equals(p_password))
			{
				Cookie cookie = new Cookie("FHDW.WebCalendar", "login=true&userId=2&username="+ p_username);
				cookie.setMaxAge(3600);
				p_response.addCookie(cookie);
				redirect =  "Calendar.jsp";
			}
			else
			{
				redirect = "Login.jsp?username=" + p_username + "&message=Der Benutzername, oder das Passwort sind falsch!";
			}
		}
		catch (Exception exception)
		{
			redirect = "Login.jsp?username=" + p_username + "&message=" + exception.getMessage();
		}
		
		p_response.sendRedirect(redirect);
	}
}
