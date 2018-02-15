package com.piankov.auctions.command.list;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserAuctionsList implements Command {
    private static Logger LOGGER = LogManager.getLogger(UserAuctionsList.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'User Auctions List' command.");

        try {
            AuctionAction auctionAction = new AuctionAction();

            String userId = request.getParameter(ParameterConstant.PARAMETER_USER_ID);
            List<Auction> auctions = auctionAction.findUserAuctions(userId);

            LOGGER.info("Forwarding...");
            request.setAttribute(ParameterConstant.PARAMETER_AUCTIONS, auctions);
            request.getRequestDispatcher(PageConstant.PAGE_USER_AUCTIONS).forward(request, response);
        } catch (ActionPerformingException | ServletException | IOException e) {
            throw new CommandExecutionException("An exception occurred during 'User Auctions List' command execution.", e);
        }
    }
}
