<%@ page import="com.boa.eagls.government.controller.action.transactiondata.individualstatement.Actions,
                 com.boa.eagls.government.controller.formbean.transactiondata.IndividualStmFormValues,
                 com.boa.eagls.government.controller.action.transactiondata.individualstatement.SessionParams,
                 java.util.Collection,
                 java.util.Iterator,
                 com.boa.eagls.government.dto.transactiondata.TransactionDataDTO"%>
<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%
/**
 *
 *
 * @author Stanislav Shabalin VDI
 * @version $Revision: 1.6 $
 * revision of :search_transIndStatement.html
 *
 **/
%>
   <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<HTML>
<HEAD>
<TITLE>Individual Statment Search Multiple Results</TITLE>
<META NAME="Name" CONTENT="Multiple Statements Screen">
<META NAME="Description" CONTENT="Search Results for Multiple Statements">
<META NAME="Author" CONTENT="David Taylor">
<META NAME="Created" CONTENT="04 August 1998">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>
<html:html>

<!-------------------------------------------------------------------------->

<BODY>
<template:insert template="/jsp/gsa/common/header_systemDefault.jsp" >
    <template:put name="screenTitle-1" content="Multiple Result" direct="true"/>
    <template:put name="screenTitle-2" content="Screen" direct="true"/>
</template:insert>

<BR/><html:img src="jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<%
    Collection col = (Collection) session.getAttribute(SessionParams.SEARCH_RESULTS);
    Iterator it = col.iterator();
    while (it.hasNext()) {
        Collection innerCol = (Collection) it.next();
        Iterator innerIt = innerCol.iterator();
        boolean first = true;
        while (innerIt.hasNext()) {
            TransactionDataDTO agency = (TransactionDataDTO) innerIt.next();//shows details
            if (first) {
                first = false;//shows header
                %>
                 The following statements are available for the month <u><%=agency.getStmt_Date().substring(0, 10)%></u>
                 <ul>
                <%
            }
            %><li>
            <a href="#"><%=agency.getFirst_Name()%> <%=agency.getLast_Name()%> <%=agency.getAgency_Name()%> <%=agency.getAccount_Nbr1()%></a>
            </li><%
        }
        %>
        </ul>
        <%
    }
%>

<BR>

<TABLE>
 <TR>
  <TD WIDTH=575 ALIGN = "CENTER">
    <INPUT TYPE="button" NAME="but_revise" VALUE="Revise Search"
       onClick="history.go(-1)">
  </TD>
 </TR>
</TABLE>


<BR/>
<html:img src="jsp/gsa/images/line.gif" width="575" height="6"/>
<template:insert template="/jsp/gsa/common/footer_systemDefault.jspf" />
</html:form>
</BODY>

</html:html>
