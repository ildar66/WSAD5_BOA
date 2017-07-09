package com.boa.eagls.government.dao;

import com.boa.eagls.government.controller.formbean.account.ViewAccountForm;
import com.boa.eagls.government.dto.CentralOffice;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.NameDescTable;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.account.CentralAccount;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.dto.user.UserAccountDTO;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.HierarchyBase;
import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.service.centralaccount.CentralAccountService;
import com.boa.eagls.government.sql.constants.AccountConstants;
import com.boa.eagls.government.util.*;
import com.boa.eagls.government.dto.authorizationcontrols.AcctControls;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * A class that provide all database related function for Account management .
 * @version 1.0
 * @author Oleg Klimenko
 */
public class AccountDAO extends DAOBase {

	private static Logger logger = Logger.getLogger(AccountDAO.class);

	//:iCentralAcctID
	/**
	 * Creates new AccountDAO
	 * @param connection A Connection object, which will be used to
	 * execute database in AccountDAO
	 * @deprecated
	 */
	public AccountDAO(java.sql.Connection connection) {
		setConnection(connection);
	}

	/**
	 * default constructor
	 */
	public AccountDAO() {
	}

	private BrowseHierarchyFields fillHierarchyBean(
		HierarchyDisplay hierarchy,
		HierarchyDTO[] currentHierarchy,
		String currentBaseRole,
		boolean edit) {
		return fillHierarchyBean(
			hierarchy,
			currentHierarchy,
			currentBaseRole,
			edit,
			false);
	}

