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
import com.piankov.auctions.validator.LotValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateAuctionCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(CreateAuctionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        LOGGER.info("Execution 'Create Auction' command.");

        try {
            String page;

            LotValidator lotValidator = new LotValidator();
            AuctionValidator auctionValidator = new AuctionValidator();

            Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

            LOGGER.info("Validating lot and auction data.");
            if (lotValidator.validateLotData(parameterMap) && auctionValidator.validateAuctionData(parameterMap)) {
                AuctionAction auctionAction = new AuctionAction();
                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

                LOGGER.info("Defining user permission.");
                if (user.isClient()) {
                    LOGGER.info("Creating verifying auction.");
                    Auction auction = auctionAction.createVerifyingAuction(parameterMap, user);

                    request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                    page = PageConstant.PAGE_VERIFYING_AUCTION;
                } else {
                    LOGGER.info("Creating active auction.");
                    Auction auction = auctionAction.createActiveAuction(parameterMap, user);

                    request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                    page = PageConstant.PAGE_ACTIVE_AUCTION;
                }
            } else {
                LOGGER.info("Handling invalid data.");

                request.setAttribute(ParameterConstant.PARAMETER_AUCTION_ERROR_MESSAGE, "Illegal register data.");
                page = PageConstant.PAGE_CREATE_AUCTION;
            }

            LOGGER.info("Forwarding...");
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Create Auction' command execution.", e);
        }
    }
}
