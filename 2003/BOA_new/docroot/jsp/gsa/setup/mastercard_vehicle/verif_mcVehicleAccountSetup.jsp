<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<html:html>
<HEAD>

<TITLE>MasterCard Vehicle Account Setup Verification</TITLE>

<META NAME="Name" CONTENT="MasterCard Vehicle Account Setup Verification">
<META NAME="Description" CONTENT="Used to verify new vehicle setup was correct">
<META NAME="Author" CONTENT="Charlie Bruggemann">
<META NAME="Created" CONTENT="14 July 1998">
<META NAME="Revised" CONTENT="09/14/1998 Background added to screen  -BB">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

<SCRIPT language="JavaScript">
<!-- Start Hiding

function Initialize(theForm)
    {
     creditLimit=theForm.txt_creditLimit.value;
     creditLimit=parent.logo.searchAndReplace(creditLimit,"$","",false,false);
     theForm.txt_creditLimit.value=creditLimit;
    }

//Stop Hiding-->
</SCRIPT>
</HEAD>

<!-------------------------------------------------------------------->

<BODY onLoad="Initialize(document.mcVehicleSetupSubmitForm);">
<jsp:useBean id="mcVehicleSetupVerifyDTO"
	class="com.boa.eagls.government.dto.setup.MCVehicleSetupVerifyDTO" scope="request" />

<html:form
	action="/confirmVehicleAccount"
    method="Post">

<TABLE WIDTH="575">
    <TR>
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Vehicle Account Setup<BR>
          Verification
        </H2>
      </TD>
    </TR>
</TABLE>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>
<!-------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
	  Please verify that the Account Setup information you have entered for the vehicle below is correct.
	</TD>
    </TR>
</TABLE>

<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Hierarchy Information</B></FONT>

<!------------------------------------------------------------------>


<!-- JKT --1/19/2000  replace hierarcy table with include ---------->

<%@ include file="/jsp/gsa/common/right_hierarchy.jsp"%>
<!--END JKT -------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR><BR>

<!--------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Central Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213"  ALIGN="Left">
        Central Account Name
      </TH>
      <TD WIDTH="352">
        <!-- GX type=cell id=results.centralName>centralName</GX -->
		<%=mcVehicleSetupVerifyDTO.getHdn_centralName()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH  ALIGN="Left">
        Central Account Number
      </TH>
      <TD>
        <!-- GX type=cell id=results.centralNumber>centralNumber</GX -->
        <%=mcVehicleSetupVerifyDTO.getHdn_centralNumber()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH  ALIGN="Left">
        Central Account ID
      </TH>
      <TD>
        <!--GX type=cell id=results.centralId>centralId</GX -->
        <%=mcVehicleSetupVerifyDTO.getHdn_centralId()%>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left">
        <B>Accounting Center ID</B>
      </TD>
      <TD WIDTH="352">
        <!-- GX type=cell id=results.accountingCenterId>accountingCenterId</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_accountingCenterId()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left" COLSPAN="2">
        <B>Master Accounting Code</B>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left" COLSPAN="2">
        <!-- GX type=cell id=results.accountingCode>accountingCode</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_accountingCode()%>&nbsp;
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR><BR>

<!--------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"  ALIGN="Left">
        <B>Program Type</B>
      </TD>
      <TD WIDTH="352">
        <!-- GX type=cell id=results.programType>programType</GX -->
        <%=mcVehicleSetupVerifyDTO.getHdn_programType()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD  ALIGN="Left">
        <B>Billing Type</B>
      </TD>
      <TD ALIGN="Left">
        <!-- GX type=cell id=results.billingType>billingType</GX -->
        <%=mcVehicleSetupVerifyDTO.getHdn_billingType()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD  ALIGN="Left">
        <B>Card/Non-Card</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.card>card</GX -->
        <%=mcVehicleSetupVerifyDTO.getHdn_card()%>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left">
        <B>Generate Paper Statements</B>
      </TD>
      <TD WIDTH="352">
        <!-- GX type=cell id=results.generatePaper>generatePaper</GX -->
        <%=mcVehicleSetupVerifyDTO.getGeneratePaper()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Card Type</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.cardType>cardType</GX -->
        <%=mcVehicleSetupVerifyDTO.getCmb_cardType()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Credit Limit</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.creditLimit>creditLimit</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_creditLimit()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Account Expiration Date</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.accountExpireDate>accountExpireDate</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_accountExpireDate()%>&nbsp;
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR><BR>

