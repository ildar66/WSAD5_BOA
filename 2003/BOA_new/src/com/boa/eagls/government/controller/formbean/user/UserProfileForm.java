/**
 * UserProfileForm
 */
package com.boa.eagls.government.controller.formbean.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public final class UserProfileForm extends ActionForm
{
    private String userId = null;       // the user this password pertains to
    private String lastName = null;     // user's last name
    private String firstName = null;    // user's first name
    private String status = null;       // status of this user
    private boolean hasReportsAccess =
            false;				   // indicator of userID having reports Access
    private String role = null;	   // this user's default role
    private String step =
            null;				   // this field indicates the step of user profile creation
    private String accountNumber = null;
    private ArrayList hierarchy = null;
    private ArrayList programType = null;

    /**
     * Constructor declaration
     *
     */
    public UserProfileForm()
    {
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Method declaration
     *
     *
     * @param accNumber
     */
    public void setAccountNumber(String accNumber)
    {
        accountNumber = accNumber;
    }

    // all forms of user profile creation shud contain the "step" hidden field

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getStep()
    {
        return step;
    }

    /**
     * Method declaration
     *
     *
     * @param astep
     */
    public void setStep(String astep)
    {
        step = astep;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * Method declaration
     *
     *
     * @param aUserID
     */
    public void setUserId(String aUserID)
    {
        userId = aUserID;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Method declaration
     *
     *
     * @param aRoleName
     */
    public void setRole(String aRoleName)
    {
        role = aRoleName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Method declaration
     *
     *
     * @param newLastName
     */
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Method declaration
     *
     *
     * @param newFirstName
     */
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Method declaration
     *
     *
     * @param aStatus
     */
    public void setStatus(String aStatus)
    {
        status = aStatus;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean getHasReportsAccess()
    {
        return hasReportsAccess;
    }

    /**
     * Method declaration
     *
     *
     * @param reportsAccess
     */
    public void setHasReportsAccess(boolean reportsAccess)
    {
        hasReportsAccess = reportsAccess;
    }

    /**
     * Method declaration
     *
     *
     * @param h
     */
    public void setHierarchy(ArrayList h)
    {
        hierarchy = h;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public ArrayList getHierarchy()
    {
        return hierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @param pt
     */
    public void setProgramType(ArrayList pt)
    {
        programType = pt;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public ArrayList getProgramType()
    {
        return programType;
    }

    /**
     * Method declaration
     *
     *
     * @param mapping
     * @param request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        userId = null;
        lastName = null;
        firstName = null;
        status = null;
        hasReportsAccess = false;
        role = null;
    }

    /**
     * Method declaration
     *
     *
     * @param mapping
     * @param request
     *
     * @return
     */
    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request)
    {
        ActionErrors errors = new ActionErrors();

        // if ( (userId == null) || (userId.length() < 1))
        // errors.add("userID", new ActionError("error.userID.required"));
        // if ( (lastName == null) || (lastName.length() < 1))
        // errors.add("lastName", new ActionError("error.lastName.required"));
        // if ( (userStatus == null) || (userStatus.length() < 1))
        // errors.add("userStatus", new ActionError("error.userStatus.required"));
        // if ( (userStatus == null) || (userStatus.length() < 1))
        // errors.add("userStatus", new ActionError("error.userStatus.required"));
        return errors;
    }

}
