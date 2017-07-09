<%@ taglib uri="/boa/app" prefix="app" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%--<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>--%>
<html:html locale="true">

<HEAD>

<TITLE></TITLE>

<META NAME="Name" CONTENT="Warning - System Default">
<META NAME="Description" CONTENT="Generic system warning page">
<META NAME="Author" CONTENT="Brent Ramsby">
<META NAME="Created" CONTENT="08/31/1998">
<META NAME="Revised" CONTENT="10/26/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<script LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

function back()
   {
     // CHECK WINDOW HISTORY THEN GO BACK OR CLOSE WINDOW

    var applicationVersionNumber = navigator.appVersion;

    // TEST TO SEE IF BROWSER IS IE 3.X
    if ((navigator.appName == ""))
      {
          history.go(-1);   // history.length is always 0 so we don't bother to check
          window.close();   // IE 3 does not close the window unless it is an outside frame
      }else{

        // ALL OTHER BROWSERS

        if (window.history.length > 1)
          {
           window.history.go(-1);
          }
        else
          {
           window.close();
          }
       }
  }
// Stop hiding -->
</script>
</HEAD>

<!------------------------------------------------------------------------------>

<BODY>

<FORM>

<CENTER>
<TABLE>
  <TR>
    <TD ALIGN="Center">
       <H2><FONT COLOR="#0000FF" FACE="Arial, HELVETICA">
        <html:errors property="heading"/>
       </FONT></H2>
    </TD>
  </TR>
</TABLE>
<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="600" HEIGHT="6" >
</CENTER>


<!------------------------------------------------------------------------------>
<br>
<TABLE>
    <TR>
        <TD WIDTH="150" VALIGN="Top"><B>Message</B></TD>
        <TD VALIGN="Top"><B><html:errors property="msg"/></B></TD>

    </TR>
    <TR>
      <TD COLSPAN=2>&nbsp;</TD>
    </TR>
    <TR>
        <TD WIDTH="150" VALIGN="TOP"><B>Description</B></TD>
        <TD VALIGN="TOP"><B><html:errors property="desc"/></B></TD>
    </TR>

    <TR>
      <TD COLSPAN=2>&nbsp;</TD>
    </TR>
    <TR>
        <TD WIDTH="150" VALIGN="TOP"><B>Severity Code</B></TD>
        <TD VALIGN="TOP"><B><html:errors property="scode"/></B></TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Button" NAME="but_OK" VALUE="BACK"
               onClick="back();">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------------>

<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>


</FORM>
</BODY>
</html:html>
