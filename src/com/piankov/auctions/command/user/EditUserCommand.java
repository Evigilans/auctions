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
import java.util.HashMap;
import java.util.Map;

public class EditUserCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(EditUserCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

            UserAction userAction = new UserAction();

            String userId = request.getParameter(ParameterConstant.PARAMETER_USER_ID);
            User user = userAction.updateUser(userId, parameterMap);

            request.getSession().setAttribute(ParameterConstant.PARAMETER_USER_PROFILE, user);
            request.getRequestDispatcher(PageConstant.PAGE_PROFILE).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Edit User' command execution.", e);
        }
    }
}
