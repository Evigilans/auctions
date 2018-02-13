package com.piankov.auctions.command.view;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowActiveAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AuctionAction auctionAction = new AuctionAction();

        String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);

        if (true) {
            try {
                Auction auction = auctionAction.findAuctionById(auctionId);

                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                request.getRequestDispatcher(PageConstant.PAGE_ACTIVE_AUCTION).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
