<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ page import="com.boa.eagls.government.constants.web.CentralAccount" %>

<html:html>
<TITLE>MasterCard Vehicle Account Search Results</TITLE>

<META NAME="Name" CONTENT="setup_mcVehicleAccountSetup.html">
<META NAME="Description" CONTENT="Displays results of MasterCard Vehicle Account Search">
<META NAME="Author" CONTENT="Charlie Bruggemann">
<META NAME="Created" CONTENT="14 July 1998">
<META NAME="Revised" CONTENT="09/24/1998 Edited -BB">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<SCRIPT language="JavaScript">
<!-- Start Hiding

//SelectAccountGroup is called by the dynamic form associated with 'Browse' button
//SelectAccountGroup=ClearForm;

<!--*******************  CHECK CURRENCY  ***************************-->
 <!--*This function receives a form and field. It then checks to see*-->
 <!--*if the all the characters in the field are currency related   *-->
 <!--*Last Revision Date: 9/11/97 -BR                               *-->
 <!--****************************************************************-->

 function Initialize(theForm)
 {
   theForm.cmb_equipFuelLow.selectedIndex=0;
   theForm.cmb_cardType.selectedIndex=0;
 }

 function CheckCurrency(checkStr)
  {
   var checkOK="0123456789-+$,.";
   var allValid=true;

    for (i=0;  i < checkStr.length;  i++)
           {
            ch=checkStr.charAt(i);
            for (j=0;  j < checkOK.length;  j++)
              if (ch == checkOK.charAt(j))
                break;
            if (j == checkOK.length)
             {
              allValid=false;
              break;
             }
           }
     if (!allValid)
           {
            return ("error");
           }

     return ("ok");
 }

function RefreshAmount(theForm)
 {
  <!--**check for null values**-->

  amount1=(theForm.txt_creditLimit.value=="")?"0":theForm.txt_creditLimit.value;

  <!--**check for amounts that contain non-currency related char*-->

  if (CheckCurrency(amount1)=="error")
    {theForm.txt_creditLimit.focus();
     theForm.txt_creditLimit.select();
     return;
    }

  <!--**remove any currency tags**-->

  amount1=parent.logo.searchAndReplace(amount1,"$","",false,false);
  amount1=parent.logo.searchAndReplace(amount1,",","",false,false);

  <!--**add currency tags**-->

  theForm.txt_creditLimit.value=parent.logo.formatCurrency(amount1,18);

  return
 }

