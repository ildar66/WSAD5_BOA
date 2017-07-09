package com.boa.eagls.government.service;

import org.apache.log4j.Logger;
import com.boa.eagls.government.dto.setup.MCVehicleSetupVerifyDTO;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.InvalidAccountingCodeError;
import com.boa.eagls.government.constants.service.Role;

/**
 * <p><small> DVI Company, 28.07.2003 14:52:37</small></p>
 * @author GlebL
 */
public class MCVehicleSetupVerifyService extends Service {
    static final Logger logger =
            Logger.getLogger(MCVehicleSetupVerifyService.class);

    public void verify(HierarchyDTO[] currentHierarchy,
                                  MCVehicleSetupVerifyParam params,
                                  String currentBaseRole,
                                  String userID)
            throws EaglsException {
        String centerID = params.getTxt_accountingCenterId();
        String[] hier = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        String[] hierAgencyName = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        int[] acctHierarchy = new int[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        hier[0] = params.getTxt_hl0();
        hierAgencyName[0] = params.getTxt_hl0Desc();
        hier[1] = params.getTxt_hl1();
        hierAgencyName[1] = params.getTxt_hl1Desc();
        hier[2] = params.getTxt_hl2();
        hierAgencyName[2] = params.getTxt_hl2Desc();
        hier[3] = params.getTxt_hl3();
        hierAgencyName[3] = params.getTxt_hl3Desc();
        hier[4] = params.getTxt_hl4();
        hierAgencyName[4] = params.getTxt_hl4Desc();
        hier[5] = params.getTxt_hl5();
        hierAgencyName[5] = params.getTxt_hl5Desc();
        hier[6] = params.getTxt_hl6();
        hierAgencyName[6] = params.getTxt_hl6Desc();
        hier[7] = params.getTxt_hl7();
        hierAgencyName[7] = params.getTxt_hl7Desc();
        hier[8] = params.getTxt_hl8();
        hierAgencyName[8] = params.getTxt_hl8Desc();

        for (int i = 0; i < 9; i++) {
            if (hier[i] == null || hier[i].equals("")) {
                acctHierarchy[i] = 0;
            } else {
                acctHierarchy[i] = Integer.parseInt(hier[i]);
            }
        }
        acctHierarchy[0] = Integer.parseInt(hier[0]);

        //*****JKT
        HierarchyDisplay ah =
                new HierarchyDisplay(hier, hierAgencyName, hierAgencyName);

        // Validate that Hierarchy is valid for A_OPC
        AccountService accountService = new AccountService();
        if (Role.A_OPC.equalsIgnoreCase(currentBaseRole) &&
                !accountService.validateHierarchyForAopcSetup(
                        acctHierarchy, currentHierarchy)) {
            throw new EaglsException(
                    "Invalid account hierarchy entered for account");
        }
        try {
            String mac = params.getTxt_accountingCode();

            AccountingCenter accountingCenter = null;
            if ((Role.A_OPC).equalsIgnoreCase(currentBaseRole)) {
                accountingCenter =
                        AccountingService.retrieveAopc(centerID,
                                userID, currentHierarchy);
            } else {
                accountingCenter =
                        AccountingService.retrieve(centerID, userID);
            }

            // PCR 324
            if (Role.GCSU.equals(currentBaseRole))
                if (accountingCenter.getHierarchy()[1].getNumber() != ah.getValue(1)) {
                    throw new EaglsException("Accounting Center " + centerID +
                            " is not in the same HL1 as the account.");
                }
            if (mac == null) {
                mac = "";
            }
            else if (!"".equals(mac)) {
                AccountingService accountingService =
                        new AccountingService();
                accountingService.parseAccountingCode(accountingCenter, mac);
            }
        } catch (InvalidAccountingCodeError ie) {
            logger.error("Error parsing accounting code", ie);
            throw new EaglsException("Error parsing accounting code");
        }
    }

    public MCVehicleSetupVerifyDTO
            getMCVehicleSetupVerifyDTO(MCVehicleSetupVerifyParam params) {
        MCVehicleSetupVerifyDTO dto = new MCVehicleSetupVerifyDTO();
        String programNumber = params.getTxt_hl0();
        dto.setProgramNumber(programNumber);
        dto.setProgramDescription(params.getProgramDescription());
        dto.setHdn_centralName(params.getHdn_centralName());
        dto.setHdn_centralNumber(params.getHdn_centralNumber());
        dto.setHdn_centralId(params.getHdn_centralId());
        dto.setTxt_equipmentType(params.getTxt_equipmentType());
        dto.setTxt_equipmentId(params.getTxt_equipmentId());
        String temp = params.getCmb_equipFuelLow();
//        temp = temp.substring(temp.indexOf("+") + 1);
        temp = temp.substring(0, 3);
        dto.setEquipFuelLow(temp);
        temp = params.getCmb_equipFuelHigh();
//        temp = temp.substring(temp.indexOf("+") + 1);
        temp = temp.substring(0, 3);
        dto.setEquipFuelHigh(temp);
        dto.setTxt_addressLine1(params.getTxt_addressLine1());
        dto.setTxt_addressLine2(params.getTxt_addressLine2());
        dto.setTxt_addressLine3(params.getTxt_addressLine3());
        dto.setTxt_addressLine4(params.getTxt_addressLine4());
        dto.setTxt_city(params.getTxt_city());
        dto.setTxt_state(params.getTxt_state());
        dto.setTxt_country(params.getTxt_country());
        dto.setTxt_zip(params.getTxt_zip());
        dto.setTxt_creditLimit(params.getTxt_creditLimit());
        dto.setTxt_accountExpireDate(params.getTxt_accountExpireDate());
        dto.setTxt_accountingCenterId(params.getTxt_accountingCenterId());
        dto.setTxt_accountingCode(params.getTxt_accountingCode());
        dto.setHdn_programType(params.getHdn_programType());
        dto.setHdn_billingType(
                Short.parseShort(params.getHdn_billingType()) ==
                Account.CENTRAL ? "CENTRAL" : "INDIVIDUAL");
        dto.setCmb_cardType(params.getCmb_cardType());
        dto.setHdn_card(params.getHdn_card());
        if (params.getRag_generatePaper().equals("generatePaperYes")) {
            dto.setGeneratePaper("Yes");
        } else {
            dto.setGeneratePaper("No");
        }
        dto.setTxt_hl0(params.getTxt_hl0());
        dto.setTxt_hl0Desc(params.getTxt_hl0Desc());

        dto.setTxt_hl1(params.getTxt_hl1());
        dto.setTxt_hl1Desc(params.getTxt_hl1Desc());

        dto.setTxt_hl2(params.getTxt_hl2());
        dto.setTxt_hl2Desc(params.getTxt_hl2Desc());

        dto.setTxt_hl3(params.getTxt_hl3());
        dto.setTxt_hl3Desc(params.getTxt_hl3Desc());

        dto.setTxt_hl4(params.getTxt_hl4());
        dto.setTxt_hl4Desc(params.getTxt_hl4Desc());

        dto.setTxt_hl5(params.getTxt_hl5());
        dto.setTxt_hl5Desc(params.getTxt_hl5Desc());

        dto.setTxt_hl6(params.getTxt_hl6());
        dto.setTxt_hl6Desc(params.getTxt_hl6Desc());

        dto.setTxt_hl7(params.getTxt_hl7());
        dto.setTxt_hl7Desc(params.getTxt_hl7Desc());

        dto.setTxt_hl8(params.getTxt_hl8());
        dto.setTxt_hl8Desc(params.getTxt_hl8Desc());

        if (params.getRag_generatePaper().equals("generatePaperYes")) {
            dto.setGeneratePaperTransmit("Y");
        } else {
            dto.setGeneratePaperTransmit("N");
        }

        dto.setHdn_convenienceChecks(params.getHdn_convenienceChecks());

        dto.setHdn_agencyName(params.getHdn_agencyName());
        return dto;
    }

}