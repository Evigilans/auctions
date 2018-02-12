package com.piankov.auctions.command.entity;

import com.piankov.auctions.action.AuctionAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.controller.CommandType;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;
import com.piankov.auctions.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MakeBidCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(MakeBidCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        DataValidator dataValidator = new DataValidator();
        AuctionAction auctionAction = new AuctionAction();

        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

        if (dataValidator.validateBidData(parameterMap)) {
            try {
                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);
                auctionAction.makeBid(parameterMap, user);

                request.setAttribute(ParameterConstant.PARAMETER_AUCTION_ID, parameterMap.get(ParameterConstant.PARAMETER_AUCTION_ID));
                CommandType.SHOW_ACTIVE_AUCTION.getCommand().execute(request, response);
            } catch (UnauthorizedAccessException | CommandExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
