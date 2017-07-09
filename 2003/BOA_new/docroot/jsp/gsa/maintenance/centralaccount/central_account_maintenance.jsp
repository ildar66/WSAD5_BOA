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

<TITLE>Central Account Maintenance</title>

<META NAME="Name" CONTENT="Central Account Maintenance">
<META NAME="Description" CONTENT="Central Account Maintenance Data Entry Screen">

<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

<SCRIPT LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

function initialize(theForm)
    {
     parent.logo.setFocus(theForm,theForm.txt_centralAccountName,parent.logo.Status_Text_CentralAccountName);
    }

<!-------------------Program Type-------------------------------------->

<!--*******************  CHECK DATE 3  *************************-->
<!--* This function checks if the date (MM/YYYY) is prior to   *-->
<!--* the current MM/YYYY                                      *-->
<!--************************************************************-->
function checkDate3(theField)
{

   var MSlash = theField.value.substring(2,3);
   var MM     = theField.value.substring(0,2);
   var YY     = theField.value.substring(3,7);

   if (MM.substring(0,1) == "0") {
       MM = MM.substring(1,2);
   }
   var now   = new Date();
   var Month = now.getMonth();
   var Year  = now.getYear();

   // Must convert Year to a string to check the length so a date compare can be done
   mystring = new String (Year)

   if (mystring.length < 4)
   {
	// Year is formated number of days after 1900 so add 1900 to get a 4 digit year
	Year += 1900;
   }

   // Note Month is Zero Based. So, December is Month 11 !
   if ( (parseInt(MM) < parseInt(Month) + 1)  && (parseInt(YY) == parseInt(Year) ) )
   {
    // Prior month, same year
    return("error");
   }


   if( parseInt(YY) < parseInt(Year) )
   {
    // Prior year
      return("error");
   }



   return("ok");
}


<!--*******************  CHECK DATE 4  *************************-->
<!--* This function checks if the date (MM/YYYY) is validated   *-->
<!--* so that the month falls between 1 and 12 (both included)  *-->
<!--************************************************************-->

function checkDate4(theField)
{
    var MM     = theField.value.substring(0,2);

    // Bug CD2306, ANIL: To fix problem with parseInt.
    // parseInt("08") translates to 0 instead of 8.
    // This piece of code eliminates leading 0, and
    // works fine.
    if (MM.substring(0,1) == "0") {
	       MM = MM.substring(1,2);
    }

     if((parseInt(MM) > 12) || (parseInt(MM) == 0))

	 {
	    return("error");
	 }

  return("ok");

}




function radioClick(theForm,theField)
    {
     if (theForm.rag_programType[1].checked == true)
         {
          theForm.chk_cardProgram2.checked=false;
          theForm.chk_cardProgram3.checked=false;
          theForm.chk_cardProgram4.checked=false;
          theForm.chk_cardProgram1.checked=false;
         }
    }

function convenienceCheckTestA(theForm)
    {
// If any of the 3, 20, or 50 checkboxes are selected, the convenience list goes to YES
// If none are checked the convenience list goes to NO
     if ((theForm.chk_checkProgram1.checked)||
         (theForm.chk_checkProgram2.checked)||
         (theForm.chk_checkProgram3.checked))
         {
          theForm.cmb_convenienceChecks.selectedIndex = "1";
         }
     if(!(theForm.chk_checkProgram1.checked)&&
         !(theForm.chk_checkProgram2.checked)&&
         !(theForm.chk_checkProgram3.checked))
         {
          theForm.cmb_convenienceChecks.selectedIndex = "0";
         }
    }

function convenienceCheckTestB(theForm)
    {
// If convenience checks list is NO, take all 3,20,50 checkboxes to unchecked
     if (theForm.cmb_convenienceChecks.selectedIndex == "0")
         {
          theForm.chk_checkProgram1.checked=false;
          theForm.chk_checkProgram2.checked=false;
          theForm.chk_checkProgram3.checked=false;
         }
    }

