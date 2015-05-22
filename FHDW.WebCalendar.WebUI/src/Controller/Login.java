package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends javax.servlet.http.HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException
	{
		String nextJSP;
		// TODO: Korrekte Abfrage einfügen!
		if(request.getParameter("username").equals(request.getParameter("password")))
		{
			nextJSP = "/calendar.jsp";
		}
		else
		{
			nextJSP = "/login.jsp?failure=true&error=Der Benutzername, oder das Passwort sind falsch!";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request, response);
	}
}
