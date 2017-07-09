package com.boa.eagls.government.controller.action.common;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.controller.formbean.common.BrowseCenterAccountingIDForm;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.accounting.AccountingCenterSummary;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.service.AccountingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.*;

/**
 * <p><small> VDI Company, 18.07.2003 16:53:26</small></p>
 * @author OlegK
 */
public class BrowseCenterAccountingIDAction extends Action {
    static final Logger logger = Logger.getLogger(BrowseCenterAccountingIDAction.class);

/* Session Wrapper */
    protected EAGLSSession session;

    /**
     * Constructor
     */
    public BrowseCenterAccountingIDAction() {
        super();
        session = new EAGLSSession();
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
    public ActionForward perform(

            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
//        return null;
        // todo fill in error depending on the wrong search result
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();

        BrowseCenterAccountingIDForm viewAccountForm = (BrowseCenterAccountingIDForm) form;
        String currentBaseRole = session.getCurrentBaseRole(request);
        String startRecordStr = null;
        String forceUpdate = "yes";
        HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);

        //todo
        String hierarchySearchString = request.getParameter("availableHierarchiesList");

        HierarchyDisplay hierarchy = null;
        short searchDepth = 8;
        String baseRole = currentBaseRole;
        if (Role.GCSU.equals(baseRole)) {// GCSU has no hierarchy
            hierarchy = new HierarchyDisplay(hierarchySearchString);
            for (int i = 2; i < 9; i++) {//GCSU can see everything in that HL1 (same agency) and under
                hierarchy.setSegment(i, new HierarchyDTO((short) i, 0, ""));
            }
        } else if (Role.A_OPC.equals(baseRole)) { // AOPC can browse from their own hierarchy and below, dis-regard passed in account's hierarchy
            // ALSO new browse method in DAA has special logic for scope change 324.
            hierarchy = new HierarchyDisplay(currentHierarchy);
            searchDepth = 8;
//            Fix for CMW2175. Before making changes
//            to this file, PLEASE refer to the comments in
//            CMW1877, CMW1854, CMW2175, scope change 324 and CMW1767.
        } else if (Role.AH.equals(baseRole)) {
            hierarchy = new HierarchyDisplay(hierarchySearchString);//AH can see only their account's hierarchy and nothing below
            searchDepth = 0;
        } else {
            hierarchy = new HierarchyDisplay(hierarchySearchString);// others - whoever they are...
            searchDepth = 8;
        }

        //String[] acctCtrIDs = null;
        List acctSummaryList = new LinkedList(); // of AccountingCenterSummary
        if (startRecordStr == null || forceUpdate != null) { // not nextColumn or prev selection
            try {
                AccountingService accountingService = new AccountingService();
                acctSummaryList = accountingService.browseAccountingCenterIDs(hierarchy.getValues(), searchDepth, currentBaseRole);
            } catch (EaglsException e) {
                logger.error("Error during browse center accounting ID", e);
//                return processException(e);
            }
            if (viewAccountForm == null) {
                viewAccountForm = new BrowseCenterAccountingIDForm();
            }

            if (acctSummaryList != null) {
//                if (startRecordStr == null) {
//                    startRecord = 0;
//                } else {
//                    startRecord = Integer.parseInt(startRecordStr);
//                }
//                resultSetID = new Random().nextInt();
//                nextHLParams.setValString("resultSetID", String.valueOf(resultSetID));
//                prevHLParams.setValString("resultSetID", String.valueOf(resultSetID));

//                resultSet = new LaRS(session.getSessionID(), guid(), resultSetID);
//                for (int i = 0; i < acctSummaryList.length; i++) {
//                    resultSet.writeRecord(acctSummaryList[i]);
//                }
//                resultSet.finishedWriting();
//                setLaRS(resultSet);
            }
        } else { // coming in from nextColumn or prev selection
//            resultSetID = Integer.parseInt(valIn.getValString("resultSetID"));

            // get LaRS
//            resultSet = getLaRS();

            // verify result set is for inquiry
//            if (!resultSet.validate(guid(), resultSetID)) {
//                valIn.setValString("forceUpdate", "force");
//                return newRequest(guid(), valIn, valOut);
//            }

            // retrieve starting record number
//            startRecord = Integer.parseInt(startRecordStr);
//            if (startRecord > resultSet.numberOfRecords()) {
//                startRecord = 0;
//            }
        }

//        Object[] records;
//        if (resultSet.numberOfRecords() > 0) {
//            mapMultiplePageSymbols(map, guid(), prevHLParams, nextHLParams,
//                    startRecord, RECORDS_PER_PAGE, resultSet.numberOfRecords(),
//                    "");
//            GX.Release(prevHLParams);
//            GX.Release(nextHLParams);

//            records = resultSet.readRecords(startRecord, RECORDS_PER_PAGE);
        ArrayList idValues = new ArrayList();
        ArrayList idDescriptions = new ArrayList();
        Iterator it = acctSummaryList.iterator();
        while (it.hasNext()) {
            AccountingCenterSummary accountingCenterSummary = (AccountingCenterSummary) it.next();
            idValues.add(accountingCenterSummary.getAccountingCenterID());
            idDescriptions.add(accountingCenterSummary.getAccountingCenterID() +
                    " [" + accountingCenterSummary.getAccountingCenterName() + "]");
        }
        viewAccountForm.setIdValues(idValues);
        viewAccountForm.setIdDescriptions(idDescriptions);

        request.setAttribute("options", acctSummaryList);

        request.setAttribute("frm_browseAccountingCenterIds", viewAccountForm);
//            for (int i = 0; i < records.length; i++) {
//                data.rowAppend("idValue=" + ((AccountingCenterSummary) records[i]).getTxt_accountingCenterID() +
//                        ";idDescription=" + ((AccountingCenterSummary) records[i]).getTxt_accountingCenterID() +
//                        " [" + ((AccountingCenterSummary) records[i]).getAccountingCenterName() + "]");
//            }
        if (acctSummaryList.size() > 0) {
            forward = mapping.findForward(FORWARD_BROWSE_ITSELF);
        } else {
            forward = mapping.findForward(FORWARD_BROWSE_NOT_FOUND);
        }
//            return evalTemplate("gsa/common/browse_accountingCenterIds.html", data, map);
//        } else {
//            return evalTemplate("gsa/common/results_noResultsFoundAccountingID.html", (com.kivasoft.ITemplateData) null, map);
//        }

//*///        forward = mapping.findForward("setup/individualAccount/found_new");
        /*// If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.
        //todo check if error handling by server is required
        if (!errors.empty()) {
            saveErrors(request, errors);
            //	forward = mapping.findForward("failure");
        }*/
        return (forward);
    }

    private static String FORWARD_BROWSE_ITSELF = "common/browse_accountingCenterIds";
    private static String FORWARD_BROWSE_NOT_FOUND = "common/browse_noResultsFound";

}
