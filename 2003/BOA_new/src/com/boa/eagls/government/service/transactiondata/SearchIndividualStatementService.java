package com.boa.eagls.government.service.transactiondata;

import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.controller.action.transactiondata.individualstatement.Forwards;
import com.boa.eagls.government.controller.formbean.transactiondata.IndividualStatementForm;
import com.boa.eagls.government.controller.formbean.transactiondata.IndividualStmFormValues;
import com.boa.eagls.government.dto.transactiondata.TransactionDataDTO;
import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.dao.IndividualStatementDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 15.07.2003
 * Time: 16:41:14
 * To change this template use Options | File Templates.
 */
public class SearchIndividualStatementService extends Service {

    protected Logger log4j = Logger.getLogger(this.getClass());

    public static final String TXT_HL = "txt_hl";

    /**
     *
     * @param c
     * @return
     */
    public Object showStatementTransactionsScreen(SearchCriteria c) throws EaglsException {//throws NBException {

        IndividualStatementDAO dao = new IndividualStatementDAO();
        Connection con = null;

        try {
            con = getPooledConnection();
            dao.retrievePartialAccountSummary(con, c.getAcctNumber());
        } catch (SQLException e) {
            log4j.error("Short description here", e); //To change body of catch statement use Options | File Templates.
            throw new EaglsException("", e);
        } finally {
            closeConnection(con);
        }
        //valIn.setValString("accountNumber", acctNumber);
        //AccountSummary summ = IndividualAcctController.retrievePartialAccountSummary(acctNumber, getTransport());
        //acctName = summ.getFirstName() + " " + summ.getLastName();
        //short bType = summ.getBillingType();
        //String homeGUID;
        //valIn.setValString("bType", String.valueOf(bType));
        //valIn.setValString("hdn_sortFor", String.valueOf(sortTransBy));
        //valIn.setValString("hdn_searchFor", String.valueOf(searchFor));
        //valIn.setValString("hdn_acctName", acctName);
        //valIn.setValString("agencyName", summ.getAgencyName());
        //valIn.setValString("acctType", String.valueOf(summ.getAccountType()));
        //valIn.setValString("headerName", "Individual Statement");
        //valIn.setValString("acctgCenID", summ.getTxt_accountingCenterID());
        //valIn.setValString("accountNumber", acctNumber);
        //valIn.setValString("doneOneCycle", "true");
        //IValList jValList = GX.CreateValList();
        //IValList iValList = GX.CreateValList();
        //iValList.setValString("bType", String.valueOf(bType));
        //iValList.setValString("hdn_sortFor", String.valueOf(sortTransBy));
        //iValList.setValString("hdn_searchFor", String.valueOf(searchFor));
        //iValList.setValString("hdn_acctName", acctName);
        //iValList.setValString("agencyName", summ.getAgencyName());
        //iValList.setValString("acctType", String.valueOf(summ.getAccountType()));
        //iValList.setValString("headerName", "Individual Statement");
        //iValList.setValString("acctgCenID", summ.getTxt_accountingCenterID());
        //iValList.setValString("accountNumber", acctNumber);
        //iValList.setValString("doneOneCycle", "true");
        //iValList.setValString("searchGUID", createHyperlink("{FF253760-CA08-11D3-A7E4-204C4F4F5020}?searchType=individualStatement", jValList));
        //homeGUID = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
        //iValList.setValString("homeGUID", createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList));
        //valIn.setValString("homeGUID", homeGUID);
        //valIn.setValString("searchGUID", createHyperlink("{FF253760-CA08-11D3-A7E4-204C4F4F5020}?searchType=individualStatement", jValList));

        //IndStmtTransListAL indAL = new IndStmtTransListAL();
        //return indAL.execute(this, getTransport());
//		return newRequest("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", valIn, valOut);
        return new Integer(10);
    }

    public Object showIndStatementScreen(SearchCriteria c) {
        //valIn.setValString("hdn_sortFor", Short.toString(sortTransBy));
        //valIn.setValString("accountNumber", acctNumber);
        //valIn.setValString("hdn_searchFor", Short.toString(searchFor));
        //valIn.setValString("hdn_searchByDate", searchByDate);
        //valIn.setValString("hdn_searchFromDate", searchFromDate);
        //valIn.setValString("hdn_searchToDate", searchToDate);
        //IValList iValList = GX.CreateValList();
        //iValList.setValString("hdn_sortFor", Short.toString(sortTransBy));
        //iValList.setValString("accountNumber", acctNumber);
        //iValList.setValString("hdn_searchFor", Short.toString(searchFor));
        //iValList.setValString("hdn_searchByDate", searchByDate);
        //iValList.setValString("hdn_searchFromDate", searchFromDate);
        //iValList.setValString("hdn_searchToDate", searchToDate);
        //valIn.setValString("homeGUID", createHyperlink("{D6556170-3CF4-11D2-9C2A-204C4F4F5020}", iValList));
        //IndStmtAL var = new IndStmtAL();
        return Forwards.INDIVIDUAL_STATEMENT_SCREEN;
    }


