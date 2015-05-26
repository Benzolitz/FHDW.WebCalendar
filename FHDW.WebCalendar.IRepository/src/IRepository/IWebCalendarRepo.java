package IRepository;

import IRepository.Request.CreateNewCalendarRequest;
import IRepository.Request.DeleteCalendarRequest;
import IRepository.Request.DeleteEventRequest;
import IRepository.Request.DeleteUserRequest;
import IRepository.Request.GetAllSecurityQuestionsRequest;
import IRepository.Request.GetAllUserCalendarRequest;
import IRepository.Request.GetEventDetailedRequest;
import IRepository.Request.GetEventsForUserRequest;
import IRepository.Request.GetSecurityAnswerRequest;
import IRepository.Request.GetSecurityQuestionRequest;
import IRepository.Request.GetUserIdRequest;
import IRepository.Request.GetUserPasswordRequest;
import IRepository.Request.RegistrateNewUserRequest;
import IRepository.Request.ResetPasswordRequest;
import IRepository.Request.SaveEventRequest;
import IRepository.Request.UpdateEventRequest;
import IRepository.Response.CreateNewCalendarResponse;
import IRepository.Response.DeleteCalendarResponse;
import IRepository.Response.DeleteEventResponse;
import IRepository.Response.DeleteUserResponse;
import IRepository.Response.GetAllSecurityQuestionsResponse;
import IRepository.Response.GetAllUserCalendarResponse;
import IRepository.Response.GetEventDetailedResponse;
import IRepository.Response.GetEventsForUserResponse;
import IRepository.Response.GetSecurityAnswerResponse;
import IRepository.Response.GetSecurityQuestionResponse;
import IRepository.Response.GetUserIdResponse;
import IRepository.Response.GetUserPasswordResponse;
import IRepository.Response.RegistrateNewUserResponse;
import IRepository.Response.ResetPasswordResponse;
import IRepository.Response.SaveEventResponse;
import IRepository.Response.UpdateEventResponse;

/**
 * @author Eduard
 *
 */
public interface IWebCalendarRepo
{
	/**
	 * Pr�ft, ob es einen Benutzer mit dem �bergebenen Benutzernamen bzw. Email gibt.
	 * @param p_request
	 * @return Response-Objekt, in dem Informationen �ber die Abfrage stehen
	 */
	GetUserIdResponse GetUserId(GetUserIdRequest p_request); 
		
	/**
	 * Liefert das Passwort zu einem Benutzer.
	 * @param p_request
	 * @return
	 */
	GetUserPasswordResponse GetUserPassword(GetUserPasswordRequest p_request);
	
	/**
	 * Liefert alle m�glichen Sicherheitsfragen.
	 * @param p_request
	 * @return
	 */
	GetAllSecurityQuestionsResponse GetAllSecurityQuestions(GetAllSecurityQuestionsRequest p_request);
	
	/**
	 * Registriert einen neuen Benutzer. Erstellt zugleich einen leeren Kalender f�r diesen Benutzer.
	 * @param p_request
	 * @return
	 */
	RegistrateNewUserResponse RegistrateNewUser(RegistrateNewUserRequest p_request);
	
	/**
	 * Liefert die Sicherheitsfrage zu einem Benutzerkonto.
	 * @param p_request
	 * @return
	 */
	GetSecurityQuestionResponse GetSecurityQuestion (GetSecurityQuestionRequest p_request);
		
	/**
	 * Liefert von einem Benutzer die Sicherheitsantwort.
	 * @param p_request
	 * @return
	 */
	GetSecurityAnswerResponse GetSecurityAnswer(GetSecurityAnswerRequest p_request);
	
	/**
	 * Setzt das Password eines Benutzers zur�ck.
	 * @param p_request
	 * @return
	 */
	ResetPasswordResponse ResetPassword(ResetPasswordRequest p_request);
	
	/**
	 * Erstellt f�r einen bestimmten Benutzer einen neuen Kalendar.
	 * @param p_request
	 * @return
	 */
	CreateNewCalendarResponse CreateNewCalendar(CreateNewCalendarRequest p_request);
	
	/**
	 * Gibt alle Kalender von einem Benutzer zur�ck.
	 * @param p_request
	 * @return
	 */
	GetAllUserCalendarResponse GetAllUserCalendar(GetAllUserCalendarRequest p_request);
	
	/**
	 * Gibt alle Termine inklusive Kategorien f�r die Kalendaransicht zu einem bestimmten Benutzer zur�ck.
	 * @param p_request
	 * @return
	 */
	GetEventsForUserResponse GetEventsForUser(GetEventsForUserRequest p_request); 
	
	/**
	 * Gibt zu einem bestimmten Termin alle Informationen wieder.
	 * @param p_request
	 * @return
	 */
	GetEventDetailedResponse GetEventDetailed(GetEventDetailedRequest p_request);
	
	/**
	 * Speichert einen neuen oder ge�nderten Termin.
	 * @param event	Terminobjekt, welches gespeichert werden soll
	 */
	SaveEventResponse SaveEvent(SaveEventRequest p_request);

	/**
	 * L�scht einen vorhandenen Termin.
	 * @param p_request
	 * @return
	 */
	DeleteEventResponse DeleteEvent(DeleteEventRequest p_request);
	
	/**
	 * L�scht einen vorhandenen Kalendar und die dazugeh�rigen Termine.
	 * @param p_request
	 * @return
	 */
	DeleteCalendarResponse DeleteCalendar(DeleteCalendarRequest p_request);
	
	/**
	 * L�scht einen Benutzer und die dazugeh�rigen Kalender und Termine.
	 * @param p_request
	 * @return
	 */
	DeleteUserResponse DeleteUser(DeleteUserRequest p_request);
	
	/**
	 * Aktualisiert die Daten zu einem Termin.
	 * @param p_request
	 * @return
	 */
	UpdateEventResponse UpdateEvent (UpdateEventRequest p_request);
	
}
