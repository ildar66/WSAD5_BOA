/**
 * EAGLSSession
 */
package com.boa.eagls.government.statemgmt;

/**
 * EAGLS Session definition
 * @author Steven Specht
 * @version $Revision: 1.5 $ $Modtime:   29 Aug 2000 10:07:48  $
 */

import com.boa.eagls.government.dao.*;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.util.*;

import java.util.Hashtable;
import java.util.Vector;

import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.util.UserProfileUtil;
import com.boa.eagls.government.util.DynMenuData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * EAGLSSession
 *
 *
 * @author
 * @version %I%, %G%
 */
public class EAGLSSession
{
    private static final Logger logger= Logger.getLogger(EAGLSSession.class);
    public Hashtable htSessionData = null;
    boolean dirty = false;

    /**
     * Constructs an EAGLSSession object with an ISession2 object.
     * @param   s   ISession2 object created by the system
     */
    public EAGLSSession()
    {
    }

    /**
     * Stores the user's ID in the session data.
     * @param   aUserID     String holding the user's unique ID
     */
    public void setUserID(String aUserID, HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        setDirty(true);
        if (htSessionData != null)
        {
            if (aUserID != null)
            {
                htSessionData.put("UserID", aUserID);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Retrieves the user ID associated with this session.
     * @return  String holding a user's ID
     */
    public String getUserID(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return "";
        }
        return (String) htSessionData.get("UserID");
    }

    /**
     * Stores the user's current role in the session data.
     * @param   aRole   String holding the name of a role to set
     */
    public void setCurrentRole(String aRole, HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            if (aRole != null)
            {
                htSessionData.put("CurrentRole", aRole);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Retrieves from session the user's current role name.
     * @return  String containing the current role name
     */
    public String getCurrentRole(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        String currentRole = (String) htSessionData.get("CurrentRole");

        return currentRole;
    }

    /**
     * Method declaration
     *
     *
     * @param req
     *
     * @return
     */
    public Hashtable getCurrentUserData(HttpServletRequest req)
    {
        Hashtable htUserData = new Hashtable();

        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
            String baseRole = getCurrentBaseRole(req);

            if (baseRole != null)
            {
                htUserData.put("currentRole", getCurrentRole(req));
            }
            String account = getCurrentAccount(req);

            if (account != null)
            {
                htUserData.put("currentAccount", account);
            }
            htUserData.put("currentBaseRole", baseRole);
            HierarchyDTO[] h = getCurrentHierarchy(req);

            if (h != null)
            {
                htUserData.put("currentHierarchy", h);
                logger.debug("user hierarchy " + h);
            }
            String roleType = UserProfileUtil.getRoleType(baseRole);

            if (roleType != null)
            {
                htUserData.put("currentRoleType", roleType);
            }
            char[] progTypes = getCurrentProgramTypes(req);

            if (progTypes != null)
            {
                htUserData.put("currentProgramTypes", progTypes);
            }
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
            return null;
        }
        catch (Exception ex)
        {
            logger.error("ClassCastException",  ex);
            return null;
        }
        return htUserData;
    }

    /**
     * Stores the current role's base role name in session data.
     * @param   aBaseRole   String holding the name of a base role to set
     */
    public void setCurrentBaseRole(String aBaseRole, HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            if (aBaseRole != null)
            {
                htSessionData.put("CurrentBaseRole", aBaseRole);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Retrieves from session the name of the base role for the current role.
     * @return  String containing the current base role name
     */
    public String getCurrentBaseRole(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        String base = (String) htSessionData.get("CurrentBaseRole");

        return base;
    }

    /**
     * Method declaration
     *
     *
     * @param functions
     * @param req
     */
    public void setCurrentRoleFunctions(String[] functions,
                                        HttpServletRequest req)
    {
//	setDirty(true);
//	if (htSessionData != null)
//	{
//	    if (functions != null)
//	    {
        req.getSession().setAttribute("currentRoleFunctions", functions);
//		req.getSession().setAttribute("htSessionData", htSessionData);
//	    }
//	}
    }

    /**
     * Stores the current individualaccount number in session.
     * @param   anAccount   <code>String</code> containing an individualaccount number
     */
    public void setCurrentAccount(String anAccount, HttpServletRequest req)
    {
        setDirty(true);
        if (htSessionData != null)
        {
            if (anAccount != null)
            {
                htSessionData.put("CurrentAccount", anAccount);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Changes the current session individualaccount and updates the database for Crystal Reports.
     * @param	account	a valid individualaccount under user's current role.
     * @exception	NBError	problem updating database.
     */
    public void changeCurrentAccount(String account, HttpServletRequest req)
            throws NBException
    {
        setCurrentAccount(account, req);
    }

    /**
     * Clear the current individualaccount number from session. Use this method when switching to
     * a role that isn't linked to an individualaccount number (i.e. any role except AH).
     */
    public void clearCurrentAccount(HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            htSessionData.remove("CurrentAccount");
            req.getSession().setAttribute("htSessionData", htSessionData);
        }
    }

    /**
     * Clear the current individualaccount number from session. Use this method when switching to
     * a role that isn't linked to an individualaccount number (i.e. any role except AH).
     */
    public void clearCurrentUserData(HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            htSessionData.remove("CurrentAccount");
            htSessionData.remove("currentHierarchy");
            htSessionData.remove("CurrentProgramTypes");
            htSessionData.remove("CurrentBaseRole");
            htSessionData.remove("CurrentRole");
            htSessionData.remove("DynamicMenu");
            htSessionData.remove("DynamicMenuState");
            req.getSession().removeAttribute("currentMenu");
            req.getSession().setAttribute("htSessionData", htSessionData);
        }
    }

    /**
     * Retrieves from session the individualaccount number the user is currently viewing.
     * @return  <code>String</code> containing an individualaccount number
     */
    public String getCurrentAccount(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        return (String) htSessionData.get("CurrentAccount");
    }

    private static final int MAX_H_LEVELS = 9;

    /**
     * Stores in session the current hierarchy level.
     * @param   aHierarchy  array of <code>int</code> no larger than EAGLSSession.MAX_H_LEVELS
     */
    public void setCurrentHierarchy(HierarchyDTO[] aHierarchy,
                                    HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            if (aHierarchy == null)
            {

                // create an invalid hierarchy
                HierarchyDTO[] tempHeri = new HierarchyDTO[1];

                tempHeri[0] = new HierarchyDTO();
                tempHeri[0].setLevel((short) 0);
                tempHeri[0].setNumber(-1);
                tempHeri[0].setDescription("");
                tempHeri[0].setAgencyName("");

                // put it in to the session
                htSessionData.put("currentHierarchy", tempHeri);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
            else
            {
                htSessionData.put("currentHierarchy", aHierarchy);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Changes the current session hierarchy and updates the database for Crystal Reports.
     * @param	hierarchy	a valid hierarchy under user's current role.
     * @exception	NBError	problem updating database.
     */
    public void changeCurrentHierarchy(HierarchyDTO[] hierarchy,
                                       HttpServletRequest req) throws NBException
    {
        setCurrentHierarchy(hierarchy, req);
    }

    /**
     * Method declaration
     *
     *
     * @param req
     *
     * @return
     */
    public String[] getCurretRoleFunctions(HttpServletRequest req)
    {
//	try
//	{
//	    htSessionData =
//		(Hashtable) req.getSession().getAttribute("htSessionData");
//	}
//	catch (ClassCastException ex)
//	{
//        logger.error("ClassCastException",  ex);
//	}
//	if (htSessionData == null)
//	{
//	    return null;
//	}
        return (String[]) req.getSession().getAttribute("currentRoleFunctions");
    }

    // Added to accomodate the dyamic menu and dynamic search functionalities (Solo)

    /**
     * Method declaration
     *
     *
     * @param req
     *
     * @return
     */
    public String getRoleFunctionsStr(HttpServletRequest req)
    {
//	htSessionData =
//	    (Hashtable) req.getSession().getAttribute("htSessionData");
//	if (null != htSessionData)
//	{
//	    Object tmp = htSessionData.get("currentRoleFunctionsStr");
//
//	    if (null == tmp)
//	    {
//		return "";
//	    }
//	    else
//	    {
//		return tmp.toString().trim();
//	    }
//	}
//	return "";

        return (String) req.getSession().getAttribute("currentRoleFunctionsStr");
    }

    /**
     * Method declaration
     *
     *
     * @param roleFunctions
     * @param req
     */
    public void setRoleFunctionsStr(String roleFunctions,
                                    HttpServletRequest req)
    {
//	htSessionData =
//	    (Hashtable) req.getSession().getAttribute("htSessionData");
//	if (null != htSessionData)
//	{
        req.getSession().setAttribute("currentRoleFunctionsStr", roleFunctions);
//	    req.getSession().setAttribute("htSessionData", htSessionData);
//	}
    }

    /**
     * Retrieves from session the current hierarchy.
     * @return  array of <code>int</code> with each element the code of a hierarchy level
     */
    public HierarchyDTO[] getCurrentHierarchy(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        HierarchyDTO[] tempHierarchy =
                (HierarchyDTO[]) htSessionData.get("currentHierarchy");

        if (tempHierarchy == null)
        {
            return null;
        }
        else if (tempHierarchy.length == 1)
        {
            if (tempHierarchy[0].getNumber() == -1)
            {
                return null;
            }
        }
        return tempHierarchy;
    }

    /**
     * Clears the current hierarchy from session. Use this when changing to a role
     * that does not have a hierarchy associated to it (i.e. GCSU & AH).
     */
    public void clearCurrentHierarchy(HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            htSessionData.remove("currentHierarchy");
            req.getSession().setAttribute("htSessionData", htSessionData);
        }
    }

    /**
     * Stores in session the program types associated with the current hiearchy.
     * @param   someProgramTypes   String holding program types
     */
    public void setCurrentProgramTypes(char[] programTypes,
                                       HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            if (programTypes != null)
            {
                htSessionData.put("CurrentProgramTypes",
                        new String(programTypes));
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Clears the current program types from session.  Use this when switching
     * to a role that has no program types (i.e. GCSU, GSA, FMS, AH).
     */
    public void clearCurrentProgramTypes(HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            htSessionData.remove("CurrentProgramTypes");
            req.getSession().setAttribute("htSessionData", htSessionData);
        }
    }

    /**
     * Retrieves from session the program types for the current hierarchy.
     * @return  <code>String</code> containing program types
     */
    public char[] getCurrentProgramTypes(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        String strPT = (String) htSessionData.get("CurrentProgramTypes");

        if (strPT == null)
        {
            return null;
        }
        else
        {
            return strPT.toCharArray();
        }
    }

    /**
     * Stores in session the dynamic menu data and state.
     * @param   DynMenuData   structure holding menu and state
     */
    public void setDynamicMenu(DynMenuData menu, HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return;
        }
        String dynMenuBuf = DynMenuData.serialize(menu);

        if (dynMenuBuf != null)
        {
            htSessionData.put("DynamicMenu", dynMenuBuf);
            req.getSession().setAttribute("htSessionData", htSessionData);
        }
    }

    /**
     * Method declaration
     *
     *
     * @param dirty
     */
    public void setDirty(boolean dirty)
    {
        this.dirty = dirty;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean isDirty()
    {
        return dirty;
    }

    /**
     * Method declaration
     *
     */
    public void printDebugInfo()
    {
    }

    /**
     * Retrieves from session dynamic menu data structure.
     * @return  DynMenuData containing menu items and state.
     */
    public String getDynamicMenu(HttpServletRequest req)
    {
        htSessionData =
                (Hashtable) req.getSession().getAttribute("htSessionData");
        if (htSessionData == null)
        {
            return null;
        }
        String dynMenu = (String) htSessionData.get("DynamicMenu");

        return dynMenu;
    }

    /**
     * Method declaration
     *
     *
     * @param menu
     * @param req
     */
    public void setDynamicMenu(String menu, HttpServletRequest req)
    {
        htSessionData =
                (Hashtable) req.getSession().getAttribute("htSessionData");
        setDirty(true);
        if (htSessionData == null)
        {
            return;
        }
        if (menu != null)
        {
            htSessionData.put("DynamicMenu", menu);
            req.getSession().setAttribute("htSessionData", htSessionData);
        }
    }

    /**
     * Retrieves from session dynamic menu state string.
     * @return  String containing menu state.
     */
    public String getDynamicMenuState(HttpServletRequest req)
    {
        htSessionData =
                (Hashtable) req.getSession().getAttribute("htSessionData");
        if (htSessionData == null)
        {
            return null;
        }
        return (String) htSessionData.get("DynamicMenuState");
    }

    /**
     * Stores in session the dynamic menu state.
     * @param   String   string containing menu state
     */

    // public void setDynamicMenuState(String menu, HttpServletRequest req) {
    // try {
    // htSessionData = (Hashtable) req.getSession().getAttribute(
    // "htSessionData");
    // }
    // catch (ClassCastException ex) {
//    logger.error("ClassCastException",  ex);
    // }
    //
    // setDirty(true);
    // if (htSessionData == null) {
    // return;
    // }
    // if (menu != null) {
    // htSessionData.put("DynamicMenuState", menu);
    // req.getSession().setAttribute("htSessionData", htSessionData);
    // }
    // }
    //
    // /**
    // * Retrieves from session dynamic menu data structure.
    // * @return  DynMenuData containing menu items and state.
    // */
    // public DynMenuData getDynamicMenu(HttpServletRequest req) {
    // try {
    // htSessionData = (Hashtable) req.getSession().getAttribute(
    // "htSessionData");
    // }
    // catch (ClassCastException ex) {
//    logger.error("ClassCastException",  ex);
    // }
    //
    // if (htSessionData == null) {
    // return null;
    // }
    // String serDynMenu = (String)htSessionData.get("DynamicMenu");
    // return DynMenuData.deserialize(serDynMenu);
    // }
    //
    // /**
    // * Retrieves from session dynamic menu state string.
    // * @return  String containing menu state.
    // */
    // public String getDynamicMenuState(HttpServletRequest req) {
    // try {
    // htSessionData = (Hashtable) req.getSession().getAttribute(
    // "htSessionData");
    // }
    // catch (ClassCastException ex) {
//    logger.error("ClassCastException",  ex);
    // }
    //
    // if (htSessionData == null) {
    // return null;
    // }
    // return (String)htSessionData.get("DynamicMenuState");
    // }

    /**
     * Do not call directly - call similarly named method in EAGLSBaseAL
     * Stores in session an application defined string for temporary storage.
     * Can be data, serialized object, etc.  This is meant to be called
     * from the base applogic, which follows up the call with a saveSession.
     * WARNING: Can adversely impact the stateless nature of HTML.
     * @param   String	the data to be stored
     */
    public void writeScratchpad(String s, HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
//            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            if (s != null)
            {
                htSessionData.put("Scratchpad", s);
                req.getSession().setAttribute("htSessionData", htSessionData);
                setDirty(true);
            }
            else
            {
                htSessionData.remove("Scratchpad");
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Method declaration
     *
     *
     * @param s
     * @param req
     */
    public void writeScratchpad(byte[] s, HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            if (s != null)
            {
                htSessionData.put("Scratchpad", s);
                req.getSession().setAttribute("htSessionData", htSessionData);
                setDirty(true);
            }
            else
            {
                htSessionData.remove("Scratchpad");
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Do not call directly - call similarly named method in EAGLSBaseAL
     * Retrieves from session the contents of the scratchpad.  This is meant
     * to be called from the base applogic, which follows up the call with a saveSession.
     * WARNING: Can adversely impact the stateless nature of HTML.
     * @return  String	the data from scratchpad
     */
    public String readScratchpad(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        return (String) htSessionData.get("Scratchpad");
    }

    /**
     * Method declaration
     *
     *
     * @param byteArray
     * @param req
     *
     * @return
     */
    public byte[] readScratchpad(boolean byteArray, HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        return (byte[]) htSessionData.get("Scratchpad");
    }

    /**
     * Do not call directly - call similarly named method in EAGLSBaseAL Stores in session a serialized large result set.
     * WARNING: Can adversely impact the stateless nature of HTML.
     * @param   l	the large result set
     */

    public void setLaRS(LaRS l, HttpServletRequest req)
    {

        logger.debug("setting lars in session 1");
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData != null)
        {
            if (l != null)
            {
                logger.debug("setting lars in session 2");
                byte[] larsByte = LaRS.serialize(l);
                logger.debug("setting lars in session 3" + larsByte.length);
                if (larsByte != null)
                    htSessionData.put("LaRs", larsByte);

                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Do not call directly - call similarly named method in EAGLSBaseAL Retrieves from session the large result set.
     * WARNING: Can adversely impact the stateless nature of HTML.
     * @return  LaRS	the large result set
     */


    public LaRS getLaRS(HttpServletRequest req)
    {
        logger.debug("getting lars form session 1");
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        if (htSessionData == null)
        {
            return null;
        }


        byte[] sLaRS = (byte[]) htSessionData.get("LaRs");
        logger.debug("htSessionData======" + htSessionData);
        logger.debug("getting lars form session 2" + sLaRS);
        logger.debug(sLaRS != null ? LaRS.deserialize(sLaRS) : null);
        return sLaRS != null ? LaRS.deserialize(sLaRS) : null;
    }

    /**
     * Do not call directly - call similarly named method in EAGLSBaseAL Stores in session a serialized large result set.
     * This over-loaded method is created to handle the situation in which multiple large result sets are written to session.
     * instead of all writing to the same variable "LaRS" we write them under unique sesstion variables passed in as argument.
     * Stores in session a large result set. WARNING: Can adversely impact the stateless nature of HTML.
     * @param   l	the large result set fileName session variable name to write the lars data to.
     */
    public void setLaRS(LaRS l, String fileName, HttpServletRequest req)
    {
        setDirty(true);
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex);
        }
        byte[] larsByte = LaRS.serialize(l);

        if (htSessionData != null)
        {
            if (fileName != null && larsByte != null)
            {
                htSessionData.put(fileName, larsByte);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Do not call directly - call similarly named method in EAGLSBaseAL
     * Retrieves from session the large result set from the session based upon the variable name sent in as argument.
     * WARNING: Can adversely impact the stateless nature of HTML.
     * @return  LaRS	the large result set
     */
    public LaRS getLaRS(String fileName, HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error(ex.getMessage(), ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        byte[] sLaRS = (byte[]) htSessionData.get(fileName);

        return sLaRS != null ? LaRS.deserialize(sLaRS) : null;
    }

    /**
     * Method declaration
     *
     *
     * @param currentMenu
     * @param req
     */
    public void setCurrentMenu(Vector currentMenu, HttpServletRequest req)
    {

        // setDirty(true);
        // try {
        // htSessionData = (Hashtable) req.getSession().getAttribute(
        // "htSessionData");
        // }
        // catch (ClassCastException ex) {
//        logger.error(ex.getMessage(), ex);
        // }
        //
        // if (htSessionData != null) {
        // if (currentMenu != null) {
        // htSessionData.put("currentMenu", currentMenu);
        // req.getSession().setAttribute("htSessionData", htSessionData);
        // }
        // }
        req.getSession().setAttribute("currentMenu", currentMenu);
    }

    /**
     * Method declaration
     *
     *
     * @param req
     *
     * @return
     */
    public Vector getCurrentMenu(HttpServletRequest req)
    {
        return (Vector) req.getSession().getAttribute("currentMenu");
    }

    /**
     * Method declaration
     *
     *
     * @param cluster
     * @param req
     */
    public void setClusterName(String cluster, HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error(ex.getMessage(), ex);
        }
        setDirty(true);
        if (htSessionData != null)
        {
            if (cluster != null)
            {
                htSessionData.put("ClusterName", cluster);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    /**
     * Method declaration
     *
     *
     * @param req
     *
     * @return
     */
    public String getClusterName(HttpServletRequest req)
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error(ex.getMessage(), ex);
        }
        if (htSessionData == null)
        {
            return null;
        }
        return (String) htSessionData.get("ClusterName");
    }

    /**
     * Method declaration
     *
     *
     * @param aRole
     * @param req
     *
     * @exception NBException
     */
    public void changeCurrentRole(String aRole,
                                  HttpServletRequest req) throws NBException
    {
        try
        {
            htSessionData =
                    (Hashtable) req.getSession().getAttribute("htSessionData");
        }
        catch (ClassCastException ex)
        {
            logger.error("ClassCastException",  ex); ;
        }
        setDirty(true);
        if (htSessionData != null)
        {
            if (aRole != null)
            {
                htSessionData.put("CurrentRole", aRole);
                req.getSession().setAttribute("htSessionData", htSessionData);
            }
        }
    }

    public void setTitle1(String title1, HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (null != session)
        {
            session.setAttribute("title_1", title1);
        }
    }

    public String getTitle1(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (null != session)
        {
            Object tmp = session.getAttribute("title_1");
            if (null == tmp)
            {
                return "";
            }
            else
            {
                return tmp.toString().trim();
            }
        }
        return "";
    }

    public void setTitle2(String title2, HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (null != session)
        {
            session.setAttribute("title_2", title2);
        }
    }

    public String getTitle2(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (null != session)
        {
            Object tmp = session.getAttribute("title_2");
            if (null == tmp)
            {
                return "";
            }
            else
            {
                return tmp.toString().trim();
            }
        }
        return "";
    }

    public void setSearchType(String searchType, HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (null != session)
        {
            session.setAttribute("searchType", searchType);
        }
    }

    public String getSearchType(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (null != session)
        {
            Object tmp = session.getAttribute("searchType");
            if (null == tmp)
            {
                return "";
            }
            else
            {
                return tmp.toString().trim();
            }
        }
        return "";
    }

    public BrowseHierarchyFields getBrowseHierarchy(HttpServletRequest req)
    {
        HttpSession session = req.getSession();
        if (null != session)
        {
            return (BrowseHierarchyFields)session.getAttribute("browseHierarchyFields");
        }
        return null;
    }


    public String getFunctionsLastModifiedDate(HttpServletRequest req)
    {
        return (String) req.getSession().getAttribute("menuLastModifiedDate");
    }

    public void setFunctionsLastModifiedDate(String date, HttpServletRequest req)
    {
        req.getSession().setAttribute("menuLastModifiedDate", date);
    }


}
