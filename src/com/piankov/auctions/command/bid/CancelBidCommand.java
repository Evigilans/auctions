package com.piankov.auctions.command.bid;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelBidCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(CancelBidCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Cancel Bid' command.");

        try {
            AuctionAction auctionAction = new AuctionAction();

            String auctionID = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);
            Auction auction = auctionAction.findAuctionById(auctionID);
            auctionAction.deleteBid(auction);

            auction = auctionAction.findAuctionById(auctionID);

            request.getSession().setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
            request.getRequestDispatcher(PageConstant.PAGE_ACTIVE_AUCTION).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw new CommandExecutionException("An exception occurred during 'Cancel Bid' command execution.", e);
        }
    }
}
