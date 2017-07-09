<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/boa/app" prefix="eagls" %>
<HTML>
<HEAD>

<TITLE>Central Account Maintenance Verification</title>

<META NAME="Name"           CONTENT="Central Account Maintenance Verification">
<META NAME="Description"    CONTENT="Central Account Maintenance Data Entry Screen">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>


</HEAD>

<!--------------------------------------------------------------------------->

<BODY>

<html:form name="frm_centralAccountMaintenanceForm" action="/maintenance/centralAccount/centralAccountConfirmation"
 method="POST" type="com.boa.eagls.government.controller.formbean.maintenance.centralaccount.CentralAccountForm">
<%--<FORM NAME="frm_verifCentralAccountMaintenance"--%>
<%--    METHOD="POST"--%>
<%--    TARGET="content"--%>
<%--    ACTION="/cgi-bin/gx.cgi/GUIDGX-{60F0D2E7-2ACA-11D2-92D2-0000F6AACD5A}">--%>

<table WIDTH="575">
    <tr>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <td ALIGN="Center">
        <h2 class="titleText" >
          Central Account Maintenance<br>

          Verification
        </h2>
      </td>
    </tr>
</table>

<input TYPE="HIDDEN" NAME="txt_screenTitle1" value="Central Account Maintenance">

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<br>
<br>

<!--------------------------------------------------------------------------->

<table>
    <tr>
      <td WIDTH="575" ALIGN="Center">
        Please verify that the Central Account Maintenance information you have entered is correct.
      </td>
    </tr>
</table>
<br>

<!--------------------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Hierarchy Information</B></FONT>

<%@ include file="/jsp/gsa/common/right_hierarchy.jsp"%>
<BR>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR><BR>

<!--------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Central Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" >
        <B>Agency Name</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_centralAccountMaintenanceForm" property="agencyName" />&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Central Account ID</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="centralAccountID" />&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Central Account Number</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="centralAccountNumber" />&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">
        <B>Central Account Name</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_centralAccountMaintenanceForm" property="txt_centralAccountName"/>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR><BR>

<!--------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Account Attributes</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" >
        <B>Account Type</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_centralAccountMaintenanceForm" property="accountType"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Billing Type</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="billingType"/>&nbsp;
      </TD>
    </TR>
   <!-------------------------- <TR>
   Refer to Bug# : MDS0715
      <TD >
        <B>Fleet Type</B>
      </TD>
      <TD>
        <GX type=replace id=attributes.fleetType value="fleetType">fleetType</GX>&nbsp;
      </TD>
    </TR> -------------------------------->

</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">
        <B>Accounting Center ID</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_centralAccountMaintenanceForm" property="txt_accountingCenterID"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD COLSPAN="2">
        <B>Master Accounting Code</B></TD>
    </TR>
    <TR>
      <TD COLSPAN="2">
        <bean:write name="frm_centralAccountMaintenanceForm" property="txt_masterAccountingCode"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Account Expiration Date</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="txt_accountExpirationDate"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Credit Limit</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="creditLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Travelers Checks</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="cmb_travelerChecks"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>ATM Access</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="cmb_atmAccess"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Decrement Card</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="cmb_decrementCard"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>City Pair Program</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="cmb_citypairProgram"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Program Type</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="integratedType"/>&nbsp;
      </TD>
    </TR>
    <logic:present name="frm_centralAccountMaintenanceForm" property="integratedProgram">
      <TR>
        <TD>
          <B>Card Programs</B>
        </TD>
        <TD>
           <bean:write name="frm_centralAccountMaintenanceForm" property="cardPrograms"/>
        </TD>
      </TR>
    </logic:present>
    <TR>
      <TD>
        <B>Convenience Checks</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="cmb_convenienceChecks"/>&nbsp;
       </TD>
    </TR>
    <TR>
      <TD>
        <B>Number Convenience Checks</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="numberConvenienceChecks"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Invoice Medium</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="invoiceMedium"/>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<!--************HIDDEN FIELDS****************************-->

<html:hidden name="frm_centralAccountMaintenanceForm" property="agencyName"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="centralAccountID"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="centralAccountNumber"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="txt_centralAccountName"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="accountType"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="billingType"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="txt_accountingCenterID"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="txt_masterAccountingCode"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="txt_accountExpirationDate"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="creditLimit"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="cmb_travelerChecks"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="cmb_atmAccess"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="cmb_decrementCard"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="cmb_citypairProgram"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="integratedType"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="integratedProgram"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="cardPrograms"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="cmb_convenienceChecks"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="numberConvenienceChecks"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="invoiceMedium"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="txt_accountingCenterID"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="txt_accountingCenterID"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="txt_accountingCenterID"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="fleetType"/>
<html:hidden name="frm_centralAccountMaintenanceForm" property="programType"/>


<GX type=replace id=attributes.cityPair value=CityPair>
  <INPUT TYPE="Hidden" name="hdn_cityPair" value="CityPair">
</GX>

<GX type=replace id=attributes.eagls value=EAGLS>
  <INPUT TYPE="Hidden" name="hdn_eagls" value="EAGLS">
</GX>

<GX type=replace id=attributes.edi value=EDI>
  <INPUT TYPE="Hidden" name="hdn_edi" value="EDI">
</GX>

<GX type=replace id=attributes.paper value=PAPER>
  <INPUT TYPE="Hidden" name="hdn_paper" value="PAPER">
</GX>

<GX type=replace id=attributes.electronic value=ELECTRONIC>
  <INPUT TYPE="Hidden" name="hdn_electronic" value="ELECTRONIC">
</GX>



<!-------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <html:submit value="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;"/>&nbsp;&nbsp;

        <INPUT TYPE="Button" NAME="but_revise" VALUE="Revise"
                   onclick="history.go(-1)">
      </TD>
    </TR>
</TABLE>

<!-------------------------------------------------------------------------->

<%@ include file = "/jsp/gsa/footer_systemDefault.jsp"%>

</html:form>
</BODY>
</HTML>
