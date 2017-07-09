<%@ page language="java" import="com.boa.eagls.government.util.*" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>
<app:checkRights functionName="<%=com.boa.eagls.government.util.Constants.FUNC_U_CREATE_USER_PROFILE%>"/>
<!--azraU-Create User Profile-->

<%
 String firstName = (String)request.getAttribute("firstName") ;
 String lastName = (String)request.getAttribute("lastName") ;
 if(firstName==null || firstName.equals("null")) firstName=request.getParameter("firstName") ;
 if(lastName==null || lastName.equals("null")) lastName= request.getParameter("lastName") ;
 if(firstName==null || firstName.equals("null")) firstName="" ;
 if(lastName==null || lastName.equals("null")) lastName= "" ;

%>

<html:html locale="true">
<HEAD>
<TITLE>User Profile Setup</TITLE>

<META NAME="Name" CONTENT="User Profile Setup">
<META NAME="Description" CONTENT="Set up initial parameters for a user profile">
<META NAME="HTMLname" CONTENT="setup_userProfile.html">
</HEAD>

<SCRIPT LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

function form_Test(theForm)
    {
     var errormessage = parent.logo.ErrMsg_Header;

     if ((theForm.txt_lastName.value == "") &&
         (theForm.txt_firstName.value != ""))
         {
          errormessage += parent.logo.ErrMsg_NameCombination;
         }

     if ((theForm.txt_lastName.value  ==  "")||
         (parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_lastName) ==  "error"))
         {
          errormessage += parent.logo.ErrMsg_LastName;
         }

     if ((theForm.txt_firstName.value != "")&&
         (parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_firstName) ==  "error"))
         {
          errormessage += parent.logo.ErrMsg_FirstName;
         }

     if ((checkValidUserId(theForm,theForm.txt_userId)=="error")||
         (theForm.txt_userId.value.length<5))
         {
          errormessage += parent.logo.ErrMsg_Text_UserId;
         }

     if ((checkBeginOfUserId(theForm.txt_userId.value.charAt(0))=="error"))
         {
          errormessage += "First Character of UserID Cannot Be Numeric\n";
         }

     if ((theForm.txt_role.value == "")||
         (checkAlphaNumericPlus(theForm,theForm.txt_role)=="error"))
         {
          errormessage += parent.logo.ErrMsg_UserRole;
         }

     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return (false);
         }
          return (true);
    }

