<TABLE WIDTH="575">
    <TR>
      <TD ALIGN="Center">
<%
System.out.println("Heading "+(String) request.getAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING));
%>
        <H2 class="titleText"> <font face="Arial, HELVETICA" color="Blue">
          <bean:message key="<%=(String) request.getAttribute(com.boa.eagls.government.util.Constants.HEADER_HEADING) %>" /><br>
          <bean:message key="<%= (String)request.getAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING)%>" />

	   </font>
        </H2>
      </TD>
    </TR>
</TABLE>
<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="<%= request.getAttribute("header1") %>">
<IMG SRC="<%= request.getContextPath() %>/jsp/gsa/images/line.gif" WIDTH="575" HEIGHT="6">