function chkCountry(theForm)
  {
   var cntry=theForm.txt_country.value;
   cntry=cntry.toUpperCase();
   if (cntry=="USA")
    {
     if((theForm.txt_state.value =="")||
        (parent.logo.checkAlphabetic(theForm,theForm.txt_state)=="error"))
        {
         return("error");
        }
    }
        return("true");
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

<!--*******************  FORM TEST  *******************************-->
<!--*This is the function that performs form level validation     *-->
<!--***************************************************************-->

function Form_Test(theForm)
 {
 var errormessage=parent.logo.ErrMsg_Header;

    // Hierarchy Error Checking
        if (theForm.txt_hl0)
           {
           if (((theForm.txt_hl0.value == "")||(theForm.txt_hl0.value == "0000000"))||
                 (parent.logo.checkNumeric(theForm,theForm.txt_hl0) == "error")||
                 ((parent.logo.checkLength(theForm,theForm.txt_hl0,7) == "error")&&
                 (theForm.txt_hl0.value != "")))
                 {
                  errormessage += (parent.logo.ErrMsg_ProgramNumber);
                 }
           if ((theForm.txt_hl0.value == "")&&
               (theForm.txt_hl1.value == "")&&
               (theForm.txt_hl2.value == "")&&
               (theForm.txt_hl3.value == "")&&
               (theForm.txt_hl4.value == "")&&
               (theForm.txt_hl5.value == "")&&
               (theForm.txt_hl6.value == "")&&
               (theForm.txt_hl7.value == "")&&
               (theForm.txt_hl8.value == ""))
               {
                errormessage += (parent.logo.ErrMsg_NoHierarchyEntered);
               }
           }
        else
           {

           if ((theForm.txt_hl1.value == "")&&
               (theForm.txt_hl2.value == "")&&
               (theForm.txt_hl3.value == "")&&
               (theForm.txt_hl4.value == "")&&
               (theForm.txt_hl5.value == "")&&
               (theForm.txt_hl6.value == "")&&
               (theForm.txt_hl7.value == "")&&
               (theForm.txt_hl8.value == ""))
               {
                errormessage += (parent.logo.ErrMsg_NoHierarchyEntered);
               }
        }
    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl1) == "error")||
        ((parent.logo.checkLength (theForm,theForm.txt_hl1,7) == "error")&&
        (theForm.txt_hl1.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel1);
        }

    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl2) == "error")||
        ((parent.logo.checkLength(theForm,theForm.txt_hl2,7) == "error")&&
        (theForm.txt_hl2.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel2);
        }

    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl3) == "error")||
        ((parent.logo.checkLength(theForm,theForm.txt_hl3,7) == "error")&&
        (theForm.txt_hl3.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel3);
        }

    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl4) == "error")||
        ((parent.logo.checkLength(theForm,theForm.txt_hl4,7) == "error")&&
        (theForm.txt_hl4.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel4);
        }

    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl5) == "error")||
        ((parent.logo.checkLength(theForm,theForm.txt_hl5,7) == "error")&&
        (theForm.txt_hl5.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel5);
        }
    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl6) == "error")||
        ((parent.logo.checkLength(theForm,theForm.txt_hl6,7) == "error")&&
        (theForm.txt_hl6.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel6);
        }

    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl7) == "error")||
        ((parent.logo.checkLength(theForm,theForm.txt_hl7,7) == "error")&&
        (theForm.txt_hl7.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel7);
        }

    if ((parent.logo.checkNumeric(theForm,theForm.txt_hl8) == "error")||
       ((parent.logo.checkLength(theForm,theForm.txt_hl8,7) == "error")&&
        (theForm.txt_hl8.value != "")))
        {
         errormessage += (parent.logo.ErrMsg_HierarchyLevel8);
        }

    if (theForm.txt_hl0)
        {
         if (parent.logo.checkHLSequence(theForm.txt_hl0, theForm.txt_hl1, theForm.txt_hl2,
                     theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                     theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
            {
               errormessage += (parent.logo.ErrMsg_HierarchyLevelSequence);
            }
        }
    else
        {
        if (parent.logo.checkHLSequence2(theForm.txt_hl1, theForm.txt_hl2,
                             theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                             theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
            {
               errormessage += (parent.logo.ErrMsg_HierarchyLevelSequence);
            }
        }

    if ((theForm.txt_equipmentType.value == "") ||
        (parent.logo.checkAlphaNumeric(theForm,theForm.txt_equipmentType) == "error"))
        {
         errormessage += parent.logo.ErrMsg_EquipmentType;
        }

    if ((theForm.txt_addressLine1.value == "")||
         (parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_addressLine1)=="error")||
         (parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_addressLine2)=="error")||
         (parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_addressLine3)=="error")||
         (parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_addressLine4)=="error"))
         {
          errormessage += parent.logo.ErrMsg_Address;
         }

     if ((theForm.txt_city.value == "")||
         (parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_city)=="error"))
         {
          errormessage += parent.logo.ErrMsg_City;
         }

     if ((theForm.txt_state.value.length < 2)||
         (parent.logo.checkAlphabetic(theForm,theForm.txt_state)=="error"))
         {
          errormessage += parent.logo.ErrMsg_State;
         }

     if ((theForm.txt_country.value == "")||
         (parent.logo.checkAlphabetic(theForm,theForm.txt_country)=="error"))
         {
          errormessage += parent.logo.ErrMsg_Country;
         }

     // Zip code is required for USA
	  	 if ((((theForm.txt_country.value.toUpperCase() == "USA") ||
	 		   (theForm.txt_country.value.toUpperCase() == "US")) &&
	  		   (theForm.txt_zip.value == "")) ||
	 		 (parent.logo.checkZipCode(theForm,theForm.txt_zip)=="error"))
	 		 {
	 		  errormessage += parent.logo.ErrMsg_Zip;
		 }

    if ((theForm.txt_creditLimit.value == "") ||
       // (theForm.txt_creditLimit.value == "$0.00") ||
        (CheckCurrency(theForm.txt_creditLimit.value)=="error"))
        {
        errormessage += parent.logo.ErrMsg_CreditLimit;
        }

    if ((theForm.txt_accountExpireDate.value == "") ||
        ((theForm.txt_accountExpireDate.value != "") &&
         (parent.logo.checkDate2(theForm,theForm.txt_accountExpireDate) == "error")))
        {
         errormessage += parent.logo.ErrMsg_AccountExpirationDate;
        }


    if ((theForm.txt_accountingCenterId.value == "")||
        (parent.logo.checkNumeric(theForm,theForm.txt_accountingCenterId) == "error"))
        {
         errormessage += parent.logo.ErrMsg_AccountingCenterID;
        }

    if ((theForm.txt_accountingCode.value != "")&&
        (checkAlphaNumericPlus(theForm,theForm.txt_accountingCode) == "error"))
        {
        errormessage += parent.logo.ErrMsg_MasterAccountingCode;
        }

    if (errormessage != parent.logo.ErrMsg_Header)
          {
            alert(errormessage);
            return false;
            }
    else  {
            return true;
            }
 }


