package com.boa.eagls.government.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Needed to check is Java Script enabled in client browser.<br>
 * Can be modifyed to perforn a correct browser version check, if it is needed.
 * <p><small> DVI Company, 27.07.2003 18:31:32</small></p>
 * @author romanso
 */
public class ScriptFilter extends ScriptDetector implements Filter {


    /**In fact it is needed to check is Java script enabled ones for seach session - so for each
     * request we should determine is cheching already done*/
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        if (!isSessionChecked(((HttpServletRequest) servletRequest).getSession())) {
            if (!isRequestCheckingInProcess((HttpServletRequest) servletRequest)) {
                checkClientReq((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
            } else {
                if (isJavaScriptSupported((HttpServletRequest) servletRequest)) {
                    markSessionChecked(((HttpServletRequest) servletRequest).getSession());
                }
                chain.doFilter(servletRequest, servletResponse);
            }
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

}