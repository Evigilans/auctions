package com.piankov.auctions.command.auction;

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

public class VerifyAuctionCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(VerifyAuctionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Verify Auction' command.");

        try {
            String page = null;

            AuctionAction auctionAction = new AuctionAction();

            String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);
            Auction auction = auctionAction.findAuctionById(auctionId);
            if (request.getParameter("acceptAuction") != null) {
                auction = auctionAction.verifyAuction(auction);

                page = PageConstant.PAGE_ACTIVE_AUCTION;
                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
            } else if (request.getParameter("declineAuction") != null) {
                auction = auctionAction.withdrawAuction(auction);

                page = PageConstant.PAGE_ENDED_AUCTION;
                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
            }

            LOGGER.info("Forwarding...");
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw new CommandExecutionException("An exception occurred during 'Verify Auction' command execution.", e);
        }
    }
}
