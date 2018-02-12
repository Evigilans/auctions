package com.piankov.auctions.command.edit;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;
import com.piankov.auctions.validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {
        DataValidator dataValidator = new DataValidator();
        AuctionAction auctionAction = new AuctionAction();

        String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);

        Auction auction = auctionAction.findAuctionById(auctionId);

        if (auction == auction) {
            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

            if (dataValidator.validateRegistrationData(parameterMap)) {
                try {
                    auction = auctionAction.updateAuction(auction, parameterMap);

                    request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                    request.getRequestDispatcher(PageConstant.PAGE_AUCTION).forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
