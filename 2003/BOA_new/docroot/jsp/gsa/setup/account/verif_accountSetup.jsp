<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ page import="com.boa.eagls.government.controller.formbean.account.ViewAccountForm"%>
<HTML>
<HEAD>

<TITLE>Account Setup Verification</TITLE>

<META NAME="Name" CONTENT="Account Setup Verification">
<META NAME="Description" CONTENT="Account Setup Data Entry Screen">
<META NAME="Author" CONTENT="Todd Shuler">
<META NAME="Created" CONTENT="07/08/1998">
<META NAME="Revised" CONTENT="09/23/1998 Edited -BB">
<META NAME="Revised" CONTENT="11/12/1998 Edited -DT,
            Comments added to facilitate USDA interface development.">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>

<!------------------------------------------------------------------------------->

<BODY>
<%
    pageContext.setAttribute("frm_verif_AccountSetup", session.getAttribute("frm_verif_AccountSetup"));
%>
<jsp:useBean
  id="frm_verif_AccountSetup"
  class="com.boa.eagls.government.controller.formbean.account.ViewAccountForm"
  scope="session"/>

<!--html:form name="frm_verif_AccountSetup"
        type="com.boa.eagls.government.controller.formbean.account.ViewAccountForm"
      scope="session"
      action="/setup/individualAccount/search"
      method="POST"
      target="content"-->
<form action="<%=request.getContextPath()%>/jsp/gsa/setup/account/confirm_accountSetup.jsp" method="POST" target="content">

<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Account Setup<BR>
          Verification
        </H2>
      </TD>
    </TR>
</TABLE>

<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Account Setup">

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>

<!------------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        Please verify that the Account Setup information you have entered is correct.
      </TD>
    </TR>
</TABLE>
<BR>

<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Account Hierarchy</B></FONT>

<%@ include file="/jsp/gsa/common/right_hierarchy.jsp"%>
<!--GX type=cell id=GCSU-->
<!------------------ PROGRAM NUMBER VISIBLE ONLY TO GCSU USERS ------------------>
<!--
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
    <TR>
      <TD WIDTH="144"><B>Program Number</B></TD>
      <TD WIDTH="427">
        <GX type=replace id=hierarchy.hdn_programNumber value=hdn_programNumber>
          hdn_programNumber&nbsp;
        </GX>
      </TD>
    </TR>
    <TR>
      <TD><B>Program Description</B></TD>
      <TD>
        <GX type=replace id=hierarchy.hdn_programDescription value=hdn_programDescription>
          hdn_programDescription&nbsp;
        </GX>
      </TD>
    </TR>
</TABLE>
<BR>
-->
<!------------------------------ END GCSU ONLY ------------------------------------>
<!--/GX-->
<BR>
<!--GX type=include id="/gsa/common/hierarchy.html">Insert Hierarchy here </GX-->
<BR>
<!--
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
    <TR>
      <TH WIDTH="72" ALIGN="Left" >HL1</TH>
      <TH WIDTH="72" ALIGN="Left" >HL2</TH>
      <TH WIDTH="72" ALIGN="Left" >HL3</TH>
      <TH WIDTH="71" ALIGN="Left" >HL4</TH>
      <TH WIDTH="71" ALIGN="Left" >HL5</TH>
      <TH WIDTH="71" ALIGN="Left" >HL6</TH>
      <TH WIDTH="71" ALIGN="Left" >HL7</TH>
      <TH WIDTH="71" ALIGN="Left" >HL8</TH>
    </TR>
    <TR>
      <TD><GX type=replace id=hierarchy.hl1 value=hl1>hl1&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl2 value=hl2>hl2&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl3 value=hl3>hl3&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl4 value=hl4>hl4&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl5 value=hl5>hl5&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl6 value=hl6>hl6&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl7 value=hl7>hl7&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl8 value=hl8>hl8&nbsp;</GX></TD>
    </TR>
    <TR>
      <TD><GX type=replace id=hierarchy.hl1Descr value=hl1Descr>hl1Descr&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl2Descr value=hl2Descr>hl2Descr&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl3Descr value=hl3Descr>hl3Descr&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl4Descr value=hl4Descr>hl4Descr&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl5Descr value=hl5Descr>hl5Descr&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl6Descr value=hl6Descr>hl6Descr&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl7Descr value=hl7Descr>hl7Descr&nbsp;</GX></TD>
      <TD><GX type=replace id=hierarchy.hl8Descr value=hl8Descr>hl8Descr&nbsp;</GX></TD>
    </TR>
</TABLE>
-->
<BR>

<!------------------------------------------------------------------------->

<html:img page="/jsp/gsa/images/line.gif" width="575"/><BR>

<!------------------------------------------------------------------------->

