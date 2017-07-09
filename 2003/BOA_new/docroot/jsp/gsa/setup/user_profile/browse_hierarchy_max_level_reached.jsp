
<HTML>
<HEAD>

<TITLE>Confirmation</TITLE>

<META NAME="Description" CONTENT="Browse Hierarchy warning">

<script>
isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

</script>


</HEAD>

<!------------------------------------------------------------------------------>

<!--BODY BACKGROUND="/gsa/images/gsabg.gif"-->
<body>
<FORM NAME="frm_browseHierarchyWarning"
      METHOD="POST"
      TARGET="content">

<CENTER>
        <H2 class="titleText" >
          Browse<BR>
          Hierarchies </h2>
</CENTER>
<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Browse">

<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">
<BR>
<BR>

<!------------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        The last hierarchy level has been reached.  You cannot browse at this level.
      </TD>
    </TR>
</TABLE>

<BR>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="BUTTON" NAME="but_ok" VALUE="&nbsp;&nbsp;OK&nbsp;&nbsp;"
         onClick="window.close()">
      </TD>
    </TR>
</TABLE>
<!------------------------------------------------------------------------------>


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

</FORM>
</BODY>
</HTML>
