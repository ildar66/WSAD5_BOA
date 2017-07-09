
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/boa/app" prefix="eagls" %>

<html:html>

<HEAD>
    <TITLE>MCCG Controls Setup</TITLE>
    <META NAME="Name" CONTENT="MCCG Controls Setup">
    <META NAME="Description" CONTENT="User setup for MCCG Controls from Auth Controls Setup">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>

<SCRIPT LANGUAGE="JavaScript">
<!--
    function initialize(theForm) {
        parent.logo.setFocus(theForm,theForm.mccg_singleLimit,(parent.logo.Status_Text_SinglePurchaseLimit));
    }

    function formTest(theForm) {
        var errormessage=parent.logo.ErrMsg_Header;
        if (parent.logo.checkNumeric(theForm,theForm.mccg_otherTotalNumberDays) == "error") {
            errormessage += parent.logo.ErrMsg_OtherTotalNumberDays;
        }
        if ((theForm.mccg_otherRefreshDate.value != "")&&
                (parent.logo.checkDate(theForm,theForm.mccg_otherRefreshDate) == "error")) {
          errormessage += parent.logo.ErrMsg_Date;
        }
        if (errormessage != parent.logo.ErrMsg_Header) {
            alert(errormessage);
            return false;
        } else {
            return true;
        }
    }
//-->
</SCRIPT>

<!---------------------------------------------------------------------------------->

<BODY onLoad="initialize(document.frm_setupMccgControls)">

<%--
<FORM NAME="frm_setupMccgControls"
      ACTION="/cgi-bin/gx.cgi/GUIDGX-{95F59755-2237-11D2-92CE-0000F6AACD5A}"
      METHOD="POST"
      TARGET="content"
      onsubmit="return formTest(document.frm_setupMccgControls);">
--%>

<html:form name="frm_setupMccgControls" type=""
        action="/setup/authorizationControls/mccgControl"
        method="POST" target="content"
        onsubmit="return formTest(document.frm_setupMccgControls)">

<template:insert template="/jsp/gsa/common/header_systemDefault.jsp" >
    <template:put name="screenTitle-1" content="MCCG Controls Setup" direct="true"/>
    <template:put name="screenTitle-2" content="" direct="true"/>
</template:insert>
<BR>
<!---------------------------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Account Information</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213" ALIGN="left" >
        Account Name
      </TH>
      <TD WIDTH="352">
        <GX type=cell id=accountInfo.accountName></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="left" >
        Account Number
      </TH>
      <TD>
        <GX type=cell id=accountInfo.accountNumber></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="left" >
        Agency Name
      </TH>
      <TD>
        <GX type=cell id=accountInfo.agencyName></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="left" >
        Central Account ID
      </TH>
      <TD>
        <GX type=cell id=accountInfo.centralId></GX>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="left">MCCG Table Name</TD>
      <TD WIDTH="352">
        <SELECT NAME="mccg_mccgTableName" SIZE="1">
    	  <GX type=tile id=MCCGTableList>
            <GX type=replace id=MCCGTableList.mccgOptionName value=mccgOptionName>
              <GX type=replace id=MCCGTableList.mccgOptionDisplay value=mccgOptionDisplay>
                <OPTION value="mccgOptionName">mccgOptionDisplay</OPTION>
              </GX>
            </GX>
          </GX>
        </SELECT>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="left">MCCG Action</TD>
      <TD>
        <html:select property="mccg_mccgAction" size="1">
            <html:option value="Include">Include</html:option>
            <html:option value="Exclude">Exclude</html:option>
            <html:option value="Refer">Refer</html:option>
            <html:option value="Divert">Divert</html:option>
            <html:option value="Bypass">Bypass</html:option>
        </html:select>
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------------------------------------------------------------>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR>

<!------------------------------------------------------------------------------>

<BR><B><FONT COLOR="#0000FF" FACE="Arial, helvetica">MCCG Transaction Limit</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">Single Purchase Limit</TD>
      <TD WIDTH="352">
        <html:text property="mccg_singleLimit" size="15" maxlength="14"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_SinglePurchaseLimit)"/>
      </TD>
  </TR>
</TABLE>
<BR>

<!--- For code below...set up in table frmt broken by 4 segments as.. DAILY     CYCLK -->
<!--- Each section contains row data spread across columns.           MONTHLY   OTHER -->

