package com.boa.eagls.government.service.centralaccount;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: IvanK
 * Date: 05.07.2003
 * Time: 16:23:53
 * To change this template use Options | File Templates.
 */
public interface SearchCentralAccountParam extends Serializable {

    public boolean isChk_hierLevel();
    public boolean isChk_centralAccountName();
    public boolean isChk_centralAccountNumber();
    public boolean isChk_centralAccountID();
    public boolean isChk_AgencyName();

    public String getTxt_centralAccountID();
    public String getTxt_centralAccountNumber();
    public String getTxt_centralAccountName();
    public String getTxt_hierarchyDepth();
    public String getTxt_agencyName();
    public String getTxt_hl0();
    public String getTxt_hl1();
    public String getTxt_hl2();
    public String getTxt_hl3();
    public String getTxt_hl4();
    public String getTxt_hl5();
    public String getTxt_hl6();
    public String getTxt_hl7();
    public String getTxt_hl8();
}