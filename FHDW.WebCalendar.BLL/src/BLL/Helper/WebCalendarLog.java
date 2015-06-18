package BLL.Helper;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class WebCalendarLog
{	
	private static java.util.logging.Logger theLogger;
	private static final String LOGGERFILE = "Log.txt";
	
	public static java.util.logging.Logger GetLogger() {
		if (WebCalendarLog.theLogger == null) {
			WebCalendarLog.theLogger = java.util.logging.Logger.getLogger(WebCalendarLog.LOGGERFILE);	
			// File Handler erzeugen
			try
			{
				Handler file_handler = new FileHandler("ErrorLog.txt", true);
				//file_handler.setFormatter(new SimpleFormatter());
				file_handler.setFormatter(new SimpleFormatter());
				WebCalendarLog.theLogger.addHandler(file_handler);
			}
			catch (Exception ex) {
				WebCalendarLog.theLogger.log(Level.SEVERE, "Error creating log file!");
				WebCalendarLog.theLogger.log(Level.SEVERE, "", ex);
			}
		}
		return WebCalendarLog.theLogger;
	}
	
}