function test(theForm,theField)
    {
     if (theForm.rag_programType[1].checked==true)
//     alert( "hello");
         {
          if((theField==theForm.chk_cardProgram1))
              {
               theForm.chk_cardProgram2.checked=false;
               theForm.chk_cardProgram3.checked=false;
               theForm.chk_cardProgram4.checked=false;
               theForm.chk_cardProgram1.checked=true;
              }
          if((theField==theForm.chk_cardProgram2))
              {
               theForm.chk_cardProgram1.checked=false;
               theForm.chk_cardProgram3.checked=false;
               theForm.chk_cardProgram4.checked=false;
               theForm.chk_cardProgram2.checked=true;
              }
          if((theField==theForm.chk_cardProgram3))
              {
               theForm.chk_cardProgram1.checked=false;
               theForm.chk_cardProgram2.checked=false;
               theForm.chk_cardProgram4.checked=false;
               theForm.chk_cardProgram3.checked=true;
              }
          if((theField==theForm.chk_cardProgram4))
              {
               theForm.chk_cardProgram1.checked=false;
               theForm.chk_cardProgram2.checked=false;
               theForm.chk_cardProgram3.checked=false;
               theForm.chk_cardProgram4.checked=true;
              }
         }
    }

function form_test(theForm)
    {
     var errormessage=parent.logo.ErrMsg_Header;

	 if ((theForm.txt_accountingCenterID.value == "")||
	          (parent.logo.checkNumeric(theForm,theForm.txt_accountingCenterID) == "error"))
	         {
	           errormessage += parent.logo.ErrMsg_AccountingCenterID;
        }

     if ((theForm.txt_masterAccountingCode.value != "")&&
         (checkAlphaNumericPlus(theForm,theForm.txt_masterAccountingCode)== "error"))
         {
          errormessage += parent.logo.ErrMsg_MasterAccountingCode;
         }

     if ((theForm.txt_accountExpirationDate.value == "")||
         (parent.logo.checkDate2(theForm,theForm.txt_accountExpirationDate)== "error") ||
		 (checkDate3(theForm.txt_accountExpirationDate)== "error") || (checkDate4(theForm.txt_accountExpirationDate) == "error") )
         {
         errormessage += parent.logo.ErrMsg_Date;
         }

     if ( theForm.chk_cardProgram1 )
     {
        if (  !theForm.chk_cardProgram1.checked && !theForm.chk_cardProgram2.checked &&
              !theForm.chk_cardProgram3.checked && !theForm.chk_cardProgram4.checked)
        {
          errormessage += 'You must select at least one card program\n';
        }
     }

     convenienceCheckTestA(theForm);

     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert (errormessage);
          return (false);
         }
          return (true);
    }

// Added this function on 10/13/1998 -DT
// Same function as in logo.html, but does not accept blank spaces

function checkAlphaNumericPlus(theForm,theField)
  {
   var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789',#/-.&():_!@*%+-^$!";
   var checkStr = theField.value;
   var allValid = true;

  for (i = 0;  i < checkStr.length;  i++)
       {
        ch = checkStr.charAt(i);
        for (j = 0;  j < checkOK.length;  j++)
          if (ch == checkOK.charAt(j))
            break;
        if (j == checkOK.length)
         {
          allValid = false;
          break;
         }
       }
   if (!allValid)
       {
        return ("error");
       }

   return ("ok");
 }

//*******************************************************************
// WINDOW OPENING FUNCTIONS
//*******************************************************************

 function openNewWindow(theUrl)
   {
    msg=window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=400,status=no,menubar=no");
   }

 function openNewWindow_dualWindowDisplay(theUrl)
   {
   msg=window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=750,height=630,status=no,menubar=no");
   }

 function openNewWindow_singleWindowDisplay(theUrl)
   {
   msg=window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=360,status=no,menubar=no");
   }

//*******************************************************************
// BROWSE WINDOW SELECTION FUNCTIONS
//*******************************************************************


function selectAccountingCenterId(accountingCenterId)
    {
   // alert(accountingCenterId);
    document.frm_centralAccountMaintenanceForm.txt_accountingCenterID.value="";
    document.frm_centralAccountMaintenanceForm.txt_accountingCenterID.value=accountingCenterId;
    }

function selectMasterAccountingCode(masterAccountingCode)
    {
     document.frm_centralAccountMaintenanceForm.txt_masterAccountingCode.value=masterAccountingCode;
    }


//*******************************************************************
// BROWSE WINDOW URL STRING CREATION FUNCTIONS
//*******************************************************************

