package com.piankov.auctions.command.system;

import com.piankov.auctions.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getSession().setAttribute("language", request.getParameter("language_id"));
            response.sendRedirect(request.getHeader("Referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //check if refered empty
    }
}
