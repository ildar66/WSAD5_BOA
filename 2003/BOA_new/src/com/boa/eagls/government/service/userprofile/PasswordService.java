/**
 * PasswordService
 */
package com.boa.eagls.government.service.userprofile;

import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.exceptions.*;

import java.util.*;

import com.boa.eagls.government.dao.*;
import com.boa.eagls.government.service.ServiceBase;
import com.boa.eagls.government.encryption.*;

/**
 * PasswordService
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class PasswordService extends ServiceBase
{
    PasswordDTO passwordDTO;

    // -IPasswordDAO passwordDAO;
    // max number of failed login attempts allowed
    public final static short PASSWD_MAX_FAILED_LOGINS = 3;
    public static final short RESET_FAILED_PASSWORD_ATTEMPTS = 4;
    public static final short INVALID_USERID_PASSWORD = 5;
    public static final short ACCESS_DENIED = 6;
    public static final short DEFAULT_PASSWORD = 7;
    public static final short SUCCESSFUL = 8;
    public static final short BAD_OLD_PASSWORD = 9;
    public static final short BAD_FORMATTED_NEWPASSWORD = 10;
    public static final short STORED_PROCEDURE_ERROR = 11;
    public static final short NEW_PASSWORD_USED_PREVIOUSLY = 12;
    public static String defaultPassword =
            "NSCR60";    // the default password
    public static String initialPassword = "SDK659";

    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public PasswordService(java.sql.Connection connection)
    {
        setConnection(connection);
        passwordDTO = new PasswordDTO();

        // passwordDAO = (IPasswordDAO) DAOFactory.getInstance("com.boa.eagls.government.dao.UserProfileDAO");
    }

    /**
     * Method declaration
     *
     *
     * @param passwordDTO
     */
    public void create(PasswordDTO passwordDTO)
    {
        this.passwordDTO = passwordDTO;
        setStateNew();
    }

    /**
     * Method declaration
     *
     *
     * @param aUserID
     *
     * @exception NBException
     */
    public void load(String aUserID) throws NBException
    {

        // --pwd.loadPassword(aUserID, this.password);
        // -- passwordDAO.loadPassword(aUserID,passwordDTO);
        setStateCurrent();
    }

    /**
     * Method declaration
     *
     */
    public void save()
    {

        // Save this object.
        if (getStateCurrent() == BO_STATE_NEW)
        {

            // not implemented.
        }
        else if (getStateCurrent() == BO_STATE_DIRTY)
        {

            // not implemeneted.
        }
        setStateCurrent();
    }

    /**
     * Method declaration
     *
     *
     * @param s
     *
     * @return
     */
    private boolean checkPassword(String s)
    {

        // ENCRYPTION
        RIPEMD160State encryptor = new RIPEMD160State();
        char[] hash = encryptor.encrypt(s.toCharArray());

        return passwordDTO.getPassword().equals(new String(hash));
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getUserID()
    {
        return new String(passwordDTO.getUserID());
    }

    /**
     * Method declaration
     *
     *
     * @param aUserID
     */
    public void setUserID(String aUserID)
    {
        passwordDTO.setUserID(validateString(aUserID));
    }

    /**
     * Method declaration
     *
     *
     * @param anOldPassword
     * @param aNewPassword
     *
     * @return
     *
     * @exception NBException
     */
    public short changePassword(String anOldPassword,
                                String aNewPassword) throws NBException
    {
        if (hasANumericChar(aNewPassword) && aNewPassword.length() >= 7)
        {
            if (checkPassword(anOldPassword))
            {

                // ENCRYPTION
                RIPEMD160State encryptor = new RIPEMD160State();
                char[] hash =
                        encryptor.encrypt(aNewPassword.toCharArray());

                try
                {

                    // ENCRYPTION
                    // --passwordDAO.changePassword(userID, new String(hash));
                    // Remove Change of Seagate Password
                    changeCIPassword(passwordDTO.getUserID(), aNewPassword);

                    // Add change of Oracle Password only if Reports Access is granted
                    // Has a cluster name
                    try
                    {
                        setOraclePassword(passwordDTO.getUserID(),
                                aNewPassword);
                    }
                    catch (NBException e)
                    {
                        throw new NBError(e.getMessage());
                    }
                }
                catch (NBException e)
                {
                    throw new NBError(e.getDetailedMessage());
                }
                return SUCCESSFUL;
            }
            else
            {
                throw new NBError("PLS_E0009::The old password entered did not match the one in the database");
            }
        }
        else
        {
            return BAD_FORMATTED_NEWPASSWORD;
        }
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     * @param passwd
     *
     * @exception NBException
     */
    public void resetPassword(String userID, String passwd) throws NBException
    {

        // ENCRYPTION
        RIPEMD160State encryptor = new RIPEMD160State();
        char[] hash = encryptor.encrypt(passwd.toCharArray());

        // --passwordDAO.resetPassword(userID, new String(hash));
    }

    /**
     * Method declaration
     *
     *
     * @param aPassword
     */
    public void setPassword(String aPassword)
    {
        passwordDTO.setPassword(validateString(aPassword));
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Vector getPasswordHistory()
    {
        return passwordDTO.getPasswordHistory();
    }

    /**
     * Method declaration
     *
     *
     * @param aPasswordHistory
     */
    public void setPasswordHistory(Vector aPasswordHistory)
    {
        passwordDTO.setPasswordHistory(aPasswordHistory);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Date getExpiryDate()
    {
        return new Date(passwordDTO.getExpiryDate().toString());
    }

    /**
     * Method declaration
     *
     *
     * @param aDate
     */
    public void setExpiryDate(Date aDate)
    {
        passwordDTO.setExpiryDate(validateDate(aDate));
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getFailedCount()
    {
        return passwordDTO.getFailedCount();
    }

    /**
     * Method declaration
     *
     *
     * @param count
     */
    public void setFailedCount(int count)
    {
        passwordDTO.setFailedCount(count);
    }

    /**
     * Method declaration
     *
     *
     * @param count
     */
    public void setFailedCount(Integer count)
    {
        passwordDTO.setFailedCount(validateInteger(count));
    }

    /**
     * Method declaration
     *
     *
     * @param aPasswd
     *
     * @return
     */
    public int login(String aPasswd)
    {
        if (checkPassword(aPasswd))
        {
            if (passwordDTO.getFailedCount() < PASSWD_MAX_FAILED_LOGINS)
            {    // Check for the initial value. If

                // failedCount is 3 user access will be denied
                try
                {
                    if (false)
                    {
                        throw new NBError("Never Thrown!");

                        // -- change the above line with below line
                        // -- passwordDAO.resetSessionDefaults(userID);
                    }
                }
                catch (NBException e)
                {
                    return ACCESS_DENIED;
                }
                passwordDTO.setFailedCount(0);
                if (isDefault(aPasswd))
                {
                    return DEFAULT_PASSWORD;
                }
                return SUCCESSFUL;
            }
            else
            {
                return ACCESS_DENIED;
            }
        }
        else
        {	 // password check failed; increment failed attempts
            try
            {
                if (false)
                {
                    throw new NBError(" Remove it when uncomment line below");

                    // --pwd.incFailedPasswdAttempts(userID);
                }
            }
            catch (NBException e)
            {
            }
            passwordDTO.setFailedCount(passwordDTO.getFailedCount() + 1);
            if (passwordDTO.getFailedCount() >= PASSWD_MAX_FAILED_LOGINS)
            {
                return ACCESS_DENIED;
            }
            else
            {
                return INVALID_USERID_PASSWORD;
            }
        }
    }

    /**
     * This Method gets the No of Days to Expire the password
     * @return NoOfDaysToExpire
     */
    public int getNoOfDaysToExpire() throws NBException
    {
        Date expDate = getExpiryDate();
        Date curDate = new Date();
        int noOfDaysToExpire = (int) ((expDate.getTime() - curDate.getTime())
                / (24 * 60 * 60 * 1000));

        if (noOfDaysToExpire < 0)
        {
            noOfDaysToExpire = 0;
        }
        return noOfDaysToExpire;
    }

    /**
     * Checks if the password is a default Password
     * @Param    String        a password else returns false
     * @returns  boolean       if the password is default then returns true else returns false
     */
    public boolean isDefault(String password)
    {
        String currentDefault = defaultPassword;
        String userInitial =
                "";    // --passwordDAO.getInitialPassword(getUserID());

        if ((currentDefault != null) || (userInitial != null))
        {
            if (currentDefault.equals(password)
                    || userInitial.equals(password))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    /**
     * Method declaration
     *
     *
     * @param str
     *
     * @return
     */
    public boolean hasANumericChar(String str)
    {
        int len = 0;

        while (len < str.length())
        {
            if (Character.isDigit(str.charAt(len)))
            {
                return true;
            }
            else
            {
                len += 1;
            }
        }
        return false;
    }

    /**
     * Returns the system default password. The system default password is assigned to a user when a supervisor
     * resets their password. This was modified to be retrieved from the Database for Change Request 265.
     * @return the system default password.
     */
    public static String getDefaultPassword()
    {
        String pass = defaultPassword;

        if ((pass == null) || (pass.equals("")))
        {
            pass = defaultPassword;
        }
        return pass;
    }

    /**
     * Returns the system initial password. The system initial password is the password automatically assigned to a new user
     * This was modified to be retrieved from the Database for Change Request 265.
     * @return	the  system initial password.
     */
    public static String getInitialPassword()
    {
        String pwd = initialPassword;

        if ((pwd == null) || (pwd.equals("")))
        {
            pwd = initialPassword;
        }
        return pwd;
    }

    /**
     * Returns the user's initial password. The user's initial password is the password automatically assigned to
     * a new user when they are setup. This was modified to be retrieved from the Database for Change Request 265.
     * @return	the user's initial password.
     */
    public static String getInitialPassword(String userID)
    {
        return initialPassword;
    }

    /**
     * Encrypts a given string passed in
     * @param String password
     * @return	the encrypted password.
     */
    public static String encryptPassword(String password)
    {
        RIPEMD160State encryptor = new RIPEMD160State();

        return new String(encryptor.encrypt(password.toCharArray()));
    }

    /**
     * Inform Crystal Info to change password.
     * @param	userID	the user to change.
     * @param	newPasswd	the new password to save.
     * @exception	NBException	failed to save new password to database.
     */
    public void changeCIPassword(String userID,
                                 String newPasswd) throws NBException
    {

        // NOT IMPLEMENTED.
        return;
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     *
     * @return
     *
     * @exception NBException
     */
    public static String retrieveReportsPassword(String userID)
            throws NBException
    {
        String pwd =
                "Z";    // Some db need to have a char as the first character of the password.
        char[] idchars = userID.toCharArray();

        for (int i = 0; i < 4; i++)
        {
            pwd += String.valueOf((Character.getNumericValue(idchars[i])
                    + 2));
        }
        for (int j = 4; j < idchars.length; j++)
        {
            pwd += String.valueOf((Character.getNumericValue(idchars[j])
                    + 4));
        }
        return pwd;
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     * @param pass
     *
     * @exception NBException
     */
    public static void setOraclePassword(String userID,
                                         String pass) throws NBException
    {

        // --PasswordDAO pwd = new PasswordDAO();
        // --pwd.setOraclePassword(userID, pass);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getPassword()
    {
        return passwordDTO.getPassword();
    }

}
