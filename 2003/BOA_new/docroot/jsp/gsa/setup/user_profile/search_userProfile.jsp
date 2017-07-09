"<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<HTML>

<HEAD>
<TITLE>User Profile Search</TITLE>

<META NAME  =  "Name" CONTENT  =  "search_userProfile.html">
<META NAME  =  "Description" CONTENT  =  "Allows a user to search for a user profile">
<META NAME  =  "Author" CONTENT  =  "Shilpoo Agrawal">
<META NAME  =  "Created" CONTENT  =  "06/16/1998">
<META NAME  =  "Revised" CONTENT  =  "07/06/98(Brent Ramsby)">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>

<!-------------------------JAVA SCRIPTS START-------------------------->

<SCRIPT LANGUAGE  =  "JavaScript">

<!-- starting hiding

//FUNCTIONS

<!--*******************  CHECK ALPHANUMERIC  ***********************-->
 <!--*This function receives a form and field. It then checks to see*-->
 <!--*if the all the characters in the field are alphanumeric.      *-->
 <!--*Last Revision Date:                                           *-->
 <!--****************************************************************-->

 function checkAlphaNumeric(theForm,theField)
  {
   var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-&";
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

function form_Test(theForm)
    {
     var errormessage = parent.logo.ErrMsg_Header;

     if (theForm.rag_searchParameters[0].checked)
         {             <!--**test account number**-->
          if((parent.logo.checkLength(theForm,theForm.txt_accountNumber,16)=="error")||
             (parent.logo.checkNumeric(theForm,theForm.txt_accountNumber)=="error"))
              {
               errormessage += parent.logo.ErrMsg_AccountNumber;
              }
         }

     if (theForm.rag_searchParameters[1].checked)
         {
          if((!(theForm.chk_name.checked))&&
             (!(theForm.chk_hierarchy.checked)))
              {
               errormessage += "Please select an option\n";
              }
          if(theForm.chk_name.checked)
              {
               if ((theForm.txt_firstName.value != "")&&
                   (checkAlphaNumeric(theForm,theForm.txt_firstName)=="error"))
                {
                 errormessage += parent.logo.ErrMsg_FirstName;
                }
               if ((theForm.txt_lastName.value == "") &&
                  (theForm.txt_firstName.value != ""))
                {
                errormessage += parent.logo.ErrMsg_NameCombination;
                }
                if ((theForm.txt_lastName.value  ==  "")||
                   (checkAlphaNumeric(theForm,theForm.txt_lastName)=="error"))

                {
                errormessage += parent.logo.ErrMsg_LastName;
                }
              }

          if (theForm.chk_hierarchy.checked)
              {
               // Check to see which HL Sequence Test to perform

               if ((theForm.txt_hl0))
                {
                   if((theForm.txt_hl0.value=="")&&
                      (theForm.txt_hl1.value=="")&&(theForm.txt_hl2.value=="")&&
                      (theForm.txt_hl3.value=="")&&(theForm.txt_hl4.value=="")&&
                      (theForm.txt_hl5.value=="")&&(theForm.txt_hl6.value=="")&&
                      (theForm.txt_hl7.value=="")&&(theForm.txt_hl8.value==""))
                  {
                   errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
                  }
                }else
                {
                 if((theForm.txt_hl1.value=="")&&(theForm.txt_hl2.value=="")&&
                    (theForm.txt_hl3.value=="")&&(theForm.txt_hl4.value=="")&&
                    (theForm.txt_hl5.value=="")&&(theForm.txt_hl6.value=="")&&
                    (theForm.txt_hl7.value=="")&&(theForm.txt_hl8.value==""))
                 {
                    errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
                 }
               }

               if (theForm.txt_hl0)
                   {
                    if (parent.logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,theForm.txt_hl2,
                                                    theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                                                    theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
                        {
                         errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;
                        }
                   }
               else
                   {
                    if (parent.logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
                                                     theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                                                     theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
                        {
                         errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;
                        }
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

               if(theForm.txt_hl1.value.length != "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl1) == "error")||
                       (parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error")))
                       {
                        errormessage += parent.logo.ErrMsg_HierarchyLevel1;
                       }
                  }

              if(theForm.txt_hl2.value.length != "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl2) == "error")||
                        (parent.logo.checkLength(theForm,theForm.txt_hl2,7) == "error")))
                       {
                        errormessage += parent.logo.ErrMsg_HierarchyLevel2;
                       }
                  }

              if(theForm.txt_hl3.value.length != "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl3) == "error")||
                        (parent.logo.checkLength(theForm,theForm.txt_hl3,7) == "error")))
                       {
                        errormessage += parent.logo.ErrMsg_HierarchyLevel3;
                       }
                  }

              if(theForm.txt_hl4.value.length != "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl4)  == "error")||
                        (parent.logo.checkLength(theForm,theForm.txt_hl4,7) == "error")))
                       {
                        errormessage += parent.logo.ErrMsg_HierarchyLevel4;
                       }
                  }

              if(theForm.txt_hl5.value.length != "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl5)  == "error")||
                        (parent.logo.checkLength(theForm,theForm.txt_hl5,7) == "error")))
                       {
                        errormessage += parent.logo.ErrMsg_HierarchyLevel5;
                       }
                  }

              if(theForm.txt_hl6.value.length != "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl6) == "error")||
                       (parent.logo.checkLength(theForm,theForm.txt_hl6,7) == "error")))
                      {
                       errormessage +=  parent.logo.ErrMsg_HierarchyLevel6;
                      }
                  }

              if(theForm.txt_hl7.value.length != "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl7) == "error")||
                        (parent.logo.checkLength(theForm,theForm.txt_hl7,7) == "error")))
                       {
                        errormessage +=  parent.logo.ErrMsg_HierarchyLevel7;
                       }
                  }

              if(theForm.txt_hl8.value.length !=  "0")
                  {
                   if (((parent.logo.checkNumeric(theForm,theForm.txt_hl8) == "error")||
                        (parent.logo.checkLength(theForm,theForm.txt_hl8,7) == "error")))
                       {
                        errormessage += parent.logo.ErrMsg_HierarchyLevel8;
                       }
                  }
              }


  }

             if (errormessage != parent.logo.ErrMsg_Header)
                 {
                  alert(errormessage);
                  return(false);
                 }

                  return (true);
    }
