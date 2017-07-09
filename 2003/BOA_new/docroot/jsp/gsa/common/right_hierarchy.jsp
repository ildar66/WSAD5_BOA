<%@ taglib uri="/struts/struts-logic" prefix="logic" %>
<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<%@ page import="com.boa.eagls.government.dto.HierarchyDTO,
                 com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields"%>
<head><script src="<%=request.getContextPath()%>/jsp/gsa/scripts/formvalidate.js"></script>
<SCRIPT src="<%=request.getContextPath()%>/jsp/gsa/scripts/hierarchyBrowse.js"></SCRIPT> </head>
<%
    BrowseHierarchyFields browseHierarchy = (BrowseHierarchyFields)session.getAttribute("browseHierarchyFields");
    pageContext.setAttribute("browseHierarchy", browseHierarchy);
%>
<!-- GCSU Users Only -->
<logic:match name="browseHierarchy" property="GCSU" value="true" >
    <TABLE>
    <TR>
      <TD WIDTH="130">Program Number
      </TD>
      <TD>
<%--                    <GX  type=cell ID=EDIT.HL0.NUMBER>--%>
<%--              <GX  type=replace ID=HL0.NUMBER value=hl0number>--%>
        <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[0]" >
                <INPUT TYPE="TEXT"  NAME="txt_hl0" MAXLENGTH="7" SIZE="7" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[0]"/>"
					onfocus="if(form.chk_hierLevel) form.chk_hierLevel.checked=true;
					    if(form.rag_accountInquirySearch) hierarchyLevelSetRadioGroup(form); //for queued requests rag;
					    hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_ProgramNumber)"
                    onblur="if(form.chk_hierLevel){ controlEmpty(form.chk_hierLevel, form.txt_hl0.value, '', '', '');
          if(form.chk_hierLevel.checked == false) hierarchyLevelSet(document.theForm);}" >
        </logic:notPresent>
<%--			 </GX>--%>
<%--            </GX>--%>
        <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[0]" >
<%--          <GX  type=cell ID=YESEDIT.HL0.NUMBER>--%>
<%--            <GX type=cell id=HL0.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL0.NUMBER value=hl0number>--%>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <bean:write name="browseHierarchy" property="HL_NUMBER[0]"/>
                <INPUT TYPE="HIDDEN"  NAME="txt_hl0" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[0]"/>">
<%--            </GX>--%>
<%--          </GX>--%>
        </logic:notPresent>
       </TD>
       <TD ALIGN="CENTER">
       <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[0]" >
<%--         <GX  type=cell ID=EDIT.HL0.DESCRIPTION>--%>
            <INPUT TYPE="HIDDEN" VALUE="">
<%--            </GX>--%>
       </logic:notPresent>
            <bean:write name="browseHierarchy" property="HL_DESCRIPTION[0]"/>
<%--          <GX type=cell id=HL0.DESCRIPTION></GX>--%>
          &#160
       </TD>
    </TR>
</TABLE>
</logic:match>
<%--</GX>--%>
<!-- End GCSU Only -->
<!-- Non GCSU -->
<logic:notMatch name="browseHierarchy" property="GCSU" value="true" >
<INPUT TYPE="HIDDEN"  NAME="txt_hl0" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[0]"/>">
<%--<GX type=cell id=NONGCSU>--%>
<%--  <GX type=replace id=HL0.NUMBER value=hl0number>--%>
<%--    <INPUT TYPE="HIDDEN"  NAME="txt_hl0" VALUE="hl0number">--%>
<%--  </GX>--%>
<%----%>
<%--</GX>--%>
</logic:notMatch>
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
<%--          <GX  type=cell ID=EDIT.HL1.NUMBER>--%>
<%--            <GX  type=replace ID=HL1.NUMBER value=hl1number>--%>
    <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[1]" >
 			    <CENTER>
			      <INPUT TYPE="TEXT" NAME="txt_hl1" MAXLENGTH="7" SIZE="7"
                    VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[1]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;
                  hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onChange="hierarchy_parent_logo.padLeft(txt_hl1, '0', 7);clearHierDesc(form, 1);" >
			  	</CENTER>
    </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>
<%--          <GX  type=cell ID=YESEDIT.HL1.NUMBER>--%>
<%--             <GX type=cell id=HL1.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL1.NUMBER value=hl1number>--%>
    <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[1]" >
        <bean:write name="browseHierarchy" property="HL_NUMBER[1]"/>
        <INPUT TYPE="HIDDEN"  NAME="txt_hl1" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[1]"/>"><!--DO NOT DELETE HARD SPACE-->&#160
    </logic:notPresent>
