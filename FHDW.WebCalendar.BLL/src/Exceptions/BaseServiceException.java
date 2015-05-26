package Exceptions;

@SuppressWarnings ("serial")
public abstract class BaseServiceException extends Exception
{	
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