//**********************************************************************
// WINDOW OPENING FUNCTIONS
//**********************************************************************

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

//**********************************************************************
// BROWSE WINDOW SELECT FUNCTIONS
//**********************************************************************

function selectAccountingCenterId(accountingCenterId)
    {
     document.mcVehicleSetupVerifyForm.txt_accountingCenterId.value=accountingCenterId;
    }

function selectMasterAccountingCode(masterAccountingCode)
    {
     document.mcVehicleSetupVerifyForm.txt_accountingCode.value=masterAccountingCode;
    }

function selectHierarchy(hierarchyNumber)
    {
     // Check to see if Hierarchy Level 0 exists and associate

     if (document.txt_hl0)
       {
        document.mcVehicleSetupVerifyForm.txt_hl0.value=hierarchyNumber.substring(0,7);
       }

     // Associate each Hierarchy Level with it's correct value

     document.mcVehicleSetupVerifyForm.txt_hl1.value=hierarchyNumber.substring(7,14);
     document.mcVehicleSetupVerifyForm.txt_hl2.value=hierarchyNumber.substring(14,21);
     document.mcVehicleSetupVerifyForm.txt_hl3.value=hierarchyNumber.substring(21,28);
     document.mcVehicleSetupVerifyForm.txt_hl4.value=hierarchyNumber.substring(28,35);
     document.mcVehicleSetupVerifyForm.txt_hl5.value=hierarchyNumber.substring(35,42);
     document.mcVehicleSetupVerifyForm.txt_hl6.value=hierarchyNumber.substring(42,49);
     document.mcVehicleSetupVerifyForm.txt_hl7.value=hierarchyNumber.substring(49,56);
     document.mcVehicleSetupVerifyForm.txt_hl8.value=hierarchyNumber.substring(56,63);
    }

//**********************************************************************
// BROWSE WINDOW STRING CREATION FUNCTIONS
//**********************************************************************

function createMasterAccountingCodeString(theForm,theUrl)
  {
   var errormessage=parent.logo.ErrMsg_Header;
   var urlString=theUrl+"?accountingCenterId=";

   if ((theForm.txt_accountingCenterId.value == "")||
       (parent.logo.checkAlphaNumeric(theForm,theForm.txt_accountingCenterId) == "error"))
     {
      errormessage += parent.logo.ErrMsg_AccountingCenterID;
     }

   if (errormessage != parent.logo.ErrMsg_Header)
     {
      alert(errormessage);
      return;
     }
   urlString += theForm.txt_accountingCenterId.value;
   openNewWindow_dualWindowDisplay(urlString);
  }