<%--            </GX>--%>
<%--          </GX>--%>
        </TD>

        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL1.DESCRIPTION>--%>
        <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[1]" >
            <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="NAVY">
        </logic:notPresent>
<%--          </GX>--%>
  <!--        <GX type=cell id=HL1.DESCRIPTION></GX>   -->
            <div ID="hl1Desc" class="hLDisplay"  >
<%--            <GX type="replace" id="HL1.DESCRIPTION" value="HL1.DESCRIPTION">--%>
                <bean:write name="browseHierarchy" property="HL_DESCRIPTION[1]"/>
<%--            HL1.DESCRIPTION--%>
<%--            </GX>--%>
            <!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </TD>
      </TR>

      <TR>
        <TD ALIGN="CENTER">HL2</TD>
        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL2.NUMBER>--%>
<%--            <GX  type=replace ID=HL2.NUMBER value=hl2number>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[2]" >
			   <CENTER>
			     <INPUT TYPE="TEXT" NAME="txt_hl2" MAXLENGTH="7" SIZE="7" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[2]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onchange="hierarchy_parent_logo.padLeft(txt_hl2, '0', 7);clearHierDesc(form, 2);" >
			   </CENTER>
            </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>
<%--          <GX  type=cell ID=YESEDIT.HL2.NUMBER>--%>
<%--             <GX type=cell id=HL2.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL2.NUMBER value=hl2number>--%>
            <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[2]" >
                <bean:write name="browseHierarchy" property="HL_NUMBER[2]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <INPUT TYPE="HIDDEN" NAME="txt_hl2" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[2]"/>">
            </logic:notPresent>
<%--            </GX>--%>
<%--          </GX>--%>
        </TD>

        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL2.DESCRIPTION>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[2]" >
              <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[2]"/>">
            </logic:notPresent>
<%--          </GX>--%>
          <div ID="hl2Desc" class="hLDisplay" >
            <bean:write name="browseHierarchy" property="HL_DESCRIPTION[2]"/>
<%--          <GX type=cell id=HL2.DESCRIPTION></GX>--%>
          <!--DO NOT DELETE HARD SPACE-->&#160  </div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL3</TD>
        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL3.NUMBER>--%>
<%--            <GX  type=replace ID=HL3.NUMBER value=hl3number>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[3]" >
			   <CENTER>
			    <INPUT TYPE="TEXT" NAME="txt_hl3" MAXLENGTH="7" SIZE="7" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[3]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onchange="hierarchy_parent_logo.padLeft(txt_hl3, '0', 7);clearHierDesc(form, 3);" >
			   </CENTER>
            </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>
<%--          <GX  type=cell ID=YESEDIT.HL3.NUMBER>--%>
<%--		    <GX type=cell id=HL3.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL3.NUMBER value=hl3number>--%>
            <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[3]" >
                <bean:write name="browseHierarchy" property="HL_NUMBER[3]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <INPUT TYPE="HIDDEN"  NAME="txt_hl3" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[3]"/>">
            </logic:notPresent>
<%--            </GX>--%>
<%--          </GX>--%>
        </TD>

        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL3.DESCRIPTION>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[3]" >
              <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="SOME NAVY DIVISION">
            </logic:notPresent>
<%--          </GX>--%>
          <div ID="hl3Desc" class="hLDisplay" >
            <bean:write name="browseHierarchy" property="HL_DESCRIPTION[3]"/>
<%--          <GX type=cell id=HL3.DESCRIPTION></GX>--%>
          <!--DO NOT DELETE HARD SPACE-->&#160
          </div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL4</TD>
        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL4.NUMBER>--%>
<%--            <GX  type=replace ID=HL4.NUMBER value=hl4number>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[4]" >
			   <CENTER>
			      <INPUT TYPE="TEXT" NAME="txt_hl4" MAXLENGTH="7" SIZE="7"VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[4]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onchange="hierarchy_parent_logo.padLeft(txt_hl4, '0', 7);clearHierDesc(form, 4);" >
			   </CENTER>
            </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>
<%--          <GX  type=cell ID=YESEDIT.HL4.NUMBER>--%>
<%--		    <GX type=cell id=HL4.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL4.NUMBER value=hl4number>--%>
            <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[4]" >
                <bean:write name="browseHierarchy" property="HL_NUMBER[4]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <INPUT TYPE="HIDDEN"  NAME="txt_hl4" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[4]"/>">
            </logic:notPresent>
<%--            </GX>--%>
<%--          </GX>--%>
        </TD>

        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL4.DESCRIPTION>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[4]" >
              <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="SOME NAVY DIVISION">
            </logic:notPresent>
<%--          </GX>--%>
          <div ID="hl4Desc" class="hLDisplay" >
