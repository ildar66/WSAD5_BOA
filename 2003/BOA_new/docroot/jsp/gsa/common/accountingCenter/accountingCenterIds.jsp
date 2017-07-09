<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<HTML>
<HEAD>

<TITLE>Please Select an Accounting Center Identification Number</TITLE>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

function Initialize(theForm)
    {
     if (theForm.availableAccountingCenterIds.length != 0)
         {
          theForm.availableAccountingCenterIds.options[0].selected=true;
         }
    }

function assignvalue(theForm)
    {
     if ((theForm.availableAccountingCenterIds.options[theForm.availableAccountingCenterIds.selectedIndex].text == "... Previous ...")||
        (theForm.availableAccountingCenterIds.options[theForm.availableAccountingCenterIds.selectedIndex].text == "... Next ..."))
      {
       window.location.href=theForm.availableAccountingCenterIds.options[theForm.availableAccountingCenterIds.selectedIndex].value;
       return;
      }
     window.opener.selectAccountingCenterId(theForm.availableAccountingCenterIds.options[theForm.availableAccountingCenterIds.selectedIndex].value);
     window.close();
    }

// Stop hiding -->
</SCRIPT>

</HEAD>

<!------------------------------------------------------------------------------>

<BODY onload="Initialize(document.frm_browseAccountingCenterIds)">

<html:form name="frm_browseAccountingCenterIds" type="com.boa.eagls.government.controller.formbean.common.BrowseCenterAccountingIDForm"
            action="/common/browseAccountingCenterIds" method="POST"
            target="_self" >
<!------------------------------------------------------------------------------>
<CENTER>
<html:img page="/jsp/gsa/images/line.gif" width="475" height="6"/>

<BR><BR>
<B><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">Available Accounting Center Identification Numbers</FONT></B>
<BR><BR>
<!--jsp:useBean id="options" class="com.boa.eagls.government.dto.accounting.AccountingCenterSummary" scope="request" /-->

<html:select name="frm_browseAccountingCenterIds"  property="availableAccountingCenterIds" size="10" >
<logic:iterate id="accountingCenterSummary" name="options" scope="request">
        <option VALUE="<bean:write name="accountingCenterSummary" property="accountingCenterID"/>">
            <bean:write name="accountingCenterSummary" property="accountingCenterID"/>
            [<bean:write name="accountingCenterSummary" property="accountingCenterName"/>]
</logic:iterate>
    <!--html:options name="frm_browseAccountingCenterIds" property="idValues" /-->
</html:select><!--labelProperty="frm_browseAccountingCenterIds.idDescriptions"-->

<BR>
<BR>

<!------------------------------------------------------------------------------>

<INPUT TYPE="BUTTON" NAME="but_select" VALUE="Select"
       onclick="assignvalue(document.frm_browseAccountingCenterIds)">&nbsp;&nbsp;

<INPUT TYPE="BUTTON" NAME="but_cancel" value="Cancel"
       onclick="window.close()">

<!------------------------------------------------------------------------------>

<BR>
<BR>
<html:img page="/jsp/gsa/images/line.gif" width="475" height="6"/>
</CENTER>

<%--</FORM>--%>
</html:form>
</BODY>
</HTML>
