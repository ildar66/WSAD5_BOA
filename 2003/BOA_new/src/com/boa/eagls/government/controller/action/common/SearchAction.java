package com.boa.eagls.government.controller.action.common;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.Constants;

import com.boa.eagls.government.service.userprofile.HierarchyService;
import com.boa.eagls.government.service.userprofile.UserAccountService;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.controller.action.transactiondata.individualstatement.Forwards;
import com.boa.eagls.government.controller.action.transactiondata.individualstatement.Actions;


import java.util.Properties;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: richardp
 * Date: May 26, 2003
 * Time: 3:59:24 PM
 * To change this template use Options | File Templates.
 */
public class SearchAction extends Action
{

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception
    {
        String searchType = request.getParameter(Constants.SEARCH_PARAM_SEARCHTYPE);
        request.setAttribute(Constants.SEARCH_PARAM_SEARCHTYPE, searchType);
        request.setAttribute(Constants.HEADER_HEADING,
                Constants.HEADER_HEADING + "." + searchType);
        request.setAttribute(Constants.HEADER_SUBHEADING,
                Constants.HEADER_SUBHEADING + "." + searchType);


        EAGLSSession session = new EAGLSSession();
        HierarchyDTO hierarchyDTO[] =
                session.getCurrentHierarchy(request);
        HierarchyDisplay hierDisplay;
        HierarchyService hierarchyService = new HierarchyService(null);
        UserAccountService userAccountService = new UserAccountService(null);

        if (!session.getCurrentBaseRole(request).equals("GCSU"))
        {
            int[] hierarchy =
                    new HierarchyDisplay(hierarchyDTO).getValues();
            String hierarchyNbr =
                    hierarchyService
                    .getHierarchyNumber(hierarchy);

            hierDisplay =
                    userAccountService
                    .getHierarchyAgencyNames(new Integer(hierarchyNbr)
                    .intValue());
            for (int i = 0; i < hierDisplay.NUMBER_OF_SEGMENTS; i++)
            {
                hierarchyDTO[i]
                        .setDescription(hierDisplay.getAgencyName(i));
            }

        }
        //todo replace POC - ой functionnality

//        logger.debug("Seach type --> " + searchType);
        if ("individualStatement".equalsIgnoreCase(searchType)) {
            return mapping.findForward(Forwards.SEARCH_INDIVIDUAL_STATMENT_FORWARD);
        }

        //todo replace POC - ой functionnality
        return mapping.findForward("success");
    }

}
