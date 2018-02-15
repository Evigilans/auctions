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

public class PromoteUserCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(PromoteUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Promote User' command.");

        try {
            String page;

            User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

            LOGGER.info("Checking admin access.");
            if (user != null && user.isAdmin()) {
                UserAction userAction = new UserAction();

                String userId = request.getParameter(ParameterConstant.PARAMETER_USER_ID);
                User promotedUser = userAction.findUser(userId);

                LOGGER.info("Checking if promoted user is client.");
                if (promotedUser.isClient()) {
                    userAction.promoteUser(promotedUser);

                    request.setAttribute(ParameterConstant.PARAMETER_USER_PROFILE, promotedUser);
                    page = PageConstant.PAGE_PROFILE;
                } else {
                    page = PageConstant.PAGE_PROFILE;
                    request.setAttribute(ParameterConstant.PARAMETER_ERROR_MESSAGE, "error.promote.user");
                }

            } else {
                page = PageConstant.PAGE_PROFILE;
                request.setAttribute(ParameterConstant.PARAMETER_ERROR_MESSAGE, "error.access");
            }

            LOGGER.info("Forwarding...");
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ActionPerformingException | ServletException | IOException e) {
            LOGGER.error("An exception occurred during 'Promote User' command execution.", e);
            request.setAttribute(ParameterConstant.PARAMETER_ERROR_MESSAGE, "error.general");
        }
    }
}