function createMasterAccountingCodeString(theForm,theUrl)
  {

   var errormessage=parent.logo.ErrMsg_Header;
   var urlString=theUrl+"?accountingCenterId=";

   if ((theForm.txt_accountingCenterID.value == "")||
       (parent.logo.checkNumeric(theForm,theForm.txt_accountingCenterID) == "error"))
     {
      errormessage += parent.logo.ErrMsg_AccountingCenterID;
     }

   if (errormessage != parent.logo.ErrMsg_Header)
     {
      alert(errormessage);
      return;
     }
   urlString += theForm.txt_accountingCenterID.value;
   urlString += "&masterAccountingCode="+escape(theForm.txt_masterAccountingCode.value);
   //alert(urlString);

   openNewWindow_dualWindowDisplay(urlString);

  }

function createHierarchyString(theForm,theUrl,theBrowseType)
  {
   var hierarchyString=theUrl+"?availableHierarchiesList=";

  // If HL0 exists add it to the Hierarchy String

  if (theForm.txt_hl0)
 {
   hierarchyString += (theForm.txt_hl0.value == "") ? "0000000" : theForm.txt_hl0.value;
 }
  else
 {
  hierarchyString += "0000000";
 }

  // Assign "0000000" to null fields to complete HL String

   hierarchyString += (theForm.txt_hl1.value == "") ? "0000000" : theForm.txt_hl1.value;
   hierarchyString += (theForm.txt_hl2.value == "") ? "0000000" : theForm.txt_hl2.value;
   hierarchyString += (theForm.txt_hl3.value == "") ? "0000000" : theForm.txt_hl3.value;
   hierarchyString += (theForm.txt_hl4.value == "") ? "0000000" : theForm.txt_hl4.value;
   hierarchyString += (theForm.txt_hl5.value == "") ? "0000000" : theForm.txt_hl5.value;
   hierarchyString += (theForm.txt_hl6.value == "") ? "0000000" : theForm.txt_hl6.value;
   hierarchyString += (theForm.txt_hl7.value == "") ? "0000000" : theForm.txt_hl7.value;
   hierarchyString += (theForm.txt_hl8.value == "") ? "0000000" : theForm.txt_hl8.value;

   openNewWindow_singleWindowDisplay(hierarchyString);

} // End of createHierarchyString



//
// THIS FUNCTION CREATES THE URL STRING NECCESSARY TO LAUNCH THE REQUIRED POPUP WINDOW
//

function createUrlString(theForm,theUrl,theButtonSelected)
  {
   var errormessage=parent.logo.ErrMsg_Header;
   var urlString=theUrl+"?";

   //
   // CHECK WHICH BUTTON WAS PRESSED
   //

   if (theButtonSelected == "mccg")
     {
      //alert("mccg pressed");
      urlString += "hdn_mlist=" + theForm.hdn_mlist.value + "&" +
                   "hdn_centralAccountNumber=" + theForm.hdn_centralAccountNumber.value + "&" +
                   "but_submitButton=" + "MCCG Table Names";
     }

   if (theButtonSelected == "preferredVendor")
     {
      //alert("vendor pressed");
      urlString += "hdn_vlist=" + theForm.hdn_vlist.value + "&" +
                   "hdn_centralAccountNumber=" + theForm.hdn_centralAccountNumber.value + "&" +
                   "but_submitButton=" + "Vendor Table Names";
     }

   if (theButtonSelected == "emalls")
     {
      //alert("emalls pressed");
      urlString += "hdn_aList=" + theForm.hdn_aList.value + "&" +
                   "hdn_cList=" + theForm.hdn_cList.value + "&" +
                   "hdn_centralAccountNumber=" + theForm.hdn_centralAccountNumber.value + "&" +
                   "but_submitButton=" + "Emall Table Names";
     }

  alert(urlString);

   openNewWindow(urlString);
  }

// Stop hiding -->
</SCRIPT>
</HEAD>

<!--------------------------------------------------------------------------->

<BODY>
      <!--- onLoad="initialize(document.frm_centralAccountMaintenanceForm)"---->
<html:form name="frm_centralAccountMaintenanceForm" action="/maintenance/centralAccount/centralAccountVerification"
 method="POST" onsubmit="return form_test(document.frm_centralAccountMaintenanceForm)"
 type="com.boa.eagls.government.controller.formbean.maintenance.centralaccount.CentralAccountForm">
