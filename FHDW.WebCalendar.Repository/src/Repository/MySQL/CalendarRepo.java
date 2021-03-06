package Repository.MySQL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import DomainModel.Calendar.Calendar;
import DomainModel.Calendar.Event;
import DomainModel.User.*;
import IRepository.ICalendarRepo;

/**
 * Die Klasse WebCalendarRepo, welche das Interface {@link ICalendarRepo}
 * implementiert.
 * 
 * @author Eduard Kress
 */
public class CalendarRepo implements ICalendarRepo
{
    Connection conn = null;
    Statement stmt = null;
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public CalendarRepo()
    {
        InitDatabase();
    }

    /**
     * Initialisiert die Datenbank
     */
    private void InitDatabase()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String connectionUrl = "jdbc:mysql://localhost/";
            String connectionUser = "root";
            String connectionPassword = "";
            String database = "WebCalendar";
            conn = DriverManager.getConnection(connectionUrl, connectionUser,
                    connectionPassword);
            stmt = conn.createStatement();

            stmt.execute(String
                    .format("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '%s';",
                            database));

            if (!stmt.getResultSet().first())
            {
                System.out.println(String.format(
                        "The Database %s was not found. It will be created.",
                        database));
                int myResult = stmt.executeUpdate(String.format(
                        "CREATE DATABASE %s;", database));
                System.out.println(String.format(
                        "Create-Statement was send. Returncode: %s",
                        String.valueOf(myResult)));
                stmt.close();
                conn.setCatalog(database);
                stmt = conn.createStatement();

                InitDatabaseTablesWithTestData(stmt);
            }
            else
            {
                stmt.close();
                conn.setCatalog(database);
                stmt = conn.createStatement();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Erzeugt einen neuen Katalog, falls noch nicht vorhanden, und importiert
     * das Datenbankschema und Testdaten.
     */
    private void InitDatabaseTablesWithTestData(Statement p_stmt)
            throws Exception
    {
        BufferedReader in;
        String str;
        Collection<String> filePaths = new ArrayList<String>()
        {
            {
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
            }
        };

        try
        {
            System.out.println("Importing...");
            for (String filePath : filePaths)
            {
                in = new BufferedReader(new FileReader(filePath));
                System.out.println("\t" + filePath);
                StringBuffer sb = new StringBuffer();
                while ((str = in.readLine()) != null)
                    sb.append(str + "\n ");
                in.close();
                p_stmt.executeUpdate(sb.toString());
            }
            System.out.println("Import finished.");
        }
        catch (Exception e)
        {
            throw new Exception(e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetUserId(java.lang.String)
     */
    @Override
    public Integer GetUserId(String p_usernameOrEmail) throws SQLException
    {
        Integer userId = null;
        String sql;
        ResultSet rs;

        if (p_usernameOrEmail.contains("@"))
            sql = String.format("SELECT ID FROM User WHERE EMail = '%s';",
                    p_usernameOrEmail);
        else
            sql = String.format("SELECT ID FROM User WHERE Username = '%s';",
                    p_usernameOrEmail);
        rs = stmt.executeQuery(sql);
        if (rs.next())
            userId = rs.getInt(1);

        return userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetUserPassword(int)
     */
    @Override
    public String GetUserPassword(int p_userId) throws SQLException
    {
        String userPassword = null;
        String sql;
        ResultSet rs;

        sql = String.format("SELECT Pass FROM User WHERE ID = '%d';", p_userId);
        rs = stmt.executeQuery(sql);
        if (rs.next())
            userPassword = rs.getString(1);

        return userPassword;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetAllSecurityQuestions()
     */
    @Override
    public Collection<SecurityQuestion> GetAllSecurityQuestions()
            throws SQLException
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

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#RegistrateNewUser(java.lang.String,
     * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String, int, java.lang.String)
     */
    @Override
    public int RegistrateNewUser(User p_user) throws SQLException
    {
        Integer userId;
        String sql;
        ResultSet rs;

        sql = String
                .format("INSERT INTO User (Username, EMail, pass, FirstName, LastName, SecurityQuestionID, SecurityAnswer) VALUES('%s', '%s', '%s', '%s', '%s', '%d', '%s');",
                        p_user.GetUsername(), p_user.GetEMail(), p_user
                                .GetUserSecurity().GetPassword(), p_user
                                .GetFirstname(), p_user.GetLastname(), p_user
                                .GetUserSecurity().GetSecurityQuestionId(),
                        p_user.GetUserSecurity().GetSecurityAnswer());

        stmt.executeUpdate(sql);
        sql = String.format("SELECT LAST_INSERT_ID();");
        rs = stmt.executeQuery(sql);
        rs.next();
        userId = rs.getInt(1);

        return userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetSecurityQuestion(int)
     */
    @Override
    public String GetSecurityQuestion(int p_userId) throws SQLException
    {
        String securityQuestion = null;
        String sql;
        ResultSet rs;

        sql = String
                .format("SELECT SecurityQuestion.Question FROM User JOIN SecurityQuestion ON User.SecurityQuestionID = SecurityQuestion.ID where User.ID = '%d';",
                        p_userId);
        rs = stmt.executeQuery(sql);
        if (rs.next())
            securityQuestion = rs.getString(1);

        return securityQuestion;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetSecurityAnswer(int)
     */
    @Override
    public String GetSecurityAnswer(int p_userId) throws SQLException
    {
        String securityAnswer = null;
        String sql;
        ResultSet rs;

        sql = String.format("SELECT SecurityAnswer FROM User WHERE ID = '%d';",
                p_userId);
        rs = stmt.executeQuery(sql);
        rs.next();
        securityAnswer = rs.getString(1);

        return securityAnswer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#ResetPassword(int, java.lang.String)
     */
    @Override
    public void ResetPassword(int p_userId, String p_password)
            throws SQLException
    {
        String sql;

        sql = String.format("UPDATE User SET pass = '%s' WHERE ID = '%d';",
                p_password, p_userId);
        stmt.executeUpdate(sql);
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#CreateNewCalendar(int,
     * java.lang.String)
     */
    @Override
    public int CreateNewCalendar(int p_userId, String p_calendarName)
            throws SQLException
    {
        Integer calendarId = null;
        String sql;
        ResultSet rs;

        sql = String.format(
                "INSERT INTO Calendar (Name, CreatorID) VALUES ('%s', %d);",
                p_calendarName, p_userId);
        stmt.executeUpdate(sql);

        sql = String.format("SELECT LAST_INSERT_ID();");
        rs = stmt.executeQuery(sql);
        rs.next();
        calendarId = rs.getInt(1);

        return calendarId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetAllUserCalendar(int)
     */
    @Override
    public Collection<Calendar> GetAllUserCalendar(int p_userId)
            throws SQLException
    {
        Collection<Calendar> calendars;
        Calendar calendar;
        String sql;
        ResultSet rs;

        sql = String
                .format("SELECT Calendar.ID, Calendar.Name FROM Calendar WHERE Calendar.CreatorID = %d;",
                        p_userId);
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

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetEventsForUser(int, int,
     * java.util.Calendar, java.util.Calendar)
     */
    @Override
    public Collection<Event> GetEventsForUser(int p_calendarId, int p_userId,
            java.util.Calendar p_from, java.util.Calendar p_to)
            throws SQLException
    {
        Collection<Event> events;
        Event event;
        java.util.Calendar calStart = java.util.Calendar.getInstance();
        java.util.Calendar calEnd = java.util.Calendar.getInstance();
        String sql;
        ResultSet rs;

        sql = String
                .format("SELECT Event.ID, Event.StartTime, Event.EndTime, Event.Title FROM Event JOIN EventUser ON Event.ID = EventUser.EventID JOIN User ON EventUser.UserID = User.ID WHERE User.ID = '%d' AND EventUser.CalendarID = %d AND (Event.StartTime BETWEEN '%s' AND '%s');",
                        p_userId, p_calendarId, sdf.format(p_from.getTime()),
                        sdf.format(p_to.getTime()));
        rs = stmt.executeQuery(sql);

        events = new ArrayList<Event>();

        while (rs.next())
        {
            event = new Event();
            event.SetId(rs.getInt(1));
            try
            {
                calStart.setTime(sdf.parse(rs.getString(2)));
                calEnd.setTime(sdf.parse(rs.getString(3)));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            event.SetStartTime(calStart);
            event.SetEndTime(calEnd);
            event.SetTitle(rs.getString(4));
            events.add(event);
            calStart = java.util.Calendar.getInstance();
            calEnd = java.util.Calendar.getInstance();
        }

        return events;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#GetEventDetailed(int)
     */
    @Override
    public Event GetEventDetailed(int p_eventId) throws SQLException
    {
        Collection<String> optionalUser = new ArrayList<String>();
        Collection<String> requiredUser = new ArrayList<String>();
        Collection<String> categories = new ArrayList<String>();
        java.util.Calendar calStart = java.util.Calendar.getInstance();
        java.util.Calendar calEnd = java.util.Calendar.getInstance();
        java.util.Calendar calCreationTime = java.util.Calendar.getInstance();
        Event event;
        String sql;
        ResultSet rs;

        sql = String
                .format("SELECT Event.StartTime, Event.EndTime, Event.Title, Event.Location, Event.CreatorID, Event.CreationTime, Event.Message FROM Event WHERE Event.ID = %d;",
                        p_eventId);
        rs = stmt.executeQuery(sql);
        rs.next();
        event = new Event();
        try
        {
            calStart.setTime(sdf.parse(rs.getString(1)));
            calEnd.setTime(sdf.parse(rs.getString(2)));
            calCreationTime.setTime(sdf.parse(rs.getString(6)));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        event.SetStartTime(calStart);
        event.SetEndTime(calEnd);
        event.SetTitle(rs.getString(3));
        event.SetLocation(rs.getString(4));
        event.SetCreatorId(rs.getInt(5));
        event.SetCreationTime(calCreationTime);
        event.SetMessage(rs.getString(7));

        sql = String
                .format("SELECT User.EMail, EventUser.Required FROM EventUser JOIN Event ON EventUser.EventID = Event.ID JOIN User ON EventUser.UserID = User.ID WHERE Event.ID = %d;",
                        p_eventId);
        rs = stmt.executeQuery(sql);
        while (rs.next())
        {
            if (rs.getBoolean(2))
                requiredUser.add(String.format("%s", rs.getString(1)));
            else
                optionalUser.add(String.format("%s", rs.getString(1)));
        }
        event.SetOptionalUser(optionalUser);
        event.SetRequiredUser(requiredUser);

        sql = String.format("SELECT Name FROM Category WHERE EventID = %d;",
                p_eventId);
        rs = stmt.executeQuery(sql);
        while (rs.next())
        {
            categories.add(rs.getString(1));
        }
        event.SetCategory(categories);

        return event;
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#SaveEvent(java.lang.String,
     * java.lang.String, java.util.Calendar, java.util.Calendar,
     * java.lang.String, java.util.Collection, int, int, java.util.HashMap,
     * java.util.HashMap)
     */
    @Override
    public void SaveEvent(Event p_event,
            HashMap<Integer, Integer> p_requiredUserId,
            HashMap<Integer, Integer> p_optionalUserId) throws SQLException
    {
        String sql;
        ResultSet rs;
        int eventId;
        Iterator it;

        sql = String
                .format("INSERT INTO EVENT (Title, Location, StartTime, EndTime, Message, CreatorId, CreationTime) VALUES ('%s', '%s', '%s', '%s', '%s', %d, CURTIME());",
                        p_event.GetTitle(), p_event.GetLocation(),
                        sdf.format(p_event.GetStartTime().getTime()),
                        sdf.format(p_event.GetEndTime().getTime()),
                        p_event.GetMessage(), p_event.GetCreatorId());
        stmt.executeUpdate(sql);

        sql = String.format("SELECT LAST_INSERT_ID();");
        rs = stmt.executeQuery(sql);
        rs.next();
        eventId = rs.getInt(1);

        for (String category : p_event.GetCategory())
        {
            sql = String.format(
                    "INSERT INTO Category (Name, EventId) VALUES ('%s', %d);",
                    category, eventId);
            stmt.executeUpdate(sql);
        }
        it = p_optionalUserId.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            sql = String
                    .format("INSERT INTO EventUser (EventID, UserID, Required, CalendarID) VALUES (%d, %d, 0, %d)",
                            eventId, (Integer) pair.getKey(),
                            (Integer) pair.getValue());
            stmt.executeUpdate(sql);
        }
        it = p_requiredUserId.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            sql = String
                    .format("INSERT INTO EventUser (EventID, UserID, Required, CalendarID) VALUES (%d, %d, 1, %d)",
                            eventId, (Integer) pair.getKey(),
                            (Integer) pair.getValue());
            stmt.executeUpdate(sql);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#DeleteEvent(int, int)
     */
    @Override
    public void DeleteEvent(int p_eventId, int p_userId) throws SQLException
    {
        String sql;
        ResultSet rs;

        sql = String.format(
                "SELECT 1 FROM Event WHERE ID = %d AND CreatorID = %d;",
                p_eventId, p_userId);
        rs = stmt.executeQuery(sql);

        if (rs.next())
            sql = String.format("DELETE FROM Event WHERE ID = %d;", p_eventId);
        else
            sql = String
                    .format("DELETE FROM EventUser WHERE EventID = %d AND UserID = %d;",
                            p_eventId, p_userId);
        stmt.executeUpdate(sql);
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#DeleteCalendar(int)
     */
    @Override
    public void DeleteCalendar(int p_calendarId) throws SQLException
    {
        String sql;

        sql = String
                .format("DELETE FROM Calendar WHERE ID = %d;", p_calendarId);
        stmt.executeUpdate(sql);
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#DeleteUser(int)
     */
    @Override
    public void DeleteUser(int p_userId) throws SQLException
    {
        String sql;

        sql = String.format("DELETE FROM User WHERE ID = %d;", p_userId);
        stmt.executeUpdate(sql);
    }

    /*
     * (non-Javadoc)
     * 
     * @see IRepository.IWebCalendarRepo#UpdateEvent(int, java.lang.String,
     * java.lang.String, java.util.Calendar, java.util.Calendar,
     * java.lang.String, java.util.Collection, java.util.HashMap,
     * java.util.HashMap)
     */
    @Override
    public void UpdateEvent(Event p_event, 
            HashMap<Integer, Integer> requiredUserId,
            HashMap<Integer, Integer> optionalUserId) throws SQLException
    {
        String sql;
        Iterator it;

        sql = String
                .format("UPDATE Event SET Title='%s', Location='%s', StartTime='%s', EndTime='%s', Message='%s' WHERE ID=%d;",
                        p_event.GetTitle(), p_event.GetLocation(), sdf.format(p_event.GetStartTime().getTime()),
                        sdf.format(p_event.GetEndTime().getTime()), p_event.GetMessage(), p_event.GetId());
        stmt.executeUpdate(sql);

        sql = String.format("DELETE FROM Category WHERE EventID = %d;",
                p_event.GetId());
        stmt.executeUpdate(sql);

        sql = String.format("DELETE FROM EventUser WHERE EventID = %d;",
                p_event.GetId());
        stmt.executeUpdate(sql);

        for (String category : p_event.GetCategory())
        {
            sql = String.format(
                    "INSERT INTO Category (Name, EventId) VALUES ('%s', %d);",
                    category, p_event.GetId());
            stmt.executeUpdate(sql);
        }
        it = optionalUserId.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            sql = String
                    .format("INSERT INTO EventUser (EventID, UserID, Required, CalendarID) VALUES (%d, %d, 0, %d)",
                            p_event.GetId(), (Integer) pair.getKey(),
                            (Integer) pair.getValue());
            stmt.executeUpdate(sql);
        }
        it = requiredUserId.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            sql = String
                    .format("INSERT INTO EventUser (EventID, UserID, Required, CalendarID) VALUES (%d, %d, 1, %d)",
                            p_event.GetId(), (Integer) pair.getKey(),
                            (Integer) pair.getValue());
            stmt.executeUpdate(sql);
        }
    }

}
