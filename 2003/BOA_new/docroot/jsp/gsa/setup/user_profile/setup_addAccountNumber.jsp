<%@ page language="java" import="org.apache.struts.action.DynaActionForm" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>
<app:checkRights functionName="<%=com.boa.eagls.government.util.Constants.FUNC_U_CREATE_USER_PROFILE%>"/>
<%
java.util.Hashtable map = (java.util.Hashtable)request.getAttribute("map");
%>

<html:html locale="true">
<HEAD>
<TITLE>User Profile - Add Account Number</TITLE>
<!------------------------JAVA SCRIPTS STARTS------------------------->
<SCRIPT LANGUAGE="JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}




//INITIALIZATION

	function Initialize(theForm)
  	{
   	 //INSERT INITIALIZATIONS HERE
  	}

//FUNCTIONS

	function form_Test(theForm)
	{
    var errormessage = parent.logo.ErrMsg_Header;

//       if (theForm.hdn_bt1.value==1)
//       {
         if ((parent.logo.checkNumeric(theForm,theForm.txt_accountNo)!= "ok")||
			 (parent.logo.checkLength(theForm,theForm.txt_accountNo,16)!= "ok")||
			 (theForm.txt_accountNo.value == ""))
			{
              errormessage += parent.logo.ErrMsg_AccountNumber;
			}
//	   }



	    if (errormessage !=parent.logo.ErrMsg_Header)
	      	{
	      	 alert(errormessage);
	         return (false);
	     	}

	   		return (true);
	}



</SCRIPT>
</head>
<!------------------------JAVA SCRIPTS ENDS------------------------->
<BODY onload="document.forms[0].txt_accountNo.value=''">

<!------------------------FORM STARTS------------------------------->
<html:form action="/addAccount" focus="txt_accountNo" onsubmit="return form_Test(document.forms[0]);" >
<!-- onsubmit="return form_Test(document.UserProfileForm);"-->
<%--<html:hidden property="step" value="Step2AccountNumber"/>--%>
<%--<html:hidden property="btnSubmitVal" value="end"/>--%>

<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING, "heading.userprofile.setup");%>
<%--<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.information");%>--%>
<%@ include file="/jsp/gsa/header_systemDefault.jsp"%>
<BR>
<TABLE>
  <TR>
    <TD><B><FONT COLOR = "#0000FF" FACE = "ARIAL, HELVETICA">Account Number<BR> </FONT></B>
    </TD>
  </TR>
</TABLE>

<TABLE width="354">
  <TR>
    <TD width="112"><B>Name:</B></TD>
    <TD width="228">
  <bean:write scope="session" name="UserProfileBean" property="txt_firstName" /> <bean:write name="UserProfileBean" scope="session" property="txt_lastName" />
</TD>
  </TR>
  <TR>
    <TD width="112"><B>User ID:</B></TD>
    <TD width="228">
<bean:write name="UserProfileBean" property="txt_userId" />
    </TD>
  </TR>
  <TR>
    <TD width="112"><B>Role:</B></TD>
    <TD width="228">
<bean:write name="UserProfileBean" property="txt_role" />
</TD>
  </TR>
  <TR>
    <TD width="112"><B>Account Number</B></TD>
    <TD width="228">
 <html:text property="txt_accountNo" size="19" maxlength="16" onfocus ="window.status = 'Enter Account Number';"
               onclick="window.status = 'Enter Account Number';" />
</TD>
  </TR>
</TABLE>
<CENTER>
<P ALIGN = "CENTER">
<html:submit property="but_submitButton" value="End Role"/>
<html:submit property="but_submitButton" value="Add Account Number"/>
</P>
</CENTER>
<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>
<%--
<input type="hidden" name="txt_firstName" value=<%=map.get("txt_firstName")%> >
<input type="hidden" name="txt_lastName" value=<%=map.get("txt_lastName")%> >
<input type="hidden" name="txt_userId" value=<%=map.get("txt_userId")%> >
<input type="hidden" name="status" value=<%=map.get("status")%> >
<input type="hidden" name="txt_reportsAccess" value=<%=map.get("txt_reportsAccess")%> >
<input type="hidden" name="baseRole" value=<%=map.get("baseRole")%> >
<input type="hidden" name="prevRoles" value=<%=map.get("prevRoles")%> >
<input type="hidden" name="currentRole" value=<%=map.get("currentRole")%> >
<input type="hidden" name="hdn" >
<input type="hidden" name="hdn_user" value=<%=map.get("userId")%> >
--%>



</html:form>
<!------------------------FORM ENDS------------------------------->
</BODY>
</html:html>
