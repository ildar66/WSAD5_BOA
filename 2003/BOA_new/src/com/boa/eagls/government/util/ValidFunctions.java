/**
 * ValidFunctions
 */
package com.boa.eagls.government.util;

import com.boa.eagls.government.constants.web.Eagles;
import com.boa.eagls.government.controller.action.account.SearchAccountAction;
import com.boa.eagls.government.controller.formbean.maintenance.centralaccount.SearchCentralAccountForm;

import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * To change this template use Options | File Templates.
 */
public class ValidFunctions {
    public static final char YES = '1';
    public static final char NO = '0';
    private Vector funcVect = null;

    /**
     * Constructor declaration
     *
     */
    public ValidFunctions() {
        funcVect = new Vector();
        loadFuncs();
    }

    /**
     * Method declaration
     *
     *
     * @param str
     *
     * @return
     */
    public String[][] parseRoleFunctions(String str) {
        Vector retVect = new Vector();

        if (str.length() == funcVect.size()) {
            for (int i = 0; i < funcVect.size(); i++) {
                if (str.charAt(i) == YES) {
                    retVect.addElement(funcVect.elementAt(i));
                }
            }
        }
        //return buildMenu(retVect);
        return convertToSArray(retVect);
    }


    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[][] getAllFunctions() {
        return convertToSArray(funcVect);
    }

    /**
     * Method declaration
     *
     *
     * @param inVect
     *
     * @return
     */
    private String[][] convertToSArray(Vector inVect) {
        String[][] retVal = new String[inVect.size()][3];

        for (int i = 0; i < inVect.size(); i++) {
            retVal[i] = ((FuncDesc) inVect.elementAt(i)).convertToSArray();
        }
        return retVal;
    }

    /**
     * Method declaration
     *
     *
     * @param fList
     *
     * @return
     */
    public String getFunctionsString(Vector fList) {
        char[] retVal = new char[funcVect.size()];

        for (int i = 0; i < funcVect.size(); i++) {
            if (fList != null
                    && fList
                    .contains(((FuncDesc) funcVect.elementAt(i)).fName)) {
                retVal[i] = YES;
            } else {
                retVal[i] = NO;
            }
        }
        return new String(retVal);
    }

