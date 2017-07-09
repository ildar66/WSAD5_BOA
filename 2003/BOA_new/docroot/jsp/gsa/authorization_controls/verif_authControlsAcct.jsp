<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eagls" %>

<HTML>
<HEAD>

<TITLE>Account Controls SetUp: Verification</TITLE> 

<META NAME="Name" CONTENT="Auth Controls Verification Screen for Account Controls">
<META NAME="Description" CONTENT="Auth Controls - Acct Verification for Review Process">
<META NAME="Author" CONTENT="Todd Bernard(IldarS)">
<META NAME="Created" CONTENT="07/22/1998">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

</HEAD>

<!---------------------------------------------------------------------------------->

<BODY>
<html:form name="frm_verifAcctControls" type="com.boa.eagls.government.controller.formbean.authorizationcontrols.Frm_verifAcctControls"
           scope="session"
           action="/authControlSendConfirmationForAssignedAccountLimits" 
           method="POST" target="content" >

<!--
<FORM NAME  ="frm_verifAcctControls"
      ACTION="/cgi-bin/gx.cgi/GUIDGX-{6c687646-1766-11d2-92cc-0000f6aacd5a}"
      METHOD="POST"
      TARGET="content">

<GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX><BR>
-->
<!---------------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        Please verify that the Account Controls for<BR>
<!--    <B><GX type=cell id=accountInfo.accountName></GX></B><BR>  -->
        <B><bean:write name="frm_verifAcctControls" property="accountInfo.accountName"/></B><BR>
        are correct.
      </TD>
    </TR>
</TABLE>

<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Account Information</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213" ALIGN="Left" >
        Account Name
      </TH>
      <TD WIDTH="352">
<!--    <GX type=cell id=accountInfo.accountName></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="accountInfo.accountName"/>    
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Account Number
      </TH>
      <TD>
<!--    <GX type=cell id=accountInfo.accountNumber></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="accountInfo.accountNumber"/>     
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Agency Name
      </TH>
      <TD>
<!--     <GX type=cell id=accountInfo.agencyName></GX> -->
         <bean:write name="frm_verifAcctControls" property="accountInfo.agencyName"/>&nbsp;    
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Central Account ID
      </TH>
      <TD>
<!--    <GX type=cell id=accountInfo.centralId></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="accountInfo.centralId"/>&nbsp;    
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Credit Limit
      </TH>
      <TD>
<!--    <GX type=cell id="accountInfo.creditLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="accountInfo.creditLimit"/>&nbsp;    
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6" /><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Account Transaction Limit</FONT></B><BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Single Purchase Limit</B></TD>
      <TD WIDTH="352">
<!--    <GX type=cell id="acct.singleLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.singleLimit"/>&nbsp;    
      </TD>
    </TR>
</TABLE>
<BR>

<!--- For code below...set up in table frmt broken by 4 segments as.. DAILY     CYCLK -->
<!--- Each section contains row data spread across columns.           MONTHLY   OTHER -->

<TABLE BORDER="1">
    <TR>
      <TH></TH>
      <TH COLSPAN="2" ALIGN="Left" >
        Daily
      </TH>
      <TH COLSPAN="2" ALIGN="Left" >
        Cycle
      </TH>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "dailyTransLimit" and "cycleTransLimit"-->
      <TD WIDTH="141"><B>Transaction Limit</B>
      </TD>
      <TD WIDTH="140" ALIGN="Right">
<!--    <GX type=cell id=acct.dailyTransLimit></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.dailyTransLimit"/>&nbsp; 
      </TD>
      <TD WIDTH="140"><B>Transaction Limit</B>
      </TD>
      <TD WIDTH="140" ALIGN="Right">
<!--    <GX type=cell id=acct.cycleTransLimit></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.cycleTransLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "dailyAmountLimit" and "cycleAmountLimit"-->
      <TD><B>Amount Limit</B>
      </TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="acct.dailyAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.dailyAmountLimit"/>&nbsp;
      </TD>
      <TD><B>Amount Limit</B>
      </TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="acct.cycleAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.cycleAmountLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH>
      </TH>
      <TH COLSPAN="2" ALIGN="Left" >
        Monthly
      </TH>
      <TH COLSPAN="2" ALIGN="Left" >
        Other
      </TH>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "monthTransLimit" and "otherTransLimit"-->
      <TD><B>Transaction Limit</B>
      </TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id=acct.monthTransLimit></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.monthTransLimit"/>&nbsp;
      </TD>
      <TD><B>Transaction Limit</B>
      </TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id=acct.otherTransLimit></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.otherTransLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "monthAmountLimit" and "otherAmountLimit"-->
      <TD><B>Amount Limit</B>
      </TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="acct.monthAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.monthAmountLimit"/>&nbsp;
      </TD>
      <TD><B>Amount Limit</B>
      </TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="acct.otherAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.otherAmountLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 3rd line references "otherTotalNumberDays"-->
      <TD><BR>
      </TD>
      <TD><BR>
      </TD>
      <TD><B>Total No. of Days</B>
      </TD>
      <TD>
