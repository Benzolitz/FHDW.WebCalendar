package BLL.Exceptions;

/**
 * Fehlermeldung für Eingabefehler vom Benutzer.
 * 
 * @author Frederik Heinrichs
 */
public class IOException extends BaseServiceException
{
	private static final long serialVersionUID = 5289904557352948280L;

	public IOException(String p_message)
	{
		super(p_message);
	}		
}
