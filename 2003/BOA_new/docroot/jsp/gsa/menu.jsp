<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>

<%@ page import="java.util.Vector,
                 java.util.Hashtable,
                 java.sql.Date,
                 java.text.SimpleDateFormat"%>

<%! Vector currentMenu = null;
     String date ="";
%>
<%  com.boa.eagls.government.statemgmt.EAGLSSession eaglsSession = new com.boa.eagls.government.statemgmt.EAGLSSession();
    if (null == eaglsSession)
    {
        response.sendRedirect("systemLogon.jsp");
    }
    else
    {   date = eaglsSession.getFunctionsLastModifiedDate(request);
        currentMenu = (Vector) eaglsSession.getCurrentMenu(request);
/*
        for(short j=0; j < 1; j++)
        {
             System.out.println("Current Mneu "+(currentMenu.elementAt(j)).getClass().toString());
        }
*/
    }
%>

<HTML>
<HEAD>
<TITLE></TITLE>
<META NAME="Description" CONTENT="Template controls placement of menu options">


<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/jsp/gsa/scripts/reviseSearch.js"></SCRIPT>
<%--<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/jsp/gsa/scripts/menu.js"></SCRIPT>--%>
<SCRIPT LANGUAGE="JavaScript">
isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}
</SCRIPT>
</HEAD>

<BODY BGCOLOR="FFFFFF">

<!-- START OUTER TABLE -->
<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0>

 <TR>

   <TD ALIGN="RIGHT" VALIGN="TOP">
      <!-- INNER CELL (LEFT) -->
      <img src="<%=request.getContextPath()%>/jsp/gsa/images/brandingLogo26x1400.gif" border="0">

   </TD>

   <TD ALIGN="RIGHT" VALIGN="TOP">
<%--<%! java.sql.Date date =new java.sql.Date(new java.util.Date().getTime());
%>

    try{
    sdf = (new java.text.DateFormat()).parse(date.toString()).toString();
    }
    catch(java.text.ParseException e){
        System.out.println("Exception "+e);
    }


--%>
<b><font size="2" > As of : <%=date%></font>
</b>
      <TABLE BORDER=0>
            <!-- DISPLAY CATEGORY NAME WITH HYPERLINK -->

            <logic:iterate id="category" name="currentMenu">
                 <TR>
                    <TD COLSPAN=2>
                    <A HREF="<bean:write name="category" property="hyperlink"/>"
                    TARGET="<bean:write name="category" property="target"/>"
                    onClick="if ('<bean:write name="category" property="name"/>' == 'Account Inquiry/Maintenance') clearReviseSearchHierarchy();">
                    <FONT SIZE=2><B><bean:write name="category" property="name"/></B></FONT></A></TD>
                 </TR>
              <%--   <logic:present name="category" property="functions"> --%>
                 <%--    <bean:define id="function" name="category" property="functions" type="java.util.Vector"/> --%>
                     <logic:iterate id="function" name="category" property="functions">
                     <TR>
                        <TD>&nbsp;</TD>
                        <TD><A HREF="<bean:write name="function" property="hyperlink"/>" TARGET="content"
                        onClick="clearReviseSearchHierarchy();">
                        <FONT SIZE=2><bean:write name="function" property="name"/></FONT></A></TD>
                    </TR>
                     </logic:iterate>
                <%-- </logic:present> --%>
            </logic:iterate>
              <TR>

                 <TD COLSPAN=2>

                   <A HREF="#" onClick="alert('Not implemented yet')"><FONT SIZE=2><B>Help</B></FONT></A>

                 </TD>

              </TR>
              <TR>

                 <TD COLSPAN=2>

<%--                   <A HREF="#" onClick="alert('Not implemented yet')" TARGET="content">--%>
                   <A HREF="#" onClick="alert('Not implemented yet')"><FONT SIZE=2><B>Training</B></FONT></A>

                 </TD>

              </TR>


              <TR>

                 <TD COLSPAN=2>

                   <A HREF="<%=request.getContextPath()%>/Logoff.do" TARGET="content">

                   <FONT SIZE=2><B>Log Off</B></FONT></A>

                 </TD>

              </TR>

			<TR>
				<TD COLSPAN=2><A HREF="<%=request.getContextPath()%>/jsp/tmp/ildar/AccountSetupConfirmation.html"
					TARGET="content"> <FONT SIZE=2><B>Authorization Control Setup</B></FONT></A>
				</TD>
			</TR>




      </TABLE>  <!-- END EMBEDDED TABLE (RIGHT) -->

   </TD>

 </TR>

</TABLE> <!-- END OUTER TABLE -->

</BODY>

</HTML>

