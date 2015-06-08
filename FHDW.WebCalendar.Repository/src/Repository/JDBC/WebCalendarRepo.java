package Repository.JDBC;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import IRepository.IWebCalendarRepo;
import Model.Calendar.Calendar;
import Model.Calendar.Event.Event;
import Model.Calendar.Event.EventCalendarView;
import Model.SecurityQuestion.SecurityQuestion;


public class WebCalendarRepo implements IWebCalendarRepo
{
	Connection conn = null;
	Statement stmt = null;
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public WebCalendarRepo()
	{
		 InitDatabase();
	}

	private void InitDatabase()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionUrl = "jdbc:mysql://localhost/";
			String connectionUser = "root";
			String connectionPassword = "";
			String database = "WebCalendar";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			stmt = conn.createStatement();
			
			stmt.execute(String.format("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '%s';", database));
			
			if (!stmt.getResultSet().first())
			{
				System.out.println(String.format("The Database %s was not found. It will be created.", database));
				int myResult = stmt.executeUpdate(String.format("CREATE DATABASE %s;", database));
				System.out.println(String.format("Create-Statement was send. Returncode: %s", String.valueOf(myResult)));
				stmt.close();
				conn.setCatalog(database);
				stmt=conn.createStatement();
				
				InitDatabaseTablesWithTestData(stmt);
			}
			else
			{
				stmt.close();
				conn.setCatalog(database);
				stmt=conn.createStatement();
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void InitDatabaseTablesWithTestData(Statement p_stmt) throws Exception
	{
		BufferedReader in;
		String str;
		Collection<String> filePaths = new ArrayList<String>()
				{{ 
					add("src/../1.SecurityQuestion.sql");
					add("src/../2.User.sql");
					add("src/../3.Calendar.sql");
					add("src/../4.Event.sql");
					add("src/../5.Category.sql");
					add("src/../6.EventUser.sql");
					add("src/../1.SecurityQuestionTestData.sql");
					add("src/../2.UserTestData.sql");
					add("src/../3.CalendarTestData.sql");
					add("src/../4.EventTestData.sql");
					add("src/../5.CategoryTestData.sql");
					add("src/../6.EventUserTestData.sql");
				}};
		
		try
		{
			System.out.println("Importing...");
			for (String filePath : filePaths)
			{
				in = new BufferedReader(new FileReader(filePath));
				System.out.println("\t"+filePath);
				StringBuffer sb = new StringBuffer();
				while ((str = in.readLine()) != null)
					sb.append(str + "\n ");
				in.close();
				p_stmt.executeUpdate(sb.toString());
			}
			System.out.println("Import finished.");
		} catch (Exception e)
		{
			throw new Exception(e);
		}
		
	}
	
	@Override
	public Integer GetUserId(String p_usernameOrEmail) throws SQLException
	{
		Integer userId = null;
		String sql;
		ResultSet rs;
	
		if (p_usernameOrEmail.contains("@"))
			sql = String.format("SELECT ID FROM User WHERE EMail = '%s';", p_usernameOrEmail);
		else
			sql = String.format("SELECT ID FROM User WHERE Username = '%s';", p_usernameOrEmail);			
		rs = stmt.executeQuery(sql);
		if(rs.next())
			userId = rs.getInt(1);
		
		return userId;
	}
		
	@Override
	public String GetUserPassword(int p_userId) throws SQLException
	{
		String userPassword;
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT Pass FROM User WHERE ID = '%d';", p_userId);
		rs = stmt.executeQuery(sql);
		rs.next();
		userPassword = rs.getString(1);

		return userPassword;
	}
	
	@Override
	public Collection<SecurityQuestion> GetAllSecurityQuestions() throws SQLException
	{
		Collection<SecurityQuestion> questions;
		SecurityQuestion question;
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT ID, Question FROM SecurityQuestion;");
		rs = stmt.executeQuery(sql);
		
		questions = new ArrayList<SecurityQuestion>();
		
		while (rs.next())
		{
			question = new SecurityQuestion();
			question.SetId(rs.getInt(1));
			question.SetName(rs.getString(2));
			questions.add(question);
		}

		return questions;
	}
	
	@Override
	public int RegistrateNewUser(String p_username, String p_email, String p_password, String p_firstName, String p_lastName, String p_phoneNumber, int p_securityQuestion, String p_securityAnswer) throws SQLException
	{
		Integer userId;
		String sql;
		ResultSet rs;
		
		sql = String.format("INSERT INTO User (Username, EMail, pass, FirstName, LastName, SecurityQuestionID, SecurityAnswer) VALUES('%s', '%s', '%s', '%s', '%s', '%d', '%s');", p_username, p_email, p_password, p_firstName, p_lastName, p_securityQuestion, p_securityAnswer);

		stmt.executeUpdate(sql);
		sql = String.format("SELECT LAST_INSERT_ID();");
		rs = stmt.executeQuery(sql);
		rs.next();
		
		sql = String.format("INSERT INTO Calendar (Name, CreatorID) VALUES ('Mein Kalendar', %d);", rs.getInt(1));
		userId = rs.getInt(1);
		stmt.executeUpdate(sql);
		
		return userId;
	}
	
	@Override
	public String GetSecurityQuestion(int p_userId) throws SQLException
	{
		String securityQuestion = null;
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT SecurityQuestion.Question FROM User JOIN SecurityQuestion ON User.SecurityQuestionID = SecurityQuestion.ID where User.ID = '%d';", p_userId);
		rs = stmt.executeQuery(sql);
		rs.next();
		securityQuestion = rs.getString(1);
	
		return securityQuestion;
	}
		
	@Override
	public String GetSecurityAnswer(int p_userId) throws SQLException
	{
		String securityAnswer = null;
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT SecurityAnswer FROM User WHERE ID = '%d';", p_userId);
		rs = stmt.executeQuery(sql);
		rs.next();
		securityAnswer = rs.getString(1);

		return securityAnswer;
	}
	
	@Override
	public void ResetPassword(int p_userId, String p_password) throws SQLException
	{
		String sql;
		
		sql = String.format("UPDATE User SET pass = '%s' WHERE ID = '%d';", p_password, p_userId);
		stmt.executeUpdate(sql);
	}
	
	@Override
	public int CreateNewCalendar(int p_userId, String p_calendarName) throws SQLException
	{
		Integer calendarId = null;
		String sql;
		ResultSet rs;
		
		sql = String.format("INSERT INTO Calendar (Name, CreatorID) VALUES ('%s', %d);", p_calendarName, p_userId);
		stmt.executeUpdate(sql);
		
		sql = String.format("SELECT LAST_INSERT_ID();");
		rs = stmt.executeQuery(sql);
		rs.next();
		calendarId = rs.getInt(1);

		return calendarId;
	}
	
	@Override
	public Collection<Calendar> GetAllUserCalendar(int p_userId)  throws SQLException
	{
		Collection<Calendar> calendars;
		Calendar calendar;
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT Calendar.ID, Calendar.Name FROM Calendar WHERE Calendar.CreatorID = %d;", p_userId);
		rs = stmt.executeQuery(sql);
		
		calendars = new ArrayList<Calendar>();
		
		while (rs.next())
		{
			calendar = new Calendar();
			calendar.SetId(rs.getInt(1));
			calendar.SetName(rs.getString(2));
			calendar.SetOwnerId(p_userId);
			calendars.add(calendar);
		}

		return calendars;
	}
		
	@Override
	public Collection<EventCalendarView> GetEventsForUser(int p_calendarId, int p_userId, java.util.Calendar p_from, java.util.Calendar p_to) throws SQLException
	{
		Collection<EventCalendarView> events;
		EventCalendarView event;
		java.util.Calendar calStart = java.util.Calendar.getInstance();
		java.util.Calendar calEnd = java.util.Calendar.getInstance();
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT Event.ID, Event.StartTime, Event.EndTime, Event.Title FROM Event JOIN EventUser ON Event.ID = EventUser.EventID JOIN User ON EventUser.UserID = User.ID WHERE User.ID = '%d' AND Event.CalendarID = %d AND (Event.StartTime BETWEEN '%s' AND '%s');", p_userId, p_calendarId, sdf.format(p_from.getTime()), sdf.format(p_to.getTime()));
		rs = stmt.executeQuery(sql);
		
		events = new ArrayList<EventCalendarView>();
		
		while (rs.next())
		{
			event = new EventCalendarView();
			event.SetId(rs.getInt(1));
			calStart.setTime(rs.getDate(2));
			event.SetStartTime(calStart);
			calEnd.setTime(rs.getDate(3));
			event.SetEndTime(calEnd);
			event.SetTitle(rs.getString(4));
			events.add(event);
		}
	
		return events;
	}

	@Override
	public Event GetEventDetailed(int p_eventId) throws SQLException
	{
		Collection<String> optionalUser = new ArrayList<String>();
		Collection<String> requiredUser = new ArrayList<String>();
		java.util.Calendar calStart = java.util.Calendar.getInstance();
		java.util.Calendar calEnd = java.util.Calendar.getInstance();
		java.util.Calendar calCreationTime = java.util.Calendar.getInstance();
		Event event;
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT Event.StartTime, Event.EndTime, Event.Title, Event.Location, Event.CreatorID, Event.CreationTime, Event.Message, Event.CalendarID FROM Event WHERE Event.ID = %d;", p_eventId);
		rs = stmt.executeQuery(sql);
		rs.next();
		event = new Event();
		calStart.setTime(rs.getDate(1));
		event.SetStartTime(calStart);
		calEnd.setTime(rs.getDate(2));
		event.SetEndTime(calEnd);
		event.SetTitle(rs.getString(3));
		event.SetLocation(rs.getString(4));
		event.SetCreatorId(rs.getInt(5));
		calCreationTime.setTime(rs.getDate(6));
		event.SetCreationTime(calCreationTime);
		event.SetMessage(rs.getString(7));
		event.SetCalendarId(rs.getInt(8));
		
		sql = String.format("SELECT User.EMail, User.FirstName, User.LastName ,EventUser.Required FROM EventUser JOIN Event ON EventUser.EventID = Event.ID JOIN User ON EventUser.UserID = User.ID WHERE Event.ID = %d;", p_eventId);
		rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			if(rs.getBoolean(4))
				requiredUser.add(String.format("%s, %s (%s)", rs.getString(3), rs.getString(2), rs.getString(1)));
			else
				optionalUser.add(String.format("%s, %s (%s)", rs.getString(3), rs.getString(2), rs.getString(1)));
		}
		event.SetOptionalUser(optionalUser);
		event.SetRequiredUser(requiredUser);
		
		return event;
	}
	
