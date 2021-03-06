package BLL.Exceptions;

/**
 * Basisklasse f�r alle Exception aus der BLL.
 * 
 * @author Frederik Heinrichs
 */
public abstract class BaseServiceException extends Exception
{	
	private static final long serialVersionUID = 5295251919693217551L;

	public BaseServiceException() {
		super();
	}
	
	public BaseServiceException(String p_message) {
		super(p_message);
	}
	
	public BaseServiceException(String p_message, Throwable p_cause) {
		super(p_message,p_cause);
	}
	
}
