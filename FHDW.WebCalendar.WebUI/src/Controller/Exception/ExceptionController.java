package Controller.Exception;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ExceptionController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public static void handleRuntimeException(Exception ex, HttpServletResponse response, String message) throws IOException
	{
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.flushBuffer();
	}
	
}
