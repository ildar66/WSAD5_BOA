<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<HEAD>

<TITLE></TITLE>

<META NAME="Author" CONTENT="?">
<META NAME="Description" CONTENT="?">
<META NAME="Revised" CONTENT="10/27/1998 Edited -BB">

</HEAD>
<SCRIPT LANGUAGE = "javascript">
<!-- Start hiding from other browsers

function assignValue(theForm)
  {
     var theUrl = "<%=request.getContextPath()%>/common/BrowseMasterAcctCodeAction.do";
     
         theUrl=theUrl+"?hdn_accountingCode=";
         theUrl=theUrl+escape(theForm.hdn_accountingCode.value);
	 	 theUrl=theUrl+"&hdn_accountingCenterId="+escape(theForm.hdn_accountingCenterId.value);
		 theUrl=theUrl+"&accountingCenterId="+escape(theForm.hdn_accountingCenterId.value);
         theUrl=theUrl+"&hdn_segmentNumber="+escape(theForm.hdn_segmentNumber.value);
         theUrl=theUrl+"&hdn_segmentName="+escape(theForm.hdn_segmentName.value);
         theUrl=theUrl+"&hdn_segmentValue="+escape(theForm.hdn_segmentValue.value);
     	 theUrl=theUrl+"&hdn_valueSet="+escape(theForm.hdn_valueSet.value);
         theUrl=theUrl+"&fromHyperLink="+"YES";
		 theUrl=theUrl+"&hideButton="+escape(theForm.hideButton.value);
         theUrl=theUrl+"&slashes="+escape(theForm.slashes.value);
    if (theForm.hdn_flag.value==1)
     {
       window.parent.segmentSelection.location.href=theUrl;
       window.parent.accountingCodeSelection.location.href=
	   	"<%=request.getContextPath()%>/jsp/gsa/common/attention_accountingCodeSegmentSelection.jsp";
       return;
     }
    else
     {
       window.parent.accountingCodeSelection.location.href=
	   	"<%=request.getContextPath()%>/jsp/gsa/common/attention_accountingCodeSegmentSelection.jspf";
       return;
     }
  }
// Stop hiding -->
</SCRIPT>
<!------------------------------------------------------------------------------>

<BODY >
<jsp:useBean id="browseAccountingCodesDTO" 
	class="com.boa.eagls.government.dto.browse.BrowseAccountingCodesDTO" scope="request" />

<FORM NAME="frm_segmentValueVerification"
      METHOD="POST"
      TARGET="segmentSelection">

<!------------------------------------------------------------------------------>

<CENTER>
<FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Segment Value Verification</B></FONT>
<BR><BR>
<GX type=replace id=segmentName value=theSegmentName>
  <GX type=replace id=hdn_displaySegmentNumber value=theSegmentNumber>
    <GX type=replace id=hdn_segmentValue value=theSegmentValue>
      <FONT FACE="Arial, Helvetica">
        Update Master Accounting Code with<BR>
        Segment&nbsp;<B><%=browseAccountingCodesDTO.getHdn_segmentNumber()%></B>&nbsp;(<B><%=browseAccountingCodesDTO.getHdn_segmentName()%></B>)&nbsp;=&nbsp;<B><%=browseAccountingCodesDTO.getHdn_segmentValue()%></B>?<BR>
      </FONT>
    </GX>
  </GX>
</GX>
<BR>

<!-- HIDDEN FIELDS -->

<GX type=replace id=hdn_accountingCode value=theAccountingCode>
  <INPUT TYPE="hidden" NAME="hdn_accountingCode" VALUE="theAccountingCode">
</GX>

<GX type=replace id=hdn_accountingCenterId value=theAcctCtrId>
  <INPUT TYPE="hidden" NAME="hdn_accountingCenterId" VALUE="<%=browseAccountingCodesDTO.getHdn_accountingCenterId()%>">
</GX>

<GX type=replace id=hdn_segmentNumber value=theNumber>
  <INPUT TYPE="hidden" NAME="hdn_segmentNumber" VALUE="<%=browseAccountingCodesDTO.getHdn_segmentNumber()%>">
</GX>

<GX type=replace id=hdn_segmentName value=theName>
  <INPUT TYPE="hidden" NAME="hdn_segmentName" VALUE="<%=browseAccountingCodesDTO.getHdn_segmentName()%>">
</GX>

<GX type=replace id=hdn_segmentValue value=theValue>
  <INPUT TYPE="hidden" NAME="hdn_segmentValue" VALUE="<%=browseAccountingCodesDTO.getHdn_segmentValue()%>">
</GX>

<GX type=replace id=hdn_valueSet value=x>
  <INPUT TYPE="Hidden" NAME="hdn_valueSet" VALUE="<%=browseAccountingCodesDTO.getHdn_valueSet()%>">
</GX>

<GX type=replace id=hideButton value=x>
  <INPUT TYPE="Hidden" NAME="hideButton" VALUE="<%=browseAccountingCodesDTO.getHideButton()%>">
</GX>

<GX type=replace id=slashes value=x>
  <INPUT TYPE="Hidden" NAME="slashes" VALUE="<%=browseAccountingCodesDTO.getSlashes()%>">
</GX>

  <INPUT TYPE="hidden" NAME="hdn_flag" VALUE=0>

<!------------------------------------------------------------------------------>

<TABLE>
    <TR>
      <TD>
        <INPUT TYPE="BUTTON" NAME="but_submit" VALUE="  OK  "
         onClick = "document.frm_segmentValueVerification.hdn_flag.value=1;
                    assignValue(document.frm_segmentValueVerification)">&nbsp;&nbsp;

        <INPUT TYPE="BUTTON" NAME="but_cancel" VALUE="Cancel"
         onClick = "document.frm_segmentValueVerification.hdn_flag.value=0;
                    assignValue(document.frm_segmentValueVerification)">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------------------------>

<BR>
<html:img src="/jsp/gsa/images/line.gif" width="575" height="6"/>
</CENTER>

</FORM>
</BODY>
</html:html>
