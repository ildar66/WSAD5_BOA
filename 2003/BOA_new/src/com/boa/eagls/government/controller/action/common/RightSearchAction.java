package com.boa.eagls.government.controller.action.common;

import com.boa.eagls.government.constants.web.Eagles;
import com.boa.eagls.government.constants.web.ErrorMessages;
import com.boa.eagls.government.constants.web.Messages;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.search.SearchDTO;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.IndividualAccountService;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.ValidFunctions;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Common search action. Used from multiple places.
 * @author Oleg Klimenko
 * Date: 25.07.2003
 * Time: 12:33:31
 */
public class RightSearchAction extends ActionBaseNew {
    private static Logger logger = Logger.getLogger(RightSearchAction.class);

    /**
     * Constructor
     */
    public RightSearchAction() {
        super();
    }


    /**
     * The method is called by Struts framework after a form has been submited.
     * All of business logic dealing with processing form submission event must be
     * placed here.
     * @param mapping class containing keys from struts-config.xml for forward mapping
     * @param form form bean filled in by submitted form values
     * @param request servlet's request object
     * @param response servlet's response object
     * @return simple object filled in by forward information from <code>mapping</code>
     * object
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    public ActionForward eaglsExecute(

            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        ActionForward forward = new ActionForward();
        SearchDTO viewAccountForm = (SearchDTO) form;
        String currentBaseRole = session.getCurrentBaseRole(request);
        char[] programTypes = session.getCurrentProgramTypes(request);
        HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);
        if ("on".equals(viewAccountForm.getChk_accountNumber())) {
            request.setAttribute("functionality", "Search by Account Number");
            return mapping.findForward("notImplemented");
        }

        Collection results = null;
        try {
            IndividualAccountService individualAccountService = new IndividualAccountService();
            results = individualAccountService.search(viewAccountForm, currentHierarchy, currentBaseRole, programTypes);
        } catch (EaglsDAOError eaglsDAOError) {
            logger.error("logic error", eaglsDAOError);
        }

        request.setAttribute("searchResult", results);

//        HierarchyDisplay hDisplay = new HierarchyDisplay();
//        BrowseHierarchyFields browseHierarchyFields = new BrowseHierarchyFields();//Service.fillHierarchyBean(hDisplay,currentHierarchy,currentBaseRole,true);
//        request.getSession().setAttribute("browseHierarchyFields", browseHierarchyFields);
        if (results == null || results.size() <= 0) {
            ActionErrors errors = new ActionErrors();
            if (viewAccountForm.getRag_acctMaintSearchFor() != null && viewAccountForm.getRag_acctMaintSearchFor().length() > 0) {
                switch (viewAccountForm.getRag_acctMaintSearchFor().charAt(0)) {
                    case 'O':
                        errors.add(ErrorMessages.SEARCH_ACCOUNT_STATUS,
                                new ActionError(ErrorMessages.SEARCH_ACCOUNT_STATUS, "Open"));
                        break;
                    case 'C':
                        errors.add(ErrorMessages.SEARCH_ACCOUNT_STATUS,
                                new ActionError(ErrorMessages.SEARCH_ACCOUNT_STATUS, "Closed"));
                        break;
                    default:
//                        errors.add(ErrorMessages.SEARCH_ACCOUNT_STATUS,
//                                new ActionError(ErrorMessages.SEARCH_ACCOUNT_STATUS, "All Accounts"));
                }
            } else {
//                errors.add(ErrorMessages.SEARCH_ACCOUNT_STATUS,
//                        new ActionError(ErrorMessages.SEARCH_ACCOUNT_STATUS, "All Accounts"));
            }

            /*if ("on".equals(viewAccountForm.getChk_accountNumber())) {
                errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_NUMBER,
                        new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_NUMBER,
                                viewAccountForm.getTxt_accountNumber()));
            }*/
            if ("on".equals(viewAccountForm.getChk_Name())) {
                errors.add(ErrorMessages.SEARCH_INDIVIDUAL_ACCOUNT_NAME,
                        new ActionError(ErrorMessages.SEARCH_INDIVIDUAL_ACCOUNT_NAME,
                                viewAccountForm.getTxt_firstName() == null ? "" : viewAccountForm.getTxt_firstName() + " " +
                        viewAccountForm.getTxt_lastName()));
                if (viewAccountForm.getTxt_zipCode() != null && viewAccountForm.getTxt_zipCode().length() > 0) {
                    errors.add(ErrorMessages.SEARCH_ZIP_POSTAL_CODE,
                            new ActionError(ErrorMessages.SEARCH_ZIP_POSTAL_CODE,
                                    viewAccountForm.getTxt_zipCode()));
                }
            }

