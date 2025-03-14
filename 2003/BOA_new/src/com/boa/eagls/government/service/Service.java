package com.boa.eagls.government.service;

import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.util.HTMLFormat;
import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.service.userprofile.UserAccountService;
import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Locale;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: OlegK
 * Date: 25.06.2003
 * Time: 17:38:59
 */
public abstract class Service {
    private static final Logger logger = Logger.getLogger(Service.class);

    //private static final String JNDI_NAME = "java:comp/env/jdbc/boa";
    private static final String JNDI_NAME = "jdbc/boa";

    private static DataSource source = null;
    private static final Object LOCK = new Object();

    public static final int[] getCurrentShortHierarchy(HierarchyDTO[] hierarchy) {
        int[] intArray;
        if (hierarchy != null) {
            intArray = new int[hierarchy.length];
            for (int i = 0; i < intArray.length; i++) {
                if (hierarchy[i] != null) {
                    intArray[i] = hierarchy[i].getNumber();
                } else {
                    intArray[i] = 0;
                }
            }
        } else {
            //todo check how to fill
            intArray = new int[9];
            for (int i = 0; i < 9; i++) {
                intArray[i] = 0;
            }
        }
        return intArray;
    }

    protected static final Connection getPooledConnection() throws SQLException {
        if (source == null) {
            InitialContext ic = null;
            synchronized (LOCK) {
                if (source == null)
                    try {
                        ic = new InitialContext();
                        source = (DataSource) ic.lookup(JNDI_NAME);
                    } catch (NamingException e) {
                            logger.fatal(JNDI_NAME + " DataSource not found", e);
                            throw new SQLException(e.getMessage());
                    }
            }
        }
        return source.getConnection();
    }