<bean:parameter id="searchType" name="searchType"/>
<table WIDTH="575">
    <tr>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <td ALIGN="Center">

        <h2 class="titleText" >
          <logic:equal parameter="searchType" value="centralAccountMaintenance">
             Central Account Maintenance
          </logic:equal>
          <logic:equal parameter="searchType" value="centralAccountInquiry">
              Central Account Inquiry<br>
              Results
          </logic:equal><br>

        </h2>
      </td>
    </tr>
</table>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<br>
<br>

<!--------------------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Hierarchy Information</B></FONT>


<GX type=cell id=GCSU>
<!--
----------------------- GCSU USERS ONLY --------------------
<BR>
<TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
    <TR>
      <TD WIDTH="144">
        <B>Program Number</B>
      </TD>
      <TD WIDTH="427">
        <GX TYPE=replace id=attributes.programNumber value=PN>PN</GX>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Program Description</B>
      </TD>
      <TD>
        <GX TYPE=replace id=attributes.programDescription value=PD>PD</GX>&nbsp;
      </TD>
    </TR>
</TABLE>
--->
</GX>
<BR>
<%@ include file="/jsp/gsa/common/right_hierarchy.jsp"%>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Central Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">
        <B>Agency Name</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_centralAccountMaintenanceForm" property="agencyName" />
        <html:hidden name="frm_centralAccountMaintenanceForm" property="agencyName"/>
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Central Account ID</B>
      </TD>
      <TD>
         <bean:write name="frm_centralAccountMaintenanceForm" property="centralAccountID" />
         <html:hidden name="frm_centralAccountMaintenanceForm" property="centralAccountID"/>
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Central Account Number</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="centralAccountNumber" />
        <html:hidden name="frm_centralAccountMaintenanceForm" property="centralAccountNumber"/>
      </TD>
    </TR>
    <TR>
</TABLE>
<BR>

<TABLE BORDER="1">
<!-------------------Call 2 Letter Change Function--------------->
      <TD WIDTH="213">Central Account Name</TD>
      <TD WIDTH="352">
        <html:text name="frm_centralAccountMaintenanceForm" property="txt_centralAccountName"
        size="36" maxlength="35" onfocus="parent.logo.Display_Message(parent.logo.Status_Text_CentralAccountName)" />
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Account Attributes</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">
        <B>Account Type</B>
      </TD>
      <TD WIDTH="352">
        <bean:write name="frm_centralAccountMaintenanceForm" property="accountType" />
        <html:hidden name="frm_centralAccountMaintenanceForm" property="accountType"/>
        &nbsp;
      </TD>
    </TR>
    <TR>
      <TD>
        <B>Billing Type</B>
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="billingType" />&nbsp;
        <html:hidden name="frm_centralAccountMaintenanceForm" property="billingType"/>
      </TD>
    </TR>
    <html:hidden name="frm_centralAccountMaintenanceForm" property="fleetType"/>
    <!-----------------------<TR>
	  Refer to Bug# : MDS0715
      <TD>
        <B>Fleet Type</B>
      </TD>
      <TD>
        <GX type=replace id=attributes.fleetType value=FT>
          FT
        </GX>&nbsp;
      </TD>
    </TR>-------------------------------------------->

</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"><B>Accounting Center ID</B></TD>
      <TD WIDTH="352">
      <html:text name="frm_centralAccountMaintenanceForm" property="txt_accountingCenterID"
        size="13" maxlength="12" />

          <INPUT TYPE="Button" NAME="but_browse" VALUE="Browse" onclick="alert('Not implemented yet');">
<%--          onclick="createHierarchyString(document.frm_centralAccountMaintenanceForm,'/cgi-bin/gx.cgi/GUIDGX-{0FA5ACE0-35FD-11D2-96E4-204C4F4F5020}','accountingCenterId')">--%>
      </TD>
    </TR>
    <TR>
      <TD COLSPAN="2">
        Master Accounting Code&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <INPUT TYPE="Button" NAME="but_browse" VALUE="Browse" onclick="alert('Not implemented yet');">

