package com.boa.eagls.government.service.common;

import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegmentDescription;
import com.boa.eagls.government.dto.browse.BrowseMasterAcctCodeFrameDTO;
import com.boa.eagls.government.exceptions.InvalidAccountingCodeError;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.service.AccountingService;
import com.boa.eagls.government.service.Service;
import org.apache.log4j.Logger;

import java.net.URLEncoder;
import java.util.StringTokenizer;

/**
 * <p><small> DVI Company, 22.07.2003 17:35:53</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeFrameService extends Service {
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeFrameService.class);
    private AccountingService accountingService;

    public BrowseMasterAcctCodeFrameService() {
        super();
        accountingService = new AccountingService();
    }

    public BrowseMasterAcctCodeFrameDTO getBrowseMasterAcctCodeFrameDTO
            (String accountingCenterId,
             String masterAccountingCode,
             String hideFavoriteButton,
             String slashes,
             String userID
             ) throws EaglsException {
        masterAccountingCode = createMasterAccountingCode(
                masterAccountingCode,
                accountingCenterId,
                userID);
        BrowseMasterAcctCodeFrameDTO dto =
                new BrowseMasterAcctCodeFrameDTO();
        dto.setAccountingCenterId(accountingCenterId);
        hideFavoriteButton = hideFavoriteButton == null ? "doNotHide" : hideFavoriteButton;
        dto.setHideFavoriteButton(hideFavoriteButton);
        dto.setMasterAccountingCode(masterAccountingCode);
        slashes = slashes == null ? "noSlashes" : slashes;
        dto.setSlashes(slashes);
        return dto;
    }

    private String createMasterAccountingCode(String masterAccountingCode,
                                                     String acctCenterId,
                                                     String userID) throws EaglsException {
        String mac = null;
        if (masterAccountingCode != null) {
            mac = URLEncoder.encode(chopOffSlashes(masterAccountingCode));
            try {
                AccountingCenter tAcctCenter =
                        accountingService.retrieveReadOnly(acctCenterId, userID);
                accountingService.parseAccountingCode(tAcctCenter,
                        chopOffSlashes(masterAccountingCode), false, true);
            } catch (InvalidAccountingCodeError invalidAccountingCodeError) {
                logger.error("Short description here", invalidAccountingCodeError); //To change body of catch statement use Options | File Templates.
                throw new EaglsException("error.createMasterAccountingCode", invalidAccountingCodeError);
            }
        } else {
            mac = "";
        }
        return mac;
    }

    private String chopOffSlashes(String str) {
        StringTokenizer temp = new StringTokenizer(str, "/", false);
        String choppedOffString = "";
        while (temp.hasMoreTokens()) choppedOffString += temp.nextToken();
        return choppedOffString;
    }

    public String chopOffSlashesWithPad(String maCode, String acctCenterId,
                                        String userID)throws EaglsException {
        AccountingCodeSegmentDescription[] aCSDArr = null;
            aCSDArr = (accountingService.retrieveReadOnly(acctCenterId, userID)).
                    getAccountingCodeSegmentDescriptions();

        int acctCodeLength = 0;
        for (int i = 0; i < aCSDArr.length; i++) {
            acctCodeLength += aCSDArr[i].getLength();
        }


        int startPos = 0;
        StringBuffer segValueBuf = new StringBuffer();
        boolean isSlash = true;
        for (int i = startPos; i < aCSDArr.length; i++) {
            int indexNextSlash = maCode.indexOf("/", startPos);
            if (indexNextSlash == -1 && isSlash) {
                if (i == aCSDArr.length - 1) {//for the last segment
                    String lastSt = maCode.substring(startPos);
                    if (lastSt.length() <= aCSDArr[i].getLength()) {
                        segValueBuf.append(lastSt);
                        for (int j = 0; j < aCSDArr[i].getLength() - lastSt.length(); j++) {
                            segValueBuf.append("#");
                        }
                    } else {
                        for (int j = 0; j < aCSDArr[i].getLength(); j++) {
                            segValueBuf.append("#");
                        }
                    }
                } else {//user input without slash
                    isSlash = false;
                    if (maCode.length() >= acctCodeLength) {
                        segValueBuf.append(maCode.substring(0, acctCodeLength));
                    } else {
                        segValueBuf.append(maCode);
                        int lenToPad = acctCodeLength - segValueBuf.length();
                        for (int j = 0; j < lenToPad; j++) {
                            segValueBuf.append("#");
                        }
                    }

                    /*if (maCode.length() <= aCSDArr[0].getLength()) {
                        int lenToPad = acctCodeLength - maCode.length();
                        segValueBuf.append(maCode);
                        for (int j=0; j<lenToPad; j++) {
                            segValueBuf.append("#");
                        }
                    } else  {
                        int lenToPad = acctCodeLength;
                        for (int j=0; j<lenToPad; j++) {
                            segValueBuf.append("#");
                        }
                    }*/
                }
            } else {//if slashes found
                if (isSlash) {
                    String segValueSt = maCode.substring(startPos, indexNextSlash);
                    if (segValueSt.length() > aCSDArr[i].getLength()) {
                        for (int j = 0; j < aCSDArr[i].getLength(); j++) {
                            segValueBuf.append("#");
                        }
                    } else {
                        segValueBuf.append(segValueSt);
                        int lenToPad = aCSDArr[i].getLength() - segValueSt.length();
                        for (int j = 0; j < lenToPad; j++) {
                            segValueBuf.append("#");
                        }
                    }
                    startPos = indexNextSlash + 1;
                }
            }
        }//end for loop
        return new String(segValueBuf);

    }//end chopOffSlashesWithPad()

}