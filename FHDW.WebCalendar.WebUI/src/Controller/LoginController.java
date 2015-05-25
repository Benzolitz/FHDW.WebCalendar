package Controller;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LoginController")
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
		if (p_username.equals(p_password))
		{
			Cookie cookie = new Cookie("username", p_username);
			cookie.setMaxAge(3600);
			p_response.addCookie(cookie);
			p_response.sendRedirect("Calendar.jsp");
		}
		else
		{
			p_response.sendRedirect("Login.jsp?message=Der Benutzername, oder das Passwort sind falsch!");
		}	
	}
}
