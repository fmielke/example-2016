package de.htwberlin.mae.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestIdLoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    private static Logger log = LogManager.getRootLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            final String requestId = ((HttpServletRequest) servletRequest).getHeader("X-Request-ID");
            if (requestId != null) {
                System.out.println("request_id=" + requestId);
                log.warn("request_id=" + requestId);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}