<%--            <GX type=cell id=HL4.DESCRIPTION></GX>--%>
            <bean:write name="browseHierarchy" property="HL_DESCRIPTION[4]"/>
            <!--DO NOT DELETE HARD SPACE-->&#160
          </div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL5</TD>
        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL5.NUMBER>--%>
<%--            <GX  type=replace ID=HL5.NUMBER value=hl5number>--%>
        <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[5]" >
			 <CENTER>
			    <INPUT TYPE="TEXT" NAME="txt_hl5" MAXLENGTH="7" SIZE="7" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[5]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onchange="hierarchy_parent_logo.padLeft(txt_hl5, '0', 7);clearHierDesc(form, 5);">
			 </CENTER>
        </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>
<%--          <GX  type=cell ID=YESEDIT.HL5.NUMBER>--%>
<%--		    <GX type=cell id=HL5.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL5.NUMBER value=hl5number>--%>
        <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[5]" >
                <bean:write name="browseHierarchy" property="HL_NUMBER[5]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <INPUT TYPE="HIDDEN"  NAME="txt_hl5" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[5]"/>">
        </logic:notPresent>
<%--            </GX>--%>
<%--          </GX>--%>
        </TD>

        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL5.DESCRIPTION>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[5]" >
              <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="SOME NAVY DIVISION">
            </logic:notPresent>
<%--          </GX>--%>
          <div ID="hl5Desc" class="hLDisplay" >
<%--          <GX type=cell id=HL5.DESCRIPTION></GX>--%>
          <bean:write name="browseHierarchy" property="HL_DESCRIPTION[5]"/>
          <!--DO NOT DELETE HARD SPACE-->&#160
          </div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL6</TD>
        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL6.NUMBER>--%>
<%--            <GX  type=replace ID=HL6.NUMBER value=hl6number>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[6]" >
			  <CENTER>
			     <INPUT TYPE="TEXT" NAME="txt_hl6" MAXLENGTH="7" SIZE="7" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[6]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onchange="hierarchy_parent_logo.padLeft(txt_hl6, '0', 7);clearHierDesc(form, 6);" >
			  </CENTER>
            </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>
<%--          <GX  type=cell ID=YESEDIT.HL6.NUMBER>--%>
<%--		    <GX type=cell id=HL6.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL6.NUMBER value=hl6number>--%>
            <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[6]" >
                <bean:write name="browseHierarchy" property="HL_NUMBER[6]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <INPUT TYPE="HIDDEN"  NAME="txt_hl6" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[6]"/>">
            </logic:notPresent>
<%--            </GX>--%>
<%--          </GX>--%>
        </TD>

        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL6.DESCRIPTION>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[6]" >
              <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="SOME NAVY DIVISION">
            </logic:notPresent>
<%--          </GX>--%>
          <div ID="hl6Desc" class="hLDisplay" >
            <bean:write name="browseHierarchy" property="HL_DESCRIPTION[6]"/>
<%--            <GX type=cell id=HL6.DESCRIPTION></GX>--%>
            <!--DO NOT DELETE HARD SPACE-->&#160
          </div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL7</TD>
        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL7.NUMBER>--%>
<%--            <GX  type=replace ID=HL7.NUMBER value=hl7number >--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[7]" >
			   <CENTER>
			     <INPUT TYPE="TEXT" NAME="txt_hl7" MAXLENGTH="7" SIZE="7"VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[7]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onchange="hierarchy_parent_logo.padLeft(txt_hl7, '0', 7);clearHierDesc(form, 7);" >
			   </CENTER>
            </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>
<%--          <GX  type=cell ID=YESEDIT.HL7.NUMBER>--%>
<%--		  	<GX type=cell id=HL7.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL7.NUMBER value=hl7number>--%>
            <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[7]" >
                <bean:write name="browseHierarchy" property="HL_NUMBER[7]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <INPUT TYPE="HIDDEN"  NAME="txt_hl7" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[7]"/>">
            </logic:notPresent>
<%--            </GX>--%>
          </GX>
        </TD>

        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL7.DESCRIPTION>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[7]" >
              <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="SOME NAVY DIVISION">
            </logic:notPresent>
<%--          </GX>--%>
            <div ID="hl7Desc" class="hLDisplay" >
                <bean:write name="browseHierarchy" property="HL_DESCRIPTION[7]"/>
<%--                <GX type=cell id=HL7.DESCRIPTION></GX>--%>
            <!--DO NOT DELETE HARD SPACE-->&#160</div>
        </TD>
      </TR>
      <TR>
        <TD ALIGN="CENTER">HL8</TD>
        <TD ALIGN="CENTER">
