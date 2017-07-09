<%@ page import="com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields,
                 com.boa.eagls.government.util.HierarchyDisplay,
                 com.boa.eagls.government.service.Service,
                 com.boa.eagls.government.constants.service.Role,
                 com.boa.eagls.government.dto.HierarchyDTO"%>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<%
//    HierarchyDisplay hDisplay = new HierarchyDisplay();
    HierarchyDTO[] iHierarchy = new HierarchyDTO[9];
    for (int i=0; i<9; i++) {
        iHierarchy[i] = new HierarchyDTO();
    }
    BrowseHierarchyFields browseHierarchyFields = Service.fillHierarchyBean(iHierarchy, null, Role.GCSU, true,false);
//    BrowseHierarchyFields browseHierarchyFields = new BrowseHierarchyFields();
    session.setAttribute("browseHierarchyFields", browseHierarchyFields);
%>
<HTML>
<HEAD>

<TITLE>Search</TITLE>

<META NAME="Name" 		 CONTENT="Search Screen">
<META NAME="File Name"   CONTENT="search.html">
<META NAME="Description" CONTENT="Search Screen">
<META NAME="Author"      CONTENT="Philip Tobin">
<META NAME="Created"     CONTENT="01-13-00">
<META NAME="Revised"     CONTENT="01-26-00 (Brett Sears)">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/reviseSearch.js"></SCRIPT>


<SCRIPT language="JavaScript">
<!-- Start hiding from other browsers
var NumberOfWc = 0;
var MaxWildCards = 1;
var trnAcctFlag = 0;
var isLoaded = false;

// validation rules for strings containing the wild card character '*' are as follows:
//   only one '*' is allowed in the string
//   '*' must be the last character
//   there must be at least 2 characters preceeding the '*'
function ValidateWildCardString(theField)
{
	var charCount = 0
	var astriskFound = false
  var underscoreFound = false

	for(position = 0; position < theField.value.length; position++)
	{
    if(theField.value.charAt(position) == '_') {
          //screen for '_' because they don't work with Oracle's LIKE function
          underscoreFound = true;
      }

		if(theField.value.charAt(position) == '*') {
			if(astriskFound) {
				// this is the second astrisk which is not allowed
				return false
			}
			astriskFound = true;

      if(underscoreFound){
          // underscores not permitted with wild cards
          return false;
      }

			if(charCount < 2) {
				// must have at least 2 characters before the '*'
				return false;
			}
		} else if(theField.value.charAt(position) != ' ') {
			if(astriskFound) {
				// can not have characters after the '*'
				return false;
      }

			charCount++;
		}
	}
	return true;
}

function CheckNumberOfWC(theField)
{
	var i = theField.value.length-1;
	var wspace = "false";

	while (wspace == "false"){
		if (theField.value.charAt(i) == " "){
			i=i-1;
		}
		else if (theField.value.charAt(i) == "*"){
			NumberOfWc = NumberOfWc +1;
			wspace = "true";
		}
		else { wspace = "true";}
	}
	return(true);
}


function pageload()
{
  if(isLoaded){
  createHierarchyString(document.theForm,'/cgi-bin/gx.cgi/GUIDGX-{259A23D0-1B6A-11D2-96DD-204C4F4F5020}')
  }else
   return false;
}


