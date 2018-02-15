package com.piankov.auctions.command.bid;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.validator.AuctionValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MakeBidCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(MakeBidCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Make Bid' command.");

        try {
            String page = null;

            AuctionValidator auctionValidator = new AuctionValidator();
            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

            if (auctionValidator.validateBid(parameterMap)) {
                AuctionAction auctionAction = new AuctionAction();

                String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);
                Auction auction = auctionAction.findAuctionById(auctionId);

                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

                if (!auction.getLot().getOwner().equals(user)) {
                    int bidValue = Integer.parseInt(request.getParameter(ParameterConstant.PARAMETER_BID_VALUE));
                    if (auction.isActive() && (auction.getStartPrice() < bidValue) && (auction.getCurrentMaximalBid() == null || auction.getCurrentMaximalBid().getValue() < bidValue)) {
                        auctionAction.makeBid(parameterMap, user);

                        auction = auctionAction.findAuctionById(auctionId);

                        page = PageConstant.PAGE_ACTIVE_AUCTION;
                        request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                    }
                }
            }

            LOGGER.info("Forwarding...");
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw new CommandExecutionException("An exception occurred during 'Make Bid' command execution.", e);
        }
    }
}
