package com.boa.eagls.government.dto.pointOfContact;

import com.boa.eagls.government.dto.DTOBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;


/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 30.06.2003
 * Time: 18:17:05
 * To change this template use Options | File Templates.
 */

public class PointOfContactDTO extends DTOBase {
       //The attributes otherwise noted map to screen elements described in SDRs.
    public static final String CENTRAL_ACCOUNT_ID_CN = "AGENCY_BILL_NBR";
    public static final String TDO_CN = "TYPE_TDO";
    public static final String DBO_CN = "TYPE_DBO";
    public static final String ECEDI_CN = "TYPE_ECEDI";
    public static final String AOPC_CN = "TYPE_AOPC";
    public static final String PAYMENT_OFFICE_CN = "TYPE_PAYMENT";
    public static final String PRIMARY_TDO_CN = "TYPE_TDO_FLAG";
    public static final String PRIMARY_DBO_CN = "TYPE_DBO_FLAG";
    public static final String PRIMARY_ECEDI_CN = "TYPE_ECEDI_FLAG";
    public static final String PRIMARY_AOPC_CN = "TYPE_AOPC_FLAG";
    public static final String PRIMARY_PAYMENT_OFFICE_CN = "TYPE_PAYMENT_FLAG";
    public static final String FIRST_NAME_CN = "FIRST_NAME";
    public static final String LAST_NAME_CN = "LAST_NAME";
    public static final String ADDRESS_1_CN = "ADDRESS1";
    public static final String ADDRESS_2_CN = "ADDRESS2";
    public static final String ADDRESS_3_CN = "ADDRESS3";
    public static final String ADDRESS_4_CN = "ADDRESS4";
    public static final String CITY_CN = "CITY";
    public static final String STATE_CN = "STATE";
    public static final String COUNTRY_CN = "COUNTRY";
    public static final String ZIP_CN = "ZIP";
    public static final String WORK_PHONE_CN = "WORK_PHONE";
    public static final String HOME_PHONE_CN = "HOME_PHONE";
    public static final String FAX_CN = "FAX";
    public static final String EMAIL_CN = "EMAIL";
    public static final String NEWSLATTER_CN = "NEWSLATTER_FLAG";
    public static final String TASK_ORDER_NUMBER_CN = "TASK_ORDER_NUMBER";
    public static final String HIERARCHY_CN = "HIERARCHI_NBR";
    public static final String TRUE_STRING = "Y";
    public static final String FALSE_STRING = "N";

    //the following is used for newsletterMedium
    public static final char HARDCOPY = 'H';
    public static final char ELECTRONIC = 'E';


    public int[] hierarchy = INT_ARRAY_DEFAULT;
    public boolean tdo = BOOLEAN_DEFAULT;
    public boolean dbo = BOOLEAN_DEFAULT;
    public boolean ecedi = BOOLEAN_DEFAULT;
    public boolean aopc = BOOLEAN_DEFAULT;
    public boolean paymentOffice = BOOLEAN_DEFAULT;
    public boolean priTDO = BOOLEAN_DEFAULT;         //pri = primary
    public boolean priDBO = BOOLEAN_DEFAULT;
    public boolean priECEDI = BOOLEAN_DEFAULT;
    public boolean priAOPC = BOOLEAN_DEFAULT;
    public boolean priPaymentOffice = BOOLEAN_DEFAULT;
    public String homePhone = STRING_DEFAULT;
    public String newsletterMedium = "E";//electronic
    public String taskOrderNo = STRING_DEFAULT;
    public String rowId = STRING_DEFAULT;

    private String lastName = STRING_DEFAULT;
    private String firstName = STRING_DEFAULT;
    private String address1 = STRING_DEFAULT;
    private String address2 = STRING_DEFAULT;
    private String address3 = STRING_DEFAULT;
    private String address4 = STRING_DEFAULT;
    private String city = STRING_DEFAULT;
    private String state = STRING_DEFAULT;
    private String country = STRING_DEFAULT;
    private String zip = STRING_DEFAULT;
    private String workPhone = STRING_DEFAULT;
    private String fax = STRING_DEFAULT;
    private String eMail = STRING_DEFAULT;
    private String centralAccountNbr = STRING_DEFAULT;


//----------------------------------------Constructor-------------------------------------------

    public PointOfContactDTO() {
    }