<!--------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Equipment Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"  ALIGN="Left">
        <B>Equipment ID</B>
      </TD>
      <TD WIDTH="352">
        <!-- GX type=cell id=results.equipmentId>equipmentId</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_equipmentId()%>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left">
        <B>Equipment Type</B>
      </TD>
      <TD WIDTH="352">
        <!-- GX type=cell id=results.equipmentType>equipmentType</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_equipmentType()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Equipment Fuel Type Low</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.equipFuelLow>equipFuelLow</GX -->
        <%=mcVehicleSetupVerifyDTO.getEquipFuelLow()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Equipment Fuel Type High</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.equipFuelHigh>equipFuelHigh</GX -->
        <%=mcVehicleSetupVerifyDTO.getEquipFuelHigh()%>&nbsp;
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR><BR>

<!--------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Address Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left">
        <B>Address 1</B>
      </TD>
      <TD WIDTH="352">
        <!-- GX type=cell id=results.addressLine1>addressLine1</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_addressLine1()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Address 2</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.addressLine2>addressLine2</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_addressLine2()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Address 3</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.addressLine3>addressLine3</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_addressLine3()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Address 4</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.addressLine4>addressLine4</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_addressLine4()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>City</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.city>city</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_city()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>State/Province</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.state>state</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_state()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Country</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.country>country</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_country()%>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Zip/Postal Code</B>
      </TD>
      <TD>
        <!-- GX type=cell id=results.zip>zip</GX -->
        <%=mcVehicleSetupVerifyDTO.getTxt_zip()%>&nbsp;
      </TD>
    </TR>
</TABLE>

<!-------------------- Hidden Fields -------------------------------------->

<!-- GX type=replace id=navigate.agencyNameTransmit value=agencyNameTransmit -->
  <INPUT TYPE="Hidden" NAME="hdn_agencyName" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_agencyName()%>">
<!--/GX -->

<!-- GX type=replace id=navigate.hl0Transmit value=hl0Transmit -->
  <INPUT TYPE="Hidden" NAME="hdn_hl0" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl0()%>">
<!--/GX -->

