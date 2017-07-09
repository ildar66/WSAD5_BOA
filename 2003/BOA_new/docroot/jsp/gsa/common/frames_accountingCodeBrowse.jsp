<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ page import="com.boa.eagls.government.constants.web.CentralAccount" %>

<html:html>
<HEAD>
<TITLE>Accounting Code Selection</TITLE>

<META NAME="Name" CONTENT="Frames Generation Document">
<META NAME="Description" CONTENT="Controls the placement of the logo, menu and content areas">
<META NAME="Author" CONTENT="Brent Ramsby">
<META NAME="Created" CONTENT="06/24/1998">
<META NAME="Revised" CONTENT="10/26/1998 Edited -BB">

</HEAD>
<jsp:useBean id="browseMasterAcctCodeFrameDTO" 
	class="com.boa.eagls.government.dto.browse.BrowseMasterAcctCodeFrameDTO" scope="request" />

<FRAMESET ROWS="270,50,200" FRAMEBORDER="0">
   noSlashes
    <FRAME SRC="<%=request.getContextPath()%>/common/BrowseMasterAcctCodeAction.do?accountingCenterId=<%=browseMasterAcctCodeFrameDTO.getAccountingCenterId()%>&masterAccountingCode=<%=browseMasterAcctCodeFrameDTO.getMasterAccountingCode()%>&hideButton=<%=browseMasterAcctCodeFrameDTO.getHideFavoriteButton()%>&slashes=<%=browseMasterAcctCodeFrameDTO.getSlashes()%>"
           NAME="segmentSelection" SCROLLING=NO >
    <FRAME SRC="<%=request.getContextPath()%>/jsp/gsa/common/object_screenBreak.jsp"
        NAME="screenBreak" SCROLLING=NO>
    <FRAME SRC="<%=request.getContextPath()%>/jsp/gsa/common/attention_accountingCodeSegmentSelection.jsp"
        NAME="accountingCodeSelection" SCROLLING=NO>
</FRAMESET>

<BODY></BODY>

</html:html>
