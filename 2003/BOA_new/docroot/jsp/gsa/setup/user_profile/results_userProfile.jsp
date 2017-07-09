
<TABLE>
    <TR>
      <TD><FONT COLOR="#0000FF" FACE="Arial, Helvetica"><B>Search Criteria</B></TD>
      <TD COLSPAN="2">&nbsp;</TD>
    </TR>

      <TR>
        <TD><B><%= request.getAttribute("searchCriteria") %>:</B></TD>
        <TD><%= request.getAttribute("searchCriteriaValue") %></TD>
      </TR>
</FONT>
</TABLE>
<BR>

<TABLE BORDER=1>
    <TR>
      <TH  ALIGN="Left">
        Name
      </TH>
      <TH  ALIGN="Left">
        User ID
      </TH>
      <TH  ALIGN="Left">
        User Status
      </TH>
      <TH  ALIGN="Left">
        Role
      </TH>
      <TH  ALIGN="Left">
        Account Number/Hierarchy Level
      </TH>
    </TR>

   <logic:iterate name="searchResult" id="user">
   <TR>
      <TD>
       <bean:write name="user" property="firstName"/> <bean:write name="user" property="lastName"/>&nbsp;
      </TD>
      <TD>
       <bean:write name="user" property="userId"/>&nbsp;
      </TD>
      <TD>
       <bean:write name="user" property="status"/>&nbsp;
      </TD>
      <TD>
       <bean:write name="user" property="role"/>&nbsp;
      </TD>
      <TD>
       <logic:notEqual name="user" property="accountNumber" value="">
          <bean:write name="user" property="accountNumber"/>&nbsp;
       </logic:notEqual>
       <logic:notEqual name="user" property="hierarchy" value="">
          <bean:write name="user" property="hierarchy"/>&nbsp;
       </logic:notEqual>
       <logic:equal name="user" property="accountNumber" value="">
           <logic:equal name="user" property="hierarchy" value="">
                &nbsp;
            </logic:equal>
       </logic:equal>

      </TD>
    </TR>
    </logic:iterate>
</TABLE>

<!------------------------------------------------------------->

<BR>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="submit" NAME="but_submitButton" VALUE="New Profile">&nbsp;&nbsp;
        <INPUT TYPE="BUTTON" NAME="but_Revise" VALUE="Revise Search"
          onClick = "goBack();">
      </TD>
    </TR>
</TABLE>

<!------------------------------------------------------------->

<input type="hidden" name="firstName" value='<%= request.getAttribute("firstName") %>'>
<input type="hidden" name="lastName" value='<%= request.getAttribute("lastName") %>'>


