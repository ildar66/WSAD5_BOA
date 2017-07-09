<%@ page language="java" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<HTML><HEAD><TITLE>Session Unavailable</TITLE>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<BODY>
<FORM target=main>
<CENTER>
<TABLE>
  <TBODY>
  <TR>
    <TD align=middle>
      <H2><FONT face="Arial, HELVETICA" color=#0000ff>System Access<BR>Session
      Unavailable </FONT></H2></TD></TR></TBODY></TABLE>
<IMG height=6 src="images/line.gif" width=600> <BR><BR>

<FONT SIZE="3" face="Times New Roman"><html:errors /></FONT>
<CENTER><BR><IMG height=6 src="images/line.gif" width=575>
<TABLE>
  <TBODY>
  <TR>
    <TD align=middle width=575>
      <H6 class=copyright>Copyright © 1999 Bank of America, NA (USA). All rights
      reserved. </H6></TD></TR></TBODY></TABLE>
</CENTER>

</CENTER>
</FORM></BODY></HTML>
