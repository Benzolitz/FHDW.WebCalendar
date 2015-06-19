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

import BLL.Services.*;
import Controller.Exception.ExceptionController;
import DomainModel.Calendar.Event;

/**
 * In diesem Controller befinden sich alle, für den Kalender (Calendar.jsp) wichtige Informationen.
 * 
 * @author Lucas Engel
 * 
 */


@WebServlet ("/CalendarController")
public class CalendarController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private CalendarService calendarService;
	private EventService eventService;
	
	/**
	 * Initialisierung des Controllers.
	 * 
	 */
	public CalendarController()
	{
		calendarService = new CalendarService();
		eventService = new EventService();
	}
	protected void doGet(HttpServletRequest p_request, HttpServletResponse p_response)
	{
		
	}
	
	/**
	 * DoPost wird bei jeder Anfrage an den Controller aufgerufen.
	 * Über einen Parameter wird entschieden welche Aktion als nächstes ausgeführt wird.
	 * 
	 * @param p_request
	 * @param p_response
	 */
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

	/**
	 * Falls der Benutzer eine Suche ausführen möchte wird diese Methode aufgerufen.
	 * Die Methode SeachEvent fragt bei der BLL an, ob es bereits Termine mit den eingegebenen Suchkriterien gibt.
	 * Die JavaScript-Methode, welche diese Methode aufruft, bekommt eine Auflistung aller Termine als JSON-String zurück.
	 * 
	 * @param p_request
	 * @param p_response
	 */
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

	/**
	 * Die Methode CreateNewCalendar dient für die Erstellung eines neuen Kalenders.
	 * 
	 * @param p_request
	 * @param p_response
	 */
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

	/**
	 * Die Methode Get-Events fragt bei der BLL nach allen Terminen in einem bestimmten Zeitraum.
	 * 
	 * @param p_request
	 * @param p_response
	 */
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

	/**
	 * Der Cookie "FHDW.WebCalendar" wird gelöscht und der Benutzer wird auf die Login.jsp weitergeleitet.
	 * 
	 * @param p_request
	 * @param p_response
	 */
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
