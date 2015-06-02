package Services;

import java.sql.SQLException;

import Exceptions.IOException;
import HTMLHelper.EventHelper;
import Model.Calendar.Calendar;
import Model.Calendar.Event.Event;

public class EventService extends BaseService
{	

	public int createEvent(Event event) throws IOException {
		EventHelper.checkEventData(event);
		try
		{
			GetRepo().SaveEvent(event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory(), event.GetCalendarId(), event.GetCalendarId());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		// TODO: Was ist wenn das gleiche Event schon exisitiert???
		// TODO: Was ist wenn das Event nicht gespeichert werden konnte???
		// TODO: Wie ist das mit nem Rückgabe wert z.B. die Id des Events damit man damit später weiter arbeiten kann
		return 0;
	}
	
	public boolean changeEvent(Event event) throws IOException {
		EventHelper.checkEventData(event);
		try
		{
			GetRepo().UpdateEvent(event.GetId(), event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		//TODO: Was is wenn das Update nicht funktioniert hat, ein boolean als Rückgabe wert wäre von vorteil
		return true;
	}
	
	public boolean removeEvent(int p_eventId) {
		try
		{
			GetRepo().DeleteEvent(p_eventId);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//TODO: boolean als Rückgabe wert ob das geklappt hat wäre von Vorteil!
		return true;
	}
	
	public Event getEvent(int p_eventId, Calendar calendar) {
		//TODO:wäre es nicht Sinnvoller hier immer calendar id und userId mit angeben zu müssen?
		try
		{
			GetRepo().GetEventDetailed(p_eventId);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		//TODO: Ein Rückgabewert wäre von vorteil!
		return new Event();
	}

	
}
