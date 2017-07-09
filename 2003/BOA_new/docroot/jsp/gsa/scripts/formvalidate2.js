//  THIS SCRIPT IS LIKE FORM VALIDATE BUT ONLY POST INPUT FIEDS THAT HAVE
//  CHANGED SINCE THE CREATEION OF THIS PAGE.
//

var isIE = document.all ? true : false;
var formText = "";
function changeContent(what,text)
/*****************************************************************
    Change the content within a DIV or LAYER tag (depending on
    browser type).
                                1/25/00 5:58PM
*****************************************************************/
{
    if (isIE)
      what.innerHTML = text;
    else {
      what.document.open();
      what.document.write(text);
      what.document.close();
    }
} //changeContent

function getObject(name)
/*****************************************************************
    Return an object (DIV or LAYER) for the passed in ID value.
                                1/25/00 5:58PM
*****************************************************************/
    {
      var obj = null;
      if (isIE)
        obj = document.all(name);
      else
        obj = document.layers[name];
      return (obj);
    }

function formTag( formName, end )
    //*****************************************************************
    //  Build a form tag (begin and end) for adding content dynamically.
    // IE does not allow for nested forms, but Netscape requires them.
    //                               1/25/00 3:07PM
    //*****************************************************************/
    {
    if ( end == null)
        return '<P ID="dynamicP"> <FORM NAME="' + formName + '">';
    else
        return "</FORM> </P>";
    }  // formTag

function formHeader( formName, formAction )
{
  formText += '<P ID="dynamicP"><form name = "' + formName + '" METHOD="GET" action="' + formAction + '" > \r';
}  // formHeader

function writeElement( varName, varValue )
{
    formText += "<INPUT NAME='" + varName + "' TYPE=Hidden VALUE='" + varValue + "'>\r";
}  // writeElement

function writeForm(objName)
  {
    formText += ' </FORM></P>';
    changeContent(getObject(objName), formText);
    formText = "";
  }  // writeForm


function processForms( doc )
{
    for ( var i=0;i<doc.layers.length;i++ ) {
        processForms(doc.layers[i].document);
    }

    for ( var i=0;i<doc.forms.length;i++ ) {
        for ( var j=0;j<doc.forms[i].elements.length;j++ ) {
            var e= doc.forms[i].elements[j];
            if (e.type == "select-one"){
		if (!e.options[e.selectedIndex].defaultSelected)
            	    writeElement(e.name, e.options[e.selectedIndex].value);
            } else if( e.type == "select-multiple"){
            	while (e.selectedIndex >= 0){
            	    e.options[e.selectedIndex].selected = false
            	    writeElement(e.name, e.options[e.selectedIndex].value);
            	}
            } else {
            	if (e.value != e.defaultValue)
            	    writeElement(e.name, e.value);
            }
        }
    }
}  // processForms


function buildForm(layerName, theForm, formAction)
/*****************************************************************
    Create a form with using inputs from every FORM tag that is
    currently being displayed on the page. Hidden INPUTs will be
    included.
    layerName is the layer name to use for the new FORM
    theForm is the name for the new FORM
    formAction is the action for the new FORM
                                1/26/00 3:20PM
*****************************************************************/
    {
    // wipe out anything that may have been there...
    changeContent(getObject(layerName), '<P ID="dynamicP">"  </P>');

      if ( isIE ) {
        formHeader( theForm, formAction);
        for ( var i=0;i<document.forms.length;i++ ) {
            for ( var j=0;j<document.forms[i].elements.length;j++ ) {
                var e = document.forms[i].elements[j];
                writeElement(e.name, e.value);
            }
        }
        writeForm(layerName);
        var newObj = eval("document." + theForm);
        return newObj;
      }
    // Netscape specific
    formHeader( theForm, formAction);
    processForms(document);
    writeForm(layerName);
    var newObj = findForm(document, theForm);
    return newObj;;
  }  // buildForm



function findForm( doc, theForm )
{
    var retVal = null;
    for ( var i=0;i<doc.layers.length;i++ ) {
        retVal = findForm(doc.layers[i].document, theForm);
    }
    if ( retVal != null ) {
        return retVal;
    }

    for ( var i=0;i<doc.forms.length;i++ ) {
        for ( var j=0;j<doc.forms[i].elements.length;j++ ) {
            if ( doc.forms[i].name == theForm ) {
                return doc.forms[i];
            }
        }
    }
}  // processForms


