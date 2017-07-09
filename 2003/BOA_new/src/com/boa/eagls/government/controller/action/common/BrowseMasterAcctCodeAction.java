package com.boa.eagls.government.controller.action.common;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.controller.action.ActionBase;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.constants.web.AgencyAccount;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.browse.BrowseMasterAcctCodeDTO;
import com.boa.eagls.government.service.AccountingService;
import com.boa.eagls.government.service.common.BrowseMasterAcctCodeService;
import com.boa.eagls.government.constants.web.CenterAccounting;
import com.boa.eagls.government.exceptions.application.EaglsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p><small> DVI Company, 18.07.2003 17:55:38</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeAction extends ActionBaseNew {
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeAction.class);
    private static final String SETUP_ACCOUNTING_CODE_SEGMENT_SELECTION_URL
            = "commom/setupAccountingCodeSegmentSelection";
    private static final String BROWSE_MASTER_ACCT_CODE_DTO =
            "browseMasterAcctCodeDTO";

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
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {
        ActionForward forward = new ActionForward();
        String userID = session.getUserID(request);
        String acctCenterID = request.getParameter("accountingCenterId");
        String hdn_valueSet = request.getParameter("hdn_valueSet");
        String hdn_segNumSet = request.getParameter("hdn_segNumSet");
        String fromHyperLink = request.getParameter("fromHyperLink");
        String masterAccountingCode = request.getParameter("masterAccountingCode");
        String hdn_segmentNumber = request.getParameter("hdn_segmentNumber");
        String hdn_segmentValue = request.getParameter("hdn_segmentValue");
        String hideButton = request.getParameter("hideButton");
        String slashes = request.getParameter("slashes");

        try {
            BrowseMasterAcctCodeService service =
                    new BrowseMasterAcctCodeService();
            BrowseMasterAcctCodeDTO browseMasterAcctCodeDTO = service.
                    getBrowseMasterAcctCodeDTO(userID,
                            acctCenterID,
                            hdn_valueSet,
                            hdn_segNumSet,
                            fromHyperLink,
                            masterAccountingCode,
                            hdn_segmentNumber,
                            hdn_segmentValue,
                            hideButton,
                            slashes);

            request.setAttribute(BROWSE_MASTER_ACCT_CODE_DTO, browseMasterAcctCodeDTO);
            forward = mapping.findForward(
                    SETUP_ACCOUNTING_CODE_SEGMENT_SELECTION_URL);
        } catch (EaglsException e) {
            logger.error("error.browseMasterAcctCode", e);
            forward = mapping.findForward(ERROR_URL);
        }
        return (forward);

    }

}