package com.piankov.auctions.command.auction;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WithdrawAuctionCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(WithdrawAuctionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
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
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Withdraw Auction' command execution.", e);
        }
    }
}