function checkNumeric(theChar)
 {
   var checkOK = "0123456789";
   var checkStr = theChar;
   var allValid = true;

  for (i = 0;  i < checkStr.length;  i++)
       {
        ch = checkStr.charAt(i);
        for (j = 0;  j < checkOK.length;  j++)
          if (ch == checkOK.charAt(j))
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


function checkBeginOfUserId(theChar)
 {
   var checkOK = "0123456789_#";
   var checkStr = theChar;
   var allValid = true;

  for (i = 0;  i < checkStr.length;  i++)
       {
        ch = checkStr.charAt(i);
        for (j = 0;  j < checkOK.length;  j++)
          if (ch == checkOK.charAt(j))
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





function selectRole(selectedRoleName)
    {
     document.forms[0].txt_role.value=selectedRoleName;

    }

function openNewWindow(theUrl)
    {
     msg=window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=400,status=no,menubar=no");
    }

 <!--*******************  CHECK ALPHANUMERIC PLUS  ********************-->
 <!--* This function receives a form and field. It then checks to see *-->
 <!--* if the all the characters in the field are alphanumeric and    *-->
 <!--* some particular charctors only ** (including '_')              *-->
 <!--******************************************************************-->

function checkAlphaNumericPlus(theForm,theField)
    {
     var checkOK="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_',#/-.&(): ";
     var checkStr=theField.value;
     var allValid=true;

     for (i=0;  i < checkStr.length;  i++)
         {
          ch=checkStr.charAt(i);
          for (j=0;  j < checkOK.length;  j++)
            if (ch == checkOK.charAt(j))
              break;
          if (j == checkOK.length)
             {
              allValid=false;
              break;
             }
         }
     if (!allValid)
         {
          return ("error");
         }
     return ("ok");
    }

 <!--*******************  CHECK VALID USER ID  ********************-->
 <!--* This function receives a form and field. It then checks to see *-->
 <!--* if the all the characters in the field are alphanumeric and    *-->
 <!--* some particular charactors like  '_' and '#' )              *-->
 <!--******************************************************************-->



function checkValidUserId(theForm,theField)
    {
     var checkOK="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_#";
     var checkStr=theField.value;
     var allValid=true;

     for (i=0;  i < checkStr.length;  i++)
         {
          ch=checkStr.charAt(i);
          for (j=0;  j < checkOK.length;  j++)
            if (ch == checkOK.charAt(j))
              break;
          if (j == checkOK.length)
             {
              allValid=false;
              break;
             }
         }
     if (!allValid)
         {
          return ("error");
         }
     return ("ok");
    }

<!-- Added on 03/19/2003 -->
     <!--*******************  Changes Reports Access to "Yes"************-->
	 <!--* This function changes the Reports Access to Yes              *-->
	 <!--*  if the default role is "AOPC					            *-->
	 <!--****************************************************************-->


    function setReportsAccess(){

		if(document.frm_userProfile.txt_role.value == "A_OPC"){
			if(	document.frm_userProfile.txt_reportsAccess){
				for(var i = 0; i < document.frm_userProfile.txt_reportsAccess.length; i++){
					if( document.frm_userProfile.txt_reportsAccess[i].text == "Yes" ){
						document.frm_userProfile.txt_reportsAccess[i].selected=true;
						break;
					}
				}
			}
		}

	}

// Stop hiding-->
</SCRIPT>

<BODY>
<html:errors />
<html:form action="/addUserProfile" focus="txt_lastName" onsubmit="return form_Test(document.UserProfileForm);">
<html:hidden property="btnSubmitVal" value=""/>

<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING, "heading.userprofile.setup");%>
<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.information");%>
<%@ include file="/jsp/gsa/header_systemDefault.jsp"%>

<!------------------------------------------------------------------->

<TABLE width="669">
    <TR>
      <TD width="121"><b>Last Name</b></TD>
      <TD width="534">
       <%-- <html:text property="txt_firstName" size="27" /> --%>
	<input type="text" name="txt_lastName" size="27" value="<%=lastName%>" maxlength="26" >
      </TD>
    </TR>
    <TR>
      <TD width="121">First Name</TD>
      <TD width="534">

    <%-- <html:text property="txt_lastName" size="27" /> --%>
    <input type="text" name="txt_firstName" size="27" value="<%=firstName%>" maxlength="26">
      </TD>
    </TR>
    <TR>
      <TD width="121"><B>User ID</B></TD>
      <TD width="534">

 <html:text property="txt_userId" size="9" maxlength="8" />
      </TD>
    </TR>
    <TR>
      <TD width="121"><B>User Status</B></TD>
      <TD width="534">
    <SELECT  NAME = "status"  size="1" onclick="window.status='Select the User Status';" onfocus="window.status='Select the User Status';">
      <OPTION VALUE ="Active" SELECTED>Active</OPTION>
      <OPTION VALUE ="Inactive" >InActive</OPTION>
    </SELECT>
      </TD>
    </TR>
    <TR>
      <TD width="121"><B>Default Role</B></TD>
      <TD width="534">

  <input name="txt_role" type="text" SIZE="26" MAXLENGTH="25" onFocus="window.status ='Select the Role'">
  <input type="button" value="Browse" name="B3"
                 onClick="javascript:openNewWindow('<%=request.getContextPath()%>/browseRoles.do');">

  </TD>
  </TR>
  <TR>
    <TD><B>Reports Access</B></TD>
      <TD width="534">
   <SELECT  NAME = "txt_reportsAccess"  onclick="window.status='Select Reports Access';"  onfocus="window.status='Select Reports Access';" size=1>
     <OPTION VALUE ="No" >No</OPTION>
      <OPTION VALUE ="Yes" SELECTED >Yes</OPTION>

    </SELECT>
   &nbsp;
      </TD>
    </TR>
      <TR>
    <TD width="661" colspan="2"><font size="2">
    <i>Please note that Reports Access is not granted to User IDs with only Account Holder Roles</i>
	</font>
    </TD>
    </TR>

</TABLE>
<!-------------------------------------------------------------------------->
<BR>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <html:submit property="but_submitButton" value="Continue" />
      </TD>
    </TR>
</TABLE>
<br>
<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>
</html:form>

</BODY>
</html:html>