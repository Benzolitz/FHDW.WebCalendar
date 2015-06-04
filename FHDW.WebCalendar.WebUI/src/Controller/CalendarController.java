package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
	
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
			eventCollection.add(getTestEvent(1, 10, 00, 14, 10));
			eventCollection.add(getTestEvent(1, 14, 00, 15, 00));
			
			eventCollection.add(getTestEvent(2, 11, 00, 13, 00));
			eventCollection.add(getTestEvent(3, 13, 00, 15, 00));
			eventCollection.add(getTestEvent(4, 8, 00, 16, 00));
			eventCollection.add(getTestEvent(4, 11, 00, 15, 00));
			eventCollection.add(getTestEvent(5, 10, 00, 12, 00));
			eventCollection.add(getTestEvent(6, 10, 00, 12, 00));
			eventCollection.add(getTestEvent(7, 13, 00, 14, 00));
			
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
	
	private Event getTestEvent(int p_day, int p_startHour, int p_startMinute, int p_endHour, int p_endMinute)
	{
		Event event = new Event();
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(2015, 6, p_day, p_startHour, p_startMinute);
		event.SetStartTime(calStart);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(2015, 6, p_day, p_endHour, p_endMinute);
		event.SetEndTime(calEnd);
		
		event.SetTitle("TEST");
		
		Random rand = new Random();
		
		event.SetId(rand.nextInt(100));
		
		return event;
	}
}
