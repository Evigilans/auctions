package com.piankov.auctions.command.list;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.AuctionState;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActiveAuctionsListCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(ActiveAuctionsListCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Active Auctions List' command.");

        try {
            AuctionAction auctionAction = new AuctionAction();

            LOGGER.info("Getting auction list.");
            List<Auction> auctions = auctionAction.findAllAuctionsByState(AuctionState.IN_PROGRESS.getValue());

            LOGGER.info("Forwarding...");
            request.setAttribute(ParameterConstant.PARAMETER_AUCTIONS, auctions);
            request.getRequestDispatcher(PageConstant.PAGE_ACTIVE_AUCTIONS_LIST).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw new CommandExecutionException("An exception occurred during 'Active Auctions List' command execution.", e);
        }
    }
}