<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<html:html>
<HEAD>

<TITLE>MasterCard Vehicle Account Setup Search</TITLE>

<META NAME="Name" CONTENT="MasterCard Vehicle Account Setup Search ">
<META NAME="Description" CONTENT="Allows a user to setup a MasterCard Vehicle Account">
<META NAME="Author" CONTENT="Charlie Bruggemann/David Taylor">
<META NAME="Created" CONTENT="07/14/1998">
<META NAME="Revised" CONTENT="08/31/98 Screen layout updated -BB">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<SCRIPT LANGUAGE="Javascript">
<!-- Start Hidding

function Initialize(theForm)
    {
     parent.logo.setFocus(theForm,theForm.txt_centralAccountId,(parent.logo.Status_Text_CentralAccountID));
    }

function Form_Test(theForm)
    {
     var errormessage = parent.logo.ErrMsg_Header;

     if ((theForm.txt_centralAccountId.value == "") ||
         (parent.logo.checkLength(theForm,theForm.txt_centralAccountId,7)== "error") ||
         (parent.logo.checkNumeric(theForm,theForm.txt_centralAccountId) == "error"))
         {
          errormessage += parent.logo.ErrMsg_CentralAccountID;
         }

    <!--MM--07/14--Commented this part to make Equipment id not a required field

    if ((parent.logo.checkAlphaNumeric(theForm,theForm.txt_equipmentId) == "error"))
        {
         errormessage += parent.logo.ErrMsg_EquipmentID;
        }

     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return false;
         }
     else
         return true;
    }

//Stop Hidding-->
</SCRIPT>

</HEAD>

<!------------------------------------------------------------------------->

<BODY onLoad="Initialize(document.searchVehicleAccountForm)">

<html:form action="/searchVehicleAccount"
 method="POST"
 onsubmit="return Form_Test(document.searchVehicleAccountForm)">

<TABLE WIDTH="575">
    <TR>
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Vehicle Setup<BR>
          Search
        </H2>
      </TD>
    </TR>
</TABLE>

<html:hidden value="Vehicle Setup" property="txt_screenTitle1"/>
<html:img page="/jsp/gsa/images/line.gif" width="600" height="6"/>
<BR>
<BR>
<!------------------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Search by</B></FONT>

<TABLE BORDER="0">
    <TR>
      <TD WIDTH="150"><B>Central Account ID</B></TD>
      <TD WIDTH="325">
        <html:text property="txt_centralAccountId" size="8" maxlength="7"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_CentralAccountID)" />
      </TD>
    </TR>
    <TR>
      <TD>Equipment ID</TD>
      <TD>
        <html:text property="txt_equipmentId" size="18" maxlength="17"
               onfocus="parent.logo.Display_Message(parent.logo.Status_Text_EquipmentID)" />
      </TD>
    </TR>
</TABLE>

<!-------------------------------------------------------------------------->

<BR>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <html:submit value="Submit" property="but_submit"/>&nbsp;&nbsp;
        <html:reset value="&nbsp;Clear&nbsp;" property="but_clear" />
      </TD>
    </TR>
</TABLE>

<!-------------------------------------------------------------------------->

<template:insert template="/jsp/gsa/common/footer_systemDefault.jspf" />

</html:form>
</BODY>
</html:html>