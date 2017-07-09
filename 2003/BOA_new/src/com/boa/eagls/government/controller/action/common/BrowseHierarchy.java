package com.boa.eagls.government.controller.action.common;

import com.boa.eagls.government.controller.action.ActionBase;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.service.common.BrowseService;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.HTMLFormat;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.util.LaRS;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

/**
 *
 * <p>Title: BrowseHierarchy class</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class BrowseHierarchy extends ActionBase {
    static final Logger logger = Logger.getLogger(BrowseHierarchy.class);
    static final int RECORDS_PER_PAGE = 20;

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
                                      HttpServletResponse response) throws Exception {
        session = new EAGLSSession();

        /* Checking for session expiry */
        /*if (session.getCurrentRole(request) == null)
        {
            return mapping.findForward("sessionExpired");
        }*/


        BrowseService browseService = new BrowseService(null);
        Hashtable map = new Hashtable();
        LaRS resultSet = null;
        int startRecord;
        int resultSetID;
        Hashtable prevHLParams = copyUserValInParams(request);
        Hashtable nextHLParams = copyUserValInParams(request);
        String startRecordStr = request.getParameter("startRecord");
        String forceUpdate = request.getParameter("forceUpdate");

        if (forceUpdate == null) {
            forceUpdate = (String) request.getAttribute("forceUpdate");
        }
        Vector data = new Vector();
        String completeHierarchy =
                request.getParameter("availableHierarchiesList");

        // get getCurrentBaseRole() from session
        if (!session.getCurrentBaseRole(request).equals("GCSU")
                && completeHierarchy.length() == 56) {

            // get getCurrentHierarchy() from session
            completeHierarchy =
                    String
                    .valueOf(session.getCurrentHierarchy(request)[0].getNumber())
                    + completeHierarchy;
        }
        HierarchyDisplay hierarchy = new HierarchyDisplay(completeHierarchy);
        // Check to see if hierarchy completely filled in.  If so, put up error message.
        if (HierarchyDisplay.NUMBER_OF_SEGMENTS == (hierarchy.getDepth())) {
            request.setAttribute("map", map);

            // return mapping.findForward("this");
            return mapping.findForward("browse_hierarchy_max_level_reached");
        }
        int[] hBrowse = hierarchy.getValues();

        if (hBrowse[0] == 0) {
            HierarchyDTO[] current = session.getCurrentHierarchy(request);

            if (current != null) {
                hBrowse[0] = current[0].getNumber();
                hierarchy.setSegment(0, current[0]);
            }
        }
        HierarchyDTO[] nextLevel;

        if (startRecordStr == null || forceUpdate != null) {    // not nextColumn or prev selection
            String isTransfer = request.getParameter("isTransfer");
            String disregardAccess = request.getParameter("hdn_isTransfer");

            if ((isTransfer != null && isTransfer.equals("transfer"))
                    || disregardAccess != null) {
                try {
                    nextLevel = browseService.browseHierarchyWithDesc(hBrowse,
                            false, 50, true,
                            session.getCurrentHierarchy(request),
                            session.getCurrentBaseRole(request));
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    return mapping.findForward("error");
                }
            } else {
                try {
                    nextLevel = browseService.browseHierarchyWithDesc(hBrowse,
                            false, 100, session.getCurrentHierarchy(request),
                            session.getCurrentBaseRole(request));
                    map.put("noAccess", "");
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    return mapping.findForward("browse_hierarchyLevels");

                    // return mapping.findForward("error");
                }
            }
            if (nextLevel != null) {
                if (startRecordStr == null) {
                    startRecord = 0;
                } else {
                    startRecord = Integer.parseInt(startRecordStr);
                }
                resultSetID = new Random().nextInt();
                nextHLParams.put("resultSetID", String.valueOf(resultSetID));
                prevHLParams.put("resultSetID", String.valueOf(resultSetID));
                resultSet = new LaRS(request.getSession().getId(),
                        resultSetID);
                for (int i = 0; i < nextLevel.length; i++) {
                    resultSet.writeRecord(nextLevel[i]);
                }
                resultSet.finishedWriting();
                session.setLaRS(resultSet, request);
            } else {
                return mapping.findForward("this");
            }
        } else {    // coming in from nextColumn or prev selection
            resultSetID =
                    Integer.parseInt(request.getParameter("resultSetID"));

            // get LaRS
            resultSet = session.getLaRS(request);

            // verify result set is for inquiry
            if (!resultSet.validate(resultSetID)) {
                request.setAttribute("forceUpdate", "force");
                return mapping.findForward("this");
            }

            // retrieve starting record number

            startRecord = Integer.parseInt(startRecordStr);
            if (startRecord > resultSet.numberOfRecords()) {
                startRecord = 0;
            }
        }
        Object[] records;

        if (resultSet.numberOfRecords() > 0) {
            mapMultiplePageSymbols(map,
                    request.getContextPath()
                    + "/browseHierarchy.do",
                    prevHLParams, nextHLParams, startRecord,
                    RECORDS_PER_PAGE,
                    resultSet.numberOfRecords(), "");

            records = resultSet.readRecords(startRecord, RECORDS_PER_PAGE);
            int depth = hierarchy.getDepth();
            HierarchyDTO hrchy = null;

            for (int i = 0; i < records.length; i++) {
                hrchy = (HierarchyDTO) records[i];
                hierarchy.setSegment(depth, hrchy);

                // String sTemp = hierarchy.getCombinedSegments63();
                StringBuffer sTemp =
                        new StringBuffer(hierarchy.getCombinedSegments63());

                for (int j = 1; j < 9; j++) {

                    if (hrchy.getParentAgencyName(j) != null
                            && !hrchy.getParentAgencyName(j).equals("")) {
                        // sTemp += hrchy.getParentAgencyName(j);
                        sTemp.append(hrchy.getParentAgencyName(j));
                    } else {
                    }
                    if (j < 8) {
                        sTemp.append("^");
                    }
                }
                sTemp =
                        new StringBuffer(HTMLFormat.htmlReplace(sTemp.toString(),
                                '\"'));
                Hashtable htTemp = new Hashtable();

                htTemp.put("hierarchyValue", sTemp.toString());
                htTemp.put("hierarchyDescription",
                        hierarchy.getAgencyName(hierarchy.getDepth() - 1));
                data.add(htTemp);
            }
            if (hrchy != null) {
                if (depth > 1 && hrchy.getParentAgencyName(1) != null) {
                    map.put("hl1Desc", hrchy.getParentAgencyName(1));
                } else {
                    map.put("hl1Desc", "");
                }
                if (depth > 2 && hrchy.getParentAgencyName(2) != null) {
                    map.put("hl2Desc", hrchy.getParentAgencyName(2));
                } else {
                    map.put("hl2Desc", "");
                }
                if (depth == 4
                        && hrchy.getParentAgencyName(depth - 1) != null) {
                    map.put("hlCurrentDesc",
                            hrchy.getParentAgencyName(depth - 1));
                } else if (depth > 4
                        && hrchy.getParentAgencyName(depth - 1) != null) {
                    map.put("hlCurrentDesc",
                            "<b>.....</B> "
                            + hrchy.getParentAgencyName(depth - 1));
                } else {
                    map.put("hlCurrentDesc", "");
                }
            }
            if (HierarchyDisplay.NUMBER_OF_SEGMENTS == (depth + 1)) {
                map.put("showNextLevelButton", "");
            }

            request.setAttribute("data", data);
            request.setAttribute("map", map);

            logger.debug("Entered BrowseHierarchyAction .....");
            return mapping.findForward("browse_hierarchyLevels");
        }    // end of if for results found
        else {
            logger.debug("Entered BrowseHierarchyAction .....");
            return mapping.findForward("this");
        }
    }	     // end execute()


}    // end class
