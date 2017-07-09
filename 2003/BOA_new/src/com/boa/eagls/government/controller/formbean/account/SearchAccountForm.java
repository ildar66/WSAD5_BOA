package com.boa.eagls.government.controller.formbean.account;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.service.individualaccount.SearchAccountParam;

/**
 * Struts Form Bean (actually Value Object) for Search in Individual Account Setup use case
 * @version 	1.0
 * @author  Oleg Klimenko
 */
public class SearchAccountForm extends ActionForm implements SearchAccountParam  {

//    private int hierarhyAccess[] = null;
	private String txtCentralAccountID;
	private String txtLastName = null;
	private String txtFirstName = null;
	private boolean ragAccountSetupSearch = true;
	private String txtSocialSecurityNumber = null;

    public boolean isSearchByName() {
        return ragAccountSetupSearch;
    }
	/**
	 * Get txt_centralAccountID
	 * @return String
	 */
	public String getTxtCentralAccountID() {
		return txtCentralAccountID;
	}

	/**
	 * Set txt_centralAccountID
	 * @param <code>String</code>
	 */
	public void setTxtCentralAccountID(String t) {
		txtCentralAccountID = t;
	}
	/**
	 * Get txt_lastName
	 * @return String
	 */
	public String getTxtLastName() {
		return txtLastName;
	}

	/**
	 * Set txt_lastName
	 * @param <code>String</code>
	 */
	public void setTxtLastName(String t) {
		txtLastName = t;
	}
	/**
	 * Get txt_firstName
	 * @return String
	 */
	public String getTxtFirstName() {
		return txtFirstName;
	}

	/**
	 * Set txt_firstName
	 * @param <code>String</code>
	 */
	public void setTxtFirstName(String t) {
		txtFirstName = t;
	}
	/**
	 * Get rag_accountSetupSearch
	 * @return boolean
	 */
	public boolean getRagAccountSetupSearch() {
		return ragAccountSetupSearch;
	}

	/**
	 * Set rag_accountSetupSearch
	 * @param <code>boolean</code>
	 */
	public void setRagAccountSetupSearch(boolean r) {
		ragAccountSetupSearch = r;
	}
	/**
	 * Get txt_socialSecurityNumber
	 * @return String
	 */
	public String getTxtSocialSecurityNumber() {
		return txtSocialSecurityNumber;
	}

	/**
	 * Set txt_socialSecurityNumber
	 * @param <code>String</code>
	 */
	public void setTxtSocialSecurityNumber(String t) {
		txtSocialSecurityNumber = t;
	}
	/**
	* Constructor
	*/
	public SearchAccountForm() {
		super();
        ragAccountSetupSearch = true;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		txtCentralAccountID = null;
		txtLastName = null;
		txtFirstName = null;
		ragAccountSetupSearch = true;
		txtSocialSecurityNumber = null;
	}
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
        //todo add server side form validation

        try {
            Integer.parseInt(txtCentralAccountID);
        } catch(Exception e) {
            errors.add("txtCentralAccountID", new ActionError("error.centralAccountID.required"));
        }

		if (txtCentralAccountID == null && txtCentralAccountID.length()<=0) {
			errors.add("txtCentralAccountID", new ActionError("error.centralAccountID.required"));
        }

        /*// search by Last Name & First Name
        if (ragAccountSetupSearch) {
            if (txtLastName == null || txtLastName.length()<=0) {
	        	errors.add("txtLastName", new ActionError("error.lastName.required"));
			}
        } 
        //search by SSN
        else {
        	//todo add additional formatting check of SSN (format is 999-99-9999)
        	if (txtSocialSecurityNumber == null || txtSocialSecurityNumber.length()<=0) {
	        	errors.add("txtSocialSecurityNumber", new ActionError("error.socialSecurityNumber.required"));
			}
        }//*/

		return errors;

	}

}
