package Exceptions;

/**
 * @author Frederik Heinrichs
 * TODO: Kommentar schreiben
 */
public class NotFound extends BaseServiceException
{
	private static final long serialVersionUID = - 1629332416452021740L;
	
	public NotFound(String p_string)
	{
		super(p_string);
	}
	
}
