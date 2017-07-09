<%@ page import="java.util.*"%>

<%@ taglib uri="/boa/app" prefix="app" %>


<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>

<%
 Hashtable map=(Hashtable)request.getAttribute("map");
 if(map==null) map = new Hashtable();

String showNextLevelButton = (String)map.get("showNextLevelButton");

 Vector data = (Vector)request.getAttribute("data");

 if(data==null) data = new Vector();
 boolean revise=false;

%>

<HTML>
<HEAD>

<TITLE>Please Select a Hierarchy Level</TITLE>

<SCRIPT LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

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

// Stop hiding -->
</SCRIPT>

</HEAD>

<!--------------------------------------------------------------------->

<BODY onload="Initialize(document.frm_browseHierarchies)">

<FORM NAME="frm_browseHierarchies"
      ACTION="browseHierarchy.do"
      TARGET="_self"
      onsubmit="return formTest(document.frm_browseHierarchies)">

 <% if(data.size()!=0) { %>
<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Parent Hierarchies</FONT></B><BR>
<%
 }
%>
<FONT SIZE=-1>
<%= map.get("hl1Desc")==null?"":map.get("hl1Desc") %>

  <dd>
<%= map.get("hl2Desc")==null?"":map.get("hl2Desc")%>
&nbsp; &nbsp;  <DD><DD>

<%= map.get("hlCurrentDesc")==null?"":map.get("hlCurrentDesc") %>
<BR>
</FONT>

<!--------------------------------------------------------------------->

<CENTER>
<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6"><BR><BR>

<B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Available Hierarchies</FONT></B><BR><BR>

<SELECT NAME="availableHierarchiesList" SIZE="10">
<%
Iterator it=data.iterator();
if(data.size()==0){
revise=true;
%>
    <OPTION VALUE = "noHierarchiesAvailable">No Hierarchies Found</OPTION>
<%
}
else {


if(map.get("showPreviousButton")==null)
{

%>
<OPTION VALUE= "<%=map.get("previousHyperlink")%>">... Previous ...</OPTION>

<%
}



for(int i=0; i<data.size() ; i++) {
	Hashtable ht = (Hashtable)it.next();
%>
    <OPTION VALUE='<%=ht.get("hierarchyValue")==null?"":ht.get("hierarchyValue")%>'><%=ht.get("hierarchyDescription")==null?"":ht.get("hierarchyDescription")%></OPTION>

<% }

if(map.get("showNextButton")==null)
{

%>
<OPTION VALUE= "<%=map.get("nextHyperlink")%>">... Next ...</OPTION>

<%
}

}





%>




</SELECT>

<BR><BR>

<!--------------------------------------------------------------------->

<TABLE>
    <TR>
      <TD>
	<% if (revise) {%>
        <INPUT type="Button" name="but_revise" value="Revise"
               onclick="historyTest()">&nbsp;&nbsp;

	<% } else { %>
        <INPUT TYPE="BUTTON" NAME="but_selectButton" VALUE="Select"
               onclick="assignvalue(document.frm_browseHierarchies)">&nbsp;&nbsp;

        <INPUT type="Button" name="but_prevLevel" value="Prev Level"
               onclick="history.go(-1);">&nbsp;&nbsp;

        <%
        if(showNextLevelButton==null)
        {
        %>
        <INPUT TYPE="SUBMIT" NAME="but_submitButton" VALUE="Next Level">
                &nbsp;&nbsp;
        <%}%>

        <INPUT TYPE="BUTTON" NAME="but_cancelButton" value="Cancel"
               onclick="window.close()">
	<%
		}
	%>

      </TD>
    </TR>
</TABLE>
<BR>


	<INPUT TYPE="HIDDEN" NAME="hdn_isTransfer" value="TRUE">


<!--------------------------------------------------------------------->

<IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">
</CENTER>
<br>
<br>
<br>
<br>
</FORM>
</BODY>
</HTML>