<!-- GX type=replace id=navigate.hl0DescrTransmit value=hl0DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl0Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl0Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl1Transmit value=hl1Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl1" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl1()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl1DescrTransmit value=hl1DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl1Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl1Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl2Transmit value=hl2Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl2" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl2()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl2DescrTransmit value=hl2DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl2Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl2Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl3Transmit value=hl3Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl3" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl3()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl3DescrTransmit value=hl3DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl3Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl3Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl4Transmit value=hl4Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl4" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl4()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl4DescrTransmit value=hl4DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl4Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl4Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl5Transmit value=hl5Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl5" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl5()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl5DescrTransmit value=hl5DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl5Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl5Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl6Transmit value=hl6Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl6" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl6()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl6DescrTransmit value=hl6DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl6Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl6Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl7Transmit value=hl7Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl7" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl7()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl7DescrTransmit value=hl7DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl7Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl7Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl8Transmit value=hl8Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl8" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl8()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.hl8DescrTransmit value=hl8DescrTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_hl8Desc" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_hl8Desc()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.centralNameTransmit value=centralNameTransmit -->
  <INPUT TYPE="Hidden" NAME="hdn_centralName" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_centralName()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.centralNumberTransmit value=centralNumberTransmit -->
  <INPUT TYPE="Hidden" NAME="hdn_centralNumber" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_centralNumber()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.centralIdTransmit value=centralIdTransmit -->
  <INPUT TYPE="Hidden" NAME="hdn_centralId" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_centralId()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.equipmentTypeTransmit value=equipmentTypeTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_equipmentType" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_equipmentType()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.equipmentIdTransmit value=equipmentIdTransmit -->
  <INPUT TYPE="Hidden" NAME="txt_equipmentId" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_equipmentId()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.equipFuelLowTransmit value=equipFuelLowTransmit -->
  <INPUT TYPE="Hidden" NAME="cmb_equipFuelLow" VALUE="<%=mcVehicleSetupVerifyDTO.getEquipFuelLow()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.equipFuelHighTransmit value=equipFuelHighTransmit -->
  <INPUT TYPE="Hidden" NAME="cmb_equipFuelHigh" VALUE="<%=mcVehicleSetupVerifyDTO.getEquipFuelHigh()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.addressLine1Transmit value=addressLine1Transmit -->
  <INPUT TYPE="Hidden" NAME="txt_addressLine1" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_addressLine1()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.addressLine2Transmit value=addressLine2Transmit-->
  <INPUT TYPE="Hidden" NAME="txt_addressLine2" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_addressLine2()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.addressLine3Transmit value=addressLine3Transmit-->
  <INPUT TYPE="Hidden" NAME="txt_addressLine3" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_addressLine3()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.addressLine4Transmit value=addressLine4Transmit-->
  <INPUT TYPE="Hidden" NAME="txt_addressLine4" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_addressLine4()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.cityTransmit value=cityTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_city" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_city()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.stateTransmit value=stateTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_state" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_state()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.countryTransmit value=countryTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_country" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_country()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.zipTransmit value=zipTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_zip" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_zip()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.creditLimitTransmit value=creditLimitTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_creditLimit" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_creditLimit()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.accountExpireDateTransmit value=accountExpireDateTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_accountExpireDate" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_accountExpireDate()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.accountingCenterIdTransmit value=accountingCenterIdTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_accountingCenterId" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_accountingCenterId()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.accountingCodeTransmit value=accountingCodeTransmit-->
  <INPUT TYPE="Hidden" NAME="txt_accountingCode" VALUE="<%=mcVehicleSetupVerifyDTO.getTxt_accountingCode()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.programTypeTransmit value=programTypeTransmit -->
  <INPUT TYPE="Hidden" NAME="hdn_programType" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_programType()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.billingTypeTransmit value=billingTypeTransmit-->
  <INPUT TYPE="Hidden" NAME="hdn_billingType" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_billingType()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.cardTypeTransmit value=cardTypeTransmit -->
  <INPUT TYPE="Hidden" NAME="cmb_cardType" VALUE="<%=mcVehicleSetupVerifyDTO.getCmb_cardType()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.convenienceChecksTransmit value=convenienceChecksTransmit -->
  <INPUT TYPE="Hidden" NAME="hdn_convenienceChecks" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_convenienceChecks()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.cardTransmit value=cardTransmit -->
  <INPUT TYPE="Hidden" NAME="hdn_card" VALUE="<%=mcVehicleSetupVerifyDTO.getHdn_card()%>">
<!-- /GX -->

<!-- GX type=replace id=navigate.generatePaperTransmit value=generatePaperTransmit -->
  <INPUT TYPE="Hidden" NAME="rag_generatePaper" VALUE="<%=mcVehicleSetupVerifyDTO.getGeneratePaperTransmit()%>">
<!-- /GX -->

<!------------------------------------------------------------------->

<BR>
<TABLE BORDER="0">
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_submit"
               VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;">&nbsp;&nbsp;

        <INPUT TYPE="Button" NAME="but_revise" VALUE="Revise"
               onClick="history.go(-1);">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------->

<template:insert template="/jsp/gsa/common/footer_systemDefault.jspf" />

</html:form>
</BODY>
</html:html>