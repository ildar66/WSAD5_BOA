<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.boa.eagls.government.constants.web.CentralAccount" %>

<html:html>
<HEAD>
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
<TITLE></TITLE>

<SCRIPT LANGUAGE="JavaScript">
<!-- Start hiding from other browsers

browseFlag = false;

function Initialize(theForm)
    {
     if (theForm.sel_availableSegments.length != 0)
         {
          theForm.sel_availableSegments.options[0].selected=true;
         }
    }

function assignValue(theForm)
    {
     //REMOVE "/'S" FROM MASTER ACCOUNTING CODE

     var theSegmentRepresentation=theForm.hdn_segmentRepresentation.value;
     var theSegmentDescription="";

	 if (theForm.slashes.value == "x") {
     theSegmentRepresentation=searchAndReplace(theSegmentRepresentation,"/","",false,false);
	 //theSegmentDescription=searchAndReplace(theForm.hdn_segmentDescription1.value,"/","",false,false);
	 }
	 if (theForm.hdn_segmentDescription1.value.length > 0) {
	 	if (theForm.slashes.value == "x") {
			theSegmentDescription=searchAndReplace(theForm.hdn_segmentDescription1.value,"/","",false,false);
	 	} else {
			theSegmentDescription=theForm.hdn_segmentDescription1.value;
		}
	 } else {
	   theSegmentDescription="";
	 }

     window.parent.opener.selectMasterAccountingCode(theSegmentRepresentation, theSegmentDescription);
	 //window.parent.opener.selectMasterAccountingCode(theSegmentRepresentation);
     window.parent.close();
    }

<!--******************  SearchandReplace    *************************-->
<!--*This function will search and replace                          *-->
<!--*target=the orginal string    oldterm= term to replace        *-->
<!--*newterm=replace with         casesense=true/false          *-->
<!--*wordonly=whole word (true), substring (false)                *-->
<!--*****************************************************************-->

function searchAndReplace(target,oldterm,newterm,casesens,wordonly)
    {
     var work=target;
     var ind=0;
     var next=0;

     if (!casesens)
         {
          oldterm=oldterm.toLowerCase();
          work=target.toLowerCase();
         }

     while ((ind=work.indexOf(oldterm,next)) >= 0)
         {
          if (wordonly)
              {
               var before=ind -1;
               var after=ind + oldTerm.length;
               if (!(space(work.charAt(before)) && space(work.charAt(after))))
                   {
                    next=ind + oldTerm.length;
                    continue;
                   }
              }
          target=target.substring(0,ind) + newterm +
          target.substring(ind+oldterm.length,target.length);
          work=work.substring(0,ind) + newterm +
          work.substring(ind+oldterm.length,work.length);
          next=ind + newterm.length;
          if (next >= work.length)
              {
               break;
              }
         }
     return (target);
    }

function setBrowseFlag()
    {
     browseFlag=true;
    }

function browseWarning(theForm)
    {
     if (browseFlag)
         {
          if (theForm.txt_browseCriteria.value=="")
              {
               var response = confirm("The data you are about to request may be up to 17 megabytes in length, requiring a long transfer time for dial-up connections.  To narrow your search, please use browse criteria.  Do you wish to continue with the full list?");
               if (response)
                   {
                    browseFlag=false;
                    return true;
                   }
               else
                   {
                    browseFlag=false;
                    return false;
                   }
              }
          return true;
         }
     return true;
    }

	function addToFavoriteList(theForm)
	    {
		  theSegmentRepresentation=searchAndReplace(theForm.hdn_segmentRepresentation.value," ","",false,false);
		  // Prevent # being interpreted as a URL anchor
		  theSegmentRepresentation=searchAndReplace(theForm.hdn_segmentRepresentation.value,"#","%23",false,false);

		  //theSegmentRepresentation=searchAndReplace(theForm.hdn_segmentRepresentation.value,"/","",false,false);
		  theUrl = '<%=request.getContextPath()%>/jsp/gsa/notImplemented.jsp?acctCenterID=' + theForm.hdn_accountingCenterId.value ;
		  theUrl = theUrl + '&allocationCode=' + theSegmentRepresentation
	      msg=window.open(theUrl,"FavoritesWindow","screenX=40,screenY=40,width=600,height=500,status=no,menubar=no,scrollbars");
    }

// Stop hiding -->
</SCRIPT>

<BASE TARGET="lowerFrame">
</HEAD>

<!------------------------------------------------------------------------------>

<BODY onload="Initialize(document.frm_accountingCodeSegmentSelection)">
<jsp:useBean id="browseMasterAcctCodeDTO" 
	class="com.boa.eagls.government.dto.browse.BrowseMasterAcctCodeDTO" scope="request" />

<FORM NAME="frm_accountingCodeSegmentSelection"
      ACTION="<%=request.getContextPath()%>/commom/browseAccountingCodesAction.do"
      METHOD="POST"
      TARGET="accountingCodeSelection"
      onSubmit="return browseWarning(document.frm_accountingCodeSegmentSelection)">

