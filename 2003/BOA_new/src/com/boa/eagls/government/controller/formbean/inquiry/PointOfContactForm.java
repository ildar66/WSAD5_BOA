package com.boa.eagls.government.controller.formbean.inquiry;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 04.07.2003
 * Time: 17:52:07
 * To change this template use Options | File Templates.
 */
public class PointOfContactForm extends ActionForm implements IPointOfContact {
    private boolean tdo;
    private boolean dbo;
    private boolean ecedi;
    private boolean paymentOffice;
    private boolean aopc;
    private boolean priTDO;         //pri = primary
    private boolean priDBO;
    private boolean priECEDI;
    private boolean priPaymentOffice;
    private boolean priAOPC;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String city;
    private String state;
    private String country;
    private String zip;
    private String workPhone;
    private String homePhone;
    private String fax;
    private String eMail;
    private String newsletterMedium;
    private String taskOrderNo;
    private String centralAccountNbr;

    private final static String YES = "Yes";
    private final static String NO = "No";

    public boolean getTdo() {
        return tdo;
    }

    public static String stringValue(boolean value) {
        String res = NO;
        if (value) {
            res = YES;
        }
        return res;
    }

    public String getStrTdo() {
        return stringValue(tdo);
    }

    public void setTdo(boolean tdo) {
        this.tdo = tdo;
    }

    public boolean getDbo() {
        return dbo;
    }

    public String getStrDbo() {
        return stringValue(dbo);
    }

    public void setDbo(boolean dbo) {
        this.dbo = dbo;
    }

    public boolean getEcedi() {
        return ecedi;
    }

    public String getStrEcedi() {
        return stringValue(ecedi);
    }

    public void setEcedi(boolean ecedi) {
        this.ecedi = ecedi;
    }

    public boolean getAopc() {
        return aopc;
    }

    public String getStrAopc() {
        return stringValue(aopc);
    }

    public void setAopc(boolean aopc) {
        this.aopc = aopc;
    }

    public boolean getPaymentOffice() {
        return paymentOffice;
    }

    public String getStrPaymentOffice() {
        return stringValue(paymentOffice);
    }

    public void setPaymentOffice(boolean paymentOffice) {
        this.paymentOffice = paymentOffice;
    }

    public String getStrPriTDO() {
        return stringValue(priTDO);
    }

    public boolean getPriTDO() {
        return priTDO;
    }
    public void setPriTDO(boolean priTDO) {
        this.priTDO = priTDO;
    }

    public boolean getPriDBO() {
        return priDBO;
    }

    public String getStrPriDBO() {
        return stringValue(priDBO);
    }

    public void setPriDBO(boolean priDBO) {
        this.priDBO = priDBO;
    }

    public boolean getPriECEDI() {
        return priECEDI;
    }

    public String getStrPriECEDI() {
        return stringValue(priECEDI);
    }

    public void setPriECEDI(boolean priECEDI) {
        this.priECEDI = priECEDI;
    }

    public boolean getPriAOPC() {
        return priAOPC;
    }

    public String getStrPriAOPC() {
        return stringValue(priAOPC);
    }

    public void setPriAOPC(boolean priAOPC) {
        this.priAOPC = priAOPC;
    }

    public boolean getPriPaymentOffice() {
        return priPaymentOffice;
    }

    public String getStrPriPaymentOffice() {
        return stringValue(priPaymentOffice);
    }

    public void setPriPaymentOffice(boolean priPaymentOffice) {
        this.priPaymentOffice = priPaymentOffice;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getNewsletterMedium() {
        return newsletterMedium;
    }

    public String getNewsletter() {
        String ret = "Electronic";
        if (!"E".equalsIgnoreCase(newsletterMedium)) {
           ret = "Post Office";
        }
        return ret;
    }

    public void setNewsletterMedium(String newsletterMedium) {
        this.newsletterMedium = newsletterMedium;
    }

    public String getTaskOrderNo() {
        return taskOrderNo;
    }

    public void setTaskOrderNo(String taskOrderNo) {
        this.taskOrderNo = taskOrderNo;
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

    public String getCentralAccountNbr() {
        return centralAccountNbr;
    }

    public void setCentralAccountNbr(String centralAccountNbr) {
        this.centralAccountNbr = centralAccountNbr;
    }
}