<!--    <GX type=cell id=acct.otherTotalNumberDays></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.otherTotalNumberDays"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 3rd line references "otherRefreshDate"-->
      <TD><BR>
      </TD>
      <TD><BR>
      </TD>
      <TD><B>Refresh Date</B></TD>
      <TD>
<!--    <GX type=cell id=acct.otherRefreshDate></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.otherRefreshDate"/>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6" /><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Foreign Currency Information</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Foreign Currency <BR>Restriction Set</B></TD>
      <TD WIDTH="352">
<!--    <GX type=cell id=acct.foreignCurrRestrictSet></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.foreignCurrRestrictSet"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Foreign Type</B>
      </TD>
      <TD>
<!--    <GX type=cell id=acct.foreignType></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.foreignType"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Foreign Action</B></TD>
      <TD>
<!--    <GX type=cell id=acct.foreignAction></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.foreignAction"/>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6" /><BR><BR>

<!--------------------------------------------------------------->

 <B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Vendor Information</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Vendor Check</B></TD>
      <TD WIDTH="352">
<!--    <GX type=cell id=acct.vendorCheck></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.vendorCheck"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Vendor Type</B>
      </TD>
      <TD>
<!--    <GX type=cell id=acct.vendorType></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.vendorType"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Vendor Action</B>
      </TD>
      <TD>
<!--    <GX type=cell id=acct.vendorAction></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.vendorAction"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Valid Preferred Tables</B>
      </TD>
      <TD>
<!--    <GX type=cell id=acct.validPreferredTables></GX>&nbsp; -->
        <bean:write name="frm_verifAcctControls" property="acct.validPreferredTables"/>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>
<BR>
<!-----New Code for MCCG Added here---------------->

<logic:equal name="frm_verifAcctControls" property="showMCCGHeader" value="true">
<%-- <GX type=cell id=showMCCGHeader> --%>
<TABLE>
 <TR>
  <TD WIDTH="575" ALIGN="CENTER">
   <B><FONT COLOR="#0000FF" SIZE=4 FACE="Arial, helvetica">MCCG Information</FONT></B>
  </TD>
 </TR>
</TABLE>
<BR>
<BR>
<%--</GX>--%>
</logic:equal>

<logic:iterate name="frm_verifAcctControls" property="mccgInfo" id="mccgInfoItem">

<!-- <GX type=tile id=mccgInfo> -->
 <html:img page="/jsp/gsa/images/line.gif" width="575" height="6" /><BR>
 <BR>
 <B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Account Information</FONT></B>
 <BR>
 <TABLE BORDER="1">
     <TR>
      <TH WIDTH="213" ALIGN="left" >
        MCCG Table Name
      </TH>
      <TD WIDTH="352">
<!--    <GX type=cell id=mccgInfo.mccgTableName></GX>&nbsp; -->
        <bean:write  name="mccgInfoItem" property="mccgTableName"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="left" >
        MCCG Action
      </TH>
      <TD>
<!--    <GX type=cell id=mccgInfo.mccgAction></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="mccgAction"/>&nbsp;
      </TD>
    </TR>
 </TABLE>
 <BR>
 <!--------------------------------------------------------------->
 <BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6" /><BR><BR>
 <!--------------------------------------------------------------->
 <B><FONT COLOR="#0000FF" FACE="Arial, helvetica">MCCG Transaction Limit</FONT></B><BR>
 <TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Single Purchase Limit</B></TD>
      <TD WIDTH="352">
<!--    <GX type=cell id="mccgInfo.singleLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="singleLimit"/>&nbsp;
      </TD>
    </TR>
 </TABLE>
 <BR>

 <!--- For code below...set up in table frmt broken by 4 segments as.. DAILY     CYCLK -->
 <!--- Each section contains row data spread across columns.           MONTHLY   OTHER -->

 <TABLE BORDER="1">
    <TR>
      <TH>
      </TH>
      <TH COLSPAN="2" ALIGN="left" >
        Daily
      </TH>
      <TH COLSPAN="2" ALIGN="left" >
        Cycle
      </TH>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "dailyTransLimit" and "cycleTransLimit"-->
      <TD WIDTH="141"><B>Transaction Limit</B></TD>
      <TD WIDTH="140" ALIGN="Right">
<!--    <GX type=cell id=mccgInfo.dailyTransLimit></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="dailyTransLimit"/>&nbsp;
      </TD>
      <TD WIDTH="140"><B>Transaction Limit</B></TD>
      <TD WIDTH="140" ALIGN="Right">