function Form_Test(theForm)
    {
	  if(!isLoaded){
	  	return false;
	  }

     var errormessage = parent.logo.ErrMsg_Header;
	 theForm.hdn_noneChecked.value = 1;
	 theForm.hdn_noneChecked2.value = 0;
	 var checkedOption = 0;
	 NumberOfWc = 0;

    if(theForm.txt_hl0){
     	theForm.txt_programNumber.value = theForm.txt_hl0.value;}

	if (theForm.chk_newGCSU)
		{
    	  if (theForm.chk_newGCSU.checked == true)
    	     {
			     theForm.hdn_noneChecked2.value = 1;

    	     }
	    }
	if (theForm.chk_pendGCSU)
		{
		  if (theForm.chk_pendGCSU.checked == true)
    	     {
			     theForm.hdn_noneChecked2.value = 1;

    	     }
		}
	if (theForm.chk_processedGCSU)
		{
 		  if (theForm.chk_processedGCSU.checked == true)
			 {
			     theForm.hdn_noneChecked2.value = 1;

    	     }
		}
	if (theForm.chk_newStatus)
			{
	 		  if (theForm.chk_newStatus.checked == true)
				 {
				     theForm.hdn_noneChecked2.value = 1;

	    	     }
		}
		if (theForm.chk_pendRecAOPC)
			{
	 		  if (theForm.chk_pendRecAOPC.checked == true)
				 {
				     theForm.hdn_noneChecked2.value = 1;

	    	     }
		}
		if (theForm.chk_pendGCSUStat)
		{
			 if (theForm.chk_pendGCSUStat.checked == true)
				 {
				   theForm.hdn_noneChecked2.value = 1;

			     }
		}

		if (theForm.chk_procGCSU)
		{
			if (theForm.chk_procGCSU.checked == true)
				{
					theForm.hdn_noneChecked2.value = 1;

				}
		}
		if (theForm.chk_declinedStat)
		{
			if (theForm.chk_declinedStat.checked == true)
				{
					theForm.hdn_noneChecked2.value = 1;

				}
		}

		if (theForm.chk_Approved)
		{
			if (theForm.chk_Approved.checked == true)
				{
					theForm.hdn_noneChecked2.value = 1;

				}
		}

		if (theForm.chk_pendStat)
		{
			if (theForm.chk_pendStat.checked == true)
				{
					theForm.hdn_noneChecked2.value = 1;

				}
		}


	if ( theForm.rag_statementSearch)
		{
			checkedOption=1;
		}
	if (theForm.chk_pendingUntil)
		{
		  if (theForm.chk_pendingUntil.checked == true)
    	     {
			   checkedOption == 1;
	  	      //theForm.hdn_noneChecked.value = 0;
             if ((parent.logo.checkDate(theForm,theForm.txt_pendingUntilFrom) == "error")||
		  		(parent.logo.checkDate(theForm,theForm.txt_pendingUntilTo) =="error")||
		  		(theForm.txt_pendingUntilFrom.value == "")||
				(theForm.txt_pendingUntilTo.value == ""))
			{
				errormessage += "Invalid Pending Until Dates Entered\n";
			}


			var BMM = theForm.txt_pendingUntilFrom.value.substring(0,2) ;
	        var EMM = theForm.txt_pendingUntilTo.value.substring(0,2) ;
	        var BDD = theForm.txt_pendingUntilFrom.value.substring(3,5) ;
	        var EDD = theForm.txt_pendingUntilTo.value.substring(3,5) ;
	        var BYY = theForm.txt_pendingUntilFrom.value.substring(6,10) ;
	        var EYY = theForm.txt_pendingUntilTo.value.substring(6,10) ;

	         if (parent.logo.compareTwoDates(theForm,BMM,BDD,BYY,EMM,EDD,EYY) == "error")
	            {
	              errormessage += "Invalid Pending Until Date Range\n" ;
	            }

             }
		}

	if (theForm.chk_effectiveDate)
		{
			if (theForm.chk_effectiveDate.checked == true)
         	{
		  		checkedOption == 1;
				//theForm.hdn_noneChecked.value = 0;
          		if ((parent.logo.checkDate(theForm,theForm.txt_effectiveDateFrom) == "error")||
		  			(parent.logo.checkDate(theForm,theForm.txt_effectiveDateTo) == "error")||
		  			(theForm.txt_effectiveDateFrom.value == "")||
					(theForm.txt_effectiveDateTo.value == ""))
             	{
              		 errormessage += "Invalid Effective Dates Entered\n";
             }


			var BMM = theForm.txt_effectiveDateFrom.value.substring(0,2) ;
	        var EMM = theForm.txt_effectiveDateTo.value.substring(0,2) ;
	        var BDD = theForm.txt_effectiveDateFrom.value.substring(3,5) ;
	        var EDD = theForm.txt_effectiveDateTo.value.substring(3,5) ;
	        var BYY = theForm.txt_effectiveDateFrom.value.substring(6,10) ;
	        var EYY = theForm.txt_effectiveDateTo.value.substring(6,10) ;

	         if (parent.logo.compareTwoDates(theForm,BMM,BDD,BYY,EMM,EDD,EYY) == "error")
	            {
	              errormessage += "Invalid Effective Date Range\n" ;
	            }
	         }
		}

	if (theForm.rag_invoiceSearch) {
				if (theForm.rag_invoiceSearch[2].checked){
				if (theForm.txt_invoiceDate.value != ""){
					var workDate = theForm.txt_invoiceDate.value;
				var slashPos = workDate.indexOf( "/" );
				var MM, YYYY;
				if ( slashPos > 0 )
				{
				  MM = workDate.substring(0,slashPos);
				  if ( MM.length == 1 )
				  {
					MM = "0" + MM;
				  }
				  YYYY = workDate.substring(slashPos+1, workDate.length);
				  theForm.txt_invoiceDate.value = MM + "/" + YYYY;
				}
				else {
				  MM = "MM";
				  YYYY= "YYYY";
				  }
				}

				if ((theForm.txt_invoiceDate.value == "") || ((theForm.txt_invoiceDate.value != "") && (parent.logo.checkDateMMYYYY(theForm,theForm.txt_invoiceDate) == "error")))
				{
					errormessage += "Invalid Invoice Date\n";
				}
			 }
 	}

	if (theForm.rag_statementSearch &&  theForm.rag_statementSearch[2].checked)
      {
        if ((theForm.txt_statementDate.value == "") ||
                ((theForm.txt_statementDate.value != "") &&
                 (parent.logo.checkDate2(theForm,theForm.txt_statementDate) == "error")))
                {
                 errormessage += "Invalid Statement Date\n";
        }
      }


	if (theForm.rag_statementSearch && theForm.rag_statementSearch[3] && theForm.rag_statementSearch[3].checked)
	{
		if (parent.logo.checkDate(theForm, theForm.txt_begTransDate) == "error")
		{
			errormessage += "Invalid Transaction Beginning Date\n";
		}

		if (parent.logo.checkDate(theForm, theForm.txt_endTransDate) == "error")
		{
			errormessage += "Invalid Transaction Ending Date\n";
		}

		var BMM = theForm.txt_begTransDate.value.substring(0,2) ;
		var EMM = theForm.txt_endTransDate.value.substring(0,2) ;
		var BDD = theForm.txt_begTransDate.value.substring(3,5) ;
		var EDD = theForm.txt_endTransDate.value.substring(3,5) ;
		var BYY = theForm.txt_begTransDate.value.substring(6,10) ;
		var EYY = theForm.txt_endTransDate.value.substring(6,10) ;

		if (parent.logo.compareTwoDates(theForm,BMM,BDD,BYY,EMM,EDD,EYY) == "error")
		{
			errormessage += (parent.logo.ErrMsg_DateRange) ;
	    }
	}

     if(theForm.rag_accountInquirySearch) {
	      theForm.hdn_noneChecked.value = 0;
	       checkedOption=1;
	      	if (theForm.rag_accountInquirySearch[0].checked)
	 	    {
	 	       if (((theForm.txt_accountNumber.value == "")||(theForm.txt_accountNumber.value == "0000000"))||
	 	           (parent.logo.checkLength(theForm,theForm.txt_accountNumber,16) == "error")||
	 	           (parent.logo.checkNumeric(theForm,theForm.txt_accountNumber) == "error"))
	 	       {
	 	            errormessage +=  parent.logo.ErrMsg_AccountNumber;
	 	       }
			   else
			   		accountNumberSetRadioGroup(theForm);
			}

			if (theForm.rag_accountInquirySearch[2].checked)
				partialrequestSetRadioGroup(theForm);

	       if (theForm.rag_accountInquirySearch[3].checked)
	       {

	       if (theForm.txt_hl0  && (theForm.txt_hl0.type != "hidden"))
	       {

	         if (((theForm.txt_programNumber.value == "")||(theForm.txt_programNumber.value == "0000000"))||
	              (theForm.txt_hl0.value <=0) || (parent.logo.checkNumeric(theForm,theForm.txt_programNumber) == "error")||
	             ((parent.logo.checkLength(theForm,theForm.txt_programNumber,7) == "error")&&
	           (theForm.txt_programNumber.value != "")))
	               {
	         errormessage += (parent.logo.ErrMsg_ProgramNumber);
	                 }

	         if ((theForm.txt_programNumber.value == "")&&
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


	       if (theForm.txt_programNumber)
	             {
	                if (parent.logo.checkHLSequence(theForm.txt_programNumber,theForm.txt_hl1,
	             theForm.txt_hl2,theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
	             theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
	             {
	              errormessage += (parent.logo.ErrMsg_HierarchyLevelSequence);
	             }
	              }
	           else
	           {
	            if (parent.logo.checkHLSequence2(theForm.txt_hl1, theForm.txt_hl2,theForm.txt_hl3,
	                theForm.txt_hl4,theForm.txt_hl5,theForm.txt_hl6,theForm.txt_hl7,
	             theForm.txt_hl8) == "error")
	               {
	             errormessage += (parent.logo.ErrMsg_HierarchyLevelSequence);
	             }
	                }

	  		}

	      }

     if (theForm.chk_equipmentID)
     {
    	 if (theForm.chk_equipmentID.checked == true)
    	 	{    checkedOption=1;
    	 		theForm.hdn_noneChecked.value = 0;
				theForm.txt_equipmentID.value = stringTrim(theForm.txt_equipmentID.value);
    	 		if ((parent.logo.checkAlphaNumericWC(theForm,theForm.txt_equipmentID) == "error") ||
    	 		    (theForm.txt_equipmentID.value == "") || !ValidateWildCardString(theForm.txt_equipmentID))
    	 			{
    	 				errormessage += "Invalid Equipment ID\n";
    	 			}
    	 	     else
    	 	        {
    	 	            CheckNumberOfWC(theForm.txt_equipmentID);
    	 	        }
    	 	}
	 }
	 if (theForm.chk_accountingCenterId && theForm.chk_accountingCenterId.checked)
	 	 {        checkedOption=1;
	 	 		 if((theForm.txt_accountingCenterId.value == "")||
	 	 		       (parent.logo.checkNumeric(theForm,theForm.txt_accountingCenterId)=="error"))
	 	 		        {
	 	 		         errormessage += parent.logo.ErrMsg_AccountingCenterID;
	 	 		        }
	      }
	 if (theForm.chk_accountingCenterName && theForm.chk_accountingCenterName.checked)
	 	 {        checkedOption=1;
				  theForm.txt_accountingCenterName.value = stringTrim(theForm.txt_accountingCenterName.value);
	 	          if ((theForm.txt_accountingCenterName.value == "")||
	 	 		 	     (parent.logo.checkAlphaNumericWC(theForm,theForm.txt_accountingCenterName)=="error") ||
	 	 		 	     !ValidateWildCardString(theForm.txt_accountingCenterName))
	 	 		 	     {
	 	 		 	      errormessage += "Invalid Accounting Center Name\n";
	 	                  }
	 	           else {
	 	            CheckNumberOfWC(theForm.txt_accountingCenterName);
	 	           }
	 }
     if (theForm.chk_agencyName && theForm.chk_agencyName.checked)
     {         checkedOption=1;
			   theForm.txt_agencyName.value = stringTrim(theForm.txt_agencyName.value)
	           if ((parent.logo.checkAlphaNumericPlusWC(theForm,theForm.txt_agencyName) == "error")||
	               (theForm.txt_agencyName.value == "")||
	               !ValidateWildCardString(theForm.txt_agencyName))
	               {
	                errormessage += (parent.logo.ErrMsg_AgencyName);
	               }
	            else {
	               CheckNumberOfWC(theForm.txt_agencyName);
	            }
      }

     if (theForm.chk_transDate)
     {
		 if (theForm.chk_transDate.checked == true)
			 {    checkedOption=1;
				 theForm.hdn_noneChecked.value = 0;
				 if (parent.logo.checkDate(theForm,theForm.txt_begTransDate) == "error")
					  {
						errormessage += "Invalid Transaction Beginning Date\n";
					  }

				 if (parent.logo.checkDate(theForm,theForm.txt_endTransDate) == "error")
					  {
						errormessage += "Invalid Transaction Ending Date\n";
					  }

				 var BMM = theForm.txt_begTransDate.value.substring(0,2) ;
				 var EMM = theForm.txt_endTransDate.value.substring(0,2) ;
				 var BDD = theForm.txt_begTransDate.value.substring(3,5) ;
				 var EDD = theForm.txt_endTransDate.value.substring(3,5) ;
				 var BYY = theForm.txt_begTransDate.value.substring(6,10) ;
				 var EYY = theForm.txt_endTransDate.value.substring(6,10) ;

				 if (parent.logo.compareTwoDates(theForm,BMM,BDD,BYY,EMM,EDD,EYY) == "error")
					  {
						errormessage += (parent.logo.ErrMsg_DateRange) ;
					  }

			 }
	}

     if (theForm.chk_accountNumber)
     {
		 if (theForm.chk_accountNumber.checked == true)
			 { checkedOption=1;
			  theForm.hdn_noneChecked.value = 0;
			  if ((theForm.txt_accountNumber.value == "")||(checkNumeric(theForm,theForm.txt_accountNumber) == "error") ||
				  (parent.logo.checkLength(theForm,theForm.txt_accountNumber,16)=="error")
				 )
				  {
				   errormessage +=  parent.logo.ErrMsg_AccountNumber;
				  }
			 }

    }

     if (theForm.chk_userID)
     {
		 if (theForm.chk_userID.checked == true)
			 { checkedOption=1;
			  theForm.txt_userID.value = stringTrim(theForm.txt_userID.value);
			  theForm.hdn_noneChecked.value = 0;
			  if ((theForm.txt_userID.value == "")||
			  	  (parent.logo.checkAlphaNumericPlusWC(theForm,theForm.txt_userID) == "error")||
	               !ValidateWildCardString(theForm.txt_userID))
				  {
				   errormessage +=  parent.logo.ErrMsg_UserID;
				  }
		      else {
		      	CheckNumberOfWC(theForm.txt_userID);
		      }
			 }

    }


    if (theForm.chk_Name){
		if (theForm.chk_Name.checked == true)
			 { checkedOption=1;
			  theForm.txt_lastName.value = stringTrim(theForm.txt_lastName.value);
			  theForm.txt_firstName.value = stringTrim(theForm.txt_firstName.value);
			  theForm.hdn_noneChecked.value = 0;
			  var t = stringTrim(theForm.txt_lastName.value);
			  var lnameErr = 0;
			  if ((t != "") && ValidateWildCardString(theForm.txt_lastName) ){
			  		CheckNumberOfWC(theForm.txt_lastName);
			  }
			  else{
			     errormessage +=  parent.logo.ErrMsg_LastName;
			     lnameErr = 1;
			  }

			  if (theForm.txt_firstName.value.length > 0){
			    var t = stringTrim(theForm.txt_firstName.value);
			  	if ((t != "") && ValidateWildCardString(theForm.txt_firstName)){
			  		CheckNumberOfWC(theForm.txt_firstName);
			  	}
			  	else{
			  		errormessage +=  parent.logo.ErrMsg_FirstName;
			  	}
			  }

			  if ((parent.logo.checkAlphaNumericPlusWC(theForm,theForm.txt_lastName) == "error") && lnameErr == 0)
				  {
				   errormessage +=  parent.logo.ErrMsg_LastName;
				  }

			  if ((theForm.txt_firstName.value != "")&&
				  (parent.logo.checkAlphaNumericPlusWC(theForm,theForm.txt_firstName) == "error"))
				  {
				   errormessage +=  parent.logo.ErrMsg_FirstName;
				  }

			  if ((theForm.txt_firstName.value != "")&&
				  (theForm.txt_lastName.value == ""))
				  {
				   errormessage +=  parent.logo.ErrMsg_NameCombination;
				  }

			  if (theForm.txt_zipCode) {
				 if ((theForm.txt_zipCode.value != "") &&
					  (parent.logo.checkZipCode(theForm,theForm.txt_zipCode) != "ok"))
					  /* (checkZipCode(theForm, theForm.txt_zipCode) == "error"))
						 CD 9/2/99
						 Fixed Discrepancy MDS2269
						 Resolution: This page performed local validation on the zip code
									 field preventing any character except numerics and
									 the slash character. Modified to call the common zip
									 code validation function within logo.html. Effectively
									 removes any validation other than empty from the field.
					  */
					{
					 errormessage += parent.logo.ErrMsg_NameCombination;
					}
					else if (!ValidateWildCardString(theForm.txt_zipCode)){
						errormessage += "Invalid Zip Code";
					}
					else
					{
						CheckNumberOfWC(theForm.txt_zipCode);
					}

			 	}
			 }
		}

	 if(theForm.chk_busPhone) {
		if (theForm.chk_busPhone.checked == true)
			 { checkedOption=1;
			 theForm.hdn_noneChecked.value = 0;
                         if(theForm.txt_businessPhone1 && theForm.txt_businessPhone1.value != ""){
                             theForm.txt_businessPhone.value = theForm.txt_businessPhone1.value + theForm.txt_businessPhone2.value + theForm.txt_businessPhone3.value;
                         }
                         else if(theForm.txt_businessPhoneIntl && theForm.txt_businessPhoneIntl.value != ""){
                             theForm.txt_businessPhone.value = theForm.txt_businessPhoneIntl.value;
                         }
			 if ((theForm.txt_businessPhone.value == "")||(checkPhoneNumber(theForm,theForm.txt_businessPhone) == "error"))

				 {
				  errormessage += "Invalid Business Phone\n";
				 }
			 }
	}

	 if (theForm.chk_SSN){
		 if (theForm.chk_SSN.checked == true)
			 { checkedOption=1;
			 theForm.hdn_noneChecked.value = 0;
                         theForm.txt_socialSecurityNumber.value=theForm.txt_SS1.value + '-' + theForm.txt_SS2.value + '-' + theForm.txt_SS3.value;
			 if ( (theForm.txt_socialSecurityNumber.value == "")||(parent.logo.checkSSN(theForm, theForm.txt_socialSecurityNumber) == "error") )

				 {
				  errormessage += (parent.logo.ErrMsg_SSN);
				 }
			 }
		}

	if (theForm.chk_centralAccountID){
	    if (theForm.chk_centralAccountID.checked == true)
	    {    checkedOption=1;
			theForm.hdn_noneChecked.value = 0;
			var x = theForm.txt_centralAccountID.value.length -1;
			var wspace = "false";

			while (wspace == "false"){
				if (theForm.txt_centralAccountID.value.charAt(x) == " ")
						{
							x=x-1;
						}
						else { wspace = "true";}

			}
			   if (theForm.txt_centralAccountID.value.charAt(x) == "*")
			   {
			   		var checkOK = "0123456789";
					var checkStr = theForm.txt_centralAccountID.value;
					var allValid = true;

					if(!ValidateWildCardString(theForm.txt_centralAccountID)) {
						allValid = false;
					}

					  for (i = 0;  i < x ; i++)
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
					        errormessage += (parent.logo.ErrMsg_AccountID);
					       }
					  else {
			       			CheckNumberOfWC(theForm.txt_centralAccountID);               			   }
			   			   }
               else if ((parent.logo.checkNumeric(theForm,theForm.txt_centralAccountID) == "error")||
                   (parent.logo.checkLength(theForm,theForm.txt_centralAccountID,7) == "error")||
                   (theForm.txt_centralAccountID.value == ""))
               {
                   errormessage += (parent.logo.ErrMsg_AccountID);

               }

            }
	}

	if (theForm.chk_centralAccountNumber){
		if (theForm.chk_centralAccountNumber.checked == true)
		{    checkedOption=1;
			theForm.hdn_noneChecked.value = 0;
			if ((parent.logo.checkNumeric(theForm,theForm.txt_centralAccountNumber) == "error") ||
				(parent.logo.checkLength(theForm,theForm.txt_centralAccountNumber,16) == "error")||
              	                (theForm.txt_centralAccountNumber.value == ""))
              	        {
              	             errormessage += (parent.logo.ErrMsg_AccountNumber);
              	        }
                 }
	}

	if (theForm.chk_centralAccountName){

		if (theForm.chk_centralAccountName.checked)
        {   checkedOption=1;
            theForm.txt_centralAccountName.value = stringTrim(theForm.txt_centralAccountName.value);
			theForm.hdn_noneChecked.value = 0;
			if ((parent.logo.checkAlphaNumericWC(theForm,theForm.txt_centralAccountName) == "error")||
              	(theForm.txt_centralAccountName.value == "") || !ValidateWildCardString(theForm.txt_centralAccountName))
              	{
              	 errormessage += (parent.logo.ErrMsg_AccountName);
              	}
            else {
            	CheckNumberOfWC(theForm.txt_centralAccountName);
            }
        }
	}

     if (theForm.chk_hierLevel){
		 if (theForm.chk_hierLevel.checked == true)
			 { checkedOption=1;
			  theForm.hdn_noneChecked.value = 0;
			  if ((theForm.txt_hl0) && (theForm.txt_hl0.type != "hidden"))
				  {
				   if ((theForm.txt_hl0 == "")&&
					   (theForm.txt_hl1.value == "")&&
					   (theForm.txt_hl2.value == "")&&
					   (theForm.txt_hl3.value == "")&&
					   (theForm.txt_hl4.value == "")&&
					   (theForm.txt_hl5.value == "")&&
					   (theForm.txt_hl6.value == "")&&
					   (theForm.txt_hl7.value == "")&&
					   (theForm.txt_hl8.value == ""))
					   {
						errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
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
						 errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
					   }
				  }

			  if (theForm.txt_hl0)
				  {
				   if ((parent.logo.checkNumeric(theForm,theForm.txt_hl0) == "error")||
					   (parent.logo.checkLength(theForm,theForm.txt_hl0,7) == "error")||
					   theForm.txt_hl0.value <=0)
					   {
						errormessage += (parent.logo.ErrMsg_ProgramNumber);
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
				   if (parent.logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,
					   theForm.txt_hl2,theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
					   theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
					   {errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;}
				  }
			  else
				  {
				   if (parent.logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
													theForm.txt_hl3,theForm.txt_hl4,
													theForm.txt_hl5,theForm.txt_hl6,
													theForm.txt_hl7,theForm.txt_hl8) == "error")
					   {errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;}
				  }
			 }
		}

     if (NumberOfWc > MaxWildCards)
	    {
	      	  errormessage += "You may only have "+MaxWildCards+" Wild Card per search.";
     	}

     if (theForm.txt_voyagerNumber) {
         if (theForm.chk_voyagerNumber.checked == true){
              checkedOption=1;
         	if((parent.logo.checkLength(theForm,theForm.txt_voyagerNumber,14)=="error")&&
         	    (parent.logo.checkLength(theForm,theForm.txt_voyagerNumber,15)=="error")||
         	    (parent.logo.checkNumeric(theForm,theForm.txt_voyagerNumber)=="error"))
         	     {
         	      errormessage += parent.logo.ErrMsg_VoyagerNumber;
         	     }
         }
     }

     if (theForm.chk_dateRange){
         if (theForm.chk_dateRange.checked == true){
			checkedOption=1;
			if (parent.logo.checkDate(theForm,theForm.txt_begdate) == "error")
			{
				errormessage += "Invalid Beginning Date\n";
			}

			if (parent.logo.checkDate(theForm,theForm.txt_endDate) == "error")
			{
				errormessage += "Invalid Ending Date\n";
			}

			var BMM = theForm.txt_begdate.value.substring(0,2) ;
			var EMM = theForm.txt_endDate.value.substring(0,2) ;
			var BDD = theForm.txt_begdate.value.substring(3,5) ;
			var EDD = theForm.txt_endDate.value.substring(3,5) ;
			var BYY = theForm.txt_begdate.value.substring(6,10) ;
			var EYY = theForm.txt_endDate.value.substring(6,10) ;

			if (parent.logo.compareTwoDates(theForm,BMM,BDD,BYY,EMM,EDD,EYY) == "error")
			{
				errormessage += (parent.logo.ErrMsg_DateRange) ;
			}

         }
     }

    if (theForm.chk_newGCSU || theForm.chk_newStatus)
	   {

		  if ( theForm.hdn_noneChecked2.value == 0 && checkedOption == 0 &&  trnAcctFlag == 0)
		  	    {
		  		errormessage+= "No Criteria Selected\n";
		  		}
		  	if ( theForm.hdn_noneChecked2.value == 1 && checkedOption == 0 )
		  	    {
		  	    errormessage+= "No Search By parameter checked\n";
		  		}
		  	if ( theForm.hdn_noneChecked2.value == 0 && checkedOption == 1)
		  	    {
		  		errormessage+= "No Search For parameter checked\n";
		  		}

	   }
	   else{
	       if (checkedOption == 0)
		   {
	   		trnAcctFlag=1;
	   		errormessage +="No Criteria Selected\n";
		    }
    	}

//	     && theForm.chk_accountNumber.checked == false && theForm.chk_hierLevel.checked == false)

	 if (theForm.txt_screenTitle1.value == "Individual Statement" && theForm.hdn_noneChecked2.value == 0
	     && (theForm.chk_accountNumber && theForm.chk_accountNumber.checked == false)
		 && (theForm.chk_hierLevel && theForm.chk_hierLevel.checked == false) )
  	    {
  	    errormessage+= "No Search By parameter checked\n";
  		}


     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return false;
         }
     else
	 	{//set the hierarchy for ReviseSearch here. We have to do it here to handle formTest failure.
		    if (theForm.txt_hl1) //if hierarchy.html included
			{
 				setReviseSearchHierarchy(theForm);
            	return true;
			}
	 	}
    }


<!--*******************  DISPLAY MESSAGE  **************************-->
<!--*This function checks to see if a field is empty.  If it is it *-->
<!--*deselects the check box                                       *-->
<!--****************************************************************-->

function controlEmpty(chk_box, field1, field2, field3, field4)
    {
	if (field1 == "" && field2 == "" && field3 == "" && field4 == "")
	    {
		chk_box.checked = false;
	    }
    }


<!--*******************  DISPLAY MESSAGE  **************************-->
<!--*This function checks to see if a field is empty.  If it is it *-->
<!--*deselects the check box                                       *-->
<!--****************************************************************-->

function hierarchyEmpty(chk_box, field0, field1, field2, field3, field4, field5, field6, field7, field8)
    {
	if (field0 == "" && field1 == "" && field2 == "" && field3 == "" && field4 == "" && field5 == "" && field6 == "" && field7 == "" && field8 == "")
	    {
		chk_box.checked = false;
	    }
    }



<!--*******************  DISPLAY MESSAGE  **************************-->
<!--*This function receives a text message and displays it in the  *-->
<!--*Netscape Status Bar. -BR                                      *-->
<!--****************************************************************-->

function Display_Message(message)
    {
     self.status = message;
     return true;
    }


<!--******************* OTHER OPTION CLEAR*****************************-->
<!--*This functions clears the contents of all fields in other options*-->
<!--*******************************************************************-->


function accountNumberSetRadioGroup(theForm)
  {
  theForm.rag_accountInquirySearch[0].checked=true;
  theForm.hdn_search_by.value="accountNumber";
  if (theForm.txt_programNumber) {theForm.txt_programNumber.value="";}
  if (theForm.txt_hl0) {theForm.txt_hl0.value="";}
  theForm.txt_hl1.value="";
  theForm.txt_hl2.value="";
  theForm.txt_hl3.value="";
  theForm.txt_hl4.value="";
  theForm.txt_hl5.value="";
  theForm.txt_hl6.value="";
  theForm.txt_hl7.value="";
  theForm.txt_hl8.value="";
  theForm.txt_hierarchyDepth.selectedIndex="0";
  clearHierDesc(theForm,1);
  return true;
  }

function requestSetRadioGroup(theForm)
  {
  theForm.txt_accountNumber.value="";
  theForm.hdn_search_by.value="allRequests";
  if (theForm.txt_programNumber) {theForm.txt_programNumber.value="";}
  if (theForm.txt_hl0) {theForm.txt_hl0.value="";}
  theForm.txt_hl1.value="";
  theForm.txt_hl2.value="";
  theForm.txt_hl3.value="";
  theForm.txt_hl4.value="";
  theForm.txt_hl5.value="";
  theForm.txt_hl6.value="";
  theForm.txt_hl7.value="";
  theForm.txt_hl8.value="";
  theForm.txt_hierarchyDepth.selectedIndex="0";
  clearHierDesc(theForm,1);
  return true;
  }

  function partialrequestSetRadioGroup(theForm)
    {
    theForm.txt_accountNumber.value="";
    theForm.hdn_search_by.value="filterRequests";
    if (theForm.txt_programNumber) {theForm.txt_programNumber.value="";}
	if (theForm.txt_hl0) {theForm.txt_hl0.value="";}
    theForm.txt_hl1.value="";
    theForm.txt_hl2.value="";
    theForm.txt_hl3.value="";
    theForm.txt_hl4.value="";
    theForm.txt_hl5.value="";
    theForm.txt_hl6.value="";
    theForm.txt_hl7.value="";
    theForm.txt_hl8.value="";
    theForm.txt_hierarchyDepth.selectedIndex="0";
    clearHierDesc(theForm,1);
    return true;
  }

function hierarchyLevelSetRadioGroup(theForm)
  {
  theForm.rag_accountInquirySearch[3].checked=true;
  theForm.hdn_search_by.value="hierarchyLevel";
  theForm.rag_accountInquirySearch[3].checked=true;
  theForm.txt_accountNumber.value="";
  return true;
  }

function dateRangeSet(theForm)
	    {
	     if (theForm.chk_dateRange.checked == true)
		 {
	        SetFocus(theForm,theForm.txt_begdate,'Enter Beginning Transaction Date');
		 }
		 else
		 {
		    theForm.txt_begdate.value = "";
			theForm.txt_endDate.value = "";
		 }
	     return true;
    }



function transDateSet(theForm)
    {
     if (theForm.chk_transDate.checked == true)
	 {
        SetFocus(theForm,theForm.txt_begTransDate,'Enter Beginning Transaction Date');
	 }
	 else
	 {
	    theForm.txt_begTransDate.value = "";
		theForm.txt_endTransDate.value = "";
	 }
     return true;
    }

function pendingUntilSet(theForm)
	    {
		 if (theForm.chk_pendingUntil.checked == true)
		 {
			parent.logo.setFocus(theForm,theForm.txt_pendingUntilFrom,"Enter Pending Until Date Range");
			//parent.logo.setFocus(theForm,theForm.txt_pendingUntilTo,"Enter Pending Until Date Range");
		 }
		 else
		 {
			theForm.txt_pendingUntilFrom.value = "";
			theForm.txt_pendingUntilTo.value = "";
		 }
		 return true;
	    }

function effectiveDateSet(theForm)
	    {
		 if (theForm.chk_effectiveDate.checked == true)
		 {
			parent.logo.setFocus(theForm,theForm.txt_effectiveDateFrom, "Enter Effective Date Range");
			//parent.logo.setFocus(theForm,theForm.txt_effectiveDateTo, "Enter Effective Date Range");
		 }
		 else
		 {
			theForm.txt_effectiveDateFrom.value = "";
			theForm.txt_effectiveDateTo.value = "";
		 }
		 return true;
    }


function accountNumberSet(theForm)
    {
	 if (theForm.chk_accountNumber.checked == true)
	 {
		SetFocus(theForm,theForm.txt_accountNumber,'Enter Account Number');
	 }
	 else
	 {
		theForm.txt_accountNumber.value = "";
	 }
	 return true;
    }

function userIDSet(theForm)
    {
	 if (theForm.chk_userID.checked == true)
	 {
		SetFocus(theForm,theForm.txt_userID,'Enter User ID');
	 }
	 else
	 {
		theForm.txt_userID.value = "";
	 }
	 return true;
    }


 function equipmentIDSet(theForm)
     {
 	 if (theForm.chk_equipmentID.checked == true)
 	 {
 		SetFocus(theForm,theForm.txt_equipmentID,'Enter Equipment ID');
 	 }
 	 else
 	 {
 		theForm.txt_equipmentID.value = "";
 	 }
 	 return true;
    }

function lastNameSet(theForm)
    {
	 if (theForm.chk_Name.checked == true)
	 {
		SetFocus(theForm,theForm.txt_lastName,'Enter Last Name');
	 }
	 else
	 {
		theForm.txt_lastName.value = "";
		theForm.txt_firstName.value = "";
		if (theForm.txt_zipCode)
		{
			theForm.txt_zipCode.value = "";
		}
	 }
	 return true;
    }

//function zipCodeSet(theForm)
 //   {
	// if (theForm.chk_zipCode.checked == true)
	// {
	//	SetFocus(theForm,theForm.txt_zipCode,parent.logo.Status_Text_Zip);
	// }
	// else
	// {
	//	theForm.txt_zipCode.value = "";
	// }
	// return true;
    //}

function busPhoneSet(theForm)
    {
	 if (theForm.chk_busPhone.checked == true)
	 {
		SetFocus(theForm,theForm.txt_businessPhone1,parent.logo.Status_Text_Business_Phone);
	 }
	 else
	 {
            if(theForm.txt_businessPhone1){
		    	theForm.txt_businessPhone1.value = "";
		    	theForm.txt_businessPhone2.value = "";
		    	theForm.txt_businessPhone3.value = "";
            }
            if(theForm.txt_businessPhoneIntl) {
		    	theForm.txt_businessPhoneIntl.value = "";
		   	}
            theForm.txt_businessPhone.value = "";
	 }
	 return true;
    }

function ssnSet(theForm)
    {
	 if (theForm.chk_SSN.checked == true)
	 {
		SetFocus(theForm,theForm.txt_SS1,'Enter Social Security Number');
	 }
	 else
	 {
		theForm.txt_socialSecurityNumber.value = "";
		theForm.txt_SS1.value = "";
		theForm.txt_SS2.value = "";
		theForm.txt_SS3.value = "";
	 }
	 return true;
    }

function centralAcctIDSet(theForm)
    {
	 if (theForm.chk_centralAccountID.checked == true)
	 {
		SetFocus(theForm,theForm.txt_centralAccountID,'Enter Central Account ID');
	 }
	 else
	 {
		theForm.txt_centralAccountID.value = "";
	 }
	 return true;
    }

function centralAcctNbrSet(theForm)
    {
	 if (theForm.chk_centralAccountNumber.checked == true)
	 {
		SetFocus(theForm,theForm.txt_centralAccountNumber,'Enter Central Account Number');
	 }
	 else
	 {
		theForm.txt_centralAccountNumber.value = "";
	 }
	 return true;
    }

function centralAcctNameSet(theForm)
    {
	 if (theForm.chk_centralAccountName.checked == true)
	 {
		SetFocus(theForm,theForm.txt_centralAccountName,'Enter Central Account Name');
	 }
	 else
	 {
		theForm.txt_centralAccountName.value = "";
	 }
	 return true;
    }

function agencyNameSet(theForm)
   {
    if (theForm.chk_agencyName.checked == true )
      {
	  SetFocus(theForm,theForm.txt_agencyName,'Enter Agency Name');
	  }
	   else
	   {
		 theForm.txt_agencyName.value = "";
	   }
		return true;
	   }

function accountingCenterNameSet(theForm)
	  {
      if (theForm.chk_accountingCenterName.checked == true )
		 {
		  SetFocus(theForm,theForm.txt_accountingCenterName,'Enter Accounting Center Name');
		 }
		 else
		 {
		   theForm.txt_accountingCenterName.value = "";
		 }
		 return true;
	  }

function accountingCenterIdSet(theForm)
	  {
     if (theForm.chk_accountingCenterId.checked == true )
	   {
	   SetFocus(theForm,theForm.txt_accountingCenterId,'Enter Accounting Center ID');
	   }
	   else
	   	 {
	   	   theForm.txt_accountingCenterId.value = "";
	   	 }
	   	 return true;
	  }

function hierarchyLevelSet(theForm)
    {
	 if (theForm.chk_hierLevel.checked == true)
	 	 {
	 		if ((theForm.txt_hl0) && (theForm.txt_hl0.type != "hidden"))
			{
		  	  SetFocus(theForm,theForm.txt_hl0,parent.logo.Status_Text_HierarchyLevel);
		    }
			else
			{
				if (theForm.txt_hl1 && (theForm.txt_hl1.type != "hidden") ) {
					SetFocus(theForm,theForm.txt_hl1,parent.logo.Status_Text_HierarchyLevel);
				} else if (theForm.txt_hl2 && (theForm.txt_hl2.type != "hidden")) {
					SetFocus(theForm,theForm.txt_hl2,parent.logo.Status_Text_HierarchyLevel);
				} else if (theForm.txt_hl3 && (theForm.txt_hl3.type != "hidden") ) {
					SetFocus(theForm,theForm.txt_hl3,parent.logo.Status_Text_HierarchyLevel);
				} else if (theForm.txt_hl4 && (theForm.txt_hl4.type != "hidden") ) {
					SetFocus(theForm,theForm.txt_hl4,parent.logo.Status_Text_HierarchyLevel);
				} else if (theForm.txt_hl5 && (theForm.txt_hl5.type != "hidden") ) {
					SetFocus(theForm,theForm.txt_hl5,parent.logo.Status_Text_HierarchyLevel);
				} else if (theForm.txt_hl6 && (theForm.txt_hl6.type != "hidden") ) {
					SetFocus(theForm,theForm.txt_hl6,parent.logo.Status_Text_HierarchyLevel);
				} else if (theForm.txt_hl7 && (theForm.txt_hl7.type != "hidden") ) {
					SetFocus(theForm,theForm.txt_hl7,parent.logo.Status_Text_HierarchyLevel);
				} else if (theForm.txt_hl8 && (theForm.txt_hl8.type != "hidden") ) {
					SetFocus(theForm,theForm.txt_hl8,parent.logo.Status_Text_HierarchyLevel);
				}
		    }
	 	 }
	 	 else
	 	 {
			 theForm.txt_hierarchyDepth.options[0].selected = true;
			 if (theForm.txt_programNumber) {theForm.txt_programNumber.value = "";}
			 if (theForm.txt_hl0.type == "hidden") {
				if (theForm.txt_programNumber) {theForm.txt_programNumber.value = "";}
			 }
			 else {
        		theForm.txt_hl1.value = "";
        		clearHierDesc(theForm,1);
        		if (theForm.txt_hl0) {theForm.txt_hl0.value = "";}
			 }
			 if (theForm.txt_hl2.type != "hidden") {
                theForm.txt_hl2.value = "";
             	clearHierDesc(theForm,2);
             }
			 if (theForm.txt_hl3.type != "hidden") {
                theForm.txt_hl3.value = "";
             	clearHierDesc(theForm,3);
             }
             if (theForm.txt_hl4.type != "hidden") {
			    theForm.txt_hl4.value = "";
			    clearHierDesc(theForm,4);
             }
             if (theForm.txt_hl5.type != "hidden") {
			    theForm.txt_hl5.value = "";
			    clearHierDesc(theForm,5);
             }
             if (theForm.txt_hl6.type != "hidden") {
			    theForm.txt_hl6.value = "";
			    clearHierDesc(theForm,6);
             }
             if (theForm.txt_hl7.type != "hidden") {
			    theForm.txt_hl7.value = "";
			    clearHierDesc(theForm,7);
             }
             if (theForm.txt_hl8.type != "hidden") {
			    theForm.txt_hl8.value = "";
			  	clearHierDesc(theForm,8);
             }
	 	 }
	 return true;
    }

<!------changeContent(getObject("hl1Desc"), "");      ---->
function currentTransactionsSet(theForm)
  {
  theForm.rag_invoiceSearch[0].checked=true;
  theForm.txt_invoiceDate.value="";
  return true;
  }

function currentInvoiceSet(theForm)
  {
  theForm.rag_invoiceSearch[1].checked=true;
  theForm.txt_invoiceDate.value="";
  return true;
  }

function invoiceDateSet(theForm)
  {
  theForm.rag_invoiceSearch[2].checked=true;
  return true;
  }

function voyagerNumberSet(theForm)
 {
  if (theForm.chk_voyagerNumber.checked == true)
  {
 	SetFocus(theForm,theForm.txt_voyagerNumber,'Enter Voyager Number');
  }
  else
  {
	theForm.txt_voyagerNumber.value = "";
  }
  return true;
 }

function setIntlPhone(theForm)
{
	theForm.txt_businessPhone1.value="";
	theForm.txt_businessPhone2.value="";
	theForm.txt_businessPhone3.value="";
	theForm.chk_busPhone.checked = true;

 	return true;
}
function setLocalPhone(theForm)
{
	theForm.txt_businessPhoneIntl.value="";
	theForm.chk_busPhone.checked = true;

 	return true;
}
<!--******************* SET FOCUS *************************************-->
<!--*This function sets focus on particular field in Netscape Navigator-->
<!--*******************************************************************-->

function SetFocus(theForm,theField,theMessage)
    {
     if (window.focus)
         {
          theField.focus();
          Display_Message(theMessage);
         }
     return true;
    }

// THIS SECTION WAS ADDED TO SUPPORT HIERARCHY BROWSING

function openNewWindow(theUrl)
    {
     msg = window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=400,status=no,menubar=no");
    }

<!-- Same as in logo.html, but added wildcard character '*' -DT 04/21/99 -->

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

function setPhoneFormat(theForm, format){

    theForm.chk_busPhone.value=true;
    if(format == "dom"){
	document.all.areaPhone.innerHTML = "<INPUT TYPE='Text' NAME='txt_businessPhone1' SIZE='3' MAXLENGTH='3' value='' onFocus='Display_Message(parent.logo.Status_Text_Business_Phone); form.chk_busPhone.checked=true' ><INPUT TYPE='Text' NAME='txt_businessPhone2' SIZE='3' MAXLENGTH='3' value='' onFocus='Display_Message(parent.logo.Status_Text_Business_Phone); form.chk_busPhone.checked=true' ><INPUT TYPE='Text' NAME='txt_businessPhone3' SIZE='4' MAXLENGTH='4' value='' onFocus='Display_Message(parent.logo.Status_Text_Business_Phone); form.chk_busPhone.checked=true'>";
    }else if(format = "intl"){
	document.all.areaPhone.innerHTML = "<INPUT TYPE='Text' NAME='txt_businessPhoneIntl' SIZE='17' MAXLENGTH='17' value='' onFocus='Display_Message(parent.logo.Status_Text_Business_Phone); form.chk_busPhone.checked=true'>"
    }
}
<!--*******************  CHECK ZIP CODE  ************************************-->
<!--*This function receives a form and field. It then checks to see if the  *-->
<!--*all the characters in the field are numeric with a dash and either     *-->
<!--*in the form of 5 numbers, or 5 numbers, a dash, then 4 numbers         *-->
<!--*************************************************************************-->

function checkZipCode(theForm,theField)
  {
   var checkOK = "0123456789-";
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


<!--*******************  CHECK NUMERIC  ****************************-->
<!--*This function receives a form and field. It then checks to see*-->
<!--*if the all the characters in the field are numeric.           *-->
<!--****************************************************************-->

function checkNumeric(theForm,theField)
 {
   var checkOK = "0123456789*";
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

 function clearFieldsB(theForm)
     {
      //theForm.txt_agencyName.value = "";
      theForm.chk_hierLevel.checked=true;
     }


function stringTrim( strVar )
{
     var newArray = strVar.split( " " );
     var retVal = "";
     var charFound = false;
     for ( var i=0;i<newArray.length;i++ )
     {
       if ( newArray[i] == "" && charFound )
         {
         retVal = retVal + " " ; }
       else if ( newArray[i] != "" )
           {
           retVal = retVal + newArray[i] + " " ;
           charFound = true; }
       }
     newArray = retVal.split( " " );
     charFound = false;
     retVal = "";
     var i;
     for ( i=newArray.length-1; i>=0; i-- )
     {
       if ( newArray[i] != "" )
         break;
     }
     // have a value for i
     theSpace = ""
     for ( var j=0;j<=i;j++ )
     {
       if ( j== 1  )
         theSpace = " ";
       retVal = retVal + theSpace + newArray[j];
     }
     return retVal;

 }


 function StatementDateset(theForm)
 {
	 	theForm.rag_statementSearch[2].checked=true;
		theForm.rag_statementSearch[3].checked=false;
		theForm.txt_begTransDate.value="";
	 	theForm.txt_endTransDate.value="";
		return true;
 }

 function TransactionDateSet(theForm)
 {
 		theForm.rag_statementSearch[3].checked=true;
		theForm.rag_statementSearch[2].checked=false;
		theForm.txt_statementDate.value="";
		return true;
 }





<!--*******************  resetFix       ****************************-->
<!--*This function is a workaround for a bug in IE3 where a RESET  *-->
<!--*is not allowing the text inputs to be populated from popup    *-->
<!--*Function is being called from onClick event of Reset button!  *-->
<!--****************************************************************-->

function resetFix( theForm )
{
	if (theForm.chk_equipmentID)
	{
		theForm.chk_equipmentID.checked = false;
        theForm.txt_equipmentID.value =  "";
	}

	if (theForm.chk_transDate)
    {
		theForm.chk_transDate.checked = false;
		theForm.txt_begTransDate.value = "";
	}

    if (theForm.chk_accountNumber)
    {
		theForm.chk_accountNumber.checked = false;
		theForm.txt_accountNumber.value = "";
    }

    if (theForm.chk_userID)
    {
		theForm.chk_userID.checked = false;
		theForm.txt_userID.value = "";
    }

    if (theForm.chk_Name){
		theForm.chk_Name.checked = false;
		theForm.txt_lastName.value = "";
	}

	if (theForm.chk_busPhone) {
		theForm.chk_busPhone.checked = false;
                if(theForm.txt_businessPhone1){
                     theForm.txt_businessPhone1.value ="";
                     theForm.txt_businessPhone2.value ="";
                     theForm.txt_businessPhone3.value ="";
                }else if(theForm.txt_businessPhoneIntl)
                     theForm.txt_businessPhoneIntl.value ="";
		theForm.txt_businessPhone.value = "";
	}


    if (theForm.chk_SSN){
		theForm.chk_SSN.checked = false;
		theForm.txt_socialSecurityNumber.value == "";
    }

    if (theForm.chk_centralAccountID){
		theForm.chk_centralAccountID.checked = false;
		theForm.txt_centralAccountID.value = "";
    }

    if (theForm.chk_centralAccountNumber){
		theForm.chk_centralAccountNumber.checked = false;
		theForm.txt_centralAccountNumber.value = "";
    }

    if (theForm.chk_centralAccountName){
		theForm.chk_centralAccountName.checked = false;
		theForm.txt_centralAccountName.value = "";
    }

	if (theForm.chk_accountingCenterId) {
		    theForm.chk_accountingCenterId.checked = false;
			theForm.txt_accountingCenterId.value = "";
	}
	if (theForm.chk_accountingCenterName){
		    theForm.chk_accountingCenterName.checked = false;
			theForm.txt_accountingCenterName.value = "";
	}
	if (theForm.chk_agencyName){
		    theForm.chk_agencyName.checked = false;
			theForm.txt_agencyName.value = "";
	}
    if (theForm.chk_hierLevel){
     if (theForm.txt_programNumber) {
	 	theForm.txt_programNumber.value = "";
	 }
     if (theForm.txt_hl1.type != "hidden") {
        	theForm.txt_hl1.value = "";
        	changeContent(getObject("hl1Desc"), "");
     	}
     if (theForm.txt_hl2.type != "hidden")
        {
         theForm.txt_hl2.value = "";
         changeContent(getObject("hl2Desc"), "");
        }
     if (theForm.txt_hl3.type != "hidden")
    	 {
    	  theForm.txt_hl3.value = "";
    	  changeContent(getObject("hl3Desc"), "");
    	 }
   	 if (theForm.txt_hl4.type != "hidden")
   		 {
   		  theForm.txt_hl4.value = "";
   		  changeContent(getObject("hl4Desc"), "");
   		 }
   	 if (theForm.txt_hl5.type != "hidden")
   		 {
   		  theForm.txt_hl5.value = "";
   		  changeContent(getObject("hl5Desc"), "");
   		 }
   	 if (theForm.txt_hl6.type != "hidden")
   		 {
   		  theForm.txt_hl6.value = "";
   		  changeContent(getObject("hl6Desc"), "");
   		 }
   	 if (theForm.txt_hl7.type != "hidden")
   		 {
   		  theForm.txt_hl7.value = "";
   		  changeContent(getObject("hl7Desc"), "");
   		 }
   	 if (theForm.txt_hl8.type != "hidden")
   		 {
   		  theForm.txt_hl8.value = "";
   		  changeContent(getObject("hl8Desc"), "");
   		 }
	if (theForm.hierParmsPassed.value == "1")
		{
		 theForm.chk_hierLevel.checked=false;
   		 refreshHierarchy();
        }
    }

    if(theForm.rag_accountInquirySearch){
    theForm.rag_accountInquirySearch[0].checked=true;
	  theForm.txt_accountNumber.value="";
	  if (theForm.txt_programNumber) {theForm.txt_programNumber.value="";}

     if (theForm.txt_hl1.type != "hidden") {
        	theForm.txt_hl1.value = "";
        	changeContent(getObject("hl1Desc"), "");
     	}
     if (theForm.txt_hl2.type != "hidden")
        {
         theForm.txt_hl2.value = "";
         changeContent(getObject("hl2Desc"), "");
        }
     if (theForm.txt_hl3.type != "hidden")
    	 {
    	  theForm.txt_hl3.value = "";
    	  changeContent(getObject("hl3Desc"), "");
    	 }
   	 if (theForm.txt_hl4.type != "hidden")
   		 {
   		  theForm.txt_hl4.value = "";
   		  changeContent(getObject("hl4Desc"), "");
   		 }
   	 if (theForm.txt_hl5.type != "hidden")
   		 {
   		  theForm.txt_hl5.value = "";
   		  changeContent(getObject("hl5Desc"), "");
   		 }
   	 if (theForm.txt_hl6.type != "hidden")
   		 {
   		  theForm.txt_hl6.value = "";
   		  changeContent(getObject("hl6Desc"), "");
   		 }
   	 if (theForm.txt_hl7.type != "hidden")
   		 {
   		  theForm.txt_hl7.value = "";
   		  changeContent(getObject("hl7Desc"), "");
   		 }
   	 if (theForm.txt_hl8.type != "hidden")
   		 {
   		  theForm.txt_hl8.value = "";
   		  changeContent(getObject("hl8Desc"), "");
   		 }
  	  theForm.txt_hierarchyDepth.selectedIndex="0";

  }
}   // resetFix

function refreshHierarchy(){
   if(document.theForm.txt_hl1){
       if(document.theForm.txt_hl1.value == ""){
          clearHierDesc(document.theForm,1);
       }else {
          updateHierarchyDesc(document.theForm);
       }
   }
}

function resetIsLoaded() {
	isLoaded = true;
}

function callClearReviseHierarchy(){
	if (window.top.logo.delimitedReviseHierarchy)
		window.top.logo.delimitedReviseHierarchy='';
	else
	    if (document.theForm.txt_hl1) clearReviseHierarchy();//if hierarchy.html included
}

// Stop hiding -->
</SCRIPT>
</HEAD>

<!------------------------------------------------------------------->

<BODY onLoad="resetIsLoaded();refreshHierarchy();callClearReviseHierarchy();populateReviseSearchHierarchy(document.theForm);">

<%--<GX  type=replace ID=actionLink value=link>--%>
<html:form action="/search/account_inquiry_maintenance"
        name="theForm" type="com.boa.eagls.government.dto.search.SearchDTO"
            method="POST" scope="request"
            onsubmit="return Form_Test(document.theForm);" >
<%--<FORM NAME="theForm"--%>
<%--      ACTION="link"--%>
<%--      METHOD="POST"--%>
<%--      onSubmit="return Form_Test(document.theForm);">--%>
<%--</GX>--%>


<%--<GX TYPE="include" ID="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX>--%>
<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          Account Inquiry/Maintenance<BR>
          Search
        </H2>
      </TD>
    </TR>
</TABLE>

<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="Account Inquiry/Maintenance">

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>

<!------------------------------------------------------------------->
<%--<GX type=cell id=wildCard>--%>
<center>
  <b><i>To perform partial name searches on non numeric fields, <BR>enter partial search criteria followed by an asterisk '*'.<BR>
  Ex: Entering 'Jo*' in the Last Name field<BR>will find last names such as 'Jones' and 'Johnson'.<BR>
  Note: Partial searches take longer to complete than exact searches.</i></b>
</center>
<%--</GX>--%>

<BR>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_submit" VALUE="Submit">&nbsp;&nbsp;

        <INPUT TYPE="Reset" NAME="but_clear" VALUE="&nbsp;Clear&nbsp;" onClick="resetHierarchy( document.theForm );">
      </TD>
    </TR>
</TABLE>







<!-- Show Account MaintSearch For-->
<%--<GX type=cell id=accountMaintSearchFor>--%>

<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Search For</FONT></B>
<TABLE>
    <TR>
      <TD WIDTH="20">
        <html:radio value="O" property="rag_acctMaintSearchFor"
    	    onclick="Display_Message('Search For Open Accounts')"/>
      </TD>
      <TD COLSPAN="2">Open Accounts</TD>
    </TR>
    <TR>
      <TD>
        <html:radio value="C" property="rag_acctMaintSearchFor"
    	    onclick="Display_Message('Search Closed Accounts')"/>
      </TD>
      <TD COLSPAN="2">Closed Accounts</TD>
	  <TD>&nbsp;</TD>
    </TR>
    <TR>
      <TD>
        <html:radio value="" property="rag_acctMaintSearchFor"
    	    onclick="Display_Message('Search For All Accounts')"/>
<%--        <INPUT TYPE="Radio" NAME="rag_acctMaintSearchFor" VALUE=""--%>
<%--                onClick="Display_Message('Search For All Accounts')">--%>
      </TD>
      <TD WIDTH="110">All Accounts</TD>
	  <TD>&nbsp;</TD>
</TABLE>
<%--</GX>--%>



<TABLE>


<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Search By</FONT></B>

    <!-- Show Account Number -->
<%--    <GX type=cell id=searchAccountNumber>--%>
    <TR>
      <TD WIDTH="20">
        <!--html:checkbox name="theForm" property="chk_accountNumber"
            onclick="accountNumberSet(document.theForm)"/-->
        <INPUT TYPE="Checkbox" NAME="chk_accountNumber" onClick="accountNumberSet(document.theForm)" >
      </TD>
      <TD WIDTH="175">Account Number</TD>
      <TD WIDTH="406">
        <!--html:text name="theForm" property="txt_accountNumber"
            size="19" maxlength="16"
            onfocus="Display_Message(parent.logo.Status_Text_AccountNumber);form.chk_accountNumber.checked=true"
            onblur="controlEmpty(form.chk_accountNumber, form.txt_accountNumber.value, '', '','')"/-->
        <INPUT TYPE="Text"  NAME="txt_accountNumber" SIZE="19" MAXLENGTH="16"
                onFocus="Display_Message(parent.logo.Status_Text_AccountNumber);
                        form.chk_accountNumber.checked=true"
	            onBlur="controlEmpty(form.chk_accountNumber, form.txt_accountNumber.value, '', '','')" >
      </TD>
    </TR>
<%--    </GX>--%>
    <!-- End Show Account Number -->


    <!-- Show Name -->
<%--    <GX type=cell id=searchName>--%>
    <TR>
      <TD>
        <html:checkbox name="theForm" property="chk_Name"
            onclick="lastNameSet(document.theForm)"/>
<%--        <INPUT TYPE="Checkbox" NAME="chk_Name"--%>
<%--               onClick="lastNameSet(document.theForm)">--%>
      </TD>
      <TD WIDTH="175">Last Name</TD>
      <TD>
        <html:text name="theForm" property="txt_lastName"
            size="27" maxlength="26"
            onfocus="Display_Message(parent.logo.Status_Text_LastName);form.chk_Name.checked=true"
            onblur="controlEmpty(form.chk_Name, form.txt_lastName.value, form.txt_firstName.value, form.txt_zipCode?form.txt_zipCode.value:'','')"
 />
<%--        <INPUT TYPE="Text"  NAME="txt_lastName" SIZE="27" MAXLENGTH="26"--%>
<%--               onFocus="Display_Message(parent.logo.Status_Text_LastName);--%>
<%--                        form.chk_Name.checked=true"--%>
<%--	       onBlur="controlEmpty(form.chk_Name, form.txt_lastName.value, form.txt_firstName.value, form.txt_zipCode?form.txt_zipCode.value:'','')">--%>
      </TD>
    </TR>
    <tr>
    <td> </TD>
      <td COLSPAN=2>&nbsp;&nbsp;<I> Optional Criteria with Last Name:</I></td>

    </tr>
    <TR>
      <TD>&nbsp;</TD>
      <TD>&nbsp;&nbsp;&nbsp;First Name</TD>
      <TD>
        <html:text name="theForm" property="txt_firstName"
            size="27" maxlength="26"
            onfocus="Display_Message(parent.logo.Status_Text_FirstName);form.chk_Name.checked=true"
            onblur="controlEmpty(form.chk_Name, form.txt_lastName.value, form.txt_firstName.value, form.txt_zipCode?form.txt_zipCode.value:'','')" />
<%--        <INPUT TYPE="Text"  NAME="txt_firstName" SIZE="27" MAXLENGTH="26"--%>
<%--               onFocus="Display_Message(parent.logo.Status_Text_FirstName);--%>
<%--                        form.chk_Name.checked=true"--%>
<%--	       onBlur="controlEmpty(form.chk_Name, form.txt_lastName.value, form.txt_firstName.value, form.txt_zipCode?form.txt_zipCode.value:'','')">--%>
      </TD>
    </TR>

    <!-- Show Zip Code -->
<%--    <GX type=cell id=searchZip>--%>
	<TR>
<td>&nbsp </td>

	  <TD>&nbsp;&nbsp;&nbsp;Zip/Postal Code</TD>
	  <TD>
        <html:text name="theForm" property="txt_zipCode"
            size="14" maxlength="13"
            onfocus="Display_Message(parent.logo.Status_Text_Zip);form.chk_Name.checked=true"
            onblur="controlEmpty(form.chk_Name, form.txt_lastName.value, form.txt_firstName.value, form.txt_zipCode.value,'')" />
<%--	     <INPUT TYPE="Text" NAME="txt_zipCode" MAXLENGTH="13" SIZE="14" value=""--%>
<%--	      onFocus="Display_Message(parent.logo.Status_Text_Zip);--%>
<%--                        form.chk_Name.checked=true"--%>
<%--	       onBlur="controlEmpty(form.chk_Name, form.txt_lastName.value, form.txt_firstName.value, form.txt_zipCode.value,'')">--%>
	  </TD>
	</TR>
<%--    </GX>--%>
    <!-- End Zip Code -->
<%--    </GX>--%>
    <!-- End Show Name -->

    <!-- Show Business Phone -->
<%--    <GX type=cell id=searchBusPhone>--%>
	<TR>
	  <TD>
        <html:checkbox name="theForm" property="chk_busPhone" value="T"
            onclick="busPhoneSet(document.theForm)"/>
<%--	     <INPUT TYPE="checkbox" NAME="chk_busPhone" value="T"--%>
<%--		        onClick="busPhoneSet(document.theForm)">--%>
	  </TD>
	  <TD WIDTH="175">Business Phone</TD>
          </TR>
          <TR>
          <TD>&nbsp;</TD>
        <!--  <TD><FONT size="-1">&nbsp;&nbsp;
                <INPUT TYPE="Radio" NAME="rad_phoneFormat" VALUE="O" CHECKED
                    onClick="Display_Message('US formatted Phone Number');setPhoneFormat(theForm, 'dom');form.chk_busPhone.checked=true;">US or Canada<BR>
		&nbsp;&nbsp;
                <INPUT TYPE="Radio" NAME="rad_phoneFormat" VALUE="1"
               onClick="Display_Message('International Phone Format');setPhoneFormat(theForm, 'intl');form.chk_busPhone.checked=true;">International</FONT></TD>
	  -->
	  <TD><FONT size="-1">US or Canada</FONT></TD>
	  <TD>
        <html:text name="theForm" property="txt_businessPhone1"
            size="3" maxlength="3"
            onfocus="Display_Message(parent.logo.Status_Text_Business_Phone);form.chk_busPhone.checked=true;setLocalPhone(theForm);"
            onkeyup="parent.logo.moveFocus(txt_businessPhone1, txt_businessPhone2, 3, parent.logo.IE ? event.keyCode : event.which);"
	        onblur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)"/>
<%--	     <INPUT TYPE="Text" NAME="txt_businessPhone1" SIZE="3" MAXLENGTH="3" value=""--%>
<%--		        onFocus="Display_Message(parent.logo.Status_Text_Business_Phone);--%>
<%--                         form.chk_busPhone.checked=true;setLocalPhone(theForm);"--%>
<%--                onKeyUp="parent.logo.moveFocus(txt_businessPhone1, txt_businessPhone2, 3, parent.logo.IE ? event.keyCode : event.which);"--%>
<%--	       onBlur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)"--%>
<%--            >--%>
        <html:text name="theForm" property="txt_businessPhone2"
            size="3" maxlength="3"
            onfocus="Display_Message(parent.logo.Status_Text_Business_Phone);form.chk_busPhone.checked=true"
            onkeyup="parent.logo.moveFocus(txt_businessPhone2, txt_businessPhone3, 3, parent.logo.IE ? event.keyCode : event.which);"
            onblur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)"/>
<%--            <INPUT TYPE="Text" NAME="txt_businessPhone2" SIZE="3" MAXLENGTH="3" value=""--%>
<%--		        onFocus="Display_Message(parent.logo.Status_Text_Business_Phone);--%>
<%--                         form.chk_busPhone.checked=true"--%>
<%--                onKeyUp="parent.logo.moveFocus(txt_businessPhone2, txt_businessPhone3, 3, parent.logo.IE ? event.keyCode : event.which);"--%>
<%--	        onBlur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)"--%>
<%--            >--%>
        <html:text name="theForm" property="txt_businessPhone3"
            size="4" maxlength="4"
            onfocus="Display_Message(parent.logo.Status_Text_Business_Phone);form.chk_busPhone.checked=true"
            onblur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)"/>
