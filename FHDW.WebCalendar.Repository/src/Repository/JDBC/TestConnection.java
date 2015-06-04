package Repository.JDBC;
import java.sql.SQLException;

import Model.Calendar.Calendar;
import Model.Calendar.Event.EventCalendarView;

public class TestConnection
{

	public static void main(String[] args)
	{
		WebCalendarRepo testRepo = new WebCalendarRepo();
		
		try
		{
			testRepo.RegistrateNewUser("userN", "emailN", "passN", "firstN", "LasN", "p_phoneNumber", 2, "answer");
			System.out.println("all fine");
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
