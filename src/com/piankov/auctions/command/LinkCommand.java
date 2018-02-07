package com.piankov.auctions.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LinkCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getParameter("url");
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
