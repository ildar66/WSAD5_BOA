package com.boa.eagls.government.dto.accounting;

import org.apache.log4j.Logger;
import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * <p><small> DVI Company, 21.07.2003 18:12:17</small></p>
 * @author GlebL
 */
public class AccountingCodeSegment
    extends BusinessObject implements Serializable {
    static final Logger logger = Logger.getLogger(AccountingCodeSegment.class);

    private String dbRowID = null;//STRING_DEFAULT;
    private String value = null;//STRING_DEFAULT;
    private String description = null;//STRING_DEFAULT;
    private boolean active = BOOLEAN_DEFAULT;

    private String combinedValue = null;///combined representation of all values. Performance reasons.

    private boolean separated = false;///keeps track if the values have been


    /**
     * The constructor which allows the complete object to be created with a single value. This is aadded for performance reasons.
     * @param value The valid value. It represent one of the possible value that can be used in that segment
     * of AccountingCode.
     * @param description Description of that value.
     * @param active The active/inactive indicator value.
     */

    public AccountingCodeSegment(String combinedValue) {

        this.combinedValue = combinedValue;
        /*
        if(!separated){
        int i1 = combinedValue.indexOf("+");
        if(i1==-1) throw new IllegalArgumentException("Wrong Value of AccountingCodeSegment combined string ="+combinedValue);
        int i2 = combinedValue.indexOf("+",(i1+1));
        if(i2==-1) throw new IllegalArgumentException("Wrong Value of AccountingCodeSegment combined string ="+combinedValue);
        this.value = combinedValue.substring(0,i1);
        this.description = combinedValue.substring(i1+1,i2);
        this.active = combinedValue.substring(i2+1,i2+2).equals("Y")?true:false;
        }
        */
    }


    /**
     * The constructor which allows the complete object to be created.
     *
     * @param value The valid value. It represent one of the possible value that can be used in that segment
     * of AccountingCode.
     * @param description Description of that value.
     * @param active The active/inactive indicator value.
     */

    public AccountingCodeSegment(String value, String description, boolean active) {
        this.value = value;
        this.description = description;
        this.active = active;

        separated = true;/// values came as seperated.
    }//end Cons

    /**
     * The constructor which allows the complete object to be created.
     *
     * @param value The valid value. It represent one of the possible value that can be used in that segment
     * of AccountingCode.
     * @param description Description of that value.
     * @param active The active/inactive indicator value.
     * @param dbRowID for faster Oracle database access.
     * @deprecated dbRowID not to be used.
     */

    public AccountingCodeSegment(String value, String description, boolean active, String dbRowID) {
        this.value = value;
        this.description = description;
        this.active = active;
        this.dbRowID = dbRowID;
        separated = true;
    }//end Cons


    /**
     * get the RowID information used specific to OracleData Base for quick Updates.
     *
     * @param dbRowID  string representation of Oracle Data base rowID value for the accountingCenter general information.
     * @return dbRowID value.
     * @deprecated  due to performance reasons
     */
    public String getDBRowID() {
        return dbRowID;
    }

    /**
     * To find out if the dbRowID information is valid to be used by OracleData Base for quick Updates.
     *
     * @param dbRowID  string representation of Oracle Data base rowID value for the accountingCenter general information.
     * @return boolean true if dbRowID is valid else false .
     */
    public boolean isDBRowIDValid() {
        if (dbRowID.equals(STRING_DEFAULT) || dbRowID == null) return false;
        return true;
    }


    /**
     * To get the value of this AccountingCodeSegment
     *
     * @return The value of this AccountingCodeSegment.
     */


    public String getValue() {
        separate();
        return value;
    }

    /**
     * Set the value for the AccountingCodeSegment
     *
     * @param value The value of this AccountingCodesegment
     */

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * The description of the AccountingCodeSegment value is returned
     *
     * @return The decsription String
     */

    public String getDescription() {
        separate();
        return description;
    }

    /**
     * Set the dscription of the value of AccountingCodeSegment
     *
     * @param description The description of the Value of this AccountingCodeSegment
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Inquired the Active/InActive status of the AccountingcodeSegment Value.
     *
     * @return the active/inActive status of this AccountingCodeSegment value.
     */

    public boolean isActive() {
        separate();
        return active;
    }

    /**
     * Sets the AccountingCodeSegment value to be Active/InActive.
     *
     * @param active The active/inactive indicator value to be set.
     */

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     *Checks if the input object contains all the same values except for rowId.
     *@param obj must be a AccountingCodeSegment only.
     *@return true or false depending if object were equals or not.
     */

    public boolean equals(Object obj) {
        AccountingCodeSegment segment = (AccountingCodeSegment) obj;

        if (!(segment.getValue().equals(this.getValue()))) return false;
        if (!(segment.getDescription().equals(this.getDescription()))) return false;
        if (!(segment.isActive() == this.isActive())) return false;

        return true;
    }

    /*
    *Separate the combine values is not already separated.
    */

    private void separate() {
/*
        int i1 = combinedValue.indexOf("^");
        if (i1 == -1) throw new IllegalArgumentException("Wrong Value of AccountingCodeSegment combined string =" + combinedValue);
        int i2 = combinedValue.indexOf("^", (i1 + 1));
        if (i2 == -1) throw new IllegalArgumentException("Wrong Value of AccountingCodeSegment combined string =" + combinedValue);
        this.value = combinedValue.substring(0, i1);
        this.description = combinedValue.substring(i1 + 1, i2);
        this.active = combinedValue.substring(i2 + 1, i2 + 2).equals("Y") ? true : false;
        separated = true;
*/


        if (!separated) {
            int i1 = combinedValue.indexOf("^");
            if (i1 == -1) throw new IllegalArgumentException("Wrong Value of AccountingCodeSegment combined string =" + combinedValue);
            int i2 = combinedValue.indexOf("^", (i1 + 1));
            if (i2 == -1) throw new IllegalArgumentException("Wrong Value of AccountingCodeSegment combined string =" + combinedValue);
            this.value = combinedValue.substring(0, i1);
            this.description = combinedValue.substring(i1 + 1, i2);
            try {
                this.active = combinedValue.substring(i2 + 1, i2 + 2).equals("Y") ? true : false;
                separated = true;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new IllegalArgumentException("Wrong Value of AccountingCodeSegment combined string =" + combinedValue);
            }
        }
    }
}