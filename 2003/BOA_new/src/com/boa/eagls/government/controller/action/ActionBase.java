/**
 * ActionBase
 */
package com.boa.eagls.government.controller.action;

import org.apache.struts.action.*;
import org.apache.log4j.Logger;

import javax.servlet.http.*;

import com.boa.eagls.government.statemgmt.EAGLSSession;

import java.util.Hashtable;
import java.util.Enumeration;
import java.net.URLEncoder;

/**
 * <p>Title: ActionBase Class </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public abstract class ActionBase extends Action
{
    private static final Logger logger= Logger.getLogger(ActionBase.class);

    /* Session Wrapper */
    protected EAGLSSession session;

    /**
     * Method execute This method is called by the Struts' ActionServlet
     * each time an action is performed in the application.  This
     * method calls the eaglsExecute method in every class that inherits
     * from the ActionBase class. This method is used to catch any and all
     * exceptions that were not caught elsewhere in the application.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws java.lang.Exception
     * @pre $none
     * @post $none
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception
    {
        ActionErrors errors = new ActionErrors();
        session = new EAGLSSession();

        try
        {


/* Checking for session expiry */





            if (request.getSession().getAttribute("htSessionData") == null)
            {
                return mapping.findForward("sessionExpired");
            }

            return (eaglsExecute(mapping, form, request, response));
        }
        catch (Exception e)
        {
            logger.error("error.unknown", e);
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.unknown"));
            saveErrors(request, errors);
            return (mapping.findForward("generalFailure"));
        }
    }

    /**
     * Method eaglsExecute
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws java.lang.Exception
     * @pre $none
     * @post $none
     */
    public abstract ActionForward eaglsExecute(ActionMapping mapping,
                                               ActionForm form,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws Exception;


    /**
     * Method declaration
     *
     *
     * @param req
     *
     * @return
     */
    public Hashtable copyUserValInParams(HttpServletRequest req)
    {
        Hashtable htTemp = new Hashtable();
        Enumeration enum = req.getParameterNames();

        while (enum.hasMoreElements())
        {
            String nextElement = (String) enum.nextElement();

            if (Character.isLowerCase(nextElement.charAt(0)))
            {
                if (req.getParameter(nextElement) != null)
                {
                    htTemp.put(nextElement, req.getParameter(nextElement));
                }
            }
        }
        return htTemp;
    }

    /**
     * Method declaration
     *
     *
     * @param map
     * @param guid
     * @param otherPrevParams
     * @param otherNextParams
     * @param startRecord
     * @param recordsPerPage
     * @param totalRecords
     * @param reviseGUID
     *
     * @exception Exception
     */
    public void mapMultiplePageSymbols(Hashtable map, String guid,
                                       Hashtable otherPrevParams,
                                       Hashtable otherNextParams,
                                       int startRecord, int recordsPerPage,
                                       int totalRecords,
                                       String reviseGUID) throws Exception
    {

            if (!(startRecord > 0))
            {
                map.put("showPreviousButton", "");
            }
            otherPrevParams.put("startRecord",
                    ""
                    + String.valueOf(startRecord
                    - recordsPerPage));
            map.put("previousHyperlink",
                    createHyperlink(guid, otherPrevParams));
            if ((totalRecords > recordsPerPage ? null : "") != null)
            {
                if (!(totalRecords > recordsPerPage))
                {
                    map.put("showPageNumbers", "");
                }
            }
            map.put("currentPageNumber",
                    String.valueOf(currentPage(startRecord, recordsPerPage)));
            map.put("totalPageNumber",
                    String.valueOf(numberOfPages(totalRecords,
                            recordsPerPage)));
            if ((totalRecords > (startRecord + recordsPerPage) ? null : "")
                    != null)
            {
                if (!(totalRecords > (startRecord + recordsPerPage)))
                {
                    map.put("showNextButton", "");
                }
            }
            otherNextParams.put("startRecord",
                    String.valueOf(startRecord + recordsPerPage));
            map.put("nextHyperlink", createHyperlink(guid, otherNextParams));
            if ((reviseGUID == null ? "" : null) != null)
            {
                if (reviseGUID == null)
                    map.put("showReviseButton", "");
            }
            map.put("reviseHyperlink",
                    createHyperlink(reviseGUID, (Hashtable) null));
    }

    /**
     * createHyperlink creates the string necessary to call an applogic from an HTML hyperlink.
     * @param		guid=guid of applogic to call, params=IValList of parameters
     * @return		String for hyperlink
     */
    public final String createHyperlink(String guid, Hashtable params)
    {
        StringBuffer hl = new StringBuffer("");

        hl.append(guid);
        int count = 0;

        if (params != null)
        {
            count = params.size();
        }
        if (count > 0)
        {
            String key, val;

            hl.append("?");
            Enumeration enum = params.keys();

            for (int i = 0; i < count; i++)
            {
                key = (String) enum.nextElement();    // params.getNextKey();
                val = (String) params.get(key);

                // val.replace(' ','+');
                String URLString = URLEncoder.encode(val);

                // logDebug("The string to be replaced with a special character
                //... ", "VAL = " +val + " URLSTring = " + URLString);
                // hl+=key+"="+val+(i<(count-1) ? "&" : "");
                hl.append(key);
                hl.append("=");
                hl.append(URLString);
                hl.append(i < (count - 1) ? "&" : "");
            }
        }

        String retVal = hl.toString();
        int lbIndex = 0;

        while ((lbIndex = retVal.indexOf("#")) != -1
                && lbIndex < retVal.length())
        {
            retVal = retVal.substring(0, lbIndex) + "%23"
                    + retVal.substring(lbIndex + 1);
        }
        return retVal;
    }

    /**
     * Method declaration
     *
     *
     * @param currentRecord
     * @param recordsPerPage
     *
     * @return
     */
    public int currentPage(int currentRecord, int recordsPerPage)
    {
        return (currentRecord + recordsPerPage) / recordsPerPage;
    }

    /**
     * Method declaration
     *
     *
     * @param totalRecords
     * @param recordsPerPage
     *
     * @return
     */
    public int numberOfPages(int totalRecords, int recordsPerPage)
    {
        int toReturn = 0;

        if (recordsPerPage != 0)
        {
            toReturn = (totalRecords + recordsPerPage - 1) / recordsPerPage;
        }
        return toReturn;
    }


}
