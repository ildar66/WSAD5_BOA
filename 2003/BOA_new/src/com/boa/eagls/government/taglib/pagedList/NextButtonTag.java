package com.boa.eagls.government.taglib.pagedList;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Tag for rendering nextColumn button in PagedList. It may render nothing if no nextColumn
 * records are exist.
 * User: OlegK
 * Date: 06.07.2003
 * @author Oleg Klimenko
 */
public class NextButtonTag extends TagSupport {

    private static Logger logger = Logger.getLogger(NextButtonTag.class);

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
     * @throws JspException
     */
    public int doStartTag() throws JspException {
        ValueListIterator valueListIterator = PageListUtil.lookupForIterator(pageContext);
        try {
            if (valueListIterator.hasNext(50)) {
                logger.debug("IS NEXT");
//                pageContext.getOut().print("<INPUT TYPE='SUBMIT' name='nextColumn' value='Next'/>");
                pageContext.getOut().print("<INPUT TYPE='BUTTON' name='nextColumn' value='Next' ");
                if (searchParameter == null) {
                    pageContext.getOut().print("onclick='window.location=\""+
                            ((HttpServletRequest)(pageContext.getRequest())).getRequestURL()+"?Next=Next\"'/>");
                } else {
                    pageContext.getOut().print("onclick='window.location=\""+
                            ((HttpServletRequest)(pageContext.getRequest())).getRequestURL()+"?Next=Next&searchType="+searchParameter+"\"'/>");
                }
            } else {
                logger.debug("IS NOT NEXT");
                pageContext.getOut().print("&nbsp;");
            }
        } catch (EaglsDAOError eaglsDAOError) {
            logger.error(eaglsDAOError.getMessage(), eaglsDAOError);
            PageListUtil.handleDaoError(eaglsDAOError.getMessage(), eaglsDAOError, this);
//            throw new JspException("NextButtonTag: ", eaglsDAOError);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new JspException("NextButtonTag: ", e);
        }
        return SKIP_BODY;
    }

    public int doEndTag() {
        return EVAL_PAGE;
    }

}
