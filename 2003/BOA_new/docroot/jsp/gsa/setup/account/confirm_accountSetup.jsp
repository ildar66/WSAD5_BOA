<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>


<html>
<head>

<title>Account Setup Confirmation</title>

<meta NAME="Name" CONTENT="Account Setup Confirmation">
<meta NAME="Description" CONTENT="Account Setup Confirmation">
<meta NAME="Author" CONTENT="Todd Shuler">
<meta NAME="Created" CONTENT="07/08/1998">
<meta NAME="Revised" CONTENT="09/23/1998 Edited -BB">
<meta NAME="Revised" CONTENT="09/23/1998 Edited -BB">
<meta NAME="Revised" CONTENT="11/12/1998 Edited -DT,
            Comments added to facilitate USDA interface development.">
<script LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></script>
</head>

<!-------------------------------------------------------------------------->

<body>

<FORM NAME="frm_confirm_AccountSetup"
      METHOD="POST"
      TARGET="content"
      ACTION="<%=request.getContextPath()%>/setup/individualAccount/search.do">

<table WIDTH="575">
    <tr>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif"
WIDTH="82" HEIGHT="80"></TD-->
      <td ALIGN="Center">
        <h2 class="titleText" >
          Account Setup<br>
          Confirmation
        </h2>
      </td>
    </tr>
</table>

<input TYPE="HIDDEN" NAME="txt_screenTitle1" value="Account Setup">

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<br>
<br>

<!----------------------------------------------------------------------
---->
<jsp:useBean id="frm_verif_AccountSetup"
	class="com.boa.eagls.government.controller.formbean.account.ViewAccountForm" scope="session" />


<table>
    <tr>
      <td WIDTH="575" ALIGN="Center">
        You have successfully submitted Account Setup Information
for&nbsp;<b><br>
        <bean:write name="frm_verif_AccountSetup" property="txt_firstName"/>&nbsp;
        <bean:write name="frm_verif_AccountSetup" property="txt_lastName"/></b><br>
        associated with the following Hierarchy structure.
      </td>
    </tr>
</table>
<br>

<!----------------------------------------------------------------------
---->
<!--
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
    <TR>
      <TH WIDTH="72" ALIGN="Left" >HL1</TH>
      <TH WIDTH="72" ALIGN="Left" >HL2</TH>
      <TH WIDTH="72" ALIGN="Left" >HL3</TH>
      <TH WIDTH="71" ALIGN="Left" >HL4</TH>
      <TH WIDTH="71" ALIGN="Left" >HL5</TH>
      <TH WIDTH="71" ALIGN="Left" >HL6</TH>
      <TH WIDTH="71" ALIGN="Left" >HL7</TH>
      <TH WIDTH="71" ALIGN="Left" >HL8</TH>
    </TR>
    <TR>
      <TD>0000004&nbsp;</TD>
      <TD>2000001&nbsp;</TD>
      <TD>&nbsp;</TD>
      <TD>&nbsp;</TD>
      <TD>&nbsp;</TD>
      <TD>&nbsp;</TD>
      <TD>&nbsp;</TD>
      <TD>&nbsp;</TD>
    </TR>
</TABLE>
-->
<br>


<!-- GCSU Users Only -->
<jsp:useBean id="browseHierarchyFields"
	class="com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields" scope="session" />

    <table>
    <tr>
      <td WIDTH="130">Program Number
      </td>
      <td>
           <bean:write name="frm_verif_AccountSetup" property="txt_hl[0]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160  <input TYPE="HIDDEN"  NAME="txt_hl0" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[0]"/>">
       </td>
       <td ALIGN="CENTER">
          <bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[0]"/>
          &#160
       </td>
    </tr>
</table>

<!-- End GCSU Only -->
<!-- Non GCSU -->

<!-- End Non GCSU -->

<br>
<!--TABLE BORDER="1" WIDTH="575" CELLSPACING="0" CELLPADDING="0"-->
    <table BORDER="1" WIDTH="575">
      <tr>
        <th>
          Level
        </th>
        <th WIDTH="100" ALIGN="CENTER">
          Number
        </th>
        <th ALIGN="CENTER">
          Description/Name
        </th>
      </tr>
      <tr>
        <td ALIGN="CENTER">HL1</td>
        <td ALIGN="CENTER">
             <bean:write name="frm_verif_AccountSetup" property="txt_hl[1]"/>
                <input TYPE="HIDDEN"  NAME="txt_hl1" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[1]"/>"><!--DO NOT DELETE HARD SPACE-->&#160
        </td>

        <td ALIGN="CENTER">

  <!--        NON SIT AGENCY 550383   -->
            <div ID="hl1Desc" class="hLDisplay"  >
            <bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[1]"/>
            <!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </td>
      </tr>

      <tr>
        <td ALIGN="CENTER">HL2</td>
        <td ALIGN="CENTER">
            <bean:write name="frm_verif_AccountSetup" property="txt_hl[2]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
            <input TYPE="HIDDEN"  NAME="txt_hl2" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[2]"/>">
        </td>

        <td ALIGN="CENTER">

          <div ID="hl2Desc" class="hLDisplay" ><bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[2]"/><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </td>
      </tr>
      <tr>
        <td ALIGN="CENTER">HL3</td>
        <td ALIGN="CENTER">
                <bean:write name="frm_verif_AccountSetup" property="txt_hl[3]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <input TYPE="HIDDEN"  NAME="txt_hl3" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[3]"/>">
        </td>

        <td ALIGN="CENTER">

          <div ID="hl3Desc" class="hLDisplay" ><bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[3]"/><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </td>
      </tr>
      <tr>
        <td ALIGN="CENTER">HL4</td>
        <td ALIGN="CENTER">
                <bean:write name="frm_verif_AccountSetup" property="txt_hl[4]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <input TYPE="HIDDEN"  NAME="txt_hl4" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[4]"/>">
        </td>

        <td ALIGN="CENTER">

          <div ID="hl4Desc" class="hLDisplay" ><bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[4]"/><!--DO NOT DELETE HARD