<%--            <INPUT TYPE="Text" NAME="txt_businessPhone3" SIZE="4" MAXLENGTH="4" value=""--%>
<%--		        onFocus="Display_Message(parent.logo.Status_Text_Business_Phone);--%>
<%--                         form.chk_busPhone.checked=true"--%>
<%--	       onBlur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)">--%>
			<FONT size="-1">(Numeric Only)</FONT>
	  </TD>
	</TR>
	<TR>
          <TD>&nbsp;</TD>
          <TD><FONT size="-1">International</FONT></TD>
          <TD>
          <html:text name="theForm" property="txt_businessPhoneIntl"
            size="17" maxlength="17"
            onfocus="Display_Message(parent.logo.Status_Text_Business_Phone); form.chk_busPhone.checked=true;setIntlPhone(theForm);"
            onblur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)"/>
<%--          <INPUT TYPE='Text' NAME='txt_businessPhoneIntl' SIZE='17' MAXLENGTH='17' --%>
<%-- value='' onFocus='Display_Message(parent.logo.Status_Text_Business_Phone); form.chk_busPhone.checked=true;setIntlPhone(theForm);'--%>
<%--	       onBlur="controlEmpty(form.chk_busPhone, form.txt_businessPhone1.value, form.txt_businessPhone2.value, form.txt_businessPhone3.value, form.txt_businessPhoneIntl.value)">--%>
  			<FONT size="-1">(Numeric Only)</FONT>
			</TD>
    </TR>
