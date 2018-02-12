package com.piankov.auctions.command.edit;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;
import com.piankov.auctions.validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditBidCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {
        DataValidator dataValidator = new DataValidator();
        AuctionAction auctionAction = new AuctionAction();

        /////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String auction = request.getParameter(ParameterConstant.PARAMETER_BID_ID);

        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

        if (dataValidator.validateRegistrationData(parameterMap)) {
            try {
                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                request.getRequestDispatcher(PageConstant.PAGE_AUCTION).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}