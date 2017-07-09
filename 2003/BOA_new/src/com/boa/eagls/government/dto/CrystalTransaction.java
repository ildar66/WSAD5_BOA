package com.boa.eagls.government.dto;

import java.util.*;
import java.io.*;

import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.service.userprofile.HierarchyService;

/**
 * CrystalTransaction <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class CrystalTransaction extends DTOBase implements Serializable
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1;

    /**
     * transactionType
     */
    private String transactionType = STRING_DEFAULT;

    /**
     * User Id
     */
    private String userID = STRING_DEFAULT;

    /**
     * Password
     */
    private String password = STRING_DEFAULT;

    /**
     * First Name
     */
    private String firstName = STRING_DEFAULT;

    /**
     * Last Name
     */
    private String lastName = STRING_DEFAULT;

    /**
     * Hierarchy
     */
    private HierarchyDTO[] hierarchy = new HierarchyDTO[HIERARCHY_LIMIT];

    /**
     * User Role
     */
    private String userRole = STRING_DEFAULT;

    /**
     * New Password
     */
    private String newPassword = STRING_DEFAULT;

    /**
     * Reports Id
     */
    private String reportID = STRING_DEFAULT;

    /**
     * Default Constructor
     */
    public CrystalTransaction()
    {
    }

    /**
     * convenince constructor for user profile setup/maintenance
     */
    public CrystalTransaction(String tUserID, String tUserRole,
                              String tFirstName, String tLastName)
    {
        this.setUserID(tUserID);
        this.setUserRole(tUserRole);
        this.setFirstName(tFirstName);
        this.setLastName(tLastName);
    }

    // ----------------------------------------get methods-------------------------------------

    /**
     * getTransactionType
     * @return String
     */
    public String getTransactionType()
    {
        return transactionType;
    }

    /**
     * getUserID
     * @return String
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * getPassword
     * @return String
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * getFirstName
     * @return String
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * getLastName
     * @return String
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * getHierarchy
     * @return HierarchyDTO[]
     */
    public HierarchyDTO[] getHierarchy()
    {
        return copyHierarchy(hierarchy);
    }

    /**
     * getUserRole
     * @return String
     */
    public String getUserRole()
    {
        return userRole;
    }

    /**
     * getNewPassword
     * @return String
     */
    public String getNewPassword()
    {
        return newPassword;
    }

    /**
     * getReportID
     * @return String
     */
    public String getReportID()
    {
        return reportID;
    }

    // ----------------------------------------set methods-------------------------------------

    /**
     * setTransactionType
     * @param tTransactionType
     */
    public void setTransactionType(String tTransactionType)
    {
        transactionType = validateString(tTransactionType);
    }

    /**
     * setUserID
     * @param tUserID
     */
    public void setUserID(String tUserID)
    {
        userID = validateString(tUserID);
    }

    /**
     * setPassword
     * @param tPassword
     */
    public void setPassword(String tPassword)
    {
        password = validateString(tPassword);
    }

    /**
     * setFirstName
     * @param tFirstName
     */
    public void setFirstName(String tFirstName)
    {
        firstName = validateString(tFirstName);
    }

    /**
     * setLastName
     * @param tLastName
     */
    public void setLastName(String tLastName)
    {
        lastName = validateString(tLastName);
    }

    /**
     * setHierarchy
     * @param tHierarchy
     */
    public void setHierarchy(HierarchyDTO[] tHierarchy)
    {
        hierarchy = validateHierarchy(tHierarchy);
    }

    /**
     * setUserRole
     * @param tUserRole
     */
    public void setUserRole(String tUserRole)
    {
        userRole = validateString(tUserRole);
    }

    /**
     * setNewPassword
     * @param tNewPassword
     */
    public void setNewPassword(String tNewPassword)
    {
        newPassword = validateString(tNewPassword);
    }

    /**
     * setReportID
     * @param tReportID
     */
    public void setReportID(String tReportID)
    {
        reportID = validateString(tReportID);
    }

    /**
     * validateHierarchy
     * @param tHierarchy
     * @return HierarchyDTO[]
     */
    protected HierarchyDTO[] validateHierarchy(HierarchyDTO[] tHierarchy)
    {
        if (tHierarchy != null)
        {
            return tHierarchy;
        }
        else
        {
            HierarchyDTO[] newHierarchy = new HierarchyDTO[HIERARCHY_LIMIT];

            for (int i = 0; i < HIERARCHY_LIMIT; i++)
            {
                newHierarchy[i] = new HierarchyDTO((short) 0, 0, "");
            }
            return newHierarchy;
        }
    }

    /**
     * copyHierarchy
     * @param tHierarchy
     * @return HierarchyDTO[]
     */
    public HierarchyDTO[] copyHierarchy(HierarchyDTO[] tHierarchy)
    {
        HierarchyDTO[] copyHierarchy = new HierarchyDTO[tHierarchy.length];

        /*
         * for (int i=0; i < tHierarchy.length; i++)
         * {
         * copyHierarchy[i] = tHierarchy[i];
         * }
         */
        System.arraycopy(tHierarchy, 0, copyHierarchy, 0, tHierarchy.length);
        return copyHierarchy;
    }

}
