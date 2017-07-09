<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>

<%@ page import="java.util.Vector,
                 java.util.Hashtable"%>

<%! Vector currentMenu = null;
%>
<%
    if (null == session)
    {
        response.sendRedirect("systemLogon.jsp");
    }
    else
    {
        currentMenu = (Vector) session.getAttribute("currentMenu");
    }
%>

<HTML>

<HEAD>

<TITLE></TITLE>

<META NAME="Author" CONTENT="Brent Ramsby">
<META NAME="Description" CONTENT="Template controls placement of menu options">
<META NAME="Created" CONTENT="08/05/98">
<META NAME="Revised" CONTENT="?">


<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/jsp/scripts/reviseSearch.js"></SCRIPT>
<%--<SCRIPT LANGUAGE="JavaScript" src="<%=request.getContextPath()%>/jsp/scripts/menu.js"></SCRIPT>--%>
</HEAD>

<BODY BGCOLOR="FFFFFF">

<!-- START OUTER TABLE -->
<TABLE BORDER=0 CELLPADDING=0 CELLSPACING=0>

 <TR>

   <TD ALIGN="RIGHT" VALIGN="TOP">
      <!-- INNER CELL (LEFT) -->
      <html:img src="jsp/images/brandingLogo26x1400.gif" border="0"/>

   </TD>

   <TD ALIGN="RIGHT" VALIGN="TOP">
      <!-- INNER CELL (RIGHT) -->
<!--	  <FONT SIZE=2><B>As Of :  <GX type=replace id=menu.defDate value=defDate>defDate</GX></B></FONT> -->
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
                 <logic:present name="category" property="functions">
                     <bean:define id="functions" name="category" property="functions" type="java.util.Vector"/>
                     <logic:iterate id="function" name="functions">
                     <TR>
                        <TD>&nbsp;</TD>
                        <TD><A HREF="<bean:write name="function" property="hyperlink"/>" TARGET="content"
                        onClick="clearReviseSearchHierarchy();"><FONT SIZE=2><bean:write name="function" property="name"/></FONT></A></TD>
                    </TR>
                     </logic:iterate>
                 </logic:present>
            </logic:iterate>

            <!-- ADD BLANK ROW BETWEEN CATEGORIES -->

<!--           <GX type=cell id=userRole>

             <TR>

                 <TD COLSPAN=2>

                    <A HREF="<GX type=cell id=reports.seagateURL></GX>" TARGET="_top">

                    <FONT SIZE=2><B>Reporting Tool</B></FONT></A>

                 </TD>

              </TR>

            </GX>



			<GX type=cell id=reconUsers>

              <TR>

                 <TD COLSPAN=2>


                   <a HREF="<GX type=cell id=help.THDURL></GX>" target="_blank"><FONT SIZE=2><B>Help</B></FONT></a>

                 </TD>

              </TR>

			</GX>



			<GX type=cell id=reconUsers>

              <TR>

                 <TD COLSPAN=2>

                   <a HREF="<GX type=cell id=training.THDURL></GX>" target="_blank"><FONT SIZE=2><B>Training</B></FONT></a>

                 </TD>

              </TR>

			</GX>
    -->


              <TR>

                 <TD COLSPAN=2>

                   <A HREF="/Logoff.do" TARGET="content">

                   <FONT SIZE=2><B>Log Off</B></FONT></A>

                 </TD>

              </TR>


      </TABLE>  <!-- END EMBEDDED TABLE (RIGHT) -->

   </TD>

 </TR>

</TABLE> <!-- END OUTER TABLE -->

</BODY>

</HTML>

