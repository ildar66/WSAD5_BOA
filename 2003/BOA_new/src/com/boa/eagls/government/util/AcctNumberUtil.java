/**
 * AcctNumberUtil
 */
package com.boa.eagls.government.util;

/**
 * Utility class for Account Number
 * <p>Title: </p> <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class AcctNumberUtil
{

    /**
     * Account Number
     */
    public String acctNumber;

    /**
     * Default Constructor
     */
    public AcctNumberUtil()
    {
        acctNumber = new String();
    }

    /**
     * getAcctNumber
     * @return String
     */
    public String getAcctNumber()
    {
        return acctNumber;
    }

    /**
     * setAcctNumber
     * @param acctNumber
     */
    public void setAcctNumber(String acctNumber)
    {
        this.acctNumber = acctNumber;
    }

}
