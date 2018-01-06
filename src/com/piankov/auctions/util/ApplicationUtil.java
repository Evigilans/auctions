package com.piankov.auctions.util;

import com.piankov.auctions.client.Client;
import com.piankov.auctions.connection.ConnectionWrapper;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationUtil {
    private static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_CLIENT_NAME_IN_COOKIE";

    public static void storeConnection(ServletRequest request, ConnectionWrapper connectionWrapper) {
        request.setAttribute(ATT_NAME_CONNECTION, connectionWrapper);
    }

    public static ConnectionWrapper getStoredConnection(ServletRequest request) {
        return (ConnectionWrapper) request.getAttribute(ATT_NAME_CONNECTION);
    }

    public static void storeLoginedClient(HttpSession session, Client loginedClient) {
        session.setAttribute("loginedClient", loginedClient);
    }

    public static Client getLoginedClient(HttpSession session) {
        return (Client) session.getAttribute("loginedClient");
    }

    public static void storeClientCookie(HttpServletResponse response, Client client) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, client.getName());
        cookieUserName.setMaxAge(24 * 60 * 60); //?????????????
        response.addCookie(cookieUserName);
    }

    public static String getClientNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteClientCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
