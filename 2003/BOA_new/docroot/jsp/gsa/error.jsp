<%@ page autoFlush = "true"%>
<%@ page  isErrorPage = "true"%>
<%@ taglib uri = "/struts/struts-html" prefix = "html" %>
<%@ taglib uri = "/struts/struts-logic" prefix = "logic" %>
<%@ taglib uri = "/struts/struts-bean" prefix = "bean" %>

<%@ page import="java.io.PrintWriter,
                 javax.naming.NamingException,
                 com.boa.eagls.government.taglib.pagedList.PageListException,
                 org.apache.log4j.Logger,
                 com.boa.eagls.government.constants.web.Messages"%>

<%! private static Logger logger = Logger.getLogger("errorPage.jsp");%>


<html:html locale = "true">
<head>
<title>EAGLS Error</title>

<script language = "JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
   document.write( '<link rel = stylesheet type = "text/css" href = "<%= request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
   document.write( '<link rel = stylesheet type = "text/css" href = "<%= request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}


//Start hiding from other browsers
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

<BODY>

<%if(exception!=null){%>
<%@ include file="errorStack.jspf" %>
<%}else{%>

<!-- BACKGROUND="/gsa/images/gsabg.gif"-->

<FORM NAME ="frm_noResultsFound"
      ACTION=""
      METHOD="POST">


<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
            <html:messages message="true" id="message" property="<%=Messages.TITLE%>">
                <bean:write name="message" filter="false" />
            </html:messages>
        </H2>
      </TD>
    </TR>
</TABLE>

<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Information">

<html:img page="/jsp/gsa/images/line.gif" width="600" height="6" />
<BR>
<BR>


<TABLE>
    <TR>
      <TD><FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B
            ><html:messages message="true" id="message" property="<%=Messages.HEADER%>">
                <bean:write name="message" filter="false" />
            </html:messages></B></TD>
      <TD COLSPAN="2">&nbsp;</TD>
    </TR>
    <html:errors/>

</TABLE>
<BR>
<BR>

<!------------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <html:messages message="true" id="message" property="<%=Messages.FOOTER%>">
            <bean:write name="message" filter="false" />
        </html:messages>
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
         <INPUT type="Button" name="but_revise"
                value='<html:messages message="true" id="message" property="<%=Messages.BUTTON%>"><bean:write name="message" filter="false" /></html:messages>'
                onclick="continueTo()">
      </TD>
    </TR>
</TABLE>


<!------------------------------------------------------------------------------>

</FORM>
<br>
<%@ include file = "footer_systemDefault.jsp"%>
<%}%>
</body>
</html:html>