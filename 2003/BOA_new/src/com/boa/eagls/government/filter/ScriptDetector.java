package com.boa.eagls.government.filter;

/*
 * Enhydra Java Application Server Project
 *
 * The contents of this file are subject to the Enhydra Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License on
 * the Enhydra web site (http://www.enhydra.org/).
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific terms governing rights and limitations
 * under the License.
 *
 * The Initial Developer of the Enhydra Application Server is Lutris
 * Technologies, Inc. The Enhydra Application Server and portions created
 * by Lutris Technologies, Inc. are Copyright Lutris Technologies, Inc.
 * All Rights Reserved.
 *
 * Contributor(s): Christian Cryder, Jacob Kjome
 */


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//import org.enhydra.barracuda.core.view.*;

/**
 * This class simply detects whether or not the
 * client has scripting enabled.
 *
 * @author  Christian Cryder <christianc@enhydra.org>
 * @author  Jacob Kjome <hoju@visi.com>
 * @link http://barracuda.enhydra.org/Barracuda/src/org/enhydra/barracuda/core/helper/servlet/ ScriptDetector
 */
public class ScriptDetector {
    //public vars...eventually, these should probably be final
    protected static Logger logger = Logger.getLogger(ScriptDetector.class);

    /**
     * Flag indicating whether or not to check for client-side
     * scripting
     */
//temporarily turned off...there seems to be a bug in IE 5.5 where the client side script rewriting
//hoses the URL...I need to think about how to handle this. If you need to override this value
//you can do so through the assembler xml file - csc_013002
//    public static boolean DETECT_CLIENT_SCRIPTING_ENABLED = true;
//    public static boolean DETECT_CLIENT_SCRIPTING_ENABLED = false;

    /**
     * Flag added to URL's and Forms and sent with requests
     * allowing the server to determine if the client
     * supports scripting
     */
    public static final String SCRIPT_FLAG = "$csjs";
    private static final String SEPARATOR = System.getProperty("line.separator");
    private static final String SCRIPT_FLAG_EXISTS = "true";
    private static final String SCRIPT_FLAG_ABSENT = "false";

//    /**
//     * Flag added to URL's and Forms and sent with requests
//     * to avoid browser and proxy caching
//     */
//    private static final String UNIQUE_FLAG = "$u";

    //-------------------- ScriptDetector ------------------------
    /**
     * This method checks an incoming request to see if it has
     * a scripting flag which indicates whether or not the client
     * supports scripting. If not, it writes a response that
     * is sent back to the client which immediately causes the
     * client to return again. However, this time, with the scripting flag
     * set.
     * <p>Right now, we only call this method if we are handling a GET
     * request (the assumption being that if the client is POSTing data,
     * it probably came from the server in the first place and, thus, should
     * already have the script flag).</p>
     *
     * @param  req   the servlet request
     * @param  resp  the servlet response
     * @throws       java.io.IOException
     */
    public static void checkClientReq(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //the basic idea here is that we want to determine whether or
        //not a client has scripting enabled. So...we write out a response
//        if (DETECT_CLIENT_SCRIPTING_ENABLED &&
//            req.getParameter(SCRIPT_FLAG)==null) {
//        if (req.getSession().getAttribute(SCRIPT_FLAG)==null) {

        //csc_061202.1 - added
        //set the caching hdrs to prevent caching
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", System.currentTimeMillis());

        //build the response
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        StringBuffer buf = createUrl(req);

        resp.setContentLength(buf.length());
        out.print(buf.toString());
        //out.flush();
        resp.flushBuffer(); //instead of out.flush for Servlet 2.2 compatibility (Craig McClanahan http://w4.metronet.com/~wjm/tomcat/2000/Nov/msg00174.html)
        out.close();
//            return true;
//        } else {
//            return false;
//        }
    }

    protected static final boolean isRequestCheckingInProcess(HttpServletRequest req) {
        return req.getParameter(SCRIPT_FLAG)!=null;
    }
    protected static final boolean isJavaScriptSupported(HttpServletRequest req) {
        return SCRIPT_FLAG_EXISTS.equals(req.getParameter(SCRIPT_FLAG));
    }

    protected static final boolean isSessionChecked(HttpSession session) {
        return session.getAttribute(SCRIPT_FLAG) != null;
    }

    protected static final void markSessionChecked(HttpSession session) {
        session.setAttribute(SCRIPT_FLAG, new Boolean(true));
    }


    protected static final StringBuffer createUrl(HttpServletRequest req) throws IOException {
        //Note the following pseudo-code: decoded(getRequestURI) == decoded(getContextPath) + getServletPath + getPathInfo
        //Notice that getRequestURI already includes getPathInfo.  So, we don't need to append that to getRequestURI (comment based on old removed code)
        String queryStr = req.getQueryString();
        if (queryStr == null)
            queryStr = SCRIPT_FLAG;
        else
            queryStr += "&" + SCRIPT_FLAG;
        String url = new StringBuffer(30).append(req.getRequestURI()).append('?').append(queryStr).toString();

        StringBuffer buf = new StringBuffer(850); //around 828 chars calculated based on url being 30 chars
        //write a valid XHTML1.1 document so we don't run into future standards issues
        buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(SEPARATOR);
        buf.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">").append(SEPARATOR);
        buf.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en-US\">").append(SEPARATOR);
        buf.append("  <head>").append(SEPARATOR);
        buf.append("    <meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml; charset=UTF-8\" />").append(SEPARATOR);
        buf.append("    <meta http-equiv=\"Content-Script-Type\" content=\"application/x-javascript\" />").append(SEPARATOR);
        buf.append("    <title>Check for scripting support...</title>").append(SEPARATOR);
        buf.append("    <script type=\"text/javascript\">").append(SEPARATOR);
        buf.append("      location.replace('").append(url).append("=").append(SCRIPT_FLAG_EXISTS).append("');").append(SEPARATOR);
        buf.append("    </script>").append(SEPARATOR);
        buf.append("    <noscript>").append(SEPARATOR);
        buf.append("      <meta http-equiv=\"REFRESH\" CONTENT=\"5; URL=").append(url).append('=').append(SCRIPT_FLAG_ABSENT).append("\" />").append(SEPARATOR);
        buf.append("    <noscript>").append(SEPARATOR);
        buf.append("  </head>").append(SEPARATOR);
        buf.append("  <body>").append(SEPARATOR);
        buf.append("   <center>").append(SEPARATOR);
        buf.append("Your brouser is not support or disable JavaScript<br>You should enable this feature for comfortable use of the system").append(SEPARATOR);
        buf.append("<b>NOTE:</b> Some pages could work uncorrectly due to this limitation.").append(SEPARATOR);
        buf.append("    <h3>You will be redirected to the corrected URL in 5 seconds</h3>").append(SEPARATOR);
        buf.append("    <p>If you are not automatically redirected, please click <a href='").append(url).append('=').append(SCRIPT_FLAG_ABSENT).append("'>here</a></p>").append(SEPARATOR);
        buf.append("   </center>").append(SEPARATOR);
        buf.append("  </body>").append(SEPARATOR);
        buf.append("</html>");
        return buf;
    }

}

