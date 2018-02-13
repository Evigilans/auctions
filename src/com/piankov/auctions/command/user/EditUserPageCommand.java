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
        try {
            UserAction userAction = new UserAction();

            String auctionId = request.getParameter(ParameterConstant.PARAMETER_USER_ID);

            if (true) {

                User user = userAction.findUser(auctionId);

                request.setAttribute(ParameterConstant.PARAMETER_USER_PROFILE, user);
                request.getRequestDispatcher(PageConstant.PAGE_EDIT_PROFILE).forward(request, response);
            }
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Edit User Page' command execution.", e);
        }
    }
}
