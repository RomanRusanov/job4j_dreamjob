package ru.job4j.dream.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 18.11.2020
 * email roman9628@gmail.com
 * The class filter check all request url if url different from auth servlet,
 * when check(get user attribute) user was authenticated if not redirect to login page.
 */


public class AuthFilter implements Filter {
    /**
     * The method override init. Make some action before filter call doFilter.
     * @param filterConfig FilterConfig.
     * @throws ServletException Exception.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * The filter check request url equals "auth.do" if true return,
     * otherwise check was user authenticated in app.
     * @param sreq ServletRequest.
     * @param sresp ServletResponse.
     * @param chain FilterChain.
     * @throws IOException Exception.
     * @throws ServletException Exception.
     */
    @Override
    public void doFilter(ServletRequest sreq, ServletResponse sresp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sreq;
        HttpServletResponse resp = (HttpServletResponse) sresp;
        String uri = req.getRequestURI();
        if (uri.endsWith("auth.do") || uri.endsWith("reg.do")) {
            chain.doFilter(sreq, sresp);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        chain.doFilter(sreq, sresp);
    }

    @Override
    public void destroy() {
    }
}