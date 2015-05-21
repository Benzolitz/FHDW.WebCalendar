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
				System.out.println("The Database " + database + " was not found. It will be created.");
				int myResult = stmt.executeUpdate("CREATE DATABASE " + database + ";");
				System.out.println("Create-Statement was send. Returncode: " + String.valueOf(myResult));
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
		String statement;
		ResultSet rs;
		
		try
		{
			if (p_request.GetUsernameOrEmail().contains("@"))
			{
				statement = String.format("SELECT 1 FROM user where EMail = '%s'", p_request.GetUsernameOrEmail());
			} else
			{
				statement = String.format("SELECT 1 FROM user where Username = '%s'", p_request.GetUsernameOrEmail());
			}
			rs = stmt.executeQuery(statement);
			
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
		String statement;
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
			
			statement = String.format("SELECT 1 FROM user where (Username = '%s' or EMail = '%s') and pass = '%s';", p_request.GetUsernameOrEmail(), p_request.GetUsernameOrEmail(), p_request.GetPassword());
			rs = stmt.executeQuery(statement);

			if(rs.first())
			{
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
		String statement;
		int rs;
		
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
				throw new Exception("Email already used.");
			}
			
			statement = String.format("INSERT INTO User (Username, EMail, pass, FirstName, LastName, SecurityQuestionID, SecurityAnswer) VALUES('%s', '%s', '%s', '%s', '%s', '%d', '%s');", p_request.GetUsername(), p_request.GetEMail(), p_request.GetPassword(), p_request.GetFirstName(), p_request.GetLastName(), p_request.GetSecurityQuestion(), p_request.GetSecurityAnswer());
			rs = stmt.executeUpdate(statement);
			
			if (rs > 0)
			{
				Response.MessageSuccess("New User with Username " + p_request.GetUsername() + " has been registrated.");
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
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}



	
}
