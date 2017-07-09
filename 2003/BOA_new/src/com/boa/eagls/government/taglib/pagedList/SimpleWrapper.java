package com.boa.eagls.government.taglib.pagedList;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Date: 13.07.2003
 * @author Oleg Klimenko
 */
public class SimpleWrapper extends BodyTagSupport {

    private static Logger logger = Logger.getLogger(SimpleWrapper.class);

    public SimpleWrapper() {
        super();
    }

    /** .
     *
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     */
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;//EVAL_BODY_AGAIN);
    }

    /**
     * This method is called after the JSP engine finished processing the tag.
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP page, otherwise return SKIP_PAGE.
     */
    public int doEndTag() throws JspException {
        logger.debug("enter doEndTag");
        if (bodyContent != null) {
            try {
                JspWriter out = getPreviousOut();
                out.print(bodyContent.getString());
            } catch (IOException e) {
                throw new JspException("IO error", e);
            }
        }
        return EVAL_PAGE;
    }

    /**
     * This method is called after the JSP engine processes the body content of the tag.
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body again, otherwise return SKIP_BODY.
     */
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

}
