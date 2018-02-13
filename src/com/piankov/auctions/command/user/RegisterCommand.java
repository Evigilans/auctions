package com.piankov.auctions.command.user;

import com.piankov.auctions.action.UserAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            UserValidator userValidator = new UserValidator();
            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

            if (userValidator.validateRegisterData(parameterMap)) {
                UserAction userAction = new UserAction();

                String email = parameterMap.get(ParameterConstant.PARAMETER_EMAIL)[0];

                if (userAction.canRegister(email)) {
                    User user = userAction.registerUser(parameterMap);

                    request.getSession().setAttribute(ParameterConstant.PARAMETER_USER, user);
                    request.setAttribute(ParameterConstant.PARAMETER_USER_PROFILE, user);
                    request.getRequestDispatcher(PageConstant.PAGE_PROFILE).forward(request, response);
                } else {
                    request.setAttribute(ParameterConstant.PARAMETER_LOGIN_ERROR_MESSAGE, "User with this login already exists.");
                    request.getRequestDispatcher(PageConstant.PAGE_LOGIN).forward(request, response);
                }
            } else {
                request.setAttribute(ParameterConstant.PARAMETER_LOGIN_ERROR_MESSAGE, "Illegal register data.");
                request.getRequestDispatcher(PageConstant.PAGE_LOGIN).forward(request, response);
            }
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Register' command execution.", e);
        }
    }
}
