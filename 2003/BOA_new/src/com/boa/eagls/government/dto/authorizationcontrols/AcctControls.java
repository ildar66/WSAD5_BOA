package com.boa.eagls.government.dto.authorizationcontrols;

/**
 * @author IldarS
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
/**
 * <code>This is the container object for account controls for an account
 * AcctControls or Account Controls is a part of Authroization Controls
 * The controls on a specific account: central, Mastercard Vehicle, or individual are
 * described in AcctControls as well as the parent class Controls.
 * AcctControls also contain a list of MCCG Table Names that are used to show MCCG Table Controls.
 * In the Inquiry function, AcctControls information is called from from the result page
 * of central, Mastercard Vehicle, and individual inquiry.</code>
 *
 /* 	@version 1.0 $Modtime:   11 Jun 1999 14:34:58  $$Revision: 1.5 $
 /* 	@author  Ed Zhang  & Frank Jary
 **/

import com.boa.eagls.government.dao.AccountDAO;
import com.boa.eagls.government.dto.account.Account;
//import com.nb.gsa.Adapter.DataAccess.core.DASTransport;
import com.boa.eagls.government.exceptions.*;

import java.io.Serializable;
import java.sql.Connection;

public class AcctControls extends Controls implements Serializable {
	//The attributes otherwise noted map to screen elements described in SDRs.

	private boolean foreignCurResSet = BOOLEAN_DEFAULT;
	//Foreign Currency Restricition Set used or not
	private short foreignType = SHORT_DEFAULT;
	private short foreignAction = SHORT_DEFAULT;
	private String[] mccgTableNames = STRING_ARRAY_DEFAULT;
	//List of MCCGTableNames used then to get specific MCCG Table
	private short checkNullCRS = SHORT_DEFAULT;
	// Added to check for null values
	private short checkNullVC = SHORT_DEFAULT;

	//----------------------------------------Constructor-------------------------------------------

	public AcctControls() {
	}

	public AcctControls(AcctControls tAcctControls) {
		super(tAcctControls);
		setForeignCurResSet(tAcctControls.getForeignCurResSet());
		setForeignType(tAcctControls.getForeignType());
		setForeignAction(tAcctControls.getForeignAction());
		setMCCGTableNames(tAcctControls.getMCCGTableNames());
	}

	// Standard interface
	public static int createQueueRequest(
	    Connection conn,
		AcctControls acctControls,
		String accountNumber)
		throws NBException {
		AccountDAO adapter = new AccountDAO();
//todo: pemporary commented
//		return adapter.queueAccountControls(accountNumber, acctControls);
        return 1;
	}

	/**
	    public static int createQueueRequest(AcctControls acctControls, String accountNumber, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport) throws NBException {
	        AccountDAO adapter = new AccountDAO();
	        return adapter.queueAccountControls(accountNumber, acctControls);
	    }

	    public static void update(String acctNo, AcctControls oldAcctControls, AcctControls newAcctControls, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
	            throws NBException {
	        if (!newAcctControls.equals(oldAcctControls)) {
	            AccountDAA adapter = new AccountDAA(aDASTransport);
	            adapter.updateAccountControls(acctNo, newAcctControls);
	        }
	    }
	*/
	//--------------------------------------get methods-----------------------------------------------

	// These methods are used to retrieve data elements from AcctControls.

	//This is not going to be used for Build 2
	public String[] getMCCGTableNames() {
		String[] copyMCCGTableNames = new String[mccgTableNames.length];
		for (int i = 0; i < mccgTableNames.length; i++) {
			copyMCCGTableNames[i] = mccgTableNames[i];
		}
		return copyMCCGTableNames;
	}

	public boolean getForeignCurResSet() {
		return foreignCurResSet;
	}

	public short getForeignType() {
		return foreignType;
	}

	public short getForeignAction() {
		return foreignAction;
	}

	public short getNullCheckForCRS() {
		return checkNullCRS;
	}

	public short getNullCheckForVC() {
		return checkNullVC;
	}

	//--------------------------------------set methods-----------------------------------------------
	// These methods are used to set data element values in AcctControls.

	//This is not going to be used for Build 2
	public void setMCCGTableNames(String[] tMCCGTableNames) {
		if (tMCCGTableNames != null) {
			mccgTableNames = tMCCGTableNames;
		} else {
			mccgTableNames = new String[HIERARCHY_LIMIT];
			for (int i = 0; i < HIERARCHY_LIMIT; i++) {
				mccgTableNames[i] = "";
			}
		}
	}

	public void setForeignCurResSet(boolean tForeignCurResSet) {
		foreignCurResSet = tForeignCurResSet;
	}

	public void setForeignCurResSet(Boolean tForeignCurResSet) {
		foreignCurResSet = validateBoolean(tForeignCurResSet);
	}

	public void setForeignType(short tForeignType) {
		foreignType = tForeignType;
	}

	public void setForeignAction(short tForeignAction) {
		foreignAction = tForeignAction;
	}

	public void setNullCheckForCRS(short tCheckNullCRS) {
		checkNullCRS = tCheckNullCRS;
	}

	public void setNullCheckForVC(short tCheckNullVC) {
		checkNullVC = tCheckNullVC;
	}

	//-----------------------------------action methods---------------------------------

	public boolean equals(AcctControls aAcctControls) {

		boolean parent = super.equals(aAcctControls);
		if ((parent)
			&& (this.getForeignCurResSet() == aAcctControls.getForeignCurResSet())
			&& (this.getForeignType() == aAcctControls.getForeignType())
			&& (this.getForeignAction() == aAcctControls.getForeignAction())) {
			return true;
		} else {
			return false;
		}
	}
}
