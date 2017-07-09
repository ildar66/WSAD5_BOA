package com.boa.eagls.government.service.common;

import java.io.Serializable;

/**
 * <p><small> DVI Company, 24.07.2003 20:21:01</small></p>
 * @author GlebL
 */
public interface BrowseMasterAcctCodeActionsParam
        extends Serializable{
    String getStartRecord();
    String getForceUpdate();
    String getTxt_browseCriteria();
    String getHdn_accountingCenterId();
    String getBut_submit();
    String getSel_availableSegments();
    String getHdn_valueSet();
    String getHdn_segNumSet();
    String getHideButton();
    String getSlashes();
    String getTxt_segmentValue();
}