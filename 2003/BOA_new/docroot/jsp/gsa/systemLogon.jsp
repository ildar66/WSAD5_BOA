<%@ page language="java" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<html:html>
<HEAD>

<TITLE>EAGLS System Logon</TITLE>

<META NAME="Name" CONTENT="System Logon">
<META NAME="Description" CONTENT="Requires users to enter a username and password">
<META http-equiv="Expires" content="0" >
</HEAD>
<html:base/>

<SCRIPT LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}


function initialize(theForm)
    {
      theForm.txt_password.value = "";
      if ( parseInt(navigator.appVersion) >= 4 ) {
        browserVersion = 4;
      }
      else {
        window.location = "/gsa/bad_browser.htm";
        return;
      }
    }

function clearPassword(theForm)
    {
     theForm.txt_password.value = theForm.txt_passwd.value;
     theForm.txt_passwd.value = "";
     return true;
    }

function formTest(theForm)
    {
     var ErrMsg_Header = "\nThe request you have submitted has missing or invalid information.\n";
         ErrMsg_Header += "Please return to the request screen and correct the following...\n \n";

     var errormessage  = ErrMsg_Header;

     if ((theForm.txt_username.value == "")&&
         (theForm.txt_passwd.value == ""))
         {
          errormessage += "Please enter a User ID and Password \n";
         }

     if ((theForm.txt_username.value != "")&&
        ((checkAlphaNumericPlus(theForm,theForm.txt_username)=="error")))
         {
          errormessage += "Please enter a Valid User ID\n";
         }

     if ((theForm.txt_username.value != "")&&
         (theForm.txt_passwd.value == ""))
         {
          errormessage += "Please enter a Password\n";
         }

     if ((theForm.txt_username.value == "")&&
         (theForm.txt_passwd.value != ""))
         {
          errormessage += "Please enter a User ID\n";
         }

     if (errormessage != ErrMsg_Header)
         {
          alert(errormessage);
          return(false);
         }
     clearPassword(theForm);
		//window.location.href='frames.html'
     return (true);
    }

function checkAlphaNumericPlus(theForm,theField)
    {
     var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789',#/-.&(): _";
     var checkStr = theField.value;
     var allValid = true;

     for (i = 0;  i < checkStr.length;  i++)
         {
          ch = checkStr.charAt(i);
          for (j = 0;  j < checkOK.length;  j++)
              if (ch == checkOK.charAt(j))
                  break;
              if (j == checkOK.length)
                  {
                   allValid = false;
                   break;
                  }
         }

     if (!allValid)
         {
          return ("error");
         }
     return ("ok");
    }

// Stop hiding -->

</SCRIPT>

<!---------------------------------------------------------------------->

<BODY onLoad = "initialize(document.forms[0]);">
<CENTER>
<TABLE>
    <TR>
      <TD ALIGN="Center">
        <H2><FONT COLOR="Blue" FACE="Arial, serif, Courier New">EAGLS System Logon</FONT></H2>
      </TD>
    </TR>
</TABLE>
<BR>

<!---------------------------------------------------------------------->

<IMG SRC="images/line.gif" WIDTH="575" HEIGHT="6">

<!---------------------------------------------------------------------->

<p><FONT SIZE=4 COLOR="Black" FACE="Arial,Helvetica">Please Enter Your User ID and Password</FONT> </p>

<html:errors/>
<html:form
      action="/logon"
      onsubmit="return formTest(document.forms[0])">

<!---------------------------------------------------------------------->


<FONT FACE="Arial,serif, Courier New" COLOR="#000000">
<TABLE>
    <TR>
      <TD>User ID</TD><!--nnnbb-->
      <TD><INPUT TYPE="Text" SIZE="21" NAME="txt_username" MAXLENGTH="8" VALUE=""></TD>
    </TR>
    <TR>
      <TD>Password</TD>
      <TD><INPUT TYPE="Password" SIZE="21" NAME="txt_passwd" MAXLENGTH="20" VALUE=""></TD>
    </TR>
</TABLE> </FONT>
<BR>


<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_submitButton" VALUE="Submit">&nbsp;&nbsp;

        <INPUT TYPE="Reset" NAME="but_resetButton" VALUE="&nbsp;Clear&nbsp;" >
      </TD>
    </TR>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
		<FONT SIZE=2><B>As Of :  05/01/2003</B></FONT>
	  </TD>
	</TR>

</TABLE>
<BR>
<BR>

<!---------------------------------------------------------------------->

<IMG SRC="images/line.gif" WIDTH="575" HEIGHT="6">

<!---------------------------------------------------------------------->
<TABLE ALIGN="CENTER" WIDTH="90%">
  <TR>
    <TD>

      <H6 class="copyright">
       <FONT>This system contains privileged and confidential information intended only for
       the use of the authorized user. This system contains collected information
       and/or other account
       information that is part of the system of records:
       Travel Charge Card Program: GSA/GOVT - 3 and therefore
       is subject to the Privacy Act of 1974 (5 U.S.C. 552a) and
       Applicable agency regulations. In the event of a violation
       of the Privacy Act, a civil action may be brought, and violations
       of the Privacy Act may involve the imposition of criminal penalties.</FONT>
     </H6>
   </TD>
 </TR>
 <BR>
 <TR>
   <TD ALIGN="CENTER">
     <H6 class="copyright">
       Copyright © 1999 Bank of America, NA (USA). All rights reserved.
     </H6>
   </TD>
</TR>
</TABLE>
</CENTER>

<INPUT TYPE="hidden" NAME="txt_password" VALUE="">

</html:form>
</BODY>
</html:html>

