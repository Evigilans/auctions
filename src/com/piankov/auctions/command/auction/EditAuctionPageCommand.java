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

public class EditAuctionPageCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(EditAuctionPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Edit Auction Page' command.");

        try {
            String page;

            AuctionAction auctionAction = new AuctionAction();

            User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);
            String auctionId = request.getParameter(ParameterConstant.PARAMETER_AUCTION_ID);

            Auction auction = auctionAction.findAuctionById(auctionId);

            if (user.isAdmin() || auction.getLot().getOwner().equals(user)) {
                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                page = PageConstant.PAGE_EDIT_AUCTION;
            } else {
                page = PageConstant.PAGE_EDIT_AUCTION;
                request.setAttribute(ParameterConstant.PARAMETER_ERROR_MESSAGE, "error.access");
            }

            LOGGER.info("Forwarding...");
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw new CommandExecutionException("An exception occurred during 'Edit Auction Page' command execution.", e);
        }
    }
}
