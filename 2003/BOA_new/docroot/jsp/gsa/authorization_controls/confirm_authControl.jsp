<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eagls" %>

<HTML>
<HEAD>

<TITLE>Authorization Controls Setup: Confirmation</TITLE>

<META NAME="Name"
	CONTENT="Authorization Controls SetUp Confirmation Screen">
<META NAME="Description"
	CONTENT="Confirms Authorizations Control Setup Data">
<META NAME="Author" CONTENT="Todd Bernard(IldarS)">
<META NAME="Created" CONTENT="07/22/1998">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

</HEAD>

<!------------------------------------------------------------------->

<BODY>
<html:form name="frm_confirmAuthControls"
	type="com.boa.eagls.government.controller.formbean.authorizationcontrols.Frm_confirmAuthControls"
	scope="request"
	action="notImplemented" method="POST" target="content">

	<!--
<FORM NAME="frm_confirmAuthControls"
      METHOD="POST"
      TARGET="content"
      ACTION= "<GX type=cell id=homeGuid></GX>">

<GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX>
-->
	<!------------------------------------------------------------------->

	<BR>

	<TABLE>
		<TR>
			<TD WIDTH="575" ALIGN="Center">You have successfully setup the
			Authorization Controls for<BR>
<!--		<B> <GX type=replace id=accountInfo.confirmAuthControlsName	value=confirmAuthControlsName>confirmAuthControlsName</GX>	</B>.</TD> -->
			<B> <bean:write name="frm_confirmAuthControls" property="accountInfo.accountName"/> </B>.</TD>
		</TR>
	</TABLE>
	<BR>

	<!-------------------------------------------------------------------------->
	<!--------
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <GX type=replace id=AuthControlSetupHyperlink value=AuthControlSetupHyperlink>
          <INPUT TYPE="button" NAME="but_submitButton"
                 VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;"
                 onClick="window.location.href='AuthControlSetupHyperlink'">
        </GX>
      </TD>
    </TR>
</TABLE>
----------->

	<TABLE>
		<TR>
			<TD WIDTH="575" ALIGN="Center">
			<GX type=replace id=AuthControlSetupHyperlink value=AuthControlSetupHyperlink> 
			   <INPUT TYPE="hidden" name="hyperlinksOnly" value="YES"> 
			   
			   <GX type=replace id=account.number value=accNumVal> 
			     <INPUT TYPE="hidden" name="hlAcctNo" value="accNumVal"> 
			   </GX>
			    
			   <GX type=replace id=account.number value=acNmVal> 
			     <INPUT TYPE="hidden" name="hdn_centralAccountNumber" value="acNmVal"> 
			   </GX> 
			   
			   <INPUT TYPE="submit" NAME="but_submitButton"	VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;"> 
			</GX>
			</TD>
		</TR>
	</TABLE>

	<!-------------------------------------------------------------------------->

	<!-- <GX type=include id="/gsa/common/footer_systemDefault.html">Insert Footer Here</GX> -->

</html:form>
</BODY>
</HTML>
