<!-- Hide from other browsers

<!--*******************  CHECK DATE 3  *************************-->
<!--* This function checks if the date (MM/YYYY) is prior to   *-->
<!--* the current MM/YYYY                                      *-->
<!--************************************************************-->
var txt_revise=""

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
   // Note Year is current year - 99. So, 1999 is 99
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

function formTest(theForm)
    {
     var errormessage = parent.logo.ErrMsg_Header;

     // Section added to provide hierarchy assignment in non-prepopulated fields
     if ((theForm.hdn_hl1.value != "")&&
         (theForm.txt_hl1.value != theForm.hdn_hl1.value))
         {
          theForm.txt_hl1.value = theForm.hdn_hl1.value;
         }
     if ((theForm.hdn_hl2.value != "")&&
         (theForm.txt_hl2.value != theForm.hdn_hl2.value))
         {
          theForm.txt_hl2.value = theForm.hdn_hl2.value;
         }
     if ((theForm.hdn_hl3.value != "")&&
         (theForm.txt_hl3.value != theForm.hdn_hl3.value))
         {
          theForm.txt_hl3.value = theForm.hdn_hl3.value;
         }
     if ((theForm.hdn_hl4.value != "")&&
         (theForm.txt_hl4.value != theForm.hdn_hl4.value))
         {
          theForm.txt_hl4.value = theForm.hdn_hl4.value;
         }
     if ((theForm.hdn_hl5.value != "")&&
         (theForm.txt_hl5.value != theForm.hdn_hl5.value))
         {
          theForm.txt_hl5.value = theForm.hdn_hl5.value;
         }
     if ((theForm.hdn_hl6.value != "")&&
         (theForm.txt_hl6.value != theForm.hdn_hl6.value))
         {
          theForm.txt_hl6.value = theForm.hdn_hl6.value;
         }
     if ((theForm.hdn_hl7.value != "")&&
         (theForm.txt_hl7.value != theForm.hdn_hl7.value))
         {
          theForm.txt_hl7.value = theForm.hdn_hl7.value;
         }
     if ((theForm.hdn_hl8.value != "")&&
         (theForm.txt_hl8.value != theForm.hdn_hl8.value))
         {
          theForm.txt_hl8.value = theForm.hdn_hl8.value;
         }

     if (theForm.txt_hl0)
         {
          if (((theForm.txt_hl0.value == "0000000"))||
              ((theForm.txt_hl0.value != "")&&
              ((parent.logo.checkLength(theForm,theForm.txt_hl0,7) == "error")||
               (parent.logo.checkNumeric(theForm,theForm.txt_hl0) == "error"))))
              {
               errormessage += parent.logo.ErrMsg_ProgramNumber;
              }
         }
     if (theForm.txt_hl0)
         {
          if ((theForm.txt_hl0.value == "")&&(theForm.txt_hl1.value == "")&&
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

     if ((theForm.txt_hl1.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl1) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel1;
         }

     if ((theForm.txt_hl2.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl2,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl2) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel2;
         }

     if ((theForm.txt_hl3.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl3,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl3) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel3;
         }

     if ((theForm.txt_hl4.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl4,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl4) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel4;
         }

     if ((theForm.txt_hl5.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl5,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl5) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel5;
         }

     if ((theForm.txt_hl6.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl6) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel6;
         }

     if ((theForm.txt_hl7.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl7,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl7) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel7;
         }

     if ((theForm.txt_hl8.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl8,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl8) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel8;
         }

     if ((theForm.txt_acctCenterID.value == "")||
         (parent.logo.checkAlphaNumeric(theForm,theForm.txt_acctCenterID)== "error"))
         {
          errormessage += parent.logo.ErrMsg_AccountingCenterID;
         }

     if ((checkAlphaNumericPlus(theForm,theForm.txt_masterAccountingCode)== "error"))
         {
          errormessage += parent.logo.ErrMsg_MasterAccountingCode;
         }

     if ((theForm.txt_accountExpirationDate.value == "")||
         (parent.logo.checkDate2(document.frm_accountSetupInputForm,
                                 document.frm_accountSetupInputForm.txt_accountExpirationDate) == "error")
	||	 (checkDate3(document.frm_accountSetupInputForm.txt_accountExpirationDate) == "error"))
         {
          errormessage += parent.logo.ErrMsg_AccountExpirationDate;
         }

     if ((theForm.txt_creditLimit.value == "")||
         (parent.logo.checkNumeric(theForm,theForm.hdn_crdtLmt,11)== "error"))
         {
          errormessage += parent.logo.ErrMsg_CreditLimit;
         }

     if ((parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_firstName)=="error")||
         (theForm.txt_firstName.value == ""))
         {
          errormessage += parent.logo.ErrMsg_FirstName;
         }

     if ((parent.logo.checkAlphaNumericPlus(theForm,theForm.txt_lastName)=="error")||
         (theForm.txt_lastName.value == ""))
         {
          errormessage += parent.logo.ErrMsg_LastName;
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

     if ((theForm.txt_state.value == "")||
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
		   (theForm.txt_zipCode.value == "")) ||
		 (parent.logo.checkZipCode(theForm,theForm.txt_zipCode)=="error"))
		 {
		  errormessage += parent.logo.ErrMsg_Zip;
		 }

	 if ((theForm.txt_businessPhone.value == "") ||
		 (parent.logo.checkPhoneNumber(theForm,theForm.txt_businessPhone) == "error"))
		 {
		  errormessage += (parent.logo.ErrMsg_BusinessPhone);
	 }

     if (theForm.txt_emailAddress.value != "")
         {
          var temp = theForm.txt_emailAddress.value;
          var atTheRate = temp.indexOf('@');
          if(atTheRate<1)
              {
               errormessage += parent.logo.ErrMsg_Email;
              }
         }

     if ((theForm.txt_socialSecurityNumber.value != "")&&
         (parent.logo.checkSSN(theForm,theForm.txt_socialSecurityNumber) == "error"))
         {
          errormessage += parent.logo.ErrMsg_SSN;
         }

     if (parent.logo.checkAlphaNumeric(theForm,theForm.txt_employeeIdentificationNumber) == "error")
         {
          errormessage += parent.logo.ErrMsg_EmployeeID;
         }




// -----------------DISPLAY ERROR MESSAGE ---------------------//
     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return false;
         }
     else
	 	{
			//set the hierarchy for Revise here. We have to do it here to handle formTest failure.
 			setReviseHierarchy(document.frm_accountSetupInputForm);
     		return true;
		 }
	}