<%--    </GX>--%>
    <!-- End Show Business Phone -->

    <!-- Show SS Number -->
<%--    <GX type=cell id=searchSocialSecurity>--%>
	<TR>
	  <TD>
      <html:checkbox name="theForm" property="chk_SSN"
        value="T" onclick="ssnSet(document.theForm)" />
<%--	     <INPUT TYPE="checkbox" NAME="chk_SSN" VALUE="T"--%>
<%--		        onClick="ssnSet(document.theForm)">--%>
	  </TD>
	  <TD nowrap>Social Security #</TD>
	  <TD nowrap>
        <html:text name="theForm" property="txt_SS1"
            size="3" maxlength="3"
            onfocus="Display_Message('Enter Social Security Number');form.chk_SSN.checked=true"
            onkeyup="parent.logo.moveFocus(txt_SS1, txt_SS2, 3, parent.logo.IE ? event.keyCode : event.which);"
            onblur="controlEmpty(form.chk_SSN, form.txt_SS1.value, form.txt_SS2.value, form.txt_SS3.value, '')" />

<%--	     <INPUT TYPE="Text" NAME="txt_SS1" SIZE="3" MAXLENGTH="3" value=""--%>
<%--		        onFocus="Display_Message('Enter Social Security Number');--%>
<%--                         form.chk_SSN.checked=true"--%>
<%--                onKeyUp="parent.logo.moveFocus(txt_SS1, txt_SS2, 3, parent.logo.IE ? event.keyCode : event.which);"--%>
<%--	       onBlur="controlEmpty(form.chk_SSN, form.txt_SS1.value, form.txt_SS2.value, form.txt_SS3.value, '')"--%>
<%--            >--%>
            <font size="+2">-</font>
            <html:text name="theForm" property="txt_SS2"
                size="2" maxlength="2"
                onfocus="Display_Message('Enter Social Security Number');form.chk_SSN.checked=true"
                onkeyup="parent.logo.moveFocus(txt_SS2, txt_SS3, 2, parent.logo.IE ? event.keyCode : event.which);"
                onblur="controlEmpty(form.chk_SSN, form.txt_SS1.value, form.txt_SS2.value, form.txt_SS3.value, '')"/>
