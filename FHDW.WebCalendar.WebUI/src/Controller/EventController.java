package Controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.Normalizer;
import java.text.Normalizer.Form;
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

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Exception.ExceptionController;
import Exceptions.DatabaseException;
import Exceptions.NotFound;
import Model.Calendar.Event;
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
		String action = request.getParameter("Action");
		
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
	
	private void DeleteEvent(HttpServletResponse p_response, HttpServletRequest p_request) throws IOException
	{
		String redirect = "";
		try
		{
			if (eventService.RemoveEvent(Integer.parseInt(p_request.getParameter("eventId")), Integer.parseInt(p_request.getParameter("calendarId"))))
			{
				p_response.getWriter().print("SUCCESS!");
			}
		}
		catch (Exception e)
		{
			ExceptionController.handleRuntimeException(e, p_response, "FEHLER!!");
		}
	}
	
	private void SaveEvent(HttpServletResponse p_response, HttpServletRequest p_request) throws IOException
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);
			
			Event event = new Event();
			
			event.SetCalendarId(Integer.parseInt(p_request.getParameter("CalendarId")));
			event.SetCreatorId(Integer.parseInt(p_request.getParameter("UserId")));
			event.SetId(Integer.parseInt(p_request.getParameter("EventId")));
			event.SetTitle(p_request.getParameter("EventTitle"));
			event.SetCategory(Arrays.asList(p_request.getParameter("EventCategories").split("\\s*,\\s*")));
			
			Calendar calStart = Calendar.getInstance();
			calStart.setTime(sdf.parse(p_request.getParameter("EventStartTime")));
			event.SetStartTime(calStart);
			
			Calendar calEnd = Calendar.getInstance();
			calEnd.setTime(sdf.parse(p_request.getParameter("EventEndTime")));
			event.SetEndTime(calEnd);
			
			event.SetLocation(p_request.getParameter("EventLocation"));
			event.SetRequiredUser(Arrays.asList(p_request.getParameter("EventRequiredGuests").split("\\s*,\\s*")));
			event.SetOptionalUser(Arrays.asList(p_request.getParameter("EventOptionalGuests").split("\\s*,\\s*")));
			event.SetMessage(p_request.getParameter("EventComment"));
			
			if (event.GetId() == - 1)
			{
				eventService.CreateEvent(event);
			}
			else
			{
				eventService.ChangeEvent(event);
			}
			
			p_response.getWriter().print("SUCCESS!");
		}
		catch (Exception e)
		{
			ExceptionController.handleRuntimeException(e, p_response, "FEHLER!!");
		}
		
	}
	
	private void GetEventData(HttpServletResponse p_response, HttpServletRequest p_request) throws IOException
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
			
			p_response.setContentType("text/html; charset=UTF-8");
			p_response.getWriter().print(new Gson().toJson(event, type));
		}
		catch (Exception e)
		{
			ExceptionController.handleRuntimeException(e, p_response, "FEHLER!!");
		}
	}
}
