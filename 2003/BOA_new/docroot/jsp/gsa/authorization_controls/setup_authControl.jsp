<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<HEAD>

<TITLE>Authorization Controls Setup</TITLE> 

<META NAME="Name" CONTENT="Authorization Controls Navigation Setup Screen">
<META NAME="Description" CONTENT="Auth Control Navigation Setup Screen">
<META NAME="Author" CONTENT="Todd Bernard(IldarS)">
<META NAME="Created" CONTENT="07/21/1998">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

</HEAD>

<!---------------------------------------------------------------------------------->

<BODY>
<html:errors />
<html:form name="frm_authControls" type="com.boa.eagls.government.controller.formbean.authorizationcontrols.Frm_authControls"
        action="/decideAuthorizeControlSetup" scope="session" target="content" >
<!--   
<FORM NAME="frm_authControls"
      ACTION="/cgi-bin/gx.cgi/GUIDGX-{95F59750-2237-11D2-92CE-0000F6AACD5A}"
      METHOD="POST"
      TARGET="content">
-->
<!--<GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX><BR> -->

<!---------------------------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, helvetica">Account Information</FONT></B>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213" ALIGN="Left" >
        Account Name
      </TH>
      <TD WIDTH="352">
<!--    <GX type=cell id=accountInfo.accountName></GX>&nbsp; -->
        <bean:write name="frm_authControls" property="accountInfo.accountName"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >Account Number</TH>
      <TD>
<!--    <GX type=cell id=accountInfo.accountNumber></GX>&nbsp; -->
        <bean:write name="frm_authControls" property="accountInfo.accountNumber"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >Agency Name</TH>
      <TD>
<!--    <GX type=cell id=accountInfo.agencyName></GX>&nbsp; -->
        <bean:write name="frm_authControls" property="accountInfo.agencyName"/>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >
        Central Account ID
      </TH>
      <TD>
<!--    <GX type=cell id=accountInfo.centralId></GX>&nbsp; -->
        <bean:write name="frm_authControls" property="accountInfo.centralId"/>&nbsp;  
      </TD>
    </TR>
    <TR>
      <TH ALIGN="Left" >Credit Limit
    </TH>
      <TD>
<!--    <GX type=cell id="accountInfo.creditLimit, format($#,###.00)"></GX>&nbsp; -->
        <bean:write name="frm_authControls" format="$#,###.00" property="accountInfo.creditLimit"/>&nbsp;
 <%--       <%=java.text.NumberFormat.getCurrencyInstance().format()%>     --%>  
      </TD>
    </TR>
</TABLE>
<BR>

<!---------------------------------------------------------------------------------->
<!--
<INPUT TYPE="Hidden" NAME="acctControl" value="<GX type=cell id=acctControl></GX>">
<INPUT TYPE="Hidden" NAME="mccgControl" value="<GX type=cell id=mccgControl></GX>">
<INPUT TYPE="Hidden" NAME="auth_accountNumber" value="<GX type=cell id=accountInfo.accountNumber></GX>">
<INPUT TYPE="Hidden" NAME="auth_accountName" value="<GX type=cell id=accountInfo.accountName></GX>">
<INPUT TYPE="Hidden" NAME="auth_agencyName" value="<GX type=cell id=accountInfo.agencyName></GX>">
<INPUT TYPE="Hidden" NAME="auth_centralId" value="<GX type=cell id=accountInfo.centralId></GX>">
<INPUT TYPE="Hidden" NAME="auth_creditLimit" value="<GX type=cell id=accountInfo.creditLimit></GX>">
<INPUT TYPE="Hidden" NAME="auth_guid" value="<GX type=cell id=accountInfo.guid></GX>">
-->
<html:hidden name="frm_authControls" property="acctControl" />
<html:hidden name="frm_authControls" property="mccgControl" />
<html:hidden name="frm_authControls" property="accountInfo.accountNumber" />
<html:hidden name="frm_authControls" property="accountInfo.accountName" />
<html:hidden name="frm_authControls" property="accountInfo.agencyName" />
<html:hidden name="frm_authControls" property="accountInfo.centralId" />
<html:hidden name="frm_authControls" property="accountInfo.creditLimit" />
<html:hidden name="frm_authControls" property="accountInfo.guid" />

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <html:submit property="but_submitButton" value="Add Account Controls"></html:submit>&nbsp;&nbsp; 

        <html:submit property="but_submitButton" value="Add MCCG Controls"></html:submit>&nbsp;&nbsp; 

        <html:submit property="but_submitButton" value="Finish"></html:submit>&nbsp;&nbsp; 
        
        <html:button property="but_cancelButton" value="Cancel" onclick="history.go(-1);"></html:button>
      </TD>
    </TR>
</TABLE>

<!---------------------------------------------------------------------------------->

<!--type=include id="/gsa/common/footer_systemDefault.html">Insert Footer Here</GX> -->

</html:form>
</BODY>
</html>
