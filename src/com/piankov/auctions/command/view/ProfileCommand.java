package com.piankov.auctions.command.view;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        UserDAO clientDAO = new UserDAO();
        try {
            User user = clientDAO.findById(request.getParameter("id"));
            request.setAttribute("user", user);
            request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
        }
    }
}
