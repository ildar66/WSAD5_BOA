<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<HTML>
<HEAD>
<TITLE>Session Unavailable</TITLE>

<META NAME="Name" CONTENT="Warning - Session Unavailable">
<META NAME="Description" CONTENT="Displays Session Unavailable Warning">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>

<BODY>
<FORM TARGET="main">
<CENTER>
<TABLE>
  <TR>
    <TD ALIGN="Center">
       <H2><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">
       System Access<BR>
       Session Unavailable
       </FONT></H2>
    </TD>
  </TR>
</TABLE>
<IMG SRC="jsp/gsa/images/line.gif" WIDTH="600" HEIGHT="6">

<BR>
<BR>
<FONT SIZE=+1>
Your current EAGLS session has timed out. <br>
Please return to the Logon Screen to reenter the system.

<BR>
</FONT>
<CENTER>
<%@include file="/jsp/gsa/footer_systemDefault.jsp"%>
</CENTER>
</FORM>
</BODY>
</HTML>
