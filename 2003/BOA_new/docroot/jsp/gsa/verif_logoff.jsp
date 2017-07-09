<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<HTML>

<HEAD>

<script LANGUAGE="JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

</script>


<TITLE>EAGLS System Logoff verification</TITLE>

<META NAME="Name" CONTENT="System Logoff">
<META NAME="Description" CONTENT="EAGLS system logoff verification">

</HEAD>
<!-------------------------------------------------------------->
<!--BODY BACKGROUND="/gsa/images/gsabg.gif"-->
<!-------------------------------------------------------------->
<CENTER>
<TABLE>
  <TR>
    <!--TD><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
    <TD ALIGN="Center">
       <H2><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">
       EAGLS System Logoff<BR>
       Verification
       </FONT></H2>
    </TD>
  </TR>
</TABLE>
<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" width="600" height="6" >

<!-------------------------------------------------------------->
<html:form action="/Logoff">
<html:hidden property="hdn_confirmLogoff" value="true"/>

<!-------------------------------------------------------------->

<FONT COLOR="#000000" FACE="ARIAL" SIZE=+1>Do you want to end the current EAGLS session ?</FONT>
<BR>
<BR>

<TABLE BORDER="0">
  <TR>
   <TD ALIGN="CENTER"><html:submit property="but_submitButton" value="     OK     " />&nbsp;&nbsp;</TD>
   <TD ALIGN="CENTER"><INPUT TYPE="BUTTON" NAME="but_cancelButton" VALUE="Cancel"
    onClick = "history.go(-1);"></TD>
  </TR>
</TABLE>

</html:form>
<%@include file="footer_systemDefault.jsp"%>
</CENTER>
</HTML>
