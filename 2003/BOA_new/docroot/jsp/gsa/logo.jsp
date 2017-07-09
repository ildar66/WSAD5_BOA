<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<HTML>
<HEAD>
<TITLE></TITLE>
<META NAME="Generator" CONTENT="Microsoft FrontPage 4.0">
<META NAME="Author" CONTENT="?">
<META NAME="Keywords" CONTENT="?">
<META NAME="Description" CONTENT="?">

<script src="<%=request.getContextPath()%>/jsp/gsa/scripts/appGlobal.js"></script>
<script language="javascript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}


<!-- starting hiding

var IE = document.all;


<!--*******************  MESSAGE BLOCK  ***************************-->
<!--*This area defines the global message variables used in all   *-->
<!--*Eagls application screens.                                   *-->
<!--***************************************************************-->

var Confirm_Msg = "Your request has been Submitted";


<!--**Error Messages**-->
var ErrMsg_UserRole = "Invalid User Role\n";
var ErrMsg_RoleName = "Invalid Role Name\n";
var ErrMsg_TableDescriptionName = "You must enter both a Table Name and Description\n";
var ErrMsg_DescriptionName = "You must enter a Description Name\n";
var ErrMsg_abaRoutingNumber = "Invalid ABA Routing Number\n";
var ErrMsg_ddaNumber = "Invalid DDA Number\n";
var ErrMsg_Quantity = "Invalid Quantity\n";
var ErrMsg_ProgramNumber = "Invalid Program Number\n";
var ErrMsg_ProgramDescription = "Invalid Program Description\n";
var ErrMsg_AgentCode = "Invalid Agent Code\n";
var ErrMsg_Task_orderNumber = "Invalid Task Order Number\n";
var ErrMsg_UserID = "Invalid User ID\n";
var ErrMsg_ALC = "Invalid Agency Location Code\n";
var ErrMsg_AgencyName = "Invalid Agency Name\n";
var ErrMsg_Username = "Invalid Username\n";
var ErrMsg_Password = "Invalid Password\n";
var ErrMsg_AccountNumber = "Invalid Account Number\n";
var ErrMsg_AccountName = "Invalid Account Name\n";
var ErrMsg_AccountExpirationDate = "Invalid Account Expiration Date\n";
var ErrMsg_AccountingCenterID = "Invalid Accounting Center ID\n";
var ErrMsg_LastName = "Invalid Last Name\n";
var ErrMsg_FirstName = "Invalid First Name\n";
var ErrMsg_UserName = "Invalid User Name\n";
var ErrMsg_NameCombination = "Invalid Name Combination\n";
var ErrMsg_SSN = "Invalid Social Security Number\n";
var ErrMsg_BeginDate = "Invalid Transaction Beginning Date\n";
var ErrMsg_Date = "Invalid Date\n";
var ErrMsg_ActivateDate = "You Must Enter At Least One Date\n";
var ErrMsg_EndDate = "Invalid Transaction Ending Date\n";
var ErrMsg_DateRange = "Invalid Date Range\n";
var ErrMsg_DailyAmount = "Invalid Daily Amount\n";
var ErrMsg_MonthlyAmount = "Invalid Monthly Amount\n";
var ErrMsg_CycleAmount = "Invalid Cycle Amount\n";
var ErrMsg_SingleAmount = "Invalid Single Amount\n";
var ErrMsg_DailyNumber = "Invalid Daily Number\n";
var ErrMsg_MonthlyNumber = "Invalid Monthly Number\n";
var ErrMsg_CycleNumber = "Invalid Cycle Number\n";
var ErrMsg_TransactionAmount = "Invalid Transaction Amount\n";
var ErrMsg_TransactionNumber = "Invalid Transaction Number\n";
var ErrMsg_IntervalLength = "Invalid Interval Length\n";
var ErrMsg_TransactionAmount = "Invalid Transaction Amount\n";
var ErrMsg_Vendor = "Invalid Vendor Table Name\n";
var ErrMsg_AgencyNumber = "Invalid Agency Number\n";
var ErrMsg_Address = "Invalid Address\n";
var ErrMsg_City = "Invalid City\n";
var ErrMsg_State = "Invalid State/Province\n";
var ErrMsg_FaxNumber = "Invalid Fax Number\n";
var ErrMsg_Country = "Invalid Country\n";
var ErrMsg_Zip = "Invalid Zip/Postal Code\n";
var ErrMsg_Activation = "Invalid Activation Code\n";
var ErrMsg_CreditLimit = "Invalid Credit Limit\n";
var ErrMsg_CashLimit = "Invalid Cash Limit\n";
var ErrMsg_MCC = "Invalid MCC\n";
var ErrMsg_MCC_Range = "Invalid MCC Range\n";
var ErrMsg_Occ = "Invalid Object Class Code\n";
var ErrMsg_FundingCode = "Invalid Funding Code\n";
var ErrMsg_Month = "Invalid Month Value\n";
var ErrMsg_Quarter = "Invalid Quarter Value\n";
var ErrMsg_requiredField = "All Field Names in Bold must be entered\n";

var ErrMsg_Header = "\nThe request you have submitted has missing or invalid information.\n";
ErrMsg_Header += "Please return to the request screen and correct the following...\n \n";

// THESE MESSAGE WERE ADDED FOR EAGLS 2.0

