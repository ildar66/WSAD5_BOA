<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eagls" %>

<HTML>
<HEAD>

<TITLE>Account Controls Setup</TITLE> 

<META NAME="Name" CONTENT="Account/Authorization Controls Setup">
<META NAME="Description" CONTENT="Setup for Account Controls from Auth Controls Setup">
<META NAME="Author" CONTENT="Todd Bernard(IldarS)">
<META NAME="Created" CONTENT="07/22/1998">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript"
	SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<SCRIPT LANGUAGE="Javascript">
<!-- Start hiding from other browsers
function initialize(theForm)
    {
     parent.logo.setFocus(theForm,theForm.acct_singleLimit,(parent.logo.Status_Text_SinglePurchaseLimit));
    }

function form_Test(theForm)

    {
     var errormessage = parent.logo.ErrMsg_Header;


     if ((theForm.acct_singleLimit.value != "") &&
         (parent.logo.checkCurrency(theForm.acct_singleLimit.value) == "error"))
         {
          errormessage += parent.logo.ErrMsg_SinglePurchaseLimit;
         }

     if ((theForm.acct_dailyTransLimit != "") &&
         (parent.logo.checkNumeric(theForm,theForm.acct_dailyTransLimit) == "error"))
         {
          errormessage += parent.logo.ErrMsg_DailyTransactionLimit;
         }

     if ((theForm.acct_dailyAmountLimit.value != "") &&
         (parent.logo.checkCurrency(theForm.acct_dailyAmountLimit.value) == "error"))
         {
          errormessage += parent.logo.ErrMsg_DailyAmountLimit;
         }

     if ((theForm.acct_cycleTransLimit != "") &&
         (parent.logo.checkNumeric(theForm,theForm.acct_cycleTransLimit) == "error"))
         {
          errormessage += parent.logo.ErrMsg_CycleTransactionLimit;
         }

     if ((theForm.acct_cycleAmountLimit.value != "") &&
         (parent.logo.checkCurrency(theForm.acct_cycleAmountLimit.value) == "error"))
         {
          errormessage += parent.logo.ErrMsg_CycleAmountLimit;
         }

     if ((theForm.acct_monthTransLimit != "") &&
         (parent.logo.checkNumeric(theForm,theForm.acct_monthTransLimit) == "error"))
         {
          errormessage += parent.logo.ErrMsg_MonthTransactionLimit;
         }

     if ((theForm.acct_monthAmountLimit.value != "") &&
         (parent.logo.checkCurrency(theForm.acct_monthAmountLimit.value) == "error"))
         {
          errormessage += parent.logo.ErrMsg_MonthAmountLimit;
         }

     if ((theForm.acct_otherTransLimit != "") &&
         (parent.logo.checkNumeric(theForm,theForm.acct_otherTransLimit) == "error"))
         {
          errormessage += parent.logo.ErrMsg_OtherTransactionLimit;
         }

     if ((theForm.acct_otherAmountLimit.value != "") &&
         (parent.logo.checkCurrency(theForm.acct_otherAmountLimit.value) == "error"))
         {
          errormessage += parent.logo.ErrMsg_OtherAmountLimit;
         }

     if ((theForm.acct_otherTotalNumberDays != "") &&
         (parent.logo.checkNumeric(theForm,theForm.acct_otherTotalNumberDays) == "error"))
         {
          errormessage += parent.logo.ErrMsg_OtherTotalNumberDays;
         }

     if ((theForm.acct_otherRefreshDate.value != "") &&
         (parent.logo.checkDate(theForm,theForm.acct_otherRefreshDate) == "error"))
         {
          errormessage += parent.logo.ErrMsg_Date;
         }

     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return false;
         }
     else
       return true;
    }

// Stop hiding -->
</SCRIPT>


</HEAD>

<!---------------------------------------------------------------------------------->

<BODY onLoad="initialize(document.frm_setupAccountControls);">
<html:errors />

