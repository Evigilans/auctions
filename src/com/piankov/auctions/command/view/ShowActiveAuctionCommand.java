package com.piankov.auctions.command.view;

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

public class ShowActiveAuctionCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(ShowActiveAuctionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        AuctionAction auctionAction = new AuctionAction();

        String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);

        if (true) {
            try {
                Auction auction = auctionAction.findAuctionById(auctionId);

                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                request.getRequestDispatcher(PageConstant.PAGE_ACTIVE_AUCTION).forward(request, response);
            } catch (ServletException | IOException | ActionPerformingException e) {
                throw  new CommandExecutionException("An exception occurred during 'Show Active Auction' command execution.", e);
            }
        }
    }
}
