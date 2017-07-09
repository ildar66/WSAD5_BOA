package com.boa.eagls.government.dto.account;

import org.apache.log4j.Logger;
import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * <code>This is the container object for account transaction summary for an account
 * inquiry. This is used for Individual and Mastercard Vehicle accounts.</code>
 * <p><small> VDI Company, 30.07.2003 15:34:30</small></p>
 * @author StanislavS
 */


public class AccountTransactionSummary {
    private static Logger log4j = Logger.getLogger(AccountTransactionSummary.class);
//AcctTransSummary or Account Transaction Summary is a part of Inquiry
//The data elements for transactions on a specific account: Mastercard Vehicle
//or individual are described in AcctTransSummary.
//CentralAcctTransSummary extends from this class and is used in Central Account Inquiry.

    public class AcctTransSummary extends BusinessObject implements Serializable {
        //The attributes otherwise noted map to screen elements described in SDRs.

        private double currentBalance;     //this will be used as statement or invoice balance
        private short pastDueDays;
        private double pastDueAmt;
        private double totalDisputesAmt;   //total amount in dispute

        /**
         Total Pending Authorizations used in calculating available credit. This used
         to be a long but is now (1/28/1999) a currency (double).
         */
        private double pendingAuths = DOUBLE_DEFAULT;


        //total pending authorizations, received from TSYS via MQ, used in calculating avail credit

        private long availCredit;
        private char moneyAvailSign = 'a';       //used in calculating available credit
        private long moneyAvailPad;        //usee in caluclation avaiable credit/
        private double paymentDue;
        private String paymentDueDate;
        private String lastPaymentDate;
//    private Date lastPaymentDate;

        private double lastPaymentAmt;
        private double amtDebitTransCC;      //Total Amount of Debit Transactions Current Cycle
        private double amtDebitTransPC;      //Total Amount of Debit Transactions Previous Cycle
        private double amtCreditTransCC;     //Total Amount of Credit Transactions Current Cycle
        private double amtCreditTransPC;     //Total Amount of Credit Transactions Previous Cycle
        private double totalAmtTransCC;      //Total Amount of Transactions Current Cycle
        private double totalAmtTransPC;      //Total Amount of Transations Previous Cycle
        private double cenBilledTrans;       //Total Amount of Centrally Billed Transactions Current Cycle
        private double indBilledTrans;       //Total Amount of Individually Billed Transactions Current Cycle

//----------------------------------------Constructor-------------------------------------------

        public AcctTransSummary() {
        }

        public AcctTransSummary(AcctTransSummary tAcctTransSummary) {
            setCurrentBalance(tAcctTransSummary.getCurrentBalance());      //this is uses as Individual Billing statement Balance.
            setPastDueDays(tAcctTransSummary.getPastDueDays());
            setPastDueAmt(tAcctTransSummary.getPastDueAmt());
            setTotalDisputesAmt(tAcctTransSummary.getTotalDisputesAmt());   //total amount in dispute
            setPendingAuths(tAcctTransSummary.getPendingAuths());         //total pending authorizations, received from TSYS via MQ, used in calculating avail credit
            setAvailCredit(tAcctTransSummary.getAvailCredit());
            setMoneyAvailSign(tAcctTransSummary.getMoneyAvailSign());       //used in calculating available credit
            setMoneyAvailPad(tAcctTransSummary.getMoneyAvailPad());        //usee in caluclation avaiable credit/
            setPaymentDue(tAcctTransSummary.getPaymentDue());
            setPaymentDueDate(tAcctTransSummary.getPaymentDueDate());
            setLastPaymentDate(tAcctTransSummary.getLastPaymentDate());
            setLastPaymentAmt(tAcctTransSummary.getLastPaymentAmt());
            setAmtDebitTransCC(tAcctTransSummary.getAmtDebitTransCC());      //Total Amount of Debit Transactions Current Cycle
            setAmtDebitTransPC(tAcctTransSummary.getAmtDebitTransPC());      //Total Amount of Debit Transactions Previous Cycle
            setAmtCreditTransCC(tAcctTransSummary.getAmtCreditTransCC());     //Total Amount of Credit Transactions Current Cycle
            setAmtCreditTransPC(tAcctTransSummary.getAmtCreditTransPC());     //Total Amount of Credit Transactions Previous Cycle
            setTotalAmtTransCC(tAcctTransSummary.getTotalAmtTransCC());      //Total Amount of Transactions Current Cycle
            setTotalAmtTransPC(tAcctTransSummary.getTotalAmtTransPC());      //Total Amount of Transations Previous Cycle
            setCenBilledTrans(tAcctTransSummary.getCenBilledTrans());        //Total Amount of Centrally Billed Transactions Current Cycle
            setIndBilledTrans(tAcctTransSummary.getIndBilledTrans());        //Total Amount of Individually Billed Transactions Current Cycle
        }

//--------------------------------------get methods-----------------------------------------------


