<%@ page language="java" import="org.apache.struts.action.DynaActionForm" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>

<html:html locale="true">
<HEAD>
<TITLE>User Profile Setup - Confirmation</TITLE>

<META NAME="Name" CONTENT="User Profile Confirmation">
<META NAME="Description" CONTENT="Confirms a user profile entry">


<script LANGUAGE="JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

</script>

</HEAD>
<BODY>
<P>

<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING, "heading.userprofile.setup");%>
<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.confirmation");%>
<%@ include file="/jsp/gsa/header_systemDefault.jsp"%>
<CENTER>
<html:errors />
<BR>
<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
<%=request.getAttribute("reportsMsg")==null?"":request.getAttribute("reportsMsg")%>
<br>
<br>
<INPUT TYPE="BUTTON" NAME="but_Ok" VALUE=" OK "
       onClick="window.location.href='<%=request.getContextPath()%>/Search.do?searchType=createUserProfile&selected=T'">

</CENTER>
<br><br>
<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>
</BODY>
</html:html>

