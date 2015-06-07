package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.*;

import java.lang.reflect.Type;

import Model.Calendar.Event.EventCalendarView;
import Services.*;

@WebServlet ("/CalendarController")
public class CalendarController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private CalenderService calendarService;
	
	public CalendarController()
	{
		calendarService = new CalenderService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		String action = request.getParameter("action");
		String userId = request.getParameter("userid");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		switch (action.toLowerCase())
		{
			case "getevents" :
				GetEvents(response, userId, startTime, endTime);
				break;
			case "logout" :
				Logout(response, request.getCookies());
				break;
			default :
				break;
		}
	}
	
	public static void Logout(HttpServletResponse p_response, Cookie[] p_cookies) throws IOException
	{
		Cookie calendarCookie = null;
		if (p_cookies != null)
		{
			for (Cookie cookie : p_cookies)
			{
				if (cookie.getName().equals("FHDW.WebCalendar"))
				{
					calendarCookie = cookie;
					break;
				}
			}
		}
		if (calendarCookie != null)
		{
			calendarCookie.setMaxAge(0);
			p_response.addCookie(calendarCookie);
		}
	}
	
	private void GetEvents(HttpServletResponse p_response, String p_userId, String p_startTime, String p_endTime) throws IOException
	{
		String output = "";
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(sdf.parse(p_startTime));
			
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(sdf.parse(p_endTime));
			
			Collection <EventCalendarView> view = calendarService.GetEventsFromTo(Integer.parseInt(p_userId), calStart.getTime(), calEnd.getTime());
			
			Type type = new TypeToken <Collection <EventCalendarView>>()
			{}.getType();
			
			output = new Gson().toJson(view, type);
		}
		catch (Exception e)
		{
			output = e.getMessage();
		}
		p_response.getWriter().print(output);
	}
}
