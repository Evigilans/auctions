package com.piankov.auctions.servlet;

import com.piankov.auctions.client.Client;
import com.piankov.auctions.connection.ConnectionWrapper;
import com.piankov.auctions.util.ApplicationUtil;
import com.piankov.auctions.util.DatabaseUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        Client client = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            ConnectionWrapper conn = ApplicationUtil.getStoredConnection(request);
            try {
                client = DatabaseUtil.findClient(conn, userName, password);
                if (client == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        if (hasError) {
            client = new Client();
            client.setName(userName);
            client.setPasswordHash(password);
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", client);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            ApplicationUtil.storeLoginedClient(session, client);
            if (remember) {
                ApplicationUtil.storeClientCookie(response, client);
            } else {
                ApplicationUtil.deleteClientCookie(response);
            }
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
}