// ---------------------------- END OF FORM TEST -------------------------------- //


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

function chkCountry(theForm)
  {
   var cntry = theForm.txt_country.value;
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

/*
function createAccountingCenterIDString(theForm,theUrl){

     var errormessage=parent.logo.ErrMsg_Header;
     var hierarchyString=theUrl+"?availableHierarchiesList=";
     hierarchyString+="CurrentUserHierarchy";
     return openNewWindow_singleWindowDisplay(hierarchyString);
}
*/

function createHierarchyString(theForm,theUrl,theBrowseType)
    {
     var errormessage = parent.logo.ErrMsg_Header;
     var hierarchyString = theUrl+"?availableHierarchiesList=";

     // Section added to provide hierarchy assignment in non-prepopulated fields
     if ((theForm.hdn_hl1.value != "")&&
         (theForm.txt_hl1.value != theForm.hdn_hl1.value))
         {
          theForm.txt_hl1.value = theForm.hdn_hl1.value;
         }
     if ((theForm.hdn_hl2.value != "")&&
         (theForm.txt_hl2.value != theForm.hdn_hl2.value))
         {
          theForm.txt_hl2.value = theForm.hdn_hl2.value;
         }
     if ((theForm.hdn_hl3.value != "")&&
         (theForm.txt_hl3.value != theForm.hdn_hl3.value))
         {
          theForm.txt_hl3.value = theForm.hdn_hl3.value;
         }
     if ((theForm.hdn_hl4.value != "")&&
         (theForm.txt_hl4.value != theForm.hdn_hl4.value))
         {
          theForm.txt_hl4.value = theForm.hdn_hl4.value;
         }
     if ((theForm.hdn_hl5.value != "")&&
         (theForm.txt_hl5.value != theForm.hdn_hl5.value))
         {
          theForm.txt_hl5.value = theForm.hdn_hl5.value;
         }
     if ((theForm.hdn_hl6.value != "")&&
         (theForm.txt_hl6.value != theForm.hdn_hl6.value))
         {
          theForm.txt_hl6.value = theForm.hdn_hl6.value;
         }
     if ((theForm.hdn_hl7.value != "")&&
         (theForm.txt_hl7.value != theForm.hdn_hl7.value))
         {
          theForm.txt_hl7.value = theForm.hdn_hl7.value;
         }
     if ((theForm.hdn_hl8.value != "")&&
         (theForm.txt_hl8.value != theForm.hdn_hl8.value))
         {
          theForm.txt_hl8.value = theForm.hdn_hl8.value;
         }

     //
     // DO THE FOLLOWING CHECK FOR ACCOUNTING CENTER ID'S
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
              {
               errormessage += parent.logo.ErrMsg_ProgramNumber;
              }
         }

     // Check to make sure HL fields are not blank
     if (theForm.txt_hl0)
         {
          if ((theForm.txt_hl0.value == "")&&
              (theForm.txt_hl1.value == "") &&
              (theForm.txt_hl2.value == "") &&
              (theForm.txt_hl3.value == "") &&
              (theForm.txt_hl4.value == "") &&
              (theForm.txt_hl5.value == "") &&
              (theForm.txt_hl6.value == "") &&
              (theForm.txt_hl7.value == "") &&
              (theForm.txt_hl8.value == ""))
              {
              errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
              }
         }
     else
         {
          if ((theForm.txt_hl1.value == "") &&
              (theForm.txt_hl2.value == "") &&
              (theForm.txt_hl3.value == "") &&
              (theForm.txt_hl4.value == "") &&
              (theForm.txt_hl5.value == "") &&
              (theForm.txt_hl6.value == "") &&
              (theForm.txt_hl7.value == "") &&
              (theForm.txt_hl8.value == ""))
              {
               errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
              }

// Check to see which HL Sequence Test to perform
          if (theForm.txt_hl0)
              {
               // If the program number exists, it and at least one hierarchy level must
               // be filled.
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
                    errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
                   }

               // If the program number exists, use it in the validation of the hierarchy
               // sequence validation.  Sequence validation means the levels must be
               // filled from the bottom up only.
               if (parent.logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,
                                               theForm.txt_hl2,theForm.txt_hl3,
                                               theForm.txt_hl4,theForm.txt_hl5,
                                               theForm.txt_hl6,theForm.txt_hl7,
                                               theForm.txt_hl8) == "error")
                   {
                    errormessage += (parent.logo.ErrMsg_HierarchyLevelSequence);
                   }
              }
          else
              {
               //
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

               if (parent.logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
                                                theForm.txt_hl3,theForm.txt_hl4,
                                                theForm.txt_hl5,theForm.txt_hl6,
                                                theForm.txt_hl7,theForm.txt_hl8) == "error")
                   {
                    errormessage += (parent.logo.ErrMsg_HierarchyLevelSequence);
                   }
              }
         }

    // This section checks the standard HL1 - HL8

     if ((theForm.txt_hl1.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl1) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel1;
         }

     if ((theForm.txt_hl2.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl2,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl2) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel2;
         }

     if ((theForm.txt_hl3.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl3,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl3) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel3;
         }

     if ((theForm.txt_hl4.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl4,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl4) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel4;
         }

     if ((theForm.txt_hl5.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl5,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl5) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel5;
         }

     if ((theForm.txt_hl6.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl6) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel6;
         }

     if ((theForm.txt_hl7.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl7,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl7) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel7;
         }

     if ((theForm.txt_hl8.value != "")&&
        ((parent.logo.checkLength(theForm,theForm.txt_hl8,7) == "error")||
         (parent.logo.checkNumeric(theForm,theForm.txt_hl8) == "error")))
         {
          errormessage += parent.logo.ErrMsg_HierarchyLevel8;
         }

     // Check to see which HL Sequence Test to perform

     if (theForm.txt_hl0)
         {
          if (parent.logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,
                                          theForm.txt_hl2,theForm.txt_hl3,theForm.txt_hl4,
                                          theForm.txt_hl5,theForm.txt_hl6,theForm.txt_hl7,
                                          theForm.txt_hl8) == "error")
              {
               errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;
              }
         }
     else
         {
          if (parent.logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
                                           theForm.txt_hl3,theForm.txt_hl4,
                                           theForm.txt_hl5,theForm.txt_hl6,
                                           theForm.txt_hl7,theForm.txt_hl8) == "error")
              {
               errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;
              }
         }

     // If no errors occurred create HL string, else display errors

     if (errormessage == parent.logo.ErrMsg_Header)
         {
          // If HL0 exists add it to the Hierarchy String

          if (theForm.txt_hl0)
              {
               hierarchyString += (theForm.txt_hl0.value == "") ? "0000000" : theForm.txt_hl0.value;
              }
          else
              {
               hierarchyString += theForm.hdn_programNumber.value;
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
          }
     else
          {
           alert(errormessage);
          }
     } // End of createHierarchyString

