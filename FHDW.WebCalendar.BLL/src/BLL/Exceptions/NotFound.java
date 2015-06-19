package BLL.Exceptions;

/**
 * Fehlerklasse um Anzuzeigen, dass ein Object nicht mehr Existiert oder nicht gefunden werden konnte.
 * 
 * @author Frederik Heinrichs
 */
public class NotFound extends BaseServiceException
{
	private static final long serialVersionUID = - 1629332416452021740L;
	
	public NotFound(String p_string)
	{
		super(p_string);
	}
	
}
