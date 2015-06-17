package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Services.UserService;

/**
 * @author Lucas Engel
 * 
 * 			ResetSecurity
 * 			In diesem Controller befinden sich alle, für die Passwort zurücksetzung (ResetSecurity.jsp) wichtige Informationen.
 * 
 * 
 */

@WebServlet ("/ResetSecurityController")
public class ResetSecurityController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	UserService userService;
	
	public ResetSecurityController()
	{
		userService = new UserService();
	}
	
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException
	{
		ChangeUserPassword(p_request, p_response);
	}

	
	/**
	 * Falls die eingegebene Sicherheitsantwort korrekt eingegeben wurde, wird das neue Passwort in die Datenbank geschrieben.
	 * 
	 * @param p_request
	 * @param p_response
	 */
	private void ChangeUserPassword(HttpServletRequest p_request, HttpServletResponse p_response)
	{
		
		try
		{
			String userId = p_request.getParameter("userId");
			String password = p_request.getParameter("newPassword");
			String securityAnswer = p_request.getParameter("securityAnswer");
			
			if (userService.CheckSecurityAnswer(Integer.parseInt(userId), securityAnswer))
			{
				userService.ChangeUserPasword(Integer.parseInt(userId), password);
				
				p_response.sendRedirect("Login.jsp?message=Das Passwort wurde erfolgreich geaendert!");
			}
			else
			{
				p_response.sendRedirect("Reset.jsp?message=Fehler!");				
			}
		}
		catch (Exception e)
		{}
	}
	
}
