package com.boa.eagls.government.controller.formbean.inquiry;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 08.07.2003
 * Time: 17:58:44
 * To change this template use Options | File Templates.
 */
public interface IPointOfContact {
    boolean getTdo();

    void setTdo(boolean tdo);

    boolean getDbo();

    void setDbo(boolean dbo);

    boolean getEcedi();

    void setEcedi(boolean ecedi);

    boolean getAopc();

    void setAopc(boolean aopc);

    boolean getPaymentOffice();

    void setPaymentOffice(boolean paymentOffice);

    boolean getPriTDO();

    void setPriTDO(boolean priTDO);

    boolean getPriDBO();

    void setPriDBO(boolean priDBO);

    boolean getPriECEDI();

    void setPriECEDI(boolean priECEDI);

    boolean getPriAOPC();

    void setPriAOPC(boolean priAOPC);

    boolean getPriPaymentOffice();

    void setPriPaymentOffice(boolean priPaymentOffice);

    String getHomePhone();

    void setHomePhone(String homePhone);

    String getNewsletterMedium();

    void setNewsletterMedium(String newsletterMedium);

    String getTaskOrderNo();

    void setTaskOrderNo(String taskOrderNo);

    String getLastName();

    void setLastName(String lastName);

    String getFirstName();

    void setFirstName(String firstName);

    String getAddress1();

    void setAddress1(String address1);

    String getAddress2();

    void setAddress2(String address2);

    String getAddress3();

    void setAddress3(String address3);

    String getAddress4();

    void setAddress4(String address4);

    String getCity();

    void setCity(String city);

    String getState();

    void setState(String state);

    String getCountry();

    void setCountry(String country);

    String getZip();

    void setZip(String zip);

    String getWorkPhone();

    void setWorkPhone(String workPhone);

    String getFax();

    void setFax(String fax);

    String geteMail();

    void seteMail(String eMail);

    String getCentralAccountNbr();

    void setCentralAccountNbr(String centralAccountNbr);
}