<!------------------------------------------------------------------>
function refresh()
    {
     if (document.frm_searchUP.txt_hl0)
         {
          document.frm_searchUP.txt_hl0.value = "";
         }
          document.frm_searchUP.txt_hl1.value = "";
          document.frm_searchUP.txt_hl2.value = "";
          document.frm_searchUP.txt_hl3.value = "";
          document.frm_searchUP.txt_hl4.value = "";
          document.frm_searchUP.txt_hl5.value = "";
          document.frm_searchUP.txt_hl6.value = "";
          document.frm_searchUP.txt_hl7.value = "";
          document.frm_searchUP.txt_hl8.value = "";
    }
<!------------------------------------------------------------------>

//FOCUSING FUNCTIONS

function focusing(theForm)
    {

     if (theForm.rag_searchParameters[0].checked)
         {
          theForm.chk_name.checked=false;
          theForm.chk_hierarchy.checked = false;
          theForm.txt_firstName.value="";
          theForm.txt_lastName.value="";
          theForm.cmb_hierarchyDepth.selectedIndex=0;
          refresh();
          parent.logo.setFocus(theForm,theForm.txt_accountNumber,parent.logo.Status_Text_AccountNumber);
         }

     if (theForm.rag_searchParameters[1].checked)
         {

          theForm.txt_accountNumber.value="";
          theForm.chk_name.checked=true;
          parent.logo.setFocus(theForm,theForm.txt_lastName,parent.logo.Status_Text_LastName);
         }
    }

function chkFocusing(theForm,theField)
    {
     if (theField==theForm.chk_name)
         {

          if(theField.checked==true)
              {
               theForm.rag_searchParameters[1].checked=true;
               theForm.txt_accountNumber.value="";
               parent.logo.setFocus(theForm,theForm.txt_lastName,parent.logo.Status_Text_LastName);
              }
         }

     if (theField==theForm.chk_hierarchy)
         {
          if(theField.checked==true)
              {
               theForm.rag_searchParameters[1].checked=true;
               theForm.txt_accountNumber.value="";

               if (theForm.txt_hl0)
                   {
                    parent.logo.setFocus(theForm,theForm.txt_hl0,parent.logo.Status_Text_HierarchyLevel);
                   }
               else
                    parent.logo.setFocus(theForm,theForm.txt_hl1,parent.logo.Status_Text_HierarchyLevel);
              }
         }
    }


