<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/boa/app" prefix="eagls" %>
<%@ page import="com.boa.eagls.government.constants.web.CentralAccount" %>

<html:html>
<HEAD>

<TITLE>Browse Accounting Codes</TITLE>

<META NAME="Author" CONTENT="?">
<META NAME="Description" CONTENT="?">

<SCRIPT LANGUAGE = "javascript">
<!-- Start hiding from other browsers

function Initialize(theForm)
    {
     if (theForm.sel_availableSegmentValues.length != 0)
         {
          theForm.sel_availableSegmentValues.options[0].selected = true;
         }
    }

function assignvalue(theForm)
	{
    if (theForm.sel_availableSegmentValues.options[0])
     {
     if ((theForm.sel_availableSegmentValues.options[theForm.sel_availableSegmentValues.selectedIndex].text==
	 		"... Previous ...")||
         (theForm.sel_availableSegmentValues.options[theForm.sel_availableSegmentValues.selectedIndex].text==
		 	"... Next ..."))
         {
          window.parent.accountingCodeSelection.location.href=
		  	theForm.sel_availableSegmentValues.options[theForm.sel_availableSegmentValues.selectedIndex].value;
          return;
         }

	 	window.parent.segmentSelection.location.href=
	 		theForm.sel_availableSegmentValues.options[theForm.sel_availableSegmentValues.selectedIndex].value;
     	window.parent.accountingCodeSelection.location.href=
	 		"<%=request.getContextPath()%>/jsp/gsa/common/attention_accountingCodeSegmentSelection.jsp";
     	return;
	 }
	 alert("There is nothing to select. Please check Browse Criteria.")
 	}
// Stop hiding -->
</SCRIPT>

</HEAD>

<!------------------------------------------------------------------------------>

<BODY
      onload="Initialize(document.frm_browseAccountingCodes)">
<jsp:useBean id="browseAccountingCodesDTO"
	class="com.boa.eagls.government.dto.browse.BrowseAccountingCodesDTO" scope="request" />

<FORM NAME="frm_browseAccountingCodes"
      METHOD="POST"
      TARGET="segmentSelection">

<!------------------------------------------------------------------------------>

<CENTER>
<FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Select A Valid Segment Value</B></FONT><BR>

<TABLE>
    <TR>
      <TD>
        <SELECT NAME="sel_availableSegmentValues" SIZE="5">
<%--		  <eagls:pagedList howMany='50' scope='request' errorUrl='<%=request.getContextPath()+"/jsp/errorTest.html"%>'>--%>
		  <eagls:pagedList howMany="50" scope="request" errorUrl="/jsp/errorTest.html">
		  	<eagls:previousOption value="previous" />
    	  	<eagls:pagedListBody id="accountingCodeSegment" name="searchResult" >
            	<OPTION VALUE="<%=request.getContextPath()%>/common/BrowseMasterAcctCodeAction.do<%=browseAccountingCodesDTO.getOptionHyperlink()%>&hdn_segmentValue=<bean:write name="accountingCodeSegment" property="value"/>">
					<bean:write name="accountingCodeSegment" property="value"/> &nbsp; <bean:write name="accountingCodeSegment" property="description"/>
				</OPTION>
    		</eagls:pagedListBody>
            <eagls:nextOption value="next" />
		  </eagls:pagedList>
        </SELECT>
      </TD>
    </TR>
</TABLE>


<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <!-- GX type=cell id=showPageNumbers>
          <GX type=replace id=currentPageNumber value=theCurrentPageNumber>
            <GX type=replace id=totalPageNumber value=theTotalPageNumber>
              <B>Page theCurrentPageNumber of theTotalPageNumber</B>
            </GX>
          </GX>
        </GX -->
      </TD>
    </TR>
</TABLE>


<!-- INSERT HIDDEN FIELDS -->

<INPUT TYPE="Hidden" NAME="hdn_accountingCode" VALUE="theAccountingCode">
<INPUT TYPE="Hidden" NAME="hdn_accountingCenterId"
	VALUE="<%=browseAccountingCodesDTO.getHdn_accountingCenterId()%>">
<INPUT TYPE="Hidden" NAME="hdn_segmentNumber"
	VALUE="<%=browseAccountingCodesDTO.getHdn_segmentNumber()%>">
<INPUT TYPE="Hidden" NAME="hdn_segmentName"
	VALUE="<%=browseAccountingCodesDTO.getHdn_segmentName()%>">
<INPUT TYPE="Hidden" NAME="hdn_valueSet"
	VALUE="<%=browseAccountingCodesDTO.getHdn_valueSet()%>">
<INPUT TYPE="Hidden" NAME="hdn_segNumSet"
	VALUE="<%=browseAccountingCodesDTO.getHdn_segNumSet()%>">
<INPUT TYPE="Hidden" NAME="hideButton"
	VALUE="<%=browseAccountingCodesDTO.getHideButton()%>">
<INPUT TYPE="Hidden" NAME="slashes"
	VALUE="<%=browseAccountingCodesDTO.getSlashes()%>">

<!------------------------------------------------------------------------------>

<INPUT TYPE="Button" NAME="but_submit" VALUE="Select"
       onClick="assignvalue(document.frm_browseAccountingCodes)"><BR><BR>

<!------------------------------------------------------------------------------>

</CENTER>
<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>

</FORM>
</BODY>
</html:html>