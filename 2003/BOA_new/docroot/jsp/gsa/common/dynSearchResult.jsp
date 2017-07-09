<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eagls" %>

<HTML>
<HEAD>

<TITLE>Account Maintenance Search Multiple Results</TITLE>

<META NAME="Name" CONTENT="Account Maintenance Search Multiple Results">
<META NAME="Description" CONTENT="Returns results on Account Maintenance Search Criteria">
<META NAME="Author" CONTENT="Todd Shuler">
<META NAME="Created" CONTENT="07/27/1998">
<META NAME="Revised" CONTENT="09/17/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>

<!------------------------------------------------------------------------------>


<BODY>
<FORM NAME="frm_resultsmaintainAccountInfo"
      METHOD="POST"
      TARGET="content"
      ACTION="/cgi-bin/gx.cgi/GUIDGX-{3BC94900-3151-11D2-9C1B-204C4F4F5020">




<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Maintain Account Information<BR>
          Multiple Search Results
        </H2>
      </TD>
    </TR>
</TABLE>

<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Maintain Account Information">
<useBean id="viewAccountForm" scope="request"/>
<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>


<TABLE>
    <TR>
      <TD><FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Search Criteria</B></TD>
      <TD COLSPAN="2">&nbsp;</TD>
    </TR>

    <logic:notEmpty name="viewAccountForm" scope="request" property="rag_acctMaintSearchFor" >
      <TR>
        <TD><B>Account Status</B></TD>
        <TD><bean:write name="viewAccountForm" scope="request" property="rag_acctMaintSearchFor" /></TD>
      </TR>
    </logic:notEmpty>

    <logic:notEmpty name="viewAccountForm" scope="request" property="chk_Name" >
        <TR>
        <TD><B>Name</B></TD>
        <TD><bean:write name="viewAccountForm" scope="request" property="txt_firstName" />
            <bean:write name="viewAccountForm" scope="request" property="txt_lastName" /></TD>
      </TR>
    </logic:notEmpty>

</TABLE>
<BR>
<BR>

<!------------------------------------------------------------------------------>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="130" >
        <B>Account Number</B>
      </TD>
      <TD WIDTH="150" >
        <B>Central Account ID</B>
      </TD>
      <TD WIDTH="130" >
        <B>Account Name</B>
      </TD>
      <TD WIDTH="190" >
        <B>Social Security Number</B>
      </TD>
    </TR>

    <!--useBean name="searchResult" scope="request"/-->
    <logic:iterate id="individualAccount" name="searchResult" scope="request">
        <tr>
            <td>
                <a href="javascript:alert('This function is not implemented')">
                    <bean:write name="individualAccount" property="acctNumber"/>&nbsp;
                </a>
            </td>
            <td><bean:write name="individualAccount" property="centralAcctID"/>&nbsp;</td>
            <td><bean:write name="individualAccount" property="firstName"/>&nbsp;
                <bean:write name="individualAccount" property="lastName"/>&nbsp;</td>
            <td><bean:write name="individualAccount" property="ssn"/>&nbsp;</td>
        </tr>
    </logic:iterate>

</TABLE>
<BR>

<!------------------------FORM ENDS------------------------->

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/reviseSearch.js"></SCRIPT>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">

      </TD>
    </TR>
</TABLE>
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