<%--            <INPUT TYPE="Text" NAME="txt_SS2" SIZE="2" MAXLENGTH="2" value=""--%>
<%--		        onFocus="Display_Message('Enter Social Security Number');--%>
<%--                         form.chk_SSN.checked=true"--%>
<%--                onKeyUp="parent.logo.moveFocus(txt_SS2, txt_SS3, 2, parent.logo.IE ? event.keyCode : event.which);"--%>
<%--	       onBlur="controlEmpty(form.chk_SSN, form.txt_SS1.value, form.txt_SS2.value, form.txt_SS3.value, '')"--%>
<%--            >--%>
            <font size="+2">-</font>
            <html:text name="theForm" property="txt_SS3"
                size="4" maxlength="4"
                onfocus="Display_Message('Enter Social Security Number');form.chk_SSN.checked=true"
                onblur="controlEmpty(form.chk_SSN, form.txt_SS1.value, form.txt_SS2.value, form.txt_SS3.value, '')"/>
<%--            <INPUT TYPE="Text" NAME="txt_SS3" SIZE="4" MAXLENGTH="4" value=""--%>
<%--		        onFocus="Display_Message('Enter Social Security Number');--%>
<%--                         form.chk_SSN.checked=true"--%>
<%--	       onBlur="controlEmpty(form.chk_SSN, form.txt_SS1.value, form.txt_SS2.value, form.txt_SS3.value, '')">--%>
  			<FONT size="-1">(Numeric Only)</FONT>
	  </TD>
	</TR>
