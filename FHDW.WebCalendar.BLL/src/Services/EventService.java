package Services;

import Exceptions.IOException;
import HTMLHelper.EventHTMLHelper;
import Model.Calendar.Calendar;
import Model.Calendar.Event.Event;

public class EventService extends BaseService
{	

	public int createEvent(Event event) throws IOException {
		EventHTMLHelper.checkEventData(event);
		GetRepo().SaveEvent(event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory(), event.GetCalendarId(), event.GetCalendarId());
		// TODO: Was ist wenn das gleiche Event schon exisitiert???
		// TODO: Was ist wenn das Event nicht gespeichert werden konnte???
		// TODO: Wie ist das mit nem Rückgabe wert z.B. die Id des Events damit man damit später weiter arbeiten kann
		return 0;
	}
	
	public boolean changeEvent(Event event) throws IOException {
		EventHTMLHelper.checkEventData(event);
		GetRepo().UpdateEvent(event.GetId(), event.GetTitle(), event.GetLocation(), event.GetStartTime(), event.GetEndTime(), event.GetMessage(), event.GetCategory());	
		//TODO: Was is wenn das Update nicht funktioniert hat, ein boolean als Rückgabe wert wäre von vorteil
		return true;
	}
	
	public boolean removeEvent(int p_eventId) {
		GetRepo().DeleteEvent(p_eventId);
		//TODO: boolean als Rückgabe wert ob das geklappt hat wäre von Vorteil!
		return true;
	}
	
	public Event getEvent(int p_eventId, Calendar calendar) {
		//TODO:wäre es nicht Sinnvoller hier immer calendar id und userId mit angeben zu müssen?
		GetRepo().GetEventDetailed(p_eventId);
		//TODO: Ein Rückgabewert wäre von vorteil!
		return new Event();
	}

	
}
