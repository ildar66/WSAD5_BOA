package com.boa.eagls.government.constants.web;

/**The interface should be used for storing all errors descriprions
 * are displayed on Error Pages
 * <p><small> VDI Company, 24.07.2003 17:35:19</small></p>
 * @author AlexanderZe
 */
public interface ErrorMessages {

    //THE NEXT FOUR CONSTANTS ARE USED ON ERROR PAGE
    //FOR DISPLAYING MESSADES IN MAINTAINS CENTRAL ACCOUNT
    public static final String SEARCH_CENTRAL_ACCOUNT_ID="search.central.accountId"; //Central Account ID
    public static final String SEARCH_CENTRAL_ACCOUNT_NUMBER="search.central.accountNumber";//Central Account Number
    public static final String SEARCH_CENTRAL_ACCOUNT_NANE="search.central.accountName";//Central Account Name
    public static final String SEARCH_HIERARCHY="search.hierarchy";//Hierarchy Depth
    public static final String SEARCH_INDIVIDUAL_ACCOUNT_HIERARCHY = "search.individual.account.hierarchy";
    public static final String SEARCH_INDIVIDUAL_ACCOUNT_NAME = "search.individual.account.name";

    //Used in TransactionData Individual Account
    public static final String TRANSACTION_DATA_ACCOUNT_NUMBER="transactiondata.individualaccount.accountNumber";//Hierarchy Depth

    public static final String APPLICATION_ERROR_MESSAGE = "applicationError.message";
    public static final String SEARCH_NAME="search.name";
    public static final String SEARCH_ZIP_POSTAL_CODE="search.zipPostalCode";
    public static final String SEARCH_BUSINESS_PHONE="search.businessPhone";
    public static final String SEARCH_SSN="search.ssn";
    public static final String SEARCH_ACCOUNT_STATUS="search.accountStatus";

}
