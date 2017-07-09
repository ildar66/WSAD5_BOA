<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">


<HTML>

<HEAD>

<TITLE>EAGLS System Logoff Confirmation</TITLE>

<META NAME="Name" CONTENT="System Logoff">
<META NAME="Description" CONTENT="EAGLS system logoff Confirmation">


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
<!-------------------------------------------------------------->
<!--BODY BACKGROUND="/gsa/images/gsabg.gif"-->
<!-------------------------------------------------------------->
<CENTER>
<TABLE>
  <TR>
    <!--TD><IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
    <TD ALIGN="Center">
       <H2><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">
       EAGLS System Logoff<BR>
       Confirmation
       </FONT></H2>
    </TD>
  </TR>
</TABLE>
<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="600" HEIGHT="6" >

<!-------------------------------------------------------------->
<FORM NAME="frm_systemLogoffConfirm"
      onSubmit ="window.parent.close();">
<!-------------------------------------------------------------->

<FONT COLOR="#000000" FACE="ARIAL" SIZE=+1>Thank you for using EAGLS.</FONT>
<BR>
<BR>

<TABLE BORDER="0">
  <TR>
   <TD ALIGN="CENTER"><INPUT TYPE="SUBMIT" NAME="but_submitButton" VALUE="Close Application"></TD>
  </TR>
</TABLE>

</FORM>
<%@include file="/jsp/gsa/footer_systemDefault.jsp"%>
</CENTER>
</BODY>
</HTML>
