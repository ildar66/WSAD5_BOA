<%@ page language="java" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<html:html>
<HEAD>

<TITLE>Large Results Set Warning</TITLE>

<META NAME="Description" CONTENT="Large Result Set warning">
<META NAME="File Name" CONTENT="warning_largeResultsSet.html">

<script LANGUAGE="JavaScript">
isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}
</script>
</HEAD>

<!------------------------------------------------------------------------------>

<BODY>

<FORM action="<%=request.getContextPath()%>/createUserProfile.do">

<!------------------------------------------------------------------------------>
<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Information<BR>
          Large Search Results
        </H2>
      </TD>
    </TR>
</TABLE>
<!------------------------------------------------------------------------------>
<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Information">

<IMG SRC="<%= request.getContextPath() %>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">
<BR>
<BR>

<!------------------------------------------------------------------------------>
<BR>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        This search will return <b>
          <bean:write scope="request" name="resultCount"/></b>
          records and may take a considerable amount of time to download.<BR>
      </TD>
    </TR>
</TABLE>

<BR>

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="BUTTON" NAME="but_revise" VALUE="Revise Search"
         onClick="history.go(-1)">
		 &nbsp;&nbsp;
		<INPUT TYPE="SUBMIT" NAME="but_continue" VALUE="Continue">
      </TD>
    </TR>
</TABLE>
<BR>
<%@include file="/jsp/gsa/footer_systemDefault.jsp"%>

<input type="hidden" name= "txt_accountNumber" value='<bean:write name="bean" property="txt_accountNumber"/>' >
<input type="hidden" name= "txt_lastName" value='<bean:write name="bean" property="txt_lastName"/>' >
<input type="hidden" name= "txt_firstName" value='<bean:write name="bean" property="txt_firstName"/>' >
<input type="hidden" name= "txt_hierarchyDepth" value='<bean:write name="bean" property="txt_hierarchyDepth"/>' >
<input type="hidden" name= "programNumber" value='<bean:write name="bean" property="txt_hl0"/>' >
<input type="hidden" name= "txt_hl0" value='<bean:write name="bean" property="txt_hl0"/>' >
<input type="hidden" name= "txt_hl1" value='<bean:write name="bean" property="txt_hl1"/>' >
<input type="hidden" name= "txt_hl2" value='<bean:write name="bean" property="txt_hl2"/>' >
<input type="hidden" name= "txt_hl3" value='<bean:write name="bean" property="txt_hl3"/>' >
<input type="hidden" name= "txt_hl4" value='<bean:write name="bean" property="txt_hl4"/>' >
<input type="hidden" name= "txt_hl5" value='<bean:write name="bean" property="txt_hl5"/>' >
<input type="hidden" name= "txt_hl6" value='<bean:write name="bean" property="txt_hl6"/>' >
<input type="hidden" name= "txt_hl7" value='<bean:write name="bean" property="txt_hl7"/>' >
<input type="hidden" name= "txt_hl8" value='<bean:write name="bean" property="txt_hl8"/>' >

<input type="hidden" name="countFlag" value="false">


</FORM>
</BODY>
</html:html>
