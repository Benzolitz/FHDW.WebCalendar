import java.util.ArrayList;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Array;


public class TestConnection
{

	public static void main(String[] args) {
		
		WebCalendarRepo testRepo = new WebCalendarRepo();
		
		User testUser = new User();
		testUser.SetId(1);
		
		Event testEvent = new Event();
		testEvent.SetCategory(new ArrayList<String>(){{add("5");}});
		testEvent.SetCreationTime(new Date());
		testEvent.SetCreator(testUser);
		testEvent.SetEndTime(new Date());
		testEvent.SetLocation("Location Troll");
		testEvent.SetMessage("Message Troll");
		testEvent.SetStartTime(new Date());
		
		testRepo.SaveEvent(testEvent);
	}

}
