package com.piankov.auctions.command.user;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            request.getSession().setAttribute("user", null);
            request.getRequestDispatcher("pages/home.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new CommandExecutionException("An error occurred during execution a command.");
        }
    }
}
