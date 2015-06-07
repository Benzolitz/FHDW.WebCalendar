package Controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Calendar.Event.Event;
import Model.Calendar.Event.EventCalendarView;

@WebServlet ("/EventController")
public class EventController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		String eventId = request.getParameter("eventId");
		
		switch (action.toLowerCase())
		{
			case "geteventdata" :
				GetEventData(response, eventId);
				break;
			case "saveevent":
				SaveEvent(response);
				break;
			case "deleteevent":
				DeleteEvent(response);
				break;
		}
	}
	
	private void DeleteEvent(HttpServletResponse response)
	{
		
	}

	private void SaveEvent(HttpServletResponse response)
	{
		
	}

	private void GetEventData(HttpServletResponse response, String eventId) throws IOException
	{
		Event event = new Event();
		
		ArrayList <String> categories = new ArrayList <String>();
		categories.add("Hallo");
		categories.add("Welt");
		categories.add("Whatever");
		event.SetCategory(categories);
		event.SetId(Integer.parseInt(eventId));
		
		Calendar calCreation = Calendar.getInstance();
		calCreation.set(2015, 06, 07, 13, 00, 00);
		event.SetCreationTime(calCreation);
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(2015, 06, 07, 14, 00, 00);
		event.SetStartTime(calStart);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(2015, 06, 07, 15, 00, 00);
		event.SetEndTime(calEnd);
		
		event.SetTitle("Hallo Welt");
		event.SetLocation("Welt");
		
		Type type = new TypeToken <Event>()
		{}.getType();
		
		response.getWriter().print(new Gson().toJson(event, type));
	}
}
