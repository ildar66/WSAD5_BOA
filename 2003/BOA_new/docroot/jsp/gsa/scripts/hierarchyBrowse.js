var hierarchyForm;

var original_hierarcy_desc = ""

//build a delimited string for capturing the hierarchy descriptions
function buildOrigiinalHierarchy(theForm){
	if (original_hierarcy_desc == ""){
		for (var i=1;i<9;i++){
			var inputName ="txt_hl" + i + "Desc" ;
			original_hierarcy_desc += theForm.elements[inputName].value +"^"
		}
	}
}

//reset the hierarchy back to the original set
function resetHierarchy(theForm){
   //buildOrigiinalHierarchy(theForm)
   setHierarchyDesc(theForm, theForm.hdn_delimitiedHierarchy.value )
}

// FIND THE LOGO FRAME
 var hierarchy_topWindow = window
 if (window.top)
 	hierarchy_topWindow = window.top

 while (!hierarchy_topWindow.logo){
 		hierarchy_topWindow = hierarchy_topWindow.opener
		if (hierarchy_topWindow.top)
		 	hierarchy_topWindow = hierarchy_topWindow.top

 }
var hierarchy_parent_logo = hierarchy_topWindow.logo


/** takes a delimited string, parses it and stores the descriptions in hierarchy.html's
hidden inputs.
*/
function clearHierDesc(theForm, startingRow ){
 	//buildOrigiinalHierarchy(theForm)
      var i = 0;
      for( i=startingRow; i<9; i++){
          var inputName ="txt_hl" + i + "Desc" ;
          theForm.elements[inputName].value = "";
      }
      updateHierarchyDesc(theForm);
      for( i=startingRow+1; i<9; i++){
          var inputName2 =   "txt_hl" + i;
          theForm.elements[inputName2].value = "";
      }

}

function getLogo()
{

    var topWindow = window.top
    while (!topWindow.logo){
	topWindow = topWindow.opener
    }
    var logo = topWindow.logo
    return logo;

}

function resetHierDesc(theForm){
       //buildOrigiinalHierarchy(theForm)
      var logo = getLogo();
      var hardSpace = " ";

      for( i=0; i<8; i++){
          var hlField = "txt_hl" + (i+1);
          var fieldName = "txt_hl" + (i+1) + "Desc";
          if (theForm.elements[hlField].value !== "0000000"){
              if (theForm.elements[hlField].value !== "") {
                  var isIE = document.all ? true : false;
                  if (isIE){
                      logo.topForm = 0;
                  }else{
                      var empty = theForm.elements[fieldName].value
                      arrayDesc = empty.split(" ");
		      var j = arrayDesc.length;
                      if (j == 1){
                          logo.topForm = 0;
                      }
                  }
              }
          }
      }
      if (logo.topForm == 0){
          var delim = '^';
          var i = 0;
	  var logo = getLogo();
          arrayDesc = logo.backHierarchy.split(delim);
          while(i < arrayDesc.length){
              var inputName ="txt_hl" + (i+1) + "Desc" ;
      	      if(theForm.elements[inputName]){
      	          if (arrayDesc[i] == ""){
      	              theForm.elements[inputName].value = theForm.elements[inputName].value
      	          }else {
      	              theForm.elements[inputName].value = arrayDesc[i];
      	          }
      	      }
      	      i++;
      	  }
          updateBackHierarchyDesc(theForm);
      }else{
          for( var j=1 ;j<9; j++){
              var hlField = "txt_hl" + (j);
              if (theForm.elements[hlField].value == ""){
                  clearHierDesc(theForm,j);
                  j = 10;
              }
          }
      }
}


function updateBackHierarchyDesc(theForm){
       //       buildOrigiinalHierarchy(theForm)
      var delim = '^';
      var i = 0;

      for( i=0; i<8; i++){
          var hlField = "txt_hl" + (i+1);
          var fieldName = "txt_hl" + (i+1) + "Desc";
          var divName = "hl" + (i+1) + "Desc";
          var divText = "<P ID='hLx'>"+ theForm.elements[fieldName].value + "</P>";
          if (theForm.elements[hlField].value !== "0000000" && theForm.elements[hlField].value !== "") {
                changeContent(getObject(divName), divText);
          }else{
                changeContent(getObject(divName),"<P ID='hLx'> </P>");
          }
      }
}

