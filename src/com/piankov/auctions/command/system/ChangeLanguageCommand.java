package com.piankov.auctions.command.system;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute(ParameterConstant.PARAMETER_LANGUAGE, request.getParameter(ParameterConstant.PARAMETER_LANGUAGE_ID));
            response.sendRedirect(request.getHeader(ParameterConstant.REFER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //check if refered empty
    }
}