<%--    </GX>--%>
    <!-- End Show SS Number -->


<!-- Show Hierarchy -->
<%--    <GX type=cell id=searchHierarchy>--%>
    <TR>
      <TD WIDTH=20>
        <html:checkbox name="theForm" property="chk_hierLevel" value="hierarchy"
        onclick="hierarchyLevelSet(document.theForm)"/>
<%--	      <INPUT TYPE="checkbox" name="chk_hierLevel" VALUE="hierarchy" <GX type=cell id=chk_hierLevel> </GX>--%>
<%--             onClick="hierarchyLevelSet(document.theForm)";>--%>
      </TD>
      <TD>Hierarchy Depth
      </TD>
      </TD>
      <TD>
      <SELECT NAME="txt_hierarchyDepth" SIZE="1"
                  OnFocus="form.chk_hierLevel.checked=true;
                         parent.logo.Display_Message(parent.logo.Status_Text_HierarchyDepth)">
          <OPTION VALUE="0" SELECTED>Current Level</OPTION>
          <OPTION VALUE="1">One Level</OPTION>
          <OPTION VALUE="2">Two Levels</OPTION>
          <OPTION VALUE="3">Three Levels</OPTION>
          <OPTION VALUE="4">Four Levels</OPTION>
          <OPTION VALUE="5">Five Levels</OPTION>
          <OPTION VALUE="6">Six Levels</OPTION>
          <OPTION VALUE="7">Seven Levels</OPTION>
        </SELECT>

        <!--INPUT TYPE="BUTTON" NAME="but_Hierarchy" VALUE="Browse Hierarchy"
              onclick="form.chk_hierLevel.checked=true;
