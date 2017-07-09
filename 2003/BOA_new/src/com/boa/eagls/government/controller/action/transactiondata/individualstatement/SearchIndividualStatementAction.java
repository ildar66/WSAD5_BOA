package com.boa.eagls.government.controller.action.transactiondata.individualstatement;

import org.apache.struts.action.*;
import org.apache.log4j.Logger;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.controller.formbean.transactiondata.IndividualStatementForm;
import com.boa.eagls.government.controller.formbean.transactiondata.IndividualStmFormValues;
import com.boa.eagls.government.service.transactiondata.SearchIndividualStatementService;
import com.boa.eagls.government.service.transactiondata.SearchCriteria;
import com.boa.eagls.government.service.transactiondata.SearchTypesValues;
import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.constants.web.Messages;
import com.boa.eagls.government.constants.web.ErrorMessages;
import com.boa.eagls.government.exceptions.application.EaglsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 11.07.2003
 * Time: 19:12:22
 * To change this template use Options | File Templates.
 */
public class SearchIndividualStatementAction extends Action{
    private static final Logger LOGGER = Logger.getLogger(SearchIndividualStatementAction.class);

    protected EAGLSSession session;

    /**
     * Constructor
     */
    public SearchIndividualStatementAction() {
        super();
        session = new EAGLSSession();
    }

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {


        IndividualStatementForm individualStatementForm = (IndividualStatementForm)form;
        //LOGGER.debug(individualStatementForm.getRag_statementSearch());
        Map htUserData = session.getCurrentUserData(request);
        String userID = session.getUserID(request);

        String curAcc = session.getCurrentAccount(request);
        SearchCriteria  crit = prepareSearchCriteria(curAcc, individualStatementForm, htUserData, userID);
        String forward = null;
        try {
            forward = getForwardName(crit, request.getSession(), individualStatementForm.isChk_accountNumber());
        } catch (EaglsException e) {
             ActionMessages messages = new ActionMessages();
            messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_APPLICATION_ERROR));
            messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER));
            messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER));
            messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_OK));
            saveMessages(request, messages);

            ActionErrors errors = new ActionErrors();
            errors.add(ErrorMessages.APPLICATION_ERROR_MESSAGE, new ActionError(""));
            this.saveErrors(request, errors);
            LOGGER.error("Short description here", e); //To change body of catch statement use Options | File Templates.
        }

        //todo set attribute SEARCH_RESULTS to request??
        ActionForward frwd = mapping.findForward(forward);
        LOGGER.debug("Forward obtained " + forward);
        return frwd;//Forwards.SEARCH_INDIVIDUAL_STATMENT_FORWARD);
    }

    private String getForwardName(SearchCriteria  crit, HttpSession session, boolean selectedAcctNumber) throws EaglsException {
        SearchIndividualStatementService service = new SearchIndividualStatementService();
        String result = "";
        Object toBePlacedInSession = null;
        LOGGER.info("Search " + crit.getSearchOption());
        switch (crit.getSearchOption()) {
            case SearchTypesValues.FOR_CURRENT_TRANSACTIONS_BY_ACCOUNTNUM:
                crit.setSearchFor(SearchTypesValues.CURRENT_TRANSACTIONS);
                toBePlacedInSession = service.showStatementTransactionsScreen(crit);
                result = Forwards.STATEMENT_TRANSACTION_LIST;
                break;
            case SearchTypesValues.FOR_CURRENT_STATEMENT_BY_ACCOUNTNUM:
            case SearchTypesValues.FOR_DATE_BY_ACCOUNTNUM:
            case SearchTypesValues.FOR_DATE_RANGE_BY_ACCOUNTNUM:
                crit.setSearchFor(SearchTypesValues.CURRENT_STATEMENT);
                toBePlacedInSession = service.showIndStatementScreen(crit);
                result = Forwards.INDIVIDUAL_STATEMENT_SCREEN;
                break;
            case SearchTypesValues.FOR_CURRENT_TRANSACTIONS_BY_HIERARCHY:
            case SearchTypesValues.FOR_CURRENT_STATEMENT_BY_HIERARCHY:
            case SearchTypesValues.FOR_DATE_BY_HIERARCHY:
                crit.setSearchFor(SearchTypesValues.CURRENT_TRANSACTIONS);
                toBePlacedInSession = service.showMultipleResultsScreen(crit, selectedAcctNumber);
                result = Forwards.MULTIPLE_RESULTS_SCREEN;
                break;
        }
        LOGGER.info(result);
        session.setAttribute(SessionParams.SEARCH_RESULTS, toBePlacedInSession);
        return result;
    }


    private SearchCriteria prepareSearchCriteria(String currentAcc, IndividualStatementForm formBean, Map htUserData, String userID) throws NumberFormatException {
        SearchCriteria c = new SearchCriteria();
        c.setAcctNumber(currentAcc);
        String forSelection = formBean.getRag_statementSearch();
        LOGGER.info("For selection " + forSelection);
        //pageParams.setValString(RAG_STATEMENT_SEARCH, forSelection);
        //boolean holder = formBean.isChk_accountNumber();
        boolean selectedAcctNumber = formBean.isChk_accountNumber();//holder;
        //pageParams.setValString(CHK_ACCOUNT_NUMBER, holder);
        //holder = valIn.getValString(CHK_HIER_LEVEL);
        boolean selectedHierarchy = formBean.isChk_hierLevel();//!(holder == null || holder.equals(""));
        //pageParams.setValString(CHK_HIER_LEVEL, holder);
        String role = (String) htUserData.get("currentRole");
        if (Role.AH.equalsIgnoreCase(role)) {
            c.setAcctNumber(currentAcc);
        } else {
            if (selectedAcctNumber) {
                c.setAcctNumber(formBean.getCentralAccountID());//valIn.getValString(TXT_ACCOUNT_NUMBER);
            } else if (selectedHierarchy) {
//UNUSED NOW
                String holder = formBean.getTxt_hierarchyDepth();//getTxt_hierarchyDepth()
                c.setDepth(Integer.parseInt(holder.trim()));
                int[] hCodes = new int[9];
                hCodes[0] = Integer.parseInt(formBean.getTxt_hl0());
                hCodes[1] = Integer.parseInt(formBean.getTxt_hl1());
                hCodes[2] = Integer.parseInt(formBean.getTxt_hl2());
                hCodes[3] = Integer.parseInt(formBean.getTxt_hl3());
                hCodes[4] = Integer.parseInt(formBean.getTxt_hl4());
                hCodes[5] = Integer.parseInt(formBean.getTxt_hl5());
                hCodes[6] = Integer.parseInt(formBean.getTxt_hl6());
                hCodes[7] = Integer.parseInt(formBean.getTxt_hl7());
                hCodes[8] = Integer.parseInt(formBean.getTxt_hl8());
                c.sethCodes(hCodes);
            }
        }
        if (IndividualStmFormValues.CURRENT_TRANSACTIONS.equalsIgnoreCase(forSelection)) {
            c.setSearchFor(SearchTypesValues.CURRENT_TRANSACTIONS);
            if (selectedAcctNumber || Role.AH.equalsIgnoreCase(role)) {
                c.setSearchOption(SearchTypesValues.FOR_CURRENT_TRANSACTIONS_BY_ACCOUNTNUM);
            } else {// by hierarchy
                c.setSearchOption(SearchTypesValues.FOR_CURRENT_TRANSACTIONS_BY_HIERARCHY);
            }

        } else if (IndividualStmFormValues.CURRENT_STATEMENT.equalsIgnoreCase(forSelection)) {
            c.setSearchFor(SearchTypesValues.CURRENT_STATEMENT);
            if (selectedAcctNumber || Role.AH.equalsIgnoreCase(role)) {
                c.setSearchOption(SearchTypesValues.FOR_CURRENT_STATEMENT_BY_ACCOUNTNUM);
            } else {// by hierarchy
                c.setSearchOption(SearchTypesValues.FOR_CURRENT_STATEMENT_BY_HIERARCHY);
            }
//        } else(IndividualStmFormValues.STATEMENT.equalsIgnoreCase(forSelection)){
        } else {
            c.setSearchFor(SearchTypesValues.CURRENT_INVOICE);
            c.setSearchByDate(formBean.getTxt_statementDate());
            if (selectedAcctNumber || Role.AH.equalsIgnoreCase(role)) {
                c.setSearchOption(SearchTypesValues.FOR_DATE_BY_ACCOUNTNUM);
            } else { // by hierarchy
                c.setSearchOption(SearchTypesValues.FOR_DATE_BY_HIERARCHY);
            }
//        } else (IndividualStmFormValues.STATEMENT_RANGE.equalsIgnoreCase(forSelection)) {
            //todo ??find where determines range in web page??
            //searchFor = SearchTypesValues.CURRENT_INVOICE;
            //searchFromDate = formBean. //.getValString(TXT_STATEMENT_BEGIN_DATE);
            //searchToDate = valIn.getValString(TXT_STATEMENT_END_DATE);
            //pageParams.setValString(TXT_STATEMENT_BEGIN_DATE, searchFromDate);
            //pageParams.setValString(TXT_STATEMENT_END_DATE, searchToDate);
            //if (selectedAcctNumber || getCurrentBaseRole().equals("AH"))
            //    searchOption = FOR_DATE_RANGE_BY_ACCOUNTNUM;
            //else
            //    searchOption = FOR_DATE_RANGE_BY_HIERARCHY;
        }
        String transSort = formBean.getSel_indTransSort();//.valIn.getValString(SEL_IND_TRANS_SORT);
        if (IndividualStmFormValues.TRANSACTION_POST_DATE.equalsIgnoreCase(transSort)) {
            c.setSortTransBy(SearchTypesValues.TRANSACTION_POST_DATE);
        } else if (IndividualStmFormValues.TRANSACTION_AMOUNT.equalsIgnoreCase(transSort)) {
            c.setSortTransBy(SearchTypesValues.TRANSACTION_AMOUNT);
        } else if (IndividualStmFormValues.TRANSACTION_STATUS.equalsIgnoreCase(transSort)) {
            c.setSortTransBy(SearchTypesValues.TRANSACTION_STATUS);
        } else if (IndividualStmFormValues.MERCHANT_NAME.equalsIgnoreCase(transSort)) {
            c.setSortTransBy(SearchTypesValues.MERCHANT);
        }
        return c;
    }

}
