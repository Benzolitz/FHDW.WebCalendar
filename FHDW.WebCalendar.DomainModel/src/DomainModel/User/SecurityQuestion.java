package DomainModel.User;

import DomainModel.Base.BaseObject;

/**
 * @author Eduard Kress, Lucas Engel, Frederik Heinrichs
 */
public class SecurityQuestion extends BaseObject
{
    private String name;

    public void SetName(String p_name)
    {
        name = p_name;
    }

    public String GetName()
    {
        return name;
    }
}
