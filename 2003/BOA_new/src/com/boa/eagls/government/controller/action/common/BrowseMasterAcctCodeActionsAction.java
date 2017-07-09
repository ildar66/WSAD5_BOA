package com.boa.eagls.government.controller.action.common;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.service.common.BrowseMasterAcctCodeActionsService;
import com.boa.eagls.government.service.common.BrowseMasterAcctCodeActionsParam;
import com.boa.eagls.government.dto.browse.BrowseAccountingCodesDTO;
import com.boa.eagls.government.constants.web.Eagles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p><small> DVI Company, 24.07.2003 19:21:21</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeActionsAction
        extends ActionBaseNew{
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeActionsAction.class);
    private static final String BROWSE_ACCOUNTING_CODES_URL =
            "commom/browseAccountingCodesBrowse";
    private static final String WARNING_INVALID_ACCOUNTING_CODE_URL =
            "commom/warningInvalidAccountingCode";
    private static final String VERIF_ACCOUNTING_CODE_SEGMENT_ADDITION_URL =
            "commom/verifAccountingCodeSegmentAddition";
    private static final String BROWSE_ACCOUNTING_CODES_DTO =
            "browseAccountingCodesDTO";
    //private static final String ADD_SEGMENT = "Add Segment";
    private static final String BROWSE_SEGMENT = "Browse Segment";

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @pre $none
     * @post $none
     */
    public ActionForward eaglsExecute(ActionMapping mapping,
                                      ActionForm formBean,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {
        ActionForward forward = new ActionForward();
        String userID = session.getUserID(request);
        BrowseMasterAcctCodeActionsParam form =
                (BrowseMasterAcctCodeActionsParam) formBean;
        try {
            Map parameterMap = request.getParameterMap();
            String action = form.getBut_submit().trim();
            boolean isCodeOptions = false;
            Object searchResult = request.getAttribute(Eagles.VALUE_LIST_ITERATOR);
            if (searchResult != null) {
                isCodeOptions = true;
            }
            BrowseMasterAcctCodeActionsService service =
                    new BrowseMasterAcctCodeActionsService();
            BrowseAccountingCodesDTO dto =
                    service.
                    getBrowseAccountingCodesDTO(parameterMap, form,
                            userID, isCodeOptions);
            request.setAttribute(BROWSE_ACCOUNTING_CODES_DTO, dto);
            if (BROWSE_SEGMENT.equals(action)) {
                if (!isCodeOptions) {
                    request.setAttribute(Eagles.VALUE_LIST_ITERATOR, dto.getCodeOptions());
                }
                else if (searchResult != null) {
                    request.setAttribute(Eagles.VALUE_LIST_ITERATOR, searchResult);
                }
                forward = mapping.findForward(
                        BROWSE_ACCOUNTING_CODES_URL);
            }
            else {
                if (service.isAccountingCodeSegmentValidated()) {
                    forward = mapping.findForward(
                            VERIF_ACCOUNTING_CODE_SEGMENT_ADDITION_URL);
                }
                else {
                    forward = mapping.findForward(
                            WARNING_INVALID_ACCOUNTING_CODE_URL);
                }
            }
        } catch (EaglsException e) {
            logger.error("error.browseAccountingCodes", e);
            forward = mapping.findForward(ERROR_URL);
        }
        return (forward);
    }

}