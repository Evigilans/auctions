package com.piankov.auctions.command.entity;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        DataValidator dataValidator = new DataValidator();
        AuctionAction auctionAction = new AuctionAction();

        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

        if (dataValidator.validateAuctionData(parameterMap)) {
            try {
                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);
                Auction auction;

                if (user.isClient()) {
                    auction = auctionAction.createVerifyingAuction(parameterMap, user);
                } else {
                    auction = auctionAction.createActiveAuction(parameterMap, user);
                }

                request.setAttribute(ParameterConstant.PARAMETER_AUCTION, auction);
                request.getRequestDispatcher(PageConstant.PAGE_AUCTION).forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
