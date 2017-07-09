<%@ taglib uri="/boa/app" prefix="app" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<html:html locale="true">

<HEAD>
 <TITLE>Temporary Information Screen</TITLE>

<SCRIPT LANGUAGE="JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

function pageReturnTest()
  {
   if (window.opener)
     {
      window.close();
     }
   else
     {
      history.go(-1);
     }
  }
</SCRIPT>

</HEAD>
<body>

<!-------------------------------------------------------------->
<FORM>
<CENTER>
<html:img page="/jsp/gsa/images/line.gif" width="600" height="6" />
<BR>

   <B> This function is not implemented yet</B>
   <br>
   <table border=0 width=70%>
   <tr><td>URL:</td><td><%=request.getRequestURI()%></td></tr>
   <tr><td>Search:</td><td><%=request.getQueryString()%></td></tr>
   <logic:present name="functionality" scope="request"  >
    <tr><td>Functionality:</td><td><%=request.getAttribute("functionality")%></td></tr>
   </logic:present>
   </table>
<BR>
<BR>
<TABLE>
 <TR>
  <TD ALIGN=CENTER>
   <INPUT TYPE="BUTTON" NAME="ok" VALUE="&nbsp;&nbsp;OK&nbsp;&nbsp;"
 onclick="pageReturnTest()">
  </TD>
 </TR>
</TABLE>
</CENTER>
</FORM>
<BR>
<CENTER>
<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>
</CENTER>

</BODY>
</html:html>