/*
function createAccountingCenterIDString(theForm,theUrl){
	 var errormessage=parent.logo.ErrMsg_Header;
     var hierarchyString=theUrl+"?availableHierarchiesList=";
     hierarchyString+="CurrentUserHierarchy";
     return openNewWindow(hierarchyString);
}
*/
function createHierarchyString(theForm,theUrl,theBrowseType)
  {
   var errormessage=parent.logo.ErrMsg_Header;
   var hierarchyString=theUrl+"?availableHierarchiesList=";

   //
   // If browsing for accounting center id's check the following
   //

   if (theBrowseType == "accountingCenterId")
     {
      if (theForm.txt_hl0)
        {
         if ((theForm.txt_hl0.value == "")||
             (theForm.txt_hl1.value == ""))
           {
            errormessage += "Program Number and HL1 are required";
           }
        }
      else
        {
         if (theForm.txt_hl1.value == "")
           {
            errormessage += "HL1 is required";
           }
         }

      if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return;
         }

    }  // END ACCOUNTING CENTER ID CHECK

   // Check to see if Program Number (HL0) exists

   if (theForm.txt_hl0)
     {
      if (((theForm.txt_hl0.value == "")||(theForm.txt_hl0.value == "0000000"))||
          ((theForm.txt_hl0.value != "")&&
          ((parent.logo.checkLength(theForm,theForm.txt_hl0,7) == "error")||
           (parent.logo.checkNumeric(theForm,theForm.txt_hl0) == "error"))))
            {errormessage += parent.logo.ErrMsg_ProgramNumber;}
     }

   // This section checks the standard HL1 - HL8

   if ((theForm.txt_hl1.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl1) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel1;}

   if ((theForm.txt_hl2.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl2,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl2) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel2;}

   if ((theForm.txt_hl3.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl3,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl3) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel3;}

   if ((theForm.txt_hl4.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl4,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl4) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel4;}

   if ((theForm.txt_hl5.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl5,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl5) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel5;}

   if ((theForm.txt_hl6.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl6) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel6;}

   if ((theForm.txt_hl7.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl7,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl7) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel7;}

   if ((theForm.txt_hl8.value != "")&&
       ((parent.logo.checkLength(theForm,theForm.txt_hl8,7) == "error")||
        (parent.logo.checkNumeric(theForm,theForm.txt_hl8) == "error")))
        {errormessage += parent.logo.ErrMsg_HierarchyLevel8;}

   // Check to see which HL Sequence Test to perform

   if (theForm.txt_hl0)
     {
      if (parent.logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,theForm.txt_hl2,
                          theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                          theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
        {errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;}
     }
   else
     {
      if (parent.logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
                          theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                          theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
        {errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;}
     }

   // If no errors occurred create HL string, else display errors

   if (errormessage == parent.logo.ErrMsg_Header)
     {
      hierarchyString += (theForm.txt_hl0.value == "") ? "0000000" : theForm.txt_hl0.value;


      // Assign "0000000" to null fields to complete HL String

      hierarchyString += (theForm.txt_hl1.value == "") ? "0000000" : theForm.txt_hl1.value;
      hierarchyString += (theForm.txt_hl2.value == "") ? "0000000" : theForm.txt_hl2.value;
      hierarchyString += (theForm.txt_hl3.value == "") ? "0000000" : theForm.txt_hl3.value;
      hierarchyString += (theForm.txt_hl4.value == "") ? "0000000" : theForm.txt_hl4.value;
      hierarchyString += (theForm.txt_hl5.value == "") ? "0000000" : theForm.txt_hl5.value;
      hierarchyString += (theForm.txt_hl6.value == "") ? "0000000" : theForm.txt_hl6.value;
      hierarchyString += (theForm.txt_hl7.value == "") ? "0000000" : theForm.txt_hl7.value;
      hierarchyString += (theForm.txt_hl8.value == "") ? "0000000" : theForm.txt_hl8.value;

      openNewWindow(hierarchyString);
     }
   else
     {
      alert(errormessage);
     }


  } // End of createHierarchyString

<!--*******************  resetFix       ****************************-->
<!--*This function is a workaround for a bug in IE3 where a RESET  *-->
<!--*is not allowing the text inputs to be populated from popup    *-->
<!--*Function is being called from onClick event of Reset button!  *-->
<!--****************************************************************-->

function resetFix( theForm )
{
     //theForm.txt_hl1.value = "";
     theForm.txt_hl2.value = "";
     theForm.txt_hl3.value = "";
     theForm.txt_hl4.value = "";
     theForm.txt_hl5.value = "";
     theForm.txt_hl6.value = "";
     theForm.txt_hl7.value = "";
     theForm.txt_hl8.value = "";

}  // resetFix


// Stop Hiding -->
</SCRIPT>

</HEAD>

<!------------------------------------------------------------------------->

<BODY onLoad="Initialize(document.mcVehicleSetupVerifyForm);">
<jsp:useBean id="centralAcct"
	class="com.boa.eagls.government.dto.account.Account" scope="request" />
<jsp:useBean id="centralOffice"
	class="com.boa.eagls.government.dto.CentralOffice" scope="request" />
<jsp:useBean id="agencyCore"
	class="com.boa.eagls.government.dto.agency.AgencyCore" scope="request" />

<html:form
	action="/verifyVehicleAccount"
    method="Post"
    onsubmit="return Form_Test(document.mcVehicleSetupVerifyForm);">

<TABLE WIDTH="575">
    <TR>
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Vehicle Account Setup<BR>
          Search Results
        </H2>
      </TD>
    </TR>
</TABLE>

<html:hidden value="Vehicle Account Setup" property="txt_screenTitle1"/>

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>
<BR>

<!------------------------------------------------------------------------->

<FONT COLOR="#0000FF" FACE="Arial, helvetica"><B>Hierarchy Information</B></FONT>

 <TABLE BORDER="1" CELLSPACING="0" CELLPADDING="0">
    <TR>
      <TD WIDTH="144" ALIGN="Left">Hierarchy Level</TD>
      <TD WIDTH="427">
        <INPUT TYPE="Button" VALUE="Browse" NAME="but_browseHierarchy"
               onclick="createHierarchyString(document.mcVehicleSetupVerifyForm,
			   	'<%=request.getContextPath()%>/browseHierarchy.do','hierarchy')">
      </TD>
    </TR>
