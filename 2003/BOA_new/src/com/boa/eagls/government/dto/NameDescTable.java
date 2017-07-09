package com.boa.eagls.government.dto;

import com.boa.eagls.government.business.BusinessObject;

import java.io.*;

public class NameDescTable extends BusinessObject implements Serializable
{
    //The attributes otherwise noted map to screen elements described in SDRs.
    private String name = STRING_DEFAULT;
    private String description = STRING_DEFAULT;

//----------------------------------------Constructors-------------------------------------------

    public NameDescTable()
    {}

    public NameDescTable(NameDescTable tNameDescTable)
    {
        setName(tNameDescTable.getName());
        setDescription(tNameDescTable.getDescription());
    }

    public NameDescTable(String tName, String tDescription)
    {
        setName(tName);
        setDescription(tDescription);
    }



//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from NameDescTable.

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in NameDescTable.

    public void setName(String tName)
    {
        name = validateString(tName);
    }

    public void setDescription(String tDescription)
    {
        description = validateString(tDescription);
    }

//-------------------------------------other useful methods----------------------------
    // These methods helps in doing operations on the Emall.

    public boolean equals(NameDescTable aNameDescTable)
    {
        if ( (aNameDescTable != null) && (aNameDescTable instanceof NameDescTable))
        {
            if ((aNameDescTable.name.equals(name)) && (aNameDescTable.description.equals(description)))
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        return "Name = " + name + ", " + "Description = " + description;
    }

    public String getSelectString() {
        if ("".equals(name) && "".equals(description)) {
            return  "";
        }
        else {
            return  name + "+" + description;
        }
    }

}