createHierarchyString(document.theForm,'/cgi-bin/gx.cgi/GUIDGX-{259A23D0-1B6A-11D2-96DD-204C4F4F5020}')"-->
        <input type="button" name="but_Hierarchy" value="Browse Hierarchy"
onclick="form.chk_hierLevel.checked=true;createHierarchyString(document.theForm,'<%=request.getContextPath()%>/browseHierarchy.do')">
      </TD>
    </TR>
</TABLE>
<BR>


<TABLE CELLSPACING="0" CELLPADDING="0" BORDER="0">
    <TR>
        <%@ include file="/jsp/gsa/common/right_hierarchy.jsp"%>
<%--	<GX TYPE=include ID="/gsa/common/hierarchy.html">Insert Hierachy display Here</GX>--%>
    </TR>
<%--    </GX>--%>
    <!-- End Show Hierarchy -->

<!-------------------------------------------------------------------------->

<BR>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_submit" VALUE="Submit">&nbsp;&nbsp;

        <INPUT TYPE="Reset" NAME="but_clear" VALUE="&nbsp;Clear&nbsp;" onClick="resetHierarchy( document.theForm );">
      </TD>
    </TR>
</TABLE>

<!-------------------------------------------------------------------------->


<BR><%@ include file = "/jsp/gsa/footer_systemDefault.jsp"%>


<INPUT TYPE="hidden" NAME="hdn_noneChecked" VALUE="1">
<INPUT TYPE="hidden" NAME="txt_programNumber" VALUE="0">
<INPUT TYPE="hidden" NAME="txt_businessPhone" VALUE="">
<INPUT TYPE="hidden" NAME="txt_socialSecurityNumber" VALUE="">
<INPUT TYPE="hidden" NAME="hdn_search_by" VALUE="">
<INPUT TYPE="hidden" NAME="hdn_noneChecked2" VALUE="1">
<INPUT TYPE="hidden" NAME="hierParmsPassed" VALUE="<GX type=cell ID=hierParmsPassed> </GX>">

<%--<GX  type=replace ID=transferAcct value=trnacct>--%>
<%--	<INPUT TYPE="hidden" NAME="hdn_user" VALUE=trnacct>--%>
<%--</GX>--%>


</html:form>
</BODY>
</HTML>