//*******************************************************************
// WINDOW OPENING FUNCTIONS
//*******************************************************************

function openNewWindow_dualWindowDisplay(theUrl)
    {
     msg=window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=750,height=630,status=no,menubar=no");
    }

function openNewWindow_singleWindowDisplay(theUrl)
    {
     msg = window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=360,status=no,menubar=no");
    }

//*******************************************************************
// BROWSE WINDOW SELECTION FUNCTIONS
//*******************************************************************

function selectAccountingCenterId(accountingCenterId)
    {
     document.frm_accountSetupInputForm.txt_acctCenterID.value = accountingCenterId;
    }

function selectMasterAccountingCode(masterAccountingCode)
    {
     document.frm_accountSetupInputForm.txt_masterAccountingCode.value = masterAccountingCode;
    }

function selectHierarchy(hierarchyNumber)
    {
     // Check to see if Hierarchy Level 0 exists and associate

     if (document.txt_hl0)
       {
        document.frm_accountSetupInputForm.txt_hl0.value = hierarchyNumber.substring(0,7);
       }

     // Associate each Hierarchy Level with it's correct value

 document.frm_accountSetupInputForm.txt_hl1.value=hierarchyNumber.substring(7,14);
 document.frm_accountSetupInputForm.txt_hl2.value=hierarchyNumber.substring(14,21);
 document.frm_accountSetupInputForm.txt_hl3.value=hierarchyNumber.substring(21,28);
 document.frm_accountSetupInputForm.txt_hl4.value=hierarchyNumber.substring(28,35);
 document.frm_accountSetupInputForm.txt_hl5.value=hierarchyNumber.substring(35,42);
 document.frm_accountSetupInputForm.txt_hl6.value=hierarchyNumber.substring(42,49);
 document.frm_accountSetupInputForm.txt_hl7.value=hierarchyNumber.substring(49,56);
 document.frm_accountSetupInputForm.txt_hl8.value=hierarchyNumber.substring(56,63);
    }

