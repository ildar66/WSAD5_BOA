<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<center>
  <b><i>To perform partial name searches on non numeric fields, <BR>enter partial search criteria followed by an asterisk '*'.<BR>
  Ex: Entering 'Jo*' in the Last Name field<BR>will find last names such as 'Jones' and 'Johnson'.<BR>
  Note: Partial searches take longer to complete than exact searches.</i></b>
</center>
<br>
<TABLE>
    <TR>
      <TD WIDTH="575" ALIGN="Center">
        <INPUT TYPE="Submit" NAME="but_submit" VALUE="Submit">&nbsp;&nbsp;

        <INPUT TYPE="Reset" NAME="but_clear" VALUE="&nbsp;Clear&nbsp;" onClick="resetHierarchy( document.forms[0] );">
      </TD>
    </TR>
</TABLE>


<TABLE>


<BR><B><FONT COLOR="#0000FF" FACE="Arial, Helvetica">Search By</FONT></B>




    <TR>
      <TD WIDTH="20">
        <INPUT TYPE="Checkbox" name="chk_accountNumber" onclick="accountNumberSet(document.forms[0])"/>
      </TD>
      <TD WIDTH="175">Account Number</TD>
      <TD WIDTH="406">
        <html:text  property="txt_accountNumber" size="19" maxlength="16"
           onfocus="Display_Message(parent.logo.Status_Text_AccountNumber);
             document.forms[0].chk_accountNumber.checked=true"
           onblur="controlEmpty(document.forms[0].chk_accountNumber, document.forms[0].txt_accountNumber.value, '', '','')"/>
   </TD>
    </TR>

    <TR>
      <TD>
        <INPUT TYPE="Checkbox" NAME="chk_Name" onClick="lastNameSet(document.forms[0])">
      </TD>
      <TD WIDTH="175">Last Name</TD>
      <TD>
        <html:text property="txt_lastName" size="27" maxlength="26"
                 onfocus="Display_Message(parent.logo.Status_Text_LastName);
                       document.forms[0].chk_Name.checked=true"
	       onblur="controlEmpty(document.forms[0].chk_Name, document.forms[0].txt_lastName.value, document.forms[0].txt_firstName.value, document.forms[0].txt_zipCode?form.txt_zipCode.value:'','')"/>
      </TD>
    </TR>
    <tr>
    <td> </TD>
      <td COLSPAN=2>&nbsp;&nbsp;<I> Optional Criteria with Last Name:</I></td>

    </tr>
    <TR>
      <TD>&nbsp;</TD>
      <TD>&nbsp;&nbsp;&nbsp;First Name</TD>
      <TD>
        <html:text property="txt_firstName" size="27" maxlength="26"
          onfocus="Display_Message(parent.logo.Status_Text_FirstName);
                        document.forms[0].chk_Name.checked=true"
	       onblur="controlEmpty(document.forms[0].chk_Name, document.forms[0].txt_lastName.value, document.forms[0].txt_firstName.value, document.forms[0].txt_zipCode?document.forms[0].txt_zipCode.value:'','')"/>
      </TD>
    </TR>

<!-- Show Hierarchy -->

    <TR>
      <TD WIDTH=20>
	      <INPUT TYPE="checkbox" name="chk_hierLevel" VALUE="hierarchy"
             onClick="hierarchyLevelSet(document.forms[0])"; >
      </TD>
      <TD>Hierarchy Depth
      </TD>
      </TD>
      <TD>
      <html:select property="txt_hierarchyDepth" size="1" onfocus="document.forms[0].chk_hierLevel.checked=true;
                         parent.logo.Display_Message(parent.logo.Status_Text_HierarchyDepth)">

          <html:option value="0">Current Level</html:option>
          <html:option value="1">One Level</html:option>
          <html:option value="2">Two Levels</html:option>
          <html:option value="3">Three Levels</html:option>
          <html:option value="4">Four Levels</html:option>
          <html:option value="5">Five Levels</html:option>
          <html:option value="6">Six Levels</html:option>
          <html:option value="7">Seven Levels</html:option>
        </html:select>

       <INPUT TYPE="BUTTON" NAME="but_Hierarchy" VALUE="Browse Hierarchy"
          onclick="document.forms[0].chk_hierLevel.checked=true;
           createHierarchyString(document.forms[0],'<%=request.getContextPath()%>/browseHierarchy.do')" >

      </TD>
    </TR>
</TABLE>
<BR>



