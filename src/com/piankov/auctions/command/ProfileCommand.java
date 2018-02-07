package com.piankov.auctions.command;

import com.piankov.auctions.dao.ClientDAO;
import com.piankov.auctions.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ClientDAO clientDAO = new ClientDAO();
        try {
            Client client = clientDAO.findById(request.getParameter("id"));
            request.setAttribute("client", client);
            request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
        }
    }
}
