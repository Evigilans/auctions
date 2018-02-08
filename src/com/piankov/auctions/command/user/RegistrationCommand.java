package com.piankov.auctions.command.user;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.validator.EntityValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        EntityValidator entityValidator = new EntityValidator();

        if (entityValidator.validateRegistrationData()) {
            try {
                User user = new User();

                user.setLogin(login);
                user.setName(name);
                user.setEmail(email);

                UserDAO clientDAO = new UserDAO();
                long generatedId = clientDAO.create(user);

                user.setId(generatedId);

                request.getSession().setAttribute("client", user);
                request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
            } catch (SQLException | ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
