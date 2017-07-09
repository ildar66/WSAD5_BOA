<!-- GCSU Users Only -->
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ taglib uri="/struts/struts-logic" prefix="logic" %>



<%@ page import = "com.boa.eagls.government.statemgmt.EAGLSSession"%>
<%@ page import = "com.boa.eagls.government.dto.HierarchyDTO"%>
<% EAGLSSession ses = new EAGLSSession();
    HierarchyDTO[] hys = ses.getCurrentHierarchy(request);
    if (hys==null) {
        out.write("getting another hierarchy...");
        hys = (HierarchyDTO[])session.getAttribute("hierarchy");
            out.write(hys.toString());
    }
//    out.write(hys.toString());
String hierValue = "";
    int number = 0;
    if(hys!=null)
       number = hys[0].getNumber();
if(number == 0)
{
%>
               dfgsdfgsdf
    <TABLE>
    <TR>
      <TD WIDTH="130">Program Number
      </TD>
      <TD>

                <INPUT TYPE="TEXT"  NAME="txt_hl0" MAXLENGTH="7" SIZE="7" value=""	OnFocus="if(document.forms[0].chk_hierLevel) document.forms[0].chk_hierLevel.checked=true;
					if(document.forms[0].rag_accountInquirySearch) hierarchyLevelSetRadioGroup(document.forms[0]); //for queued requests rag;
					hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_ProgramNumber)"
          onBlur="if(document.forms[0].chk_hierLevel){ controlEmpty(document.forms[0].chk_hierLevel, document.forms[0].txt_hl0.value, '', '', '');
          if(document.forms[0].chk_hierLevel.checked == false) hierarchyLevelSet(document.forms[0]);}" >




       </TD>
       <TD ALIGN="CENTER">

          &#160
          &#160
       </TD>
    </TR>
</TABLE>
<% }
else {
hierValue = hys[0].getNumberAsString(); %>
                <INPUT TYPE="hidden"  NAME="txt_hl0" MAXLENGTH="7" SIZE="7" value="<%=hierValue%>">
<% } %>
<!-- End GCSU Only -->
<!-- Non GCSU -->

<!-- End Non GCSU -->

<BR>
<!--TABLE BORDER="1" WIDTH="575" CELLSPACING="0" CELLPADDING="0"-->
    <TABLE BORDER="1" WIDTH="575">
      <TR>
        <TH>
          Level
        </TH>
        <TH WIDTH="100" ALIGN="CENTER">
          Number
        </TH>
        <TH ALIGN="CENTER">
          Description/Name
        </TH>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL1</TD>
        <TD ALIGN="CENTER">

       <%   if(hys!=null)
               number = hys[1].getNumber();
           if(number != 0){
              hierValue = hys[1].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl1" VALUE=<%=hierValue%>>
      <% } else{%>


			    <CENTER>
			      <INPUT TYPE="TEXT" NAME="txt_hl1" MAXLENGTH="7" SIZE="7" VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)" onChange="hierarchy_parent_logo.padLeft(txt_hl1, '0', 7);clearHierDesc(form, 1);" >
			  	</CENTER>

