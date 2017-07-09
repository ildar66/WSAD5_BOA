/**
 * HierarchyPTUtil
 */
package com.boa.eagls.government.util;

import java.util.*;

import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.HierarchyDTO;

/**
 * Hierarchy/ProgramType Util
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p> <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class HierarchyPTUtil
{
    public HierarchyDTO hierarchy[] = new HierarchyDTO[9];
    public Vector programType = null;

    /**
     * Default constructor
     */
    public HierarchyPTUtil()
    {
        programType = new Vector(5, 5);
    }

}
