package com.piankov.auctions.command.user;

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

public class EditUserPageCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(EditUserPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Edit User Page' command.");

        try {
            String page;

            UserAction userAction = new UserAction();

            User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);
            String userId = request.getParameter(ParameterConstant.PARAMETER_USER_ID);

            if (user.isAdmin() || (userId.equals(String.valueOf(user.getId())))) {
                User editedUser = userAction.findUser(userId);

                request.setAttribute(ParameterConstant.PARAMETER_USER_PROFILE, editedUser);
                page = PageConstant.PAGE_EDIT_PROFILE;
            } else {
                page = PageConstant.PAGE_EDIT_PROFILE;
                request.setAttribute(ParameterConstant.PARAMETER_ERROR_MESSAGE, "error.access");
            }

            LOGGER.info("Forwarding...");
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw new CommandExecutionException("An exception occurred during 'Edit User Page' command execution.", e);
        }
    }
}
