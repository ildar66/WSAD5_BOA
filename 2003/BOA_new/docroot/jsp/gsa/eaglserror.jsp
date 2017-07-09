<%@ taglib uri="/boa/app" prefix="app" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%--<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>--%>
<html:html locale="true">

<HEAD>
 <TITLE>EAGLS Error</TITLE>

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

<CENTER>
<TABLE>
  <TR>
    <TD ALIGN="Center">
       <H2><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">
        <html:errors property="<%=com.boa.eagls.government.util.Constants.ERR_HEADING%>"/>
       </FONT></H2>
    </TD>
  </TR>
</TABLE>
<html:img page="/jsp/gsa/images/line.gif" width="600" height="6"/>
</CENTER>


<!-------------------------------------------------------------->
<FORM>
<CENTER>

<TABLE  WIDTH = "575" >
 <TR>
  <TD  WIDTH = "150" >
   <B> Message</B>
  </TD>
  <TD WIDTH = "425" >
  <html:errors property="<%=com.boa.eagls.government.util.Constants.ERR_MSG%>"/>
  </TD>
 </TR>
</TABLE>

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