    protected static final void closeConnection(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            logger.error("Connection can't be closed", e);
        }
    }

    protected static final void rollbackTransaction(Connection conn) {
        try {
            if (conn != null)
                conn.rollback();
        } catch (SQLException e) {
            logger.error("transaction can't be discarted", e);
        }
    }

    protected static final void recoverCommitState(Connection conn, Boolean state) {
        try {
            if (conn != null && state!=null)
                conn.setAutoCommit(state.booleanValue());
        } catch (SQLException e) {
            logger.error("Autocommit state can't be recovered", e);
        }
    }

    public static BrowseHierarchyFields fillHierarchyBean(
		HierarchyDTO[] iHierarchy,
		HierarchyDTO[] currentHierarchy,
		String currentBaseRole,
		boolean edit) {
		return AccountService.fillHierarchyBean(
			iHierarchy,
			currentHierarchy,
			currentBaseRole,
			edit,
			false);
	}

    public static BrowseHierarchyFields fillHierarchyBean(
		HierarchyDTO[] iHierarchy,
		HierarchyDTO[] currentHierarchy,
		String currentBaseRole,
		boolean edit,
		boolean hL1Lock) {
		String curSegment = null;
		String curAgencyName = null;
		BrowseHierarchyFields browseHierarchyFields =
			new BrowseHierarchyFields();
		String EDIT_HL_NUMBER[] = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
		String YESEDIT_HL_NUMBER[] = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
		String HL_NUMBER[] = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
		String EDIT_HL_DESCRIPTION[] = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
		String HL_DESCRIPTION[] = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        HierarchyDisplay hDisplay = null;
        HierarchyDTO[] hier = null;
        if (!Role.GCSU.equals(currentBaseRole)) {
            hier = currentHierarchy;
            int[] hierarchyD = new HierarchyDisplay(hier).getValues();
            try {
                HierarchyBase hierarchyBase = new HierarchyBase();
                String hierarchyNbr =
                        hierarchyBase.getHierarchyNumber(hierarchyD);
                UserAccountService userAccountService =
                        new UserAccountService(null);

                //                hDisplay = AccountController.getHierarchyAgencyNames(new Integer(hierarchyNbr).intValue(), das);
                hDisplay =
                        userAccountService.getHierarchyAgencyNames(
                                new Integer(hierarchyNbr).intValue());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } //end catch NBException
        } else {
            hier = iHierarchy;//agency.getAHierarchy();
            hDisplay = new HierarchyDisplay(hier);
        }
        if (edit) {
			for (int i = 0; i < HierarchyDisplay.NUMBER_OF_SEGMENTS; i++) {
				int hlval = 0;
				if (currentHierarchy != null
					&& !Role.GCSU.equals(currentBaseRole)) {
					hlval = currentHierarchy[i].getNumber();
				}

				if (hlval == 0) {
					if ((i == 0 || i == 1) && hL1Lock) {
						EDIT_HL_NUMBER[i] = "";
						YESEDIT_HL_NUMBER[i] = null;
						//                        template.putString("EDIT.HL" + i + ".NUMBER", "");
						//                        template.putString("YESEDIT.HL" + i + ".NUMBER", null);
					} else {
						EDIT_HL_NUMBER[i] = null;
						YESEDIT_HL_NUMBER[i] = "";
						//                        template.putString("EDIT.HL" + i + ".NUMBER", null);
						//                        template.putString("YESEDIT.HL" + i + ".NUMBER", "");
					}
				} else {
					EDIT_HL_NUMBER[i] = null;
					YESEDIT_HL_NUMBER[i] = "";
					//                    template.putString("EDIT.HL" + i + ".NUMBER", "");
					//                    template.putString("YESEDIT.HL" + i + ".NUMBER", null);
				}

				curSegment = hDisplay.getSegment(i);
				if (curSegment == null) {
					curSegment = "";
				}
				HL_NUMBER[i] = curSegment;
				EDIT_HL_DESCRIPTION[i] = "";
				//                template.putString("HL" + i + ".NUMBER", curSegment);
				//                template.putString("EDIT.HL" + i + ".DESCRIPTION", "");

				curAgencyName = hDisplay.getAgencyName(i);
				if (curAgencyName == null || "".equals(curAgencyName)) {
					curAgencyName = " "; //&#160";
				}
				HL_DESCRIPTION[i] = HTMLFormat.htmlReplace(curAgencyName, '\"');
				//                template.putString("HL" + i + ".DESCRIPTION", util.nb.gsa.HTMLFormat.htmlReplace(curAgencyName, '\"'));
			}

		} else {
			for (int i = 0; i < hDisplay.NUMBER_OF_SEGMENTS; i++) {
				EDIT_HL_NUMBER[i] = "";
				YESEDIT_HL_NUMBER[i] = null;
				//                template.putString("EDIT.HL" + i + ".NUMBER", "");
				//                template.putString("YESEDIT.HL" + i + ".NUMBER", null);
				curSegment = hDisplay.getSegment(i);
				if (curSegment == null || "".equals(curSegment)) {
					curSegment = "";
				}
				HL_NUMBER[i] = curSegment;
				EDIT_HL_DESCRIPTION[i] = "";
				//                template.putString("HL" + i + ".NUMBER", curSegment);
				//                template.putString("EDIT.HL" + i + ".DESCRIPTION", "");

				curAgencyName = hDisplay.getAgencyName(i);
				if (curAgencyName == null || "".equals(curAgencyName)) {
					curAgencyName = "";
				}
				HL_DESCRIPTION[i] = HTMLFormat.htmlReplace(curAgencyName, '\"');
			}
		}
		browseHierarchyFields.setEditHlNumber(EDIT_HL_NUMBER);
		browseHierarchyFields.setYesEditHlNumber(YESEDIT_HL_NUMBER);
		browseHierarchyFields.setHlNumber(HL_NUMBER);
		browseHierarchyFields.setEditHlDescription(EDIT_HL_DESCRIPTION);
		browseHierarchyFields.setHlDescription(HL_DESCRIPTION);
		browseHierarchyFields.setGCSU(Role.GCSU.equals(currentBaseRole));
		return browseHierarchyFields;
	}

    /**
     * createHyperlink creates the string necessary to call an applogic
     * from an HTML hyperlink.
     *
     * @param		url guid of applogic to call, params=IValList of parameters
     * @param		params Map
     * @return		String for hyperlink
     **/

    public static final String createHyperlink(String url, Map params) {
        StringBuffer hl = new StringBuffer("");
        hl.append(url);

        Set keySet = params.keySet();
        Iterator it = keySet.iterator();
        if (it.hasNext()) {
            hl.append("?");
        }
        int count = params.size();
        int i = 0;
        while (it.hasNext()) {
            String key = (String) it.next();
            String val = (String) params.get(key);
            String URLString = URLEncoder.encode(val);
            hl.append(key);
            hl.append("=");
            hl.append(URLString);
            hl.append(i < (count - 1) ? "&" : "");
            i++;
        }
        String retVal = hl.toString();
        int lbIndex = 0;
        while ((lbIndex = retVal.indexOf("#")) !=
                -1 && lbIndex < retVal.length()) {
            retVal = retVal.substring(0, lbIndex) +
                    "%23" + retVal.substring(lbIndex + 1);
        }
        return retVal;
    }

    /**
     * Converts a string holding a currency in the format $#,###.##
     * into a double type.
     *
     * @param   s   holds the currency to convert
     * @return  double   the double conversion of <code>s</code>;
     *                  <code>Double.NAN</code> if s cannot be parsed.
     **/
    public static double currencyToDouble(String s) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        Number num;
        try {
            num = nf.parse(s);
        } catch (ParseException pe) {
            try {
                return Double.valueOf(s).doubleValue();
            } catch (NumberFormatException nfe) {
                return Double.NaN;
            }
        }
        return num.doubleValue();
    }

}
