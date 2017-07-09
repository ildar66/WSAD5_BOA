/**
 * CheckLogonTag
 */

package com.boa.eagls.government.taglib;

import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.Constants;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * Check for a valid User logged on in the current session.  If there is no
 * such user, forward control to the logon page.
 *
 * <p>Title: CheckLogonTag</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public final class CheckLogonTag extends TagSupport {


    // --------------------------------------------------- Instance Variables


    /**
     * The key of the session-scope bean we look for.
     */
    private String name = "user";


    /**
     * The page to which we should forward for the user to log on.
     */
    private String page = "/error.do";

    private String functionName = "";


    // ----------------------------------------------------------- Properties


    /**
     * Return the bean name.
     * @return
     */
    public String getName() {
        return (this.name);
    }


    /**
     * Set the bean name.
     *
     * @param name The new bean name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the function name.
     * @return
     */
    public String getFunctionName() {
        return (this.functionName);
    }


    /**
     * Set the function name.
     *
     * @param name The new bean name
     */
    public void setFunctionName(String fname) {
        this.functionName = fname;

    }

    /**
     * Return the forward page.
     * @return
     */
    public String getPage() {
        return (this.page);
    }


    /**
     * Set the forward page.
     *
     * @param page The new forward page
     */
    public void setPage(String page) {
        this.page = page;
    }


    // ------------------------------------------------------- Public Methods


    /**
     * Defer our checking until the end of this tag is encountered.
     * @return
     * @throws JspException if a JSP exception has occurred
     */
    public int doStartTag() throws JspException {
        return (SKIP_BODY);
    }


    /**
     * Perform our logged-in user check by looking for the existence of
     * a session scope bean under the specified name.  If this bean is not
     * present, control is forwarded to the specified logon page.
     *
     * @exception JspException if a JSP exception has occurred
     * @return
     * @pre $none
     * @post $none
     */
    public int doEndTag() throws JspException {

        // Is there a valid user logged on?
        boolean valid = false;
        HttpSession session = pageContext.getSession();
        try {
            if ((session != null) && (session.getAttribute(name) != null))
                valid = true;

            // Forward control based on the results
            if (valid) {
                return (EVAL_PAGE);
            }
            pageContext.setAttribute(Constants.ERR_HEADING, "heading.error.access.denied");
            pageContext.setAttribute(Constants.ERR_MSG, "error.msg.notLoggedIn");
            pageContext.forward(page);

        } catch (Exception e) {
            throw new JspException(e.toString());
        }
        return (SKIP_PAGE);
    }


    /**
     * Release any acquired resources.
     */
    public void release() {

        super.release();
        this.name = "user";
        this.page = "/error.do";

    }

    public boolean hasRights(String function) {
        EAGLSSession session = new EAGLSSession();
        boolean rights = true;
//     session.
        return rights;
    }

}
