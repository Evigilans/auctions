package com.piankov.auctions.command.bid;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.controller.CommandType;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;
import com.piankov.auctions.validator.AuctionValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CancelBidCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {
        try {
            AuctionValidator auctionValidator = new AuctionValidator();

            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

            if (auctionValidator.validateBid(parameterMap)) {
                AuctionAction auctionAction = new AuctionAction();

                String bidID = request.getParameter(ParameterConstant.PARAMETER_BID_ID);
                Auction auction = auctionAction.findBidById(bidID);

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
        } catch (UnauthorizedAccessException | CommandExecutionException e) {
            e.printStackTrace();
        }
    }
}
