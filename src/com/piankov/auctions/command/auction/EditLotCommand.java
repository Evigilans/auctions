package com.piankov.auctions.command.auction;

import com.piankov.auctions.action.LotAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.validator.LotValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditLotCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(EditLotCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {

            String lotId = request.getParameter(ParameterConstant.PARAMETER_LOT_ID);

            LotAction lotAction = new LotAction();
            Lot lot = lotAction.findLotById(lotId);

            if (lot != null) {
                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

                if (user.equals(lot.getOwner()) || user.isAdmin()) {

                    LotValidator lotValidator = new LotValidator();

                    Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());

                    if (lotValidator.validateLotData(parameterMap)) {

                        lot = lotAction.updateLot(lot, parameterMap);

                        response.sendRedirect(request.getHeader(ParameterConstant.REFERER));
                    }
                }
            } else {
                //
            }
        } catch (IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Edit Lot' command execution.", e);
        }
    }
}
