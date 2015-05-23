import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class WebCalendarRepo implements IWebCalendarRepo
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
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
			stmt=conn.createStatement();
			
			stmt.execute("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = \"" + database + "\";");
			
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
		} finally {
			//try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			//try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			//try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	private void InitDatabaseTablesWithTestData(Statement p_stmt) throws Exception
	{
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
		BufferedReader in;
		String str;
		try
		{
			System.out.println("Importing...");
			for (String filePath : filePaths)
			{
				in = new BufferedReader(new FileReader(filePath));
				System.out.println("\t"+filePath);
				StringBuffer sb = new StringBuffer();
				while ((str = in.readLine()) != null)
				{
					sb.append(str + "\n ");
				}
				in.close();
				p_stmt.executeUpdate(sb.toString());
			}
		} catch (Exception e)
		{
			throw new Exception(e);
		}
		
	}
	
	@Override
	public CheckUsernameOrEmailResponse CheckUsernameOrEmail(CheckUsernameOrEmailRequest p_request)
	{
		CheckUsernameOrEmailResponse Response = new CheckUsernameOrEmailResponse();
		String sql;
		ResultSet rs;
		
		try
		{
			if (p_request.GetUsernameOrEmail().contains("@"))
			{
				sql = String.format("SELECT 1 FROM user WHERE EMail = '%s';", p_request.GetUsernameOrEmail());
			} else
			{
				sql = String.format("SELECT 1 FROM user WHERE Username = '%s';", p_request.GetUsernameOrEmail());
			}
			rs = stmt.executeQuery(sql);
			
			if(rs.first())
			{
				Response.MessageSuccess("Username or Email exists.");
			}else
			{
				throw new Exception("Username or Email does not exist.");
			}
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	@Override
	public ValidateLoginResponse ValidateLogin(ValidateLoginRequest p_request)
	{
		ValidateLoginResponse Response = new ValidateLoginResponse();
		String sql;
		ResultSet rs;
		
		CheckUsernameOrEmailResponse CheckUsernameOrEmailrp;
		CheckUsernameOrEmailRequest CheckUsernameOrEmailrq = new CheckUsernameOrEmailRequest();
		CheckUsernameOrEmailrq.SetUsernameOrEmail(p_request.GetUsernameOrEmail());
		
		try
		{
			CheckUsernameOrEmailrp = CheckUsernameOrEmail(CheckUsernameOrEmailrq);
			if(!CheckUsernameOrEmailrp.IsSuccess())
			{
				throw new Exception(CheckUsernameOrEmailrp.GetMessage());
			}
			
			sql = String.format("SELECT ID FROM user WHERE (Username = '%s' OR EMail = '%s') AND pass = '%s';", p_request.GetUsernameOrEmail(), p_request.GetUsernameOrEmail(), p_request.GetPassword());
			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				Response.SetUserId(rs.getInt(1));
				Response.MessageSuccess("Login is successful.");
			}else
			{
				throw new Exception("Login failed. Wrong Password.");
			}
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	@Override
	public RegistrateNewUserResponse RegistrateNewUser(RegistrateNewUserRequest p_request)
	{
		RegistrateNewUserResponse Response = new RegistrateNewUserResponse();
		String sql;
		int userId;
		int rs;
		ResultSet rs2;
		
		CheckUsernameOrEmailResponse CheckUsernameOrEmailrp;
		CheckUsernameOrEmailRequest CheckUsernameOrEmailrq = new CheckUsernameOrEmailRequest();
		
		try
		{
			CheckUsernameOrEmailrq.SetUsernameOrEmail(p_request.GetUsername());
			CheckUsernameOrEmailrp = CheckUsernameOrEmail(CheckUsernameOrEmailrq);
			if(CheckUsernameOrEmailrp.IsSuccess())
			{
				throw new Exception("Username already exists.");
			}
			CheckUsernameOrEmailrq.SetUsernameOrEmail(p_request.GetEMail());
			CheckUsernameOrEmailrp = CheckUsernameOrEmail(CheckUsernameOrEmailrq);
			if(CheckUsernameOrEmailrp.IsSuccess())
			{
				throw new Exception("Email already in use.");
			}
			
			sql = String.format("INSERT INTO User (Username, EMail, pass, FirstName, LastName, SecurityQuestionID, SecurityAnswer) VALUES('%s', '%s', '%s', '%s', '%s', '%d', '%s');", p_request.GetUsername(), p_request.GetEMail(), p_request.GetPassword(), p_request.GetFirstName(), p_request.GetLastName(), p_request.GetSecurityQuestion(), p_request.GetSecurityAnswer());
			rs = stmt.executeUpdate(sql);
			
			if (!(rs > 0))
				throw new Exception("An unknown error occured.");
			
			sql = String.format("SELECT LAST_INSERT_ID();");
			rs2 = stmt.executeQuery(sql);
			rs2.next();
			userId = rs2.getInt(1);
			
			sql = String.format("INSERT INTO Calendar (Name, CreatorID) VALUES ('Mein Kalendar', %d);", userId);
			rs = stmt.executeUpdate(sql);
			
			if (!(rs > 0))
				throw new Exception("An unknown error occured.");
			
			Response.SetUserId(userId);
			Response.MessageSuccess("New User with Username " + p_request.GetUsername() + " has been registrated. New Calendar was created for this User.");
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	@Override
	public GetSecurityQuestionResponse GetSecurityQuestion(GetSecurityQuestionRequest p_request)
	{
		GetSecurityQuestionResponse Response = new GetSecurityQuestionResponse();
		String sql;
		ResultSet rs;
		
		CheckUsernameOrEmailResponse CheckUsernameOrEmailrp;
		CheckUsernameOrEmailRequest CheckUsernameOrEmailrq = new CheckUsernameOrEmailRequest();
		CheckUsernameOrEmailrq.SetUsernameOrEmail(p_request.GetUsernameOrEmail());
		
		try
		{
			CheckUsernameOrEmailrp = CheckUsernameOrEmail(CheckUsernameOrEmailrq);
			if(!CheckUsernameOrEmailrp.IsSuccess())
			{
				throw new Exception(CheckUsernameOrEmailrp.GetMessage());
			}
			
			sql = String.format("SELECT SecurityQuestion.Question FROM User JOIN SecurityQuestion ON User.SecurityQuestionID = SecurityQuestion.ID where Username = '%s' or EMail = '%s';", p_request.GetUsernameOrEmail(), p_request.GetUsernameOrEmail());
			rs = stmt.executeQuery(sql);
			rs.next();
			Response.SetSecurityQuestion(rs.getString(1));
			Response.MessageSuccess("Question was found.");
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	
	@Override
	public ValidateSecurityAnswerResponse ValidateSecurityAnswer(ValidateSecurityAnswerRequest p_request)
	{
		ValidateSecurityAnswerResponse Response = new ValidateSecurityAnswerResponse();
		String sql;
		ResultSet rs;
		
		try
		{
			sql = String.format("SELECT 1 FROM User WHERE (Username = '%s' OR EMail = '%s') AND SecurityAnswer = '%s';", p_request.GetUsernameOrEmail(), p_request.GetUsernameOrEmail(), p_request.GetAnswer());
			rs = stmt.executeQuery(sql);
			
			if(rs.first())
			{
				Response.MessageSuccess("Answer was correct.");
			}else
			{
				throw new Exception("Answer was incorrect.");
			}
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	
	@Override
	public ResetPasswordResponse ResetPassword(ResetPasswordRequest p_request)
	{
		ResetPasswordResponse Response = new ResetPasswordResponse();
		String sql;
		int rs;
		
		try
		{
			sql = String.format("UPDATE User SET pass = '%s' WHERE Username = '%s' OR EMail = '%s';", p_request.GetPassword(), p_request.GetUsernameOrEmail(), p_request.GetUsernameOrEmail());
			rs = stmt.executeUpdate(sql);
			
			if (rs > 0)
			{
				Response.MessageSuccess("Password has been successfully changed.");
			}else
			{
				Response.MessageFailure("An unknown error occured.");
			}
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	
	@Override
	public CreateNewCalendarResponse CreateNewCalendar(CreateNewCalendarRequest p_request)
	{
		CreateNewCalendarResponse Response = new CreateNewCalendarResponse();
		String sql;
		int rs;
		ResultSet rs2;
		
		try
		{
			sql = String.format("INSERT INTO Calendar (Name, CreatorID) VALUES ('%s', %d);", p_request.GetCalendarName(), p_request.GetUserId());
			rs = stmt.executeUpdate(sql);
			
			sql = String.format("SELECT LAST_INSERT_ID();");
			rs2 = stmt.executeQuery(sql);
			rs2.next();
			
			Response.SetCalendarId(rs2.getInt(1));
			Response.MessageSuccess(String.format("Calendar with the Name %s was successfully created.", p_request.GetCalendarName()));
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}

		return Response;
	}
	
	@Override
	public GetAllUserCalendarResponse GetAllUserCalendar(GetAllUserCalendarRequest p_request)
	{
		GetAllUserCalendarResponse Response = new GetAllUserCalendarResponse();
		Collection<Calendar> calendars = new ArrayList<Calendar>();
		Calendar calendar;
		String sql;
		ResultSet rs;
		
		try
		{
			sql = String.format("SELECT Calendar.ID, Calendar.Name FROM Calendar WHERE Calendar.CreatorID = 1;", p_request.GetUserId());
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				calendar = new Calendar();
				calendar.SetId(rs.getInt(1));
				calendar.SetName(rs.getString(2));
				calendar.SetOwnerId(p_request.GetUserId());
				calendars.add(calendar);
			}
			
			Response.SetCalendars(calendars);
			Response.MessageSuccess(String.format("Calendars of User %d were found.", p_request.GetUserId()));
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}

		return Response;
	}
	
	//TODO: Events zu einem bestimmten Kalender wiedergeben
	
	@Override
	public GetEventsForUserResponse GetEventsForUser(GetEventsForUserRequest p_request)
	{
		GetEventsForUserResponse Response = new GetEventsForUserResponse();
		Collection<EventCalendarView> events = new ArrayList<EventCalendarView>();
		EventCalendarView event;
		Collection<String> categories;
		String sql;
		ResultSet rs;
		
		try
		{
			sql = String.format("SELECT Event.ID, Event.StartTime, Event.EndTime, Event.Title, Event.Location, Event.CalendarID, EventUser.Required FROM Event JOIN EventUser ON Event.ID = EventUser.EventID JOIN User ON EventUser.UserID = User.ID WHERE User.ID = '%d' AND Event.CalendarID = %d;", p_request.GetUserId(), p_request.GetCalendarId());
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				event = new EventCalendarView();
				event.SetId(rs.getInt(1));
				event.SetStartTime(rs.getDate(2));
				event.SetEndTime(rs.getDate(3));
				event.SetTitle(rs.getString(4));
				event.SetLocation(rs.getString(5));
				event.SetCalendarId(rs.getInt(6));
				event.SetRequired(rs.getBoolean(7));
				events.add(event);
			}
			for (EventCalendarView e : events)
			{
				categories = new ArrayList<String>();
				sql = String.format("SELECT Category.Name FROM Category JOIN Event ON Category.EventID = Event.ID WHERE Event.ID = %d;", e.GetId());
				rs = stmt.executeQuery(sql);
				while (rs.next())
					categories.add(rs.getString(1));
				e.SetCategory(categories);
			}
			Response.SetEvents(events);
			Response.MessageSuccess(String.valueOf(events.size()) + " has been returned;");
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	//TODO: Creator in Event zu CreatorID wechseln
	
	@Override
	public GetEventDetailedResponse GetEventDetailed(GetEventDetailedRequest p_request)
	{
		GetEventDetailedResponse Response = new GetEventDetailedResponse();
		Collection<String> optionalUser = new ArrayList<String>();
		Collection<String> requiredUser = new ArrayList<String>();
		Event event = new Event();
		String sql;
		ResultSet rs;
		
		try
		{
			sql = String.format("SELECT Event.StartTime, Event.EndTime, Event.Title, Event.Location, Event.CreatorID, Event.CreationTime, Event.Message, Event.CalendarID FROM Event WHERE Event.ID = %d;", p_request.GetEventId());
			rs = stmt.executeQuery(sql);
			rs.next();
			event.SetStartTime(rs.getDate(1));
			event.SetEndTime(rs.getDate(2));
			event.SetTitle(rs.getString(3));
			event.SetLocation(rs.getString(4));
			//event.SetCreatorId(rs.getInt(5));
			event.SetCreationTime(rs.getDate(6));
			event.SetMessage(rs.getString(7));
			event.SetCalendarId(rs.getInt(8));
			
			sql = String.format("SELECT User.EMail, User.FirstName, User.LastName ,EventUser.Required FROM EventUser JOIN Event ON EventUser.EventID = Event.ID JOIN User ON EventUser.UserID = User.ID WHERE Event.ID = %d;", p_request.GetEventId());
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
			
			Response.SetEvent(event);
			Response.MessageSuccess("Event informations recieved.");
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}
		
		return Response;
	}
	
	
	@Override
	public SaveEventResponse SaveEvent(Event event)
	{
		SaveEventResponse Response = new SaveEventResponse();
		
		try
		{		
			stmt.executeUpdate(String.format("INSERT INTO Event (StartTime, EndTime, Location, CreatorID, CreationTime, Message, CalendarID)"
					+ "VALUES ('%s', '%s', '%s', %d, '%s', '%s', %d);",
					sdf.format(event.GetStartTime()),
					sdf.format(event.GetEndTime()),
					event.GetLocation(),
					event.GetCreator().GetId(),
					sdf.format(new Date()),
					event.GetMessage(),
					1
					));
			Response.MessageSuccess("Inserted new Event.");
		} catch (SQLException e)
		{
			e.printStackTrace();
			Response.MessageFailure("Not implemented.");
			System.out.println(e.getMessage());
		}
		
		return Response;
	}

	
	@Override
	public DeleteEventResponse DeleteEvent(Event event)
	{
		DeleteEventResponse Response = new DeleteEventResponse();
		
		try
		{
			throw new Exception("Not implemented.");
		} catch (Exception e)
		{
			Response.MessageFailure(e.getMessage());
		}

		return Response;
	}

	
	

		
}
