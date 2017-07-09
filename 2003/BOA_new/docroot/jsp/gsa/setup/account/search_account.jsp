<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<html:html>
<HEAD>

<TITLE>Account Setup Search</TITLE>

<META NAME="Name" CONTENT="Account Setup Search ">
<META NAME="Description" CONTENT="Allows a user to search for an account">
<META NAME="Author" CONTENT="Todd Shuler">
<META NAME="Created" CONTENT="07/06/1998">
<META NAME="Revised" CONTENT="09/23/1998 Edited -BB">
<SCRIPT src="<%=request.getContextPath()%>/jsp/gsa/scripts/hierarchyBrowse.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<SCRIPT LANGUAGE="Javascript">
<!-- Start hiding from other browsers

function form_Test(theForm)
    {
     var errormessage = parent.logo.ErrMsg_Header;

     if ((theForm.txtCentralAccountID.value == "")||
         (parent.logo.checkLength(theForm,theForm.txtCentralAccountID,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txtCentralAccountID) == "error"))
         {
          errormessage += parent.logo.ErrMsg_CentralAccountID;
         }

     if (theForm.ragAccountSetupSearch[0].checked)
         {
          if ((theForm.txtLastName.value  ==  "")||
             (parent.logo.checkAlphaNumericPlus(theForm,theForm.txtLastName) ==  "error"))
              {
               errormessage += parent.logo.ErrMsg_LastName;
              }

          if (parent.logo.checkAlphaNumericPlus(theForm,theForm.txtFirstName) ==  "error")
              {
               errormessage += parent.logo.ErrMsg_FirstName;
              }
         }

     if (theForm.ragAccountSetupSearch[1].checked)
         {
          if ((theForm.txtSocialSecurityNumber.value == "")||
              (parent.logo.checkSSN(theForm,theForm.txtSocialSecurityNumber) ==  "error"))
             {
               errormessage += parent.logo.ErrMsg_SSN;
             }
         }

     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return false;
         }
     else
     return true;
    }

function clearFieldsA(theForm)
    {
     theForm.ragAccountSetupSearch[0].checked=true;
     theForm.txtSocialSecurityNumber.value = "";
    }

function clearFieldsB(theForm)
    {
     theForm.ragAccountSetupSearch[1].checked=true;
     theForm.txtLastName.value = "";
     theForm.txtFirstName.value = "";
    }

// Stop hiding -->
</SCRIPT>

</HEAD>

<!------------------------------------------------------------------------------->

<BODY onLoad="clearReviseHierarchy();">

<html:errors />

<!--useBean id="searchAccountForm" class="com.boa.eagls.government.controller.formbean.account.SearchAccountForm"/-->

<!--jsp:setProperty name="searchAccountForm" property="true" /-->

<html:form action="/setup/individualAccount/searchAccount"
    method="POST" onsubmit="return form_Test(document.searchAccountForm)" >
<!--  -->

<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Account Setup<BR>Search
        </H2>
      </TD>
    </TR>
</TABLE>

<html:hidden value="Account Setup" property="txt_screenTitle1"/>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>

<!------------------------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Search By</FONT></B>

<TABLE BORDER="0">
    <TR>
      <TD COLSPAN="2">Central Account ID</TD>
      <TD>
        <html:text maxlength="7" property="txtCentralAccountID" size="8"/>
      </TD>
    </TR>
    <TR>
      <TD WIDTH="20">
        <html:radio value="true" property="ragAccountSetupSearch"
    	onclick="clearFieldsA(document.searchAccountForm);parent.logo.setFocus(document.searchAccountForm,document.searchAccountForm.txtLastName,parent.logo.Status_Text_LastName)"/>
      </TD>
      <TD WIDTH="170">Last Name</TD>
      <TD WIDTH="385">
        <html:text property='txtLastName' value=''
    		onfocus="clearFieldsA(document.searchAccountForm)"
    		size="27" maxlength="26"/>
      </TD>
    </TR>
    <TR>
      <TD>&nbsp;</TD>
      <TD>First Name</TD>
      <TD>
        <html:text property='txtFirstName' value=''
    		onfocus="clearFieldsA(document.searchAccountForm);parent.logo.Display_Message(parent.logo.Status_Text_FirstName)"
    		size="27" maxlength="26" />
      </TD>
    </TR>
    <TR>
      <TD>
        <html:radio value="false" property="ragAccountSetupSearch"
	    	onclick="clearFieldsB(document.searchAccountForm);parent.logo.setFocus(document.searchAccountForm,document.searchAccountForm.txtSocialSecurityNumber,parent.logo.Status_Text_SSN)"
	    	 />
      </TD>
      <TD>Social Security Number</TD>
      <TD>
        <html:text property='txtSocialSecurityNumber' value=''
    		onfocus="clearFieldsB(document.searchAccountForm);parent.logo.Display_Message(parent.logo.Status_Text_SSN);"
    		maxlength="11" size="12"/>
      </TD>
    </TR>
</TABLE>
<BR>

<!-------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <html:submit value="Submit" property="but_submit"/>&nbsp;&nbsp;

        <html:reset value="Clear"/>
      </TD>
    </TR>
</TABLE>


<%--<BR><html:img src="line.gif" width="575" height="6"/>--%>
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


</html:form>
</BODY>
</html:html>
