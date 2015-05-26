package IRepository.Response;
import java.util.Collection;


public class GetAllSecurityQuestionsResponse extends IResponse
{
	private Collection<String> categories;

	public Collection<String> GetCategories()
	{
		return categories;
	}

	public void SetCategories(Collection<String> p_categories)
	{
		categories = p_categories;
	}
	
}
