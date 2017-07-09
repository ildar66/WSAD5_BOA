<%@ page contentType="text/html; charset=windows-1251" %>
<%@ taglib uri="/struts/struts-bean" prefix="bean" %>
<%@ taglib uri="/struts/struts-html" prefix="html" %>
<html:html>
<HEAD>
<script LANGUAGE="JavaScript">
isIE = document.all ? true:false;
if ( isIE ) {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
	document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}

</script>

<TITLE>W A R N I N G !</TITLE>

<META NAME="Name" CONTENT="Warning Screen">
<META NAME="Description" CONTENT="Displays System Access Warning">


</HEAD>

<BODY >
<bean:struts id="logon_forward" forward="logon/prompt"/>
<FORM action='<%=request.getContextPath()%><bean:write name="logon_forward" property="path"/>'>
<H1 ALIGN="CENTER">
<FONT COLOR="#FF0000" SIZE="6" FACE="ARIAL">
<I><U>W A R N I N G !</U></I>
</FONT>
</H1>
<CENTER>
<TABLE WIDTH=650 CELLSPACING=10>
    <TR>
        <TD ALIGN=CENTER>
           <FONT COLOR="#000000" FACE="ARIAL"><B><I>
           You are attempting to access a private Web Site protected by a Security System.
           Access to and use of this facility requires explicit, current authorization and is<BR>
           strictly limited.
           </I></B></FONT>
        </TD>
    </TR>
        <TD ALIGN=CENTER>
            <FONT COLOR="#000000" FACE="ARIAL"><B><I>
            Unauthorized, or any attempt at unauthorized access, usage, copying, alteration,
            destruction, or damage to its data, programs or equipment may violate the<BR>
            Federal Computer Fraud and Abuse Act of 1986 as well as applicable<BR>
            state law and/or civil liability.
            </I></B></FONT>
        </TD>
    </TR>
        <TD ALIGN=CENTER>
            <FONT COLOR="#000000" FACE="ARIAL"><B><I>
            With the before mentioned understood, should you proceed further, you may<BR>
            subject yourself to investigation that could lead to prosecution should you<BR> not have
            authorization or violate any of these restrictions.
            </I></B></FONT>
        </TD>
    </TR>
	<TR><TD ALIGN=CENTER><FONT COLOR="#000000" FACE="ARIAL"><B><I>
			The EAGLS System that you are accessing and information about the operation,<BR>
			maintenance and upgrades of the EAGLS System are confidential and proprietary to <BR>
			Bank of America, N.A. (USA).  The EAGLS System is to be used by authorized <BR>
			persons solely for the governmental purposes described in <BR>
			General Services Administration Contract GSA No. GS-23F-98004.
	 </I></B></FONT>
	 </TD> </TR>
</TABLE>
<BR>

<html:submit value="CONTINUE" />
<%--
<INPUT TYPE="BUTTON" VALUE="CONTINUE" onclick="window.location.href='<bean:write name="logon_forward" property="path"/>'">
--%>

</CENTER>
</FORM>
</BODY>
</html:html>
