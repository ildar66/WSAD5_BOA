if ( document.all){
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/iestyle.css">' );
}
else {
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="<%=request.getContextPath()%>/jsp/gsa/nnstyle.css">' );
}