<!------------------------------------------------------------------------------>
<CENTER>
<FONT COLOR="#0000FF" FACE="Arial, HELVETICA"><B>Construct Accounting Code</B></FONT>
<BR>
<TABLE>
    <TR>
      <TD WIDTH="200">
       <B>Accounting Code <br>and Description</B>
      </TD>
      <TD WIDTH="250">
        <GX type=replace id=segmentRepresentation value=theSegmentRepresentation>
          <B><SMALL><%=browseMasterAcctCodeDTO.getSegmentRepresentation()%></SMALL></B>
        </GX>
		<BR>
	    <GX type=replace id=segmentDescription1 value=theSegmentDescription12>
	         <B><SMALL><%=browseMasterAcctCodeDTO.getSegmentDescription1()%></SMALL></B>
	    </GX>
	  </TD>
    </TR>
    <TR>
      <TD><B>Segment</B></TD>
      <TD>
        <SELECT NAME="sel_availableSegments" SIZE="1">
		  <%int i = 0;%>
		  <logic:iterate id="item"
		  		collection="<%=browseMasterAcctCodeDTO.getAccountingCodeSegmentDescriptions() %>"
		  	type="com.boa.eagls.government.dto.accounting.AccountingCodeSegmentDescription">
   			<OPTION VALUE="<%=i%>">
				<%="(" + (++i) + ") " + item.getDescription()%>
			</OPTION>
		  </logic:iterate>
        </SELECT>
      </TD>
    </TR>
    <TR>
      <TD><B>Browse Criteria</B></TD>
      <TD>
        <INPUT TYPE="Text" NAME="txt_browseCriteria" SIZE="55" MAXLENGTH="84"
               onFocus="document.frm_accountingCodeSegmentSelection.txt_segmentValue.value=''">
      </TD>
    </TR>
    <TR>
      <TD><B>Add Segment Value</B></TD>
      <TD>
        <INPUT TYPE="Text" NAME="txt_segmentValue" SIZE="31" MAXLENGTH="64"
               onFocus="document.frm_accountingCodeSegmentSelection.txt_browseCriteria.value=''">
      </TD>
    </TR>
</TABLE>


<!-- HIDDEN FIELDS -->
<INPUT TYPE="Hidden" NAME="hdn_segmentRepresentation" 
	VALUE="<%=browseMasterAcctCodeDTO.getSegmentRepresentation1()%>">

<INPUT TYPE="Hidden" NAME="hdn_segmentDescription" 
	VALUE="<%=browseMasterAcctCodeDTO.getSegmentDescription()%>">

<INPUT TYPE="Hidden" NAME="hdn_segmentDescription1" 
	VALUE="<%=browseMasterAcctCodeDTO.getSegmentDescription1()%>">

<INPUT TYPE="Hidden" NAME="hdn_accountingCode" 
	VALUE="<%=browseMasterAcctCodeDTO.getHdn_accountingCode()%>">

<INPUT TYPE="Hidden" NAME="hdn_accountingCenterId" 
	VALUE="<%=browseMasterAcctCodeDTO.getHdn_accountingCenterId()%>">

<INPUT TYPE="Hidden" NAME="hdn_valueSet" 
	VALUE="<%=browseMasterAcctCodeDTO.getHdn_valueSet()%>">

<INPUT TYPE="Hidden" NAME="hdn_segNumSet" 
	VALUE="<%=browseMasterAcctCodeDTO.getHdn_segNumSet()%>">

<INPUT TYPE="Hidden" NAME="hideButton" 
	VALUE="<%=browseMasterAcctCodeDTO.getHideButton()%>">

<INPUT TYPE="Hidden" NAME="slashes" 
	VALUE="<%=browseMasterAcctCodeDTO.getSlashes()%>">


<!------------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD>
	  <!----------acctCenterID,allocationDescription,allocationCode------------------->
	  <!-----------need to be incorporated------------------------------------------->
	    <GX type=cell id=showButton>
	    	<INPUT TYPE="Button" NAME="but_selectSegment" VALUE="Add to Favorites"
  		       onClick="addToFavoriteList(document.frm_accountingCodeSegmentSelection)">&nbsp;&nbsp;
	    </GX>

        <INPUT TYPE="Button" NAME="but_selectSegment" VALUE="Finished"
               onClick="assignValue(document.frm_accountingCodeSegmentSelection)">&nbsp;&nbsp;

        <INPUT TYPE="Submit" NAME="but_submit" VALUE="Add Segment"
               onClick="document.frm_accountingCodeSegmentSelection.txt_browseCriteria.value=''">&nbsp;&nbsp;

        <INPUT TYPE="Submit" NAME="but_submit" VALUE="Browse Segment"
               onClick="document.frm_accountingCodeSegmentSelection.txt_segmentValue.value=''
                        setBrowseFlag()">&nbsp;&nbsp;

        <INPUT TYPE="Button" NAME="but_cancel" VALUE="Cancel"
               onClick="window.parent.close();">
      </TD>
    </TR>
</TABLE>
</CENTER>

<!------------------------------------------------------------------------------>

</FORM>
</BODY>
</html:html>