<%--          onclick="createMasterAccountingCodeString(document.frm_centralAccountMaintenanceForm,'/cgi-bin/gx.cgi/GUIDGX-{6CF4BC10-378D-11D2-96E7-204C4F4F5020}')">--%>
      </TD>
    <TR>
      <TD COLSPAN="2">
      <html:text name="frm_centralAccountMaintenanceForm" property="txt_masterAccountingCode"
        size="64" maxlength="64" />
      </TD>
    </TR>
    <TR>
      <TD>Account Expiration Date
      </TD>
      <TD>
      <html:text name="frm_centralAccountMaintenanceForm" property="txt_accountExpirationDate"
        size="8" maxlength="7" />
        (MM/YYYY)
      </TD>
    </TR>
    <TR>
      <TD>Credit Limit
      </TD>
      <TD>
        <bean:write name="frm_centralAccountMaintenanceForm" property="creditLimit" />&nbsp;
        <html:hidden name="frm_centralAccountMaintenanceForm" property="creditLimit"/>
    </TR>
    <TR>
      <TD>Travelers Checks
      </TD>
      <TD>
        <html:select name="frm_centralAccountMaintenanceForm" property="cmb_travelerChecks"
        onfocus="parent.logo.Display_Message('Select Travelers Checks')">
            <html:option value="No" >No</html:option>
            <html:option value="Yes" >Yes</html:option>
        </html:select>
        <!--<SELECT NAME="cmb_travelerChecks"
                OnFocus="parent.logo.Display_Message('Select Travelers Checks')">
          <GX type=replace id=select.travelersChecksNo value=travelersChecksNo>
            <OPTION VALUE="No" travelersChecksNo>No</OPTION>
          </GX>
          <GX type=replace id=select.travelersChecksYes value=travelersChecksYes>
            <OPTION VALUE="Yes" travelersChecksYes>Yes</OPTION>
          </GX>
        </SELECT>-->
      </TD>
    </TR>
    <TR>
      <TD>ATM Access
      </TD>
      <TD>
        <html:select name="frm_centralAccountMaintenanceForm" property="cmb_atmAccess">
            <html:option value="Yes" >Yes</html:option>
            <html:option value="No" >No</html:option>
        </html:select>

      </TD>
    </TR>
    <TR>
      <TD>Decrement Card
      </TD>
      <TD>
        <html:select name="frm_centralAccountMaintenanceForm" property="cmb_decrementCard">
            <html:option value="Yes" >Yes</html:option>
            <html:option value="No" >No</html:option>
        </html:select>

      </TD>
    </TR>
     <TR>
      <TD>
        City Pair Program</FONT>
      </TD>
      <TD>
        <html:select name="frm_centralAccountMaintenanceForm" property="cmb_citypairProgram">
            <html:option value="Yes" >Yes</html:option>
            <html:option value="No" >No</html:option>
        </html:select>
      </TD>
    </TR>

</TABLE>

<!-- **************************************** TAKE OUT FOR NOW
           <TABLE BORDER="1">
               <TR>
                 <TD WIDTH="213">Program Type</TD>
                 <TD WIDTH="352">
                     <INPUT TYPE=RADIO NAME="rag_programType" VALUE="Integrated" CHECKED>
                     Integrated&nbsp;
                     <INPUT TYPE=RADIO NAME="rag_programType" VALUE="Non-Integrated"
                          onClick="radioClick(document.frm_centralAccountMaintenanceForm,document.frm_centralAccountMaintenanceForm.rag_programType);">
                     Non-Integrated
                 </TD>
               </TR>
               <TR>
                 <TD>Card Programs
                 </TD>
                 <TD>
                     <INPUT TYPE="Checkbox" NAME="chk_cardProgram1" VALUE="Fleet"
                          onClick="test(document.frm_centralAccountMaintenanceForm,document.frm_centralAccountMaintenanceForm.chk_cardProgram1);">
                     Fleet
                     <INPUT TYPE="Checkbox" NAME="chk_cardProgram2" VALUE="Purchace"
                          onClick="test(document.frm_centralAccountMaintenanceForm,document.frm_centralAccountMaintenanceForm.chk_cardProgram2);">
                     Purchase
                     <INPUT TYPE="Checkbox" NAME="chk_cardProgram3" VALUE="Travel"
                          onClick="test(document.frm_centralAccountMaintenanceForm,document.frm_centralAccountMaintenanceForm.chk_cardProgram3);">
                     Travel
                     <INPUT TYPE="Checkbox" NAME="chk_cardProgram4" VALUE="Interagency"
                          onClick="test(document.frm_centralAccountMaintenanceForm,document.frm_centralAccountMaintenanceForm.chk_cardProgram4);">
                     Interagency
                 </TD>
               </TR>
           </TABLE>
