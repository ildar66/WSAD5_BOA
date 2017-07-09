<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<HTML>
<HEAD>

<TITLE>No Results Found</TITLE>

<META NAME="Name" CONTENT="No Results Screen">
<META NAME="Description" CONTENT="Displays the message for no results found">
<META NAME="Author" CONTENT="Brent Ramsby">
<META NAME="Created" CONTENT="07/10/1998">
<META NAME="Revised" CONTENT="MM/DD/YYYY(Developer Name)">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<script LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

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

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>


<TABLE>
    <TR>
      <TD><FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Search Criteria</B></TD>
      <TD COLSPAN="2">&nbsp;</TD>
    </TR>

      <TR>
        <TD><B>Central Account ID</B></TD>
        <TD><bean:write name="searchAccountForm" property="txtCentralAccountID"/></TD>
      </TR>

</TABLE>
<BR>
<BR>

<!------------------------------------------------------------------------------>

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


<TABLE>
  <TR>

      <TD WIDTH="150" VALIGN="Top"><B>Message</B></TD>
      <TD WIDTH="400" VALIGN="Top"><B>theMessage</B></TD>

  </TR>
  <TR>
    <TD COLSPAN=2>&nbsp;</TD>
  </TR>

    <TR>

        <TD VALIGN="Top"><B>Description</B></TD>
        <TD VALIGN="Top"><B>theDescription</B></TD>

    </TR>

  <TR>
    <TD COLSPAN=2>&nbsp;</TD>
  </TR>

    <TR>

        <TD VALIGN="Top"><B>Severity Code</B></TD>
        <TD VALIGN="Top"><B>theSeverityCode</B></TD>

    </TR>

</TABLE>
<HR WIDTH="575">
<BR>

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


<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>

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
