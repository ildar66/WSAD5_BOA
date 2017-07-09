package com.boa.eagls.government.controller.action.setup;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.service.MCVehicleSetupVerifyParam;
import com.boa.eagls.government.service.MCVehicleSetupVerifyService;
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.service.MastercardVehicleSetupService;
import com.boa.eagls.government.dto.setup.MCVehicleSetupVerifyDTO;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.constants.web.Eagles;
import com.boa.eagls.government.constants.web.Messages;
import com.boa.eagls.government.constants.web.ErrorMessages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p><small> DVI Company, 28.07.2003 14:45:48</small></p>
 * @author GlebL
 */
public class MCVehicleSetupVerifyAction
        extends ActionBaseNew{
    static final Logger logger =
            Logger.getLogger(MCVehicleSetupVerifyAction.class);
    private final static String MC_VEHICLE_SETUP_VERIFY_URL =
            "mastercard_vehicle/MCVehicleSetupVerify";
    private final static String MC_VEHICLE_SETUP_VERIFY_DTO =
            "mcVehicleSetupVerifyDTO";

    public ActionForward eaglsExecute(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        // todo fill in error depending on the wrong search result
        //ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        MCVehicleSetupVerifyParam params =
                (MCVehicleSetupVerifyParam) actionForm;
        String userID = session.getUserID(request);
        String currentBaseRole = session.getCurrentBaseRole(request);
        HierarchyDTO[] currentHierarchy =
                session.getCurrentHierarchy(request);
        try {
            MastercardVehicleSetupService service =
                    new MastercardVehicleSetupService();
            AgencyCore agencyCore = service.
                        retrieveAgencyCoreByCentralAcctID(params.getHdn_centralId(), userID);
                HierarchyDTO[] hierarchy = agencyCore.getAHierarchy();
                BrowseHierarchyFields fields =
                        AccountService.fillHierarchyBean(hierarchy, currentHierarchy,
                                currentBaseRole, false);
                request.getSession().setAttribute(Eagles.BROWSE_HIERARHY_FIELDS, fields);
            MCVehicleSetupVerifyService verifyService =
                    new MCVehicleSetupVerifyService();
            verifyService.verify(currentHierarchy,
                    params, currentBaseRole, userID);
            MCVehicleSetupVerifyDTO dto =
                    verifyService.getMCVehicleSetupVerifyDTO(params);
            request.setAttribute(MC_VEHICLE_SETUP_VERIFY_DTO, dto);
            forward = mapping.findForward(MC_VEHICLE_SETUP_VERIFY_URL);
        }catch(Exception e) {
            ActionMessages messages = new ActionMessages();
            messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_APPLICATION_ERROR));

//            messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
            messages.add(Messages.FOOTER, new ActionMessage(
                    Messages.FOOTER_VEHICLE_SERUP, e.getMessage()));
            messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_OK));
            saveMessages(request, messages);
//            ActionErrors errors = new ActionErrors();
//            errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
//                    new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID));
//            saveErrors(request, errors);
            forward = mapping.findForward(ERROR_URL);
        }
        return forward;
    }

}