************************************************ -->

<BR>
<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">Program Type</TD>
      <TD WIDTH="352">
        <bean:write name="frm_centralAccountMaintenanceForm" property="integratedType" />
        <html:hidden name="frm_centralAccountMaintenanceForm" property="integratedType"/>
      </TD>
    </TR>
    <logic:present name="frm_centralAccountMaintenanceForm" property="integratedProgram">
    <html:hidden name="frm_centralAccountMaintenanceForm" property="integratedProgram"/>
     <TR>
      <TD>Card Programs</TD>
      <TD>
        <logic:present name="frm_centralAccountMaintenanceForm" property="showCheckBoxes">
          <html:checkbox name="frm_centralAccountMaintenanceForm" property="selectFleetCheckBox" >Fleet</html:checkbox>
          <html:checkbox name="frm_centralAccountMaintenanceForm" property="selectPurchaseCheckBox" >Purchase</html:checkbox>
          <html:checkbox name="frm_centralAccountMaintenanceForm" property="selectTravelCheckBox" >Travel</html:checkbox>
          <html:checkbox name="frm_centralAccountMaintenanceForm" property="selectInteragencyCheckBox" >Interagency</html:checkbox>
        </logic:present>
        <logic:present name="frm_centralAccountMaintenanceForm" property="showPlainText">
          <bean:write name="frm_centralAccountMaintenanceForm" property="programType" />
          <html:hidden name="frm_centralAccountMaintenanceForm" property="programType"/>
        </logic:present>
      &nbsp;</TD>
    </TR>
    </logic:present>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">Convenience Checks
      </TD>
      <TD WIDTH="352">
        <html:select name="frm_centralAccountMaintenanceForm" property="cmb_convenienceChecks"
            onchange="convenienceCheckTestB(document.frm_centralAccountMaintenanceForm)">
            <html:option value="No" >No</html:option>
            <html:option value="Yes" >Yes</html:option>
        </html:select>
      </TD>
    </TR>
    <TR>
      <TD>Number of Convenience Checks
      </TD>
      <TD>
          <html:checkbox name="frm_centralAccountMaintenanceForm" property="chk_checkProgram1" >3</html:checkbox>
          <html:checkbox name="frm_centralAccountMaintenanceForm" property="chk_checkProgram2" >20</html:checkbox>
          <html:checkbox name="frm_centralAccountMaintenanceForm" property="chk_checkProgram3" >50</html:checkbox>
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Invoice Medium</B></FONT><BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213">EAGLS
      </TD>
      <TD WIDTH="352">
        <!-- <INPUT TYPE="Checkbox" NAME="chk_EAGLS" VALUE="ON" CHECKED> -->
        Yes
      </TD>
    </TR>
    <TR>
      <TD>Paper
      </TD>
      <TD>
        <html:checkbox name="frm_centralAccountMaintenanceForm" property="paperYes" ></html:checkbox>
      </TD>
    </TR>
    <TR>
      <TD>EDI
      </TD>
      <TD>
        <html:checkbox name="frm_centralAccountMaintenanceForm" property="EDIYes" ></html:checkbox>
      </TD>
    </TR>
    <TR>
      <TD>Electronic
      </TD>
      <TD>
        <html:checkbox name="frm_centralAccountMaintenanceForm" property="electronicYes" ></html:checkbox>
      </TD>
    </TR>
</TABLE>
<BR>

<!----------------------------- VALID TABLE NAMES Buttons ------------------->
<logic:equal parameter="searchType" value="centralAccountMaintenance">
             <TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <GX type=replace id=mccgHyperlink value=theMccgHyperlink>
          <INPUT TYPE="button" NAME="but_submitButton" VALUE="Maintain MCCG Table Names" onclick="alert('Not implemented yet');">
