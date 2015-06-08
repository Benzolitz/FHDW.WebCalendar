package Services;

import java.util.Collection;
import java.util.HashMap;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Helper.EventHelper;
import Model.Calendar.Event.Event;

public class EventService extends BaseService
{	
	
	public boolean createEvent(Event event) throws DatabaseException, IOException, NotFound  {
		EventHelper.checkEventData(event); // throws IOException	
		try
		{		
			HashMap<Integer, Integer> p_requiredUser = checkEventUserList(event.GetRequiredUser());
			p_requiredUser.put(event.GetCreatorId(), event.GetCalendarId());
			GetRepo().SaveEvent(event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory(),event.GetCreatorId(),event.GetCalendarId(), p_requiredUser, checkEventUserList(event.GetOptionalUser()));
			return true;
		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}
	}
	
	public boolean changeEvent(Event event) throws IOException, DatabaseException, NotFound {
		EventHelper.checkEventData(event);
		try
		{
			HashMap<Integer, Integer> p_requiredUser = checkEventUserList(event.GetRequiredUser());
			p_requiredUser.put(event.GetCreatorId(), event.GetCalendarId());
			GetRepo().UpdateEvent(event.GetId(), event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory(), p_requiredUser, checkEventUserList(event.GetOptionalUser()));		
			return true;
		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}	
	}
	
	public boolean removeEvent(int p_eventId, int p_userId) throws DatabaseException {
		try
		{
			GetEvent(p_eventId); // Throws NotFound
			//TODO: Was is wenn das Update nicht funktioniert hat, ein boolean als R�ckgabe wert w�re von vorteil
			GetRepo().DeleteEvent(p_eventId, p_userId);
			return true;
		}
		catch (Exception e)
		{
			if (e instanceof NotFound) {
				// Event existiert nicht mehr
				return true;
			}
			throw new DatabaseException(e);
		}

	}
	
	public Event GetEvent(int p_eventId) throws NotFound, DatabaseException {
		try
		{
			Event result_Event = GetRepo().GetEventDetailed(p_eventId); 
			if (result_Event == null) {
				throw new NotFound("Das Event mit der ID: " + p_eventId + "Existiert nicht");
			}
			return result_Event;
		}
		catch (Exception e)
		{
			throw new DatabaseException(e);
		}
	}
	
	private HashMap<Integer, Integer> checkEventUserList(Collection<String> p_userNameList) throws DatabaseException, NotFound {
		HashMap<Integer, Integer>  result_userIds = new HashMap<Integer, Integer>();
		for(String userName : p_userNameList) {
			try
			{
				int userId = GetUserService().GetUserId(userName);
				int defaultCalendarID = GetCalendarService().GetDefaultCalendarId(userId);
				if (defaultCalendarID <0) {
					throw new NotFound("Termin konnte f�r den Teilnehmer: " + userName + " nicht erstellt werden.");
				}
				
				result_userIds.put(userId, defaultCalendarID);
			}
			catch (IOException e)
			{
				// Benutzer mit leeren namen ignorieren
			}
		}
		
		return result_userIds;
	}
	
}
