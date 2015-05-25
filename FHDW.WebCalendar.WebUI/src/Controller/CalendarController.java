package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ("/CalendarController")
public class CalendarController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		String action = request.getParameter("action");
		
		switch (action.toLowerCase())
		{
			case "getcalendar" :
				GetCalendar(response, Integer.parseInt(request.getParameter("weeknumber")));
				break;
			case "logout" : 
				Logout(response, request.getCookies());
				break;
			default :
				break;
		}
	}
	
	private void Logout(HttpServletResponse response, Cookie[] cookies) throws IOException
	{
		Cookie loginCookie = null;
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
		if (loginCookie != null)
		{
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
		}
		response.sendRedirect("Login.jsp");
	}
	
	private void GetCalendar(HttpServletResponse response, Integer weekNumber) throws IOException
	{
		String output = "";
		try
		{
			
		}
		catch (Exception e)
		{
			output = e.getMessage();
		}
		response.getWriter().write(output);
	}
}
