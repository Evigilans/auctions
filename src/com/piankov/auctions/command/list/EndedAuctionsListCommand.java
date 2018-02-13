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
import java.util.ArrayList;
import java.util.List;

public class EndedAuctionsListCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(EndedAuctionsListCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            AuctionAction auctionAction = new AuctionAction();


            List<Auction> auctions = new ArrayList<>();
            auctions.addAll(auctionAction.findAllAuctionsByState(AuctionState.SUCCESSFUL.getValue()));
            auctions.addAll(auctionAction.findAllAuctionsByState(AuctionState.UNSUCCESSFUL.getValue()));
            auctions.addAll(auctionAction.findAllAuctionsByState(AuctionState.WITHDRAW_FROM_SALES.getValue()));

            request.setAttribute(ParameterConstant.PARAMETER_AUCTIONS, auctions);
            request.getRequestDispatcher(PageConstant.PAGE_ENDED_AUCTIONS_LIST).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Ended Auctions List' command execution.", e);
        }
    }
}
