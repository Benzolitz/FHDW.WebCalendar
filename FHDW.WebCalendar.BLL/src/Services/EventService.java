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

			
			if (!isCalendarExisting) {
				throw new NotFound("Das Event konnte nicht erstellt werden da der Kalender nicht existiert");
			}
	
			GetRepo().SaveEvent(event.GetTitle(), event.GetLocation(), EventHelper.formatDateTime(event.GetStartTime()), EventHelper.formatDateTime(event.GetEndTime()), event.GetMessage(), event.GetCategory(),event.GetCreatorId(),event.GetCalendarId(), null, null);
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

			GetRepo().UpdateEvent(event.GetId(), event.GetTitle(), event.GetLocation(), EventHelper.formatDateTime(event.GetStartTime()), EventHelper.formatDateTime(event.GetEndTime()), event.GetMessage(), event.GetCategory(), null, null);

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