function reverseFocus(theForm,theField)
    {
     if (theField==theForm.txt_accountNumber)
         {
          theForm.rag_searchParameters[0].checked=true;
          theForm.chk_name.checked=false;
          theForm.chk_hierarchy.checked=false;
          theForm.txt_firstName.value="";
          theForm.txt_lastName.value="";
          theForm.cmb_hierarchyDepth.selectedIndex=0;
          refresh();
         }

     if ((theField==theForm.txt_firstName)||(theField==theForm.txt_lastName))
         {
          theForm.rag_searchParameters[1].checked=true;
          theForm.txt_accountNumber.value="";
          theForm.chk_name.checked=true;
         }

     if (theField==theForm.cmb_hierarchyDepth)
         {
          theForm.rag_searchParameters[1].checked=true;
          theForm.txt_accountNumber.value="";
         }
     if (theForm.txt_hl0)
         {
          if ((theField==theForm.txt_hl0)||
              (theField==theForm.txt_hl1)||
              (theField==theForm.txt_hl2)||
              (theField==theForm.txt_hl3)||
              (theField==theForm.txt_hl4)||
              (theField==theForm.txt_hl5)||
              (theField==theForm.txt_hl6)||
              (theField==theForm.txt_hl7)||
              (theField==theForm.txt_hl8))
          {
           theForm.rag_searchParameters[1].checked=true;
           theForm.txt_accountNumber.value="";
           theForm.chk_hierarchy.checked=true;
          }
         }
     else
          if ((theField==theForm.txt_hl1)||
              (theField==theForm.txt_hl2)||
              (theField==theForm.txt_hl3)||
              (theField==theForm.txt_hl4)||
              (theField==theForm.txt_hl5)||
              (theField==theForm.txt_hl6)||
              (theField==theForm.txt_hl7)||
              (theField==theForm.txt_hl8))
          {
           theForm.rag_searchParameters[1].checked=true;
           theForm.txt_accountNumber.value="";
           theForm.chk_hierarchy.checked=true;
          }
    }


// THIS SECTION WAS ADDED TO SUPPORT HIERARCHY BROWSING

function openNewWindow(theUrl)
    {
     msg = window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=400,status=no,menubar=no");
    }

function selectHierarchy(hierarchyNumber)
    {
        // Check to see if Hierarchy Level 0 exists and associate

     if (document.txt_hl0)
         {
          document.frm_searchUP.txt_hl0.value = hierarchyNumber.substring(0,7);
         }

        // Associate each Hierarchy Level with it's correct value

          document.frm_searchUP.txt_hl1.value = hierarchyNumber.substring(7,14);
          document.frm_searchUP.txt_hl2.value = hierarchyNumber.substring(14,21);
          document.frm_searchUP.txt_hl3.value = hierarchyNumber.substring(21,28);
          document.frm_searchUP.txt_hl4.value = hierarchyNumber.substring(28,35);
          document.frm_searchUP.txt_hl5.value = hierarchyNumber.substring(35,42);
          document.frm_searchUP.txt_hl6.value = hierarchyNumber.substring(42,49);
          document.frm_searchUP.txt_hl7.value = hierarchyNumber.substring(49,56);
          document.frm_searchUP.txt_hl8.value = hierarchyNumber.substring(56,63);
    }

