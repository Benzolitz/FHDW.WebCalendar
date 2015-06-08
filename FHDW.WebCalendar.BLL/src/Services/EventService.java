package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
			// throws NotFound, DatabasException
			GetRepo().SaveEvent(event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory(),event.GetCreatorId(),event.GetCalendarId(), checkEventUserList(event.GetRequiredUser()), checkEventUserList(event.GetOptionalUser()));
			return true;
		}
		catch (SQLException e)
		{
			// TODO: SQLException Loggen
			// TODO: Fehlermeldung Benutzerfreundlich durchreichen
			throw new DatabaseException(e.getMessage(), e);
		}
	}
	
	public boolean changeEvent(Event event) throws IOException, DatabaseException, NotFound {
		EventHelper.checkEventData(event);
		try
		{
			// throws NotFound, DatabasException
			GetRepo().UpdateEvent(event.GetId(), event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory(), checkEventUserList(event.GetRequiredUser()), checkEventUserList(event.GetOptionalUser()));		
			return true;
		}
		catch (SQLException e)
		{
			// TODO: SQLException Loggen
			// TODO: Fehlermeldung Benutzerfreundlich durchreichen
			throw new DatabaseException(e.getMessage(), e);
		}	
	}
	
	public boolean removeEvent(int p_eventId, int p_userId) throws DatabaseException {
		try
		{
			GetEvent(p_eventId); // Throws NotFound
			//TODO: Was is wenn das Update nicht funktioniert hat, ein boolean als Rückgabe wert wäre von vorteil
			GetRepo().DeleteEvent(p_eventId, p_userId);
			return true;
		}
		catch (SQLException e)
		{
			// TODO: SQLException Loggen
			// TODO: Fehlermeldung Benutzerfreundlich durchreichen
			throw new DatabaseException(e.getMessage(), e);
		}
		catch (NotFound e)
		{
			// TODO: NotFound sollte trotzdem geloggt werden
			// Event existiert nicht mehr
			return true;
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
		catch (SQLException e)
		{
			// TODO: SQLException Loggen
			// TODO: Fehlermeldung Benutzerfreundlich durchreichen
			throw new DatabaseException(e.getMessage(), e);
		}
	}
	
	private Collection<Integer> checkEventUserList(Collection<String> p_userNameList) throws DatabaseException, NotFound {
		Collection<Integer> result_userIds = new ArrayList <Integer>();
		for(String userName : p_userNameList) {
			try
			{
				result_userIds.add(GetUserService().GetUserId(userName));
			}
			catch (IOException e)
			{
				// Benutzer mit leeren namen ignorieren
			}
		}
		
		return result_userIds;
	}
	
}
