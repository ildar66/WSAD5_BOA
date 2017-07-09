package com.boa.eagls.government.dto.agency;

import com.boa.eagls.government.business.BusinessObject;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.NameDescTable;

import java.io.Serializable;

/**
 * <code>This is the container object for an Agency Core
 * The Agency Core contains essential information about an agency and can be return
 * for some searches.  Agency extends for Agency Core
 * AgencyCore is the core information for an agency, which is used for
 * Mastercard and Individual Account Setup</code>
 *
/* 	@version 1.0 $Modtime:   21 Sep 1998 21:06:14  $$Revision: 1.5 $
/* 	@author  Ed Zhang
**/

import java.util.*;
import java.io.*;

/**
 * Agency details Value Object design pattern
 * @author Oleg Klimenko
 */
public class AgencyCore extends BusinessObject implements Serializable
{
    //static finals for fleetType
    public static final short MASTERCARD = 0;
    public static final short VOYAGER = 1;
    public static final short NONE = 2;

    // General static final values
    public static final int HIERARCHY_LIMIT = 9;

    //The attributes otherwise noted map to screen elements described in SDRs.
    private short fleetType = SHORT_DEFAULT;
    private boolean performCreditChecks = BOOLEAN_DEFAULT;
    private int agencyID = INT_DEFAULT;
    private HierarchyDTO[] hierarchy;
    private String agencyName = STRING_DEFAULT;
    private String[] accountingCenterIDs = STRING_ARRAY_DEFAULT;
    private String accountingCenterID = STRING_DEFAULT;
    private NameDescTable[] cardTypes;          // needs to be a drop down list of choices during setup
    private String[] gradeAndStatuses  = STRING_ARRAY_DEFAULT;         // needs to be a drop down list of choices during setup
    private String[] employmentStatuses = STRING_ARRAY_DEFAULT;        // needs to be a drop down list of choices during setup

//--------------------------------------------------constructor------------------------------------
    public AgencyCore()
    {
        hierarchy = new HierarchyDTO[9];
        cardTypes = new NameDescTable[0];
    }

    public AgencyCore(AgencyCore tAgencyCore)
    {
        setFleetType(tAgencyCore.getFleetType());
        setPerformCreditChecks(tAgencyCore.getPerformCreditChecks());
        setAgencyID(tAgencyCore.getAgencyID());
        setHierarchy(tAgencyCore.getAHierarchy());
        setAgencyName(tAgencyCore.getAgencyName());
        setAccountingCenterIDs(tAgencyCore.getAccountingCenterIDs());
        setAccountingCenterID(tAgencyCore.getAccountingCenterID());
        setCardTypes(tAgencyCore.getCardTypes());
        setGradeAndStatuses(tAgencyCore.getGradeAndStatuses());
        setEmploymentStatuses(tAgencyCore.getEmploymentStatuses());
    }

//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from AgencyCore.

    public int getAgencyID ()
    {
        return agencyID;
    }

    public short getFleetType()
    {
        return fleetType;
    }

    public boolean getPerformCreditChecks()
    {
        return performCreditChecks;
    }

    public String getAgencyName()
    {
        return new String(agencyName);
    }

    public String[] getAccountingCenterIDs()
    {
        String[] copyAccountingCenterIDs = new String[accountingCenterIDs.length];
        for (int i=0; i < accountingCenterIDs.length; i++)
        {
            copyAccountingCenterIDs[i] = accountingCenterIDs[i];
        }
        return copyAccountingCenterIDs;
    }

    public String getAccountingCenterID()
    {
        return new String(accountingCenterID);
    }

    public NameDescTable[] getCardTypes()
    {
        NameDescTable[] copyCardTypes = new NameDescTable[cardTypes.length];
        for (int i = 0; i < cardTypes.length; i ++)
        {
            copyCardTypes[i] = cardTypes[i];
        }
        return copyCardTypes;
    }

    public String[] getGradeAndStatuses()
    {
        String[] copyGradeAndStatuses = new String[gradeAndStatuses.length];
        for (int i=0; i < gradeAndStatuses.length; i++)
        {
            copyGradeAndStatuses[i] = gradeAndStatuses[i];
        }
        return copyGradeAndStatuses;
    }

    public String[] getEmploymentStatuses()
    {
        String[] copyEmploymentStatuses = new String[employmentStatuses.length];
        for (int i=0; i < employmentStatuses.length; i++)
        {
            copyEmploymentStatuses[i] = employmentStatuses[i];
        }
        return copyEmploymentStatuses;
    }

    public HierarchyDTO[] getAHierarchy()
    {
        return getHierarchy();
    }

    public HierarchyDTO[] getHierarchy()
    {
        HierarchyDTO[] copyHierarchy = new HierarchyDTO[hierarchy.length];
        for (int i=0; i < hierarchy.length; i++)
        {
            copyHierarchy[i] = hierarchy[i];
        }
        return copyHierarchy;
    }


//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements of AgencyCore.

    public void setFleetType(short tFleetType)
    {
        fleetType = tFleetType;
    }

    public void setAgencyID (int tAgencyID)
    {
        agencyID = tAgencyID;
    }

    public void setAgencyID (Integer tAgencyID)
    {
        agencyID = validateInteger(tAgencyID);
    }

    public void setPerformCreditChecks(boolean tPerformCreditChecks)
    {
        performCreditChecks = tPerformCreditChecks;
    }

    public void setPerformCreditChecks(Boolean tPerformCreditChecks)
    {
        performCreditChecks = validateBoolean(tPerformCreditChecks);
    }

    public void setAgencyName(String tAgencyName)
    {
        agencyName = validateString(tAgencyName);
    }

    public void setAccountingCenterID(String tAccountingCenterID)
    {
        accountingCenterID = validateString(tAccountingCenterID);
    }

    public void setAccountingCenterIDs(String[] tAccountingCenterIDs)
    {
        if (tAccountingCenterIDs!= null) {
            accountingCenterIDs = tAccountingCenterIDs;
        } else {
            accountingCenterIDs[0] = "";
        }
    }

    public void setCardTypes(NameDescTable[] tCardTypes)
    {
        if (tCardTypes!= null) {
            cardTypes = tCardTypes;
        } else {
            cardTypes[0] = new NameDescTable("","");
        }
    }

    public void setGradeAndStatuses(String[] tGradeAndStatuses)
    {
        if (tGradeAndStatuses!= null) {
            gradeAndStatuses = tGradeAndStatuses;
        } else {
            gradeAndStatuses[0] = "";
        }
    }

    public void setEmploymentStatuses(String[] tEmploymentStatuses)
    {
        if (tEmploymentStatuses!= null) {
            employmentStatuses = tEmploymentStatuses;
        } else {
            employmentStatuses[0] = "";
        }
    }

    public void setHierarchy(HierarchyDTO[] tHierarchy)
    {
        hierarchy = validateHierarchy(tHierarchy);
    }
}