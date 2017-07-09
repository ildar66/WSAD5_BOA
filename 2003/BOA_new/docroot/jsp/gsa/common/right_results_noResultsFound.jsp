
<HTML>
<HEAD>

<TITLE>No Results Found</TITLE>

<META NAME="Name" CONTENT="No Results Screen">
<META NAME="Description" CONTENT="Displays the message for no results found">


<script LANGUAGE="JavaScript">
<!-- Start hiding from other browsers
isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

function historyTest()
   {
     // CHECK WINDOW HISTORY THEN GO BACK OR CLOSE WINDOW

    var applicationVersionNumber = navigator.appVersion;

    // TEST TO SEE IF BROWSER IS IE 3.X

    if ((navigator.appName == "Microsoft Internet Explorer")&&
        ((applicationVersionNumber.substring(0,1) == "3")||
         (applicationVersionNumber.substring(0,1) == "2")) )
      {
       if (window.history.length > 0)
         {
          history.go(-1);
         }
       else
         {
          window.close();
         }

      }

    // ALL OTHER BROWSERS

     if (window.history.length > 1)
       {
        history.go(-1);
       }
     else
       {
        window.close();
       }
    }

function continueTo()
  {
    if(window.name != "content" && window.name != "AcctMaintWindow")
	{
      window.parent.close();
	}
	else
	{
	  history.go(-1)
	}
  }

// Stop hiding -->
</script>
</HEAD>

<!------------------------------------------------------------------------------>

<!--BODY BACKGROUND="/gsa/images/gsabg.gif"-->

<FORM NAME ="frm_noResultsFound"
      ACTION=""
      METHOD="POST">


<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Information<BR>
          No Data Found for Your Query
        </H2>
      </TD>
    </TR>
</TABLE>
<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Information">
<IMG SRC="<%= request.getContextPath() %>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">
<BR>
<BR>
<table>
    <TR>
      <TD><FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Search Criteria</B></TD>
      <TD COLSPAN="2">&nbsp;</TD>
    </TR>
      <TR>
        <TD><B><%= request.getAttribute("searchCriteria") %>:</B></TD>
        <TD><%= request.getAttribute("searchCriteriaValue") %></TD>
      </TR>
</FONT>
</TABLE>

<!------------------------------------------------------------------------------>
<br><br>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        No results were found for this query.
      </TD>
    </TR>
</TABLE>
<BR>

<!-- THIS WAS ADDED TO HANDLE EXCEPTION MESSAGING -->
<!--
<GX type=cell id=showExceptionMessaging>

<TABLE>
  <TR>
    <GX type=replace id=message value=theMessage>
      <TD WIDTH="150" VALIGN="Top"><B>Message</B></TD>
      <TD WIDTH="400" VALIGN="Top"><B>theMessage</B></TD>
    </GX>
  </TR>
  <TR>
    <TD COLSPAN=2>&nbsp;</TD>
  </TR>
  <GX type=cell id=showDescription>
    <TR>
      <GX type=replace id=description value=theDescription>
        <TD VALIGN="Top"><B>Description</B></TD>
        <TD VALIGN="Top"><B>theDescription</B></TD>
      </GX>
    </TR>
  </GX>
  <TR>
    <TD COLSPAN=2>&nbsp;</TD>
  </TR>
  <GX type=cell id=showSeverityCode>
    <TR>
      <GX type=replace id=severityCode value=theSeverityCode>
        <TD VALIGN="Top"><B>Severity Code</B></TD>
        <TD VALIGN="Top"><B>theSeverityCode</B></TD>
      </GX>
    </TR>
  </GX>
</TABLE>
<HR WIDTH="575">
<BR>
</GX>
-->
<!-- END EXCEPTION MESSAGING -->

<!------------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
         <INPUT type="Button" name="but_revise" value="Revise Search"
                onclick="continueTo()">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------------>

<br>
<%@include file="/jsp/gsa/footer_systemDefault.jsp"%>

</FORM>
</BODY>
</HTML>
