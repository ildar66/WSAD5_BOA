<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<html:html>
<HEAD>

<TITLE>MasterCard Vehicle Account Setup (Account Exists)</TITLE>

<META NAME="Name" CONTENT="MasterCard Vehicle Account Setup (Account Exists)">
<META NAME="Description" CONTENT="Used to notify user account already exists">
<META NAME="Author" CONTENT="Charlie Bruggemann/David Taylor">
<META NAME="Created" CONTENT="14 July 1998">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>

<!------------------------------------------------------------------------->

<BODY>

<TABLE WIDTH="575">
    <TR>
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Information<BR>
          An Account Already Exists for Your Query
        </H2>
      </TD>
    </TR>
</TABLE>
<html:img src="jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>
<!------------------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Search Criteria</B></FONT>

<TABLE>
    <TR>
      <TH WIDTH="150" ALIGN="Left">Central Account ID</TH>
      <TD WIDTH="352">
        <GX type=cell id=results.centralAccountId>centralAccountId</GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left">Equipment ID</TH>
      <TD>
        <GX type=cell id=results.equipmentId>equipmentId</GX>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        An account already exists for the above Central Account ID and Equipment ID combination.
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------------------------------------------------------->

<TABLE BORDER="0">
    <TR>
      <TD WIDTH="575" ALIGN="CENTER">
		<html:button value="Revise Search" property="but_revise" onclick="history.go(-1);"/>
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------->

<template:insert template="/jsp/gsa/common/footer_systemDefault.jsp" />

</BODY>
</html:html>