function setBackHierarchy(theForm){
      var delim = '^';
      var i = 0;
      var logo = getLogo();
      logo.topForm = logo.topForm + 1;
      if (logo.backHierarchy ==  ""){
          for( i=1; i<9; i++){
              var inputName ="txt_hl" + i + "Desc" ;
              logo.backHierarchy =  logo.backHierarchy + theForm.elements[inputName].value + "^";
          }
      }
}


/** takes a delimited string, parses it and stores the descriptions in hierarchy.html's
hidden inputs.
*/
function setHierarchyDesc(theForm, descs ){
       //	buildOrigiinalHierarchy(theForm)
      var delim = '^';
      var hlDesc= "";
      var i = 0;
      arrayDesc = descs.split(delim);
      while(i < arrayDesc.length){
          var inputName ="txt_hl" + (i+1) + "Desc" ;
          if(theForm.elements[inputName])
		  {
               theForm.elements[inputName].value = arrayDesc[i];
			  }
          i++;
      }
      updateHierarchyDesc(theForm);
}

/** uses dynamic HTML to display the the hierarchy descriptions set in hierarchy.html's
hidden inputs.
*/
function updateHierarchyDesc(theForm){
       //	buildOrigiinalHierarchy(theForm)
      for( i=0; i<8; i++){
          var fieldName = "txt_hl" + (i+1) + "Desc";
          var divName = "hl" + (i+1) + "Desc";
          var divText = "<P ID='hLx'>"+ theForm.elements[fieldName].value + "</P>";
          changeContent(getObject(divName), divText);
      }
}

/** called from browes window to pass back the hierarchy numbers and descriptions selected in
the browse window. creatHierarchyString must be called first, generally by the browse button.
*/
function selectHierarchy(hierarchyNumber)
    {
    hierarchyForm = document.forms[0];
    

      // Check to see if Hierarchy Level 0 (program number) exists and associate

     if (hierarchyForm.txt_hl0)
       {
        hierarchyForm.txt_hl0.value = hierarchyNumber.substring(0,7);
       }

     // Associate each Hierarchy Level with it's correct value

     hierarchyForm.txt_hl1.value = hierarchyNumber.substring(7,14);
     hierarchyForm.txt_hl2.value = hierarchyNumber.substring(14,21);
     hierarchyForm.txt_hl3.value = hierarchyNumber.substring(21,28);
     hierarchyForm.txt_hl4.value = hierarchyNumber.substring(28,35);
     hierarchyForm.txt_hl5.value = hierarchyNumber.substring(35,42);
     hierarchyForm.txt_hl6.value = hierarchyNumber.substring(42,49);
     hierarchyForm.txt_hl7.value = hierarchyNumber.substring(49,56);
     hierarchyForm.txt_hl8.value = hierarchyNumber.substring(56,63);

     setHierarchyDesc(hierarchyForm, hierarchyNumber.substring(63, hierarchyNumber.length));
    }


