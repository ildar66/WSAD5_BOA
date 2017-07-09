/**
 * HierarchyService
 */

package com.boa.eagls.government.service.userprofile;

import com.boa.eagls.government.service.*;
import com.boa.eagls.government.service.userprofile.*;

import java.util.*;

import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.dao.*;
import com.boa.eagls.government.util.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dto.user.*;

import java.sql.Connection;

import com.boa.eagls.government.dao.HierarchyDAO;
import org.apache.log4j.Logger;

/**
 * <p>Title: HierarchyService </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @version 1.0
 */
public class HierarchyService extends ServiceBase {
    private static final Logger logger= Logger.getLogger(HierarchyService.class);
    HierarchyDAO hDao;
    UserHierarchyDTO hierarchyDTO;

    // ----------------------------------------Constructor-------------------------------------------


    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public HierarchyService(Connection connection) {
        setConnection(connection);
        hierarchyDTO = new UserHierarchyDTO();
        hierarchyDTO.setHierarchyCache(new Vector(10, 5));
        try {
            hDao = new HierarchyDAO(getConnection());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Method declaration
     *
     */
    public void create() {
        setStateNew();
    }

    /**
     * Method declaration
     *
     */
    public void validate() {
    }

    // --------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from Hierarchy.

    /**
     * Method declaration
     *
     *
     * @return
     */
    public short getPosition() {
        return hierarchyDTO.getPosition();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getValue() {
        return hierarchyDTO.getValue();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getDescription() {
        return new String(hierarchyDTO.getDescription());
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAgencyName() {
        return new String(hierarchyDTO.getAgencyName());
    }

    // --------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in Hierarchy.

    /**
     * Method declaration
     *
     *
     * @param tPosition
     */
    public void setPosition(short tPosition) {
        hierarchyDTO.setPosition(tPosition);
    }

    /**
     * Method declaration
     *
     *
     * @param tValue
     */
    public void setValue(int tValue) {
        hierarchyDTO.setValue(tValue);
    }

    /**
     * Method declaration
     *
     *
     * @param tValue
     */
    public void setValue(Integer tValue) {
        hierarchyDTO.setValue(validateInteger(tValue));
    }

    /**
     * Method declaration
     *
     *
     * @param tDescription
     */
    public void setDescription(String tDescription) {
        if (tDescription == null) {
            tDescription = "";
        }
        hierarchyDTO.setDescription(validateString(tDescription));
    }

    /**
     * Method declaration
     *
     *
     * @param tAgencyName
     */
    public void setAgencyName(String tAgencyName) {
        if (tAgencyName == null) {
            tAgencyName = "";
        }
        hierarchyDTO.setAgencyName(tAgencyName);
    }

    /**
     * add parent Agency Names.
     * @params pos - hierarchy position of the Agency name - the Agency Name
     */
    public void setParentAgencyName(int pos, String name) {
        hierarchyDTO.setParentAgencyName(pos, name);
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
        return hierarchyDTO.getParentAgencyName(pos);
    }

    // --------------------------------------action methods---------------------------------------------
    // This is comparison method to compare two hierarchies

    /**
     * Method declaration
     *
     *
     * @param otherHierarchy
     *
     * @return
     */
    public boolean equals(UserHierarchyDTO otherHierarchy) {
        return ((hierarchyDTO.getPosition() == otherHierarchy.getPosition())
                && (hierarchyDTO.getValue() == otherHierarchy.getValue()));
    }

    /**
     * This method gets the default hierarchy for this user role.
     * @return         a Hierarchy object containing the default hierarchy
     */
    public HierarchyDTO[] getDefaultHierarchy() {
        return hierarchyDTO.getDefaultHierarchy();
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     * @param aRoleName
     * @param aHierarchy
     *
     * @exception NBException
     */
    public void changeDefaultHierarchy(String userID, String aRoleName,
                                       HierarchyDTO aHierarchy[]) throws NBException {

        // --hDao.setDefaultUserHierarchy(userID, aRoleName, aHierarchy);
    }

    /**
     * This method sets the default hierarchy for this user role.
     * @param          aHierarchy - the new default hierarchy
     */
    public void setDefaultHierarchy(HierarchyDTO[] aHierarchy) {
        HierarchyDTO defaultHierarchy[] = new HierarchyDTO[9];

        for (short i = 0; i < 9; i++) {
            defaultHierarchy[i] = new HierarchyDTO();
            defaultHierarchy[i].setLevel(i);
            defaultHierarchy[i].setNumber(aHierarchy[i].getNumber());
            defaultHierarchy[i]
                    .setDescription(aHierarchy[i].getDescription());
            defaultHierarchy[i].setAgencyName(aHierarchy[i].getAgencyName());
        }
        hierarchyDTO.setDefaultHierarchy(defaultHierarchy);
    }

    /**
     * Retrieves all hierarchies that have been added since the last save.
     * @return	an array of UserHierarchy objects.
     */
    public UserHierarchyDTO[] getNewUserHierarchies() {
        UserHierarchyDTO[] h =
                new UserHierarchyDTO[hierarchyDTO.getHierarchyCache().size()];

        hierarchyDTO.getHierarchyCache().copyInto(h);
        return h;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getUserHierarchies() {
        return hierarchyDTO.getUserHierarchies();
    }

    /**
     * Method declaration
     *
     *
     * @param aHierarchy
     *
     * @return
     *
     * @exception NBException
     */
    public HierarchyDTO getHierarchy(HierarchyDTO[] aHierarchy)
            throws NBException {
        if (hierarchyDTO.getUserHierarchies() != null) {
            for (short i = 0; i < hierarchyDTO.getUserHierarchies().length;
                 i++) {
                HierarchyDTO[] h = hierarchyDTO.getUserHierarchies();

                if (h[i].equals(aHierarchy)) {
                    return h[i];
                }
            }
        }
        if (hierarchyDTO.getHierarchyCache() == null) {
            return null;
        }
        Enumeration vEnum = hierarchyDTO.getHierarchyCache().elements();

        while (vEnum.hasMoreElements()) {
            HierarchyDTO userHier = (HierarchyDTO) vEnum.nextElement();

            if (userHier.equals(aHierarchy)) {
                return userHier;
            }
        }
        return null;
    }

    /**
     * Method declaration
     *
     *
     * @param tUserHierarchies
     */
    public void setUserHierarchies(HierarchyDTO[] tUserHierarchies) {
        hierarchyDTO.setUserHierarchies(tUserHierarchies);
    }

    /**
     * Method declaration
     *
     *
     * @param aHierarchy
     * @param isDefault
     *
     * @exception NBException
     */
    public void addHierarchy(HierarchyDTO[] aHierarchy,
                             boolean isDefault) throws NBException {
        UserHierarchyDTO hierarchy = new UserHierarchyDTO();

        hierarchyDTO.getHierarchyCache().addElement(hierarchy);
        if (isDefault) {
            if (hierarchyDTO.getDefaultHierarchy() != null) {
                HierarchyDTO oldDefault =
                        getHierarchy(hierarchyDTO.getDefaultHierarchy());
            }
            hierarchyDTO.setDefaultHierarchy(aHierarchy);
        }
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean isDefault() {
        return hierarchyDTO.isDefault();
    }

    /**
     * Method declaration
     *
     *
     * @param Default
     */
    public void setDefault(boolean Default) {
        hierarchyDTO.setDefault(Default);
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchy
     */
    public void setHierarchy(HierarchyDTO[] hierarchy) {
        hierarchyDTO.setHierarchy(hierarchy);
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchyCache
     */
    public void setHierarchyCache(Vector hierarchyCache) {
        hierarchyDTO.setHierarchyCache(hierarchyCache);
    }

    /**
     * Method declaration
     *
     *
     * @param parentAgencyNames
     */
    public void setParentAgencyNames(String[] parentAgencyNames) {
        hierarchyDTO.setParentAgencyNames(parentAgencyNames);
    }

    /**
     * Method declaration
     *
     *
     * @param programTypes
     */
    public void setProgramTypes(String programTypes) {
        hierarchyDTO.setProgramTypes(programTypes);
    }

    /**
     * Method declaration
     *
     *
     * @param roleName
     */
    public void setRoleName(String roleName) {
        hierarchyDTO.setRoleName(roleName);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getHierarchy() {
        return hierarchyDTO.getHierarchy();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Vector getHierarchyCache() {
        return hierarchyDTO.getHierarchyCache();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getParentAgencyNames() {
        return hierarchyDTO.getParentAgencyNames();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getProgramTypes() {
        return hierarchyDTO.getProgramTypes();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRoleName() {
        return hierarchyDTO.getRoleName();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getUserID() {
        return hierarchyDTO.getUserID();
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     */
    public void setUserID(String userID) {
        hierarchyDTO.setUserID(userID);
    }

    /**
     * Method declaration
     *
     *
     * @param dto
     * @param userID
     *
     * @exception NBException
     */
    public void save(UserHierarchyDTO dto, String userID) throws NBException {
        UserProfileDAO userProfileDAO = new UserProfileDAO(getConnection());

        validate();

        // Save this object.
        if (getStateCurrent() == BO_STATE_NEW) {
            userProfileDAO.createUserHierarchy(dto, userID);

            // Create the program types also
            if (hierarchyDTO.getProgramTypes() != null) {

                // --char[] arrPT = hierarchyDTO.getProgramTypes().toCharArray();
                char[] arrPT = dto.getProgramTypes().toCharArray();

                for (short i = 0; i < arrPT.length; i++) {
                    userProfileDAO.createProgramType(dto, new String(""
                            + arrPT[i]));
                }
            }
        } else if (getStateCurrent() == BO_STATE_DIRTY) {
        }

        // --setStateCurrent();
    }

    /**
     * Appends a program type to this hierarchy. If this object is in a
     * new state, the new program type will become persistent when #save is called.  Otherwise, it is saved immediately using
     * <code>com.boa.eagls.government.Adapter.DataAccess.DAAUserProfile.createProgramType()</code>.
     * @param   aPType  the name or abbreviation of a program type
     * @exception   NBException an error occurred when saving to database
     * @see com.boa.eagls.government.Adapter.DataAccess.DAAUserProfile#createProgramType
     */
    public void addProgramType(String aPType) throws NBException {
        char chPType;

        if (aPType.equalsIgnoreCase("F") || aPType.equalsIgnoreCase("FLEET")) {
            chPType = 'F';
        } else if (aPType.equalsIgnoreCase("P")
                || aPType.equalsIgnoreCase("PURCHASE")) {
            chPType = 'P';
        } else if (aPType.equalsIgnoreCase("I")
                || aPType.equalsIgnoreCase("INTEGRATED")) {
            chPType = 'I';
        } else if (aPType.equalsIgnoreCase("A")
                || aPType.equalsIgnoreCase("INTERAGENCY")) {
            chPType = 'A';
        } else if (aPType.equalsIgnoreCase("T")
                || aPType.equalsIgnoreCase("TRAVEL")) {
            chPType = 'T';
        } else {
            throw new IllegalArgumentException("Unrecognized program type");
        }
        if (hierarchyDTO.getProgramTypes() == null) {
            hierarchyDTO.setProgramTypes(new String("" + chPType));
        } else {
            hierarchyDTO.setProgramTypes(hierarchyDTO.getProgramTypes()
                    + chPType);
        }
        if (getStateCurrent() != BO_STATE_NEW) {
            try {
                new UserProfileDAO(getConnection())
                        .createProgramType(this.hierarchyDTO,
                                new String("" + chPType));
            } catch (Exception ex) {
                throw new NBError(ex.getMessage());
            }
        }
    }

    /**
     * Method declaration
     *
     *
     * @param hTest
     *
     * @return
     */
    public boolean equals(HierarchyDTO[] hTest) {
        if (hTest != null && hTest.length == 9) {
            HierarchyDTO h[] = hierarchyDTO.getHierarchy();

            for (short i = 0; i < 9; i++) {
                if (hTest[i].getNumber() != h[i].getNumber()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     * @param hierarchy
     *
     * @return
     *
     * @exception DataConnException
     * @exception EAGLSException
     * @exception NBException
     * @exception NBException
     */
    public String validateHierarchy(String userID, HierarchyDTO[] hierarchy)
            throws NBException, EAGLSException, DataConnException, NBException {
        UserProfileDAO upDAO = new UserProfileDAO(getConnection());

        return upDAO.validateHierarchy(userID, hierarchy);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public UserHierarchyDTO getHierarchyDTO() {
        return hierarchyDTO;
    }

    /**
     * Method declaration
     *
     *
     * @param HierarchyNumber
     *
     * @return
     *
     * @exception Exception
     * @exception NBError
     */
    public String[] getHierarchy(String HierarchyNumber)
            throws NBError, Exception {
        return hDao.getHierarchy(HierarchyNumber);
    }

    /**
     * Method declaration
     *
     *
     * @param hBrowse
     *
     * @return
     *
     * @exception Exception
     * @exception NBError
     */
    public String getHierarchyNumber(int[] hBrowse) throws NBError, Exception {
        return hDao.getHierarchyNumber(hBrowse);
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchy
     * @param isDefault
     * @param roleName
     * @param userId
     */
    public void poplulateHierarchy(HierarchyDTO[] hierarchy,
                                   boolean isDefault, String roleName,
                                   String userId) {
        hierarchyDTO = new UserHierarchyDTO();
        hierarchyDTO.setHierarchy(hierarchy);
        hierarchyDTO.setDefault(isDefault);
        hierarchyDTO.setRoleName(roleName);
        hierarchyDTO.setUserID(userId);
    }

}
