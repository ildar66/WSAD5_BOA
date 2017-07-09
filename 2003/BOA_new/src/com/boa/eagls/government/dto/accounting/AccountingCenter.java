package com.boa.eagls.government.dto.accounting;

import com.boa.eagls.government.business.BusinessObject;
import com.boa.eagls.government.dao.AccountingCenterDAO;
import com.boa.eagls.government.dto.HierarchyDTO;
import org.apache.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p><small> DVI Company, 21.07.2003 17:46:50</small></p>
 * @author GlebL
 */
public class AccountingCenter extends BusinessObject
        implements PropertyChangeListener, Serializable {
    static final Logger logger = Logger.getLogger(AccountingCenter.class);

    public static final short FREEFORM_COMMENTS = 0;
    public static final short SEGMENTED_COMMENTS = 1;

    public static final short MAX_SEGMENT_LENGTH = 64;
    public static final short MAX_COMMENT_LENGTH = 100;

    public static final short ACCOUNTINGCODE_VALIDATE = 4;
    public static final short ACCOUNTINGCODE_NO_VALIDATE = 5;
    public static final short PARSE_ONLY = 6;

    private String dbRowID = STRING_DEFAULT;
    private String accountingCenterID = STRING_DEFAULT;
    private String accountingCenterName = STRING_DEFAULT;

    private boolean creditDebitIndicatorAllowed = BOOLEAN_DEFAULT;
    //private boolean creditAllocsAllowed = BOOLEAN_DEFAULT;//depricated
    private boolean reallocsAfterAASAllowed = BOOLEAN_DEFAULT;

    private short commentType = FREEFORM_COMMENTS;
    private int centralAccountPriority = 1;
    private int accountHolderPriority = 2;
    private int mCCPriority = 3;

    private HierarchyDTO[] hierarchy = new HierarchyDTO[HIERARCHY_LIMIT];

    private ArrayList codeSegmentDescription = new ArrayList();
    private ArrayList commentSegmentDescription = new ArrayList();

    private int lastStartingPosition = 1;

    //PropertyChange Support
    private PropertyChangeSupport propertyChangeHelper;

    /// Intergrity support
    private int currentCommentTotalLength = 0;
    private int currentsegmentTotalLength = 0;

    //// save supports
    private boolean readOnly = true;
    AccountingCenterDAO dAA = null;

    boolean saveAccountingCenterInformation = false;
    boolean saveAddCommentSegment = false;
    boolean saveDeleteCommentSegments = false;

    boolean saveAddSegments = false;
    boolean saveSetSegmentRequired = false;

    boolean saveAddSegmentValue = false;
    boolean saveUpdateSegmentValue = false;

    ArrayList saveSegmentRequiredNumbers = new ArrayList();

    ArrayList saveSegmentValueNumbers = new ArrayList();
    ArrayList saveSegmentValueValues = new ArrayList();
    ArrayList saveUpdateSegmentValueNumbers = new ArrayList();
    ArrayList saveUpdateSegmentValueValues = new ArrayList();

    //// lazy load support

    boolean validValuesLoaded = false;

    // constructors

    /**
     * Accounting Center will be constructed by
     * a factory method. It  needs a valid accountingCenterID  for Construction.
     *
     * @param accountingCenterID  The unique identifier of AccountingCenter object.
     */

    public AccountingCenter(String accountingCenterID) {
        this.accountingCenterID = accountingCenterID;
        this.propertyChangeHelper = new PropertyChangeSupport(this);
        for (int i = 0; i < HIERARCHY_LIMIT; i++)
            hierarchy[i] = new HierarchyDTO();
    }


    /**
     * Accounting Center will be constructed by
     * a factory method. It  needs a valid accountingCenterID  for Construction.
     *
     0* @param accountingCenterID  The unique identifier of AccountingCenter object.
     * @param dbRowID  The rowID value used by Oracle DataBase for quick data access.
     */

    public AccountingCenter(String accountingCenterID, String dbRowID) {
        this(accountingCenterID);
        this.dbRowID = dbRowID;
    }

    /**
     * The AccountingCenter hierarchy is set.
     *
     * @param newValue hierarchy object.
     */
    public void setHierarchy(HierarchyDTO newValue[]) {
        HierarchyDTO[] oldValue = this.hierarchy;
        boolean equals = true;
        for (int i = 0; i < newValue.length; i++) { //// make it a utility function in Hierarchy class.Ashish 05/04/1999
            if (!newValue[i].equals(hierarchy[i])) equals = false;
        }
        if (!equals) {
            this.hierarchy = newValue;
            firePropertyChange("hierarchy", oldValue, newValue);
        }
    }

    /**
     *
     * @param newValue centralAccountPriority value.
     */
    public void setCentralAccountPriority(int newValue) {
        int oldValue = this.centralAccountPriority;
        if (oldValue != newValue) {
            this.centralAccountPriority = newValue;
            //centralAccountPriority = centralAccountPriority==newValue?oldValue:centralAccountPriority;
            accountHolderPriority = accountHolderPriority ==
                    newValue ? oldValue : accountHolderPriority;
            mCCPriority = mCCPriority == newValue ? oldValue : mCCPriority;
            firePropertyChange("priority", new Integer(oldValue),
                    new Integer(newValue));
        }
    }

    /**
     *
     * @param newValue accountHolderPriority value.
     */
    public void setAccountHolderPriority(int newValue) {
        int oldValue = this.accountHolderPriority;
        if (oldValue != newValue) {
            this.accountHolderPriority = newValue;
            centralAccountPriority = centralAccountPriority == newValue ?
                    oldValue : centralAccountPriority;
            //accountHolderPriority = accountHolderPriority ==newValue?oldValue:accountHolderPriority;
            mCCPriority = mCCPriority == newValue ? oldValue : mCCPriority;

            firePropertyChange("priority", new Integer(oldValue),
                    new Integer(newValue));
        }
    }

    /**
     *
     * @param newValue value.
     */
    public void setMCCPriority(int newValue) {
        int oldValue = this.mCCPriority;
        if (oldValue != newValue) {
            this.mCCPriority = newValue;
            centralAccountPriority = centralAccountPriority == newValue ?
                    oldValue : centralAccountPriority;
            accountHolderPriority = accountHolderPriority == newValue ?
                    oldValue : accountHolderPriority;
            //mCCPriority = mCCPriority==newValue?oldValue:mCCPriority;
            firePropertyChange("priority", new Integer(oldValue),
                    new Integer(newValue));
        }

    }

    /**
     * Adds a new Segment Description to the AccountingCenter.
     *
     * @param name  Name of the segment in AccountingCode.
     * @param description Deascription of the segment in AccountingCode.
     * @param length the no of characters that AccountingCode Segment can have.
     * @param required whether this Accounting Code Segmemt  is mandatory or not?.
     * @param startPos starting position of the segment in the AccountingCode.
     * @param dbRowID used by Oracle for faster database access.
     */
    public void addSegment(String name, String description,
                           int length, boolean required,
                           int startPos, String dbRowID)
            throws IllegalArgumentException {
        Iterator it = codeSegmentDescription.iterator();
        while (it.hasNext()) {
            AccountingCodeSegmentDescription segmentDesc =
                    (AccountingCodeSegmentDescription) it.next();
            if (segmentDesc.getName().equals(name)) {
                throw new IllegalArgumentException(
                        "Segment name " + name +
                        " already  exist with the AccountingCenter " +
                        getAccountingCenterID());
            }
        }
        if (lastStartingPosition != startPos) {
            throw new IllegalArgumentException(
                    "Staring Position Miss-Match: " +
                    "Check database order sequence or " +
                    "AccountingCode Segmemnt setUp!!");
        }
        AccountingCodeSegmentDescription newValue
                = new AccountingCodeSegmentDescription(this, name,
                        description, length, required, startPos, dbRowID);
        if ((getSegmentsTotalLength() + length) > MAX_SEGMENT_LENGTH)
            throw new IllegalArgumentException("Total segment length " +
                    String.valueOf(getSegmentsTotalLength() + length) +
                    " exceeds max limit of " +
                    String.valueOf(MAX_SEGMENT_LENGTH));
        codeSegmentDescription.add(newValue);
        lastStartingPosition += length;
        firePropertyChange("addsegment", null, newValue);
    }


    /**
     * @param  segmentNumber : Segments are ordered in AccountingCenter. Staring from 0
     * @param  required to specify this Segment of AccountingCode as mandatory or not.
     * @exception IndexOutOfBoundsException  A runtime exception is thrown if segmentNumber is not valid.
     */
    public void setSegmentRequired(int segmentNumber, boolean required)
            throws IndexOutOfBoundsException {
        ((AccountingCodeSegmentDescription)
                codeSegmentDescription.get(segmentNumber)).
                setRequired(required);
        firePropertyChange("setsegmentrequired",
                new Integer(segmentNumber), null);
    }

    // get only methods to gather information of the Accounting Center.

    /**
     * To get a AccountingCodeSegmentDescription from the AccountingCenter.
     * AccountingCodeSegmentDescription has all setXX methods a protected hence
     * Object return can not nee modified by any non-package/non-subclass.
     *
     * @param  segmentNumber Segments are ordered in AccountingCenter. Starting value is 0.
     * @return AccountingCodeSegmentDescription corrosponding to that segmentNumber.
     * @exception IndexOutOfBoundsException a runtime exception is thrown if segmentNumber is not found in the AccountingCodeSegment.
     */
    public AccountingCodeSegmentDescription
            getAccountingCodeSegmentDescription(int segmentNumber)
            throws IndexOutOfBoundsException{
        return (AccountingCodeSegmentDescription)
                codeSegmentDescription.get(segmentNumber);
    }

    public AccountingCodeSegmentDescription
            getAccountingCodeSegmentDescription(String segmentName)
            throws IllegalArgumentException {
        for (int i = 0; i < codeSegmentDescription.size(); i++) {
            AccountingCodeSegmentDescription desc =
                    (AccountingCodeSegmentDescription)
                    codeSegmentDescription.get(i);
            if (desc.getName().equals(segmentName)) return desc;
        }
        throw new IllegalArgumentException("No Segment by the name " +
                segmentName + " found in AccountingCenter " +
                this.getAccountingCenterID());
    }

    /**
     * All the AccountingCodeSegmentDescription objects in AccountingCenter are returned.
     * AccountingCodeSegmentDescription has all setXX methods a protected hence
     * Object return can not nee modified by any non-package/non-subclass.
     *
     * @return AccountingCodeSegmentDescription[] All AccountingCodeSegmentDescription objects.
     */
    public AccountingCodeSegmentDescription[]
            getAccountingCodeSegmentDescriptions() {
        AccountingCodeSegmentDescription[] segmentDescriptionArray =
                new AccountingCodeSegmentDescription[
                        codeSegmentDescription.size()];
        codeSegmentDescription.toArray(segmentDescriptionArray);
        return segmentDescriptionArray;
    }

    /**
     * Add a comment segment with defined length and a name.
     *
     * @param  name of the comment.
     * @param  length of the comment.
     * @exception IllegalArgumentException  a runtime exception is thrown if cumulative lenght of comments segments exceed the maxvalue.
     */
    public void addCommentSegment(String name, int length) {
        CommentSegmentDescription newValue =
                new CommentSegmentDescription(name, length);
        if ((getCommentsTotalLength() + length) > MAX_COMMENT_LENGTH) throw new IllegalArgumentException("Total comment length " + String.valueOf(currentCommentTotalLength + length) + " exceeds max limit " + String.valueOf(MAX_COMMENT_LENGTH));
        commentSegmentDescription.add(newValue);
        firePropertyChange("addcommentsegment", null, newValue);
        //fire PropertyChangeEvent.
    }

    /**
     * Add a comment segment with defined length and a name.
     *
     * @param  name of the comment.
     * @param  length of the comment.
     * @param  dbRowID used by  Oracle for faster updates.
     * @exception IllegalArgumentException  a runtime exception is thrown if cumulative lenght of comments segments exceed the maxvalue.
     */
    public void addCommentSegment(String name, int length, String dbRowID) {
        CommentSegmentDescription newValue =
                new CommentSegmentDescription(name, length, dbRowID);
        if ((currentCommentTotalLength + length) > MAX_COMMENT_LENGTH) throw new IllegalArgumentException("Total comment length " + String.valueOf(currentCommentTotalLength + length) + " exceeds max limit " + String.valueOf(MAX_COMMENT_LENGTH));
        commentSegmentDescription.add(newValue);
        currentCommentTotalLength += length;
        firePropertyChange("addcommentsegment", null, newValue);
        //fire PropertyChangeEvent.
    }

    /**
     * To get a comment segment object at a perticular segment Number. Segment Number starts at 0
     *
     * @param  segmentNumber the ordered value of the commentsegment as stored in AccountingCenter Object.
     * @return CommentSegmentDescription
     * @exception IllegalArgumentException  a runtime exception is thrown if cumulative lenght of comments segments exceed the maxvalue.
     */
    public CommentSegmentDescription getCommentSegment(int segmentNumber) {
        return (CommentSegmentDescription)
                commentSegmentDescription.get(segmentNumber);
        //fire PropertyChangeEvent.
    }

    /**
     * To get all  the comment segment objects.
     *
     * @return CommentSegmentDescription array
     * @exception IllegalArgumentException  a runtime exception is thrown if cumulative lenght of comments segments exceed the maxvalue.
     */
    public CommentSegmentDescription[] getCommentSegments() {
        CommentSegmentDescription[] commentSegmentDescriptionArray =
                new CommentSegmentDescription[commentSegmentDescription.size()];
        commentSegmentDescription.toArray(commentSegmentDescriptionArray);
        return commentSegmentDescriptionArray;

    }

    public int getCommentsTotalLength() {
        CommentSegmentDescription[] array = getCommentSegments();
        int len = 0;
        for (int i = 0; i < array.length; i++) {
            len += array[i].getLength();
        }
        return len;
    }

    public int getSegmentsTotalLength() {
        AccountingCodeSegmentDescription[] array =
                getAccountingCodeSegmentDescriptions();
        int len = 0;
        for (int i = 0; i < array.length; i++) {
            len += array[i].getLength();
        }
        return len;
    }

    /**
     * Set the commentType value. If the value is changed from Segmented to FreeForm the
     * existing segmented values needs to be deleted.
     *
     * @param  typeIndicator to set the commentType to Free or Segmented.
     * @exception  IllegalArgumentException  a runtime exception is thrown  if typeIndicatoe is not valid.
     */
    public void setCommentType(short typeIndicator) {

        short oldValue = commentType;

        if (oldValue == typeIndicator) return;


        switch (typeIndicator) {

            case FREEFORM_COMMENTS:
                commentType = FREEFORM_COMMENTS;
                break;

            case SEGMENTED_COMMENTS:
                commentType = SEGMENTED_COMMENTS;
                break;

            default:
                throw new IllegalArgumentException("Invalid CommentType Indicator Value = " + String.valueOf(typeIndicator));
        }

        firePropertyChange("commenttype", new Short(oldValue), new Short(commentType));
        //delete segmented comments if changed to freeform.
    }

    /**
     * get the commentIndicator value.
     *
     * @return commentType value.
     */
    public int getCommentType() {
        return commentType;
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

    /**
     * To find out if the This AccountingCenter Object changes can be made persistance.
     *
     *
     * @return boolean true if dbRowID is valid else false .
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * To set the AccountingCenter object to be read only , changes can not be saved.
     *
     * @param value boolean if true No changes can be saved.
     */
    public void setReadOnly(boolean value) {
        readOnly = value;
    }

    /**
     * Set the creditDebit value.
     *
     * @deprecated use setCreditDebitIndicatorAllowed instead.
     * @param  newValue true = credit false = debit
     */
    public void setCreditAllocsAllowed(boolean newValue) {
        setCreditDebitIndicatorAllowed(newValue);
    }

    /**
     * get CreditAllocsAllowed value.
     *
     * @deprecated use isCreditDebitIndicatorAllowed instead.
     * @return booelan
     */
    public boolean isCreditAllocsAllowed() {

        return isCreditDebitIndicatorAllowed();
    }


    /**
     * Set the creditDebitIndicatorAllowed value.
     *
     * @param  newValue true = allowed false = not allowed.
     */
    public void setCreditDebitIndicatorAllowed(boolean newValue) {

        boolean oldValue = this.creditDebitIndicatorAllowed;
        // if(oldValue != newValue){ // find out what if set in set up. Ashish
        this.creditDebitIndicatorAllowed = newValue;
        firePropertyChange("accountingcenter", new Boolean(oldValue), new Boolean(newValue));
        //}


    }

    /**
     * get creditDebitIndicatorAllowed value.
     *
     * @return booelan
     */
    public boolean isCreditDebitIndicatorAllowed() {
        return creditDebitIndicatorAllowed;
    }


    /**
     * set Flag for reallocsafter AAS.
     *
     * @param  newValue boolean value.
     */
    public void setReallocsAfterAASAllowed(boolean newValue) {

        boolean oldValue = this.reallocsAfterAASAllowed;
        if (oldValue != newValue) {
            this.reallocsAfterAASAllowed = newValue;
            firePropertyChange("accountingcenter",
                    new Boolean(oldValue),
                    new Boolean(newValue));
        }
    }

    /**
     * get Reallocsafter AAS value.
     *
     * @return ReallocsafterASS indicator boolean value.
     */
    public boolean isReallocsAfterAASAllowed() {

        return reallocsAfterAASAllowed;
    }
    /////// get only methods


    /**
     * To get  the value of accountingcenterid.
     *
     * @return AccountingCenterId value String.
     */
    public String getAccountingCenterID() {
        return accountingCenterID;
    }

    /* public void setAccountingCenterID(String acID){

        this.accountingCenterID = acID;
    }
    */

    /**
     * The count of AccountingCodeSegments is returned.
     *
     * @return derived value of the no of AccountingCodeSegment.
     */
    public int getNoOfAccountingCodeSegments() {

        return codeSegmentDescription.size();
    }

    /**
     * The count of Comments Segments will be returned.
     *
     * @return derived value of the no of CommentSegments.
     */
    public int getNoOfCommentSegments() {

        return commentSegmentDescription.size();
    }
    /////// get/set methods

    /**
     * The AccountingCenter Name is returned.
     *
     * @return Name of the AccountingCenter.
     */

    public String getAccountingCenterName() {

        return accountingCenterName;
    }


    /**
     * The AccountingCenter Name is set.
     *
     * @param newValue name of the AccountingCenter
     */
    public void setAccountingCenterName(String newValue) {

        String oldValue = this.accountingCenterName;
        if (!oldValue.equals(newValue)) {
            this.accountingCenterName = newValue;
            firePropertyChange("accountingcenter", oldValue, newValue);
        }


        //FirePropertyChangeEvent
    }


    /**
     * The AccountingCenter Hierarchy is returned.
     *
     * @return Hierarchy[] object.
     */

    public HierarchyDTO[] getHierarchy() {
        return hierarchy;
    }

    public void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeHelper.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * This method will keep track of values that have changed.
     *
     * @param event java.beans.PropertyChangeEvent.
     */
   public void propertyChange(java.beans.PropertyChangeEvent event) {
     String propertyType = event.getPropertyName();
     Object newValue = event.getNewValue();
     Object oldValue = event.getOldValue();
     if (propertyType.equals("accountingcenter") || propertyType.equals("hierarchy") || propertyType.equals("priority")) {
        //save AccountingCenterInfo
        //ALL
        saveAccountingCenterInformation = true;

     } else if (propertyType.equals("commenttype")) {
        /// saveCommentsSegment   Input none  none
        ///ALL
        short oldComment = ((Short) oldValue).shortValue();
        short newComment = ((Short) newValue).shortValue();

        if (oldComment == SEGMENTED_COMMENTS && newComment == FREEFORM_COMMENTS) {
            saveDeleteCommentSegments = true;
        }
        saveAccountingCenterInformation = true;
     } else if (propertyType.equals("addcommentsegment")) {
                /// saveCommentsSegment   Input none  none
                ///ALL
        saveAddCommentSegment = true;

     } else if (propertyType.equals("addsegment")) {
        /// addsegment Input none none  //// rowID
        ///ALL
        saveAddSegments = true;

     } else if (propertyType.equals("setsegmentrequired")) {
        /// updateSegmentRequired Input segment#  none  //// rowID
        ///Individual+collective
        Integer sequenceNumber = (Integer) oldValue;
        saveSegmentRequiredNumbers.add(sequenceNumber);
        saveSetSegmentRequired = true;

     } else if (propertyType.equals("addsegmentvalue")) {
        ///// addSegmentValue() Input segment# value
        //// Individual+collective
        Integer sequenceNumber = (Integer) oldValue;
        String validValue = (String) newValue;
        saveSegmentValueNumbers.add(sequenceNumber);
        saveSegmentValueValues.add(validValue);
        saveAddSegmentValue = true;
     } else if (propertyType.equals("deactivatesegmentvalue")
             || propertyType.equals("updatesegmentvalue")) {
        ///// deactivateSegmentValue Input segment# value  // rowID
        Integer sequenceNumber = (Integer) oldValue;
        String validValue = (String) newValue;
        saveUpdateSegmentValueNumbers.add(sequenceNumber);
        saveUpdateSegmentValueValues.add(validValue);
        saveUpdateSegmentValue = true;
     }

  }

  /**
     * Static  method will replace the Spaces with the replaceBy char. Also suffix padding is done if
     * padUpto is more than then String length.
     *
     * @param value to be parsed.
     * @param replaceBy char value that will replace Space.
     * @param padUpto If greater than value length the padding will be done with replaceBy up to the padUpto length.
     * @since Eagls Enhancement
     */

    public static String parseSpace(String value, char replaceBy, int padUpto) {

        StringBuffer strBuff = new StringBuffer();

        char[] charArray = value.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isSpaceChar(charArray[i])) charArray[i] = replaceBy;
        }

        strBuff.append(charArray);

        int len1 = strBuff.length();

        if (padUpto > len1) {
            char[] pad = new char[padUpto - len1];
            for (int j = 0; j < pad.length; j++) {
                pad[j] = replaceBy;
            }
            strBuff.append(pad);
        }
        return strBuff.toString();

    }//end parseSpace

}//end of class

