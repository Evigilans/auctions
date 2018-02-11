package com.piankov.auctions.command.view;

import com.piankov.auctions.action.UserAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        UserAction userAction = new UserAction();

        String userId = request.getParameter(ParameterConstant.PARAMETER_USER_ID);
        try {
            User user = userAction.findUser(userId);

            request.setAttribute(ParameterConstant.PARAMETER_USER_PROFILE, user);
            request.getRequestDispatcher(PageConstant.PAGE_PROFILE).forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