	//TODO: Remove Null-Checks
	@Override
	public void SaveEvent(String p_title, String p_location, java.util.Calendar p_starttime, java.util.Calendar p_endtime, String p_message, Collection<String> p_categories, int p_creatorId, int p_calendarId, Collection<Integer> requiredUserId, Collection<Integer> optionalUserId) throws SQLException
	{
		String sql;
		ResultSet rs;
		int eventId;
		
		sql = String.format("INSERT INTO EVENT (Title, Location, StartTime, EndTime, Message, CreatorId, CreationTime, CalendarId) VALUES ('%s', '%s', '%s', '%s', '%s', %d, CURTIME(), %d);", p_title, p_location, sdf.format(p_starttime.getTime()), sdf.format(p_endtime.getTime()), p_message, p_creatorId, p_calendarId);
		stmt.executeUpdate(sql);
		
		sql = String.format("SELECT LAST_INSERT_ID();");
		rs = stmt.executeQuery(sql);
		rs.next();
		eventId =  rs.getInt(1);
		
		for (String category : p_categories)
		{
			sql = String.format("INSERT INTO Category (Name, EventId) VALUES ('%s', %d);", category, eventId);
			stmt.executeUpdate(sql);
		}
		if(optionalUserId != null)
			for (Integer id : optionalUserId)
			{
				sql = String.format("INSERT INTO EventUser (EventID, UserID, Required) VALUES (%d, %d, 0)", eventId, id);
				stmt.executeUpdate(sql);
			}
		if(requiredUserId != null)
			for (Integer id : requiredUserId)
			{
				sql = String.format("INSERT INTO EventUser (EventID, UserID, Required) VALUES (%d, %d, 1)", eventId, id);
				stmt.executeUpdate(sql);
			}
		
	}
	
