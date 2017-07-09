/**
 * PasswordDTO
 */
package com.boa.eagls.government.dto.user;

import com.boa.eagls.government.dto.DTOBase;

import java.util.Date;
import java.util.Vector;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class PasswordDTO extends DTOBase {
    private String userID =
            STRING_DEFAULT;				  // the user this password pertains to
    private String password = STRING_DEFAULT;    // the current password
    private Vector passwordHistory;		  // a list of previous passwords
    private int failedCount =
            INT_DEFAULT;				  // number of times a password check has failed
    private Date expiryDate =
            DATE_DEFAULT;				  // date the current password will expire
    private int noOfDaysToExpire =
            INT_DEFAULT;				  // no of days to expire the password
    private boolean isCurrentPwdDefault = false;

    /**
     * Constructor declaration
     *
     */
    public PasswordDTO() {
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getNoOfDaysToExpire() {
        Date expDate = getExpiryDate();
        Date curDate = new Date();

        noOfDaysToExpire = (int) ((expDate.getTime() - curDate.getTime())
                / (24 * 60 * 60 * 1000));
        if (noOfDaysToExpire < 0) {
            noOfDaysToExpire = 0;
        }
        return noOfDaysToExpire;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method declaration
     *
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Method declaration
     *
     *
     * @param expiryDate
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getFailedCount() {
        return failedCount;
    }

    /**
     * Method declaration
     *
     *
     * @param failedCount
     */
    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * Method declaration
     *
     */
    public void incFailedCount() {
        this.failedCount++;
    }

    /**
     * Method declaration
     *
     *
     * @param count
     */
    public void setFailedCount(Integer count) {
        failedCount = validateInteger(count);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Vector getPasswordHistory() {
        return passwordHistory;
    }

    /**
     * Method declaration
     *
     *
     * @param passwordHistory
     */
    public void setPasswordHistory(Vector passwordHistory) {
        this.passwordHistory = passwordHistory;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean getIsCurrentPwdDefault() {
        return isCurrentPwdDefault;
    }

    /**
     * Method declaration
     *
     *
     * @param isDefault
     */
    public void setIsCurrentPwdDefault(boolean isDefault) {
        isCurrentPwdDefault = isDefault;
    }

}