    /**
     * Method declaration
     *
     */
    public final void loadFuncs() {
        funcVect.addElement(new FuncDesc("_top_level_function",
                Constants.FUNC_ACC_INQUIRY_MAINTENANCE,
                "jsp/gsa/common/dynamicSearch.jsp",
//                /*"Search.do"*/  NOT_IMPLEMENTED_ACTION,                        // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=acctMaint"));
        funcVect.addElement(new FuncDesc("Setup",
                Constants.FUNC_S_AGENCY,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=agencySetup"));

        funcVect.addElement(new FuncDesc("Setup", Constants.FUNC_S_ACC_CENTER,
                /*"AccountingCenterSetupDetail.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "AccountingCenterSetupDetail"));

        funcVect.addElement(new FuncDesc("Setup", Constants.FUNC_S_CENTRAL_ACC,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=centralAccountSetup"));

        funcVect.addElement(new FuncDesc("Setup", Constants.FUNC_S_ACCOUNT,
                SearchAccountAction.SEARCH_URL,
                Constants.SEARCH_PARAM_SEARCHTYPE + "=indivAccountSetup"));
        funcVect.addElement(new FuncDesc("Setup", Constants.FUNC_S_VEHICLE_ACC,
//                "MCVehicleSetupSearchOptions",
                "jsp/gsa/setup/mastercard_vehicle/search_vehicle_account.jsp",
                Constants.SEARCH_PARAM_SEARCHTYPE + "=vehicleSetup"));
        funcVect.addElement(new FuncDesc("Inquiry", Constants.FUNC_I_AGENCY_PROFILE,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=agencyInquiry"));
        funcVect.addElement(new FuncDesc("Inquiry", Constants.FUNC_I_CENTRAL_ACC,
                "jsp/gsa/centralaccount/search.jsp",
                Constants.SEARCH_PARAM_SEARCHTYPE + "=" + SearchCentralAccountForm.INQUIRY_SEARCH_STR));
        funcVect.addElement(new FuncDesc("Inquiry", Constants.FUNC_I_ACCOUNT, "Search.do",
                Constants.SEARCH_PARAM_SEARCHTYPE + "=accountInquiry"));
        funcVect.addElement(new FuncDesc("Inquiry", Constants.FUNC_I_VEHICLE_ACC,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=vehicleAccountInquiry"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_AGENCY_PROFILE,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=agencyMaintenance"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_ACCOUNTING_CENTER,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=accountingcentermaintenance"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_CENTRAL_ACC,
                "jsp/gsa/centralaccount/search.jsp",
                Constants.SEARCH_PARAM_SEARCHTYPE + "=" + SearchCentralAccountForm.MAINTENANCE_SEARCH_STR));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_TRANSFER_ACC,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=transferAcctSearchAopc"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_TRANSFER_ACC_QUEUE,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=transferAcctSearch"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_VEHICLE_ACC,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=vehicleMaintenance"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_ACC_ACTIVATION,
                /*"AccountActivationSearch.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=AccountActivationSearch"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_ACC_STATUS,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=accountStatus"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_BULK_ACTIVATION,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=bulkActivation"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_CHANGE_ATM_PIN,
                /*"ChangeATMPinSearch.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "ChangeATMPinSearch"));           // Replaced by S12o
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_CREDIT_LIMIT,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=creditLimitMaintenance"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_QUEUED_REQ,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=queuedRequest"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_TRAVELERS_CHECKS,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=travelersChecks"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_RENEW_ACC,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=accountRenewal"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_REQUEST_CARD,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=requestCard"));
        funcVect.addElement(new FuncDesc("Transaction Data", Constants.FUNC_T_AGENCY_INVOICE,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=agencyInvoice"));
        funcVect.addElement(new FuncDesc("Transaction Data", Constants.FUNC_T_COST_ALLOCATION,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=costAllocationAll"));
        funcVect.addElement(new FuncDesc("Transaction Data",
                Constants.FUNC_T_INDV_STATEMENT,
                "Search.do",
                Constants.SEARCH_PARAM_SEARCHTYPE + "=individualStatement"));
        funcVect.addElement(new FuncDesc("Transaction Data",
                Constants.FUNC_T_VIEW_CURRENT, "Undefined.do"));
        funcVect.addElement(new FuncDesc("Transaction Data", Constants.FUNC_T_RAPID_RECON,
                /*"RapidReconSearch.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "RapidReconSearch"));                // Replaced by S12o
        funcVect.addElement(new FuncDesc("User Options", Constants.FUNC_U_CHANGE_ACCOUNT,
                /*"ChangeAccountsSubmit.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "ChangeAccountsSubmit"));
        funcVect.addElement(new FuncDesc("User Options",
                Constants.FUNC_U_CHANGE_HIER,
                /*"ChangeHierarchySubmit.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "ChangeHierarchySubmit.do"));
        funcVect.addElement(new FuncDesc("User Options", Constants.FUNC_U_CHANGE_ROLE,
                /*"ChangeRoleEntry.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "ChangeRoleEntry"));
        funcVect.addElement(new FuncDesc("User Options", Constants.FUNC_U_CHANGE_PWD,
                /*"ChangePassword.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "ChangePassword"));
        funcVect.addElement(new FuncDesc("User Options", Constants.FUNC_U_CREATE_ROLE,
                /*"CreateRoleEntry.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "CreateRoleEntry"));
        funcVect.addElement(new FuncDesc("User Options", Constants.FUNC_U_MODIFY_ROLE,
                /*"ModifyRoleEntry.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "ModifyRoleEntry"));
        funcVect.addElement(new FuncDesc("User Options", Constants.FUNC_U_RESET_PWD,
                /*"ResetPasswordSearch.do"*/ Eagles.NOT_IMPLEMENTED_ACTION, "User Options"));
        funcVect.addElement(new FuncDesc("User Options",
                Constants.FUNC_U_CREATE_USER_PROFILE,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=createUserProfile"));
        funcVect.addElement(new FuncDesc("User Options",
                Constants.FUNC_U_MODIFY_USER_PROFILE,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=modifyUserProfile"));
        funcVect.addElement(new FuncDesc("User Options", Constants.FUNC_U_FAVORITES_LIST,
                Eagles.NOT_IMPLEMENTED_ACTION, "AcctCodeFavorites.do"));

        /*
         * IValList GSAPrgForm = CState.getNodeContents("URLs");
         * if ((GSAPrgForm != null) && (GSAPrgForm.getValString("GSAProgramFormsURL")!= null)){
         * funcVect.addElement(new FuncDesc("Program Information","P-GSA Program Forms",
         * GSAPrgForm.getValString("GSAProgramFormsURL"),1) );
         * //               Modified to read from Eagls Config to solve THD changing
         * //               URLS per Christine/Dan -- CD 8/8/2000
         * //               "http://www.gcsuthd.bankofamerica.com/forms",1) );
         * }
         */
        funcVect.addElement(new FuncDesc("Program Information", Constants.FUNC_P_PCN_PROGRAM_FORMS,
                /*"/gsa/program_information/pcn_program_forms/menu_pcnProgramForms.html"*/ Eagles.NOT_IMPLEMENTED_ACTION,
                1));
        funcVect.addElement(new FuncDesc("Program Information", Constants.FUNC_P_NEWSLETTER,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=newsletters"));
        funcVect.addElement(new FuncDesc("Maintenance", Constants.FUNC_M_CONVENIENCE_CHECKS,
                /*"Search.do"*/  Eagles.NOT_IMPLEMENTED_ACTION, // Replaced by S12o
                Constants.SEARCH_PARAM_SEARCHTYPE + "=convenienceChecks"));
    }

}
