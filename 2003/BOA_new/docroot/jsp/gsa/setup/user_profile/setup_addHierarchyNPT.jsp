<%@ page language="java" import="com.boa.eagls.government.util.LookUp,org.apache.struts.action.DynaActionForm" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>
<app:checkRights functionName="<%=com.boa.eagls.government.util.Constants.FUNC_U_CREATE_USER_PROFILE%>"/>
<%
java.util.Hashtable map= (java.util.Hashtable)request.getAttribute("map");
%>
<html:html locale="true">
<HEAD>
<TITLE>User Profile - Add Hierarchy and Program Type</TITLE>
<META NAME = "Name" CONTENT = "Add Hierarchy and Program Type">
<META NAME = "Description" CONTENT = "Allows a user to add an HL and PT a user profile">
<SCRIPT LANGUAGE="JavaScript" SRC="<%= request.getContextPath() %>/jsp/gsa/scripts/formvalidate.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/hierarchyBrowse.js"></SCRIPT>
<!--------------------------------------------------------------------->
<SCRIPT LANGUAGE = "JavaScript">
//FUNCTIONS

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}




function form_Test(theForm)
{
	var errormessage =parent.logo.ErrMsg_Header;


 //if (theForm.hdn_cnc.value!=1)
 //{

 // Check to see if the User is AOPC he can not change the Program Number.

//	if((theForm.hdn_user.value =="A_OPC")&&
//	  (theForm.hdn_prgrmNmbr.value!=theForm.txt_hl0.value))
//	{
//		errormessage += "Program Number can not be changed.\n";
//	}


	// Check to see if the user is GCSU and the End role Button is presssed
	//	 the Program Number field should be filled

//	if((theForm.hdn_user.value=="GCSU")&&
	if( (theForm.hdn_cnc.value == 1))
	{
		if(theForm.txt_hl0)
		{
			if(theForm.txt_hl0.value =="")
				errormessage += parent.logo.ErrMsg_ProgramNumber;;
		}
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

	// Check to see if Program Number (HL0) exists

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

	if (theForm.hdn_cnc.value!=1)
	{
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
		}
		else
		{
			if((theForm.txt_hl1.value=="")&&(theForm.txt_hl2.value=="")&&
                     (theForm.txt_hl3.value=="")&&(theForm.txt_hl4.value=="")&&
                     (theForm.txt_hl5.value=="")&&(theForm.txt_hl6.value=="")&&
                     (theForm.txt_hl7.value=="")&&(theForm.txt_hl8.value==""))
			{
				errormessage += parent.logo.ErrMsg_NoHierarchyEntered;
			}
		}
	}


	if(theForm.txt_hl1.value.length != "0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl1) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel1;
		}
	}

	if(theForm.txt_hl2.value.length!="0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl2) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl2,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel2;
		}
	}

	if(theForm.txt_hl3.value.length != "0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl3) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl3,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel3;
		}
	}

	if(theForm.txt_hl4.value.length != "0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl4) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl4,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel4;
		}
	}

	if(theForm.txt_hl5.value.length != "0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl5) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl5,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel5;
		}
	}

	if(theForm.txt_hl6.value.length != "0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl6) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl6,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel6;
		}
	}

	if(theForm.txt_hl7.value.length != "0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl7) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl7,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel7;
		}
	}

	if(theForm.txt_hl8.value.length != "0")
	{
		if ((parent.logo.checkNumeric(theForm,theForm.txt_hl8) == "error")||
		   (parent.logo.checkLength(theForm,theForm.txt_hl8,7) == "error"))
		{
			errormessage += parent.logo.ErrMsg_HierarchyLevel8;
		}
	}

	// Check to see if any of the Program Type is selected

	if (theForm.txt_hl0.selectedIndex<0)
	{
		errormessage +=parent.logo.ErrMsg_ProgramType;
	}

	// Make sure that an HL1 is entered for all Roles (GCSUs do not need an HL1
	//     but GCSUs do not use this setup)
	//if (theForm.baseRole.value !='GCSU')
	{
		if(theForm.txt_hl1.value == "")
		{
			errormessage += "You have to enter at least an HL1 to add a non GCSU role\n"
		}
	}

	if (errormessage != parent.logo.ErrMsg_Header)
	{
		alert(errormessage);
		return (false);
	}

	return (true);
 //    }
  }


// THIS SECTION WAS ADDED TO SUPPORT HIERARCHY BROWSING

function openNewWindow(theUrl)
  {
   msg = window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=450,status=no,menubar=no");
  }


    //Stop Hiding
 function setBtnVal(val){
//    alert("set Btn val "+val);
    document.forms[0].btnSubmitVal.value == val;
  }

  function validateProgramNumber(){
     var pnum = document.forms[0].txt_hl0.value;
      if (((pnum == "") || (pnum == "0000000")) || ((pnum != "") && (pnum.length() != 7)))
      {
         alert("Propgram Number "+pnum +" is not valid!");
         errormessage += parent.logo.ErrMsg_ProgramNumber;
         return false;
      }
return true;

  }