var ErrMsg_DisputeAmoumt = "Invalid Dispute Amount\n";
var ErrMsg_TableName1 = "You must enter a Table Name for row 1\n";
var ErrMsg_TableName2 = "You must enter a Table Name for row 2\n";
var ErrMsg_TableName3 = "You must enter a Table Name for row 3\n";
var ErrMsg_TableName4 = "You must enter a Table Name for row 4\n";
var ErrMsg_TableName5 = "You must enter a Table Name for row 5\n";
var ErrMsg_DescriptionName1 = "You must enter a Table Description for row 1\n";
var ErrMsg_DescriptionName2 = "You must enter a Table Description for row 2\n";
var ErrMsg_DescriptionName3 = "You must enter a Table Description for row 3\n";
var ErrMsg_DescriptionName4 = "You must enter a Table Description for row 4\n";
var ErrMsg_DescriptionName5 = "You must enter a Table Description for row 5\n";
var ErrMsg_requiredField = "All Field Names in Bold must be entered\n";
var ErrMsg_CentralAccountName = "Invalid Central Account Name\n";
var ErrMsg_Email = "Invalid E-mail Address\n";
var ErrMsg_Agent = "Invalid Agent Number\n";
var ErrMsg_Primary_Contact = "Invalid Primary Contact\n";
var ErrMsg_Primary_APC = "Invalid Primary APC\n";
var ErrMsg_Sol = "Invalid Sol Number\n";
var ErrMsg_BasisPoints = "Invalid Basis Points\n";
var ErrMsg_IndividualBasisPoints = "Invalid Individual Basis Points\n";
var ErrMsg_ElectronicPaymentBasisPoints = "Invalid Electronic Payment Basis Points\n";
var ErrMsg_FundingFee = "Invalid Funding Fee\n";
var ErrMsg_InvoiceRebate = "Invalid Invoice\n";
var ErrMsg_ElectronicPaymentRebate = "Invalid Electronic Payment Rebate\n";
var ErrMsg_ContractExpensesRebate = "Invalid Contract Related Expenses Rebate\n";
var ErrMsg_ProductsAndServices = "Invalid Products and Services Rebate\n";
var ErrMsg_OtherOptions = "No Options Selected\n";
var ErrMsg_HomeNumber = "Invalid Home Telephone Number\n";
var ErrMsg_BusinessPhone = "Invalid Business Telephone Number\n";
var ErrMsg_FaxNumber = "Invalid Fax Number\n";
var ErrMsg_CentralAccountID = "Invalid Central Account ID\n";
var ErrMsg_CentralAccountNumber = "Invalid Central Account Number\n";
var ErrMsg_AccountID = "Invalid Account ID\n";
var ErrMsg_EmployeeID = "Invalid Employee ID\n";
var ErrMsg_MasterAccountingCode = "Invalid Master Accounting Code\n";
var ErrMsg_OfficeName = "Invalid Office Name\n";
var ErrMsg_HierarchyLevel = "Invalid Hierarchy Level\n";
var ErrMsg_HierarchyLevelDescription = "Invalid Hierarchy Level Description\n";
var ErrMsg_HierarchyLevel1 = "Invalid Hierarchy Level 1\n";
var ErrMsg_HierarchyLevel2 = "Invalid Hierarchy Level 2\n";
var ErrMsg_HierarchyLevel3 = "Invalid Hierarchy Level 3\n";
var ErrMsg_HierarchyLevel4 = "Invalid Hierarchy Level 4\n";
var ErrMsg_HierarchyLevel5 = "Invalid Hierarchy Level 5\n";
var ErrMsg_HierarchyLevel6 = "Invalid Hierarchy Level 6\n";
var ErrMsg_HierarchyLevel7 = "Invalid Hierarchy Level 7\n";
var ErrMsg_HierarchyLevel8 = "Invalid Hierarchy Level 8\n";
var ErrMsg_HierarchyLevel9 = "Invalid Hierarchy Level 9\n";
var ErrMsg_HierarchyLevelSequence = "Invalid Hierarchy Level Sequence\n";
var ErrMsg_NoHierarchyEntered = "You must enter at least one Hierarchy Level\n";
var ErrMsg_EquipmentID = "Invalid Equipment ID\n";
var ErrMsg_EquipmentType = "Invalid Equipment Type\n";
var ErrMsg_AccountingCenterID = "Invalid Accounting Center ID\n";
var ErrMsg_ProgramType = "Invalid Program Type\n";
var ErrMsg_AccountExpireDate = "Invalid Account Expiration Date\n";
var ErrMsg_Task_orderNumber = "Invalid Task Order Number\n";
var ErrMsg_Text_UserId = "Invalid User ID\n";
var ErrMsg_SinglePurchaseLimit = "Invalid Single Purchase Limit\n";
var ErrMsg_DailyTransactionLimit = "Invalid Daily Transaction Limit\n";
var ErrMsg_DailyAmountLimit = "Invalid Daily Amount Limit\n";
var ErrMsg_CycleTransactionLimit = "Invalid Cycle Transaction Limit\n";
var ErrMsg_CycleAmountLimit = "Invalid Cycle Amount Limit\n";
var ErrMsg_MonthTransactionLimit = "Invalid Monthly Transaction Limit\n";
var ErrMsg_MonthAmountLimit = "Invalid Monthly Amount Limit\n";
var ErrMsg_OtherTransactionLimit = "Invalid Other Transaction Limit\n";
var ErrMsg_OtherAmountLimit = "Invalid Other Amount Limit\n";
var ErrMsg_OtherTotalNumberDays = "Invalid Other Total Days to Refresh Value\n";
var ErrMsg_OtherRefreshDate = "Invalid Other Refresh Date\n";
var ErrMsg_NewAtmPin = "Invalid New PIN Number\n";
var ErrMsg_CurrentAtmPin = "Invalid Current PIN Number\n";
var ErrMsg_DateFormat = "Invalid Date Format\n";
var ErrMsg_CentralOffice = "Central Office Name\n";
var ErrMsg_CardType1 = "All CMID fields must be filled before adding more entries\n";
var ErrMsg_CardTypeDesc1 = "All CMID description fields must be filled before adding more entries\n";
var ErrMsg_EMall1 = "All E-Mall fields must be filled before adding more entries\n";
var ErrMsg_EMallDesc1 = "All E-Mall description fields must be filled before adding more entries\n";
var ErrMsg_EmploymentStatus1 = "All Employment Status fields must be filled before adding more entries\n";
var ErrMsg_GradeRank1 = "All Grade or Rank fields must be filled before adding more entries\n";
var ErrMsg_deletedEntries = "Existing entries can not be deleted\n";
var ErrMsg_GradeRank = "Invalid Grade or Rank\n";
var ErrMsg_EmploymentStatus = "Invalid Employment Status\n";
var ErrMsg_EMall = "Invalid E-mall\n";
var ErrMsg_EMallDesc = "Invalid E-mall Description\n";
var ErrMsg_CardType = "Invalid Card Type\n";
var ErrMsg_CardTypeDesc = "Invalid Card Type Description\n";
var ErrMsg_AgencyID = "Invalid Agency ID\n";
var ErrMsg_TelephoneNumber = "Invalid Telephone Number\n";
var ErrMsg_AllocationAmountCode = "Allocation amounts must have an accounting code\n";
var ErrMsg_AllocationTotal = "Allocation amounts must total to the transaction amount\n";
var ErrMsg_AccountingCode = "Invalid Accounting Code\n";
var ErrMsg_AllocationComment = "Invalid Allocation Comment\n";
var ErrMsg_BlankAccountingCode = "Accounting Code can not be blank\n";
var ErrMsg_NewAccountingCode = "A new accounting code must be selected before adding it to the list\n";
var ErrMsg_AddAccountingCode = "The space must be empty before adding an accounting code\n";
var ErrMsg_NoAllocationComment = "There is no allocation comment to delete\n";
var ErrMsg_VoyagerNumber = "Invalid Voyager Number\n";
var ErrMsg_VoyagerNumber = "Invalid Voyager Number\n";
var ErrMsg_RoleDescription = "Invalid Role Description\n";
var ErrMsg_NegativeAmount = "Negative amounts are not allowed.\n";
<!--**Status Bar Messages, Text Fields**-->

