package com.boa.eagls.government.taglib.pagedList;


import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;


/**
 * Implementation of <code>TagExtraInfo</code> for the <b>iterate</b>
 * tag, identifying the scripting object(s) to be made visible.
 * @author Oleg Klimenko
 */

public class PagedListBodyTei extends TagExtraInfo {

    private static Logger logger = Logger.getLogger(PagedListBodyTei.class);

    /**
     * Return information about the scripting variables to be created.
     * @param data
     * @return
     */
    public VariableInfo[] getVariableInfo(TagData data) {

        String type = data.getAttributeString("type");
        if (type == null)
            type = "java.lang.Object";
        logger.debug("type: " + type);
        logger.debug("data.getAttributeString(id): " + data.getAttributeString("id"));
        VariableInfo typeInfo = new VariableInfo(data.getAttributeString("id"), type, true, VariableInfo.NESTED);

        String indexId = data.getAttributeString("indexId");
        VariableInfo indexIdInfo = null;
        if (indexId != null)
            indexIdInfo = new VariableInfo(indexId, "java.lang.Integer", true, VariableInfo.NESTED);

        if (indexIdInfo == null) {
            logger.debug("indexIdInfo is null");
            return new VariableInfo[]{typeInfo};
        } else {
            logger.debug("indexIdInfo is NOT null");
            return new VariableInfo[]{typeInfo, indexIdInfo};
        }

    }


}