<%--                 onclick="openNewWindow_dualWindowDisplay('theMccgHyperlink')">--%>
        </GX> </TD>
	</TR>
	<TR> <TD WIDTH="575" ALIGN="Center">
		<GX type=replace id=vendorHyperlink value=theVendorHyperlink>
          <INPUT TYPE="button" NAME="but_submitButton" VALUE="Maintain Vendor Table Names" onclick="alert('Not implemented yet');">
<%--                 onclick="openNewWindow_dualWindowDisplay('theVendorHyperlink')">--%>
         </GX>

        <GX type=replace id=emallHyperlink value=theEmallHyperlink>
          <INPUT TYPE="button" NAME="but_submitButton" VALUE="Web Site(s)" onclick="alert('Not implemented yet');">
<%--                 onclick="openNewWindow_dualWindowDisplay('theEmallHyperlink')">--%>
         </GX> </TD>
    </TR>
</TABLE>
<br>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
          <INPUT TYPE="submit" NAME="but_submitButton" VALUE="Submit">&nbsp;&nbsp;

          <INPUT TYPE="reset" NAME="but_resetButton" VALUE="Clear">
      </TD>
    </TR>
</TABLE>
</logic:equal>
<logic:equal parameter="searchType" value="centralAccountInquiry">
          <table>
              <tr>
                  <td WIDTH=575 ALIGN="CENTER">

                    <a HREF="#top">Back to Top</a></center>
                  </td>
              </tr>
          </table>
          <br>
      <table>
        <tr>
          <td WIDTH="575" ALIGN="Center">

            <!-- SHOW ONLY IF BASE ROLE IS NOT CL OR GSA -->



                <a HREF="<%=request.getContextPath()%>/notImplemented.do">Agency Invoice</a>&nbsp;&nbsp;&nbsp;




                <a HREF="<%=request.getContextPath()%>/notImplemented.do">Authorization Control Area</a>&nbsp;&nbsp;&nbsp;



                <a HREF="<%=request.getContextPath()%><%=PointOfContactActions.SEARCH_POINTS_OF_CONTACT%>.do?<%=PointOfContactParams.ACCOUNT_NUMBER%>=<bean:write name="frm_centralAccountMaintenanceForm" property="centralAccountNumber"/>">Point Of Contact Information</a>


            <br>
                <!-- THESE FOUR LINKS ARE SHOWN ONLY IF THE ACCOUNT IS A CENTRAL (not diversion) ACCOUNT -->

                <a HREF="<%=request.getContextPath()%>/notImplemented.do">DBO Information</a>&nbsp;&nbsp;&nbsp;




               <a HREF="<%=request.getContextPath()%>/notImplemented.do">EC/EDI Information</a>&nbsp;&nbsp;&nbsp;



               <a HREF="<%=request.getContextPath()%>/notImplemented.do">Payment Office Information</a>&nbsp;



               <a HREF="<%=request.getContextPath()%>/notImplemented.do">TDO Information</a>&nbsp;&nbsp;&nbsp;








          </td>

        </tr>
    </table>
    <br>
 </logic:equal>






