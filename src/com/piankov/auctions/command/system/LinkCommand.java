package com.piankov.auctions.command.system;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LinkCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = request.getParameter(ParameterConstant.PARAMETER_URL);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