<BR>
<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Central Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213" ALIGN="Left" >
        Central Account ID
      </TH>
      <TD WIDTH="352">
        <bean:write scope="session" name="frm_verif_AccountSetup" property="hdn_centralAccountID"/>
        <!--GX type=replace id=attributes.centralAccountID value=centralAccountID-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Central Account Name
      </TH>
      <TD>
        <!--GX type=replace id=attributes.centralAccountName value=centralAccountName-->
        <bean:write scope="session" name="frm_verif_AccountSetup" property="hdn_centralAccountName"/>
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Central Account Number
      </TH>
      <TD>
        <!--GX type=replace id=attributes.centralAccountNumber value=centralAccountName-->
        <bean:write scope="session" name="frm_verif_AccountSetup" property="hdn_centralAccountNumber"/>
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Accounting Center ID</B></TD>
      <TD WIDTH="352">
        <!--GX type=replace id=attributes.accountingCenterID value=accountingCenterID-->
        <bean:write name="frm_verif_AccountSetup" property="txt_acctCenterID"/>
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD COLSPAN=2><B>Master Accounting Code</B></TD>
    </TR>
    <TR>
      <TD COLSPAN=2>
        <!--GX type=replace id=attributes.masterAccountingCode value=masterAccountingCode-->
        <bean:write name="frm_verif_AccountSetup" property="txt_masterAccountingCode"/>
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------------------------------------------------------->

<html:img page="/jsp/gsa/images/line.gif" width="575"/><BR>

<!------------------------------------------------------------------------->

<BR>
<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213" ALIGN="Left" >
        Program Type
      </TH>
      <TD WIDTH="352">
        <bean:write name="frm_verif_AccountSetup" property="hdn_programType"/>
        <!--GX type=replace id=attributes.programType value=programType-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Travelers Checks
      </TH>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="hdn_travelersCheckFlag"/>
        <!--GX type=replace id=attributes.travelersCheckFlag value=travelersCheckFlag-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Billing Type
      </TH>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="hdn_billingType"/>
        <!--GX type=replace id=attributes.billingType value=billingType-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Generate Paper Statement</B></TD>
      <TD WIDTH="352">
        <bean:write name="frm_verif_AccountSetup" property="cmb_generatePaperStatementFlag"/>
        <!--GX type=replace id=attributes.generatePaperStatementFlag
            value=generatePaperStatementFlag-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Card Type</B></TD>
      <TD>
        <!--unknown GX type=replace id=attributes.cardDesc value=cardDesc-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Card/Non-Card</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="cmb_cardNoncard"/>
<%--      cmbCardNoncard--%>
        <!--GX type=replace id=attributes.cardNoncard value=cardNoncard-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Account Expiration Date</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_accountExpirationDate"/>
<%--      txtAccountExpirationDate--%>
        <!--GX type=replace id=attributes.accountExpirationDate value=accountExpirationDate-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Credit Limit</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_creditLimit"/>
<%--      txtCreditLimit--%>
        <!--GX type=replace id=attributes.creditLimit value=creditLimit-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
</TABLE>
<BR>

<!---------------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR>

<!---------------------------------------------------------------------->

<BR>
<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Account Holder Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Last Name</B></TD>
      <TD WIDTH="352">
        <bean:write name="frm_verif_AccountSetup" property="txt_lastName"/>
<%--      txtLastName--%>
        <!--GX type=replace id=information.lastName value=lastName-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>First Name</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_firstName"/>
<%--        txtFirstName--%>
        <!--GX type=replace id=information.firstName value=firstName-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Address 1</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_addressLine1"/>
<%--        txtAddressLine1--%>
        <!--GX type=replace id=information.address value=address-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Address 2</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_addressLine2"/>
<%--        txtAddressLine2--%>
        <!--GX type=replace id=information.address2 value=address2-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Address 3</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_addressLine3"/>
<%--        txtAddressLine3--%>
        <!--GX type=replace id=information.address3 value=address3-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Address 4</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_addressLine4"/>
<%--        txtAddressLine4--%>
        <!--GX type=replace id=information.address4 value=address4-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>City</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_city"/>
<%--        txtCity--%>
        <!--GX type=replace id=information.city value=city-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>State/Province</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_state"/>
<%--        txtState--%>
        <!--GX type=replace id=information.state value=state-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Country</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_country"/>
<%--        txtCountry--%>
        <!--GX type=replace id=information.country value=country-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Zip/Postal Code</B></TD>
     <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_zipCode"/>
<%--        txtZipCode--%>
        <!--GX type=replace id=information.zipCode value=zipCode-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Work Phone</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_businessPhone"/>
<%--        txtBusinessPhone--%>
        <!--GX type=replace id=information.businessPhone value=businessPhone-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>E-mail Address</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_emailAddress"/>
<%--        txtEmailAddress--%>
        <!--GX type=replace id=information.emailAddress value=emailAddress-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
</TABLE>
<BR>

<!---------------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR>

<!---------------------------------------------------------------------->

<BR>
<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Identification</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Social Security Number</B></TD>
      <TD WIDTH="352">
        <bean:write name="frm_verif_AccountSetup" property="txt_socialSecurityNumber"/>