</SCRIPT>
</head>

<BODY onLoad="resetHierDesc(document.forms[0])">

<html:form action="/addHierarchyPT" onsubmit = "return form_Test(document.forms[0])">
<html:hidden property="step" value="Step2HierarchyAndPT"/>
<html:hidden property="btnSubmitVal" value="end"/>

<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING, "heading.userprofile.setup");%>
<%--<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.information");%>--%>
<%@ include file="/jsp/gsa/header_systemDefault.jsp"%>
<br>
<br>
<TABLE >
  <TR>
    <TD><B><FONT COLOR = "#0000FF" FACE = "Arial, HELVETICA">Hierarchy / Program Type  </FONT></B>
    </TD>
  </TR>
</TABLE>
<TABLE BORDER = 0>
  <TR>
    <TD><B>Name:</B></TD>
    <TD>
        <bean:write name="UserProfileBean" property="txt_firstName" />
        <bean:write name="UserProfileBean" property="txt_lastName" />
    </TD>
    <TD>
</TD>
  </TR>
  <TR>
    <TD ><B>User ID:</B></TD>
    <TD>
       <bean:write name="UserProfileBean" property="txt_userId" />
    </TD>
    <TD>

</TD>
  </TR>
  <TR>
    <TD><B>Role:</B></TD>
    <TD>
        <bean:write name="UserProfileBean" property="txt_role" />
    </TD>
    <TD>

</TD>
  </TR>
  <TR>
    <TD><B>Hierarchy</B></TD>
    <TD><html:button property="but_Browse" value="Browse" onclick="createHierarchyString(document.forms[0],'browseHierarchy.do')" /></TD>
    <TD>

    </TD>
  </TR>
</table>
<%@ include file="/jsp/gsa/common/hierarchy.jsp"%>


<BR><i>
Select multiple Program Types by holding down the CTRL key and selecting multiple items.
<BR>
Deselect items by holding down the CTRL and clicking on selection.
<BR></i>
<TABLE >
<TR>
    <TD VALIGN = "TOP"><B>Program Type</B></TD>
    <TD VALIGN = "TOP">
    <SELECT  NAME = "programTypes" SIZE = "5" Multiple onclick = "window.status = 'Select Program Type(s)';">
      <OPTION VALUE ="Fleet" SELECTED>Fleet</OPTION>
      <OPTION VALUE ="Integrated" >Integrated</OPTION>
      <OPTION VALUE ="Interagency">Interagency</OPTION>
      <OPTION VALUE ="Purchase">Purchase</OPTION>
      <OPTION VALUE ="Travel">Travel</OPTION>
   </SELECT>
    </TD>
  </TR>
</TABLE>
<CENTER>
 <P ALIGN="CENTER">

<%--<html:submit property="but_submitButton" value="End Role" onclick="setBtnVal('end')" />
<html:submit property="but_submitButton" value="Add Hierarchy/PT" onclick="setBtnVal('more');setBackHierarchy(document.forms[0]);document.forms[0].hdn_cnc.value=0;  window.location.href='jsp/gsa/notavailable.jsp'" />
--%>
        <INPUT TYPE = "SUBMIT" NAME = "but_submitButton" VALUE = "End Role"
		onClick="document.forms[0].hdn_cnc.value=1;">
        <INPUT TYPE = "SUBMIT" NAME = "but_submitButton" VALUE = "Add Hierarchy/PT"
		onClick="setBackHierarchy(document.forms[0]);document.forms[0].hdn_cnc.value=0;">

</P>
</CENTER>
<!-- **** HIDDEN VARIABLES **** -->
<INPUT TYPE="HIDDEN" NAME="hdn_cnc" VALUE="0">

<%--
<input type="hidden" name="txt_firstName" value="<%=map.get("txt_firstName")%>">
<input type="hidden" name="txt_lastName" value="<%=map.get("txt_lastName")%>">
<input type="hidden" name="txt_userId" value="<%=map.get("txt_userId")%>">
<input type="hidden" name="status" value="<%=map.get("status")%>">
<input type="hidden" name="currentRole" value="<%=map.get("currentRole")%>">
<input type="hidden" name="baseRole" value="<%=map.get("baseRole")%>">
<input type="hidden" name="prevRoles" value="<%=map.get("prevRoles")%>">
<input type="hidden" name="hdn_prgmnmbr" >
<input type="hidden" name="hdn_user" >
<input type="hidden" name="txt_reportsAccess" value="<%=map.get("txt_reportsAccess")%>">

--%>
<!-- **** END HIDDEN VARIABLES **** -->
<br>
<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>

</html:form>
</BODY>
</html:html>
