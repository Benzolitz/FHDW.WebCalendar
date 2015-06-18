package BLL.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import BLL.Exceptions.DatabaseException;
import BLL.Exceptions.IOException;
import BLL.Exceptions.NotFound;
import BLL.Services.EventService;
import DomainModel.Calendar.Event;

public class EventServiceTest
{

    private EventService eventService;
    private final int USERIDTRUE = 1;
    private final int CALENDERIDTRUE = 1;
    private final int EVENTIDTRUE = 1;
    
    /**
     * @return the calenderServic
     */
    public EventService GetEventService()
    {
        if (this.eventService == null)
        {
            this.eventService = new EventService();
        }
        return eventService;
    }

    private Event GetTrueEvent()
    {
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
        
        List<String> requiredOptinonalUser = new ArrayList <String>();
        requiredOptinonalUser.add("User2");
        event.SetRequiredUser(requiredOptinonalUser);
        
        return event;
    }

    @Test
    public void TestCreateEvent()
    {
        try
        {
            assertTrue(GetEventService().ChangeEvent(GetTrueEvent()));
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
    public void TestChangeEvent()
    {
    	try
		{
    		assertTrue(GetEventService().ChangeEvent(GetTrueEvent()));
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
    public void TestRemoveEvent()
    {
    	try
		{
			assertTrue(GetEventService().RemoveEvent(EVENTIDTRUE, USERIDTRUE));
		}
        catch (DatabaseException e)
        {
            fail(e.getMessage() + "\n" + e.getStackTrace());
        }
    }
    
    @Test
    public void TestGetEvent()
    {
    	try
		{
    		assertNotNull(GetEventService().GetEvent(EVENTIDTRUE));
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
    public void TestSearchEvents()
    {
    	try
		{
            // Setze den SuchZeitram von 1.1.2000 - 31.12.2050
			assertNotNull(GetEventService().SearchEvents(USERIDTRUE, ""));
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
    public void TestGetEventsFromTo()
    {
    	try
		{
            // Setze den SuchZeitram von 1.1.2000 - 31.12.2050
            java.util.Calendar DateFrom = java.util.Calendar.getInstance();
            DateFrom.set(1990, 1, 1, 0, 0);
            java.util.Calendar DateTo = java.util.Calendar.getInstance();
            DateTo.set(2050, 1, 1, 0, 0);
			assertNotNull(GetEventService().GetEventsFromTo(USERIDTRUE, CALENDERIDTRUE, DateFrom, DateTo));
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
}
