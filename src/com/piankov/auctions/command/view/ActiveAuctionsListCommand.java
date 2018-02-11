package com.piankov.auctions.command.view;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.AuctionState;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActiveAuctionsListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws UnauthorizedAccessException, CommandExecutionException {
        try {
            AuctionAction auctionAction = new AuctionAction();

            List<Auction> auctions = auctionAction.findAllAuctionsByState(AuctionState.IN_PROGRESS.getValue());

            request.setAttribute(ParameterConstant.PARAMETER_AUCTIONS, auctions);
            request.getRequestDispatcher(PageConstant.PAGE_LIST).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}