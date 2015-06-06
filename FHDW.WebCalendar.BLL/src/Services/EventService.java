package Services;

import java.sql.SQLException;
import java.util.Collection;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import HTMLHelper.EventHelper;
import Model.Calendar.Calendar;
import Model.Calendar.Event.Event;

public class EventService extends BaseService
{	

	public boolean createEvent(Event event) throws IOException, DatabaseException, NotFound {
		EventHelper.checkEventData(event); // throws IOException
		
		try
		{
			boolean isCalendarExisting = false;
			Collection<Calendar>  userCalendar = new CalenderService().GetAllUserCalendar(event.GetCreatorId());
			for (Calendar calendar : userCalendar) {
				if (event.GetCalendarId() == calendar.GetId()) {
					isCalendarExisting = true;
				}
			}
			// TODO: Prüfen ob der Calender Existiert
			// Prüfen ob der Benutzer existiert
			
			if (!isCalendarExisting) {
				throw new NotFound("Das Event konnte nicht erstellt werden da der Kalender nicht existiert");
			}
			
			boolean isUserExisting = true;
			if (!isUserExisting) {
				//TODO: Was passiert wenn die BenutzerId nicht korrekt war
			}
			
			GetRepo().SaveEvent(event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory(), event.GetCreatorId(), event.GetCreatorId());
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
			GetEvent(event.GetId()); // throws NotFound
			//TODO: Was is wenn das Update nicht funktioniert hat, ein boolean als Rückgabe wert wäre von vorteil
			GetRepo().UpdateEvent(event.GetId(), event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory());
			return true;
		}
		catch (SQLException e)
		{
			// TODO: SQLException Loggen
			// TODO: Fehlermeldung Benutzerfreundlich durchreichen
			throw new DatabaseException(e.getMessage(), e);
		}	
	}
	
	public boolean removeEvent(int p_eventId) throws DatabaseException {
		try
		{
			GetEvent(p_eventId); // Throws NotFound
			//TODO: Was is wenn das Update nicht funktioniert hat, ein boolean als Rückgabe wert wäre von vorteil
			GetRepo().DeleteEvent(p_eventId);
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
		//TODO:wäre es nicht Sinnvoller hier immer calendar id und userId mit angeben zu müssen?
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

}