function createHierarchyString(theForm,theUrl)
    {
     var errormessage = parent.logo.ErrMsg_Header;
     var hierarchyString = theUrl+"?availableHierarchiesList=";

      // Check to see if Program Number (HL0) exists


          theForm.rag_searchParameters[1].checked=true;
          theForm.txt_accountNumber.value="";
          theForm.chk_hierarchy.checked = true;


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
             if (parent.logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,theForm.txt_hl2,
                                             theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                                             theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
                 {
                  errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;
                 }
            }
        else
            {
             if (parent.logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
                                              theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                                              theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
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

                  openNewWindow(hierarchyString);
            }
        else
            {
             alert(errormessage);
            }


    }

  // End of createHierarchyString

<!--*******************  resetFix       ****************************-->
<!--*This function is a workaround for a bug in IE3 where a RESET  *-->
<!--*is not allowing the text inputs to be populated from popup    *-->
<!--*Function is being called from onClick event of Reset button!  *-->
<!--****************************************************************-->

function resetFix( theForm )
{
     theForm.txt_hl1.value = "";
     theForm.txt_hl2.value = "";
     theForm.txt_hl3.value = "";
     theForm.txt_hl4.value = "";
     theForm.txt_hl5.value = "";
     theForm.txt_hl6.value = "";
     theForm.txt_hl7.value = "";
     theForm.txt_hl8.value = "";

}  // resetFix


//Stop Hiding
</SCRIPT>

<!------------------------JAVA SCRIPTS ENDS------------------------->
</HEAD>
<BODY>
<GX type=include id="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX>
<!------------------------------------------------------------------>
<BR>
<TABLE>
  <TR>
   <TD><B><FONT COLOR = "#0000FF" FACE = "Arial, HELVETICA">Search By</FONT></B>
   </TD>
  </TR>
</TABLE>

<!------------------------FORM STARTS-------------------------------->
<FORM NAME  =  "frm_searchUP"
    ACTION = "/cgi-bin/gx.cgi/GUIDGX-{0BFA0AA0-0B78-11D2-96DC-0000F62AA024}"
    METHOD = "POST"
    TARGET = "content"
    onsubmit = "return form_Test(document.frm_searchUP)">

<!------------------------------------------------------------------->

<TABLE BORDER = 0>
  <TR>
   <TD COLSPAN = 3>
    <INPUT TYPE = "RADIO" NAME = "rag_searchParameters" VALUE = "accountNumber" CHECKED
         onClick="focusing(document.frm_searchUP);">
     Account Number
   </TD>
   <TD>
    <INPUT TYPE = "TEXT" NAME = "txt_accountNumber" SIZE = "20" MAXLENGTH = "16"
         onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_AccountNumber);
                    reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_accountNumber);"
         onclick = "parent.logo.Display_Message(parent.logo.Status_Text_AccountNumber);">

   </TD>
  </TR>

  <TR>
   <TD COLSPAN = 3>
    <INPUT TYPE = "RADIO" NAME = "rag_searchParameters" VALUE = "otherOptions"
             onClick="focusing(document.frm_searchUP);">
      Other Options
   </TD>
   <TD>&nbsp;</TD>
  </TR>

  <TR>
   <TD>&nbsp;</TD>
   <TD><INPUT TYPE = "CHECKBOX" NAME = "chk_name" VALUE = "byName"
         onClick = "chkFocusing(document.frm_searchUP,document.frm_searchUP.chk_name);">
   </TD>
   <TD>Last Name</TD>
   <TD><INPUT TYPE = "TEXT" NAME = "txt_lastName" SIZE=27 MAXLENGTH=26
           onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_LastName);
                    reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_lastName);"
             onclick = "parent.logo.Display_Message(parent.logo.Status_Text_LastName);">
   </TD>
  </TR>
  <TR>
   <TD>&nbsp;</TD>
   <TD>&nbsp;</TD>
   <TD>First Name</TD>
   <TD><INPUT TYPE = "TEXT" NAME = "txt_firstName" SIZE=27 MAXLENGTH=26
         onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_FirstName);
                reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_firstName);"
         onclick = "parent.logo.Display_Message(parent.logo.Status_Text_FirstName);">
   </TD>
  </TR>

  <TR>
   <TD>&nbsp;</TD>
   <TD><INPUT TYPE = "CHECKBOX" NAME = "chk_hierarchy" VALUE = "byHierarchy"
         onclick = "chkFocusing(document.frm_searchUP,document.frm_searchUP.chk_hierarchy);">
   </TD>
   <TD>Hierarchy Level</TD>
   <TD>
     <INPUT TYPE = "BUTTON" NAME = "but_browse" VALUE = "Browse"
             onclick = "createHierarchyString(document.frm_searchUP,'/cgi-bin/gx.cgi/GUIDGX-{259A23D0-1B6A-11D2-96DD-204C4F4F5020}')">
   </TD>
  </TR>
  <TR>
   <TD>&nbsp;</TD>
   <TD>&nbsp;</TD>
   <TD>Hierarchy Depth</TD>
   <TD>
    <SELECT NAME="cmb_hierarchyDepth" SIZE="1"
           onfocus = "reverseFocus(document.frm_searchUP,document.frm_searchUP.cmb_hierarchyDepth);
                  parent.logo.Display_Message(parent.logo.Status_Text_HierarchyDepth);">

      <OPTION VALUE="0" SELECTED>Current Level</OPTION>
      <OPTION VALUE="1">One Level</OPTION>
      <OPTION VALUE="2">Two Levels</OPTION>
      <OPTION VALUE="3">Three Levels</OPTION>
      <OPTION VALUE="4">Four Levels</OPTION>
      <OPTION VALUE="5">Five Levels</OPTION>
      <OPTION VALUE="6">Six Levels</OPTION>
      <OPTION VALUE="7">Seven Levels</OPTION>
   </SELECT>
  </TD>
  </TR>
