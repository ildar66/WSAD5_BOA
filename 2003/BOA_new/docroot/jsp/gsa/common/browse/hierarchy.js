isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}


function Initialize(theForm)
    {

     if (theForm.availableHierarchiesList.length > 0)
         {
          theForm.availableHierarchiesList.options[0].selected=true;
         }
    }

function assignvalue(theForm)
    {
     if ((theForm.availableHierarchiesList.options[theForm.availableHierarchiesList.selectedIndex].text == "... Previous ...")||
         (theForm.availableHierarchiesList.options[theForm.availableHierarchiesList.selectedIndex].text == "... Next ..."))
         {


window.location.href=theForm.availableHierarchiesList.options[theForm.availableHierarchiesList.selectedIndex].value;
          return;
         }
     window.opener.selectHierarchy(theForm.availableHierarchiesList.options[theForm.availableHierarchiesList.selectedIndex].value);
    window.close();
    }

function formTest(theForm)
    {
       if ((theForm.availableHierarchiesList.selectedIndex <0))
	        {
//	           alert("\nNo account hierarchy selected.");
	           return false
        }
     if ((theForm.availableHierarchiesList.options[theForm.availableHierarchiesList.selectedIndex].text == "... Previous ...")||
         (theForm.availableHierarchiesList.options[theForm.availableHierarchiesList.selectedIndex].text == "... Next ..."))

         {
          alert("\nDrilling is not permitted on this selection");
          return false;
         }

     return true;
    }

function historyTest()
   {

	// TEST TO SEE IF BROWSER IS IE
    // NOTE: window.history.length always returns 0 in IE 3.x browsers.
	//       Thus, window will always close in IE 3.x browsers
	// ALSO, NN browers start counting history length at 1, IE at 0.

    if (navigator.appName.indexOf("Microsoft") != -1)
      {
       if (window.history.length > 0)
         {
          history.go(-1);
         }
       else
         {
          window.close();
         }
      }
    else // ALL OTHER BROWSERS (NETSCAPE)
	  {
       if (window.history.length > 1)
         {
           history.go(-1);
         }
       else
         {
           window.close();
         }
      }
    }

