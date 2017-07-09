package com.boa.eagls.government.taglib.pagedList;

import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * The tag performs iterations of it's content. Itarator are created by
 * instances of derived from <code>ValueListIterator</code> classes.
 * Date: 06.07.2003
 * @author Oleg Klimenko
 */
public class PagedListBodyTag extends BodyTagSupport {

    private static Logger logger = Logger.getLogger(PagedListBodyTag.class);

    private Iterator iterator;
    protected Object collection;
    private String id;
    private String name;
    private String property;

    public PagedListBodyTag() {
        super();
    }

    /**
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     */
    public int doStartTag() throws JspException {
//        bodyContent.
        Object collection = null;
        collection = this.collection;
        try {
            if (collection == null) {
//            try {
//                Object bean = pageContext.findAttribute(getName());  // ).findAttribute(
//                pageContext.findAttribute()
//                System.out.println("get bean");
//                if (bean == null) {
//                    throw new JspException("Null iterator bean");
//                }
//                if (getProperty() == null) {
                collection = readSearchResult();//bean;
                if (collection == null) {
                    throw new JspTagException("Null collection reached");
                }
//            } catch (Exception e) {
//                 throw new JspException("Null collection");
//                //throw new JspException("Problems during bean peroperty access");
//            }
            }
            // Construct an iterator for this collection
            if (collection instanceof Collection) {
                logger.debug("gettin iter #1");
                setIterator(((Collection) collection).iterator());
                logger.debug("collection size: " + ((Collection) collection).size());
            } else if (collection instanceof Iterator) {
                logger.debug("gettin iter #2");
                setIterator((Iterator) collection);
                logger.debug("get by iterator");
            }
            // Store the first value and evaluate,
            // or skip the body if none
//        return (SKIP_BODY);

            if (getIterator().hasNext()) {
                Object element = getIterator().next();
                pageContext.setAttribute(getId(), element);
                logger.debug("getId(): " + getId());
                logger.debug("element: " + element);
                logger.debug(" doStartTag: EVAL_BODY_TAG");
                return (EVAL_BODY_BUFFERED);//EVAL_BODY_AGAIN);
            } else {
                logger.debug(" doStartTag: SKIP_BODY");
                return (SKIP_BODY);
            }

//        if (theBodyShouldBeEvaluated()) {
//            return EVAL_BODY_BUFFERED;
//        } else {
//            return SKIP_BODY;
//        }
        } catch (EaglsDAOError eaglsDAOError) {
            logger.error(eaglsDAOError.getMessage(), eaglsDAOError);
            PageListUtil.handleDaoError(eaglsDAOError.getMessage(), eaglsDAOError, this);
            return SKIP_BODY;
        }
    }

    /**
     * This method is called after the JSP engine finished processing the tag.
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP page, otherwise return SKIP_PAGE.
     */
    public int doEndTag() throws JspException, JspException {
        logger.debug("enter doEndTag");
        if (bodyContent != null) {
            try {
                JspWriter out = getPreviousOut();
//                out.print("<FORM METHOD='GET'" + "/>");
                out.print(bodyContent.getString());
//                out.print("</FORM>");
            } catch (IOException e) {
                throw new JspException("IO error", e);
            }
        }
        return EVAL_PAGE;
    }

    /** .
     * This method is called after the JSP engine processes the body content of the tag.
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body again, otherwise return SKIP_BODY.
     */
    public int doAfterBody() throws JspException {
/*        try {
            //
            // This code is generated for tags whose bodyContent is "JSP"
            //
            BodyContent bodyContent = getBodyContent();
            JspWriter out = bodyContent.getEnclosingWriter();

            bodyContent.writeOut(out);
            bodyContent.clearBody();

//            writeTagBodyContent(out, bodyContent);
        } catch (Exception ex) {
            handleBodyContentException(ex);
        }*/

        if (getIterator().hasNext()) {
            Object element = getIterator().next();
            pageContext.setAttribute(getId(), element);
            return EVAL_BODY_BUFFERED;//(EVAL_BODY_AGAIN);
        } else {
            return SKIP_BODY;
        }

    }

    private Collection readSearchResult() throws JspException, EaglsDAOError {
        logger.debug("entering readSearchResult()");
        String scope = (String) pageContext.getAttribute("scope");
        ValueListIterator valueListIterator = PageListUtil.lookupForIterator(pageContext, scope);
        Collection c = null;
        try {
            int howMany = ((Integer) pageContext.getAttribute("howMany")).intValue();
            if (pageContext.getRequest().getParameter("prev") != null) {// previous button has been pressed
                logger.debug("executing PREV");
                c = valueListIterator.getPrevious(howMany);//getHowMany().intValue());
            } else {// nextColumn button has been pressed or first view of the page
                logger.debug("executing NEXT");
                c = valueListIterator.getNext(howMany);//getHowMany().intValue());
            }
        } catch (ClassCastException e) {
            throw new JspException(e.getMessage(), e);
        } catch (NullPointerException e) {
            throw new JspException(e.getMessage(), e);
        }
        return c;
    }

    public Iterator getIterator() {
        return iterator;
    }

    public void setIterator(Iterator iterator) {
        this.iterator = iterator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getCollection() {
        return (this.collection);
    }

    public void setCollection(Object collection) {
        this.collection = collection;
    }

}
