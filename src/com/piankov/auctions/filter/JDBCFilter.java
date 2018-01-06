package com.piankov.auctions.filter;

import com.piankov.auctions.connection.ConnectionWrapper;
import com.piankov.auctions.util.ApplicationUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@WebFilter(filterName = "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter {

    public JDBCFilter() {}

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}

    @Override
    public void destroy() {}

    private boolean needJDBC(HttpServletRequest request) {
        System.out.println("JDBC Filter");
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();

        String urlPattern = servletPath;

        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
        }

        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (this.needJDBC(req)) {
            System.out.println("Open Connection for: " + req.getServletPath());

            ConnectionWrapper connectionWrapper = null;
            try {
                //connectionWrapper = .getConnection();
                //connectionWrapper.setAutoCommit(false);

                ApplicationUtil.storeConnection(request, connectionWrapper);

                chain.doFilter(request, response);
                connectionWrapper.commit();
            } catch (Exception e) {
                e.printStackTrace();
                //connectionWrapper.rollback();
                //
            } finally {
                //connectionWrapper.close();
            }
        }
        else {
            chain.doFilter(request, response);
        }

    }
}