<!--    <GX type=cell id=mccgInfo.cycleTransLimit></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="cycleTransLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "dailyAmountLimit" and "cycleAmountLimit"-->
      <TD><B>Amount Limit</B></TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="mccgInfo.dailyAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="dailyAmountLimit"/>&nbsp;
      </TD>
      <TD><B>Amount Limit</B></TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="mccgInfo.cycleAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="cycleAmountLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH>
      </TH>
      <TH COLSPAN="2" ALIGN="left" >
        Monthly
      </TH>
      <TH COLSPAN="2" ALIGN="left" >
        Other
      </TH>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "monthTransLimit" and "otherTransLimit"-->
      <TD><B>Transaction Limit</B></TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id=mccgInfo.monthTransLimit></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="monthTransLimit"/>&nbsp;
      </TD>
      <TD><B>Transaction Limit</B></TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id=mccgInfo.otherTransLimit></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="otherTransLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 1st and 3rd line reference "monthAmountLimit" and "otherAmountLimit"-->
      <TD><B>Amount Limit</B></TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="mccgInfo.monthAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="monthAmountLimit"/>&nbsp;
      </TD>
      <TD><B>Amount Limit</B></TD>
      <TD ALIGN="Right">
<!--    <GX type=cell id="mccgInfo.otherAmountLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="otherAmountLimit"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 3rd line references "otherTotalNumberDay"-->
      <TD><BR></TD>
      <TD><BR></TD>
      <TD><B>Total No. of Days</B></TD>
      <TD>
<!--    <GX type=cell id=mccgInfo.otherTotalNumberDays></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="otherTotalNumberDays"/>&nbsp;
      </TD>
    </TR>
    <TR>  <!--The 3rd line references "otherRefreshDate"-->
      <TD><BR></TD>
      <TD><BR></TD>
      <TD><B>Refresh Date</B></TD>
      <TD>
<!--    <GX type=cell id=mccgInfo.otherRefreshDate></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="otherRefreshDate"/>&nbsp;
      </TD>
    </TR>
 </TABLE>
 <BR>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6" /><BR><BR>

<!--------------------------------------------------------------->

 <B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Vendor Information</FONT></B>

 <TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Vendor Check</B></TD>
      <TD WIDTH="352">
<!--    <GX type=cell id=mccgInfo.vendorCheck></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="vendorCheck"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Vendor Type</B></TD>
      <TD>
<!--    <GX type=cell id=mccgInfo.vendorType></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="vendorType"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Vendor Action</B></TD>
      <TD>
<!--    <GX type=cell id=mccgInfo.vendorAction></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="vendorAction"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Valid Preferred Tables</B></TD>
      <TD>
<!--    <GX type=cell id=mccgInfo.validPreferredTables></GX>&nbsp; -->
        <bean:write name="mccgInfoItem" property="validPreferredTables"/>&nbsp;
      </TD>
    </TR>
 </TABLE>
 <BR>
<!-- </GX> -->

</logic:iterate>
<!------------------------------------------------------------------------->
<!--
<INPUT TYPE="Hidden" NAME="acctControl" value="<GX type=cell id=acctControl></GX>">
<INPUT TYPE="Hidden" NAME="mccgControl" value="<GX type=cell id=mccgControl></GX>">
<INPUT TYPE="Hidden" NAME="auth_accountNumber" value="<GX type=cell id=accountInfo.accountNumber></GX>">
<INPUT TYPE="Hidden" NAME="auth_accountName" value="<GX type=cell id=accountInfo.accountName></GX>">
<INPUT TYPE="Hidden" NAME="auth_agencyName" value="<GX type=cell id=accountInfo.agencyName></GX>">
<INPUT TYPE="Hidden" NAME="auth_centralId" value="<GX type=cell id=accountInfo.centralId></GX>">
<INPUT TYPE="Hidden" NAME="auth_creditLimit" value="<GX type=cell id=accountInfo.creditLimit></GX>">
<INPUT TYPE="Hidden" NAME="auth_guid" value="<GX type=cell id=accountInfo.guid></GX>">
-->
<html:hidden property="acctControl" />
<html:hidden property="mccgControl" />
<html:hidden property="accountInfo.accountNumber" />
<html:hidden property="accountInfo.accountName" />
<html:hidden property="accountInfo.agencyName" />
<html:hidden property="accountInfo.centralId" />
<html:hidden property="accountInfo.creditLimit" />
<html:hidden property="accountInfo.guid" />

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
          <INPUT TYPE="Submit" NAME="but_submitButton"
                 VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;">&nbsp;&nbsp;

          <INPUT TYPE="Button" NAME="but_cancelButton" VALUE="Cancel"
                 onclick="history.go(-1)">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------->

<!-- <GX type=include id="/gsa/common/footer_systemDefault.html">Insert Footer Here</GX> -->

</html:form>

</BODY>
</HTML>
