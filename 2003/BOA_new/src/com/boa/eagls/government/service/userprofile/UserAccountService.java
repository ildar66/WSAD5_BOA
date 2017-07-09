/**
 * UserAccountService
 */
package com.boa.eagls.government.service.userprofile;

import com.boa.eagls.government.service.ServiceBase;
import com.boa.eagls.government.util.Constants;

import java.util.Hashtable;

import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dao.*;

import java.sql.Connection;

/**
 * User Account Service - Concrete Class
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class UserAccountService extends ServiceBase {
    private UserProfileDAO userProfileDAO;    // data access adapter
    private AccountDAO accountDAO;
    private UserAccountDTO userAccountDTO;

    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public UserAccountService(Connection connection) {
        setConnection(connection);
        userAccountDTO = new UserAccountDTO();
        userProfileDAO = new UserProfileDAO(getConnection());
        accountDAO = new AccountDAO(getConnection());
    }

    /**
     * Method declaration
     *
     */
    public void create() {

        // userAccountDTO = accountDTO;
        setStateNew();
    }


    /**
     * Method declaration
     *
     *
     * @param accountDTO
     *
     * @exception NBException
     */
    public void save(UserAccountDTO accountDTO) throws NBException {
        validate();
        userProfileDAO.setConnection(getConnection());

        // Save this object.
        if (getStateCurrent() == BO_STATE_NEW) {
            userProfileDAO.createUserAccount(accountDTO);
        } else if (getStateCurrent() == BO_STATE_DIRTY) {

            // --userProfileDAO.saveUserAccount(this.userAccountDTO);
        }

        // --setStateCurrent();
    }

    /**
     * Method declaration
     *
     */
    public void validate() {
    }

    /**
     * Method declaration
     *
     *
     * @param acctNumber
     * @param sessionUserID
     *
     * @return
     *
     * @exception DataConnException
     * @exception EAGLSException
     * @exception NBError
     * @exception NBException
     * @exception NoDataFoundException
     */
    public short validateAccountNumber(String acctNumber, String sessionUserID)
            throws NBException, NBError, NoDataFoundException,
            EAGLSException, DataConnException {
        return accountDAO.validateAccountNumber(acctNumber, sessionUserID);
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchyNumber
     *
     * @return
     *
     * @exception Exception
     * @exception NBError
     * @exception NBException
     */
    public com.boa.eagls.government.util
            .HierarchyDisplay getHierarchyAgencyNames(int hierarchyNumber)
            throws NBException, NBError, Exception {
        return accountDAO.getHierarchyAgencyNames(hierarchyNumber);
    }

    /**
     * Method declaration
     *
     *
     * @param userId
     * @param roleName
     * @param acctNumber
     * @param isDefault
     */
    public void populateAccount(String userId, String roleName,
                                String acctNumber, boolean isDefault) {
        userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUserID(userId);
        userAccountDTO.setRoleName(roleName);
        userAccountDTO.setAccountNumber(acctNumber);
        userAccountDTO.setDefault(isDefault);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public UserAccountDTO getUserAccountDTO() {
        return userAccountDTO;
    }

    /**
     * Method declaration
     *
     *
     * @param userAccountDTO
     */
    public void setUserAccountDTO(UserAccountDTO userAccountDTO) {
        this.userAccountDTO = userAccountDTO;
    }

}