var Status_Text_Username = "Enter Login";
var Status_Text_RoleName = "Enter Role Name";
var Status_Text_EDIAddress = "Enter EDI Address";
var Status_Text_Password = "Enter Password";
var Status_Text_AccountNumber = "Enter Account Number";
var Status_Text_CentralAccountNumber = "Enter Central Account Number";
var Status_Text_CentralOfficeName = "Enter Central Office Name";
var Status_Text_CentralAccountName = "Enter Central Account Name";
var Status_Text_MCCGTableName = "Enter Valid MCCG Table Name"
var Status_Text_MCCGTableDescription = "Enter Valid MCCG Table Description"
var Status_Text_AccountID = "Enter Account ID";
var Status_Text_AccountingCenterID = "Enter Accounting Center ID";
var Status_Text_CentralAccountID = "Enter Central Account ID";
var Status_Text_LastName = "Enter Last Name";
var Status_Text_FirstName = "Enter First Name";
var Status_Text_UserName = "Enter User Name";
var Status_Text_Payment_Office_Name = "Enter Payment Office Name";
var Status_Text_Telephone_AreaCode = "Enter Telephone Area Code";
var Status_Text_Telephone_Prefix = "Enter Telephone Prefix";
var Status_Text_Telephone_Extension = "Enter Telephone Extension";
var Status_Text_Business_Phone = "Enter Business Phone Number";
var Status_Text_SSN = "Enter Social Security Number";
var Status_Text_Date_Month = "Enter Month (MM)";
var Status_Text_Date_Day = "Enter Day (DD)";
var Status_Text_Date_Year = "Enter Year (YYYY)";
var Status_Text_ProgramDescription = "Enter Program Description";
var Status_Text_HierarchyRange = "Enter Hierarchy Range";
var Status_Text_HierarchyDepth = "Enter Hierarchy Depth";
var Status_Text_HierarchyLevel = "Enter Hierarchy Level";
var Status_Text_HierarchyDescription = "Enter Hierarchy Description";
var Status_Text_ProgramNumber = "Enter Program Number"
var Status_Text_AgencyName = "Enter Agency Name";
var Status_Text_AgencyID = "Enter Agency ID";
var Status_Text_ALC = "Enter Agency Location Code";
var Status_Text_AccountHolderName = "Enter Account Holder Name";
var Status_Text_Username = "Enter User Name";
var Status_Text_DailyAmount = "Enter Daily Amount";
var Status_Text_MonthlyAmount = "Enter Monthly Amount";
var Status_Text_CycleAmount = "Enter Cycle Amount";
var Status_Text_SingleAmount = "Enter Single Amount";
var Status_Text_DailyNumber = "Enter Daily Number";
var Status_Text_MonthlyNumber = "Enter Monthly Number";
var Status_Text_CycleNumber = "Enter Cycle Number";
var Status_Text_TransactionAmount = "Enter Transaction Amount";
var Status_Text_TransactionNumber = "Enter Transaction Number";
var Status_Text_IntervalLength = "Enter Interval Length";
var Status_Text_BasisPoints = "Enter Basis Points";
var Status_Text_FundingFee = "Enter Funding Fee";
var Status_Text_InvoiceRebate = "Enter Invoice Rebate";
var Status_Text_ElectronicPaymentRebate = "Enter Electronic Paymnet Rebate";
var Status_Text_ContractExpensesRebate = "Enter Contract Related Expenses Rebate";
var Status_Text_ProductsAndServices = "Enter Products and Services Rebate";
var Status_Text_Vendor = "Enter Preferred Vendor Table Name";
var Status_Text_AgencyNumber = "Enter Agency Number";
var Status_Text_Address = "Enter Address";
var Status_Text_AddressLine2 = "Enter Address Line2";
var Status_Text_AddressLine3 = "Enter Address Line3";
var Status_Text_AddressLine4 = "Enter Address Line4";
var Status_Text_City = "Enter City";
var Status_Text_State = "Enter State/Province Abbreviation";
var Status_Text_Zip = "Enter Zip Code";
var Status_Text_Fax_AreaCode = "Enter Fax Area Code";
var Status_Text_Fax_Prefix = "Enter Fax Prefix";
var Status_Text_Fax_Extension = "Enter Fax Extension";
var Status_Text_Fax_Number = "Enter Fax Number";
var Status_Text_Email_Address = "Enter EMail Address";
var Status_Text_Credit_Limit = "Enter Credit Limit";
var Status_Text_Country = "Enter Country";
var Status_Text_AgentNumber = "Enter Agent Number";
var Status_Text_PrimaryContact = "Enter Primary Contact";
var Status_Text_TransactionDisputeOffice = "Enter Transaction Dispute Name";
var Status_Text_PrimaryAPC = "Enter Primary APC";
var Status_Text_SecondaryAPC = "Enter Secondary APC";
var Status_Text_MCC = "Enter Valid MCC";
var Status_Text_Occ = "Enter Object Class Code";
var Status_Text_FundingCode = "Enter Funding Code";
var Status_TransactionAmount = "Enter Transaction Amount";
var Status_Text_Date_Quarter = "Enter Quarter Value";
var Status_Text_Activation_Code = "Enter Activation Code";
var Status_Text_SOLNumber = "Enter SOL Number";
var Status_Text_Sort = "Select Sort By Option";

<!--**New Status Bar Messages, Text Fields for EAGLS 2.0**-->

