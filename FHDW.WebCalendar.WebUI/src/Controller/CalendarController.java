package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.*;
import java.lang.reflect.Type;
import Model.Calendar.Event.Event;

@WebServlet ("/CalendarController")
public class CalendarController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException
	{
		String action = request.getParameter("action");
		
		switch (action.toLowerCase())
		{
			case "getevents" :
				GetEvents(response);
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
	
	private void GetEvents(HttpServletResponse response) throws IOException
	{
		String output = "";
		try
		{
			// TODO: Event Daten holen.
			Collection <Event> eventCollection = new ArrayList <Event>();
			eventCollection.add(getTestEvent());
			
			Type type = new TypeToken <Collection <Event>>()
			{}.getType();
			
			output = new Gson().toJson(eventCollection, type);
		}
		catch (Exception e)
		{
			output = e.getMessage();
		}
		response.getWriter().print(output);
	}
	
	private Event getTestEvent()
	{
		Event event = new Event();
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(2015, 6, 1, 0, 33);
		event.SetStartTime(calStart);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(2015, 6, 1, 1, 20);
		event.SetEndTime(calEnd);
		
		event.SetTitle("TEST");
		
		return event;
	}
}