//*******************************************************************
// BROWSE WINDOW URL STRING CREATION FUNCTIONS
//*******************************************************************

function createMasterAccountingCodeString(theForm,theUrl)
    {
     var errormessage = parent.logo.ErrMsg_Header;
     var urlString = theUrl+"?accountingCenterId=";

     if ((theForm.txt_acctCenterID.value == "")||
         (parent.logo.checkNumeric(theForm,theForm.txt_acctCenterID) == "error"))
         {
          errormessage += parent.logo.ErrMsg_AccountingCenterID;
         }

     if (errormessage != parent.logo.ErrMsg_Header)
         {
          alert(errormessage);
          return;
         }
     urlString += theForm.txt_acctCenterID.value;
     openNewWindow_dualWindowDisplay(urlString+"&slashes=yes");
    }

function eraseCreditLimit(theForm)
    {
     if (clearLimitFlag)
         {

         }

     return true;
    }

amount1 = 0; // Globalize the amount1 variable so it is persistant and null values
           // can be weeded out from iterative loops.
function updateCurrency(theForm)
    {
     // Verify that the amount is in valid currency format.  If this fails, change the
     // amount to zero.
     if ((parent.logo.checkCurrency(theForm.txt_creditLimit.value) == "error"))
         {
          theForm.txt_creditLimit.value = "0";
         }

     // If the old value is null, do not process a new value for it.
     if (theForm.txt_creditLimit.value != "")
         {
          amount1 = theForm.txt_creditLimit.value;
         }
     else
         {
          return true;
         }

     // Strip out the currency format characters
     amount1 = parent.logo.searchAndReplace(amount1,"$","",false,false);
     amount1 = parent.logo.searchAndReplace(amount1,",","",false,false);

     // Put the amounts back in to currency format and put on the screen
     amount1 = parent.logo.formatCurrency(amount1,16);
     theForm.txt_creditLimit.value = "";
     theForm.txt_creditLimit.value = amount1;
     window.setCreditFlag="okToClear";
     return true;
    }

