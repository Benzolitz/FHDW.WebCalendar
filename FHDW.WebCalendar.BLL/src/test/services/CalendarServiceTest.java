package test.services;

import static org.junit.Assert.*;

import Services.CalendarService;

public class CalendarServiceTest
{
		
	private CalendarService calenderService;
	private final int USERIDTRUE = 1;
	private final int USERIDFALSE = 1337;
	private final int CALENDERIDFALSE = 1337;
	private final String CALENDARNAMETRUE = "MeinTestKalender";
	
	/**
	 * @return the calenderServic
	 */
	public CalendarService GetCalenderService()
	{
		if (this.calenderService == null) {
			this.calenderService = new CalendarService();
		}
		return calenderService;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	@Test
//	public void TestCreateAndRemoveCalendarToExistingUser()
//	{
//		try
//		{
//			boolean userIdTrue = false;
//			int calendarId;
//			if ((calendarId = GetCalenderService().CreateCalendar(this.USERIDTRUE, this.CALENDARNAMETRUE)) > 0) {
//				userIdTrue = true;
//			}
//			assertTrue(userIdTrue);	
//			assertTrue(GetCalenderService().deleteCalendar(calendarId));	
//		}
//		catch (DatabaseException e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//		catch (IOException e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//	}
//	
//	@Test
//	public void TestCreateCalendarToNotExistingUser()
//	{
//		try
//		{
//			boolean userIdTrue = false;
//			if (GetCalenderService().CreateCalendar(this.USERIDFALSE, this.CALENDARNAMETRUE) > 0) {
//				userIdTrue = true;
//			}
//			assertFalse(userIdTrue);	
//		}
//		catch (DatabaseException e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//		catch (IOException e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//	}
//	
//	@Test
//	public void TestRemoveCalendarFromNotExistingUser()
//	{
//		try
//		{
//			assertFalse(GetCalenderService().deleteCalendar(this.CALENDERIDFALSE));
//		}
//		catch (DatabaseException e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//	}
//	
//	@Test
//	public void TestCreateCalendarIOException()
//	{
//		try
//		{
//			boolean userIdTrue = false;
//			if (GetCalenderService().CreateCalendar(this.USERIDTRUE, "") > 0) {
//				userIdTrue = true;
//			}
//			assertFalse(userIdTrue);
//		}
//		catch (DatabaseException e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//		catch (IOException e)
//		{
//			// Erwartet
//		}
//	}
//	
//	@Test
//	public void TestGetAlLEventsFromExistingUser()
//	{
//		try
//		{
//			Collection<EventCalendarView> events = GetCalenderService().GetAllEvents(this.USERIDTRUE);
//		    assertNotNull(events);
//		}
//		catch (DatabaseException e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//		catch (NotFound e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//	}
//	
//	@Test
//	public void TestGetAlLEventsFromNotExistingUser()
//	{
//		try
//		{
//			Collection<EventCalendarView> events = GetCalenderService().GetAllEvents(this.USERIDFALSE);
//		    assertNotNull(events);
//		}
//		catch (DatabaseException e)
//		{
//			
//		}
//		catch (NotFound e)
//		{
//			fail(e.getMessage() + "\n" + e.getStackTrace());
//		}
//	}
//	
//	@Test
//	public void TestGetEventsFromTo()
//	{
//		fail("Not yet implemented");
//	}
		
}