	@Override
	public void DeleteEvent(int p_eventId, int p_userId) throws SQLException
	{
		String sql;
		ResultSet rs;
		
		sql = String.format("SELECT 1 FROM Event WHERE ID = %d AND CreatorID = %d;", p_eventId, p_userId);
		rs = stmt.executeQuery(sql);
		
		if(rs.next())
			sql = String.format("DELETE FROM Event WHERE ID = %d;", p_eventId);
		else
			sql = String.format("DELETE FROM EventUser WHERE EventID = %d AND UserID = %d;", p_eventId, p_userId);
		stmt.executeUpdate(sql);
	}
	
	@Override
	public void DeleteCalendar(int p_calendarId) throws SQLException
	{
		String sql;
		
		sql = String.format("DELETE FROM Calendar WHERE ID = %d;", p_calendarId);
		stmt.executeUpdate(sql);
	}
	
	@Override
	public void DeleteUser(int p_userId) throws SQLException
	{
		String sql;
		
		sql = String.format("DELETE FROM User WHERE ID = %d;", p_userId);
		stmt.executeUpdate(sql);
	}

	@Override
	public void UpdateEvent(int p_eventId, String p_title, String p_location, java.util.Calendar p_starttime, java.util.Calendar p_endtime, String p_message, Collection<String> p_categories, Collection<Integer> requiredUserId, Collection<Integer> optionalUserId) throws SQLException
	{
		String sql;
		
		sql = String.format("UPDATE Event SET Title='%s', Location='%s', StartTime='%s', EndTime='%s', Message='%s' WHERE ID=%d;", p_title, p_location, sdf.format(p_starttime.getTime()), sdf.format(p_endtime.getTime()), p_message, p_eventId);
		stmt.executeUpdate(sql);
		
		sql = String.format("DELETE FROM Category WHERE EventID = %d;", p_eventId);
		stmt.executeUpdate(sql);
		
		sql = String.format("DELETE FROM EventUser WHERE EventID = %d;", p_eventId);
		stmt.executeUpdate(sql);
		
		for (String category : p_categories)
		{
			sql = String.format("INSERT INTO Category (Name, EventId) VALUES ('%s', %d);", category, p_eventId);
			stmt.executeUpdate(sql);
		}
		for (Integer id : optionalUserId)
		{
			sql = String.format("INSERT INTO EventUser (EventID, UserID, Required) VALUES (%d, %d, 0)", p_eventId, id);
			stmt.executeUpdate(sql);
		}
		for (Integer id : requiredUserId)
		{
			sql = String.format("INSERT INTO EventUser (EventID, UserID, Required) VALUES (%d, %d, 1)", p_eventId, id);
			stmt.executeUpdate(sql);
		}
	}

}
