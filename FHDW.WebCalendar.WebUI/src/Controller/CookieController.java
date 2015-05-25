package Controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author engel
 *
 */
@WebServlet ("/CookieController")
public class CookieController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest currentRequest;
	private HttpServletResponse currentResponse;
	
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException
	{
		currentRequest = p_request;
		currentResponse = p_response;
		
		try
		{
			switch (p_request.getParameter("action").toLowerCase())
			{
				case "cookiecheck" :
					CheckCookie();
					break;
				case "logout" :
					Logout();
					break;
				case "login" :
					Login();
					break;
			}
		}
		catch (Exception e)
		{
			currentResponse.sendRedirect("Error.jsp?error=Fehler! " + e.getMessage());
		}
		
		currentRequest = null;
		currentResponse = null;
	}
	
	private void CheckCookie() throws IOException
	{
		Cookie cookie = GetCookie();
		if (cookie == null)
		{
			currentResponse.sendRedirect("Login.jsp?message=Sie müssen sich zuerst einloggen!");
		}
		else
		{
			currentResponse.sendRedirect("Calendar.jsp");
		}
	}
	
	private void Login() throws IOException
	{
		String username = currentRequest.getParameter("username");
		// TODO: Korrekte Abfrage einfügen!
		if (currentRequest.getParameter("username").equals(currentRequest.getParameter("password")))
		{
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(3600);
			currentResponse.addCookie(cookie);
			currentResponse.sendRedirect("Calendar.jsp");
		}
		else
		{
			currentResponse.sendRedirect("Login.jsp?failure=true&error=Der Benutzername, oder das Passwort sind falsch!");
		}
	}
	
	private void Logout() throws IOException
	{
		Cookie cookie = GetCookie();
		if (cookie != null)
		{
			cookie.setMaxAge(0);
			currentResponse.addCookie(cookie);
		}
		currentResponse.sendRedirect("Login.jsp");
	}
	
	private Cookie GetCookie()
	{
		Cookie loginCookie = null;
		Cookie[] cookies = currentRequest.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals("username"))
				{
					loginCookie = cookie;
					break;
				}
			}
		}
		return loginCookie;
	}
}
