/* ProfileSearchActionForm class */
package com.boa.eagls.government.controller.formbean.common;

import org.apache.struts.action.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

/**
 * <p>Title: </p> <p>Description: The form bean class for searching in the
 * create user use case</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 * @invariant $none
 */
public class ProfileSearchActionForm extends ActionForm
{
    private String txt_accountNumber;
    private String txt_lastName;
    private String txt_firstName;
    private String txt_hierarchyDepth;
    private String programNumber;
    private String[] hierarchy;
    private String txt_hl0;
    private String txt_hl1;
    private String txt_hl2;
    private String txt_hl3;
    private String txt_hl4;
    private String txt_hl5;
    private String txt_hl6;
    private String txt_hl7;
    private String txt_hl8;

    // Set Logger
    private static Logger logger =
            Logger
            .getLogger(com.boa.eagls.government.controller.formbean.common
            .ProfileSearchActionForm.class);

    /**
     * Constructor declaration
     *
     */
    public ProfileSearchActionForm()
    {
        logger.debug("Creating instance of ProfileSearchForm");
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_accountNumber()
    {
        return txt_accountNumber;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_accountNumber
     */
    public void setTxt_accountNumber(String txt_accountNumber)
    {
        this.txt_accountNumber = txt_accountNumber;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_lastName()
    {
        return txt_lastName;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_lastName
     */
    public void setTxt_lastName(String txt_lastName)
    {
        this.txt_lastName = txt_lastName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_firstName()
    {
        return txt_firstName;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_firstName
     */
    public void setTxt_firstName(String txt_firstName)
    {
        this.txt_firstName = txt_firstName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hierarchyDepth()
    {
        return txt_hierarchyDepth;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hierarchyDepth
     */
    public void setTxt_hierarchyDepth(String txt_hierarchyDepth)
    {
        this.txt_hierarchyDepth = txt_hierarchyDepth;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getProgramNumber()
    {
        return programNumber;
    }

    /**
     * Method declaration
     *
     *
     * @param programNumber
     */
    public void setProgramNumber(String programNumber)
    {
        this.programNumber = programNumber;
    }

    /**
     * Method declaration
     *
     *
     * @param actionMapping
     * @param httpServletRequest
     *
     * @return
     */
    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest)
    {

        /**
         * @todo: finish this method, this is just the skeleton.
         */
        return null;
    }

    /**
     * Method declaration
     *
     *
     * @param actionMapping
     * @param httpServletRequest
     */
    public void reset(ActionMapping actionMapping,
                      HttpServletRequest httpServletRequest)
    {
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getHierarchy()
    {
        hierarchy = new String[9];
        logger.debug("in get hierarchy " + hierarchy.length);
        hierarchy[0] = getTxt_hl0();
        hierarchy[1] = getTxt_hl1();
        hierarchy[2] = getTxt_hl2();
        hierarchy[3] = getTxt_hl3();
        hierarchy[4] = getTxt_hl4();
        hierarchy[5] = getTxt_hl5();
        hierarchy[6] = getTxt_hl6();
        hierarchy[7] = getTxt_hl7();
        hierarchy[8] = getTxt_hl8();
        if (hierarchy[0] == null || hierarchy[0].equals(""))
        {
            hierarchy = null;
        }
        return hierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchy
     */
    public void setHierarchy(String[] hierarchy)
    {
        this.hierarchy = hierarchy;
    }

    /**
     * Method declaration
     *
     */
    public void logDetails()
    {
        logger.debug(this.getTxt_accountNumber());
        logger.debug(this.getTxt_lastName());
        logger.debug(this.getTxt_firstName());
        logger.debug(this.getTxt_hierarchyDepth());
        logger.debug("hierarchy " + getHierarchy());
        if (hierarchy != null)
        {
            for (int i = 0; i < hierarchy.length; i++)
            {
                logger.debug(hierarchy[i]);
            }
        }
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl0()
    {
        return txt_hl0;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl0
     */
    public void setTxt_hl0(String txt_hl0)
    {
        this.txt_hl0 = txt_hl0;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl1()
    {
        return txt_hl1;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl1
     */
    public void setTxt_hl1(String txt_hl1)
    {
        this.txt_hl1 = txt_hl1;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl2()
    {
        return txt_hl2;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl2
     */
    public void setTxt_hl2(String txt_hl2)
    {
        this.txt_hl2 = txt_hl2;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl3()
    {
        return txt_hl3;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl3
     */
    public void setTxt_hl3(String txt_hl3)
    {
        this.txt_hl3 = txt_hl3;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl4()
    {
        return txt_hl4;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl4
     */
    public void setTxt_hl4(String txt_hl4)
    {
        this.txt_hl4 = txt_hl4;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl5()
    {
        return txt_hl5;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl5
     */
    public void setTxt_hl5(String txt_hl5)
    {
        this.txt_hl5 = txt_hl5;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl6()
    {
        return txt_hl6;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl6
     */
    public void setTxt_hl6(String txt_hl6)
    {
        this.txt_hl6 = txt_hl6;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl7()
    {
        return txt_hl7;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl7
     */
    public void setTxt_hl7(String txt_hl7)
    {
        this.txt_hl7 = txt_hl7;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getTxt_hl8()
    {
        return txt_hl8;
    }

    /**
     * Method declaration
     *
     *
     * @param txt_hl8
     */
    public void setTxt_hl8(String txt_hl8)
    {
        this.txt_hl8 = txt_hl8;
    }

}
