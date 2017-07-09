<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<html:html>
<HEAD>

<TITLE>MasterCard Vehicle Account Setup Search Results</TITLE>

<META NAME="Name" CONTENT="MasterCard Vehicle Account Setup Search Results">
<META NAME="Description" CONTENT="Search Results for No Data Found">
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
          No Data Found for Your Query
        </H2>
      </TD>
    </TR>
</TABLE>

<html:img src="jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>
<!------------------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="ARIAL, HELVETICA"><B>Search Criteria</B></FONT>

<TABLE>
    <TR>
      <TH WIDTH="150" ALIGN="Left">Central Account ID</TH>
      <TD WIDTH="300">
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
        No results were found for this search.
      </TD>
    </TR>
</TABLE>
<BR>
<BR>

<!------------------------------------------------------------------------->

<TABLE BORDER="0">
    <TR>
      <TD WIDTH="575" ALIGN="Center">
		  <html:button value="Revise Search" property="but_revise" onclick="history.go(-1);"/>
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------->

<template:insert template="/jsp/gsa/common/footer_systemDefault.jspf" />

</BODY>
</html:html>
