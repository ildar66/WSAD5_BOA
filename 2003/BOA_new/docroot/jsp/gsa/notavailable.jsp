<HTML>
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
<!-------------------------------------------------------------->
<!--BODY BACKGROUND ="/gsa/images/gsabg.gif"-->
<!-------------------------------------------------------------->
<CENTER>
<TABLE>
  <TR>
    <!--TD><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
    <TD ALIGN="Center">
       <H2>&nbsp;</H2>
       <H2><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">
       EAGLS Application Error
       </FONT></H2>
    </TD>
  </TR>
</TABLE>
</CENTER>
<p align="center">
<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" width="600" height="6" >

<!-------------------------------------------------------------->
<FORM><CENTER>
<TABLE  WIDTH = "575" >
 <TR>
  <TD  WIDTH = "150" >
   <B> Message</B>
  </TD>
  <TD WIDTH = "425" >
   Sorry for inconvenience, this functionality is not
   currently available.
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
<CENTER>
<%@include file="footer_systemDefault.jsp"%>
</CENTER>

</HTML>
