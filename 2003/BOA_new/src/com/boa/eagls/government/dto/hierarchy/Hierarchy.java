package com.boa.eagls.government.dto.hierarchy;

import org.apache.log4j.Logger;
import com.boa.eagls.government.dto.HierarchyDTO;

/**
 * <code>This is the container object for an portion of a Hierarchy
 * It is always used as an array.  As an array it describes where
 * business object (agency, account, accounting center) level. </code>
 * <p><small> VDI Company, 30.07.2003 15:15:14</small></p>
 * @author StanislavS
 */


public class Hierarchy extends HierarchyDTO {
    public final static int HIERARCHY_LIMIT = 9;
    private static Logger log4j = Logger.getLogger(Hierarchy.class);
//Hierarchy is the storage object for all information concerning Hierarchy
//This class contains position, value, and description and will be used in an array.

    private short position;          //The hierarchy position ex: hl1 = 1
    private int value;                 //The hierarchy position's value
    private String description;     //The hierarchy value's description
    private String agencyName;      //This is put in just to be used in hierarchy browse
    private String[] parentAgencyNames = new String[9];

//----------------------------------------Constructor-------------------------------------------

    public Hierarchy() {
    }

    public Hierarchy(Hierarchy tHierarchy) {
        setPosition(tHierarchy.getPosition());
        setValue(tHierarchy.getValue());
        setDescription(tHierarchy.getDescription());
        setAgencyName(tHierarchy.getAgencyName());
    }

    public Hierarchy(short p, int v, String d) {
        position = p;
        value = v;
        if (d != null) {
            description = d;
            agencyName = d;
        } else {
            description = "";
            agencyName = "";
        }

    }

//--------------------------------------action methods---------------------------------------------

    //This is comparison method to compare two hierarchies
    public boolean equals(Hierarchy otherHierarchy) {
        // Hierarchy otherHierarchy = (Hierarchy)obj;
        return ((this.position == otherHierarchy.position) && (this.value == otherHierarchy.value));

    }

    public static int[] toIntArray(Hierarchy[] hierarchy) {
        int[] intArray = new int[hierarchy.length];
        if (hierarchy != null) {
            for (int i = 0; i < intArray.length; i++) {
                if (hierarchy[i] != null) {
                    intArray[i] = hierarchy[i].getValue();
                } else {
                    intArray[i] = 0;
                }
            }
        }
        return intArray;
    }

    public short getPosition() {
        return position;
    }

    public void setPosition(short position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String[] getParentAgencyNames() {
        return parentAgencyNames;
    }

    public void setParentAgencyNames(String[] parentAgencyNames) {
        this.parentAgencyNames = parentAgencyNames;
    }
}

