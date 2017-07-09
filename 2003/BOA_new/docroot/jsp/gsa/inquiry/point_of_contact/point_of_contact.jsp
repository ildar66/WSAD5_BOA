<%@ page import="java.util.Collection,
                 com.boa.eagls.government.dto.pointOfContact.PointOfContactDTO"%>
<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>


<html:html>
<HEAD>

<TITLE>Point Of Contact</TITLE>

<META NAME="Name" CONTENT="Point Of Contact">
<META NAME="Description" CONTENT="Allows a user to view TDO, DBO, EC/EDI, Paimant Office or A/OPC points of contact fo the Central Account, or A/OPC points of contact for the Agency">
<META NAME="Author" CONTENT="Stanislav Shabalin">
<META NAME="Created" CONTENT="01/07/2003">
<META NAME="Revised" CONTENT="">
</HEAD>

<!------------------------------------------------------------------------------->
<body>
<!-------------------------------------------------------------------------->

<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Point of Contact<BR>

        </H2>
      </TD>
    </TR>
</TABLE>
<br>
<br>
<html:img width="575" height="6" page="/jsp/gsa/images/line.gif"/>
<br>
<br>

<%--<BODY>--%>

<%--<FORM NAME="frm_contactCentral"--%>
<%--      METHOD="POST"--%>
<%--      TARGET="MAIN">--%>

<%--<GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX><BR>--%>
<html:form action="/updatePointOfContact" method="POST">

<!------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Account Information</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213" ALIGN="Left" >
        Central Account ID
      </TH>
      <TD WIDTH="352">
<%--        <GX type=replace id=account.centralID value=centralID>centralID</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="centralAccountNbr"/>&nbsp;
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------------->

<BR>
<%--<IMG SRC="/gsa/images/line.gif" WIDTH="575">--%>
<html:img width="575" height="6" page="/jsp/gsa/images/line.gif"/>
<BR>

<!--------------------------------------------------------------------->

<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Contact Information</B><BR></FONT>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="141" ALIGN="Left" >
        TDO
      </TH>
      <TD WIDTH="140">
<%--        <GX type=replace id=contact.TDO value=TDO>TDO</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strTdo"/>&nbsp;
      </TD>
      <TH WIDTH="140" ALIGN="Left" >
        Primary TDO
      </TH>
      <TD WIDTH="140">
<%--        <GX type=replace id=contact.TDOPrim value=TDOPrim>TDOPrim</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strPriTDO"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        DBO
      </TH>
      <TD>
<%--        <GX type=replace id=contact.DBO value=DBO>DBO</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strDbo"/>&nbsp;
      </TD>
      <TH ALIGN="Left" >
        Primary DBO
      </TH>
      <TD>
<%--        <GX type=replace id=contact.DBOPrim value=DBOPrim>DBOPrim</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strPriDBO"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        EC/EDI
      </TH>
      <TD>
<%--        <GX type=replace id=contact.ECEDI value=ECEDI>ECEDI</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strEcedi"/>&nbsp;
      </TD>
      <TH ALIGN="Left" >
        Primary EC/EDI
      </TH>
      <TD>
<%--        <GX type=replace id=contact.ECEDIPrim value=ECEDIPrim>ECEDIPrim</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strPriECEDI"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Payment Office
      </TH>
      <TD>
<%--        <GX type=replace id=contact.paymentOffice value=paymentOffice>--%>
<%--          paymentOffice--%>
<%--        </GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strPaymentOffice"/>&nbsp;
      </TD>
      <TH ALIGN="Left" >
        Primary Payment<BR>Office
      </TH>
      <TD>
<%--        <GX type=replace id=contact.paymentOfficePrim value=paymentOfficePrim>paymentOfficePrim</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strPriPaymentOffice"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        A/OPC
      </TH>
      <TD>
<%--        <GX type=replace id=contact.AOPC value=AOPC>AOPC</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strAopc"/>&nbsp;
      </TD>
      <TH ALIGN="Left" >
        Primary A/OPC
      </TH>
      <TD>
<%--        <GX type=replace id=contact.AOPCPrim value=AOPCPrim>AOPCPrim</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="strPriAOPC"/>&nbsp;
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------------->

<BR>
<%--<IMG SRC="/gsa/images/line.gif" WIDTH="575">--%>
<html:img width="575" height="6" page="/jsp/gsa/images/line.gif"/>
<BR>

<!--------------------------------------------------------------------->

<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Address Information</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213" ALIGN="Left" >
        First Name
      </TH>
      <TD WIDTH="352">
        <!--GX type=replace id=address.firstName value=firstName>firstName</GX-->
        <bean:write name="pointOfContactForm" property="firstName"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Last Name
      </TH>
      <TD>
        <!--GX type=replace id=address.lastName value=lastName>lastName</GX>&nbsp;-->
        <bean:write name="pointOfContactForm" property="lastName"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Address 1
      </TH>
      <TD>
<%--        <GX type=replace id=address.line1 value=line1>line1</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="address1"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Address 2
      </TH>
      <TD>
<%--        <GX type=replace id=address.line2 value=line2>line2</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="address2"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Address 3
      </TH>
      <TD>
<%--        <GX type=replace id=address.line3 value=line3>line3</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="address3"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Address 4
      </TH>
      <TD>
<%--        <GX type=replace id=address.line4 value=line4>line4</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="address4"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        City
      </TH>
      <TD>
<%--        <GX type=replace id=address.city value=city>city</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="city"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        State/Province
      </TH>
      <TD>
<%--        <GX type=replace id=address.state value=state>state</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="state"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Country
      </TH>
      <TD>
<%--        <GX type=replace id=address.country value=country>country</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="country"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Zip
      </TH>
      <TD>
<%--        <GX type=replace id=address.zip value=zip>zip</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="zip"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Work Phone
      </TH>
      <TD>
<%--        <GX type=replace id=address.workPhone value=workPhone>workPhone</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="workPhone"/>&nbsp;
      </TD>
    </TR>
	<TR>
	  <TH ALIGN="Left" >
	    Home Phone
	  </TH>
	  <TD>
<%--	    <GX type=replace id=address.homePhone value=homePhone>homePhone</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="homePhone"/>&nbsp;
	  </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Fax
      </TH>
      <TD>
<%--        <GX type=replace id=address.fax value=fax>fax</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="fax"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        E-Mail
      </TH>
      <TD>
<%--        <GX type=replace id=address.eMail value=eMail>eMail</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="eMail"/>&nbsp;
      </TD>
    </TR>
</TABLE>

<BR>
<TABLE BORDER="1">
    <TR>
      <TH WIDTH="141" ALIGN="Left" >
        Newsletter Medium
      </TH>
      <TD WIDTH="140" ALIGN="Left">
<%--        <GX type=replace id=AOPC.newsletter value=newsletter>newsletter</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="newsletter"/>&nbsp;
      </TD>
      <TH WIDTH="140" ALIGN="Left" >
        Task Order Number
      </TH>
      <TD WIDTH="140" ALIGN="Left">
<%--        <GX type=replace id=AOPC.taskOrder value=taskOrder>taskOrder</GX>&nbsp;--%>
        <bean:write name="pointOfContactForm" property="taskOrderNo"/>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Button" NAME="Back" VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;"
               onClick="history.go(-1)">
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------------->
<br/>
<%@ include file = "/jsp/gsa/footer_systemDefault.jsp"%>
</html:form>
</body>
</html:html>

