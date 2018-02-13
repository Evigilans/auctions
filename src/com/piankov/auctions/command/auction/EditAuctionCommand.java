package com.piankov.auctions.command.auction;

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

public class EditAuctionCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(EditAuctionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Edit Auction' command.");

        try {
            String page;

            AuctionAction auctionAction = new AuctionAction();
            AuctionValidator auctionValidator = new AuctionValidator();

            String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);
            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
            Auction auction = auctionAction.findAuctionById(auctionId);

            if (auction != null) {
                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

                if (!auction.isActive() && auctionValidator.validateAuctionData(parameterMap)) {
                    if (user.equals(auction.getLot().getOwner()) || user.isAdmin()) {
                        auction = auctionAction.updateAuction(auction, parameterMap);

                        request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                        request.getRequestDispatcher(PageConstant.PAGE_ACTIVE_AUCTION).forward(request, response);
                    } else {
                        //
                    }
                } else {
                    //
                }
            } else {
                //
            }
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw new CommandExecutionException("An exception occurred during 'Edit Auction' command execution.", e);
        }

    }
}
