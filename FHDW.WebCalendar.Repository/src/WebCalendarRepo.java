import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class WebCalendarRepo implements IWebCalendarRepo
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
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
			
			stmt.execute("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = \""+database+"\";");
			
			if (!stmt.getResultSet().first())
			{
				System.out.println("The Database " + database+ " was not found. It will be created.");
				int myResult = stmt.executeUpdate("CREATE DATABASE "+database+";");
				System.out.println("Create-Statement was send. Returncode: " + String.valueOf(myResult));
			}
			
			int myResult = stmt.executeUpdate("DROP DATABASE "+database+";");
			System.out.println("Drop-Statement was send. Returncode: " + String.valueOf(myResult));
			
		} catch (Exception e)
		{
			// TODO: handle exception
		} finally {
			try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	@Override
	public SaveEventResponse SaveEvent(Event event)
	{
		SaveEventResponse Response = new SaveEventResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}

	@Override
	public DeleteEventResponse DeleteEvent(Event event)
	{
		DeleteEventResponse Response = new DeleteEventResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}

	@Override
	public RegistrateUserResponse RegistrateUser(User user)
	{
		RegistrateUserResponse Response = new RegistrateUserResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}

	@Override
	public CheckUserResponse CheckUser(User user)
	{
		CheckUserResponse Response = new CheckUserResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}


}
