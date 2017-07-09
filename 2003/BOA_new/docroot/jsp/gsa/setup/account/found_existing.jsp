<%@ page import="java.util.Collection,
                 java.util.Iterator,
                 com.boa.eagls.government.dto.account.IndividualAccount"%>
<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/boa/app" prefix="eagls" %>


<HTML>
<HEAD>

<TITLE>Account Setup Search Multiple Results</TITLE>

<META NAME="Name" CONTENT="Account Setup Search Results">
<META NAME="Description" CONTENT="Returns results based on Account Setup Search Criteria">
<META NAME="Author" CONTENT="Todd Shuler">
<META NAME="Created" CONTENT="07/07/1998">
<META NAME="Revised" CONTENT="09/23/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript">
isIE = document.all ? true:false;

if ( isIE ) {
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}
</SCRIPT>

<!----------------------------------------------------------------------------->

<BODY>




<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Setup<BR>
          Account
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


    <logic:notEmpty name="searchAccountForm" property="txtCentralAccountID">
      <TR>
        <TD><B>Central Account ID</B></TD>
        <TD><bean:write name="searchAccountForm" property="txtCentralAccountID"/></TD>
      </TR>
    </logic:notEmpty>


    <logic:notEmpty name="searchAccountForm" property="txtLastName">
      <TR>
        <TD><B>Account Name</B></TD>
        <TD><bean:write name="searchAccountForm" property="txtLastName"/>&nbsp;<bean:write name="searchAccountForm" property="txtFirstName"/></TD>
      </TR>
    </logic:notEmpty>

    <logic:notEmpty name="searchAccountForm" property="txtSocialSecurityNumber">
      <TR>
        <TD><B>Social Security Number</B></TD>
        <TD><bean:write name="searchAccountForm" property="txtSocialSecurityNumber"/></TD>
      </TR>
    </logic:notEmpty>

<!--      <TR>
        <TD><B>Account Name</B></TD>
        <TD> BROWN</TD>
      </TR>-->

</TABLE>
<BR>


<!------------------------------------------------------------------------->
<eagls:pagedList howMany="50">
    <eagls:pagedListHeader>
        <TABLE BORDER="1" WIDTH="700">
        <TR>
          <TD WIDTH="175" >
            <B>Account Number</B>
          </TD>
          <TD WIDTH="185" >
            <B>Central Account ID</B>
          </TD>
          <TD WIDTH="150" >
            <B>Account Name</B>
          </TD>
          <TD WIDTH="190" >
            <B>Social Security Number</B>
          </TD>
        </TR>
    </eagls:pagedListHeader>
    <eagls:pagedListBody id="individualAccount" name="searchResult" >
        <tr>
            <td><bean:write name="individualAccount" property="acctNumber"/>&nbsp;</td>
            <td><bean:write name="individualAccount" property="centralAcctID"/>&nbsp;</td>
            <td><bean:write name="individualAccount" property="accountName"/>&nbsp;</td>
            <td><bean:write name="individualAccount" property="ssn"/>&nbsp;</td>
        </tr>
    </eagls:pagedListBody>
</eagls:pagedList>
</TABLE>

<%--    <eagls:pagedListBar>
        </TABLE>
        <TABLE BORDER="1" WIDTH="700">
            <tr>
                <td align="center"><eagls:previousButton/></td>
                <td align="center"><eagls:nextButton/></td>
            </tr>
        </TABLE>
    </eagls:pagedListBar> --%>

<%/*
<logic:iterate id="individualAccount" name="searchResult" scope="session">
    <tr>
        <td><bean:write name="individualAccount" property="acctNumber"/>&nbsp;</td>
        <td><bean:write name="individualAccount" property="centralAcctID"/>&nbsp;</td>
        <td><bean:write name="individualAccount" property="firstName"/>&nbsp;
            <bean:write name="individualAccount" property="lastName"/>&nbsp;</td>
        <td><bean:write name="individualAccount" property="ssn"/>&nbsp;</td>
    </tr>
</logic:iterate>*/
    %>



<FORM NAME="frm_resultsAccountSetupMultipleResults"
      METHOD="POST"
      TARGET="content"
      ACTION="<%=request.getContextPath()%>/setup/individualAccount/accountDetails.do">

   <INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Setup">

   <INPUT TYPE="Hidden" NAME="txt_centralAccountID" VALUE="9025202">


<!------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_submitButton" VALUE="New Account">&nbsp;&nbsp;

        <INPUT TYPE="Button" NAME="but_CancelButton" VALUE="Revise Search"
               onclick="history.go(-1);">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------>


<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
         <H6 class="copyright">
           Copyright © 1999 Bank of America, NA (USA). All rights reserved.
         </H6>
      </TD>
    </TR>
</TABLE>


</FORM>
</BODY>
</HTML>
<% //session.removeAttribute("searchResult"); %>
