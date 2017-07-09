package com.boa.eagls.government.dto.account;

import org.apache.log4j.Logger;
import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * <code>This is the container object for account status and reason code tables
 * it will serve for change account status functionality  </code>
 * <p><small> VDI Company, 30.07.2003 15:25:48</small></p>
 * @author StanislavS
 */


public class AccountStatus extends BusinessObject implements Serializable {
    private static Logger log4j = Logger.getLogger(AccountStatus.class);
    private String tsysStatus;
    private String reasonCode;
    private int priority;
    private String statusDate;
    private String description;
    private char accountStatus;

//------------------------------------------constructor-----------------------------------------

    public AccountStatus() {
    }

    public AccountStatus(String tTSYSStatus, String tReasonCode, int tPriority, String tStatusDate, String tDescription, char tAccountStatus) {
        setTsysStatus(tTSYSStatus);
        setReasonCode(tReasonCode);
        setPriority(tPriority);
        setStatusDate(tStatusDate);
        setDescription(tDescription);
        setAccountStatus(tAccountStatus);
    }

    public AccountStatus(String tTSYSStatus, String tReasonCode, int tPriority, String tDescription) {
        setTsysStatus(tTSYSStatus);
        setReasonCode(tReasonCode);
        setPriority(tPriority);
        setDescription(tDescription);
    }

//--------------------------------------------------get methods----------------------------------------
    public String getTsysStatus() {
        return tsysStatus;
    }

    public void setTsysStatus(String tsysStatus) {
        this.tsysStatus = tsysStatus;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(String statusDate) {
        this.statusDate = statusDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(char accountStatus) {
        this.accountStatus = accountStatus;
    }
}
