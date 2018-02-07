package com.piankov.auctions.command;

import com.piankov.auctions.dao.ClientDAO;
import com.piankov.auctions.entity.Client;
import com.piankov.auctions.validator.Validator;

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

        Validator validator = new Validator();

        if (validator.validateRegistrationData()) {
            try {
                Client client = new Client();

                client.setLogin(login);
                client.setName(name);
                client.setEmail(email);
                client.setPasswordHash(password);

                ClientDAO clientDAO = new ClientDAO();
                long  generatedId = clientDAO.create(client);

                client.setId(generatedId);

                request.getSession().setAttribute("client", client);
                request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
            } catch (SQLException | ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
