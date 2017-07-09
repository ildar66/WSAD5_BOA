package com.boa.eagls.government.dto.accounting;

import org.apache.log4j.Logger;
import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * <p><small> DVI Company, 21.07.2003 21:07:34</small></p>
 * @author GlebL
 */
public class CommentSegmentDescription
        extends BusinessObject implements Serializable{
    static final Logger logger =
            Logger.getLogger(CommentSegmentDescription.class);
    private String dbRowID = STRING_DEFAULT;
    private String name = STRING_DEFAULT;
    private int length = INT_DEFAULT;

    /**
     * The constructor which allows the complete object to be created.
     *
     * @param name The name of the comment segment
     * @param length lenght of that comment segment.
     */

    public CommentSegmentDescription(String name, int length) {
        this.name = name;
        this.length = length;
    }//end Cons

    /**
     * The constructor which allows the complete object to be created with a dbRowID.
     *
     * @param name The name of the comment segment.length
     * @param length lenght of that comment segment.
     * @param dbRowID for faster Oracle database access.
     */

    public CommentSegmentDescription(String name, int length, String dbRowID) {
        this.name = name;
        this.length = length;
        this.dbRowID = dbRowID;
    }//end Cons


    /**
     * get the RowID information used specific to OracleData Base for quick Updates.
     *
     * @param dbRowID  string representation of Oracle Data base rowID value for the accountingCenter general information.
     * @return dbRowID value.
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
     * To get the value of this CommentSegmentDescription
     *
     * @return The value of this CommentSegmentDescription.
     */


    public String getName() {
        return name;
    }

    /**
     * Set the value for the CommentSegmentDescription
     *
     * @param value The value of this AccountingCodesegment
     */

    public void setName(String value) {
        this.name = value;
    }

    /**
     * The length of the CommentSegmentDescription value is returned
     *
     * @return The
     */

    public int getLength() {
        return length;
    }

    /**
     * Set the length of the Comment Segment.
     *
     * @param length The length of the Comment Segment.
     */

    public void setLength(int length) {
        this.length = length;
    }


}