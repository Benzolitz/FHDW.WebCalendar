import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;

@WebServlet("/CalendarController")
public class CalendarController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException
	{
		response.setContentType("text/html");
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		{
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("username"))
				{
					loginCookie = cookie;
					break;
				}
			}
		}
		if(loginCookie != null)
		{
			loginCookie.setMaxAge(0);
			response.addCookie(loginCookie);
		}
		response.sendRedirect("Login.jsp");
	}
	
	protected void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, IOException
	{
		String output;
		try
		{
			Integer weekNumber = Integer.parseInt(request
					.getParameter("weeknumber"));
			
			if(weekNumber == -1)
			{
				LocalDate date = LocalDate.now();
				WeekFields weekFields = WeekFields.of(Locale.getDefault());
				weekNumber = date.get(weekFields.weekOfWeekBasedYear());
			}
			output = "FUCK JAVA";
		}
		catch(Exception e)
		{
			output = e.getMessage();
		}
		response.getWriter().write(output);
	}
}
