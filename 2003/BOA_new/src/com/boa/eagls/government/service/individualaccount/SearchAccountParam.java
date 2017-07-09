package com.boa.eagls.government.service.individualaccount;

import java.io.Serializable;

/**
 * Used for sending Struts Form Bean from Web layer to Service layer. Using this interface Web
 * layer hides unnecessary methods and keeps only gettets. Used in Search in SetupIndividual Account Setup.
 * @author Oleg Klimenko
 * Date: 24.06.2003
 * Time: 17:31:26
 */
public interface SearchAccountParam extends Serializable {

    /**
     *
     * @return
     */
    public boolean isSearchByName();

    /**
	 * Get txt_centralAccountID
	 * @return String
	 */
    String getTxtCentralAccountID();

    /**
	 * Get txt_lastName
	 * @return String
	 */
    String getTxtLastName();

    /**
	 * Get txt_firstName
	 * @return String
	 */
    String getTxtFirstName();

    /**
	 * Get rag_accountSetupSearch
	 * @return boolean
	 */
    boolean getRagAccountSetupSearch();

    /**
	 * Get txt_socialSecurityNumber
	 * @return String
	 */
    String getTxtSocialSecurityNumber();

}
