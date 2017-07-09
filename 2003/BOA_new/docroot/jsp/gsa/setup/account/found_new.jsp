<%@ page contentType="text/html; charset=windows-1251" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<html:html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Setup Account</title>
</head>

<body>

<html:form action="http://segfault.moscow.vdiweb.com/cgi-bin/gx.cgi/GUIDGX-{892094D0-1B7C-11D2-96DD-204C4F4F5020}" target="content" onsubmit="return formTest(document.frm_accountSetupInputForm);" name="frm_accountSetupInputForm" method="post">
  <table width="575">
    <tbody>
      <tr>
        <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
        <td align="middle">
          <h2 class="titleText">Setup<br>
          Account</h2>
        </td>
      </tr>
    </tbody>
  </table>
  <html:hidden value="Setup" property="txt_screenTitle1"/><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><br>
  <br>
  <!--------------------------------------------------------------------------->
  <font face="Arial, helvetica" color="#0000ff"><b>Hierarchy Information</b></font><br>
  <table cellSpacing="0" cellPadding="0" border="1">
    <tbody>
      <tr>
        <td width="140">Hierarchy Level</td>
        <td width="435"><html:button property=""/></td>
      </tr>
    </tbody>
  </table>
  <!------------------ PROGRAM NUMBER VISIBLE ONLY TO GCSU USERS ------------------>
  <!------------------------------ END GCSU ONLY ------------------------------------>
  <br>
  <script src="http://segfault.moscow.vdiweb.com/gsa/scripts/formvalidate.js"></script>
  <script src="http://segfault.moscow.vdiweb.com/gsa/scripts/hierarchyBrowse.js"></script>
  <!-- GCSU Users Only -->
  <table>
    <tbody>
      <tr>
        <td width="130">Program Number</td>
        <td align="middle">NON SIT AGENCY 553588 &nbsp;</td>
      </tr>
    </tbody>
  </table>
  <!-- End GCSU Only -->
  <!-- Non GCSU -->
  <!-- End Non GCSU -->
  <br>
  <!--TABLE BORDER="1" WIDTH="575" CELLSPACING="0" CELLPADDING="0"-->
  <table width="575" border="1">
    <tbody>
      <tr>
        <th>Level</th>
        <th align="middle" width="100">Number</th>
        <th align="middle">Description/Name</th>
      </tr>
      <tr>
        <td align="middle">HL1</td>
        <td align="middle"><center><html:text value="0000003" maxlength="7" property="txt_hl1" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl1, '0', 7);clearHierDesc(form, 1);"/></center></td>
        <td align="middle"><!--        NON SIT AGENCY 550807   -->
          <div class="hLDisplay" id="hl1Desc">
            NON SIT AGENCY 550807 <!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
      <tr>
        <td align="middle">HL2</td>
        <td align="middle"><center><html:text value="0000005" maxlength="7" property="txt_hl2" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl2, '0', 7);clearHierDesc(form, 2);"/></center></td>
        <td align="middle">
          <div class="hLDisplay" id="hl2Desc">
            NON SIT AGENCY 551766<!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
      <tr>
        <td align="middle">HL3</td>
        <td align="middle"><center><html:text value="3100691" maxlength="7" property="txt_hl3" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl3, '0', 7);clearHierDesc(form, 3);"/></center></td>
        <td align="middle">
          <div class="hLDisplay" id="hl3Desc">
            NON SIT AGENCY 550808<!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
      <tr>
        <td align="middle">HL4</td>
        <td align="middle"><center><html:text value="4106975" maxlength="7" property="txt_hl4" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl4, '0', 7);clearHierDesc(form, 4);"/></center></td>
        <td align="middle">
          <div class="hLDisplay" id="hl4Desc">
            NON SIT AGENCY 550809<!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
      <tr>
        <td align="middle">HL5</td>
        <td align="middle"><center><html:text value="5106978" maxlength="7" property="txt_hl5" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl5, '0', 7);clearHierDesc(form, 5);"/></center></td>
        <td align="middle">
          <div class="hLDisplay" id="hl5Desc">
            NON SIT AGENCY 551776<!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
      <tr>
        <td align="middle">HL6</td>
        <td align="middle"><center><html:text maxlength="7" property="txt_hl6" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl6, '0', 7);clearHierDesc(form, 6);"/></center></td>
        <td align="middle">
          <div class="hLDisplay" id="hl6Desc">
            &nbsp;<!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
      <tr>
        <td align="middle">HL7</td>
        <td align="middle"><center><html:text maxlength="7" property="txt_hl7" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl7, '0', 7);clearHierDesc(form, 7);"/></center></td>
        <td align="middle">
          <div class="hLDisplay" id="hl7Desc">
            &nbsp;<!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
      <tr>
        <td align="middle">HL8</td>
        <td align="middle"><center><html:text maxlength="7" property="txt_hl8" onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" size="7" onchange="hierarchy_parent_logo.padLeft(txt_hl8, '0', 7);clearHierDesc(form, 8);"/></center></td>
        <td align="middle">
          <div class="hLDisplay" id="hl8Desc">
            &nbsp;<!--DO NOT DELETE HARD SPACE-->
            &nbsp;
          </div>
        </td>
      </tr>
    </tbody>
  </table>
  <!--jkt Descriptions passed here-->
  <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="NON SIT AGENCY 553588" property="txt_hl0Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="NON SIT AGENCY 550807" property="txt_hl1Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="NON SIT AGENCY 551766" property="txt_hl2Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="NON SIT AGENCY 550808" property="txt_hl3Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="NON SIT AGENCY 550809" property="txt_hl4Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="NON SIT AGENCY 551776" property="txt_hl5Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="&nbsp;" property="txt_hl6Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="&nbsp;" property="txt_hl7Desc"/> <!--DO NOT DELETE HARD SPACE-->
  &nbsp; <html:hidden value="&nbsp;" property="txt_hl8Desc"/> <html:hidden value="NON SIT AGENCY 550807^NON SIT AGENCY 551766^NON SIT AGENCY 550808^NON SIT AGENCY 550809^NON SIT AGENCY 551776^&nbsp;^&nbsp;^&nbsp;" property="hdn_delimitiedHierarchy"/>
  <!--- END DESCRIPTIONS------------------------------->
  <br>
  <!------------------------------------------------------------------------------->
  <br>
  <html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><br>
  <!------------------------------------------------------------------------------->
  <br>
  <font face="Arial, helvetica" color="#0000ff"><b>Central Account Information</b></font>
  <table border="1">
    <tbody>
      <tr>
        <th align="left" width="200">Central Account ID</th>
        <td width="370"><html:hidden value="9025342" property="hdn_centralAccountID"/>9025342&nbsp;</td>
      </tr>
      <tr>
        <th align="left">Central Account Name</th>
        <td>EAGLS CORP ACCOUNT CO 9025342&nbsp; <html:hidden value="EAGLS CORP ACCOUNT CO 9025342" property="hdn_centralAccountName"/></td>
      </tr>
      <tr>
        <th align="left">Central Account Number</th>
        <td>5568168800000299&nbsp; <html:hidden value="5568168800000299" property="hdn_centralAccountNumber"/></td>
      </tr>
    </tbody>
  </table>
  <br>
  <table border="1">
    <tbody>
      <tr>
        <th align="left" width="200">Accounting Center ID</th>
        <td width="370">text field here</td>
      </tr>
      <tr>
        <td align="left">Master Accounting Code</td>
        <td><html:button value="Browse" property="browse" onclick="createMasterAccountingCodeString(document.frm_accountSetupInputForm,'/cgi-bin/gx.cgi/GUIDGX-{6CF4BC10-378D-11D2-96E7-204C4F4F5020}')"/></td>
      </tr>
      <tr>
        <td colSpan="2"><html:text maxlength="64" property="txt_masterAccountingCode" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_MasterAccountingCode)" size="65"/></td>
      </tr>
    </tbody>
  </table>
  <!------------------------------------------------------------------------------->
  <br>
  <html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><br>
  <!------------------------------------------------------------------------------->
  <br>
  <font face="Arial, helvetica" color="#0000ff"><b>Account Information</b></font>
  <table border="1">
    <tbody>
      <tr>
        <th align="left" width="200">Program Type</th>
        <td width="370"><html:hidden value="Integrated" property="hdn_programType"/>Integrated&nbsp;</td>
      </tr>
      <tr>
        <th align="left">Travelers Checks</th>
        <td><html:hidden value="No" property="hdn_travelersCheckFlag"/>No&nbsp;</td>
      </tr>
      <tr>
        <th align="left">Billing Type</th>
        <td><html:hidden value="Individual" property="hdn_billingType"/>Individual&nbsp;</td>
      </tr>
    </tbody>
  </table>
  <br>
  <table border="1">
    <tbody>
      <tr>
        <th align="left" width="200">Generate Paper Statement</th>
        <td width="370"><html:select property="cmb_generatePaperStatementFlag" size="1">
            <html:option value="No">No</html:option>
            <html:option value="Yes">Yes</html:option>
          </html:select></td>
      </tr>
      <tr>
        <th align="left">Card Type</th>
        <td><html:select property="cmb_cardType" size="1">
            <html:option value="value"></html:option>
          </html:select></td>
      </tr>
      <tr>
        <th align="left">Card/Non-card</th>
        <td><html:select property="cmb_cardNoncard" size="1">
            <html:option value="Card">Card</html:option>
            <html:option value="Non-card">Non-card</html:option>
          </html:select></td>
      </tr>
      <tr>
        <th align="left">Account Expiration Date</th>
        <td><html:text maxlength="7" property="txt_accountExpirationDate" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_AccountExpirationDate)" size="8"/>
          (MM/YYYY)</td>
      </tr>
      <tr>
        <th align="left">Credit Limit</th>
        <td><html:text maxlength="11" property="txt_creditLimit" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Credit_Limit);" size="12" onchange="updateCurrency(document.frm_accountSetupInputForm);"/></td>
      </tr>
    </tbody>
  </table>
  <br>
  <!------------------------------------------------------------------------------->
  <br>
  <html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><br>
  <!------------------------------------------------------------------------------->
  <!-- Account Holder Information Starts -->
  <br>
  <font face="Arial, helvetica" color="#0000ff"><b>Account Holder Information</b></font>
  <table border="1">
    <tbody>
      <tr>
        <th align="left" width="200">Last Name</th>
        <td width="370"><html:text maxlength="26" property="txt_lastName" size="41"/></td>
      </tr>
      <tr>
        <th align="left">First Name</th>
        <td><html:text maxlength="26" property="txt_firstName" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_FirstName);" size="41"/></td>
      </tr>
      <tr>
        <th align="left">Address 1</th>
        <td><html:text value="100 MAIN STREET" maxlength="40" property="txt_addressLine1" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Address)" size="41"/></td>
      </tr>
      <tr>
        <td align="left">Address 2</td>
        <td><html:text maxlength="40" property="txt_addressLine2" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_AddressLine2)" size="41"/></td>
      </tr>
      <tr>
        <td align="left">Address 3</td>
        <td><html:text maxlength="40" property="txt_addressLine3" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_AddressLine3)" size="41"/></td>
      </tr>
      <tr>
        <td align="left">Address 4</td>
        <td><html:text maxlength="40" property="txt_addressLine4" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_AddressLine4)" size="41"/></td>
      </tr>
      <tr>
        <th align="left">City</th>
        <td><html:text value="NORFOLK" maxlength="19" property="txt_city" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_City)" size="20"/></td>
      </tr>
      <tr>
        <th align="left">State/Province</th>
        <td><html:text value="VA" maxlength="3" property="txt_state" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_State)" size="4"/></td>
      </tr>
      <tr>
        <th align="left">Country</th>
        <td><html:text value="USA" maxlength="3" property="txt_country" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Country)" size="4"/></td>
      </tr>
      <tr>
        <th align="left">Zip/Postal Code</th>
        <td><html:text value="23510" maxlength="13" property="txt_zipCode" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Zip)" size="14"/></td>
      </tr>
      <tr>
        <th align="left">Work Phone</th>
        <td><html:text value="5551234567" maxlength="17" property="txt_businessPhone" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Business_Phone)" size="18"/></td>
      </tr>
      <tr>
        <td align="left">E-mail Address</td>
        <td><html:text maxlength="60" property="txt_emailAddress" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Email_Address)" size="41"/></td>
      </tr>
    </tbody>
  </table>
  <br>
  <!------------------------------------------------------------------------------->
  <br>
  <html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><br>
  <!------------------------------------------------------------------------------->
  <!-- Identification Section Starts -->
  <br>
  <font face="Arial, Helvetica" color="#0000ff"><b>Identification</b></font>
  <table border="1">
    <tbody>
      <tr>
        <td width="200">Social Security Number</td>
        <td width="370"><html:text value="123-45-0559" maxlength="11" property="txt_socialSecurityNumber" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_SSN)" size="12"/></td>
      </tr>
      <tr>
        <td>Employee ID</td>
        <td><html:text maxlength="19" property="txt_employeeIdentificationNumber" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_EmployeeID)" size="19"/></td>
      </tr>
      <tr>
        <td>Grade</td>
        <td><html:select property="cmb_grade" size="1">
          </html:select></td>
      </tr>
      <tr>
        <td>Employment Status</td>
        <td><html:select property="cmb_status" size="1">
          </html:select></td>
      </tr>
    </tbody>
  </table>
  <!------------------------------------------------------------------------------->
  <html:hidden property="hdn_crdtLmt"/><html:hidden value="1000000" property="hdn_programNumber"/><!-- hdn_programNumber -->
  <html:hidden value="NON SIT AGENCY 553588" property="txt_programDescription"/><!-- txt_programDescription -->
  <html:hidden value="0000003" property="hdn_hl1"/><html:hidden value="0000005" property="hdn_hl2"/><html:hidden value="3100691" property="hdn_hl3"/><html:hidden value="4106975" property="hdn_hl4"/><html:hidden value="5106978" property="hdn_hl5"/><html:hidden property="hdn_hl6"/><html:hidden property="hdn_hl7"/><html:hidden property="hdn_hl8"/><html:hidden value="NON SIT AGENCY 550807" property="txt_hl1Descr"/><!-- txt_hl1Descr -->
  <html:hidden value="NON SIT AGENCY 551766" property="txt_hl2Descr"/><!-- txt_hl2Descr -->
  <html:hidden value="NON SIT AGENCY 550808" property="txt_hl3Descr"/><!-- txt_hl3Descr -->
  <html:hidden value="NON SIT AGENCY 550809" property="txt_hl4Descr"/><!-- txt_hl4Descr -->
  <html:hidden value="NON SIT AGENCY 551776" property="txt_hl5Descr"/><!-- txt_hl5Descr -->
  <html:hidden value=" " property="txt_hl6Descr"/><!-- txt_hl6Descr -->
  <html:hidden value=" " property="txt_hl7Descr"/><!-- txt_hl7Descr -->
  <html:hidden value=" " property="txt_hl8Descr"/><!-- txt_hl8Descr -->
  <!--ADDITIONAL COMMENTS ADDED ON 8/20/1998-->
  <html:hidden value="accountval1" property="hdn_oldAcct"/><!-- hdn_oldAcct -->
  <!------------------------------------------------------------------------------->
  <br>
  <table>
    <tbody>
      <tr>
        <td align="middle" width="575"><html:submit value="Submit" property="but_submit"/>&nbsp;&nbsp;
          text field here</td>
      </tr>
    </tbody>
  </table>
  <small><b>Bold Fields Are Required</b></small><br>
  <!------------------------------------------------------------------------------->
  <br>
  <html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
  <table>
    <tbody>
      <tr>
        <td align="middle" width="575">
          <h6 class="copyright">Copyright © 1999 Bank of America, NA (USA). All
          rights reserved.</h6>
        </td>
      </tr>
    </tbody>
  </table>
</html:form>

</body>

</html:html>
 