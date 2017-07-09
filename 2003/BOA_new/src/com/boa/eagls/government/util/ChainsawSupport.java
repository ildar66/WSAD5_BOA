package com.boa.eagls.government.util;

import org.apache.log4j.NDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p><small> DVI Company, 02.08.2003 20:12:56</small></p>
 * @author romanso
 */
public class ChainsawSupport implements Filter {
    public ChainsawSupport() {
        super();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        NDC.remove();
        NDC.push(request.getSession(true).getId());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}