<%}%>

        </TD>

        <TD>
      <%
           String desc1 = "";
           if(hys!=null){
               desc1 = hys[1].getDescription();
//             System.out.println("Description of level1  "+desc1);
           }
           if(desc1 == null){
              desc1 = "&#160";
           }
      %>

          <div ID="hl1Desc" class="hLDisplay" > <%=desc1%><!--DO NOT DELETE HARD SPACE-->&#160  </div>
       </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL2</TD>
        <TD ALIGN="CENTER">
      <%   if(hys!=null)
               number = hys[2].getNumber();
           if(number != 0){
              hierValue = hys[2].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl2" VALUE=<%=hierValue%>>
      <% } else{%>


			   <CENTER>
			     <INPUT TYPE="TEXT" NAME="txt_hl2" MAXLENGTH="7" SIZE="7"VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"  onChange="hierarchy_parent_logo.padLeft(txt_hl2, '0', 7);clearHierDesc(form, 2);" >
			   </CENTER>
        <%}%>


        </TD>

        <TD ALIGN="CENTER">
      <%
           String desc2 = "";
           if(hys!=null){
               desc2 = hys[2].getDescription();
 //            System.out.println("Description of level1  "+desc2);
           }
           if(desc2 == null){
              desc2 = "&#160";
           }
      %>
          <div ID="hl2Desc" class="hLDisplay" ><%=desc2%><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL3</TD>
        <TD ALIGN="CENTER">

      <%   if(hys!=null)
               number = hys[3].getNumber();
           if(number != 0){
              hierValue = hys[3].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl3" VALUE=<%=hierValue%>>
      <% } else{%>

			   <CENTER>
			    <INPUT TYPE="TEXT" NAME="txt_hl3" MAXLENGTH="7" SIZE="7"VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"  onChange="hierarchy_parent_logo.padLeft(txt_hl3, '0', 7);clearHierDesc(form, 3);" >
			   </CENTER>

 <%}%>

        </TD>

        <TD ALIGN="CENTER">
      <%
           String desc3 = "";
           if(hys!=null){
               desc3 = hys[3].getDescription();
 //            System.out.println("Description of level1  "+desc3);
           }
           if(desc3 == null){
              desc3 = "&#160";
           }
      %>

          <div ID="hl3Desc" class="hLDisplay" ><%=desc3%><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </TD>
      </TR>
     <TR>
        <TD ALIGN="CENTER">HL4</TD>
        <TD ALIGN="CENTER">

      <%   if(hys!=null)
               number = hys[4].getNumber();
           if(number != 0){
              hierValue = hys[4].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl4" VALUE=<%=hierValue%>>
      <% } else{%>

			   <CENTER>
			      <INPUT TYPE="TEXT" NAME="txt_hl4" MAXLENGTH="7" SIZE="7"VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"  onChange="hierarchy_parent_logo.padLeft(txt_hl4, '0', 7);clearHierDesc(form, 4);" >
			   </CENTER>

 <%}%>

        </TD>

        <TD ALIGN="CENTER">
      <%
           String desc4 = "";
           if(hys!=null){
               desc4 = hys[4].getDescription();
//             System.out.println("Description of level1  "+desc);
           }
           if(desc4 == null){
              desc4 = "&#160";
           }
      %>

          <div ID="hl4Desc" class="hLDisplay" >&#160<!--DO NOT DELETE HARD SPACE-->&#160</div>
        </TD>
      </TR>
     <TR>
        <TD ALIGN="CENTER">HL5</TD>
        <TD ALIGN="CENTER">

      <%   if(hys!=null)
               number = hys[5].getNumber();
           if(number != 0){
              hierValue = hys[5].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl5" VALUE=<%=hierValue%>>
      <% } else{%>

			 <CENTER>
			    <INPUT TYPE="TEXT" NAME="txt_hl5" MAXLENGTH="7" SIZE="7" VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"  onChange="hierarchy_parent_logo.padLeft(txt_hl5, '0', 7);clearHierDesc(form, 5);" >
			 </CENTER>

<%}%>

        </TD>

        <TD ALIGN="CENTER">
      <%
           String desc5 = "";
           if(hys!=null){
               desc5 = hys[5].getDescription();
 //            System.out.println("Description of level1  "+desc5);
           }
           if(desc5 == null){
              desc5 = "&#160";
           }
      %>

          <div ID="hl5Desc" class="hLDisplay" ><%=desc5%><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL6</TD>
        <TD ALIGN="CENTER">

      <%   if(hys!=null)
               number = hys[6].getNumber();
           if(number != 0){
              hierValue = hys[6].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl6" VALUE=<%=hierValue%>>
      <% } else{%>

			  <CENTER>
			     <INPUT TYPE="TEXT" NAME="txt_hl6" MAXLENGTH="7" SIZE="7" VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"  onChange="hierarchy_parent_logo.padLeft(txt_hl6, '0', 7);clearHierDesc(form, 6);" >
			  </CENTER>
     <%}%>


        </TD>

        <TD ALIGN="CENTER">
      <%
           String desc6 = "";
           if(hys!=null){
               desc6 = hys[6].getDescription();
 //            System.out.println("Description of level1  "+desc);
           }
           if(desc6 == null){
              desc6 = "&#160";
           }
      %>

          <div ID="hl6Desc" class="hLDisplay" ><%=desc6%><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </TD>
      </TR>
     <TR>
        <TD ALIGN="CENTER">HL7</TD>
        <TD ALIGN="CENTER">

      <%   if(hys!=null)
               number = hys[7].getNumber();
           if(number != 0){
              hierValue = hys[7].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl7" VALUE=<%=hierValue%>>
      <% } else{%>

			   <CENTER>
			     <INPUT TYPE="TEXT" NAME="txt_hl7" MAXLENGTH="7" SIZE="7"VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"  onChange="hierarchy_parent_logo.padLeft(txt_hl7, '0', 7);clearHierDesc(form, 7);" >
			   </CENTER>

 <%}%>

        </TD>

        <TD ALIGN="CENTER">
      <%
           String desc7 = "";
           if(hys!=null){
               desc7 = hys[7].getDescription();
 //            System.out.println("Description of level1  "+desc);
           }
           if(desc7 == null){
              desc7 = "&#160";
           }
      %>

            <div ID="hl7Desc" class="hLDisplay" ><%=desc7%>&#160<!--DO NOT DELETE HARD SPACE-->&#160</div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL8</TD>
        <TD ALIGN="CENTER">

      <%   if(hys!=null)
               number = hys[8].getNumber();
           if(number != 0){
              hierValue = hys[8].getNumberAsString();
      %>
         <%=hierValue %>

                <INPUT TYPE="HIDDEN"  NAME="txt_hl8" VALUE=<%=hierValue%>>
      <% } else{%>

			   <CENTER>
			       <INPUT TYPE="TEXT" NAME="txt_hl8" MAXLENGTH="7" SIZE="7"VALUE=""
			       OnFocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"  onChange="hierarchy_parent_logo.padLeft(txt_hl8, '0', 7);clearHierDesc(form, 8);" >
			   </CENTER>

    <%}%>

        </TD>

        <TD ALIGN="CENTER">
      <%
           String desc8 = "";
           if(hys!=null){
               desc8 = hys[8].getDescription();
 //            System.out.println("Description of level1  "+desc);
           }
           if(desc8 == null){
              desc8 = "&#160";
           }
      %>

            <div ID="hl8Desc" class="hLDisplay" ><%=desc8%><!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </TD>
      </TR>
    </TABLE>
<!--jkt Descriptions passed here-->

      <%
           String desc = "";
           if(hys!=null){
               desc = hys[0].getDescription();
  //           System.out.println("Description of level1  "+desc);
           }
           if(desc == null){
              desc = "&#160";
           }
      %>
	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl0Desc" value="<%=desc%>">

	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl1Desc" value="<%=desc1%>">

	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl2Desc" value="<%=desc2%>">

 	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl3Desc" value="<%=desc3%>">

 	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl4Desc" value="<%=desc4%>">

	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl5Desc" value="<%=desc5%>">

 	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl6Desc" value="<%=desc6%>">

	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl7Desc" value="<%=desc7%>">

	 	<!--DO NOT DELETE HARD SPACE-->&#160  <INPUT TYPE="HIDDEN" NAME="txt_hl8Desc" value="<%=desc8%>">

		<INPUT name="hdn_delimitiedHierarchy"type=HIDDEN value="&#160^&#160^&#160^&#160^&#160^&#160^&#160^&#160">
<!--- END DESCRIPTIONS------------------------------->




    <!-- End Show Hierarchy -->

<!-------------------------------------------------------------------------->