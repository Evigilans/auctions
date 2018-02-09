package com.piankov.auctions.command.user;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.creator.UserCreator;
import com.piankov.auctions.dao.UserDAO;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class RegistrationCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        DataValidator dataValidator = new DataValidator();
        Map<String, String[]> parameterMap = request.getParameterMap();

        if (dataValidator.validateRegistrationData(parameterMap)) {
            try (UserDAO userDAO = new UserDAO()) {
                UserCreator userCreator = new UserCreator();
                User user = userCreator.buildEntityFromMap(parameterMap);

                long generatedId = userDAO.create(user);
                user.setId(generatedId);

                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
            } catch (SQLException | ServletException | IOException e) {
                throw new CommandExecutionException("An error occurred during execution a command.");
            }
        }
    }
}
