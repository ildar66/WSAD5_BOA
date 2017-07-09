/**
 * HierarchyDisplay
 */
package com.boa.eagls.government.util;

import com.boa.eagls.government.dto.HierarchyDTO;

/**
 * <p>Title: HierarchyDisplay</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class HierarchyDisplay
{
    public static final int NUMBER_OF_SEGMENTS = 9;
    private String[] segments =
            {
                "", "", "", "", "", "", "", "", ""
            };
    private String[] descr =
            {
                "", "", "", "", "", "", "", "", ""
            };
    private String[] agencyNames =
            {
                "", "", "", "", "", "", "", "", ""
            };

    /**
     * This is the default constructor and creates an emplty, but initialized HierarchyDisplay Object.
     * @param    none
     * @return   none
     * @author   Jeremy Thomason
     */
    public HierarchyDisplay()
    {
    }

    /**
     * Overloaded constructor
     */
    public HierarchyDisplay(HierarchyDTO[] hierarchy)
    {
        if (hierarchy != null)
        {
            for (int i = 0; i < hierarchy.length; i++)
            {
                segments[i] = (hierarchy[i].getNumber() == 0) ? ""
                        : pad7(hierarchy[i].getNumber());
                descr[i] = (hierarchy[i].getDescription() == null) ? ""
                        : hierarchy[i].getDescription();
                agencyNames[i] = (hierarchy[i].getAgencyName() == null) ? ""
                        : hierarchy[i].getAgencyName();
            }
        }
    }

    /**
     * Overloaded constructor
     */
    public HierarchyDisplay(String[] hierarchy)
    {
        if (hierarchy != null)
        {
            for (int i = 0; i < hierarchy.length; i++)
            {
                segments[i] = (hierarchy[i] == null) ? "" : hierarchy[i];
            }
        }
    }

    /**
     * Overloaded constructor
     */
    public HierarchyDisplay(int[] values)
    {
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                segments[i] = (values[i] == 0) ? "" : pad7(values[i]);
                descr[i] = "";
                agencyNames[i] = "";
            }
        }
    }

    /**
     * Overloaded constructor
     */
    public HierarchyDisplay(String[] hierarchy, String[] description)
    {
        if (hierarchy != null)
        {
            for (int i = 0; i < hierarchy.length; i++)
            {
                segments[i] = (hierarchy[i] == null) ? "" : hierarchy[i];
            }
        }
        if (description != null)
        {
            for (int i = 0; i < description.length; i++)
            {
                descr[i] = (description[i] == null) ? "" : description[i];
            }
        }
    }

    /**
     * Overloaded constructor
     */
    public HierarchyDisplay(String[] hierarchy, String[] description,
                            String[] agncyNms)
    {
        if (hierarchy != null)
        {
            for (int i = 0; i < hierarchy.length; i++)
            {
                segments[i] = (hierarchy[i] == null) ? "" : hierarchy[i];
            }
        }
        if (description != null)
        {
            for (int i = 0; i < description.length; i++)
            {
                descr[i] = (description[i] == null) ? "" : description[i];
            }
        }
        if (agncyNms != null)
        {
            for (int i = 0; i < agncyNms.length; i++)
            {
                agencyNames[i] = (agncyNms[i] == null) ? "" : agncyNms[i];
            }
        }
    }

    /**
     * Overloaded constructor
     */
    public HierarchyDisplay(String hierarchy)
    {
        if (hierarchy != null)
        {
            for (int i = 0; i < NUMBER_OF_SEGMENTS; i++)
            {
                try
                {
                    segments[i] = hierarchy.substring(i * 7, i * 7 + 7);
                    if (Integer.parseInt(segments[i]) == 0)
                    {
                        segments[i] = "";
                    }
                }
                catch (NumberFormatException nfe)
                {
                    segments[i] = "";
                }
                catch (StringIndexOutOfBoundsException sioobe)
                {
                    segments[i] = "";
                }
            }
        }
    }

    /**
     * getProgramNumber
     * @return String
     */
    public String getProgramNumber()
    {
        return segments[0];
    }

    /**
     * getProgramNumberDescription
     * @return String
     */
    public String getProgramNumberDescription()
    {
        return descr[0];
    }

    /**
     * getSegments
     * @return String[]
     */
    public String[] getSegments()
    {
        return segments;
    }

    /**
     * setSegment
     * @param i
     * @param h
     */
    public void setSegment(int i, HierarchyDTO h)
    {
//        System.out.println("set Segment 1" + i);
//        System.out.println("set Segment 2" + h);
        segments[i] = pad7(h.getNumber());
//        System.out.println("set Segment 2");
        descr[i] = h.getDescription();
//        System.out.println("set Segment 3");
        agencyNames[i] = h.getAgencyName();
//        System.out.println("set Segment 4");
    }

    /**
     * getSegment
     * @param i
     * @return String
     */
    public String getSegment(int i)
    {
        return segments[i];
    }

    /**
     * setDescription
     * @param i
     * @param desc
     */
    public void setDescription(int i, String desc)
    {
        if (desc == null)
        {
            desc = "";
        }
        descr[i] = desc;
    }

    /**
     * setAgencyName
     * @param i
     * @param agncyNm
     */
    public void setAgencyName(int i, String agncyNm)
    {
        if (agncyNm == null)
        {
            agncyNm = "";
        }
        agencyNames[i] = agncyNm;
    }

    /**
     * @return
     */
    public String[] getDescriptions()
    {
        return descr;
    }

    /**
     * @param i
     * @return
     */
    public String getDescription(int i)
    {
        return descr[i];
    }

    /**
     * @param i
     * @return
     */
    public String getAgencyName(int i)
    {
        return agencyNames[i];
    }

    /**
     * @return
     */
    public String[] getAgencyNames()
    {
        return agencyNames;
    }

    /**
     * @return
     */
    public int[] getValues()
    {
        int[] hier = new int[NUMBER_OF_SEGMENTS];

        for (int i = 0; i < NUMBER_OF_SEGMENTS; i++)
        {
            try
            {
                hier[i] = Integer.parseInt(segments[i], 10);
            }
            catch (NumberFormatException e)
            {
                hier[i] = 0;
            }
        }
        return hier;
    }

    /**
     * @param i
     * @return
     */
    public int getValue(int i)
    {
        try
        {
            return Integer.parseInt(segments[i], 10);
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    /**
     * @param i
     * @param aValue
     */
    public void setValue(int i, int aValue)
    {
        if (aValue == 0)
        {
            segments[i] = "";
        }
        else
        {
            segments[i] = String.valueOf(aValue);
        }
    }

    /**
     * @return
     */
    public String getCombinedSegmentsSansProgramNumber()
    {
        String temp = "";

        for (int i = 1; i < segments.length; i++)
        {
            temp += segments[i] + " ";
        }
        return temp;
    }

    /**
     * @return
     */
    public String getCombinedSegments()
    {
        return segments[0] + " " + getCombinedSegmentsSansProgramNumber();
    }

    /**
     * @param baseRole
     * @return
     */
    public String getCombinedSegmentsByBaseRole(String baseRole)
    {
        if (baseRole.equalsIgnoreCase("GCSU"))
        {
            return getCombinedSegments();
        }
        else
        {
            return getCombinedSegmentsSansProgramNumber();
        }
    }

    /**
     * @return
     */
    public String getCombinedSegments63()
    {

        // returns 63 character string for segments
        String temp = "";

        for (int i = 0; i < segments.length; i++)
        {
            temp += (segments[i].equals("") ? pad7(0) : segments[i]);
        }
        return temp;
    }

    /**
     * @return
     */
    public String getCombinedDescriptions()
    {
        String temp = "";

        for (int i = 0; i < descr.length; i++)
        {
            temp += pad7(descr[i]);
        }
        return temp;
    }

    /**
     * @return
     */
    public HierarchyDTO[] getHierarchy()
    {
        HierarchyDTO[] hier = new HierarchyDTO[NUMBER_OF_SEGMENTS];
        int[] values = getValues();

        for (int i = 0; i < NUMBER_OF_SEGMENTS; i++)
        {
            hier[i] = new HierarchyDTO((short) i, values[i], descr[i]);
        }
        return hier;
    }

    /**
     * @return
     */
    public int getDepth()
    {
        for (int i = 0; i < NUMBER_OF_SEGMENTS; i++)
        {
            if (segments[i].equals("") || segments[i].equals("0"))
            {
                return i;
            }
        }
        return NUMBER_OF_SEGMENTS;
    }

    /**
     * @param n
     * @return
     */
    public static String pad7(int n)
    {
        String padding = "0000000";
        String nStr = String.valueOf(n);

        if (nStr.length() < 7)
        {
            return padding.substring(0, 7 - nStr.length()) + nStr;
        }
        else
        {
            return nStr;
        }
    }

    /**
     * @param n
     * @return
     */
    private String pad7(String n)
    {
        String padding = "       ";

        if (n.length() < 7)
        {
            return n + padding.substring(0, 7 - n.length());
        }
        else
        {
            return n.substring(0, 7);
        }
    }

}
