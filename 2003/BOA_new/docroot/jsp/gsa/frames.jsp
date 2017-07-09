<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>

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
<TITLE>EAGLS - Electronic Account Government Ledger System</TITLE>

<META NAME="Name" CONTENT="Frames Generation Document">
<META NAME="Description" CONTENT="Controls the placement of the logo, menu and content areas">

</HEAD>

<FRAMESET cols="185,*" FRAMEBORDER="1" >
    <FRAMESET rows="50,*" FRAMEBORDER="1">
        <FRAME SRC="<%=request.getContextPath()%>/jsp/gsa/logo.jsp" NAME="logo" MARGINHEIGHT=0 MARGINWIDTH=0 NORESIZE SCROLLING="NO" >
        <FRAME SRC="DynMenuSelect.do?name=&selected=F" NAME="menu" MARGINHEIGHT=0 MARGINWIDTH=0 scrolling="auto">
    </FRAMESET>
    <FRAME SRC="<%=request.getContextPath()%>/jsp/gsa/welcome.jsp" NAME="content" MARGINWIDTH=5 scrolling="auto">

    <noframes>

<BODY></BODY>
    </noframes>

</FRAMESET>

</HTML>
