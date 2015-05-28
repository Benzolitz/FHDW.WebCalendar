package Exceptions;

/**
 * @author Frederik Heinrichs
 * TODO: Kommentar schreiben
 */
public class AlreadyExist extends BaseServiceException
{
	private static final long serialVersionUID = 5969643010060126312L;

	public AlreadyExist(String p_message)
	{
		super(p_message);
	}	
}
