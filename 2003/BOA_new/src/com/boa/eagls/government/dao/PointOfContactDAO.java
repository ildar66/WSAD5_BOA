package com.boa.eagls.government.dao;

import com.boa.eagls.government.dao.DAOBaseNew;
import com.boa.eagls.government.dto.pointOfContact.PointOfContactDTO;
import com.boa.eagls.government.controller.formbean.inquiry.IPointOfContact;


import java.util.Collection;
import java.util.ArrayList;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.math.BigDecimal;

import org.apache.log4j.Logger;


/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 30.06.2003
 * Time: 18:12:42
 * To change this template use Options | File Templates.
 */
public class PointOfContactDAO extends DAOBaseNew {
    public static final Logger logger = Logger.getLogger(PointOfContactDAO.class);

    public final static int STRING_TYPE = 1;
    public final static int BOOL_CHAR_TYPE = 2;


    private final static String SQL_SEARCH_POINTS_OF_CONTACT =
            "SELECT * FROM GSA.POC";
    private final static String SQL_WHERE_CLAUSE = " WHERE ACCOUNT_NBR=";

    private static final String SP_CREATE_CENTRAL_ACCOUNT_POC_DETAIL =

    "POC_SETUP.ADD_POC_CENTRALACCOUNT   (?, ?, ?, ?, ?," +
                                        "?, ?, ?, ?, ?," +
                                        "?, ?, ?, ?, ?," +
                                        "?, ?, ?, ?, ?," +
                                        "?, ?, ?, ?, ?," +
                                        "?, ?, ?, ?, ?)";
    //"POC_SETUP.ADD_POC_CENTRALACCOUNT   (:userID, :centralAcctNo, :tdoFlag, :dboFlag, :ecediFlag, :aopcFlag, :poFlag, " +
    //":tdoPrimaryFlag, :dboPrimaryFlag, :ecediPrimaryFlag,:aopcPrimaryFlag, :poPrimaryFlag, " +
    //":firstName, :lastName, :address1, :address2, :address3, :address4, " +
    //":city, :state, :country, :zip, :businessPhone, :homePhone, :fax, :email, " +
    //":newsletterMedium, :taskOrderNumber, :oResult, :status " +
    //")";


    protected void setCharBooleanValue(CallableStatement st, int index, boolean value) throws SQLException {
        String inValue = "N";
        if (value) {
            inValue = "Y";
        }
        st.setString(index, inValue);
    }

    /**
     * Uppdates POC with values from formBean
     * @param con connection to DB
     * @param formBean form beam with new values
     * @throws java.sql.SQLException
     */
    public void updatePointOfContact(Connection con, IPointOfContact formBean) throws SQLException {

        //String strSQL;
        CallableStatement st = con.prepareCall(SP_CREATE_CENTRAL_ACCOUNT_POC_DETAIL);
        int i = 1;
        st.setString(i++, "NEWuserID"); //userID
        /** account number */
        st.setBigDecimal(i++, new BigDecimal(formBean.getCentralAccountNbr()));
        setCharBooleanValue(st, i++, formBean.getTdo()); //TDO
        setCharBooleanValue(st, i++, formBean.getDbo()); //DBO
        setCharBooleanValue(st, i++, formBean.getEcedi()); //EC/EDI
        setCharBooleanValue(st, i++, formBean.getAopc()); //A/OPC
        setCharBooleanValue(st, i++, formBean.getPaymentOffice()); //PO
        setCharBooleanValue(st, i++, formBean.getPriTDO()); //primary TDO
        setCharBooleanValue(st, i++, formBean.getPriDBO()); //primary DBO
        setCharBooleanValue(st, i++, formBean.getPriECEDI()); //primary EC/EDI
        setCharBooleanValue(st, i++, formBean.getPriAOPC()); //primary A/OPC
        setCharBooleanValue(st, i++, formBean.getPaymentOffice()); //primary PO
        st.setString(i++, formBean.getFirstName()); //first name
        st.setString(i++, formBean.getLastName()); //last name
        st.setString(i++, formBean.getAddress1()); //address1
        st.setString(i++, formBean.getAddress2()); //address1
        st.setString(i++, formBean.getAddress3()); //address1
        st.setString(i++, formBean.getAddress4()); //address1
        st.setString(i++, formBean.getCity()); //City
        st.setString(i++, formBean.getState()); //State
        st.setString(i++, formBean.getCountry()); //Country
        st.setString(i++, formBean.getZip()); //Zip
        st.setString(i++, formBean.getWorkPhone()); //Work phone
        st.setString(i++, formBean.getHomePhone()); //Home phone
        st.setString(i++, formBean.getFax()); //Fax
        st.setString(i++, formBean.geteMail()); //E mail
        st.setString(i++, formBean.getNewsletterMedium()); //Newslatter
        st.setString(i++, ""); //taskOrderNumber
        st.setString(i++, ""); //Newslatter
        //st.setString(i++, " "); //oResult
        st.registerOutParameter(i++, Types.VARCHAR);
        //st.setString(i++, " "); //status
        st.registerOutParameter(i++, Types.VARCHAR);
        st.execute();
        st.close();
    }

    /**
     * Selects list of POC of current account
     * @param con connection to DB
     * @param centralAcctID string with central account number
     * @return collecion with all //todo need to implements paging
     * @throws java.sql.SQLException
     */
    public Collection selectPointsOfContacts(Connection con, String centralAcctID) throws SQLException {
        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_POINTS_OF_CONTACT);

        if (!("".equalsIgnoreCase(centralAcctID))) {
            sqlStmt.append(SQL_WHERE_CLAUSE);
            sqlStmt.append(centralAcctID);
            sqlStmt.append("\n");
        }
        PreparedStatement st = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        try {
            //todo send SQL to logger
            logger.debug("SQL : " + sqlStmt);
            st = con.prepareStatement(sqlStmt.toString());
            res =  st.executeQuery();
            while(res.next()){
                PointOfContactDTO pointOfContact = new PointOfContactDTO();
                pointOfContact.populateFromRS(res);
                list.add(pointOfContact);
            }
            int i = 0;
        }finally{
            closeAll(res, st);
        }
        return list;
    }
}
