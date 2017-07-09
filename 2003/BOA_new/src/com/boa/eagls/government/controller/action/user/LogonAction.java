/**
 * LogonAction class
 */
package com.boa.eagls.government.controller.action.user;

/**
 * <p>Title: LogonAction</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */

import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.user.PasswordDTO;
import com.boa.eagls.government.dto.user.UserHierarchyDTO;
import com.boa.eagls.government.dto.user.UserProfileDTO;
import com.boa.eagls.government.dto.user.UserRoleDTO;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.LoginFailedException;
import com.boa.eagls.government.service.userprofile.LogonService;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.util.DynMenuState;
import com.boa.eagls.government.util.ValidFunctions;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

/**
 * LogonAction class
 *
 *
 * @author
 * @version %I%, %G%
 */
public class LogonAction extends Action {

    // Set Logger
    private static Logger logger = Logger.getLogger(LogonAction.class);

    /**
     * Execute is the function called by NAS whenever user attempts to log in.
     * @return       int indicating whether execute was successful
     * (return value of 0) or not (any other value)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        logger.debug("LogonAction --> execute() --> START");
        request.getSession().setAttribute("htSessionData", new Hashtable());
        String next = "";

        try {
            String userid = (String) ((DynaActionForm) form).get("txt_username");    // request.getParameter("txt_username");
            String passwd = (String) ((DynaActionForm) form).get("txt_password");    // request.getParameter("txt_password");

//            request.getSession().

            if (null == userid || null == passwd || userid.length()==0 || passwd.length()==0){
                logger.info("LogonAction --> execute() --> Error : userId or pwd is null");
                request.setAttribute("errmsg", "UserID or Password not provided.");
                ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING,
                            new ActionError("heading.error.invalid.password"));
                    errors.add(Constants.ERR_MSG,
                            new ActionError("error.invalid.password"));

                next = "error";
                saveErrors(request, errors);

                return mapping.findForward(next);
            }
            EAGLSSession session = new EAGLSSession();
            LogonService logonService = new LogonService(null);
            UserProfileDTO userProfileDTO = logonService.login(userid,
                    passwd);    // ,htUserData);//user.loadUserProfile(userid);
            UserRoleDTO defaultRole =
                    userProfileDTO.getRole(userProfileDTO.getDefaultRoleName());
            session.setFunctionsLastModifiedDate(logonService.getUserFunctionLastModifiedDate(), request);

            // set values in session
            session.setUserID(userid, request);
            String currentRole = defaultRole.getRoleName();

            session.setCurrentRole(currentRole, request);
            String currentBaseRole = defaultRole.getCoreRoleName();

            session.setCurrentBaseRole(currentBaseRole, request);
            session.setRoleFunctionsStr(defaultRole.getRoleFunctionStr(),
                    request);
            ValidFunctions vf = new ValidFunctions();
            DynMenuState ds =
                    new DynMenuState(vf.parseRoleFunctions(session.getRoleFunctionsStr(request)));

            session.setDynamicMenu(ds.toString(), request);

            // place special values in session according to the current base role
            if (currentBaseRole.equals("AH")) {
                session.setCurrentAccount(defaultRole.getDefaultAccount(),
                        request);
            } else if (currentBaseRole.equals("GSA")
                    || currentBaseRole.equals("FMS")) {
                session.setCurrentHierarchy(defaultRole.getDefaultHierarchy(),
                        request);
            } else if (!currentBaseRole.equals("GCSU")) {
                HierarchyDTO[] hierarchy = defaultRole.getDefaultHierarchy();

                session.setCurrentHierarchy(hierarchy, request);
                UserHierarchyDTO[] uh =
                        defaultRole
                        .getUserHierarchies();    // roleService.getUserHierarchies();
                char[] tempArr =
                        uh[0].getProgramTypes().toCharArray();

                session.setCurrentProgramTypes(tempArr, request);
            }
            //added due to absolutely broken Pac's logic
            else if (currentBaseRole.equals("GCSU")) {
                HierarchyDTO[] hierarchy = defaultRole.getDefaultHierarchy();
                session.setCurrentHierarchy(hierarchy, request);
            }
            PasswordDTO pwd = userProfileDTO.getPassword();
            int numDays =
                    userProfileDTO.getPassword().getNoOfDaysToExpire();

            if ((numDays <= 0) || (pwd.getIsCurrentPwdDefault())) {
                next = "changePassword";
            } else if (numDays <= 7) {
                next = "changePasswordWarning";
            } else {
                next = "success";
            }
        } catch (NBException nbe) {
            logger.debug("LogonAction --> execute() -->  Exception " + nbe);
            ActionErrors errors = new ActionErrors();
            if (nbe instanceof com.boa.eagls.government.exceptions.LoginFailedException) {
                errors.add(Constants.ERR_HEADING,
                        new ActionError("heading.error.invalid.password"));
                errors.add(Constants.ERR_MSG,
                        new ActionError("error.invalid.password"));

            } else if (nbe
                    instanceof com.boa.eagls.government.exceptions.AccessDeniedException) {
                errors.add(Constants.ERR_HEADING,
                        new ActionError("heading.error.access.denied"));
                errors.add(Constants.ERR_MSG,
                        new ActionError("error.access.denied"));

            } else if (nbe
                    instanceof com.boa.eagls.government.exceptions.MaxPasswordAttemptsReachedException) {
                errors.add(Constants.ERR_HEADING,
                        new ActionError("heading.error.password.maxfailedcountreached"));

                errors.add(Constants.ERR_MSG,
                        new ActionError("error.password.maxfailedcountreached"));

            } else {
                errors.add(Constants.ERR_HEADING,
                        new ActionError("heading.error.login.failed"));
                errors.add(Constants.ERR_MSG,
                        new ActionError("error.login.failed"));

            }
            next = "error";
            saveErrors(request, errors);

        } catch (Exception e) {
            logger.error("LogonAction --> execute() -->  Exception ", e);
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING,
                    new ActionError("heading.error.login.failed"));
            errors.add(Constants.ERR_MSG,
                    new ActionError("error.login.failed"));
            saveErrors(request, errors);
            next = "error";
        }
        logger.debug("LogonAction --> execute() --> END");
        return mapping.findForward(next);
    }

}
