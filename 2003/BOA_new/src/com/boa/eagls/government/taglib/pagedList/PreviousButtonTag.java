package com.boa.eagls.government.taglib.pagedList;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Tag for rendering previous button in PagedList. It may render nothing if no
 * previous records are exist.
 * User: OlegK
 * Date: 06.07.2003
 * @author Oleg Klimenko
 */
public class PreviousButtonTag extends TagSupport {

    private static Logger logger = Logger.getLogger(PreviousButtonTag.class);

    private String searchParameter;

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    /**
     * It's a simple tag. So it writes to output during startTag processing.
     * It doesn't have a body.
     * @return <code>SKIP_BODY</code> constant to define bodyless tag
     * @throws JspException
     */
    public int doStartTag() throws JspException {
        try {
            ValueListIterator valueListIterator = PageListUtil.lookupForIterator(pageContext);
            if (valueListIterator.hasPrevious(50)) {
                logger.debug("IS PREV");
                pageContext.getOut().print("<INPUT TYPE='BUTTON' name='prev' value='Previous' ");
                if (searchParameter == null) {
                    pageContext.getOut().print("onclick='window.location=\""+
                            ((HttpServletRequest)(pageContext.getRequest())).getRequestURL()+"?Previous=Previous\"'/>");
                } else {
                    pageContext.getOut().print("onclick='window.location=\""+
                            ((HttpServletRequest)(pageContext.getRequest())).getRequestURL()+"?Previous=Previous&searchType="+searchParameter+"\"'/>");
                }
            } else {
                logger.debug("IS NOT PREV");
                pageContext.getOut().print("&nbsp;");
            }
        } catch (EaglsDAOError eaglsDAOError) {
            logger.error(eaglsDAOError.getMessage(), eaglsDAOError);
            PageListUtil.handleDaoError(eaglsDAOError.getMessage(), eaglsDAOError, this);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new JspException(e.getMessage(), e);
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
