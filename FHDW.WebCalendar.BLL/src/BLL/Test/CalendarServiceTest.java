package BLL.Test;

import static org.junit.Assert.*;
import BLL.Services.CalendarService;

public class CalendarServiceTest
{
		
	private CalendarService calenderService;
	private final int USERIDTRUE = 1;
	private final int USERIDFALSE = 1337;
	private final int CALENDERIDFALSE = 1337;
	private final String CALENDARNAMETRUE = "MeinTestKalender";
	
	/**
	 * @return the calenderService
	 */
	public CalendarService GetCalenderService()
	{
		if (this.calenderService == null) {
			this.calenderService = new CalendarService();
		}
		return calenderService;
	}
	
		
}