	private BrowseHierarchyFields fillHierarchyBean(
		HierarchyDisplay hierarchy,
		HierarchyDTO[] currentHierarchy,
		String currentBaseRole,
		boolean edit,
		boolean hL1Lock) {
		String curSegment = null;
		String curAgencyName = null;
		BrowseHierarchyFields browseHierarchyFields =
			new BrowseHierarchyFields();
		String EDIT_HL_NUMBER[] = new String[hierarchy.NUMBER_OF_SEGMENTS];
		String YESEDIT_HL_NUMBER[] = new String[hierarchy.NUMBER_OF_SEGMENTS];
		String HL_NUMBER[] = new String[hierarchy.NUMBER_OF_SEGMENTS];
		String EDIT_HL_DESCRIPTION[] = new String[hierarchy.NUMBER_OF_SEGMENTS];
		String HL_DESCRIPTION[] = new String[hierarchy.NUMBER_OF_SEGMENTS];
		if (edit) {
			for (int i = 0; i < hierarchy.NUMBER_OF_SEGMENTS; i++) {
				int hlval = 0;
				if (currentHierarchy != null
					&& !"GCSU".equals(currentBaseRole)) {
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
					EDIT_HL_NUMBER[i] = "";
					YESEDIT_HL_NUMBER[i] = null;
					//                    template.putString("EDIT.HL" + i + ".NUMBER", "");
					//                    template.putString("YESEDIT.HL" + i + ".NUMBER", null);
				}

				curSegment = hierarchy.getSegment(i);
				if (curSegment == null || "".equals(curSegment)) {
					curSegment = "";
				}
				HL_NUMBER[i] = curSegment;
				EDIT_HL_DESCRIPTION[i] = "";
				//                template.putString("HL" + i + ".NUMBER", curSegment);
				//                template.putString("EDIT.HL" + i + ".DESCRIPTION", "");

				curAgencyName = hierarchy.getAgencyName(i);
				if (curAgencyName == null || "".equals(curAgencyName)) {
					curAgencyName = "&#160";
				}
				HL_DESCRIPTION[i] = HTMLFormat.htmlReplace(curAgencyName, '\"');
				//                template.putString("HL" + i + ".DESCRIPTION", util.nb.gsa.HTMLFormat.htmlReplace(curAgencyName, '\"'));
			}

		} else {
			for (int i = 0; i < hierarchy.NUMBER_OF_SEGMENTS; i++) {
				EDIT_HL_NUMBER[i] = "";
				YESEDIT_HL_NUMBER[i] = null;
				//                template.putString("EDIT.HL" + i + ".NUMBER", "");
				//                template.putString("YESEDIT.HL" + i + ".NUMBER", null);
				curSegment = hierarchy.getSegment(i);
				if (curSegment == null || "".equals(curSegment)) {
					curSegment = "";
				}
				HL_NUMBER[i] = curSegment;
				EDIT_HL_DESCRIPTION[i] = "";
				//                template.putString("HL" + i + ".NUMBER", curSegment);
				//                template.putString("EDIT.HL" + i + ".DESCRIPTION", "");

				curAgencyName = hierarchy.getAgencyName(i);
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
		browseHierarchyFields.setGCSU("GCSU".equals(currentBaseRole));
		return browseHierarchyFields;
	}

	/**
	 * Returns a CentralAcctCore obj given a central AcctID
	 * @param   centralAcctID
	 * @return  CentralAcctCore
	 **/
	public CentralAccount retrieveCentralAcctByCentralAcctID(
		Connection conn,
		int centralAcctID,
		AgencyCore agency,
		String userId,
		String currentBaseRole,
		HierarchyDTO[] currentHierarchy,
		String lastName,
		String firstName,
		String socialSecurityNumber,
		HierarchyDTO[] hier,
		HierarchyDisplay hDisplay)
		throws SQLException, EaglsDAOError {
		//        transport.logDebug("retrieveCentralAcctByCentralAcctID", "entered method");

		// getting
		/*        HierarchyDisplay hDisplay = null;
		        HierarchyDTO[] hier;
		
		        if (!"GCSU".equals(currentBaseRole)) {
		            hier = currentHierarchy;
		            int[] hierarchyD = new HierarchyDisplay(hier).getValues();
		            try {
		                String hierarchyNbr = HierarchyBase.getHierarchyNumber(hierarchyD);
		                UserAccountService userAccountService = new UserAccountService(null);
		
		//                hDisplay = AccountController.getHierarchyAgencyNames(new Integer(hierarchyNbr).intValue(), das);
		                hDisplay = userAccountService.getHierarchyAgencyNames(new Integer(hierarchyNbr).intValue());
		            } catch (Exception e) {
		                logger.error(e.getMessage(), e);
		            }//end catch NBException
		        } else {
		            hier = agency.getAHierarchy();
		            hDisplay = new HierarchyDisplay(hier);
		        }*/
		//        BrowseHierarchyFields browseHierarchyFields =
		//                fillHierarchyBean(hDisplay, currentHierarchy, currentBaseRole, true);
		//        request.getSession().setAttribute("browseHierarchyFields", browseHierarchyFields);

		CallableStatement cs = null;
		CentralAccount newCentralAcct = new ViewAccountForm();
		try {
			cs = conn.prepareCall(GET_CENTRAL_ID_INFO);
			cs.setString(QUERY_iUserId, userId);
			cs.setInt(QUERY_iCentralAccountId, centralAcctID);

			cs.registerOutParameter(QUERY_oCentralAccountName, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oCentralAccountNumber, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oConvenienceChecks, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oTravelerChecks, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oBillingType, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oAtmAccess, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oDecrementingCard, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oProgramType, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oAccountingCenterID, Types.VARCHAR);
			cs.registerOutParameter(QUERY_oResult, Types.VARCHAR);

			cs.execute();

			//        transport.logDebug("retrieveCentralAcctByCentralAcctID", "checkpoint #1");  // DEBUG
			//            HierarchyDisplay hDisplay = generateHDisplay(currentBaseRole, currentHierarchy, agency);

			newCentralAcct.setHdn_programNumber(hDisplay.getSegment(0));

			newCentralAcct.setHdn_centralAccountID(centralAcctID);
			newCentralAcct.setHdn_centralAccountName(
				cs.getString(QUERY_oCentralAccountName));

			String programType = cs.getString(QUERY_oProgramType).trim();
			if (programType.equals("P")) {
				//                programType = CentralAccount.PURCHASE;
				newCentralAcct.setHdn_programType("Purchase");
			} else if (programType.equals("T")) {
				//                programType = CentralAccount.TRAVEL;
				newCentralAcct.setHdn_programType("Travel");
			} else if (programType.equals("F")) {
				newCentralAcct.setHdn_programType("Fleet");
				//                programType = CentralAccount.FLEET;
			} else if (programType.equals("I")) {
				newCentralAcct.setHdn_programType("Integrated");
				//                programType = CentralAccount.INTEGRATE;
			} else if (programType.equals("I")) {
				newCentralAcct.setHdn_programType("Interagency");
				//                programType = CentralAccount.INTERAGENCY;
			}

			newCentralAcct.setHdn_centralAccountNumber(
				cs.getString(QUERY_oCentralAccountNumber));
			newCentralAcct.setHdn_travelersCheckFlag(
				"T".equalsIgnoreCase(
					cs.getString(QUERY_oTravelerChecks) == null
						? ""
						: cs.getString(QUERY_oTravelerChecks).trim())
					? "Yes"
					: "No");

			short billingType = 0;
			String strBillingType = cs.getString(QUERY_oBillingType);
			if (strBillingType != null)
				strBillingType = strBillingType.trim();
			if (strBillingType.equals("C")) {
				billingType = Account.CENTRAL;
			} else if (strBillingType.equals("I")) {
				billingType = Account.INDIVIDUAL;
			}
			newCentralAcct.setHdn_billingType(
				billingType == Account.CENTRAL ? "Central" : "Individual");
			newCentralAcct.setAccountingCenterID(
				cs.getString(QUERY_oAccountingCenterID));

			// Card Types
			// todo implement card types
			/*NameDescTable[] cardTypeList = agency.getCardTypes();
			if (cardTypeList != null || cardTypeList.length > 0) {
			    for (short i = 0; i < cardTypeList.length; i++) {
			        cardTypes.rowAppend("Value=" + cardTypeList[i].getName() +
			                ";Desc=" + cardTypeList[i].getDescription());
			    }
			} else {
			    cardTypes.rowAppend("Value=DataMissing;Desc=No Data Found");
			}
			newCentralAcct.setCmb_cardType();*/
			// Card Types end

			newCentralAcct.setTxt_lastName(lastName); //from request
			newCentralAcct.setTxt_firstName(firstName);
			newCentralAcct.setTxt_socialSecurityNumber(socialSecurityNumber);

			// Account Holder Information.
			CentralOffice cOffice = null;
			CentralAccountService centralAcct = new CentralAccountService();
			if (("A_OPC").equalsIgnoreCase(currentBaseRole)) {
				// AOPC doing setup send flag to method
				cOffice =
					centralAcct.retrieveCentralOffice(
						agency.getAgencyID(),
						Service.getCurrentShortHierarchy(hier),
						false,
						userId);
			} else {
				// behave as originally coded
				cOffice =
					centralAcct.retrieveCentralOffice(
						agency.getAgencyID(),
						Service.getCurrentShortHierarchy(hier),
						userId);
			}

			newCentralAcct.setTxt_addressLine1(cOffice.getAddress1());
			newCentralAcct.setTxt_addressLine2(cOffice.getAddress2());
			newCentralAcct.setTxt_addressLine3(cOffice.getAddress3());
			newCentralAcct.setTxt_addressLine4(cOffice.getAddress4());
			newCentralAcct.setTxt_city(cOffice.getCity());
			String state = cOffice.getStateOrProvince();
			newCentralAcct.setTxt_state(state == null ? state : state.trim());
			String country = cOffice.getCountry();
			newCentralAcct.setTxt_country(
				country == null ? null : country.trim());
			String zip = cOffice.getZip();
			newCentralAcct.setTxt_zipCode(zip == null ? null : zip.trim());
			newCentralAcct.setTxt_businessPhone(cOffice.getBusinessPhone());
			newCentralAcct.setTxt_emailAddress(cOffice.getEMail());

			//todo implement Rank/Grade
			//            String[] ranks = agency.getGradeAndStatuses();
			//            TemplateDataBasic rank = new TemplateDataBasic("cmb_grade");
			//            if (ranks != null) {
			//                for (int i = 0; i < ranks.length; i++) {
			//                    rank.rowAppend("optClick=" + ranks[i] + ";optVisible=" + ranks[i]);
			//                }
			//            } else {
			//                rank.rowAppend("optClick=DataMissing;optVisible=No Data Found");
			//            }
			//            combined.groupAppend(rank);

			// todo implement Employment Status
			//            String[] emplStatuses = agency.getEmploymentStatuses();
			//            TemplateDataBasic status = new TemplateDataBasic("cmb_status");
			//            if (emplStatuses != null) {
			//                for (int i = 0; i < emplStatuses.length; i++) {
			//                    status.rowAppend("multiStatus=" + emplStatuses[i] + ";optSee=" + emplStatuses[i]);
			//                }
			//            } else {
			//                status.rowAppend("multiStatus=DataMissing;optSee=No Data Found");
			//            }
			//            combined.groupAppend(status);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EaglsDAOError(e.getMessage());
		}
		return newCentralAcct;
	}

	/**
	 * retrieves Agency details by Central Account ID
	 * @param conn opened connection
	 * @param centralAcctID
	 * @param userId
	 * @return
	 * @throws SQLException
	 * @throws EaglsDAOError
	 */
	public AgencyCore retrieveAgencyCoreByCentralAcctID(
		Connection conn,
		int centralAcctID,
		String userId)
		throws SQLException, EaglsDAOError {

		//        transport.logDebug("retrieveAgencyCoreByCentralAcctID", "entered method");

		if (centralAcctID <= 0) {
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "centralAcctID unspecified");
			throw new EaglsDAOError(
				"APP_W0009:AccountDAA: centralAcctID unspecified" + ".");
		}

		AgencyCore newAgencyCore = new AgencyCore();
		CallableStatement cs = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			cs = conn.prepareCall(GET_AGENCY_INFO);
			int count = 1;
			cs.setString(count++, userId); //1 - iUserId
			cs.setString(count++, "" + centralAcctID);
			//2 - "iCentralAccountId"

			cs.registerOutParameter(count++, Types.VARCHAR);
			//3 "oPerformCreditChecks"
			for (short i = 0; i < 9; i++) {
				cs.registerOutParameter(count++, Types.INTEGER);
				// "oHL" + (new Short(i)).toString()
			}
			for (short i = 0; i < 9; i++) {
				cs.registerOutParameter(count++, Types.VARCHAR);
				// "oHL" + (new Short(i)).toString() + "Desc"
			}
			cs.registerOutParameter(count++, Types.VARCHAR); //22 "oAgencyName"
			cs.registerOutParameter(count++, Types.INTEGER); //  oAgencyID
			cs.registerOutParameter(count++, Types.VARCHAR);
			//"oAccountingCenterID"
			cs.registerOutParameter(count++, Types.VARCHAR); //"oFleetType"
			cs.registerOutParameter(count++, Types.VARCHAR); //"oResult"

			logger.debug("GET_AGENCY_INFO sql: " + GET_AGENCY_INFO);
			cs.execute();
			logger.debug("cs.getString(3): " + cs.getString(3));

			newAgencyCore.setPerformCreditChecks(
				stringToBoolean(cs.getString(3)));
			//            newAgencyCore.setPerformCreditChecks(stringToBoolean(cs.getString(3)));

			HierarchyDTO newHierarchy[] = new HierarchyDTO[9];
			for (short i = 0; i < 9; i++) {
				HierarchyDTO hLevel = new HierarchyDTO();
				hLevel.setLevel(i);
				hLevel.setNumber(cs.getInt(4 + i)); //":oHL" + i
				String tmpDesc = cs.getString(13 + i); //":oHL" + i + "Desc"
				if (tmpDesc == null || tmpDesc.equals("~")) {
					hLevel.setDescription("");
					hLevel.setAgencyName("");
				} else {
					hLevel.setDescription(tmpDesc);
					hLevel.setAgencyName(tmpDesc);
				}
				newHierarchy[i] = hLevel;
			}
			newAgencyCore.setHierarchy(newHierarchy);

			newAgencyCore.setAgencyName(cs.getString(22)); //"oAgencyName"
			newAgencyCore.setAgencyID(cs.getInt(23)); //"oAgencyID"
			newAgencyCore.setAccountingCenterID(cs.getString(24));
			//"oAccountingCenterID"
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "oPerformCreditChecks: " + outParams.getString(":oPerformCreditChecks"));

			short fleetType = 0;
			if ("M".equals(cs.getString(25))) { //"oFleetType"
				fleetType = AgencyCore.MASTERCARD;
			} else if ("V".equals(cs.getString(25))) { //"oFleetType"
				fleetType = AgencyCore.VOYAGER;
			} else {
				fleetType = AgencyCore.NONE;
			}
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "FLEETTYPE1: " + fleetType);
			newAgencyCore.setFleetType(fleetType);
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "FLEETTYPE2: " + fleetType);
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "checkpoint #1");  // DEBUG

			// another part: Accounting Center IDs
			st = conn.prepareStatement(SQL_ACCOUNTING_CENTER_IDS);
			st.setInt(1, centralAcctID);
			rs = st.executeQuery();

			List aVector = new LinkedList();
			while (rs.next()) {
				aVector.add(rs.getString(1));
			}
			String accountingCenterIds[] = new String[aVector.size()];
			aVector.toArray(accountingCenterIds);
			newAgencyCore.setAccountingCenterIDs(accountingCenterIds);
			closeAll(rs, st);
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "checkpoint #2");  // DEBUG
			st = conn.prepareStatement(SQL_SEARCH_CARD_TYPES);
			st.setInt(1, centralAcctID);
			rs = st.executeQuery();

			List newNDTVector = new LinkedList();
			while (rs.next()) {
				NameDescTable aNDT = new NameDescTable();
				aNDT.setName(rs.getString(1));
				aNDT.setDescription(rs.getString(2));
				newNDTVector.add(aNDT);
			}
			NameDescTable nameDescTable[] =
				new NameDescTable[newNDTVector.size()];
			newNDTVector.toArray(nameDescTable);
			newAgencyCore.setCardTypes(nameDescTable);
			closeAll(rs, st);
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "checkpoint #3");  // DEBUG
			// Employment Status
			String employmentStatus[] =
				retrieveEmploymentStatuses(conn, centralAcctID);
			newAgencyCore.setEmploymentStatuses(employmentStatus);
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "checkpoint #4");  // DEBUG

			// Rank Grade
			String rankGrade[] = retrieveRankGrades(conn, centralAcctID);
			newAgencyCore.setGradeAndStatuses(rankGrade);
			//            transport.logDebug("retrieveAgencyCoreByCentralAcctID", "end of method");  // DEBUG

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} finally {
			closeAll(null, st);
			closeAll(rs, st);
		}
		return newAgencyCore;

	}

	/**
	 * Retrieves Listing of Employment Statuses for the passed in central Acct ID
	 * @param   conn    opened connection
	 * @param   centralAcctID    identifies the central Acct ID
	 * @return  String[]        List of Employment Statuses
	 *
	 **/
	private String[] retrieveEmploymentStatuses(
		Connection conn,
		int centralAcctID)
		throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(SQL_SEARCH_EMPLOYMENT_STATUS);
			st.setInt(1, centralAcctID);
			rs = st.executeQuery();

			List anEmpStatus = new LinkedList();
			while (rs.next()) {
				anEmpStatus.add(rs.getString(1));
			}
			String employeeStatus[] = new String[anEmpStatus.size()];
			anEmpStatus.toArray(employeeStatus);
			return employeeStatus;

		} finally {
			closeAll(rs, st);
		}
	}

	/**
	 * Retrieves Listing of Ranks and Grades for the passed in central Acct ID
	 *
	 * @param conn opened connection
	 * @param   centralAcctID    identifies the central Acct ID
	 * @return  String[]        List of Ranks and Grades
	 *
	 **/
	public String[] retrieveRankGrades(Connection conn, int centralAcctID)
		throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(SQL_SEARCH_RANK_GRADE);
			st.setInt(1, centralAcctID);
			rs = st.executeQuery();

			List anRankGrade = new LinkedList();
			while (rs.next()) {
				anRankGrade.add(rs.getString(1));
			}
			String rankGrade[] = new String[anRankGrade.size()];
			anRankGrade.toArray(rankGrade);
			return rankGrade;
		} finally {
			closeAll(rs, st);
		}
	}

	/**
	 * get retrieve vendor names from DB
	 * @param conn opened connection
	 * @param centralAcctID gets vendor names by centralAcctID
	 * @return
	 * @throws SQLException
	 * @throws EaglsDAOError
	 */
	public NameDescTable[] retrieveVendorNames(
		Connection conn,
		String centralAcctID)
		throws SQLException, EaglsDAOError {

		PreparedStatement st = null;
		ResultSet rs = null;
		NameDescTable[] ndtArray = null;
		List aVector = new ArrayList();
		try {
			st = conn.prepareStatement(SQL_RETRIEVE_VENDOR_NAMES);
			st.setString(1, centralAcctID);
			rs = st.executeQuery();

			if (!rs.next()) {
				ndtArray = new NameDescTable[1];
				ndtArray[0] = new NameDescTable("", "");
			} else {
				while (rs.next()) {
					String str = rs.getString(1);
					NameDescTable ndt = new NameDescTable(str, rs.getString(2));
					aVector.add(ndt);
				}
			}
			ndtArray = new NameDescTable[aVector.size()];
			aVector.toArray(ndtArray);
		} finally {
			closeAll(rs, st);
		}
		return ndtArray;
		/*  Why are we catching this?  No Data Found needs to be thrown
		try{
		    rs = this.callQuery(sqlStmt.toString(), inParams.toIValList());
		}catch(NBException e){}
		*/

		/**RJ BUG#001543 Code added to Handle the NoDataFoundException
		 As Vendor Tables being non-existant for the account is an
		 acceptable condition.
		 So Just return an empty VendorTable list when a NoDataFoundException
		 is thrown. (08/11/1999)
		 */
	}

	public NameDescTable[] retrieveMCCGTableNamesByCentralAcctID(
		Connection conn,
		String centralAcctID)
		throws SQLException {
		PreparedStatement st = null;
		ResultSet rs = null;
		NameDescTable[] ndtArray = null;
		List aVector = new ArrayList();
		try {
			st = conn.prepareStatement(SQL_RETRIEVE_MCCGTABLE_NAMES);
			st.setString(1, centralAcctID);
			rs = st.executeQuery();

			while (rs.next()) {
				String str = rs.getString(1);
				NameDescTable ndt = new NameDescTable(str, rs.getString(2));
				aVector.add(ndt);
			}

			ndtArray = new NameDescTable[aVector.size()];
			aVector.toArray(ndtArray);
		} finally {
			closeAll(rs, st);
		}
		return ndtArray;

	}

	/**
	 * Returns a boolean if the given central account number indicating whether is exists in EAGLS
	 * @throws  SQLException
	 * @param   centralAcctID
	 * @return  boolean     indicates whether it is exists in EAGLS
	 **/
	public boolean validateCentralAcctID(
		Connection conn,
		int centralAcctID,
		String userID)
		throws SQLException {
		CallableStatement cs = null;

		try {
			logger.debug("-------******  ENTERING validateCentralAcctID");
			cs = conn.prepareCall(VALIDATE_CENTRAL_ACCOUNT_ID);
			logger.debug("userID: " + userID);
			cs.setString(1, userID);
			logger.debug("centralAcctID: " + centralAcctID);
			cs.setInt(2, centralAcctID);
			cs.registerOutParameter(3, Types.VARCHAR);

			cs.execute();

			logger.debug(
				"*********--------  EXITING validateCentralAcctID: "
					+ cs.getString(3));
			return "T".equals(cs.getString(3));
		} finally {
			cs.close();
		}

		//        IValListWrap inParams =   new IValListWrap();
		//        inParams.set(":iUserId", super.getUserID());
		//        inParams.set(":iCentralAccountId", centralAcctID);
		//        inParams.set(":oResult", EMPTY_STRING);
		//
		//       IValListWrap outParams = new IValListWrap(this.callStoredProc(VALIDATE_CENTRAL_ACCOUNT_ID, inParams.toIValList()));
	}

	/**
	 * Returns a true if the given central account number
	 * indicating whether it exists in EAGLS or is in the hierarchy of
	 * an A_OPC user (above or below)
	 * @param conn
	 * @param centralAcctID
	 * @param userHierarchy
	 * @return
	 * @throws SQLException
	 */
	public boolean validateCentralAcctIDForSetup(
		Connection conn,
		int centralAcctID,
		int[] userHierarchy)
		throws SQLException {
		int acctHierarchy[] = new int[HierarchyDisplay.NUMBER_OF_SEGMENTS];
		int hierarchyNumber;

		// get hierarchy for centralAcctID
		StringBuffer sqlStmt = new StringBuffer();
		sqlStmt.append(
			"SELECT H.HIERARCHY_NBR, H.HL0, H.HL1, H.HL2, H.HL3, H.HL4, H.HL5,\n");
		sqlStmt.append(" H.HL6, H.HL7, H.HL8\n");
		sqlStmt.append(" FROM ACCOUNT A, AGENCY_HL H\n");
		sqlStmt.append(" WHERE \n");
		sqlStmt.append(" A.HIERARCHY_NBR = H.HIERARCHY_NBR\n");
		sqlStmt.append(" AND A.AGENCY_BILL_NBR = '" + centralAcctID + "'");
		sqlStmt.append(" AND A.ACCOUNT_TYPE = 'C'");

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			logger.debug("SQL validateCentralAcctIDForSetup: " + sqlStmt);
			st = conn.prepareStatement(sqlStmt.toString());
			rs = st.executeQuery();
			if (rs.next()) {
				hierarchyNumber = rs.getInt(1);
				acctHierarchy[0] = rs.getInt(2);
				acctHierarchy[1] = rs.getInt(3);
				acctHierarchy[2] = rs.getInt(4);
				acctHierarchy[3] = rs.getInt(5);
				acctHierarchy[4] = rs.getInt(6);
				acctHierarchy[5] = rs.getInt(7);
				acctHierarchy[6] = rs.getInt(8);
				acctHierarchy[7] = rs.getInt(9);
				acctHierarchy[8] = rs.getInt(10);
			}
		} finally {
			closeAll(rs, st);
		}

		return validateHierarchyForAopcSetup(acctHierarchy, userHierarchy);
	}

	private boolean validateHierarchyForAopcSetup(
		int[] acctHierarchy,
		int userHierarchy[]) {
		if (userHierarchy == null) {
			return false;
		}
		try {
			HierarchyBase.sameHierarchyTree(userHierarchy, acctHierarchy);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Creates a database entry for a new UserAccount object.  This
	 * method should only be used by UserAccounts <code>save()</code> method.
	 * @param   userAccount   a UserAccount object filled with new data
	 * @exception   NBException   individualaccount already assigned to user role
	 */
	public void createUserAccount(UserAccountDTO userAccount)
		throws NBException {
		Dummy.notImplemented();
	}

	/**
	 * Constructs a call to a database stored procedure that will delete
	 * a given user individualaccount from the system. A valid user ID and role must
	 * be supplied along with the individualaccount number to be deleted.  This does
	 * not delete the individualaccount from the system, but just removes ownership of the individualaccount by the user.
	 * @param	UserID	the user whos individualaccount number will be deleted
	 * @param	RoleName	the role the deleted individualaccount number resides under
	 * @param	AccountNumber	the user individualaccount to delete
	 * @throws	NBException	stored procedure returns an error; stored procedures error message stored in exception
	 */
	public void deleteUserAccount(
		String UserID,
		String RoleName,
		String AccountNumber)
		throws NBException {
		Dummy.notImplemented();
	}

	/**
	 * Constructs a call to a database stored procedure that will save
	 * a given user individualaccount from the system. A valid UserAccountDTO must be supplied to work this function properly.
	 * @param	userAccount	A userAccountDTO which contains the information about the individualaccount to be saved
	 * @throws	NBException	stored procedure returns an error; stored procedures error message stored in exception
	 */
	public void saveUserAccount(UserAccountDTO userAccount)
		throws NBException {
		Dummy.notImplemented();
	}

	/**
	 * Retrieves the type of a given individualaccount number.
	 * @param	acctNbr	an individualaccount number.
	 * @return    String  String containing the individualaccount Type
	 * @return	the type of the individualaccount number specified by acctNbr; null if the individualaccount does not exist.
	 * @exception	NBError	error occurred while querying the database.
	 */
	public String getAccountType(String acctNbr) throws NBError {
		Dummy.notImplemented();
		return "";
	}

	/**
	 * Constructs a call to a database stored procedure that will load
	 * a given user individualaccount from the system. A valid user ID ,role , Account
	 * Number and UserAccountDTO must be supplied to load the User Account.
	 * @param	userID	the user whos individualaccount number will be deleted
	 * @param	roleName	the role under which this  individualaccount number resides
	 * @param	anAccount	the user individualaccount to load
	 * @param   userAccount     A UserAccountDTO which contains the information about the individualaccount which is required
	 * to load the user individualaccount
	 */
	public void loadUserAccount(
		String userID,
		String roleName,
		String anAccount,
		UserAccountDTO userAccount)
		throws NBError {
		Dummy.notImplemented();
	}

	/**
	 * @param userID
	 * @param HL1
	 * @return
	 * @throws NBError
	 */
	public boolean validateHL1(String userID, String HL1) throws NBError {
		Dummy.notImplemented();
		return false;
	}

	/**
	 * @param Account
	 * @param userID
	 * @return
	 * @throws NBError
	 */
	public boolean validateAcctHL1(String Account, String userID)
		throws NBError {
		Dummy.notImplemented();
		return false;
	}

	/**
	 * Constructs a call to a database stored procedure that will load individualaccount type
	 * of given individualaccount number from the system. A valid individualaccount number
	 * and User ID must be supplied to validate the individualaccount number.
	 * @param acctNumber String containing the individualaccount number which will validate
	 * @param sessionUserID String containing the UserID of the currently login User
	 * @return A short variable containing individualaccount type
	 * @throws NBException
	 * @throws NBError
	 * @throws NoDataFoundException
	 * @throws EAGLSException
	 * @throws DataConnException
	 */
	public short validateAccountNumber(String acctNumber, String sessionUserID)
		throws
			NBException,
			NBError,
			NoDataFoundException,
			EAGLSException,
			DataConnException {
		logger.debug(
			"Entering  in method  validateAccountNumber(String acctNumber, String sessionUserID) in AccountDAO");
		logger.debug("Param name=\"acctNumber\" , value = " + acctNumber);
		logger.debug("Param name=\"sessionUserID\" , value = " + sessionUserID);
		if (acctNumber == null
			|| sessionUserID == null
			|| sessionUserID == ""
			|| acctNumber == "") {
			throw new NBError("Account number or Session UserID is NULL in method validateAccountNumber");
		}

		// Getting the userId
		String userId = AccountConstants.EMPTY_STRING;

		//        try {
		if (sessionUserID != null) {
			userId = sessionUserID;
		}
		KeyValueList kvl = new KeyValueList();

		kvl.add("iUserId", userId, SQLConstants.INPUT_PARAMETER);
		kvl.add("iAccountNumber", acctNumber, SQLConstants.INPUT_PARAMETER);
		kvl.add(
			"oAccountType",
			AccountConstants.EMPTY_STRING,
			SQLConstants.OUTPUT_PARAMETER,
			new String());
		kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER, new String());
		HashMap outParams =
			callStoredProcedure(AccountConstants.VALIDATE_ACCOUNT, kvl);

		short acctType = 0;

		if (outParams.get("oAccountType") != null) {
			if ((outParams
				.get("oAccountType")
				.toString()
				.trim()
				.equals("C"))) {
				acctType = Constants.CENTRAL;
			} else if (
				(outParams
					.get("oAccountType")
					.toString()
					.trim()
					.equals("D"))) {
				acctType = Constants.DIVERSION_ACCT;
			} else if (
				(outParams
					.get("oAccountType")
					.toString()
					.trim()
					.equals("I"))) {
				acctType = Constants.INDIVIDUAL;
			} else if (
				(outParams
					.get("oAccountType")
					.toString()
					.trim()
					.equals("T"))) {
				acctType = Constants.MASTERCARD_VEHICLE;
			}
			logger.debug(
				"Return value of method validateAccountNumber in AccountDAO = "
					+ acctType);
		} else {
			throw new NBError("Invalid individualaccount number entered.");
		}
		return acctType;
	}

	/**
	 * Constructs a call to a database stored procedure that will load Hierarchy Agency Names
	 * of given hierarchy Number from the system. A valid Hierarchy Number must be supplied to get Hierarchy Agency Names
	 * @param hierarchyNumber
	 * @return HierarchyDisplay
	 * @throws NBException
	 * @throws NBError
	 * @throws Exception
	 */
	public HierarchyDisplay getHierarchyAgencyNames(int hierarchyNumber)
		throws NBException, NBError, Exception {
		logger.debug("Entering getHierarchyAgencyNames method in AccountDAO");
		logger.debug(
			"Param name=\"hierarchyNumber\" , value = " + hierarchyNumber);
		KeyValueList kvl = new KeyValueList();
		kvl.add("ihierno", "" + hierarchyNumber, SQLConstants.INPUT_PARAMETER);
		for (short i = 0; i < 9; i++) {
			kvl.add(
				"oHL" + i,
				"0",
				SQLConstants.OUTPUT_PARAMETER,
				new String());
		}
		for (short i = 0; i < 9; i++) {
			kvl.add(
				"oHL" + i + "AgencyName",
				"",
				SQLConstants.OUTPUT_PARAMETER,
				new String());
		}
		kvl.add("oResult", " ", SQLConstants.OUTPUT_PARAMETER, new String());
		logger.debug(
			"Going to call stored procedure "
				+ AccountConstants.SP_GET_HIERARCHY_AGENCY_NAME
				+ " in getHierarchyAgencyNames method in AccountDAO received ");
		java.util.HashMap outParams =
			callStoredProcedure(
				AccountConstants.SP_GET_HIERARCHY_AGENCY_NAME,
				kvl);

		logger.debug(
			"After calling stored procedure "
				+ AccountConstants.SP_GET_HIERARCHY_AGENCY_NAME
				+ " in getHierarchyAgencyNames method in AccountDAO , HashMAP return = "
				+ outParams);

		HierarchyDTO newHierarchy[] = new HierarchyDTO[9];

		for (short i = 0; i < 9; i++) {
			HierarchyDTO hLevel = new HierarchyDTO();

			hLevel.setLevel(i);
			hLevel.setNumber(
				Integer.parseInt((String) outParams.get("oHL" + i)));
			String tmpAgencyName =
				(String) outParams.get("oHL" + i + "AgencyName");
			hLevel.setAgencyName(tmpAgencyName);
			newHierarchy[i] = hLevel;
		}
		HierarchyDisplay hierDisplay = new HierarchyDisplay(newHierarchy);
		logger.debug(
			"Exiting getHierarchyAgencyNames method in AccountDAO , return value = "
				+ hierDisplay);
		return hierDisplay;
	}

	/**
	 * Retrieves requestID
	 *
	 * @param   String account Number and AccountControls
	 * @return  an integer which is a requestID, requestId is the unique id which identified
	 * that request in Quue table
	 * @exception com.nb.gsa.Exceptions.NBException
	 **/
	public int queueAccountControls(
		String tempAccountNumber,
		AcctControls anAcctControl)
		throws NBException {

		//**START**---------->Setting inParams in IValist
		IValListWrap inParams = new IValListWrap();
		inParams.set(":iUserId", getUserID());
		inParams.set(":iAccountNumber", tempAccountNumber);
		inParams.set(":iControlType", "A");
		String aDateString =
			DateUtil.convertDateToString(anAcctControl.getOtherRefreshDate());
		if (aDateString != null) {
			inParams.set(":iOtherRefreshDate", aDateString);
		} else
			inParams.setNull(":iOtherRefreshDate");
		Double purchaseVal = new Double(anAcctControl.getSinglePurchaseLimit());
		inParams.set(":iSingleTransLimit", purchaseVal);
		inParams.set(":iMCCGTableName", EMPTY_STRING);
		inParams.set(":iMCCGAction", EMPTY_STRING);
		inParams.set(
			":iForeignRestrictionSet",
			((anAcctControl.getForeignCurResSet()) ? "Y" : "N"));
		inParams.set(
			":iForeignType",
			((anAcctControl.getForeignType() == 0) ? "I" : "E"));
		String aForeignAction = null;
		if (anAcctControl.getForeignAction() == AcctControls.ACCEPT) {
			aForeignAction = "A";
		} else if (anAcctControl.getForeignAction() == AcctControls.REFER) {
			aForeignAction = "R";
		} else if (anAcctControl.getForeignAction() == AcctControls.DECLINE) {
			aForeignAction = "D";
		}
		inParams.set(":iForeignAction", aForeignAction);
		inParams.set(
			":iVendorCheck",
			((anAcctControl.getVendorCheck()) ? "Y" : "N"));
		String aVendorType = null;
		if (anAcctControl.getVendorType() == anAcctControl.INCLUDE) {
			aVendorType = "I";
		} else if (anAcctControl.getVendorType() == anAcctControl.EXCLUDE) {
			aVendorType = "E";
		}
		inParams.set(":iVendorType", aVendorType);
		String aVendorAction = null;
		if (anAcctControl.getVendorAction() == anAcctControl.ACCEPT) {
			aVendorAction = "A";
		} else if (anAcctControl.getVendorAction() == anAcctControl.DECLINE) {
			aVendorAction = "D";
		} else if (anAcctControl.getVendorAction() == anAcctControl.REFER) {
			aVendorAction = "R";

		}
		inParams.set(":iVendorAction", aVendorAction);
		inParams.set(
			":iPreferredVendorTable",
			(anAcctControl.getVendorTableName()));
		inParams.set(
			":iDailyTransactionLimit",
			(anAcctControl.getDailyTransLimit()));
		Double dailyAmtVal = new Double(anAcctControl.getDailyAmtLimit());
		inParams.set(":iDailyAmountLimit", dailyAmtVal);
		inParams.set(
			":iTotalCycleTransactions",
			(anAcctControl.getCycleTransLimit()));
		Double cycleAmtVal = new Double(anAcctControl.getCycleAmtLimit());
		inParams.set(":iCycleAmountLimit", cycleAmtVal);
		inParams.set(
			":iMonthlyTransactionLimit",
			(anAcctControl.getMonthlyTransLimit()));
		Double monthlyAmtVal = new Double(anAcctControl.getMonthlyAmtLimit());
		inParams.set(":iMonthlyAmountLimit", monthlyAmtVal);
		inParams.set(
			":iOtherTotalNumberOfDays",
			(anAcctControl.getOtherNoDays()));
		inParams.set(":iOtherTotalTrans", (anAcctControl.getOtherTransLimit()));
		Double totalAmtVal = new Double(anAcctControl.getOtherAmtLimit());
		inParams.set(":iOtherTotalAmountLimit", totalAmtVal);
		//Calling the private method wich returns the sequential number from Oracle
		int requestIdInt = doGetRequestId();
		inParams.set(":iRequestID", requestIdInt);
		inParams.set(":oResult", EMPTY_STRING);
		//**END**---------->Setting inParams in IValist

		//Calling the store Procdeure--------->SET_AUTH_CONTROL(AUTHORIZATION_CONTROL_SETUP.SET_AUTH_CONTROLS)
		IValListWrap outParams =
			new IValListWrap(
				this.callStoredProc(SET_AUTH_CONTROL, inParams.toIValList()));

		return requestIdInt;
	}
	/**
	* Retrieves integer
	*
	* @return  an integer which is a requestID, a sequential number receibved from Oracle
	* @exception com.nb.gsa.Exceptions.NBException
	**/
	public int doGetRequestId() throws NBException {

		//**START**---------->Setting inParams in IValist
		IValListWrap inReqParams = new IValListWrap();
		inReqParams.set(":iUserId", getUserID());
		inReqParams.set(":oRequestID", 0);
		inReqParams.set(":oResult", EMPTY_STRING);
		//**END**---------->Setting inParams in IValist

		//Calling the store Procdeure--------->GET_REQUEST_ID(AUTHORIZATION_CONTROL_SETUP.GET_REQUEST_ID)
		IValListWrap outReqParams =
			new IValListWrap(
				this.callStoredProc(GET_REQUEST_ID, inReqParams.toIValList()));

		//Checking for the requestID, which can never be null, just a sanity check
		if (outReqParams.getInteger(":oRequestID") == null) {
			return 0;
		} else {
			return (outReqParams.getInteger(":oRequestID").intValue());
		}
	}

	/** This method returns the userid and if the userid is null
	 * throws NBExcception
	 */

	protected String getUserID() throws NBException {
		String userId = transport.getUserID();
		if (userId == null)
			throw new NBWarning("Userid is null");
		return userId;
	}

		public final static String GET_AGENCY_INFO =
			"{call STANDARD_OBJECTS_2.GET_AGENCY_INFO(" + "?," + //iUserId
		"?," + // iCentralAccountId
		"?," + // oPerformCreditChecks 3
		"?," + // 4 oHL0
		"?," + // 5 oHL1
		"?," + // 6 oHL2
		"?," + // 7 oHL3
		"?," + // 8 oHL4
		"?," + // 9 oHL5
		"?," + // 10 oHL6
		"?," + // 11 oHL7
		"?," + // 12 oHL8
		"?," + // 13 oHL0Desc
		"?," + // 14 oHL1Desc
		"?," + // 15 oHL2Desc
		"?," + // 16 oHL3Desc
		"?," + // 17 oHL4Desc
		"?," + // 18 oHL5Desc
		"?," + // 19 oHL6Desc
		"?," + // 20 oHL7Desc
		"?," + // 21 oHL8Desc
		"?," + // 22 oAgencyName
		"?," + // 23 oAgencyID
		"?," + // 24 oAccountingCenterID
		"?," + // 25 oFleetType
	"?)}"; // 26 oResult

	public final static String SQL_ACCOUNTING_CENTER_IDS =
		"SELECT ACCOUNTING_CENTER_ID\n"
			+ "FROM BILLING_AGENCY\n"
			+ "WHERE AGENCY_BILL_NBR = ?\n";

		public final static String SQL_SEARCH_CARD_TYPES =
			"SELECT c.CMID, c.DESCRIPTION "
				+ "FROM CARD_DESIGN c, ACCOUNT a "
				+ "WHERE a.ACCOUNT_TYPE = 'C' "
	+ //MM--MODIFIED AS PER REQUETS FROM PERFORMANCE TEAM
	"AND a.AGENCY_BILL_NBR = ? " + "AND a.HIERARCHY_NBR = c.HIERARCHY_NBR(+) ";

	public final static String SQL_SEARCH_EMPLOYMENT_STATUS =
		"SELECT /*+ RULE */ e.STATUS, e.DESCRIPTION"
			+ " FROM EMPLOYMENT_STATUS e "
			+ " WHERE E.HIERARCHY_NBR = (SELECT A.HIERARCHY_NBR \n "
			+ " FROM ACCOUNT a \n "
			+ " WHERE a.ACCOUNT_TYPE = 'C' "
			+ " AND a.AGENCY_BILL_NBR = ?)";
	//:iCentralAccountId

	//Added order by - VK
		public final static String SQL_SEARCH_RANK_GRADE =
			" SELECT r.RANK_GRADE "
				+ " FROM RANK_GRADE r "
				+ " WHERE r.HIERARCHY_NBR = (SELECT A.HIERARCHY_NBR \n "
				+ " FROM ACCOUNT A \n "
				+ " WHERE A.ACCOUNT_TYPE = 'C' \n "
				+ " AND A.AGENCY_BILL_NBR = ?) \n "
	+ //:iCentralAccountId
	" ORDER BY r.RANK_GRADE ";

		public final static String GET_CENTRAL_ID_INFO =
			"{call STANDARD_OBJECTS_2.GET_CENTRAL_ID_INFO(" + "?," + //iUserId
		"?," + // iCentralAccountId
		"?," + //oCentralAccountName
		"?," + // oCentralAccountNumber
		"?," + //oConvenienceChecks
		"?," + //oBillingType
		"?," + //oTravelerChecks
		"?," + //oAtmAccess
		"?," + //oDecrementingCard
		"?," + // oProgramType
		"?," + //oAccountingCenterID
	"?)}"; // oResult
	
		public final static String GET_REQUEST_ID =
			"AUTHORIZATION_CONTROL_SETUP.GET_REQUEST_ID("
				+ ":iUserId,"
				+ ":oRequestID,"
				+ 
	// p_CentralAccountID IN
	":oResult)";

	private static int QUERY_iUserId = 1;
	private static int QUERY_iCentralAccountId = 2;
	private static int QUERY_oCentralAccountName = 3;
	private static int QUERY_oCentralAccountNumber = 4;
	private static int QUERY_oConvenienceChecks = 5;
	private static int QUERY_oBillingType = 6;
	private static int QUERY_oTravelerChecks = 7;
	private static int QUERY_oAtmAccess = 8;
	private static int QUERY_oDecrementingCard = 9;
	private static int QUERY_oProgramType = 10;
	private static int QUERY_oAccountingCenterID = 11;
	private static int QUERY_oResult = 12;

	public final static String EMPTY_STRING = " ";

	public final static String VALIDATE_CENTRAL_ACCOUNT_ID =
		"{call STANDARD_OBJECTS_2.VALIDATE_CENTRAL_ACCOUNT_ID(" + "?," +
		//p_userid IN
		"?," + // p_CentralAccountID IN
	"?)}"; //result OUT

	public static final String SQL_RETRIEVE_VENDOR_NAMES =
		"\nSELECT /*+ RULE */ preferred_vendor_table_name, vendor_description\n"
			+ " FROM preferred_vendor\n"
			+ " WHERE agency_bill_nbr = ?\n";
	//:iCentralAcctID

	public static final String SQL_RETRIEVE_MCCGTABLE_NAMES =
		"SELECT /*+ RULE */ mccg_table_name, mccg_description\n"
			+ " FROM mccg\n"
			+ " WHERE agency_bill_nbr = ?\n";
			
	public final static String SET_AUTH_CONTROL =
		"AUTHORIZATION_CONTROL_SETUP.SET_AUTH_CONTROLS("
			+ ":iUserId,"
			+ ":iControlType,"
			+ ":iAccountNumber,"
			+ ":iSingleTransLimit,"
			+ ":iMCCGTableName,"
			+ ":iMCCGAction,"
			+ ":iForeignRestrictionSet,"
			+ ":iForeignType,"
			+ ":iForeignAction,"
			+ ":iVendorCheck,"
			+ ":iVendorType,"
			+ ":iVendorAction,"
			+ ":iPreferredVendorTable,"
			+ ":iDailyTransactionLimit,"
			+ ":iDailyAmountLimit,"
			+ ":iTotalCycleTransactions,"
			+ ":iCycleAmountLimit,"
			+ ":iMonthlyTransactionLimit,"
			+ ":iMonthlyAmountLimit,"
			+ ":iOtherRefreshDate,"
			+ ":iOtherTotalNumberOfDays,"
			+ ":iOtherTotalTrans,"
			+ ":iOtherTotalAmountLimit,"
			+ ":iRequestID,"
			+ ":oResult)";
	//New one

}
