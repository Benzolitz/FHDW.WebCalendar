package DomainModel.Calendar;

import DomainModel.Base.BaseObject;

/**
 * Kalendar-Objekt um alle Daten von Kalendern speichern zu können.
 * 
 * @author Eduard Kress, Lucas Engel, Frederik Heinrichs
 */
public class Calendar extends BaseObject
{
    private int ownerId;
    private String name;

    public void SetOwnerId(int p_ownerId)
    {
        ownerId = p_ownerId;
    }

    public int GetOwnerId()
    {
        return ownerId;
    }

    public void SetName(String p_name)
    {
        name = p_name;
    }

    public String GetName()
    {
        return name;
    }
}
