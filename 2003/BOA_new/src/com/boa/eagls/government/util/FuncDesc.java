/**
 * FuncDesc
 */

package com.boa.eagls.government.util;

/**
 * FuncDesc
 *
 *
 * @author
 * @version %I%, %G%
 */
public class FuncDesc
{

    /**
     * Function Name
     */
    protected String fName;

    /**
     * Function graphical name
     */
    protected String fGrpName;

    /**
     * Function's url
     */
    protected String url;

    /**
     * Private string for request pre tag
     */
    private static final String rqstPreTag = "/";

    /**
     * Constructor
     * @param grp
     * @param name
     * @param rqst
     * @param param
     */
    public FuncDesc(String grp, String name, String rqst, String param)
    {
        fName = name;
        fGrpName = grp;
        url = rqst + '?' + param;
    }

    /**
     * Overloaded constructor
     * @param grp
     * @param name
     * @param rqst
     */
    public FuncDesc(String grp, String name, String rqst)
    {
        fName = name;
        fGrpName = grp;
        url = rqstPreTag + rqst;
    }

    /**
     * Overloaded constructor
     * @param grp
     * @param name
     * @param rqst
     * @param k
     */
    public FuncDesc(String grp, String name, String rqst, int k)
    {
        fName = name;
        fGrpName = grp;
        url = rqst;
    }

    /**
     * Convert to String array
     * @return String[]
     */
    public String[] convertToSArray()
    {
        String[] retVal = new String[3];

        retVal[0] = new String(fName);
        retVal[1] = new String(fGrpName);
        retVal[2] = new String(url);
        return retVal;
    }

}
