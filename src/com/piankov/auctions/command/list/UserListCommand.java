package com.piankov.auctions.command.list;

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
import java.util.List;

public class UserListCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(UserListCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'User List' command.");

        try {
            UserAction userAction = new UserAction();

            List<User> users = userAction.findAllUsers();

            LOGGER.info("Forwarding...");
            request.setAttribute(ParameterConstant.PARAMETER_USERS, users);
            request.getRequestDispatcher(PageConstant.PAGE_USERS_LIST).forward(request, response);
        } catch (ActionPerformingException | ServletException | IOException e) {
            throw new CommandExecutionException("An exception occurred during 'User List' command execution.", e);
        }
    }
}
