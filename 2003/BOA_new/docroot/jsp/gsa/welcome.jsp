<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
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


<TITLE>Welcome To EAGLS</TITLE>

<META NAME="Name" CONTENT="Welcome Screen">
<META NAME="Description" CONTENT="Displays EAGLS Brand Logo">

</HEAD>

<!--BODY BACKGROUND="/gsa/images/gsabg.gif"-->
<CENTER>
<BR><BR><BR>
<IMG SRC="images/line.gif" width="575" height="6">
<BR><BR><BR>

<IMG SRC="images/eaglsLogo.gif" width="327" height="167"><br>
<IMG SRC="images/BofALogo.gif" width="166" height="42">
<BR>
<B><I>Version 2.0<BR>Release 7.8.0.1</I></B>
<BR><BR>
<IMG SRC="images/line.gif" width="575" height="6">
<H6><FONT SIZE="2" COLOR="000000">Copyright © 1999 Bank of America, NA (USA). All rights reserved.</FONT></H6>

</CENTER>
</HTML>
