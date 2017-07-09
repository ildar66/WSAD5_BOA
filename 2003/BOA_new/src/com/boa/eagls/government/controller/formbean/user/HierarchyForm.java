/**
 * HierarchyForm
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
public final class HierarchyForm extends ActionForm
{
    private String[] hierarchy = new String[10];
    private String[] programTypes = null;
    private String programNumber = null;
    private String btnSubmitVal = null;
    private String step = null;
    private String txt_hl1 = null;
    private String txt_hl2 = null;
    private String txt_hl3 = null;
    private String txt_hl4 = null;
    private String txt_hl5 = null;
    private String txt_hl6 = null;
    private String txt_hl7 = null;
    private String txt_hl8 = null;
    private String txt_hl0 = null;

    /**
     * Constructor declaration
     *
     */
    public HierarchyForm()
    {
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getBtnSubmitVal()
    {
        return btnSubmitVal;
    }

    /**
     * Method declaration
     *
     *
     * @param val
     */
    public void setBtnSubmitVal(String val)
    {
        btnSubmitVal = val;
    }

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
     * @param val
     */
    public void setStep(String val)
    {
        step = val;
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
     * @param apn
     */
    public void setProgramNumber(String apn)
    {
        programNumber = apn;
    }

    /**
     * Method declaration
     *
     *
     * @param h
     */
    public void setHierarchy(String[] h)
    {
        hierarchy = h;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getHierarchy()
    {
        return new String[]
        {
            txt_hl0, txt_hl1, txt_hl2, txt_hl3, txt_hl4, txt_hl5, txt_hl6,
            txt_hl7, txt_hl8
        };
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getProgramTypes()
    {
        return programTypes;
    }

    /**
     * Method declaration
     *
     *
     * @param pt
     */
    public void setProgramTypes(String[] pt)
    {
        programTypes = pt;
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
        hierarchy = null;
        programTypes = null;
        programNumber = null;
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

        if ((programNumber == null) || (programNumber.length() < 1))
        {
            errors.add("User Profile ",
                    new ActionError("error.programNumber.required"));
        }
        return errors;
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

}
