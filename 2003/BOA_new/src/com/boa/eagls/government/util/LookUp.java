/**
 * LookUp
 */
package com.boa.eagls.government.util;

/**
 * <p>Title: LookUp</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @version 1.0
 */

import java.util.Vector;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public final class LookUp
{
    public static Vector roles;
    public static Vector programTypes;

    /**
     * Method declaration
     *
     *
     * @param refresh
     *
     * @return
     */
    public static Vector getProgramTypes(boolean refresh)
    {
        if (programTypes == null || refresh)
        {
            programTypes = new Vector();
        }
        return programTypes;
    }

    /**
     * Method declaration
     *
     *
     * @param refresh
     *
     * @return
     */
    public static Vector getRoles(boolean refresh)
    {
        if (roles == null || refresh)
        {
            roles = new Vector();
        }
        return roles;
    }

}
