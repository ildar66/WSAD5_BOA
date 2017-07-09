<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<html:html>

<TITLE>MasterCard Vehicle Account Setup Confirmation</TITLE>

<META NAME="Name" CONTENT="MasterCard Vehicle Account Setup Confirmation">
<META NAME="Description" CONTENT="Used to confirmation new vehicle setup was correct">
<META NAME="Author" CONTENT="Charlie Bruggemann">
<META NAME="Created" CONTENT="14 July 1998">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

</HEAD>

<!------------------------------------------------------------------------->

<BODY>

<FORM NAME="theForm"
      METHOD="Post">
<TABLE WIDTH="575">
    <TR>
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Vehicle Account Setup<BR>
          Confirmation
        </H2>
      </TD>
    </TR>
</TABLE>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>

<!------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        You have successfully submitted Vehicle Account Setup information for the vehicle account associated
	with the following Hierarchy structure.
      </TD>
    </TR>
</TABLE>

<!-- JKT --1/19/2000  replace hierarcy table with include ---------->
<%@ include file="/jsp/gsa/common/right_hierarchy.jsp"%>
<!--END JKT -------------------------------------------------------->


<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        The account number will be available to you the next business day.<BR>
        You may now setup Authorization Controls for this new account.
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------------------------------------------------------->

<TABLE BORDER="0">
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Button" NAME="but_ok" VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;"
               onclick="window.location.href='jsp/gsa/setup/mastercard_vehicle/search_vehicle_account.jsp'">
      </TD>
    </TR>
</TABLE>
<BR>

<!------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <GX type=replace id=results.lnk_authControl value=hyperlink>
          <A HREF="<%=request.getContextPath()%>/jsp/gsa/notImplemented.jsp">Authorization Control Setup</A>
        </GX>
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------->

<template:insert template="/jsp/gsa/common/footer_systemDefault.jspf" />

</FORM>
</BODY>
</html:html>
