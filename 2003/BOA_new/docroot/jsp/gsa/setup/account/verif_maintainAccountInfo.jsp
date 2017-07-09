<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<HTML>
<HEAD>

<TITLE>Verification:  Account Information</title>

<META NAME="Name" CONTENT="Account Information Maintenance Verification">
<META NAME="Description" CONTENT="Account Information Maintenance Verification Screen">
<META NAME="Author" CONTENT="Todd Shuler">
<META NAME="Created" CONTENT="07/25/1998">
<META NAME="Revised" CONTENT="09/17/1998 Edited -BB">
<META NAME="Revised" CONTENT="11/12/1998 Edited -DT,
            Comments added to facilitate USDA interface development.">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

</HEAD>

<!------------------------------------------------------------------->

<BODY>

<html:form name="frm_verif_maintainAccountInfo"
      action="/cgi-bin/gx.cgi/GUIDGX-{F2FA1390-2B1E-11D2-9C10-204C4F4F5020}"
      method="POST"
      target="content">


<!--GX type=include ID="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX-->
<BR>

<!------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        Please verify that the Account information you have entered is correct.
      </TD>
    </TR>
</TABLE>

<BR><FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Hierarchy Information</B></FONT>


<!--GX type=cell id=GCSU-->
<!------------------- PROGRAM NUMBER IS ONLY VISIBLE TO GCSU USERS ---------->
<BR>
<!--TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
    <TR>
      <TD WIDTH="144"><B>Program Number</B></TD>
      <TD WIDTH="427">
        <GX type=replace id=account.programNumber value=PGNVAL>PGNVAL
          <INPUT TYPE="Hidden" NAME="hdn_programNumber" VALUE="PGNVAL">
        </GX>
      </TD>
    </TR>
    <TR>
      <TD><B>Program Description</B>
      </TD>
      <TD>
        <GX type=replace id=account.programDescription value=PGDVAL>PGDVAL
          <INPUT TYPE="Hidden" NAME="hdn_programDescription" VALUE="PGDVAL">
        </GX>
      </TD>
    </TR>
</TABLE-->
 <!------------------------------------------------------------------------>
<!--/GX-->

<BR>
<!--GX type=include id="/gsa/common/hierarchy.html">Insert Hierarchy Template Here</GX-->

<!-- TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
    <TR>
      <TD WIDTH="72" ><B>HL1</B></TD>
      <TD WIDTH="72" ><B>HL2</B></TD>
      <TD WIDTH="72" ><B>HL3</B></TD>
      <TD WIDTH="71" ><B>HL4</B></TD>
      <TD WIDTH="71" ><B>HL5</B></TD>
      <TD WIDTH="71" ><B>HL6</B></TD>
      <TD WIDTH="71" ><B>HL7</B></TD>
      <TD WIDTH="71" ><B>HL8</B></TD>
    </TR>
    <TR>
      <TD><GX type=cell id=account.hl1></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl2></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl3></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl4></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl5></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl6></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl7></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl8></GX>&nbsp;</TD>
    </TR>
    <TR>
      <TD><GX type=cell id=account.hl1Desc></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl2Desc></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl3Desc></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl4Desc></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl5Desc></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl6Desc></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl7Desc></GX>&nbsp;</TD>
      <TD><GX type=cell id=account.hl8Desc></GX>&nbsp;</TD>
    </TR>
</TABLE-->

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR>


<!--------------------------------------------------------------->

<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Central Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" >
        <B>Agency Name</B>
      </TD>
      <TD WIDTH="352">
        <!--bean:write name="frm_verif_maintainAccountInfo" property="hdn_centralAccountID"/-->
        <!--GX type=cell id=attributes.agencyName></GX-->&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Cen</B>
      </TD>
      <TD>
        <bean:write name="frm_verif_maintainAccountInfo" property="centralAcctID"/>
        <!--GX type=cell id=attributes.centralAccountID></GX-->&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Central Account Name</B>
      </TD>
      <TD>
      <bean:write name="frm_verif_maintainAccountInfo" property="hdn_centralAccountName"/>
        <!--GX type=cell id=attributes.centralAccountName></GX-->&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Central Account Number</B>
      </TD>
      <TD>
        <bean:write name="frm_verif_maintainAccountInfo" property="hdn_centralAccountNumber"/>
        <!--GX type=cell id=attributes.centralAccountNumber></GX-->&nbsp;
      </TD>
    </TR>
</TABLE>

