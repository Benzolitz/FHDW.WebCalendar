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

import Exception.ExceptionController;
import Model.Calendar.Event;
import Services.*;

@WebServlet ("/CalendarController")
public class CalendarController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private CalendarService calendarService;
	private EventService eventService;
	
	public CalendarController()
	{
		calendarService = new CalendarService();
		eventService = new EventService();
	}
	
	protected void doGet(HttpServletRequest p_request, HttpServletResponse p_response)
	{
		
	}
	
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws javax.servlet.ServletException, IOException
	{
		String action = p_request.getParameter("action");
		
		switch (action.toLowerCase())
		{
			case "getevents" :
				GetEvents(p_request, p_response);
				break;
			case "logout" :
				Logout(p_request, p_response);
				break;
			case "createnewcalendar" :
				CreateNewCalendar(p_request, p_response);
				break;
			case "searchevent" :
				SearchEvent(p_request, p_response);
				break;
			default :
				break;
		}
	}
	
	private void SearchEvent(HttpServletRequest p_request, HttpServletResponse p_response)
	{
		try
		{
			String userId = p_request.getParameter("User");
			String searchString = p_request.getParameter("SearchString");
			
			Collection <Event> searchResultSet = eventService.SearchEvents(Integer.parseInt(userId), searchString);
			
			Type type = new TypeToken <Collection <Event>>()
			{}.getType();
			
			p_response.getWriter().print(new Gson().toJson(searchResultSet, type));
		}
		catch (Exception e)
		{}
	}
	
	private void CreateNewCalendar(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
	{
		try
		{
			p_response.getWriter().print(calendarService.CreateCalendar(Integer.parseInt(p_request.getParameter("userId")), p_request.getParameter("calendarName")));
		}
		catch (Exception e)
		{
			ExceptionController.handleRuntimeException(e, p_response, "FEHLER!!");
		}
		
	}
	
	private void GetEvents(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(sdf.parse(p_request.getParameter("startTime")));
			
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(sdf.parse(p_request.getParameter("endTime")));
			
			String user = p_request.getParameter("userid");
			String calendar = p_request.getParameter("calendarId");
			
			Collection <Event> view = eventService.GetEventsFromTo(Integer.parseInt(user), Integer.parseInt(calendar), calStart, calEnd);
			
			Type type = new TypeToken <Collection <Event>>()
			{}.getType();
			
			p_response.getWriter().print(new Gson().toJson(view, type));
		}
		catch (Exception e)
		{
			ExceptionController.handleRuntimeException(e, p_response, "FEHLER!!");
		}
	}
	
	private void Logout(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
	{
		Cookie[] cookies = p_request.getCookies();
		Cookie calendarCookie = null;
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
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
		
		p_response.sendRedirect("Login.jsp");
	}
}
