<%@ taglib uri="/struts/struts-template" prefix="template" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>

<TABLE WIDTH="575">
    <TR>
      <!--TD ALIGN="Right"><IMG SRC="/gsa/images/eagle_logo.gif" WIDTH="82" HEIGHT="80"></TD-->
      <TD ALIGN="Center">
        <H2 class="titleText" >
          <template:get name='screenTitle-1'/><BR>
          <template:get name='screenTitle-2'/>
        </H2>
      </TD>
    </TR>
</TABLE>

<INPUT TYPE="HIDDEN" NAME="txt_screenTitle1" value="<template:get name='screenTitle-1'/>">

<html:img page="/jsp/gsa/images/line.gif" width="575" height="6"/>
<BR>

