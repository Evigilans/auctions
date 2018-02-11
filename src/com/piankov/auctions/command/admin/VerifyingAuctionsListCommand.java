package com.piankov.auctions.command.admin;

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

public class VerifyingAuctionsListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {
        System.out.println("11111");

        try {
            AuctionAction auctionAction = new AuctionAction();

            List<Auction> auctions = auctionAction.findAllAuctionsByState(AuctionState.ON_VERIFICATION.getValue());

            request.setAttribute(ParameterConstant.PARAMETER_AUCTIONS, auctions);
            request.getRequestDispatcher(PageConstant.PAGE_VERIFYING_AUCTIONS_LIST).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
