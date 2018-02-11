package com.piankov.auctions.command.entity;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.validator.EntityValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        if (request.getParameter("acceptAuction") != null) {
            EntityValidator entityValidator = new EntityValidator();
            AuctionAction auctionAction = new AuctionAction();

            String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);
            Auction auction = auctionAction.findAuctionById(auctionId);

            if (auction != null && entityValidator.validate(auction)) {
                try {
                    auction = auctionAction.verifyAuction(auction);

                    request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                    request.getRequestDispatcher(PageConstant.PAGE_AUCTION).forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
