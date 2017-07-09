package com.boa.eagls.government.service.centralaccount;

import com.boa.eagls.government.util.pagedList.ValueListHandler;
import com.boa.eagls.government.util.pagedList.ValueListSelector;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;

/**
 * <p><small> VDI Company, 24.07.2003 11:18:22</small></p>
 * @author AlexanderZe
 */
public class CentralAccountValueListHandler extends ValueListHandler{
    private String txt_centralAccountNumber = null;

    public String getTxt_centralAccountNumber() {
        return txt_centralAccountNumber;
    }

    public CentralAccountValueListHandler(ValueListSelector selector, String txt_centralAccountNumber) throws EaglsDAOError {
        super(selector);
        this.txt_centralAccountNumber = txt_centralAccountNumber;
    }

}