</TABLE>
<BR>
<!-------------------------------------------------------------->
<GX type=cell id=GCSU>
<TABLE>
  <TR>
   <TD COLSPAN=3>Program Number</TD>
   <TD><INPUT TYPE = "TEXT" NAME = "txt_hl0" VALUE = "" SIZE = "8"MAXLENGTH = 7
         onfocus = "reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl0);
                 parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
  </TR>
</TABLE>
</GX>
<!-------------------------------------------------------------->
<TABLE WIDTH="575" CELLSPACING="0" CELLPADDING="0" BORDER="0">
  <TR>
   <TD WIDTH="70">HL1</TD>
   <TD WIDTH="70">HL2</TD>
   <TD WIDTH="70">HL3</TD>
   <TD WIDTH="70">HL4</TD>
   <TD WIDTH="70">HL5</TD>
   <TD WIDTH="70">HL6</TD>
   <TD WIDTH="70">HL7</TD>
   <TD WIDTH="70">HL8</TD>
  </TR>
  <TR>
   <TD><INPUT TYPE = "text" NAME = "txt_hl1" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                   reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl1);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
   <TD><INPUT TYPE = "text" NAME = "txt_hl2" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                 reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl2);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
   <TD><INPUT TYPE = "text" NAME = "txt_hl3" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                   reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl3);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
   <TD><INPUT TYPE = "text" NAME = "txt_hl4" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                   reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl4);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
   <TD><INPUT TYPE = "text" NAME = "txt_hl5" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                   reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl5);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
   <TD><INPUT TYPE = "text" NAME = "txt_hl6" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                   reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl6);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
   <TD><INPUT TYPE = "text" NAME = "txt_hl7" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                   reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl7);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
   <TD><INPUT TYPE = "text" NAME = "txt_hl8" SIZE = "8" MAXLENGTH = "7"
        onfocus = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);
                   reverseFocus(document.frm_searchUP,document.frm_searchUP.txt_hl8);"
        onclick = "parent.logo.Display_Message(parent.logo.Status_Text_HierarchyLevel);">
   </TD>
  </TR>

</TABLE>

<!-------------------------------------------------------------------------->
<GX type=replace ID=guid value=returnGUID>
	<INPUT TYPE="HIDDEN" NAME="guid" VALUE="returnGUID">
</GX>

<BR>
<TABLE WIDTH="575">
  <TR>
   <TD WIDTH="575" ALIGN="Center">
     <INPUT TYPE="Submit" NAME="but_submit" VALUE="Submit">&nbsp;&nbsp;
     <INPUT TYPE="Reset" NAME="but_clear" VALUE="&nbsp;Clear&nbsp;" 
		onClick="resetHierarchy(document.frm_searchUP);">
   </TD>
  </TR>
</TABLE>

<!-------------------------------------------------------------------------->

<GX type=include id="/gsa/common/footer_systemDefault.html">Insert Footer Here</GX>

</BODY>
</HTML>