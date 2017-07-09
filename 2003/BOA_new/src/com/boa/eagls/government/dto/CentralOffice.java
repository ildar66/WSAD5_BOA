package com.boa.eagls.government.dto;

/**
 * <code>CentralOffice is the storage object for all
 * information concerning an Agency's Central Office.</code>
 * This class extends the Profile object. LastName is used for CentralOffice name.
 *
/* 	@version 1.0 $Modtime:   20 Sep 1998 15:31:58  $$Revision: 1.1 $
/* 	@author  Ed Zhang & Frank Jary
**/

public class CentralOffice extends Profile
{
    //The attributes otherwise noted map to screen elements described in SDRs.
    private HierarchyDTO[] hierarchy;

    public CentralOffice()
    {
        super();
        hierarchy = new HierarchyDTO[9];
    }

    public CentralOffice(CentralOffice tCentralOffice)
    {
        super(tCentralOffice);
        setHierarchy(tCentralOffice.getAHierarchy());
    }
//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from CentralOffice.

    public HierarchyDTO[] getAHierarchy()
    {
        HierarchyDTO[] copyHierarchy = new HierarchyDTO[hierarchy.length];
        for (int i = 0; i < HIERARCHY_LIMIT; i++)   {
            copyHierarchy[i] = new HierarchyDTO(hierarchy[i]);
        }
        return copyHierarchy;
    }

//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in CentralOffice.

    public void setHierarchy(HierarchyDTO[] tHierarchy)
    {
        if (tHierarchy != null) {
            hierarchy = tHierarchy;
        } else {
            hierarchy = new HierarchyDTO[HIERARCHY_LIMIT];
            for (int i = 0; i < HIERARCHY_LIMIT; i++) {
                hierarchy[i] = new HierarchyDTO((short)0,0,"");
            }
        }
    }
}
