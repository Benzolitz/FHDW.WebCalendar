package HTMLHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Exceptions.IOException;
import Model.Calendar.Event.Event;

/**
 * @author Frederik Heinrichs
 * 
 * Helper für die überprüfung eingebener Daten für Event objekte
 * 
 * @see Event
 */
public class EventHelper
{	
	
	private static SimpleDateFormat dateFormatter;
	
	public static SimpleDateFormat GetDateFormatter() {
		if (EventHelper.dateFormatter == null) {
			EventHelper.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return EventHelper.dateFormatter;
	}
	
	public static void checkEventData(Event event) throws IOException { 
		//throw new IOException("");	
	}
	
	public static void checkTitle(String p_title) throws IOException {
		//throw new IOException("");	
	}
	
	public static void checkDateTime(Date p_dateTime) throws IOException {
		//throw new IOException("");	
	}

}