    public PointOfContactDTO(PointOfContactDTO tPointOfContactDTO) {
        for (int i = 0; i < HIERARCHY_LIMIT; i++) {
            hierarchy[i] = tPointOfContactDTO.getAHierarchy()[i];
        }
        setTDO(tPointOfContactDTO.getTDO());
        setDBO(tPointOfContactDTO.getDBO());
        setECEDI(tPointOfContactDTO.getECEDI());
        setAOPC(tPointOfContactDTO.getAOPC());
        setPaymentOffice(tPointOfContactDTO.getPaymentOffice());
        setPriTDO(tPointOfContactDTO.getPriTDO());         //pri = primary
        setPriDBO(tPointOfContactDTO.getPriDBO());
        setPriECEDI(tPointOfContactDTO.getPriECEDI());
        setPriAOPC(tPointOfContactDTO.getPriAOPC());
        setPriPaymentOffice(tPointOfContactDTO.getPriPaymentOffice());
        setHomePhone(tPointOfContactDTO.getHomePhone());
        setNewsletterMedium(tPointOfContactDTO.getNewsletterMedium());
        setTaskOrderNo(tPointOfContactDTO.getTaskOrderNo());
        setRowId(tPointOfContactDTO.getRowId());
    }


//--------------------------------------get methods-----------------------------------------------

