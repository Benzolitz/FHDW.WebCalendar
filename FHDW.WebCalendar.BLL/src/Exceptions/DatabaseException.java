package Exceptions;

import java.util.logging.Level;

import Helper.WebCalendarLog;

/**
 * @author Frederik Heinrichs
 * TODO: Kommentar schreiben
 */
public class DatabaseException extends BaseServiceException
{
	private static final long serialVersionUID = 7762338393142045588L;
	
	public static final String DATABASEEXCEPTIONSTRING = "Ein Fehler im System ist aufgetreten, bitte kontaktieren sie den Administrator";

	public DatabaseException()
	{
		super(DatabaseException.DATABASEEXCEPTIONSTRING);
		WebCalendarLog.GetLogger().log(Level.SEVERE,DatabaseException.DATABASEEXCEPTIONSTRING);
	}
	
	public DatabaseException(Throwable p_cause)
	{
		super(DatabaseException.DATABASEEXCEPTIONSTRING, p_cause);
		WebCalendarLog.GetLogger().log(Level.SEVERE,DatabaseException.DATABASEEXCEPTIONSTRING);
		WebCalendarLog.GetLogger().log(Level.SEVERE,"",p_cause);
	}

			
}
