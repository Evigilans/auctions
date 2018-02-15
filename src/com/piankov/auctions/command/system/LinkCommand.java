package com.piankov.auctions.command.system;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LinkCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(LinkCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Link Command' command.");

        try {
            String url = request.getParameter(ParameterConstant.PARAMETER_URL);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException | IOException e) {
            throw  new CommandExecutionException("An exception occurred during 'Link Command' command execution.", e);
        }
    }
}
