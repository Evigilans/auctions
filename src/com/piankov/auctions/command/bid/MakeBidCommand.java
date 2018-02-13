package com.piankov.auctions.command.bid;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.controller.CommandType;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.validator.AuctionValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MakeBidCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(MakeBidCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            AuctionValidator auctionValidator = new AuctionValidator();

            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

            if (auctionValidator.validateBid(parameterMap)) {
                AuctionAction auctionAction = new AuctionAction();

                String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);
                Auction auction = auctionAction.findAuctionById(auctionId);

                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

                if (!auction.getLot().getOwner().equals(user)) {
                    auctionAction.makeBid(parameterMap, user);

                    request.setAttribute(ParameterConstant.PARAMETER_AUCTION_ID, parameterMap.get(ParameterConstant.PARAMETER_AUCTION_ID));
                    CommandType.SHOW_ACTIVE_AUCTION.getCommand().execute(request, response);
                } else {
                    //
                }
            } else {
                //
            }
        } catch (CommandExecutionException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Make Bid' command execution.", e);
        }
    }
}
