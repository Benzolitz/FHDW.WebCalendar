package DomainModel.Base;

/**
 * Basis-Objekt f�r alle DomainModel-Objekte.
 * 
 * @author Eduard Kress, Lucas Engel, Frederik Heinrichs
 */
public class BaseObject
{
    private int id;

    public void SetId(int p_id)
    {
        id = p_id;
    }

    public int GetId()
    {
        return id;
    }
}
