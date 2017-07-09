<%@ page language="java" import="org.apache.struts.action.DynaActionForm,java.util.*"%>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>
<app:checkRights functionName="<%=com.boa.eagls.government.util.Constants.FUNC_U_CREATE_USER_PROFILE%>"/>
<%
java.util.Hashtable map = (java.util.Hashtable)request.getAttribute("map");
%>


<html:html locale="true">
<HEAD>
<TITLE>User Profile Setup - Verification</TITLE>
<META NAME = "Name" CONTENT = "User Profile Verification">
<META NAME = "Description" CONTENT = "gets confirmation from the User ">
<SCRIPT LANGUAGE = "JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

function form_Test(theForm)
 {
  if(theForm.hdn_temp.value==1)
   {
    if(confirm('Are you sure you want to abort the setup of this User Profile?'))
     {
      theForm.hdn_temp.value=0;
      return(true);
     }
    else
     {
      theForm.hdn_temp.value=0;
      return(false);
     }
   }
  else
    return(true);
 }
  function setBtnVal(val){
//    alert("set Btn val "+val);
    document.forms[0].btnSubmitVal.value = val;
//    alert("Btn val "+document.UserProfileForm.btnSubmitVal.value);
  }

</SCRIPT>
<!----------------------------------------------------------------->
</HEAD>
<BODY>
<html:errors/>

<html:form action="/finalPage" onsubmit="return form_Test(document.forms[0]);">
<html:hidden property="step" value="Step3Confirmation"/>
<html:hidden property="btnSubmitVal" value="end"/>
<!----------------------------------------------------------------->

<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING, "heading.userprofile.setup");%>
<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.role.information");%>
<%@ include file="/jsp/gsa/header_systemDefault.jsp"%>

<br><br><br>
<CENTER>
The following User Profile information was entered for
<B>

<%--<%= map.get("txt_firstName") +" "+ map.get("txt_lastName")%> --%>
<bean:write name="UserProfileBean" property="txt_firstName" /> <bean:write name="UserProfileBean" property="txt_lastName" />
</B>
</CENTER>
<BR>

 <TABLE>
  <TR>
    <TD><B>Name:</B></TD>
    <TD>
    <bean:write name="UserProfileBean" property="txt_firstName" /> <bean:write name="UserProfileBean" property="txt_lastName" />
</TD>
  </TR>
  <TR>
    <TD><B>User ID:</B></TD>
    <TD>

   <bean:write name="UserProfileBean" property="txt_userId" />
</TD>
  </TR>



<TR>
  <TD><B>Reports Access: </B></TD>
  <TD>
       <bean:write name="UserProfileBean" property="txt_reportsAccess" />
  </TD>
  </TR>

  </TABLE>

 <font size="2">&nbsp;&nbsp;<I>Please note that Reports Access is not granted to User IDs with only Account Holder Roles</I></font>
<BR>

<BR>


<table border=1 width="575">
<%
Vector verificationPage = (Vector)request.getAttribute("verificationPage");

request.removeAttribute("verificationPage");
if(verificationPage!=null)
for (int i = 0; i < verificationPage.size(); i++) {
      java.util.Vector results = (java.util.Vector) verificationPage.get(i);
      java.util.Iterator resultIterator = results.iterator();
      while (resultIterator.hasNext()) {
        Object o = resultIterator.next();
        if (o instanceof String) {

%>
           <TR>
            <TD colspan="3"><B> Role Name </B></TD>
            <TD> <%=o%> </TD>
           </TR>
<%
        }
        else
        if (o instanceof Vector) {

          java.util.Vector roleAttributeType = (java.util.Vector) o;
          java.util.Iterator roleAttributeTypeIterator = roleAttributeType.iterator();
          while (roleAttributeTypeIterator.hasNext()) {
            Object oo = roleAttributeTypeIterator.next();

            if (oo instanceof String) {

%>
	   <TR>
             <TD colspan="3"><B> Role Attribute(s) </B></TD>
             <TD> <%=oo%> </TD>
          </TR>

<%
            }
            else
            if (oo instanceof java.util.Vector) {

              Vector roleAttribute = (java.util.Vector) oo;

              Iterator roleAttributeIterator = roleAttribute.iterator();
              while (roleAttributeIterator.hasNext()) {
                Object ooo = roleAttributeIterator.next();

                if(ooo instanceof String)
                {
		String testing = (String)roleAttributeIterator.next();
%>
      <TR>
          <TD WIDTH = 10>&nbsp;</TD>
          <TD COLSPAN=2><%=ooo%></TD>
           <TD><%=testing%></TD>
        </TR>

<%
                }else
                if (ooo instanceof Vector) {
                  Vector subAttribute = (Vector) ooo;
                  Iterator subAttributeIterator = subAttribute.iterator();

                  while (subAttributeIterator.hasNext()) {
                    Object oooo = subAttributeIterator.next();

                    if (oooo instanceof String) {
			String testing2= (String)subAttributeIterator.next();

%>

	    	 <tr><TD WIDTH ="10">&nbsp;</TD>
		     <TD WIDTH=10>&nbsp;</TD>
		     <TD><%=oooo%></TD>
		 <TD><%=testing2%></TD>
		</tr>

<%
                    }
                    else {

                    }
                  }
                }
              }
            }
          }
        }
      }
    }
%>


<TABLE border="1" width="575">


</TABLE>


<!----------------------------------------------------------------->
<BR>
<TABLE WIDTH = "575">
 <TR>
  <TD ALIGN = "CENTER">
<html:submit property="but_submitButton" value="Add Role"
        onclick="setBtnVal('more'); document.forms[0].hdn_temp.value=0;" />
<html:submit property="but_submitButton" value="Finished"
	onclick="setBtnVal('end'); document.forms[0].hdn_temp.value=0;" />
<html:submit property="but_submitButton" value="Cancel"
	onclick="setBtnVal('cancel'); document.forms[0].hdn_temp.value=1;" />
  </TD>
 </TR>
</TABLE>
<br>
<br>


<INPUT TYPE="HIDDEN" NAME="hdn_temp" VALUE=0>
<!----------------------------------------------------------------->

<%--
<input type="hidden" name="txt_firstName" value="<%=map.get("txt_firstName")%>">
<input type="hidden" name="txt_lastName" value="<%=map.get("txt_lastName")%>">
<input type="hidden" name="txt_userId" value="<%=map.get("txt_userId")%>">
<input type="hidden" name="status" value="<%=map.get("status")%>">
<input type="hidden" name="txt_reportsAccess" value="<%=map.get("txt_reportsAccess")%>">
<input type="hidden" name="currentRole" value="<%=map.get("currentRole")%>">
<input type="hidden" name="baseRole" value="<%=map.get("baseRole")%>">
<input type="hidden" name="prevRoles" value="<%=map.get("prevRoles")%>">
<input type="hidden" name="tempHierString" value="<%=request.getAttribute("HierarchyString")%>">
--%>

<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>
</html:form>
</BODY>
</html:html>
