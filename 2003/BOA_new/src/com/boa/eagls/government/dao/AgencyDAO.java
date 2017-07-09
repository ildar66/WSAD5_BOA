package com.boa.eagls.government.dao;

import com.boa.eagls.government.dto.CentralOffice;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;

import java.sql.*;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * DAO for Agency and related entities
 * @author OlegK
 * Date: 09.07.2003
 * Time: 19:38:25
 */
public class AgencyDAO extends DAOBaseNew {
    private static final Logger logger= Logger.getLogger(AgencyDAO.class);

    public CentralOffice retrieveCentralOfficeDetail(Connection conn, int aHierarchy[], int aAgencyID,
                                                     boolean isSetup, String aUserId)
            throws SQLException, EaglsDAOError {
//        log("RetrieveCentralOfficeDetail");
//Get userID
//        String aUserId = transport.getUserID();

        CentralOffice centralOffice = new CentralOffice();
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall(SP_RETRIEVE_CENTRAL_OFFICE_DETAIL);
            for (int i = 0; i < aHierarchy.length; i++) {
                cs.setInt(QUERY_hl0 + i, aHierarchy[i]);//":hl" + i
            }
            cs.setInt(QUERY_agencyID, aAgencyID);//":agencyID"
            cs.setString(QUERY_userID, aUserId);

            String validateFlag = "";
            if (isSetup)
                validateFlag = "Y";
            else
                validateFlag = "N";
            cs.setString(QUERY_isSetup, validateFlag);

            cs.registerOutParameter(QUERY_hierarchyDescription, Types.VARCHAR);
            cs.registerOutParameter(QUERY_agencyName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_centralOfficeName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_address1, Types.VARCHAR);
            cs.registerOutParameter(QUERY_address2, Types.VARCHAR);
            cs.registerOutParameter(QUERY_address3, Types.VARCHAR);
            cs.registerOutParameter(QUERY_address4, Types.VARCHAR);
            cs.registerOutParameter(QUERY_city, Types.VARCHAR);
            cs.registerOutParameter(QUERY_state, Types.VARCHAR);
            cs.registerOutParameter(QUERY_country, Types.VARCHAR);
            cs.registerOutParameter(QUERY_zip, Types.VARCHAR);
            cs.registerOutParameter(QUERY_businessPhone, Types.VARCHAR);
            cs.registerOutParameter(QUERY_fax, Types.VARCHAR);
            cs.registerOutParameter(QUERY_email, Types.VARCHAR);
            cs.registerOutParameter(QUERY_allocTransTimeLimit, Types.INTEGER);
            cs.registerOutParameter(QUERY_oResult, Types.VARCHAR);
            for (int i = 0; i < 9; i++) {
                cs.registerOutParameter(QUERY_HLD0 + i, Types.VARCHAR);//":HLD" + i
            }
//            log("Before stored procedure...");
            cs.execute();
            //**********************************************************************************
            //   Set Hierarchy params
            //**********************************************************************************
            //Construct Hierarchy Array
            HierarchyDTO[] ha = new HierarchyDTO[9];
            for (int i = 0; i < 9; i++) {
                //Construct Hierarchy
                HierarchyDTO h = new HierarchyDTO();
                //Set attributes
                h.setLevel((short) i);
                h.setNumber(aHierarchy[i]);
                String str = cs.getString(QUERY_HLD0 + i);// output.getString(":HLD" + i);
                if (str != null) {
                    h.setDescription(str);
                    h.setAgencyName(str);
                }
                //h.setDescription((output.getString(":HLD" + i).equals("~"))?"":output.getString(":HLD" + i));
                //Add to array
                ha[i] = h;
            }

            //**********************************************************************************
            //   Set CentralOffice params
            //**********************************************************************************
            //Construct Central Office object
            centralOffice.setHierarchy(ha);
            centralOffice.setLastName(cs.getString(QUERY_centralOfficeName));
            centralOffice.setAddress1(cs.getString(QUERY_address1));
            centralOffice.setAddress2(cs.getString(QUERY_address2));
            centralOffice.setAddress3(cs.getString(QUERY_address3));
            centralOffice.setAddress4(cs.getString(QUERY_address4));
            String city = cs.getString(QUERY_city);
            if (city != null) {
                city = city.trim();
            }
            centralOffice.setCity(city);
            String state = cs.getString(QUERY_state);
            if (state != null) {
                state = state.trim();
            }
            centralOffice.setStateOrProvince(state);
            String country = cs.getString(QUERY_country);
            if (country != null) {
                country = country.trim();
            }
            centralOffice.setCountry(country);
            String zip = cs.getString(QUERY_zip);
            if (zip != null) {
                zip = zip.trim();
            }
            centralOffice.setZip(zip);
            centralOffice.setBusinessPhone(cs.getString(QUERY_businessPhone));
            centralOffice.setFax(cs.getString(QUERY_fax));
            //Bug Fix for the purpose of setting the allocTransTimeLimit,
            centralOffice.setFirstName(cs.getString(QUERY_allocTransTimeLimit));
            centralOffice.setEMail(cs.getString(QUERY_email));
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (cs != null) cs.close();
        }
        return centralOffice;
    }

    private final static String SP_RETRIEVE_CENTRAL_OFFICE_DETAIL =
            "{call AGENCY_INQUIRY.GET_CENTRAL_OFFICE_DETAIL(" +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, " +
            "?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?, ?, ?" +
            ")}";
    //"hl0, hl1, hl2, hl3, hl4, hl5, hl6, hl7, hl8, agencyID, userID, isSetup, " +
//            "hierarchyDescription, agencyName, centralOfficeName, " +
//            "address1, address2, address3, address4, " +
//            "city, state, country, zip, businessPhone, fax, email, allocTransTimeLimit, oResult," +
//            "HLD0, HLD1, HLD2, HLD3, HLD4, HLD5, HLD6, HLD7, HLD8" +
//            ")}";

    private final static int QUERY_hl0 = 1;
    private final static int QUERY_hl1 = 2;
    private final static int QUERY_hl2 = 3;
    private final static int QUERY_hl3 = 4;
    private final static int QUERY_hl4 = 5;
    private final static int QUERY_hl5 = 6;
    private final static int QUERY_hl6 = 7;
    private final static int QUERY_hl7 = 8;
    private final static int QUERY_hl8 = 9;
    private final static int QUERY_agencyID = 10;
    private final static int QUERY_userID = 11;
    private final static int QUERY_isSetup = 12;
    private final static int QUERY_hierarchyDescription = 13;
    private final static int QUERY_agencyName = 14;
    private final static int QUERY_centralOfficeName = 15;
    private final static int QUERY_address1 = 16;
    private final static int QUERY_address2 = 17;
    private final static int QUERY_address3 = 18;
    private final static int QUERY_address4 = 19;
    private final static int QUERY_city = 20;
    private final static int QUERY_state = 21;
    private final static int QUERY_country = 22;
    private final static int QUERY_zip = 23;
    private final static int QUERY_businessPhone = 24;
    private final static int QUERY_fax = 25;
    private final static int QUERY_email = 26;
    private final static int QUERY_allocTransTimeLimit = 27;
    private final static int QUERY_oResult = 28;
    private final static int QUERY_HLD0 = 29;
    private final static int QUERY_HLD1 = 30;
    private final static int QUERY_HLD2 = 31;
    private final static int QUERY_HLD3 = 32;
    private final static int QUERY_HLD4 = 33;
    private final static int QUERY_HLD5 = 34;
    private final static int QUERY_HLD6 = 35;
    private final static int QUERY_HLD7 = 36;
    private final static int QUERY_HLD8 = 37;
}
