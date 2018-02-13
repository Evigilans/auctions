package com.piankov.auctions.command.view;

import com.piankov.auctions.action.UserAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(ProfileCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

            if (user != null) {
                UserAction userAction = new UserAction();

                String userId = request.getParameter(ParameterConstant.PARAMETER_USER_ID);
                User userProfile = userAction.findUser(userId);

                if (userProfile != null) {
                    request.setAttribute(ParameterConstant.PARAMETER_USER_PROFILE, userProfile);
                    request.getRequestDispatcher(PageConstant.PAGE_PROFILE).forward(request, response);

                } else {
                    request.setAttribute(ParameterConstant.PARAMETER_ERROR_MESSAGE, "Can't find user profile!");
                    request.getRequestDispatcher(PageConstant.PAGE_PROFILE).forward(request, response);
                }
            } else {
                request.getRequestDispatcher(PageConstant.PAGE_PROFILE).forward(request, response);
            }
        } catch (IOException | ServletException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Profile' command execution.", e);
        }
    }
}
