package com.boa.eagls.government.taglib;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.config.ModuleConfig;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.util.ValidFunctions;


public final class CheckRightsTag extends TagSupport
{

    private String functionName = "";
    private String page = "/error.do";//"../../AccessDenied.jsp";


    /**
     * Return the function name.
     * @return
     */
    public String getFunctionName()
    {
        return (this.functionName);
    }

    /**
     * Set the function name.
     *
     * @param name The new bean name
     */
    public void setFunctionName(String fname)
    {
        this.functionName = fname;
    }

    /**
     * Return the forward page.
     * @return
     */
    public String getPage()
    {
        return (this.page);
    }


    /**
     * Set the forward page.
     *
     * @param page The new forward page
     */
    public void setPage(String page)
    {
        this.page = page;
    }


    /**
     * Defer our checking until the end of this tag is encountered.
     * @return
     * @throws JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException
    {
        return (SKIP_BODY);
    }

    /**
     * Validate if currently logged on user has rights to view the source jsp
     * or has rights to perform this function
     * functionName - function name which user wants to perform
     * page - Context-relative path to nextColumn page     *
     * @exception JspException if a JSP exception has occurred
     * @return
     * @pre $none
     * @post $none
     */
    public int doEndTag() throws JspException
    {
//        System.out.println("CheckRightsTag --> doEndTag() --> Start");
        // Is there a valid user logged on?
        boolean valid = false;
        HttpSession session = pageContext.getSession();
        try
        {
            ValidFunctions vf = new ValidFunctions();
            String[][] roleFunctions = vf.parseRoleFunctions((String) session.getAttribute("currentRoleFunctionsStr"));

            // Forward control based on the results
            if (hasRights(functionName, roleFunctions))
            {
//                System.out.println("CheckRightsTag --> doEndTag() --> has rights");
                return (EVAL_PAGE);
            }

            pageContext.getRequest().setAttribute(Constants.ERR_HEADING,
                    "heading.error.access.denied");
            pageContext.getRequest().setAttribute(Constants.ERR_MSG,
                    "error.msg.hasNoFunctionRights");
            pageContext.forward(page);

        }
        catch (Exception e)
        {
            throw new JspException(e.toString());
        }
        return (SKIP_PAGE);
    }


    /**
     * Release any acquired resources.
     */
    public void release()
    {
        super.release();
        this.functionName = "";
        this.page = "/error.do";
    }

    public boolean hasRights(String function, String[][] roleFunctions)
    {
        boolean rights = false;
        if (function.equals("")) return rights;
//       System.out.println("my function "+function);
        for (int i = 0; i < roleFunctions.length; i++)
        { // System.out.println("function "+roleFunctions[i][0]);
            if (roleFunctions[i][0].equals(function))
            {
                rights = true;
                break;
            }
        }
        return rights;
    }


}