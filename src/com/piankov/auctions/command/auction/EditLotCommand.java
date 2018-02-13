package com.piankov.auctions.command.auction;

import com.piankov.auctions.action.LotAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;
import com.piankov.auctions.validator.LotValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditLotCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {
        String lotId = request.getParameter(ParameterConstant.PARAMETER_LOT_ID);

        LotAction lotAction = new LotAction();
        Lot lot = lotAction.findLotById(lotId);

        if (lot != null) {
            User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

            if (user.equals(lot.getOwner()) || user.isAdmin()) {

                LotValidator lotValidator = new LotValidator();

                Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

                if (lotValidator.validateLotData(parameterMap)) {
                    try {
                        lot = lotAction.updateLot(lot, parameterMap);

                        response.sendRedirect(request.getHeader(ParameterConstant.REFERER));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //
            }
        }
    }
}