<TABLE BORDER="1">
    <TR>
      <TH COLSPAN="2" ALIGN="Left" >Daily</TH>
      <TH COLSPAN="2" ALIGN="Left" >Cycle</TH>
    </TR>
    <TR>
      <TD WIDTH="141">Transaction Limit</TD>
      <TD WIDTH="140">
        <html:text property="mccg_dailyTransLimit" size="10" maxlength="9"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_DailyTransLimit)"/>
      </TD>
      <TD WIDTH="140">Transaction Limit</TD>
      <TD WIDTH="140">
        <html:text property="mccg_cycleTransLimit" size="10" maxlength="9"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_CycleTransLimit)"/>
      </TD>
    </TR>
    <TR>
      <TD>Amount Limit</TD>
      <TD>
        <html:text property="mccg_dailyAmountLimit" size="15" maxlength="14"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_DailyAmountLimit)"/>
      </TD>
      <TD>Amount Limit</TD>
      <TD>
        <html:text property="mccg_cycleAmountLimit" size="15" maxlength="14"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_CycleAmountLimit)"/>
      </TD>
    </TR>
    <TR>
      <TH COLSPAN="2" ALIGN="left" >Monthly</TH>
      <TH COLSPAN="2" ALIGN="left" >Other</TH>
    </TR>
    <TR>
      <TD>Transaction Limit</TD>
      <TD>
        <html:text property="mccg_monthTransLimit" size="10" maxlength="9"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_MonthTransLimit)"/>
      </TD>
      <TD>Transaction Limit</TD>
      <TD>
        <html:text property="mccg_otherTransLimit" size="10" maxlength="9"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherTransLimit)"/>
      </TD>
    </TR>
    <TR>
      <TD>Amount Limit</TD>
      <TD>
        <html:text property="mccg_monthAmountLimit" size="15" maxlength="14"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_MonthAmountLimit)"/>
      </TD>
      <TD>Amount Limit</TD>
      <TD>
        <html:text property="mccg_otherAmountLimit" size="15" maxlength="14"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherAmountLimit)"/>
      </TD>
    </TR>
    <TR>
      <TD><BR></TD>
      <TD><BR></TD>
      <TD>Total No. of Days</TD>
      <TD>
        <html:text property="mccg_otherTotalNumberDays" size="4" maxlength="3"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherTotalNumberDays)"/>
      </TD>
    </TR>
    <TR>
      <TD><BR></TD>
      <TD><BR></TD>
      <TD>Refresh Date</TD>
      <TD>
        <html:text property="mccg_otherRefreshDate" size="11" maxlength="10"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherRefreshDate)"/><BR>(MM/DD/YYYY)
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------------------------------------------------------------>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR><BR>

<!------------------------------------------------------------------------------>

<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Vendor Information</FONT></B><BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">Vendor Check</TD>
      <TD WIDTH="352">
        <html:select property="mccg_vendorCheck" size="1">
            <html:option value="Yes">Yes</html:option>
            <html:option value="No">No</html:option>
        </html:select>
      </TD>
    </TR>
    <TR>
      <TD>Vendor Type</TD>
      <TD>
        <html:select property="mccg_vendorType" size="1">
            <html:option value="Include">Include</html:option>
            <html:option value="Exclude">Exclude</html:option>
        </html:select>
      </TD>
    </TR>
    <TR>
      <TD>Vendor Action</TD>
      <TD>
        <html:select property="mccg_vendorAction" size="1">
            <html:option value="Accept">Accept</html:option>
            <html:option value="Refer">Refer</html:option>
            <html:option value="Decline">Decline</html:option>
        </html:select>
<!--
        <SELECT NAME="mccg_vendorAction" SIZE="1">
          <OPTION value="Accept">Accept</OPTION>
          <OPTION value="Refer">Refer</OPTION>
          <OPTION SELECTED value="Decline">Decline</OPTION>
        </SELECT>
-->
      </TD>
    </TR>
    <TR>
      <TD>Valid Preferred Tables</TD>
      <TD>
        <SELECT NAME="mccg_validPreferredTables" SIZE="1">
         <GX type=tile id=validPreferred>
          <GX type=replace id=validPreferred.preferredOptionName value=preferredOptionName>
            <GX type=replace id=validPreferred.preferredOptionDisplay value=preferredOptionDisplay>

				  <OPTION VALUE="preferredOptionName" >
					preferredOptionDisplay
				  </OPTION>

            </GX>
          </GX>
        </GX>
        </SELECT>
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------------------------------------------------------------>

<html:hidden property="acctControl"/>
<html:hidden property="mccgControl"/>
<html:hidden property="auth_accountNumber"/>
<html:hidden property="auth_accountName"/>
<html:hidden property="auth_agencyName"/>
<html:hidden property="auth_centralId"/>
<html:hidden property="auth_creditLimit"/>
<html:hidden property="auth_guid"/>

<%--
<INPUT TYPE="Hidden" NAME="acctControl" value="<GX type=cell id=acctControl></GX>">
<INPUT TYPE="Hidden" NAME="mccgControl" value="<GX type=cell id=mccgControl></GX>">
<INPUT TYPE="Hidden" NAME="auth_accountNumber" value="<GX type=cell id=accountInfo.accountNumber></GX>">
<INPUT TYPE="Hidden" NAME="auth_accountName" value="<GX type=cell id=accountInfo.accountName></GX>">
<INPUT TYPE="Hidden" NAME="auth_agencyName" value="<GX type=cell id=accountInfo.agencyName></GX>">
<INPUT TYPE="Hidden" NAME="auth_centralId" value="<GX type=cell id=accountInfo.centralId></GX>">
<INPUT TYPE="Hidden" NAME="auth_creditLimit" value="<GX type=cell id=accountInfo.creditLimit></GX>">
<INPUT TYPE="Hidden" NAME="auth_guid" value="<GX type=cell id=accountInfo.guid></GX>">
--%>

<!------------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <html:submit value="Submit" property="but_submitButton"/>&nbsp;&nbsp;
<GX type=cell id=showAddMCCG>
        <html:submit value="Add MCCG Controls" property="but_submitButton"/>&nbsp;&nbsp;
</GX>
        <html:reset value="Clear" property="but_resetButton"/>&nbsp;&nbsp;

		<html:button value="Cancel" property="but_cancelButton" onclick="history.go(-1)"/>
      </TD>
    </TR>
</TABLE>
<BR>
</html:form>

<!------------------------------------------------------------------------------>

<template:insert template="/jsp/gsa/common/footer_systemDefault.jspf" />

</BODY>
</html:html>