<BR>
<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Accounting Center ID</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_verif_maintainAccountInfo" property="accountingCenterID"/>
        <!--GX type=cell id=attributes.accountingCenterID></GX-->&nbsp;
      </TD>
    </TR>
    <TR>
      <TD COLSPAN=2><B>Master Accounting Code</B>
      </TD>
    </TR>
    <TR>
      <TD COLSPAN=2>
        <bean:write name="frm_verif_maintainAccountInfo" property="txt_masterAccountingCode"/>
        <!--GX type=cell id=attributes.masterAccountingCode></GX-->&nbsp;
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR>

<!--------------------------------------------------------------->

<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Account Information</B></FONT><BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" >
        <B>Program Type</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_verif_maintainAccountInfo" property="txt_masterAccountingCode"/>
        <!--GX type=cell id=screen_only.pt></GX-->&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Account Status</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.accountStatus></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Credit Limit</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.creditLimit></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Cash Limit</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.cashLimit></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Temporary Cash Limit</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.tempCashLimit></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>Start Date</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.tempCashStartDate></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD >
        <B>End Date</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.tempCashEndDate></GX>&nbsp;
      </TD>
    </TR>


    <TR>
      <TD >
        <B>Billing Type</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.billingType></GX>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD><B>Travelers Checks</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.travelersCheckFlag></GX>&nbsp;
      </TD>
    </TR>

    <TR>
      <TD><B>Generate Paper Statement</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.generatePaperStatementFlag></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Card Type</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.cardType></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Card/Non-Card</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.cardNonCard></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Account Expiration Date</B>
      </TD>
      <TD>
        <GX type=cell id=attributes.accountExpirationDate></GX>&nbsp;
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Account Holder Information</B></FONT><BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Last Name</B>
      </TD>
      <TD WIDTH="352">
        <GX type=cell id=information.lastName></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>First Name</B>
      </TD>
      <TD>
        <GX type=cell id=information.firstName></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Address 1</B>
      </TD>
      <TD>
        <GX type=cell id=information.address></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Address 2</B>
      </TD>
      <TD>
        <GX type=cell id=information.address2></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Address 3</B>
      </TD>
      <TD>
        <GX type=cell id=information.address3></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Address 4</B>
      </TD>
      <TD>
        <GX type=cell id=information.address4></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>City</B>
      </TD>
      <TD>
        <GX type=cell id=information.city></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>State/Province</B>
      </TD>
      <TD>
        <GX type=cell id=information.state></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Country</B>
      </TD>
      <TD>
        <GX type=cell id=information.country></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Zip/Postal Code</B>
      </TD>
      <TD>
        <GX type=cell id=information.zipCode></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Business Phone</B>
      </TD>
      <TD>
        <GX type=cell id=information.businessPhone></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Home Phone</B>
      </TD>
      <TD>
        <GX type=cell id=information.homePhone></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Fax Number</B>
      </TD>
      <TD>
        <GX type=cell id=information.faxNumber></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>E-mail Address</B>
      </TD>
      <TD>
        <GX type=cell id=information.emailAddress></GX>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------->

<html:img page="/jsp/gsa/images/line.gif" width="575"/><BR>

<!--------------------------------------------------------------->

<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Identification</B></FONT><BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Social Security Number</B>
      </TD>
      <TD WIDTH="352">
        <GX type=cell id=identification.ssn></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Employee Id</B>
      </TD>
      <TD>
        <GX type=cell id=identification.employeeID></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Grade</B>
      </TD>
      <TD>
        <GX type=cell id=identification.cmb_grade></GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD><B>Employment Status</B>
      </TD>
      <TD>
        <GX type=cell id=identification.status></GX>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------- HIDDEN FIELDS---------------------------->


<GX type=replace id=navigate.acct value=Acct>
  <INPUT TYPE="Hidden" name="hdn_acct"
  value="Acct"> <!-- hdn_acct -->
</GX>

<GX type=replace id=oldAcct value=oldcct>
  <INPUT TYPE="Hidden" name="hdn_oldAcct"
  value="oldcct"> <!-- hdn_oldAcct -->
</GX>

<!------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_okButton" VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;">&nbsp;&nbsp;

        <INPUT TYPE="Reset" NAME="but_cancelButton" VALUE="Revise"
               onclick="history.go(-1);">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------->

<GX TYPE=include ID="/gsa/common/footer_systemDefault.html">Insert Footer Here</GX>

</FORM>
</BODY>
</HTML>
