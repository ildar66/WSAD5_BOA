package com.boa.eagls.government.controller.action.common;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.service.common.BrowseMasterAcctCodeFrameService;
import com.boa.eagls.government.dto.browse.BrowseMasterAcctCodeFrameDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p><small> DVI Company, 22.07.2003 17:35:09</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeFrameAction
        extends ActionBaseNew {
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeFrameAction.class);
    private static final String BROWSE_MASTER_ACCT_CODE_FRAME_URL =
            "common/framesAccountingCodeBrowse";
    private static final
            String BROWSE_MASTER_ACCOUNTING_CODE_FRAME_DTO =
            "browseMasterAcctCodeFrameDTO";

    public ActionForward eaglsExecute(ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        ActionForward forward = new ActionForward();
        String accountingCenterId = request.
                getParameter("accountingCenterId");
        String masterAccountingCode = request.
                getParameter("masterAccountingCode");
        String hideFavoriteButton = request.
                getParameter("hideFavoriteButton");
        String slashes = request.
                getParameter("slashes");
        String userID = session.getUserID(request);
        try {
            BrowseMasterAcctCodeFrameService service =
                    new BrowseMasterAcctCodeFrameService();
            BrowseMasterAcctCodeFrameDTO dto = service.
                    getBrowseMasterAcctCodeFrameDTO(accountingCenterId,
                            masterAccountingCode,
                            hideFavoriteButton,
                            slashes,
                            userID
                    );
            request.setAttribute(
                    BROWSE_MASTER_ACCOUNTING_CODE_FRAME_DTO, dto);
            forward = mapping.findForward(BROWSE_MASTER_ACCT_CODE_FRAME_URL);
        } catch (EaglsException e) {
            forward = mapping.findForward(ERROR_URL);
        }
        return (forward);
    }


}