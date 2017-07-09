package com.boa.eagls.government.taglib.pagedList;

import com.boa.eagls.government.constants.web.Eagles;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.pagedList.ValueListIterator;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;

/**
 * <p><small> VDI Company, 24.07.2003 12:03:57</small></p>
 * @author Roman Solodovnichenko
 */
public class PageListUtil {

    protected static final ValueListIterator lookupForIterator(PageContext pageContext) {
        return lookupForIterator(pageContext, null);
    }

    protected static final ValueListIterator lookupForIterator(PageContext pageContext, String scope) {
        if ("request".equals(scope)) {
            return (ValueListIterator) pageContext.getRequest().getAttribute(Eagles.VALUE_LIST_ITERATOR);
        } else {
            return (ValueListIterator) pageContext.getSession().getAttribute(Eagles.VALUE_LIST_ITERATOR);
        }
    }

    /**
     * find a PagedListTag in all parent tags
     * @param currentTag
     * @return PagedListTag
     * @throws JspTagException if PagedListTag not found
     */
    protected static final PagedListTag findPagedListTag(Tag currentTag) throws JspTagException {
        if(currentTag instanceof PagedListTag)
            return (PagedListTag)currentTag;
        Tag parent = currentTag.getParent();
        if (parent == null)
            throw new JspTagException("PagedListTag not found in parent tags");
        if (parent instanceof PagedListTag)
            return (PagedListTag) parent;
        return findPagedListTag(parent);
    }

    //Should be used for handle all <code>EaglsDAOError</code> in a similar way.
    protected static final void handleDaoError(String message, EaglsDAOError error, Tag currentTag) throws JspException{
        findPagedListTag(currentTag).processError(message, error);
    }
}