<%--<!************HIDDEN FIELDS****************************>--%>
<%----%>
<%--<GX type=replace id=attributes.programNumber value=PN>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_programNumber" VALUE="PN">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.programDescription value=PD>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_programDescription" VALUE="PD">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl1 value=HL1>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl1" VALUE="HL1">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl2 value=HL2>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl2" VALUE="HL2">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl3 value=HL3>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl3" VALUE="HL3">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl4 value=HL4>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl4" VALUE="HL4">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl5 value=HL5>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl5" VALUE="HL5">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl6 value=HL6>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl6" VALUE="HL6">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl7 value=HL7>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl7" VALUE="HL7">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl8 value=HL8>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl8" VALUE="HL8">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl1Desc value=HL1DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl1Desc" VALUE="HL1DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl2Desc value=HL2DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl2Desc" VALUE="HL2DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl3Desc value=HL3DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl3Desc" VALUE="HL3DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl4Desc value=HL4DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl4Desc" VALUE="HL4DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl5Desc value=HL5DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl5Desc" VALUE="HL5DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl6Desc value=HL6DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl6Desc" VALUE="HL6DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl7Desc value=HL7DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl7Desc" VALUE="HL7DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=account.hl8Desc value=HL8DESC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_hl8Desc" VALUE="HL8DESC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=information.agencyName value=AN>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_agencyName" VALUE="AN">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=information.centralAccountID value=CAI>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_centralAccountID" VALUE="CAI">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=information.centralAccountNumber value=CAN>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_centralAccountNumber" VALUE="CAN">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=information.centralAccountName value=CAName>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_centralAccountName" VALUE="CAName">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.masterAccountingCode value=MAC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_masterAccountingCode" VALUE="MAC">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.accountingCenterID value=ACI>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_acctCenterID" VALUE="ACI">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.cityPair value=CP>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_cityPair" VALUE="CP">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.accountType value=AT>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_accountType" VALUE="AT">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.billingType value=BT>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_billingType" VALUE="BT">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.fleetType value=FT>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_fleetType" VALUE="FT">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.accountExpirationDate value=AED>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_accountExpirationDate" VALUE="AED">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.creditLimit value=CL>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_creditLimit" VALUE="CL">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=misc.mname value=CL>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_mlist" VALUE="CL">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=misc.vane value=CL>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_vlist" VALUE="CL">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=misc.cmalls value=CL>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_cList" VALUE="CL">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=misc.amalls value=CL>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_aList" VALUE="CL">--%>
<%--</GX>--%>
<%----%>
<%--<!----------- Drop down list status fields and Check boxes------------>--%>
<%----%>
<%--<GX type=replace id=attribute.travelersChecks value=travelersChecksTransmit>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_travelersChecks" VALUE="travelersChecksTransmit">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attribute.decrementCard value=decrementCardTransmit>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_decrementCard" VALUE="decrementCardTransmit">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attribute.atm value=atmTransmit>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_atm" VALUE="atmTransmit">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attribute.convenienceCheck value=convenienceChecks>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_convenienceCheck" VALUE="convenienceChecks">--%>
<%--</GX>--%>
<%----%>
<%--<!------------- Program and Card Types ------------------------------->--%>
<%----%>
<%--<GX type=replace id=attributes.programType value=PT>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_programType" VALUE="PT">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=programs.integratedType value=PT>--%>
<%--  <INPUT TYPE="Hidden" NAME="rag_programType" value="PT">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=programs.programType value=PT>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_cardProgramType" value="PT">--%>
<%--</GX>--%>
<%----%>
<%--<!---------------------Number of Convience Checks------------------>--%>
<%----%>
<%--<GX type=replace id=checks.number10 value=N10>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_number10" VALUE="N10">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=checks.number20 value=N20>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_number20" VALUE="N20">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=checks.number50 value=N50>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_number50" VALUE="N50">--%>
<%--</GX>--%>
<%----%>
<%--<!-----------------------Invoice Medium Options  ------------------->--%>
<%----%>
<%--<GX type=replace id=medium.invoiceEagls value=IEAGLS>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_invoiceEagls" VALUE="IEAGLS">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=medium.invoicePaper value=IP>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_invoicePaper" VALUE="IP">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=medium.invoiceEdi value=IEDI>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_invoiceEdi" VALUE="IEDI">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=medium.invoiceElectronic value=IE>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_invoiceElectronic" VALUE="IE">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=card.numChecks10Transmit value=numChecks10Transmit>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_numConvenienceChecks10" VALUE="numChecks10Transmit">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=card.numChecks20Transmit value=numChecks20Transmit>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_numConvenienceChecks20" VALUE="numChecks20Transmit">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=card.numChecks50Transmit value=numChecks50Transmit>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_numConvenienceChecks50" VALUE="numChecks50Transmit">--%>
<%--</GX>--%>
<%----%>
<%--<GX type=replace id=attributes.creditLimit value=IC>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_creditLimit" VALUE="IC">--%>
<%--</GX>--%>
<%----%>
<%--<!-------------------- Need Credit Limit For Agency ------------------->--%>
<%----%>
<%--<GX type=replace id=agency.agencyCreditLimit value=CL>--%>
<%--  <INPUT TYPE="Hidden" NAME="hdn_agencyCreditLimit" VALUE="CL">--%>
<%--</GX>--%>
<%----%>
<%--<!---------- END HIDDEN FIELDS FOR INTERNAL ERROR CHECKING----->--%>



<!------------------------FORM ENDS------------------------->
<%@ include file = "/jsp/gsa/footer_systemDefault.jsp"%>

</html:form>
</BODY>
</HTML>
