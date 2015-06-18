package BLL.Exceptions;

/**
 * @author Frederik Heinrichs
 * Fehlermeldung f�r Eingabefehler vom Benutzer
 */
public class IOException extends BaseServiceException
{
	private static final long serialVersionUID = 5289904557352948280L;

	public IOException(String p_message)
	{
		super(p_message);
	}		
}
