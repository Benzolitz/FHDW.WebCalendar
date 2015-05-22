import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Login")
public class LoginController extends javax.servlet.http.HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, IOException
	{
		String username = request.getParameter("username");
		// TODO: Korrekte Abfrage einfügen!
		if(request.getParameter("username").equals(request.getParameter("password")))
		{
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(3600);
            response.addCookie(cookie);
            response.sendRedirect("calendar.jsp");
		}
		else
		{
            response.sendRedirect("login.jsp?failure=true&error=Der Benutzername, oder das Passwort sind falsch!");
		}
	}
}
