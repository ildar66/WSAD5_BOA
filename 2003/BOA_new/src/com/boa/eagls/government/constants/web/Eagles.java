package com.boa.eagls.government.constants.web;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: GlebL
 * Date: 18.07.2003
 * Time: 11:35:23
 * To change this template use Options | File Templates.
 */
public interface Eagles {
    public static final String NAME_DESC_TABLE = "NAME_DESC_TABLE";

    /** Used for storing a <code>ValueListIterator</code> object in the session.
     * @see com.boa.eagls.government.util.pagedList.ValueListIterator
     * @see com.boa.eagls.government.taglib.pagedList.PageListUtil#lookupForIterator(javax.servlet.jsp.PageContext)
     */
    public static final String VALUE_LIST_ITERATOR = "searchResult";

    // GLOBAL FORWARDS SECTION
    public static final String ERROR_FORWARD = "error";
//    public static final String ERROR_TEST_FORWARD = "errorTest";
    public static final String BROWSE_HIERARHY_FIELDS =
            "browseHierarchyFields";

    public static final String NOT_IMPLEMENTED_ACTION = "notImplemented.do";
    public static final String NOT_IMPLEMENTED_FORWARD = "/notImplemented";


}