</TABLE>
<BR>

<%@ include file="/jsp/gsa/common/right_hierarchy.jsp"%>
<BR>

<!--------------------------------------------------------------->

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Central Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TH WIDTH="213"  ALIGN="Left">
        Central Account Name
      </TH>
      <TD WIDTH="352">
        <%=centralAcct.getAcctName() %>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH  ALIGN="Left">
        Central Account Number
      </TH>
      <TD>
        <%=centralAcct.getAcctNo() %>&nbsp;
      </TD>
    </TR>
    <TR>
      <TH  ALIGN="Left">
        Central Account ID
      </TH>
      <TD>
        <%=centralAcct.getCentralAcctID() %>&nbsp;
      </TD>
    </TR>
</TABLE>
<BR>
<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left"><B>Accounting Center ID</B></TD>
      <TD WIDTH="352">
      	<html:text property='txt_accountingCenterId' value='<%=centralAcct.getAccountingCenterID() %>'
    		onfocus="parent.logo.Display_Message(parent.logo.Status_Text_AccountingCenterID);"
						size="13" maxlength="12"/>

    &nbsp;
       	<INPUT TYPE="Button" NAME="but_browseAccountingCenterId" VALUE="Browse"
             onclick="createHierarchyString(document.mcVehicleSetupVerifyForm,
			 '<%=request.getContextPath()%>/common/browseAccountingCenterIds.do','accountingCenterId')">
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">Master Accounting Code</TD>
      <TD>
        <INPUT TYPE="Button" NAME="but_browseAccountingCode" VALUE="Browse"
			onclick="createMasterAccountingCodeString(document.mcVehicleSetupVerifyForm,
			'<%=request.getContextPath()%>/common/framesAccountingCodeBrowseAction.do')">
      </TD>
    </TR>
    <TR>
      <TD COLSPAN="2">
        <html:text property='txt_accountingCode' value=''
    		onfocus="parent.logo.Display_Message(parent.logo.Status_Text_MasterAccountingCode);"
						size="65" maxlength="64"/>
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Account Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"  ALIGN="Left">
        <B>Program Type</B>
      </TD>
      <TD WIDTH="352">
        <%=centralAcct.getProgramTypeDescription() %>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD  ALIGN="Left">
        <B>Billing Type</B>
      </TD>
      <TD ALIGN="Left">
        <%=centralAcct.getBillingTypeDescription() %>&nbsp;
      </TD>
    </TR>
    <TR>
      <TD  ALIGN="Left">
        <B>Card/Non-Card</B>
      </TD>
      <TD>
        Card
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left"><B>Generate Paper Statements</B></TD>
      <TD WIDTH="352">
          <INPUT TYPE="Radio" NAME="rag_generatePaper" VALUE="generatePaperYes"
                 onClick="parent.logo.Display_Message(parent.logo.Status_Radio_GeneratePaperYes);">Yes
          <INPUT TYPE="Radio" NAME="rag_generatePaper" VALUE="generatePaperNo" CHECKED
                 onClick="parent.logo.Display_Message(parent.logo.Status_Radio_GeneratePaperNo);">No
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
    <B>Card Type</B>
      </TD>
      <TD>
        <select name="cmb_cardType" size="1"
                onfocus="parent.logo.Display_Message(parent.logo.Status_Text_CardType)">
		  <logic:iterate id="item" collection="<%=agencyCore.getCardTypes() %>"
		  	type="com.boa.eagls.government.dto.NameDescTable">
			<option value="<%=item.getSelectString()%>">
				<%=item.getDescription()%>
			</option>
		  </logic:iterate>
        </select>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Credit Limit</B>
      </TD>
      <TD>
        <html:text property='txt_creditLimit' value='$0.00'
    		onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Credit_Limit);"
               onchange="RefreshAmount(mcVehicleSetupVerifyForm)"
						size="18" maxlength="18"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Account Expiration Date</B>
      </TD>
      <TD>
        <INPUT TYPE="Text" NAME="txt_accountExpireDate" SIZE="8" MAXLENGTH="7"
               VALUE="<%=request.getAttribute(CentralAccount.EXP_DATE_STRING)%>"
               onFocus="parent.logo.Display_Message(parent.logo.Status_Text_AccountExpirationDate);" >

        &nbsp;&nbsp;(MM/YYYY)
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Equipment Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213"  ALIGN="Left">
        <B>Equipment ID</B>
      </TD>
      <TD WIDTH="352" ALIGN="Left">

	  <html:text property='txt_equipmentId'
	  	value='<%=request.getParameter("txt_equipmentId") %>'
		size="21" maxlength="20"/>
      </TD>
    </TR>
