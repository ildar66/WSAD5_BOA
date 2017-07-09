/**
 * HierarchyDTO
 */
package com.boa.eagls.government.dto;

/**
 * Hierarchy DTO
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Oleg Klimenko
 * @version 1.0
 */
public class HierarchyDTO extends DTOBase {
    private short level;		 // The hierarchy position ex: hl1 = 1
    private int number;	 // The hierarchy position's value
    private String description = "";    // The hierarchy value's description
    private HierarchyDTO parent;
    private String agencyName = "";    // This is put in just to be used in hierarchy browse
    private String[] parentAgencyNames = new String[9];

    // ----------------------------------------Constructor-------------------------------------------

    /**
     * Constructor declaration
     *
     */
    public HierarchyDTO() {
    }

    /**
     * Constructor declaration
     *
     *
     * @param lvl
     * @param num
     * @param desc
     */
    public HierarchyDTO(short lvl, int num, String desc) {
        level = lvl;
        number = num;
        description = desc;
    }

    public HierarchyDTO(HierarchyDTO tHierarchy) {
        level = tHierarchy.level;
        number = tHierarchy.number;
        description = tHierarchy.description;
        agencyName = tHierarchy.agencyName;
    }

    // --------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from Hierarchy.

    /**
     * Method declaration
     *
     *
     * @return
     */
    public short getLevel() {
        return level;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getDescription() {
        return (description);
    }

    // --------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in Hierarchy.

    /**
     * Method declaration
     *
     *
     * @param tLevel
     */
    public void setLevel(short tLevel) {
        level = tLevel;
    }

    /**
     * Method declaration
     *
     *
     * @param tNum
     */
    public void setNumber(int tNum) {
        number = tNum;
    }

    /**
     * Method declaration
     *
     *
     * @param tDescription
     */
    public void setDescription(String tDescription) {
        description = tDescription;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO getParent() {
        return parent;
    }

    /**
     * Method declaration
     *
     *
     * @param hparent
     */
    public void setParent(HierarchyDTO hparent) {
        parent = hparent;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAgencyName() {
        return agencyName;
    }

    /**
     * Method declaration
     *
     *
     * @param agencyName
     */
    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    /**
     * Method declaration
     *
     *
     * @param pos
     * @param name
     */
    public void setParentAgencyName(int pos, String name) {
        if (name != null) {
            parentAgencyNames[pos] = name;
        }
    }

    /**
     * Method declaration
     *
     *
     * @param pos
     *
     * @return
     */
    public String getParentAgencyName(int pos) {
        String returnValue = STRING_DEFAULT;

        if (parentAgencyNames[pos] != null) {
            returnValue = parentAgencyNames[pos];
        }
        return returnValue;
    }

    // This is comparison method to compare two hierarchies

    /**
     * Method declaration
     *
     *
     * @param otherHierarchy
     *
     * @return
     */
    public boolean equals(HierarchyDTO otherHierarchy) {
        return ((this.level == otherHierarchy.level)
                && (this.number == otherHierarchy.number));
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getParentAgencyNames() {
        return parentAgencyNames;
    }

    /**
     * Method declaration
     *
     *
     * @param parentAgencyNames
     */
    public void setParentAgencyNames(String[] parentAgencyNames) {
        this.parentAgencyNames = parentAgencyNames;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getNumberAsString() {
        StringBuffer buf = new StringBuffer("");

        buf.append(number);
        while (buf.length() < 7) {
            buf.insert(0, 0);
        }
        return buf.toString();
    }

    public String toString() {
        return "level: " + level + ", number: " + number +
                "description: " + description +
                "agencyName: " + agencyName;
    }

}