<jsp:useBean id="frm_setupAccountControls"
				class="com.boa.eagls.government.controller.formbean.authorizationcontrols.Frm_setupAccountControls"
				scope="session">
</jsp:useBean>

<html:form name="frm_setupAccountControls"
	type="com.boa.eagls.government.controller.formbean.authorizationcontrols.Frm_setupAccountControls"
	scope="session" action="/decideAccountControlsSetup" method="POST"
	target="content"
	onsubmit="return form_Test(document.frm_setupAccountControls)">
<!--
<FORM NAME="frm_setupAccountControls"
      ACTION="/cgi-bin/gx.cgi/GUIDGX-{95F59754-2237-11D2-92CE-0000F6AACD5A}"
      METHOD="POST"
      TARGET="content"
      onSubmit="return formTest(document.frm_setupAccountControls);">

	 <GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX><BR>
-->

	<!---------------------------------------------------------------------------------->

	<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Account Information</FONT></B>

	<TABLE BORDER="1">
		<TR>
			<TH WIDTH="213" ALIGN="left">Account Name</TH>
			<TD WIDTH="352">
			<%-- <GX type=cell id=accountInfo.accountName></GX>&nbsp;--%>
			<bean:write name="frm_setupAccountControls"
				property="accountInfo.accountName" /></TD>
		</TR>
		<TR>
			<TH ALIGN="left">Account Number</TH>
			<TD>
			<%--<GX type=cell id=accountInfo.accountNumber></GX>&nbsp;--%>
			<bean:write name="frm_setupAccountControls"
				property="accountInfo.accountNumber" /></TD>
		</TR>
		<TR>
			<TH ALIGN="left">Agency Name</TH>
			<TD>
			<%--<GX type=cell id=accountInfo.agencyName></GX>&nbsp;--%>
			<bean:write name="frm_setupAccountControls"
				property="accountInfo.agencyName" /></TD>
		</TR>
		<TR>
			<TH ALIGN="left">Central Account ID</TH>
			<TD>
			<%--<GX type=cell id=accountInfo.centralId></GX>&nbsp;--%>
			<bean:write name="frm_setupAccountControls"
				property="accountInfo.centralId" /></TD>
		</TR>
		<TR>
			<TH ALIGN="left">Credit Limit</TH>
			<TD>
			<%--<GX type=cell id="accountInfo.creditLimit, format($#,###.00)"></GX>&nbsp;--%>
			<bean:write name="frm_setupAccountControls"
				property="accountInfo.creditLimit" format="$#,###.00" /></TD>
		</TR>
	</TABLE>
	<BR>

	<!---------------------------------------------------------------------------------->

	<html:img page="/jsp/gsa/images/line.gif" width="575" height="6" />
	<BR>
	<BR>

	<!---------------------------------------------------------------------------------->

	<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Account Transaction
	Limit</FONT></B>

	<TABLE BORDER="1">
		<TR>
			<TD WIDTH="213">Single Purchase Limit</TD>
			<TD WIDTH="352" ALIGN="Right"><html:text name="frm_setupAccountControls" property="acct_singleLimit"
				size="15" maxlength="14"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_SinglePurchaseLimit);" />
			</TD>
		</TR>
	</TABLE>
	<BR>

	<!--- For code below...set up in table frmt broken by 4 segments as.. DAILY     CYCLK -->
	<!--- Each section contains row data spread across columns.           MONTHLY   OTHER -->

	<TABLE BORDER="1">
		<TR>
			<TH COLSPAN="2" ALIGN="left">Daily</TH>
			<TH COLSPAN="2" ALIGN="left">Cycle</TH>
		</TR>
		<TR>
			<TD WIDTH="141">Transaction Limit</TD>
			<TD WIDTH="140"><html:text name="frm_setupAccountControls" property="acct_dailyTransLimit" size="10"
				maxlength="9"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_DailyTransLimit);" />
			</TD>
			<TD WIDTH="140">Transaction Limit</TD>
			<TD WIDTH="140"><html:text name="frm_setupAccountControls" property="acct_cycleTransLimit" size="10"
				maxlength="9"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_CycleTransLimit);" />
			</TD>
		</TR>
		<TR>
			<TD>Amount Limit</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_dailyAmountLimit" size="15"
				maxlength="14"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_DailyAmountLimit);" />
			</TD>
			<TD>Amount Limit</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_cycleAmountLimit" size="15"
				maxlength="14"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_CycleAmountLimit);" />
			</TD>
		</TR>
		<TR>
			<TH COLSPAN="2" ALIGN="left">Monthly</TH>
			<TH COLSPAN="2" ALIGN="left">Other</TH>
		</TR>
		<TR>
			<TD>Transaction Limit</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_monthTransLimit" size="10"
				maxlength="9"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_MonthTransLimit);" />
			</TD>
			<TD>Transaction Limit</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_otherTransLimit" size="10"
				maxlength="9"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherTransLimit);" />
			</TD>
		</TR>
		<TR>
			<TD>Amount Limit</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_monthAmountLimit" size="15"
				maxlength="14"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_MonthAmountLimit);" />
			</TD>
			<TD>Amount Limit</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_otherAmountLimit" size="15"
				maxlength="14"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherAmountLimit);" />
			</TD>
		</TR>
		<TR>
			<TD><BR>
			</TD>
			<TD><BR>
			</TD>
			<TD>Total No. of Days</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_otherTotalNumberDays" size="4"
				maxlength="3"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherTotalNumberDays);" />
			</TD>
		</TR>
		<TR>
			<TD><BR>
			</TD>
			<TD><BR>
			</TD>
			<TD>Refresh Date</TD>
			<TD><html:text name="frm_setupAccountControls" property="acct_otherRefreshDate" size="11"
				maxlength="10"
				onfocus="parent.logo.Display_Message(parent.logo.Status_Text_OtherRefreshDate);" /><BR>
			(MM/DD/YYYY)</TD>
		</TR>
	</TABLE>
	<BR>

	<!---------------------------------------------------------------------------------->

	<html:img page="/jsp/gsa/images/line.gif" width="575" height="6" />
	<BR>
	<BR>

	<!---------------------------------------------------------------------------------->

	<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Foreign Currency
	Information</FONT></B>

	<TABLE BORDER="1">
		<TR>
			<TD WIDTH="213">Foreign Currency Restriction Set</TD>
			<TD WIDTH="352">
			    <html:select name="frm_setupAccountControls"    property="acct_foreignCurrRestrictSet" size="1">
				   <html:options name="frm_setupAccountControls"	property="acct_foreignCurrRestrictSetValue" />
			    </html:select>
			</TD>
		</TR>
		<TR>
			<TD>Foreign Type</TD>
			<TD>
			    <html:select name="frm_setupAccountControls"    property="acct_foreignType" size="1">
				   <html:options name="frm_setupAccountControls"	property="acct_foreignTypeValue" />
			    </html:select>
			</TD>
		</TR>
		<TR>
			<TD>Foreign Action</TD>
			<TD>
			    <html:select name="frm_setupAccountControls"    property="acct_foreignAction" size="1">
				   <html:options name="frm_setupAccountControls"	property="acct_foreignActionValue" />
			    </html:select>
			</TD>
		</TR>
	</TABLE>
	<BR>
	<!---------------------------------------------------------------------------------->

	<html:img page="/jsp/gsa/images/line.gif" width="575" height="6" />
	<BR>
	<BR>

	<!---------------------------------------------------------------------------------->

	<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Vendor Information</FONT></B>

	<TABLE BORDER="1">
		<TR>
			<TD WIDTH="213">Vendor Check</TD>
			<TD WIDTH="352">
		    <html:select name="frm_setupAccountControls"    property="acct_vendorCheck" size="1">
			   <html:options name="frm_setupAccountControls"	property="acct_vendorCheckValue" />
		    </html:select>
			</TD>
		</TR>
		<TR>
			<TD>Vendor Type</TD>
			<TD>
		    <html:select name="frm_setupAccountControls"    property="acct_vendorType" size="1">
			   <html:options name="frm_setupAccountControls"	property="acct_vendorTypeValue" />
		    </html:select>
			</TD>
		</TR>
		<TR>
			<TD>Vendor Action</TD>
			<TD>
			<html:select name="frm_setupAccountControls" property="acct_vendorAction">
			   <html:options name="frm_setupAccountControls"	property="acct_vendorActionValue" />
			</html:select>
			</TD>
		</TR>
		<TR>
			<TD>Valid Preferred Tables</TD>
			<TD>
