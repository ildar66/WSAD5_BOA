<%@ page import="java.util.*" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>

<%
 Vector data = (Vector)request.getAttribute("rolesData");
 if(data==null) data = new Vector();
%>


<html:html>
<HEAD>
<html:base/>
<TITLE>Please Select a User Role</TITLE>

<SCRIPT LANGUAGE="JavaScript">
<!-- Start hiding from other browsers
isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}


function Initialize(theForm)
    {
     if (theForm.availableRoleList.length > 0)
         {
          theForm.availableRoleList.options[0].selected=true;
         }
    }

function assignvalue(theForm)
    {
     window.opener.selectRole(theForm.availableRoleList.options[theForm.availableRoleList.selectedIndex].value);
     window.close();
    }

// Stop hiding -->
</SCRIPT>

</HEAD>

<!--------------------------------------------------------------------->

<BODY onload="Initialize(document.frm_browseRoles)">

<FORM NAME="frm_browseRoles"
      ACTION="">

<!--------------------------------------------------------------------->

<CENTER>
<IMG SRC="<%= request.getContextPath() %>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6"><BR><BR>

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Available Roles</FONT></B><BR><BR>

<TABLE>
    <TR>
      <TD>
        <SELECT NAME="availableRoleList" SIZE="10">

	<% for(int i=0; i<data.size(); i++ )
	{ %>
               <OPTION VALUE="<%=data.elementAt(i)%>"><%=data.elementAt(i)%></OPTION>

	<% } %>

       </SELECT>
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD>
        <INPUT TYPE="Button" NAME="but_selectButton" VALUE="Select"
               onclick="assignvalue(document.frm_browseRoles)">

        <INPUT TYPE="Button" NAME="but_cancelButton" value="Cancel"
               onclick="window.close()">
      </TD>
    </TR>
</TABLE>
<BR>

<!--------------------------------------------------------------------->

<IMG SRC="<%= request.getContextPath() %>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">
</CENTER>

</FORM>
</BODY>
</html:html>
