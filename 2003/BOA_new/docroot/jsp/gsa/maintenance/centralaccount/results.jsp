<%@ page import="java.util.Collection,
                 java.util.Iterator,
                 com.boa.eagls.government.dto.centralaccount.CentralAcctSummary,
                 com.boa.eagls.government.controller.action.inquiry.PointOfContactParams"%>
<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/boa/app" prefix="eagls" %>
<HTML>
<HEAD>

<TITLE>Central Account Maintenance Search Results</TITLE>

<META NAME="Author"         CONTENT="Kristy Kurtzman">
<META NAME="Description"    CONTENT="Multiple results on Central Account search">
<META NAME="Created"        CONTENT="07/23/1998">
<META NAME="Revised"        CONTENT="09/20/1998 Edited -BB">
<SCRIPT language="JavaScript">
var isIE = document.all ? true:false;

if ( isIE ) {
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}
</SCRIPT>

</HEAD>

<!--------------------------------------------------------------->

<BODY>

 <FORM NAME="frm_centralAccountMaintMultipleResults"
      METHOD="POST"
      TARGET="content"
      ACTION="/cgi-bin/gx.cgi/GUIDGX-{60F0D2E4-2ACA-11D2-92D2-0000F6AACD5A}"
      onSubmit="return formTest(document.frm_centralAccountMaintMultipleResults)">
<%--<html
m action="/maintenance/centralAccount/search" method="POST" onsubmit="return formTest(document.forms[0])">--%>
<bean:parameter id="searchType" name="searchType"/>
<INPUT TYPE="hidden" NAME="searchType" VALUE='<%=request.getParameter("searchType")%>'>

<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Central Account
          <logic:equal parameter="searchType" value="centralAccountMaintenance">
             Maintenance
          </logic:equal>
          <logic:equal parameter="searchType" value="centralAccountInquiry">
             Inquiry
          </logic:equal><BR>
          Search Results
        </H2>
      </TD>
    </TR>
</TABLE>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>
<TABLE>
    <TR>
      <TD><FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Search Criteria</B></TD>
      <TD COLSPAN="2">&nbsp;</TD>
    </TR>

<%--    <logic:equal value="true" property="chk_centralAccountNumber">Number Equal</logic:equal>--%>


    <logic:equal value="true" name="searchCentralAccountForm" property="chk_centralAccountNumber">
        <TR>
            <TD><B>Account Number:</B></TD>
            <TD><bean:write name="searchCentralAccountForm" property="txt_centralAccountNumber"/></TD>
        </TR>
    </logic:equal>

    <logic:equal value="false" name="searchCentralAccountForm" property="chk_centralAccountNumber">
        <logic:equal value="true" name="searchCentralAccountForm" property="chk_centralAccountID">
            <TR>
                <TD><B>Central Account ID:</B></TD>
                <TD><bean:write name="searchCentralAccountForm" property="txt_centralAccountID"/></TD>
            </TR>
       </logic:equal>
        <logic:equal value="true" name="searchCentralAccountForm" property="chk_centralAccountName">
            <TR>
                <TD><B>Central Account Name:</B></TD>
                <TD><bean:write name="searchCentralAccountForm" property="txt_centralAccountName"/></TD>
            </TR>
       </logic:equal>
        <logic:equal value="true" name="searchCentralAccountForm" property="chk_hierLevel">
            <TR>
                <TD><B>Hierarchy:</B></TD>
                <TD>
                    <bean:write name="searchCentralAccountForm" property="txt_hl0"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl1"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl2"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl3"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl4"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl5"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl6"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl7"/>
                    <bean:write name="searchCentralAccountForm" property="txt_hl8"/>
                </TD>
            </TR>
       </logic:equal>
    </logic:equal>

</TABLE>
<BR>
<BR>
<!--
<GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX><BR>

<GX type=include id="/gsa/common/object_searchCriteria.html">Insert Search Criteria Here</GX><BR>
-->
<!--------------------------------------------------------------->

<eagls:pagedList howMany="50">
    <eagls:pagedListHeader>
        <TABLE BORDER="1" WIDTH="700">
        <TR>
            <TH WIDTH="188"  ALIGN="Center">
                Central Account Number
            </TH>
            <TH WIDTH="188"  ALIGN="Center">
                Central Account ID
            </TH>
            <TH WIDTH="187"  ALIGN="Center">
                Central Account Name
            </TH>
        </TR>
    </eagls:pagedListHeader>
    <eagls:pagedListBody id="CentralAcctSummary" name="searchResult" >
        <tr>
            <td><a href="/boa/maintenance/centralAccount/search.do?chk_centralAccountNumber=true&txt_centralAccountNumber=<bean:write name='CentralAcctSummary' property='centralAcctNo'/>&searchType=<%=request.getParameter("searchType")%>">
                <bean:write name="CentralAcctSummary" property="centralAcctNo"/>
                </a>&nbsp;</td>
            <td><bean:write name="CentralAcctSummary" property="centralAcctID"/>&nbsp;</td>
            <td><bean:write name="CentralAcctSummary" property="centralAcctName"/>&nbsp;</td>
        </tr>
    </eagls:pagedListBody>
    <eagls:pagedListBar>
        </TABLE>
        <TABLE BORDER="1" WIDTH="700">
            <tr>
                <td align="center"><eagls:previousButton searchParameter = '<%=request.getParameter("searchType")%>'/></td>
                <td align="center"><eagls:nextButton searchParameter = '<%=request.getParameter("searchType")%>'/></td>
            </tr>
        </TABLE>
    </eagls:pagedListBar>
</eagls:pagedList>


<!-------------------------------------------------------------------------->
<!--
<GX type=include id="/gsa/common/object_multiplePageControls.html">Insert Multiple Page Controls Here</GX><BR>
-->

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/reviseSearch.js"></SCRIPT>
<BR>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Button" NAME="but_revise" VALUE="Revise Search"
                   onclick="goBack();">
          &nbsp;&nbsp;
       </TD>
    </TR>
</TABLE>
<BR>

<%@ include file = "/jsp/gsa/footer_systemDefault.jsp"%>

</form>
</BODY>

</HTML>
