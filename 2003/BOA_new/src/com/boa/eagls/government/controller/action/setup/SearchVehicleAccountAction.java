package com.boa.eagls.government.controller.action.setup;

import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.constants.web.*;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.controller.formbean.setup.SearchVehicleAccountForm;
import com.boa.eagls.government.dto.CentralOffice;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.service.MastercardVehicleSetupService;
import com.boa.eagls.government.util.DisplayDateUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class SearchVehicleAccountAction extends ActionBaseNew {
    private static final String SETUP_VEHICLE_URL = "setup";
    private static final String EXISTING_VEHICLE_URL = "exists";
    private static final Logger logger =
            Logger.getLogger(SearchVehicleAccountAction.class);


    /**
     * Constructor
     */
    public SearchVehicleAccountAction() {
        super();
    }

    public ActionForward eaglsExecute(ActionMapping mapping,ActionForm actionForm,
                                      HttpServletRequest request,HttpServletResponse response) {
        // todo fill in error depending on the wrong search result
        //ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        // return value
        SearchVehicleAccountForm searchVehicleAccountForm = (SearchVehicleAccountForm) actionForm;
        Map htUserData = session.getCurrentUserData(request);
        String userID = session.getUserID(request);
        MastercardVehicleSetupService service = new MastercardVehicleSetupService();
        String centralAcctID = searchVehicleAccountForm.getTxt_centralAccountId();
        String currentBaseRole = session.getCurrentBaseRole(request);
        String equipmentID = searchVehicleAccountForm.getTxt_equipmentId();
        if(equipmentID!=null){
            equipmentID = equipmentID.trim();
        }else{
            equipmentID="";
        }
        HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);
        Account entralAcct = null;
        ArrayList mcvAcctSumm = null;

        try {
            if (centralAcctID == null || centralAcctID.length() <= 0) {
                throw new EaglsException("error.emptyOrNullCentralAccountID");
            }
            AccountService centralAccount = new AccountService();
            int intCentralAcctID = Integer.parseInt(centralAcctID);
            if (!centralAccount.validateCentralAcctIDForSetup(intCentralAcctID,
                    htUserData, userID)) {
                throw new EaglsException("error.centralAccountDoesntExist");
            }
            mcvAcctSumm = service.searchMastercardVehicleByEquipmentID(centralAcctID,equipmentID, htUserData);
            if (mcvAcctSumm == null || mcvAcctSumm.isEmpty()) {
                entralAcct = service.retrieveCentralAcctByCentralAcctID(centralAcctID, userID);
                request.setAttribute(CentralAccount.CENTRAL_ACCOUNT, entralAcct);

                AgencyCore agencyCore = service.
                        retrieveAgencyCoreByCentralAcctID(centralAcctID, userID);
                HierarchyDTO[] hierarchy = agencyCore.getAHierarchy();
                BrowseHierarchyFields fields =
                        AccountService.fillHierarchyBean(hierarchy, currentHierarchy,
                                currentBaseRole, true);
                request.getSession().setAttribute(Eagles.BROWSE_HIERARHY_FIELDS, fields);
                if (agencyCore.getFleetType() == AgencyCore.MASTERCARD) {
                    AccountService accountService = new AccountService();
                    CentralOffice centralOffice = null;
                    //HierarchyDTO[] hierarchy = agencyCore.getAHierarchy();
                    int[] intArray = new int[hierarchy.length];
                    for (int i = 0; i < hierarchy.length; i++) {
                        intArray[i] = hierarchy[i].getNumber();
                    }
                    if ((Role.A_OPC).equalsIgnoreCase(
                            session.getCurrentBaseRole(request))) {
                        centralOffice = accountService.
                                retrieveCentralOffice(agencyCore.getAgencyID(), intArray,
                                        false, userID);
                    } else {
                        centralOffice = accountService.
                                retrieveCentralOffice(agencyCore.getAgencyID(), intArray, userID);
                    }
                    request.setAttribute(CentralAccount.CENTRAL_OFFICE, centralOffice);
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.YEAR, 2);
                    String expDateString = "";
                    expDateString = DisplayDateUtil.convertExpiryToString(cal.getTime());
                    request.setAttribute(CentralAccount.EXP_DATE_STRING, expDateString);
                    ArrayList nameDescTable = service.retrieveFuelTypeInfo();
                    request.setAttribute(CentralAccount.NAME_DESC_TABLE, nameDescTable);
                    request.setAttribute(AgencyAccount.AGENCY_CORE, agencyCore);
                    forward = mapping.findForward(SETUP_VEHICLE_URL);
                } else {
                    //не мастер - is not a master card
                    ActionMessages messages = new ActionMessages();
                    messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_APPLICATION_ERROR));
// NO HEADER IN THIS SCREEN
                    messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
                    messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER_NOT_MASTERCARD));
                    messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_OK));
                    saveMessages(request, messages);

                    ActionErrors errors = new ActionErrors();
                        errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                                new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                                        searchVehicleAccountForm.getTxt_centralAccountId()));

                    saveErrors(request, errors);

                    forward = mapping.findForward(Eagles.ERROR_FORWARD);
                }
            } else {
                forward = mapping.findForward(EXISTING_VEHICLE_URL); /////////// уже есть аккаунт
            }
        } catch (EaglsException e) {
            ActionMessages messages = new ActionMessages();
            messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_NO_DATA_FOUND));
            messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
            messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER_SEARCH));
            messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_SERACH));
            saveMessages(request, messages);

            ActionErrors errors = new ActionErrors();
                errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                        new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                                searchVehicleAccountForm.getTxt_centralAccountId()));

            saveErrors(request, errors);
            forward = mapping.findForward(Eagles.ERROR_FORWARD);
        } catch (EaglsDAOError e) {
            logger.error("ERROR in searchACTION!", e);
        }
        return (forward);
    }

}
