package com.boa.eagls.government.dto.accounting;

import org.apache.log4j.Logger;
import com.boa.eagls.government.business.BusinessObject;
import com.boa.eagls.government.dto.HierarchyDTO;

/**
 * This class keep the summary information of a AccountingCenter (Value Object design pattern).
 * <p><small> VDI Company, 18.07.2003 17:08:09</small></p>
 * @author Oleg Klimenko
 */
public class AccountingCenterSummary extends BusinessObject implements java.io.Serializable {
    static final Logger logger = Logger.getLogger(AccountingCenterSummary.class);


    private String accountingCenterID = STRING_DEFAULT;
    private String accountingCenterName = STRING_DEFAULT;
    private String agencyName = STRING_DEFAULT;
    private HierarchyDTO[] hierarchy = new HierarchyDTO[HIERARCHY_LIMIT];
    private String rowID = STRING_DEFAULT;

    /**
     * Default constructor
     */

    public AccountingCenterSummary() {
    }

    /**
     * This constructor is used to completely fill the summary object.
     *
     * @param agencyName The name of the agency of the corrosponding AccountingCenter object.
     * @param acctCenterID The unique identification value of the Accountingcenter obeject.
     * @param acctName The Name of the AccountingCenter Object.
     * @param hierarchy The Hierarchy information of the AccountingCenter object.
     */


    public AccountingCenterSummary(String agencyName, String acctCenterID, String acctName, HierarchyDTO[] hierarchy) {

        this.agencyName = agencyName;
        this.accountingCenterID = acctCenterID;
        this.accountingCenterName = acctName;
        this.hierarchy = hierarchy;

    }

    /**
     * @return The Agency name String
     */


    public String getAgencyName() {

        return agencyName;
    }

    /**
     * @param value The Agency name to be set
     */

    public void setAgencyName(String value) {

        this.agencyName = value;

    }

    /**
     * @return String representing accountingCenterID
     */

    public String getAccountingCenterID() {

        return accountingCenterID;
    }

    /**
     * @param value The value contains the accountingCenterID for the Accounting object
     */

    public void setAccountingCenterID(String value) {

        this.accountingCenterID = value;

    }

    /**
     * @return The Name of the AccountingCenter object
     */

    public String getAccountingCenterName() {

        return accountingCenterName;
    }

    /**
     * @param value The value of the Name of the AccountingCenter object.
     */

    public void setAccountingCenterName(String value) {

        accountingCenterName = value;

    }

    /**
     * @return The Hierarchy object which contains the information of AcoountingCenter hierarchy.
     */

    public HierarchyDTO[] getHierarchy() {

        return hierarchy;
    }

    /**
     * @param value Set the hierarchy information.
     */

    public void setHierarchy(HierarchyDTO[] value) {

        hierarchy = value;

    }

    public String getRowID() {

        return rowID;
    }


}//end of class

