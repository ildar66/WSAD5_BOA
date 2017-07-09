package com.boa.eagls.government.dto.authorizationcontrols;

import java.io.Serializable;

/**
 * @author IldarS
 *
 * <code>MCCGControls is a part of Authroization Controls
 * The controls on a specific MCCGTable for central, Mastercard Vehicle, or individual are
 * described in MCCGontrols as well as the parent class Controls.
 * In the Inquiry function, MCCGControls information is called from from the result page
 * of central, Mastercard Vehicle, and individual AcctControls inquiry.</code>
 *
 */
public class MCCGControls extends Controls implements Serializable {
    //The attributes otherwise noted map to screen elements described in SDRs.

    //additional static finals used for mccgAction;
    public static final short DIVERT = 5;
    public static final short BYPASS = 4;

    private short mccgTableNo = SHORT_DEFAULT;          //the mccgTableNo is used to hold count during setup
    private String mccgTableName = STRING_DEFAULT;
    private short mccgAction = SHORT_DEFAULT;
    private short mccgCheckNullvc = SHORT_DEFAULT;
//----------------------------------------Constructor-------------------------------------------

    public MCCGControls() {
    }

    public MCCGControls(MCCGControls tMCCGControls) {
        super(tMCCGControls);
        setMCCGTableNo(tMCCGControls.getMCCGTableNo());
        setMCCGTableName(tMCCGControls.getMCCGTableName());
        setMCCGAction(tMCCGControls.getMCCGAction());
    }
/**
    // Standard interface functions
    public static void update(String acctNo, MCCGControls oldMCCGControls, MCCGControls newMCCGControls, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        if (!newMCCGControls.equals(oldMCCGControls)) {
            AccountDAA adapter = new AccountDAA(aDASTransport);
            adapter.updateMCCGControls(acctNo, newMCCGControls);
        }
    }

    public static void create(MCCGControls mccgControls, String acctNo, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        AccountDAA adapter = new AccountDAA(aDASTransport);
        adapter.queueMCCGControls(acctNo, mccgControls);
    }

    public static void create(MCCGControls mccgControls, String acctNo, int requestID, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        AccountDAA adapter = new AccountDAA(aDASTransport);
        adapter.queueMCCGControls(acctNo, requestID, mccgControls);
    }

    public static void delete(String acctNo, String mccgTableName, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        AccountDAA adapter = new AccountDAA(aDASTransport);
        adapter.deleteMCCGTable(acctNo, mccgTableName);
    }
*/
//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from MCCGControls.

    public String getMCCGTableName() {
        return new String(mccgTableName);
    }

    public short getMCCGTableNo() {
        return mccgTableNo;
    }

    public short getMCCGAction() {
        return mccgAction;
    }

    public short getCheckNullVC() {
        return mccgCheckNullvc;
    }


//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data element values in MCCGControls.

    public void setMCCGAction(short tMCCGAction) {
        mccgAction = tMCCGAction;
    }

    public void setMCCGTableNo(short tMCCGTableNo) {
        mccgTableNo = tMCCGTableNo;
    }

    public void setMCCGTableName(String tMCCGTableName) {
        mccgTableName = validateString(tMCCGTableName);
    }

    public void setCheckNullVC(short tmccgCheckNullvc) {
        mccgCheckNullvc = tmccgCheckNullvc;
    }

//----------------------------------------action methods-------------------------------------------
    //These methods are to help perform functions on this class

    public boolean equals(MCCGControls aMCCG) {

        boolean parent = super.equals(aMCCG);
        if ((parent) && (this.getMCCGTableName().equals(aMCCG.getMCCGTableName()))
                && (this.getMCCGAction() == aMCCG.getMCCGAction())) {
            return true;
        } else {
            return false;
        }
    }

}