setCreditFlag="okToClear";

// This function checks to see if the currrency value is old or not in order to clear it.
// Variations between browsers will lead to differences in the iteration of the update
// cycle otherwise.
function creditClear(theForm)
    {
     if (window.setCreditFlag == "okToClear")
         {
          theForm.txt_creditLimit.value = '';
         }
     window.setCreditFlag = "doNotClear";
     return false;
    }

<!--*******************  resetFix       ****************************-->
<!--*This function is a workaround for a bug in IE3 where a RESET  *-->
<!--*is not allowing the text inputs to be populated from popup    *-->
<!--*Function is being called from onClick event of Reset button!  *-->
<!--****************************************************************-->
<!--DONT USE THIS FUNCTION ANY MORE -->

function resetFix( theForm )
{
     if (theForm.hdn_hl1.value == "") {
        	theForm.txt_hl1.value = "";
	       	changeContent(getObject("hl1Desc"), "");

	 }
	 else
	 {
	        if (theForm.txt_hl1Descr.value != "")
	        	changeContent(getObject("hl1Desc"), theForm.txt_hl1Descr.value);
	        else if (theForm.txt_hl1Desc.value != "")
	        	changeContent(getObject("hl1Desc"), theForm.txt_hl1Desc.value);
	 }
	 if (theForm.hdn_hl2.value == "")
	 {
	        theForm.txt_hl2.value = "";
	        changeContent(getObject("hl2Desc"), "");
	 }
	 else
	 {
	 	        if (theForm.txt_hl2Descr.value != "")
	 	        	changeContent(getObject("hl2Desc"), theForm.txt_hl2Descr.value);
	 	        else if (theForm.txt_hl1Desc.value != "")
	 	        	changeContent(getObject("hl2Desc"), theForm.txt_hl2Desc.value);
	 }
	 if (theForm.hdn_hl3.value == "")
	 {
	 	    theForm.txt_hl3.value = "";
	        changeContent(getObject("hl3Desc"), "");
	 }
	 else
	 {
	 	 	if (theForm.txt_hl3Descr.value != "")
	 	 	     changeContent(getObject("hl3Desc"), theForm.txt_hl3Descr.value);
	 	 	else if (theForm.txt_hl3Desc.value != "")
	 	        	changeContent(getObject("hl3Desc"), theForm.txt_hl3Desc.value);
	 }
	 if (theForm.hdn_hl4.value == "")
	 {
	        theForm.txt_hl4.value = "";
	        changeContent(getObject("hl4Desc"), "");
	 }
	 else
	 {
	 	 	        if (theForm.txt_hl4Descr.value != "")
	 	 	        	changeContent(getObject("hl4Desc"), theForm.txt_hl4Descr.value);
	 	 	        else if (theForm.txt_hl4Desc.value != "")
	 	 	        	changeContent(getObject("hl4Desc"), theForm.txt_hl4Desc.value);
	 }
	 if (theForm.hdn_hl5.value == "")
	 {
	 	  theForm.txt_hl5.value = "";
	 	  changeContent(getObject("hl5Desc"), "");
	 }
	 else
	 {
	 	 	        if (theForm.txt_hl5Descr.value != "")
	 	 	        	changeContent(getObject("hl5Desc"), theForm.txt_hl5Descr.value);
	 	 	        else if (theForm.txt_hlDesc.value != "")
	 	 	        	changeContent(getObject("hl5Desc"), theForm.txt_hl5Desc.value);
	 }
	 if (theForm.hdn_hl6.value == "")
	 {
	 	  theForm.txt_hl6.value = "";
	 	  changeContent(getObject("hl6Desc"), "");
	 }
	 else
	 {
	 	 	        if (theForm.txt_hl6Descr.value != "")
	 	 	        	changeContent(getObject("hl6Desc"), theForm.txt_hl6Descr.value);
	 	 	        else if (theForm.txt_hl6Desc.value != "")
	 	 	        	changeContent(getObject("hl6Desc"), theForm.txt_hl6Desc.value);
	 }
	 if (theForm.hdn_hl7.value == "")
	 {
	 	  theForm.txt_hl7.value = "";
	 	  changeContent(getObject("hl7Desc"), "");
	 }
	 else
	 {
	 	 	        if (theForm.txt_hl7Descr.value != "")
	 	 	        	changeContent(getObject("hl7Desc"), theForm.txt_hl7Descr.value);
	 	 	        else if (theForm.txt_hl7Desc.value != "")
	 	 	        	changeContent(getObject("hl7Desc"), theForm.txt_hl7Desc.value);
	 }
	 if (theForm.hdn_hl8.value == "")
	 {
	 	  theForm.txt_hl8.value = "";
	 	  changeContent(getObject("hl8Desc"), "");
   	 }
	 else
	 {
	 	 	        if (theForm.txt_hl8Descr.value != "")
	 	 	        	changeContent(getObject("hl8Desc"), theForm.txt_hl8Descr.value);
	 	 	        else if (theForm.txt_hl8Desc.value != "")
	 	 	        	changeContent(getObject("hl8Desc"), theForm.txt_hl8Desc.value);
	 }
}  // resetFix

//This is a temporary fix since NS was not restting the txt_hldesc flds on reset
//To reset the form, for NS populate the hrchy descrs.
function resetForm(theForm){
    theForm.reset();
	if (isIE)
		updateHierarchyDesc(theForm);
	else
	{
	    if (theForm.elements["txt_hl1Descr"])
		   //update from txt_hlxDescr flds
			for( i=1; i<9; i++){
			    var fieldName = "txt_hl" + i + "Descr";
			    var divName = "hl" + i + "Desc";
			    var divText = "<P ID='hLx'>"+ theForm.elements[fieldName].value + "</P>";
				changeContent(getObject(divName), divText);
			}
	}
}

// Stop hiding -->
