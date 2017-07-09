/**
 * LogonService
 */
package com.boa.eagls.government.service.userprofile;

import com.boa.eagls.government.service.ServiceBase;
import com.boa.eagls.government.dto.user.UserProfileDTO;
import com.boa.eagls.government.dto.user.UserProfileDTO;
import com.boa.eagls.government.dto.user.UserRoleDTO;
import com.boa.eagls.government.dto.user.UserRoleDTO;
import com.boa.eagls.government.dto.user.PasswordDTO;
import com.boa.eagls.government.dto.user.PasswordDTO;
import com.boa.eagls.government.dao.RoleDAO;
import com.boa.eagls.government.dao.UserProfileDAO;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.util.UserProfileUtil;
import org.apache.log4j.Logger;

import java.util.Hashtable;

/**
 * LogonService
 *
 *
 * @author
 * @version %I%, %G%
 */
public class LogonService extends ServiceBase
{
    private static Logger logger =Logger.getLogger(LogonService.class);


    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public LogonService(java.sql.Connection connection)
    {
        setConnection(connection);
    }

    /**
     * @param userId
     * @param aPassword
     * @description  load password info of this userId load general info of user, validate password
     * returns userProfile Object if valid user otherwise throws exception
     * @return
     * @throws NBException
     */
    public UserProfileDTO login(String userId,
                                String aPassword) throws NBException
    {
        logger.debug("LogonService --> login() --> START");
        PasswordDTO pwdDTO = null;
        UserProfileDTO userProfileDTO = null;
        UserProfileDAO userProfileDAO = null;

//            if (null == userId || null == aPassword || userId.length()==0 || aPassword.length()==0)
//            {
//                throw new LoginFailedException("UserId or Password is not valid!");
//            }
            userProfileDAO = new UserProfileDAO(getConnection());
            pwdDTO = userProfileDAO.getPassword(userId);
            userProfileDTO = userProfileDAO.getUserProfile(userId);
            if (null == pwdDTO || null == userProfileDTO)
            {
                throw new LoginFailedException("UserId or Password is noit valid!");
            }
            userProfileDTO.setPassword(pwdDTO);
            if (userProfileDTO.getStatus().equals("I"))
            {
                throw new AccessDeniedException();
            }

            // Check if the user's setup status is "A" ie approved
            if (userProfileDTO.getSetupStatus() != null
                    && !userProfileDTO.getSetupStatus().equals("A"))
            {
                throw new LoginFailedException("UserId or Password is not valid!");
            }

            // ****this was included in DAO in old App I think it shud be in Service *****//
            int isValid = isPasswordValid(pwdDTO, aPassword,
                    userProfileDAO);
            String userRole = "";

            if (isValid == Constants.DEFAULT_PASSWORD)
            {
                pwdDTO.setIsCurrentPwdDefault(true);
            }
            userRole = userProfileDTO.getDefaultRoleName();
            RoleDAO roleDAO = new RoleDAO(getConnection());
            UserRoleDTO roleDTO = new UserRoleDTO();

            roleDTO = roleDAO.loadUserRole(userId, userRole);
            roleDTO.setRoleAttribute(UserProfileUtil.getRoleType(userRole));
            roleDTO
                    .setRoleFunctionsStr(userProfileDAO
                    .getUserFunctions(userRole));
            userProfileDTO.addRole(roleDTO);
        logger.debug("LogonService --> login() --> END");
        return userProfileDTO;
    }

    /**
     * @param password
     * @param aPasswd
     * @param userProfileDAO
     * @description   Check if the password is valid
     * @return
     * @throws NBException
     */
    private int isPasswordValid(PasswordDTO password, String aPasswd,
                                UserProfileDAO userProfileDAO) throws NBException
    {
        logger.debug("LogonService --> isPasswordValid() --> START");
        int toReturn = 0;

        if (checkPassword(password, aPasswd))
        {
            if (password.getFailedCount()
                    < Constants.PASSWD_MAX_FAILED_LOGINS)
            {    // Check for the initial value. If

                // failedCount is 3 user access will be denied
                userProfileDAO.resetSessionDefaults(password.getUserID());
                //password.setFailedCount(0);
                if (isDefault(password, aPasswd, userProfileDAO))
                {
                    toReturn = Constants.DEFAULT_PASSWORD;
                }
                else
                {
                    toReturn = Constants.SUCCESSFUL;
                }
            }
            else
            {
                throw new MaxPasswordAttemptsReachedException();
            }
        }
        else
        {	 // password check failed; increment failed attempts

            userProfileDAO.incFailedPasswdAttempts(password.getUserID());
//            System.out.println("LogonService --> isPasswordValid() --> failed count" + password.getFailedCount());
            if (password.getFailedCount()
                    >= Constants.PASSWD_MAX_FAILED_LOGINS)
            {
                throw new MaxPasswordAttemptsReachedException();
            }
            else
            {
                throw new LoginFailedException();
            }
        }
        logger.debug("LogonService --> isPasswordValid() --> END");
        return toReturn;
    }

    /**
     * encrypt the given password then match it with the password fetched from database
     * @param password
     * @param s
     * @return
     * @throws NBException
     */
    private boolean checkPassword(PasswordDTO password,
                                  String s) throws NBException
    {
        logger.debug("LogonService --> checkPassword() --> START");
        com.boa.eagls.government.encryption.RIPEMD160State encryptor =
                new com.boa.eagls.government.encryption.RIPEMD160State();
        char[] hash =
                encryptor.encrypt(s.toCharArray());

        logger.debug("LogonService --> checkPassword() --> END");
        return password.getPassword().equals(new String(hash));
    }

    /**
     * check if this pwd is equal to the default password fetched from the database
     * @param pwDTO
     * @param password
     * @param userPrfoileDAO
     * @return
     * @throws NBException
     */
    private boolean isDefault(PasswordDTO pwDTO, String password,
                              UserProfileDAO userPrfoileDAO) throws NBException
    {
        logger.debug("LogonService --> isDefault() --> START");
        String currentDefault =
                userPrfoileDAO.getDefaultPassword(pwDTO.getUserID());
        String userInitial =
                userPrfoileDAO.getInitialPassword(pwDTO.getUserID());
        boolean isDefault = false;

        if ((currentDefault != null) || (userInitial != null))
        {
            if (currentDefault.equals(password)
                    || userInitial.equals(password))
            {
                isDefault = true;
            }
        }
        logger.debug("LogonService --> isDefault() --> END");
        return isDefault;
    }

    public String getUserFunctionLastModifiedDate() throws NBException
    {
        UserProfileDAO userProfileDAO = new UserProfileDAO(getConnection());
        return userProfileDAO.getUserFunctionsLastModifiedDate();
    }

}
