package com.piankov.auctions.command.auction;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.validator.AuctionValidator;
import com.piankov.auctions.validator.LotValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;

        LotValidator lotValidator = new LotValidator();
        AuctionValidator auctionValidator = new AuctionValidator();

        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

        if (lotValidator.validateLotData(parameterMap) && auctionValidator.validateAuctionData(parameterMap)) {
            AuctionAction auctionAction = new AuctionAction();
            User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

            if (user.isClient()) {
                Auction auction = auctionAction.createVerifyingAuction(parameterMap, user);

                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                page = PageConstant.PAGE_VERIFYING_AUCTION;
            } else {
                System.out.println("==========================");
                Auction auction = auctionAction.createActiveAuction(parameterMap, user);

                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                page = PageConstant.PAGE_ACTIVE_AUCTION;
            }
        } else {
            request.setAttribute(ParameterConstant.PARAMETER_AUCTION_ERROR_MESSAGE, "Illegal register data.");
            page = PageConstant.PAGE_CREATE_AUCTION;
        }

        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
