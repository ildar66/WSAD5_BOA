
<%@ page language="java" import="com.boa.eagls.government.util.LookUp" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>
<app:checkRights functionName="<%=com.boa.eagls.government.util.Constants.FUNC_U_CREATE_USER_PROFILE%>"/>
<html:html>

<HEAD>
<TITLE>User Profile - Add Role</TITLE>

<META NAME = "Name" CONTENT = "User Profile - Add Role">
<META NAME = "Description" CONTENT = "Allows a user to a role to a user profile">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>

<SCRIPT LANGUAGE="JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}



<!-- starting hiding


//FUNCTIONS

    function Initialize(theForm)
	  {
	   //INSERT INITIALIZATIONS HERE
	  }


	function form_Test(theForm)
	{
    var errormessage = parent.logo.ErrMsg_Header;

         if  (theForm.txt_role.value == "")
		 	 {
			  errormessage += "Enter the Value for Role";
			 }

	    if (errormessage != parent.logo.ErrMsg_Header)
	      	{
	      	 alert(errormessage);
	         return (false);
	     	}

	   		return (true);
	}

function openNewWindow(theUrl)
  {
   msg = window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=400,status=no,menubar=no");
  }

function selectRole(theRoleName)
  {
   document.forms[0].txt_role.value = "";
   document.forms[0].txt_role.value = theRoleName;
  }


//-->
</SCRIPT>

<!--------------------------------------------------------------------->

<BODY onload = "Initialize(document.frm_addHierarchyUP)">

<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING, "heading.userprofile.setup");%>
<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.role.add");%>
<%@ include file="/jsp/gsa/header_systemDefault.jsp"%>


<!--------------------------------------------------------------------->

<html:form action = "/userProfileRolesAction"
	  target= "content"
	  onsubmit = "return form_Test(document.forms[0])">

<TABLE>
  <TR>
    <TD> <B><FONT COLOR = "#0000FF" FACE = "Arial, HELVETICA">Role<BR> </FONT></B>
    </TD>
  </TR>
</TABLE>

<TABLE BORDER = 0>
  <TR>
    <TD><B>Name:</B></TD>
    <TD><bean:write name="UserProfileBean" property="txt_firstName" /> <bean:write name="UserProfileBean" property="txt_lastName" />
    </TD>
  </TR>
  <TR>
    <TD><B>User ID:</B></TD>
    <TD><bean:write name="UserProfileBean" property="txt_userId" /></TD>
  </TR>
  <TR>
  <TR>
    <TD><B>New Role</B></TD>
    <TD><INPUT TYPE = "TEXT" NAME = "txt_role" SIZE="20"
		       onfocus = "window.status = 'Enter the Role Name';"
			   onclick = "window.status = 'Enter the Role Name';">
		<INPUT TYPE = "BUTTON" NAME = "but_browseRoles" VALUE = "Browse"
		       onclick = "openNewWindow('<%=request.getContextPath()%>/browseRoles.do')">
    </TD>
  </TR>
</TABLE>

<CENTER>

<P ALIGN = "CENTER">
<INPUT TYPE = "SUBMIT" NAME = "but_submitButton" VALUE = "Continue">
</P>

</CENTER>

<!-- **** HIDDEN VARIABLES **** -->

<%--<%@ include file="/jsp/gsa/setup/user_profile/object_userProfileHiddenFields.jsp"%>--%>

<!-- **** END HIDDEN VARIABLES **** -->
</html:form>

<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>

</BODY>
</html:html>
