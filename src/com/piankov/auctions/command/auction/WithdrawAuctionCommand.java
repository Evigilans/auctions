package com.piankov.auctions.command.auction;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WithdrawAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {
        try {
            AuctionAction auctionAction = new AuctionAction();

            String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);
            Auction auction = auctionAction.findAuctionById(auctionId);
            if (auction != null) {
                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

                if (user.equals(auction.getLot().getOwner()) || user.isAdmin()) {
                    auction = auctionAction.withdrawAuction(auction);

                    request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                    request.getRequestDispatcher(PageConstant.PAGE_ENDED_AUCTION).forward(request, response);
                } else {
                    //
                }
            } else {
                //
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
