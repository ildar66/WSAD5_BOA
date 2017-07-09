<%@ page language="java" import="org.apache.struts.action.DynaActionForm" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/boa/app" prefix="app" %>
<app:checkLogon name="htSessionData" page="/jsp/gsa/index.jsp"/>
<app:checkRights functionName="<%=com.boa.eagls.government.util.Constants.FUNC_U_CREATE_USER_PROFILE%>"/>
<%
java.util.Hashtable map = (java.util.Hashtable)request.getAttribute("map");
%>
<HEAD>
<TITLE>User Profile - Add Hierarchy</TITLE>
<SCRIPT src="<%= request.getContextPath() %>/jsp/gsa/scripts/hierarchyBrowse.js"></SCRIPT>
<script src="<%= request.getContextPath() %>/jsp/gsa/scripts/formvalidate.js"></script>
<SCRIPT LANGUAGE  =  "JavaScript">

isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}


//FUNCTIONS

function form_Test(theForm)
{
    var errormessage =parent.logo.ErrMsg_Header;

     // Check to see which HL Sequence Test to perform

       if (theForm.txt_hl0)
         {
          if (parent.logo.checkHLSequence(theForm.txt_hl0,theForm.txt_hl1,theForm.txt_hl2,
                              theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                              theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
            {errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;}
         }
       else
         {
          if (parent.logo.checkHLSequence2(theForm.txt_hl1,theForm.txt_hl2,
                              theForm.txt_hl3,theForm.txt_hl4,theForm.txt_hl5,
                              theForm.txt_hl6,theForm.txt_hl7,theForm.txt_hl8) == "error")
            {errormessage += parent.logo.ErrMsg_HierarchyLevelSequence;}
         }

		// Check to see if the user is GCSU and the End role Button is presssed
		//the Program Number field should be filled

	//	 if((theForm.hdn_user.value=="GCSU")&&
		 if((theForm.hdn_cnc.value == 1))
			{
			 if(theForm.txt_hl0)
			  {
			   if(theForm.txt_hl0.value =="")
			    errormessage += parent.logo.ErrMsg_ProgramNumber;;
			  }
			}

        // Check to see if Program Number (HL0) exists

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
           }
       if (theForm.txt_hl0)
         {
          if (((theForm.txt_hl0.value == "0000000"))||
              ((theForm.txt_hl0.value != "")&&
              ((parent.logo.checkLength(theForm,theForm.txt_hl0,7) == "error")||
               (parent.logo.checkNumeric(theForm,theForm.txt_hl0) == "error"))))
                {errormessage += parent.logo.ErrMsg_ProgramNumber;}
         }
        if(theForm.txt_hl1.value.length != "0")
         {
            if ((parent.logo.checkNumeric(theForm,theForm.txt_hl1) == "error")||
                (parent.logo.checkLength(theForm,theForm.txt_hl1,7) == "error"))
            {
              errormessage += parent.logo.ErrMsg_HierarchyLevel1;
            }
         }

        if(theForm.txt_hl2.value.length != "0")
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

  }
// THIS SECTION WAS ADDED TO SUPPORT HIERARCHY BROWSING

function openNewWindow(theUrl)
  {
   msg = window.open(theUrl,"NewWindow","screenX=40,screenY=40,width=500,height=450,status=no,menubar=no");
  }



  function setBtnVal(val){
//    alert("set Btn val "+val);
//	document.forms[0].txt_hl0.value=document.forms[0].programNumber.value;
//   document.HierarchyForm.btnSubmitVal.value = val;
  }
  function validateProgramNumber(){
     var pnum = document.forms[o].txt_hl0.value;
      if (((pnum == "") ||(pnum == "0000000")) || ((pnum != "") && (pnum.length() != 7)))
      {
         alert("Propgram Number "+pnum +"is not valid!");
        errormessage += parent.logo.ErrMsg_ProgramNumber;
        return false;
      }
    return true;

  }
</SCRIPT>
<!------------------------JAVA SCRIPTS ENDS------------------------->
</head>
<!-- onLoad="resetHierDesc(document.frm_addHierarchy)" -->
<BODY>


<html:html locale="true">

<!------------------------FORM STARTS------------------------------->
<html:form action="/hierarchy" onsubmit = "return form_Test(document.forms[0])">
<%--<html:hidden property="step" value="Step2Hierarchy"/>
<html:hidden property="btnSubmitVal" value="end"/>--%>

<%request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING, "heading.userprofile.setup");%>
<%@ include file="/jsp/gsa/header_systemDefault.jsp"%>
<br>
<TABLE>
  <TR>
    <TD> <B><FONT COLOR = "#0000FF" FACE = "Arial, HELVETICA">Hierarchy<BR> </FONT></B>
    </TD>
  </TR>
</TABLE>

<TABLE BORDER = 0>
  <TR>
    <TD><B>Name:</B></TD>
    <TD>
  <bean:write name="UserProfileBean" property="txt_firstName" /> <bean:write name="UserProfileBean" property="txt_lastName" />
</TD>
  </TR>

  <TR>
    <TD ><B>User ID:</B></TD>
    <TD>
<bean:write name="UserProfileBean" property="txt_userId" />
</TD>
  </TR>
  <TR>
    <TD><B>Role:</B></TD>
    <TD>
<bean:write name="UserProfileBean" property="txt_role" />
</TD>
  </TR>

  <TR>
    <TD><B>Hierarchy</B></TD>
    <TD>
    <html:button property="but_Browse" value="Browse" onclick="createHierarchyString(document.forms[0],'browseHierarchy.do')" />
   </TD>
  </TR>
</table>

<%@ include file="/jsp/gsa/common/hierarchy.jsp"%>



		<INPUT name="hdn_delimitiedHierarchy"type=HIDDEN value="&#160^&#160^&#160^&#160^&#160^&#160^&#160^&#160">
<!--- END DESCRIPTIONS------------------------------->

<CENTER>
<P ALIGN = "CENTER">
<html:submit property="but_submitButton" value="End Role" onclick="setBtnVal('end'); document.forms[0].hdn_cnc.value=1;" />
<html:submit property="but_submitButton" value="Add Hierarchy" onclick="setBtnVal('more');" />
<!--; setBackHierarchy(document.frm_addHierarchy);document.forms[0].value=0;"-->
</P>
</CENTER>
<INPUT TYPE="HIDDEN" NAME="hdn_cnc" VALUE=0>

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

<br>
<%@ include file="/jsp/gsa/footer_systemDefault.jsp"%>

</html:form>
<!------------------------FORM ENDS------------------------------->
</BODY>
</html:html>
