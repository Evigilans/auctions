package com.piankov.auctions.command.edit;

import com.piankov.auctions.action.UserAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;
import com.piankov.auctions.validator.DataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class EditAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {
        DataValidator dataValidator = new DataValidator();
        UserAction userAction = new UserAction();

        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());


    }
}
