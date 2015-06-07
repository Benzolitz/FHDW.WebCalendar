package Controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Exceptions.DatabaseException;
import Exceptions.NotFound;
import Model.Calendar.Event.Event;
import Services.EventService;

@WebServlet ("/EventController")
public class EventController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private EventService eventService;
	
	public EventController()
	{
		eventService = new EventService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		
		switch (action.toLowerCase())
		{
			case "geteventdata" :
				GetEventData(response, request);
				break;
			case "saveevent" :
				SaveEvent(response, request);
				break;
			case "deleteevent" :
				DeleteEvent(response, request);
				break;
		}
	}
	
	private void DeleteEvent(HttpServletResponse p_response, HttpServletRequest p_request)
	{
		
	}
	
	private void SaveEvent(HttpServletResponse p_response, HttpServletRequest p_request)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);

			String eventId = p_request.getParameter("eventId");
			String calendarId = p_request.getParameter("calendarId");
			String title = p_request.getParameter("title");
			String categories = p_request.getParameter("categories");
			String eventStart = p_request.getParameter("eventStart");
			String eventEnd = p_request.getParameter("eventEnd");
			String location = p_request.getParameter("location");
			String requiredGuests = p_request.getParameter("requiredGuests");
			String optionalGuests = p_request.getParameter("optionalGuests");
			String comment = p_request.getParameter("comment");
			
			List <String> categoryList = Arrays.asList(categories.split(", "));
			List <String> requiredGuestsList = Arrays.asList(requiredGuests.split(", "));
			List <String> optionalGuestsList = Arrays.asList(optionalGuests.split(", "));
			
			Event event = new Event();
			
			event.SetCalendarId(Integer.parseInt(calendarId));
			
			event.SetTitle(title);
			event.SetCategory(categoryList);
			
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(sdf.parse(eventStart));
			
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(sdf.parse(eventEnd));
			event.SetStartTime(calStart);
			event.SetEndTime(calEnd);
			event.SetLocation(location);
			event.SetRequiredUser(requiredGuestsList);
			event.SetOptionalUser(optionalGuestsList);
			event.SetMessage(comment);
			
			if (eventId == "-1")
			{
				eventService.createEvent(event);
			}
			else
			{
				event.SetId(Integer.parseInt(eventId));
				eventService.changeEvent(event);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void GetEventData(HttpServletResponse p_response, HttpServletRequest p_request)
	{
		try
		{
			Event event = eventService.GetEvent(Integer.parseInt(p_request.getParameter("eventId")));
			
			if (event.GetCategory() == null)
			{
				event.SetCategory(new ArrayList <String>());
			}
			
			Type type = new TypeToken <Event>()
			{}.getType();
			
			p_response.getWriter().print(new Gson().toJson(event, type));
		}
		catch (Exception e)
		{	
			
		}
	}
}
