<%@ page import="java.util.Collection,
                 java.util.Iterator,
                 com.boa.eagls.government.dto.pointOfContact.PointOfContactDTO,
                 com.boa.eagls.government.controller.formbean.inquiry.PointOfContactForm"%>
<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<HTML>
<HEAD>
<TITLE>Point of Contact Search Results</TITLE>

<META NAME="Author" CONTENT="Stanislav Shabalin">
<META NAME="File" CONTENT="CAcctResults_contactMultipleResults.html">
<META NAME="Description" CONTENT="Multiple results on point of contact search">
<META NAME="Created" CONTENT="04/07/2003">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

<!----------------------------------------------------------------------------->

<BODY>
<%--<bean:struts id="logon_forward" forward="/inqury/CentralAccount"/>--%>
<%--<FORM action="<%=request.getContextPath()%><bean:write name="logon_forward" property="path"/>">--%>
<FORM action="<%=request.getContextPath()%>/jsp/gsa/centralaccount/search.jsp">
<input type="hidden" name="searchType" value="centralAccountInquiry"/>

<input type="hidden" name="selected" value="T"/>

<template:insert template="/jsp/gsa/common/header_systemDefault.jsp" >
    <template:put name="screenTitle-1" content="Current Points of Contact" direct="true"/>
    <template:put name="screenTitle-2" content="" direct="true"/>
</template:insert>

<%--<a href="<%=request.getContextPath()%>/jsp/gsa/centralaccount/search.jsp?searchType=centralAccountInquiry&selected=T'">revise</a>--%>

<!--GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX><BR-->
<!-------------------------------------------------------------------------->
<!--GX type=tile id=searchCriteria>
      <TR>
        <TD><B><GX type=cell id=searchCriteria.criteria>criteria</GX></B></TD>
        <TD><GX type=cell id=searchCriteria.value>value</GX></TD>
      </TR>
    </GX>
<BR><BR-->

<BR><BR>

<TABLE border=1>
  <TBODY>
  <TR>
    <TH align=left  width=175>Name
    </TH>
    <TH align=left  width=70>TDO
      </TD>
    <TH align=left  width=70>DBO
      </TD>
    <TH align=left  width=70>EC/EDI
      </TD>
    <TH align=left  width=112>Payment
      Office</TD>
    <TH align=left  width=70>A/OPC
      </TD></TR><GX id=results type="tile">
<%
    Collection pointsOfContacts = (Collection) request.getSession().getAttribute("searchResult");
    int i = 0;
    if (pointsOfContacts != null) {
        Iterator it = pointsOfContacts.iterator();
        while (it.hasNext()) {
            PointOfContactDTO poinOfContact = (PointOfContactDTO) it.next();

%>
    <tr>
        <td><a href="/boa/viewPointOfContact.do?id=<%=i%>">
        <%=poinOfContact.getFirstName()%> <%=poinOfContact.getLastName()%></a></td>
        <td><%=PointOfContactForm.stringValue(poinOfContact.getTDO())%></td>
        <td><%=PointOfContactForm.stringValue(poinOfContact.getDBO())%></td>
        <td><%=PointOfContactForm.stringValue(poinOfContact.getECEDI())%></td>
        <td><%=PointOfContactForm.stringValue(poinOfContact.getPaymentOffice())%></td>
        <td><%=PointOfContactForm.stringValue(poinOfContact.getAOPC())%></td>
    </tr>
<%      i++;
        }
    }
%>

</TBODY>
</TABLE>
<br/>
<br/>
<TABLE>
<TBODY>
<TR>
	<TD align=middle width=575>
<%--		<!INPUT name=but_submit type=button value="Revise Search">--%>
		<INPUT TYPE="submit" NAME="but_submit" VALUE="Revise Search" >&nbsp;&nbsp;
		<INPUT TYPE=button NAME="but_cancel" VALUE="Cancel"
			onclick="history.go(-1);">
	</TD>
</TR>

</TBODY>
</TABLE>
<br/>
<!-------------------------------------------------------------------------->
<%@ include file = "/jsp/gsa/footer_systemDefault.jsp"%>
<!--GX id=/gsa/common/footer_systemDefault.html type="include">Insert Footer Here</GX-->
</FORM>
</BODY>
</HTML>

