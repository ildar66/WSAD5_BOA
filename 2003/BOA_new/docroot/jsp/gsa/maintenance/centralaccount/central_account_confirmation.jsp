<%@ page import="com.boa.eagls.government.controller.action.inquiry.PointOfContactActions,
                 com.boa.eagls.government.controller.action.inquiry.PointOfContactParams"%>
<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/boa/app" prefix="eagls" %>
<HTML>
<HEAD>

<TITLE>Central Account Maintenance Confirmation</TITLE>

<META NAME="Description"    CONTENT="Confirms a Central Account maintenance entry">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

</HEAD>

<!-------------------------------------------------------------------------->

<BODY>
<html:form name="frm_centralAccountMaintenanceForm" action="/maintenance/centralAccount/centralAccountConfirmation"
 method="POST" type="com.boa.eagls.government.controller.formbean.maintenance.centralaccount.CentralAccountForm"
onsubmit="return formTest(document.frm_centralAccountMaintenanceForm);">


<%--<FORM NAME="frm_centralAccountMaintConfirmation"--%>
<%--      METHOD="POST"--%>
<%--      TARGET="content"--%>
<%--      ACTION="/cgi-bin/gx.cgi/GUIDGX-{60f0d2e2-2aca-11d2-92d2-0000f6aacd5a}"--%>
<%--      onsubmit="return formTest(document.frm_centralAccountMaintConfirmation)">--%>

<table WIDTH="575">
    <tr>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <td ALIGN="Center">
        <h2 class="titleText" >
          Central Account Maintenance<br>

          Confirmation
        </h2>
      </td>
    </tr>
</table>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<br>
<br>
<!-------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        You have successfully submitted Central Account Maintenance information for<BR>
          <B><bean:write name="frm_centralAccountMaintenanceForm" property="txt_centralAccountName"/></B>
      </TD>
    </TR>
</TABLE>
<BR>

<!-------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        Please choose another maintenance option to continue Central Account Maintenance
      </TD>
    </TR>
</TABLE>
<BR>

<!-------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <!-- THESE TWO LINKS ARE SHOWN ALL THE TIME -->
<%--        <GX type=replace id=link.AuthControlHyperlink value=AuthControlHyperlink>--%>
          <A HREF="<%=request.getContextPath()%>/notImplemented.do">Authorization Controls</A>&nbsp;&nbsp;&nbsp;
<%--        </GX>--%>
<%--        <GX type=replace id=link.POCHyperlink value=POCHyperlink>--%>
          <A HREF="<%=request.getContextPath()%>/notImplemented.do">Point of Contact</A>&nbsp;&nbsp;&nbsp;
<%--        </GX>--%>

        <!-- THESE FOUR LINKS ARE SHOWN ONLY IF THE ACCOUNT IS A CENTRAL (not diversion) ACCOUNT -->
<%--        <GX TYPE=cell id=showLinks>--%>
<%--             <GX TYPE=replace id=link.ECHyperlink value=ECHyperlink>--%>
               <A HREF="<%=request.getContextPath()%>/notImplemented.do">EC/EDI Office Maintenance</A>&nbsp;&nbsp;&nbsp;
<%--             </GX>--%>
             <BR>
<%--             <GX type=replace id=link.DBOHyperlink value=DBOHyperlink>--%>
               <A HREF="<%=request.getContextPath()%>/notImplemented.do">DBO Maintenance</A>&nbsp;&nbsp;&nbsp;
<%--             </GX>--%>
<%--             <GX type=replace id=link.POHyperlink value=POHyperlink>--%>
               <A HREF="<%=request.getContextPath()%>/notImplemented.do">PO Maintenance</A>&nbsp;&nbsp;&nbsp;
<%--             </GX>--%>
<%--             <GX type=replace id=link.TDOHyperlink value=TDOHyperlink>--%>
               <A HREF="<%=request.getContextPath()%>/notImplemented.do">TDO Maintenance</A>&nbsp;&nbsp;&nbsp;
<%--             </GX>--%>
<%--        </GX>--%>


      </TD>
    </TR>
</TABLE>
<BR>

<!-------------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Button" NAME="but_Ok" VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;"
               onClick="location.href='<%=request.getContextPath()%>/jsp/gsa/centralaccount/search.jsp?searchType=centralAccountMaintenance'">
      </TD>
    </TR>
</TABLE>
<BR>

<!-------------------------------------------------------------------------->

<%@ include file = "/jsp/gsa/footer_systemDefault.jsp"%>

</html:form>
</BODY>
</HTML>
