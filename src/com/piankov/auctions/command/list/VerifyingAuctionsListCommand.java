package com.piankov.auctions.command.list;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.AuctionState;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VerifyingAuctionsListCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(VerifyingAuctionsListCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

            if (user.isAdmin()) {
                AuctionAction auctionAction = new AuctionAction();
                List<Auction> auctions = auctionAction.findAllAuctionsByState(AuctionState.ON_VERIFICATION.getValue());

                request.setAttribute(ParameterConstant.PARAMETER_AUCTIONS, auctions);
                request.getRequestDispatcher(PageConstant.PAGE_VERIFYING_AUCTIONS_LIST).forward(request, response);
            } else {
                //
            }
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Verifying Auction List' command execution.", e);
        }
    }
}
