package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import BLL.Services.*;

/**
 * In diesem Controller befinden sich alle, für den Login (Login.jsp) wichtige Informationen.
 * 
 * @author Lucas Engel
 */

@WebServlet ("/LoginController")
public class LoginController extends javax.servlet.http.HttpServlet
{
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public LoginController()
	{
		userService = new UserService();
	}
	
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws javax.servlet.ServletException, IOException
	{
		Login(p_request, p_response);
	}

	/**
	 * In der Login-Methode wird ein Cookie für den Benutzer erstellt und der Benutzer wird bei erfolgreichem Login an seinen Kalender weitergeleitet.
	 * 
	 * @param p_request
	 * @param p_response
	 */
	private void Login(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
	{
		String username = p_request.getParameter("username");
		String password = p_request.getParameter("password");
		String redirect = "";
		try
		{
			if (userService.CheckLoginData(username, password) > 0)
			{
				Cookie cookie = new Cookie("FHDW.WebCalendar", String.format("login=true&userId=%d&username=%s", userService.GetUserId(username), username));
				cookie.setMaxAge(3600);
				p_response.addCookie(cookie);
				redirect = "Calendar.jsp";
			}
			else
			{
				redirect = "Login.jsp?username=" + username + "&message=Der Benutzername, oder das Passwort sind falsch!";
			}
		}
		catch (Exception exception)
		{
			redirect = "Login.jsp?username=" + username + "&message=Der Benutzername, oder das Passwort sind falsch!";
		}
		
		p_response.sendRedirect(redirect);
	}
}
