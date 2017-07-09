package com.boa.eagls.government.taglib.pagedList;

import com.boa.eagls.government.util.pagedList.ValueListIterator;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * <p><small> DVI Company, 25.07.2003 18:58:29</small></p>
 * @author GlebL
 */
public class PreviousOptionTag extends TagSupport {
    static final Logger logger =
            Logger.getLogger(PreviousOptionTag.class);
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * It's a simple tag. So it writes to output during startTag processing.
     * It doesn't have a body.
     * @return <code>SKIP_BODY</code> constant to define bodyless tag
     * @throws javax.servlet.jsp.JspException
     */
    public int doStartTag() throws JspException {
        try {
            String scope = (String) pageContext.getAttribute("scope");
            ValueListIterator valueListIterator =
                    PageListUtil.lookupForIterator(pageContext, scope);
            int howMany = Integer.parseInt(
                    pageContext.getAttribute("howMany").toString());
            if (howMany <= 0) {
                throw new Exception();
            }
            if (valueListIterator.hasPrevious(howMany)) {
                logger.debug("IS PREV");
                pageContext.getOut().print("<OPTION VALUE=");
                pageContext.getOut().print(value);
                pageContext.getOut().print(">... Previous ...</OPTION>");
            } else {
                logger.debug("IS NOT PREV");
                pageContext.getOut().print("&nbsp;");
            }
        } catch (Exception ex) {
            throw new JspTagException("PreviousOptionTag: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    /**
     * sets flag to indicate following page processing
     * @return <code>EVAL_PAGE</code> constant
     */
    public int doEndTag() {
        return EVAL_PAGE;
    }

}