    public int[] getAHierarchy() {
        return hierarchy;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public boolean getTDO() {
        return tdo;
    }

    public boolean getDBO() {
        return dbo;
    }

    public boolean getECEDI() {
        return ecedi;
    }

    public boolean getAOPC() {
        return aopc;
    }

    public boolean getPaymentOffice() {
        return paymentOffice;
    }

    public boolean getPriTDO() {
        return priTDO;
    }

    public boolean getPriDBO() {
        return priDBO;
    }

    public boolean getPriECEDI() {
        return priECEDI;
    }

    public boolean getPriAOPC() {
        return priAOPC;
    }

    public boolean getPriPaymentOffice() {
        return priPaymentOffice;
    }

    public String getNewsletterMedium() {
        return newsletterMedium;
    }

    public String getTaskOrderNo() {
        return new String(taskOrderNo);
    }

    public String getRowId() {
        return new String(rowId);
    }

//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in CentralAcctPOC.

    public void setHierarchy(int[] tHierarchy) {
        hierarchy = tHierarchy;
    }

    public void setTDO(boolean tTDO) {
        tdo = tTDO;
    }

    public void setTDO(Boolean tTDO) {
        tdo = tTDO.booleanValue(); //todo check validation
    }

    public void setDBO(boolean tDBO) {
        dbo = tDBO;
    }

    public void setDBO(Boolean tDBO) {
        dbo = tDBO.booleanValue(); //todo check validation
    }

    public void setECEDI(boolean tECEDI) {
        ecedi = tECEDI;
    }

    public void setECEDI(Boolean tECEDI) {
        ecedi = tECEDI.booleanValue(); //todo check validation
    }

    public void setAOPC(boolean tAOPC) {
        aopc = tAOPC;
    }

    public void setAOPC(Boolean tAOPC) {
        aopc = tAOPC.booleanValue(); //todo check validation
    }

    public void setPaymentOffice(boolean tPaymentOffice) {
        paymentOffice = tPaymentOffice;
    }

    public void setPaymentOffice(Boolean tPaymentOffice) {
        paymentOffice = tPaymentOffice.booleanValue(); //todo check validation
    }

    public void setPriTDO(boolean tPriTDO) {
        priTDO = tPriTDO;
    }

    public void setPriTDO(Boolean tPriTDO) {
        priTDO = tPriTDO.booleanValue(); //todo check validation
    }

    public void setPriDBO(boolean tPriDBO) {
        priDBO = tPriDBO;
    }

    public void setPriDBO(Boolean tPriDBO) {
        priDBO = tPriDBO.booleanValue(); //todo check validation
    }

    public void setPriECEDI(boolean tPriECEDI) {
        priECEDI = tPriECEDI;
    }

    public void setPriECEDI(Boolean tPriECEDI) {
        priECEDI = tPriECEDI.booleanValue();//todo check validation
    }

    public void setPriAOPC(boolean tPriAOPC) {
        priAOPC = tPriAOPC;
    }

    public void setPriAOPC(Boolean tPriAOPC) {
        priAOPC = tPriAOPC.booleanValue();//todo check validation
    }

    public void setPriPaymentOffice(boolean tPriPaymentOffice) {
        priPaymentOffice = tPriPaymentOffice;
    }

    public void setPriPaymentOffice(Boolean tPriPaymentOffice) {
        priPaymentOffice = tPriPaymentOffice.booleanValue(); //todo check validation
    }

    public void setHomePhone(String tHomePhone) {
        homePhone = tHomePhone;//todo check validation
    }

    public void setNewsletterMedium(String tNewsletterMedium) {
        newsletterMedium = tNewsletterMedium;
    }

    public void setTaskOrderNo(String tTaskOrderNo) {
        taskOrderNo = tTaskOrderNo;//todo check validation
    }

    public void setRowId(String tRowId) {
        rowId = tRowId; //todo check validation
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void populateFromRS(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        String columnName;
        for (int i = 1; i <= md.getColumnCount(); i++) {
            columnName = md.getColumnName(i);
            if (TDO_CN.equalsIgnoreCase(columnName)) {
                setTDO(getBooleanFromString(rs.getString(i)));
            } else if (DBO_CN.equalsIgnoreCase(columnName)) {
                setDBO(getBooleanFromString(rs.getString(i)));
            } else if (ECEDI_CN.equalsIgnoreCase(columnName)) {
                setECEDI(getBooleanFromString(rs.getString(i)));
            } else if (ECEDI_CN.equalsIgnoreCase(columnName)) {
                setAOPC(getBooleanFromString(rs.getString(i)));
            } else if (PAYMENT_OFFICE_CN.equalsIgnoreCase(columnName)) {
                setPaymentOffice(getBooleanFromString(rs.getString(i)));
            } else if (PRIMARY_TDO_CN.equalsIgnoreCase(columnName)) {
                setPriTDO(getBooleanFromString(rs.getString(i)));
            } else if (PRIMARY_DBO_CN.equalsIgnoreCase(columnName)) {
                setPriDBO(getBooleanFromString(rs.getString(i)));
            } else if (PRIMARY_ECEDI_CN.equalsIgnoreCase(columnName)) {
                setPriECEDI(getBooleanFromString(rs.getString(i)));
            } else if (PRIMARY_AOPC_CN.equalsIgnoreCase(columnName)) {
                setPriAOPC(getBooleanFromString(rs.getString(i)));
            } else if (PRIMARY_AOPC_CN.equalsIgnoreCase(columnName)) {
                setPriAOPC(getBooleanFromString(rs.getString(i)));
            } else if (FIRST_NAME_CN.equalsIgnoreCase(columnName)) {
                setFirstName(rs.getString(i));
            } else if (LAST_NAME_CN.equalsIgnoreCase(columnName)) {
                setLastName(rs.getString(i));
            } else if (ADDRESS_1_CN.equalsIgnoreCase(columnName)) {
                setAddress1(rs.getString(i));
            } else if (ADDRESS_2_CN.equalsIgnoreCase(columnName)) {
                setAddress2(rs.getString(i));
            } else if (ADDRESS_3_CN.equalsIgnoreCase(columnName)) {
                setAddress3(rs.getString(i));
            } else if (ADDRESS_4_CN.equalsIgnoreCase(columnName)) {
                setAddress4(rs.getString(i));
            } else if (CITY_CN.equalsIgnoreCase(columnName)) {
                setCity(rs.getString(i));
            } else if (STATE_CN.equalsIgnoreCase(columnName)) {
                setState(rs.getString(i));
            } else if (COUNTRY_CN.equalsIgnoreCase(columnName)) {
                setCountry(rs.getString(i));
            } else if (ZIP_CN.equalsIgnoreCase(columnName)) {
                setZip(rs.getString(i));
            } else if (WORK_PHONE_CN.equalsIgnoreCase(columnName)) {
                setWorkPhone(rs.getString(i));
            } else if (HOME_PHONE_CN.equalsIgnoreCase(columnName)) {
                setHomePhone(rs.getString(i));
            } else if (FAX_CN.equalsIgnoreCase(columnName)) {
                setFax(rs.getString(i));
            } else if (EMAIL_CN.equalsIgnoreCase(columnName)) {
                seteMail(rs.getString(i));
            } else if (NEWSLATTER_CN.equalsIgnoreCase(columnName)) {
                setNewsletterMedium(rs.getString(i));
            } else if (TASK_ORDER_NUMBER_CN.equalsIgnoreCase(columnName)) {
                setTaskOrderNo(rs.getString(i));
            } else if (CENTRAL_ACCOUNT_ID_CN.equalsIgnoreCase(columnName)) {
                setCentralAccountNbr(rs.getString(i));
            } else if (AOPC_CN.equalsIgnoreCase(columnName)) {
                setAOPC(getBooleanFromString(rs.getString(i)));
            } else if (PRIMARY_PAYMENT_OFFICE_CN.equalsIgnoreCase(columnName)) {
                setPriPaymentOffice(getBooleanFromString(rs.getString(i)));
            }


        }
        //java.sql.Array hierarchyArray = rs.getArray(HIERARCHY_CN);
        // todo need to setHierarchy();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public static boolean getBooleanFromString(String value) {
        boolean res = false;
        if ("Y".equalsIgnoreCase(value)) {
            res = true;
        }
        return res;
    }

    public static String getStringFromBoolean(boolean value) {
        String res = FALSE_STRING;
        if (value) {
            res = TRUE_STRING;
        }
        return res;
    }

    public String getCentralAccountNbr() {
        return centralAccountNbr;
    }

    public void setCentralAccountNbr(String centralAccountNbr) {
        this.centralAccountNbr = centralAccountNbr;
    }
}