var Status_Text_UserStatus = "Select User Status";
var Status_Text_AgentCode = "Enter Agent Code";
var Status_Text_AgencyInventory = "Select Agency Inventory";
var Status_Text_OfficeName = "Enter Office Name";
var Status_Text_Quantity = "Enter Quantity";
var Status_Text_Fax_Number = "Enter Fax Number";
var Status_Text_Worktelephone = "Enter Work Telephone Number";
var Status_Text_Hometelephone = "Enter Home Telephone Number";
var Status_Text_Newsletter_Medium ="Select Newsletter Medium";
var Status_Text_Taskorder_number = "Enter Task Order Number";
var Status_Text_UserId = "Enter User ID";
var Status_Text_EmployeeID = "Enter Employee ID";
var Status_Text_EquipmentID = "Enter Equipment ID";
var Status_Text_EquipmentType = "Enter Equipment Type";
var Status_Text_EquipmentFuelType = "Select Equipment Fuel Type";
var Status_Text_AccountExpirationDate = "Enter Account Expiration Date";
var Status_Text_AccountingCenterID = "Enter Accounting Center ID";
var Status_Text_MasterAccountingCode = "Enter Master Accounting Code";
var Status_Text_CardType = "Select Card Type";
var Status_Text_CreditChecks = "Select Credit Checks";
var Status_Text_TravelersChecks = "Select Travelers Checks";
var Status_Text_BillingOption = "Select Billing Option";
var Status_Text_TimeLimit = "Enter Time Limit";
var Status_Text_SinglePurchaseLimit = "Enter Single Purchase Limit";
var Status_Text_DailyTransLimit = "Enter Daily Transaction Limit";
var Status_Text_CycleTransLimit = "Enter Cycle Transaction Limit";
var Status_Text_DailyAmountLimit = "Enter Daily Amount Limit";
var Status_Text_CycleAmountLimit = "Enter Cycle Amount Limit";
var Status_Text_MonthTransLimit = "Enter Monthly Transaction Limit";
var Status_Text_OtherTransLimit = "Enter Other Transaction Limit";
var Status_Text_MonthAmountLimit = "Enter Monthly Amount Limit";
var Status_Text_OtherAmountLimit = "Enter Other Amount Limit";
var Status_Text_OtherTotalNumberDays = "Enter Other Total Number of Days";
var Status_Text_OtherRefreshDate = "Enter Other Refresh Date";
var Status_Text_CardType = "Enter CMID";
var Status_Text_CardTypeDesc = "Enter CMID Description";
var Status_Text_EMall = "Enter E-Mall URL";
var Status_Text_EMall = "Enter E-Mall Description";
var Status_Text_EmploymentStatus = "Enter Employment Status";
var Status_Text_GradeRank = "Enter Grade or Rank";
var Status_Text_DisputeAmount = "Enter Disputed Amount";
var Status_Text_AllocationAmount = "Enter Allocation Amount";
var Status_Text_AllocationCode = "Enter Allocation Code";
var Status_Text_RoleDescription = "Enter Role Description";
var Status_Text_FirstName = "Enter First Name";
var Status_Text_LastName = "Enter Last Name";
var Status_Text_FromDate = "Enter From Date";
var Status_Text_ToDate = "Enter To Date";
var Status_Text_DDA = "Enter DDA Number";
var Status_Text_ABA = "Enter ABA Number";
var Status_Text_RequestStatus = "Select Request Status";
var Status_Text_VoyagerNumber = "Enter Voyager Number";

<!--*******Confirm Messages and Alert Mesages****************-->
var Msg_Text_SelectCurrentStatus = "Please select a status from the Current Status List Box.";
var Msg_Text_SelectMaintainableStatus = "Please select a status from the Maintainable Status List Box.";
var CnfmMsg_Text_DeleteStatus = "Are you sure you want to delete this status ? ";
var CnfmMsg_Text_AddReason = "Are you sure about adding this Reason to the status.";


<!--**Status Bar Messages, Radio Buttons**-->

var Status_Radio_NewAccounts = "New Accounts Selected";
var Status_Radio_HierarchyLevel = "Hierarchy Levels Selected";
var Status_Radio_HierarchyRange = "Hierarchy Range Selected";
var Status_Radio_AccountNumber = "Account Number Selected";
var Status_Radio_OtherOptions = "Other Options Selected";
var Status_Radio_CycletoDate = "Cycle to Date Selected";
var Status_Radio_LastCycle = "Last Cycle Selected";
var Status_Radio_PostingDate = "Posting Date Selected";
var Status_Radio_Debit = "Debit Transactions Selected";
var Status_Radio_Credit = "Credit Transactions Selected";
var Status_Radio_DebitandCredit = "Debit and Credit Transactions Selected";
var Status_Radio_AgencyName = "Agency Name Selected";
var Status_Radio_AllAgencies = "All Agencies Selected";
var Status_Radio_Lastname = "Last Name Selected";
var Status_Radio_Telephone = "Business Telephone Number Selected";
var Status_Radio_Username = "User Name Selected";
var Status_Radio_InactiveAccounts = "Inactive Accounts Selected";
var Status_Radio_ExpirationDate = "Expiration Date Selected";
var Status_Radio_ALC = "Agency Location Code Selected";
var Status_Radio_SingleAccount = "Single Account Selected";
var Status_Radio_OtherAccounts = "Other Accounts Selected";
var Status_Radio_CardHolderAccounts = "Card Holder Accounts Selected";
var Status_Radio_AgencyAccounts = "Agency Accounts Selected";
var Status_Radio_ControlAccounts = "Control Accounts Selected";
var Status_Radio_AllAccounts = "All Accounts Selected";
var Status_Radio_Yesterday = "Yesterday Selected";
var Status_Radio_LastMonth = "Last Month Selected";
var Status_Radio_MonthandYear = "Month and Year Selected";
var Status_Radio_Today = "Today Selected";
var Status_Radio_Location_USA = "USA Address Selected";
var Status_Radio_Location_Intl = "International Address Selected";
var Status_Radio_OpenDate = "Open Date Selected";
var Status_Radio_MTD = "Month to Date Report Selected";
var Status_Radio_QTD = "Quarter to Date Report Selected";
var Status_Radio_YTD = "Year to Date Report Selected";
var Status_Radio_ByMonth = "Report By Month Selected";
var Status_Radio_ByQuarter = "Report By Quarter Selected";

<!--**New Status Bar Messages, for EAGLS 2.0**-->
var Status_Radio_GeneratePaperYes = "Yes, Generate Paper Statements";
var Status_Radio_GeneratePaperNo = "No, Do Not Generate Paper Statements";

<!--**Status Bar Messages, Checkboxes**-->

