package com.piankov.auctions.command.system;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Change Language' command.");

        try {
            request.getSession().setAttribute(ParameterConstant.PARAMETER_LANGUAGE, request.getParameter(ParameterConstant.PARAMETER_LANGUAGE_ID));
            response.sendRedirect(request.getHeader(ParameterConstant.REFERER));
        } catch (IOException e) {
            throw new CommandExecutionException("An exception occurred during 'Change Language' command execution.", e);
        }
    }
}