            if ("on".equals(viewAccountForm.getChk_busPhone())) {
                //search by international phone
                if (viewAccountForm.getTxt_businessPhoneIntl() != null && viewAccountForm.getTxt_businessPhoneIntl().length() > 0) {
                    errors.add(ErrorMessages.SEARCH_BUSINESS_PHONE,
                            new ActionError(ErrorMessages.SEARCH_BUSINESS_PHONE,
                                    viewAccountForm.getTxt_businessPhoneIntl()));
                } else {//search by USA or Canada phone
                    errors.add(ErrorMessages.SEARCH_BUSINESS_PHONE,
                            new ActionError(ErrorMessages.SEARCH_BUSINESS_PHONE,
                                    viewAccountForm.getTxt_businessPhone1() + viewAccountForm.getTxt_businessPhone2() +
                            viewAccountForm.getTxt_businessPhone3()));
                }
            }

            if ("on".equals(viewAccountForm.getChk_SSN())) {
                errors.add(ErrorMessages.SEARCH_SSN,
                        new ActionError(ErrorMessages.SEARCH_SSN,
                                viewAccountForm.getTxt_SS1() + "-" + viewAccountForm.getTxt_SS2() + "-" +
                        viewAccountForm.getTxt_SS3()));
            }

            if ("hierarchy".equals(viewAccountForm.getChk_hierLevel())) {
                String[] hierarhyStr = new String[9];
                for (int i = 0; i < 9; i++) {
                    hierarhyStr[i] = viewAccountForm.getTxt_hl(i);
                }
                errors.add(ErrorMessages.SEARCH_INDIVIDUAL_ACCOUNT_HIERARCHY,
                        new ActionError(ErrorMessages.SEARCH_INDIVIDUAL_ACCOUNT_HIERARCHY, hierarhyStr));
            }

            saveErrors(request, errors);

            ActionMessages messages = new ActionMessages();
            messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_NO_DATA_FOUND));//Messages.TITLE_NO_DATA_FOUND
            messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
            messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER_SEARCH));
            messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_SERACH));
            saveMessages(request, messages);

            forward = mapping.findForward(Eagles.ERROR_FORWARD);
        } else if (results.size() == 1) {
            request.setAttribute("functionality", "View account details");
            return mapping.findForward("notImplemented");
        } else {
            forward = mapping.findForward("success");
        }

        if (viewAccountForm.getRag_acctMaintSearchFor() != null && viewAccountForm.getRag_acctMaintSearchFor().length() > 0) {
                switch (viewAccountForm.getRag_acctMaintSearchFor().charAt(0)) {
                    case 'O':
                        viewAccountForm.setRag_acctMaintSearchFor("Open");
                        break;
                    case 'C':
                        viewAccountForm.setRag_acctMaintSearchFor("Closed");
                        break;
                    default:
                        viewAccountForm.setRag_acctMaintSearchFor("");
                }
        }
        request.setAttribute("viewAccountForm", viewAccountForm);

        return forward;
//            int centralAcctID = Integer.parseInt((String) request.getSession().getAttribute("centralAcctID")); //viewAccountForm.getTxtC valIn.getValString("txt_centralAccountID");
//            HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);

    }
}
