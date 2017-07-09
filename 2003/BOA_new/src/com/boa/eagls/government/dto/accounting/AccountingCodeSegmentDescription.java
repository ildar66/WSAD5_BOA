package com.boa.eagls.government.dto.accounting;

import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p><small> DVI Company, 21.07.2003 18:08:11</small></p>
 * @author GlebL
 */
public class AccountingCodeSegmentDescription
        extends BusinessObject implements Serializable {

    /// attributes
    private AccountingCenter accountingCenter = null;
    private String dbRowID = STRING_DEFAULT;
    protected String name = STRING_DEFAULT;
    protected String description = STRING_DEFAULT;
    protected int startingPosition = INT_DEFAULT;
    protected int length = INT_DEFAULT;
    protected boolean required = BOOLEAN_DEFAULT;

    protected ArrayList accountingCodeSegmentList = new ArrayList();
    //

    // lazy load
    boolean validValuesLoaded = false;
    boolean individualFetchEver = false;

    /**
     * Constructor
     *
     * @param name  Name of the Segment.
     * @param description  Description of Segment.
     * @param length Length of the segment.
     * @param required  To state that weather this segment is mandatory in AccountingCode Segment.
     * @param startPos starting position of the segment in the AccountingCode.
     */
    protected AccountingCodeSegmentDescription(AccountingCenter accountingCenter,
                                               String aName,
                                               String aDescription,
                                               int aLength,
                                               boolean aRequired,
                                               int aStartingPosition) {
        this.accountingCenter = accountingCenter;
        this.name = aName;
        this.description = aDescription;
        this.length = aLength;
        this.required = aRequired;
        this.startingPosition = aStartingPosition;

    }

    /**
     * Constructor
     *
     * @param name  Name of the Segment.
     * @param description  Description of Segment.
     * @param length Length of the segment.
     * @param required  To state that weather this segment is mandatory in AccountingCode Segment.
     * @param startPos starting position of the segment in the AccountingCode.
     * @param dbRowID value used by Oracle for faster database access.
     */
    protected AccountingCodeSegmentDescription(AccountingCenter accountingCenter,
                                               String aName,
                                               String aDescription,
                                               int aLength,
                                               boolean aRequired,
                                               int aStartingPosition,
                                               String dbRowID) {

        this(accountingCenter, aName, aDescription, aLength,
                aRequired, aStartingPosition);
        this.dbRowID = dbRowID;
    }

    /**
     * Creating a new AccountingCodeSegment containing validvalue-description
     * and adding this object  in the list of AccountingCodeSegment
     *
     * @param  value  The AccountingCodeSegment value that is added to the segment possible valid value list.
     * @param  description The description of the value being set.
     * @param  active  The indicator to state the 'value' to be active or inactive.
     * @exception IllegalArgumentException runtime exception. If the value is already present.
     */
    protected void addSegmentValue(String value,
                                   String description,
                                   boolean active) {
        accountingCodeSegmentList.add(
                new AccountingCodeSegment(value, description, active));

    }

    /**
     * Add all the value in one go.
     * This method replaces any previous values added.
     */
    public void addAllSegmentValues(ArrayList vecValue) {
        vecValue.size();// call this function to throw the null pointer exception if any!!!.
        this.accountingCodeSegmentList = vecValue;
    }

    /**
     * get the RowID information used specific to OracleData Base for quick Updates.
     *
     * @return dbRowID value.
     */
    public String getDBRowID() {
        return dbRowID;
    }

    /**
     * To find out if the dbRowID information is valid to be used by OracleData Base for quick Updates.
     *
     * @return boolean true if dbRowID is valid else false .
     */
    public boolean isDBRowIDValid() {
        if (dbRowID.equals(STRING_DEFAULT) || dbRowID == null) return false;
        return true;
    }

    private void setValidValuesLoaded(boolean value) {
        validValuesLoaded = value;
    }

    public boolean isValidValuesLoaded() {
        return validValuesLoaded;
    }

    public boolean isIndividualFetchEver() {
        return individualFetchEver;
    }

    public boolean setIndividualFetchEver(boolean value) {
        return individualFetchEver = value;
    }


    /**
     * Inquire about Name.
     * @return String  Name of the AccountingCodeSegmentDescription.
     */
    public String getName() {

        return this.name;
    }

    /**
     * @param name  Name of the AccountingCodeSegmentDescription.
     */
    protected void setName(String name) {

        this.name = name;
    }

    /**
     *
     * @return description  Description of the AccountingCodeSegmentDescription.
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * @param description  Description of the AccountingCodeSegmentDescription.
     */
    protected void setDescription(String description) {
        this.description = description;

    }

    /**
     * @return length Length of the AccountingCodeSegmentDescription.
     */
    public int getLength() {

        return this.length;
    }

    /**
     * @param length  Length of the AccountingCodeSegmentDescription .
     */
    protected void setLength(int length) {
        this.length = length;
    }

    /**
     * @return isRequired this AccountingCodeSegmentDescription is mandatory or not.
     */
    public boolean isRequired() {
        return this.required;
    }

    /**
     * @param required Set the AccountingCodeSegmentDescription as mandatory or not.
     */
    protected void setRequired(boolean required) {
        this.required = required;
    }

    protected void setStartingPosition(int startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int getStartingPosition() {
        return this.startingPosition;
    }

    /**
     * Determine if value is null, all pounds (#).
     *
     * @param   value a segment value.
     * @return  true if string represents a null segment.
     */
    public boolean isSegmentValueNull(String value) {
        for (short i = 0; i < value.length(); i++) {
            if (value.charAt(i) != '#') {
                return false;
            }
        }
        return true;
    }

    public ArrayList getAccountingCodeSegmentList() {
        return accountingCodeSegmentList;
    }


}