        public double getCurrentBalance() {
            return currentBalance;
        }

        public void setCurrentBalance(double currentBalance) {
            this.currentBalance = currentBalance;
        }

        public short getPastDueDays() {
            return pastDueDays;
        }

        public void setPastDueDays(short pastDueDays) {
            this.pastDueDays = pastDueDays;
        }

        public double getPastDueAmt() {
            return pastDueAmt;
        }

        public void setPastDueAmt(double pastDueAmt) {
            this.pastDueAmt = pastDueAmt;
        }

        public double getTotalDisputesAmt() {
            return totalDisputesAmt;
        }

        public void setTotalDisputesAmt(double totalDisputesAmt) {
            this.totalDisputesAmt = totalDisputesAmt;
        }

        public double getPendingAuths() {
            return pendingAuths;
        }

        public void setPendingAuths(double pendingAuths) {
            this.pendingAuths = pendingAuths;
        }

        public long getAvailCredit() {
            return availCredit;
        }

        public void setAvailCredit(long availCredit) {
            this.availCredit = availCredit;
        }

        public char getMoneyAvailSign() {
            return moneyAvailSign;
        }

        public void setMoneyAvailSign(char moneyAvailSign) {
            this.moneyAvailSign = moneyAvailSign;
        }

        public long getMoneyAvailPad() {
            return moneyAvailPad;
        }

        public void setMoneyAvailPad(long moneyAvailPad) {
            this.moneyAvailPad = moneyAvailPad;
        }

        public double getPaymentDue() {
            return paymentDue;
        }

        public void setPaymentDue(double paymentDue) {
            this.paymentDue = paymentDue;
        }

        public String getPaymentDueDate() {
            return paymentDueDate;
        }

        public void setPaymentDueDate(String paymentDueDate) {
            this.paymentDueDate = paymentDueDate;
        }

        public String getLastPaymentDate() {
            return lastPaymentDate;
        }

        public void setLastPaymentDate(String lastPaymentDate) {
            this.lastPaymentDate = lastPaymentDate;
        }

        public double getLastPaymentAmt() {
            return lastPaymentAmt;
        }

        public void setLastPaymentAmt(double lastPaymentAmt) {
            this.lastPaymentAmt = lastPaymentAmt;
        }

        public double getAmtDebitTransCC() {
            return amtDebitTransCC;
        }

        public void setAmtDebitTransCC(double amtDebitTransCC) {
            this.amtDebitTransCC = amtDebitTransCC;
        }

        public double getAmtDebitTransPC() {
            return amtDebitTransPC;
        }

        public void setAmtDebitTransPC(double amtDebitTransPC) {
            this.amtDebitTransPC = amtDebitTransPC;
        }

        public double getAmtCreditTransCC() {
            return amtCreditTransCC;
        }

        public void setAmtCreditTransCC(double amtCreditTransCC) {
            this.amtCreditTransCC = amtCreditTransCC;
        }

        public double getAmtCreditTransPC() {
            return amtCreditTransPC;
        }

        public void setAmtCreditTransPC(double amtCreditTransPC) {
            this.amtCreditTransPC = amtCreditTransPC;
        }

        public double getTotalAmtTransCC() {
            return totalAmtTransCC;
        }

        public void setTotalAmtTransCC(double totalAmtTransCC) {
            this.totalAmtTransCC = totalAmtTransCC;
        }

        public double getTotalAmtTransPC() {
            return totalAmtTransPC;
        }

        public void setTotalAmtTransPC(double totalAmtTransPC) {
            this.totalAmtTransPC = totalAmtTransPC;
        }

        public double getCenBilledTrans() {
            return cenBilledTrans;
        }

        public void setCenBilledTrans(double cenBilledTrans) {
            this.cenBilledTrans = cenBilledTrans;
        }

        public double getIndBilledTrans() {
            return indBilledTrans;
        }

        public void setIndBilledTrans(double indBilledTrans) {
            this.indBilledTrans = indBilledTrans;
        }
    }
}