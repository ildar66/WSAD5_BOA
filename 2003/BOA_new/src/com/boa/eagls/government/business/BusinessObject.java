package com.boa.eagls.government.business;

import com.boa.eagls.government.dto.HierarchyDTO;

import java.util.Date;

/**
 * </code>Base class for business objects.  This object implements the
 * state machine embedded in business objects to aid the process
 * of making objects persistent.  This also contains commonly used
 * methods throughout business objects.</code>
 * User: OlegK
 * Date: 30.06.2003
 * Time: 10:32:32
 */
public class BusinessObject {

    // Default values
    protected static final String STRING_DEFAULT = "";
    protected static final String[] STRING_ARRAY_DEFAULT = {"", "", ""};
    protected static final short SHORT_DEFAULT = 0;
    protected static final Date DATE_DEFAULT = new Date();
    public static final int INT_DEFAULT = 0;
    protected static final float FLOAT_DEFAULT = 0;
    protected static final boolean BOOLEAN_DEFAULT = false;
    protected static final long LONG_DEFAULT = 0;
    protected static final char CHAR_DEFAULT = '*'; // TODO:  Need to evaluate.
    protected static final double DOUBLE_DEFAULT = 0;
    protected static final int[] INT_ARRAY_DEFAULT = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    // General static final values
    public static final int HIERARCHY_LIMIT = 9;

//    protected DASTransport transport;           // system services carrier

    protected final static int BO_STATE_EMPTY = 0;
    protected final static int BO_STATE_NEW = 1;
    protected final static int BO_STATE_DIRTY = 2;
    protected final static int BO_STATE_CURRENT = 3;

    protected int state = BO_STATE_CURRENT;          // current state

    //Create a central DebugLog object
//	protected DebugLog debugLog;
//	protected ErrorLog errorLog;

    public BusinessObject() {
        super();
    }

    //copy contructor
    public BusinessObject(BusinessObject businessObject) {
        setStateEmpty();
        setStateNew();
        setStateDirty();
        setStateCurrent();
    }

/*    public BusinessObject(DASTransport aTransport) {
//         transport = aTransport;
        // setStateEmpty();
    }*/

    public int getStateCurrent() {
        return state;
    }

    protected void setStateEmpty() {
        state = BO_STATE_EMPTY;
    }

    protected void setStateNew() {
        state = BO_STATE_NEW;
    }

    protected void setStateDirty() {
        // Can't UPDATE an object that isn't INSERTed yet.
        if (state != BO_STATE_NEW) {
            state = BO_STATE_DIRTY;
        }
    }

    protected void setStateCurrent() {
        state = BO_STATE_CURRENT;
    }

    protected String validateString(String value) {
        if (value != null) {
            return value;
        } else {
            return STRING_DEFAULT;
        }
    }

    protected boolean validateBoolean(Boolean value) {
        if (value != null) {
            return value.booleanValue();
        } else {
            return BOOLEAN_DEFAULT;
        }
    }

    protected double validateDouble(Double value) {
        if (value != null) {
            return value.doubleValue();
        } else {
            return DOUBLE_DEFAULT;
        }
    }

    protected int validateInteger(Integer value) {
        if (value != null) {
            return value.intValue();
        } else {
            return INT_DEFAULT;
        }
    }

    protected Date validateDate(Date date) {
        if (date != null) {
            return date;
        } else {
            return DATE_DEFAULT;
        }
    }

    protected HierarchyDTO[] validateHierarchy(HierarchyDTO[] hierarchy) {
        if (hierarchy != null) {
            return hierarchy;
        } else {
            HierarchyDTO[] newHierarchy = new HierarchyDTO[HIERARCHY_LIMIT];
            for (int i = 0; i < HIERARCHY_LIMIT; i++) {
                newHierarchy[i] = new HierarchyDTO((short) 0, 0, "");
            }
            return newHierarchy;
        }
    }

    /*protected Profile validateProfile(Profile profile) {
        if (profile != null) {
            return profile;
        } else {
            return new Profile();
        }
    }*/

    protected HierarchyDTO[] copyHierarchy(HierarchyDTO[] hierarchy) {
        HierarchyDTO[] copyHierarchy = new HierarchyDTO[hierarchy.length];
        for (int i = 0; i < hierarchy.length; i++) {
            copyHierarchy[i] = hierarchy[i];
        }
        return copyHierarchy;
    }
}
