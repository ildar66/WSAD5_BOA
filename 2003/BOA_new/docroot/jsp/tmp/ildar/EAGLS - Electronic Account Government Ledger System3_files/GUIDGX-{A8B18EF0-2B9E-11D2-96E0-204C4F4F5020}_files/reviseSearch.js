//go back to the search screen started from
function goBack()
{
       var logo = getLogoFrame();
	   if (logo.startReviseSearchHierarchy)
	       location.replace(logo.startReviseSearchHierarchy);
	   else
	       history.back(-1);
}
//get the logo frame
function getLogoFrame()
{
// FIND THE LOGO FRAME
		var topWindow = window.top
	 	while (!topWindow.logo){
	 		topWindow = topWindow.opener
	 	}
		var logo = topWindow.logo
		return logo;
}
//This is Revise fix. Clear it after it's used
function populateReviseSearchHierarchy(theForm)
{
	// FIND THE LOGO FRAME
 	var topWindow = window.top
 	while (!topWindow.logo){
 		topWindow = topWindow.opener
 	}
	var logo = topWindow.logo
	if (logo.delimitedReviseSearchHierarchy)
	{
		setHierarchyDesc(theForm, logo.delimitedReviseSearchHierarchy);
		clearReviseSearchHierarchy();
	}
}
function setReviseSearchHierarchy(theForm)
{
	clearReviseSearchHierarchy();
	var logo = getLogoFrame();
	for (var i=1;i<9;i++){
		var inputName ="txt_hl" + i + "Desc" ;
		logo.delimitedReviseSearchHierarchy += theForm.elements[inputName].value +"^"
	}
	var wloc = new String(window.location);
	//logo.startReviseSearchHierarchy = (wloc).substring((wloc).indexOf("searchType=")+"searchType".length+1,(wloc).length);
	logo.startReviseSearchHierarchy = wloc.toString();
}	
function clearReviseSearchHierarchy()
{
	var logo = getLogoFrame();
	logo.delimitedReviseSearchHierarchy = "";		
	logo.startReviseSearchHierarchy = "";		
}
