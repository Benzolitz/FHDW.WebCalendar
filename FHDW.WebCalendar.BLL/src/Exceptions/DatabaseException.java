package Exceptions;

/**
 * @author Frederik Heinrichs
 * TODO: Kommentar schreiben
 */
public class DatabaseException extends BaseServiceException
{
	private static final long serialVersionUID = 7762338393142045588L;

	public DatabaseException(String p_getMessage)
	{
		super(p_getMessage);
	}

	public DatabaseException(String p_message, Throwable p_cause)
	{
		super(p_message,p_cause);
	}		
}
