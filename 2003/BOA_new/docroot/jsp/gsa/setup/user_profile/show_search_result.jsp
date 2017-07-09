<%@ page language="java" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>

<html:html>
<head>
<META NAME="Name"        CONTENT="Search Screen">
<META NAME="File Name"   CONTENT="search.html">
<META NAME="Description" CONTENT="Search Screen">
<SCRIPT LANGUAGE="JavaScript" SRC="<%= request.getContextPath() %>/jsp/gsa/scripts/reviseSearch.js"></SCRIPT>

<script LANGUAGE="JavaScript">
isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}
</script>

</head>

<body bgcolor="#ffffff">
<form action="<%=request.getContextPath()%>/jsp/gsa/setup/user_profile/setup_userProfile.jsp">


<TABLE WIDTH="575">
    <TR>
      <TD ALIGN="Center">

        <H2 class="titleText" ><FONT COLOR="#0000FF" FACE="Arial, Helvetica">
          User Profile Setup<BR>

          Search Results
        </FONT></H2>
      </TD>
    </TR>
</TABLE>
<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6"/>

<%@include file="results_userProfile.jsp"%>

<br>
<%@include file="/jsp/gsa/footer_systemDefault.jsp"%>
</form>
</body>
</html:html>