var Status_Checkbox_LastName = "Last Name Selected";
var Status_Checkbox_Telephone = "Business Telephone Number Selected";
var Status_Checkbox_SSN = "Social Security Number Selected";
var Status_Checkbox_HierarchyLevel = "Hierarchy Levels Selected";
var Status_Checkbox_Authorization = "Authorization Controls Selected";
var Status_Checkbox_Interval = "Interval Controls Selected";
var Status_Checkbox_Vendor = "Preferred Vendor Table Selected";
var Status_Checkbox_OtherOptions = "Click Checkbox to Select Other Options";
var Status_Checkbox_Transaction_Date = "Transaction Date Selected";
var Status_Checkbox_Acct_Queue = "Account Queue Selected";

<!--**Status Bar Messages, Push Buttons**-->

var Status_Reset_Form = "Reset Form Values";
var Status_Reset = "Clear Contents";
var Status_Submit = "Submit Information";
var Status_Clear = "";
var Status_Cancel = "Cancel";
var Status_Activate = "Activate Account";
var Status_Browse = "Browse Button Selected";

<!--*******************  DISPLAY MESSAGE  **************************-->
<!--*This function receives a text message and displays it in the  *-->
<!--*Netscape Status Bar.                                          *-->
<!--****************************************************************-->

function Display_Message(message)
  {self.status = message;
   return true;
  }

<!--*******************  MOVE FOCUS  ***********************************-->
<!--*This function checks to see if the char count in txtHasFocus has  *-->
<!--*reached maxLen, if it has, it sets focus to txtNextFocus          *-->
<!--********************************************************************-->

function moveFocus(txtHasFocus, txtNextFocus, maxLen, keyCode){
   var key = String.fromCharCode(keyCode);
//alert("key = " + key + "\nkeyCode = " + keyCode);

    if( (keyCode >= 48 &&  keyCode <= 122)) {
        if(txtHasFocus.value.length >= maxLen) txtNextFocus.focus();
    }else
        return false;
}

<!--***********************  PAD LEFT  *******************************-->
<!--*This function receives pads a value with the char provided      *-->
<!--*The char will be added to the left until specified total length *-->
<!--******************************************************************-->
function padLeft(theField, chr, len){
    if(theField.value.length > 0){
       while(theField.value.length < len){
	   theField.value = chr + theField.value;
       }
    }
}

<!--***********************  PAD HIERARCHY ***************************-->
<!--*This function pads a hierarchy text field with leading 0's ******-->
<!--*The char will be added to the left until specified total length *-->
<!--******************************************************************-->
      function padHierarchy( field )
      {
        var theNumber = new Number(field.value);
        var theLength = theNumber.toString().length;
        var tempVar = "" ;
        for ( i=theLength; i < 9; i++ ) {
          tempVar += "0";
        }
        field.value = tempVar + theNumber;
      }  // fillzeros

<!--*******************  CHECK LENGTH  *****************************-->
<!--*This function receives a form, field and required length. It  *-->
<!--*then checks to see if the field equals the required length.   *-->
<!--****************************************************************-->

function checkLength(theForm,theField,theLength)
 {
  var fieldname = theField.name;
  var fieldnamevalue = theField.value;
  var fieldnamelength = theField.value.length;
  var requiredlength = theLength;

  if (fieldnamelength != requiredlength)
      {
       return ("error");
        }
  return ("ok");
 }


<!--*******************  CHECK NUMERIC  ****************************-->
<!--*This function receives a form and field. It then checks to see*-->
<!--*if the all the characters in the field are numeric.           *-->
<!--****************************************************************-->

