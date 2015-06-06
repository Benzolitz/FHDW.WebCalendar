package test.services;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Model.Calendar.Event.EventCalendarView;
import Services.CalenderService;

public class CalendarServiceTest
{
		
	private CalenderService calenderService;
	private final int USERIDTRUE = 1;
	private final int USERIDFALSE = 1337;
	private final int CALENDERIDFALSE = 1337;
	private final String CALENDARNAMETRUE = "MeinTestKalender";
	
	/**
	 * @return the calenderServic
	 */
	public CalenderService GetCalenderServic()
	{
		if (this.calenderService == null) {
			this.calenderService = new CalenderService();
		}
		return calenderService;
	}

	@Test
	public void TestCreateAndRemoveCalendarToExistingUser()
	{
		try
		{
			boolean userIdTrue = false;
			int calendarId;
			if ((calendarId = GetCalenderServic().CreateCalendar(this.USERIDTRUE, this.CALENDARNAMETRUE)) > 0) {
				userIdTrue = true;
			}
			assertTrue(userIdTrue);	
			assertTrue(GetCalenderServic().deleteCalendar(calendarId));	
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void TestCreateCalendarToNotExistingUser()
	{
		try
		{
			boolean userIdTrue = false;
			if (GetCalenderServic().CreateCalendar(this.USERIDFALSE, this.CALENDARNAMETRUE) > 0) {
				userIdTrue = true;
			}
			assertFalse(userIdTrue);	
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void TestRemoveCalendarFromNotExistingUser()
	{
		try
		{
			assertFalse(GetCalenderServic().deleteCalendar(this.CALENDERIDFALSE));
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void TestCreateCalendarIOException()
	{
		try
		{
			boolean userIdTrue = false;
			if (GetCalenderServic().CreateCalendar(this.USERIDTRUE, "") > 0) {
				userIdTrue = true;
			}
			assertFalse(userIdTrue);
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (IOException e)
		{
			// Erwartet
		}
	}
	
	@Test
	public void TestGetAlLEventsFromExistingUser()
	{
		try
		{
			Collection<EventCalendarView> events = GetCalenderServic().GetAllEvents(this.USERIDTRUE);
		    assertNotNull(events);
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void TestGetAlLEventsFromNotExistingUser()
	{
		try
		{
			Collection<EventCalendarView> events = GetCalenderServic().GetAllEvents(this.USERIDFALSE);
		    assertNotNull(events);
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void TestGetEventsFromTo()
	{
		fail("Not yet implemented");
	}
		
}