<%--        txtSocialSecurityNumber--%>
        <!--GX type=replace id=identification.ssn value=ssn-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Employee ID</B></TD>
      <TD>
        <bean:write name="frm_verif_AccountSetup" property="txt_employeeIdentificationNumber"/>
<%--        txtEmployeeIdentificationNumber--%>
        <!--GX type=replace id=identification.employeeID value=employeeID-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Grade</B></TD>
      <TD WIDTH="192">
        <bean:write name="frm_verif_AccountSetup" property="cmb_grade"/>
<%--        cmb_grade--%>
        <!--GX type=replace id=identification.cmb_grade value=cmb_grade-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
    <TR>
      <TD><B>Employment Status</B></TD>
      <TD>
        <!--unknown GX type=replace id=identification.status value=status-->
          &nbsp;
        <!--/GX-->
      </TD>
    </TR>
</TABLE>


<!------------------------- HIDDEN FIELDS---------------------------->

<!--GX type=replace id=hierarchy.programNumber value=ProgramNumber-->
<%--hdn_programNumber--%>
  <INPUT type="hidden" name="hdn_programNumber" value="<bean:write name="frm_verif_AccountSetup" property="hdn_programNumber"/>">
<!--/GX-->

<%--<GX type=replace id=hierarchy.programDescription value=ProgramDescription>--%>
<%--unknown--%>
  <INPUT type="hidden" name="hdn_programDescription" value="ProgramDescription">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl1 value=HL1>--%>
<%--hdn_hl1--%>
  <INPUT type="hidden" name="hdn_hl1" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl1"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl2 value=HL2>--%>
<%--hdn_hl2--%>
  <INPUT type="hidden" name="hdn_hl2" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl2"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl3 value=HL3>--%>
<%--hdn_hl3--%>
  <INPUT type="hidden" name="hdn_hl3" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl3"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl4 value=HL4>--%>
<%--hdn_hl4--%>
  <INPUT type="hidden" name="hdn_hl4" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl4"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl5 value=HL5>--%>
<%--hdn_hl5--%>
  <INPUT type="hidden" name="hdn_hl5" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl5"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl6 value=HL6>--%>
<%--hdn_hl6    --%>
  <INPUT type="hidden" name="hdn_hl6" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl6"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl7 value=HL7>--%>
<%--hdn_hl7--%>
  <INPUT type="hidden" name="hdn_hl7" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl7"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl8 value=HL8>--%>
<%--hdn_hl8--%>
  <INPUT type="hidden" name="hdn_hl8" value="<bean:write name="frm_verif_AccountSetup" property="hdn_hl8"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl1Descr value=HL1Descr>--%>
<%--txt_hl1Descr--%>
  <INPUT type="hidden" name="hdn_hl1Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl1Descr"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl2Descr value=HL2Descr>--%>
<%--txt_hl2Descr   --%>
  <INPUT type="hidden" name="hdn_hl2Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl2Descr"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl3Descr value=HL3Descr>--%>
<%--txt_hl3Descr--%>
  <INPUT type="hidden" name="hdn_hl3Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl3Descr"/>r">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl4Descr value=HL4Descr>--%>
<%--txt_hl4Descr--%>
  <INPUT type="hidden" name="hdn_hl4Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl4Descr"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl5Descr value=HL5Descr>--%>
<%--txt_hl5Descr--%>
  <INPUT type="hidden" name="hdn_hl5Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl5Descr"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl6Descr value=HL6Descr>--%>
<%--txt_hl6Descr--%>
  <INPUT type="hidden" name="hdn_hl6Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl6Descr"/>">
<%--</GX>        /--%>
<%--<GX type=replace id=hierarchy.hl7Descr value=HL7Descr>--%>
<%--txt_hl7Descr--%>
  <INPUT type="hidden" name="hdn_hl7Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl7Descr"/>">
<%--</GX>--%>

<%--<GX type=replace id=hierarchy.hl8Descr value=HL8Descr>--%>
<%--txt_hl8Descr--%>
  <INPUT type="hidden" name="hdn_hl8Descr" value="<bean:write name="frm_verif_AccountSetup" property="txt_hl8Descr"/>">
<%--</GX>--%>

<%--don't need this - serialized object--%>
<%--<GX type=replace id=navigate.acct value=account>--%>
<%--  <INPUT type="hidden" name="hdn_acct"--%>
<%--  value="account"> <!-- hdn_acct -->--%>
<%--</GX>--%>

<!------------------------------------------------------------------->

<BR>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_okButton"
               VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;">&nbsp;&nbsp;

        <INPUT TYPE="Reset" NAME="but_cancelButton" VALUE="Revise"
               onclick="history.go(-1);">
      </TD>
    </TR>
</TABLE><BR>

<!------------------------------------------------------------------->

<!--GX type=include id="/gsa/common/footer_systemDefault.html">Insert Footer Here</GX-->

</FORM>
<!--/html:form-->
</BODY>
</HTML>