SPACE-->&#160</div>
        </td>
      </tr>
      <tr>
        <td ALIGN="CENTER">HL5</td>
        <td ALIGN="CENTER">
                <input TYPE="HIDDEN"  NAME="txt_hl3" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[5]"/>">
                <!--DO NOT DELETE HARD SPACE-->&#160
                <input TYPE="HIDDEN"  NAME="txt_hl5" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[5]"/>">
        </td>

        <td ALIGN="CENTER">

          <div ID="hl5Desc" class="hLDisplay" ><bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[5]"/><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </td>
      </tr>
      <tr>
        <td ALIGN="CENTER">HL6</td>
        <td ALIGN="CENTER">
                <bean:write name="frm_verif_AccountSetup" property="txt_hl[6]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160  <input TYPE="HIDDEN"  NAME="txt_hl6" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[6]"/>">
        </td>

        <td ALIGN="CENTER">

          <div ID="hl6Desc" class="hLDisplay" ><bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[6]"/><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </td>
      </tr>
      <tr>
        <td ALIGN="CENTER">HL7</td>
        <td ALIGN="CENTER">
                <bean:write name="frm_verif_AccountSetup" property="txt_hl[7]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160  <input TYPE="HIDDEN"  NAME="txt_hl7" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[7]"/>">
        </td>

        <td ALIGN="CENTER">

            <div ID="hl7Desc" class="hLDisplay" ><bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[7]"/><!--DO NOT DELETE HARD SPACE-->&#160</div>
        </td>
      </tr>
      <tr>
        <td ALIGN="CENTER">HL8</td>
        <td ALIGN="CENTER">
                <bean:write name="frm_verif_AccountSetup" property="txt_hl[8]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160  <input TYPE="HIDDEN"  NAME="txt_hl8" VALUE="<bean:write name="frm_verif_AccountSetup" property="txt_hl[8]"/>">
        </td>

        <td ALIGN="CENTER">
            <div ID="hl8Desc" class="hLDisplay" ><bean:write name="browseHierarchyFields" property="HL_DESCRIPTION[8]"/><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </td>
      </tr>
    </table>
<!--jkt Descriptions passed here-->

	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl0Desc" value="NON SIT AGENCY 553588">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl1Desc" value="NON SIT AGENCY 550383">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl2Desc" value="NON SIT AGENCY 551476">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl3Desc" value="">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl4Desc" value="">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl5Desc" value="">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl6Desc" value="">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl7Desc" value="">


	 	<!--DO NOT DELETE HARD SPACE-->&#160
         <input TYPE="HIDDEN" NAME="txt_hl8Desc" value="">

		<input name="hdn_delimitiedHierarchy"type=HIDDEN value="NON SIT AGENCY 550383^NON SIT AGENCY 551476^^^^^^">
<!--- END DESCRIPTIONS------------------------------->


<br>
<table>
    <tr>
      <td WIDTH="575" ALIGN="Center">
        The account number will be available to you the next business
day.
        You may now setup Authorization Controls or User Profile for
this new account.
      </td>
    </tr>
</table>
<br>

<!----------------------------------------------------------------------
---->

<table>
    <tr>
      <td WIDTH="575" ALIGN="Center">
        <input TYPE="submit" NAME="but_submitButton"
               VALUE="&nbsp;&nbsp;&nbsp;OK&nbsp;&nbsp;&nbsp;">
      </td>
    </tr>
</table>
<br>

<table>
    <tr>
      <td WIDTH="575" ALIGN="Center">
            <a HREF="javascript:alert('Not implemented')">Authorization Controls Setup</a>
        &nbsp;&nbsp;
            <a HREF="javascript:alert('Not implemented')">User Profile Setup</a>
      </td>
    </tr>
</table>

<!----------------------------------------------------------------------
---->

<!-- Conf_State -->


<br><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>

<table>
    <tr>
      <td WIDTH="575" ALIGN="Center">
         <h6 class="copyright">
           Copyright © 1999 Bank of America, NA (USA). All rights reserved.
         </h6>
      </td>
    </tr>
</table>


</form>
</body>
</html>