/** generates a string containing the Hierarchsy to pass to the browes window. */
function createHierarchyString(theForm,theUrl)
    {
   theForm = document.forms[0];
     var errormessage = hierarchy_parent_logo.ErrMsg_Header;
     var hierarchyString = theUrl+"?availableHierarchiesList=";
       //  hierarchyLevelSet(theForm);
     // Check to see if Program Number (HL0) exists
	

     if (theForm.txt_hl0)
         {
          if (((theForm.txt_hl0.value == "")||(theForm.txt_hl0.value == "0000000"))||
              ((theForm.txt_hl0.value != "")&&
              ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl0,7) == "error")||
               (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl0) == "error"))))
              {errormessage += hierarchy_parent_logo.ErrMsg_ProgramNumber;}
         }


     // This section checks the standard HL1 - HL8

     if ((theForm.txt_hl1.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl1) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel1;}

     if ((theForm.txt_hl2.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl2,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl2) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel2;}

     if ((theForm.txt_hl3.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl3,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl3) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel3;}

     if ((theForm.txt_hl4.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl4,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl4) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel4;}

     if ((theForm.txt_hl5.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl5,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl5) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel5;}

     if ((theForm.txt_hl6.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl1,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl6) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel6;}

     if ((theForm.txt_hl7.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl7,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl7) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel7;}

     if ((theForm.txt_hl8.value != "")&&
         ((hierarchy_parent_logo.checkLength(theForm,theForm.txt_hl8,7) == "error")||
          (hierarchy_parent_logo.checkNumeric(theForm,theForm.txt_hl8) == "error")))
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevel8;}

     // Check to see which HL Sequence Test to perform

    /* if (theForm.txt_hl0)
       {
        if (hierarchy_parent_logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,
            theForm.txt_hl2,theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
            theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevelSequence;}
       }
     else
       {
        if (hierarchy_parent_logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
                            theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                            theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
          {errormessage += hierarchy_parent_logo.ErrMsg_HierarchyLevelSequence;}
       }

   */  // If no errors occurred create HL string, else display errors

     if (errormessage == hierarchy_parent_logo.ErrMsg_Header)
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

      if(theForm.hdn_transfer){
	      hierarchyString += (theForm.txt_hl1.value == "") ? "0000000" : theForm.txt_hl1.value;
	      hierarchyString += "0000000" ;
	      hierarchyString += "0000000" ;
	      hierarchyString += "0000000" ;
	      hierarchyString += "0000000" ;
	      hierarchyString += "0000000" ;
	      hierarchyString += "0000000" ;
	      hierarchyString += "0000000" ;
	      hierarchyString += "&isTransfer=transfer";
	}
      else {
              hierarchyString += (theForm.txt_hl1.value == "") ? "0000000" : theForm.txt_hl1.value;
	      hierarchyString += (theForm.txt_hl2.value == "") ? "0000000" : theForm.txt_hl2.value;
	      hierarchyString += (theForm.txt_hl3.value == "") ? "0000000" : theForm.txt_hl3.value;
	      hierarchyString += (theForm.txt_hl4.value == "") ? "0000000" : theForm.txt_hl4.value;
	      hierarchyString += (theForm.txt_hl5.value == "") ? "0000000" : theForm.txt_hl5.value;
	      hierarchyString += (theForm.txt_hl6.value == "") ? "0000000" : theForm.txt_hl6.value;
	      hierarchyString += (theForm.txt_hl7.value == "") ? "0000000" : theForm.txt_hl7.value;
	      hierarchyString += (theForm.txt_hl8.value == "") ? "0000000" : theForm.txt_hl8.value;

              hierarchyString += "&isTransfer=noTransfer";
      }


          openNewWindow(hierarchyString);
         }
     else
         {
          alert(errormessage);
         }
    } // End of createHierarchyString



/** creates a new window of of the provided URL.
  * This method must be overridden if you are in a frameset within a
  * frameset.  The overridden method must call the a ancestor window object witin
  * the window or original frameset.
  **/
var msg
function openNewWindow(theUrl)
    {
    	msg = window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=450,status=no,menubar=no")
    }


//This is Revise fix. Clear it after it's used
function populateReviseHierarchy(theForm)
{
	// FIND THE LOGO FRAME
 	var topWindow = window.top
 	while (!topWindow.logo){
 		topWindow = topWindow.opener
 	}
	var logo = topWindow.logo
	if (logo.delimitedReviseHierarchy)
	{
		setHierarchyDesc(theForm, logo.delimitedReviseHierarchy);
		clearReviseHierarchy();

	}
}


function setReviseHierarchy(theForm)
{
    // FIND THE LOGO FRAME
	 	var topWindow = window.top
	 	while (!topWindow.logo){
	 		topWindow = topWindow.opener
 	}
	var logo = topWindow.logo
	logo.delimitedReviseHierarchy = "";
	for (var i=1;i<9;i++){
		var inputName ="txt_hl" + i + "Desc" ;
		logo.delimitedReviseHierarchy += theForm.elements[inputName].value +"^"
	}
}

function clearReviseHierarchy()
{
	// FIND THE LOGO FRAME
	 	var topWindow = window.top
	 	while (!topWindow.logo){
	 		topWindow = topWindow.opener
 	}
	var logo = topWindow.logo;
	logo.delimitedReviseHierarchy = "";
}

function clearBackHier()
{
	var logo = getLogo();
	logo.BackHierarchy = "";
	logo.topFrom = 0;
}

