package com.boa.eagls.government.service;

import org.apache.log4j.Logger;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.util.DisplayDateUtil;
import com.boa.eagls.government.util.DateUtil;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.exceptions.NoDataFoundException;
import com.boa.eagls.government.dao.MastercardVehicleDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;

/**
 * <p><small> DVI Company, 29.07.2003 14:24:44</small></p>
 * @author GlebL
 */
public class MCVehicleSetupSubmitService extends Service {
    static final Logger logger =
            Logger.getLogger(MCVehicleSetupSubmitService.class);

    public void createMastercardVehicle(MCVehicleSetupSubmitParam params,
                                               String userID)
            throws EaglsDAOError, NoDataFoundException {

        HierarchyDisplay hierarchy = null;

        String[] hier = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        String[] desc = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        hier[0] = params.getTxt_hl0();
        desc[0] = params.getTxt_hl0Desc();
        hier[1] = params.getTxt_hl1();
        desc[1] = params.getTxt_hl1Desc();
        hier[2] = params.getTxt_hl2();
        desc[2] = params.getTxt_hl2Desc();
        hier[3] = params.getTxt_hl3();
        desc[3] = params.getTxt_hl3Desc();
        hier[4] = params.getTxt_hl4();
        desc[4] = params.getTxt_hl4Desc();
        hier[5] = params.getTxt_hl5();
        desc[5] = params.getTxt_hl5Desc();
        hier[6] = params.getTxt_hl6();
        desc[6] = params.getTxt_hl6Desc();
        hier[7] = params.getTxt_hl7();
        desc[7] = params.getTxt_hl7Desc();
        hier[8] = params.getTxt_hl8();
        desc[8] = params.getTxt_hl8Desc();

        Map map = new HashMap();
        hierarchy = new HierarchyDisplay(hier, desc, desc);
        map.put("aHierarchy", hierarchy.getHierarchy());///////////////
//        //See if hierarchy is null, throw error if it is.
//        if (hierarchy == null || hierarchy.getHierarchy()==null) {
//            throw new EaglsDAOError("ERROR_HIERARCHY_ACCESS");
//        }
        map.put("userID", userID);
        String genericPaper = (params.getRag_generatePaper().
                equals("generatePaperNo")) ? "Y" : "N";
        map.put("rag_generatePaper", genericPaper);
        boolean card = (params.getHdn_card().equals("card"));
        map.put("hdn_card", new Boolean(card));

        //make accountExpirationDate string a date object
        java.util.Date expDate = new java.util.Date();
        try {
            expDate = DisplayDateUtil.convertStringToExpiry(
                    params.getTxt_accountExpireDate());
        } catch (NumberFormatException e) {
            logger.error("format exception on date", e);
        }
        map.put("txt_accountExpireDate", DateUtil.convertExpiryToString(expDate));
        map.put("hdn_centralId", params.getHdn_centralId());
        map.put("hdn_centralName", params.getHdn_centralName());

        short progType = 0;
        String programType = params.getHdn_programType();

        if (programType != null && programType.
                equalsIgnoreCase("PURCHASE"))
            progType = Account.PURCHASE;
        else if (programType != null && programType.
                equalsIgnoreCase("TRAVEL"))
            progType = Account.TRAVEL;
        else if (programType != null && programType.
                equalsIgnoreCase("FLEET"))
            progType = Account.FLEET;
        else if (programType != null && programType.
                equalsIgnoreCase("INTEGRATED"))
            progType = Account.INTEGRATE;
        else if (programType != null && programType.
                equalsIgnoreCase("INTERAGENCY"))
            progType = Account.INTERAGENCY;
        map.put("hdn_programType", new Short(progType));

        map.put("hdn_centralNumber", params.getHdn_centralNumber());
        map.put("txt_accountingCenterId", params.getTxt_accountingCenterId());
        map.put("txt_accountingCode", params.getTxt_accountingCode());

        /*map.put("hdn_billingType",
                new Short(Short.parseShort(params.getHdn_billingType(), 10)));*/

        if ("CENTRAL".equals(params.getHdn_billingType())) {
           map.put("hdn_billingType", "C");
        }
        else if ("INDIVIDUAL".equals(params.getHdn_billingType())) {
           map.put("hdn_billingType", "I");
        }

        map.put("cmb_cardType", params.getCmb_cardType());
        map.put("txt_equipmentType", params.getTxt_equipmentType());
        map.put("txt_equipmentId", params.getTxt_equipmentId());

        //Temporary fix so that a dB insert can occur -- the default values from HTML are illegal
        if (!(params.getCmb_equipFuelLow().
                equals("equipFuelLowTransmit"))) {
            String temp = params.getCmb_equipFuelLow();
            try {
//                temp = temp.substring(0, temp.indexOf("+"));
                temp = temp.substring(0, 3);
            } catch (StringIndexOutOfBoundsException e) {

            }
            map.put("cmb_equipFuelLow", temp);
        }
        if (!(params.getCmb_equipFuelHigh().
                equals("equipFuelHighTransmit"))) {
            String temp = params.getCmb_equipFuelHigh();
            try {
//                temp = temp.substring(0, temp.indexOf("+"));
                temp = temp.substring(0, 3);
            } catch (StringIndexOutOfBoundsException e) {}
            map.put("cmb_equipFuelHigh", temp);
        }


        map.put("txt_addressLine1", params.getTxt_addressLine1());
        map.put("txt_addressLine2", params.getTxt_addressLine2());
        map.put("txt_addressLine3",params.getTxt_addressLine3());
        map.put("txt_addressLine4", params.getTxt_addressLine4());
        map.put("txt_city", params.getTxt_city());
        map.put("txt_state",params.getTxt_state());
        map.put("txt_zip", params.getTxt_zip());
        map.put("txt_country", params.getTxt_country());

        String tempCredit = params.getTxt_creditLimit();
        tempCredit = "$" + tempCredit;
        map.put("txt_creditLimit",
                new Double(currencyToDouble(tempCredit)));
        Connection conn = null;

        try {
            conn = getPooledConnection();
            //conn.setAutoCommit(false);
            MastercardVehicleDAO adapter = new MastercardVehicleDAO();
            adapter.sendMastercardVehicle(conn, map);
            //conn.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            /*try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.error(e1.getMessage(), e1);
                throw new EaglsDAOError();
            }   */
            throw new EaglsDAOError(e.getMessage());
        } finally {
            closeConnection(conn);
        }
    }

}
