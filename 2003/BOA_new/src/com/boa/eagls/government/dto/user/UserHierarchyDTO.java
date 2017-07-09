package com.boa.eagls.government.dto.user;

import com.boa.eagls.government.dto.*;

import java.util.*;

/**
 * UserHierarchyDTO
 *
 *
 * @author
 * @version %I%, %G%
 */
public class UserHierarchyDTO extends DTOBase
{
    private String userID = STRING_DEFAULT;
    private String roleName = STRING_DEFAULT;
    private HierarchyDTO[] hierarchy = new HierarchyDTO[HIERARCHY_LIMIT];
    private boolean Default = BOOLEAN_DEFAULT;
    private String programTypes = STRING_DEFAULT;
    private String agencyName = STRING_DEFAULT;
    private short position =
            SHORT_DEFAULT;     // The hierarchy position ex: hl1 = 1
    private int value =
            INT_DEFAULT;       // The hierarchy position's value
    private String description =
            STRING_DEFAULT;    // The hierarchy value's description
    private String[] parentAgencyNames = new String[9];
    private HierarchyDTO[] defaultHierarchy;    // the default hierarchy for this role
    private Vector hierarchyCache;    // the list of hierarchies under this role
    private HierarchyDTO[] userHierarchies;

    /**
     * Constructor declaration
     *
     */
    public UserHierarchyDTO()
    {
        hierarchyCache = new Vector();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAgencyName()
    {
        return agencyName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean isDefault()
    {
        return Default;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getHierarchy()
    {
        return hierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getProgramTypes()
    {
        return programTypes;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRoleName()
    {
        return roleName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     */
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    /**
     * Method declaration
     *
     *
     * @param roleName
     */
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    /**
     * Method declaration
     *
     *
     * @param programTypes
     */
    public void setProgramTypes(String programTypes)
    {
        this.programTypes = programTypes;
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchy
     */
    public void setHierarchy(HierarchyDTO[] hierarchy)
    {
        this.hierarchy = hierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @param Default
     */
    public void setDefault(boolean Default)
    {
        this.Default = Default;
    }

    /**
     * Method declaration
     *
     *
     * @param agencyName
     */
    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
    }

    /**
     * Method declaration
     *
     *
     * @param hTest
     *
     * @return
     */
    public boolean equals(HierarchyDTO[] hTest)
    {
        if (hTest != null && hTest.length == 9)
        {
            for (short i = 0; i < 9; i++)
            {
                if (hTest[i].getNumber() != hierarchy[i].getNumber())
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Method declaration
     *
     *
     * @param value
     */
    public void setValue(int value)
    {
        this.value = value;
    }

    /**
     * Method declaration
     *
     *
     * @param userHierarchies
     */
    public void setUserHierarchies(HierarchyDTO[] userHierarchies)
    {
        this.userHierarchies = userHierarchies;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getUserHierarchies()
    {
        return userHierarchies;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public short getPosition()
    {
        return position;
    }

    /**
     * Method declaration
     *
     *
     * @param position
     */
    public void setPosition(short position)
    {
        this.position = position;
    }

    /**
     * Method declaration
     *
     *
     * @param parentAgencyNames
     */
    public void setParentAgencyNames(String[] parentAgencyNames)
    {
        this.parentAgencyNames = parentAgencyNames;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getParentAgencyNames()
    {
        return parentAgencyNames;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Vector getHierarchyCache()
    {
        return hierarchyCache;
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchyCache
     */
    public void setHierarchyCache(Vector hierarchyCache)
    {
        this.hierarchyCache = hierarchyCache;
    }

    /**
     * Method declaration
     *
     *
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getDefaultHierarchy()
    {
        return defaultHierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @param defaultHierarchy
     */
    public void setDefaultHierarchy(HierarchyDTO[] defaultHierarchy)
    {
        this.defaultHierarchy = defaultHierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @param pos
     * @param parentAgencyName
     */
    public void setParentAgencyName(int pos, String parentAgencyName)
    {
        parentAgencyNames[pos] = parentAgencyName;
    }

    /**
     * Method declaration
     *
     *
     * @param pos
     *
     * @return
     */
    public String getParentAgencyName(int pos)
    {
        return parentAgencyNames[pos];
    }

    /**
     * Method declaration
     *
     *
     * @param aPType
     */
    public void addProgramType(String aPType)
    {
        char chPType;

        if (aPType.equalsIgnoreCase("F") || aPType.equalsIgnoreCase("FLEET"))
        {
            chPType = 'F';
        }
        else if (aPType.equalsIgnoreCase("P")
                || aPType.equalsIgnoreCase("PURCHASE"))
        {
            chPType = 'P';
        }
        else if (aPType.equalsIgnoreCase("I")
                || aPType.equalsIgnoreCase("INTEGRATED"))
        {
            chPType = 'I';
        }
        else if (aPType.equalsIgnoreCase("A")
                || aPType.equalsIgnoreCase("INTERAGENCY"))
        {
            chPType = 'A';
        }
        else if (aPType.equalsIgnoreCase("T")
                || aPType.equalsIgnoreCase("TRAVEL"))
        {
            chPType = 'T';
        }
        else
        {
            throw new IllegalArgumentException("Unrecognized program type");
        }
        if (programTypes == null)
        {
            programTypes = new String("" + chPType);
        }
        else
        {
            programTypes += chPType;
        }
    }

}
