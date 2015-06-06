package test.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import Exceptions.DatabaseException;
import Exceptions.IOException;
import Exceptions.NotFound;
import Model.Calendar.Event.Event;
import Services.EventService;

public class EventServiceTest
{
	
	private EventService eventService;
	private final int EVENTIDTRUE = 1;
	private final int EVENTIDFALSE = 1337;
	
	private final int USERIDTRUE = 1;
	private final int USERIDFALSE = 1337;
	
	private final int CALENDERIDFALSE = 1337;
	private final int CALENDERIDTRUE = 1;
	
	/**
	 * @return the calenderServic
	 */
	public EventService GetEventService()
	{
		if (this.eventService == null) {
			this.eventService = new EventService();
		}
		return eventService;
	}
	
	private Event GetTrueEvent() {
		Event event = new Event();
		event.SetCalendarId(this.CALENDERIDTRUE);
		event.SetCreatorId(this.USERIDTRUE);
		event.SetCategory(new ArrayList<String>());
		
		Calendar calStart = Calendar.getInstance();
		calStart.set(2015, 6, 6, 8, 0);
		event.SetStartTime(calStart);
		
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(2015, 6, 6, 18, 0);
		event.SetEndTime(calEnd);
		
		Calendar creationTime = Calendar.getInstance();
		event.SetCreationTime(creationTime);
		
		event.SetLocation("DE");
		event.SetMessage("Test");
		event.SetTitle("TEST Event");

		return event;
	}
	
	@Test
	public void TestCreateEventTrue()
	{
		try
		{
			assertTrue(GetEventService().createEvent(GetTrueEvent()));
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (NotFound e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
	}
	
	@Test
	public void TestCreateEventUserFalse()
	{
		try
		{
			Event event = GetTrueEvent();
			event.SetCreatorId(this.USERIDFALSE);
			assertFalse(GetEventService().createEvent(event));
		}
		catch (IOException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (DatabaseException e)
		{
			fail(e.getMessage() + "\n" + e.getStackTrace());
		}
		catch (NotFound e)
		{
			//Erwartet
		}
	}
	
	@Test
	public void TestCreateEventCalendarFalse()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	public void TestChangeEventTrue()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void TestChangeEventIOException()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void TestChangeEventNotExist()
	{
		fail("Not yet implemented");
	}
	
	
	@Test
	public void TestRemoveEventTrue()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void TestRemoveEventNotExist()
	{
		fail("Not yet implemented");
	}
	

	@Test
	public void TestGetEvent()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void TestGetEventNotExist()
	{
		fail("Not yet implemented");
	}
		
}
