public class WebCalendarRepo implements IWebCalendarRepo
{

	@Override
	public SaveEventResponse SaveEvent(Event event)
	{
		SaveEventResponse Response = new SaveEventResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}

	@Override
	public DeleteEventResponse DeleteEvent(Event event)
	{
		DeleteEventResponse Response = new DeleteEventResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}

	@Override
	public RegistrateUserResponse RegistrateUser(User user)
	{
		RegistrateUserResponse Response = new RegistrateUserResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}

	@Override
	public CheckUserResponse CheckUser(User user)
	{
		CheckUserResponse Response = new CheckUserResponse();
		
		Response.MessageFailure("Not implemented.");
		
		return Response;
	}


}
