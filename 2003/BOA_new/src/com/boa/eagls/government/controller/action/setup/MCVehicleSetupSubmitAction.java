package com.boa.eagls.government.controller.action.setup;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.service.MastercardVehicleSetupService;
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.service.MCVehicleSetupSubmitParam;
import com.boa.eagls.government.service.MCVehicleSetupSubmitService;
import com.boa.eagls.government.constants.web.Eagles;
import com.boa.eagls.government.constants.web.Messages;
import com.boa.eagls.government.exceptions.NoDataFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p><small> DVI Company, 29.07.2003 14:19:26</small></p>
 * @author GlebL
 */
public class MCVehicleSetupSubmitAction
        extends ActionBaseNew {
    static final Logger logger =
            Logger.getLogger(MCVehicleSetupSubmitAction.class);
    private final static String MC_VEHICLE_SETUP_SUBMIT_URL =
            "mastercard_vehicle/MCVehicleSetupSubmit";

    public ActionForward eaglsExecute(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        // todo fill in error depending on the wrong search result
        //ActionErrors errors = new ActionErrors();
        MCVehicleSetupSubmitParam params =
                (MCVehicleSetupSubmitParam) actionForm;
        ActionForward forward = new ActionForward();
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
            MCVehicleSetupSubmitService submitService =
                    new MCVehicleSetupSubmitService();
            submitService.createMastercardVehicle(params, userID);
            forward = mapping.findForward(MC_VEHICLE_SETUP_SUBMIT_URL);
        }catch(Exception e) {
            logger.error("Error creating account", e);
            ActionMessages messages = new ActionMessages();
            messages.add(Messages.TITLE,
                    new ActionMessage(Messages.TITLE_APPLICATION_ERROR));
            messages.add(Messages.FOOTER, new ActionMessage(
                    Messages.FOOTER_VEHICLE_SERUP, e.getMessage()));
            messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_OK));
            saveMessages(request, messages);
            forward = mapping.findForward(ERROR_URL);
        }
        return forward;
    }
}