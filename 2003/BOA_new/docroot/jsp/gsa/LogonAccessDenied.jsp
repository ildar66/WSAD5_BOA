<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<html:html locale="true">

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


<TITLE>EAGLS System Logon Access Denied</TITLE>

<META NAME="Name" CONTENT="System Logon Access Denied">
<META NAME="Description" CONTENT="Prompts the users about the acces denial for EAGLS">

</HEAD>
<body>


<!---------------------------------------------------->
<FORM NAME="frm_invalidUserIDPassword">
<!---------------------------------------------------->

<CENTER>
<TABLE>
  <TR>
    <TD ALIGN="Center">
       <H2><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">
        System Access<BR>
        Logon Failed
       </FONT></H2>
    </TD>
  </TR>
 </TABLE>

<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">
<BR><BR>
<TABLE BORDER="0" WIDTH="575">
  <TR>
   <TD ALIGN = "CENTER">
     <FONT SIZE="3" face="Times New Roman">
     <BR>
<html:errors />
     </FONT>
   </TD>
  </TR>
  <TR>
   <TD>&nbsp;</TD>
  </TR>
  <TR>
   <TD ALIGN = "CENTER">
      <INPUT TYPE="BUTTON" NAME = "but_submitButton" VALUE="  OK  "
        onClick = "history.go(-1);">
   </TD>
  </TR>

</TABLE>
<BR><IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
         <H6 class="copyright">
           Copyright © 1999 Bank of America, NA (USA). All rights reserved.
         </H6>
      </TD>
    </TR>
</TABLE>

</CENTER>
</FORM>
</body>
</html:html>