    public Object showMultipleResultsScreen(SearchCriteria c, boolean selectedAcctNumber) throws EaglsException {
        ArrayList resultList = null;
        //LaRS resultSet = null;
        //int startRecord = 0;
        //int resultSetID;
        //String startRecordStr = valIn.getValString(START_RECORD);
        //S/tring forceUpdate = valIn.getValString(FORCE_UPDATE);
        //String searchForStr = valIn.getValString(SEARCH_FOR);
        //
        //TemplateMapBasic map;
        //IndividualStatementSummary[] result = null;
        //TemplateDataBasic criteria = new TemplateDataBasic("searchCriteria");
        //TemplateDataBasic combined = new TemplateDataBasic("combined");

        //pageParams.setValString(SEARCH_FOR, searchForStr);
        //initializeLargeResultSetSearch();   // initialize Flags for LargeResultSet
        //String holder = valIn.getValString(CHK_ACCOUNT_NUMBER);
        ///pageParams.setValString(CHK_ACCOUNT_NUMBER, holder);
        //boolean selectedAcctNumber = !(holder == null || holder.equals(""));
        if (selectedAcctNumber) {
            log4j.info("selectedAcctNumber");
            //criteria.rowAppend("criteria=AccountNumber;value=" + acctNumber);
        } else {
            log4j.info("Not selectedAcctNumber");
            //HierarchyDisplay hierarchy = new HierarchyDisplay(hCodes);
            //criteria.rowAppend("criteria=Hierarchy;value=" + hierarchy.getCombinedSegments());
        }

        Connection con = null;
        try {
            con = getPooledConnection();

            com.boa.eagls.government.dao.IndividualStatementDAO individualStatementDAO = new com.boa.eagls.government.dao.IndividualStatementDAO();
            String strDate = c.getSearchByDate();
            //todo replace value
            Collection collection = individualStatementDAO.searchStatementByHierarchyDate(con, "07/2001");
            resultList = new ArrayList();
            ArrayList innerList = null;
            Iterator it = collection.iterator();
            boolean first = true;
            String oldDate = "";
            String newDate = null;
            while (it.hasNext()) {
                TransactionDataDTO agency = (TransactionDataDTO) it.next();
                newDate = agency.getStmt_Date();
                if (first) {
                    first = false;
                    oldDate = newDate;
                    innerList = new ArrayList();
                    //log4j.info("first date " + newDate);
                }

                if (!(oldDate.equalsIgnoreCase(newDate))) {
                    resultList.add(innerList);
                    innerList = new ArrayList();
                    //log4j.info("new date " + newDate);
                } else {
                    innerList.add(agency);
                }
            }
            log4j.info("Result collection filled");
        } catch (SQLException e) {

            log4j.error("Short description here", e); //To change body of catch statement use Options | File Templates.
            throw new EaglsException(e);
        } finally {
            closeConnection(con);
        }
        //if (searchFor != 0) {
        //    map = getEAGLSTemplateMap("Individual Statement", "Account Search Results");
        //} else {
        //    map = getEAGLSTemplateMap("Current Transactions", "Account Search Results");
        //}
        //TemplateDataBasic results = new TemplateDataBasic("searchResults");
        //todo start record
        //coming in from search page or a forceupdate was called
        //if (startRecordStr == null || forceUpdate != null) {
        //    if (startRecordStr == null) {
        //        startRecord = 0;
        //    } else {
        //        startRecord = Integer.parseInt(startRecordStr);
        //    }

        /*    resultSetID = new java.util.Random().nextInt();
            pageParams.setValString(RESULTSET_ID, String.valueOf(resultSetID));

            if (searchFor != 2) {
                searchStatementByHierarchy(hCodes, depth, searchFor, sortTransBy, isCountFlag(), 10);
            } else {
                searchStatementByHierarchy(hCodes, depth, DisplayDateUtil.convertStringToExpiry(searchByDate), sortTransBy, isCountFlag(), 10/* per page*/


        //data has been retrived and put into the results array
        //now we copy the data into a lars
        /*    resultSet = new LaRS(session.getSessionID(), guid(), resultSetID);
            for (int k = 0; k < result.length; k++) {
                resultSet.writeRecord(result[k]);
            }
            resultSet.finishedWriting();
            setLaRS(resultSet);
        } else {
            //coming in from nextColumn-previous button, resultset has been saved
            //to a lars
            String rid = valIn.getValString(RESULTSET_ID);
            resultSetID = Integer.parseInt(rid);
            pageParams.setValString(RESULTSET_ID, rid);
            resultSet = getLaRS();
            //verify resultset
            if (!resultSet.validate(guid(), resultSetID)) {
                valIn.setValString(FORCE_UPDATE, "force");
                return newRequest(guid(), valIn, valOut);
            }

            //retrieve the starting record for the page or reset to 0
            startRecord = Integer.parseInt(startRecordStr);
            if (startRecord > resultSet.numberOfRecords()) {
                startRecord = 0;
            }

            //copy all input params
            pageParams = copyUserValInParams();
        }

        //try and restore the resultset from the saved lars
        Object[] tempRecs = null;
        if (resultSet.numberOfRecords() >= 1) {
            tempRecs = resultSet.readRecords(startRecord, getLarsRowsPerPage());
            result = new IndividualStatementSummary[tempRecs.length];

            for (int j = 0; j < tempRecs.length; j++) {
                result[j] = (IndividualStatementSummary) tempRecs[j];
            }
        }

        String hyperlink;
        String pType = "";
        if (result != null) {
            if (result.length > 1) {
                for (int i = 0; i < result.length; i++) {
                    acctName = result[i].getFirstName() + " " + result[i].getLastName();
                    IValList iValList = GX.CreateValList();
                    iValList.setValString("bType", String.valueOf(result[i].getBillingType()));
                    iValList.setValString("acctType", String.valueOf(result[i].getAccountType()));
                    iValList.setValString("accountNumber", result[i].getIndAccountNumber());
                    iValList.setValString("hdn_acctName", result[i].getFirstName() + " " + result[i].getLastName());
                    iValList.setValString("hdn_sortFor", String.valueOf(sortTransBy));
                    iValList.setValString("hdn_searchFor", String.valueOf(searchFor));
                    iValList.setValString("hdn_searchByDate", DisplayDateUtil.convertDateToString(result[i].getStatementDate()));
                    iValList.setValString("haventChosenStatement", "false");
                    iValList.setValString("hdn_accName", acctName);
                    iValList.setValString("agencyName", result[i].getAgencyName());
                    iValList.setValString("acctgCenID", result[i].getTxt_accountingCenterID());
                    if (searchFor == TransactionController.CURRENT_TRANSACTIONS) {
                        iValList.setValString("headerName", "Individual Statement");
                        iValList.setValString("homeGUID", createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList));
                        IValList jValList = GX.CreateValList();
                        iValList.setValString("searchGUID", createHyperlink("{FF253760-CA08-11D3-A7E4-204C4F4F5020}?searchType=individualStatement", jValList));
                        hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
                    } else {
                        iValList.setValString("homeGUID", createHyperlink("{D6556170-3CF4-11D2-9C2A-204C4F4F5020}", iValList));
                        iValList.setValString("hdn_searchByDate", DisplayDateUtil.convertDateToString(result[i].getStatementDate()));
                        hyperlink = createHyperlink("{D6556170-3CF4-11D2-9C2A-204C4F4F5020}", iValList);
                    }

                    switch (result[i].getProgramType()) {
                        case IndividualStatementSummary.PURCHASE:
                            pType = "Purchase";
                            break;
                        case IndividualStatementSummary.TRAVEL:
                            pType = "Travel";
                            break;
                        case IndividualStatementSummary.FLEET:
                            pType = "Fleet";
                            break;
                        case IndividualStatementSummary.INTEGRATE:
                            // CD 9/10/99
                            //   BUG MDS2321
                            //   Description: Change Integrate to Integrated
                            //
                            pType = "Integrated";
                            break;
                        case IndividualStatementSummary.INTERAGENCY:
                            pType = "Interagency";
                            break;
                    }

                    results.rowAppend("individualAccountNumber=" + result[i].getIndAccountNumber() +
                            ";accountHolderName=" + result[i].getLastName() + ", " + result[i].getFirstName() +
                            ";agencyName=" + result[i].getAgencyName() +
                            ";centralAccountNumber=" + result[i].getCenAccountNumber() +
                            ";programType=" + pType +
                            ";statementDate=" + DisplayDateUtil.convertDateToString(result[i].getStatementDate()) +
                            ";hyperlink=" + hyperlink);

                }
                if (searchFor == TransactionController.CURRENT_TRANSACTIONS) {
                    map.putString("showStatementDate", "");
                } else {
                    map.putString("showStatementDate", null);
                }
                combined.rowAppend("");
                combined.groupAppend(criteria);
                combined.groupAppend(results);
                mapMultiplePageSymbols(map, guid(), pageParams, pageParams,
                        startRecord, getLarsRowsPerPage(),
                        resultSet.numberOfRecords(),
                        "{FF253760-CA08-11D3-A7E4-204C4F4F5020}?searchType=individualStatement");
                return evalTemplate("gsa/transactions/statement/results_transIndStatementMultipleResults.html", combined, map);
            } else if (result.length == 1) {
                if (searchFor == TransactionController.CURRENT_TRANSACTIONS) {
                    acctNumber = result[0].getIndAccountNumber();
                    return showStatementTransactionsScreen();
                } else {
                    acctNumber = result[0].getIndAccountNumber();
                    return showIndStatementScreen();
                }
            }
        }
        map.putString("errmsg", "No matches found");*/
        return resultList;//"";//evalTemplate("gsa/common/results_noResultsFound.html", (com.kivasoft.ITemplateData) null, map);
    }
}