<%--	 	   <SELECT NAME="acct_validPreferredTables" SIZE="1">
        		  <GX type=tile id=acct_validPreferredTables>
	    	      <GX type=replace id=acct_validPreferredTables.optionValue value=theOptionValue>
    		        <GX type=replace id=acct_validPreferredTables.optionText value=theOptionDescr>
	        	      <GX type=replace id=acct_validPreferredTables.selection value=selected>
            		    <OPTION VALUE="theOptionValue" selected>theOptionDescr</OPTION>
	        	      </GX>
    		        </GX>
    	    	  </GX>
		          </GX>
   			    </SELECT>--%>
		        <html:select name="frm_setupAccountControls" property="acct_validPreferredTables">
					<logic:iterate id="vendorTable" name = "frm_setupAccountControls"
						           collection="<%=frm_setupAccountControls.getVendorNDT()%>"
						           type="com.boa.eagls.government.dto.NameDescTable">
						<html:option value="<%=vendorTable.getName()%>"><%=vendorTable.getDescription()%></html:option>
					</logic:iterate>
				</html:select>
			</TD>
		</TR>
	</TABLE>
	<BR>

	<!------------------------------------------------------------------------------------>
	<html:hidden property="mccgControl" />
	<html:hidden property="accountInfo.accountNumber" />
	<html:hidden property="accountInfo.accountName" />
	<html:hidden property="accountInfo.agencyName" />
	<html:hidden property="accountInfo.centralId" />
	<html:hidden property="accountInfo.creditLimit" />
	<html:hidden property="accountInfo.guid" />
	<!--
    <INPUT TYPE="Hidden" NAME="mccgControl" value="<GX type=cell id=mccgControl></GX>">
    <INPUT TYPE="Hidden" NAME="auth_accountNumber" value="<GX type=cell id=accountInfo.accountNumber></GX>">
    <INPUT TYPE="Hidden" NAME="auth_accountName" value="<GX type=cell id=accountInfo.accountName></GX>">
    <INPUT TYPE="Hidden" NAME="auth_agencyName" value="<GX type=cell id=accountInfo.agencyName></GX>">
    <INPUT TYPE="Hidden" NAME="auth_centralId" value="<GX type=cell id=accountInfo.centralId></GX>">
    <INPUT TYPE="Hidden" NAME="auth_creditLimit" value="<GX type=cell id=accountInfo.creditLimit></GX>">
	<INPUT TYPE="Hidden" NAME="auth_guid" value="<GX type=cell id=accountInfo.guid></GX>">
	-->

	<TABLE>
		<TR>
			<TD WIDTH="575" ALIGN="Center">
			<html:submit property="but_submitButton" value="Submit"></html:submit>&nbsp;&nbsp;

            <html:submit property="but_submitButton" value="Add MCCG Controls"></html:submit>&nbsp;&nbsp;
            
            <html:reset property="but_resetButton" value="Clear"></html:reset>&nbsp;&nbsp;
            
            <html:button property="but_cancelButton" value="Cancel" onclick="history.go(-1);"></html:button>             
			</TD>
		</TR>
	</TABLE>

	<!------------------------------------------------------------------------------------>

	<!-- <GX type=include id="/gsa/common/footer_systemDefault.html">Insert Footer Here</GX> -->

</html:form>
</BODY>
</HTML>
