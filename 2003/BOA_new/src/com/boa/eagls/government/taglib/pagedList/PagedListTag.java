package com.boa.eagls.government.taglib.pagedList;


//import javax.servlet.jsp.tagext.Tag;
//import javax.servlet.jsp.tagext.IterationTag;
//import javax.servlet.jsp.tagext.BodyTag;
//import javax.servlet.jsp.tagext.TagSupport;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.ServletException;
import java.io.IOException;


/**
 * Main tag for HTML paged list generation. The tag can contain nested tads
 * pagedListBody and pagedListBar. This tag just flushes body content to HTML.
 * Date: 01.07.2003
 * @author Oleg Klimenko
 */
public class PagedListTag extends BodyTagSupport {

    private static Logger logger = Logger.getLogger(PagedListTag.class);

    private Integer howMany;
    private String scope = "session";
    private String errorUrl = null;

    public void setScope(String scope) {
        this.scope = scope;
    }

    public PagedListTag() {
        super();
    }

    /**
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     */
    public int doStartTag() throws JspException {
        ValueListIterator valueListIterator = PageListUtil.lookupForIterator(pageContext, scope);
        pageContext.setAttribute("howMany", getHowMany());
        pageContext.setAttribute("scope", scope);
        try {
            if (valueListIterator.hasPrevious(getHowMany().intValue())) {
                pageContext.setAttribute("isPrev", "true");
            } else {
                pageContext.removeAttribute("isPrev");
            }
            if (valueListIterator.hasNext(getHowMany().intValue())) {
                pageContext.setAttribute("isNext", "true");
            } else {
                pageContext.removeAttribute("isNext");
            }
        } catch (EaglsDAOError eaglsDAOError) {
            logger.error(eaglsDAOError.getMessage(), eaglsDAOError);
            PageListUtil.handleDaoError(eaglsDAOError.getMessage(), eaglsDAOError, this);
        }
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
//                out.print("<FORM METHOD='GET'" +
//                        " action='/boa/jsp/gsa/setup/account/found_existing.jsp'/>");
                out.print(bodyContent.getString());
//                out.print("</FORM>");
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

    public Integer getHowMany() {
        return howMany;
    }

    public void setHowMany(Integer howMany) {
        this.howMany = howMany;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    protected void processError(String message, EaglsDAOError error) throws JspException{
        if(error==null)
            return;
        throw new PageListException(message, error);
//        if(getErrorUrl()==null)
//            throw new JspException("Error occurs, default application error processing will be used", error);
//        try {
//            pageContext.getServletContext().getRequestDispatcher(getErrorUrl())
//                    .forward(pageContext.getRequest(), pageContext.getResponse());
//        } catch (ServletException e) {
//            logger.error(e.getMessage(), e);
//            throw new JspException(e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//            throw new JspException(e.getMessage(), e);
//        }
    }
}
