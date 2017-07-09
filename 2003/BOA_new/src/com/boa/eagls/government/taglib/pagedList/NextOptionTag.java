package com.boa.eagls.government.taglib.pagedList;

import com.boa.eagls.government.util.pagedList.ValueListIterator;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * <p><small> DVI Company, 25.07.2003 18:13:49</small></p>
 * @author GlebL
 */
public class NextOptionTag extends TagSupport {
    static final Logger logger = Logger.getLogger(NextOptionTag.class);
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * It's a simple tag. So it writes to output during startTag processing.
     * It doesn't have a body.
     * @return
     * @throws javax.servlet.jsp.JspException
     */
    public int doStartTag() throws JspException {
        try {
            String scope = (String) pageContext.getAttribute("scope");
            ValueListIterator valueListIterator = PageListUtil.lookupForIterator(pageContext, scope);
            int howMany = Integer.parseInt(pageContext.getAttribute("howMany").toString());
            if (howMany <= 0)
                throw new JspException("howMany <= 0 , howMany=" + howMany);
            if (valueListIterator.hasNext(howMany)) {
                logger.debug("IS NEXT");
                pageContext.getOut().print("<OPTION VALUE=" + value + ">... Next ...</OPTION>");
            } else {
                logger.debug("IS NOT NEXT");
                pageContext.getOut().print("&nbsp;");
            }
        } catch (EaglsDAOError eaglsDAOError) {
            logger.error(eaglsDAOError.getMessage(), eaglsDAOError);
            PageListUtil.handleDaoError(eaglsDAOError.getMessage(), eaglsDAOError, this);
        } catch (IOException e) {
            logger.error("Enter Short description here", e);
            throw new JspTagException("NextOptionTag: " + e.getMessage());
        }
        return SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }

}