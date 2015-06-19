package Controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import BLL.Services.EventService;
import Controller.Exception.ExceptionController;
import DomainModel.Calendar.Event;

/**
 * In diesem Controller befinden sich alle, für die Termine (Event.jsp) wichtige Informationen.
 * 
 * @author Lucas Engel
 * 
 * 
 * 
 */

@WebServlet ("/EventController")
public class EventController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private EventService eventService;
	
	public EventController()
	{
		eventService = new EventService();
	}

	/**
	 * DoPost wird bei jeder Anfrage an den Controller aufgerufen.
	 * Über einen Parameter wird entschieden welche Aktion als nächstes ausgeführt wird.
	 * 
	 * @param p_request
	 * @param p_response
	 */
	protected void doPost(HttpServletRequest p_request, HttpServletResponse p_response) throws ServletException, IOException
	{
		String action = p_request.getParameter("Action");
		
		switch (action.toLowerCase())
		{
			case "geteventdata" :
				GetEventData(p_request, p_response);
				break;
			case "saveevent" :
				SaveEvent(p_request, p_response);
				break;
			case "deleteevent" :
				DeleteEvent(p_request, p_response);
				break;
		}
	}
	
	/**
	 * DeleteEvent dient dazu einen Termin aus der Datenbank.
	 * 
	 * @param p_request
	 * @param p_response
	 */
	private void DeleteEvent(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
	{
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

	/**
	 * SaveEvent speichert entweder einen neuen Termin, oder sendet die aktualisierten Daten eines Termins.
	 * 
	 * @param p_request
	 * @param p_response
	 */
	private void SaveEvent(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
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

	/**
	 * Mit GetEventData werden alle Informationen zu einem Termin ausgelesen.
	 * 
	 * @param p_request
	 * @param p_response
	 */
	private void GetEventData(HttpServletRequest p_request, HttpServletResponse p_response) throws IOException
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