function checkNumeric(theForm,theField)
 {
   var checkOK = "0123456789";
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


<!--*******************  CHECK ALPHABETIC  *************************-->
<!--*This function receives a form and field. It then checks to see*-->
<!--*if the all the characters in the field are alphbetic.         *-->
<!--****************************************************************-->

function checkAlphabetic(theForm,theField)
 {
   var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
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


 <!--*******************  CHECK ALPHANUMERIC  ***********************-->
 <!--*This function receives a form and field. It then checks to see*-->
 <!--*if the all the characters in the field are alphanumeric.      *-->
 <!--*Last Revision Date:                                           *-->
 <!--****************************************************************-->

 function checkAlphaNumeric(theForm,theField)
  {
   var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";
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

 <!--**********  CHECK ALPHANUMERIC WITH WILD CARDS  ****************-->
 <!--*This function receives a form and field. It then checks to see*-->
 <!--*if the all the characters in the field are alphanumeric.      *-->
 <!--*Last Revision Date:                                           *-->
 <!--****************************************************************-->

 function checkAlphaNumericWC(theForm,theField)
  {
   var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789* ";
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

 <!--*******************  CHECK ALPHANUMERIC PLUS  ********************-->
 <!--* This function receives a form and field. It then checks to see *-->
 <!--* if the all the characters in the field are alphanumeric and    *-->
 <!--* some particular charctors only                                 *-->
 <!--******************************************************************-->

 function checkAlphaNumericPlus(theForm,theField)
  {
   var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789',#/-.&(): _";
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
 <!--************  CHECK ALPHANUMERIC PLUS WITH WILDCARDS  ************-->
 <!--* This function receives a form and field. It then checks to see *-->
 <!--* if the all the characters in the field are alphanumeric and    *-->
 <!--* some particular charctors only                                 *-->
 <!--******************************************************************-->

 function checkAlphaNumericPlusWC(theForm,theField)
  {
   var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789',#/-.&(): _*";
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


<!--*******************  CHECK FIRSTALPHA    ***********************-->
<!--*This function receives a form and field. It then checks to see*-->
<!--*if the first characters in the field is alpha.                *-->
<!--*Last Revision Date:                                           *-->
<!--****************************************************************-->

function checkFirstAlpha(theForm, theField)
    {
     var checkOK="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
     var checkStr=theField.value;
     var allValid=true;

     ch=checkStr.charAt(0);
     for (j=0;  j < checkOK.length;  j++) {
        if (ch == checkOK.charAt(j)) {
           break;
        }
     }
     if (j == checkOK.length){
        allValid=false;
     }
     if (!allValid) {
          return ("error");
     }
     return ("ok");
    }

<!--*******************  CHECK RANGE  ******************************-->
<!--*This function receives a form, a field, a beginning number and*-->
<!--*an ending number. It then checks to see if the field value is *-->
<!--*between the beginning and ending numbers                      *-->
<!--****************************************************************-->

function checkRange(theForm,theField,beginning,ending)
 {
  var fieldname = theField.name;
  var fieldnamevalue = theField.value;
  var beginning_value = beginning;
  var ending_value = ending;

  if (checkNumeric(theForm,theField)=="error")    //check if numeric
    {
     return ("error");
    }

  if ((fieldnamevalue < beginning_value)||
    (fieldnamevalue > ending_value))
        {
          return ("error");
          }
  return ("ok");
 }

<!--*******************  CHECK DAY RANGE  **********************-->
<!--* This function checks month and verifies day falls within *-->
<!--* the valid day range                                      *-->
<!--************************************************************-->

 function checkDayRange(f,m,d,y){

    if (!(isNaN(y)) && !(isNaN(m)) && !(isNaN(d)))   {
    if ((y == 0) || (m == 0)) return ('error');

        /* month is Feb */
        if (m==2){
	      /* Leap year calc */
	      if (((y % 4 == 0) && (y % 100 > 0)) || (y % 400 == 0)){
              if ((d<1) || (d>29)){
                  return('error');
              }
              return('ok');
          }
          else{  /* Not Leap Year  */
              if ((d<1) || (d>28)){
                  return('error');
              }
              return('ok');
          } /* end leap year check */
        } /* end February check */

	    /* month is Jan, Mar, May, Jul, Aug, Oct or Dec */
        else if ((m==1)||(m==3)||(m==5)||(m==7)||(m==8)||(m==10)||(m==12)){
            if ((d<1) || (d>31)){
                return('error');
            }
            return('ok');
	    }
	    else{
            if ((d<1) || (d>30)){
                return('error');
            }
            return('ok');
        }

      return('error');  //return error if values are not numeric
    }
 }/*  end checkDayRange */


<!--*******************  COMPARE TWO DATES  ************************-->
<!--*This function receives a form and 6 date fields, beginning    *-->
<!--*month/day/year and ending month/day/year.  All Days and Months*-->
<!--*must be in the form DD or MM.  This function needs to throw an*-->
<!--*error when the beginning value(sysdate) > ending value(screen)*-->
<!--****************************************************************-->


function compareTwoDates(theForm,BMM,BDD,BYY,EMM,EDD,EYY)
 {
  var beginning_month_value = BMM;
  var beginning_day_value = BDD;
  var beginning_year_value = BYY;
  var ending_month_value = EMM;
  var ending_day_value = EDD;
  var ending_year_value = EYY;

  if (beginning_year_value <= ending_year_value)
      {
       if (beginning_year_value == ending_year_value)
          {
           if (beginning_month_value <= ending_month_value)
              {
               if (beginning_month_value == ending_month_value)
                    {

                   if (beginning_day_value <= ending_day_value)
                      {
                       return ("ok");
                      }
                   return ("error");
                  }
              return ("ok");
               }
           return ("error");
            }
       return ("ok");
      }
  return ("error");
 }

<!--*******************  COMPARE TWO DATES  ************************-->
<!--*This function receives a form and 6 date fields, beginning    *-->
<!--*month/day/year and ending month/day/year.  All Days and Months*-->
<!--*must be in the form DD or MM.  This function needs to throw an*-->
<!--*error when ending value(screen)is <= beginning value(sysdate) *-->
<!--****************************************************************-->


function compareTwoDates2(theForm,BMM,BDD,BYY,EMM,EDD,EYY)
 {
  var beginning_month_value = BMM;
  var beginning_day_value = BDD;
  var beginning_year_value = BYY;
  var ending_month_value = EMM;
  var ending_day_value = EDD;
  var ending_year_value = EYY;

  if (beginning_year_value <= ending_year_value)
      {
       if (beginning_year_value == ending_year_value)
          {
           if (beginning_month_value <= ending_month_value)
              {
               if (beginning_month_value == ending_month_value)
                    {

                   if (ending_day_value > beginning_day_value)
                      {
                       return ("ok");
                      }
                   return ("error");
                  }
              return ("ok");
               }
           return ("error");
            }
       return ("ok");
      }
  return ("error");
 }


<!--*******************  CHECK HIERCHY LEVEL SEQUENCE***************-->
<!--*This function checks to ensure that hierarchy levelssubmitted *-->
<!--*are in sequence    --modified 2/4/98 MM                       *-->
<!--****************************************************************-->

function checkHLSequence(field1,field2,field3,field4,field5,field6,field7,field8,field9)
{

        var i, test=0, j=1;
        var HL = new Array();

        HL[1]=field1.value;
        HL[2]=field2.value;
        HL[3]=field3.value;
        HL[4]=field4.value;
        HL[5]=field5.value;
        HL[6]=field6.value;
        HL[7]=field7.value;
        HL[8]=field8.value;
    	HL[9]=field9.value; //modified 06/19/98 BB

        <!--i added this to detect which HL's are occupied-->

        counter=0;
        FieldsOccupied=0;
        while (counter<9)   // modified 7-->8 MM
          {++counter;
           if (HL[counter]!="")
                 {
                  ++FieldsOccupied;
                 }
          }



        <!--**this will generate an invalid sequence error if -->
        <!--**no fields are occupied.  uncomment if necessary -->

       <!--   if (FieldsOccupied == 0)      -->
       <!--   {alert("no fields entered");  -->
       <!--      return("error");           -->
       <!--  }                              -->

        if (FieldsOccupied == 0)
          {
           return("ok");
          }
        if ((FieldsOccupied > 0)&&
            (HL[1] == ""))
          {
		//alert(1);
           return("error");
          }

        <!-- stopped adding stuff -->

        while (j<10){
                for (i=j; i<10; ++i){
                        if (HL[i]!=""){
                                ++j;
                                ++test;
                                if ((HL[i+1]!="")&&((i+1)<10)){
                                        ++j;
                                        if
                                        ((HL[i+2]!="")&&((i+2)<10)){
                                                ++j;
                                                if
                                                ((HL[i+3]!="")
                                                &&((i+3)<10)){
                                                        ++j;
                                                        if
                                                        ((HL[i+4]!="")&&((i+4)<10)){
                                                                ++j;;
                                                                if ((HL[i+5]!="")&&((i+5)<10)){
                                                                      ++j;
                                if ((HL[i+6]!="")&&((i+6)<10)){
                                    ++j;
                                if((HL[i+7]!="")&&((i+7)<10)){
                                    j=9;
                                    break;
                                  }
                                    break;
                                  }
                                    break;
                                  }
                                                                        break;
                                                                        }
                                                                break;
                                                                }
                                                        break;
                                                        }
                                                break;
                                                }
                                        break;
                                        }
                                break;
                                }

                        j++;
                        }

        if (test>1){
                alert("1 i"+i);
                return('error');
                }
        else if(test<2){
                return('ok');
                }
        }

<!--*******************  CHECK HIERCHY LEVEL SEQUENCE 2*************-->
<!--*This function checks to ensure that hierarchy levelssubmitted *-->
<!--*are in sequence (only 8 fields required )                     *-->
<!--****************************************************************-->

function checkHLSequence2(field1,field2,field3,field4,field5,field6,field7,field8)
{

        var i, test=0, j=1;
        var HL = new Array();

        HL[1]=field1.value
        HL[2]=field2.value
        HL[3]=field3.value
        HL[4]=field4.value
        HL[5]=field5.value
        HL[6]=field6.value
        HL[7]=field7.value
        HL[8]=field8.value

        <!--i added this to detect which HL's are occupied-->

        counter=0;
        FieldsOccupied=0;
        while (counter<8)   // modified 7-->8 MM
          {++counter;
           if (HL[counter]!="")
                 {
                  ++FieldsOccupied;
                 }
          }



        <!--**this will generate an invalid sequence error if -->
        <!--**no fields are occupied.  uncomment if necessary -->

       <!--   if (FieldsOccupied == 0)       -->
       <!--   {alert("no fields entered");  -->
       <!--      return("error");              -->
       <!--  }                             -->

        if (FieldsOccupied == 1 && (HL[1] != ""))
          {
           return("ok");
          }
        if ((FieldsOccupied > 1)&&
            (HL[1] == ""))
          {
           return("error");
          }

        <!-- stopped adding stuff -->

        while (j<9){
                for (i=j; i<9; ++i){
                        if (HL[i]!=""){
                                ++j;
                                ++test;
                                if ((HL[i+1]!="")&&((i+1)<9)){
                                        ++j;
                                        if
                                        ((HL[i+2]!="")&&((i+2)<9)){
                                                ++j;
                                                if
                                                ((HL[i+3]!="")
                                                &&((i+3)<9)){
                                                        ++j;
                                                        if
                                                        ((HL[i+4]!="")&&((i+4)<9)){
                                                                ++j;;
                                                                if ((HL[i+5]!="")&&((i+5)<9)){
                                                                      ++j;
                                     if ((HL[i+6]!="")&&((i+6)<9)){
                                         j=8;
                                         break;
                                       }
                                      break;
                                        }
                                                                        break;
                                                                        }
                                                                break;
                                                                }
                                                        break;
                                                        }
                                                break;
                                                }
                                        break;
                                        }
                                break;
                                }

                        j++;
                        }

        if(test == 1 && (HL[1] == "")){
                return('error');
        }
        if (test>1){
                return('error');
                }
        else if(test<2){
                return('ok');
                }
        }




function checkHierarchyLength(theForm,theField,theLength)
 {
        var fieldname = theField.name;
        var fieldnamevalue = theField.value;
        var fieldnamelength = theField.value.length;
        var requiredlength = theLength;
        if (fieldnamevalue == "")
                return('ok');
        if (fieldnamelength != requiredlength)
                  {
                    return('error');
              }
        return('ok')
 }

<!--*******************  DISPLAY MESSAGE  **************************-->
<!--*This function receives a text message and displays it in the  *-->
<!--*Netscape Status Bar                                           *-->
<!--****************************************************************-->

 function Display_Message(message){
     self.status = message;
     return true;
 }

//*******************   SET FOCUS    *****************************
//**This function is a workaround for setting focus in IE3     ***
//****************************************************************

function setFocus(theForm,theField,theMessage)
  {
   if (window.focus)
    {
     theField.focus();
     Display_Message(theMessage)
    }
   return true;
  }

<!--******************  FORMAT CURRENCY   ***************************-->
<!--*This function formats an allocation amount field into proper   *-->
<!--*currency format.                                               *-->
<!--*****************************************************************-->

function formatCurrency(theAmount,theLength)
 {
  var decSign = ".";
  var grpSign = ",";
  var negSign = "-";
  var curSign = "$";
  var reqLength = theLength;
  var theNumber = Math.abs(Math.round(theAmount*100));
  var theString = ((theNumber<10)?"00":((theNumber<100)?"0":""))+theNumber;
  theString = curSign+
              ((theAmount<0) ? negSign : "") +
        groupString(theString.substring(0,(theString.length - 2)))+
        decSign+
              theString.substring((theString.length - 2),theString.length)+
        ((theAmount<0&&negSign == "(") ? ")" : "");
  return (theString.length>reqLength) ? "Value is too large" : theString;
 }

function groupString(theString)
  {
   return (theString.length<4) ? theString : (groupString(theString.substring(0,theString.length-3)) +
                                              "," + theString.substring(theString.length-3,theString.length));
  }

<!--******************  SearchandReplace  ***************************-->
<!--*This function will search and replace                          *-->
<!--*target = the orginal string    oldterm= term to replace        *-->
<!--*newterm = replace with         casesense = true/false          *-->
<!--*wordonly = whole word (true), substring (false)                *-->
<!--*****************************************************************-->

function searchAndReplace(target,oldterm,newterm,casesens,wordonly)
 {
  var work=target;
  var ind=0;
  var next=0;

  if (!casesens)
    {oldterm = oldterm.toLowerCase();
   work=target.toLowerCase();
  }
  while ((ind = work.indexOf(oldterm,next)) >= 0)
    {if (wordonly)
     {var before = ind -1;
      var after = ind + oldTerm.length;
    if (!(space(work.charAt(before)) && space(work.charAt(after))))
      {next = ind + oldTerm.length;
       continue;
      }
     }
     target=target.substring(0,ind) + newterm +
          target.substring(ind+oldterm.length,target.length);
   work=work.substring(0,ind) + newterm +
        work.substring(ind+oldterm.length,work.length);
   next = ind + newterm.length;
   if (next >= work.length)
     {break;
     }
  }
  return (target);
  }

 <!--*******************  CHECK NUMERIC  **********************************-->
 <!--*This function receives a form and field Value. It then checks to see*-->
 <!--*if the all the characters in the field Value are numeric.           *-->
 <!--**********************************************************************-->
  function checkNumeric2(value)
     {
        var checkOK = "0123456789";
        var checkStr = value;
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

  <!-- *******************  CHECK DATE  ******************************* -->
  <!-- ***  This function checks for the date of format MM/DD/YYYY    * -->
  <!-- **************************************************************** -->

 function checkDate(theForm,theField)
 {

   var MSlash = theField.value.substring(2,3);
   var DSlash = theField.value.substring(5,6);

   var MM = theField.value.substring(0,2);
   var DD = theField.value.substring(3,5);
   var YY = theField.value.substring(6,10);


      if (((MM < 1) || (MM > 12)) ||
          (parent.logo.checkNumeric2(MM) == "error") ||
          (parent.logo.checkNumeric2(DD) == "error") ||
          (parent.logo.checkNumeric2(YY) == "error") ||
          (parent.logo.checkDayRange(theForm,MM,DD,YY) == "error")||
          (MSlash != "/")||
          (YY.length!=4)||
          (DSlash != "/"))
         {
            return("error");;
         }


            return("ok");

  }
  <!--*******************  CHECK DATE 2  *************************-->
  <!--* This function checks for the date of format MM/YYYY      *-->
  <!--************************************************************-->

  function checkDate2(theForm,theField)
  {

   var MSlash = theField.value.substring(2,3);

   var MM = theField.value.substring(0,2);
   var YY = theField.value.substring(3,7);

      if (((MM < 1) || (MM > 12)) ||
          (parent.logo.checkNumeric2(MM) == "error") ||
          (parent.logo.checkNumeric2(YY) == "error") ||
          (parent.logo.checkDayRange(theForm,MM,"01",YY) == "error")||
          (MSlash != "/")||
          (YY.length!=4))
         {
            return("error");;
         }


            return("ok");

}

function checkDateMMYYYY(theForm, theField){

   if ("" == theField.value) return("error");
   var workDate = theField.value;
   var MM = workDate.substring(0,2);
   var YYYY=workDate.substring(3,workDate.length);
   if ((parent.logo.checkNumeric2(MM) == "error") ||
       (parent.logo.checkNumeric2(YYYY) == "error") ||
       (parent.logo.checkDayRange(theForm,MM,"01",YYYY) == "error")||
       (YYYY.length!=4)){
            return("error");
   }


   return("ok");

}


<!--******************  SPACE CHECK  ***************************-->
function space(check)
  {var space=" .,/<>?!`';@#$%^&*()=-|[]{}" + '"' + "\\\n\t";
   for (var i=0; i < space.length; i++)
     if (check == space.charAt(i))
        {return true;}
     if (check == "")
        {return true;}

     return false;
   }

<!--*******************  CHECK SOCIAL SECURITY NUMBER  *****************-->
<!--*This function receives a form and field. It then checks to see    *-->
<!--*if the all the characters in the field are COMPRISE valid numbers *-->
<!--*and dashes                                                        *-->
<!--********************************************************************-->
 function checkSSN(theForm,theField)
{

    var SSFDash = theField.value.substring(3,4);
    var SSLDash = theField.value.substring(6,7);

    var SSF = theField.value.substring(0,3);
    var SSL = theField.value.substring(4,6);
    var LFour = theField.value.substring(7,11);

        if ((checkNumeric2(SSF) == "error") ||
        	(checkNumeric2(SSL) == "error") ||
        	(checkNumeric2(LFour) == "error") ||
          	(SSF.length != 3) ||
          	(SSFDash != "-") ||
          	(SSL.length != 2) ||
          	(SSLDash  != "-") ||
          	(LFour.length!=4))
         {
            return("error");;
         }


            return("ok");

  }


<!--*******************  CHECK CURRENCY  ***************************-->
<!--*This function receives a form and field. It then checks to see*-->
<!--*if the all the characters in the field are currency related   *-->
<!--****************************************************************-->

function checkCurrency(checkStr)
  {
   var checkOK = "0123456789-+$,.";
   var allValid = true;

   // Check for valid characters
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

   // Check for numeric presence
   var checkOK = "0123456789";
   var foundNumber = false;

   for (i = 0;  i < checkStr.length;  i++)
           {
            ch = checkStr.charAt(i);
            for (j = 0;  j < checkOK.length;  j++)
              if (ch == checkOK.charAt(j))
			    {
                  foundNumber = true;
				  break;
			    }
           }

     if (!foundNumber)
        {
         allValid = false;
        }

     // Check for duplicates
	 var checkOK = "-+$.";
     var foundDups = false;
	 var dupCount = 0;

	 for (i = 0;  i < checkOK.length;  i++)
	            {
				 dupCount = 0;
	             ch = checkOK.charAt(i);
	             for (j = 0;  j < checkStr.length;  j++)
	               if (ch == checkStr.charAt(j))
	 			    {
	                  dupCount += 1;
	 			    }
				 if (dupCount > 1)
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

  <!--*******************  CHECK PHONE NUMBER  ************************-->
  <!--*This function receives a form and field. It then checks to see* -->
  <!--*if the all the characters in the field are phone number related -->
  <!--*****************************************************************-->

  function checkPhoneNumber(theForm,theField)
    {
       var checkOK = "0123456789- ";
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

  <!--*******************  CHECK ZIP CODE  ************************************-->
  <!--*This function receives a form and field. It then checks to see if the  *-->
  <!--*all the characters in the field are numeric with a dash and either     *-->
  <!--*in the form of 5 numbers, or 5 numbers, a dash, then 4 numbers         *-->
  <!--*7/14/99 Jon Credit removed validation on zipcode field per Maria Moster*-->
  <!--*************************************************************************-->

  function checkZipCode(theForm,theField)
      {
       return("ok");
      }

  <!--*******************  CHECK ADDRESSLINE  ************************-->
  <!--*This function receives a form and field. It then checks to see*-->
  <!--*if the all the characters in the field are alphanumeric and . *-->
  <!--*some particular charctors only                                *-->
  <!--****************************************************************-->

  // ** DO NOT USE THIS FUNCTION, USE checkAlphaNumericPlus. 08/19/98 -BB

  function checkAdderssLine(theForm,theField)
   {
    var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789',#/-.&() ";
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

// stop hiding -->
</script>
</HEAD>
<BODY>
<CENTER>
<%--<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/BofATitle.gif" BORDER="0" Width="124" Hieght="18" ALIGN="TOP" VSPACE="2">--%>
<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/BofALogo.gif" BORDER="0" Width="158" Hieght="40">
</CENTER>
</BODY>
</HTML>