</TABLE>
<BR>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left"><B>Equipment Type</B></TD>
      <TD WIDTH="352">
          <html:text property='txt_equipmentType'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_EquipmentType);"
	  		value='<%=request.getParameter("txt_equipmentId") %>'
			size="16" maxlength="15"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        <B>Equipment Fuel Type Low</B>
      </TD>
      <TD>
		<select name="cmb_equipFuelLow" size="1"
                onfocus="parent.logo.Display_Message(parent.logo.Status_Text_EquipmentFuelType)">
		  <logic:iterate id="item" collection="<%=request.getAttribute(CentralAccount.NAME_DESC_TABLE)%>"
		  	type="com.boa.eagls.government.dto.NameDescTable">
			<option value="<%=item.getSelectString()%>">
				<%=item.getDescription()%>
			</option>
		  </logic:iterate>
		</select>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">
        Equipment Fuel Type High
      </TD>
      <TD>
		<select name="cmb_equipFuelHigh" size="1"
                onfocus="parent.logo.Display_Message(parent.logo.Status_Text_EquipmentFuelType)">
		  <logic:iterate id="item" collection="<%=request.getAttribute(CentralAccount.NAME_DESC_TABLE)%>"
		  	type="com.boa.eagls.government.dto.NameDescTable">
			<option value="<%=item.getSelectString()%>">
				<%=item.getDescription()%>
			</option>
		  </logic:iterate>
		</select>
      </TD>
    </TR>