<%--          <GX  type=cell ID=EDIT.HL8.NUMBER>--%>
<%--            <GX  type=replace ID=HL8.NUMBER value=hl8number>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_NUMBER[8]" >
			   <CENTER>
			       <INPUT TYPE="TEXT" NAME="txt_hl8" MAXLENGTH="7" SIZE="7"VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[8]"/>"
			      onfocus="if (form.chk_hierLevel) form.chk_hierLevel.checked=true;hierarchy_parent_logo.Display_Message(hierarchy_parent_logo.Status_Text_HierarchyLevel)"
                  onChange="hierarchy_parent_logo.padLeft(txt_hl8, '0', 7);clearHierDesc(form, 8);" >
			   </CENTER>
            </logic:notPresent>
<%--			 </GX>--%>
<%--          </GX>--%>

            <logic:notPresent name="browseHierarchy" property="YESEDIT_HL_NUMBER[8]" >
<%--          <GX  type=cell ID=YESEDIT.HL8.NUMBER>--%>
<%--		  	<GX type=cell id=HL8.NUMBER></GX>--%>
<%--   			<GX  type=replace ID=HL8.NUMBER value=hl8number>--%>
                <bean:write name="browseHierarchy" property="HL_NUMBER[8]"/>
                <!--DO NOT DELETE HARD SPACE-->&#160
                <INPUT TYPE="HIDDEN"  NAME="txt_hl8" VALUE="<bean:write name="browseHierarchy" property="HL_NUMBER[8]"/>">
            </logic:notPresent>
<%--            </GX>--%>
<%--          </GX>--%>
        </TD>

        <TD ALIGN="CENTER" nowrap>
<%--          <GX  type=cell ID=EDIT.HL8.DESCRIPTION>--%>
            <logic:notPresent name="browseHierarchy" property="EDIT_HL_DESCRIPTION[8]" >
              <INPUT TYPE="TEXT" MAXLENGTH="40" SIZE="44" VALUE="1234567890123456789012345678901234567890">
            </logic:notPresent>
<%--          </GX>--%>
            <div ID="hl8Desc" class="hLDisplay" >
            <bean:write name="browseHierarchy" property="HL_DESCRIPTION[8]"/>
<%--                <GX type=cell id=HL8.DESCRIPTION></GX>--%>
                <!--DO NOT DELETE HARD SPACE-->&nbsp;
            </div>
        </TD>
      </TR>
    </TABLE>
<!--jkt Descriptions passed here-->
<%--    <gx type=replace id=HL0.DESCRIPTION value=HL0.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl0Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[0]"/>">
<%--         </gx>--%>

<%--    <gx type=replace id=HL1.DESCRIPTION value=HL1.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl1Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[1]"/>">
<%--    </gx>--%>

<%--	 <gx type=replace id=HL2.DESCRIPTION value=HL2.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl2Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[2]"/>">
<%--         </gx>--%>

<%--	 <gx type=replace id=HL3.DESCRIPTION value=HL3.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl3Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[3]"/>">
<%--         </gx>--%>

<%--	 <gx type=replace id=HL4.DESCRIPTION value=HL4.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl4Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[4]"/>">
<%--         </gx>--%>

<%--	 <gx type=replace id=HL5.DESCRIPTION value=HL5.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl5Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[5]"/>">
<%--         </gx>--%>

<%--	 <gx type=replace id=HL6.DESCRIPTION value=HL6.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl6Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[6]"/>">
<%--         </gx>--%>

<%--	 <gx type=replace id=HL7.DESCRIPTION value=HL7.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl7Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[7]"/>">
<%--         </gx>--%>

<%--	 <gx type=replace id=HL8.DESCRIPTION value=HL8.DESCRIPTION>--%>
	 	<!--DO NOT DELETE HARD SPACE-->&#160
    <INPUT TYPE="HIDDEN" NAME="txt_hl8Desc" value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[8]"/>">
<%--         </gx>--%>

    <INPUT name="hdn_delimitiedHierarchy"type=HIDDEN
    value="<bean:write name="browseHierarchy" property="HL_DESCRIPTION[1]"/>^<bean:write name="browseHierarchy" property="HL_DESCRIPTION[2]"/>^<bean:write name="browseHierarchy" property="HL_DESCRIPTION[3]"/>^<bean:write name="browseHierarchy" property="HL_DESCRIPTION[4]"/>^<bean:write name="browseHierarchy" property="HL_DESCRIPTION[5]"/>^<bean:write name="browseHierarchy" property="HL_DESCRIPTION[6]"/>^<bean:write name="browseHierarchy" property="HL_DESCRIPTION[7]"/>^<bean:write name="browseHierarchy" property="HL_DESCRIPTION[8]"/>">
<!--- END DESCRIPTIONS------------------------------->

