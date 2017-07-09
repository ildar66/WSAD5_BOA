package com.boa.eagls.government.dto.browse;

import org.apache.log4j.Logger;
import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * <p><small> DVI Company, 22.07.2003 17:26:57</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeFrameDTO
        extends BusinessObject implements Serializable{
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeFrameDTO.class);
    private String accountingCenterId;
    private String masterAccountingCode;
    private String hideFavoriteButton;
    private String slashes;

    public String getAccountingCenterId() {
        return accountingCenterId;
    }

    public void setAccountingCenterId(String accountingCenterId) {
        this.accountingCenterId = accountingCenterId;
    }

    public String getMasterAccountingCode() {
        return masterAccountingCode;
    }

    public void setMasterAccountingCode(String masterAccountingCode) {
        this.masterAccountingCode = masterAccountingCode;
    }

    public String getHideFavoriteButton() {
        return hideFavoriteButton;
    }

    public void setHideFavoriteButton(String hideFavoriteButton) {
        this.hideFavoriteButton = hideFavoriteButton;
    }

    public String getSlashes() {
        return slashes;
    }

    public void setSlashes(String slashes) {
        this.slashes = slashes;
    }
}