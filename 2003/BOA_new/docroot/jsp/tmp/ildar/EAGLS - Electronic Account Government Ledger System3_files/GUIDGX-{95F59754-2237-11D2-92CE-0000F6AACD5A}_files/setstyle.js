isIE = document.all ? true:false;

if ( isIE ) {
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="/gsa/iestyle.css">' );
}
else {
        document.write( '<LINK REL=STYLESHEET TYPE="text/css" HREF="/gsa/nnstyle.css">' );
}