</TABLE>

<!--------------------------------------------------------------->

<BR><html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/><BR><BR>

<!--------------------------------------------------------------->

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Address and Contact Information</B></FONT>

<TABLE BORDER="1">
    <TR>
      <TD WIDTH="213" ALIGN="Left"><B>Address  1</B></TD>
      <TD WIDTH="352">
      	<html:text property='txt_addressLine1'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Address);"
	  		value='<%=centralOffice.getAddress1()%>'
			size="41" maxlength="40"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">Address  2</TD>
      <TD>
        <html:text property='txt_addressLine2'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Address2);"
	  		value='<%=centralOffice.getAddress2()%>'
			size="41" maxlength="40"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">Address  3</TD>
      <TD>
        <html:text property='txt_addressLine3'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Address3);"
	  		value='<%=centralOffice.getAddress3()%>'
			size="41" maxlength="40"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left">Address  4</TD>
      <TD>
        <html:text property='txt_addressLine4'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Address4);"
	  		value='<%=centralOffice.getAddress3()%>'
			size="41" maxlength="40"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left"><B>City</B></TD>
      <TD>
        <html:text property='txt_city'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_City);"
	  		value='<%=centralOffice.getCity() %>'
			size="21" maxlength="20"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left"><B>State/Province</B></TD>
      <TD>
        <html:text property='txt_state'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_State);"
	  		value='<%=centralOffice.getStateOrProvince()%>'
			size="4" maxlength="3"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left"><B>Country</B></TD>
      <TD>
        <html:text property='txt_country'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Country);"
	  		value='<%=centralOffice.getCountry() %>'
			size="4" maxlength="3"/>
      </TD>
    </TR>
    <TR>
      <TD ALIGN="Left"><B>Zip/Postal Code</B></TD>
      <TD>
        <html:text property='txt_zip'
		  	onfocus="parent.logo.Display_Message(parent.logo.Status_Text_Zip);"
	  		value='<%=centralOffice.getZip() %>'
			size="14" maxlength="13"/>
      </TD>
    </TR>
</TABLE>

<!----------------------------- Hidden Variables -------------------->

<html:hidden value="<%=centralAcct.getAgencyName()%>" property="hdn_agencyName"/>
<html:hidden value="<%=centralAcct.getAcctName()%>" property="hdn_centralName"/>
<html:hidden value="<%=centralAcct.getAcctNo() %>" property="hdn_centralNumber"/>
<html:hidden value="<%=centralAcct.getCentralAcctID() %>" property="hdn_centralId"/>
<html:hidden value="<%=request.getParameter(\"txt_equipmentId\") %>" property="hdn_equipmentId"/>
<html:hidden value="<%=centralAcct.getProgramTypeDescription() %>" property="hdn_programType"/>
<INPUT TYPE="Hidden" NAME="hdn_billingType" VALUE="<%=centralAcct.getBillingType()%>">

<html:hidden value="convenienceChecksTransmit" property="hdn_convenienceChecks"/>
<html:hidden value="cardTransmit" property="hdn_card"/>
<html:hidden value="" property="hdn_year"/>
<html:hidden value="" property="hdn_month"/>

<!--------------------------------------------------------------------->

<BR>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
	  	<html:submit value="Submit" property="but_submit"/>
        &nbsp;&nbsp;
		<html:reset value="Clear" property="but_clear" 
			onclick="resetHierarchy(document.mcVehicleSetupVerifyForm);"/>
        </TD>
    </TR>
</TABLE>

<SMALL><B>Bold Fields Are Required</B></SMALL><BR>

<!--------------------------------------------------------------------->

<template:insert template="/jsp/gsa/common/footer_systemDefault.jspf" />

</html:form>
</BODY>
</html:html>
