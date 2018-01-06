package com.piankov.auctions.filter;

import com.piankov.auctions.client.Client;
import com.piankov.auctions.connection.ConnectionWrapper;
import com.piankov.auctions.util.ApplicationUtil;
import com.piankov.auctions.util.DatabaseUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        Client clientInSession = ApplicationUtil.getLoginedClient(session);
        if (clientInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }

        ConnectionWrapper connectionWrapper = ApplicationUtil.getStoredConnection(request);

        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && connectionWrapper != null) {
            String userName = ApplicationUtil.getClientNameInCookie(req);
            try {
                Client user = DatabaseUtil.findClient(connectionWrapper, userName);
                ApplicationUtil.storeLoginedClient(session, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }

        chain.doFilter(request, response);
    }
}