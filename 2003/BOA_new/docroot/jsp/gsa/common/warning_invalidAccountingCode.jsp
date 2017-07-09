<%@ page contentType="text/html; charset=windows-1252" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<HEAD>

<TITLE></TITLE>

<META NAME="Author" CONTENT="?">
<META NAME="Description" CONTENT="?">
<SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
</HEAD>

<BODY >

<FORM NAME="frm_invalidSegmentValue"
      METHOD="POST"
      TARGET="segmentSelection">

<CENTER>
<FONT COLOR="#0000FF" FACE="Arial, HELVETICA"><B>Invalid Segment Value</B></FONT>
<BR><BR>

<FONT FACE="Arial, HELVETICA">
<BR><B>
  You have entered an invalid segment value.<BR>
  Please reenter another value in the segment<BR>
  value field or browse for a valid segment value.<BR>
</B>
</FONT>
<BR>
<BR>
<BR>
<BR>
</CENTER>

<html:img src="/jsp/gsa/images/line.gif" width="575" height="6"/>

</FORM>
</BODY>
</html:html>
