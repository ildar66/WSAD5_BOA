package com.boa.eagls.government.dto.agency;

import org.apache.log4j.Logger;

/**
 * <p><small> VDI Company, 18.07.2003 15:04:04</small></p>
 * @author StanislavS
 */


public class AgencyDTO {
    private static Logger log4j = Logger.getLogger(AgencyDTO.class);
    private String Agency_Name;
    private String Account_Nbr1;
    private String Account_Nbr2;
    private String Prog_Type;
    private String Last_Name;
    private String First_Name;
    private String Stmt_Date;
    private String Billing_Type;
    private String Account_Type;
    private String Accounting_Center_Id;

    public String getAgency_Name() {
        return Agency_Name;
    }

    public void setAgency_Name(String agency_Name) {
        Agency_Name = agency_Name;
    }

    public String getAccount_Nbr1() {
        return Account_Nbr1;
    }

    public void setAccount_Nbr1(String account_Nbr1) {
        Account_Nbr1 = account_Nbr1;
    }

    public String getAccount_Nbr2() {
        return Account_Nbr2;
    }

    public void setAccount_Nbr2(String account_Nbr2) {
        Account_Nbr2 = account_Nbr2;
    }

    public String getProg_Type() {
        return Prog_Type;
    }

    public void setProg_Type(String prog_Type) {
        Prog_Type = prog_Type;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getStmt_Date() {
        return Stmt_Date;
    }

    public void setStmt_Date(String stmt_Date) {
        Stmt_Date = stmt_Date;
    }

    public String getBilling_Type() {
        return Billing_Type;
    }

    public void setBilling_Type(String billing_Type) {
        Billing_Type = billing_Type;
    }

    public String getAccount_Type() {
        return Account_Type;
    }

    public void setAccount_Type(String account_Type) {
        Account_Type = account_Type;
    }

    public String getAccounting_Center_Id() {
        return Accounting_Center_Id;
    }

    public void setAccounting_Center_Id(String accounting_Center_Id) {
        Accounting_Center_Id = accounting_Center_Id;
    }
}
