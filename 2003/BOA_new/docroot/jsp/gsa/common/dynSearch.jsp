<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/boa/eagls" prefix="eagls" %>

<%! String varSearchType="";%>
<%
  varSearchType = (String) request.getAttribute(com.boa.eagls.government.util.Constants.SEARCH_PARAM_SEARCHTYPE);

  System.out.println("Search type "+varSearchType);
%>
<HTML>
    <HEAD>

        <TITLE>Search</TITLE>

        <META NAME="Name" 		 CONTENT="Search Screen">
        <META NAME="File Name"   CONTENT="search.html">
        <SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/setstyle.jsp"></SCRIPT>
        <SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/reviseSearch.js"></SCRIPT>
        <SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/hierarchyBrowse.js"></SCRIPT>
        <SCRIPT LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/formvalidate.js"></script>
        <SCRIPT language="JavaScript" SRC="<%=request.getContextPath()%>/jsp/gsa/scripts/dynSearch.js"></SCRIPT>
<SCRIPT language="JavaScript">
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}
    </script>
    </HEAD>

<!------------------------------------------------------------------->

    <BODY onLoad="resetIsLoaded();
            refreshHierarchy();
            callClearReviseHierarchy();
            populateReviseSearchHierarchy(document.forms[0]);">

        <!-- <GX TYPE="include" ID="/gsa/common/header_systemDefault.html">Insert System Default Header Here</GX> -->

            <%@ include file="/jsp/gsa/header_systemDefault.jsp" %>
            <html:form styleId="theForm" action="<%=varSearchType%>"  onsubmit="return Form_Test(document.forms[0]);"> <!-- onsubmit="return Form_Test(document.theForm);" -->
                <eagls:loadTemplate contextPath="<%= request.getContextPath()%>" filterName="searchType" filterValue="<%=varSearchType%>" xmlName="/dynSearch.xml" xsltName="/dynSearch.xsl"/>
                <TABLE CELLSPACING="0" CELLPADDING="0" BORDER="0">
                    <TR>
                      <%--  <eagls:loadTemplate contextPath="<%= request.getContextPath()%>" filterName="searchType" filterValue="<%=varSearchType%>" xmlName="/hierarchy.xml" xsltName="/hierarchy.xsl"/>
                      --%>
<%@ include file="/jsp/gsa/common/hierarchy.jsp"%>
                    </TR>
                </TABLE>
                <BR/>
                <TABLE>
                    <TR>
                      <TD WIDTH="575" ALIGN="Center">
                        <INPUT TYPE="Submit" NAME="but_submit" VALUE="Submit"/>&nbsp;&nbsp;

                        <INPUT TYPE="Reset" NAME="but_clear" VALUE="Clear" onClick="resetHierarchy( document.forms[0] );"/>
                      </TD>
                    </TR>
                </TABLE>
            </html:form>


            <IMG SRC="<%=request.getContextPath()%>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">


            <TABLE>
                <TR>
                  <TD WIDTH="575" ALIGN="Center">
                     <H6 CLASS="copyright">
                       Copyright &#169; 1999 Bank of America, NA (USA). All rights reserved.
                     </H6>
                  </TD>
                </TR>
            </TABLE>

    </BODY>
</HTML>
