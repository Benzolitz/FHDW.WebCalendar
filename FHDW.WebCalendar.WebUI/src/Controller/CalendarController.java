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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.*;

import java.lang.reflect.Type;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import Exception.ExceptionController;
import Model.Calendar.Event.Event;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		String action = request.getParameter("action");
		
		switch (action.toLowerCase())
		{
			case "getevents" :
				GetEvents(response, request);
				break;
			case "logout" :
				Logout(response, request.getCookies());
				break;
			case "createnewcalendar" :
				CreateNewCalendar(response, request);
				break;
			case "searchevent" :
				SearchEvent(response, request);
				break;
			default :
				break;
		}
	}
	
	private void SearchEvent(HttpServletResponse p_response, HttpServletRequest p_request)
	{
		try
		{
			String userId = p_request.getParameter("User");
			String searchString = p_request.getParameter("SearchString");
			
			Collection<Event> searchResultSet = eventService.SearchEvents(Integer.parseInt(userId), searchString);

			Type type = new TypeToken <Collection <Event>>()
			{}.getType();
			
			p_response.getWriter().print(new Gson().toJson(searchResultSet, type));
		}
		catch (Exception e)
		{
			String test = e.getMessage();
		}
	}
	
	private void CreateNewCalendar(HttpServletResponse p_response, HttpServletRequest p_request) throws IOException
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
	
	private void GetEvents(HttpServletResponse p_response, HttpServletRequest p_request) throws IOException
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
		
		p_response.sendRedirect("Login.jsp");
	}
}
