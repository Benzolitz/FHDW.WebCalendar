package BLL.Helper;

import BLL.Exceptions.IOException;

public class CalendarHelper
{	
	
	/**
	 * Überprüft die Syntax eines Passwords<br>
	 * 1. Das password darf nicht leer oder null sein
	 * 
	 * @param p_password
	 * 
	 * @throws IOException wenn das eingebene password leer war
	 */
	public static void checkCalendarName(String p_calendarName) throws IOException {
		if (p_calendarName == null  || p_calendarName.isEmpty()) {
			throw new IOException("Der Eingeben Kalendar Name ist leer");
		} 
	}
	
}
